package com.gasaiyuno.car.controller.admin;

import com.UpYun;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gasaiyuno.car.config.UpYunConfig;
import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.service.GoodsTypeService;
import com.gasaiyuno.car.service.SwiperService;
import com.gasaiyuno.car.service.UpYunService;
import com.gasaiyuno.car.util.CodeCreateUtils;
import com.gasaiyuno.car.util.EmptyUtil;
import com.gasaiyuno.car.vo.goods.GoodsSearchVo;
import com.upyun.UpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminGoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private UpYunService upYunService;


    @RequestMapping("/goodsList")
    public String goodsList(Integer currentPage, String goodsName, String goodsType, Model model) {

        //商品类别
        List<String> goodsTypeList = goodsTypeService.listType();

        model.addAttribute("goodsTypeList", goodsTypeList);

        int num = 8; //每次查询的数据条数

        //当前页数
        if (currentPage == null || currentPage == 0) {
            currentPage = 1;
        }
        model.addAttribute("currentPage", currentPage);

        //分页查询数据
        HashMap<String, Object> limit = new HashMap<>();
        limit.put("start", (currentPage - 1) * num);
        limit.put("num", num);

        if (!EmptyUtil.isEmpty(goodsName)) {
            limit.put("goods_name", goodsName);
        }
        if (!EmptyUtil.isEmpty(goodsType)) {
            Long category_id = goodsTypeService.findByTypeName(goodsType);
            limit.put("category_id", category_id);
        }
        List<Goods> goodsList = goodsService.queryGoods(limit);
        model.addAttribute("goodsList", goodsList);

        //总页数
        int page;
        int count = goodsList.size();
        if (EmptyUtil.isEmpty(goodsName) && EmptyUtil.isEmpty(goodsType)) {
            count = goodsService.countGoods();
        }
        if (count % num == 0) {
            page = count / num;
        } else {
            page = count / num + 1;
        }
        model.addAttribute("page", page);
        return "admin/goodsManage/goodsList";
    }

    @RequestMapping("/toGoodsEdit/{goodsId}")
    public String toGoodsEdit(@PathVariable Long goodsId, Model model) {
        //商品类别
        List<String> goodsTypeList = goodsTypeService.listType();

        model.addAttribute("goodsTypeList", goodsTypeList);

        Goods goods = goodsService.getGoodbyId(goodsId);
        String[] imgList = null;
        if (EmptyUtil.isNotEmpty(goods.getContent())) {
            imgList = goods.getContent().split(",");
        }

        GoodsType type = goodsTypeService.getType(goods.getCategoryId());
        model.addAttribute("typeName", type.getGoodsTypeName());
        model.addAttribute("imgList", imgList);

        model.addAttribute("goods", goods);
        return "admin/goodsManage/goodsEdit";
    }

    //商品添加
    @RequestMapping("/addGoods")
    public String addGoods(Model model) {
        Goods goods = new Goods();
        //商品类别
        List<String> goodsTypeList = goodsTypeService.listType();

        model.addAttribute("goodsTypeList", goodsTypeList);
        model.addAttribute("goods", goods);
        return "admin/goodsManage/goodsAdd";
    }


    //删除商品
    @PostMapping("/deleteGoods")
    public void deleteSwiper(Long goodsId) {
        goodsService.deleteGoods(goodsId);
    }


    //新增&更新轮播图
    @PostMapping("/updateGoods")
    public String post(Goods goods, @RequestParam("goodsType") String goodsType, @RequestParam("file00") MultipartFile file, @RequestParam("files") MultipartFile[] files, RedirectAttributes attributes, HttpSession session) throws IOException, UpException {

        String firstPicture = null;
        //如果file不为空，则上传图片到upyun
        if (!file.isEmpty()) {
            try {
                firstPicture = upYunService.upLoadImage(file);
            } catch (IOException e) {
                attributes.addFlashAttribute("message", "新增失败");
                return "redirect:/admin/goodsList";
            } catch (UpException e) {
                attributes.addFlashAttribute("message", "新增失败");
                return "redirect:/admin/goodsList";
            }
        }

        String content = "";
        //如果file不为空，则上传图片到upyun

        for (MultipartFile file2 : files) {
            if (EmptyUtil.isNotEmpty(file2.getOriginalFilename())){
                try {
                    content += upYunService.upLoadImage(file2);
                    content += ",";
                } catch (IOException e) {
                    attributes.addFlashAttribute("message", "新增失败");
                    return "redirect:/admin/goodsList";
                } catch (UpException e) {
                    attributes.addFlashAttribute("message", "新增失败");
                    return "redirect:/admin/goodsList";
                }
            }

        }

        if (EmptyUtil.isNotEmpty(content)) {
            String substring = content.substring(0, content.length() - 1);
            goods.setContent(substring);
        }

        Long goodsTypeId = goodsTypeService.findByTypeName(goodsType);

        goods.setCategoryId(goodsTypeId);
        goods.setImageUrl(firstPicture);

        int s;

        if (EmptyUtil.isEmpty(goods.getId())) {
            s = goodsService.updateGoods(null, goods);
            if (EmptyUtil.isEmpty(goods.getGoodsId())){
                goods.setGoodsId(goods.getId());
            }
            goodsService.updateGoods(goods.getId(), goods);
        } else {
            s = goodsService.updateGoods(goods.getId(), goods);
        }

        if (s == 1) {
            attributes.addFlashAttribute("message", "新增成功");
        } else {
            attributes.addFlashAttribute("message", "新增失败");
        }

        return "redirect:/admin/goodsList";

    }
}
