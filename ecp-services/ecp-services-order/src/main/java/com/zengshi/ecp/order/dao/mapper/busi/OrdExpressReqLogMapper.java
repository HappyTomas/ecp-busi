package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdExpressReqLog;
import com.zengshi.ecp.order.dao.model.OrdExpressReqLogCriteria;
import com.zengshi.ecp.order.dao.model.OrdExpressReqLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdExpressReqLogMapper {
    Long countByExample(OrdExpressReqLogCriteria example) throws DataAccessException;

    int deleteByExample(OrdExpressReqLogCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdExpressReqLogWithBLOBs record) throws DataAccessException;

    int insertSelective(OrdExpressReqLogWithBLOBs record) throws DataAccessException;

    List<OrdExpressReqLogWithBLOBs> selectByExampleWithBLOBs(OrdExpressReqLogCriteria example) throws DataAccessException;

    List<OrdExpressReqLog> selectByExample(OrdExpressReqLogCriteria example) throws DataAccessException;

    OrdExpressReqLogWithBLOBs selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdExpressReqLogWithBLOBs record, @Param("example") OrdExpressReqLogCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") OrdExpressReqLogWithBLOBs record, @Param("example") OrdExpressReqLogCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdExpressReqLog record, @Param("example") OrdExpressReqLogCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdExpressReqLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(OrdExpressReqLogWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(OrdExpressReqLog record) throws DataAccessException;
}