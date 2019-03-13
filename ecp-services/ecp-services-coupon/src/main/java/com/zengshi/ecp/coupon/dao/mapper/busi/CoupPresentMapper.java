package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupPresent;
import com.zengshi.ecp.coupon.dao.model.CoupPresentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupPresentMapper {
    Long countByExample(CoupPresentCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupPresent record) throws DataAccessException;

    int insertSelective(CoupPresent record) throws DataAccessException;

    List<CoupPresent> selectByExample(CoupPresentCriteria example) throws DataAccessException;

    CoupPresent selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupPresent record, @Param("example") CoupPresentCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupPresent record, @Param("example") CoupPresentCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupPresent record) throws DataAccessException;

    int updateByPrimaryKey(CoupPresent record) throws DataAccessException;
}
