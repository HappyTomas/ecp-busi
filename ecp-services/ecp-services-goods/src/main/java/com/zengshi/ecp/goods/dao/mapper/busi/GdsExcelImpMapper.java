package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsExcelImp;
import com.zengshi.ecp.goods.dao.model.GdsExcelImpCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsExcelImpMapper {
    Long countByExample(GdsExcelImpCriteria example) throws DataAccessException;

    int deleteByExample(GdsExcelImpCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long importId) throws DataAccessException;

    int insert(GdsExcelImp record) throws DataAccessException;

    int insertSelective(GdsExcelImp record) throws DataAccessException;

    List<GdsExcelImp> selectByExample(GdsExcelImpCriteria example) throws DataAccessException;

    GdsExcelImp selectByPrimaryKey(Long importId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsExcelImp record, @Param("example") GdsExcelImpCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsExcelImp record, @Param("example") GdsExcelImpCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsExcelImp record) throws DataAccessException;

    int updateByPrimaryKey(GdsExcelImp record) throws DataAccessException;
}
