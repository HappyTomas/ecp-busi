package com.zengshi.ecp.demo.dao.mapper.busi;

import com.zengshi.ecp.demo.dao.model.DemoInfo;
import com.zengshi.ecp.demo.dao.model.DemoInfoCriteria;
import java.util.List;
import org.springframework.dao.DataAccessException;

public interface DemoInfoMapper {
    int countByExample(DemoInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(DemoInfo record) throws DataAccessException;

    int insertSelective(DemoInfo record) throws DataAccessException;

    List<DemoInfo> selectByExample(DemoInfoCriteria example) throws DataAccessException;

    DemoInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByPrimaryKeySelective(DemoInfo record) throws DataAccessException;

    int updateByPrimaryKey(DemoInfo record) throws DataAccessException;

    void insertBatch(List<DemoInfo> recordLst) throws DataAccessException;
}
