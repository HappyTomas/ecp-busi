/**
 * 
 */
package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyNameIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyStaffIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustAuthstrMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDX;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDX;
import com.zengshi.ecp.staff.dao.model.CustAuthstr;
import com.zengshi.ecp.staff.dao.model.CustAuthstrCriteria;
import com.zengshi.ecp.staff.dao.model.CustAuthstrCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustCheckSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.cache.interfaces.ICompanyCacheSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月5日下午8:49:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version
 * @since JDK 1.7
 */
public class CustCheckSVImpl implements ICustCheckSV {

    private static final String MODULE = CustCheckSVImpl.class.getName();
    @Resource
    private IAuthRelManageSV authRelManageSV;
    
    @Resource
    private CustAuthstrMapper authstrMapper;

    @Resource
    private CustInfoMapper custInfoMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Resource
    private CompanyStaffIDXMapper companyStaffIDXMapper;

    @Resource
    private CompanyNameIDXMapper companyNameIDXMapper;
    
    @Resource
    private IRegisterSV registerSV;
    
    @Resource
    private ICompanyCacheSV companyCacheSV;
    
    @Resource(name = "seq_company_info_id")
    private Sequence seq_company; 
    
    @Override
    public PageResponseDTO<CustAuthstrResDTO> queryCustCheckList(CustAuthstrReqDTO info)
            throws BusinessException {
        PageResponseDTO<CustAuthstrResDTO> pageinfo = new PageResponseDTO<CustAuthstrResDTO>();
        pageinfo.setPageNo(info.getPageNo());
        pageinfo.setPageSize(info.getPageSize());
        int startNum = pageinfo.getStartRowIndex();

        CustAuthstrCriteria criteria = new CustAuthstrCriteria();
        Criteria sql = criteria.createCriteria();
        if (!StringUtil.isBlank(info.getStaffCode())) {
            sql.andStaffCodeLike("%"+info.getStaffCode()+"%");
        }
        if (!StringUtil.isBlank(info.getSerialNumber())) {
            sql.andSerialNumberLike("%"+info.getSerialNumber()+"%");
        }
        if (!StringUtil.isBlank(info.getCheckStatus())) {
            sql.andCheckStatusEqualTo(info.getCheckStatus());
        }
        criteria.setOrderByClause("create_time");
        criteria.setLimitClauseStart(startNum);
        criteria.setLimitClauseCount(info.getPageSize());
        try {
            List<CustAuthstr> list = authstrMapper.selectByExample(criteria);
            long count =  authstrMapper.countByExample(criteria);
            List<CustAuthstrResDTO> list1 = new ArrayList<>();
            for (CustAuthstr custAuthstr : list) {
                CustAuthstrResDTO dto = new CustAuthstrResDTO();
                ObjectCopyUtil.copyObjValue(custAuthstr, dto, null, false);
                if(dto.getCheckStaff()!=null){
                String staffcode =  registerSV.queryAuthStaffName(dto.getCheckStaff());
                dto.setCheckStaffName(staffcode);
                }
                String area =  StaffTools.createArea(dto.getCountryCode(), dto.getProvinceCode(), dto.getCityCode(), dto.getCountyCode());
                dto.setAreaCode(area);
                String trade =  BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_TRADE, dto.getTrade());
                dto.setTrade(trade);
                String employee = BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_EMPLOYEE_NUM, dto.getEmployeeNum());
                dto.setEmployeeNum(employee);
                list1.add(dto);
            }
            pageinfo.setCount(count);
            pageinfo.setResult(list1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageinfo;
    }

    @Override
    public CustAuthstrResDTO updateCustToCompanyCust(CustAuthstrReqDTO info) throws BusinessException {

        CustAuthstrResDTO dto;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        CustAuthstrResDTO req = new CustAuthstrResDTO();
        try {
            // 查询待审核表
            dto = queryCustAuthser(info);
            // 插入企业表
            req =  saveCompanyInfo(dto);

            // 插入企业与用户索引表
            CompanyStaffIDX staffidx = new CompanyStaffIDX();
            staffidx.setCompanyId(dto.getId());
            staffidx.setStaffId(dto.getStaffId());
            staffidx.setApplyStatus(StaffConstants.custInfo.CHECK_STATUS_PASS);
            companyStaffIDXMapper.insert(staffidx);
            LogUtil.info("updateCustToCompanyCust", "插入企业与用户索引表");

            // 新增企业名称表
            CompanyNameIDX companyNameidx = new CompanyNameIDX();
            companyNameidx.setCompanyId(dto.getId());
            companyNameidx.setCompanyName(dto.getCompanyName());
            companyNameidx.setSerialNumbr(dto.getSerialNumber());
            companyNameidx.setStaffId(dto.getStaffId());
            companyNameIDXMapper.insert(companyNameidx);

            // 审核通过，修改用户表
            CustInfo modelInfo = new CustInfo();
            modelInfo.setId(dto.getStaffId());
            modelInfo.setCheckStaff(info.getCheckStaff());
            modelInfo.setCheckDate(time);
            modelInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_C);
            modelInfo.setCompanyId(dto.getId());
            custInfoMapper.updateByPrimaryKeySelective(modelInfo);
            //企业会员分组
            AuthStaff2Group group_e = new AuthStaff2Group();
            group_e.setStaffId(dto.getStaffId());
            group_e.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);
            group_e.setCreateStaff(dto.getStaffId());
            group_e.setCreateTime(DateUtil.getSysDate());
            group_e.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
            group_e.setUpdateStaff(dto.getStaffId());
            group_e.setUpdateTime(DateUtil.getSysDate());
            authRelManageSV.saveStaffGroupRel(group_e);
            
