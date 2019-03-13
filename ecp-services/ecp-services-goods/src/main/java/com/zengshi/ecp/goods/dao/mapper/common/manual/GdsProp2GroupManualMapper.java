package com.zengshi.ecp.goods.dao.mapper.common.manual;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsProp2GroupManualMapper {
    /**
     * 
     * 查询出已经与指定属性组关联的属性ID.<br/>
     * 
     * @author liyong7
     * @param propgroupId
     * @param status
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropIds(@Param("propgroupId") Long propgroupId,@Param("status") String... status) throws DataAccessException;
    /**
     * 
     * 批量移除指定属性组已关联属性.
     * 
     * @author liyong7
     * @param propgroupId
     * @param propIds
     * @param updateStaff
     * @param updateTime
     * @return
     * @throws DataAccessException 
     * @since JDK 1.6
     */
    int batchDeleteGdsProps(@Param("propgroupId") Long propgroupId,@Param("propIds") List<Long> propIds,@Param("updateStaff")Long updateStaff, @Param("updateTime") Timestamp updateTime) throws DataAccessException;
}
