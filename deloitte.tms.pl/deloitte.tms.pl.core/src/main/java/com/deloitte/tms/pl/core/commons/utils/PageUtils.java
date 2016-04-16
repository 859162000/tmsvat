package com.deloitte.tms.pl.core.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;



/**
 * 分页操作的工具类
 * 
 * @author dada
 */
public class PageUtils {
	
	public static Map prepareParams(Map params){
		Map<String,Object> result=new HashMap<String,Object>();
		if(params!=null){
			for (Object key : params.keySet()) {
				if(key!=null){
					String newkey=key.toString().replace("params.", "");
					result.put(newkey,params.get(key));
				}
			}
		}
		return result;
	}
	public static Integer getPageIndex(Map params){
		AssertHelper.notEmpty_assert(params,"分页参数错误:空");
		AssertHelper.notEmpty_assert(params.get("pageIndex"),"分页参数:pageIndex错误");
		return Integer.parseInt(params.get("pageIndex").toString());
	}
	public static Integer getPageNumber(Map params){
		AssertHelper.notEmpty_assert(params,"分页参数错误:空");
		AssertHelper.notEmpty_assert(params.get("pageNumber"),"分页参数:pageNumber错误");
		return Integer.parseInt(params.get("pageNumber").toString());
	}
	public static Integer getPageSize(Map params){
		AssertHelper.notEmpty_assert(params,"分页参数错误:空");
		AssertHelper.notEmpty_assert(params.get("pageSize"),"分页参数:pageSize错误");
		return Integer.parseInt(params.get("pageSize").toString());
	}

	/**
	 * 根据每页记录数和当前页页数返回当前页首条在数据库里的位置
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @author dada
	 */
	public static int getRecordIndex(int pageIndex, int pageSize) {
		return (pageIndex - 1) * pageSize;
	}
	public static DaoPage subList(List<?> result, int pageSize, int pageIndex) {
		DaoPage page = new DaoPage();
		List list = new ArrayList();
		int recordCount = result.size();

		if (pageSize == 0 || recordCount == 0) {
			list = result;
		} else {
			int pageCount = recordCount / pageSize;
			int leave = recordCount % pageSize;
			if (leave != 0) {
				pageCount += 1;
			}
			if (pageCount == 1) {
				list = result;
			} else {
				int fromIndex = pageSize * (pageIndex - 1);
				int toIndex = 0;
				if (pageIndex == pageCount) {
					//关于分页leave是否为0的操作 added by Shilei
					if(leave==0){
						//对于分页正好整除的话.最后页就不加leave,加pageSize
						toIndex = fromIndex + pageSize;
					}else{
						//对于分页,如果是由于有leave而加上一页的
						toIndex = fromIndex + leave;
					}
				} else {
					toIndex = fromIndex + pageSize;
				}
				//如果由于操作使toIndex超过记录数了.则取第一页记录 added by Shilei
				if(toIndex>recordCount){
					list = result.subList(0, pageSize);
				}else{
					list = result.subList(fromIndex, toIndex);
				}
			}
		}
		page.setResult(list);
		page.setRecordCount(recordCount);
		//对于dorado.data.Dataset加上分页的每页页数 added by Shilei
		page.setPageSize(pageSize);
		return page;
	}
}
