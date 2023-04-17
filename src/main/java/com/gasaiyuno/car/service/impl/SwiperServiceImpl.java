package com.gasaiyuno.car.service.impl;


import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.SwiperRepository;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.mapper.SwipperMapper;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.service.SwiperService;
import com.gasaiyuno.car.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Autowired
    private SwiperRepository swiperRepository;

    @Autowired
    private SwipperMapper swipperMapper;


    @Override
    public List<Swiper> getList() {
        List<Swiper> SwiperList= swiperRepository.findByIsPublishAndIsDelete(EnumStatus.VAILD.getCode(), EnumStatus.INVAILD.getCode());

        return SwiperList;
    }

    @Override
    public Swiper findById(Long id) {
        Swiper swiper = swiperRepository.findById(id).get();
        return swiper;
    }

    @Override
    public int updateSwiper(Long id,Swiper swiper) {

        if (EmptyUtil.isEmpty(id)){
            int insert = swipperMapper.insert(swiper);
            return insert;
        }

        Swiper s = swiperRepository.findById(id).orElse(null);
        if (s == null) {
            throw new ServiceException(ReturnCode.error_code,"该轮播图不存在");
        }
        BeanUtils.copyProperties(swiper, s, MyBeanUtils.getNullPropertyNames(swiper));
        s.setUpdateTime(new Date());
//        Swiper save = swiperRepository.save(s);

        int update = swipperMapper.updateById(s);
    
        return update;

    }

    @Override
    public void deleteSwiper(Long id) {
        swipperMapper.deleteById(id);
    }
}
