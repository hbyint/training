package kr.ucube.umis.training.web.action;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sutil.UtilCommon;

import kr.ucube.umis.training.domain.Cell;
import kr.ucube.umis.training.domain.Grid;
import kr.ucube.umis.training.domain.Course;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.service.dao.CourseDao;
import kr.ucube.umis.training.service.util.SearchUtil;

@Controller
@RequestMapping("/CourseAdmin/*")
public class CourseAdminAction {
	@Autowired
	private CourseDao dao;
	private Course dmo = new Course();
	private QueryParam qp = new QueryParam();
	private SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping("/insertCourse")
	@ResponseBody
	public boolean insertCourse(
		@RequestParam("courseName") 		String courseName, 
		@RequestParam("courseTypeId")		String courseTypeId,
		@RequestParam("courseTypeName")		String courseTypeName, 
		@RequestParam("institutionId") 		String institutionId,
		@RequestParam("institutionName") 	String institutionName,
		@RequestParam("courseTerm") 		String courseTerm,
		@RequestParam("courseTime") 		String courseTime,
		@RequestParam("courseInformation") 	String courseInformation,
		@RequestParam("coursePrice") 		String coursePrice,
		@RequestParam("courseStartDate") 	String courseStartDate,
		@RequestParam("courseEndDate") 		String courseEndDate) throws Exception 
	{
		System.out.println("inserCourse Action found !!!!!!!!!!!!!!!!");
		String courseId = UtilCommon.idMake("TC");
		Date startDate = null;
		Date endDate = null;
	
		courseName = new String(courseName.getBytes("8859_1"), "UTF-8");
		courseTypeId = new String(courseTypeId.getBytes("8859_1"), "UTF-8");
		courseTypeName = new String(courseTypeName.getBytes("8859_1"), "UTF-8");
		institutionId = new String(institutionId.getBytes("8859_1"), "UTF-8");
		institutionName = new String(institutionName.getBytes("8859_1"), "UTF-8");
		courseTerm = new String(courseTerm.getBytes("8859_1"), "UTF-8");
		courseTime = new String(courseTime.getBytes("8859_1"), "UTF-8");
		courseInformation = new String(courseInformation.getBytes("8859_1"), "UTF-8");
		coursePrice = new String(coursePrice.getBytes("8859_1"), "UTF-8");
		startDate = (Date) format.parse(courseStartDate);
		endDate = (Date) format.parse(courseEndDate);
		
		dmo.setCourseId(courseId);
		dmo.setCourseName(courseName);
		dmo.setCourseTypeId(courseTypeId);
		dmo.setCourseTypeName(courseTypeName);
		dmo.setInstitutionId(institutionId);
		dmo.setInstitutionName(institutionName);
		dmo.setCourseTerm(courseTerm);
		dmo.setCourseTime(courseTime);
		dmo.setCourseInformation(courseInformation);
		dmo.setCoursePrice(coursePrice);
		dmo.setCourseStartDate(startDate);
		dmo.setCourseEndDate(endDate);

		return dao.insertCourse(dmo);
	}
	
	@RequestMapping("/updateCourse")
	@ResponseBody
	public int updateInstitution (
		@RequestParam("courseName") 		String courseName, 
		@RequestParam("courseTypeId")		String courseTypeId, 
		@RequestParam("courseTypeName")		String courseTypeName, 
		@RequestParam("institutionId") 		String institutionId,
		@RequestParam("institutionName") 	String institutionName,
		@RequestParam("courseTerm") 		String courseTerm,
		@RequestParam("courseTime") 		String courseTime,
		@RequestParam("courseInformation") 	String courseInformation,
		@RequestParam("coursePrice") 		String coursePrice,
		@RequestParam("courseStartDate") 	String courseStartDate,
		@RequestParam("courseEndDate") 		String courseEndDate) throws Exception 
	{
		String courseId = UtilCommon.idMake("TC");
		Date startDate = null;
		Date endDate = null;
		
		courseName = new String(courseName.getBytes("8859_1"), "UTF-8");
		courseTypeId = new String(courseTypeId.getBytes("8859_1"), "UTF-8");
		courseTypeName = new String(courseTypeName.getBytes("8859_1"), "UTF-8");
		institutionId = new String(institutionId.getBytes("8859_1"), "UTF-8");
		institutionName = new String(institutionName.getBytes("8859_1"), "UTF-8");
		courseTerm = new String(courseTerm.getBytes("8859_1"), "UTF-8");
		courseTime = new String(courseTime.getBytes("8859_1"), "UTF-8");
		courseInformation = new String(courseInformation.getBytes("8859_1"), "UTF-8");
		coursePrice = new String(coursePrice.getBytes("8859_1"), "UTF-8");
		startDate = (Date) format.parse(courseStartDate);
		endDate = (Date) format.parse(courseEndDate);
		
		dmo.setCourseId(courseId);
		dmo.setCourseName(courseName);
		dmo.setCourseTypeId(courseTypeId);
		dmo.setCourseTypeName(courseTypeName);
		dmo.setInstitutionId(institutionId);
		dmo.setInstitutionName(institutionName);
		dmo.setCourseTerm(courseTerm);
		dmo.setCourseTime(courseTime);
		dmo.setCourseInformation(courseInformation);
		dmo.setCoursePrice(coursePrice);
		dmo.setCourseStartDate(startDate);
		dmo.setCourseEndDate(endDate);

		return dao.updateCourse(dmo);
	}
	
