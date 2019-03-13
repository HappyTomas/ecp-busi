package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsCatgCustDisc;
import com.zengshi.ecp.goods.dao.model.GdsCatgCustDiscCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsCatgCustDiscMapper {
    Long countByExample(GdsCatgCustDiscCriteria example) throws DataAccessException;

    int deleteByExample(GdsCatgCustDiscCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsCatgCustDisc record) throws DataAccessException;

    int insertSelective(GdsCatgCustDisc record) throws DataAccessException;

    List<GdsCatgCustDisc> selectByExample(GdsCatgCustDiscCriteria example) throws DataAccessException;

    GdsCatgCustDisc selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsCatgCustDisc record, @Param("example") GdsCatgCustDiscCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsCatgCustDisc record, @Param("example") GdsCatgCustDiscCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsCatgCustDisc record) throws DataAccessException;

    int updateByPrimaryKey(GdsCatgCustDisc record) throws DataAccessException;
}
