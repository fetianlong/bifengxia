package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.ArticleResource;

@MyBatisRepository
public interface ArticleResourceDao {

    ArticleResource selectByPrimaryKey(Long id);

	List<ArticleResource> selectListByProgramaId(Long parentId);
}