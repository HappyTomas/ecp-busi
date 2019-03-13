package com.zengshi.ecp.prom.service.busi.discountRule.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
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
public interface IPromDiscountRuleSV extends IGeneralSQLSV{
 
	/**
     * 是否免邮
     * @param promId
     * @param ifFreePost
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean ifFreePost(Long promId,String ifFreePost) throws BusinessException;
    /**
     * 是否免邮
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @param ifThrows
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean ifFreePost(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromShipDTO promShipDTO) throws BusinessException;
    /**
     * 是否满足优惠规则 (单品促销类型)
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @param ifThrows
     * @param promTypeMsgResponseDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean isFulFilPromByGds(OrdPromDTO ordProDTO, OrdPromDetailDTO ordPromDetailDTO,
            String constraintStr,String ifThrows,PromTypeMsgResponseDTO promTypeMsgResponseDTO) throws BusinessException;
 
    /**
     * 计算优惠信息
     * @param ordProDTO
     * @param ordPromDetailDTO
     * @param constraintStr
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromDiscountDTO calculatePromotion(OrdPromDTO ordProDTO,
            OrdPromDetailDTO ordPromDetailDTO, String constraintStr) throws BusinessException;

    /**
     * 促销信息录入-优惠规则,是否需要验证
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean needToVerified(PromDTO promDTO) throws BusinessException;
 
    /**
     * 促销信息录入-优惠规则验证  例如订单满XX元 送YY券 验证规则
     * @param promDiscountRuleStr
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void valid(String promDiscountRuleStr,PromDTO promDTO) throws BusinessException;
    /**
     * 赠送 积分、 优惠券、 赠品列表
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 赠品列表
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromGiftDTO> promGiftList(Long promId) throws BusinessException;
}
