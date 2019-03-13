package com.zengshi.ecp.prom.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromMatchSV extends IGeneralSQLSV{
    
    /**
     * 搭配商品 保存
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMatchSku(PromDTO promDTO) throws BusinessException;
 
    /**
     * 获得搭配单品信息
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchDTO> queryMatchList(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    /**
     * 获得搭配促销列表
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> queryMatchPromList(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    /**
     * 搭配单品列表查询
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchSkuDTO> queryMatchSkuList(PromMatchSkuDTO promMatchSkuDTO) throws BusinessException;
    
    /**
     * 搭配商品列表 分页功能
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromMatchSkuRespDTO> queryPromMatchSkuForPage(PromMatchSkuDTO promMatchSkuDTO)
            throws BusinessException;
    /**
     * 促销编码获得搭配信息
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchSkuDTO> queryMatchSkuListByPromId(Long promId) throws BusinessException;
}
