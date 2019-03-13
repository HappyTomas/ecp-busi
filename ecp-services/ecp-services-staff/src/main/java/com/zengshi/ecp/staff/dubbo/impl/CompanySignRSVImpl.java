package com.zengshi.ecp.staff.dubbo.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CompanySign;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanySignRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

public class CompanySignRSVImpl implements ICompanySignRSV {
    
    @Resource
    private ICompanySignSV companySignService;
    
    @Resource
    private ICompanyManageSV companyManageService;
    
    /**
     * t_cust_info客户信息表操作服务类
     */
    @Resource
    private ICustInfoSV custInfoService;
    
    /**
     * 
     * TODO 保存企业入驻申请信息. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV#saveComanySignInfo(com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO)
     */
    @Override
    public int save(CompanySignReqDTO pSignInfo, CustInfoReqDTO custinfo) throws BusinessException {
        
        if(pSignInfo == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数转换
        CompanySign signinfo = new CompanySign();
        ObjectCopyUtil.copyObjValue(pSignInfo, signinfo, null, false);
 
        boolean flag = checkName(signinfo, custinfo);

        if(flag)
        {
            //抛出企业名称重复异常
            throw new BusinessException(StaffConstants.companyInfo.COMPANY_NAME_REPEAT_ERROR, new String[]{signinfo.getCompanyName()});
        }
        if(companySignService.exist(signinfo))
        {
            companySignService.update(signinfo);
        }
        else {
            companySignService.save(signinfo);
        }
        return 0;
    }

    /**
     * 
     * TODO 根据用户ID找出该用户企业申请记录. 
     * @see com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV#findCompanySignInfoByStaffID(java.lang.Long)
     */
    @Override
    public CompanySignResDTO find(CustInfoReqDTO pCustinfo) throws BusinessException {
        
        
        if(pCustinfo == null || pCustinfo.getId() ==null  || pCustinfo.getCustType() == null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //1.根据当前用户ID，找出该用户企业申请记录
        Long pStaffID = pCustinfo.getId();
        String pCustType = pCustinfo.getCustType();
        CompanySign signinfo = companySignService.find(pStaffID);
        
        //2.是否存在该申请信息，存在着转换成dto，返回
        if(signinfo != null)
        {
            CompanySignResDTO dto = new CompanySignResDTO();
            ObjectCopyUtil.copyObjValue(signinfo, dto, null, false);
            return dto;
        }
        //3.不存在，则判断该用户是否为企业客户
        if(StaffConstants.custInfo.CUST_TYPE_C.equals(pCustType))
        {
            //4.取出企业信息
            CustInfo custinfo = custInfoService.findCustInfoById(pStaffID);   
            CompanyInfoResDTO cmDto= companyManageService.findCompanyInfoById(custinfo.getCompanyId());
            //如果企业下已经有店铺，则认为已进行了入驻申请或者后台已给企业分配了用户，所以不能再以该
            //公司进行入驻申请
            if (cmDto != null && cmDto.getShopNum() != null && cmDto.getShopNum() > 0L) {
            	LogUtil.info(CompanySignRSVImpl.class.getName(), "该企业已入驻，无法以该企业进行入驻申请。");
            	return null;
            }
            //5.拼接企业入驻申请信息dto返回
            return changeToSign(cmDto);
        }
        return null;
    }

    private boolean checkName(CompanySign companySign, CustInfoReqDTO custinfo) throws BusinessException {
        

        
        boolean flag = false;
        try {
            flag = companySignService.checkName(companySign);
        } catch (Exception e) {
            // TODO: handle exception
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{companySign.toString()});
        }
        return flag;
    }
    private CompanySignResDTO changeToSign(CompanyInfoResDTO pDto)
    {
        if(pDto == null)
        {
            return null;
        }
        CompanySignResDTO sResult = new CompanySignResDTO();
        
        sResult.setCompanyId(pDto.getId());
        //拷贝相同属性值
        ObjectCopyUtil.copyObjValue(pDto, sResult, null, false);
        
        return sResult;
        
    }
}

