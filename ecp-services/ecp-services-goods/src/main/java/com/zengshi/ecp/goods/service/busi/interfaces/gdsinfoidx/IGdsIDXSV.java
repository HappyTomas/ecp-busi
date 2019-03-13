/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsEvalIDXSV.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.interfaces 
 * Date:2015年8月24日上午11:38:07 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEval;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Project Name:ecp-services-goods <br>
 * Description:评价，评价回复相关索引服务接口 <br>
 * Date:2015年8月24日上午11:38:07 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsIDXSV extends IGeneralSQLSV {
    /**
     * 
     * 创建商品评价索引.
     * 
     * @author liyong7
     * @param record gdsId,skuId,staffId,orderId必传参数.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void createGdsEvalIdx(GdsEval record)throws BusinessException;
    /**
     * 
     * 删除商品评价索引.
     * 
     * @author liyong7
     * @param record gdsId,skuId,staffId,orderId必传参数.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void deleteGdsEvalIdx(GdsEval record)throws BusinessException;
    /**
     * 
     * 更新评价索引. 
     * 
     * @author liyong7
     * @param record
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void updateGdsEvalIdx(GdsEval record) throws BusinessException;

    /**
     * 创建商品收藏索引，<b><font color=red>收藏编码和三个维度的编码均为必传参数。创建商品收藏索引每个维度使用的收藏编码均和收藏表一致，值直接来源于reqDTO</font></b>
     * 
     * @param dto
     * @return
     * @throws BusinessException
     */
    void saveGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException;

    /**
     * 删除商品收藏索引，<b><font color=red>收藏编码和三个维度的编码均为必传参数</font></b>
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException
     */
    void deleteGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException;

    /**
     * 修改商品收藏索引，<b><font color=red>收藏编码和三个维度的编码均为必传参数</font></b>
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException
     */
    void updateGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException;
    
    /**
     * 商品维度索引表查询
     * @param example
     * @return
     * @throws BusinessException
     */
    public List<GdsCollectGdsIdx> selectByExample(GdsCollectGdsIdxCriteria example) throws BusinessException;
    
    /**
     * 店铺维度索引表查询
     * @param example
     * @return
     * @throws BusinessException
     */
    public List<GdsCollectShopIdx> selectByExample(GdsCollectShopIdxCriteria example) throws BusinessException;
    
    /**
     * 根据收藏数量查询总数.
     * @param example
     * @return
     * @throws BusinessException
     */
    public long countByExampleOrderByCollAmount(GdsCollectShopIdxCriteria example) throws BusinessException;
    
    /**
     * 分组降序查询出单品ID.
     * @param example
     * @return
     * @throws BusinessException
     */
    public List<GdsCollectRespDTO> selectByExampleOrderByCollAmount(GdsCollectShopIdxCriteria example) throws BusinessException;
    
    
    /**
     * 用户维度索引表查询
     * @param example
     * @return
     * @throws BusinessException
     */
    public List<GdsCollectStaffIdx> selectByExample(GdsCollectStaffIdxCriteria example) throws BusinessException;
    
    /**
     * 商品维度索引表查询统计
     * @param example
     * @return
     * @throws BusinessException
     */
    public long countByExample(GdsCollectGdsIdxCriteria example) throws BusinessException;
    
    /**
     * 店铺维度索引表查询统计
     * @param example
     * @return
     * @throws BusinessException
     */
    public long countByExample(GdsCollectShopIdxCriteria example) throws BusinessException;
    
    /**
     * 用户维度索引表查询统计
     * @param example
     * @return
     * @throws BusinessException
     */
    public long countByExample(GdsCollectStaffIdxCriteria example) throws BusinessException;

}
