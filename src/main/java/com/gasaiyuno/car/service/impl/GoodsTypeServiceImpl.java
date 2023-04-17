package com.gasaiyuno.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.GoodsTypeRepository;
import com.gasaiyuno.car.dto.goodsType.GoodsTypeDTO;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.mapper.GoodsTypeMapper;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.service.GoodsTypeService;
import com.gasaiyuno.car.util.EmptyUtil;
import com.gasaiyuno.car.util.EnumStatus;
import com.gasaiyuno.car.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeRepository goodsTypeRepository;

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public GoodsType getType(Long id) {
        GoodsType goodsType = goodsTypeMapper.selectById(id);
        return goodsType;
    }

    @Override
    public Long findByTypeName(String name) {
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_type_name",name);
        GoodsType goodsType = goodsTypeMapper.selectOne(queryWrapper);
        Long id = goodsType.getId();
        return id;
    }

    @Override
    public GoodsType getByName(String name) {
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper();
        queryWrapper.eq("goods_type_name",name);
        GoodsType goodsType = goodsTypeMapper.selectOne(queryWrapper);
        return goodsType;
    }

    @Override
    public Integer saveType(GoodsType type) {
        if (EmptyUtil.isEmpty(type.getId())){
            Integer insert = goodsTypeMapper.insert(type);
            return insert;
        }

        GoodsType s = goodsTypeMapper.selectById(type.getId());
        if (s == null) {
            throw new ServiceException(ReturnCode.error_code,"该商品不存在");
        }
        BeanUtils.copyProperties(type, s, MyBeanUtils.getNullPropertyNames(type));
        s.setUpdateTime(new Date());
//        Swiper save = swiperRepository.save(s);

        int update = goodsTypeMapper.updateById(s);

        return update;
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

    @Override
    public List<String> listType() {
        List<String> list = new ArrayList<>();
        List<GoodsType> all = goodsTypeRepository.findAll();
        for (GoodsType goodsType : all) {
            list.add(goodsType.getGoodsTypeName());
        }
        return list;
    }

    @Override
    public Page<GoodsType> listType(Pageable pageable) {
        return goodsTypeRepository.findAll(pageable);
    }

    @Override
    public void deleteGoodsType(Long id) {
        goodsTypeRepository.deleteById(id);
    }
}
