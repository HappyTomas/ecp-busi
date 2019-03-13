package com.zengshi.ecp.coupon.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.coupon.dubbo.util.CoupCacheUtil;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-1 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupTypeRSVImpl implements ICoupTypeRSV {

    private static final String MODULE = CoupTypeRSVImpl.class.getName();
    
    @Resource
    private ICoupTypeSV coupTypeSV;
    /**
     * 优惠券类型保存
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveCoupType(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException{
        
        if(coupTypeReqDTO==null){
            throw new BusinessException("coupon.450001");
        }
        //id 为空 新增
        if(StringUtil.isEmpty(coupTypeReqDTO.getId())){
            coupTypeSV.saveCoupType(coupTypeReqDTO);
        }else{
            coupTypeSV.saveeditCoupType(coupTypeReqDTO);
        }
        //提交 保存缓存数据
        if(CouponConstants.CoupType.STATUS_1.equals(coupTypeReqDTO.getStatus())){
            CoupCacheUtil.addCoupType(coupTypeReqDTO.getId());
        }
       
    }
 
    /**
     * 优惠券类型 生效
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void validCoupType(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException{
        if(coupTypeReqDTO==null){
            throw new BusinessException("coupon.450001");
        }
        if(coupTypeReqDTO.getId()==null){
            //优惠券类型id为空
            throw new BusinessException("coupon.450002");
        }
      /*  if(StringUtil.isEmpty(coupTypeReqDTO.getStatus())){
            //优惠券类型 状态为空
            throw new BusinessException("coupon.450003");
        }*/
        coupTypeReqDTO.setStatus(CouponConstants.CoupType.STATUS_1);
        coupTypeSV.saveCoupTypeStatusById(coupTypeReqDTO);
        //缓存
        CoupCacheUtil.addCoupType(coupTypeReqDTO.getId());
    }
    
    /**
     * 优惠券类型 失效
     * @param coupTypeReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void invalidCoupType(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException{
        if(coupTypeReqDTO==null){
            throw new BusinessException("coupon.450001");
        }
        if(coupTypeReqDTO.getId()==null){
            //优惠券类型id为空
            throw new BusinessException("coupon.450002");
        }
        coupTypeReqDTO.setStatus(CouponConstants.CoupType.STATUS_0);
        
        coupTypeSV.saveCoupTypeStatusById(coupTypeReqDTO);
        
        //缓存
        CoupCacheUtil.addCoupType(coupTypeReqDTO.getId());
    }
    
    /**
     *  优惠券类型查询  编码必传
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupTypeRespDTO queryCoupType(CoupTypeReqDTO coupTypeReqDTO)
            throws BusinessException{
        
        if(coupTypeReqDTO==null){
            throw new BusinessException("coupon.450001");
        }
        if(coupTypeReqDTO.getId()==null){
            //优惠券类型id为空
            throw new BusinessException("coupon.450002");
        }
       return coupTypeSV.queryCoupTypeById(coupTypeReqDTO);
        
    }
    
    /**
     * 优惠券类型查询
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<CoupTypeRespDTO> queryCoupTypePage(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException{ 
        return coupTypeSV.queryCoupTypePage(coupTypeReqDTO);
    }
    /**
     * 优惠券类型查询
     * @param coupTypeReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupTypeRespDTO> queryCoupTypeList(CoupTypeReqDTO coupTypeReqDTO) throws BusinessException{ 
        return coupTypeSV.queryCoupTypeList(coupTypeReqDTO);
    }
}
