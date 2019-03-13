package com.zengshi.ecp.im.dao.mapper.common;

import com.zengshi.ecp.im.dao.model.ImStaffHotline;
import com.zengshi.ecp.im.dao.model.ImStaffHotlineCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImStaffHotlineMapper {
    Long countByExample(ImStaffHotlineCriteria example) throws DataAccessException;

    int deleteByExample(ImStaffHotlineCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ImStaffHotline record) throws DataAccessException;

    int insertSelective(ImStaffHotline record) throws DataAccessException;

    List<ImStaffHotline> selectByExample(ImStaffHotlineCriteria example) throws DataAccessException;

    ImStaffHotline selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImStaffHotline record, @Param("example") ImStaffHotlineCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImStaffHotline record, @Param("example") ImStaffHotlineCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImStaffHotline record) throws DataAccessException;

    int updateByPrimaryKey(ImStaffHotline record) throws DataAccessException;
}