package com.chatm.search.model;

import java.io.Serializable;

public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id; 
	private String userName; 
	private String email;
	private Integer state; 
	private String pwd;
	private String salt;
	private String roleCategory;
	private Long franchiseesId;
	private String maces;
	private Boolean isDeleteFlag;
	private Long createrId;
	private String createrName;
	private String createDateTime;
	
	private String realname;

    private String birthdate;

    private String idnumber;

    private Integer sex;

    private String telephone;
    
    private String remark;
    
    private String sellerName;
    
    private String plainPassword;
    
    private Integer parentId;
    private Integer roleId;
    
    private String resetPwdFlag;
    
    private String franchiseesName;
    
    private String newpassword;
    
	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the roleCategory
	 */
	public String getRoleCategory() {
		return roleCategory;
	}
	/**
	 * @param roleCategory the roleCategory to set
	 */
	public void setRoleCategory(String roleCategory) {
		this.roleCategory = roleCategory;
	}
	/**
	 * @return the franchiseesId
	 */
	public Long getFranchiseesId() {
		return franchiseesId;
	}
	/**
	 * @param franchiseesId the franchiseesId to set
	 */
	public void setFranchiseesId(Long franchiseesId) {
		this.franchiseesId = franchiseesId;
	}
	/**
	 * @return the maces
	 */
	public String getMaces() {
		return maces;
	}
	/**
	 * @param maces the maces to set
	 */
	public void setMaces(String maces) {
		this.maces = maces;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		
		if (this.getId() != other.getId())
			return false;
		if (this.getUserName() != other.getUserName())
			return false;
		if (this.getEmail() != other.getEmail())
			return false;
		if (this.getState() != other.getState())
			return false;
		if (this.getPwd() != other.getPwd())
			return false;
		
		return false;
	}
	/**
	 * @return the isDeleteFlag
	 */
	public Boolean getIsDeleteFlag() {
		return isDeleteFlag;
	}
	/**
	 * @param isDeleteFlag the isDeleteFlag to set
	 */
	public void setIsDeleteFlag(Boolean isDeleteFlag) {
		this.isDeleteFlag = isDeleteFlag;
	}
	/**
	 * @return the createrId
	 */
	public Long getCreaterId() {
		return createrId;
	}
	/**
	 * @param createrId the createrId to set
	 */
	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}
	/**
	 * @return the createrName
	 */
	public String getCreaterName() {
		return createrName;
	}
	/**
	 * @param createrName the createrName to set
	 */
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	/**
	 * @return the createDateTime
	 */
	public String getCreateDateTime() {
		return createDateTime;
	}
	/**
	 * @param createDateTime the createDateTime to set
	 */
	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getPlainPassword() {
		return plainPassword;
	}
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getResetPwdFlag() {
		return resetPwdFlag;
	}
	public void setResetPwdFlag(String resetPwdFlag) {
		this.resetPwdFlag = resetPwdFlag;
	}
	public String getFranchiseesName() {
		return franchiseesName;
	}
	public void setFranchiseesName(String franchiseesName) {
		this.franchiseesName = franchiseesName;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
