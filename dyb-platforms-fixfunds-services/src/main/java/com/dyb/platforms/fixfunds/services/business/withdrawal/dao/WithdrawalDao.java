/**
 * 2016/10/10 10:32:36 lenovo created.
 * Generated by Caven.CodeBuilder (funiJava.mybatis_dao_imp 1.0).
 */

package com.dyb.platforms.fixfunds.services.business.withdrawal.dao;

import com.dyb.platforms.fixfunds.services.business.withdrawal.entity.Withdrawal;
import com.dyb.platforms.fixfunds.services.utils.core.dao.IbatisBaseStatement;
import com.dyb.platforms.fixfunds.services.utils.core.dao.IbatisDataDAOImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 回购记录 Ibatis Dao 实现
 * Created by lenovo on 2016/10/10.
 */
@Repository
public class WithdrawalDao extends IbatisDataDAOImpl<Withdrawal, String> implements IWithdrawalDao {

    private IbatisBaseStatement ibatisBaseStatement;

    // dao的spring配置
    /*
    <bean id="withdrawalDao" class="com.dyb.platforms.fixfunds.services.business.withdrawal.entity.daos.WithdrawalDao">
        <constructor-arg name="sqlSession" ref="sqlSession"/>
    </bean>
    */

    /**
     * 用Ibatis会话创建Dao
     * @param sqlSession Ibatis会话
     */
    @Autowired
    public WithdrawalDao(SqlSessionTemplate sqlSession){
        super(sqlSession);
    }

	@Override
	protected IbatisBaseStatement getIbatisBaseStatement() {
		if (ibatisBaseStatement == null) {
			ibatisBaseStatement = new IbatisBaseStatement();
			ibatisBaseStatement.setInsertStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.insertObject");
			ibatisBaseStatement.setUpdateStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.updateObject");
			ibatisBaseStatement.setDeleteStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.deleteObject");
			ibatisBaseStatement.setGetHasDetailStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.getDetailObject");
			ibatisBaseStatement.setGetNoDetailStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.getBaseObject");
			ibatisBaseStatement.setWhereDeleteStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.deleteObjectByWhere");
			ibatisBaseStatement.setQueryCountStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.queryCount");
			ibatisBaseStatement.setQueryHasDetailListStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.queryDetailList");
			ibatisBaseStatement.setQueryNoDetailListStatementId("com.dyb.platforms.fixfunds.services.business.withdrawal.dao.IWithdrawalDao.queryBaseList");
		}
		return ibatisBaseStatement;
	}

}
