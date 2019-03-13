package com.zengshi.ecp.staff.service.busi.custinfo.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffEIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffNIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CompanyInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustAuthstrMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustEmailLogMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustGrowInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.common.CustLevelInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.CompanyInfo;
import com.zengshi.ecp.staff.dao.model.CustAuthstr;
import com.zengshi.ecp.staff.dao.model.CustEmailLog;
import com.zengshi.ecp.staff.dao.model.CustGrowInfo;
import com.zengshi.ecp.staff.dao.model.CustGrowInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustInfoCriteria;
import com.zengshi.ecp.staff.dao.model.CustLevelInfo;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustEmailLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustGrowRuleSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 会员资料信息服务接口实现类<br>
 * Date:2015-8-24下午5:51:35 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version
 * @since JDK 1.7
 */
public class CustInfoSVImpl implements ICustInfoSV {

    private static String MODULE = CustInfoSVImpl.class.getName();

    @Resource
    private CustAuthstrMapper authstrMapper;

    @Resource
    private CustEmailLogMapper custEmailLogMapper;

    @Resource
    private AuthStaffCIDXMapper authStaffCIDXMapper;

    @Resource
    private CustInfoMapper custInfoMapper;

    @Resource
    private AuthStaffEIDXMapper authStaffEIDXMapper;

    @Resource
    private AuthStaffMIDXMapper authStaffMIDXMapper;

    @Resource
    private AuthStaffNIDXMapper authStaffNIDXMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Resource
    private CustGrowInfoMapper custGrowInfoMapper;

    @Resource
    private CustLevelInfoMapper custLevelInfoMapper;

    @Resource
    private ICustVipSV custVipSV;

    @Resource(name = "custGrowRuleBySaleSV")
    private ICustGrowRuleSV custGrowRuleSV;

    @Resource(name = "seq_cust_authstr_id")
    private Sequence seq_cust_authstr_id;

    @Resource(name = "seq_cust_email_log_id")
    private Sequence seq_cust_email_log_id;

    @Resource(name = "seq_cust_grow_info_id")
    private Sequence seq_cust_grow_info_id;

    @Override
    public int saveCustAuthstr(CustAuthstrReqDTO authstr) throws BusinessException {
        CustAuthstr custAuthstr = new CustAuthstr();
        ObjectCopyUtil.copyObjValue(authstr, custAuthstr, null, true);
        custAuthstr.setId(seq_cust_authstr_id.nextValue());
        custAuthstr.setCreateTime(DateUtil.getSysDate());
        custAuthstr.setStatus("1");
        custAuthstr.setUpdateTime(DateUtil.getSysDate());
        return authstrMapper.insertSelective(custAuthstr);
    }

    @Override
    public long saveCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException {
        CustEmailLog log = new CustEmailLog();
        ObjectCopyUtil.copyObjValue(logInfo, log, null, true);
        log.setId(seq_cust_email_log_id.nextValue());
        log.setCreateStaff(logInfo.getStaff().getId());
        log.setCreateTime(DateUtil.getSysDate());
        log.setStartTime(DateUtil.getSysDate());
        custEmailLogMapper.insertSelective(log);
        return log.getId();
    }

    @Override
    public int updateCustEmailLog(CustEmailLogInfoReqDTO logInfo) throws BusinessException {
        CustEmailLog log = new CustEmailLog();
        ObjectCopyUtil.copyObjValue(logInfo, log, null, true);
        return custEmailLogMapper.updateByPrimaryKeySelective(log);
    }

