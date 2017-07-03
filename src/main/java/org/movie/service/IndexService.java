package org.movie.service;

import java.util.List;

import org.movie.dao.INewsDao;
import org.movie.model.News;
import org.movie.model.PageBean;
import org.movie.tools.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class IndexService {
	
	@Autowired
	INewsDao newsDao;
	/**
	 * 获取新闻list
	 */
	public List<News> getNewsListByPage(Integer pageNum,Integer pageSize) {
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
		return list;
	}

}
