package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCollectStaffIdxMapper {
    Long countByExample(GdsCollectStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsCollectStaffIdxCriteria example) throws DataAccessException;

    int insert(GdsCollectStaffIdx record) throws DataAccessException;

    int insertSelective(GdsCollectStaffIdx record) throws DataAccessException;

    List<GdsCollectStaffIdx> selectByExample(GdsCollectStaffIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCollectStaffIdx record, @Param("example") GdsCollectStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCollectStaffIdx record, @Param("example") GdsCollectStaffIdxCriteria example) throws DataAccessException;
}
