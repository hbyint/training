package kr.ucube.umis.training.service.dao;

import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.CourseType;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.mapper.CourseTypeMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseTypeDao {
	@Autowired
	private CourseTypeMapper courseTypeMapper;
	
	@Transactional
	public boolean insertCourseType(CourseType courseType) {
		try {
			courseTypeMapper.insertCourseType(courseType);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Transactional
	public int updateCourseType(CourseType courseType) {
		return courseTypeMapper.updateCourseType(courseType);
	}
	
	@Transactional
	public int deleteCourseType(HashMap ids) {
		return courseTypeMapper.deleteCourseType(ids);
	}
	
	public List<CourseType> getCourseTypeAll() {
		return courseTypeMapper.getCourseTypeAll();
	}
	
	public List<CourseType> getCourseTypeList(QueryParam qp) {
		return courseTypeMapper.getCourseTypeList(qp);
	}
	
	public int getCountCourseType() {
		return courseTypeMapper.getCountCourseType();
	}
}
