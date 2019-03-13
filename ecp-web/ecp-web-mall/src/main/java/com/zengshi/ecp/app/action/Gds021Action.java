package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds021Req;
import com.zengshi.ecp.app.resp.Gds021Resp;
import com.zengshi.ecp.app.resp.gds.ShopSearchResultVO;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 店铺搜索
 * Created by HDF on 2016/6/1.
 */
@Service("gds021")
@Action(bizcode = "gds021", userCheck = false)
@Scope("prototype")
public class Gds021Action extends AppBaseAction<Gds021Req, Gds021Resp>{
    @Override
    protected void getResponse() {

        Gds021Req gds021Req = this.request;
        Gds021Resp gds021Resp = this.response;

        SearchResult<ShopSearchResultVO> searchResult = ShopSearchUtil
                .searchShop(gds021Req);
        if (searchResult.isSuccess()) {

            gds021Resp.setCount(searchResult.getNumFound());
            gds021Resp.setPageCount(searchResult.getTotallyPage());
            gds021Resp.setPageRespVO(ShopSearchUtil.renderShopSearchResult(searchResult.getResultList()));
        }else{
            throw new BusinessException(searchResult.getMessage());
        }

    }
}
