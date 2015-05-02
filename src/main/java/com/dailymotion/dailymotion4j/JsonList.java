package com.dailymotion.dailymotion4j;

import java.util.List;

public final class JsonList<T> {
	
	private int page;
	private int limit;
	private int total;
	private boolean explicit;
	private boolean has_more;
	private List<T> list;
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the explicit
	 */
	public boolean isExplicit() {
		return explicit;
	}
	/**
	 * @param explicit the explicit to set
	 */
	public void setExplicit(boolean explicit) {
		this.explicit = explicit;
	}
	/**
	 * @return the has_more
	 */
	public boolean isHas_more() {
		return has_more;
	}
	/**
	 * @param has_more the has_more to set
	 */
	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}
	/**
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
