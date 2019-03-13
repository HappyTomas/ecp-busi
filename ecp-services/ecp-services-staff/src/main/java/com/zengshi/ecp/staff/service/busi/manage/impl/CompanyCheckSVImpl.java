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
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanySignMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyStaffIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDX;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDX;
import com.zengshi.ecp.staff.dao.model.CompanySign;
import com.zengshi.ecp.staff.dao.model.CompanySignCriteria;
import com.zengshi.ecp.staff.dao.model.CompanySignCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CompanySignKey;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDX;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.CompanyCheckResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanySignResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyCheckSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

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
public class CompanyCheckSVImpl implements ICompanyCheckSV {
    
    @Resource
    private CompanySignMapper companySignMapper;
    
    @Resource
    private CompanyInfoMapper companyInfoMapper;
    
    @Resource
    private CompanyShopIDXMapper companyShopIDXMapper;
    
    @Resource
    private CompanyStaffIDXMapper companyStaffIDXMapper;
    
    @Resource
    private CustInfoMapper custInfoMapper;
    
    @Resource
    private CompanyNameIDXMapper companyNameIDXMapper;
    
    @Resource
    private IRegisterSV registerSV;
    
    @Resource
    private IAuthRelManageSV authRelManageSV; //用户权限关系服务
    
    @Resource
    private IShopMgrSV shopMgrService;

