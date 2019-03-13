package com.zengshi.ecp.im.service.busi.interfaces;

import com.zengshi.ecp.im.dubbo.dto.CustInfoImResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-im-server <br>
 * Description: openfire用户相关的接口<br>
 * Date:2016年8月10日下午4:10:45  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
public interface IOfuserSV{
	
	/**
	 * 
	 * saveOfuser:(保存of用户与第三方系统用户的关系). <br/> 
	 * 同时保存一份索引表，方便后续通过of用户获第三方用户信息
	 * @author huangxl5 
	 * @param req
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public String saveOfuser(ImOfuserRelReqDTO req) throws BusinessException;
	
	/**
	 * 
	 * findCustByOfuser:(通过openfire用户账号，获取第三方用户信息). <br/> 
	 * 
	 * @author huangxl5 
	 * @param ofStaffCode
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	public CustInfoImResDTO findCustByOfuser(String ofStaffCode) throws BusinessException;
	
	/**
	 * 通过openfire用户获取用户
	 * @param dto
	 * @return
	 * @throws BusinessException
	 */
	public ImOfuserRelResDTO findofuserByCust(ImOfuserRelReqDTO dto) throws BusinessException;

	
}

