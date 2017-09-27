package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.CommodityPriceDao;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.MyPage;

@Transactional
@Component
public class CommodityPriceService {
	
	@Autowired
	CommodityPriceDao commodityPriceDao;
	
    public int deleteByPrimaryKey(Long id){
    	return commodityPriceDao.deleteByPrimaryKey(id);
    };

    public int insert(CommodityPrice record){
    	return commodityPriceDao.insert(record);
    };

    public int insertSelective(CommodityPrice record){
    	return commodityPriceDao.insertSelective(record);
    };

    public CommodityPrice selectByPrimaryKey(Long id){
    	return commodityPriceDao.selectByPrimaryKey(id);
    };

    public int updateByPrimaryKeySelective(CommodityPrice record){
    	return commodityPriceDao.updateByPrimaryKeySelective(record);
    };

    public int updateByPrimaryKey(CommodityPrice record){
    	return commodityPriceDao.updateByPrimaryKey(record);
    };
    
    public List<CommodityPrice> selectLimitByPage(MyPage page){
    	return commodityPriceDao.selectLimitByPage(page);
    }

	public List<CommodityPrice> selectPriceBycomId(Long comId) {
		// TODO Auto-generated method stub
		return commodityPriceDao.selectPriceBycomId(comId);
	};
}