            //删除待审核通过数据
            CustAuthstrReqDTO authstr = new CustAuthstrReqDTO();
            authstr.setId(dto.getId());
            removeCustAuthstr(authstr);

        } catch (Exception e) {
            LogUtil.error(MODULE, "审核异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
           return req;
    }

    @Override
    public CustAuthstrResDTO queryCustAuthser(CustAuthstrReqDTO info) throws BusinessException {

        CustAuthstrResDTO dto = new CustAuthstrResDTO();
        CustAuthstrCriteria criteria = new CustAuthstrCriteria();
        Criteria sql = criteria.createCriteria();
        sql.andStaffIdEqualTo(info.getStaffId());
        List<CustAuthstr> list = authstrMapper.selectByExample(criteria);
        try {
            if (!CollectionUtils.isEmpty(list)) {
                for (CustAuthstr custAuthstr : list) {
                    ObjectCopyUtil.copyObjValue(custAuthstr, dto, null, false);
                }
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询会员审核异常",e);
        }
        return dto;

    }

    // 待申请表数据转移至企业表
    public CustAuthstrResDTO saveCompanyInfo(CustAuthstrResDTO dto) throws BusinessException {
        
        CompanyNameIDXCriteria cname = new CompanyNameIDXCriteria();
        cname.createCriteria().andCompanyNameEqualTo(dto.getCompanyName());
        List<CompanyNameIDX> list =  companyNameIDXMapper.selectByExample(cname);
        if(null!=list&&list.size()>0){
            throw new BusinessException(StaffConstants.companyInfo.COMPANY_NAME_REPEAT_ERROR,new String[]{dto.getCompanyName()});
        }
        CustAuthstrResDTO res = new CustAuthstrResDTO();
        CompanyInfo info = new CompanyInfo();
        info.setId(dto.getId());
        info.setCompanyName(dto.getCompanyName());
        info.setCompanyAdress(dto.getCompanyAdress());
        info.setTrade(dto.getTrade());
        info.setCompanyType(StaffConstants.companyInfo.COMPANY_TYPE_G);
        info.setEmployeeNum(dto.getEmployeeNum());
        info.setCountryCode(dto.getCountryCode());
        info.setProvinceCode(dto.getProvinceCode());
        info.setCityCode(dto.getCityCode());
        info.setCountyCode(dto.getCountyCode());
        info.setCompanyEmail(dto.getCompanyEmail());
        info.setStatus(StaffConstants.companyInfo.COMPANY_STATUS_VALID);
        info.setLinkPersonMsg(dto.getLinkPersonMsg());
        info.setLinkPhoneMsg(dto.getLinkPhoneMsg());
        info.setLinkPsnCard(dto.getLinkPsnCard());
        info.setLinkTelephone(dto.getLinkTelephone());
        info.setCompanyWeixin(dto.getCompanyWeixin());
        info.setCompanyQq(dto.getCompanyQq());
        info.setIsEnter(StaffConstants.companyInfo.COMPANY_TYPE_G);
        info.setSource(StaffConstants.companyInfo.SOURCE_FROM_CUST);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        info.setCreateStaff(dto.getCreateStaff());
        info.setCreateTime(time);
        try {
           int i = companyInfoMapper.insert(info);
           if(i>0){
               companyCacheSV.updateCacheCompany(dto.getId());
               res.setId(info.getId());
               res.setCompanyName(info.getCompanyName());
               res.setCreateStaff(info.getCreateStaff());
           }
          
        } catch (Exception e) {
            LogUtil.error(MODULE, "新增企业信息表异常",e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR,
                    new String[] { "T_COMPANY_INFO" });
        }
        return res;
    }
    
    //审核通过后，删除待申请表数据
    public void removeCustAuthstr(CustAuthstrReqDTO info){
        try {
            authstrMapper.deleteByPrimaryKey(info.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR,
                    new String[] { "T_CUST_AUTHSTER" });
        }
    }

    @Override
    public void updateNoCustToCompanyCust(CustAuthstrReqDTO info) throws BusinessException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        CustAuthstr custAuthstr = new CustAuthstr();
        custAuthstr.setId(info.getId());
        custAuthstr.setCheckStatus(StaffConstants.custInfo.CHECK_STATUS_NOPASS);
        custAuthstr.setCheckStaff(info.getCheckStaff());
        custAuthstr.setCheckRemark(info.getCheckRemark());
        custAuthstr.setCheckDate(time);
        authstrMapper.updateByPrimaryKeySelective(custAuthstr);

    }

    @Override
    public int saveCustAuthstr(CustAuthstrReqDTO info) throws BusinessException {
        CompanyInfoCriteria c = new CompanyInfoCriteria();
        c.createCriteria().andCompanyNameEqualTo(info.getCompanyName());
        List<CompanyInfo> list = companyInfoMapper.selectByExample(c);
        if(list.size()>0){
            throw new BusinessException("对不起，该企业已经注册");
        }
        
        CustAuthstr custAuthstr =new CustAuthstr();
        ObjectCopyUtil.copyObjValue(info, custAuthstr, null, false);
        custAuthstr.setCheckStatus("1");
        custAuthstr.setStatus("1");
        custAuthstr.setId(seq_company.nextValue());
        return authstrMapper.insertSelective(custAuthstr);
    }

	@Override
	public int updateCustAuthstr(CustAuthstrReqDTO info)
			throws BusinessException {
		CustAuthstr custAuthstr = new CustAuthstr();
        custAuthstr.setId(info.getId());
        custAuthstr.setStaffId(info.getStaffId());
        custAuthstr.setCheckStatus(info.getCheckStatus());
        ObjectCopyUtil.copyObjValue(info, custAuthstr, null, true);
        return authstrMapper.updateByPrimaryKeySelective(custAuthstr);
	}

}
