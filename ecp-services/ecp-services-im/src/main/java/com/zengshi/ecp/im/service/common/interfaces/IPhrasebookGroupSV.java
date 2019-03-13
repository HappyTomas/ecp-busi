package com.zengshi.ecp.im.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookGroupResDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: <br>
 * 常用语短语
 * <br>
 * Date:2017年4月5日下午5:49:55  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public interface IPhrasebookGroupSV {
	/**
	 * 
	 * findPhrasebookGroupByShopId:(通过店铺查分组，含常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<ImPhrasebookGroupResDTO> findPhrasebookGroupByShopId(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;

	/**
	 * 
	 * queryPhrasebookGroupById:(根据id查分组信息). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public ImPhrasebookGroupResDTO queryPhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryPhrasebookGroup:(分页查询分组). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public PageResponseDTO<ImPhrasebookGroupResDTO> queryPhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * deletePhrasebookGroupById:(通过id删除). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public int deletePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * savePhrasebookGroup:(保存分组). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public Long savePhrasebookGroup(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * updatePhrasebookGroupById:(通过分组id修改分组信息). <br/> 
	 * 不允许修改分组类型
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public int updatePhrasebookGroupById(ImPhrasebookGroupReqDTO reqDTO) throws BusinessException;
	
}
