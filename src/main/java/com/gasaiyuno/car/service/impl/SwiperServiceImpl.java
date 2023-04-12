package com.gasaiyuno.car.service.impl;

import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.SwiperRepository;
import com.gasaiyuno.car.dao.UserRepository;
import com.gasaiyuno.car.dto.BaseUserInfo;
import com.gasaiyuno.car.dto.user.UserDTO;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.po.User;
import com.gasaiyuno.car.service.SwiperService;
import com.gasaiyuno.car.service.UserService;
import com.gasaiyuno.car.util.*;
import com.gasaiyuno.car.vo.user.LoginVo;
import com.gasaiyuno.car.vo.user.UserCreateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperRepository swiperRepository;


    @Override
    public List<Swiper> getList() {
        List<Swiper> SwiperList= swiperRepository.findByIsPublishAndIsDelete(EnumStatus.VAILD.getCode(), EnumStatus.INVAILD.getCode());

        return SwiperList;
    }
}
