package cn.com.yuzhushui.movie.common;

import java.util.HashMap;
import java.util.Map;

import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月11日下午3:53:43
 **/
public class BaseQuery {

	private Map<String, Object> queryData = new HashMap<String, Object>();// 一些查询条件
	private int pageNum = 1;// 当前页
	private int pageSize = 5;// 每页的数量
	private String orderDirection;// 排序方向
	private String orderColumns;// 排序字段（多个以半角逗号隔开）

	private String orderBy;// 排序
	
	public void addParam(String key, Object value){
		this.queryData.put(key, value);
	}
	public Map<String, Object> getQueryData() {
		return queryData;
	}

	public void setQueryData(Map<String, Object> queryData) {
		this.queryData = queryData;
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

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getOrderColumns() {
		return orderColumns;
	}

	public void setOrderColumns(String orderColumns) {
		this.orderColumns = orderColumns;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy() {
		if(!StringUtil.isEmpty(getOrderColumns())) {
			this.orderBy = getOrderColumns() + " " + (getOrderDirection() == null ? "" : getOrderDirection());
		}
	}
}
