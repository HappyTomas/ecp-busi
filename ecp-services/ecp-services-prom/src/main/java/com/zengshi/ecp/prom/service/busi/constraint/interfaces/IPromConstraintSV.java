package com.zengshi.ecp.prom.service.busi.constraint.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
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
public interface IPromConstraintSV extends IGeneralSQLSV{
    /**
     * 能否参与促销，验证
     * 
     * @param promId
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean check(Long promId, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 能否参与促销，验证
     * 
     * @param promId
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean checkSolr(Long promId, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;

}
