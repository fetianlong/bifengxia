package com.chatm.search.Ticket;

/**
 * 订单推送到票务系统
 * @author pu
 *
 */
public class TicktOrder {
	private String item_id;	//商品id
	private String item_name;	//商品名
	private String cust_name;	//客户名称
	private String cust_phone;	//客户手机号
	private String cust_no;	//客户身份证号
	
	private String order_id;	//订单号
	private String order_no;	//批次号
	private String ticket_id;	//票种id
	private String price;	//单价
	private String amount;	//总价
	private Integer size;	//购买次数1
	private String verify_code;	//票号，由票务系统产生
	private String status;	//状态: 0,未支付  1,可用  2,已使用  3,已过期
	private String expire_time;
	private String active_time;	//票的开始可用时间
	private String create_time;	//订单时间
	private String pay_type;	//1:现金，2：刷卡，3：支付宝，4：微信
	
	
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
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
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
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
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
}
