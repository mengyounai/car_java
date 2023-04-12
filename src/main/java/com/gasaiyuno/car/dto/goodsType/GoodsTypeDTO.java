package com.gasaiyuno.car.dto.goodsType;

import com.gasaiyuno.car.vo.common.BaseVo;
import lombok.Data;

@Data
public class GoodsTypeDTO extends BaseVo {


    String[] childs;
    int level;
    Long id;
    int pid;
    Long categoryId; // 商品分类
    String GoodsTypeName;
    int isPublish;   //0下架，1上架

    public GoodsTypeDTO() {
        level = 1;
        pid = 0;
    }
}
