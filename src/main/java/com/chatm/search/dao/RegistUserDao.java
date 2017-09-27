package com.chatm.search.dao;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.RegistUser;

@MyBatisRepository
public interface RegistUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(RegistUser record);

    int insertSelective(RegistUser record);

    RegistUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RegistUser record);

    int updateByPrimaryKey(RegistUser record);
    
    /**
     * 根据用户名查询当前用户
     * @param username
     * @return  
     * @description   
     * @version currentVersion  
     * @author pujianhua  
     * @createtime 2016年3月24日 下午5:55:45
     */
	RegistUser selectRegistUserByUserName(String username);

	RegistUser selectRegistUserByMobile(String mobile);
	
	RegistUser selectRegistUserByEmail(String email);
	
	/**
	 * 通过用户id查询email
	 * @param user_id
	 * @return
	 */
	String EmailById(String user_id);

	RegistUser selectByUniqueKey(String uniqueKey);
}