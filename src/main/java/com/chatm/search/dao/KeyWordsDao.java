package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.KeyWords;

@MyBatisRepository
public interface KeyWordsDao {
	List<KeyWords> selectKeyWordsBykeyword(String keyword);

	void shutdownUserInfoById(Long id);
}