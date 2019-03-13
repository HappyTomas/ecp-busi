package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEvalGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalGdsIdxCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalGdsIdxMapper {
    Long countByExample(GdsEvalGdsIdxCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalGdsIdxCriteria example) throws DataAccessException;

    int insert(GdsEvalGdsIdx record) throws DataAccessException;

    int insertSelective(GdsEvalGdsIdx record) throws DataAccessException;

    List<GdsEvalGdsIdx> selectByExample(GdsEvalGdsIdxCriteria example) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEvalGdsIdx record, @Param("example") GdsEvalGdsIdxCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEvalGdsIdx record, @Param("example") GdsEvalGdsIdxCriteria example) throws DataAccessException;
}
