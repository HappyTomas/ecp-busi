package com.zengshi.ecp.order.dao.mapper.busi.manual;

import com.zengshi.ecp.order.dao.model.OrdFileImport;
import com.zengshi.ecp.order.dao.model.OrdFileImportCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OrdFileImportUalMapper {

    int updateById(OrdFileImport record) throws DataAccessException;
}