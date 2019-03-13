package com.zengshi.ecp.demo.dao.mapper.busi;

import com.zengshi.ecp.demo.dao.model.DemoDistributed;
import com.zengshi.ecp.demo.dao.model.DemoDistributedCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface DemoDistributedMapper {
    int countByExample(DemoDistributedCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DemoDistributed record) throws DataAccessException;

    int insertSelective(DemoDistributed record) throws DataAccessException;

    List<DemoDistributed> selectByExample(DemoDistributedCriteria example) throws DataAccessException;

    DemoDistributed selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(DemoDistributed record) throws DataAccessException;

    int updateByPrimaryKey(DemoDistributed record) throws DataAccessException;

    void insertBatch(List<DemoDistributed> recordLst) throws DataAccessException;
}
