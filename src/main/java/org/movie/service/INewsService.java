package org.movie.service;

import java.util.List;

import org.movie.model.News;
import org.movie.model.PageBean;

public interface INewsService {

	PageBean<News> getNewsListByPage(Integer pageNum, Integer pageSize);
	
}
