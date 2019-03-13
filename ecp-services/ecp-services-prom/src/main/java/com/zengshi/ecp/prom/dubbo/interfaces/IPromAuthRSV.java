package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
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
public interface IPromAuthRSV {
 
    /**
     * 促销授权店铺 保存
     * @param promType4ShopDTOList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromType4ShopDTO> savePromType4Shop(List<PromType4ShopDTO> promType4ShopDTOList)
            throws BusinessException;

    /**
     * 促销授权店铺 编辑保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromType4ShopById(PromType4ShopDTO promType4ShopDTO) throws BusinessException;

    /**
     * 促销类型 授权店铺列表
     * @param queryPromType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromType4ShopResponseDTO> queryPromType4ShopForPage(
            QueryPromType4ShopDTO queryPromType4ShopDTO) throws BusinessException;

    /**
     * 根据id活动 授权店铺信息
     * @param promType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromType4ShopResponseDTO queryPromType4ShopById(PromType4ShopDTO promType4ShopDTO) throws BusinessException;
    /**
     * 编辑状态
     * @param id
     * @param status
     * @throws BusinessException
     * @author huangjx
     */
    public void updateStatusById(PromType4ShopDTO promType4ShopDTO) throws BusinessException;
}
