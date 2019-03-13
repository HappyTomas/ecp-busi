package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfSkuSend;
import com.zengshi.ecp.unpf.dao.model.UnpfSkuSendCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfSkuSendMapper {
    Long countByExample(UnpfSkuSendCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfSkuSend record) throws DataAccessException;

    int insertSelective(UnpfSkuSend record) throws DataAccessException;

    List<UnpfSkuSend> selectByExample(UnpfSkuSendCriteria example) throws DataAccessException;

    UnpfSkuSend selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfSkuSend record, @Param("example") UnpfSkuSendCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfSkuSend record, @Param("example") UnpfSkuSendCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfSkuSend record) throws DataAccessException;

    int updateByPrimaryKey(UnpfSkuSend record) throws DataAccessException;
}