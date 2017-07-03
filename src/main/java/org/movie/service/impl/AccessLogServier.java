package org.movie.service.impl;

import org.movie.dao.IAccessLogDao;
import org.movie.model.AccessLog;
import org.movie.service.IAccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccessLogServier implements IAccessLogService {
	
	@Autowired
	IAccessLogDao accessLogDao;
	
	@Override
	public Boolean addAccessLog(AccessLog accessLog) {
		Boolean flag=accessLogDao.addAccessLog(accessLog);
		return null;
	}

}
