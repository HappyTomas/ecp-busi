package com.zengshi.ecp.busi.seller.prom.shop.vo;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

public class PromChkVO extends EcpBasePageReqVO implements Serializable {

	private static final long serialVersionUID = 1L;

	//促销类型
	//审核状态
	//申请时间
	//店铺名称
	private Long id;
	@NotNull
	private Long promId;   
	@NotNull
	private String status;  
	@NotNull
	private String chkDesc;     
	
    private Long createStaff;
	                           
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreateStaff() {
		return createStaff;
	}

	public void setCreateStaff(Long createStaff) {
		this.createStaff = createStaff;
	}

	public Long getPromId() {                         
	  return promId;                                  
	}                                                 
	                                                  
	public void setPromId(Long promId) {              
	  this.promId = promId;                           
	}                                                 
	                                                  
	public String getStatus() {                       
	  return status;                                  
	}                                                 
	                                                  
	public void setStatus(String status) {            
	  this.status = status;                           
	}                                                 
	                                                  
	public String getChkDesc() {                      
	  return chkDesc;                                 
	}                                                 
	                                                  
	public void setChkDesc(String chkDesc) {          
	  this.chkDesc = chkDesc;                         
	}   

}
