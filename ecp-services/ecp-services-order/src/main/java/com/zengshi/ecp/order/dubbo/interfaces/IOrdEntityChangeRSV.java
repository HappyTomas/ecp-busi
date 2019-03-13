package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdEntityChangeRSV {
   
    /** 
     * queryEntityChg:实体编号变更查询. <br/> 
     * @author cbl 
     * @param rEntityInputRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowEntityChgResponse> queryEntityChg(RShowEntityChgRequest rShowEntityChgRequest) throws BusinessException;
    
    /** 
     * entityCodeChange:实体编号单个变更. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public RShowImportChgInfoResponse entityCodeChange(REntityCodeChgRequest rEntityCodeChgRequest) throws BusinessException;
 
    /** 
     * entityCodeChangeFromFile:通过文件修改实体编号. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void entityCodeChangeFromFile(REntityChgImportRequest rEntityChgImportRequest) throws BusinessException;
    
    
    /** 
     * queryImportStatus:导入批次状态查看. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportResponse> queryImportStatus(RShowImportRequest rShowImportRequest) throws BusinessException;
    
    /** 
     * queryFailImportInfo:失败的批次失败原因查看. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportChgInfoResponse> queryFailImportInfo(REntityImportRequest rEntityImportRequest) throws BusinessException;
    
    /** 
     * deleteFailImport:删除失败的批次. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void deleteFailImport(REntityImportRequest rEntityImportRequest) throws BusinessException;
}

