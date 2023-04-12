package com.gasaiyuno.car.service.impl;


import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.AdminRepository;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.mapper.AdminMapper;
import com.gasaiyuno.car.po.Admin;
import com.gasaiyuno.car.service.AdminService;
import com.gasaiyuno.car.util.EnumStatus;
import com.gasaiyuno.car.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int countAll() {
        return adminRepository.findAll().size();
    }

    @Override
    public Admin queryAdminById(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }


    @Override
    public Admin queryAdminByName(String adminName) {
        return adminRepository.findAdminByAdminName(adminName);
    }

    @Override
    public List<Admin> queryAdmin(Map map) {
        return adminMapper.queryAdmin(map);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(int id,Admin admin) {
        Admin a=adminRepository.findById(id).orElse(null);
        if (a == null) {
            throw new ServiceException(ReturnCode.error_code,"该管理员不存在");
        }
        BeanUtils.copyProperties(admin, a, MyBeanUtils.getNullPropertyNames(admin));
        return adminRepository.save(a);
    }

    @Override
    public void deleteAdmin(int adminId) {
        adminRepository.deleteById(adminId);
    }
}