    @Override
    public int updateCustInfo(CustInfoReqDTO custInfo) throws BusinessException {
        if (!StringUtil.isEmpty(custInfo.getNickname())) {
            AuthStaffNIDX nidx = new AuthStaffNIDX();
            nidx.setNickname(custInfo.getNickname());
            // 先验证昵称是否已经存在
            AuthStaffNIDX result = this.findAuthStaffNIDX(nidx);
            // 查到结果，且不是本身
            if (result != null && custInfo.getId().longValue() != result.getStaffId().longValue()) {
                throw new BusinessException(StaffConstants.STAFF_NICKNAME_EXIST);
            }
        }

        CustInfo info = new CustInfo();
        // 根据会员ID获取会员信息
        info = this.findCustInfoById(custInfo.getId());
        /* 会员昵称索引表插入逻辑 */
        /* 1、如果原来昵称为空，传入的参数昵称不为空，则索引表为新增 */
        if (StringUtil.isEmpty(info.getNickname()) && !StringUtil.isEmpty(custInfo.getNickname())) {
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());
            authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);
            /* 2、如果原来昵称不为空，传入的昵称与原来不一样，则需要删除原来的昵称索引，再新增昵称索引 */
        } else if (!StringUtil.isEmpty(info.getNickname())
                && !StringUtil.isEmpty(custInfo.getNickname())
                && !info.getNickname().equals(custInfo.getNickname())) {
            /* 2-1、删除原来的昵称索引数据 */
            AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
            Criteria sql = criteria.createCriteria();
            sql.andNicknameEqualTo(info.getNickname());
            authStaffNIDXMapper.deleteByExample(criteria);
            /* 2-2、新增昵称索引 */
            AuthStaffNIDX authStaffNIDX = new AuthStaffNIDX();
            authStaffNIDX.setNickname(custInfo.getNickname());// 新的昵称
            if (StringUtil.isNotBlank(custInfo.getSerialNumber())) {
                authStaffNIDX.setSerialNumber(Long.parseLong(custInfo.getSerialNumber()));
            }
            authStaffNIDX.setStaffId(custInfo.getId());
            authStaffNIDXMapper.insertSelective(authStaffNIDX);// 新增昵称索引
        }

        // 把界面上可以编码的字段，进行属性复制
        info.setNickname(custInfo.getNickname());// 昵称
        info.setGender(custInfo.getGender());// 性别
        info.setCustBirthday(custInfo.getCustBirthday());// 生日
        info.setSerialNumber(custInfo.getSerialNumber());// 手机
        info.setEmail(custInfo.getEmail());// 邮箱
        info.setCustName(custInfo.getCustName());// 真实姓名
        info.setProvinceCode(custInfo.getProvinceCode());// 省份
        info.setCityCode(custInfo.getCityCode());// 地市
        info.setCountyCode(custInfo.getCountyCode());// 区县
        info.setTelephone(custInfo.getTelephone());// 固定电话
        info.setDatailedAddress(custInfo.getDatailedAddress());// 详细地址
        info.setDisturbFlag(custInfo.getDisturbFlag());// 是否开启免打扰模式
        info.setCustBirthday(custInfo.getCustBirthday());
        return custInfoMapper.updateByPrimaryKey(info);// 更新会员信息
    }

    @Override
    public int saveAuthStaffEIDX(CustInfoReqDTO custInfo) throws BusinessException {
        AuthStaffEIDX authStaffEIDX = new AuthStaffEIDX();
        authStaffEIDX.setEmail(custInfo.getEmail());
        authStaffEIDX.setStaffCode(custInfo.getStaffCode());
        authStaffEIDX.setStaffId(custInfo.getId());
        return authStaffEIDXMapper.insertSelective(authStaffEIDX);
    }

    @Override
    public int saveAuthStaffMIDX(CustInfoReqDTO custInfo) throws BusinessException {
        AuthStaffMIDX authStaffMIDX = new AuthStaffMIDX();
        authStaffMIDX.setSerialNumber(custInfo.getSerialNumber());
        authStaffMIDX.setStaffCode(custInfo.getStaffCode());
        authStaffMIDX.setStaffId(custInfo.getId());
        return authStaffMIDXMapper.insertSelective(authStaffMIDX);
    }

    @Override
    public CustInfo findCustInfoById(Long staffId) throws BusinessException {
        return custInfoMapper.selectByPrimaryKey(staffId);
    }

    @Override
    public AuthStaffNIDX findAuthStaffNIDX(AuthStaffNIDX staffNIDX) throws BusinessException {
        AuthStaffNIDXCriteria criteria = new AuthStaffNIDXCriteria();
        Criteria sql = criteria.createCriteria();
        sql.andNicknameEqualTo(staffNIDX.getNickname());
        List<AuthStaffNIDX> resultList = authStaffNIDXMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public int saveCustPic(CustInfoReqDTO req) throws BusinessException {
        /* 2、更新用户信息表中的头像文件id */
        CustInfo custInfo = this.findCustInfoById(req.getId());
        custInfo.setCustPic(req.getCustPic());
        custInfo.setUpdateTime(DateUtil.getSysDate());
        return custInfoMapper.updateByPrimaryKey(custInfo);
    }

    @Override
    public byte[] readFile(String fileId) throws BusinessException {
        return FileUtil.readFile(fileId);
    }

    @Override
    public void deleteFileById(String fileId) throws BusinessException {
        FileUtil.deleteFile(fileId);
    }

    @Override
    public AuthStaffCIDX findAuthStaffCIDX(AuthStaffCIDX staffCIDX) throws BusinessException {
        AuthStaffCIDXCriteria criteria = new AuthStaffCIDXCriteria();
        criteria.createCriteria().andStaffCodeEqualTo(staffCIDX.getStaffCode());
        List<AuthStaffCIDX> resultList = authStaffCIDXMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(resultList)) {
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public CustInfoResDTO getCustInfoById(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
        CustInfoResDTO dto = new CustInfoResDTO();
        CustInfoCriteria c = new CustInfoCriteria();
        com.zengshi.ecp.staff.dao.model.CustInfoCriteria.Criteria sql = c.createCriteria();
        sql.andIdEqualTo(custInfoReqDTO.getId());
        if (StringUtil.isNotBlank(custInfoReqDTO.getStatus())) {
            sql.andStatusEqualTo(custInfoReqDTO.getStatus());
        }
        if (StringUtil.isNotBlank(custInfoReqDTO.getCustType())) {
            sql.andCustTypeEqualTo(custInfoReqDTO.getCustType());
        }
        List<CustInfo> list = custInfoMapper.selectByExample(c);
        if (!CollectionUtils.isEmpty(list)) {
            CustInfo custinfo = list.get(0);
            if (StaffConstants.custInfo.CUST_TYPE_C.equals(custinfo.getCustType())) {
                CompanyInfo company = companyInfoMapper.selectByPrimaryKey(custinfo.getCompanyId());
                custinfo.setProvinceCode(company.getProvinceCode());
                custinfo.setCityCode(company.getCityCode());
                custinfo.setCountyCode(company.getCountyCode());
            }
            ObjectCopyUtil.copyObjValue(custinfo, dto, null, false);
            // 查询会员等级名称
            if (StringUtil.isNotBlank(custinfo.getCustLevelCode())) {
                CustLevelInfo level = custLevelInfoMapper.selectByPrimaryKey(custinfo
                        .getCustLevelCode());
                dto.setCustLevelName(level.getCustLevelName());
            }

        }
        return dto;
    }

    @Override
    public void insertCustGrowInfo(CustGrowInfo custGrowInfo) throws BusinessException {

        long id = seq_cust_grow_info_id.nextValue();
        custGrowInfo.setId(id);
        try {
            custGrowInfoMapper.insertSelective(custGrowInfo);
        } catch (Exception e) {
            LogUtil.error(MODULE, "更新会员成长值明细异常", e);
        }

    }

    @Override
    public int updateCustLevelCode(PayQuartzInfoRequest dto) throws BusinessException {
        // 计算获取成长值
        long growValue = custGrowRuleSV.getGrowByParamRule(dto);
        Timestamp time = DateUtil.getSysDate();
        int i = 0;
        if(growValue>0){
        // 根据明细表计算一年内的成长值
        CustLevelRuleReqDTO custLevelRuleReqDTO = new CustLevelRuleReqDTO();
        CustInfo custInfo = custInfoMapper.selectByPrimaryKey(dto.getStaffId());
        if (!StringUtil.isEmpty(custInfo)) {
            long grow = 0;
            BaseSysCfgRespDTO SysDto = SysCfgUtil.fetchSysCfg(StaffConstants.CUST_GROW_TIME);
            String year = SysDto.getParaValue();
            Timestamp beforeTime = DateUtil.getOffsetYearsTime(time, -Integer.parseInt(year));

            CustGrowInfoCriteria custGrow = new CustGrowInfoCriteria();
            custGrow.createCriteria().andStaffIdEqualTo(dto.getStaffId())
                    .andCreateTimeLessThanOrEqualTo(time)
                    .andCreateTimeGreaterThanOrEqualTo(beforeTime).andStatusEqualTo(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            List<CustGrowInfo> growlist = custGrowInfoMapper.selectByExample(custGrow);
            for (CustGrowInfo custGrowInfo : growlist) {
                grow = grow + custGrowInfo.getGrowValue();
            }
            grow = grow + growValue;
            custInfo.setCustGrowValue(grow);
            custLevelRuleReqDTO.setMaxValue(new BigDecimal(grow));
            custLevelRuleReqDTO.setMinValue(new BigDecimal(grow));
            CustLevelRule custLevelRule = custVipSV.queryCustLevelRule(custLevelRuleReqDTO);
            // 返回的等级编码bean
            if (!StringUtil.isEmpty(custLevelRule)) {
                custInfo.setCustLevelCode(custLevelRule.getCustLevelCode());
                custInfo.setCustGrowValue(grow);
            }
            i = custInfoMapper.updateByPrimaryKeySelective(custInfo);
        }
        if (i > 0) {
            // 插入明细表
            CustGrowInfo info = new CustGrowInfo();
            info.setStaffId(dto.getStaffId());
            info.setGrowValue(growValue);
            info.setOrdId(dto.getOrderId());
            info.setSourceType(dto.getSourceType());
            info.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
            info.setCreateStaff(dto.getStaff().getId());
            info.setCreateTime(time);
            info.setRemark(BaseParamUtil.fetchParamValue(StaffConstants.custInfo.CUST_ACTION_TYPE,
                    dto.getSourceType()) + "发生时间为" + time);
            insertCustGrowInfo(info);
          }
        }
        return i;
    }

    @Override
    public void updateCustGrowInfo(CustGrowInfo custGrowInfo) throws BusinessException {
        CustGrowInfoCriteria criteria = new CustGrowInfoCriteria();
        criteria.createCriteria().andStaffIdEqualTo(custGrowInfo.getStaffId());
        custGrowInfoMapper.updateByExampleSelective(custGrowInfo, criteria);
    }

	@Override
	public Long getCustNewCount(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
		CustInfoCriteria example = new CustInfoCriteria();
		Timestamp t = DateUtil.getSysDate();
		Calendar calender = Calendar.getInstance();
		calender.setTime(t);
		int day = custInfoReqDTO.getCustDayTime();
		if(day==0){
			day=1;
		}
		calender.add(Calendar.DAY_OF_MONTH, -day);
		example.createCriteria().andCreateTimeBetween(new Timestamp(calender.getTimeInMillis()),t);
		Long count = custInfoMapper.countByExample(example);
		return count;
	}

}
