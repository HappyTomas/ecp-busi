package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreCaclSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 积分计算处理RSV实现类<br>
 * Date:2016年5月18日下午9:06:02  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public class ScoreCaclRSVImpl implements IScoreCaclRSV{

    @Resource
    private IScoreCaclSV scoreCaclSV;
    
    @Override
    public ScoreResultResDTO updateScore(String pSourceType, CustInfoReqDTO pCustInfo,
            PayQuartzInfoRequest pOrderInfo) throws BusinessException {
        return scoreCaclSV.updateScore(pSourceType, pCustInfo, pOrderInfo);
    }

    @Override
    public ScoreResultResDTO saveDoCaclScore(ScoreResultResDTO scoreResultInfo,
            CustInfoReqDTO pCustInfo, ScoreInfoReqDTO scoreInfo, ProductInfoReqDto pProductInfo)
                    throws BusinessException {
        return scoreCaclSV.saveDoCaclScore(scoreResultInfo, pCustInfo, scoreInfo, pProductInfo);
    }

}

