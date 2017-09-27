package com.chatm.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.chatm.search.dao.VoteDetailDao;
import com.chatm.search.model.VoteDetail;

@Component
@Transactional
public class VoteDetailServer {
	@Autowired
	VoteDetailDao voteDetailDao;

	public int insertSelective(VoteDetail record){
    	return voteDetailDao.insertSelective(record);
    }

	public List<VoteDetail> selectByArtId(Long articleId) {
		// TODO Auto-generated method stub
		return voteDetailDao.selectByArtId(articleId);
	}

	public List<VoteDetail> selectByVoteId(Long id) {
		// TODO Auto-generated method stub
		return voteDetailDao.selectByVoteId(id);
	}

	public List<VoteDetail> selectByVote(VoteDetail vd) {
		// TODO Auto-generated method stub
		return voteDetailDao.selectByVote(vd);
	};

}