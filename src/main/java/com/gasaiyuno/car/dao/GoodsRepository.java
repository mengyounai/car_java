package com.gasaiyuno.car.dao;



import com.gasaiyuno.car.dto.goods.GoodsPageDTO;
import com.gasaiyuno.car.po.Goods;
import com.gasaiyuno.car.util.EnumStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import javax.persistence.Converter;
import java.util.List;
import java.util.Map;

public interface GoodsRepository extends JpaRepositoryImplementation<Goods, Long> {

    //获取所有上架商品
    List<Goods> findByIsPublishAndIsDelete(int isPublish, int IsDelete);

    //获取所有限时秒杀的商品
    List<Goods> findByIsLimitAndIsPublishAndIsDelete(int isLimit,int isPublish, int IsDelete);

    //获取所有推荐的商品
    List<Goods> findByIsRecommendAndIsPublishAndIsDelete(int isRecommend,int isPublish, int IsDelete);

    List<Goods> findByGoodsId(Long goodsId);

    List<Goods> findByIsDelete(int IsDelete);

//    @Query(value = "select  new com.pojo.SentenceObject(list,list2) " +
//            " from tbl_basic_sentence list,tbl_knowledge_point list2 " +
//            " where  list.bid = :b_id and list.kid=list2.kid",nativeQuery = true)
//    List<GoodsPageDTO> findByBid2(@Param("b_id") Integer b_id);

    @Query(value = "SELECT g, SUM(o.status)  FROM Goods g LEFT JOIN OrderRecord o ON g.goodsId = o.goodsId ")
    Page<Object[]> findGoodsAndSales(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT g.*, COUNT(o.id) as sellNum FROM t_goods g " +
            "LEFT JOIN t_order_record o ON g.goods_id = o.goods_id " +
            "WHERE g.is_delete = 0 AND o.status = 1 " +
            "GROUP BY g.id")
    Page<Object[]> findGoodsWithSales(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT g.*, COUNT(o.id) as sellNum FROM Goods g " +
            "LEFT JOIN OrderRecord o ON g.goodsId = o.goodsId " +
            "WHERE g.isDelete = 0 AND o.status = 1 " +
            "GROUP BY g.id")
    Page<GoodsPageDTO> findGoodsWithSales2(Pageable pageable);


}
