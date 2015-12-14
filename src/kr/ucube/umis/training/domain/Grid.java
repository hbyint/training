package kr.ucube.umis.training.domain;

import java.util.List;

public class Grid {
	private int page;
	private int total;
	private List<Cell> rows;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<Cell> getRows() {
		return rows;
	}
	public void setRows(List<Cell> rows) {
		this.rows = rows;
	}
}
