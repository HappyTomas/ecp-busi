/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsMedisCategorySV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月21日下午5:49:26 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsMediaCategory;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * 媒体分类接口定义. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月21日下午5:49:26 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsMediaCategorySV extends IGeneralSQLSV {
    /**
     * 
     * 保存媒体分类信息.
     * 
     * @author liyong7
     * @param record
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsMediaCategory saveGdsMedisCatg(GdsMediaCategory record) throws BusinessException;
    /**
     * 
     * 新建媒体分类. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsMediaCategoryRespDTO saveGdsMediaCatg(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * 编辑媒体分类.
     * 
     * @author liyong7
     * @param gdsMediaCategory
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsMediaCategory edisGdsMediaCategory(GdsMediaCategory gdsMediaCategory)
            throws BusinessException;

    /**
     * 
     * 根据主键ID结合是否有效状态过滤条件查询出媒体分类信息.
     * 
     * @author liyong7
     * @param cateCode
     * @param isValid
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsMediaCategory queryGdsMediaCategoryByPK(String catgCode, boolean isValid)
            throws BusinessException;

    
    GdsMediaCategoryRespDTO queryGdsMediaCategoryByPK(GdsMediaCategoryReqDTO reqDTO)
            throws BusinessException;
    /**
     * 
     * 分页查询.<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    PageResponseDTO<GdsMediaCategoryRespDTO> queryGdsMediaCategoryRespDTOPaging(
            GdsMediaCategoryReqDTO dto) throws BusinessException;

    /**
     * 
     * 根据主键删除媒体分类，如果需要在没有有效子类别时才允许删除的话，需要调用<br/>
     * {@link #executeCountSubMediaCatg(catgCode, status)}方法执行子分类统计后再执行删除操作。<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param updateStaff
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    int executeDeleteGdsMediaCategoryByPK(String catgCode, Long updateStaff)
            throws BusinessException;

    /**
     * 
     * 根据分类名称,层级以及状态条件查询是否已经存在媒体分类信息.<br/>
     * 
     * @author liyong7
     * @param catgName
     * @param level
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    boolean queryExist(String catgName, Short level, String... status) throws BusinessException;
    
    /**
     * 
     * 根据分类名称,层级以及状态条件查询是否已经存在媒体分类信息.<br/>
     * 
     * @author liyong7
     * @param catgName
     * @param shopId
     * @param level
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    boolean queryExist(String catgName,Long shopId, Short level, String... status) throws BusinessException;

    /**
     * 
     * 结合分类ID与状态条件统计出子节点个数.<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    long executeCountSubMediaCatg(String catgCode, String... status) throws BusinessException;

    /**
     * 
     * 查询出指定分类下的所有子分类信息。
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    List<GdsMediaCategory> querySubMediaCatgs(String catgCode, String... status)
            throws BusinessException;

    /**
     * 
     * 根据主键查询媒体分类。<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsMediaCategory queryGdsMediaCategoryByPk(String catgCode) throws BusinessException;
    /**
     * 
     * 查询根分类。<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<GdsMediaCategory> queryRootCategory(String... status) throws BusinessException;
    /**
     * 
     * 批量删除指定媒体分类。<br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param updateStaff
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void batchDelete(List<String> catgCodes, Long updateStaff) throws BusinessException;
    /**
     * 
     * 更新是否叶节点字段.
     * 
     * @author liyong7
     * @param catgCode
     * @param updateStaff
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateIfLeafByPK(String catgCode,Long updateStaff) throws BusinessException;
    
    /**
     * 
     * queryCategoryTraceUpon:获取媒体分类轨迹. <br/> 
     * 
     * @author Lenovo 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsMediaCategoryRespDTO> queryCategoryTraceUpon(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;


}
