/** 
 * Project Name:ecp-services-goods-server 
 * File Name:IGdsEvalReplyRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015-9-23下午3:40:45 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2015-9-23下午3:40:45  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsEvalReplyRSV {
	
	 /**
     * 
     * 添加评价回复。
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsEvalReplyRespDTO addEvalReply(GdsEvalReplyReqDTO reqDTO) throws BusinessException;
    
    
    /**
     * 
     * 根据评价ID统计回复总数。<br/>
     * 
     * @author liyong7
     * @param reqDTO evalId必传
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public long executeCountReplyByEvalPK(GdsEvalReplyReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 是否存在回复。<br/>
     * 
     * @author liyong7
     * @param reqDTO evalId必传
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public boolean queryExistReply(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 是否存在子回复。
     * 
     * @author liyong7
     * @param id
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public boolean queryExistSubReply(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据评价ID删除回复。
     * 
     * @author liyong7
     * @param reqDTO reqDTO.evalId不允许为空。
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public int deleteReplyByEvalId(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据回复主键删除评价回复。 
     * 
     * @author liyong7
     * @param reqDTO id 评价回复主键ID。isRecursive 是否递归删除子回复。
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteEvalReplyByPK(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 分页查询。
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsEvalReplyRespDTO> queryGdsEvalReplyRespDTOPaging(GdsEvalReplyReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据回复ID查询子回复。 
     * 
     * @author liyong7
     * @param reqDTO id必传
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public List<GdsEvalReplyRespDTO> querySubReplyByReplyId(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 删除子回复.<br/>
     * 
     * @author liyong7
     * @param reqDTO id必传.
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void deleteSubReplyByReplyId(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
	/**
	 * 
	 * 根据主键查询回复。 
	 * 
	 * @author liyong7
	 * @param reqDTO reqDTO.id必传参数；
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public GdsEvalReplyRespDTO queryReplyByPK(GdsEvalReplyReqDTO reqDTO)throws BusinessException;
    

}

