package com.zengshi.ecp.staff.service.busi.custinfo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustGrowInfoResDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员资料信息服务接口<br>
 * Date:2015-8-24下午5:50:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICustGrowInfoSV extends IGeneralSQLSV {

    
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

