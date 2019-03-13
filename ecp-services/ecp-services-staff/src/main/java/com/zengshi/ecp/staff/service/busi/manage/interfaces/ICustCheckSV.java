package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import java.lang.reflect.InvocationTargetException;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustAuthstrReqDTO;

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

public interface ICustCheckSV {
    
/**
 * 
 * queryCustCheckList:(查询待审核与审核不通过用户). <br/> 
 * 
 * @author wangbh
 * @param info
 * @return
 * @throws BusinessException 
 * @since JDK 1.7
 */
 
    public PageResponseDTO<CustAuthstrResDTO> queryCustCheckList(CustAuthstrReqDTO info) throws BusinessException;
    /**
     * 
     * queryCustAuthser:(查询单个待审核企业用户). <br/> 
     * 
     * @author wangbh
     * @return
     * @throws BusinessException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     * @since JDK 1.7
     */
    
    public CustAuthstrResDTO queryCustAuthser(CustAuthstrReqDTO info) throws BusinessException;
    
    
    
	/**
	 * 
	 * checkCustToCompanyCust:(企业会员审核通过). <br/> 
	 * 
	 * @author wangbh
	 * @param info
	 * @throws BusinessException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @since JDK 1.7
	 */
    public CustAuthstrResDTO updateCustToCompanyCust(CustAuthstrReqDTO info)  throws BusinessException;
        
    
    /**
     * 
     * checkNoCustToCompanyCust:(企业会员审核不通过). <br/> 
     * 
     * @author wangbh
     * @param info
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateNoCustToCompanyCust(CustAuthstrReqDTO info) throws BusinessException;
    
    
     /**
      * 
      * removeCustAuthstr:(删除待审核表记录). <br/> 
      * 
      * @author wangbh
      * @param dto
      * @throws BusinessException 
      * @since JDK 1.7
      */
    public void removeCustAuthstr(CustAuthstrReqDTO info) throws BusinessException;
    
    
    /**
     * 
     * saveCustAuthstr:(新增企业审核表). <br/> 
     * @author wangbh
     * @param info
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public int saveCustAuthstr(CustAuthstrReqDTO info) throws BusinessException;
    
    
    
    
    
    public int updateCustAuthstr(CustAuthstrReqDTO info) throws BusinessException;
    
}
