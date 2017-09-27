package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Programa;

@MyBatisRepository
public interface ProgramaDao {

    Programa selectByPrimaryKey(Long id);

	List<Programa> selectListProgramasByLevel(int level);

	List<Programa> selectListProgramasByPid(Long parentId);

	Programa selectProgramasByPid(Long pid);

	List<Programa> seletProgramaBrotherById(Long programaId);
}