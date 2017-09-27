package com.chatm.search.dao;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.UserInfo;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 * @author calvin
 */
@MyBatisRepository
public interface UserInfoDao {

	UserInfo selectUserInfoByEmail();
	
	void startUserInfoById(Long id);

}
