package com.zengshi.ecp.staff.dao.mapper.busi;

import com.zengshi.ecp.staff.dao.model.CustWechatRel;
import com.zengshi.ecp.staff.dao.model.CustWechatRelCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface CustWechatRelMapper {
    Long countByExample(CustWechatRelCriteria example) throws DataAccessException;

    int deleteByExample(CustWechatRelCriteria example) throws DataAccessException;

    int deleteByPrimaryKey(String wechatId) throws DataAccessException;

    int insert(CustWechatRel record) throws DataAccessException;

    int insertSelective(CustWechatRel record) throws DataAccessException;

    List<CustWechatRel> selectByExample(CustWechatRelCriteria example) throws DataAccessException;

    CustWechatRel selectByPrimaryKey(String wechatId) throws DataAccessException;

    int updateByExampleSelective(@Param("record") CustWechatRel record, @Param("example") CustWechatRelCriteria example) throws DataAccessException;

    int updateByExample(@Param("record") CustWechatRel record, @Param("example") CustWechatRelCriteria example) throws DataAccessException;

    int updateByPrimaryKeySelective(CustWechatRel record) throws DataAccessException;

    int updateByPrimaryKey(CustWechatRel record) throws DataAccessException;
}