package com.zengshi.ecp.staff.service.busi.company.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyNameIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanySignMapper;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDX;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CompanySign;
import com.zengshi.ecp.staff.dao.model.CompanySignCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.login.interfaces.IAuthLoginSV;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年8月31日下午5:21:31  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 企业入驻服务类，处理企业入驻信息表
 */
public class CompanySignSVImpl implements ICompanySignSV {
    /**
     * 企业ID序列值
     */
    @Resource(name = "seq_company_info_id")
    private Sequence seq_company_id; 
    
    /**
     * 企业入驻申请信息表ID值
     */
    @Resource(name = "seq_company_sign_id")
    private Sequence seq_company_sign_id; 
    
    /**
     * 企业入驻申请信息表mapper操作
     */
    @Resource
    private CompanySignMapper companySignMapper;
    
    /**
     * 企业名称与ID索引表mapper操作
     */
    @Resource
    private CompanyNameIDXMapper companyNameIDXMapper;
    
    @Resource
    private IAuthLoginSV authLoginSV;
    
    /**
     * 
     * TODO 根据企业入驻申请人，查询企业入驻申请信息（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV#findCompanySignByStaffID(java.lang.Long)
     */
    @Override
    public CompanySign find(Long pStaffID) throws BusinessException {
        
        CompanySignCriteria criteria = new CompanySignCriteria();
        criteria.createCriteria().andCreateStaffEqualTo(pStaffID);
        List<CompanySign> result = null;
        try {
            result = companySignMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return CollectionUtils.isEmpty(result)?null:result.get(0);
    }

    /**
     * 
     * TODO 保存企业入驻申请信息（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV#saveCompanySignInfo(com.zengshi.ecp.staff.dao.model.CompanySign)
     */
    @Override
    public int save(CompanySign pSignInfo) throws BusinessException {
        //1.生成主键ID
        pSignInfo.setId(seq_company_sign_id.nextValue());
        
        //获取客户资料
        Long sStaffID = authLoginSV.findIDByStaffCode(pSignInfo.getStaffCode());
        
        //2.新增企业信息需要填补企业ID
        if(pSignInfo.getCompanyId() == null)
        {
            CustInfo custInfo = authLoginSV.getCustInfoByID(sStaffID);
            if(StaffConstants.custInfo.CUST_TYPE_C.equals(custInfo.getCustType()))
            {
                //如果该客户是企业客户，填补企业ID
                pSignInfo.setCompanyId(custInfo.getCompanyId());
            }
            else
            {
                pSignInfo.setCompanyId(seq_company_id.nextValue());
            }
        }
        
        //3.填补创建人信息

        pSignInfo.setCreateStaff(sStaffID);
        pSignInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pSignInfo.setUpdateStaff(sStaffID);
        pSignInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        try {
            companySignMapper.insertSelective(pSignInfo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        
        return 0;
    }

    /**
     * 
     * TODO 更新企业入驻申请信息（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV#updateCompanySignInfo(com.zengshi.ecp.staff.dao.model.CompanySign)
     */
    @Override
    public void update(CompanySign pSignInfo) throws BusinessException {
        //登记更新信息
//        pSignInfo.setUpdateStaff();
        pSignInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            companySignMapper.updateByPrimaryKeySelective(pSignInfo);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 
     * TODO 判断公司名称是否重复：false 不重复； true 重复. 
     * @see com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV#checkCompanyNameRepeat(java.lang.String)
     */
    @Override
    public boolean checkName(CompanySign companySign) throws BusinessException {
        CompanyNameIDXCriteria criteria = new CompanyNameIDXCriteria();
        Criteria cc = criteria.createCriteria();
        if(companySign.getCompanyId() != null)
        {
            cc.andCompanyIdNotEqualTo(companySign.getCompanyId());
        }
        if(companySign.getCompanyName() != null)
        {
            cc.andCompanyNameEqualTo(companySign.getCompanyName());
        }
        List<CompanyNameIDX> result = null;
        try {
            result = companyNameIDXMapper.selectByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return CollectionUtils.isEmpty(result)?false:true;
    }

    /**
     * 
     * TODO 根据staffCode判断企业申请记录是否已经存在. 
     * @see com.zengshi.ecp.staff.service.busi.company.interfaces.ICompanySignSV#exist(com.zengshi.ecp.staff.dao.model.CompanySign)
     */
    @Override
    public boolean exist(CompanySign pSignInfo) throws BusinessException {
        CompanySignCriteria criteria = new CompanySignCriteria();
        criteria.createCriteria().andStaffCodeEqualTo(pSignInfo.getStaffCode());
        Long result = null;
        try {
            result = companySignMapper.countByExample(criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw e;
        }
        return result>0?true:false;
    }

}

