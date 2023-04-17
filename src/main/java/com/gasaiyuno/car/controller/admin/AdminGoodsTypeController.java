package com.gasaiyuno.car.controller.admin;

import com.gasaiyuno.car.po.GoodsType;
import com.gasaiyuno.car.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminGoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @GetMapping("/goodsTypeList")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC)
                                Pageable pageable, Model model) {
        model.addAttribute("page", goodsTypeService.listType(pageable));
        return "admin/goodsTypeManage/types.html";
    }

    @GetMapping("/goodsType/input")
    public String input(Model model) {
        model.addAttribute("type", new GoodsType());
        return "admin/goodsTypeManage/types-input";
    }
//
    @GetMapping("/goodsType/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type", goodsTypeService.getType(id));
        return "admin/goodsTypeManage/types-input";
    }
//
    @PostMapping("/goodsTypeList")
    public String post(@Valid GoodsType type, BindingResult result, RedirectAttributes attributes,Model model) {
        GoodsType type1 = goodsTypeService.getByName(type.getGoodsTypeName());
        if (type1 != null) {
            result.rejectValue("GoodsTypeName", "nameError", "不能重复添加分类");
        }
        if (result.hasErrors()) {
            model.addAttribute("type", new GoodsType());
            model.addAttribute("message","不能重复添加分类");
            return "admin/goodsTypeManage/types-input";
        }
        int t = goodsTypeService.saveType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return "redirect:/admin/goodsTypeList";
    }

    @PostMapping("/goodsType/{id}")
    public String editPost(@Valid GoodsType type, BindingResult result, @PathVariable Long id, RedirectAttributes attributes,Model model) {
        GoodsType type1 = goodsTypeService.getByName(type.getGoodsTypeName());
        if (type1 != null) {
            result.rejectValue("GoodsTypeName", "nameError", "不能添加重复的分类");
        }
        if (result.hasErrors()) {
            model.addAttribute("type", new GoodsType());
            return "admin/goodsTypeManage/types-input";
        }
        int t = goodsTypeService.saveType(type);
        if (t == 0) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/goodsTypeList";
    }
//
    @GetMapping("/goodsType/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        goodsTypeService.deleteGoodsType(id);
        attributes.addFlashAttribute("message", "删除分类成功");
        return "redirect:/admin/goodsTypeList";
    }


}
