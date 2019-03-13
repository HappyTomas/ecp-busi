/** 
 * Project Name:ecp-web-mall 
 * File Name:AppBaseAction.java 
 * Package Name:com.zengshi.ecp.app.base 
 * Date:2016-2-22下午6:25:09 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.butterfly.app.base.BaseActionHandler;
import com.zengshi.butterfly.app.model.IBody;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-2-22下午6:25:09  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public abstract class AppBaseAction<Request extends IBody, Response extends IBody> extends BaseActionHandler<Request, Response> {
    
    protected Logger logger = LoggerFactory.getLogger(AppBaseAction.class);
    
    protected boolean hasCalledFailed;
    
    protected String errorMsg;
    
    public void doAction() throws BusinessException, SystemException, Exception{
        before();
        getResponse();
        after();
    }

    protected abstract void getResponse() throws BusinessException, SystemException, Exception;
    
    /**
     * 
     * before: 前置操作。用于处理用户信息<br/> 
     * 
     * @author yugn 
     * @throws Exception 
     * @since JDK 1.6
     */
    protected void before() throws Exception {
        
        logger.info("---------------------------------> base action before");

    }
    
    protected void setFaild(String msg) throws Exception {
        hasCalledFailed = true;
        errorMsg = msg;
        throw new Exception(msg);
    }

    protected void setFaild(String msg, Exception e) throws Exception {
        logger.error(msg, e);
        if(e instanceof com.zengshi.ecp.server.front.exception.BusinessException){
            setFaild(((com.zengshi.ecp.server.front.exception.BusinessException)e).getErrorMessage());
        }else{
            setFaild(msg);
        }
    }
    
    protected void after() throws Exception {
        if (hasCalledFailed) {
            throw new Exception(errorMsg);
        }
    }
}

