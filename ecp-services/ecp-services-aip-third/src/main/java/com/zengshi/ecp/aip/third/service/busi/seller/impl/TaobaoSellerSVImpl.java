package com.zengshi.ecp.aip.third.service.busi.seller.impl;

import javax.annotation.Resource;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SellerRespDTO;
import com.zengshi.ecp.aip.third.service.busi.seller.interfaces.ISellerSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;

public class TaobaoSellerSVImpl implements ISellerSV{
    
    public static final String MODULE = TaobaoSellerSVImpl.class.getName();

    @Resource
   	private ITokenSV defaultTokenSV;
    
    //返回字段设置  user_id nick sex status type
  	private String fields;
  	
  	public String getFields() {
  		return fields;
  	}
  	public void setFields(String fields) {
  		this.fields = fields;
  	}
  	
    @Override
    public SellerRespDTO getSellerInfo(BaseShopAuthReqDTO baseShopAuthReqDTO)throws BusinessException{
    	SellerRespDTO resp=null;
        try{ 
        	
        	TaobaoClient client = new DefaultTaobaoClient(
        			baseShopAuthReqDTO.getServerUrl(), baseShopAuthReqDTO.getAppkey(),
        			baseShopAuthReqDTO.getAppscret());
        	
        	UserSellerGetRequest req = new UserSellerGetRequest();
            req.setFields(fields);//必填
           // 验证参数是否正确
           req.check();
	       String token=defaultTokenSV.fetchShopToken(baseShopAuthReqDTO);
	       UserSellerGetResponse rsp = client.execute(req,token); 
	       
	      	// 返回结果解析
			if (rsp.isSuccess()) { 
			       resp=new SellerRespDTO();
			       resp.setNick(rsp.getUser().getNick());
			       resp.setSex(rsp.getUser().getSex());
			       resp.setStatus(rsp.getUser().getStatus());
			       resp.setUserId(rsp.getUser().getUserId().toString());
			       resp.setType(rsp.getUser().getType());
			       
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
			
        }catch(Exception e){
        	LogUtil.error(MODULE, e.toString());
        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }    
    	
        return resp;
    	 
    }
	 
}

