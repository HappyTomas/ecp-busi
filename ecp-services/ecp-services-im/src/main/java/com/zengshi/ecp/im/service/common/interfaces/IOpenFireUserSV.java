package com.zengshi.ecp.im.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.im.dao.model.ofUser;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionResDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;



/**
 * 获取会员关系
 * @author wangbh
 *
 */
public interface IOpenFireUserSV extends IGeneralSQLSV{
	
	
	/**
	 * 新增openfire用户
	 * @param ofUser
	 * @return
	 * @throws BusinessException
	 */
	public int addOfUser(String userName, String passWord)throws BusinessException;



}

