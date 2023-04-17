package com.gasaiyuno.car.controller.admin;

import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.UserService;
import com.gasaiyuno.car.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;


    @RequestMapping("/setting")
    public String setting(){
        return "admin/setting";
    }

    /*
        管理员管理
     */
    //管理员列表页面
    @RequestMapping("/adminList")
    public String adminList(String adminInfo,Model model){
//        HashMap<String,Object> map = new HashMap<>();
//        if(!EmptyUtil.isEmpty(adminInfo)){
//            map.put("info",adminInfo);
//        }
        List<User> adminList = userService.userList();
        model.addAttribute("adminList",adminList);
        return "admin/adminManage/adminList";
    }
    //编辑管理员页面
    @GetMapping("/adminEdit/{adminId}")
    public String editAdmin(@PathVariable Long adminId, Model model){
        User admin = userService.findById(adminId);
        model.addAttribute("admin",admin);

        return "admin/adminManage/adminEdit";

    }
    //添加管理员页面
    @RequestMapping("/toAddAdmin")
    public String toAddAdmin(){
        return "admin/adminManage/adminAdd";
    }
    //更新管理员
    @RequestMapping("/updateAdmin")
    @ResponseBody
    public int updateAdmin(User user){
        try{
            userService.updateUser(user);
            return 1;
        }catch (Exception e){
            return -1;
        }
    }



}
