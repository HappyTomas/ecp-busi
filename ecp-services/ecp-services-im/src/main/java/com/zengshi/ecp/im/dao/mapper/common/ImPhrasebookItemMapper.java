package com.zengshi.ecp.im.dao.mapper.common;

import com.zengshi.ecp.im.dao.model.ImPhrasebookItem;
import com.zengshi.ecp.im.dao.model.ImPhrasebookItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImPhrasebookItemMapper {
    Long countByExample(ImPhrasebookItemCriteria example) throws DataAccessException;

    int deleteByExample(ImPhrasebookItemCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ImPhrasebookItem record) throws DataAccessException;

    int insertSelective(ImPhrasebookItem record) throws DataAccessException;

    List<ImPhrasebookItem> selectByExample(ImPhrasebookItemCriteria example) throws DataAccessException;

    ImPhrasebookItem selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImPhrasebookItem record, @Param("example") ImPhrasebookItemCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImPhrasebookItem record, @Param("example") ImPhrasebookItemCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImPhrasebookItem record) throws DataAccessException;

    int updateByPrimaryKey(ImPhrasebookItem record) throws DataAccessException;
}