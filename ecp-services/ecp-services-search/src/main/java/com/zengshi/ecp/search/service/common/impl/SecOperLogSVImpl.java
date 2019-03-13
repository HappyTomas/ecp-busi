package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecOperLogMapper;
import com.zengshi.ecp.search.dao.model.SecOperLog;
import com.zengshi.ecp.search.dubbo.dto.SecOperLogReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecOperLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午7:44:02  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecOperLogSVImpl extends GeneralSQLSVImpl implements ISecOperLogSV{
    
    @Autowired
    private SecOperLogMapper secOperLogMapper;

    @Resource(name = "seq_sec_operlog")
    private Sequence seqSecOperLog;

    @Override
    public long saveSecOperLog(SecOperLogReqDTO secOperLogReqDTO) throws BusinessException {
        
        // DTO转BO,同名属性自动赋值
        SecOperLog secOperLog = new SecOperLog();
        BeanTransfermationUtils.dto2bo(secOperLog, secOperLogReqDTO);

        // 补值操作
        long id = this.seqSecOperLog.nextValue();
        secOperLog.setId(id);
        secOperLog.setStatus(SearchConstants.STATUS_VALID);
        
        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secOperLog.setCreateStaff(secOperLogReqDTO.getStaff().getId());
        secOperLog.setCreateTime(t);
        secOperLog.setUpdateStaff(secOperLogReqDTO.getStaff().getId());
        secOperLog.setUpdateTime(t);

        this.secOperLogMapper.insert(secOperLog);

        return id;
        
    }

}

