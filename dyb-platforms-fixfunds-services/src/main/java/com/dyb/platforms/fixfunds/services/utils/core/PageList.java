package com.dyb.platforms.fixfunds.services.utils.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 魏源 on 2015/6/12 0012.
 */
public class PageList<T> implements java.io.Serializable {
    private static final long serialVersionUID = -4169096025868641406L;
    private List<T> list = new ArrayList();
    private int pageIndex;
    private int pageCount;
    private int pageSize;
    private int totalSize;

    public PageList() {
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }


}
