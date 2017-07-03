package org.movie.listener;

import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.movie.model.User;
import org.movie.model.UserLoginStatus;
import org.movie.tools.ApplicationContextUtil;
import org.movie.tools.Constant;
import org.movie.tools.MemcachedUtil;
import org.movie.tools.MySessionContext;
import org.springframework.util.StringUtils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

/**
 * Application Lifecycle Listener implementation class SessionListener
 *
 */
public class SessionListener implements HttpSessionListener {
	
	//自己实现的一个session上下文
	private MySessionContext sessionContext=MySessionContext.getInstance();
    /**
     * Default constructor. 
     */
    public SessionListener() {
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent event)  {
    	sessionContext.addSession(event.getSession());
    	//session总数自增
    	sessionContext.incSessionCount();
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event )  { 
    	HttpSession session=event.getSession();
    	sessionContext.delSession(session.getId());
    	//session总数自减
    	sessionContext.decSessionCount();
    	//重置缓存中对应类型的登陆状态 
    	MemcachedUtil.changLoginStatusCache(session.getId());
    	
    	
    	
    	
    	
    	
    }
	
}
