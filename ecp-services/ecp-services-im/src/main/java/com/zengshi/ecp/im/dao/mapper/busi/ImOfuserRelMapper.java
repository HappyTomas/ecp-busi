package com.zengshi.ecp.im.dao.mapper.busi;

import com.zengshi.ecp.im.dao.model.ImOfuserRel;
import com.zengshi.ecp.im.dao.model.ImOfuserRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImOfuserRelMapper {
    Long countByExample(ImOfuserRelCriteria example) throws DataAccessException;

    int deleteByExample(ImOfuserRelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String staffCode) throws DataAccessException;

    int insert(ImOfuserRel record) throws DataAccessException;

    int insertSelective(ImOfuserRel record) throws DataAccessException;

    List<ImOfuserRel> selectByExample(ImOfuserRelCriteria example) throws DataAccessException;

    ImOfuserRel selectByPrimaryKey(String staffCode) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImOfuserRel record, @Param("example") ImOfuserRelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImOfuserRel record, @Param("example") ImOfuserRelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImOfuserRel record) throws DataAccessException;

    int updateByPrimaryKey(ImOfuserRel record) throws DataAccessException;
}