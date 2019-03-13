package com.zengshi.ecp.im.dao.mapper.busi;

import com.zengshi.ecp.im.dao.model.ImCustservSatisfyInfo;
import com.zengshi.ecp.im.dao.model.ImCustservSatisfyInfoCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface ImCustservSatisfyInfoMapper {
    Long countByExample(ImCustservSatisfyInfoCriteria example) throws DataAccessException;

    int deleteByExample(ImCustservSatisfyInfoCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(ImCustservSatisfyInfo record) throws DataAccessException;

    int insertSelective(ImCustservSatisfyInfo record) throws DataAccessException;

    List<ImCustservSatisfyInfo> selectByExample(ImCustservSatisfyInfoCriteria example) throws DataAccessException;

    ImCustservSatisfyInfo selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") ImCustservSatisfyInfo record, @Param("example") ImCustservSatisfyInfoCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") ImCustservSatisfyInfo record, @Param("example") ImCustservSatisfyInfoCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(ImCustservSatisfyInfo record) throws DataAccessException;

    int updateByPrimaryKey(ImCustservSatisfyInfo record) throws DataAccessException;
}