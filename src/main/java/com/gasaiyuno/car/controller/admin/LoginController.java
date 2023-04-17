package com.gasaiyuno.car.controller.admin;


import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;


@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public String loginPage() {
        return "admin/login.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
//            user.setPassword(null);
            session.setAttribute("user", user);
            model.addAttribute("num1",userService.countUser());
            model.addAttribute("num5",goodsService.countGoods());
            model.addAttribute("time",new Date());


            return "admin/main.html";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
        }

    }


//    @GetMapping("/login")
//    public String adminLogin(@RequestParam String username,
//                             @RequestParam String password, HttpServletRequest request,
//                             HttpServletResponse response,RedirectAttributes attributes) {
//        User user = userService.checkUser(username, password);
//        if (EmptyUtil.isEmpty(user)) {
//            attributes.addFlashAttribute("message", "用户名或密码错误");
//            return "redirect:/admin";
//        }
//        Map<String,String> map = new HashMap<>();
//        map.put("password",user.getPassword());
//        map.put("userName",user.getUsername());
//
//        attributes.addFlashAttribute("map", map);
//
//
////        session.setAttribute("user", user);
//        request.getSession().setAttribute("user", user);
//        return "admin/index";
//    }




    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute("user");
        Cookie cookie=new Cookie("token","token");
        //这里需要注意要将cookie路径设置为根目录
        cookie.setPath("/");
        //设置到期时间为一个月 默认-1关闭浏览器即消失
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/admin";
    }
}
