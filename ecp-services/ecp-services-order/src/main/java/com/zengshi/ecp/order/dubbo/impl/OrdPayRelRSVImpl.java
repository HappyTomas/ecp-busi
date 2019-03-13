package com.zengshi.ecp.order.dubbo.impl;

import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelReq;
import com.zengshi.ecp.order.dubbo.dto.ROrdPayRelResp;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdPayRelRSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdPayRelSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;
import java.util.List;

public class OrdPayRelRSVImpl implements IOrdPayRelRSV {
    @Resource
    private IOrdPayRelSV iOrdPayRelSV;
    
    @Override
    public ROrdPayRelResp saveOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException {
        return iOrdPayRelSV.saveOrdPayRel(rOrdPayRelReq);
    }

    @Override
    public List<ROrdPayRelResp> queryOrdPayRelByOption(ROrdPayRelReq rOrdPayRelReq)
            throws BusinessException {
        return iOrdPayRelSV.queryOrdPayRelByOption(rOrdPayRelReq);
    }

    @Override
    public void updateOrdPayRel(ROrdPayRelReq rOrdPayRelReq) throws BusinessException {
        iOrdPayRelSV.updateOrdPayRel(rOrdPayRelReq);
    }

}

