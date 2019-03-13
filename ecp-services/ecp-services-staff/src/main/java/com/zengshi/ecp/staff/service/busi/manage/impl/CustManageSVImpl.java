package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffNIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyStaffIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustGrowInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustSensitiveLogMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDX;
import com.zengshi.ecp.staff.dao.model.CompanyStaffIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustGrowInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfoCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dao.model.CustSensitiveLog;
import com.zengshi.ecp.staff.dao.model.CustSensitiveLogCriteria;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustThirdSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员管理服务接口实现类<br>
 * Date:2015-8-12下午8:20:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class CustManageSVImpl extends GeneralSQLSVImpl implements ICustManageSV {
    
    private static final String MODULE = CustManageSVImpl.class.getName();

    @Resource
    private CustInfoMapper custInfoMapper;
    
    @Resource
    private CustSensitiveLogMapper custSensitiveLogMapper;
    
    @Resource
    private AuthStaffNIDXMapper staffNIDXMapper;
    
    @Resource
    private CompanyStaffIDXMapper companySIDXMapper;
    
    @Resource
    private ICustVipSV custVipSV;
    
    @Resource
    private ICompanyManageSV companyManageSV;
    
    @Resource
    private AuthStaffNIDXMapper authStaffNIDXMapper;
    
    @Resource
    private AuthStaffCIDXMapper authStaffCIDXMapper;
    
    @Resource
    private AuthStaffMIDXMapper authStaffMIDXMapper;
    
    @Resource
    private IShopMgrSV shopMgrSV;
    
    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource
    private IAuthRelManageSV authRelManageSV;
    
    @Resource
    private IRegisterSV registerSV;
    
    @Resource
    private CompanyStaffIDXMapper companyStaffIDXMapper;
    
    @Resource
    private PasswordUtils passwordUtils;
    
    @Resource
    private ICustThirdSV custThirdSV;
    
    @Resource
    private CustGrowInfoMapper custGrowInfoMapper;
    
    @Override
    public int saveCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        CustInfo cust = new CustInfo();
        ObjectCopyUtil.copyObjValue(custInfo,cust,null,true);
        //先验证用户名是否已经存在
        if (!StringUtil.isEmpty(custInfo.getStaffCode())) {
            registerSV.checkExist(custInfo.getStaffCode(), custInfo.getId(), StaffConstants.STAFF_CODE_REPEAT);
        }
        //验证手机号码是否已经存在
        if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
            registerSV.checkExist(custInfo.getSerialNumber(), custInfo.getId(), StaffConstants.STAFF_SERIALNUMBER_REPEAT);
        }
        //根据会员等级获取成长值
        if (!StringUtil.isEmpty(custInfo.getCustLevelCode())) {
            CustLevelRuleReqDTO ruleInfo = new CustLevelRuleReqDTO();
            ruleInfo.setCustLevelCode(custInfo.getCustLevelCode());
            CustLevelRule levelRule = custVipSV.queryCustLevelMinValueAndMaxValue(ruleInfo);
            if (levelRule != null) {
                cust.setCustGrowValue(levelRule.getMinValue().longValue());
            }
            //没有传入会员等级时
        } else {
            // 获取等级编码
            CustLevelRuleReqDTO levelinfo = new CustLevelRuleReqDTO();
            levelinfo.setMinValue(new BigDecimal(0));
            levelinfo.setMaxValue(new BigDecimal(0));
            CustLevelRule rule = custVipSV.queryCustLevelRule(levelinfo);
            cust.setCustLevelCode(rule.getCustLevelCode());
            cust.setCustGrowValue(new Long(0));
        }
        cust.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
        if(StringUtil.isNotBlank(cust.getSysType())){
        	cust.setSysType(cust.getSysType());
        }else{
        	cust.setSysType(StaffConstants.custInfo.CUST_SYS_TYPE_OWN);
        }
        int result = custInfoMapper.insert(cust);
        if(result>0){
            StaffTools.logInfo("saveCustInfoLog", cust);
        }
        //如果有昵称，保存昵称的索引表
        if (!StringUtil.isEmpty(custInfo.getNickname())) {
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());
            authStaffNIDX.setStaffId(custInfo.getId());
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            staffNIDXMapper.insertSelective(authStaffNIDX);
        }
        //如果有企业信息，则同时增加用户与企业的索引表
        if (!StringUtil.isEmpty(custInfo.getCompanyId())) {
            CompanyStaffIDX companyStaffIDX = new CompanyStaffIDX();
            companyStaffIDX.setStaffId(custInfo.getId());
            companyStaffIDX.setCompanyId(custInfo.getCompanyId());
            companyStaffIDX.setApplyStatus(StaffConstants.custInfo.CHECK_STATUS_PASS);
            companySIDXMapper.insert(companyStaffIDX);
            
            //插入权限表，会员分组，企业会员为：10002
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);//会员分组，企业会员为：10002
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
            group.setCreateStaff(cust.getId());
            group.setUpdateStaff(cust.getId());
            
            authRelManageSV.saveStaffGroupRel(group);
        }else{
            //插入权限表，会员分组，普通会员为：10001
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_GENERAL);//会员分组，企业会员为：10001
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
            group.setCreateStaff(cust.getId());
            group.setUpdateStaff(cust.getId());
            
            authRelManageSV.saveStaffGroupRel(group);
        }
        //如果是卖家，还要插入卖家分组
        if (StaffConstants.custInfo.SHOP_FLAG_YES.equals(custInfo.getCustShopFlag())) {
        	//插入权限表，会员分组，普通会员为：10001
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//卖家分组，为：10003
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
            group.setCreateStaff(cust.getId());
            group.setUpdateStaff(cust.getId());
            
            authRelManageSV.saveStaffGroupRel(group);
        }
        
        return result;
    }

    @Override
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        CustInfo info = new CustInfo();
        ObjectCopyUtil.copyObjValue(custInfo, info, null, true);
        //验证手机号码是否已经存在
        if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
            registerSV.checkExist(custInfo.getSerialNumber(), custInfo.getId(), StaffConstants.STAFF_SERIALNUMBER_REPEAT);
        }
        CustInfo cust = new CustInfo();
        //根据会员ID获取会员信息
        cust = custInfoSV.findCustInfoById(custInfo.getId());
        /*会员昵称索引表插入逻辑*/
        /*1、如果原来昵称为空，传入的参数昵称不为空，则索引表为新增*/
        if (StringUtil.isEmpty(cust.getNickname()) && !StringUtil.isEmpty(custInfo.getNickname())) {
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);
        /*2、如果原来昵称不为空，传入的昵称与原来不一样，则需要删除原来的昵称索引，再新增昵称索引*/
        } else if (!StringUtil.isEmpty(cust.getNickname()) && !StringUtil.isEmpty(custInfo.getNickname()) && !cust.getNickname().equals(custInfo.getNickname())) {
            /*2-1、删除原来的昵称索引数据*/
            AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
            com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria.Criteria sql = criteria.createCriteria();
            sql.andNicknameEqualTo(cust.getNickname());
            authStaffNIDXMapper.deleteByExample(criteria);
            /*2-2、新增昵称索引*/
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());//新的昵称
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);//新增昵称索引
        /*3、如果原来的昵称不为空，传入的昵称为空，则要删除原来的昵称索引*/
        } else if (!StringUtil.isEmpty(cust.getNickname()) && StringUtil.isEmpty(custInfo.getNickname())) {
            /*2-1、删除原来的昵称索引数据*/
            AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
            com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria.Criteria sql = criteria.createCriteria();
            sql.andNicknameEqualTo(cust.getNickname());
            authStaffNIDXMapper.deleteByExample(criteria);
        }
        //如果会员等级变动
        if(StringUtil.isNotBlank(custInfo.getCustLevelCode()) && !custInfo.getCustLevelCode().equals(cust.getCustLevelCode())){
            CustLevelRuleReqDTO levelDto = new CustLevelRuleReqDTO();
            levelDto.setCustLevelCode(custInfo.getCustLevelCode());
            CustLevelRule rule = custVipSV.queryCustLevelMinValueAndMaxValue(levelDto);
            info.setCustGrowValue(rule.getMinValue().longValue());
            //同时插入对应的成长值记录，如果是等级提升，则插入正的差值，如果是降级，则插入负的多出来的成长值
            //这里的成长值，是取自成长值来源记录表里面，计算时，根据配置的有效期，取有效期内的数据
            long grow = 0;
            Timestamp time = DateUtil.getSysDate();
            BaseSysCfgRespDTO SysDto = SysCfgUtil.fetchSysCfg(StaffConstants.CUST_GROW_TIME);//平台配置的成长值有效期
            String year = SysDto.getParaValue();
            Timestamp beforeTime = DateUtil.getOffsetYearsTime(time, -Integer.parseInt(year));
            CustGrowInfoCriteria custGrow = new CustGrowInfoCriteria();
            custGrow.createCriteria().andStaffIdEqualTo(custInfo.getId())
                    .andCreateTimeLessThanOrEqualTo(time)
                    .andCreateTimeGreaterThanOrEqualTo(beforeTime).andStatusEqualTo(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            List<CustGrowInfo> growlist = custGrowInfoMapper.selectByExample(custGrow);
            for (CustGrowInfo custGrowInfo : growlist) {
                grow = grow + custGrowInfo.getGrowValue();//累计成长值
            }
            // 插入明细表
            CustGrowInfo growInfo = new CustGrowInfo();
            growInfo.setStaffId(custInfo.getId());
            growInfo.setGrowValue(rule.getMinValue().longValue() - grow);//如果是负值，说明是降级
            growInfo.setSourceType("06");//管理员调整
            growInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            growInfo.setCreateStaff(custInfo.getId());
            growInfo.setCreateTime(time);
            growInfo.setRemark("后台管理员调整会员等级。发生时间为" + time);
            custInfoSV.insertCustGrowInfo(growInfo);
        }
        if(custInfo.getShopId() != null && custInfo.getShopId() != 0){
            info.setCustShopFlag("1");
        }
        //如果更新了会员的所属企业，则先删除原索引关系，同时增加用户与企业的索引表
        if (custInfo.getCompanyId() != null && custInfo.getCompanyId() != cust.getCompanyId()) {
            companySIDXMapper.deleteByPrimaryKey(custInfo.getId());
            
            CompanyStaffIDX companyStaffIDX = new CompanyStaffIDX();
            companyStaffIDX.setStaffId(custInfo.getId());
            companyStaffIDX.setCompanyId(custInfo.getCompanyId());
            companySIDXMapper.insert(companyStaffIDX);
            //如果原来的企业不为空，更新的会员企业为空，则删除
        } else if (cust.getCompanyId() != null && custInfo.getCompanyId() == null) {
        	companySIDXMapper.deleteByPrimaryKey(custInfo.getId());
        	//同时删除企业会员分组的权限
        	AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);//卖家分组，企业会员为：10003
        	authRelManageSV.deleteStaffGroupRel(group);
        }
        /*4、更新卖家分组权限*/
        /*4.1、如果原来不是卖家，现在是卖家，则新增卖家分组权限*/
        if (!StaffConstants.custInfo.SHOP_FLAG_YES.equals(cust.getCustShopFlag()) && StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
        	//插入权限表，会员分组，普通会员为：10001
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//卖家分组，为：10003
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
            group.setCreateStaff(cust.getId());
            group.setUpdateStaff(cust.getId());
            authRelManageSV.saveStaffGroupRel(group);
            /*4.2、如果原来是卖家，现在不是卖家，则删除卖家分组权限*/
        } else if (StaffConstants.custInfo.SHOP_FLAG_YES.equals(cust.getCustShopFlag()) && !StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
        	AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//卖家分组，为：10003
        	authRelManageSV.deleteStaffGroupRel(group);
        }
        
        info.setUpdateStaff(custInfo.getStaff().getId());
        info.setUpdateTime(DateUtil.getSysDate());
        //更新会员信息
        int result = custInfoMapper.updateByPrimaryKeySelective(info);
        
        return result;
    }

    @Override
    public CustInfoResDTO findCustInfoById(Long id) throws BusinessException {
        CustInfo info = custInfoMapper.selectByPrimaryKey(id);
        if (info != null) {
            CustInfoResDTO dto = new CustInfoResDTO();
            ObjectCopyUtil.copyObjValue(info, dto, null, true);
            //会员等级编码转为会员等级名称
            if (!StringUtil.isEmpty(dto.getCustLevelCode())) {
                CustLevelInfoResDTO level = new CustLevelInfoResDTO();
                level.setCustLevelCode(dto.getCustLevelCode());
                dto.setCustLevelName(custVipSV.queryCustLevelName(level));
            }
            return dto;
        } else {
            return null;
        }
    }

    @Override
    public PageResponseDTO<CustInfoResDTO> listCustInfo(final CustInfoReqDTO info)
            throws BusinessException {
        PageResponseDTO<CustInfoResDTO> pageInfo = new PageResponseDTO<CustInfoResDTO>();
        pageInfo.setPageNo(info.getPageNo());
        pageInfo.setPageSize(info.getPageSize());
        pageInfo.setCount(0);//初始化记录为0
        pageInfo.setResult(null);//初始传空值
        
        CustInfoCriteria custCriteria = new CustInfoCriteria();
        com.zengshi.ecp.staff.dao.model.CustInfoCriteria.Criteria sql = custCriteria.createCriteria();
        //查询条件：登录工号
        if (StringUtil.isNotBlank(info.getStaffCode())) {
            sql.andStaffCodeLike("%" + info.getStaffCode() + "%");
        }
        //查询条件：手机号码
        if (StringUtil.isNotBlank(info.getSerialNumber())) {
            sql.andSerialNumberLike("%" + info.getSerialNumber() + "%");
        }
        //查询条件：所属企业
        if (info.getCompanyId() != null && info.getCompanyId() != 0L) {
            sql.andCompanyIdEqualTo(info.getCompanyId());
        }
        //查询条件：状态
        if (StringUtil.isNotBlank(info.getStatus())) {
            sql.andStatusEqualTo(info.getStatus());
        }
        //查询条件：会员昵称
        if (StringUtil.isNotBlank(info.getNickname())) {
            sql.andNicknameLike("%" + info.getNickname() + "%");
        }
        //查询条件：会员真实姓名
        if (StringUtil.isNotBlank(info.getCustName())) {
            sql.andCustNameLike("%" + info.getCustName() + "%");
        }
        //查询条件：会员等级
        if (StringUtil.isNotBlank(info.getCustLevelCode())) {
            sql.andCustLevelCodeEqualTo(info.getCustLevelCode());
        }
        
        if(StringUtil.isNotBlank(info.getSensitiveType())){
         if(info.getSensitiveType().equals("5")){
        	 sql.andSensitiveTypeNotEqualTo("0");
        	
         }else{
        	 sql.andSensitiveTypeEqualTo(info.getSensitiveType());
        	
         }
        	
        }
        
        custCriteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        custCriteria.setLimitClauseCount(pageInfo.getPageSize());
        custCriteria.setOrderByClause("create_time desc");
        pageInfo = super.queryByPagination(info, custCriteria, true, new PaginationCallback<CustInfo, CustInfoResDTO>() {

            @Override
            public List<CustInfo> queryDB(BaseCriteria criteria) {
                return custInfoMapper.selectByExample((CustInfoCriteria)criteria);
            }

            @Override
            public List<Comparator<CustInfo>> defineComparators() {
                List<Comparator<CustInfo>> ls=new ArrayList<Comparator<CustInfo>>();
                ls.add(new Comparator<CustInfo>(){

                    @Override
                    public int compare(CustInfo o1, CustInfo o2) {
                    		    if(o1.getCreateTime().getTime()==o2.getCreateTime().getTime()){
                                    return 0;
                                }
                                return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    
                    }
                    
                });
                return ls;
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return custInfoMapper.countByExample((CustInfoCriteria)criteria);
            }

            @Override
            public CustInfoResDTO warpReturnObject(CustInfo t) {
                CustInfoResDTO dto = new CustInfoResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                CustManageSVImpl.this.dataNameConvert(dto);
                CustSensitiveLog custSensitiveLog=getCustSensitiveLog(t.getId());
                if(null!=custSensitiveLog){
                dto.setCreateStaffCode(custSensitiveLog.getCreateStaffCode());
                dto.setSensitiveTime(custSensitiveLog.getCreateTime());
                }
                if(StringUtil.isNotBlank(t.getSensitiveType())&&!t.getSensitiveType().equals("0")){
                String value = BaseParamUtil.fetchParamValue("CUST_SENSITIVE_TYPE", t.getSensitiveType());
                dto.setSensitiveType(value);
                }
                return dto;
            }
        });
        return pageInfo;
        
    }

    /**
     * 
     * dataNameConvert:(会员列表字段由code获取相应的name的方法). <br/> 
     * 
     * @author huangxl 
     * @param dto 
     * @since JDK 1.7
     */
    private void dataNameConvert(CustInfoResDTO dto) {
        //会员等级编码转为会员等级名称
        if (!StringUtil.isEmpty(dto.getCustLevelCode())) {
            CustLevelInfoResDTO info = new CustLevelInfoResDTO();
            info.setCustLevelCode(dto.getCustLevelCode());
            dto.setCustLevelName(custVipSV.queryCustLevelName(info));
        }
        //国家-省份-地市-区县  转换
        if (!StringUtil.isEmpty(dto.getProvinceCode())) {
            dto.setPccString(StaffTools.createArea("", dto.getProvinceCode(), dto.getCityCode(), dto.getCountyCode()));
        }
        //所属企业由CODE获取NAME
        if (!StringUtil.isEmpty(dto.getCompanyId())) {
            CompanyInfoResDTO company = companyManageSV.findCompanyInfoById(dto.getCompanyId());
            if (company != null) {
                dto.setCompanyName(company.getCompanyName());
            }
        }
        //所属店铺由code获取name
        if (!StringUtil.isEmpty(dto.getShopId())) {
            ShopInfo shopInfo = shopMgrSV.findShopByShopID(dto.getShopId());
            if(!StringUtil.isEmpty(shopInfo)){
                dto.setShopName(shopInfo.getShopName());
            }
        }
        CustThirdCodeReqDTO treqdto = new CustThirdCodeReqDTO();
        treqdto.setStaffId(dto.getId());
        treqdto.setThirdCodeType(StaffConstants.custInfo.CUST_THIRD_CODE_TYPE_TMALL);
        CustThirdCodeResDTO tresdto = custThirdSV.queryCustThirdCode(treqdto);
        if(tresdto!=null){
            dto.setThirdCode(tresdto.getThirdCode());
        }
    }

    @Override
    public void deleteCustInfoById(CustInfo custInfo, Boolean isPhy) throws BusinessException {
        if(isPhy!=null&&isPhy){//物理删除
            //1.删除t_cust_info
            try {
                custInfoMapper.deleteByPrimaryKey(custInfo.getId());
            } catch (DataAccessException e) {
                LogUtil.error(MODULE, "数据删除异常", e);
                throw new BusinessException("code1", new String[]{ "客户[id:"+custInfo.getId()+"]物理删除失败" });
            }
            //2.删除依赖  TODO
            
        }else{//逻辑删除
            CustInfoReqDTO updateCustInfo = new CustInfoReqDTO();
            updateCustInfo.setId(custInfo.getId());
            updateCustInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_FAILURE);
            try {
                updateCustInfo(updateCustInfo);
            } catch (Exception e) {
                LogUtil.error(MODULE, "数据逻辑删除异常", e);
                throw new BusinessException("code1", new String[]{ "客户[id:"+custInfo.getId()+"]逻辑删除失败" });
            }
        }
    }

    @Override
    public void updateCustTypeById(Long companyId, String custType) throws BusinessException {
        
        CompanyStaffIDXCriteria c = new CompanyStaffIDXCriteria();
        c.createCriteria().andCompanyIdEqualTo(companyId);
        List<CompanyStaffIDX> list =  companyStaffIDXMapper.selectByExample(c);
        for (CompanyStaffIDX ids : list) {
            CustInfo info = new CustInfo();
            info.setId(ids.getStaffId());
            info.setCustType(custType);
            custInfoMapper.updateByPrimaryKey(info);
        }
        
    }

    @Override
    public Long saveCustInfoForRSV(CustInfoReqDTO custInfo) throws BusinessException {
        custInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));//创建时间
        /*先新增登录用户信息*/
        AuthStaffReqDTO staff = new AuthStaffReqDTO();
        //密码由页面传入已加密的密码
        staff.setStaffPasswd(custInfo.getStaffPassword());
        staff.setCreateStaff(custInfo.getCreateStaff());
        staff.setUpdateStaff(custInfo.getUpdateStaff());
        staff.setStaffCode(custInfo.getStaffCode());
        staff.setCreateFrom(StaffConstants.authStaff.CREATE_FROM_MANAGER);
        staff.setSerialNumber(custInfo.getSerialNumber());
        staff.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
        staff.setEmail(custInfo.getEmail());
        Long staffId = registerSV.saveAuthStaff(staff);
        
        //新增会员信息,staffId取自登录用户表，共用。其他信息由页面传入
        custInfo.setId(staffId);
        this.saveCustInfo(custInfo);
        return staffId;
    }

    @Override
    public int updateCustStatus(CustInfoReqDTO custInfo) throws BusinessException {
        int result = 0;
        //根据ID查出会员信息
        CustInfoResDTO cust = this.findCustInfoById(custInfo.getId());
        if (!StringUtil.isEmpty(cust)) {
            //如果是企业会员，在生效前，需要先判断所属企业是否为生效，如果是失效状态，则不让更新
            if (StaffConstants.authStaff.STAFF_FLAG.equals(custInfo.getStatus())) {
                if (cust.getCompanyId() != null && cust.getCompanyId() != 0L) {
                    CompanyInfoResDTO companyRes = companyManageSV.findCompanyInfoById(cust.getCompanyId());
                    if (StaffConstants.companyInfo.COMPANY_STATUS_INVALID.equals(companyRes.getStatus())) {
                        throw new BusinessException(StaffConstants.custInfo.CUST_STATUS_OPT_ERROR);
                    }
                }
            }
            
            CustInfoReqDTO info = new CustInfoReqDTO();
            ObjectCopyUtil.copyObjValue(cust, info, null, true);
            //变更会员状态
            info.setStatus(custInfo.getStatus());
            //更新时间
            cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            result = this.updateCustInfo(info);
            //同时更新工号表的状态字段t_auth_staff.staff_flag
            AuthStaffResDTO authDto = registerSV.findAuthStaffById(custInfo.getId());
            AuthStaffReqDTO staffInfo = new AuthStaffReqDTO();
            ObjectCopyUtil.copyObjValue(authDto, staffInfo, null, true);
            staffInfo.setStaffFlag(custInfo.getStatus());
            staffInfo.setUpdateStaff(custInfo.getUpdateStaff());
            staffInfo.setUpdateTime(custInfo.getUpdateTime());
            registerSV.updateAuthStaff(staffInfo);
        }
        return result;
    }

    @Override
    public int resetPwd(AuthStaffReqDTO req) throws BusinessException {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        req.setUpdateTime(time);//更新时间
        //密码失效时间，同步修改为重置密码后的3个月
        req.setInvalidTime(DateUtil.getOffsetDaysTime(time, 90));
        //重置密码为123456，经MD5加密
        passwordUtils.setEncryAlgorithm("MD5-SALT");
        String password = passwordUtils.encry(StaffConstants.authStaff.PWD_RESET_DEFAULT);
        req.setStaffPasswd(password);
        registerSV.updateAuthStaff(req);
        return 1;
    }

    @Override
    public AuthStaffCIDX findAuthStaffCIDXByCode(AuthStaffCIDX req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参对象"});
        }
        AuthStaffCIDXCriteria criteria = new AuthStaffCIDXCriteria();
        //查询条件：staffCode
        if (StringUtil.isNotBlank(req.getStaffCode())) {
            criteria.createCriteria().andStaffCodeEqualTo(req.getStaffCode());
        }
        List<AuthStaffCIDX> list = authStaffCIDXMapper.selectByExample(criteria);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException {
        CustInfoCriteria criteria = new CustInfoCriteria();
        Criteria sql = criteria.createCriteria();
        boolean flag = false;
        //查询条件：昵称
        if (StringUtil.isNotBlank(req.getNickname())) {
            sql.andNicknameEqualTo(req.getNickname());
            flag = true;
        }
        //查询条件：手机号码
        if (StringUtil.isNotBlank(req.getSerialNumber())) {
            sql.andSerialNumberEqualTo(req.getSerialNumber());
            flag = true;
        }
        //查询条件：登录工号
        if (StringUtil.isNotBlank(req.getStaffCode())) {
            sql.andStaffCodeEqualTo(req.getStaffCode());
            flag = true;
        }
        //查询条件：电子邮箱
        if (StringUtil.isNotBlank(req.getEmail())) {
            sql.andEmailEqualTo(req.getEmail());
            flag = true;
        }
        if (!flag) {
            return null;
        }
        List<CustInfo> custList = custInfoMapper.selectByExample(criteria);
        if (!CollectionUtils.isEmpty(custList)) {
            CustInfo custInfo = custList.get(0);
            CustInfoResDTO dto = new CustInfoResDTO();
            ObjectCopyUtil.copyObjValue(custInfo, dto, null, true);
            //会员等级编码转为会员等级名称
            if (!StringUtil.isEmpty(dto.getCustLevelCode())) {
                CustLevelInfoResDTO info = new CustLevelInfoResDTO();
                info.setCustLevelCode(dto.getCustLevelCode());
                dto.setCustLevelName(custVipSV.queryCustLevelName(info));
            }
            return dto;
        }
        return null;
    }

    @Override
    public int updatePhoneByStaffId(CustInfoReqDTO req) throws BusinessException {
        
        /*1、更新会员表*/
        CustInfo custInfo = new CustInfo();
        ObjectCopyUtil.copyObjValue(req, custInfo, null, false);
        custInfo.setUpdateTime(DateUtil.getSysDate());
        custInfo.setUpdateStaff(req.getStaff().getId());
        custInfoMapper.updateByPrimaryKeySelective(custInfo);
        
        /*2、更新手机索引表：先删除，后插入*/
        /*2.1、查询出原来的手机索引记录*/
        AuthStaffMIDXCriteria criteria = new AuthStaffMIDXCriteria();
        criteria.createCriteria().andStaffIdEqualTo(req.getId());
        List<AuthStaffMIDX> midxList = authStaffMIDXMapper.selectByExample(criteria);
        
        /*2.2、删除原来的索引记录*/
        if (!CollectionUtils.isEmpty(midxList)) {
            authStaffMIDXMapper.deleteByPrimaryKey(midxList.get(0).getSerialNumber());
        }
        
        /*2.3、插入新的手机索引数据*/
        AuthStaffMIDX midx = new AuthStaffMIDX();
        midx.setSerialNumber(req.getSerialNumber());
        midx.setStaffCode(req.getStaffCode());
        midx.setStaffId(req.getId());
        authStaffMIDXMapper.insert(midx);
        
        /*3、更新staffCode的索引表*/
        AuthStaffCIDX cidx = new AuthStaffCIDX();
        cidx.setSerialNumber(Long.parseLong(req.getSerialNumber()));
        cidx.setStaffCode(req.getStaffCode());
        authStaffCIDXMapper.updateSerialNumberByCode(cidx);
        
        return 1;
    }

	@Override
	public int updateCustInfoForEmpty(CustInfoReqDTO custInfo) throws BusinessException {
		CustInfo info = new CustInfo();
        ObjectCopyUtil.copyObjValue(custInfo, info, null, true);
        //先验证昵称是否已经存在
        if (!StringUtil.isEmpty(custInfo.getNickname())) {
            registerSV.checkExist(custInfo.getNickname(), custInfo.getId(),StaffConstants.STAFF_NICKNAME_REPEAT);
        }
        
        CustInfo cust = new CustInfo();
        //根据会员ID获取会员信息
        cust = custInfoSV.findCustInfoById(custInfo.getId());
        /*会员昵称索引表插入逻辑*/
        /*1、如果原来昵称为空，传入的参数昵称不为空，则索引表为新增*/
        if (StringUtil.isEmpty(cust.getNickname()) && !StringUtil.isEmpty(custInfo.getNickname())) {
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);
        /*2、如果原来昵称不为空，传入的昵称与原来不一样，则需要删除原来的昵称索引，再新增昵称索引*/
        } else if (!StringUtil.isEmpty(cust.getNickname()) && !StringUtil.isEmpty(custInfo.getNickname()) && !cust.getNickname().equals(custInfo.getNickname())) {
            /*2-1、删除原来的昵称索引数据*/
            AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
            com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria.Criteria sql = criteria.createCriteria();
            sql.andNicknameEqualTo(cust.getNickname());
            authStaffNIDXMapper.deleteByExample(criteria);
            /*2-2、新增昵称索引*/
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());//新的昵称
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);//新增昵称索引
        /*3、如果原来的昵称不为空，传入的昵称为空，则要删除原来的昵称索引*/
        } else if (!StringUtil.isEmpty(cust.getNickname()) && StringUtil.isEmpty(custInfo.getNickname())) {
            /*2-1、删除原来的昵称索引数据*/
            AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
            com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria.Criteria sql = criteria.createCriteria();
            sql.andNicknameEqualTo(cust.getNickname());
            authStaffNIDXMapper.deleteByExample(criteria);
        }
      //如果会员等级变动
        if(StringUtil.isNotBlank(custInfo.getCustLevelCode()) && !custInfo.getCustLevelCode().equals(cust.getCustLevelCode())){
            CustLevelRuleReqDTO levelDto = new CustLevelRuleReqDTO();
            levelDto.setCustLevelCode(custInfo.getCustLevelCode());
            CustLevelRule rule = custVipSV.queryCustLevelMinValueAndMaxValue(levelDto);
            info.setCustGrowValue(rule.getMinValue().longValue());
            //同时插入对应的成长值记录，如果是等级提升，则插入正的差值，如果是降级，则插入负的多出来的成长值
            //这里的成长值，是取自成长值来源记录表里面，计算时，根据配置的有效期，取有效期内的数据
            long grow = 0;
            Timestamp time = DateUtil.getSysDate();
            BaseSysCfgRespDTO SysDto = SysCfgUtil.fetchSysCfg(StaffConstants.CUST_GROW_TIME);//平台配置的成长值有效期
            String year = SysDto.getParaValue();
            Timestamp beforeTime = DateUtil.getOffsetYearsTime(time, -Integer.parseInt(year));
            CustGrowInfoCriteria custGrow = new CustGrowInfoCriteria();
            custGrow.createCriteria().andStaffIdEqualTo(custInfo.getId())
                    .andCreateTimeLessThanOrEqualTo(time)
                    .andCreateTimeGreaterThanOrEqualTo(beforeTime).andStatusEqualTo(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            List<CustGrowInfo> growlist = custGrowInfoMapper.selectByExample(custGrow);
            for (CustGrowInfo custGrowInfo : growlist) {
                grow = grow + custGrowInfo.getGrowValue();//累计成长值
            }
            // 插入明细表
            CustGrowInfo growInfo = new CustGrowInfo();
            growInfo.setStaffId(custInfo.getId());
            growInfo.setGrowValue(rule.getMinValue().longValue() - grow);//如果是负值，说明是降级
            growInfo.setSourceType("06");//管理员调整
            growInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            growInfo.setCreateStaff(custInfo.getId());
            growInfo.setCreateTime(time);
            growInfo.setRemark("后台管理员调整会员等级。发生时间为" + time);
            custInfoSV.insertCustGrowInfo(growInfo);
        }
        if(custInfo.getShopId() != null && custInfo.getShopId() != 0){
            info.setCustShopFlag("1");
        }
        //如果更新了会员的所属企业，则先删除原索引关系，同时增加用户与企业的索引表
        if (custInfo.getCompanyId() != null && custInfo.getCompanyId() != cust.getCompanyId()) {
            companySIDXMapper.deleteByPrimaryKey(custInfo.getId());
            
            CompanyStaffIDX companyStaffIDX = new CompanyStaffIDX();
            companyStaffIDX.setStaffId(custInfo.getId());
            companyStaffIDX.setCompanyId(custInfo.getCompanyId());
            companySIDXMapper.insert(companyStaffIDX);
            //如果原来的企业不为空，更新的会员企业为空，则删除
        } else if (cust.getCompanyId() != null && custInfo.getCompanyId() == null) {
        	companySIDXMapper.deleteByPrimaryKey(custInfo.getId());
        	//同时删除企业会员分组的权限
        	AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_ENTERPRISE);//卖家分组，企业会员为：10003
        	authRelManageSV.deleteStaffGroupRel(group);
        }
        /*4、更新卖家分组权限*/
        /*4.1、如果原来不是卖家，现在是卖家，则新增卖家分组权限*/
        if (!StaffConstants.custInfo.SHOP_FLAG_YES.equals(cust.getCustShopFlag()) && StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
        	//插入权限表，会员分组，普通会员为：10001
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//卖家分组，为：10003
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
            group.setCreateStaff(cust.getId());
            group.setUpdateStaff(cust.getId());
            authRelManageSV.saveStaffGroupRel(group);
            /*4.2、如果原来是卖家，现在不是卖家，则删除卖家分组权限*/
        } else if (StaffConstants.custInfo.SHOP_FLAG_YES.equals(cust.getCustShopFlag()) && !StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
        	AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(cust.getId());//员工ID
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//卖家分组，为：10003
        	authRelManageSV.deleteStaffGroupRel(group);
        }
        info.setUpdateStaff(custInfo.getStaff().getId());
        info.setUpdateTime(DateUtil.getSysDate());
        //更新会员信息
        int result = custInfoMapper.updateByPrimaryKey(info);
        
        return result;
	}

	@Override
	public boolean checkCodeExist(String code,Long staffId, String type) throws BusinessException {
		boolean isExist = false;
		try {
			//如果存在，则会抛出异常
			registerSV.checkExist(code, staffId, "");
		} catch (Exception e) {
			isExist = true;
		}
		
		return isExist;
	}

	@Override
	public int updateScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		CustInfo record = new CustInfo();
		record.setId(custInfoReqDTO.getId());
		record.setSensitiveType(custInfoReqDTO.getSensitiveType());
		record.setSensitiveDesc(custInfoReqDTO.getSensitiveDesc());
		record.setUpdateTime(DateUtil.getSysDate());
		custInfoMapper.updateByPrimaryKeySelective(record);
		
		CustSensitiveLog custSensitiveLog = new CustSensitiveLog();
		custSensitiveLog.setActionType(custInfoReqDTO.getActionType());
		custSensitiveLog.setCreateStaff(custInfoReqDTO.getStaff().getId());
		custSensitiveLog.setCreateTime(DateUtil.getSysDate());
		AuthStaffResDTO authStaffResDTO = registerSV.findAuthStaffById(custInfoReqDTO.getStaff().getId());
		custSensitiveLog.setCreateStaffCode(authStaffResDTO.getStaffCode());
		custSensitiveLog.setSensitiveDesc(custInfoReqDTO.getSensitiveDesc());
		custSensitiveLog.setSensitiveType(custInfoReqDTO.getSensitiveType());
		custSensitiveLog.setStaffId(custInfoReqDTO.getId());
		return custSensitiveLogMapper.insertSelective(custSensitiveLog);
	}

	@Override
	public int delScust(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		CustInfo custInfo = custInfoMapper.selectByPrimaryKey(custInfoReqDTO.getId());
		CustInfo record = new CustInfo();
		record.setId(custInfoReqDTO.getId());
		record.setSensitiveType("0");
		record.setSensitiveDesc(null);
		record.setUpdateTime(DateUtil.getSysDate());
		custInfoMapper.updateByPrimaryKeySelective(record);
		CustSensitiveLog custSensitiveLog = new CustSensitiveLog();
		custSensitiveLog.setActionType(custInfoReqDTO.getActionType());
		custSensitiveLog.setCreateStaff(custInfoReqDTO.getStaff().getId());
		AuthStaffResDTO authStaffResDTO = registerSV.findAuthStaffById(custInfoReqDTO.getStaff().getId());
		custSensitiveLog.setCreateTime(DateUtil.getSysDate());
		custSensitiveLog.setCreateStaffCode(authStaffResDTO.getStaffCode());
		custSensitiveLog.setSensitiveDesc(custInfo.getSensitiveDesc());
		custSensitiveLog.setSensitiveType(custInfo.getSensitiveType());
		custSensitiveLog.setStaffId(custInfo.getId());
		return custSensitiveLogMapper.insertSelective(custSensitiveLog);
	}
	
	
	public CustSensitiveLog getCustSensitiveLog(Long staffId)throws BusinessException{
		CustSensitiveLogCriteria example = new CustSensitiveLogCriteria();
		example.setOrderByClause("CREATE_TIME DESC");
		example.createCriteria().andStaffIdEqualTo(staffId);
		List<CustSensitiveLog> list = custSensitiveLogMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)){
			return list.get(0);
		}
		 return null;
	}

}

