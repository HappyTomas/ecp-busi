package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsEvalRSV {

	/**
	 * 
	 * 保存评价. <br/>
	 * 
	 * @author liyong7
	 * @param reqDTO 
	 * <pre>
	 *    reqDTO里  content,gdsId,orderId,orderSubId,shopId,staffId为必传参数.
	 *    其中content为mongoDB返回的ID,请将原始的评价内容保存至detail,并设用mongoDB工具类存储文件后
	 *    对content进行赋值.
	 * </pre>
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	GdsEvalRespDTO saveGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException;
    
   /**
    * 
    * 删除评价.<br/>
    * 
    * @author liyong7
    * @param reqDTO
    * @throws BusinessException 
    * @since JDK 1.6
    */
    void deleteGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 编辑评价. <br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void ediaGdsEval(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 分页查询. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsEvalRespDTO> queryPaging(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键查询评价详情.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsEvalRespDTO queryGdsEvalByPK(GdsEvalReqDTO reqDTO) throws BusinessException;
    
    
    /**
     * 
     * 查询是否已经评价.
     * 
     * @author liyong7
     * @param reqDTO orderId,orderSubId必传
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryHaveEval(GdsEvalReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 批量审核. 
     * 
     * @author liyong7
     * @param reqDTO reqDTO.auditStatus 必传,reqDTO.ids 不允许为空.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchAudit(GdsEvalReqDTO reqDTO) throws BusinessException;
	/**
	 * 
	 * queryGdsEvalRespDTOPagingForGdsDetail:(商品详情评价列表查询). <br/> 
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
     * queryPagingForStaff:(根据用户获取评价). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsEvalRespDTO> queryPagingForStaff(GdsEvalReqDTO reqDTO)
            throws BusinessException;
    
    /**
     * 
     * calCulateShopGoodEvalRate:(根据用户获取评价). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhangjh6 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Double calCulateShopGoodEvalRate(GdsEvalReqDTO dto) throws BusinessException ;
    
    
    /**
     * 
     * statisticEvalByPass:(获取待审核总数). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhangjh6 
     * @param dto
     * @return
     * @throws BusinessException
     */
    public Long statisticEvalByPass(GdsEvalReqDTO dto) throws BusinessException;
    
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
}

