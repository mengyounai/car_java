package com.gasaiyuno.car.dao;


import com.gasaiyuno.car.po.Swiper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SwiperRepository extends JpaRepository<Swiper, Long> {

    List<Swiper> findByIsPublishAndIsDelete(int isPublish,int IsDelete);

}
