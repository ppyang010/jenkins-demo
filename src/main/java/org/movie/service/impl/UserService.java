package org.movie.service.impl;

import org.apache.log4j.Logger;
import org.movie.dao.IUserDao;
import org.movie.model.User;
import org.movie.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class UserService implements IUserService{
	private Logger logger=Logger.getLogger(UserService.class);
	@Autowired
	IUserDao userDao;
	/**
	 * 获取用户信息 
	 * 可以根据id 手机号 用户名 邮箱获取
	 */
	@Override
	public User getUserInfo(User user) {
		User userInfo = userDao.getUserInfo(user);
		if(StringUtils.isEmpty(userInfo)){
			return null;
		}
		return userInfo;
		
	}

}
