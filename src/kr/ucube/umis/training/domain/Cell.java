package kr.ucube.umis.training.domain;

import java.util.List;

public class Cell {
	private String id;
	private List<String> cell;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCell() {
		return cell;
	}

	public void setCell(List<String> cell) {
		this.cell = cell;
	}
}
