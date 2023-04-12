package com.gasaiyuno.car.dao;


import com.gasaiyuno.car.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findAdminByAdminName(String AdminName);
}
