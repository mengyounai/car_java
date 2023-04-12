package com.gasaiyuno.car.dao;

import com.gasaiyuno.car.po.OrderRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {

    int findByGoodsId(Long goodId);
}
