package kr.ucube.umis.training.service.util;

import kr.ucube.umis.training.domain.QueryParam;

public class SearchUtil {
	public QueryParam getQueryParam(String searchField, String searchCondition) {
		QueryParam qp = new QueryParam();
		
		qp.setSearchField(searchField);
		qp.setSearchCondition(searchCondition);
		qp.setSortname("");
		qp.setSortorder("");
		qp.setStart("");
		qp.setEnd("");
		
		return qp;
	}
	public QueryParam getQueryParam(String searchField, String searchCondition, int page, int rp, String sortname, String sortorder) {
		QueryParam qp = new QueryParam();
		String start = "";
		String end = "";
		
		start = Integer.toString((page - 1) * rp + 1);
		end = Integer.toString(page * rp);
		
		qp.setSearchField(searchField);
		qp.setSearchCondition(searchCondition);
		qp.setSortname(sortname);
		qp.setSortorder(sortorder);
		qp.setStart(start);
		qp.setEnd(end);
		
		return qp;
	}
}
