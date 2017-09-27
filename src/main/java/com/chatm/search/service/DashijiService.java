package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.DashijiDao;
import com.chatm.search.model.Dashiji;
import com.chatm.search.model.MyPage;

@Component
@Transactional
public class DashijiService {
	
	@Autowired
	DashijiDao dashijiDao;
	
    public int deleteByPrimaryKey(Long id){
    	return dashijiDao.deleteByPrimaryKey(id);
    };

    public int insert(Dashiji record){
    	return dashijiDao.insert(record);
    };

    public int insertSelective(Dashiji record){
    	return dashijiDao.insertSelective(record);
    };

    public Dashiji selectByPrimaryKey(Long id){
    	return dashijiDao.selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(Dashiji record){
    	return dashijiDao.updateByPrimaryKeySelective(record);
    };

    public int updateByPrimaryKey(Dashiji record){
    	return dashijiDao.updateByPrimaryKey(record);
    };
    
    public List<Dashiji> selectDashijiLimit(MyPage<Dashiji> page) {
		return dashijiDao.selectDashijiLimit(page);
	}

	public List<Dashiji> selectDashiji() {
		// TODO Auto-generated method stub
		return dashijiDao.selectDashiji();
	}
}