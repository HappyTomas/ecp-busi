/**
 * 
 */
package com.zengshi.ecp.staff.service.busi.register.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;



import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffEIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffNIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.CustInfoMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffEIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffMIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffNIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.common.vip.interfaces.ICustVipSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
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
public class RegisterSVImpl implements IRegisterSV {


    @Resource
    private AuthStaffMapper authStaffMapper;

    @Resource
    private AuthStaffCIDXMapper authStaffCIDXMapper;

    @Resource
    private AuthStaffMIDXMapper authStaffMIDXMapper;

    @Resource
    private AuthStaffEIDXMapper authStaffEIDXMapper;

    @Resource
    private AuthStaffNIDXMapper authStaffNIDXMapper;

    @Resource
    private CustInfoMapper custInfoMapper;
    
    @Resource
    private PasswordUtils passwordUtils;

    @Resource(name = "custVipSV")
    private ICustVipSV custVipSV;

    Timestamp time = new Timestamp(System.currentTimeMillis());

    @Resource(name = "seq_staff_info_id")
    private Sequence sequence;
    
    @Resource
    private IAuthRelManageSV authRelManageSV;

    @Override
    public long saveAuthStaff(AuthStaffReqDTO info) throws BusinessException {
        long id = sequence.nextValue();
        AuthStaff authStaff = new AuthStaff();
        authStaff.setId(id);
        authStaff.setStaffCode(info.getStaffCode());
        authStaff.setStaffClass(info.getStaffClass());
        authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        passwordUtils.setEncryAlgorithm("MD5-SALT");
        String password = passwordUtils.encry(info.getStaffPasswd());
        authStaff.setStaffPasswd(password);
        authStaff.setStartDate(time);
        authStaff.setInvalidTime(DateUtil.getOffsetDaysTime(time, 90));
        authStaff.setCreateFrom(info.getCreateFrom());
        if(StaffConstants.authStaff.CREATE_FROM.equals(info.getCreateFrom())){
            authStaff.setCreateStaff(id);
        }else{
            authStaff.setCreateStaff(info.getCreateStaff());
        }
      
        authStaff.setUpdateStaff(info.getUpdateStaff());
        authStaff.setCreateTime(time);
        authStaff.setUpdateTime(time);
        AuthStaffCIDX authStaffCIDX = new AuthStaffCIDX();
        authStaffCIDX.setStaffCode(info.getStaffCode());
        authStaffCIDX.setStaffId(id);
        //校验手机号码
        if (StringUtil.isNotBlank(info.getSerialNumber())) {
            checkSerialNumber(info);
            authStaffCIDX.setSerialNumber(Long.parseLong(info.getSerialNumber()));
        }
        //校验账号
        checkStaffCode(info);
        
        authStaffMapper.insert(authStaff);
        authStaffCIDXMapper.insert(authStaffCIDX);
        if(!StringUtil.isBlank(info.getSerialNumber())){
            AuthStaffMIDX authStaffMIDX = new AuthStaffMIDX();
            authStaffMIDX.setSerialNumber(info.getSerialNumber());
            authStaffMIDX.setStaffCode(info.getStaffCode());
            authStaffMIDX.setStaffId(id);
            authStaffMIDXMapper.insert(authStaffMIDX);
        }
        if(!StringUtil.isBlank(info.getEmail())){
            this.checkExist(info.getEmail(),id,StaffConstants.STAFF_EMAIL_REPEAT);
            AuthStaffEIDX e = new AuthStaffEIDX();
            e.setEmail(info.getEmail());
            e.setStaffCode(info.getStaffCode());
            e.setStaffId(id);
            authStaffEIDXMapper.insertSelective(e);
        }

        return id;

    }

