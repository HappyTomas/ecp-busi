package com.zengshi.ecp.prom.service.busi.prom.interfaces;

import java.util.Date;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromInfoCheckSV extends IGeneralSQLSV{

    /**
     * 促销基本信息，是否需要验证
     * 
     * @param gdsId
     * @param skuId
     * @param date
     * @param promInfoDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean isCheck(Long gdsId, Long skuId, Date date, PromInfoDTO promInfoDTO)
            throws BusinessException;

    /**
     * 促销基本信息，验证
     * 
     * @param gdsId
     * @param skuId
     * @param date
     * @param promInfoDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean check(Long gdsId, Long skuId, Date date, PromInfoDTO promInfoDTO)
            throws BusinessException;

}
