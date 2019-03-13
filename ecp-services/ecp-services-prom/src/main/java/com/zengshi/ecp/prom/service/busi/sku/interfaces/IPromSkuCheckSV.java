package com.zengshi.ecp.prom.service.busi.sku.interfaces;

import java.util.List;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
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
public interface IPromSkuCheckSV extends IGeneralSQLSV{

    /**
     * 促销 商品是否需要验证
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean isCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;

    /**
     * 促销 商品 能否参加促销
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 促销商品验证
     * skuId skuId非空需要验证gdsId+skuId，否则验证gdsId
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean gdsCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;

    /**
     * 促销列表
     * @param promRuleCheckDTO
     * @param ifConstCheck
     * @param promClass
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listGdsPromInfo(PromRuleCheckDTO promRuleCheckDTO,boolean ifConstCheck,String promClass) throws BusinessException;

    /**
     * 参加促销列表
     * @param promSku
     * @param date
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromList(PromSku promSku, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 促销基本信息验证
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean checkPromInfo(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 促销黑名单验证
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean checkBlackLimit(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
}
