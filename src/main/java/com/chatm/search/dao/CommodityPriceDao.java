package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.CommodityPrice;
import com.chatm.search.model.MyPage;

@MyBatisRepository
public interface CommodityPriceDao {
    int deleteByPrimaryKey(Long id);

    int insert(CommodityPrice record);

    int insertSelective(CommodityPrice record);

    CommodityPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommodityPrice record);

    int updateByPrimaryKey(CommodityPrice record);

	List<CommodityPrice> selectLimitByPage(MyPage page);

	List<CommodityPrice> selectPriceBycomId(Long comId);
}