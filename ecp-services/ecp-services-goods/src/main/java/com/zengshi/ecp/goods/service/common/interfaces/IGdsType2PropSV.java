/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsProp.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月13日下午4:59:22 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsType2Prop;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IGdsType2PropSV extends IGeneralSQLSV{

    /**
     * 
     * 保存类型属性关联关系. <br/>
     * 
     * @param gdsType2Prop
     * @return 返回商品类型对象.
     * @throws BusinessException
     * @since JDK 1.6
     */
	public GdsType2Prop saveGdsType2Prop(GdsType2Prop gdsType2Prop) throws BusinessException;

    /**
     * 
     * 批量添加类型属性关联关系. <br/>
     * 
     * @param typeId
     * @param propIds
     * @param createStaff
     * @param skipWhenExist  检查是否存在,true-如果存在则不添加, false-如果已经关联则抛出异常.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchAddGdsType2Props(Long typeId, List<Long> propIds, Long createStaff, boolean skipWhenExist)
            throws BusinessException;

    /**
     * 
     * 分页查询出已关联属性信息,根据SORT_NO自然排序.<br/>
     * 
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsType2PropRespDTO> queryGdsType2PropRespDTOPaging(
            GdsType2PropReqDTO dto) throws BusinessException;

    /**
     * 
     * 批量删除指定类型的已关联属性关系. <br/>
     * 
     * @param typeId
     * @param propIds
     * @param updateStaff
     * @return
     * @throws DataAccessException
     * @since JDK 1.6
     */
    public int batchDeleteCatg2Prop(Long typeId, List<Long> propIds, Long updateStaff)
            throws BusinessException;
    /**
     * 
     * 查询出与指定类型已经关联的属性ID序列. <br/> 
     * 
     * @param typeId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<Long> queryConfigedPropIds(Long typeId, String... status) throws BusinessException;
    
    /**
     * 
     * 判断选定类型是否已经与指定属性关联.
     * 
     * @param typeId
     * @param propId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryExist(Long typeId, Long propId, String... status) throws BusinessException;
    
    /**
     * 
     * 执行是否基础属性选定事件(即针对一个属性是否为基础属性的操作对数据库执行更新操作) <br/> 
     * 
     * @param typeId
     * @param propId
     * @param isBasic
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsBaseConfig(Long typeId, Long propId, String isBasic, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 执行是否必须属性选定事件(即针对一个类型与属性的关联关系是否为必须属性的操作对数据库执行更新操作) <br/>
     * 
     * @param typeId
     * @param propId
     * @param isRequired
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsRequireConfig(Long typeId, Long propId, String isRequired, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 执行是否搜索属性选定事件(即针对一个类型与属性的关联关系是否为必须属性的操作对数据库执行更新操作) <br/>
     * 
     * @param typeId
     * @param propId
     * @param isRequired
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsSearchConfig(Long typeId, Long propId, String isSearch, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 根据类型信息查询对应类型的属性
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType2PropRelationRespDTO queryTypeProps(GdsType2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据条件查询出类型以及与之关联的属性的信息以及属性值信息。 
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType2PropRelationRespDTO queryTypePropsByCondition(GdsType2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 属性配置. 
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executePropConfig(GdsType2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 查询指定属性是否已经与类型关联.
     * 
     * @param reqDTO propId必传.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryIsInUse(GdsType2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * 查询类型属性关系
     * 
     * @param reqDTO
     * @return 
     * @since JDK 1.6
     */
    public List<GdsType2Prop> queryConfigedProps(GdsType2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * 根据条件查询出类型ID.
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<Long> queryRelationTypeIdsByExample(GdsType2PropReqDTO reqDTO) throws BusinessException;
    /**
     *     
     * 统计查询出不可编辑类型属性关联关系
     * 
     * @param typeId
     * @param propIds
     * @return 
     * @since JDK 1.6
     */
    public Long queryUneditable(Long typeId, List<Long> propIds);
    
    
}
