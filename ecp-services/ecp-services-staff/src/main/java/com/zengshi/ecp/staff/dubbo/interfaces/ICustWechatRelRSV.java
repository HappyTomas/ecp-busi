package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustWechatRelRespDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 商城用户与微信用户关系接口RSV<br>
 * Date:2016年7月21日下午4:09:59  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface ICustWechatRelRSV {

	/**
	 * 
	 * findCustWechatRel:(查询商城用户与微信用户的关系). <br/> 
	 * 
	 * @author huangxl5 
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public PageResponseDTO<CustWechatRelRespDTO> findCustWechatRel(CustWechatRelReqDTO req) throws BusinessException;
	
	/**
	 * 
	 * saveCustWechatRel:(保存商城用户与微信用户的关系). <br/> 
	 * 
	 * @author huangxl5 
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public long saveCustWechatRel(CustWechatRelReqDTO req) throws BusinessException;
	
	
	/**
	 * 
	 * updateCustWechatRel:(更新商城用户与微信用户的关系). <br/> 
	 * 
	 * @author huangxl5 
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public long updateCustWechatRel(CustWechatRelReqDTO req) throws BusinessException;
	
	/**
	 * 
	 * findCustWechatRelByOpenId:(主要根据wechatId查询). <br/> 
	 * 
	 * @author huangxl5 
	 * @param wechatId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public CustWechatRelRespDTO findCustWechatRelByWechatId(String wechatId) throws BusinessException;
	
	/**
	 * 
	 * deleteCustWechatRel:(通过微信的id，删除用户关系). <br/> 
	 * 
	 * @author huangxl5 
	 * @param wechatId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public long deleteCustWechatRel(String wechatId) throws BusinessException;
	
	/**
	 * 
	 * deleteCustWechatRelBy:(通过staffCode或staffId，删除用户关系). <br/> 
	 * 
	 * @author huangxl5 
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public long deleteCustWechatRelBy(CustWechatRelReqDTO req) throws BusinessException;
}

