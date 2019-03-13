package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsMediaLib;
import com.zengshi.ecp.goods.dao.model.GdsMediaLibCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsMediaLibMapper {
    Long countByExample(GdsMediaLibCriteria example) throws DataAccessException;

    int deleteByExample(GdsMediaLibCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsMediaLib record) throws DataAccessException;

    int insertSelective(GdsMediaLib record) throws DataAccessException;

    List<GdsMediaLib> selectByExample(GdsMediaLibCriteria example) throws DataAccessException;

    GdsMediaLib selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsMediaLib record, @Param("example") GdsMediaLibCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsMediaLib record, @Param("example") GdsMediaLibCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsMediaLib record) throws DataAccessException;

    int updateByPrimaryKey(GdsMediaLib record) throws DataAccessException;
}
