package com.gasaiyuno.car.service;

import com.gasaiyuno.car.dto.goodsType.GoodsTypeDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;

import java.util.List;

public interface GoodsTypeService {

    GoodsType getType(Long id);

    GoodsType saveType(GoodsType type);

    List<GoodsTypeDTO> getGoodsTypeList();
}
