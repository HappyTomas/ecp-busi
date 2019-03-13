package com.zengshi.ecp.im.dubbo.interfaces;

import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * 获取用户与openfire信息
 * @author Administrator
 *
 */
public interface IStaffHotlineRSV{
	
	
	    /**
	     * 获取用户与openfire数据
	     * @param dto
	     * @return
	     * @throws BusinessException
	     */
        public ImStaffHotlineResDTO getStaffHotline(ImStaffHotlineReqDTO dto) throws BusinessException;
        
    	/**
    	 *  添加客服人员
    	 * @param dto
    	 * @return
    	 * @throws BusinessException
    	 */
    	public int addHotlineStaff(ImStaffHotlineReqDTO dto)throws BusinessException;
    	
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
    	 * 客服登出接口
    	 * @param dto
    	 * @return
    	 * @throws BusinessException
    	 */
    	public int hotlineInfoOut(SessionReqDTO dto)throws BusinessException;
	
}

