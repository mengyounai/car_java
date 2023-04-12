package com.gasaiyuno.car.service;

import com.gasaiyuno.car.dto.goods.GoodDetailDTO;
import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.vo.goods.GoodsSearchVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GoodsService {

    /*
    获取限时秒杀
     */
    List<Goods> getLimitList();

    /*
    获取推荐
     */
    List<Goods> getRecommendList();

    /*
    获取全部列表
     */
    List<Goods> getAllList();

    /*
    获取商品详情
     */
    GoodDetailDTO getGood(Long id);

    /*
    分页查询商品列表
     */
    Page<Goods> getGoodList(GoodsSearchVo goodsSearchVo);

    /*
    分页查询商品列表 mybatis版
    */
    com.baomidou.mybatisplus.extension.plugins.pagination.Page<GoodsPageDTO> getGoodList2(GoodsSearchVo goodsSearchVo);


}
