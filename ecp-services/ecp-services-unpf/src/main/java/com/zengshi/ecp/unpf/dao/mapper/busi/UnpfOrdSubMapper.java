package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfOrdSub;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSubCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfOrdSubMapper {
    Long countByExample(UnpfOrdSubCriteria example) throws DataAccessException;

    int deleteByExample(UnpfOrdSubCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String id) throws DataAccessException;

    int insert(UnpfOrdSub record) throws DataAccessException;

    int insertSelective(UnpfOrdSub record) throws DataAccessException;

    List<UnpfOrdSub> selectByExample(UnpfOrdSubCriteria example) throws DataAccessException;

    UnpfOrdSub selectByPrimaryKey(String id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfOrdSub record, @Param("example") UnpfOrdSubCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfOrdSub record, @Param("example") UnpfOrdSubCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfOrdSub record) throws DataAccessException;

    int updateByPrimaryKey(UnpfOrdSub record) throws DataAccessException;
}