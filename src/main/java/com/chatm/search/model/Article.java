package com.chatm.search.model;

import java.util.Date;
import java.util.List;

/**
 * 文章信息
 * @author pu
 *
 */
public class Article {
    private Long id;

    private String title;

    private Long programaId;
    private String name;

    private String keyWord;
    private String artiReource;

    private String abstractText;

    private String picUrl;

    private String voidUrl;

    private Integer isVote;

    private Integer releaseType;
    private Integer isTuijian;
    private Integer status;

    private Date releaseTime;

    private Long createUser;

    private Date createDate;

    private String content;
    
    private String purl;
    
    private List<ArticleResource> articleResourceList;

    public List<ArticleResource> getArticleResourceList() {
		return articleResourceList;
	}

	public void setArticleResourceList(List<ArticleResource> articleResourceList) {
		this.articleResourceList = articleResourceList;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getProgramaId() {
        return programaId;
    }

    public String getArtiReource() {
		return artiReource;
	}

	public void setArtiReource(String artiReource) {
		this.artiReource = artiReource;
	}

	public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public String getAbstractText() {
        return abstractText;
    }

    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText == null ? null : abstractText.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getVoidUrl() {
        return voidUrl;
    }

    public void setVoidUrl(String voidUrl) {
        this.voidUrl = voidUrl == null ? null : voidUrl.trim();
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public Integer getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(Integer releaseType) {
        this.releaseType = releaseType;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public Integer getIsTuijian() {
		return isTuijian;
	}

	public void setIsTuijian(Integer isTuijian) {
		this.isTuijian = isTuijian;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPurl() {
		return purl;
	}

	public void setPurl(String purl) {
		this.purl = purl;
	}
}