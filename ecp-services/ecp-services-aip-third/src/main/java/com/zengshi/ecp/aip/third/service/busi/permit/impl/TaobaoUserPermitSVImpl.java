package com.zengshi.ecp.aip.third.service.busi.permit.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.ShopTopicReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicListRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.ShopTopicRespDTO;
import com.zengshi.ecp.aip.third.service.busi.permit.interfaces.IUserPermitSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TmcUserCancelRequest;
import com.taobao.api.request.TmcUserGetRequest;
import com.taobao.api.request.TmcUserPermitRequest;
import com.taobao.api.response.TmcUserCancelResponse;
import com.taobao.api.response.TmcUserGetResponse;
import com.taobao.api.response.TmcUserPermitResponse;

public class TaobaoUserPermitSVImpl implements IUserPermitSV{
    
    public static final String MODULE = TaobaoUserPermitSVImpl.class.getName();

    @Resource
  	private ITokenSV defaultTokenSV;
    
  //返回字段设置
  	private String fields;
  	
  	public String getFields() {
  		return fields;
  	}
  	public void setFields(String fields) {
  		this.fields = fields;
  	}
  	
    @Override
    public String createPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
    	 String re="0";
        try{ 
        	
        	TaobaoClient client = new DefaultTaobaoClient(
        			shopTopicReqDTO.getServerUrl(), shopTopicReqDTO.getAppkey(),
        			shopTopicReqDTO.getAppscret());
        	
            TmcUserPermitRequest req = new TmcUserPermitRequest();  
            //req.setTopics("taobao_trade_TradeSuccess,taobao_trade_TradeCreate,taobao_trade_TradeBuyerPay"); //开通消息列表  
            req.setTopics(shopTopicReqDTO.getTopic());
           // 验证参数是否正确
           req.check();
	       String token=defaultTokenSV.fetchShopToken(shopTopicReqDTO.findBaseShopAuth());
	       TmcUserPermitResponse rsp = client.execute(req,token); 
	       
	      	// 返回结果解析
			if (rsp.isSuccess()) { 
				re="1";
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
			
        } catch (BusinessException e){
			LogUtil.error(MODULE, "授权失败",e);
			throw e;
		} catch(Exception e){
        	LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }    
    	
        return re;
    	 
    }
	// 获得第三方平台 允许调用消息列表
	@Override
    public ShopTopicListRespDTO userPermitList(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
		
		shopTopicReqDTO.checkTaoBaoUserPermitList();
		
		ShopTopicListRespDTO resps=null;
        try{ 
        	TaobaoClient client = new DefaultTaobaoClient(
        			shopTopicReqDTO.getServerUrl(), shopTopicReqDTO.getAppkey(),
        			shopTopicReqDTO.getAppscret());
        	
        	TmcUserGetRequest  req = new TmcUserGetRequest();  
        	req.setFields(this.fields);  
        	req.setNick(shopTopicReqDTO.getNick()); //店铺昵称  即登陆用户
        	
           // 验证参数是否正确
           req.check();
           TmcUserGetResponse rsp = client.execute(req);
           /*{"tmc_user_get_response":{"tmc_user":{"created":"2016-11-16 09:41:53","is_valid":true,"modified":"2016-11-16 09:41:53","topics":{"string":["taobao_item_ItemStockChanged"]},"user_id":2076226623,"user_nick":"sandbox_b_16"},"request_id":"16ecoqo68zsa1"}}*/
	      	// 返回结果解析
			if (rsp.isSuccess()) { 
				if(StringUtil.isNotBlank(rsp.getBody())){
					JSONObject js = JSON.parseObject((String) rsp.getBody());
					if(js!=null){
						JSONObject tmc_user_get_response=js.getJSONObject("tmc_user_get_response");
						if(tmc_user_get_response==null){
							return null;
						}
						JSONObject tmc_user=tmc_user_get_response.getJSONObject("tmc_user");
						if(tmc_user==null){
							return null;
						}
						JSONObject topic=tmc_user.getJSONObject("topics");
						if(topic==null){
							return null;
						}
						JSONArray jsarr=new JSONArray();
						jsarr=topic.getJSONArray("string");
						if(jsarr!=null){
							 List<ShopTopicRespDTO> topics=new ArrayList<ShopTopicRespDTO>();
							 resps=new ShopTopicListRespDTO();
							for(Object o:jsarr){
								 ShopTopicRespDTO t=new ShopTopicRespDTO();
								 t.setIfValid(tmc_user.getBoolean("is_valid")?"1":"0");
								 t.setNick(tmc_user.getString("user_nick"));
								 t.setTopic(o.toString());
								 t.setUserId(tmc_user.getString("user_id"));
								 topics.add(t);
							}
							 resps.setShopTopicRespDTOs(topics);
						}
					}
				}
				
				
			} else {
				LogUtil.error(MODULE, rsp.getBody());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}
			
        }catch(Exception e){
        	LogUtil.error(MODULE, e.toString());
        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
        }    
    	
        return resps;
    }

  //授权取消
   @Override
   public String cancelPermit(ShopTopicReqDTO shopTopicReqDTO)throws BusinessException{
	    	
	      //验证参数
	       shopTopicReqDTO.checkTaoBaoCancelPermit();
	       String re="0";
	        try{ 
	        	
	        	TaobaoClient client = new DefaultTaobaoClient(
	        			shopTopicReqDTO.getServerUrl(), shopTopicReqDTO.getAppkey(),
	        			shopTopicReqDTO.getAppscret());
	        	
	        	TmcUserCancelRequest req = new TmcUserCancelRequest();
	        	req.setNick(shopTopicReqDTO.getNick());
	           // 验证参数是否正确
	        	TmcUserCancelResponse  rsp = client.execute(req); 
		       
		      	// 返回结果解析
				if (rsp.isSuccess()) { 
					re="1";
				} else {
					LogUtil.error(MODULE, rsp.getBody().toString());
					throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
				}
				
	        }catch(Exception e){
	        	LogUtil.error(MODULE, e.toString());
	        	 throw new BusinessException("AIPTHIRD.ERRORS.100002",new String[]{e.toString()});
	        }    
	    	
	        return re;
	    	 
	    }
}

