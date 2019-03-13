package com.zengshi.ecp.im.dao.mapper.busi;

import com.zengshi.ecp.im.dao.model.ImOfuserRelIdx;
import com.zengshi.ecp.im.dao.model.ImOfuserRelIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImOfuserRelIdxMapper {
    Long countByExample(ImOfuserRelIdxCriteria example) throws DataAccessException;

    int deleteByExample(ImOfuserRelIdxCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String ofStaffCode) throws DataAccessException;

    int insert(ImOfuserRelIdx record) throws DataAccessException;

    int insertSelective(ImOfuserRelIdx record) throws DataAccessException;

    List<ImOfuserRelIdx> selectByExample(ImOfuserRelIdxCriteria example) throws DataAccessException;

    ImOfuserRelIdx selectByPrimaryKey(String ofStaffCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImOfuserRelIdx record, @Param("example") ImOfuserRelIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImOfuserRelIdx record, @Param("example") ImOfuserRelIdxCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImOfuserRelIdx record) throws DataAccessException;

    int updateByPrimaryKey(ImOfuserRelIdx record) throws DataAccessException;
}