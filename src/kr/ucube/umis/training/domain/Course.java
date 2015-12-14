package kr.ucube.umis.training.domain;

import java.io.Serializable;
import java.sql.Date;

/**
 *@ClassName	Course
 *@Author     	하병용
 *@Date			15/03/2011
 *@Description	교육과정 정보 도메인 모델
 *
 */

public class Course implements Serializable {

	private static final long serialVersionUID = -2615199118959525998L;

	private int		num;
	private String	courseId;
	private String	courseName;
	private String	courseTypeId;
	private String	courseTypeName;
	private String	institutionId;
	private String	institutionName;
	private String	courseTerm;
	private String	courseTime;
	private String	courseInformation;
	private String	coursePrice;
	private Date	courseStartDate;
	private Date	courseEndDate;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseTypeId() {
		return courseTypeId;
	}
	public void setCourseTypeId(String courseTypeId) {
		this.courseTypeId = courseTypeId;
	}
	public String getCourseTypeName() {
		return courseTypeName;
	}
	public void setCourseTypeName(String courseTypeName) {
		this.courseTypeName = courseTypeName;
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
	public String getCourseTerm() {
		return courseTerm;
	}
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getCourseInformation() {
		return courseInformation;
	}
	public void setCourseInformation(String courseInformation) {
		this.courseInformation = courseInformation;
	}
	public String getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(String coursePrice) {
		this.coursePrice = coursePrice;
	}
	public Date getCourseStartDate() {
		return courseStartDate;
	}
	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	public Date getCourseEndDate() {
		return courseEndDate;
	}
	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}
}
