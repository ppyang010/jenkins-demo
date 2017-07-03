package org.movie.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.movie.model.AccessLog;
import org.movie.service.IAccessLogService;
import org.movie.tools.Constant;
import org.movie.tools.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 日志（数据库日志）相关控制器
 * @author s
 *
 */
@Controller("/log")
@Scope("Prototype")
public class LogController {
	
	@Autowired
	IAccessLogService accessLogService;
	/**
	 * 添加访问记录
	 * @param request
	 */
	public void addAccessLog(HttpServletRequest request){
		AccessLog accessLog = new AccessLog();
		accessLog.setSessionid(request.getSession().getId());
		accessLog.setIp(RequestUtil.getIpAddress());
		accessLog.setPortaltype(Constant.PORTALTYPE_WWW);
		accessLog.setUri(request.getRequestURI());
		accessLog.setAgent(request.getHeader("user-agent"));
		accessLog.setAccesstime(new Date());
		Boolean flag=accessLogService.addAccessLog(accessLog);
		
	}
}
