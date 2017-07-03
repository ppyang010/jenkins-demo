package org.movie.tools;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.context.support.StaticApplicationContext;
/**
 * 自定义session上下文
 * @author s
 *
 */
public class MySessionContext {
	
	private static final MySessionContext instance =new MySessionContext();
	private HashMap<String, Object> sessionMap;//session集合
	private int sessionount=0;
	
	private MySessionContext(){
		sessionMap=new HashMap<String, Object>();
	}
	


	public static MySessionContext getInstance(){
		return instance;
	}
	
	
	/**
	 * 添加session
	 * @param session
	 */
	public synchronized void addSession(HttpSession session){
		if(session!=null){
			sessionMap.put(session.getId(), session);
		}
	}
	
	/**
	 * 获取session
	 * @param sessionId
	 * @return
	 */
	public HttpSession getSession(String sessionId) {
		if(sessionId==null)return null;    
		return (HttpSession) sessionMap.get(sessionId);
		
	}
	
	public synchronized void delSession(String sessionId){
		if(sessionId!=null){
			sessionMap.remove(sessionId);
		}
	}
	/**
	 * session总数自增加
	 */
	public synchronized void incSessionCount(){
		sessionount++;
	}
	
	/**
	 * session总数自减
	 */
	public synchronized void decSessionCount(){
		sessionount--;
	}
	
	public int getSessionCount(){
		return sessionount;
	}
	
}
