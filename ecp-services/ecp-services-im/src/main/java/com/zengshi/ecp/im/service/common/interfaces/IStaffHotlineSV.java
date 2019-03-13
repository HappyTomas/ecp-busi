package com.zengshi.ecp.im.service.common.interfaces;

import java.util.List;

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
public interface IStaffHotlineSV extends IGeneralSQLSV{
	
	/**
	 * 获取客服编码
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public ImStaffHotlineResDTO getHotlineByStaff(ImStaffHotlineReqDTO dto)throws BusinessException;
	
	/**
	 * 获取客服列表（提供给队列使用）
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public List<ImStaffHotlineResDTO> getHotlineByStaffList(ImStaffHotlineReqDTO dto)throws BusinessException;
	
	
	/**
	 *  添加客服人员
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public int addHotlineStaff(ImStaffHotlineReqDTO dto)throws BusinessException;
	
	
	/**
	 *  获取会话内容
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public SessionResDTO getSession(SessionReqDTO dto)throws BusinessException;
	
	
	
	/**
	 * 获取会话列表
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public List<SessionResDTO> getSessionList(SessionReqDTO dto)throws BusinessException;
	
	
	/**
	 * 获取客服列表
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public PageResponseDTO<ImHotlineInfoResDTO> getHotlineList(ImHotlineInfoReqDTO dto)throws BusinessException;
	
	
	
	/**
	 * 修改客服状态
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public int updateHotlineInfo(ImHotlineInfoReqDTO dto)throws BusinessException;
	
	
	
	/**
	 * 修改会话状态
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public int updateSession(SessionReqDTO dto)throws BusinessException;
	
	
	/**
	 * 修改客服在线状态
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public int updateHotlineOnlineStart(ImStaffHotlineReqDTO dto)throws BusinessException;



}

