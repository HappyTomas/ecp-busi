package com.zengshi.ecp.goods.service.busi.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:赠品操作服务接口 <br>
 * Date:2015年8月30日下午3:27:07  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gongxq
 * @version  
 * @since JDK 1.6
 */
public interface IGdsGiftSV {
    /**
     * 
     * saveGdsGift:(新增保存赠品). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * delteGdsGift:(删除赠品). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void delteGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * editGdsGift:(编辑赠品). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void editGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsGift:(查询获取赠品列表). <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsGiftRespDTO> queryGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * querySingleGiftInfo:(获取单条赠品记录的信息。（用于编辑查看的时候获取的信息）. <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsGiftRespDTO querySingleGiftInfo(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * increaseGiftAmount:(赠品调增、调减。即增品变化量，对外提供). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void changeGiftAmount(GdsGiftReqDTO reqDTO) throws BusinessException;
    
}

