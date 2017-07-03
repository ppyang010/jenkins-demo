package org.movie.tools;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * request 工具类
 * @author s
 *
 */
public class RequestUtil {
	/**
	 * 获取当前的request
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		return request;
	}
	
	/**
     * 获取ip地址
     * @param request
     * @return
     */
	public static String  getIpAddress(){
		HttpServletRequest request = getRequest();
		//反向代理软件会带上对应的头来表示真实ip
		String ip = request.getHeader("x-forwarded-for"); 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		    ip = request.getHeader("Proxy-Client-IP"); 
		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		    ip = request.getHeader("WL-Proxy-Client-IP"); 
		} 
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
		    ip = request.getRemoteAddr(); 
		} 
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip; 
	}
}
