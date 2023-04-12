package com.gasaiyuno.car.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderRecordMapper {

    int countByGoodId(@Param("GoodId") Long GoodId);


}
