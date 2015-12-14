package kr.ucube.umis.training.mapper;

import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.Course;
import kr.ucube.umis.training.domain.QueryParam;

public interface CourseMapper {
	public void insertCourse(Course course);
	public int updateCourse(Course course);
	public int deleteCourse(HashMap map);

	public Course getCourseById(String courseId);
	public List<Course> getCourseListBySearchCondition(QueryParam qp);
	public List<Course> getCourseListAll(QueryParam qp);
	public int getCountCourseList();
	public int getCountCourseListForSearch(QueryParam qp);
}
