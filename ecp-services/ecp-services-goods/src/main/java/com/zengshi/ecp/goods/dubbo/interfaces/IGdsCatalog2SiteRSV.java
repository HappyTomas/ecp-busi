package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
/**
 * 
 * Title: 目录站点关联关系dubbo服务. <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-20下午10:58:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface IGdsCatalog2SiteRSV {
	
	/**
	 * 
	 * 创建站点与目录关联关系.<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO reqDTO.siteId为必传参数.
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void createCatalog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * 更新站点与目录关联关系.
	 * 
	 * @author liyong7
	 * @param reqDTO reqDTO.siteId为必传参数.
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void updateCatalog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * 分页查询关联关系.<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
    public PageResponseDTO<GdsCatalogRespDTO> queryCatalogPaging(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 判断是否已经存在有效关联关系.(该方法仅较验有效关联关系.)
     * 
     * @author liyong7
     * @param reqDTO siteId,catlogId必传.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryIsExist(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据站点ID获取与之关联的目录信息.
     * 
     * @author liyong7
     * @param reqDTO siteId必传,catalogStatus可选参数(0-失效,1-有效),如果有值则会根据该值过滤返回的目录信息.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsCatalogRespDTO> queryRelationBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 查询指定目录ID是否与站点关联. 
     * 
     * @author liyong7
     * @param reqDTO catlogId为必传参数.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryIsRelationWithSite(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;

    
    public List<GdsCatalog2SiteRespDTO> querySiteListByCataLogId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    
    /**
	 * 
	 * queryRelationByShopId:根据站点ID查询出关联关系. <br/> 
	 * 
	 * @author xiaosm3
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
    public PageResponseDTO<GdsCatalog2SiteRespDTO> queryPrdRelBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    /**
	 * 
	 * batchSaveGdsCatlog2Shop:批量保存目录站点关联关系. <br/> 
	 * 
	 * @author xiaosm3
	 * @param reqDTOLst
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void batchSaveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
	  /**
		 * 
		 * 根据站点ID删除相关目录
		 * 
		 * @author xiaosm3
		 * @param reqDTOLst
		 * @throws BusinessException 
		 * @since JDK 1.6
		 */
	public void deleteRelationBySiteId(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException;
}

