package com.chatm.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.Article;
import com.chatm.search.model.MyPage;

@MyBatisRepository
public interface ArticleDao {
    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

	List<Article> selectArticleLimit(MyPage<Article> page);

	Article checkExistByArticleTitle(String title);

	List<Article> selectArticleByParentProgramaId(Long programaId);

	List<Article> selectArticleByPid(@Param("programaId") Long programaId);
}