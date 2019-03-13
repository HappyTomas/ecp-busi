package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsExcelImpLog;
import com.zengshi.ecp.goods.dao.model.GdsExcelImpLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsExcelImpLogMapper {
    Long countByExample(GdsExcelImpLogCriteria example) throws DataAccessException;

    int deleteByExample(GdsExcelImpLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long importId) throws DataAccessException;

    int insert(GdsExcelImpLog record) throws DataAccessException;

    int insertSelective(GdsExcelImpLog record) throws DataAccessException;

    List<GdsExcelImpLog> selectByExample(GdsExcelImpLogCriteria example) throws DataAccessException;

    GdsExcelImpLog selectByPrimaryKey(Long importId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsExcelImpLog record, @Param("example") GdsExcelImpLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsExcelImpLog record, @Param("example") GdsExcelImpLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsExcelImpLog record) throws DataAccessException;

    int updateByPrimaryKey(GdsExcelImpLog record) throws DataAccessException;
}
