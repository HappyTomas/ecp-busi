/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsEvaluationSV.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces 
 * Date:2015年8月24日上午11:00:19 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReplyRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description:评价回复接口 <br>
 * Date:2015年8月24日上午11:00:19  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsEvalReplySV extends IGeneralSQLSV {
    
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
     * @param evalId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public long executeCountReplyByEvalPK(Long evalId, String... status) throws BusinessException;
    /**
     * 
     * 是否存在回复。<br/>
     * 
     * @author liyong7
     * @param id
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public boolean queryExistReply(Long evalId, String... status)throws BusinessException;
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
	public boolean queryExistSubReply(Long id, String... status)throws BusinessException;
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
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public PageResponseDTO<GdsEvalReplyRespDTO> queryGdsEvalReplyRespDTOPaging(GdsEvalReplyReqDTO dto) throws BusinessException;
    /**
     * 
     * 根据回复ID查询子回复。 
     * 
     * @author liyong7
     * @param replyId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public List<GdsEvalReplyRespDTO> querySubReplyByReplyId(Long replyId, String status)throws BusinessException;
    
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

