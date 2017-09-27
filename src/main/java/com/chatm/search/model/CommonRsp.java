package com.chatm.search.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 推送票务系统使用
 * @author pu
 *
 */
public class CommonRsp {
	int rsp_code;
	String rsp_desc;
	Map<String, Object> data = new HashMap<>();
	
	public int getRsp_code() {
		return rsp_code;
	}
	
	public CommonRsp setRsp_code(int rsp_code) {
		this.rsp_code = rsp_code;
		return this;
	}
	
	public String getRsp_desc() {
		return rsp_desc;
	}
	
	public CommonRsp setRsp_desc(String rsp_desc) {
		this.rsp_desc = rsp_desc;
		return this;
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	public CommonRsp setData(Map<String, Object> data) {
		this.data = data;
		return this;
	}
	
	public CommonRsp putData(String key, Object value) {
		this.data.put(key, value);
		return this;
	}
	
	public Object getData(String key) {
		return this.data.get(key);
	}
}
