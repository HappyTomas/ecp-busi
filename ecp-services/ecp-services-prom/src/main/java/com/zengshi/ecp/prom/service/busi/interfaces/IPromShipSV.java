package com.zengshi.ecp.prom.service.busi.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromShipSV extends IGeneralSQLSV{
     
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
}
