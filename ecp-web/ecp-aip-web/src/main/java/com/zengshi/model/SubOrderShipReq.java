package com.zengshi.model;
/**
 * 通过订单接口发货时，每个子订单发货情况
 * @author luowg
 *
 */
public class SubOrderShipReq {
	
	private String sub_id;//ID
	private String cat_id;//商品大类型 100终端 200配件 300 无线上网卡 400 手机套包 900 卡包
	private String[] imeis;//串号
	private String ship_type;//0，全部与发货 1分批发
	private Integer ship_amount;//本次商品发货数量
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String[] getImeis() {
		return imeis;
	}
	public void setImeis(String[] imeis) {
		this.imeis = imeis;
	}
	public String getShip_type() {
		return ship_type;
	}
	public void setShip_type(String ship_type) {
		this.ship_type = ship_type;
	}
	public Integer getShip_amount() {
		return ship_amount;
	}
	public void setShip_amount(Integer ship_amount) {
		this.ship_amount = ship_amount;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	
}
