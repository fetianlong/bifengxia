package com.chatm.search.model;

public class ArticleVote {
	private Long id;

	private Long articleId;

	private String chooseName;
	
	private String chooseAName;

	private Integer chooseANum;

	private String chooseBName;

	private Integer chooseBNum;

	private String chooseCName;

	private Integer chooseCNum;

	private String chooseDName;

	private Integer chooseDNum;

	private String chooseEName;

	private Integer chooseENum;

	private String chooseFName;

	private Integer chooseFNum;
	
	private String articleName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public String getChooseName() {
		return chooseName;
	}

	public void setChooseName(String chooseName) {
		this.chooseName = chooseName;
	}

	public String getChooseAName() {
		return chooseAName;
	}

	public void setChooseAName(String chooseAName) {
		this.chooseAName = chooseAName == null ? null : chooseAName.trim();
	}

	public Integer getChooseANum() {
		return chooseANum;
	}

	public void setChooseANum(Integer chooseANum) {
		this.chooseANum = chooseANum;
	}

	public String getChooseBName() {
		return chooseBName;
	}

	public void setChooseBName(String chooseBName) {
		this.chooseBName = chooseBName == null ? null : chooseBName.trim();
	}

	public Integer getChooseBNum() {
		return chooseBNum;
	}

	public void setChooseBNum(Integer chooseBNum) {
		this.chooseBNum = chooseBNum;
	}

	public String getChooseCName() {
		return chooseCName;
	}

	public void setChooseCName(String chooseCName) {
		this.chooseCName = chooseCName == null ? null : chooseCName.trim();
	}

	public Integer getChooseCNum() {
		return chooseCNum;
	}

	public void setChooseCNum(Integer chooseCNum) {
		this.chooseCNum = chooseCNum;
	}

	public String getChooseDName() {
		return chooseDName;
	}

	public void setChooseDName(String chooseDName) {
		this.chooseDName = chooseDName == null ? null : chooseDName.trim();
	}

	public Integer getChooseDNum() {
		return chooseDNum;
	}

	public void setChooseDNum(Integer chooseDNum) {
		this.chooseDNum = chooseDNum;
	}

	public String getChooseEName() {
		return chooseEName;
	}

	public void setChooseEName(String chooseEName) {
		this.chooseEName = chooseEName == null ? null : chooseEName.trim();
	}

	public Integer getChooseENum() {
		return chooseENum;
	}

	public void setChooseENum(Integer chooseENum) {
		this.chooseENum = chooseENum;
	}

	public String getChooseFName() {
		return chooseFName;
	}

	public void setChooseFName(String chooseFName) {
		this.chooseFName = chooseFName == null ? null : chooseFName.trim();
	}

	public Integer getChooseFNum() {
		return chooseFNum;
	}

	public void setChooseFNum(Integer chooseFNum) {
		this.chooseFNum = chooseFNum;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	
	
}