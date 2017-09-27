package com.chatm.search.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chatm.search.model.ArticleVote;
import com.chatm.search.model.VoteDetail;
import com.chatm.search.service.ArticleService;
import com.chatm.search.service.VoteDetailServer;
import com.chatm.search.util.IpTool;

@Controller
@RequestMapping("/articleVote")
public class ArticleVoteController {
	
	@Autowired
	ArticleService articleService;
	@Autowired
	VoteDetailServer voteDetailServer;
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String update(@PathVariable("id")Long id, @RequestParam("chooseNum") String chooseNum, HttpServletRequest request){
		
		ArticleVote articleVote = articleService.selectArticleVoteById(id);
		String ip = IpTool.getClientIPAddress(request);
		
		VoteDetail vd = new VoteDetail();
		vd.setVoteId(id);
		vd.setVoteIp(ip);
		//获取当前文章投票次数
		List<VoteDetail> listVoteDetail = voteDetailServer.selectByVote(vd);
		System.out.println(listVoteDetail);
//		if(chooseNum.equals("chooseANum")){
//			articleVote.setChooseANum(articleVote.getChooseANum()+1);
//		}else
		
		switch (chooseNum) {
			case "chooseANum" : articleVote.setChooseANum(articleVote.getChooseANum()+1);vd.setVoteChoose(articleVote.getChooseAName());break;
			case "chooseBNum" : articleVote.setChooseBNum(articleVote.getChooseBNum()+1);vd.setVoteChoose(articleVote.getChooseBName());break;
			case "chooseCNum" : articleVote.setChooseCNum(articleVote.getChooseCNum()+1);vd.setVoteChoose(articleVote.getChooseCName());break;
			case "chooseDNum" : articleVote.setChooseDNum(articleVote.getChooseDNum()+1);vd.setVoteChoose(articleVote.getChooseDName());break;
			case "chooseENum" : articleVote.setChooseENum(articleVote.getChooseENum()+1);vd.setVoteChoose(articleVote.getChooseEName());break;
			case "chooseFNum" : articleVote.setChooseFNum(articleVote.getChooseFNum()+1);vd.setVoteChoose(articleVote.getChooseFName());break;
		}
		
		voteDetailServer.insertSelective(vd);
		
		articleService.updateArticleVoteBySelective(articleVote);
		return null;
	}
}
