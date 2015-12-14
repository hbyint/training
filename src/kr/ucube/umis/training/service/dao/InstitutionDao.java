package kr.ucube.umis.training.service.dao;

import java.util.HashMap;
import java.util.List;

import kr.ucube.umis.training.domain.Institution;
import kr.ucube.umis.training.domain.QueryParam;
import kr.ucube.umis.training.mapper.InstitutionMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstitutionDao {
	@Autowired
	private InstitutionMapper institutionMapper;
	
	@Transactional
	public boolean insertInstitution(Institution institution) {
		try {
			System.out.println("institutionMapper : " + institutionMapper);
			institutionMapper.insertInstitution(institution);
		} catch (Exception e) {
			e.printStackTrace();
			return false; 
		}
		return true;
	}
	
	@Transactional
	public int updateInstitution(Institution institution) {
		try {
			return institutionMapper.updateInstitution(institution);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	@Transactional
	public int deleteInstitution(HashMap ids) {
		return institutionMapper.deleteInstitution(ids);
	}
	
	public Institution getInstitutionById(String institutionId) {
		try {
			return (Institution) institutionMapper.getInstitutionById(institutionId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Institution> getInstitutionList(QueryParam qp) {
		return institutionMapper.getInstitutionList(qp);
	}
	
	public List<Institution> getInstitutionAll() {
		return institutionMapper.getInstitutionAll();
	}
	
	public int getInstitutionCount() {
		return institutionMapper.getInstitutionCount();
	}
}
