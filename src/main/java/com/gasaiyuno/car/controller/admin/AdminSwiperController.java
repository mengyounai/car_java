package com.gasaiyuno.car.controller.admin;

import com.UpYun;
import com.gasaiyuno.car.config.UpYunConfig;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.SwiperService;
import com.gasaiyuno.car.service.UpYunService;
import com.gasaiyuno.car.util.CodeCreateUtils;
import com.gasaiyuno.car.util.EmptyUtil;
import com.gasaiyuno.car.util.UpYunUtils;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminSwiperController {

    @Autowired
    private SwiperService swiperService;

    @Autowired
    private UpYunService upYunService;


    //管理员列表页面
    @RequestMapping("/swiperList")
    public String swiperList(Model model) {

        List<Swiper> swiperList = swiperService.getList();
        model.addAttribute("swiperList", swiperList);
        return "admin/swiperManage/swiperList";
    }

    //管理员列表页面
    @RequestMapping("/addSwiper")
    public String addSwiper(Model model) {
        Swiper swiper = new Swiper();
//        List<Swiper> swiperList = swiperService.getList();
        model.addAttribute("swiper", swiper);
        return "admin/swiperManage/SwiperAdd";
    }

    //编辑管理员页面
    @RequestMapping("/swiperEdit/{swiperId}")
    public String editAdmin(@PathVariable Long swiperId, Model model) {
        Swiper swiper = swiperService.findById(swiperId);
        model.addAttribute("swiper", swiper);
        return "admin/swiperManage/swiperEdit";
    }

    //删除轮播图
    @PostMapping("/deleteSwiper")
    public void deleteSwiper(Long swiperId) {
        swiperService.deleteSwiper(swiperId);
    }





    //新增&更新轮播图
    @PostMapping("/updateSwiper")
    public String post(Swiper swiper, @RequestParam("file00") MultipartFile file, RedirectAttributes attributes, HttpSession session) throws IOException, UpException {

        String firstPicture = null;
        //如果file不为空，则上传图片到upyun
        if (!file.isEmpty()) {

            try {
                firstPicture = upYunService.upLoadImage(file);
            } catch (IOException e) {
                attributes.addFlashAttribute("message", "新增失败");
                return "redirect:/admin/swiperList";
            } catch (UpException e) {
                attributes.addFlashAttribute("message", "新增失败");
                return "redirect:/admin/swiperList";
            }
        }

        swiper.setUrl(firstPicture);
        int s;

        if (EmptyUtil.isEmpty(swiper.getId())){
            s = swiperService.updateSwiper(null, swiper);
        }else {
            s = swiperService.updateSwiper(swiper.getId(), swiper);
        }

        if (s == 1) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }

        return "redirect:/admin/swiperList";
    }
}
