package com.gasaiyuno.car.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.po.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

//    Page<GoodsPageDTO> findDTO(@Param("ew") Wrapper<GoodsPageDTO> wrapper, Page<GoodsPageDTO> page);

    Page<GoodsPageDTO> findDTO(@Param("ew") Wrapper<GoodsPageDTO> wrapper, Page<GoodsPageDTO> page);

    List<Goods> goods(@Param("GoodId") Long GoodId);

    //查询疾病
    List<Goods> queryGoods(Map map);

}
