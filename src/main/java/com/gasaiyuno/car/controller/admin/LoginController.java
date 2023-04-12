package com.gasaiyuno.car.controller.admin;

import cn.hutool.core.lang.UUID;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.alibaba.fastjson.JSONObject;
import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.UserService;
import com.gasaiyuno.car.util.EmptyUtil;
import com.gasaiyuno.car.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index.html";
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
