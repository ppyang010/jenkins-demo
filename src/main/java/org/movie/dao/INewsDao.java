package org.movie.dao;

import java.util.List;

import org.movie.model.News;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface INewsDao {
//    int deleteByPrimaryKey(Integer nid);
//
//    int insert(News record);
//
//    int insertSelective(News record);
//
//    News selectByPrimaryKey(Integer nid);
//
//    int updateByPrimaryKeySelective(News record);
//
//    int updateByPrimaryKey(News record);
	public List<News> getList();
	public List<News> getList(Integer begin,Integer rows);
	public int getTotal();
}