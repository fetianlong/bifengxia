package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Commodity;
import com.chatm.search.model.MyPage;

@MyBatisRepository
public interface CommodityDao {
    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
    
    List<Commodity> selectCommodityLimit(MyPage<Commodity> page);

	List<Commodity> selectCommodityByType(Long commodityType);
}