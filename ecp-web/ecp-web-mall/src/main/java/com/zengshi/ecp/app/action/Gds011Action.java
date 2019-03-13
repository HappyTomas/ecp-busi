package com.zengshi.ecp.app.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds011Req;
import com.zengshi.ecp.app.resp.Gds011Resp;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月12日下午5:16:50  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
@Service("gds011")
@Action(bizcode = "gds011", userCheck = false)
@Scope("prototype")
public class Gds011Action extends AppBaseAction<Gds011Req, Gds011Resp> {


    private static final String MODULE = Gds011Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        
        Gds011Req gds011Req = this.request;
        Gds011Resp gds011Resp = this.response;
        SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setKeyword(gds011Req.getKeyword());
        SearchResult<CollationReuslt> result = SearchFacade.suggest2(searchParam,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
                IGdsCatalog2SiteRSV.class)));
        gds011Resp.setCollationReuslts(result.getResultList());
    }

}