    // 校验账号是否重复方法
    public void checkStaffCode(AuthStaffReqDTO info) {
        // 账号重复
        AuthStaffCIDXCriteria a = new AuthStaffCIDXCriteria();
        a.createCriteria().andStaffCodeEqualTo(info.getStaffCode());
        List<AuthStaffCIDX> list = authStaffCIDXMapper.selectByExample(a);
        if (list.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[] { "他人账号" });
        }
        //账号与手机重复
        AuthStaffMIDXCriteria b = new AuthStaffMIDXCriteria();
        b.createCriteria().andSerialNumberEqualTo(info.getStaffCode());
        List<AuthStaffMIDX> list1 = authStaffMIDXMapper.selectByExample(b);
        if (list1.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT,
                    new String[] { "他人手机号码" });
        }
        //账号与邮箱重复
        AuthStaffEIDXCriteria c = new AuthStaffEIDXCriteria();
        c.createCriteria().andEmailEqualTo(info.getStaffCode());
        List<AuthStaffEIDX> list2 = authStaffEIDXMapper.selectByExample(c);
        if (list2.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[] { "他人邮箱" });
        }
        //账号与昵称重复
        AuthStaffNIDXCriteria d = new AuthStaffNIDXCriteria();
        d.createCriteria().andNicknameEqualTo(info.getStaffCode());
        List<AuthStaffNIDX> list3 = authStaffNIDXMapper.selectByExample(d);
        if (list3.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[] { "他人昵称" });
        }
    }
    
    //验证手机号码
    public void checkSerialNumber(AuthStaffReqDTO info){
        
        //手机号码重复
        AuthStaffMIDXCriteria a = new AuthStaffMIDXCriteria();
        a.createCriteria().andSerialNumberEqualTo(info.getSerialNumber());
        List<AuthStaffMIDX> list1 = authStaffMIDXMapper.selectByExample(a);
        if (list1.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_SERIALNUMBER_REPEAT,
                    new String[] { "他人号码" });
        }
        //手机号码与账号重复
        AuthStaffCIDXCriteria b = new AuthStaffCIDXCriteria();
        b.createCriteria().andStaffCodeEqualTo(info.getSerialNumber());
        List<AuthStaffCIDX> list = authStaffCIDXMapper.selectByExample(b);
        if (list.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_SERIALNUMBER_REPEAT, new String[] { "他人账号" });
        }
        //手机号码与邮箱重复
        AuthStaffEIDXCriteria c = new AuthStaffEIDXCriteria();
        c.createCriteria().andEmailEqualTo(info.getSerialNumber());
        List<AuthStaffEIDX> list2 = authStaffEIDXMapper.selectByExample(c);
        if (list2.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[] { "他人邮箱" });
        }
        //手机号码与昵称重复
        AuthStaffNIDXCriteria d = new AuthStaffNIDXCriteria();
        d.createCriteria().andNicknameEqualTo(info.getSerialNumber());
        List<AuthStaffNIDX> list3 = authStaffNIDXMapper.selectByExample(d);
        if (list3.size() > 0) {
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[] { "他人昵称" });
        }
        
    }
    
    /**
     * 
     * checkExist:(校验是否存在：员工编码、手机号码、邮箱、用户昵称). <br/> 
     * 说明：用于校验的4个字段，都可以用来登录，所以必须保持唯一性
     * @author huangxl 
     * @param check 
     * @since JDK 1.7
     */
    public void checkExist(String check, Long staffId,String tipCode){
        if (StringUtil.isNotBlank(check)) {
            //邮箱与手机号码重复
            AuthStaffMIDXCriteria a = new AuthStaffMIDXCriteria();
            a.createCriteria().andSerialNumberEqualTo(check).andStaffIdNotEqualTo(staffId);
            List<AuthStaffMIDX> list1 = authStaffMIDXMapper.selectByExample(a);
            if (list1.size() > 0) {
                throw new BusinessException(tipCode,
                        new String[] { "他人手机号码" });
            }
            //邮箱与账号重复
            AuthStaffCIDXCriteria b = new AuthStaffCIDXCriteria();
            b.createCriteria().andStaffCodeEqualTo(check).andStaffIdNotEqualTo(staffId);
            List<AuthStaffCIDX> list = authStaffCIDXMapper.selectByExample(b);
            if (list.size() > 0) {
                throw new BusinessException(tipCode, new String[] { "他人账号" });
            }
            //邮箱与邮箱重复
            AuthStaffEIDXCriteria c = new AuthStaffEIDXCriteria();
            c.createCriteria().andEmailEqualTo(check).andStaffIdNotEqualTo(staffId);
            List<AuthStaffEIDX> list2 = authStaffEIDXMapper.selectByExample(c);
            if (list2.size() > 0) {
                throw new BusinessException(tipCode, new String[] { "他人邮箱" });
            }
            //邮箱与昵称重复
            AuthStaffNIDXCriteria d = new AuthStaffNIDXCriteria();
            d.createCriteria().andNicknameEqualTo(check).andStaffIdNotEqualTo(staffId);
            List<AuthStaffNIDX> list3 = authStaffNIDXMapper.selectByExample(d);
            if (list3.size() > 0) {
                throw new BusinessException(tipCode, new String[] { "他人昵称" });
            }
        }
    }

    @Override
    public void updateAuthStaff(AuthStaffReqDTO authStaff) throws BusinessException {
        AuthStaff staff = new AuthStaff();
        ObjectCopyUtil.copyObjValue(authStaff, staff, null, true);
        authStaffMapper.updateByPrimaryKeySelective(staff);
    }

    @Override
    public PageResponseDTO<AuthStaffResDTO> queryAuthStaff(AuthStaffReqDTO info)
            throws BusinessException {
        PageResponseDTO<AuthStaffResDTO> pageinfo = new PageResponseDTO<AuthStaffResDTO>();
        pageinfo.setPageNo(info.getPageNo());
        pageinfo.setPageSize(info.getPageSize());
        int startNum = pageinfo.getStartRowIndex();

        AuthStaffCriteria criteria = new AuthStaffCriteria();
        // Criteria sql = criteria.createCriteria();
        criteria.setLimitClauseStart(startNum);
        criteria.setLimitClauseCount(10);
        List<AuthStaff> list = authStaffMapper.selectByExample(criteria);
        long count = authStaffMapper.countByExample(criteria);
        List<AuthStaffResDTO> list1 = new ArrayList<AuthStaffResDTO>();
        for (AuthStaff authStaff : list) {
            AuthStaffResDTO dto = new AuthStaffResDTO();
            ObjectCopyUtil.copyObjValue(authStaff, dto, null, true);
            list1.add(dto);
        }
        pageinfo.setResult(list1);
        pageinfo.setCount(count);

        return pageinfo;
    }

    public AuthStaffCIDX queryAuthStaffIDX() throws BusinessException {
        AuthStaffCIDXCriteria authstaffidx = new AuthStaffCIDXCriteria();
        com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria.Criteria sql1 = authstaffidx
                .createCriteria();
        sql1.andStaffCodeEqualTo("admin");
        List<AuthStaffCIDX> list = authStaffCIDXMapper.selectByExample(authstaffidx);
        if (list.size() > 0 && list != null) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public void saveCustInfo(CustInfoReqDTO info) throws BusinessException {
        CustInfo custInfo = new CustInfo();
        custInfo.setStaffCode(info.getStaffCode());
        custInfo.setId(info.getId());
        custInfo.setSerialNumber(info.getSerialNumber());
        if (StringUtil.isNotBlank(info.getCustType())) {
        	custInfo.setCustType(info.getCustType());
        } else {
        	custInfo.setCustType(StaffConstants.custInfo.CUST_TYPE_P);
        }
        custInfo.setNickname(info.getNickname());
        custInfo.setStatus(StaffConstants.authStaff.STAFF_FLAG);
        custInfo.setShopId(info.getShopId());
        custInfo.setCompanyId(info.getCompanyId());
        if (StringUtil.isNotEmpty(info.getCustShopFlag()) && StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
        	custInfo.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);
        } else {
        	custInfo.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_NO);
        }
        
        custInfo.setCreateStaff(info.getCreateStaff());
        custInfo.setCreateTime(time);
        custInfo.setCustName(info.getCustName());
        // 获取等级编码
        CustLevelRuleReqDTO levelinfo = new CustLevelRuleReqDTO();
        levelinfo.setMinValue(new BigDecimal(0));
        levelinfo.setMaxValue(new BigDecimal(0));
        CustLevelRule rule = custVipSV.queryCustLevelRule(levelinfo);
        custInfo.setCustLevelCode(rule.getCustLevelCode());
        custInfo.setCustGrowValue(new Long(0));
        int i = custInfoMapper.insertSelective(custInfo);
        if(i>0){
            StaffTools.logInfo("saveCustInfoLog", custInfo);
        }
        
        /*2.1 插入会员权限分组：用户分组默认为普通会员分组10001，卖家默认分组为10003*/
        AuthStaff2Group group = new AuthStaff2Group();
        group.setStaffId(custInfo.getId());//员工ID
        //如果是卖家
        if (StringUtil.isNotEmpty(info.getCustShopFlag()) && StaffConstants.custInfo.SHOP_FLAG_YES.equals(info.getCustShopFlag())) {
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER);//会员分组，卖家会员为：10003
        } else {
            group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_GENERAL);//会员分组，普通会员为：10001
        }
        //如果是卖家子账号，设另一个分组：卖家子账号
        if ("1".equals(info.getSellerSubAcct())) {
        	group.setGroupId(StaffConstants.StaffGroup.STAFF_SYS_SELLER_SUB);
        }
        group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
        group.setCreateStaff(custInfo.getId());
        group.setUpdateStaff(custInfo.getId());
        
        authRelManageSV.saveStaffGroupRel(group);

    }

    @Override
    public String queryAuthStaffName(long staffId) throws BusinessException {
        AuthStaff authstaff = authStaffMapper.selectByPrimaryKey(staffId);
        
        if(authstaff==null||StringUtil.isBlank(authstaff.getStaffCode())){
            return "";
        }
        return authstaff.getStaffCode();
    }

    @Override
    public Long sign(AuthStaffReqDTO info) throws BusinessException {
        
        long id =  saveAuthStaff(info);
        CustInfoReqDTO custinfo = new CustInfoReqDTO();
        custinfo.setId(id);
        custinfo.setStaffCode(info.getStaffCode());
        custinfo.setSerialNumber(info.getSerialNumber());
        custinfo.setCreateStaff(id);
        custinfo.setCreateTime(time);
        custinfo.setUpdateTime(DateUtil.getSysDate());
        custinfo.setUpdateStaff(id);
        saveCustInfo(custinfo);
        return id;
        
    }

    @Override
    public AuthStaffResDTO findAuthStaffById(Long id) throws BusinessException {
        AuthStaff authStaff = authStaffMapper.selectByPrimaryKey(id);
        AuthStaffResDTO dto = new AuthStaffResDTO();
        ObjectCopyUtil.copyObjValue(authStaff,dto,null,true);
        return dto;
    }

}
