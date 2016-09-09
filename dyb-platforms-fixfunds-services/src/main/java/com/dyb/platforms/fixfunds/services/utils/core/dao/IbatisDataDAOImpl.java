package com.dyb.platforms.fixfunds.services.utils.core.dao;

import com.dyb.platforms.fixfunds.services.utils.DybUtils;
import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/18 0018.
 */
public abstract class IbatisDataDAOImpl<T,PK> implements IIbatisDataDAO<T,PK> {
    private SqlSession sqlSession;

    protected abstract IbatisBaseStatement getIbatisBaseStatement();

    public IbatisDataDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public int insertObject(String statement, T object) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.insert(statement,object);
    }

    @Override
    public int updateObject(String statement, T object) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.update(statement,object);
    }

    @Override
    public int deleteObject(String statement, PK key) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.delete(statement,key);
    }

    @Override
    public T getObject(String statement, PK key) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.selectOne(statement,key);
    }

    @Override
    public int insertList(String statement, T[] objects) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        int info=0;
        for (T object:objects)
            info+=this.insertObject(this.getIbatisBaseStatement().getInsertStatementId(),object);
        return info;
    }

    @Override
    public int updateList(String statement, T[] objects) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        int info=0;
        for (T object:objects)
            info+=this.updateObject(this.getIbatisBaseStatement().getUpdateStatementId(), object);
        return info;
    }

    @Override
    public int deleteList(String statement, PK[] keies) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        int info=0;
        for (PK key:keies)
            info+=this.deleteObject(this.getIbatisBaseStatement().getDeleteStatementId(), key);
        return info;
    }

    @Override
    public int deleteObjectByWhere(String statement, QueryParams wheres) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.delete(statement,wheres);
    }

    @Override
    public int queryCount(String statement, QueryParams wheres) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        return this.sqlSession.selectOne(statement,wheres);
    }

    @Override
    public List<T> queryList(String statement, QueryParams wheres, int skip, int size) {
        if (DybUtils.isEmptyOrNull(statement))
            throw new IllegalArgumentException("statement参数不能为null或empty.");
        if (skip < 0)
            throw new IllegalArgumentException("skip参数不能小于0.");
        if (size<0)
            size= RowBounds.NO_ROW_LIMIT;
        if (wheres == null)
            wheres = new QueryParams();
        RowBounds rowBounds=new RowBounds(skip,size);
        return this.sqlSession.selectList(statement,wheres,rowBounds);
    }

    @Override
    public PageList<T> queryListForPaged(String listStatement, String countStatement, QueryParams wheres, int pageIndex, int pageSize) {
        if (DybUtils.isEmptyOrNull(listStatement))
            throw new IllegalArgumentException("listStatement参数不能为null或empty.");
        if (DybUtils.isEmptyOrNull(countStatement))
            throw new IllegalArgumentException("countStatement参数不能为null或empty.");
        if (pageIndex < 0)
            throw new IllegalArgumentException("pageIndex参数不能小于0.");
        if (pageSize < 0)
            throw new IllegalArgumentException("pageSize参数不能小于0.");
        List<T> list = queryList(listStatement, wheres, pageIndex * pageSize, pageSize);
        PageList<T> result = new PageList<T>();
        result.getList().addAll(list);
        result.setPageIndex(pageIndex);
        result.setPageSize(pageSize);
        int total = queryCount(countStatement, wheres);
        result.setTotalSize(total);
        if (total % pageSize == 0)
            result.setPageCount(total / pageSize);
        else
            result.setPageCount(total / pageSize + 1);
        return result;
    }

    @Override
    public int insertObject(T object) {
        return this.insertObject(getIbatisBaseStatement().getInsertStatementId(),object);
    }

    @Override
    public int updateObject(T object) {
        return this.updateObject(getIbatisBaseStatement().getUpdateStatementId(),object);
    }

    @Override
    public int deleteObject(PK key) {
        return this.deleteObject(getIbatisBaseStatement().getDeleteStatementId(),key);
    }

    @Override
    public T getObject(PK key, boolean detail) {
        return this.getObject(detail ? getIbatisBaseStatement().getGetHasDetailStatementId():getIbatisBaseStatement().getGetNoDetailStatementId(),key);
    }

    @Override
    public int insertList(T[] objects) {
        int info=0;
        for (T object:objects)
            info+=insertObject(object);
        return info;
    }

    @Override
    public int updateList(T[] objects) {
        int info=0;
        for (T object:objects)
            info+=updateObject(object);
        return info;
    }

    @Override
    public int deleteList(PK[] keies) {
        int info=0;
        for (PK key:keies)
            info+=deleteObject(key);
        return info;
    }

    @Override
    public int deleteObjectByWhere(QueryParams wheres) {
        return this.deleteObjectByWhere(getIbatisBaseStatement().getWhereDeleteStatementId(),wheres);
    }

    @Override
    public int queryCount(QueryParams wheres) {
        return this.queryCount(getIbatisBaseStatement().getQueryCountStatementId(),wheres);
    }

    @Override
    public List<T> queryList(QueryParams wheres, int skip, int size, boolean detail) {
        return this.queryList(detail ? getIbatisBaseStatement().getQueryHasDetailListStatementId():getIbatisBaseStatement().getQueryNoDetailListStatementId(),wheres,skip,size);
    }

    @Override
    public PageList<T> queryListForPaged(QueryParams wheres, int pageIndex, int pageSize, boolean detail) {
        return queryListForPaged(detail ? getIbatisBaseStatement().getQueryHasDetailListStatementId()
                : getIbatisBaseStatement().getQueryNoDetailListStatementId(), getIbatisBaseStatement()
                .getQueryCountStatementId(), wheres, pageIndex, pageSize);
    }
}
