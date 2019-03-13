package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMainCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfOrdMainMapper {
    Long countByExample(UnpfOrdMainCriteria example) throws DataAccessException;

    int deleteByExample(UnpfOrdMainCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(UnpfOrdMain record) throws DataAccessException;

    int insertSelective(UnpfOrdMain record) throws DataAccessException;

    List<UnpfOrdMain> selectByExample(UnpfOrdMainCriteria example) throws DataAccessException;

    UnpfOrdMain selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfOrdMain record, @Param("example") UnpfOrdMainCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfOrdMain record, @Param("example") UnpfOrdMainCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfOrdMain record) throws DataAccessException;

    int updateByPrimaryKey(UnpfOrdMain record) throws DataAccessException;
}