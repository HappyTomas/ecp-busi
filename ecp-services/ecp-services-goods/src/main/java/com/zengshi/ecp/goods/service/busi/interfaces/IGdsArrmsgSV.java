package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:到货通知服务接口 <br>
 * Date:2015年8月30日下午3:27:47  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gongxq
 * @version  
 * @since JDK 1.6
 */
public interface IGdsArrmsgSV {
    /**
     * 
     * saveGdsArrmsg:(新增保存到货通知). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * deleteGdsArrmsg:(删除到货通知（只做逻辑删除）). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void deleteGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * sendNotice:(发送到货通知（一键、批量或单条发送到货通知）). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void sendNotice(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsArrmsg:(从店铺维度获取到货通知列表). <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 从用户角度－获取到货通知列表。<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsgFromStaff(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    
    
}

