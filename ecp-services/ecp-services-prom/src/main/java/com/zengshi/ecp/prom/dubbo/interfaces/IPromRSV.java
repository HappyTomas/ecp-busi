package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCommDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromRSV {
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
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void createProm(PromDTO promDTO) throws BusinessException;
    /**
     * 获得参加促销列表
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromByGds(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;

    /**
     * 获得参加促销列表
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromBySku(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;

    /**
     *  是否满足促销规则 返回成功、失败
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean isFullfilPromotion(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 下单提交 验证
     * @param promRuleCheckDTO
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void checkPromOrd(PromRuleCheckDTO promRuleCheckDTO,OrdPromDTO ordPromDTO)
            throws BusinessException;
    /**
     * @function 购物车初始化展示 初始化产品和订单级促销
     * @param OrdPromVo
     *            返回 OrdPromVo
     * @return
     */
    public OrdPromDTO initOrdProm(OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 购物车初始化展示 初始化产品和订单级促销
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromRespDTO initOrdPromMap(OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 购物车初始化展示 初始化产品和订单级促销
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CartPromRespDTO initCartPromMap(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException;
  
    /**
     * 购物车页面选择操作接口
     * 1、订单促销选择 参与 不参与  
     * 2、明细变更
     * @param rOrdCartChangeRequest
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CartPromBeanRespDTO selectProm(ROrdCartChangeRequest rOrdCartChangeRequest) throws BusinessException;
    /**
     * 购物车 实例 单品选择某个促销
     * @param queryPromDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByGds(OrdPromDetailDTO ordPromDetailDTO,
            OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 购物车 实例 订单选择某个促销
     * @param queryPromDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByOrd(Long promId,
            OrdPromDTO ordPromDTO) throws BusinessException;
    /**
     * 促销提醒信息
     * @param promCommDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeMsgResponseDTO remindProm(PromCommDTO promCommDTO) throws BusinessException;

    /**
     * 获得搭配商品
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchDTO> queryMatchList(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException;
    /**
     * 促销基本信息 发布
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void publishProm(PromCommDTO promCommDTO) throws BusinessException;
    
    /**
     * 促销基本信息 失效
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void invalidProm(PromCommDTO promCommDTO) throws BusinessException;
    
    /**
     * 促销基本信息 草稿删除
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void delProm(PromCommDTO promCommDTO) throws BusinessException;
    
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
    /**
     * 获得参加促销列表 solr引用
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromByGdsForSolr(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 获得参加促销列表 solr引用
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listPromForSolr(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException;
    /**
     * 下单提交 库存量信息
     * @param ordPromListDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSave(OrdPromListDTO ordPromListDTO)
            throws BusinessException;
    /**
     * 下单提交 库存量信息
     * @param rOrdCartsCommRequest
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSave(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException;
    /**
     * 下单提交 回滚
     * @param ordPromListDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSaveRollBack(OrdPromListDTO ordPromListDTO)
            throws BusinessException;
    /**
     * 下单提交 回滚
     * @param rOrdCartsCommRequest
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSaveRollBack(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException;
}
