package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;

public interface ICompanySignRSV {
    /**
     * 
     * saveComanySignInfo:( 企业入驻信息服务接口，保存企业入驻申请信息). <br/> 
     * dubbo服务
     * 
     * @author PJieWin 
     * @param pSignInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int save(CompanySignReqDTO pSignInfo, CustInfoReqDTO custinfo)throws BusinessException;
    /**
     * 
     * findCompanySignInfoByStaffID:(根据用户ID找出该用户申请企业记录). <br/> 
     * dubbo服务
     * 
     * @author PJieWin 
     * @param pStaffID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CompanySignResDTO find(CustInfoReqDTO custinfo) throws BusinessException;
    
}

