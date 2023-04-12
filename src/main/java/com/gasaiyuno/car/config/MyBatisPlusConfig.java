package com.gasaiyuno.car.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlus配置类
 */
@MapperScan("com.gasaiyuno.car.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {
 
    /**
     * MyBatisPlus拦截器（用于分页）
     */
//    @Bean
//    public MybatisPlusInterceptor paginationInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        //添加MySQL的分页拦截器
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
//        return interceptor;
//    }

    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 序列生成器
     */
//    @Bean
//    public OracleKeyGenerator oracleKeyGenerator(){
//        return new OracleKeyGenerator();
//    }

}