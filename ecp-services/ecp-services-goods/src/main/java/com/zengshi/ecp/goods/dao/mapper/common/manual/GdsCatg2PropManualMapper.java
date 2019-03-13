package com.zengshi.ecp.goods.dao.mapper.common.manual;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dao.model.GdsCatg2PropCriteria;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
/**
 * 
 * 针对GdsCatg2Prop操作的特殊操作接口。<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月18日上午10:23:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public interface GdsCatg2PropManualMapper {
    /**
     * 
     * 批量删除指定分类的已关联属性关系. <br/> 
     * @author liyong7
     * @param catgCode
     * @param propIds
     * @param updateStaff
     * @param updateTime
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int batchDeleteCatg2Prop(@Param("catgCode") String catgCode,@Param("propIds") List<Long> propIds,@Param("updateStaff")Long updateStaff, @Param("updateTime") Timestamp updateTime) throws DataAccessException;
    /**
     * 
     * queryConfigedPropIds:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropIds(@Param("catgCode") String catgCode,@Param("status") String... status) throws DataAccessException;
    /**
     * 
     * 执行是否基础属性配置.<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsBasicConfig(GdsCatg2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 执行是否必须属性配置.<br/> 
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsRequireConfig(GdsCatg2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 执行是否搜索属性配置.<br/> 
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int executeIsSearchConfig(GdsCatg2PropReqDTO dto) throws DataAccessException;
    /**
     * 
     * 根据据分件查询出分类ID. 
     * 
     * @author liyong7
     * @param example
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<String> queryRelationCatgCodesByExample(GdsCatg2PropCriteria example) throws DataAccessException;
    
    
}
