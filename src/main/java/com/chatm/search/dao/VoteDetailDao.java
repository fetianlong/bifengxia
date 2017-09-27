package com.chatm.search.dao;

import java.util.List;

import com.chatm.search.common.MyBatisRepository;
import com.chatm.search.model.VoteDetail;

@MyBatisRepository
public interface VoteDetailDao {
    int deleteByPrimaryKey(Long id);

    int insert(VoteDetail record);

    int insertSelective(VoteDetail record);

    VoteDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VoteDetail record);

    int updateByPrimaryKey(VoteDetail record);

	List<VoteDetail> selectByArtId(Long articleId);

	List<VoteDetail> selectByVoteId(Long voteId);

	List<VoteDetail> selectByVote(VoteDetail vd);
}