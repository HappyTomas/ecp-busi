package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds023Req;
import com.zengshi.ecp.app.resp.Gds023Resp;
import com.zengshi.ecp.app.resp.gds.GoodSearchResultVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 店铺热销
 * Created by HDF on 2016/6/1.
 */
@Service("gds023")
@Action(bizcode = "gds023", userCheck = false)
@Scope("prototype")
public class Gds023Action extends AppBaseAction<Gds023Req, Gds023Resp>{
    @Override
    protected void getResponse() {

        Gds023Req gds023Req = this.request;
        Gds023Resp gds023Resp = this.response;

        SearchResult<GoodSearchResult> searchResult = ShopSearchUtil.shopHotSales(gds023Req);

        if (searchResult.isSuccess()) {

            searchResult.setResultList(ShopSearchUtil.renderGoodSearchResult(searchResult.getResultList()));
            List<GoodSearchResultVO> goodSearchResultVOs = new ArrayList<GoodSearchResultVO>();
            for (GoodSearchResult goodSearchResult : searchResult.getResultList()) {
                GoodSearchResultVO goodSearchResultVO = new GoodSearchResultVO();
                ObjectCopyUtil.copyObjValue(goodSearchResult, goodSearchResultVO, null, false);
                goodSearchResultVOs.add(goodSearchResultVO);
            }
            gds023Resp.setCount(searchResult.getNumFound());
            gds023Resp.setPageCount(searchResult.getTotallyPage());
            gds023Resp.setPageRespVO(goodSearchResultVOs);
        }else{
            throw new BusinessException(searchResult.getMessage());
        }
    }
}
