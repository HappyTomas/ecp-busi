package com.zengshi.ecp.staff.service.busi.manage.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.PasswordUtils;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.manual.AuthStaffCIDXExtMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAuthStaffManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 登录用户管理类<br>
 * Date:2015年9月3日下午7:33:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AuthStaffManageSVImpl extends GeneralSQLSVImpl implements IAuthStaffManageSV {
    
    private static final String MODULE = AuthStaffManageSVImpl.class.getName();

    @Resource
    private AuthStaffMapper authStaffMapper;    //登录用户
    
    @Resource
    private AuthStaffCIDXExtMapper authCIDXExtMapper;
    
    @Resource(name = "seq_staff_info_id")
    private PaasSequence seqStaffInfo;  //用户sequence
    
    @Resource
    private PasswordUtils passwordUtils;
    
    @Override
    public long saveAuthStaff(AuthStaff save) throws BusinessException {
        //TODO 用户名重复验证
        save.setId(seqStaffInfo.nextValue());
        save.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(StringUtil.isBlank(save.getStaffFlag())){//缺省“有效”
            save.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
        }
        try {
            authStaffMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据插入失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        return save.getId();
    }

    @Override
    public void deleteAuthStaffById(AuthStaff delete, Boolean isPhy) throws BusinessException {
        if(isPhy!=null&&isPhy){//物理删除
            //1.删除表
            try {
            	AuthStaff auth = authStaffMapper.selectByPrimaryKey(delete.getId());
                authStaffMapper.deleteByPrimaryKey(delete.getId());
                //删除对应staffCode的索引表
                if (auth != null && StringUtil.isNotBlank(auth.getStaffCode())) {
                	AuthStaffCIDX cidx = new AuthStaffCIDX();
                    cidx.setStaffCode(auth.getStaffCode());
                    authCIDXExtMapper.deleteByStaffCode(cidx);
                }
            } catch (DataAccessException e) {
                LogUtil.error(MODULE, "物理删除异常", e);
                throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "物理删除失败" });
            }
            //2.删除关系 TODO
        }else{//逻辑删除
            AuthStaff authStaff = new AuthStaff();
            authStaff.setId(delete.getId());
            authStaff.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG_INVALID);
            
            try {
                updateAuthStaffById(authStaff);
            } catch (Exception e) {
                LogUtil.error(MODULE, "逻辑删除异常", e);
                throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "逻辑删除失败" });
            }
        }
    }

    @Override
    public void updateAuthStaffById(AuthStaff update) throws BusinessException {
        try {
            authStaffMapper.updateByPrimaryKeySelective(update);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "更新异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{ "用户更新失败" });
        }
    }

    @Override
    public AuthStaffResDTO findAuthStaffById(Long staffId) throws BusinessException {
        if(staffId==null || staffId == 0L){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthStaff as = new AuthStaff();
        try {
            as = authStaffMapper.selectByPrimaryKey(staffId);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        AuthStaffResDTO res = new AuthStaffResDTO();
        ObjectCopyUtil.copyObjValue(as, res, null, false);
        return res;
    }

    @Override
    public PageResponseDTO<AuthStaffResDTO> listAuthStaff(AuthStaffReqDTO reqDto)
            throws BusinessException {
        PageResponseDTO<AuthStaffResDTO> res = new PageResponseDTO<AuthStaffResDTO>();
        
        AuthStaffCriteria asCriteria = new AuthStaffCriteria();
        AuthStaffCriteria.Criteria sql = asCriteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getStaffFlag())){
            sql.andStaffFlagEqualTo(reqDto.getStaffFlag());
        }else{//缺省查询“有效”
            sql.andStaffFlagEqualTo(StaffConstants.authStaff.STAFF_FLAG);
        }
        if(StringUtil.isNotBlank(reqDto.getCreateFrom())){
            sql.andCreateFromEqualTo(reqDto.getCreateFrom());
        }
        if(StringUtil.isNotBlank(reqDto.getStaffCode())){
            sql.andStaffCodeEqualTo(reqDto.getStaffCode());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        
        res = super.queryByPagination(reqDto, asCriteria, true, new PaginationCallback<AuthStaff, AuthStaffResDTO>() {

            @Override
            public List<AuthStaff> queryDB(BaseCriteria criteria) {
                return authStaffMapper.selectByExample((AuthStaffCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return authStaffMapper.countByExample((AuthStaffCriteria) criteria);
            }

            @Override
            public AuthStaffResDTO warpReturnObject(AuthStaff t) {
                AuthStaffResDTO dto = new AuthStaffResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                
                return dto;
            }
            
        });
        return res;
    }

    @Override
    public void deleteAuthStaffById(AuthStaff delete) throws BusinessException {
        try {
            deleteAuthStaffById(delete,null);
        } catch (Exception e) {
            LogUtil.error(MODULE, "逻辑删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"逻辑删除"});
        }
    }

    @Override
    public void updatePwdById(AuthStaffReqDTO req) throws BusinessException {
        //查询用户信息
        AuthStaffResDTO staff = this.findAuthStaffById(req.getId());
        passwordUtils.setEncryAlgorithm("MD5-SALT");
        boolean pwdCheck = passwordUtils.checkPassword(req.getStaffPwdOld(), staff.getStaffPasswd());
        //校验原密码是否正确
        if (!pwdCheck) {
            throw new BusinessException(StaffConstants.authStaff.OLD_PWD_ERROR, new String[]{""});
        }
        try {
            AuthStaff authStaff = new AuthStaff();
            authStaff.setId(req.getId());
            authStaff.setUpdateTime(DateUtil.getSysDate());//更新时间
            authStaff.setUpdateStaff(req.getUpdateStaff());//更新人
            authStaff.setStaffPasswd(passwordUtils.encry(req.getStaffPasswd()));//新密码-加密
            this.updateAuthStaffById(authStaff);
        } catch (Exception e) {
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{""});
        }
        
    }
    
}

