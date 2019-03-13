/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsPlatRecomSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月20日上午11:17:41 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsPlatRecom;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 平台推荐服务接口 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月20日上午11:17:41 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsPlatRecomSV extends IGeneralSQLSV {
	/**
	 * 
	 * 新增平台推荐!
	 * 
	 * @author liyong7
	 * @param gdsPlatRecom
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsPlatRecom saveGdsPlatRecom(GdsPlatRecom gdsPlatRecom)
			throws BusinessException;

	/**
	 * 
	 * editGdsGuessYL:(编辑猜你喜欢). <br/>
	 * 
	 * @author linwb3
	 * @param gdsGuessYL
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public Integer editGdsPlatRecom(GdsPlatRecom gdsPlatRecom)
			throws BusinessException;

	/**
	 * 
	 * 根据分类ID，商品ID，单品ID以及状态检查记录是否已经存在。
	 * 
	 * @author liyong7
	 * @param catgCode
	 * @param gdsId
	 * @param skuId
	 * @param status
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public boolean queryExist(String catgCode, Long gdsId, Long skuId,
			String... status) throws BusinessException;

	/**
	 * 
	 * 根据ID删除平台推荐记录。（该删除操作为伪删除，即只将状态变更成失效状态）<br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public int deleteGdsPlatRecom(Long id, Long updateStaff)
			throws BusinessException;

	/**
	 * 
	 * 批量删除平台推荐。(即:将选定的推荐信息的状态改为失效状态。)
	 * 
	 * @author liyong7
	 * @param ids
	 * @param updateStaff
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public int executeBatchDeleteGdsPlatRecom(List<Long> ids, Long updateStaff)
			throws BusinessException;

	/**
	 * 
	 * 分页查询，按照SORT_NO自然排序，创建时间降序,<br/>
	 * <font color='red'>注意：该方法根据单品名称进行查询的逻辑后续需要补充。</font><br/>
	 * 
	 * @author liyong7
	 * @param dto
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public PageResponseDTO<GdsPlatRecomRespDTO> queryGdsPlatRecomRespDTOPaging(
			GdsPlatRecomReqDTO dto) throws BusinessException;

	/**
	 * 
	 * 根据主键ID查询平台推荐.<br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(Long id)
			throws BusinessException;

	/**
	 * 
	 * 根据主键结合是否有效状态条件查询平台推荐信息.<br/>
	 * 
	 * @author liyong7
	 * @param id
	 * @param isValid
	 * @return
	 * @throws BusinessException
	 * @since JDK 1.6
	 */
	public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(Long id, boolean isValid)
			throws BusinessException;
	/**
	 * 
	 * deletGdsPlatRecomByGdsId:(根据商品id删除平台分类推荐数据). <br/> 
	 * 
	 * @author zjh 
	 * @param gdsInfoReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	 public void deletGdsPlatRecomByGdsId(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException; 
}
