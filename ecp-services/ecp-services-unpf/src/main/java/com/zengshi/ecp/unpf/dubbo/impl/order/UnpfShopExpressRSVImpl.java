package com.zengshi.ecp.unpf.dubbo.impl.order;

import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderLogisticsReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfShopExpress;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfShopExpressReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfShopExpressResp;
import com.zengshi.ecp.unpf.dubbo.interfaces.order.IUnpfShopExpressRSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfShopExpressSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * 同步统一订单物流公司信息
 *
 * @author l2iu
 */
public class UnpfShopExpressRSVImpl implements IUnpfShopExpressRSV {

    @Resource
    private IUnpfShopExpressSV unpfShopExpressSV;

    @Override
    public void dealShopExpress(OrderLogisticsReqDTO req) throws BusinessException {
        // TODO Auto-generated method stub
        unpfShopExpressSV.dealShopExpress(req);
    }

    @Override
    public List<RUnpfShopExpressResp> queryShopExpressResp(RUnpfShopExpressReq req) throws BusinessException {
        // TODO Auto-generated method stub
        if (StringUtil.isEmpty(req)) {
            throw new BusinessException("unpf.100001");
        }
        if (StringUtil.isEmpty(req.getShopId())) {
            throw new BusinessException("unpf.100010");
        }
        if (StringUtil.isBlank(req.getPlatType())) {
            throw new BusinessException("unpf.100011");
        }
        return unpfShopExpressSV.queryShopExpressResp(req);
    }

    @Override
    public void insert(UnpfShopExpressReqDTO expressDTO) {
        UnpfShopExpress express = new UnpfShopExpress();
        ObjectCopyUtil.copyObjValue(expressDTO, express, null, false);
        unpfShopExpressSV.insert(express);
    }
}
