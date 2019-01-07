package com.test.util;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.List;


public class Pager implements Serializable{
	private Logger logger = Logger.getLogger(Pager.class);
	private int currentPage;//当前页
	private int rowCount;//总条数
	private int rowPerPage;//每页显示条数
	private int pageCount;//总页数
	private String url;
	private List dataList;
	
	public Pager() {
		
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		logger.debug("rowCount="+this.rowCount+
				"   rowPerPage="+rowPerPage);
		if(this.rowCount % this.rowPerPage == 0){
			this.pageCount =
					this.rowCount / this.rowPerPage;
		}else if(this.rowCount % this.rowPerPage > 0){
			this.pageCount = 
					this.rowCount / this.rowPerPage + 1;
		}else{
			this.pageCount = 0;
		}
		logger.debug("pageCount="+this.pageCount);
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public Pager(int rowCount, int rowPerPage, int currentPage) {
		//数据表中所有记录数
		this.rowCount = rowCount;
		//rowPerPage每页显示记录数
		this.rowPerPage = rowPerPage;
		this.currentPage = currentPage;
		//确定pageCount总页数
		if(this.rowCount % this.rowPerPage == 0){
			this.pageCount = 
					this.rowCount / this.rowPerPage;
		}else if(this.rowCount % this.rowPerPage > 0){
			this.pageCount =
					this.rowCount / this.rowPerPage + 1;
		}else{
			this.pageCount = 0;
		}
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
}
