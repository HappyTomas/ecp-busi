package com.zengshi.ecp.prom.dubbo.interfaces;

import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
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
public interface IPromGroupRSV {

    /**
     * 主题促销保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroup(PromGroupDTO promGroupDTO) throws BusinessException;

    /**
     * 主题促销编辑保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveEditPromGroup(PromGroupDTO promGroupDTO) throws BusinessException;

    /**
     * 主题促销 提交
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void createPromGroup(PromGroupDTO promGroupDTO) throws BusinessException;

    /**
     * 主题促销 编辑提交
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void createEditPromGroup(PromGroupDTO promGroupDTO) throws BusinessException;

    /**
     * 获得主题促销列表
     * @param promGroupDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromGroupResponseDTO> queryPromGroupList(PromGroupDTO promGroupDTO)
            throws BusinessException;
    /**
     * 获得某个主题下 店铺参加列表
     * @param groupId
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromGroup4Shop(Long groupId, PromInfoDTO promInfoDTO)
            throws BusinessException;
    /**
     * 获得主题组对象
     * @param promGroupDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromGroupResponseDTO queryPromGroupById(PromGroupDTO promGroupDTO) throws BusinessException;
}
