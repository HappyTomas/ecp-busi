package com.zengshi.ecp.goods.dubbo.impl;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropManageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropManageRSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropManageSV;
import com.zengshi.ecp.server.front.exception.BusinessException;

import javax.annotation.Resource;

/**
 * Created by HDF on 2016/8/10.
 */
public class GdsPropManageRSVImpl extends AbstractRSVImpl implements IGdsPropManageRSV {

    @Resource(name="gdsPropManageSV")
    private IGdsPropManageSV gdsPropManageSV;

    @Override
    public GdsPropManageRespDTO queryAllMergedProps(GdsCatg2PropReqDTO reqDTO, GdsType2PropReqDTO reqDTO2) throws BusinessException {
        return gdsPropManageSV.queryAllMergedProps(reqDTO,reqDTO2);
    }
}
