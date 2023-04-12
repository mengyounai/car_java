package com.gasaiyuno.car.service;



import com.gasaiyuno.car.po.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    //返回总记录数
    int countAll();

    //通过Id查找管理员
    Admin queryAdminById(int adminId);

    //通过账户名查找管理员
    Admin queryAdminByName(String adminName);

    //查询管理员(包括模糊查询)
    List<Admin> queryAdmin(Map map);

    //新增管理员
    Admin addAdmin(Admin admin);

    //更新管理员
    Admin updateAdmin(int id,Admin admin);

    //删除管理员
    void deleteAdmin(int adminId);

}
