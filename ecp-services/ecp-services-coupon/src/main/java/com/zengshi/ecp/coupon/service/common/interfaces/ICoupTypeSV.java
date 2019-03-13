package com.zengshi.ecp.coupon.service.common.interfaces;

import java.util.List;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-2 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface ICoupTypeSV {

    /**
     * 优惠券类型保存
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveCoupType(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException;
    /**
     * 优惠券类型保存 （编辑）
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveeditCoupType(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException;

    /**
     * 优惠券类型 edit
     * 
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveCoupTypeStatusById(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException;

    /**
     * 优惠券类型查询 编码必传
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupTypeRespDTO queryCoupTypeById(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException;

    /**
     * 优惠券类型查询
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupTypeRespDTO> queryCoupTypeList(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException;

    /**
     * 优惠券类型查询
     * 
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<CoupTypeRespDTO> queryCoupTypePage(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException;
}
