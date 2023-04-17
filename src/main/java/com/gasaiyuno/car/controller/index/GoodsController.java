package com.gasaiyuno.car.controller.index;

import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dto.goods.GoodDetailDTO;
import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.util.ResponseUtils;
import com.gasaiyuno.car.vo.goods.GoodsSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    //获取限时秒杀列表
    @GetMapping("/getLimitList")
    public ResponseUtils<List<Goods>> getLimitList() {
        List<Goods> list = goodsService.getLimitList();
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",list);
    }
    //获取推荐商品列表
    @GetMapping("/getRecommendList")
    public ResponseUtils<List<Goods>> getRecommendList() {
        List<Goods> list = goodsService.getRecommendList();
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",list);
    }
    //分页获取商品列表
    @GetMapping("/getPageList")
    public ResponseUtils<Page<Goods>> getPageList(GoodsSearchVo goodsSearchVo) {
        Page<Goods> page = goodsService.getGoodList(goodsSearchVo);
        if (page.getContent().size() == 0 ){
            return new ResponseUtils<>(ReturnCode.error_code,"超出商品数量上限");
        }
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",page);
    }

    //分页获取商品列表
    @GetMapping("/getPageList2")
    public ResponseUtils<com.baomidou.mybatisplus.extension.plugins.pagination.Page<GoodsPageDTO>> getPageList2(GoodsSearchVo goodsSearchVo) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<GoodsPageDTO> page = goodsService.getGoodList2(goodsSearchVo);
        if (page.getRecords().size() == 0 ){
            return new ResponseUtils<>(ReturnCode.error_code,"超出商品数量上限");
        }
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",page);
    }

    //获取商品详情
    @GetMapping("/getGood")
        public ResponseUtils<GoodDetailDTO> getGood(Long goodsId) {
        GoodDetailDTO goodDetailDTO = goodsService.getGood(goodsId);
        return new ResponseUtils<>(ReturnCode.success_code,"查询成功",goodDetailDTO);
    }

}
