package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.KeyWordsDao;
import com.chatm.search.dao.UserInfoDao;
import com.chatm.search.model.KeyWords;
import com.chatm.search.model.UserInfo;

@Component
@Transactional
public class KeyWordsService {
	
	@Autowired
	KeyWordsDao keyWordsDao;
	
	@Autowired
	UserInfoDao userInfoDao;
	
	public List<KeyWords> selectKeyWordsBykeyword(String keyword){
		return keyWordsDao.selectKeyWordsBykeyword(keyword);
	}

	public void shutdownUserInfoById(Long id) {
		keyWordsDao.shutdownUserInfoById(id);
	}
	
	public UserInfo getUserInfo(){
		return userInfoDao.selectUserInfoByEmail();
	}
	
	public void startUserInfoById(Long id){
		userInfoDao.startUserInfoById(id);
	}
}