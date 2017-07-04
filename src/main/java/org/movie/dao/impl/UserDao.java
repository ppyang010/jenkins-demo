package org.movie.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.movie.dao.IUserDao;
import org.movie.model.User;
import org.movie.service.impl.NewsService;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao extends BaseDao implements IUserDao  {
	private static Logger logger = Logger.getLogger(UserDao.class);
	@Override
	public User getUserInfo(User user) {
		String sql="UserMapper.getUserInfo";
		try {
			List<User> list = this.getSqlSession().selectList(sql, user);
			if(list.size()>0){
				return list.get(0);
			}
			
		}catch(Exception e){
			logger.error("getUserInfo error!!!!!"+e);
		}
		
		return null;
	}

}
