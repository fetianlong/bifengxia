package com.chatm.search.dao;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.ArticleVote;

@MyBatisRepository
public interface ArticleVoteDao {
	ArticleVote selectArticleVoteByarId(Long articalId);

	int updateArticleVoteBySelective(ArticleVote articleVote);

	ArticleVote selectByPrimaryKey(Long id);
}