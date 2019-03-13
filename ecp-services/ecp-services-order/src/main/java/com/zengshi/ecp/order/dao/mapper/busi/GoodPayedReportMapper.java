package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.GoodPayedReport;
import com.zengshi.ecp.order.dao.model.GoodPayedReportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GoodPayedReportMapper {
    Long countByExample(GoodPayedReportCriteria example) throws DataAccessException;

    int deleteByExample(GoodPayedReportCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(GoodPayedReport record) throws DataAccessException;

    int insertSelective(GoodPayedReport record) throws DataAccessException;

    List<GoodPayedReport> selectByExample(GoodPayedReportCriteria example) throws DataAccessException;

    GoodPayedReport selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GoodPayedReport record, @Param("example") GoodPayedReportCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GoodPayedReport record, @Param("example") GoodPayedReportCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GoodPayedReport record) throws DataAccessException;

    int updateByPrimaryKey(GoodPayedReport record) throws DataAccessException;
}