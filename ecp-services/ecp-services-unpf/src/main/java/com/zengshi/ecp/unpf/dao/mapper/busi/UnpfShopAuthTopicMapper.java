package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopic;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthTopicCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfShopAuthTopicMapper {
    Long countByExample(UnpfShopAuthTopicCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfShopAuthTopic record) throws DataAccessException;

    int insertSelective(UnpfShopAuthTopic record) throws DataAccessException;

    List<UnpfShopAuthTopic> selectByExample(UnpfShopAuthTopicCriteria example) throws DataAccessException;

    UnpfShopAuthTopic selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfShopAuthTopic record, @Param("example") UnpfShopAuthTopicCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfShopAuthTopic record, @Param("example") UnpfShopAuthTopicCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfShopAuthTopic record) throws DataAccessException;

    int updateByPrimaryKey(UnpfShopAuthTopic record) throws DataAccessException;
}