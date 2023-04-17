package com.gasaiyuno.car.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
商品
 */
@Entity
@Table(name = "t_goods")
@TableName("t_goods")
@Data
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // 商品ID
    private Long goodsId; // 商品种类ID
    private String GoodsName; // 商品名称
    private String description; // 商品描述
    private String content; // 商品详情
    private Double price; // 商品价格
    private Double minPrice; // 商品降价后价格
    private Integer stock; // 商品库存
    private String brand; // 商品品牌
    private String model; // 商品型号
    private Long categoryId; // 商品分类
    private String imageUrl; // 商品图片链接
    private int isPublish;   //0下架，1上架
    private int isLimit;      //是否限时秒杀
    private int isRecommend;   //是否推荐
    @TableLogic
    private int isDelete;   //0未删除，1已删除

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;

    private int dateStartInt;
    private int dateEndInt;

    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
