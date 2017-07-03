package org.movie.filter;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * http与https互相转换
 */
public class SwitchHttpsFilter implements Filter {
	
	private static final Logger log = Logger.getLogger(SwitchHttpsFilter.class);
	
	private final static String HTTPS = "https://";
	    
	private Pattern pat = null;
    /**
     * Default constructor. 
     */
    public SwitchHttpsFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
     	HttpServletResponse response = (HttpServletResponse)servletResponse;
     	// 如果没有过滤表达式，全部不切换。
        if (pat == null)
        {
            chain.doFilter(request, response);
            if (log.isDebugEnabled())
            {
                log.debug("switch close.");
            }
            return;
        }
        String servletPath = request.getServletPath();
        if (log.isDebugEnabled())
        {
            log.debug("filter working with servletPath :" + servletPath);
        }
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
