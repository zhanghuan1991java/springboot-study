package com.didispace.mybatis.pageInfo;

/**
 * 分页参数
 */
public class Page {
    int pageSize = 5;
    int pageIndex = 0;
    int totalPage;
    int min;
    int max;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageSize=" + pageSize +
                ", pageIndex=" + pageIndex +
                ", totalPage=" + totalPage +
                ", min=" + min +
                ", max=" + max +
                '}';
    }
}
