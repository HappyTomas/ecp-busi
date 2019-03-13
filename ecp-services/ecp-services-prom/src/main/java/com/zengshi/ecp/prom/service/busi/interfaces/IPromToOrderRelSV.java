package com.zengshi.ecp.prom.service.busi.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-prom-server <br>
 * Description: <br>
 * Date:2016-2-29上午10:47:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 保存促销与订单关系信息类
 * 
 */
public interface IPromToOrderRelSV {

    public int saveRelation(OrdPromDTO ordPromDTO) throws BusinessException;
    public int deleteRelation(OrdPromDTO ordPromDTO) throws BusinessException;
    public boolean isRollBack(OrdPromDTO ordPromDTO)throws BusinessException;
}

