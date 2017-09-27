package com.chatm.search.model;

import java.util.Date;

public class Dashiji {
    private Long id;

    private String title;

    private String content;

    private String remark;

    private Date createDate;

    private String createUser;

    private Date updateDate;

    private String updateUser;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

	@Override
	public String toString() {
		return "Dashiji [id=" + id + ", title=" + title + ", content="
				+ content + ", remark=" + remark + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate
				+ ", updateUser=" + updateUser + "]";
	}
}