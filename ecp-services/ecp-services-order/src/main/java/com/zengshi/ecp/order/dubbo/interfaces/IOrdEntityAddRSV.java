package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportResponse;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IOrdEntityAddRSV {
    /** 
     * entityInput:实体编号录入. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void entityInput(REntityInputRequest rEntityInputRequest) throws BusinessException;
    
    /** 
     * deleteSubEntity:删除子订单下所有的中单表实体编号. <br/> 
     * @author cbl 
     * @param rEntityInputRequest 
     * @since JDK 1.6 
     */ 
    public void deleteByOrdSubEntity(REntityInputRequest rEntityInputRequest) throws BusinessException;
    
    
    /** 
     * showOrderSubEntity:子订单实体编号查询. <br/> 
     * @author cbl 
     * @param rEntityInputRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowEntityResponse> queryOrderSubEntity(REntityInputRequest rEntityInputRequest) throws BusinessException;
    
    /** 
     * entityImport:实体编号导入. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void entityImport(RFileImportRequest rEntityAddImport) throws BusinessException;

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
    public PageResponseDTO<RShowImportInfoResponse> queryFailImportInfo(REntityImportRequest rEntityImportRequest) throws BusinessException;
    
    /** 
     * deleteFailImport:删除失败的批次. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void deleteFailImport(REntityImportRequest rEntityImportRequest) throws BusinessException;
}

