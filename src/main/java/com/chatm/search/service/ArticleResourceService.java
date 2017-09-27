package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.ArticleResourceDao;
import com.chatm.search.model.ArticleResource;

@Component
@Transactional
public class ArticleResourceService {
	@Autowired
	ArticleResourceDao articleResourceDao;
	
    public ArticleResource selectByPrimaryKey(Long id){
    	return articleResourceDao.selectByPrimaryKey(id);
    };
    
    public List<ArticleResource> selectListByProgramaId(Long programaId){
    	return articleResourceDao.selectListByProgramaId(programaId);
    }
}