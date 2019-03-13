package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfMsgSyc;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfMsgSycMapper {
    Long countByExample(UnpfMsgSycCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfMsgSycWithBLOBs record) throws DataAccessException;

    int insertSelective(UnpfMsgSycWithBLOBs record) throws DataAccessException;

    List<UnpfMsgSycWithBLOBs> selectByExampleWithBLOBs(UnpfMsgSycCriteria example) throws DataAccessException;

    List<UnpfMsgSyc> selectByExample(UnpfMsgSycCriteria example) throws DataAccessException;

    UnpfMsgSycWithBLOBs selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfMsgSycWithBLOBs record, @Param("example") UnpfMsgSycCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") UnpfMsgSycWithBLOBs record, @Param("example") UnpfMsgSycCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfMsgSyc record, @Param("example") UnpfMsgSycCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfMsgSycWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(UnpfMsgSycWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(UnpfMsgSyc record) throws DataAccessException;
}