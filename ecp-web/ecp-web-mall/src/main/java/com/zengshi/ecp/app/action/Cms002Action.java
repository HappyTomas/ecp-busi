package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Cms002Req;
import com.zengshi.ecp.app.resp.Cms002Resp;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 获取APP栏目服务<br>
 * Date:2016-2-22下午6:51:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6 
 */

@Service("cms002")
@Action(bizcode="cms002", userCheck=false)
@Scope("prototype")
public class Cms002Action extends AppBaseAction<Cms002Req, Cms002Resp> {

    private static String MODULE = Cms002Action.class.getName();
   
    @Resource(name = "cmsChannelRSV")
    private ICmsChannelRSV cmsChannelRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        LogUtil.info(MODULE, "==========开始进入cms002 app获取栏目服务============");
        //1.获取入参  并初始化请求参数
        CmsChannelReqDTO reqDto= new CmsChannelReqDTO();
        ObjectCopyUtil.copyObjValue(this.request, reqDto, null, false);
        
        
    }

}

