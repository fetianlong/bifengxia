package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Dashiji;
import com.chatm.search.model.MyPage;

@MyBatisRepository
public interface DashijiDao {
    int deleteByPrimaryKey(Long id);

    int insert(Dashiji record);

    int insertSelective(Dashiji record);

    Dashiji selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dashiji record);

    int updateByPrimaryKey(Dashiji record);

	List<Dashiji> selectDashijiLimit(MyPage<Dashiji> page);

	List<Dashiji> selectDashiji();
}