package com.gasaiyuno.car.vo.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageVo extends BaseVo {

    protected Integer pageIndex = 0;

    protected Integer pageSize = 10;

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex - 1;
    }

}
