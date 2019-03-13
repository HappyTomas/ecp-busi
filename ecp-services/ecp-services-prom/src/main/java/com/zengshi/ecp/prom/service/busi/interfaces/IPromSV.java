package com.zengshi.ecp.prom.service.busi.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
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
public interface IPromSV extends IGeneralSQLSV{

    /**
     * 促销信息保存 --验证
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void validProm(PromDTO promDTO) throws BusinessException;

    /**
     * 促销信息--保存草稿
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void saveProm(PromDTO promDTO) throws BusinessException;

    /**
     * 促销信息--提交发布
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromInfo createProm(PromDTO promDTO) throws BusinessException;

    /**
     * 购物车初始化展示 初始化产品
     * 
     * @param ordPromDTO
     * @throws Exception
     * @author huangjx
     */
    public void initOrdPromByGds(OrdPromDTO ordPromDTO) throws BusinessException;

    /**
     * 购物车初始化展示 订单级促销
     * 
     * @param ordPromDTO
     * @throws Exception
     * @author huangjx
     */
    public void initOrdPromByOrder(OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 促销订单 提交审核
     * @param promRuleCheckDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void checkPromOrd(PromRuleCheckDTO promRuleCheckDTO,OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 购物车 实例 单品选择某个促销
     * 
     * @param promId
     * @param gdsId
     * @param skuId
     * @param ordPromDTO
     * @throws Exception
     * @author huangjx
     */
    public void selectPromByGds(Long promId, Long gdsId, Long skuId,
            OrdPromDTO ordPromDTO) throws BusinessException;

    /**
     * 购物车 实例   订单选择某个促销
     * @param promId
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByOrd(Long promId,
            OrdPromDTO ordPromDTO) throws BusinessException;
    
    /**
     * 购物车 每个明细参与促销验证
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @param promRuleCheckDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void promByGds(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 满足哪些促销规则 返回促销规则list
     * @param getPromRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> fullFilPromotionListByGds(PromRuleCheckDTO getPromRuleCheckDTO)
            throws BusinessException;
    /**
     * 足哪些促销规则 返回促销规则list
     * @param getPromRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> fullFilPromotionListBySku(PromRuleCheckDTO getPromRuleCheckDTO) throws BusinessException;

    /**
     * 订单满足哪些促销规则 返回促销规则list
     * @param ordPromDTO
     * @param date
     * @param ordPromId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> fullFilPromotionListByOrd(OrdPromDTO ordPromDTO, Date date,Long ordPromId)
            throws BusinessException;
    
    /**
     * 订单明细 满足哪些促销规则 返回当前明细满足订单促销的list
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @param date
     * @param ordPromId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  OrdPromDetailDTO fullFilPromOrd(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO, Date date,Long ordPromId)
            throws BusinessException;
    /**
     * 获得提醒信息 返回提醒list
     * 
     * @param promId
     * @return
     * @throws Exception
     * @author huangjx
     */
    public PromTypeMsgResponseDTO remindPromMsg(Long promId) throws BusinessException;

    /**
     * 下单提交  回滚
     * @param ordPromDTO
     * @param doAction
     * @throws BusinessException
     * @author huangjx
     */
    public List<Long> savePromOrd(OrdPromDTO ordPromDTO,String doAction)
            throws BusinessException;
    /**
     * 下单提交  回滚
     * @param ordPromListDTO
     * @param doAction
     * @throws BusinessException
     * @author huangjx
     */
    public Map<Long,List<Long>> savePromOrdList(OrdPromListDTO ordPromListDTO,String doAction)
            throws BusinessException;
    
    /**
     * 
     * isRollBack:(判断订单是否已经回滚). <br/> 
     * 
     * @author PJieWin 
     * @param ordPromDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public boolean isRollBack(OrdPromDTO ordPromDTO)throws BusinessException;
    
    /**
     * 促销信息列表
     * @param promRuleCheckDTO
     * @param ifConstCheck
     * @param promClass
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromListRespDTO> listPromInfo(PromRuleCheckDTO promRuleCheckDTO,boolean ifConstCheck,String promClass) throws BusinessException;
    public  GdsPromListDTO listPromInfoNew(PromRuleCheckDTO promRuleCheckDTO,boolean ifConstCheck,String promClass) throws BusinessException;

    /**
     * 促销验证是否免邮
     * 
     * @param promIds
     * @throws Exception
     * @author huangjx
     */
    public boolean checkFreePost(List<Long> promIds) throws BusinessException;
}
