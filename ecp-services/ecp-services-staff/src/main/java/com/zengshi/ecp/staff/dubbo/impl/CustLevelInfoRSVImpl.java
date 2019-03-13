package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustLevelInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustGrowInfoSV;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员信息服务接口实现类<br>
 * Date:2015-8-24下午7:25:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CustLevelInfoRSVImpl implements ICustLevelInfoRSV{

    @Resource
    private ICustVipSV custVipSV;
    
    @Resource
    private ICustGrowInfoSV custGrowInfoSV;
    
    private static Logger logger = LoggerFactory.getLogger(CustLevelInfoRSVImpl.class);
    
    private static final String MODULE = CustLevelInfoRSVImpl.class.getName();

    @Override
    public CustLevelInfoResDTO queryValueGap(CustInfoReqDTO info) throws BusinessException {
        
        if(StringUtil.isBlank(info.getCustLevelCode())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"custLevelCode"});
        }
        if(info.getCustGrowValue()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"custGrowValue"});
        }
        return custVipSV.queryValueGap(info);
    }

    @Override
    public PageResponseDTO<CustGrowInfoResDTO> queryCustGrowInfo(
            CustGrowInfoReqDTO custGrowInfoReqDTO) throws BusinessException {
        PageResponseDTO<CustGrowInfoResDTO> page = new PageResponseDTO<CustGrowInfoResDTO>();
        try {
            page =   custGrowInfoSV.queryCustGrowInfo(custGrowInfoReqDTO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];查询异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return page;
    }

    @Override
    public CustLevelInfoResDTO queryCustLevelInfoByCardNum(CustLevelInfoReqDTO dto) throws BusinessException {
        
        return custVipSV.queryCustLevelInfo(dto);
    }
    
   
}

