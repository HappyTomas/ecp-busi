package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalStaffIdxMapper {
    Long countByExample(GdsEvalStaffIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalStaffIdxCriteria example) throws DataAccessException;

    int insert(GdsEvalStaffIdx record) throws DataAccessException;

    int insertSelective(GdsEvalStaffIdx record) throws DataAccessException;

    List<GdsEvalStaffIdx> selectByExample(GdsEvalStaffIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEvalStaffIdx record, @Param("example") GdsEvalStaffIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEvalStaffIdx record, @Param("example") GdsEvalStaffIdxCriteria example) throws DataAccessException;
}
