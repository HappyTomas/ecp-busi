package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopToStaffIDXMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ShopToStaffIDX;
import com.zengshi.ecp.staff.dao.model.ShopToStaffIDXCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustSubInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAuthStaffManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IManagerLoginSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopSubAuthStaffSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺子账户[认证用户]管理接口实现<br>
 * Date:2015年8月28日下午3:39:12  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class ShopSubAuthStaffSVImpl extends GeneralSQLSVImpl implements IShopSubAuthStaffSV {
    
    private static final String MODULE = ShopSubAuthStaffSVImpl.class.getName();

    @Resource
    private ShopToStaffIDXMapper shopToStaffIDXMapper;//店铺与子账户索引表
    
    @Resource
    private IRegisterSV registerSV; //用户注册服务
    @Resource
    private ICustManageSV custMangeSV; //客户管理服务
    @Resource
    private IAuthRelManageSV authRelManageSV; //用户权限关系服务
    @Resource
    private IManagerLoginSV managerLoginSV; //管理人员登录服务
    @Resource
    private IAuthStaffManageSV authStaffManangeSV; //系统用户管理
    @Resource
    private IAuthManageSV authManageSV; //用户权限服务
    @Resource
    private IRoleManageSV roleManageSV; //角色管理
    

    @Override
    public long saveShopSubAuthStaff(CustInfoReqDTO custReqDto, CustSubInfoReqDTO custSubReqDto) throws BusinessException {
        //1.信息验证
        //1.1校验店铺对应的子账号数，超过系统设置的子账号数，抛出异常编码100012
        long subCount = countShopSubAuthStaff(custReqDto.getShopId());
        String maxCount = BaseParamUtil.fetchParamValue("SHOP_SUB_ACCT_COUNT", "MAX_COUNT");

        if(StringUtil.isNotBlank(maxCount) && maxCount.equals(subCount)){
            throw new BusinessException("店铺子账户已达上限["
                    +maxCount+"]个");
        }
        
        //1.2登录用户名校验，非空， 4-16位字符，支持汉字、字母数字及“-”、“—”组合
        //1.3密码校验，非空，6-20位字符，建议由字母，数字和符号两种以上组合
        //1.4密码与确认密码必须相同
        //2.写入"用户登录表"
        AuthStaffReqDTO staffReqDto = new AuthStaffReqDTO();
        /*****子账号用户名必须为：当前主账号+“:”+子账号用户名*/
        String custSubCode = custReqDto.getStaffCode()+":"+custSubReqDto.getStaffCode();
        staffReqDto.setStaffCode(custSubCode);
        staffReqDto.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
        //TODO 前端密码逆解？
        staffReqDto.setStaffPasswd(custSubReqDto.getStaffPasswd());
        staffReqDto.setCreateFrom(StringUtil.isNotBlank(custSubReqDto.getCreateFrom())
                ?custSubReqDto.getCreateFrom():StaffConstants.authStaff.CREATE_FROM);//缺省为“注册”
        staffReqDto.setCreateStaff(custReqDto.getStaff().getId());
        
        long authStaffId = registerSV.saveAuthStaff(staffReqDto);
        //3.新增关系
        //3.1"用户角色"；非空，可多选。
        if(StringUtil.isNotBlank(custSubReqDto.getRoleIds())){
            String[] roleArray = custSubReqDto.getRoleIds().split(",");
            for (String roleId : roleArray) {
                roleId = roleId.trim();//去除前后空白
                AuthStaff2Role save = new AuthStaff2Role();
                save.setRoleId(Long.valueOf(roleId));
                save.setStaffId(authStaffId);
                save.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);
                save.setSysCode(StaffConstants.PublicParam.SYS_CODE_SELLER);
                save.setCreateStaff(custReqDto.getStaff().getId());
                save.setUpdateStaff(custReqDto.getStaff().getId());
                authRelManageSV.saveStaffRoleRel(save);
            }
        }else{
            throw new BusinessException("角色不可为空");
        }
        //4.写入"客户信息表"
        CustInfoReqDTO saveCust = new CustInfoReqDTO();
        saveCust.setId(authStaffId);
        saveCust.setStaffCode(custSubCode);
        saveCust.setCreateStaff(custReqDto.getStaff().getId());
        saveCust.setCustShopFlag(StaffConstants.custInfo.SHOP_FLAG_YES);//是否卖家标识 
        saveCust.setCustName(custSubReqDto.getCustName());
        saveCust.setSellerSubAcct("1");//是否卖家子账号
        saveCust.setShopId(custReqDto.getShopId());
        saveCust.setCompanyId(custReqDto.getCompanyId());
        saveCust.setCustType(StaffConstants.custInfo.CUST_TYPE_C);
        registerSV.saveCustInfo(saveCust);
        //5.保存索引
        ShopToStaffIDX saveShopStaffIDX = new ShopToStaffIDX();
        saveShopStaffIDX.setShopId(custReqDto.getShopId());
        saveShopStaffIDX.setStaffCode(custSubCode);
        saveShopStaffIDX.setStaffId(authStaffId);
        saveShopStaffIDX.setStatus("1");
        try {
            shopToStaffIDXMapper.insert(saveShopStaffIDX);
        } catch (Exception e) {
            LogUtil.error(MODULE, "数据入库异常", e);
            throw new BusinessException("店铺[id:"+custReqDto.getShopId()+"]子账户索引入库失败");
        }
        
        return authStaffId;
    }

    @Override
    public PageResponseDTO<CustInfoResDTO> listShopSubAuthStaff(CustInfoReqDTO custReqDto, CustSubInfoReqDTO custSubReqDto) throws BusinessException {
        
        PageResponseDTO<CustInfoResDTO> res = new PageResponseDTO<CustInfoResDTO>();
        
        ShopToStaffIDXCriteria stsIDXCriteria = new ShopToStaffIDXCriteria();
        ShopToStaffIDXCriteria.Criteria sql = stsIDXCriteria.createCriteria();
        //sql.getAllCriteria().size();
        if(custReqDto.getShopId()!=null){
            sql.andShopIdEqualTo(custReqDto.getShopId());
        }
        if(StringUtil.isNotBlank(custSubReqDto.getStaffCode())){
            sql.andStaffCodeLike("%"+custSubReqDto.getStaffCode()+"%");
        }
        if(StringUtil.isNotBlank(custSubReqDto.getStatus())){
            sql.andStatusEqualTo(custSubReqDto.getStatus());
        }
        stsIDXCriteria.setLimitClauseCount(custSubReqDto.getPageSize());
        stsIDXCriteria.setLimitClauseStart(custSubReqDto.getStartRowIndex());
        
        res = super.queryByPagination(custSubReqDto, stsIDXCriteria, true, new PaginationCallback<ShopToStaffIDX, CustInfoResDTO>() {

            @Override
            public List<ShopToStaffIDX> queryDB(BaseCriteria criteria) {
                return shopToStaffIDXMapper.selectByExample((ShopToStaffIDXCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return shopToStaffIDXMapper.countByExample((ShopToStaffIDXCriteria)criteria);
            }
            
            @Override
            public CustInfoResDTO warpReturnObject(ShopToStaffIDX t) {
                CustInfoResDTO custInfoDto = custMangeSV.findCustInfoById(t.getStaffId());
                if(custInfoDto!=null){
                    custInfoDto.setStatusDesc(BaseParamUtil.fetchParamValue(
                            StaffConstants.authStaff.STAFF_FLAG_PARAMKEY,t.getStatus()));
                    //组合关系角色信息
                    List<AuthStaff2Role> listRole = authManageSV.listRole(t.getStaffId());
                    if(CollectionUtils.isNotEmpty(listRole)){
                        String roleIds = "";
                        String roleNames = "";
                        for (AuthStaff2Role authStaff2Role : listRole) {
                            roleIds = roleIds + "，" + authStaff2Role.getRoleId();
                            AuthRoleResDTO findRole = roleManageSV.findAuthRoleById(authStaff2Role.getRoleId());
                            roleNames = roleNames + "，" + (findRole==null?"角色不存在":findRole.getRoleName());
                        }
                        if (StringUtil.isNotBlank(roleIds)) {
                            custInfoDto.setRoleIds(roleIds.substring(1));
                        }
                        
                        if (StringUtil.isNotBlank(roleNames)) {
                            custInfoDto.setRoleNames(roleNames.substring(1));
                        }
                    }
                }
                return custInfoDto;
            }
        });
        
        return res;
    }

    /**
     * 
     * countShopSubAuthStaff:(统计店铺子账户数量). <br/> 
     * 
     * @author linby 
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public long countShopSubAuthStaff(Long shopId) throws BusinessException{
        long res = 0L;
        if(shopId==null){
            return res;
        }
        ShopToStaffIDXCriteria ssIDXCriteria = new ShopToStaffIDXCriteria();
        ShopToStaffIDXCriteria.Criteria sql = ssIDXCriteria.createCriteria();
        sql.andShopIdEqualTo(shopId);
        try {
            res = shopToStaffIDXMapper.countByExample(ssIDXCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询异常", e);
            throw new BusinessException("店铺[id:"+shopId+"]子账户统计失败");
        }
        return res;
    }

    @Override
    public void deleteShopSubAuthStaff(Long staffId) throws BusinessException {
        //1.删除用户表
        AuthStaff delStaff = new AuthStaff();
        delStaff.setId(staffId);
        authStaffManangeSV.deleteAuthStaffById(delStaff, true);
        //2.删除客户表
        CustInfo delCust = new CustInfo();
        delCust.setId(staffId);
        custMangeSV.deleteCustInfoById(delCust, true);
        //3.删除用户角色关联表
        AuthStaff2Role role = new AuthStaff2Role();
        role.setStaffId(staffId);
        authRelManageSV.deleteStaffRoleRel(role);
        //4.删除店铺索引
        ShopToStaffIDXCriteria example = new ShopToStaffIDXCriteria();
        example.createCriteria().andStaffIdEqualTo(staffId);
        try {
            shopToStaffIDXMapper.deleteByExample(example);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据删除异常", e);
            throw new BusinessException("code1", new String[]{ "店铺子账户[id:"+staffId+"]索引删除失败" });
        }
    }

    @Override
    public void disableShopSubAuthStaff(Long staffId) throws BusinessException {
        //1.用户表
        AuthStaffReqDTO authStaff = new AuthStaffReqDTO();
        authStaff.setId(staffId);
        authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG_INVALID);
        managerLoginSV.updateAuthStaff(authStaff);
        //2.客户表
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_FAILURE);
        custMangeSV.updateCustInfo(custInfo);
        
        /*更新店铺子账号表状态，状态3为失效，与custInfo表保持一致*/
        ShopToStaffIDX shop2staff = new ShopToStaffIDX();
        shop2staff.setStatus("3");//状态失效
        ShopToStaffIDXCriteria criteria = new ShopToStaffIDXCriteria();
        criteria.createCriteria().andStaffIdEqualTo(staffId);
        shopToStaffIDXMapper.updateByExampleSelective(shop2staff, criteria);
    }

    @Override
    public void enableShopSubAuthStaff(Long staffId) throws BusinessException {
        //1.用户表
        AuthStaffReqDTO authStaff = new AuthStaffReqDTO();
        authStaff.setId(staffId);
        authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        managerLoginSV.updateAuthStaff(authStaff);
        //2.客户表
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(staffId);
        custInfo.setStatus(StaffConstants.custInfo.CUST_STATUS_NORMAL);
        custMangeSV.updateCustInfo(custInfo);
        
        ShopToStaffIDX shop2staff = new ShopToStaffIDX();
        shop2staff.setStatus("1");//状态生效
        ShopToStaffIDXCriteria criteria = new ShopToStaffIDXCriteria();
        criteria.createCriteria().andStaffIdEqualTo(staffId);
        shopToStaffIDXMapper.updateByExampleSelective(shop2staff, criteria);
    }

    @Override
    public int updateSubAcctRole(CustSubInfoReqDTO req) throws BusinessException {
        /*1、删除用户下的角色*/
        AuthStaff2Role role = new AuthStaff2Role();
        role.setStaffId(req.getId());
        authRelManageSV.deleteStaffRoleRel(role);
        
        /*2、保存更新后的用户角色*/
        if (StringUtil.isNotBlank(req.getRoleIds())) {
            String [] roleIds = req.getRoleIds().split(",");
            for (String roleId : roleIds) {
                AuthStaff2Role roleAdd = new AuthStaff2Role();
                roleAdd.setRoleId(Long.parseLong(roleId));
                roleAdd.setCreateStaff(req.getStaff().getId());
                roleAdd.setUpdateStaff(req.getStaff().getId());
                roleAdd.setSysCode(StaffConstants.PublicParam.SYS_CODE_SELLER);//卖家中心
                roleAdd.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_N);//普通会员
                roleAdd.setUpdateTime(DateUtil.getSysDate());
                roleAdd.setCreateTime(DateUtil.getSysDate());
                roleAdd.setStaffId(req.getId());
                authRelManageSV.saveStaffRoleRel(roleAdd);
            }
        }
        /*3、更新用户信息*/
        CustInfoReqDTO custInfo = new CustInfoReqDTO();
        custInfo.setId(req.getId());
        custInfo.setGender(req.getGender());
        custInfo.setCustName(req.getCustName());
        custMangeSV.updateCustInfo(custInfo);
        return 1;
    }
}

