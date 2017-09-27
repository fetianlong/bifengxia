package com.chatm.search.model;

public class OrderApi {
	private String orderNo;	//订单号
	private String subOrderNo;	//子订单号
	private String status;	//check状态:检票
	private String sign;	//签名
	private String checkTime;
	private Integer checkNum;	//检票数量
	private Integer returnNum;	//退票数量
	private Integer total;	//总数量

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getSubOrderNo() {
		return subOrderNo;
	}

	public void setSubOrderNo(String subOrderNo) {
		this.subOrderNo = subOrderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(Integer checkNum) {
		this.checkNum = checkNum;
	}

	public Integer getReturnNum() {
		return returnNum;
	}

	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
