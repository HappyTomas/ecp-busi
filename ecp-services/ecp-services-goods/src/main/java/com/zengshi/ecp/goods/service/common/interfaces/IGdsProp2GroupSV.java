package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsProp2Group;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * 属性属性组关联关系服务. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月19日下午4:11:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public interface IGdsProp2GroupSV extends IGeneralSQLSV {

    /**
     * 
     * 保存属性属性组关联关系. <br/>
     * 
     * @author liyong7
     * @param gdsProp2Group
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    GdsProp2Group saveGdsProp2Group(GdsProp2Group gdsProp2Group) throws BusinessException;

    /**
     * 
     * 批量添加属性组属性关联关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propIds
     * @param createStaff
     * @param skipWhenExits
     *            true-如果属性组已经配置属性则跳过,false-遇到重复添加属性则抛出异常,添加操作回滚.
     * @throws BusinessException
     * @since JDK 1.6
     */
    void batchAddGdsProp2Groups(Long propGrupId, List<Long> propIds, Long createStaff,
            boolean skipWhenExits) throws BusinessException;

    /**
     * 
     * 分页查询出已关联属性信息,根据创建时间降序排列.<br/>
     * 
     * @author liyong7
     * @param propgroupId
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropRespDTO> queryConfigedPropsPaging(Long propgroupId, GdsPropReqDTO dto)
            throws BusinessException;

    /**
     * 
     * 分页查询出未与指定分类关联的属性列表使用NOT IN效率上可能会有所影响,根据propgroupId来查询,propgroupId一定要传值. <br/>
     * 
     * @author liyong7
     * @param propgroupId
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsPropRespDTO> queryUnconfigedPropsPaging(Long propgroupId,GdsPropReqDTO dto)
            throws BusinessException;

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
    int batchDeleteGdsProps(Long propgroupId, List<Long> propIds, Long updateStaff)
            throws BusinessException;
    
    
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
    int batchDeleteGdsProps(Long propgroupId, Long updateStaff)
            throws BusinessException;

    /**
     * 
     * 查询出与指定属性组已经关联的属性ID序列. <br/>
     * 
     * @author liyong7
     * @param propgroupId
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropIds(Long propgroupId, String... status) throws BusinessException;

    /**
     * 
     * 判断选定属性是否已经与指定属组存在关联关系.
     * 
     * @author liyong7
     * @param propgroupId
     * @param propId
     * @param status
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    boolean queryExist(Long propgroupId, Long propId, String... status) throws BusinessException;

}
