package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsGiftRSV {
    /**
     * 
     * saveGdsGift:(新增保存商品赠品). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * delteGdsGift:(删除商品赠品信息（只做逻辑删除）). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void delteGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * editGdsGift:(编辑商品赠品). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void editGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * queryGdsGift:(查询商品赠品列表信息). <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsGiftRespDTO> queryGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * querySingleGiftInfo:(获取单条赠品记录信息。用于编辑、查看). <br/> 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsGiftRespDTO querySingleGiftInfo(GdsGiftReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * changeGiftAmount:(赠品调增、调减。即增品变化量，对外提供). <br/> 
     * @author gxq 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void changeGiftAmount(GdsGiftReqDTO reqDTO) throws BusinessException;
}

