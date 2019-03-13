package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdExpressDetail;
import com.zengshi.ecp.order.dao.model.OrdExpressDetailCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdExpressDetailMapper {
    Long countByExample(OrdExpressDetailCriteria example) throws DataAccessException;

    int deleteByExample(OrdExpressDetailCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdExpressDetail record) throws DataAccessException;

    int insertSelective(OrdExpressDetail record) throws DataAccessException;

    List<OrdExpressDetail> selectByExample(OrdExpressDetailCriteria example) throws DataAccessException;

    OrdExpressDetail selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdExpressDetail record, @Param("example") OrdExpressDetailCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdExpressDetail record, @Param("example") OrdExpressDetailCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdExpressDetail record) throws DataAccessException;

    int updateByPrimaryKey(OrdExpressDetail record) throws DataAccessException;
}