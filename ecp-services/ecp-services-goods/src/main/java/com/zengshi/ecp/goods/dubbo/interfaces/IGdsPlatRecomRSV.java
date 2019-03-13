package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:平台推荐服务接口 <br>
 * Date:2015年9月6日上午11:17:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsPlatRecomRSV {

	/**
	 * 
	 * 新增平台推荐!
	 * 
	 * @author linwb3
	 * @param gdsPlatRecom
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void saveGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 新增平台推荐!
	 * 
	 * @author linwb3
	 * @param gdsPlatRecom
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public void editGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 根据分类ID，商品ID，单品ID以及状态检查记录是否已经存在。
	 * 
	 * @author linwb3
	 * @param catgCode
	 * @param gdsId
	 * @param skuId
	 * @param status
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Boolean queryExist(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 根据ID删除平台推荐记录。（该删除操作为伪删除，即只将状态变更成失效状态）<br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Integer deleteGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 批量删除平台推荐。(即:将选定的推荐信息的状态改为失效状态。)
	 * 
	 * @author linwb3
	 * @param ids
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Integer executeBatchDeleteGdsPlatRecom(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;

	/**
	 * 
	 * 分页查询，按照SORT_NO自然排序，创建时间降序,<br/>
	 * <font color='red'>注意：该方法根据单品名称进行查询的逻辑后续需要补充。</font><br/>
	 * 
	 * @author linwb3
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsPlatRecomRespDTO> queryGdsPlatRecomRespDTOPaging(
			GdsPlatRecomReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * 根据主键ID查询平台推荐.<br/>
	 * 
	 * @author linwb3
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(GdsPlatRecomReqDTO reqDTO)
			throws BusinessException;
	
	/**
	 * 
	 * 根据分类编码查询推荐单品.
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public List<GdsInfoDetailRespDTO> queryGdsInfoDetailByCatgCode(GdsPlatRecomReqDTO reqDTO) throws BusinessException;
	
	

}
