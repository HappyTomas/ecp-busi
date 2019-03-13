package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.GoodStaffPayedReport;
import com.zengshi.ecp.order.dao.model.GoodStaffPayedReportCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GoodStaffPayedReportMapper {
    Long countByExample(GoodStaffPayedReportCriteria example) throws DataAccessException;

    int deleteByExample(GoodStaffPayedReportCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(GoodStaffPayedReport record) throws DataAccessException;

    int insertSelective(GoodStaffPayedReport record) throws DataAccessException;

    List<GoodStaffPayedReport> selectByExample(GoodStaffPayedReportCriteria example) throws DataAccessException;

    GoodStaffPayedReport selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GoodStaffPayedReport record, @Param("example") GoodStaffPayedReportCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GoodStaffPayedReport record, @Param("example") GoodStaffPayedReportCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GoodStaffPayedReport record) throws DataAccessException;

    int updateByPrimaryKey(GoodStaffPayedReport record) throws DataAccessException;
}