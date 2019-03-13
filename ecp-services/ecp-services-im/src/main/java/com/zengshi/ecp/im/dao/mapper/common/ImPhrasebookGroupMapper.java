package com.zengshi.ecp.im.dao.mapper.common;

import com.zengshi.ecp.im.dao.model.ImPhrasebookGroup;
import com.zengshi.ecp.im.dao.model.ImPhrasebookGroupCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImPhrasebookGroupMapper {
    Long countByExample(ImPhrasebookGroupCriteria example) throws DataAccessException;

    int deleteByExample(ImPhrasebookGroupCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ImPhrasebookGroup record) throws DataAccessException;

    int insertSelective(ImPhrasebookGroup record) throws DataAccessException;

    List<ImPhrasebookGroup> selectByExample(ImPhrasebookGroupCriteria example) throws DataAccessException;

    ImPhrasebookGroup selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImPhrasebookGroup record, @Param("example") ImPhrasebookGroupCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImPhrasebookGroup record, @Param("example") ImPhrasebookGroupCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImPhrasebookGroup record) throws DataAccessException;

    int updateByPrimaryKey(ImPhrasebookGroup record) throws DataAccessException;
}