package org.movie.cache;

import javax.servlet.http.HttpSession;

import org.jsoup.Connection.Request;
import org.movie.model.UserLoginStatus;
import org.movie.tools.Constant;
import org.movie.tools.RequestUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserLoginStatusCache {
	
	@Cacheable(value="userLoginStatus",key="'userLoginStatus_'+#uid")
	public UserLoginStatus  getCache(String uid){
		UserLoginStatus userLoginStatus = new UserLoginStatus();
		userLoginStatus.setUid(Integer.valueOf(uid));
		userLoginStatus.setWwwLoginStatus(Constant.LOGGED_IN);
		HttpSession session = RequestUtil.getRequest().getSession();
		if(!StringUtils.isEmpty(session)){
			userLoginStatus.setWwwSessionID(session.getId());
		}
		return userLoginStatus;
	}
}
