package com.gasaiyuno.car.service;

import com.gasaiyuno.car.po.OrderRecord;

import java.util.List;

public interface OrderRecordService {


    /*
    获取商品销售数量
     */
    int getSellNumber(Long goodId);

    /*
    获取全部记录
     */
    List<OrderRecord> getAllRecord();

}
