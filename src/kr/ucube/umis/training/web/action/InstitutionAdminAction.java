package kr.ucube.umis.training.web.action;

import java.io.UnsupportedEncodingException;
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
import kr.ucube.umis.training.domain.Institution;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.service.dao.InstitutionDao;
import kr.ucube.umis.training.service.util.SearchUtil;

@Controller
@RequestMapping("/InstitutionAdmin/*")
public class InstitutionAdminAction {
	@Autowired
	private InstitutionDao dao;
	private Institution dmo = new Institution();
	private QueryParam qp = new QueryParam();
	  
	
	@RequestMapping("/getInstitutionById")
	@ResponseBody
	public Institution getInstitutionById(@RequestParam("institutionId") String id) {
		return dao.getInstitutionById(id);
	}

	@RequestMapping("/insertInstitution")
	@ResponseBody
	public boolean insertInstitution(
			@RequestParam("inputName") 	String name, 
			@RequestParam("inputAddress")	String address, 
			@RequestParam("inputCall") 	String call ) throws UnsupportedEncodingException 
	{
		String id = UtilCommon.idMake("TI");
		try {
			name = new String(name.getBytes("8859_1"), "UTF-8");
			address = new String(address.getBytes("8859_1"), "UTF-8"); 
			call = new String(call.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		}
		
		dmo.setInstitutionId(id);
		dmo.setInstitutionName(name);
		dmo.setInstitutionAddress(address);
		dmo.setInstitutionCall(call);

		return dao.insertInstitution(dmo);
	}
	
	@RequestMapping("/updateInstitution")
	@ResponseBody
	public int updateInstitution (
			@RequestParam("institutionId") 		String id,
			@RequestParam("institutionName") 	String name, 
			@RequestParam("institutionAddress")	String address, 
			@RequestParam("institutionCall") 	String call ) {
		try {
			id = new String(id.getBytes("8859_1"), "UTF-8");
			name = new String(name.getBytes("8859_1"), "UTF-8");
			address = new String(address.getBytes("8859_1"), "UTF-8"); 
			call = new String(call.getBytes("8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		dmo.setInstitutionId(id);
		dmo.setInstitutionName(name);
		dmo.setInstitutionAddress(address);
		dmo.setInstitutionCall(call);

		return dao.updateInstitution(dmo);
	}
	
	@RequestMapping("/deleteInstitution")
	@ResponseBody
	public int deleteInstitution (@RequestParam("institutionIds") String institutionIds) {
		String[] idList = institutionIds.split(",");
		List<String> institutionIdList = Arrays.asList(idList);
		HashMap map = new HashMap();
		map.put("ids", institutionIdList);
		return dao.deleteInstitution(map);
	}
	
	@RequestMapping("/getInstitutionList")
	@ResponseBody
	public Grid getInstitutionList (
		@RequestParam("page") int page, 
		@RequestParam("rp") int rp,
		@RequestParam("sortname") String sortname,
		@RequestParam("sortorder") String sortorder) 
	{
		SearchUtil su = new SearchUtil();
		qp = su.getQueryParam("", "", page, rp, sortname, sortorder);

		List<Institution> list = dao.getInstitutionList(qp);

		Grid grid = new Grid();
		grid.setPage(page);
		grid.setTotal(dao.getInstitutionCount());
		List<Cell> rows = new ArrayList<Cell>();
		
		for (int i=0; i < list.size(); i++) {
			Cell cell = new Cell();
			Institution t = list.get(i);
			List<String> cellData = new ArrayList<String>();
			
			cellData.add(Integer.toString(t.getNum()));
			cellData.add(t.getInstitutionName());
			cellData.add(t.getInstitutionAddress());
			cellData.add(t.getInstitutionCall());
			
			cell.setId(t.getInstitutionId());
			cell.setCell(cellData);

			rows.add(cell);
		}
		grid.setRows(rows);
		return grid;
	}
	
	@RequestMapping("/getInstitutionAll")
	@ResponseBody
	public List<Institution> getInstitutionAll() {
		return dao.getInstitutionAll();
	}
}
