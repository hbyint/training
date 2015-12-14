package kr.ucube.umis.training.mapper;

import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.CourseType;
import kr.ucube.umis.training.domain.QueryParam;

public interface CourseTypeMapper {
	public void insertCourseType(CourseType courseType);
	public int updateCourseType(CourseType courseType);
	public int deleteCourseType(HashMap ids);
	public List<CourseType> getCourseTypeAll();
	public List<CourseType> getCourseTypeList(QueryParam qp);
	public int getCountCourseType();
}
