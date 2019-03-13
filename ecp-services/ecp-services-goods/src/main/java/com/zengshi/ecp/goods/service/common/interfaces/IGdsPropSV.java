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

import com.zengshi.ecp.goods.dao.model.GdsProp;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月13日下午4:59:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsPropSV extends IGeneralSQLSV{
    
    /**
     * 
     * 保存商品属性. <br/> 
     * 
     * @author liyong7
     * @param GdsPropDTO
     * @return 返回商品分类对象.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int saveGdsProp(GdsPropReqDTO gdsPropReqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键ID查询商品属性信息. <br/> 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsProp queryGdsPropByPK(Long id) throws BusinessException;
    /**
     * 
     * 根据主键ID结合是否有效状态限定条件查询属性信息. 
     * 
     * @author liyong7
     * @param id
     * @param isValid
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsProp queryGdsPropByPK(Long id, boolean isValid) throws BusinessException;
   /**
    * 
    * 根据查询条件分页查询商品属性. <br/>
    * 
    * @author liyong7
    * @param dto
    * @param excludeIds 需要排除的ID列表.
    * @param includeIds 需要包含的ID列表.
    * @return
    * @throws BusinessException 
    * @since JDK 1.6
    */
    PageResponseDTO<GdsPropRespDTO> queryGdsPropRespDTOPaging(GdsPropReqDTO dto,List<Long> excludeIds, List<Long> includeIds) throws BusinessException;
    /**
     * 
     * 编辑商品属性信息. <br/> 
     * 
     * @author liyong7
     * @param GdsProp
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int editGdsProp(GdsProp gdsProp, Long updateStaff) throws BusinessException;
    /**
     * 
     * 编辑商品属性.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int editGdsProp(GdsPropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * isExist:根据属性名称结合状态信息来判断一个商品属性是否已经存在. <br/> 
     * 
     * @author liyong7
     * @param typeName
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExist(String propName, String... status) throws BusinessException;
    /**
     * 
     * 禁用商品属性.(状态设置成失效,非物理删除) <br/> 
     * 
     * @author liyong7
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int deleteGdsProp(Long id, Long updateStaff) throws BusinessException;
    /**
     * 
     * 启用指定ID的属性。
     * 
     * @author liyong7
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int executeEnableGdsProp(Long id, Long updateStaff) throws BusinessException;
    /**
     * 
     * 根据条件查询出属性主键ID序列。 
     * 
     * @author liyong7
     * @param criteria
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<Long> queryPropIds(GdsPropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据条件查询指定属性是否已经跟有效分类关联. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryIsPropInUse(GdsCatg2PropReqDTO reqDTO) throws BusinessException;
    
}

