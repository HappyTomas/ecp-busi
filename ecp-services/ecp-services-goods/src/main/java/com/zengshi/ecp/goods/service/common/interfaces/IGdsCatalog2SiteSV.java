/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsCatalog2SiteSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015-10-20下午3:45:55 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-10-20下午3:45:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsCatalog2SiteSV extends IGeneralSQLSV{
	
	/**
	 * 
	 * 创建站点与目录关联关系.<br/>
	 * 
	 * @author liyong7
	 * @param reqDTO reqDTO.siteId(站点ID),reqDTO.validCatlogIds(目录ID序列)为必传参数.
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void createCatalog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * 更新站点与目录关联关系.
	 * 
	 * @author liyong7
	 * @param reqDTO reqDTO.siteId(站点ID),reqDTO.validCatlogIds(有效目录ID序列),reqDTO.invalidCatlogIds(失效目录ID序列)为必传参数.
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
	 * 判断是否已经存在有效关联关系.(该方法仅较验有效关联关系.)
	 * 
	 * @author liyong7
	 * @param siteId
	 * @param catlogId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
    public boolean queryIsExist(Long siteId, Long catlogId) throws BusinessException;
    /**
     * 
     * 根据站点ID获取与之关联的目录信息.
     * 
     * @author liyong7
     * @param reqDTO siteId必传,catalogStatus可选参数(1-有效状态,可引用GdsConstants.Commons.STATUS_VALID;0-失效状态,可引用GdsConstants.Commons.STATUS_INVALID)
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsCatalogRespDTO> queryRelationBySiteId(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 查询指定目录是否与站点关联. 
     * 
     * @author liyong7
     * @param reqDTO reqDTO.catlogId必传参数.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryIsRelationWithSite(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;

    
    public List<GdsCatalog2SiteRespDTO> querySiteListByCataLogId(
			GdsCatalog2SiteReqDTO reqDTO) throws Exception ;
    
    public PageResponseDTO<GdsCatalog2SiteRespDTO> queryPrdRelBySiteId(
    		GdsCatalog2SiteReqDTO reqDTO)throws Exception ;
    /**
     * 
     * 批量新增站点目录. 
     * 
     * @author xiaosm3
     * @param reqDTO r
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchSaveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
    /**
	 * 
	 * deleteGdsCatlog2Shop:根据条件删除目录站点关联关系. <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author xiaosm3
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public void deleteGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO) throws BusinessException;
	
	public void saveGdsCatlog2Site(GdsCatalog2SiteReqDTO reqDTO)
			throws BusinessException; 
}

