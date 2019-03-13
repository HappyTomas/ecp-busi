package com.zengshi.ecp.unpf.dao.mapper.busi;

import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycHis;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycHisCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfMsgSycHisWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnpfMsgSycHisMapper {
    Long countByExample(UnpfMsgSycHisCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(UnpfMsgSycHisWithBLOBs record) throws DataAccessException;

    int insertSelective(UnpfMsgSycHisWithBLOBs record) throws DataAccessException;

    List<UnpfMsgSycHisWithBLOBs> selectByExampleWithBLOBs(UnpfMsgSycHisCriteria example) throws DataAccessException;

    List<UnpfMsgSycHis> selectByExample(UnpfMsgSycHisCriteria example) throws DataAccessException;

    UnpfMsgSycHisWithBLOBs selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") UnpfMsgSycHisWithBLOBs record, @Param("example") UnpfMsgSycHisCriteria example) throws DataAccessException;

    int updateByExampleWithBLOBs(@Param("record") UnpfMsgSycHisWithBLOBs record, @Param("example") UnpfMsgSycHisCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") UnpfMsgSycHis record, @Param("example") UnpfMsgSycHisCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(UnpfMsgSycHisWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKeyWithBLOBs(UnpfMsgSycHisWithBLOBs record) throws DataAccessException;

    int updateByPrimaryKey(UnpfMsgSycHis record) throws DataAccessException;
}