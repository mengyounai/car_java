package com.gasaiyuno.car.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gasaiyuno.car.po.OrderRecord;
import com.gasaiyuno.car.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {



}
