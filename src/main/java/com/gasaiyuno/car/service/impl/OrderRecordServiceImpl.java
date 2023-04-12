package com.gasaiyuno.car.service.impl;

import com.gasaiyuno.car.dao.OrderRecordRepository;
import com.gasaiyuno.car.po.OrderRecord;
import com.gasaiyuno.car.service.OrderRecordService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderRecordServiceImpl implements OrderRecordService {

    @Autowired
    private OrderRecordRepository orderRecordRepository;

    @Override
    public int getSellNumber(Long goodId) {
        int sum = orderRecordRepository.findByGoodsId(goodId);
        return sum;
    }

    @Override
    public List<OrderRecord> getAllRecord() {
        return null;
    }
}
