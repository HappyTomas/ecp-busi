package com.zengshi.ecp.goods.dao.mapper.common.manual;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsType2PropCriteria;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
/**
 * 
 * 针对GdsType2Prop操作的特殊操作接口。<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月18日上午10:23:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface GdsType2PropManualMapper {
    /**
     * 
     * 批量删除指定类型的已关联属性关系. <br/> 
     * @param typeId
     * @param propIds
     * @param updateStaff
     * @param updateTime
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int batchDeleteCatg2Prop(@Param("typeId") Long typeId,@Param("propIds") List<Long> propIds,@Param("updateStaff")Long updateStaff, @Param("updateTime") Timestamp updateTime) throws DataAccessException;
    /**
     * 
     * @param typeId
     * @param status
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropIds(@Param("typeId") Long typeId,@Param("status") String... status) throws DataAccessException;
    /**
     * 
     * 执行是否基础属性配置.<br/>
     * 
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsBasicConfig(GdsType2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 执行是否必须属性配置.<br/> 
     * 
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsRequireConfig(GdsType2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 执行是否搜索属性配置.<br/> 
     * 
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsSearchConfig(GdsType2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 根据据分件查询出类型ID. 
     * 
     * @param example
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryRelationTypeIdsByExample(GdsType2PropCriteria example) throws DataAccessException;
    
    
}
