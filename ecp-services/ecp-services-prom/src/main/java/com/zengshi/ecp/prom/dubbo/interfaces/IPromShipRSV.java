package com.zengshi.ecp.prom.dubbo.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromPostDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPostRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromShipRSV {
  
    /**
     * 验证是否 免邮
     * 1 免邮
     * 非1 不免邮 
     * @param promShipDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromShipRespDTO qryPromShip(PromShipDTO promShipDTO) throws BusinessException;
    /**
     * 验证是否 免邮
     * 1 免邮
     * 非1 不免邮 
     * @param promPostDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPostRespDTO checkIfFreePost(PromPostDTO promPostDTO) throws BusinessException;
    
}
