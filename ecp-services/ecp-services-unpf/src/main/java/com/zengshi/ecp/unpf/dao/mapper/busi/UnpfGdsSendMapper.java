package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfGdsSendMapper {
    Long countByExample(UnpfGdsSendCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfGdsSend record) throws DataAccessException;

    int insertSelective(UnpfGdsSend record) throws DataAccessException;

    List<UnpfGdsSend> selectByExample(UnpfGdsSendCriteria example) throws DataAccessException;

    UnpfGdsSend selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfGdsSend record, @Param("example") UnpfGdsSendCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfGdsSend record, @Param("example") UnpfGdsSendCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfGdsSend record) throws DataAccessException;

    int updateByPrimaryKey(UnpfGdsSend record) throws DataAccessException;
}