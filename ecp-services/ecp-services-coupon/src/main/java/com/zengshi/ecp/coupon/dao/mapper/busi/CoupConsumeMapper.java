package com.zengshi.ecp.coupon.dao.mapper.busi;

import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CoupConsumeMapper {
    Long countByExample(CoupConsumeCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(CoupConsume record) throws DataAccessException;

    int insertSelective(CoupConsume record) throws DataAccessException;

    List<CoupConsume> selectByExample(CoupConsumeCriteria example) throws DataAccessException;

    CoupConsume selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CoupConsume record, @Param("example") CoupConsumeCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CoupConsume record, @Param("example") CoupConsumeCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CoupConsume record) throws DataAccessException;

    int updateByPrimaryKey(CoupConsume record) throws DataAccessException;
}
