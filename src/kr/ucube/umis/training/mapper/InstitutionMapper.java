package kr.ucube.umis.training.mapper;

import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.Institution;
import kr.ucube.umis.training.domain.QueryParam;

public interface InstitutionMapper {
	public void insertInstitution(Institution institution);
	public int updateInstitution(Institution institution);
	public int deleteInstitution(HashMap ids);
	public Institution getInstitutionById(String institutionId);
	public List<Institution> getInstitutionList(QueryParam qp);
	public List<Institution> getInstitutionAll();
	public int getInstitutionCount();
}
