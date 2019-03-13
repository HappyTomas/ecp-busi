package com.zengshi.ecp.staff.service.common.loginlog.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.LoginLogInfoMapper;
import com.zengshi.ecp.staff.dao.model.LoginLogInfo;
import com.zengshi.ecp.staff.dao.model.LoginLogInfoCriteria;
import com.zengshi.ecp.staff.dao.model.LoginLogInfoCriteria.Criteria;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginLogInfoResDTO;
import com.zengshi.ecp.staff.dubbo.impl.AuthAdminRSVImpl;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.staff.service.common.loginlog.interfaces.ILoginLogSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

@Service
public class LoginLogSVImpl implements ILoginLogSV {

    @Resource
    private LoginLogInfoMapper loginLogInfoMapper;
    
    @Resource(name="seq_login_log_id")
    private Sequence logid;
    
    private static final String MODULE = LoginLogSVImpl.class.getName();
    
    @Override
    public int insert(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException {
        LoginLogInfo record = new LoginLogInfo();
        ObjectCopyUtil.copyObjValue(loginLogInfoReqDTO, record, null, false);
        
        record.setId(logid.nextValue().toString());
        
        try {
            loginLogInfoMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
           LogUtil.error(MODULE, "登录日志错误", e);
           throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"查询用户：["+record.getStaffId()+"]登陆日志表出现异常"});
        }
        return 0;
    }

    @Override
    public LoginLogInfoResDTO find(Long staffId) throws BusinessException {
        return null;
    }

    @Override
    public int update(LoginLogInfoReqDTO loginLogInfoReqDTO) throws BusinessException {
        
        LoginLogInfo record = new LoginLogInfo();
        
        ObjectCopyUtil.copyObjValue(loginLogInfoReqDTO, record, null, false);
        
        LoginLogInfoCriteria criteria = new LoginLogInfoCriteria();
        Criteria sql =  criteria.createCriteria();
        if(loginLogInfoReqDTO.getStaffId() != null)
        {
            sql.andStaffIdEqualTo(loginLogInfoReqDTO.getStaffId());
        }
        if(StringUtil.isNotBlank(loginLogInfoReqDTO.getSessionId()))
        {
            sql.andSessionIdEqualTo(loginLogInfoReqDTO.getSessionId());
        }
        try {
            loginLogInfoMapper.updateByExampleSelective(record, criteria);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"更新用户：["+record.getStaffId()+"]登陆日志表出现异常"});
        }
        return 0;
    }

}

