package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthAdminCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffAdminMapper;
import com.zengshi.ecp.staff.dao.model.AuthAdminCIDX;
import com.zengshi.ecp.staff.dao.model.AuthAdminCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdminCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffAdminResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAuthStaffManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IStaffGroupManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 管理员功能管理接口实现类<br>
 * Date:2015年9月18日上午10:46:50  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AdminManageSVImpl extends GeneralSQLSVImpl implements IAdminManageSV {
    
    private static final String MODULE = AdminManageSVImpl.class.getName();
    
    @Resource
    private AuthStaffAdminMapper authStaffAdminMapper; //管理员
    
    @Resource
    private AuthAdminCIDXMapper authAdminCIDXMapper; //管理员登陆[登录名]索引表
    
    @Resource
    private PasswordUtils passwordUtils; //密码工具
    
    @Resource
    private IAuthStaffManageSV authStaffSV;  //用户管理  
    
    @Resource
    private IAuthRelManageSV authRelManageSV;  //用户权限关系管理
    
    @Resource
    private IStaffGroupManageSV staffGroupManageSV; //用户分组
    
    @Resource
    private IRoleManageSV roleManageSV; //角色管理
    
    @Override
    public long saveAuthStaffAdmin(AuthStaffAdminReqDTO save) throws BusinessException {
        //1.验证管理员:登录名[帐号]
        AuthStaffAdmin exist = findAuthStaffAdminByCode(save.getStaffCode());
        if(exist!=null){
            throw new BusinessException(StaffConstants.STAFF_CODE_REPEAT, new String[]{"其他管理员帐号"});
        }
        
        
        //2.创建用户
        AuthStaff staff = new AuthStaff();
        ObjectCopyUtil.copyObjValue(save, staff, null, true);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        staff.setCreateTime(time);//创建时间
        staff.setUpdateTime(time);//更新时间
        staff.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);
        if(StringUtil.isBlank(staff.getStaffFlag())){//缺省“有效”
            staff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        }
        passwordUtils.setEncryAlgorithm("MD5-SALT");
        String password = passwordUtils.encry(staff.getStaffPasswd());
        staff.setStaffPasswd(password);//加密
        staff.setStartDate(time);
        staff.setInvalidTime(DateUtil.getOffsetDaysTime(time, 90));
        long staffNew = authStaffSV.saveAuthStaff(staff);
        //3.创建“管理员”
        AuthStaffAdmin admin = new AuthStaffAdmin();
        ObjectCopyUtil.copyObjValue(save, admin, null, true);
        admin.setId(staffNew);
        admin.setCreateTime(time);//创建时间
        admin.setUpdateTime(time);//更新时间
        try {
            authStaffAdminMapper.insertSelective(admin);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "创建管理员数据入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{"管理员"});
        }
        //创建管理员权限：1、管理员角色权限
        if (StringUtil.isNotBlank(save.getStaffRole())) {
            String[] roleArray = save.getStaffRole().split(",");
            AuthStaff2Role role = new AuthStaff2Role();
            role.setStaffId(staffNew);//用户ID
            role.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//管理员
            role.setCreateTime(DateUtil.getSysDate());
            role.setUpdateTime(DateUtil.getSysDate());
            role.setCreateStaff(save.getCreateStaff());
            role.setUpdateStaff(save.getUpdateStaff());
            role.setSysCode("2000");//管理平台
            for (int i = 0; i < roleArray.length; i++) {
                role.setRoleId(Long.parseLong(roleArray[i]));//角色ID
                authRelManageSV.saveStaffRoleRel(role);
            }
        }
        
        //创建管理员权限：2、管理员用户组权限
        if (StringUtil.isNotBlank(save.getStaffGroup())) {
            String[] staffGroupArray = save.getStaffGroup().split(",");
            AuthStaff2Group group = new AuthStaff2Group();
            group.setStaffId(staffNew);//用户ID
            group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//管理员
            group.setCreateTime(DateUtil.getSysDate());
            group.setUpdateTime(DateUtil.getSysDate());
            group.setCreateStaff(save.getCreateStaff());
            group.setUpdateStaff(save.getUpdateStaff());
            for (int i = 0; i < staffGroupArray.length; i++) {
                group.setGroupId(Long.parseLong(staffGroupArray[i]));//用户组ID
                authRelManageSV.saveStaffGroupRel(group);
            }
        }
        
        //4.新增管理员登录名索引
        AuthAdminCIDX adminCIDX = new AuthAdminCIDX();
        adminCIDX.setStaffId(staffNew);
        adminCIDX.setStaffCode(save.getStaffCode());
        try {
            authAdminCIDXMapper.insert(adminCIDX);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "创建管理员登录名索引数据入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{"管理员索引"});
        }
        
        return staffNew;
    }

    @Override
    public AuthStaffAdmin findAuthStaffAdminById(Long id) throws BusinessException {
        if(id==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaffAdmin res = new AuthStaffAdmin();
        try {
            res = authStaffAdminMapper.selectByPrimaryKey(id);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "查询管理员失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"管理员"});
        }
        return res;
    }

    @Override
    public void updateAuthStaffAdminById(AuthStaffAdminReqDTO update) throws BusinessException {
        if(update==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaffAdmin admin = new AuthStaffAdmin();
        ObjectCopyUtil.copyObjValue(update, admin, null, true);
        admin.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        /*如果有修改用户所属角色，则先删除用户下的角色，再插入用户所属角色*/
        if ("true".equals(update.getStaffRoleEdit())) {
            //删除 
            AuthStaff2Role delete = new AuthStaff2Role();
            delete.setStaffId(update.getId());
            authRelManageSV.deleteStaffRoleRel(delete);
            //为空的时候，执行到上面的删除即结束了
            if (StringUtil.isNotBlank(update.getStaffRole())) {
                //插入
                String[] roleArray = update.getStaffRole().split(",");
                AuthStaff2Role role = new AuthStaff2Role();
                role.setStaffId(update.getId());//用户ID
                role.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//管理员
                role.setCreateTime(DateUtil.getSysDate());
                role.setUpdateTime(DateUtil.getSysDate());
                role.setCreateStaff(update.getCreateStaff());
                role.setUpdateStaff(update.getUpdateStaff());
                role.setSysCode("2000");//管理平台
                for (int i = 0; i < roleArray.length; i++) {
                    role.setRoleId(Long.parseLong(roleArray[i]));//角色ID
                    authRelManageSV.saveStaffRoleRel(role);
                }
            }
        }
        
        /*如果有修改用户所属用户组，则先删除用户下的用户组，再插入用户所属用户组*/
        if ("true".equals(update.getStaffGroupEdit())) {
            //删除 用户组
            AuthStaff2Group delete = new AuthStaff2Group();
            delete.setStaffId(update.getId());
            authRelManageSV.deleteStaffGroupRel(delete);
            //为空的时候，执行到上面的删除即结束了。
            if (StringUtil.isNotBlank(update.getStaffGroup())) {
                //插入用户组
                String[] staffGroupArray = update.getStaffGroup().split(",");
                AuthStaff2Group group = new AuthStaff2Group();
                group.setStaffId(update.getId());//用户ID
                group.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);//管理员
                group.setCreateTime(DateUtil.getSysDate());
                group.setUpdateTime(DateUtil.getSysDate());
                group.setCreateStaff(update.getCreateStaff());
                group.setUpdateStaff(update.getUpdateStaff());
                for (int i = 0; i < staffGroupArray.length; i++) {
                    group.setGroupId(Long.parseLong(staffGroupArray[i]));//用户组ID
                    authRelManageSV.saveStaffGroupRel(group);
                }
            }
        }
        
        try {
            authStaffAdminMapper.updateByPrimaryKeySelective(admin);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "修改管理员失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"管理员"});
        }
    }

    @Override
    public void deleteAuthStaffAdminById(Long id) throws BusinessException {
        if(id==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaff update = new AuthStaff();
        update.setId(id);
        update.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG_INVALID);//设置“失效”
        try {
            authStaffSV.updateAuthStaffById(update);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "删除管理员失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"管理员|逻辑删除"});
        }
    }

    @Override
    public PageResponseDTO<AuthStaffAdminResDTO> listAuthStaffAdmin(AuthStaffAdminReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        PageResponseDTO<AuthStaffAdminResDTO> res = new PageResponseDTO<AuthStaffAdminResDTO>();
        AuthStaffAdminCriteria criteria = new AuthStaffAdminCriteria();
        AuthStaffAdminCriteria.Criteria sql = criteria.createCriteria();
        
        String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$"; 
        List<Long> staffRoleFilter = new ArrayList<Long>(); //过滤角色得到的用户id
        List<Long> staffGroupFilter = new ArrayList<Long>(); //过滤用户组得到的用户id
        List<Long> staffFilter = new ArrayList<Long>(); //用于查询的用户id
        if(StringUtil.isNotBlank(reqDto.getStaffRole())){//过滤角色
            boolean isnum = reqDto.getStaffRole().matches(regex);
            if(!isnum) throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"角色参数需为角色id"});
            AuthStaff2Role staffRole = new AuthStaff2Role();
            staffRole.setRoleId(Long.valueOf(reqDto.getStaffRole()));
            staffRole.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);
            staffRole.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);
            List<AuthStaff2Role> listr = authRelManageSV.listStaffRoleRel(staffRole);
            if(CollectionUtils.isNotEmpty(listr)){
                for (AuthStaff2Role authStaff2Role : listr) {
                    staffRoleFilter.add(authStaff2Role.getStaffId());
                }
                staffFilter = staffRoleFilter;
            }else{// 无结果，则设置用户过滤缺省（此处为用户“0”）以筛选返回结果
                if(StringUtil.isBlank(reqDto.getStaffGroup())){
                    staffFilter.add(0L);
                }
            }
        }
        if(StringUtil.isNotBlank(reqDto.getStaffGroup())){//过滤用户组
            boolean isnum = reqDto.getStaffGroup().matches(regex);
            if(!isnum) throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"用户组参数需为用户组id"});
            AuthStaff2Group staffGroup = new AuthStaff2Group();
            staffGroup.setGroupId(Long.valueOf(reqDto.getStaffGroup()));
            staffGroup.setStaffClass(reqDto.getStaffClass());
            List<AuthStaff2Group> listg = authRelManageSV.listStaffGroupRel(staffGroup);
            if(CollectionUtils.isNotEmpty(listg)){
                for (AuthStaff2Group authStaff2Group : listg) {
                    staffGroupFilter.add(authStaff2Group.getStaffId());
                }
                staffFilter = staffGroupFilter;
            }else{// 无结果，则设置用户过滤缺省（此处为用户“0”）以筛选返回结果
                if(StringUtil.isBlank(reqDto.getStaffRole())){
                    staffFilter.add(0L);
                }
            }
            
        }
        
        if(StringUtil.isNotBlank(reqDto.getStaffRole())&&StringUtil.isNotBlank(reqDto.getStaffGroup())){
            staffRoleFilter.retainAll(staffGroupFilter);
            staffFilter = staffRoleFilter;
            if(CollectionUtils.isEmpty(staffFilter)){//角色与用户组双重条件查不到关联用户，return 空集
                staffFilter.add(0L);
            }
        }
        if(CollectionUtils.isNotEmpty(staffFilter)){
            sql.andIdIn(staffFilter);
        }
        //查询条件：登录名
        if(StringUtil.isNotBlank(reqDto.getStaffCode())){
            sql.andStaffCodeLike("%" + reqDto.getStaffCode() + "%");
        }
        //查询条件：手机号码
        if(StringUtil.isNotBlank(reqDto.getSerialNumber())){
            sql.andSerialNumberLike("%" + reqDto.getSerialNumber() + "%");
        }
        //查询条件：邮箱
        if(StringUtil.isNotBlank(reqDto.getStaffEmail())){
            sql.andStaffEmailLike("%" + reqDto.getStaffEmail() + "%");
        }
        //查询条件：名称
        if(StringUtil.isNotBlank(reqDto.getStaffName())){
            sql.andStaffNameLike("%" + reqDto.getStaffName() + "%");
        }
        //查询条件：昵称
        if(StringUtil.isNotBlank(reqDto.getAliasName())){
            sql.andAliasNameLike("%" + reqDto.getAliasName() + "%");
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        criteria.setLimitClauseStart(reqDto.getStartRowIndex());
        criteria.setLimitClauseCount(reqDto.getPageSize());
        criteria.setOrderByClause(" create_time desc");
        
        res = super.queryByPagination(reqDto, criteria, true, new PaginationCallback<AuthStaffAdmin, AuthStaffAdminResDTO>() {

            @Override
            public List<AuthStaffAdmin> queryDB(BaseCriteria arg0) {
                return authStaffAdminMapper.selectByExample((AuthStaffAdminCriteria) arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return authStaffAdminMapper.countByExample((AuthStaffAdminCriteria) arg0);
            }
            @Override
            public List<Comparator<AuthStaffAdmin>> defineComparators() {
                List<Comparator<AuthStaffAdmin>> ls=new ArrayList<Comparator<AuthStaffAdmin>>();
                ls.add(new Comparator<AuthStaffAdmin>(){

                    @Override
                    public int compare(AuthStaffAdmin o1, AuthStaffAdmin o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }

            @Override
            public AuthStaffAdminResDTO warpReturnObject(AuthStaffAdmin arg0) {
                AuthStaffAdminResDTO dto = new AuthStaffAdminResDTO();
                ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
                AuthStaffResDTO staff = authStaffSV.findAuthStaffById(arg0.getId());
                dto.setStaffFlag(staff.getStaffFlag());
                //1.补充用户权限关系
                //1.1 用户组
                StringBuffer sbGroup = new StringBuffer();
                StringBuffer sbGroupName = new StringBuffer();
                AuthStaff2Group staffGroupRel = new AuthStaff2Group();
                staffGroupRel.setStaffId(dto.getId());
                staffGroupRel.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);
                List<AuthStaff2Group> listg = authRelManageSV.listStaffGroupRel(staffGroupRel);
                if(CollectionUtils.isNotEmpty(listg)){
                    for (AuthStaff2Group authStaff2Group : listg) {
                        sbGroup.append(",").append(authStaff2Group.getGroupId());
                        AuthStaffGroupResDTO findg = staffGroupManageSV.findStaffGroupById(authStaff2Group.getGroupId());
                        sbGroupName.append(",").append(findg!=null&&StringUtil.isNotBlank(findg.getGroupName())?findg.getGroupName():"未命名用户组");
                    }
                    dto.setStaffGroup(sbGroup.substring(1));
                    dto.setStaffGroupName(sbGroupName.substring(1));
                }
                
                //1.2角色
                StringBuffer sbRole = new StringBuffer(); 
                StringBuffer sbRoleName = new StringBuffer(); 
                AuthStaff2Role staffRoleRel = new AuthStaff2Role();
                staffRoleRel.setStaffId(dto.getId());
                staffRoleRel.setStaffClass(StaffConstants.authStaff.STAFF_CLASS_M);
                staffRoleRel.setSysCode(StaffConstants.PublicParam.SYS_CODE_MANAGE);
                List<AuthStaff2Role> listr = authRelManageSV.listStaffRoleRel(staffRoleRel);
                if(CollectionUtils.isNotEmpty(listr)){
                    for (AuthStaff2Role authStaff2Role : listr) {
                        sbRole.append(",").append(authStaff2Role.getRoleId());
                        AuthRoleResDTO findr = roleManageSV.findAuthRoleById(authStaff2Role.getRoleId());
                        sbRoleName.append(",").append(findr!=null&&StringUtil.isNotBlank(findr.getRoleName())?findr.getRoleName():"未命名角色");
                    }
                    dto.setStaffRole(sbRole.substring(1));
                    dto.setStaffRoleName(sbRoleName.substring(1));
                }
                
                return dto;
            }
        });
        
        return res;
    }

    @Override
    public AuthStaffAdmin findAuthStaffAdminByCode(String staffCode) throws BusinessException {
        if(StringUtil.isBlank(staffCode)){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthAdminCIDXCriteria example = new AuthAdminCIDXCriteria();
        example.createCriteria().andStaffCodeEqualTo(staffCode);
        List<AuthAdminCIDX> list = authAdminCIDXMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            return null;
        }else{
            return findAuthStaffAdminById(list.get(0).getStaffId());
        }
    }

    @Override
    public void updateAuthStaffAdminFlag(AuthStaffAdminReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null||StringUtil.isBlank(reqDto.getStaffFlag())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaff update = new AuthStaff();
        update.setId(reqDto.getId());
        update.setStaffFlag(reqDto.getStaffFlag());
        update.setUpdateTime(DateUtil.getSysDate());
        try {
            authStaffSV.updateAuthStaffById(update);
        } catch (Exception e) {
            LogUtil.error(MODULE, "变更管理员帐号状态失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"变更管理员帐号状态"});
        }
    }
    
}

