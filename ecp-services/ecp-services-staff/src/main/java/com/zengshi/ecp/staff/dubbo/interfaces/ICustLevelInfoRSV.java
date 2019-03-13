package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层 会员信息服务接口<br>
 * Date:2015-8-24下午7:23:56  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public interface ICustLevelInfoRSV {

    public CustLevelInfoResDTO queryCustLevelInfoByCardNum(CustLevelInfoReqDTO dto) throws BusinessException;

    /**
     * 
     * queryValueGap:(查询当前等级与下一等级差距值). <br/> 
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustLevelInfoResDTO queryValueGap(CustInfoReqDTO info) throws BusinessException;
    
    /**
     * 
     * queryCustGrowInfo:(查询会员成长值明细). <br/> 
     * 
     * @author wangbh
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<CustGrowInfoResDTO> queryCustGrowInfo(CustGrowInfoReqDTO custGrowInfoReqDTO) throws BusinessException;
}

