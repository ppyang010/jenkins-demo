package org.movie.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.movie.dao.INewsDao;
import org.movie.model.News;
import org.movie.model.PageBean;
import org.movie.service.INewsService;
import org.movie.tools.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class NewsService implements INewsService{
	@Autowired
	INewsDao newsDao;
	//log4j的使用方法
	
	private static Logger logger = Logger.getLogger(NewsService.class);
	/*
	 */
	public PageBean<News> getNewsListByPage(Integer pageNum,Integer pageSize) {
		
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		if(StringUtils.isEmpty(pageNum)||pageNum<=Constant.ZERO){
			pageNum=Constant.DEFAULTPAGENUM;
		}
		if(StringUtils.isEmpty(pageSize)||pageSize<=Constant.ZERO){
			pageSize=Constant.DEFAULTPAGESIZE;
		}
		PageBean<News> pageBean = new PageBean<News>();
		pageBean.setPageNum(pageNum);
		pageBean.setPageSize(pageSize);
		int begin=(pageNum-Constant.ONE)*pageSize;
		List<News> list = newsDao.getList(begin,pageSize);
		pageBean.setList(list);
		int total=newsDao.getTotal();
		pageBean.setTotal(total);
		pageBean.initTotalPage();
		
		return pageBean;
	}

	
	
}
