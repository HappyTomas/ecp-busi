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

import com.zengshi.ecp.goods.dao.model.GdsCatg2Prop;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 分类与分类属性关系操作服务接口 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午4:59:22 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsCatg2PropSV extends IGeneralSQLSV{

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
	public GdsCatg2Prop saveGdsCatg2Prop(GdsCatg2Prop gdsCatg2Prop) throws BusinessException;

    /**
     * 
     * 批量添加类别属性关联关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propIds
     * @param createStaff
     * @param skipWhenExist  检查是否存在,true-如果存在则不添加, false-如果已经关联则抛出异常.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void batchAddGdsCatg2Props(String catgCode, List<Long> propIds, Long createStaff, boolean skipWhenExist)
            throws BusinessException;

    /**
     * 
     * 分页查询出已关联属性信息,根据SORT_NO自然排序.<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatg2PropRespDTO> queryConfigedGdsCatg2PropRespDTOPaging(
            GdsCatg2PropReqDTO dto) throws BusinessException;

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
   /* PageResponseDTO<GdsCatg2PropRespDTO> queryUnconfigedGdsCatg2PropRespDTOPaging(
            GdsCatg2PropReqDTO dto) throws BusinessException;*/

    /**
     * 
     * 批量删除指定分类的已关联属性关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propIds
     * @param updateStaff
     * @return
     * @throws DataAccessException
     * @since JDK 1.6
     */
    public int batchDeleteCatg2Prop(String catgCode, List<Long> propIds, Long updateStaff)
            throws BusinessException;
    /**
     * 
     * 查询出与指定分类已经关联的属性ID序列. <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<Long> queryConfigedPropIds(String catgCode, String... status) throws BusinessException;
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
    public boolean queryExist(String catgCode, Long propId, String... status) throws BusinessException;
    /**
     * 
     * 执行是否基础属性选定事件(即针对一个属性是否为基础属性的操作对数据库执行更新操作) <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param propId
     * @param isBasic
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsBaseConfig(String catgCode, Long propId, String isBasic, Long updateStaff) throws BusinessException;
    /**
     * 
     * 执行是否必须属性选定事件(即针对一个分类与属性的关联关系是否为必须属性的操作对数据库执行更新操作) <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propId
     * @param isRequired
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsRequireConfig(String catgCode, Long propId, String isRequired, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 执行是否搜索属性选定事件(即针对一个分类与属性的关联关系是否为必须属性的操作对数据库执行更新操作) <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propId
     * @param isRequired
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executeIsSearchConfig(String catgCode, Long propId, String isSearch, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * queryCategoryProps:(根据分类信息查询对应分类的属性). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCatg2PropRelationRespDTO queryCategoryProps(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据条件查询出分类以及与之关联的属性的信息以及属性值信息。 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsCatg2PropRelationRespDTO queryCategoryPropsByCondition(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 属性配置. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean executePropConfig(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 查询指定属性是否已经与分类关联.
     * 
     * @author liyong7
     * @param reqDTO propId必传.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryIsInUse(GdsCatg2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * queryConfigedProps:(查询分类属性关系). <br/> 
     * 
     * @author linwb3
     * @param catgCode
     * @param propId   可选，查询固定属性
     * @param ifBastic 可选，是否寄出属性
     * @param status   可选，不传默认查询有效数据
     * @return 
     * @since JDK 1.6
     */
    public List<GdsCatg2Prop> queryConfigedProps(GdsCatg2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * queryConfigedPropsWithParents:(查询对应属性配置的属性以及顶级父类配置的属性). <br/> 
     * 
     * @author linwb3
     * @param catgCode
     * @param propId
     * @param ifBastic
     * @param status
     * @return 
     * @since JDK 1.6
     */
    public List<GdsCatg2Prop> queryConfigedPropsWithParents(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据条件查询出分类ID.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<String> queryRelationCatgCodesByExample(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    /**
     *     
     * queryUneditable:统计查询出不可编辑分类属性关联关系. <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param propIds
     * @return 
     * @since JDK 1.6
     */
    public Long queryUneditable(String catgCode, List<Long> propIds);
    
    
}
