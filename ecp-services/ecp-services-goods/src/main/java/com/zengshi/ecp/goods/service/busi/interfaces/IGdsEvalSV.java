/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsEvalSV.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces 
 * Date:2015年8月24日上午9:46:00 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Project Name:ecp-services-goods <br>
 * Description:评价管理服务接口。 <br>
 * Date:2015年8月24日上午9:46:00  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsEvalSV extends IGeneralSQLSV {
	
	/**
	 * 评短短信通知码。
	 */
	public static final String SMS_CODE_GDS_EVAL_NOTICE = "gds.eval.notice";
    
    /**
     * 
     * 增加评价。<br/> 
     * 
     * @author liyong7
     * @param dto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsEvalRespDTO addGdsEval(GdsEvalReqDTO dto)throws BusinessException;
    /**
     * 
     * (店铺维度:即卖家维度)评价信息分页查询。
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPaging(GdsEvalReqDTO dto) throws BusinessException;
    
    /**
     * (店铺维度:即卖家维度)好评数。
     * @param dto
     * @return
     * @throws BusinessException
     */
    public Long countGoodEval(GdsEvalReqDTO dto) throws BusinessException;
    
    /**
     * (店铺维度:即卖家维度)中评数。
     * @param dto
     * @return
     * @throws BusinessException
     */
    public Long countMiddleEval(GdsEvalReqDTO dto) throws BusinessException;
    
    /**
     * (店铺维度:即卖家维度)差评数。
     * @param dto
     * @return
     * @throws BusinessException
     */
    public Long countBadEval(GdsEvalReqDTO dto) throws BusinessException;
    
    /**
     * 
     * (用户维度:即买家维度)评价信息分页查询。
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPagingByStaff(GdsEvalReqDTO dto) throws BusinessException;
    /**
     * 
     * 根据主键查询评价信息.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsEvalRespDTO queryGdsEvalByPK(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键删除回复信息.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteGdsEvalByPK(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 编辑评价。<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int editGdsEval(GdsEvalReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据主键查询评价.
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsEvalRespDTO queryGdsEvalByPK(Long id)throws BusinessException;
    /**
     * 
     * 批量审核. 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchAudit(GdsEvalReqDTO reqDTO)throws BusinessException;
    
    
    /**
     * 
     * batchAuditAccordanceWithOrder:批量审核并返回需要通过最终一致性事务与订单域同步的订单与子订单唯一元组列表对象. <br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsEvalReqDTO> batchAuditAccordanceWithOrder(GdsEvalReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 更新是否已回复字段.
     * 
     * @author liyong7
     * @param reqDTO  id主键必传,isReply必传.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateIsReplyByPK(GdsEvalReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 查询是否已经评价.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryHaveEval(GdsEvalReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * queryGdsEvalRespDTOPagingForGdsDetail:(商品详情评价列表). <br/> 
     * 
     * @author zjh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsEvalRespDTO> queryGdsEvalRespDTOPagingForGdsDetail(GdsEvalReqDTO dto)
            throws BusinessException;
    
    /**
     * 
     * isAuditSwitchOpen:是否启用评价审核  . <br/> 
     * 
     * @author liyong7
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean isAuditSwitchOpen() throws BusinessException;
    
    /**
     * 
     * isGdsEvalInvokeSms:是否评价调用短信发送服务. <br/> 
     * 
     * @author liyong7
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean isGdsEvalInvokeSms() throws BusinessException;
    
    /**
     * 
     * calCulateShopGoodEvalRate:计算好评率. <br/> 
     * 
     * @author zhangjh6
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Double calCulateShopGoodEvalRate(GdsEvalReqDTO dto)throws Exception;
    
    /**
     * 
     * statisticEvalByPass:汇总待审核评价总数. <br/> 
     * 
     * @author zhangjh6
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long statisticEvalByPass(GdsEvalReqDTO dto) throws Exception;
}

