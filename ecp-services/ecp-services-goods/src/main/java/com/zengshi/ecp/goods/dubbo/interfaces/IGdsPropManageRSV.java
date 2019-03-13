package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropManageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by HDF on 2016/8/10.
 */
public interface IGdsPropManageRSV {

    GdsPropManageRespDTO queryAllMergedProps(GdsCatg2PropReqDTO reqDTO, GdsType2PropReqDTO reqDTO2) throws BusinessException;

}
