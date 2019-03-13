package com.zengshi.ecp.order.service.busi.interfaces;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IOrdFileImportSV extends IGeneralSQLSV {
    /** 
     * saveOrdDeliveryDetails:保存OrdFileImport对象数据. <br/> 
     * @author cbl 
     * @param ordFileImport 
     * @since JDK 1.6 
     */ 
    public OrdFileImport saveOrdFileImport(OrdFileImport ordFileImport);

//    public List<OrdFileImport> findByShopId(String shopId);
    
    /** 
     * updateStatusByImportNo:根据批次号更新批次状态. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * queryImportStatus:变更导入批次状态查看. <br/> 
     * @author cbl 
     * @param rShowImportRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportResponse> queryChangeImportStatus(RShowImportRequest rShowImportRequest);
    /** 
     * queryAddImportStatus:新增导入批次状态查看. <br/> 
     * @author cbl 
     * @param rShowImportRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportResponse> queryAddImportStatus(RShowImportRequest rShowImportRequest);

    /**
     * 根据ID查询记录信息
     * @param rExportFileReq
     * @return
     * @throws BusinessException
     */
    public RExportFileResp queryById(RExportFileReq rExportFileReq) throws BusinessException;

    /**
     * 根据key更新记录的文件生成进度
     * @param ordFileImport
     * @throws BusinessException
     */
    public void updateById(OrdFileImport ordFileImport) throws  BusinessException;

    /**
     * 根据key更新记录的文件生成进度
     * @param ordFileImport
     * @throws BusinessException
     */
    public void updateByIdSelective(OrdFileImport ordFileImport) throws  BusinessException;

	public OrdFileImportDTO insertOrdFileImport(OrdFileImportDTO ordFileImportDTO);

	public void updateOrdFileImportById(OrdFileImportDTO ordFileImportDTO);
}
