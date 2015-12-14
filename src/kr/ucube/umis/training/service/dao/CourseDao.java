package kr.ucube.umis.training.service.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ucube.umis.training.domain.Course;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.mapper.CourseMapper;

@Service
public class CourseDao {
	@Autowired
	private CourseMapper courseMapper;
	
	@Transactional
	public boolean insertCourse(Course course) {
		try {
			courseMapper.insertCourse(course);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public int updateCourse(Course course) {
		return courseMapper.updateCourse(course);
	}
	
	@Transactional
	public int deleteCourse(HashMap map) {
		return courseMapper.deleteCourse(map);
	}
	
	public Course getCourseById(String courseId) {
		return courseMapper.getCourseById(courseId);
	}
	
	public List<Course> getCourseListBySearchCondition(QueryParam qp) {
		return courseMapper.getCourseListBySearchCondition(qp);
	}
	
	public List<Course> getCourseListAll(QueryParam qp) {
		return courseMapper.getCourseListAll(qp);
	}
	
	public int getCountCourseList() {
		return courseMapper.getCountCourseList();
	}
	
	public int getCountCourseListForSearch(QueryParam qp) {
		return courseMapper.getCountCourseListForSearch(qp);
	}
}
