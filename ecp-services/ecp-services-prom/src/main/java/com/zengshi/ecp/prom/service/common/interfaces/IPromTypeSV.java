package com.zengshi.ecp.prom.service.common.interfaces;

import java.util.List;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
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
public interface IPromTypeSV{
    /**
     * 促销类型
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromTypeByDB(String promTypeCode) throws BusinessException;

    /**
     * 促销类型
     * 
     * @param promDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeResponseDTO queryPromTypeByCode(String promTypeCode) throws BusinessException;

    /**
     * 促销类型列表
     * 
     * @param promTypeDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public List<PromTypeResponseDTO> queryPromTypeList(PromTypeDTO promTypeDTO)
            throws BusinessException;

}
