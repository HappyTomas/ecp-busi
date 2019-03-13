/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseExpressRSV.java 
 * Package Name:com.zengshi.ecp.sys.dubbo.interfaces 
 * Date:2015-9-2上午10:20:21 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 物流相关的配置信息<br>
 * Date:2015-9-2上午10:20:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseExpressRSV {
    
    /**
     * 
     * fetchExpressInfo: 根据ID获取 物流信息 <br/>  
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseExpressRespDTO fetchExpressInfo(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * fetchAllExpressInfo: 获取所有物流信息 <br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseExpressRespDTO> fetchAllExpressInfo(BaseExpressReqDTO reqDto) throws BusinessException;
    
    
    /**
     * 
     * fetchActiveExpressInfo: <br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseExpressRespDTO> fetchActiveExpressInfo(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * fetchExpressInfo: 分页获取物流信息<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<BaseExpressRespDTO> fetchExpressInfoByPage(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * createExpress: 创建物流信息<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long createExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * createExpress: 修改物流信息<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long updateExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * createExpress: 注销物流信息<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long removeExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * activeExpress: 启用物流信息<br/>  
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long activeExpress(BaseExpressReqDTO reqDto) throws BusinessException;

}

