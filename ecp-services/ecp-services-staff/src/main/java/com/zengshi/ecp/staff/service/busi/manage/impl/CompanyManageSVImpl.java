package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyNameIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyStaffIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopNameIDXMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CompanyInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyInfoCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDX;
import com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDX;
import com.zengshi.ecp.staff.dao.model.CompanyShopIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDX;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopNameIDX;
import com.zengshi.ecp.staff.dao.model.ShopNameIDXCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.cache.interfaces.ICompanyCacheSV;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 企业管理service接口实现类<br>
 * Date:2015-8-14下午4:33:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CompanyManageSVImpl extends GeneralSQLSVImpl implements ICompanyManageSV{

    @Resource
    private CompanyInfoMapper companyInfoMapper;
    
    @Resource
    private CompanyShopIDXMapper comShopIDXMapper;
    
    @Resource(name = "seq_company_info_id")
    private Sequence seq_company; 
    
    @Resource
    private ShopNameIDXMapper shopNameIDXMapper;
    
    @Resource
    private CompanyStaffIDXMapper companyStaffIDXMapper;
    
    @Resource
    private CompanyNameIDXMapper companyNameIDXMapper;
    
    @Resource
    private IRegisterSV registerSV;
    
    @Resource
    private IShopMgrSV shopMgrSV;
    
    @Resource(name = "custVipSV")
    private ICustVipSV custVipSV;
    
    @Resource
    private CustInfoMapper custInfoMapper;
    
    @Resource
    private ICompanyCacheSV companyCacheSV;
    
    @Resource
    private IAuthRelManageSV authRelManageSV;
    
    @Resource
    private ICustManageSV custManageSV;
    
    
    @Override
    public ShopInfoResDTO saveShopInfo(ShopInfoReqDTO req) throws BusinessException {
        //校验店铺名称是否已经存在（放到保存店铺信息方法里面进行校验）
        ShopInfo shopInfo = new ShopInfo();
        ObjectCopyUtil.copyObjValue(req, shopInfo, null, true);
        shopInfo.setCreateTime(DateUtil.getSysDate());
       
        
        /*2、根据店铺联系人信息生成账号，用户分组默认为卖家会员分组10003*/
        AuthStaffReqDTO staffInfo = new AuthStaffReqDTO();
        staffInfo.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
        staffInfo.setStaffCode(req.getStaffCode());
        staffInfo.setStaffPasswd(req.getStaffPasswd());
        //帐号的创建方式 00：注册、10：管理员新增
        staffInfo.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);
        staffInfo.setCreateStaff(shopInfo.getCreateStaff());
        staffInfo.setUpdateStaff(shopInfo.getUpdateStaff());
        staffInfo.setSerialNumber(req.getLinkMobilephone());
        staffInfo.setEmail(req.getLinkEmail());
        
        Long staffId = registerSV.saveAuthStaff(staffInfo);
        
        /*2.1 插入卖家会员权限分组：用户分组默认为卖家会员分组10003*/
        AuthStaff2Group group = new AuthStaff2Group();
        group.setStaffId(staffId);//员工ID
        group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//会员分组，卖家会员为：10003
        group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
        group.setCreateStaff(staffId);
        group.setUpdateStaff(staffId);
        authRelManageSV.saveStaffGroupRel(group);
        /*2.2 插入卖家会员权限分组：用户分组默认为企业会员分组10002*/
        group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);//会员分组，卖家会员为：10003
        authRelManageSV.saveStaffGroupRel(group);
        /*1、保存店铺基本信息*/
        long shopId = shopMgrSV.insertShopInfo(shopInfo);
        /*3、新增会员信息*/
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStaffCode(req.getStaffCode());
        custInfo.setSerialNumber(req.getLinkMobilephone());
        custInfo.setEmail(req.getLinkEmail());
        custInfo.setNickname(req.getStaffCode());
        //设置企业管理员
        CompanyStaffIDXCriteria example = new CompanyStaffIDXCriteria();
        example.createCriteria().andCompanyIdEqualTo(shopInfo.getCompanyId());
        List<CompanyStaffIDX> companyStaffList = companyStaffIDXMapper.selectByExample(example);
        //如果没有企业会员
        if(CollectionUtils.isEmpty(companyStaffList)){
        	custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_ADMIN);
        }else{
            custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_C);
        }
        custInfo.setStatus(StaffConstants.authStaff.STAFF_FLAG);
        custInfo.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
        custInfo.setShopId(shopId);
        custInfo.setCompanyId(req.getCompanyId());
        // 获取等级编码
        CustLevelRuleReqDTO levelinfo = new CustLevelRuleReqDTO();
        levelinfo.setMinValue(new BigDecimal(0));
        levelinfo.setMaxValue(new BigDecimal(0));
        CustLevelRule rule = custVipSV.queryCustLevelRule(levelinfo);
        custInfo.setCustLevelCode(rule.getCustLevelCode());
        custInfo.setCustGrowValue(new Long(0));
        custInfo.setCreateTime(DateUtil.getSysDate());
        custInfo.setUpdateTime(DateUtil.getSysDate());
        custInfo.setCreateStaff(shopInfo.getCreateStaff());
        custInfo.setUpdateStaff(shopInfo.getUpdateStaff());
        CustInfo cust = new CustInfo();
        ObjectCopyUtil.copyObjValue(custInfo, cust, null, true);
        custInfoMapper.insertSelective(cust);
        /*4、新增企业对应店铺索引表*/
        CompanyShopIDX comShopIDX = new CompanyShopIDX();
        comShopIDX.setShopId(shopInfo.getId());
        comShopIDX.setCompanyId(shopInfo.getCompanyId());
        comShopIDXMapper.insertSelective(comShopIDX);
        
        /*5、新增店铺名称查询索引表*/
