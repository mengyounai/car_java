package com.gasaiyuno.car.service;



import com.gasaiyuno.car.po.Swiper;


import java.util.List;

public interface SwiperService {

    List<Swiper> getList();

    Swiper findById(Long id);

    int updateSwiper(Long id,Swiper swiper);

    void deleteSwiper(Long id);



}
