package com.github.ext.mybatis.paginator.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

/**
 * 分页查询对象
 * 
 * @author badqiu
 * @author hunhun
 * @author miemiedev
 */
public class PageBounds extends RowBounds implements Serializable {
	private static final long serialVersionUID = -6414350656252331011L;
	public final static int NO_PAGE = 1;
	/** 页码 */
	protected int pageNumber = NO_PAGE;
	/** 分页大小 */
	protected int pageSize = NO_ROW_LIMIT;
	/** 分页排序信息 */
	protected List<OrderItem> orderItems = new ArrayList<OrderItem>();
	/** 结果集是否包含TotalCount */
	protected boolean containsTotalCount;

	protected Boolean asyncTotalCount;

	public PageBounds() {
		containsTotalCount = false;
	}

	public PageBounds(RowBounds rowBounds) {
		if (rowBounds instanceof PageBounds) {
			PageBounds pageBounds = (PageBounds) rowBounds;
			this.pageNumber = pageBounds.pageNumber;
			this.pageSize = pageBounds.pageSize;
			this.orderItems = pageBounds.orderItems;
			this.containsTotalCount = pageBounds.containsTotalCount;
			this.asyncTotalCount = pageBounds.asyncTotalCount;
		} else {
			this.pageNumber = (rowBounds.getOffset() / rowBounds.getLimit()) + 1;
			this.pageSize = rowBounds.getLimit();
		}

	}

	/**
	 * Query TOP N, default containsTotalCount = false
	 * 
	 * @param pageSize
	 */
	public PageBounds(int pageSize) {
		this.pageSize = pageSize;
		this.containsTotalCount = false;
	}

	public PageBounds(int pageNumber, int pageSize) {
		this(pageNumber, pageSize, new ArrayList<OrderItem>(), true);
	}

	public PageBounds(int pageNumber, int pageSize, boolean containsTotalCount) {
		this(pageNumber, pageSize, new ArrayList<OrderItem>(), containsTotalCount);
	}

	/**
	 * Just sorting, default containsTotalCount = false
	 * 
	 * @param orderItems
	 */
	public PageBounds(List<OrderItem> orderItems) {
		this(NO_PAGE, NO_ROW_LIMIT, orderItems, false);
	}

	/**
	 * Just sorting, default containsTotalCount = false
	 * 
	 * @param orderItems
	 */
	public PageBounds(OrderItem... orderItems) {
		this(NO_PAGE, NO_ROW_LIMIT, orderItems);
		this.containsTotalCount = false;
	}

	public PageBounds(int pageNumber, int pageSize, OrderItem... orderItems) {
		this(pageNumber, pageSize, Arrays.asList(orderItems), true);
	}

	public PageBounds(int pageNumber, int pageSize, List<OrderItem> orderItems) {
		this(pageNumber, pageSize, orderItems, true);
	}

	public PageBounds(int pageNumber, int pageSize, List<OrderItem> orderItems, boolean containsTotalCount) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.orderItems = orderItems;
		this.containsTotalCount = containsTotalCount;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getLimit() {
		return pageSize;
	}

	public void setLimit(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isContainsTotalCount() {
		return containsTotalCount;
	}

	public void setContainsTotalCount(boolean containsTotalCount) {
		this.containsTotalCount = containsTotalCount;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Boolean getAsyncTotalCount() {
		return asyncTotalCount;
	}

	public void setAsyncTotalCount(Boolean asyncTotalCount) {
		this.asyncTotalCount = asyncTotalCount;
	}

	@Override
	public int getOffset() {
		if (pageNumber >= 1) {
			return (pageNumber - 1) * pageSize;
		}
		return 0;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PageBounds {");
		sb.append("pageNumber=").append(pageNumber);
		sb.append(", pageSize=").append(pageSize);
		sb.append(", orderItems=").append(orderItems);
		sb.append(", containsTotalCount=").append(containsTotalCount);
		sb.append(", asyncTotalCount=").append(asyncTotalCount);
		sb.append("}");
		return sb.toString();
	}
}