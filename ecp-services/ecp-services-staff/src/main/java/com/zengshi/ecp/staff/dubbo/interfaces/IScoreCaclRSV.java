package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分计算处理类<br>
 * Date:2016年5月18日下午9:04:13  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IScoreCaclRSV {
    /**
     * 
     * caclScore:(积分来源积分服务接口). <br/> 
     * (当积分失败或者没有对应积分计算规则时，返回null).<br/> 
     * 
     * @author PJieWin 
     * @param pSourceType
     * @param pCustInfo
     * @param pOrderInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreResultResDTO updateScore(String pSourceType, CustInfoReqDTO pCustInfo, PayQuartzInfoRequest pOrderInfo) throws BusinessException;

    /**
     * 
     * saveDoCaclScore:(获取计算规则配置 并计算积分返回结果). <br/> 
     * 
     * @author huangxl5 
     * @param scoreResultInfo
     * @param pCustInfo
     * @param scoreInfo
     * @param pProductInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public ScoreResultResDTO saveDoCaclScore(ScoreResultResDTO  scoreResultInfo, CustInfoReqDTO pCustInfo,ScoreInfoReqDTO scoreInfo, ProductInfoReqDto pProductInfo) throws BusinessException;
}

