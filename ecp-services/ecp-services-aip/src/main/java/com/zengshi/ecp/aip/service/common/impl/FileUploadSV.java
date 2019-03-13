/** 
 * Project Name:ecp-aip-services 
 * File Name:FileUploadSV.java 
 * Package Name:com.zengshi.ecp.aip.service.common.impl 
 * Date:2015-10-21上午10:31:59 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.aip.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.aip.dao.mapper.common.FileUploadLogMapper;
import com.zengshi.ecp.aip.dao.model.FileUploadLog;
import com.zengshi.ecp.aip.dao.model.FileUploadLogCriteria;
import com.zengshi.ecp.aip.service.common.interfaces.IFileUploadSV;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-aip-services <br>
 * Description: <br>
 * Date:2015-10-21上午10:31:59  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service(value="fileUploadSV")
public class FileUploadSV implements IFileUploadSV {
    
    private static final String MODULE = FileUploadSV.class.getName();
    
    @Resource
    private FileUploadLogMapper fileUploadLogDAO;
    
    @Resource(name="seq_file_upload_log")
    private PaasSequence seqFileUploadLog;
    
    @Override
    public String insertUploadLog(String remoteUri, String localUri) {
        String log_id = "";
        try {
            FileUploadLog log = new FileUploadLog();
            String tmpId = seqFileUploadLog.nextValue()+"";
            log.setLogId(tmpId);
            log.setRemoteuri(remoteUri);
            log.setLocaluri(localUri);
            log.setCreateTime(DateUtil.getSysDate());
            log.setRemoteStart(DateUtil.getSysDate());
            this.fileUploadLogDAO.insertSelective(log);
            
            log_id = tmpId;
        } catch (Exception e) {
            LogUtil.error(MODULE, "插入上传日志表异常:"+e.getMessage());
            e.printStackTrace();
        }
        return log_id;
    }
    
    @Override
    public List<FileUploadLog> selectUploadingLog(String remoteUri) {
        FileUploadLogCriteria example;
        try {
            example = new FileUploadLogCriteria();
            FileUploadLogCriteria.Criteria cri = example.createCriteria();
            cri.andRemarkIsNull();
            cri.andUploadFinishIsNull();
            cri.andRemoteuriEqualTo(remoteUri);
            List<FileUploadLog> ls = this.fileUploadLogDAO.selectByExample(example);
            return ls;
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询" + remoteUri + "异常:"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public void deleteUploadedLogById(String logid) {
        try {
            this.fileUploadLogDAO.deleteByPrimaryKey(logid);
        } catch (Exception e) {
            System.out.println("删除日志：" + logid + "异常:");
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateUploadLog(String logId, String flag, String remark) {
        try {
            if (StringUtils.isBlank(logId))
                return;

            FileUploadLog log = new FileUploadLog();
            log.setLogId(logId);
            if ("1".equals(flag)) {
                log.setRemoteEnd(DateUtil.getSysDate());
                log.setLocalStart(DateUtil.getSysDate());
            } else if ("2".equals(flag)) {
                log.setLocalEnd(DateUtil.getSysDate());
            } else if ("3".equals(flag)) {
                log.setUploadFinish(DateUtil.getSysDate());
            } else if ("4".equals(flag)) {
                log.setRemark(remark);
            } else {
                return;
            }
            this.fileUploadLogDAO.updateByPrimaryKeySelective(log);
        } catch (Exception e) {
            System.out.println("更新上传日志表异常:");
            e.printStackTrace();
        }
    }
}

