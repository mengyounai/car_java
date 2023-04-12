package com.gasaiyuno.car.service.impl;

import com.gasaiyuno.car.dao.BusinessTypeRepository;
import com.gasaiyuno.car.po.BusinessType;
import com.gasaiyuno.car.service.BusinessTypeService;
import com.gasaiyuno.car.util.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Override
    public List<BusinessType> getList() {
        List<BusinessType> BusinessTypeList = businessTypeRepository.findByIsDelete(EnumStatus.INVAILD.getCode());
        return BusinessTypeList;
    }
}
