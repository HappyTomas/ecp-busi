package com.zengshi.ecp.order.dubbo.dto.pay;

import java.util.HashMap;
import java.util.Map;

public class ABCPayWayRequest extends BasePayRequest{

	/** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private String MSG;
	private String errorPage;
	
	@Override
	public String buildSign(String[] signParams) {
		String sign = "";
		for(int i=0;i<signParams.length;i++){
			sign+=signParams[i]==null?"":signParams[i];
		}
		return sign;
	}

	@Override
	public Map<String, String> toMap() {
		Map<String,String> result =new HashMap<String,String>();
		result.put("MSG", MSG);
		result.put("errorPage", errorPage);
		return result;
	}

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String mSG) {
        MSG = mSG;
    }

    public String getErrorPage() {
        return errorPage;
    }

    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }

}
