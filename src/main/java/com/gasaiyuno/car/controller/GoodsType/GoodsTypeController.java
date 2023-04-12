package com.gasaiyuno.car.controller.GoodsType;

import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dto.goodsType.GoodsTypeDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.GoodsType;
import com.gasaiyuno.car.service.GoodsTypeService;
import com.gasaiyuno.car.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    //获取限时秒杀列表
    @GetMapping("/getGoodsTypeList")
    public ResponseUtils<List<GoodsTypeDTO>> getGoodsTypeList() {
        List<GoodsTypeDTO> goodsTypeList = goodsTypeService.getGoodsTypeList();
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",goodsTypeList);
    }
}
