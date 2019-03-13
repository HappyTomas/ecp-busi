package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Ord022Req;
import com.zengshi.ecp.app.resp.Ord022Resp;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 查询系统配置参数<br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author 
 * @version
 * @since JDK 1.6
 */

@Service("ord022")
@Action(bizcode = "ord022", userCheck = true)
@Scope("prototype")
public class Ord022Action extends AppBaseAction<Ord022Req,Ord022Resp>{
	private static final String MODULE = Ord022Action.class.getName();

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		try{
			// 获取合并支付的开关 1-表示开启  0-表示关闭
			BaseSysCfgRespDTO payMergeSysCfg = SysCfgUtil.fetchSysCfg(this.request.getSpCode());
//			if(OrdConstants.OrdPayRel.PAY_MERGE_ENABLE.equals(payMergeSysCfg.getParaValue())){
			if(payMergeSysCfg == null){
				this.response.setParaValue("0");
			} else {
				this.response.setParaCode(payMergeSysCfg.getParaCode());
				this.response.setParaDesc(payMergeSysCfg.getParaDesc());
				this.response.setParaValue(payMergeSysCfg.getParaValue());
			}

		}catch(Exception e){
			 LogUtil.info(MODULE, "获取系统参数失败");
			 throw new Exception("获取系统参数失败");
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
