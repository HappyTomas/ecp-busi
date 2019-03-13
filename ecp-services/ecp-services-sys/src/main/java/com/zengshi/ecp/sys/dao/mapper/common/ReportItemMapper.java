package com.zengshi.ecp.sys.dao.mapper.common;

import com.zengshi.ecp.sys.dao.model.ReportItem;
import com.zengshi.ecp.sys.dao.model.ReportItemCriteria;
import com.zengshi.ecp.sys.dao.model.ReportItemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ReportItemMapper {
    Long countByExample(ReportItemCriteria example) throws DataAccessException;

    int deleteByExample(ReportItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(ReportItemKey key) throws DataAccessException;

    int insert(ReportItem record) throws DataAccessException;

    int insertSelective(ReportItem record) throws DataAccessException;

    List<ReportItem> selectByExample(ReportItemCriteria example) throws DataAccessException;

    ReportItem selectByPrimaryKey(ReportItemKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ReportItem record, @Param("example") ReportItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ReportItem record, @Param("example") ReportItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ReportItem record) throws DataAccessException;

    int updateByPrimaryKey(ReportItem record) throws DataAccessException;
}
