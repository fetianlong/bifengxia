package com.chatm.search.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Commodity {
    private Long id;

    private String commodityCode;

    private Long commodityType;
    private String commodityTypeName;

    private String name;

    private BigDecimal oldPrice;
    
    private BigDecimal price;

    private Integer status;

    private String picUrl;

    private String remark;
    
    private String introduction;

    private Long createUser;

    private Date createDate;

    private List<CommodityPrice> commodityPrice;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    public Long getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Long commodityType) {
        this.commodityType = commodityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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


	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public List<CommodityPrice> getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(List<CommodityPrice> commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public String getCommodityTypeName() {
		return commodityTypeName;
	}

	public void setCommodityTypeName(String commodityTypeName) {
		this.commodityTypeName = commodityTypeName;
	}
	
	
}