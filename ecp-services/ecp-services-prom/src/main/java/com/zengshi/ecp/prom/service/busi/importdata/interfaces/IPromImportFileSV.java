package com.zengshi.ecp.prom.service.busi.importdata.interfaces;

import com.zengshi.ecp.prom.dao.model.PromImportFile;
import com.zengshi.ecp.prom.dubbo.dto.PromImportFileReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromImportFileSV extends IGeneralSQLSV{
    /**
     * 导入中间表保存
     * @param promImportFileReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(PromImportFileReqDTO promImportFileReqDTO) throws BusinessException;
    /**
     * 查询文件
     * @param promImportFileReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public PromImportFile query(PromImportFileReqDTO promImportFileReqDTO) throws BusinessException;
}
