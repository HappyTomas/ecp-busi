/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsTypeSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月11日上午10:33:51 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

public interface IGdsTypeSV extends IGeneralSQLSV{
    
    final static String KEY_GDSTYPELIST="gdsTypeList";
    
    /**
     * 
     * 保存 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveGdsType(GdsTypeReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 保存商品类型. <br/> 
     * 
     * @author liyong7
     * @param gdsTypeDTO
     * @return 返回商品类型对象.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int saveGdsType(GdsType gdsType) throws BusinessException;
    /**
     * 
     * 根据主键ID查询商品类型信息. <br/> 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType queryGdsTypeByPK(Long id) throws BusinessException;
    /**
     * 
     * 根据dto中的主键ID查询商品类型. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsTypeRespDTO queryGdsTypeByPK(GdsTypeReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键ID查询商品类型信息. <br/>
     * 
     * @author liyong7
     * @param id
     * @param isValid true-仅查询出状态为有效的类型  false-忽略状态条件仅根据id进行查询.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType queryGdsTypeByPK(Long id, String... status) throws BusinessException;
    
    
    /**
     * 
     * 根据code查询商品类型信息. <br/>
     * 
     * @author liyong7
     * @param id
     * @param isValid true-仅查询出状态为有效的类型  false-忽略状态条件仅根据id进行查询.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType queryGdsTypeByCode(String code, String... status) throws BusinessException;
    
    
    /**
     * 
     * 查询出所有商品类型信息,结合创建时间与排序字段进行降序显示.
     * @author liyong7
     * @param gdsTypeDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsTypeRespDTO> queryGdsTypeList() throws BusinessException;
    
    public List<GdsTypeRespDTO> queryGdsTypeListFromCache()throws BusinessException;
    
    /**
     * 
     * 编辑商品类型信息. <br/> 
     * 
     * @author liyong7
     * @param gdsType
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int editGdsType(GdsType gdsType, Long updateStaff) throws BusinessException;
    /**
     * 
     * 编辑商品类型.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int editGdsType(GdsTypeReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 查询是否已经存在同名类型.
     * 
     * @author liyong7
     * @param typeName
     * @param excludeIds
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryExist(String typeName,List<Long> excludeIds, String... status)throws BusinessException;
    /**
     * 
     * queryExist:根据类型名称结合状态信息来判断一个商品类型是否已经存在. <br/> 
     * 
     * @author liyong7
     * @param typeName
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean queryExist(String typeName, String... status) throws BusinessException;
    /**
    * 
    * deleteGdsType:禁用商品类型(该删除操作非物理删除，而是把状态改成失效). <br/> 
    * 
    * @author liyong7
    * @param id
    * @param updateStaff
    * @throws BusinessException 
    * @since JDK 1.6
    */
    public int deleteGdsType(long id, Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 分页查询商品类型信息,返回PageResponseDTO数据格式. <br/> 
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsTypeRespDTO> queryGdsTypeRespDTOPaging(GdsTypeReqDTO dto) throws BusinessException;
    /**
     * 
     * 启用指定主键商品类型.<br/>
     * 
     * @author liyong7
     * @param id
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int executeEnableGdsType(Long id, Long updateStaff) throws BusinessException;
    /**
     * 
     * queryTypeCodeById:(根据主键或者价格类型编码). <br/> 
     * 
     * @author linwb3
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public String queryTypeCodeById(Long id) throws BusinessException;
    /**
     * 根据商品类型id获取商品类型，从缓存获取
     * @param id
     * @return
     * @throws BusinessException
     */
    public GdsType queryGdsTypeModelByPKFromCache(Long id) throws BusinessException;
    /**
     * 根据商品类型id获取商品类型，从缓存获取
     * @param id
     * @return
     * @throws BusinessException
     */
    public GdsTypeRespDTO queryGdsTypeByPKFromCache(Long id) throws BusinessException;
    
}

