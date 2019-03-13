/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsCatg2LabelSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月20日上午9:40:03 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsCatg2Label;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2LabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2LabelRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 分类标签关联接口. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月20日上午9:40:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsCatg2LabelSV extends IGeneralSQLSV {
    
    /**
     * 
     * 保存商品分类关联关系. <br/>
     * 
     * @author liyong7
     * @param GdsPropDTO
     * @return 返回商品分类对象.
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsCatg2Label saveGdsCatg2Label(GdsCatg2Label record) throws BusinessException;
   
    /**
     * 
     * 批量添加分类与标签关联关系。 
     * 
     * @author liyong7
     * @param catgCode
     * @param labelIds
     * @param createStaff
     * @param skipWhenExist 检查是已存在关联,true-如果存在则不添加, false-如果已经关联则抛出异常.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void batchAddGdsCatg2Label(String catgCode, List<Long> labelIds, Long createStaff, boolean skipWhenExist)
            throws BusinessException;

    /**
     * 
     * 分页查询出已关联属性信息,根据创建时间降序.<br/> 
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCatg2LabelRespDTO> queryConfigedGdsCatg2LabelRespDTOPaging(
            GdsCatg2LabelReqDTO dto) throws BusinessException;

    /**
     * 
     * 分页查询出未与指定分类关联的属性列表使用NOT IN效率上可能会有所影响,根据catgCode来查询,catgCode一定要传值. <br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsCatg2PropRespDTO> queryUnconfigedGdsLabelRespDTOPaging(
            GdsLabelReqDTO dto) throws BusinessException;

    /**
     * 
     * 批量删除指定分类的已关联属性关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param labelIds
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int batchDeleteCatg2Labels(String catgCode, List<Long> labelIds, Long updateStaff)
            throws BusinessException;
    
    
    /**
     * 
     * 批量删除指定分类与标签关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param labelIds
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int deleteAllCatg2LabelsByCatgCode(String catgCode, Long updateStaff)
            throws BusinessException;
    /**
     * 
     * 查询出与指定分类已经关联的标签ID.
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedLabelIds(String catgCode, String... status) throws BusinessException;
    /**
     * 
     * 判断选定分类是否已经与指定属性关联.
     * 
     * @author liyong7
     * @param catgCode
     * @param propId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExist(String catgCode, Long labelId, String... status) throws BusinessException;

}

