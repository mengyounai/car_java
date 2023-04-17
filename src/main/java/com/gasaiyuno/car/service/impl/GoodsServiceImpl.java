package com.gasaiyuno.car.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gasaiyuno.car.constants.ReturnCode;
import com.gasaiyuno.car.dao.GoodsRepository;
import com.gasaiyuno.car.dto.goods.GoodBaseInfo;
import com.gasaiyuno.car.dto.goods.GoodDetailDTO;
import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.exception.ServiceException;
import com.gasaiyuno.car.mapper.GoodsMapper;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.po.Swiper;
import com.gasaiyuno.car.service.GoodsService;
import com.gasaiyuno.car.util.EmptyUtil;
import com.gasaiyuno.car.util.EnumStatus;
import com.gasaiyuno.car.util.MyBeanUtils;
import com.gasaiyuno.car.vo.goods.GoodsSearchVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsMapper goodsMapper;

    private static String contentConver(String content){
        String[] split = content.split(",");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<div id=\"description\" class=\"J_DetailSection tshop-psm tshop-psm-bdetaildes\">\n" +
                "<div class=\"content ke-post\">\n" +
                "<p>");
        for (String s : split) {
            System.out.println(s);
            String img = "<img class=\"img-ks-lazyload\" src=\""+s+"\" align=\"absmiddle\" />";
            stringBuffer.append(img);
        }
        stringBuffer.append("</p>\n" +
                "<p>&nbsp;</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"J_DcBottomRightWrap\">\n" +
                "<div id=\"J_DcBottomRight\" class=\"J_DcAsyn tb-shop\">\n" +
                "<div id=\"shop16668418117\" class=\"J_TModule\" data-widgetid=\"16668418117\" data-componentid=\"5003\" data-spm=\"110.0.5003-16668418117\" data-title=\"自定义内容区\">\n" +
                "<div class=\"skin-box tb-module tshop-pbsm tshop-pbsm-shop-self-defined\"><s class=\"skin-box-tp\"><b></b></s>\n" +
                "\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>");

        return stringBuffer.toString();
    }

    private Specification<Goods> getSpecification(GoodsSearchVo goodsSearchVo) {
        Long categoryId = goodsSearchVo.getCategoryId();
        Double price = goodsSearchVo.getPrice();
        String name = goodsSearchVo.getGoodsName();

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>(); // 所有的断言
            if (EmptyUtil.isNotEmpty(name)) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (EmptyUtil.isNotEmpty(categoryId)) {
                predicates.add(cb.equal(root.get("categoryId"), categoryId));
            }
            if (EmptyUtil.isNotEmpty(price)) {
                predicates.add(cb.equal(root.get("price"), price));
            }
            predicates.add(cb.equal(root.get("isDelete"), EnumStatus.INVAILD.getCode()));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<Goods> getLimitList() {
        List<Goods> LimitList = goodsRepository.findByIsLimitAndIsPublishAndIsDelete(
                EnumStatus.VAILD.getCode(),
                EnumStatus.VAILD.getCode(),
                EnumStatus.INVAILD.getCode()
        );
        return LimitList;
    }

    @Override
    public List<Goods> getRecommendList() {
        List<Goods> LimitList = goodsRepository.findByIsRecommendAndIsPublishAndIsDelete(
                EnumStatus.VAILD.getCode(),
                EnumStatus.VAILD.getCode(),
                EnumStatus.INVAILD.getCode()
        );
        return LimitList;
    }

    @Override
    public List<Goods> getAllList() {
        return null;
    }

    @Override
    public GoodDetailDTO getGood(Long goodsId) {
        if (EmptyUtil.isEmpty(goodsId))  return null ;
        List<Goods> goods = goodsRepository.findByGoodsId(goodsId);

        GoodDetailDTO goodDetailDTO =new GoodDetailDTO();
        List<GoodBaseInfo> goodBaseInfos = new ArrayList<>();
        for (int i = 0; i < goods.size(); i++) {
            GoodBaseInfo goodBaseInfo = new GoodBaseInfo();
            goodBaseInfo.setGoodsId(goodsId);
            goodBaseInfo.setPic(goods.get(i).getImageUrl());
            goodBaseInfo.setId(goods.get(i).getId());
            goodBaseInfos.add(goodBaseInfo);
        }
        String content = null;
        if (EmptyUtil.isNotEmpty(goods.get(0).getContent())){
            content = contentConver(goods.get(0).getContent());
        }
        goodDetailDTO.setBasicInfo(goods.get(0));
        goodDetailDTO.setPics(goodBaseInfos);
        goodDetailDTO.setContent(content);
        return goodDetailDTO;
    }

    @Override
    public Goods getGoodbyId(Long id) {
        Goods goods = goodsMapper.selectById(id);
        return goods;
    }

    @Override
    public Page<Goods> getGoodList(GoodsSearchVo goodsSearchVo) {
        Specification<Goods> specification = getSpecification(goodsSearchVo);
        Pageable pageable = PageRequest.of(goodsSearchVo.getPageIndex(), goodsSearchVo.getPageSize(), Sort.Direction.DESC, "id");
        Page<Goods> page = goodsRepository.findAll(specification, pageable);

        return page;
    }

    @Override
    @Transactional
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<GoodsPageDTO> getGoodList2(GoodsSearchVo goodsSearchVo) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<GoodsPageDTO> page =
                new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>
                        (0, 5);
        QueryWrapper<GoodsPageDTO> queryWrapper = new QueryWrapper<>();

        if (EmptyUtil.isNotEmpty(goodsSearchVo.getGoodsName())) {
            queryWrapper.like("goods_name", goodsSearchVo.getGoodsName());
        }
        if (EmptyUtil.isNotEmpty(goodsSearchVo.getCategoryId())) {
            queryWrapper.eq("category_id", goodsSearchVo.getCategoryId());
        }
        if (EmptyUtil.isNotEmpty(goodsSearchVo.getPrice())) {
            queryWrapper.like("price", goodsSearchVo.getPrice());
        }

//        queryWrapper.eq("is_delete",EnumStatus.INVAILD.getCode());

        return goodsMapper.findDTO(queryWrapper, page);
    }

    @Override
    @Transactional
    public int countGoods() {
        int byIsDelete = goodsRepository.findByIsDelete(EnumStatus.INVAILD.getCode()).size();
        return byIsDelete;
    }

    @Override
    @Transactional
    public List<Goods> queryGoods(Map map) {
        List<Goods> goods = goodsMapper.queryGoods(map);
        return goods;
    }

    @Override
    @Transactional
    public int updateGoods(Long id, Goods goods) {
        if (EmptyUtil.isEmpty(id)){
            int insert = goodsMapper.insert(goods);

            return insert;
        }

        Goods s = goodsRepository.findById(id).orElse(null);
        if (s == null) {
            throw new ServiceException(ReturnCode.error_code,"该商品不存在");
        }
        BeanUtils.copyProperties(goods, s, MyBeanUtils.getNullPropertyNames(goods));
        s.setUpdateTime(new Date());
//        Swiper save = swiperRepository.save(s);

        int update = goodsMapper.updateById(s);


        return update;
    }

    @Override
    @Transactional
    public int deleteGoods(Long id) {
        int i = goodsMapper.deleteById(id);
        return i;
    }
}
