package org.movie.model;

import java.util.List;

import org.springframework.util.StringUtils;


public class PageBean<T> {
	private int pageNum;// 当前页码
	private int pageSize;// 每页显示的记录数
	private int totalPage;// 总页数
	private int total;// 总记录数
	private List<T> list;// 每页显示的数据集合
	private boolean status;//状态  
	
	
	public void initTotalPage() {
		int temp=0;
		if(pageSize > 0 && total > 0 ){
			if((total % pageSize)>0){
				temp=total/pageSize+1;
			}else{
				temp=total/pageSize;
			}
		}
		this.setTotalPage(temp);
	}
	
	public boolean getStatus() {
		if(pageNum > 0 && pageSize > 0 && total > 0 && !StringUtils.isEmpty(list)){
			return true;
		}else{
			return false;
		}
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
