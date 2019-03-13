
package com.zengshi.ecp.staff.dubbo.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyCheckRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.facade.interfaces.eventual.ICompanyMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyCheckSV;
import com.zengshi.ecp.sys.dubbo.dto.McParamsWithOtherTypesDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMcSyncSendRSV;
import com.zengshi.paas.utils.ThreadId;


public class CompanyCheckRSVImpl implements ICompanyCheckRSV{
    
    @Resource(name="companyCheckSV")
    private ICompanyCheckSV  companyCheckSV;
    
    @Resource(name="companyTxMainSV")
    private ICompanyMainSV companyMainSV;
    
    @Resource
    private IMcSyncSendRSV mcSyncSendRSV;

    
    private static Logger logger = LoggerFactory.getLogger(CompanyCheckRSVImpl.class);


    public PageResponseDTO<CompanySignResDTO> queryCompanySignList(CompanySignReqDTO info)
            throws BusinessException {
        PageResponseDTO<CompanySignResDTO> page = new PageResponseDTO<CompanySignResDTO>();
        try {
            page =  companyCheckSV.queryCompanySignList(info);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];查询Companysign list异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"T_COMPANY_SIGN"});
        }
     
        return page;
    }



    public CompanySignResDTO queryCompanySign(CompanySignReqDTO info) throws BusinessException {
        
        CompanySignResDTO dto = new CompanySignResDTO();
        try {
            dto =  companyCheckSV.queryCompanySign(info);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];查询Companysign only异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR,new String[]{"T_COMPANY_SIGN"});
        }
        return dto;
    }



    public void updateCheckCompany(CompanySignReqDTO info) throws BusinessException {
     
    	companyMainSV.companyCheckBuildStock(info);
            
        try {
        	/*发送手机短信给用户*/
            McParamsWithOtherTypesDTO types = new McParamsWithOtherTypesDTO();
            types.setMsgCode(StaffConstants.Msg.MC_PHONE_COM_CHECK);
            types.phoneNo(info.getLinkPhoneMsg());//手机号码
            Map<String,String> params = new HashMap<String,String>();
            params.put("staffCode", info.getStaffCode());
            params.put("resultMsg", "通过");
            types.setMsgParams(params);
            mcSyncSendRSV.sendMsgBySpecial(types);//此方法异常被吃了，所以不会抛异常，这里要注意
		} catch (Exception e) {
			Log.error("企业入驻申请审核短信发送异常：" + e.getMessage());
		}

    }



    public void updateNoCheckCompany(CompanySignReqDTO info) throws BusinessException {

        try {
            companyCheckSV.updateNoCheckCompany(info);
        } catch (Exception e) {
            logger.error("ThreadId:["+ThreadId.getThreadId()+"];update Companysign only异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,new String[]{"T_COMPANY_SIGN"});
        }
        try {
        	/*发送手机短信给用户*/
            McParamsWithOtherTypesDTO types = new McParamsWithOtherTypesDTO();
            types.setMsgCode(StaffConstants.Msg.MC_PHONE_COM_CHECK);
            types.phoneNo(info.getLinkPhoneMsg());//手机号码
            Map<String,String> params = new HashMap<String,String>();
            params.put("staffCode", info.getStaffCode());
            params.put("resultMsg", "不通过。原因：" + info.getCheckRemark());
            types.setMsgParams(params);
            mcSyncSendRSV.sendMsgBySpecial(types);//此方法异常被吃了，所以不会抛异常，这里要注意
		} catch (Exception e) {
			Log.error("企业入驻申请审核短信发送异常：" + e.getMessage());
		}
    }

	@Override
	public Long getCheckCompanyCount() throws BusinessException {
		return companyCheckSV.getCheckCompanyCount();
	}

   

}
