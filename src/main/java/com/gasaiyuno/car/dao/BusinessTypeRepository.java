package com.gasaiyuno.car.dao;


import com.gasaiyuno.car.po.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long> {

    List<BusinessType> findByIsDelete(int IsDelete);

}
