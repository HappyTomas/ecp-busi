package com.zengshi.ecp.prom.service.busi.group.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
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
public interface IPromGroupChkSV extends IGeneralSQLSV{

    /**
     * 保存促销审核
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroupChk(PromChkDTO promChkDTO) throws BusinessException;

    /**
     * 促销审核信息
     * 
     * @param id
     * @throws BusinessException
     * @author huangjx
     */
    public PromChkResponseDTO queryPromGroupChkById(Long id) throws BusinessException;
    
    /**
     * 促销审核信息
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromChkResponseDTO> queryPromGroupChkByPromId(Long promId) throws BusinessException;

}
