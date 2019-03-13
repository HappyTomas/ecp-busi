package com.zengshi.express.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdExpressReqLogRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.aip.security.DefaultServiceCheckChain;
import com.zengshi.butterfly.core.annotation.Security;
import com.zengshi.butterfly.core.web.BaseController;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * 快递100消息回掉
 * @author l2iu
 *
 */
@Controller
public class ExpressController extends BaseController{

	@Resource
	private IOrdExpressReqLogRSV ordExpressReqLogRSV;
	@Resource
	private IOrderExpressRSV orderExpressRSV;
	
	@RequestMapping(value="/rest" ,params="method=com.kuaidi.callBack.expressCallBack")
    @Security(mustLogin=true,authorCheckType=DefaultServiceCheckChain.class)
    @ResponseBody
	public Map<String,Object> expressCallBack(String sign,String param){
		Map<String,Object> resp = new HashMap<String,Object>();
		try{
			//处理业务
			if(StringUtil.isBlank(param)){
				//抛出业务异常
				throw new BusinessException("快递100请求参数param为空！");
			}else{				
				//响应处理结果
				orderExpressRSV.dealOrderExpress(sign, param);
				resp.put("result", true);
				resp.put("returnCode;", "200");	
				resp.put("message", "成功");
			}
		}catch(BusinessException e){
			resp.put("result", false);
			resp.put("returnCode", "501");
			resp.put("message;", e.getMessage());
		}catch(Exception e){
			//抛出服务器处理异常
			resp.put("result", false);
			resp.put("returnCode", "500");
			resp.put("message;", e.getMessage());
		}finally{
			this.writeLog(param, resp);
		}
		return resp;
	}
	
	/**
	 * 写日志
	 * @param param
	 * @param resp
	 */
	private void writeLog(String param,Map<String,Object> resp){
		OrdExpressLogReq log = new OrdExpressLogReq();
		if(StringUtils.isNotBlank(param)){
			JSONObject body = JSONObject.parseObject(param);
			String status =  body.getString("status");
			String comNew =  body.getString("comNew");
			String com =  body.getString("com");
			if(status.equals("about")){			
				if(StringUtils.isNotBlank(comNew)){
					com = comNew;
				}		
			}
			String lastResult = body.getString("lastResult");
			JSONObject lastResultBody = JSONObject.parseObject(lastResult);	
			String nu = lastResultBody.getString("nu");//运单号			
			log.setExpressCode(com);
			log.setExpressNo(nu);	
			String result = resp.get("result").toString();
			log.setResult(result);
			log.setReqParam(param.getBytes());
		}else{
			log.setResult("false");
		}		
		log.setExpressInterface("ExpressCallBack");//快递100消息回掉

		log.setRespParam(JSONObject.toJSONString(resp).getBytes());

		if(log.getResult().equals("true")){
			log.setResult("1");
		}else{
			log.setResult("0");
		}
		log.setStaffId(1000L);
		ordExpressReqLogRSV.saveOrdExpressLog(log);
	}
}
