package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.CommodityDao;
import com.chatm.search.model.Commodity;
import com.chatm.search.model.MyPage;

@Service
@Transactional
public class CommodityService {
	
	@Autowired
	CommodityDao commodityDao;
	
	/**
	 * 分页获取商品数据
	 * @param page 分页器
	 * @return  
	 * @description   
	 * @version currentVersion  
	 */
	public List<Commodity> selectCommodityLimit(MyPage<Commodity> page) {
		return commodityDao.selectCommodityLimit(page);
	}

	public int addCommodity(Commodity commodity) {
		return commodityDao.insertSelective(commodity);
	}
	
	public Commodity selectByPrimaryKey(Long id) {
		return commodityDao.selectByPrimaryKey(id);
	}
	
	public void updateByPrimaryKeySelective(Commodity commodity) {
		commodityDao.updateByPrimaryKey(commodity);
	}
	
	public void deleteByPrimaryKey(long id) {
		commodityDao.deleteByPrimaryKey(id);
	}

	public List<Commodity> selectCommodityByType(Long commodityType) {
		// TODO Auto-generated method stub
		return commodityDao.selectCommodityByType(commodityType);
	}

}
