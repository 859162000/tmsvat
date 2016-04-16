package com.deloitte.tms.pl.core.commons.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 
* <p>Title: DaoPage.java</p>
* <p>Description: </p>
* <p>Copyright: Copyright (c) 2007</p>
* <p>Company: LTGames</p>
* @author linrz
* @date 2012-9-19
* @version 1.0
 */
public class DaoPage {

	/** 记录总数 **/
	private int recordCount;
	/** 记录总数 **/
	private int total;
	
	/** 当前页页数 **/
	private int pageIndex;
	
	/** 每页记录数 **/
	private int pageSize;
	
	/** 当前页数据集 **/
	private Collection<?> result = new ArrayList<Object>();
	
	private Collection<?> rows = new ArrayList<Object>();
	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		if (recordCount % pageSize == 0)
            return recordCount / pageSize;
        else
            return recordCount / pageSize + 1;
	}

	/**
	 * 获取当前页数
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 获取每页记录数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public int getRecordCount() {
		return recordCount;
	}
	
	public int getTotal() {
		return recordCount;
	}
	public Collection<?> getRows() {
		return result;
	}

	/**
	 * 当前页首条在数据库里的位置
	 * @return
	 */
	public int getRecordIndex() {
		return (pageIndex - 1) * pageSize;
	}

	/**
	 * 获取分页结果集
	 * 
	 * @return
	 */
	public Collection<?> getResult() {
		return result;
	}

	/**
	 * 构造函数
	 * 
	 * @param recordCount 总记录数
	 * @param pageIndex 当前页数
	 * @param pageSize 每页记录数
	 * @param result 当前页数据集
	 */
	public DaoPage(int recordCount, int pageIndex, int pageSize, List<?> result) {
		super();
		this.recordCount = recordCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.result = result;
	}

	public DaoPage() {
		super();
	}

	/**
	 * 设置当前页数
	 * 
	 * @param pageIndex
	 * @author dada
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 设置每页记录数
	 * 
	 * @param pageSize
	 * @author dada
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 设置总记录数
	 * 
	 * @param recordCount
	 * @author dada
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 设置记录集
	 * 
	 * @param result
	 * @author dada
	 */
	public void setResult(Collection<?> result) {
		this.result = result;
	}
	
}
