package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsArrmsgStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsArrmsgStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsArrmsgStaffIdxMapper {
    Long countByExample(GdsArrmsgStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsArrmsgStaffIdxCriteria example) throws DataAccessException;

    int insert(GdsArrmsgStaffIdx record) throws DataAccessException;

    int insertSelective(GdsArrmsgStaffIdx record) throws DataAccessException;

    List<GdsArrmsgStaffIdx> selectByExample(GdsArrmsgStaffIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsArrmsgStaffIdx record, @Param("example") GdsArrmsgStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsArrmsgStaffIdx record, @Param("example") GdsArrmsgStaffIdxCriteria example) throws DataAccessException;
}
