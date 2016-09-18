/**
 * 2016/3/1 15:53:44 Administrator created.
 * Generated by Caven.CodeBuilder (funiJava.mybatis_dao_imp 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.businessprocess.dao;

import com.dyb.platforms.fixfunds.services.business.businessprocess.entity.BusinessRunRecord;
import com.dyb.platforms.fixfunds.services.utils.core.dao.IbatisBaseStatement;
import com.dyb.platforms.fixfunds.services.utils.core.dao.IbatisDataDAOImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 业务单头模块 Ibatis Dao 实现
 * Created by Administrator on 2016/03/01.
 */
@Repository
public class BusinessRunRecordDao extends IbatisDataDAOImpl<BusinessRunRecord, String> implements IBusinessRunRecordDao {

    private IbatisBaseStatement ibatisBaseStatement;

    // dao的spring配置
    /*
    <bean id="userDao" class="com.yc.coresystem.services.business.user.daos.UserDao">
        <constructor-arg name="sqlSession" ref="sqlSession"/>
    </bean>
    */

    /**
     * 用Ibatis会话创建Dao
     * @param sqlSession Ibatis会话
     */
    @Autowired
    public BusinessRunRecordDao(SqlSessionTemplate sqlSession){
        super(sqlSession);
    }

	@Override
	protected IbatisBaseStatement getIbatisBaseStatement() {
		if (ibatisBaseStatement == null) {
			ibatisBaseStatement = new IbatisBaseStatement();
			ibatisBaseStatement.setInsertStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.insertObject");
			ibatisBaseStatement.setUpdateStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.updateObject");
			ibatisBaseStatement.setDeleteStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.deleteObject");
			ibatisBaseStatement.setGetHasDetailStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.getDetailObject");
			ibatisBaseStatement.setGetNoDetailStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.getBaseObject");
			ibatisBaseStatement.setWhereDeleteStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.deleteObjectByWhere");
			ibatisBaseStatement.setQueryCountStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.queryCount");
			ibatisBaseStatement.setQueryHasDetailListStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.queryDetailList");
			ibatisBaseStatement.setQueryNoDetailListStatementId("com.dyb.platforms.fixfunds.services.business.businessprocess.dao.IBusinessRunRecordDao.queryBaseList");
		}
		return ibatisBaseStatement;
	}

}
