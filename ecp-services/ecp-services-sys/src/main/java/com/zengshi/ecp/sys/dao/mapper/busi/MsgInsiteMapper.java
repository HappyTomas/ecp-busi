package com.zengshi.ecp.sys.dao.mapper.busi;

import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.dao.model.MsgInsiteCriteria;
import com.zengshi.ecp.sys.dao.model.MsgInsiteKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface MsgInsiteMapper {
    Long countByExample(MsgInsiteCriteria example) throws DataAccessException;

    int deleteByExample(MsgInsiteCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(MsgInsiteKey key) throws DataAccessException;

    int insert(MsgInsite record) throws DataAccessException;

    int insertSelective(MsgInsite record) throws DataAccessException;

    List<MsgInsite> selectByExample(MsgInsiteCriteria example) throws DataAccessException;

    MsgInsite selectByPrimaryKey(MsgInsiteKey key) throws DataAccessException;

    int updateByExampleSelective(@Param("record") MsgInsite record, @Param("example") MsgInsiteCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") MsgInsite record, @Param("example") MsgInsiteCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(MsgInsite record) throws DataAccessException;

    int updateByPrimaryKey(MsgInsite record) throws DataAccessException;
}
