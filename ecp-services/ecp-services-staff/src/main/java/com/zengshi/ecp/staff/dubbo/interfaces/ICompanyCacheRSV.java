package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.Map;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;

public interface ICompanyCacheRSV {

   public Map<Long,CompanyInfoResDTO> getCacheCompany() throws BusinessException;
}
