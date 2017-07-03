package org.movie.dao.impl;

import org.movie.dao.IAccessLogDao;
import org.movie.model.AccessLog;
import org.springframework.stereotype.Repository;

/**
 * 访问日志dao
 * @author s
 *
 */
@Repository
public class AccessLogDao extends BaseDao implements IAccessLogDao{

	@Override
	public Boolean addAccessLog(AccessLog accessLog) {
		
		String sql ="";
		this.getSqlSession().insert(sql, accessLog);
		return null;
	} 

	
}
