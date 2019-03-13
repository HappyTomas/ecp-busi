package com.zengshi.ecp.order.dao.mapper.busi;

import com.zengshi.ecp.order.dao.model.OrdBackPic;
import com.zengshi.ecp.order.dao.model.OrdBackPicCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface OrdBackPicMapper {
    Long countByExample(OrdBackPicCriteria example) throws DataAccessException;

    int deleteByExample(OrdBackPicCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(OrdBackPic record) throws DataAccessException;

    int insertSelective(OrdBackPic record) throws DataAccessException;

    List<OrdBackPic> selectByExample(OrdBackPicCriteria example) throws DataAccessException;

    OrdBackPic selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") OrdBackPic record, @Param("example") OrdBackPicCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") OrdBackPic record, @Param("example") OrdBackPicCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(OrdBackPic record) throws DataAccessException;

    int updateByPrimaryKey(OrdBackPic record) throws DataAccessException;
}