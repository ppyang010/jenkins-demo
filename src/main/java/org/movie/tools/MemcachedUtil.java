package org.movie.tools;

import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpSession;

import org.movie.model.User;
import org.movie.model.UserLoginStatus;
import org.springframework.util.StringUtils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * memcache工具类
 * @author s
 *
 */
public class MemcachedUtil {
	
	/**
	 * 根据session 
	 * 重置缓存中对应类型的登陆状态
	 * @param sessionID
	 */
	public static void changLoginStatusCache(String sessionID){
		HttpSession session = MySessionContext.getInstance()
				.getSession(sessionID);
			if(!StringUtils.isEmpty(session)){
				
				User userInfo = (User) session.getAttribute("userInfo");
		    	//session中有用户信息
		    	if(!StringUtils.isEmpty(userInfo)){
		    		MemcachedClient memcacahedClient = (MemcachedClient) ApplicationContextUtil
		    				.getBean("memcachedClient");
		    		//清楚缓存中的用户信息
		    		try {
		    			UserLoginStatus loginStatus=memcacahedClient.get("userLoginStatus"+userInfo.getUid());
		    			if(StringUtils.isEmpty(loginStatus)){
							loginStatus=new UserLoginStatus();
						}else{
							Integer portalType = (Integer) session.getAttribute("portalType");
							if(StringUtils.isEmpty(portalType)){
								portalType=Constant.PORTALTYPE_WWW;
							}
							loginStatus.setLoginSession(portalType, "");
							loginStatus.setLoginStatus(portalType,Constant.NOT_LOGGED_IN);
						}
		    			memcacahedClient.set("userLoginStatus"+userInfo.getUid(), 60*30, loginStatus);
					} catch (TimeoutException | InterruptedException | MemcachedException e) {
						
					}
		    	}
			}
	}

}
