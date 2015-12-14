package kr.ucube.umis.training.domain;

import java.io.Serializable;

public class CourseType implements Serializable {
	private static final long serialVersionUID = -2388286455457999998L;
	
	private String typeId;
	private String typeName;
	private String typeOrder;
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeOrder() {
		return typeOrder;
	}
	public void setTypeOrder(String typeOrder) {
		this.typeOrder = typeOrder;
	}
}
