package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.dto.RExportFileReq;
import com.zengshi.ecp.order.dubbo.dto.RExportFileResp;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IOrdFileImportRSV {
	  /** 
     * save
     * @author cbl 
     * @param ordFileImport 
     * @since JDK 1.6 
     */ 
    public OrdFileImportDTO saveOrdFileImport(OrdFileImportDTO ordFileImportDTO);
    
    /**
     * 根据key更新记录的文件生成进度
     * @param ordFileImport
     * @throws BusinessException
     */
    public void updateById(OrdFileImportDTO ordFileImportDTO) throws  BusinessException;

    /**
     * 根据ID查询记录信息
     * @param OrdFileImportDTO
     * @return
     * @throws BusinessException
     */
    public RExportFileResp queryById(RExportFileReq rExportFileReq) throws BusinessException;

}
