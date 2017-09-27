package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Dictionary;
import com.chatm.search.model.MyPage;

@MyBatisRepository
public interface DictionaryDao {

	Long add(Dictionary model);

	Integer update(Dictionary model);

	Dictionary findById(Long id);

	Integer deleteById(Long id);

	int findCountByDictionary(Dictionary model);

	List<Dictionary> selectDictionaryLimit(MyPage<Dictionary> page);

	Dictionary getDictionaryByCode(String code);

	List<Dictionary> getListDictionary(Dictionary dic);
	
	Dictionary getDictionaryById(Long id);
	
	List<Dictionary> getParentDictionary();

	Integer updateByPrimaryKeySelective(Dictionary dictionary);

	Dictionary getDictionary(Dictionary dictionary);

}