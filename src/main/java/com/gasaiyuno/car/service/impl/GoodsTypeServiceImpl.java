package com.gasaiyuno.car.service.impl;

import com.gasaiyuno.car.dao.GoodsTypeRepository;
import com.gasaiyuno.car.dto.goodsType.GoodsTypeDTO;
import com.gasaiyuno.car.po.GoodsType;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.service.GoodsTypeService;
import com.gasaiyuno.car.util.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeRepository goodsTypeRepository;

    @Override
    public GoodsType getType(Long id) {
        return null;
    }

    @Override
    public GoodsType saveType(GoodsType type) {
        return null;
    }

    @Override
    public List<GoodsTypeDTO> getGoodsTypeList() {
        List<GoodsType> goodsTypeList = goodsTypeRepository.findByIsPublishAndIsDelete(EnumStatus.VAILD.getCode(), EnumStatus.INVAILD.getCode());
        List<GoodsTypeDTO> goodTypeDTOS = new ArrayList<>();
        for (GoodsType goodsType : goodsTypeList) {
            GoodsTypeDTO goodTypeDTO = new GoodsTypeDTO();
            goodTypeDTO.copyIgnoreNullProperties(goodsType);
            goodTypeDTOS.add(goodTypeDTO);
        }
        return goodTypeDTOS;
    }
}
