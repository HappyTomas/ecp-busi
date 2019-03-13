package com.zengshi.ecp.prom.service.busi.group.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
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
public interface IPromGroupSV extends IGeneralSQLSV{

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
     * 获得主题促销列表 分页 ifCalShopCnt 非空表示需要计算参与店铺数量
     * @param promGroupDTO
     * @param ifCalShopCnt
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromGroupResponseDTO> queryPromGroupForPage(PromGroupDTO promGroupDTO,String ifCalShopCnt)
            throws BusinessException;
    
    /**
     * 获得主题促销列表 ifCalShopCnt 非空表示需要计算参与店铺数量
     * @param promGroupDTO
     * @param ifCalShopCnt
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGroupResponseDTO> queryPromGroupForList(PromGroupDTO promGroupDTO,String ifCalShopCnt)
            throws BusinessException;

    /**
     * 获得某个主题下 店铺参加列表
     * 
     * @param groupId
     *            必传
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromGroup4Shop(Long groupId, PromInfoDTO promInfoDTO)
            throws BusinessException;

    /**
     * 获得主题组对象
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromGroupResponseDTO queryPromGroupById(Long id) throws BusinessException;

}
