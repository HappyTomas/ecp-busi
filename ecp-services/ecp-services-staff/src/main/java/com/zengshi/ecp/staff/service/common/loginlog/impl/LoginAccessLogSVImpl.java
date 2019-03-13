package com.zengshi.ecp.staff.service.common.loginlog.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.LoginAccessLogMapper;
import com.zengshi.ecp.staff.dao.model.LoginAccessLog;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.LoginAccessLogResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.loginlog.interfaces.ILoginAccessLogSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.db.sequence.Sequence;

@Service
public class LoginAccessLogSVImpl implements ILoginAccessLogSV {

    @Resource
    private LoginAccessLogMapper loginAccessLogMapper;
    
    @Resource(name="seq_login_access_log_id")
    private Sequence logid;
    
    private static final String MODULE = LoginAccessLogSVImpl.class.getName();
    
    @Override
    public int insert(LoginAccessLogReqDTO loginAccessLogReqDTO) throws BusinessException {
        
        LoginAccessLog record = new LoginAccessLog();
        ObjectCopyUtil.copyObjValue(loginAccessLogReqDTO, record, null, false);
        
        record.setId(logid.nextValue().toString());
        
        try {
            loginAccessLogMapper.insertSelective(record);
        } catch (Exception e) {
            // TODO: handle exception
           LogUtil.error(MODULE, "新增登录操作日志表异常", e);
           throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"查询用户：["+record.getStaffId()+"]登陆操作日志表出现异常"});
        }
        return 0;
    }

    @Override
    public LoginAccessLogResDTO find(Long staffId) throws BusinessException {
        return null;
    }

}

