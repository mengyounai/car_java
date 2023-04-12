package com.gasaiyuno.car.vo.goods;

import com.gasaiyuno.car.vo.common.BaseVo;
import com.gasaiyuno.car.vo.common.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSearchVo extends PageVo {

    private String GoodsName; // 商品名称
    private Long categoryId; // 商品分类
    private Double price; // 商品价格

}
