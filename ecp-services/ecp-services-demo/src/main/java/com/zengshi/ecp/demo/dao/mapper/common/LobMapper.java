package com.zengshi.ecp.demo.dao.mapper.common;

import com.zengshi.ecp.demo.dao.model.Lob;
import com.zengshi.ecp.demo.dao.model.LobCriteria;
import com.zengshi.ecp.demo.dao.model.LobWithBLOBs;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface LobMapper {
    int countByExample(LobCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(BigDecimal id) throws DataAccessException;

    int insert(LobWithBLOBs record) throws DataAccessException;

    int insertSelective(LobWithBLOBs record) throws DataAccessException;

    List<LobWithBLOBs> selectByExampleWithBLOBs(LobCriteria example) throws DataAccessException;

    List<Lob> selectByExample(LobCriteria example) throws DataAccessException;

    LobWithBLOBs selectByPrimaryKey(BigDecimal id) throws DataAccessException;

    int updateByPrimaryKeySelective(LobWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(LobWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(Lob record) throws DataAccessException;

    void insertBatch(List<LobWithBLOBs> recordLst) throws DataAccessException;
}
