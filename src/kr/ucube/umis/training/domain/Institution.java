package kr.ucube.umis.training.domain;

import java.io.Serializable;

public class Institution implements Serializable {
	private static final long serialVersionUID = 3552882526884024521L;

	private int		num;
	private	String	institutionId;
	private	String	institutionName;
	private	String	institutionAddress;
	private String	institutionCall;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}	
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getInstitutionAddress() {
		return institutionAddress;
	}
	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}
	public String getInstitutionCall() {
		return institutionCall;
	}
	public void setInstitutionCall(String institutionCall) {
		this.institutionCall = institutionCall;
	}
}
