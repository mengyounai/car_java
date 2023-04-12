package com.gasaiyuno.car.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
轮播图
 */
@Entity
@Table(name = "t_swiper")
@Data
public class Swiper  {

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private String url;
    private int index;
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
