package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecObjectBuildLogMapper;
import com.zengshi.ecp.search.dao.model.SecObjectBuildLog;
import com.zengshi.ecp.search.dubbo.dto.SecObjectBuildLogReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectBuildLogSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-search-server-xhs <br>
 * Description: <br>
 * Date:2016年1月20日下午7:43:58  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public class SecObjectBuildLogSVImpl extends GeneralSQLSVImpl implements ISecObjectBuildLogSV{
    
    @Autowired
    private SecObjectBuildLogMapper secObjectBuildLogMapper;

    @Resource(name = "seq_sec_objectbuildlog")
    private Sequence seqSecObjectBuildLog;

    @Override
    public long saveSecObjectBuildLog(SecObjectBuildLogReqDTO secObjectBuildLogDTO)
            throws BusinessException {
        
        // DTO转BO,同名属性自动赋值
        SecObjectBuildLog secObjectBuildLog = new SecObjectBuildLog();
        BeanTransfermationUtils.dto2bo(secObjectBuildLog, secObjectBuildLogDTO);

        // 补值操作
        long id = this.seqSecObjectBuildLog.nextValue();
        secObjectBuildLog.setId(id);
        secObjectBuildLog.setStatus(SearchConstants.STATUS_VALID);
        
        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secObjectBuildLog.setCreateStaff(secObjectBuildLogDTO.getStaff().getId());
        secObjectBuildLog.setCreateTime(t);
        secObjectBuildLog.setUpdateStaff(secObjectBuildLogDTO.getStaff().getId());
        secObjectBuildLog.setUpdateTime(t);

        this.secObjectBuildLogMapper.insert(secObjectBuildLog);

        return id;
        
    }

}

