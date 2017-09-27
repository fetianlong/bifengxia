package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.ProgramaDao;
import com.chatm.search.model.Programa;

@Component
@Transactional
public class ProgramaService {
	
	@Autowired
	ProgramaDao programaDao;
	
    /**
     * 获取定义级别的所有栏目
     * @param level 需要的级别栏目
     * @return  
     * @description   
     * @version currentVersion  
     * @author 兼画  
     * @createtime 2016年8月28日 上午11:39:47
     */
	public List<Programa> selectLevelProgramas(int level) {
		// TODO Auto-generated method stub
		return programaDao.selectListProgramasByLevel(level);
	}

	/**
	 * 根据父级ID获取所有栏目
	 * @param pid 父级ID
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2016年8月28日 下午12:16:37
	 */
	public List<Programa> selectListProgramasByPid(Long pid) {
		// TODO Auto-generated method stub
		return programaDao.selectListProgramasByPid(pid);
	}

	/**
	 * 根据父级ID获取栏目信息
	 * @param parseLong
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2016年8月28日 下午11:36:10
	 */
	public Programa selectProgramasByPid(long pid) {
		// TODO Auto-generated method stub
		return programaDao.selectProgramasByPid(pid);
	}

}