package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.ArticleDao;
import com.chatm.search.dao.ArticleResourceDao;
import com.chatm.search.dao.ArticleVoteDao;
import com.chatm.search.dao.ProgramaDao;
import com.chatm.search.model.Article;
import com.chatm.search.model.ArticleResource;
import com.chatm.search.model.ArticleVote;
import com.chatm.search.model.MyPage;
import com.chatm.search.model.Programa;

@Component
@Transactional
public class ArticleService {
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ProgramaDao programaDao;
	@Autowired
	ArticleVoteDao articleVoteDao;
	@Autowired
	ArticleResourceDao articleResourceDao;
	
	
    public int deleteByPrimaryKey(Long id){
    	return articleDao.deleteByPrimaryKey(id);
    };

    public int insert(Article record){
    	int rid = articleDao.insert(record);
    	return rid;
    }

    public int insertSelective(Article record){
    	int rid = articleDao.insertSelective(record);
    	return rid;
    }

    public Article selectByPrimaryKey(Long id){
    	return articleDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Article record){
    	return articleDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(Article record){
    	return articleDao.updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(Article record){
    	return articleDao.updateByPrimaryKey(record);
    }

	public List<Article> selectArticleLimit(MyPage<Article> page) {
		// TODO Auto-generated method stub
		return articleDao.selectArticleLimit(page);
	}

	/**
	 * 查询是否有相同的标题
	 * @param trim
	 * @return  
	 * @description   
	 * @version currentVersion  
	 * @author 兼画  
	 * @createtime 2016年9月1日 下午3:36:37
	 */
	public boolean checkExistByArticleTitle(String title) {
		Article ar = articleDao.checkExistByArticleTitle(title);
		return (ar != null);
	}
	
	/**
	 * 根据当前栏目ID获取子栏目ID
	 * @param programaId
	 * @return
	 */
	public List<Programa> selectArticleByParentProgramaId(Long programaId) {
		return programaDao.selectListProgramasByPid(programaId);
	}

	/**
	 * 根据栏目ID获取同级别的栏目信息
	 * @param programaId
	 * @return
	 */
	public List<Programa> seletProgramaBrotherById(Long programaId) {
		return programaDao.seletProgramaBrotherById(programaId);
	}

	public ArticleVote selectArticleVoteByarId(Long id) {
		return articleVoteDao.selectArticleVoteByarId(id);
	}

	public int updateArticleVoteBySelective(ArticleVote articleVote) {
		return articleVoteDao.updateArticleVoteBySelective(articleVote);
	}

	public ArticleVote selectArticleVoteById(Long id) {
		return articleVoteDao.selectByPrimaryKey(id);
	}

	/**
	 * 根据programaId获取Article内容
	 * @param i
	 * @return
	 */
	public List<Article> selectArticleByPid(Long programaId) {
		return articleDao.selectArticleByPid(programaId);
	}
	
}