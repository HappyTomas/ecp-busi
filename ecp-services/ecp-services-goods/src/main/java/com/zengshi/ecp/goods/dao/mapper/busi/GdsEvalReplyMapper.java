package com.zengshi.ecp.goods.dao.mapper.busi;

import com.zengshi.ecp.goods.dao.model.GdsEvalReply;
import com.zengshi.ecp.goods.dao.model.GdsEvalReplyCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GdsEvalReplyMapper {
    Long countByExample(GdsEvalReplyCriteria example) throws DataAccessException;

    int deleteByExample(GdsEvalReplyCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(GdsEvalReply record) throws DataAccessException;

    int insertSelective(GdsEvalReply record) throws DataAccessException;

    List<GdsEvalReply> selectByExample(GdsEvalReplyCriteria example) throws DataAccessException;

    GdsEvalReply selectByPrimaryKey(Long id) throws DataAccessException;

    int updateByExampleSelective(@Param("record") GdsEvalReply record, @Param("example") GdsEvalReplyCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") GdsEvalReply record, @Param("example") GdsEvalReplyCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(GdsEvalReply record) throws DataAccessException;

    int updateByPrimaryKey(GdsEvalReply record) throws DataAccessException;
}
