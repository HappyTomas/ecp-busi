package com.zengshi.ecp.order.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.order.dao.model.OrdDeliveryEntity;
import com.zengshi.ecp.order.dubbo.dto.REntityChgImportRequest;
import com.zengshi.ecp.order.dubbo.dto.REntityCodeChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowEntityChgResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowImportChgInfoResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndBatchInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndImportInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月7日下午5:21:04 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 *
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public interface IOrdDeliveryEntitySV extends IGeneralSQLSV {
    
    /** 
     * saveOrdDeliveryEntityList:保存发货实体列表信息. <br/> 
     * @author cbl 
     * @param ordDeliveryEntitys 
     * @since JDK 1.6 
     */ 
    public void saveOrdDeliveryEntityList(List<OrdDeliveryEntity> ordDeliveryEntitys);

    /** 
     * updateEntityCodeChangeFromWeb:单个实体编号变更. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public RShowImportChgInfoResponse updateEntityCodeChangeFromWeb(REntityCodeChgRequest rEntityCodeChgRequest);
    
    /** 
     * updateEntityCodeChangeFromFile:文件导入实体编号变更. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public void updateEntityCodeChangeFromFile(REntityChgImportRequest rEntityChgImportRequest);
    
    
    /** 
     * queryEntityChgPage:实体编号变更查询. <br/> 
     * @author cbl 
     * @param rEntityInputRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowEntityChgResponse> queryEntityChgPage(RShowEntityChgRequest rShowEntityChgRequest);
    
    /** 
     * queryByEntityCode:根据实体编号查询记录数. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public Long queryByEntityCode(SBaseAndImportInfo sBaseAndImportInfo);
    
    /** 
     * queryBySubId:根据批次号获取子订单下的实体编号. <br/> 
     * @author cbl 
     * @param sBaseAndImportInfo
     * @return 
     * @since JDK 1.6 
     */ 
    public List<String> queryBySubId(SBaseAndBatchInfo sBaseAndBatchInfo);
}
