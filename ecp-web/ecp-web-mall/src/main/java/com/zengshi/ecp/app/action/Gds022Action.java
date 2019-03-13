package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds022Req;
import com.zengshi.ecp.app.resp.Gds022Resp;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.CollationReuslt;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 店铺搜索建议
 * Created by HDF on 2016/6/1.
 */
@Service("gds022")
@Action(bizcode = "gds022", userCheck = false)
@Scope("prototype")
public class Gds022Action extends AppBaseAction<Gds022Req, Gds022Resp>{
    @Override
    protected void getResponse() {

        Gds022Req gds022Req = this.request;
        Gds022Resp gds022Resp = this.response;

        SearchParam searchParam = new SearchParam();
        searchParam.setKeyword(gds022Req.getKeyword());
        searchParam.setCollectionName("shopcollection");
        SearchResult<CollationReuslt> result = SearchFacade.suggest2(searchParam);

        if (result.isSuccess()) {
            gds022Resp.setCollationReuslts(result.getResultList());
        }else{
            throw new BusinessException(result.getMessage());
        }
    }
}
