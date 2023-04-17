package com.gasaiyuno.car.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gasaiyuno.car.po.Admin;
import com.gasaiyuno.car.po.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper  extends BaseMapper<Admin> {

    //返回总记录数
    int countAll();

    //通过Id查找管理员
    Admin queryAdminById(int adminId);

    //通过账户名查找管理员
    Admin queryAdminByName(String adminName);

    //查询管理员(包括模糊查询)
    List<Admin> queryAdmin(Map map);

    //新增管理员
    int addAdmin(Admin admin);

    //更新管理员
    int updateAdmin(Admin admin);

    //删除管理员
    int deleteAdmin(int adminId);
}
