package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dao.model.OrdEntityImportGroup;
import com.zengshi.ecp.order.dubbo.dto.RFileImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityInputRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseSplitInfo;
import com.zengshi.ecp.order.dubbo.dto.SEntityChkInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月14日下午4:20:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
public interface IOrdEntityImportSV extends IGeneralSQLSV {
    
    /** 
     * saveOrdEntityImportList:通过实体编号列表保存实体编号中间表信息. <br/> 
     * @author cbl 
     * @param ordEntityImports 
     * @since JDK 1.6 
     */ 
    public void saveOrdEntityImportList(List<OrdEntityImport> ordEntityImports);

    /** 
     * findByOrderId:根据订单号查询实体编号信息列表. <br/> 
     * @author cbl 
     * @param orderId
     * @return 
     * @since JDK 1.6 
     */ 
    public List<OrdEntityImport> findByOrderId(SBaseSplitInfo sBaseSplitInfo);
    
    /** 
     * writeEntity:页面录入实体编号. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportInfoResponse> saveEntityInput(REntityInputRequest rEntityInputRequest);
    
    /** 
     * deleteBySubId:删除子订单下相关明细. <br/> 
     * @author cbl 
     * @param rEntityInputRequest 
     * @since JDK 1.6 
     */ 
    public void deleteByOrdSubId(REntityInputRequest rEntityInputRequest);
    /** 
     * entityImport:实体编号文件导入. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void saveEntityImport(RFileImportRequest rEntityAddImport);
    
    /** 
     * deleteByOrderId:根据订单号删除相关明细. <br/> 
     * @author cbl 
     * @param sBaseSplitInfo 
     * @since JDK 1.6 
     */ 
    public void deleteByOrderId(SBaseSplitInfo sBaseSplitInfo);
    
    /** 
     * showOrderSubEntity:查询子订单实体编号. <br/> 
     * @author cbl 
     * @param rEntityInRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowEntityResponse> queryOrderSubEntityPage(REntityInputRequest rEntityInputRequest);
    
    /** 
     * queryFailImportInfo:失败的批次失败原因查看. <br/> 
     * @author cbl 
     * @param rShowImportRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowImportInfoResponse> queryFailImportInfo(REntityImportRequest rEntityImportRequest);
    /** 
     * deleteFailImport:删除失败的批次. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void deleteFailImport(REntityImportRequest rEntityImportRequest);
    
    /** 
     * updateStatusByImportNo:根据批次号更新批次状态. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void updateStatusByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * queryByImportNo:根据批次号查询明细记录. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo 
     * @since JDK 1.6 
     */ 
    public List<OrdEntityImport> queryByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
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
     * countByEntityCode:根据实体编号统计中间表中该实体编号记录数. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountByEntityCode(SBaseAndImportInfo sBaseAndImportInfo);
    
    
    /** 
     * countByOrderSubId:根据子订单统计记录数. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryCountByOrderSubId(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * queryOrderSubByImportNo:查询订单和子订单信息. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo 
     * @since JDK 1.6 
     */ 
    public List<OrdEntityImportGroup> queryOrderSubByImportNo(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * updateRemark:更新实体编号的校验结果信息. <br/> 
     * @author cbl 
     * @param sEntityChkInfo 
     * @since JDK 1.6 
     */ 
    public void updateRemark(SEntityChkInfo sEntityChkInfo);
    
}
