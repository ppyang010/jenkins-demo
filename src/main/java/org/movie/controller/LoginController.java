package org.movie.controller;

import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.movie.model.ResultEnumCode;
import org.movie.model.ResultInfo;
import org.movie.model.User;
import org.movie.model.UserLoginStatus;
import org.movie.service.IUserService;
import org.movie.tools.ApplicationContextUtil;
import org.movie.tools.Constant;
import org.movie.tools.MemcachedUtil;
import org.movie.tools.MySessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 登陆相关
 * @author s
 *
 */
@Controller
@RequestMapping("/")
public class LoginController {
	
	private Logger logger=Logger.getLogger(LoginController.class);
	
	@Autowired
	MemcachedClient memcachedClient;
	
	@Autowired
	IUserService userService;
	
	@ResponseBody
	@RequestMapping("/login")
	public ResultInfo login(User user,HttpServletRequest request){
		ResultInfo resultInfo = new ResultInfo();
		
		if(StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getPhone()) 
				&& StringUtils.isEmpty(user.getUsername()) ){
			logger.error("param  is null !!!!!!");
			resultInfo.setResultInfo(ResultEnumCode.PARAM_IS_NULL);
		}
		
		User userInfo = userService.getUserInfo(user);
		if(StringUtils.isEmpty(userInfo)){
			logger.error("userinfo is null ！！！！");
			resultInfo.setResultInfo(ResultEnumCode.USERINFO_IS_NULL);
		}
		
		if(userInfo.getPassword().equals(user.getPassword()) ){
			logger.debug("login success!!!!");
			
			try {
				//获取登陆信息缓存
				UserLoginStatus loginStatus = memcachedClient.get("userLoginStatus"+userInfo.getUid());
				//缓存中没有登陆信息
				if(StringUtils.isEmpty(loginStatus)){
					loginStatus=new UserLoginStatus();
				}
				//缓存中存在登陆信息
				else if(!StringUtils.isEmpty(loginStatus) && loginStatus.getWwwLoginStatus()==Constant.LOGGED_IN){
					String sessionID = loginStatus.getWwwSessionID();
					//方式一清楚另一个登陆的session
					//removeOldSession(sessionID);
					//方式二修改session 并设置 另外设备登陆标识
					changeOldSession(sessionID);
					
				}
				//设置新的缓存
				loginStatus.setUid(userInfo.getUid());
				loginStatus.setWwwLoginStatus(Constant.LOGGED_IN);
				loginStatus.setWwwSessionID(request.getSession().getId());
				boolean set = memcachedClient.set("userLoginStatus"+userInfo.getUid(),60*30,loginStatus);
				
			} catch (TimeoutException | InterruptedException
					| MemcachedException e) {
				logger.error("get userLoginStatus fail !!!", e);
			}
			
			request.getSession().setAttribute("userInfo", userInfo);
			request.getSession().setAttribute("portalType", Constant.PORTALTYPE_WWW);//设置门户类型
			request.getSession().setAttribute("otherDeviceLogin", 0);//另外设备登陆标识
		}else{
			logger.error("password is null ！！！！");
			resultInfo.setResultInfo(ResultEnumCode.PASSWORD_ERROR);
		}
		return resultInfo;
		
	}
	
	/**
	 * 清除另一个登陆的session 并修改缓存
	 * @param sessionID
	 */
	private void removeOldSession(String sessionID){
		//清楚之前的session 和修改缓存（事件监听中进行）
		MySessionContext.getInstance()
			.getSession(sessionID).invalidate();
	}
	
	/**
	 * 修改另一个登陆的session 设置 另外设备登陆标识
	 *  并修改登陆状态缓存
	 * @param sessionID
	 */
	private void changeOldSession(String sessionID){
		MemcachedUtil.changLoginStatusCache(sessionID);
		
		HttpSession session = MySessionContext.getInstance()
			.getSession(sessionID);
		
		if(!StringUtils.isEmpty(session)){
			session.removeAttribute("userInfo");
			session.setAttribute("otherDeviceLogin", 1);//另外设备登陆标识
		}
	}
	
}
