package com.zengshi.ecp.order.service.busi.impl.report;

import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdSubShopIdxUalMapper;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdxCriteria;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.service.busi.interfaces.report.IOrdGdsSaleSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class OrdGdsSaleSVImpl implements IOrdGdsSaleSV {
    
    @Resource
    private OrdSubShopIdxUalMapper ordSubShopIdxUalMapper;

    @Override
    public Long querySumBuyNumByShopId(RGoodSaleRequest rGoodSaleRequest) throws BusinessException {
        
        OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();

        OrdSubShopIdxCriteria.Criteria ca = osc.createCriteria();
        if(rGoodSaleRequest.getSiteId()!= null){
            ca.andSiteIdEqualTo(rGoodSaleRequest.getSiteId());
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getShopId())){
            ca.andShopIdEqualTo(rGoodSaleRequest.getShopId());
        }
        List<String> payedList = new ArrayList<>();
        payedList.add("02");
        payedList.add("04");
        payedList.add("05");
        payedList.add("06");
        payedList.add("07");
        payedList.add("08");
        payedList.add("80");
        ca.andStatusIn(payedList);
        
        Long resp = this.ordSubShopIdxUalMapper.sumOrderAmountForShopByExample(osc);
        return resp;
    }

}

