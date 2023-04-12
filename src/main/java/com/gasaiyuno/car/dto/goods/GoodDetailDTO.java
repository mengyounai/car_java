package com.gasaiyuno.car.dto.goods;

import com.gasaiyuno.car.po.Goods;
import lombok.Data;

import java.util.List;

@Data
public class GoodDetailDTO {

    Goods basicInfo;
    List<GoodBaseInfo> pics;
    List<GoodBaseInfo> pics2;
    String content;
}
