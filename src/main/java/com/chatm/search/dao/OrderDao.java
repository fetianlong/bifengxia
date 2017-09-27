package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Order;
@MyBatisRepository
public interface OrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

	List<Order> getOrderListByUser(Long payUser);

	Order selectOrderByOrder(Order order);

	int updateOrderByPayorderId(Order order);

	Order selectByOrderByPayorderId(String out_trade_no);

	List<Order> selectListOrderByOrder(Order order);

	Order selectByOrderByTicketNo(String ticketsNo);
}