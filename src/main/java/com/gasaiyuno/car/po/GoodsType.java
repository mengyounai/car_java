package com.gasaiyuno.car.po;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
商品类型
 */
@Entity
@Table(name = "t_goodsType")
@Data
public class GoodsType {

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private Long categoryId; // 商品分类
    private String GoodsTypeName;
    private int isPublish;   //0下架，1上架
    @TableLogic
    private int isDelete;   //0未删除，1已删除
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
