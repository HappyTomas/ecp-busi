/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsCatalogRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015-9-21上午9:25:05 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: 目录Dubbo服务. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-21上午9:25:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsCatalogRSV {
	
	/**
	 * 
	 * 保存目录信息.<br/> 
	 * 
	 * @author liyong7
	 * @param record
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
    public void saveGdsCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询.<br/> 
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatalogRespDTO> queryGdsCatalogRespDTOPaging(GdsCatalogReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 编辑目录.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void editGdsCatalog(GdsCatalogReqDTO reqDTO)throws BusinessException;
    /**
     * 查询非失效状态的目录是否已经存在.
     * 
     * 
     * @author liyong7
     * @param reqDTO 可以传入CatalogName与CatalogCode进行比对.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryExist(GdsCatalogReqDTO reqDTO)throws BusinessException;
    
    /**
     * 更新为默认目录.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateDefaultCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 批量审核. 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchAudit(GdsCatalogReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 批量删除.<br/> 
     * 
     * @author liyong7
     * @param reqDTO reqDTO.ids不允许为空.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchDelete(GdsCatalogReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 批量启用.<br/> 
     * 
     * @author liyong7
     * @param reqDTO reqDTO.ids不允许为空.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchEnable(GdsCatalogReqDTO reqDTO)throws BusinessException;
   /**
    * 
    *  清除默认目录设置.<br/>
    * 
    * @author liyong7
    * @param reqDTO
    * @throws BusinessException 
    * @since JDK 1.6
    */
    public void executeCleanDefaulCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException;
    /**
     *     
     * 查询是否存在默认目录.(该方法查询出非有效状态的默认目录)
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public  boolean queryExistDefaultCatalog(GdsCatalogReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据主键查询目录.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCatalogRespDTO queryGdsCatalogByPK(GdsCatalogReqDTO reqDTO)throws BusinessException;

    /**
     * 
     * queryDefaultCatalog:查询默认目录. <br/> 
     * 
     * @author liyong7
     * @param baseInfo
     * @return 如果没有默认目录则返回空.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCatalogRespDTO queryDefaultCatalog(BaseInfo baseInfo) throws BusinessException; 
    
}

