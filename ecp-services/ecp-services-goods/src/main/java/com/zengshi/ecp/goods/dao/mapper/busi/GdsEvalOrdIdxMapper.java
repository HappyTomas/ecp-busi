package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEvalOrdIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalOrdIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalOrdIdxMapper {
    Long countByExample(GdsEvalOrdIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalOrdIdxCriteria example) throws DataAccessException;

    int insert(GdsEvalOrdIdx record) throws DataAccessException;

    int insertSelective(GdsEvalOrdIdx record) throws DataAccessException;

    List<GdsEvalOrdIdx> selectByExample(GdsEvalOrdIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEvalOrdIdx record, @Param("example") GdsEvalOrdIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEvalOrdIdx record, @Param("example") GdsEvalOrdIdxCriteria example) throws DataAccessException;
}
