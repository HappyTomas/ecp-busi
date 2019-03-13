package com.zengshi.ecp.app.resp;

import java.util.Map;

import com.zengshi.butterfly.app.model.IBody;

public class Pay004Resp extends IBody {

    /** 
     * isStatus:请求状态. 
     * @since JDK 1.6 
     */ 
    private boolean isStatus;
    /** 
     * msg:状态信息. 
     * @since JDK 1.6 
     */ 
    private String msg;
    /** 
     * actionUrl:请求表单URL
     * @since JDK 1.6 
     */ 
    private String actionUrl;
    /** 
     * method:提交方式. 
     * @since JDK 1.6 
     */ 
    private String method;
    /** 
     * charset:请求表单字符集. 
     * @since JDK 1.6 
     */ 
    private String charset;
    /** 
     * formData:各通道各异的表单数据. 
     * @since JDK 1.6 
     */ 
    private Map<String,String> formData;
    /** 
     * 商户证书密码(支付宝SDK需要用)
     * @since JDK 1.6 
     */ 
	private String cerPassword; 
	
    /** 
     * 合并支付订单编号
     * @since JDK 1.6 
     */ 	
	private String joinOrdId;
	
    /** 
     * app的请求地址
     * @since JDK 1.6 
     */ 	
	private String appActionUrl;
	
    public boolean isStatus() {
        return isStatus;
    }
    public void setStatus(boolean isStatus) {
        this.isStatus = isStatus;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getActionUrl() {
        return actionUrl;
    }
    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getCharset() {
        return charset;
    }
    public void setCharset(String charset) {
        this.charset = charset;
    }
    public Map<String, String> getFormData() {
        return formData;
    }
    public void setFormData(Map<String, String> formData) {
        this.formData = formData;
    }
	public String getCerPassword() {
		return cerPassword;
	}
	public void setCerPassword(String cerPassword) {
		this.cerPassword = cerPassword;
	}
	public String getJoinOrdId() {
		return joinOrdId;
	}
	public void setJoinOrdId(String joinOrdId) {
		this.joinOrdId = joinOrdId;
	}
	public String getAppActionUrl() {
		return appActionUrl;
	}
	public void setAppActionUrl(String appActionUrl) {
		this.appActionUrl = appActionUrl;
	}


    
}

