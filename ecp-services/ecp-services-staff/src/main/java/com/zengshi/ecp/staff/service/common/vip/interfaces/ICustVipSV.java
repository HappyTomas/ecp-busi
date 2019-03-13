package com.zengshi.ecp.staff.service.common.vip.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustLevelRule;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustLevelRuleReqDTO;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月6日上午10:52:32  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wangbh
 * @version  
 * @since JDK 1.7
 */

public interface ICustVipSV {
    
    /**
     * 
     * queryCustLevelRule:(通过成长值查询会员等级编码). <br/> 
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustLevelRule queryCustLevelRule(CustLevelRuleReqDTO info) throws BusinessException;
    
    
    /**
     * 
     * queryCustLevelMinValueAndMaxValue:(通过会员等级编码查询成长值). <br/> 
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CustLevelRule queryCustLevelMinValueAndMaxValue(CustLevelRuleReqDTO info)throws BusinessException;
    
    
    
    
    /**
     * 
     * queryCustLevelName:(通过等级编码查询会员等级编码名称). <br/> 
     * 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
	public String queryCustLevelName(CustLevelInfoResDTO info) throws BusinessException;
	
	
	
	/**
	 * 
	 * queryValueGap:(查询下一等级的差距值). <br/> 
	 * 
	 * @author wangbh
	 * @param info
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CustLevelInfoResDTO queryValueGap(CustInfoReqDTO info) throws BusinessException;
	
	
	/**
	 * 
	 * queryCustLevelInfo:(通过等级编码查询等级信息). <br/> 
	 * 
	 * @author wangbh
	 * @param dto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public CustLevelInfoResDTO queryCustLevelInfo(CustLevelInfoReqDTO dto) throws BusinessException;
	
	
	
	
	

}
