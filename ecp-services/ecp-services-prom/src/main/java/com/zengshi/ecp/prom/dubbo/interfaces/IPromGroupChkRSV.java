package com.zengshi.ecp.prom.dubbo.interfaces;
import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkRespPageDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromGroupChkDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromGroupChkRSV {

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
     * @param promChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromChkResponseDTO queryPromGroupChkById(PromChkDTO promChkDTO) throws BusinessException;
    /**
     * 促销编码 获得审核列表
     * @param promChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromChkResponseDTO> queryPromGroupChkByPromId(PromChkDTO promChkDTO) throws BusinessException;
    /**
     * 主题促销审核列表
     * @param queryPromGroupChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromChkRespPageDTO> listPromInfoByPromGroup(QueryPromGroupChkDTO queryPromGroupChkDTO)
            throws BusinessException;
    
}
