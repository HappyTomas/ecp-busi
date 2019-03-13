package com.zengshi.ecp.app.action;

import com.zengshi.ecp.app.req.Gds018Req;
import com.zengshi.ecp.app.req.Gds024Req;
import com.zengshi.ecp.app.resp.Gds018Resp;
import com.zengshi.ecp.app.resp.Gds024Resp;
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
 * 店铺内商品最新上架
 * Created by HDF on 2016/6/1.
 */
@Service("gds024")
@Action(bizcode = "gds024", userCheck = false)
@Scope("prototype")
public class Gds024Action extends AppBaseAction<Gds024Req, Gds024Resp>{
    @Override
    protected void getResponse() {

        Gds024Req gds024Req = this.request;
        Gds024Resp gds024Resp = this.response;

        SearchResult<GoodSearchResult> searchResult = ShopSearchUtil.shopNewSales(gds024Req);

        if (searchResult.isSuccess()) {

            searchResult.setResultList(ShopSearchUtil.renderGoodSearchResult(searchResult.getResultList()));
            List<GoodSearchResultVO> goodSearchResultVOs = new ArrayList<GoodSearchResultVO>();
            for (GoodSearchResult goodSearchResult : searchResult.getResultList()) {
                GoodSearchResultVO goodSearchResultVO = new GoodSearchResultVO();
                ObjectCopyUtil.copyObjValue(goodSearchResult, goodSearchResultVO, null, false);
                goodSearchResultVOs.add(goodSearchResultVO);
            }
            gds024Resp.setCount(searchResult.getNumFound());
            gds024Resp.setPageCount(searchResult.getTotallyPage());
            gds024Resp.setPageRespVO(goodSearchResultVOs);
        }else{
            throw new BusinessException(searchResult.getMessage());
        }
    }
}