	@RequestMapping("/deleteCourse")
	@ResponseBody
	public int deleteCourse(@RequestParam("courseIds") String courseIds) {
		String[] idList = courseIds.split(",");
		List<String> courseIdList = Arrays.asList(idList);
		HashMap map = new HashMap();
		map.put("ids", courseIdList);
		return dao.deleteCourse(map);
	}
	
	@RequestMapping("/getCourseById")
	@ResponseBody
	public Course getCourseById(@RequestParam("courseId") String id) {
		return dao.getCourseById(id);
	}
	
	@RequestMapping("/getCourseListBySearchCondition")
	@ResponseBody
	public Grid getCourseListBySearchCondition (
		@RequestParam("searchField") String field,
		@RequestParam("searchCondition") String condition,
		@RequestParam("page") int page, 
		@RequestParam("rp") int rp,
		@RequestParam("sortname") String sortname,
		@RequestParam("sortorder") String sortorder) 
	{
		SearchUtil su = new SearchUtil();
		qp = su.getQueryParam(field, condition, page, rp, sortname, sortorder);
		List<Course> list = dao.getCourseListBySearchCondition(qp);
		
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setTotal(dao.getCountCourseListForSearch(qp));
		List<Cell> rows = new ArrayList<Cell>();
		
		for (int i=0; i < list.size(); i++) {
			Cell cell = new Cell();
			Course t = list.get(i);
			List<String> cellData = new ArrayList<String>();
			
			cellData.add(Integer.toString(t.getNum()));
			cellData.add(t.getCourseName());
			//cellData.add(t.getInstitutionId());
			cellData.add(t.getInstitutionName());
			//cellData.add(t.getCourseTypeId());
			cellData.add(t.getCourseTypeName());
			cellData.add(t.getCourseTerm());
			cellData.add(t.getCourseTime());
			cellData.add(t.getCourseInformation());
			cellData.add(t.getCoursePrice());
			cellData.add(format.format(t.getCourseStartDate()));
			cellData.add(format.format(t.getCourseEndDate()));
			
			cell.setId(t.getCourseId());
			cell.setCell(cellData);

			rows.add(cell);
		}
		grid.setRows(rows);
		return grid;
	}
	
	@RequestMapping("/getCourseListAll")
	@ResponseBody
	public Grid getCourseListAll(
		@RequestParam("page") int page, 
		@RequestParam("rp") int rp,
		@RequestParam("sortname") String sortname,
		@RequestParam("sortorder") String sortorder)
	{
		SearchUtil su = new SearchUtil();
		qp = su.getQueryParam("", "", page, rp, sortname, sortorder);
		List<Course> list = dao.getCourseListAll(qp);
		
		Grid grid = new Grid();
		grid.setPage(page);
		grid.setTotal(dao.getCountCourseList());
		List<Cell> rows = new ArrayList<Cell>();
		
		for (int i=0; i < list.size(); i++) {
			Cell cell = new Cell();
			Course t = list.get(i);
			List<String> cellData = new ArrayList<String>();
			
			cellData.add(Integer.toString(t.getNum()));
			cellData.add(t.getCourseName());
			//cellData.add(t.getInstitutionId());
			cellData.add(t.getInstitutionName());
			//cellData.add(t.getCourseTypeId());
			cellData.add(t.getCourseTypeName());
			cellData.add(t.getCourseTerm());
			cellData.add(t.getCourseTime());
			cellData.add(t.getCourseInformation());
			cellData.add(t.getCoursePrice());
			cellData.add(format.format(t.getCourseStartDate()));
			cellData.add(format.format(t.getCourseEndDate()));
			
			cell.setId(t.getCourseId());
			cell.setCell(cellData);

			rows.add(cell);
		}
		grid.setRows(rows);
		return grid;
	}
}
