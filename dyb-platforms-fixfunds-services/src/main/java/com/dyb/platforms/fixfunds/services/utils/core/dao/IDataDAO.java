package com.dyb.platforms.fixfunds.services.utils.core.dao;

import com.dyb.platforms.fixfunds.services.utils.core.PageList;
import com.dyb.platforms.fixfunds.services.utils.core.QueryParams;

import java.util.List;

/**
 * Created by 魏源 on 2015/6/18 0018.
 */
public interface IDataDAO<T,PK> {
    /**
     * 插入一个对象
     *
     * @param object 要插入的对象
     * @return 影响的行数
     */
    int insertObject(T object);

    /**
     * 根据对象主键更新对象
     *
     * @param object 要更新的对象
     * @return 影响的行数
     */
    int updateObject(T object);

    /**
     * 删除对象
     *
     * @param key 主键
     * @return 影响的行数
     */
    int deleteObject(PK key);

    /**
     * 获取对象
     *
     * @param key    主键
     * @param detail 是否要获取对象的详细信息
     * @return 对象
     */
    T getObject(PK key, boolean detail);

    /**
     * 插入一组对象
     *
     * @param objects 对象数组
     * @return 影响的行数
     */
    int insertList(T[] objects);

    /**
     * 更新对象主键一组对象
     *
     * @param objects 对象数组
     * @return 影响的行数
     */
    int updateList(T[] objects);

    /**
     * 删除一组对象
     *
     * @param keies 主键数组
     * @return 影响的行数
     */
    int deleteList(PK[] keies);

    /**
     * 根据条件删除对象
     *
     * @param wheres 条件
     * @return 影响的行数
     */
    int deleteObjectByWhere(QueryParams wheres);

    /**
     * 查询对象数
     *
     * @param wheres 条件
     * @return 符合条件的对象个数
     */
    int queryCount(QueryParams wheres);

    /**
     * 查询对象列表
     *
     * @param wheres 条件
     * @param skip   在结果是跳过的数目
     * @param size   返回的最大数目,小于0则返回所有记录
     * @param detail 是还返回对象详细信息
     * @return 对象列表
     */
    List<T> queryList(QueryParams wheres, int skip, int size, boolean detail);

    /**
     * 按分页的方式查询对象
     *
     * @param wheres    条件
     * @param pageIndex 返回的页码
     * @param pageSize  页大小
     * @param detail    是否返回详细信息
     * @return 对象分页列表
     */
    PageList<T> queryListForPaged(QueryParams wheres, int pageIndex, int pageSize, boolean detail);
}
