package com.gasaiyuno.car.dao;



import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsTypeRepository extends JpaRepository<GoodsType, Long> {

    List<GoodsType> findByIsPublishAndIsDelete(int isPublish, int IsDelete);


}
