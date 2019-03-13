package com.zengshi.ecp.staff.service.busi.custinfo.interfaces;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustEmailLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2016年2月22日上午11:01:50  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */
public interface ICustThirdSV {

    /**
     * 
     * saveCustThirdCode:(会员绑定第三方平台账号). <br/> 
     * 
     * @author wangbh
     * @param custThirdCodeReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void saveCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO)throws BusinessException;
    
    
    /**
     * 
     * queryCustThirdCode:(查询会员第三方绑定账号). <br/> 
     * 
     * @author wangbh
     * @param custThirdCodeReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustThirdCodeResDTO queryCustThirdCode(CustThirdCodeReqDTO custThirdCodeReqDTO) throws BusinessException;
    
    
}

