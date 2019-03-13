package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdOfflineChk;
import com.zengshi.ecp.order.dao.model.OrdOfflineChkCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdOfflineChkMapper {
    Long countByExample(OrdOfflineChkCriteria example) throws DataAccessException;

    int deleteByExample(OrdOfflineChkCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdOfflineChk record) throws DataAccessException;

    int insertSelective(OrdOfflineChk record) throws DataAccessException;

    List<OrdOfflineChk> selectByExample(OrdOfflineChkCriteria example) throws DataAccessException;

    OrdOfflineChk selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdOfflineChk record, @Param("example") OrdOfflineChkCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdOfflineChk record, @Param("example") OrdOfflineChkCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdOfflineChk record) throws DataAccessException;

    int updateByPrimaryKey(OrdOfflineChk record) throws DataAccessException;
}