/*        ShopNameIDX shopNameIDX = new ShopNameIDX();
        shopNameIDX.setShopId(shopInfo.getId());
        shopNameIDX.setShopName(shopInfo.getShopName());
        shopNameIDXMapper.insert(shopNameIDX);*/
       
        /*6、新增企业对应会员索引表*/
        CompanyStaffIDX companyStaffIDX = new CompanyStaffIDX();
        companyStaffIDX.setCompanyId(shopInfo.getCompanyId());
        companyStaffIDX.setStaffId(staffId);
        companyStaffIDX.setApplyStatus(StaffConstants.custInfo.CHECK_STATUS_PASS);
        companyStaffIDXMapper.insert(companyStaffIDX);
        ShopInfoResDTO  dto = new ShopInfoResDTO();
        ObjectCopyUtil.copyObjValue(shopInfo, dto, null, true);
        
        //更新店铺搜索数据
        StaffTools.solrUpdate("T_SHOP_INFO", shopId);
        return dto;
    }

    @Override
    public PageResponseDTO<CompanyInfoResDTO> listCompanyInfo(CompanyInfoReqDTO companyInfo)
            throws BusinessException {
        PageResponseDTO<CompanyInfoResDTO> pageInfo = new PageResponseDTO<CompanyInfoResDTO>();
        pageInfo.setPageNo(companyInfo.getPageNo());
        pageInfo.setPageSize(companyInfo.getPageSize());
        CompanyInfoCriteria criteria = new CompanyInfoCriteria();
        criteria.setOrderByClause("CREATE_TIME DESC");
        Criteria sql = criteria.createCriteria();
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        
        //企业名称
        if (StringUtil.isNotBlank(companyInfo.getCompanyName())) {
            sql.andCompanyNameLike("%" + companyInfo.getCompanyName() + "%");
        }
        //是否入驻企业
        if (StringUtil.isNotBlank(companyInfo.getCompanyType())) {
            sql.andCompanyTypeEqualTo(companyInfo.getCompanyType());
        }
        //用户规模
        if (StringUtil.isNotBlank(companyInfo.getEmployeeNum())) {
            sql.andEmployeeNumEqualTo(companyInfo.getEmployeeNum());
        }
        pageInfo = super.queryByPagination(companyInfo, criteria, true, new PaginationCallback<CompanyInfo, CompanyInfoResDTO>() {

            @Override
            public List<CompanyInfo> queryDB(BaseCriteria criteria) {
                return companyInfoMapper.selectByExample((CompanyInfoCriteria)criteria);
            }

            @Override
            public List<Comparator<CompanyInfo>> defineComparators() {
                List<Comparator<CompanyInfo>> ls=new ArrayList<Comparator<CompanyInfo>>();
                ls.add(new Comparator<CompanyInfo>(){

                    @Override
                    public int compare(CompanyInfo o1, CompanyInfo o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return companyInfoMapper.countByExample((CompanyInfoCriteria)criteria);
            }

            @Override
            public CompanyInfoResDTO warpReturnObject(CompanyInfo t) {
                CompanyInfoResDTO dto = new CompanyInfoResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                dto.setShopNum(queryCompanyShop(dto.getId()));
                dto.setCustNum(queryCompanyCust(dto.getId()));
                dto.setAreaCode(StaffTools.createArea(dto.getCountryCode(), dto.getProvinceCode(), dto.getCityCode(), dto.getCountyCode()));
                dto.setEmployeeNum(BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_EMPLOYEE_NUM, dto.getEmployeeNum()));
                dto.setTrade(BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_TRADE, dto.getTrade()));
                dto.setCompanyType(BaseParamUtil.fetchParamValue(StaffConstants.companyInfo.COMPANY_TYPE, dto.getCompanyType()));
                return dto;
            }
        });
        return pageInfo;
    }
    
    
    //查询企业所属店铺数
    public Long queryCompanyShop(long companyId){
        CompanyShopIDXCriteria c = new CompanyShopIDXCriteria();
        c.createCriteria().andCompanyIdEqualTo(companyId);
       return comShopIDXMapper.countByExample(c);
        
    }
    
    //查询企业所属会员数
    public Long queryCompanyCust(long companyId){
        
        CompanyStaffIDXCriteria c = new CompanyStaffIDXCriteria();
        c.createCriteria().andCompanyIdEqualTo(companyId);
       return companyStaffIDXMapper.countByExample(c);
        
    }
    

    @Override
    public long saveCompanyInfo(CompanyInfoReqDTO in) throws BusinessException {
        //校验企业名称是否存在
        if (!StringUtil.isEmpty(in.getCompanyName())) {
            CompanyNameIDX nameIDX = this.findCompanyNameIDX(in);
            if (nameIDX != null && in.getId() != nameIDX.getCompanyId()) {
                throw new BusinessException(StaffConstants.companyInfo.COMPANY_NAME_REPEAT_ERROR);
            }
        }
        //通过序列生成企业ID
        Long companyId = seq_company.nextValue();
        //插入企业名称的索引表
        CompanyNameIDX nameIDX = new CompanyNameIDX();
        nameIDX.setCompanyId(companyId);
        nameIDX.setCompanyName(in.getCompanyName());
        nameIDX.setSerialNumbr(in.getLinkPhoneMsg());
        companyNameIDXMapper.insert(nameIDX);
        
        //插入企业信息数据
        CompanyInfo companyInfo = new CompanyInfo();
        ObjectCopyUtil.copyObjValue(in, companyInfo, null, true);
        companyInfo.setId(companyId);//id取序列中取
        companyInfo.setCreateTime(DateUtil.getSysDate());
        companyInfo.setUpdateTime(DateUtil.getSysDate());
        companyInfo.setCreateStaff(in.getStaff().getId());
        companyInfo.setUpdateStaff(in.getStaff().getId());
        companyInfo.setStatus("1");//状态为有效
        int i  =companyInfoMapper.insertSelective(companyInfo);
        if(i > 0){
            companyCacheSV.updateCacheCompany(companyId);
            //插入企业资料日志 
            companyInfo.setRemark("保存企业资料");
            StaffTools.logInfo("saveCompanyInfoLog", companyInfo);
        }
        return companyId;
    }

    @Override
    public int updateCompanyInfo(CompanyInfoReqDTO in) throws BusinessException {
        //校验企业名称是否存在
        if (!StringUtil.isEmpty(in.getCompanyName())) {
            CompanyNameIDX nameIDX = this.findCompanyNameIDX(in);
            //名称如果存在，则抛出异常
            if (nameIDX != null && in.getId().longValue() != nameIDX.getCompanyId().longValue()) {
                throw new BusinessException(StaffConstants.companyInfo.COMPANY_NAME_REPEAT_ERROR);
            } else {
                //获取企业信息
                CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(in.getId());
                //名称不存在，且名称有变动，删除原来的索引，插入新的企业名称的索引表，后面再插入企业信息
                if (!in.getCompanyName().equals(companyInfo.getCompanyName())) {
                    //删除原来的索引数据
                    companyNameIDXMapper.deleteByPrimaryKey(in.getId());
                    //插入新的企业名称的索引表
                    CompanyNameIDX IDX = new CompanyNameIDX();
                    IDX.setCompanyId(in.getId());
                    IDX.setCompanyName(in.getCompanyName());
                    IDX.setSerialNumbr(in.getLinkPhoneMsg());
                    companyNameIDXMapper.insert(IDX);
                }
            }
        }
        //插入企业信息
        CompanyInfo companyInfo = new CompanyInfo();
        ObjectCopyUtil.copyObjValue(in, companyInfo, null, true);
        companyInfo.setUpdateTime(DateUtil.getSysDate());
        companyInfo.setUpdateStaff(in.getStaff().getId());
        //修改
        int i  =companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
        if(i>0){
            //新增企业缓存
            companyCacheSV.updateCacheCompany(companyInfo.getId());
            //插入企业资料日志 
            CompanyInfo updateInfo = companyInfoMapper.selectByPrimaryKey(companyInfo.getId());
            updateInfo.setRemark("更新企业资料");
            StaffTools.logInfo("saveCompanyInfoLog", updateInfo);
        }
        return i;
    }

    @Override
    public CompanyInfoResDTO findCompanyInfoById(Long id) throws BusinessException {
        CompanyInfo info = companyInfoMapper.selectByPrimaryKey(id);
        CompanyInfoResDTO dto = new CompanyInfoResDTO();
        ObjectCopyUtil.copyObjValue(info, dto, null, true);
        dto.setShopNum(this.queryCompanyShop(id));//查询企业下属的店铺数
        return dto;
    }

    @Override
    public CompanyNameIDX findCompanyNameIDX(CompanyInfoReqDTO nameIDX) throws BusinessException {
        CompanyNameIDXCriteria criteria = new CompanyNameIDXCriteria();
        com.zengshi.ecp.staff.dao.model.CompanyNameIDXCriteria.Criteria sql = criteria.createCriteria();
        sql.andCompanyNameEqualTo(nameIDX.getCompanyName());
        List<CompanyNameIDX> resultList = companyNameIDXMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public ShopNameIDX findShopNameIDX(ShopInfoReqDTO req) throws BusinessException {
        ShopNameIDXCriteria criteria = new ShopNameIDXCriteria();
        com.zengshi.ecp.staff.dao.model.ShopNameIDXCriteria.Criteria sql = criteria.createCriteria();
        sql.andShopNameEqualTo(req.getShopName());
        sql.andShopIdNotEqualTo(req.getId());
        List<ShopNameIDX> resultList = shopNameIDXMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public CompanyInfoResDTO findCompanyInfoByName(String companyName) throws BusinessException {
        CompanyInfoResDTO dto = new CompanyInfoResDTO();
        if (!StringUtil.isEmpty(companyName)) {
            CompanyInfoReqDTO info = new CompanyInfoReqDTO();
            info.setCompanyName(companyName);
            CompanyNameIDX idx = this.findCompanyNameIDX(info);
            if (idx != null) {
                dto = this.findCompanyInfoById(idx.getCompanyId());
            }
        }
        return dto;
    }
    
    
    @Override
    public int updateCompanyStatus(CompanyInfoReqDTO req) throws BusinessException {
        //失效操作：需要判断下挂的店铺、会员是否还有生效的，如果有，则不让其操作
        if (StaffConstants.companyInfo.COMPANY_STATUS_INVALID.equals(req.getStatus())) {
            /*1、判断企业下是否还挂有生效的店铺*/
            ShopSelectReqDTO shopReq = new ShopSelectReqDTO();
            shopReq.setCompanyId(req.getId());//企业ID
            shopReq.setStatus(StaffConstants.shopInfo.SHOP_STATUS_NOMAL);//状态正常
            PageResponseDTO<ShopInfoResDTO> shopList = shopMgrSV.listShopByCond(shopReq);
            //如果还有生效的店铺，则抛出提示语
            if (shopList != null && shopList.getResult() != null && shopList.getResult().size() != 0) {
                throw new BusinessException(StaffConstants.companyInfo.COMPANY_STATUS_INVALID_FORBID, new String[]{"店铺"});
            }
            /*2、判断企业下是否还挂有生效的会员*/
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setCompanyId(req.getId());
            /*2-1、状态正常的会员*/
            custReq.setStatus(StaffConstants.authStaff.STAFF_FLAG);
            PageResponseDTO<CustInfoResDTO> custValidList = custManageSV.listCustInfo(custReq);
            if (custValidList != null && custValidList.getResult() != null && custValidList.getResult().size() != 0) {
                throw new BusinessException(StaffConstants.companyInfo.COMPANY_STATUS_INVALID_FORBID, new String[]{"会员"});
            }
            /*2-2、状态锁定的会员*/
            custReq.setStatus(StaffConstants.authStaff.STAFF_FLAG_LOCK);
            PageResponseDTO<CustInfoResDTO> custLockList = custManageSV.listCustInfo(custReq);
            if (custLockList != null && custLockList.getResult() != null && custLockList.getResult().size() != 0) {
                throw new BusinessException(StaffConstants.companyInfo.COMPANY_STATUS_INVALID_FORBID, new String[]{"会员"});
            }
        }
        //更新状态
        return this.updateCompanyInfo(req);
    }
}

