package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdBackApply;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyInfoResp;
import com.zengshi.ecp.order.dubbo.dto.RBackApplyReq;
import com.zengshi.ecp.order.dubbo.dto.RBackGdsResp;
import com.zengshi.ecp.order.dubbo.dto.RBackReviewReq;
import com.zengshi.ecp.server.front.exception.BusinessException;

import java.util.List;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月12日下午8:20:29  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdCalculateSV {
    /** 
     * calCulateShare:计算分摊. <br/> 
     * @author linwei
     * @param info
     * @return 
     * @since JDK 1.6 
     */ 
    public RBackApplyInfoResp calCulateShare(RBackReviewReq info) throws BusinessException;
    
    /**
     * 审核时计算分摊比例
     * @param info
     * @return
     * @throws BusinessException
     */
	public long calCulateScale(RBackReviewReq info,boolean lastFlag) throws BusinessException;
    /**
     * 申请时计算分摊
     * @param
     * @return
     * @throws BusinessException
     */
    public RBackApplyInfoResp calCulateShareApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply) throws BusinessException;
    /**
     * 申请时计算分摊比例
     * @param
     * @return
     * @throws BusinessException
     */
    public long calCulateScaleApply(RBackApplyReq rBackApplyReq, OrdBackApply ordBackApply) throws BusinessException;
    /**
     * 申请时判断是否最后一笔退货退款
     * @param rBackApplyReq
     * @return
     * @throws BusinessException
     */
    public boolean isLastBackBatch(RBackApplyReq rBackApplyReq) throws BusinessException;

    
    /**
     * 申请时判断是否子订单最后一笔退货退款
     * @param rBackApplyReq
     * @return
     * @throws BusinessException
     */
    public boolean isSubLastBackBatch(RBackApplyReq rBackApplyReq)throws BusinessException;

}



