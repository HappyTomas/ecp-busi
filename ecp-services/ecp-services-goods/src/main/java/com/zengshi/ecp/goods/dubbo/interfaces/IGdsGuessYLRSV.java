package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: 猜你喜欢dubbo服务接口<br>
 * Date:2015年9月6日上午11:17:05  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsGuessYLRSV {
	 /**
     * 
     * 新增猜你喜欢!
     * 
     * @author linwb3
     * @param GdsGuessYL
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void saveGdsGuessYL(GdsGuessYLReqDTO reqDTO) throws BusinessException;
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
    public Boolean queryExist(GdsGuessYLReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据ID删除猜你喜欢记录。（该删除操作为伪删除，即只将状态变更成失效状态）<br/>
     * 
     * @author linwb3
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Integer deleteGdsGuessYL(GdsGuessYLReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 批量删除猜你喜欢。(即:将选定的推荐信息的状态改为失效状态。)
     * 
     * @author linwb3
     * @param ids
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Integer executeBatchDeleteGdsGuessYL(GdsGuessYLReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询，按照SORT_NO自然排序，创建时间降序,<br/>
     * <font color='red'>注意：该方法根据单品名称进行查询的逻辑后续需要补充。</font><br/>
     * @author linwb3
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsGuessYLRespDTO> queryGdsGuessYLRespDTOPaging(
            GdsGuessYLReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据主键ID查询猜你喜欢.<br/>
     * 
     * @author linwb3
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsGuessYLRespDTO queryGdsGuessYLByPK(GdsGuessYLReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * editGdsGuessYL:(编辑猜你喜欢). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return 
     * @since JDK 1.6
     */
    public Integer editGdsGuessYL(GdsGuessYLReqDTO reqDTO);
    /**
     * 
     * queryGdsGuessForHomePage:(首页猜你喜欢列表). <br/> 
     * 
     * @author zjh 
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsGuessHomePageRespDTO queryGdsGuessForHomePage(GdsGuessYLReqDTO dto)
            throws BusinessException;
}

