package com.zengshi.ecp.im.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImPhrasebookItemResDTO;
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
public interface IPhrasebookItemSV {
	
	/**
	 * 
	 * findPhrasebookItemByGroupId:(根据分组id查找常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<ImPhrasebookItemResDTO> findPhrasebookItemByGroupId(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryPhrasebookItemById:(根据id查找常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public ImPhrasebookItemResDTO queryPhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * savePhrasebookItem:(保存常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public Long savePhrasebookItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * deletePhrasebookItemById:(根据id删除常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public int deletePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * updatePhrasebookItemById:(通过id修改常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public int updatePhrasebookItemById(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * queryPhrasebookItem:(分页查询常用语). <br/> 
	 * 
	 * @author linby
	 * @param reqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public PageResponseDTO<ImPhrasebookItemResDTO> queryPhrasebookItem(ImPhrasebookItemReqDTO reqDTO) throws BusinessException;
	
}
