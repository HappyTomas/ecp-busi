/**
 *  客户调用发货接口时，传入的参数会映射到这个对象中
 */
package com.zengshi.model;

import java.util.List;

/**
 * @author luowenguan
 *
 */
public class OrderShipReq {
	private String order_id;				//主订单ID
	private String delivery_flag;			//提货类型		0自提, 1快递
	private String	express;  				//快递公司编码
	private String	express_name;		//快递公司名称
	private String	express_no;			//运单编码
	private String	remark_info;		//备注
	private String  sub_order;			//子订单信息
	private String province_code; //省份编码
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getDelivery_flag() {
		return delivery_flag;
	}
	public void setDelivery_flag(String delivery_flag) {
		this.delivery_flag = delivery_flag;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getRemark_info() {
		return remark_info;
	}
	public void setRemark_info(String remark_info) {
		this.remark_info = remark_info;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getSub_order() {
		return sub_order;
	}
	public void setSub_order(String sub_order) {
		this.sub_order = sub_order;
	}
	
	
}
