package com.chartTmSearch.quickstart;

public class POrder {
	private String item_id; // varchar(16) ' 团购网商品id',
	private String item_name; // varchar(16) ' 商品名',
	private String order_no; // varchar(32) ' 订单批次号',
	private String order_id; // varchar(32) ' 订单id,全局唯一(一个订单批次号对应多个订单id)',
	private String ticket_id; // varchar(32) ' 对应本地票务票种',
	private String cust_name; // varchar(32) ' 客户名',
	private String cust_phone; // varchar(32) ' 客户手机号',
	private String cust_no; // varchar(20) ' 客户身份证号',
	private String price; // int(10) ' 单价',
	private String size; // int(11) ' 购买次数',
	private String amount; // int(11) ' 总价',
	private String verify_code; // varchar(80) ' 票号:多个用逗号分隔, 微信端生成; 12位数字,
								// 本地系统票号为10位数字; 最多5个票号; ',
	private String status; // varchar(32) ' 状态: 0,未支付; 1,可用; 2,已使用; 3,已过期;
							// ',
	private String expire_time; // datetime(80) '票过期时间(注意兑换本地票后,过期时间以本地为准)',
	private String active_time; // datetime(80) DEFAULT NULL,
	private String create_time; // datetime(80) DEFAULT NULL,
	private String update_time; // datetime(80) '通常为同步到本地的时间',
	private String agent_id; // varchar(32) ' 机构id',
	private String agent_name; // varchar(32) ' 机构名(如票付通,智游宝等)',
	private String third_id; // varchar(32) ' 第三方团购网id',
	private String third_name; // varchar(32) ' 第三方团购网名称',
	private String pay_type; // 1:现金，2：刷卡，3：支付宝，4：微信
	
	public String getItem_id() {
		return item_id;
	}
	
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	
	public String getItem_name() {
		return item_name;
	}
	
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
	public String getOrder_no() {
		return order_no;
	}
	
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	
	public String getOrder_id() {
		return order_id;
	}
	
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getTicket_id() {
		return ticket_id;
	}
	
	public void setTicket_id(String ticket_id) {
		this.ticket_id = ticket_id;
	}
	
	public String getCust_name() {
		return cust_name;
	}
	
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	
	public String getCust_phone() {
		return cust_phone;
	}
	
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	
	public String getCust_no() {
		return cust_no;
	}
	
	public void setCust_no(String cust_no) {
		this.cust_no = cust_no;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getVerify_code() {
		return verify_code;
	}
	
	public void setVerify_code(String verify_code) {
		this.verify_code = verify_code;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getExpire_time() {
		return expire_time;
	}
	
	public void setExpire_time(String expire_time) {
		this.expire_time = expire_time;
	}
	
	public String getActive_time() {
		return active_time;
	}
	
	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}
	
	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	public String getUpdate_time() {
		return update_time;
	}
	
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	public String getAgent_id() {
		return agent_id;
	}
	
	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}
	
	public String getAgent_name() {
		return agent_name;
	}
	
	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}
	
	public String getThird_id() {
		return third_id;
	}
	
	public void setThird_id(String third_id) {
		this.third_id = third_id;
	}
	
	public String getThird_name() {
		return third_name;
	}
	
	public void setThird_name(String third_name) {
		this.third_name = third_name;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
}
