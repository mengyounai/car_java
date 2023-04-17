package com.gasaiyuno.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.OrderRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRecordMapper  extends BaseMapper<OrderRecord> {

    int countByGoodId(@Param("GoodId") Long GoodId);


}
