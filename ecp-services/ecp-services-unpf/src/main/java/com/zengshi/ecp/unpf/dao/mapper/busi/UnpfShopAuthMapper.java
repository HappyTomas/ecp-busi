package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfShopAuth;
import com.zengshi.ecp.unpf.dao.model.UnpfShopAuthCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfShopAuthMapper {
    Long countByExample(UnpfShopAuthCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfShopAuth record) throws DataAccessException;

    int insertSelective(UnpfShopAuth record) throws DataAccessException;

    List<UnpfShopAuth> selectByExample(UnpfShopAuthCriteria example) throws DataAccessException;

    UnpfShopAuth selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfShopAuth record, @Param("example") UnpfShopAuthCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfShopAuth record, @Param("example") UnpfShopAuthCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfShopAuth record) throws DataAccessException;

    int updateByPrimaryKey(UnpfShopAuth record) throws DataAccessException;
}