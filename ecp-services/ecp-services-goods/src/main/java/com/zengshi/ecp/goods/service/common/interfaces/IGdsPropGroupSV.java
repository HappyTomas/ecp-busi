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

import com.zengshi.ecp.goods.dao.model.GdsPropGroup;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 商品域属性组服务接口。 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午4:59:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsPropGroupSV extends IGeneralSQLSV{
    
    /**
     * 
     * 添加属性组信息. <br/>
     * 该方法可用于dubbo层.<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void createGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException;
    
    
    /**
     * 
     * 保存属性组. <br/> 
     * 
     * @author liyong7
     * @param gdsPropGroup
     * @return 返回商品分类对象.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveGdsPropGroup(GdsPropGroup gdsPropGroup) throws BusinessException;
    /**
     * 
     * 根据主键ID查询属性组信息. <br/> 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropGroup queryGdsPropGroupByPK(Long id) throws BusinessException;
    /**
     * 
     * 根据主键获取属性组信息. 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropGroupRespDTO queryGdsPropGroup(Long id,String... status)throws BusinessException;
    
    
    /**
     * 
     * 根据主键获取属性组信息以及对应属性列表
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropGroupRespDTO queryGdsPropGroupAndProps(Long id)throws BusinessException;
    
    
    
    
    /**
     * 
     * 根据主键结合是否有效过滤有效状态条件查询出属性组信息.
     * 
     * @author liyong7
     * @param id
     * @param isValid
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropGroup queryGdsPropGroupByPK(Long id, boolean isValid) throws BusinessException; 
    /**
     * 
     * 编辑属性组基础信息. <br/> 
     * 
     * @author liyong7
     * @param gdsPropGroup
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int editGdsPropGroup(GdsPropGroup gdsPropGroup, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 编辑属性组信息.<br/>
     * 该方法可以用于dubbo层,属性组ID,属性组名称允许编辑.
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int editGdsPropGroup(GdsPropGroupReqDTO dto) throws BusinessException;
    /**
     * 
     * 根据属性名称结合状态信息来判断一个商品属性是否已经存在. <br/> 
     * 
     * @author liyong7
     * @param groupName
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExist(String groupName, String... status) throws BusinessException;
    /**
     * 
     * 属性名是否存在.
     * 
     * @author liyong7
     * @param groupName 属性组名.
     * @param excludeGroupId 排除属性组ID.
     * @param status 状态条件.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExist(String groupName, Long[] excludeGroupId, String... status) throws BusinessException;
    /**
     * 
     * 禁用商品属性组.<br/> 
     * 
     * @author liyong7
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int executeDisableGdsPropGroup(Long id, Long updateStaff) throws BusinessException;
    /**
     * 
     * 启用商品属性组.<br/>
     * 
     * @author liyong7
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int executeEnableGdsPropGroup(Long id, Long updateStaff) throws BusinessException;
    /**
     * 
     * 分页查询属性组信息<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(GdsPropGroupReqDTO dto) throws BusinessException;
    /**
     * 
     * 根据查询条件查询出所有属性组ID序列.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<Long> queryGroupIds(GdsPropGroupReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 分页查询. 
     * 
     * @author liyong7
     * @param dto
     * @param excludeIds 排除属性组ID.
     * @param includeIds 包含属性组ID.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropGroupRespDTO> queryGdsPropGroupRespDTOPaging(
            GdsPropGroupReqDTO dto,List<Long> excludeIds, List<Long> includeIds) throws BusinessException;
}

