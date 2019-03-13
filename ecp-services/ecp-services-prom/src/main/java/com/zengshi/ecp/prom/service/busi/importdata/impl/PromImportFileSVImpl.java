package com.zengshi.ecp.prom.service.busi.importdata.impl;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dao.mapper.busi.PromImportFileMapper;
import com.zengshi.ecp.prom.dao.model.PromImportFile;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromImportFileSVImpl extends GeneralSQLSVImpl implements IPromImportFileSV {

    private static final String MODULE = PromImportFileSVImpl.class.getName();
 
    @Resource
    private PromImportFileMapper promImportFileMapper;
    
    /**
     * 导入中间表保存
     * @param promImportFileReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(PromImportFileReqDTO promImportFileReqDTO) throws BusinessException{
        PromImportFile importFile=new PromImportFile();
        importFile.setCreateStaff(promImportFileReqDTO.getCreateStaff());
        importFile.setCreateTime(DateUtil.getSysDate());
        importFile.setFileId(promImportFileReqDTO.getFileId());
        importFile.setFileName(promImportFileReqDTO.getFileName());
        importFile.setImportDesc(promImportFileReqDTO.getImportDesc());
        importFile.setImportType(promImportFileReqDTO.getImportType());
        promImportFileMapper.insert(importFile);
    }
    /**
     * TODO 查询文件
     * @see com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportFileSV#query(com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO)
     * @param promImportFileReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromImportFile query(PromImportFileReqDTO promImportFileReqDTO) throws BusinessException{
        return promImportFileMapper.selectByPrimaryKey(promImportFileReqDTO.getFileId());
    }
}
