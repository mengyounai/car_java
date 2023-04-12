package com.gasaiyuno.car.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
+订单记录
 */
@Entity
@Table(name = "t_orderRecord")
@Data
public class OrderRecord {


    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.INPUT)
    private Long id; // 订单ID
    private Long goodsId; // 商品种类ID
    private int status;  //订单状态，0未支付，1已支付
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
