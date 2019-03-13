package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 商品积分扩展服务<br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月24日下午3:23:03  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsScoreExtSV {

    /**
     * 
     * addGdsScoreExt:(添加商品积分信息). <br/> 
     * 
     * @author linwb3
     * @param gdsScoreExtReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void addGdsScoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException;

    /**
     * 
     * editGdsScoreExt:(编辑商品积分信息). <br/> 
     * 
     * @author linwb3
     * @param gdsScoreExtReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void editGdsScoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException;

    /**
     * 
     * deleteGdsSoreExt:(删除商品积分信息). <br/> 
     * 
     * @author linwb3
     * @param gdsScoreExtReqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteGdsSoreExt(GdsScoreExtReqDTO gdsScoreExtReqDTO) throws BusinessException;

    /**
     * 
     * queryGdsScoreExtByGds:(查询商品积分信息). <br/> 
     * 
     * @author linwb3
     * @param gdsScoreExtReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsScoreExtRespDTO> queryGdsScoreExtByGds(GdsScoreExtReqDTO gdsScoreExtReqDTO)
            throws BusinessException;

    /**
     * 
     * queryGdsScoreExtById:(通过积分配置信息id获取积分信息). <br/> 
     * 
     * @author linwb3
     * @param gdsScoreExtReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsScoreExtRespDTO queryGdsScoreExtById(GdsScoreExtReqDTO gdsScoreExtReqDTO)
            throws BusinessException;
    
    /**
     * 
     * saveGdsScoreExtList:(批量保存商品积分信息). <br/> 
     * 
     * @author linwb3
     * @param extReqDTOs
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveGdsScoreExtList(List<GdsScoreExtReqDTO> extReqDTOs)throws BusinessException;
}
