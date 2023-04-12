package com.gasaiyuno.car.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
业务种类
 */
@Entity
@Table(name = "t_BusinessType")
@Data
public class BusinessType {

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    private String name;
    private String icon;
    @TableLogic
    private int isDelete;   //0未删除，1已删除

    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
