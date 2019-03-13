/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsGuessYLSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月20日上午11:17:41 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsGuessYL;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessHomePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Project Name:ecp-services-goods <br>
 * Description: 猜你喜欢操作接口<br>
 * Date:2015年8月20日上午11:17:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsGuessYLSV extends IGeneralSQLSV {
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
	public GdsGuessYL saveGdsGuessYL(GdsGuessYL GdsGuessYL) throws BusinessException;
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
    public boolean queryExist(String catgCode, Long gdsId, Long skuId, String... status) throws BusinessException;
    
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
    public int deleteGdsGuessYL(Long id, Long updateStaff) throws BusinessException;
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
    public int executeBatchDeleteGdsGuessYL(List<Long> ids, Long updateStaff) throws BusinessException;
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
            GdsGuessYLReqDTO dto) throws BusinessException;
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
    public GdsGuessYLRespDTO queryGdsGuessYLByPK(Long id) throws BusinessException;
    /**
     * 
     * 根据主键结合是否有效状态条件查询猜你喜欢信息.<br/> 
     * 
     * @author linwb3
     * @param id
     * @param isValid
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsGuessYLRespDTO queryGdsGuessYLByPK(Long id, boolean isValid) throws BusinessException;
    
    /**
     * 
     * editGdsGuessYL:(编辑猜你喜欢). <br/> 
     * 
     * @author linwb3
     * @param  gdsGuessYL
     * @return
     * @throws BusinessException 
     * @since  JDK 1.6
     */
    public Integer editGdsGuessYL(GdsGuessYL gdsGuessYL) throws BusinessException;
    /**
     * 
     * queryGdsGuessForHomePage:(首页猜你喜欢列表). <br/> 
     * 
     * @author zjh 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsGuessHomePageRespDTO queryGdsGuessForHomePage(GdsGuessYLReqDTO dto)throws BusinessException;
   
    
    public void deleteGdsGuessYLByGdsId(GdsGuessYLReqDTO dto)throws BusinessException;

}

