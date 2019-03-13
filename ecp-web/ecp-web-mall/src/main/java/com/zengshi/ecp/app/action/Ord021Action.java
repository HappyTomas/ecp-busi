package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord021Req;
import com.zengshi.ecp.app.resp.Ord021Resp;
import com.zengshi.ecp.busi.order.OrdConstant;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderExpressRSV;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询物流信息html5页面入口<br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author 
 * @version
 * @since JDK 1.6
 */

@Service("ord021")
@Action(bizcode = "ord021", userCheck = true)
@Scope("prototype")
public class Ord021Action  extends AppBaseAction<Ord021Req,Ord021Resp>{
	private static final String MODULE = Ord021Action.class.getName();
	
    @Resource
    private IOrderExpressRSV orderExpressRSV;
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		// TODO Auto-generated method stub
		try{			
				String redisKey = md5key(this.request.getOrderId()+DateUtil.getCurrentTimeMillis());
			    CacheUtil.addItem(OrdConstant.ORDER_SESSION_KEY_PREFIX+redisKey,redisKey,OrdConstant.ORDER_SESSION_TIME);       
				String url = BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING,"1")+"/order/queryAppExpress/"+redisKey+"/"+this.request.getOrderId();			
			   // String url = "http://192.168.1.122:8083/ecp-web-mobile/order/queryAppExpress/"+redisKey+"/"+this.request.getOrderId();
				this.response.setUrl(url);			
		}catch(Exception e){
			 LogUtil.info(MODULE, "获取物流信息失败");
			 throw new Exception("获取物流信息失败");
		}

	}
	
	 /**
     * 
     * md5key:MD5加密. <br/> 
     * @author l2iu 
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private String md5key(String str){
        return ParamsTool.MD5(str).toLowerCase();
    }

}
