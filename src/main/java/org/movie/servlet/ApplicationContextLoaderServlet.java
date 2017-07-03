package org.movie.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.movie.tools.ApplicationContextUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 将spring上下文对象初始化到工具类中
 */
public class ApplicationContextLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ApplicationContextLoaderServlet() {
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(config.getServletContext());
		ApplicationContextUtil.setApplicationContext(webApplicationContext);
		
	}
    



}