    @Override
    public PageResponseDTO<CompanySignResDTO> queryCompanySignList(CompanySignReqDTO info)
            throws BusinessException {
        PageResponseDTO<CompanySignResDTO>  page = new PageResponseDTO<CompanySignResDTO>();
        page.setPageNo(info.getPageNo());
        page.setPageSize(info.getPageSize());
        int startNum = page.getStartRowIndex();
        CompanySignCriteria criteria = new CompanySignCriteria();
        Criteria sql = criteria.createCriteria();
        if(!StringUtil.isBlank(info.getStaffCode())){
            sql.andStaffCodeLike("%"+info.getStaffCode()+"%");
        }
        if(!StringUtil.isBlank(info.getCheckStatus())){
            sql.andCheckStatusEqualTo(info.getCheckStatus());
        }
        
        criteria.setOrderByClause("CREATE_TIME");
        criteria.setLimitClauseStart(startNum);
        criteria.setLimitClauseCount(info.getPageSize());
        try {
            List<CompanySign> list =   companySignMapper.selectByExample(criteria);
            long count =  companySignMapper.countByExample(criteria);
            List<CompanySignResDTO> list1 = new ArrayList<CompanySignResDTO>();
            for (CompanySign companySign : list) {
                CompanySignResDTO dto = new CompanySignResDTO();
                ObjectCopyUtil.copyObjValue(companySign, dto, null, false);
                String areaCode = StaffTools.createArea(dto.getCountryCode(), dto.getProvinceCode(), dto.getCityCode(), dto.getCountyCode());
                dto.setAreaCode(areaCode);
                String staffName = registerSV.queryAuthStaffName(dto.getCheckStaff());
                String trade =  BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_TRADE, dto.getTrade());
                dto.setTrade(trade);
                dto.setCheckStaffName(staffName);
                list1.add(dto);
            }
            
            page.setResult(list1);
            page.setCount(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

    @Override
    public CompanySignResDTO queryCompanySign(CompanySignReqDTO info) throws BusinessException { 
        
        CompanySignResDTO dto = new CompanySignResDTO();
        CompanySignCriteria criteria = new CompanySignCriteria();
        Criteria sql = criteria.createCriteria();
        sql.andCompanyIdEqualTo(info.getCompanyId());
        List<CompanySign> list = companySignMapper.selectByExample(criteria);
        if(list!=null&&list.size()>0){
            for (CompanySign companySign : list) {
                StaffTools.coverBean2Bean(companySign, dto);
            }
        }
        
        return dto;
    }

    @Override
    public CompanyCheckResDTO updateCheckCompany(CompanySignReqDTO info) throws BusinessException {
        CompanyCheckResDTO checkRes = new CompanyCheckResDTO();
        //查询待审核企业信息
        CompanySignResDTO dto = queryCompanySign(info);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        CompanyInfo companyInfo = new CompanyInfo();
        //查询企业是否已经存在
        companyInfo = companyInfoMapper.selectByPrimaryKey(dto.getCompanyId());
        if(!StringUtil.isEmpty(companyInfo)){
            StaffTools.coverBean2Bean(dto, companyInfo);
            companyInfo.setIsEnter(StaffConstants.companyInfo.COMPANY_TYPE_P);
            companyInfo.setId(dto.getCompanyId());
            companyInfo.setUpdateStaff(info.getUpdateStaff());
            companyInfo.setUpdateTime(time);
            companyInfo.setStatus("1");
            companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
            
        }else{
            companyInfo = new CompanyInfo();
            ObjectCopyUtil.copyObjValue(dto, companyInfo, null, false);
            companyInfo.setIsEnter(StaffConstants.companyInfo.COMPANY_TYPE_P);
            companyInfo.setId(dto.getCompanyId());
            companyInfo.setCreateStaff(info.getCreateStaff());
            companyInfo.setUpdateStaff(info.getUpdateStaff());
            companyInfo.setCreateTime(time);
            companyInfo.setUpdateTime(time);
            companyInfo.setStatus("1");
            companyInfoMapper.insertSelective(companyInfo);
            
            //新增企业名称索引
            CompanyNameIDX companyNameIDX = new CompanyNameIDX();
            companyNameIDX.setCompanyId(dto.getCompanyId());
            companyNameIDX.setCompanyName(dto.getCompanyName());
            companyNameIDX.setStaffId(info.getCompanyCreateStaff());
            companyNameIDX.setSerialNumbr(info.getSerialNumber());
            companyNameIDXMapper.insert(companyNameIDX);
            //返回给最终一致事物使用，代表新增的企业
            checkRes.setIfNewCompany(true);
            checkRes.setCompanyName(dto.getCompanyName());
        }
        
        //店铺新增
        ShopInfo shopinfo = new ShopInfo(); 
        ObjectCopyUtil.copyObjValue(dto, shopinfo, null, false);
        shopinfo.setCompanyId(dto.getCompanyId());
        shopinfo.setShopStatus(StaffConstants.shopInfo.SHOP_STATUS_NOMAL);
        shopinfo.setCreateStaff(info.getCreateStaff());
        shopinfo.setCreateTime(time);
        shopinfo.setUpdateStaff(info.getUpdateStaff());
        shopinfo.setUpdateTime(time);
        shopinfo.setOnlineSupported("1");
        long shopid = shopMgrService.insertShopInfo(shopinfo);
        dto.setShopId(shopid);
        //新增企业下属店铺索引表
        CompanyShopIDX companyShopIDX = new CompanyShopIDX();
        companyShopIDX.setCompanyId(dto.getCompanyId());
        companyShopIDX.setShopId(shopid);
        companyShopIDX.setApplyStatus(StaffConstants.companyInfo.COMPANY_CHECK_STATUS_PASS);
        companyShopIDXMapper.insertSelective(companyShopIDX);
        checkRes.setShopId(shopid);
        checkRes.setShopName(shopinfo.getShopName());
        //新增企业下属会员
        CompanyStaffIDXCriteria staffcriteria = new CompanyStaffIDXCriteria();
      /*  staffcriteria.createCriteria().andStaffIdEqualTo(dto.getCompanyCreateStaff()).andCompanyIdEqualTo(dto.getCompanyId());*/
        com.zengshi.ecp.staff.dao.model.CompanyStaffIDXCriteria.Criteria spl =  staffcriteria.createCriteria();
        spl.andStaffIdEqualTo(dto.getCompanyCreateStaff());
        spl.andCompanyIdEqualTo(dto.getCompanyId());
        List<CompanyStaffIDX> stafflist = companyStaffIDXMapper.selectByExample(staffcriteria);
        if(CollectionUtils.isEmpty(stafflist)){
            // 审核通过且用户不是企业会员，修改用户表为企业会员
            CustInfo modelInfo = new CustInfo();
            modelInfo.setId(dto.getCompanyCreateStaff());
            modelInfo.setCheckStaff(info.getCheckStaff());
            modelInfo.setCheckDate(time);
            modelInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
            modelInfo.setCompanyId(dto.getCompanyId());
            modelInfo.setShopId(dto.getShopId());
            modelInfo.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
            modelInfo.setUpdateStaff(info.getUpdateStaff());
            modelInfo.setUpdateTime(time);
            custInfoMapper.updateByPrimaryKeySelective(modelInfo);
            //新增索引表
            CompanyStaffIDX companyStaffIDX = new CompanyStaffIDX();
            companyStaffIDX.setCompanyId(dto.getCompanyId());
            companyStaffIDX.setStaffId(dto.getCompanyCreateStaff());
            companyStaffIDX.setApplyStatus(StaffConstants.custInfo.CHECK_STATUS_PASS);
            companyStaffIDXMapper.insert(companyStaffIDX);
            
            //更新用户的权限默认分组：删除普通会员分组、插入企业分组、卖家分组
            //保存企业分组时，会自动删除普通会员分组
            AuthStaff2Group group_e = new AuthStaff2Group();
            group_e.setStaffId(dto.getCompanyCreateStaff());
            group_e.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);
            group_e.setCreateStaff(dto.getCompanyCreateStaff());
            group_e.setCreateTime(DateUtil.getSysDate());
            group_e.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
            group_e.setUpdateStaff(dto.getCompanyCreateStaff());
            group_e.setUpdateTime(DateUtil.getSysDate());
            authRelManageSV.saveStaffGroupRel(group_e);
            
            AuthStaff2Group group_s = new AuthStaff2Group();
            group_s.setStaffId(dto.getCompanyCreateStaff());
            group_s.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);
            group_s.setCreateStaff(dto.getCompanyCreateStaff());
            group_s.setCreateTime(DateUtil.getSysDate());
            group_s.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
            group_s.setUpdateStaff(dto.getCompanyCreateStaff());
            group_s.setUpdateTime(DateUtil.getSysDate());
            authRelManageSV.saveStaffGroupRel(group_s);
        }else{
        	 // 审核通过且用户是企业会员
            CustInfo modelInfo = new CustInfo();
            modelInfo.setId(dto.getCompanyCreateStaff());
            modelInfo.setCheckStaff(info.getCheckStaff());
            modelInfo.setCheckDate(time);
            modelInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
            modelInfo.setCompanyId(dto.getCompanyId());
            modelInfo.setShopId(dto.getShopId());
            modelInfo.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
            modelInfo.setUpdateStaff(info.getUpdateStaff());
            modelInfo.setUpdateTime(time);
            custInfoMapper.updateByPrimaryKeySelective(modelInfo);
            
            //更新用户的权限默认分组：删除普通会员分组、插入企业分组、卖家分组
            //保存企业分组时，会自动删除普通会员分组
            
            AuthStaff2Group group_s = new AuthStaff2Group();
            group_s.setStaffId(dto.getCompanyCreateStaff());
            group_s.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);
            group_s.setCreateStaff(dto.getCompanyCreateStaff());
            group_s.setCreateTime(DateUtil.getSysDate());
            group_s.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
            group_s.setUpdateStaff(dto.getCompanyCreateStaff());
            group_s.setUpdateTime(DateUtil.getSysDate());
            authRelManageSV.saveStaffGroupRel(group_s);
        }
        checkRes.setCompanyId(dto.getId());
        checkRes.setStaffId(info.getCreateStaff());
        //删除对应的审核通过数据
        CompanySignKey key = new CompanySignKey();
        key.setId(dto.getId());
        key.setCompanyId(dto.getCompanyId());
        companySignMapper.deleteByPrimaryKey(key);
        
        //更新店铺搜索数据
        StaffTools.solrUpdate("T_SHOP_INFO", shopid);
        return checkRes;
    }

    @Override
    public void updateNoCheckCompany(CompanySignReqDTO info) throws BusinessException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        CompanySign companySign = new CompanySign();
        companySign.setId(info.getId());
        companySign.setCompanyId(info.getCompanyId());
        companySign.setCheckDate(time);
        companySign.setCheckRemark(info.getCheckRemark());
        companySign.setCheckStaff(info.getCheckStaff());
        companySign.setCheckStatus(StaffConstants.companyInfo.COMPANY_CHECK_STATUS_NOPASS);
        companySign.setUpdateStaff(info.getUpdateStaff());
        companySign.setUpdateTime(time);
        companySignMapper.updateByPrimaryKeySelective(companySign);
        
    }

	@Override
	public Long getCheckCompanyCount() throws BusinessException {
		CompanySignCriteria example = new CompanySignCriteria();
		Long count = companySignMapper.countByExample(example);
		return count;
		
	}

    
}
