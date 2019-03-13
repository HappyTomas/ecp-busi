package com.zengshi.ecp.search.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.search.dubbo.dto.SecRedirectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecRedirectRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:00:18  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
public interface ISecRedirectRSV{
    
    /**
     * 保存重定向配置信息
     * @param secRedirectReqDTO
     * @return
     */
    public long saveSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;
    
    /**
     * 删除重定向配置信息
     * @param secRedirectReqDTO
     * @throws BusinessException
     */
    public void deleteSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;
    
    /**
     * 更新重定向配置信息
     * @param secRedirectReqDTO
     * @throws BusinessException
     */
    public void updateSecRedirect(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;
    
    /**
     * 根据Id查询获取指定id的重定向配置信息
     * @param secRedirectReqDTO
     * @return
     * @throws BusinessException
     */
    public SecRedirectRespDTO querySecRedirectById(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;
    
    /**
     * 获取所有重定向配置信息
     * @return
     * @throws BusinessException
     */
    public List<SecRedirectRespDTO> listSecRedirect() throws BusinessException;
    
    /**
     * 分页获取重定向配置信息
     * @param secRedirectReqDTO
     * @return
     * @throws BusinessException
     */
//    public List<SecRedirect> querySecRedirectPage(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;
    
    /**
     * 分页获取重定向配置信息
     * @param secRedirectReqDTO
     * @return
     * @throws BusinessException
     */
    public PageResponseDTO<SecRedirectRespDTO> querySecRedirectPage(SecRedirectReqDTO secRedirectReqDTO) throws BusinessException;

}

