package kr.ucube.umis.training.web.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.Cell;
import kr.ucube.umis.training.domain.CourseType;
import kr.ucube.umis.training.domain.Grid;
import kr.ucube.umis.training.domain.Institution;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.service.dao.CourseTypeDao;
import kr.ucube.umis.training.service.util.SearchUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/CourseTypeAdmin/*")
public class CourseTypeAdminAction {
	@Autowired
	private CourseTypeDao dao;
	private CourseType dmo = new CourseType();
	private QueryParam qp = new QueryParam();
	
	@RequestMapping("/insertCourseType")
	@ResponseBody
	public boolean insertCourseType(
		@RequestParam("typeId") String typeId,
		@RequestParam("typeName") String typeName,
		@RequestParam("typeOrder") String typeOrder)
	{
		try {
			typeId = new String(typeId.getBytes("8859_1"), "UTF-8");
			typeName = new String(typeName.getBytes("8859_1"), "UTF-8"); 
			typeOrder = new String(typeOrder.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
		
		dmo.setTypeId(typeId);
		dmo.setTypeName(typeName);
		dmo.setTypeOrder(typeOrder);
		
		return dao.insertCourseType(dmo);
	}
	
	@RequestMapping("/updateCourseType")
	@ResponseBody
	public int updateCourseType (
		@RequestParam("typeId") String typeId,
		@RequestParam("typeName") String typeName,
		@RequestParam("typeOrder") String typeOrder)
	{
		try {
			typeId = new String(typeId.getBytes("8859_1"), "UTF-8");
			typeName = new String(typeName.getBytes("8859_1"), "UTF-8"); 
			typeOrder = new String(typeOrder.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
		
		dmo.setTypeId(typeId);
		dmo.setTypeName(typeName);
		dmo.setTypeOrder(typeOrder);

		return dao.updateCourseType(dmo);
	}
	
	@RequestMapping("/deleteCourseType")
	@ResponseBody
	public int deleteCourseType (@RequestParam("typeIds") String typeIds) {
		String[] idList = typeIds.split(",");
		List<String> typeIdList = Arrays.asList(idList);
		HashMap map = new HashMap();
		map.put("ids", typeIdList);
		return dao.deleteCourseType(map);
	}
	
	@RequestMapping("/getCourseTypeAll")
	@ResponseBody
	public List<CourseType> getCourseTypeAll() {
		return dao.getCourseTypeAll();
	}
	
	@RequestMapping("/getCourseTypeList")
	@ResponseBody
	public Grid getCourseTypeList(
		@RequestParam("page") int page, 
		@RequestParam("rp") int rp,
		@RequestParam("sortname") String sortname,
		@RequestParam("sortorder") String sortorder)
	{
		SearchUtil su = new SearchUtil();
		qp = su.getQueryParam("", "", page, rp, sortname, sortorder);

		List<CourseType> list = dao.getCourseTypeList(qp);

		Grid grid = new Grid();
		grid.setPage(page);
		grid.setTotal(dao.getCountCourseType());
		List<Cell> rows = new ArrayList<Cell>();
		
		for (int i=0; i < list.size(); i++) {
			Cell cell = new Cell();
			CourseType t = list.get(i);
			List<String> cellData = new ArrayList<String>();
			
			cellData.add(t.getTypeOrder());
			cellData.add(t.getTypeId());
			cellData.add(t.getTypeName());
			
			cell.setId(t.getTypeId());
			cell.setCell(cellData);

			rows.add(cell);
		}
		grid.setRows(rows);
		return grid;
	}
}
