package com.gasaiyuno.car.service;

import com.gasaiyuno.car.dto.goodsType.GoodsTypeDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoodsTypeService {

    GoodsType getType(Long id);

    Long findByTypeName(String name);

    GoodsType getByName(String name);

    Integer saveType(GoodsType type);

    List<GoodsTypeDTO> getGoodsTypeList();

    List<String> listType();

    Page<GoodsType> listType(Pageable pageable);

    void deleteGoodsType(Long id);
}
