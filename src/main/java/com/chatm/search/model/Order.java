package com.chatm.search.model;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Long id;

    private String payOrderId;

    private Long payCommodityId;
    private Long comPid;

    private String commodityName;

    private Integer payType;

    private Date payDate;

    private Long payUser;

    private String payUserName;

    private String payCardNo;

    private String userPhone;

    private Long pCount;

    private BigDecimal sPrice;

    private BigDecimal price;

    private Integer status;

    private String ticketsName;

    private String ticketId;

    private String ticketsPhone;
    
    private String ctype;
    
    private String ticketsNo;
    private Integer ticketsFlag;
    
    private Integer message_flag;
    private Integer send_ticket_flag;

    private Date playTime;
    
    private String trade_no;	//支付宝交易号
    private String assistCheckNo;	//取票码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId == null ? null : payOrderId.trim();
    }

    public Long getPayCommodityId() {
        return payCommodityId;
    }

    public void setPayCommodityId(Long payCommodityId) {
        this.payCommodityId = payCommodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName == null ? null : commodityName.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Long getPayUser() {
        return payUser;
    }

    public void setPayUser(Long payUser) {
        this.payUser = payUser;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName == null ? null : payUserName.trim();
    }

    public String getPayCardNo() {
        return payCardNo;
    }

    public void setPayCardNo(String payCardNo) {
        this.payCardNo = payCardNo == null ? null : payCardNo.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Long getpCount() {
        return pCount;
    }

    public void setpCount(Long pCount) {
        this.pCount = pCount;
    }


	public BigDecimal getsPrice() {
		return sPrice;
	}

	public void setsPrice(BigDecimal sPrice) {
		this.sPrice = sPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTicketsName() {
        return ticketsName;
    }

    public void setTicketsName(String ticketsName) {
        this.ticketsName = ticketsName == null ? null : ticketsName.trim();
    }

    public String getTicketsPhone() {
        return ticketsPhone;
    }

    public void setTicketsPhone(String ticketsPhone) {
        this.ticketsPhone = ticketsPhone == null ? null : ticketsPhone.trim();
    }

    public Date getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Date playTime) {
        this.playTime = playTime;
    }

	public Long getComPid() {
		return comPid;
	}

	public void setComPid(Long comPid) {
		this.comPid = comPid;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getTicketsNo() {
		return ticketsNo;
	}

	public void setTicketsNo(String ticketsNo) {
		this.ticketsNo = ticketsNo;
	}

	public Integer getTicketsFlag() {
		return ticketsFlag;
	}

	public void setTicketsFlag(Integer ticketsFlag) {
		this.ticketsFlag = ticketsFlag;
	}

	public Integer getMessage_flag() {
		return message_flag;
	}

	public void setMessage_flag(Integer message_flag) {
		this.message_flag = message_flag;
	}

	public Integer getSend_ticket_flag() {
		return send_ticket_flag;
	}

	public void setSend_ticket_flag(Integer send_ticket_flag) {
		this.send_ticket_flag = send_ticket_flag;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

    public String getTicketId() {
        return ticketId;
    }

    public String getAssistCheckNo() {
        return assistCheckNo;
    }

    public void setAssistCheckNo(String assistCheckNo) {
        this.assistCheckNo = assistCheckNo;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;

    }
}