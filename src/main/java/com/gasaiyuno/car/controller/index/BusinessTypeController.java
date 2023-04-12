package com.gasaiyuno.car.controller.index;

import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.po.BusinessType;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.service.BusinessTypeService;
import com.gasaiyuno.car.service.SwiperService;
import com.gasaiyuno.car.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/BusinessType")
public class BusinessTypeController {

    @Autowired
    private BusinessTypeService businessTypeService;

    @GetMapping("/getList")
    public ResponseUtils<List<BusinessType>> getList() {
        List<BusinessType> list = businessTypeService.getList();
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",list);
    }
}
