package org.movie.tools;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * spring 上下文对象工具类
 * @author s
 *
 */
public class ApplicationContextUtil {
	
	private static ApplicationContext  applicationContext;
	
	public static synchronized void setApplicationContext(ApplicationContext app){
		if(!StringUtils.isEmpty(app)){
			applicationContext=app;
		}
	}
	
	public static Object getBean(String name){
		if(StringUtils.isEmpty(name)) return null;
		return applicationContext.getBean(name);
	}
}
