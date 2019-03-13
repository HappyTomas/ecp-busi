package com.zengshi.ecp.prom.service.busi.constraint.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
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
public interface IPromConstraintCheckSV extends IGeneralSQLSV{

    /**
     * 能否参与促销，是否需要验证
     * 
     * @param promConstaintDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean isCheck(PromConstraintDTO promConstaintDTO) throws BusinessException;

    /**
     * 能否参与促销，验证
     * 
     * @param promConstaintDTO
     * @param promRuleCheckDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public boolean check(PromConstraintDTO promConstaintDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;

    /**
     * 获得促销设置条件
     * 
     * @param promId
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromConstraintDTO getPromConstaintInfo(Long promId) throws BusinessException;
}
