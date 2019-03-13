package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdEntityChange;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.dto.SEntityChkInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月6日下午3:32:18 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public interface IOrdEntityChangeSV extends IGeneralSQLSV {
    /**
     * saveOrdEntityChange:(新增变更记录信息). <br/>
     * @author cbl
     * @param ordEntityChange
     * @since JDK 1.6
     */
    public void saveOrdEntityChange(OrdEntityChange ordEntityChange);
    
    /** 
     * queryFailImportInfo:失败的批次失败原因查看. <br/> 
     * @author cbl 
     * @param rShowImportRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportChgInfoResponse> queryFailImportInfo(REntityImportRequest rEntityImportRequest);
    /** 
     * deleteFailImport:删除失败的批次. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void deleteFailImport(REntityImportRequest rEntityImportRequest);
    
    /** 
     * countByImportNo:按批次统计实体编号记录数. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * countByImportNo:按批次统计实体编号唯一值记录数. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountByImportNoDist(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * updateStatusByImportNo:根据批次号更新批次状态. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * queryByImportNo:根据批次号查询明细信息. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdEntityChange> queryByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    /** 
     * updateStatusByImportNo:根据批次号更新批次状态. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void updateImportRemarkChg(SEntityChkInfo sEntityChkInfo);
    
    /** 
     * countEntityCodeAf:变更后实体编号唯一数量. <br/> 
     * @author cbl 
     * @param sEntityChkInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountEntityCodeAf(SBaseAndImportInfo sBaseAndImportInfo);
    /** 
     * countEntityCodeBf:变更前实体编号唯一数量. <br/> 
     * @author cbl 
     * @param sEntityChkInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountEntityCodeBf(SBaseAndImportInfo sBaseAndImportInfo);
   
}
