package com.gasaiyuno.car.dto.goods;

import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.vo.common.BaseVo;
import lombok.Data;

@Data
public class GoodsPageDTO extends BaseVo {

    Long id; // 商品ID
    Long goodsId; // 商品种类ID
    String GoodsName; // 商品名称
    String description; // 商品描述
    String content; // 商品详情
    Double price; // 商品价格
    Double minPrice; // 商品降价后价格
    Integer stock; // 商品库存
    String brand; // 商品品牌
    String model; // 商品型号
    Long categoryId; // 商品分类
    String imageUrl; // 商品图片链接
    Integer isPublish;   //0下架，1上架
    Integer isLimit;      //是否限时秒杀
    Integer isRecommend;   //是否推荐
    Integer isDelete;   //0未删除，1已删除
    Integer numberSells;  //卖出数量
}
