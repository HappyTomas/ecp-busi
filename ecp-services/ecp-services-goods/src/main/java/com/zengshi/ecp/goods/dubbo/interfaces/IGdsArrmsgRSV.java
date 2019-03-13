package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 到货通知dubbo服务. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-21上午10:05:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface IGdsArrmsgRSV {
	
	/**
     * 
     * saveGdsArrmsg:(新增保存到货通知). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * deleteGdsArrmsg:(删除到货通知（只做逻辑删除）). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * sendNotice:(发送到货通知（一键、批量或单条发送到货通知）).<b>该方法SV暂未实现.</b> <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Deprecated
    public void sendNotice(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsArrmsg:(获取到货通知列表). <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsg(GdsArrmsgReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 用户维度查询到货通知。<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsArrmsgRespDTO> queryGdsArrmsgFromStaff(GdsArrmsgReqDTO reqDTO) throws BusinessException; 

}

