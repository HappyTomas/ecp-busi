package com.zengshi.ecp.prom.dubbo.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCommDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.sort.ComparatorPromInfoDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromUserLimitSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.prom.service.busi.prom2solr.interfaces.IProm2SolrSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromRSVImpl implements IPromRSV {

    private static final String MODULE = PromRSVImpl.class.getName();

    @Resource(name = "defaultSkuCheckSV")
    private IPromSkuCheckSV defaultSkuCheckSV;

    @Resource
    private IPromSV promSV;

    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private IPromTypeSV promTypeSV;
    
    @Resource
    private IPromUserLimitSV promUserLimitSV;

    @Resource
    private IPromMatchSV promMatchSV;
    
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
    
    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private Converter<PromInfo, Prom2SolrReqDTO> prom2SolrReqDTOConverter;
    
    @Resource
    private IProm2SolrSV prom2SolrSV;
    
    @Resource
    private IPromConstraintCheckSV channelCheckSV;
    
    @Resource(name = "defaultCheckSV")
    private IPromConstraintCheckSV defaultCheckSV;
    
    @Resource
    private IPromConstraintSV promConstraintSV;
    /**
     * 促销信息保存 --验证
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void validProm(PromDTO promDTO) throws BusinessException {
            promSV.validProm(promDTO);
    }

    /**
     * 促销信息--保存草稿
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void saveProm(PromDTO promDTO) throws BusinessException {
            if(promDTO.getCreateStaff()==null){
                promDTO.setCreateStaff(promDTO.getStaff().getId());
            }
            if(promDTO.getCreateTime()==null){
                promDTO.setCreateTime(DateUtil.getSysDate());
            }
            promSV.saveProm(promDTO);
    }

    /**
     * 促销信息--提交发布
     * 
     * @param promDTO
     * @throws Exception
     * @author huangjx
     */
    public void createProm(PromDTO promDTO) throws BusinessException {
            if(promDTO.getCreateStaff()==null){
                promDTO.setCreateStaff(promDTO.getStaff().getId());
            }
            if(promDTO.getCreateTime()==null){
                promDTO.setCreateTime(DateUtil.getSysDate());
            }
            if(promDTO.getUpdateStaff()==null){
                promDTO.setUpdateStaff(promDTO.getStaff().getId());
            }
            if(promDTO.getUpdateTime()==null){
                promDTO.setUpdateTime(DateUtil.getSysDate());
            }
           PromInfo promInfo= promSV.createProm(promDTO);
            
            //insert到 t_prom_2_solr
            Prom2SolrReqDTO prom2SolrReq=new Prom2SolrReqDTO();
            prom2SolrReq=prom2SolrReqDTOConverter.convert(promInfo);
            prom2SolrReq.setIfSendMsg("1");
            prom2SolrSV.save(prom2SolrReq);
    }

    /**
     * TODO获得商品参加促销列表(购买详情展示)
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#listPromByGds(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromByGds(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (StringUtil.isEmpty(promRuleCheckDTO.getShopId())) {
            // prom.400065 = 店铺编码 不能为空！
            throw new BusinessException("prom.400065");
        }
        if (StringUtil.isEmpty(promRuleCheckDTO.getGdsId())) {
            // prom.400066 = 商品编码 不能为空！
            throw new BusinessException("prom.400066");
        }
        if (StringUtil.isEmpty(promRuleCheckDTO.getCalDate())) {
            // prom.400067 = 日期 不能为空！
            throw new BusinessException("prom.400067");
        }
        if (StringUtil.isEmpty(promRuleCheckDTO.getSiteId())) {
            // prom.400067 = 站点 不能为空！
            throw new BusinessException("prom.400151");
        }
        if (promRuleCheckDTO.getStaffId()==null) {
            //throw new BusinessException("prom.400096");
            promRuleCheckDTO.setStaffId(String.valueOf(promRuleCheckDTO.getStaff().getId()));
        }
        //获得商品 对应的促销列表
        // return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_20);
        return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,null);
    }

    /**
     * TODO获得单品参加促销列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#listPromBySku(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromBySku(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400071 = 日期 不能为空！
            throw new BusinessException("prom.400071");
        }
        if (promRuleCheckDTO.getSkuId() == null) {
            // prom.400072 = 单品编码 不能为空！
            throw new BusinessException("prom.400072");
        }
        if (promRuleCheckDTO.getStaffId()==null) {
            throw new BusinessException("prom.400096");
        }
        //return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_20);
        return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,null);
    }

    /**
     * TODO是否满足促销商品（无促销规则和购买规则限制）
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#isFullfilPromotion(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public boolean isFullfilPromotion(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        if (promRuleCheckDTO.getSkuId() == null) {
            // prom.400072 = 单品编码 不能为空！
            throw new BusinessException("prom.400072");
        }
        if (promRuleCheckDTO.getPromId() == null) {
            throw new BusinessException("prom.400086");
        }
            // promId获得 PromInfoDTO promInfoDTO
            PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(promRuleCheckDTO.getPromId());
            
            return defaultSkuCheckSV.gdsCheck(promInfoDTO,promRuleCheckDTO);
    }
    /**
     * 下单提交 验证
     * @param promRuleCheckDTO
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void checkPromOrd(PromRuleCheckDTO promRuleCheckDTO,OrdPromDTO ordPromDTO)
            throws BusinessException{
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        promSV.checkPromOrd(promRuleCheckDTO, ordPromDTO);
    }
    /**
     * TODO购物车初始化展示 初始化产品和订单级促销
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#initOrdProm(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO initOrdProm(OrdPromDTO ordPromDTO) throws BusinessException {
        
            promSV.initOrdPromByGds(ordPromDTO);
            promSV.initOrdPromByOrder(ordPromDTO);
            
            return ordPromDTO;
    }
    /**
     * @function 购物车初始化展示 初始化产品和订单级促销
     * @param OrdPromVo
     *            返回 OrdPromVo
     * @return
     */
    public OrdPromRespDTO initOrdPromMap(OrdPromDTO ordPromDTO) throws BusinessException{
        
        promSV.initOrdPromByGds(ordPromDTO);
        promSV.initOrdPromByOrder(ordPromDTO);
        return convert2OrdPromRespDTO(ordPromDTO);
    }
    /**
     * 购物车初始化展示 初始化产品和订单级促销
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CartPromRespDTO initCartPromMap(ROrdCartsCommRequest rOrdCartsCommRequest) throws BusinessException{

        long  t1=System.currentTimeMillis();
        
        LogUtil.info(MODULE, "initCartPromMap开始执行时间"+t1);
        
        if(rOrdCartsCommRequest==null){
            throw new BusinessException("prom.400086");
        }
        
        if(CollectionUtils.isEmpty(rOrdCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException("prom.400086");
        }
        //返回对象
        CartPromRespDTO  respDTO=new CartPromRespDTO();
        
        //返回参数设置  购物车列表
        Map<Long,CartPromDTO> cartPromDTOMap  =new HashMap<Long,CartPromDTO>();
        //返回参数设置 购物车明细列表
        Map<Long,CartPromItemDTO> cartPromItemDTOMap=new HashMap<Long,CartPromItemDTO>();
        
        respDTO.setCartPromDTOMap(cartPromDTOMap);
        respDTO.setCartPromItemDTOMap(cartPromItemDTOMap);
        
        //PromRuleCheckDTO 待组织
        //组织参数 转为 ordPromDTO
        for(ROrdCartCommRequest reqDTO:rOrdCartsCommRequest.getOrdCartsCommList()){
            
            if(CollectionUtils.isEmpty(reqDTO.getOrdCartItemCommList())){
                throw new BusinessException("prom.400086");
            }
            //转为促销dto
            OrdPromDTO ordPromDTO=new OrdPromDTO();
            //此操是否购物车初始化
            String submitType = rOrdCartsCommRequest.getPromSubmitType();
            if (StringUtil.isNotBlank(submitType)) {
                
                switch (submitType) {
                
                case PromConstants.PromTypeMsg.CART_SUBMIT_TYPE:
                    ordPromDTO.setCartInitAction(true);
                    break;
                case PromConstants.PromTypeMsg.ORDER_SUBMIT_TYPE:
                    ordPromDTO.setCartInitAction(false);
                    break;
                default:
                    ordPromDTO.setCartInitAction(false);
                    break;
                }
            }
            
            
            ObjectCopyUtil.copyObjValue(reqDTO, ordPromDTO, "", false);
            
            ordPromDTO.setSiteId(rOrdCartsCommRequest.getCurrentSiteId());
            
            ordPromDTO.setCalDate(DateUtil.getSysDate());//日期配置
            //订单有参与促销id 订单传入  null 0初始化 -1选择不参与促销 正整数选择对应的促销id
            if(!StringUtil.isEmpty(reqDTO.getPromId()) && reqDTO.getPromId().longValue()!=0){
                PromInfoDTO promInfoDTO=new PromInfoDTO();
                promInfoDTO.setId(reqDTO.getPromId());
                ordPromDTO.setPromInfoDTO(promInfoDTO);
            }
            //渠道来源  客户组 客户等级 区域
           if(!StringUtil.isEmpty(reqDTO.getChannelValue()) || !StringUtil.isEmpty(reqDTO.getCustGroupValue()) ||!StringUtil.isEmpty(reqDTO.getCustLevelValue()) || !StringUtil.isEmpty(reqDTO.getAreaValue())){
               if( ordPromDTO.getPromRuleCheckDTO()==null){
                   PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                   promRuleCheckDTO.setAreaValue(reqDTO.getAreaValue());
                   promRuleCheckDTO.setChannelValue(reqDTO.getChannelValue());
                   promRuleCheckDTO.setCustGroupValue(reqDTO.getCustGroupValue());
                   promRuleCheckDTO.setCustLevelValue(reqDTO.getCustLevelValue());
                   promRuleCheckDTO.setSiteId(rOrdCartsCommRequest.getCurrentSiteId());
                   ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
               }
           }
           //购物车明细列表
            List<OrdPromDetailDTO> detailDTOList=new ArrayList<OrdPromDetailDTO>();
            
            ordPromDTO.setOrdPromDetailDTOList(detailDTOList);
            //转化购物车明细
            for(ROrdCartItemCommRequest reqDetailDTO:reqDTO.getOrdCartItemCommList()){
                
                reqDetailDTO.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
                
                OrdPromDetailDTO dto=new OrdPromDetailDTO();
                ObjectCopyUtil.copyObjValue(reqDetailDTO, dto, "", false);
                
                dto.setCurrentSiteId(rOrdCartsCommRequest.getCurrentSiteId());
                
                //有参与促销id 订单传入  null初始化 -1选择不参与促销 正整数选择对应的促销id
                if(!StringUtil.isEmpty(reqDetailDTO.getPromId()) && reqDetailDTO.getPromId().longValue()!=0){
                    PromInfoDTO promInfoDTO=new PromInfoDTO();
                    promInfoDTO.setId(reqDetailDTO.getPromId());
                    dto.setPromInfoDTO(promInfoDTO);
                }
                
                detailDTOList.add(dto);
            
             }
            
            //重构 组织 promrulecheckDTO
            if( ordPromDTO.getPromRuleCheckDTO()==null){
                PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                promRuleCheckDTO.setIfshowTime(PromConstants.PromSys.PROM_USE_IF_SHOW_TIME);
                ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
            }else{
                ordPromDTO.getPromRuleCheckDTO().setIfshowTime(PromConstants.PromSys.PROM_USE_IF_SHOW_TIME);
            }
            //促销核心方法
            long  t2=System.currentTimeMillis();
            LogUtil.debug(MODULE, "promSV.initOrdPromByGds开始执行时间"+t2);
            promSV.initOrdPromByGds(ordPromDTO);
            LogUtil.debug(MODULE, "promSV.initOrdPromByGds结束执行时间"+(System.currentTimeMillis()-t2));
            
            long  t3=System.currentTimeMillis();
            LogUtil.debug(MODULE, "promSV.initOrdPromByOrder开始执行时间"+t3);
            promSV.initOrdPromByOrder(ordPromDTO);
            LogUtil.debug(MODULE, "promSV.initOrdPromByOrder结束执行时间"+(System.currentTimeMillis()-t3));
            
            CartPromDTO  cartPromDTO=new CartPromDTO();
            cartPromDTO.setDiscountAmount(ordPromDTO.getDiscountAmount());
            cartPromDTO.setDiscountMoney(ordPromDTO.getDiscountMoney());
            cartPromDTO.setDiscountPriceMoney(ordPromDTO.getDiscountPriceMoney());
            cartPromDTO.setIfFulfillProm(ordPromDTO.isIfFulfillProm());
            cartPromDTO.setPromGiftDTOList(ordPromDTO.getPromGiftDTOList());
            cartPromDTO.setPromInfoDTO(ordPromDTO.getPromInfoDTO());
            cartPromDTO.setPromTypeMsgResponseDTO(ordPromDTO.getPromTypeMsgResponseDTO());
            cartPromDTO.setPromInfoDTOList(ordPromDTO.getPromInfoDTOList());
            //购物车列表id 作为key
            cartPromDTOMap.put(reqDTO.getId(), cartPromDTO);
            
            for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
                CartPromItemDTO  cartPromItemDTO=new CartPromItemDTO();
                cartPromItemDTO.setDiscountAmount(dto.getDiscountAmount());
                cartPromItemDTO.setDiscountMoney(dto.getDiscountMoney());
                cartPromItemDTO.setDiscountPriceMoney(dto.getDiscountPriceMoney());
                cartPromItemDTO.setDiscountPrice(dto.getDiscountPrice());//价格代码需要梳理
                cartPromItemDTO.setDiscountCaclPrice(dto.getDiscountCaclPrice());
                cartPromItemDTO.setDiscountFinalPrice(dto.getDiscountFinalPrice());
                cartPromItemDTO.setIfFulfillProm(dto.isIfFulfillProm());
                cartPromItemDTO.setPromGiftDTOList(dto.getPromGiftDTOList());
                cartPromItemDTO.setPromInfoDTO(dto.getPromInfoDTO());
                cartPromItemDTO.setPromTypeMsgResponseDTO(dto.getPromTypeMsgResponseDTO());
                cartPromItemDTO.setPromInfoDTOList(dto.getPromInfoDTOList());
                cartPromItemDTO.setOrdPromId(dto.getOrdPromId());
                //购物车明细id作为key
                cartPromItemDTOMap.put(dto.getId(), cartPromItemDTO);
            }
        }
        LogUtil.debug(MODULE, "initCartPromMap结束执行时间"+(System.currentTimeMillis()-t1));
        return respDTO;
    }
    /**
     * 购物车页面选择操作接口
     * 1、订单促销选择 参与 不参与  
     * 2、明细变更
     * @param rOrdCartChangeRequest
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CartPromBeanRespDTO selectProm(ROrdCartChangeRequest rOrdCartChangeRequest) throws BusinessException{
        //1 rOrdCartItemCommRequest 为空 验证订单级别数据 
        //2 rOrdCartItemCommRequest 非空 验证当前sku数据+订单数据
        if(rOrdCartChangeRequest==null){
            throw new BusinessException("prom.400086");
        }
        if(rOrdCartChangeRequest.getrOrdCartCommRequest()==null){
            throw new BusinessException("prom.400086");
        }
        //返回结果bean定义
        CartPromBeanRespDTO  cartPromBeanRespDTO=new CartPromBeanRespDTO();
        //整个购物车列表
        ROrdCartCommRequest reqDTO= rOrdCartChangeRequest.getrOrdCartCommRequest();
    
        //转为促销dto
        OrdPromDTO ordPromDTO=new OrdPromDTO();
        
        ObjectCopyUtil.copyObjValue(reqDTO, ordPromDTO, "", false);
        
        ordPromDTO.setSiteId(reqDTO.getCurrentSiteId());
        
        ordPromDTO.setCalDate(DateUtil.getSysDate());
        //订单有参与促销id 订单传入  -1选择不参与促销 正整数选择对应的促销id
        if(!StringUtil.isEmpty(reqDTO.getPromId()) && reqDTO.getPromId().longValue()!=0){
            PromInfoDTO promInfoDTO=new PromInfoDTO();
            promInfoDTO.setId(reqDTO.getPromId());
            ordPromDTO.setPromInfoDTO(promInfoDTO);
        }
        //渠道来源  客户组 客户等级 区域
       if(!StringUtil.isEmpty(reqDTO.getChannelValue()) || !StringUtil.isEmpty(reqDTO.getCustGroupValue()) ||!StringUtil.isEmpty(reqDTO.getCustLevelValue()) || !StringUtil.isEmpty(reqDTO.getAreaValue())){
           if( ordPromDTO.getPromRuleCheckDTO()==null){
               PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
               promRuleCheckDTO.setAreaValue(reqDTO.getAreaValue());
               promRuleCheckDTO.setChannelValue(reqDTO.getChannelValue());
               promRuleCheckDTO.setCustGroupValue(reqDTO.getCustGroupValue());
               promRuleCheckDTO.setCustLevelValue(reqDTO.getCustLevelValue());
               promRuleCheckDTO.setSiteId(reqDTO.getCurrentSiteId());
               ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
           }
       }
       //重构 组织 promrulecheckDTO
       if( ordPromDTO.getPromRuleCheckDTO()==null){
           PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
           promRuleCheckDTO.setIfshowTime(PromConstants.PromSys.PROM_USE_IF_SHOW_TIME);
           ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
       }else{
           ordPromDTO.getPromRuleCheckDTO().setIfshowTime(PromConstants.PromSys.PROM_USE_IF_SHOW_TIME);
       }
       //购物车明细列表
        List<OrdPromDetailDTO> detailDTOList=new ArrayList<OrdPromDetailDTO>();
        
        ordPromDTO.setOrdPromDetailDTOList(detailDTOList);
        //转化购物车明细
        for(ROrdCartItemCommRequest reqDetailDTO:reqDTO.getOrdCartItemCommList()){
        
            OrdPromDetailDTO dto=new OrdPromDetailDTO();
            ObjectCopyUtil.copyObjValue(reqDetailDTO, dto, "", false);
            
            //有参与促销id 订单传入  -1选择不参与促销 正整数选择对应的促销id
            if(!StringUtil.isEmpty(reqDetailDTO.getPromId()) && reqDetailDTO.getPromId().longValue()!=0){
                PromInfoDTO promInfoDTO1=new PromInfoDTO();
                promInfoDTO1.setId(reqDetailDTO.getPromId());
                dto.setPromInfoDTO(promInfoDTO1);
            }
            
            detailDTOList.add(dto);
        
         }
        //单个购物车明细变更验证
        if(rOrdCartChangeRequest.getrOrdCartItemCommRequest()!=null){
            
            ROrdCartItemCommRequest rOrdCartItemCommRequest=new ROrdCartItemCommRequest();
            rOrdCartItemCommRequest=rOrdCartChangeRequest.getrOrdCartItemCommRequest();
            
            OrdPromDetailDTO dto=new OrdPromDetailDTO();
            ObjectCopyUtil.copyObjValue(rOrdCartItemCommRequest, dto, "", false);
            
            //有参与促销id 订单传入   -1选择不参与促销 正整数选择对应的促销id
            if(!StringUtil.isEmpty(rOrdCartItemCommRequest.getPromId()) && rOrdCartItemCommRequest.getPromId().longValue()!=0){
                PromInfoDTO promInfoDTO1=new PromInfoDTO();
                promInfoDTO1.setId(rOrdCartItemCommRequest.getPromId());
                dto.setPromInfoDTO(promInfoDTO1);
            }
            
            //单品促销验证
            PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
            if(ordPromDTO.getPromRuleCheckDTO()!=null){
               ObjectCopyUtil.copyObjValue(ordPromDTO.getPromRuleCheckDTO(), promRuleCheckDTO, "", false);
            }
            promRuleCheckDTO.setShopId(dto.getShopId());
            promRuleCheckDTO.setShopName(ordPromDTO.getShopName());
            promRuleCheckDTO.setGdsId(dto.getGdsId());
            promRuleCheckDTO.setGdsName(dto.getGdsName());
            promRuleCheckDTO.setSkuId(dto.getSkuId());
            promRuleCheckDTO.setCalDate(ordPromDTO.getCalDate());
            promRuleCheckDTO.setBuyCnt(dto.getOrderAmount().toString());
            promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
            promRuleCheckDTO.setSiteId(ordPromDTO.getSiteId());
            
            promSV.promByGds(ordPromDTO, dto,promRuleCheckDTO);
            
            for(OrdPromDetailDTO dto1:ordPromDTO.getOrdPromDetailDTOList()){
                if(dto1.getId().equals(dto.getId())){
                    ObjectCopyUtil.copyObjValue(dto, dto1, "", false);
                    break;
                }
            }
            //返回结果设置
            CartPromItemDTO  cartPromItemDTO=new CartPromItemDTO();
            cartPromItemDTO.setDiscountAmount(dto.getDiscountAmount());
            cartPromItemDTO.setDiscountMoney(dto.getDiscountMoney());
            cartPromItemDTO.setDiscountPriceMoney(dto.getDiscountPriceMoney());
            cartPromItemDTO.setDiscountPrice(dto.getDiscountPrice());//价格代码需要梳理 待定
            cartPromItemDTO.setDiscountCaclPrice(dto.getDiscountCaclPrice());
            cartPromItemDTO.setDiscountFinalPrice(dto.getDiscountFinalPrice());
            cartPromItemDTO.setIfFulfillProm(dto.isIfFulfillProm());
            cartPromItemDTO.setPromGiftDTOList(dto.getPromGiftDTOList());
            cartPromItemDTO.setPromInfoDTO(dto.getPromInfoDTO());
            cartPromItemDTO.setPromTypeMsgResponseDTO(dto.getPromTypeMsgResponseDTO());
            cartPromItemDTO.setPromInfoDTOList(dto.getPromInfoDTOList());
            cartPromBeanRespDTO.setCartPromItemDTO(cartPromItemDTO);
        }
        //订单验证
        promSV.initOrdPromByOrder(ordPromDTO);
        
        //返回结果解析
        CartPromDTO  cartPromDTO=new CartPromDTO();
        cartPromDTO.setDiscountAmount(ordPromDTO.getDiscountAmount());
        cartPromDTO.setDiscountMoney(ordPromDTO.getDiscountMoney());
        cartPromDTO.setDiscountPriceMoney(ordPromDTO.getDiscountPriceMoney());
        cartPromDTO.setIfFulfillProm(ordPromDTO.isIfFulfillProm());
        cartPromDTO.setPromGiftDTOList(ordPromDTO.getPromGiftDTOList());
        cartPromDTO.setPromInfoDTO(ordPromDTO.getPromInfoDTO());
        cartPromDTO.setPromTypeMsgResponseDTO(ordPromDTO.getPromTypeMsgResponseDTO());
        cartPromDTO.setPromInfoDTOList(ordPromDTO.getPromInfoDTOList());
        cartPromBeanRespDTO.setCartPromDTO(cartPromDTO);
        
        return cartPromBeanRespDTO;
    }
    /**
     * TODO购物车实例 单品选择某个促销
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#selectPromByGds(com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param queryPromDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByGds(OrdPromDetailDTO ordPromDetailDTO,
            OrdPromDTO ordPromDTO) throws BusinessException {
        
            Long promId=null;
            if(ordPromDetailDTO.getPromInfoDTO()!=null){
                //为空表示 不参与促销 
                //非空表示参与某个促销
                promId=ordPromDetailDTO.getPromInfoDTO().getId();
            }
            promSV.selectPromByGds(promId, ordPromDetailDTO.getGdsId(), ordPromDetailDTO.getSkuId(), ordPromDTO);
            return ordPromDTO;
    }
    /**
     * 购物车 实例 订单选择某个促销
     * @param queryPromDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByOrd(Long promId,
            OrdPromDTO ordPromDTO) throws BusinessException{
        promSV.selectPromByOrd(promId,ordPromDTO);
        return ordPromDTO;
    }
    /**
     * TODO 促销提醒信息
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#remindProm(com.zengshi.ecp.prom.dubbo.dto.PromCommDTO)
     * @param promCommDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromTypeMsgResponseDTO remindProm(PromCommDTO promCommDTO) throws BusinessException {
            return promSV.remindPromMsg(promCommDTO.getPromId());
    }

    /**
     * TODO搭配商品、单品列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#queryMatchList(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromMatchDTO> queryMatchList(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        // 参数验证 必填
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (promRuleCheckDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
    /*    if (promRuleCheckDTO.getSkuId() == null) {
            // prom.400072 = 单品编码 不能为空！
            throw new BusinessException("prom.400072");
        }*/
        
        return promMatchSV.queryMatchList(promRuleCheckDTO);
    }
    /**
     * TODO促销基本信息 发布
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#publishProm(com.zengshi.ecp.prom.dubbo.dto.PromCommDTO)
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void publishProm(PromCommDTO promCommDTO) throws BusinessException{
    	
    	   String status = PromConstants.PromInfo.STATUS_10;
      	   //是否开启店铺促销审核机制
           if(SysCfgUtil.checkSysCfgValue(PromConstants.PromKey.IF_PROM_CHECK_KEY, PromConstants.PromSys.IF_PROM_CHECK)){
                 status=PromConstants.PromInfo.STATUS_40;
           }
         promInfoSV.editPromInfoStatusByPromId(promCommDTO.getPromId(), promCommDTO.getStaff().getId(), status, null);
    }
   
    /**
     * TODO促销基本信息 失效
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#invalidProm(com.zengshi.ecp.prom.dubbo.dto.PromCommDTO)
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void invalidProm(PromCommDTO promCommDTO) throws BusinessException{
        promInfoSV.editPromInfoStatusByPromId(promCommDTO.getPromId(), promCommDTO.getStaff().getId(), PromConstants.PromInfo.STATUS_20, null);
    }
    /**
     * TODO促销基本信息 草稿删除
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#invalidProm(com.zengshi.ecp.prom.dubbo.dto.PromCommDTO)
     * @param promCommDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void delProm(PromCommDTO promCommDTO) throws BusinessException{
        promInfoSV.editPromInfoStatusByPromId(promCommDTO.getPromId(), promCommDTO.getStaff().getId(), PromConstants.PromInfo.STATUS_30, null);
    }
    /**
     * 促销类型列表
     * 
     * @param promTypeDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    public List<PromTypeResponseDTO> queryPromTypeList(PromTypeDTO promTypeDTO)
            throws BusinessException{
        return promTypeSV.queryPromTypeList(promTypeDTO);
    }
    /**
     * TODO 获得参加促销列表 solr引用
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#listPromByGdsForSolr(com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO)
     * @param queryPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> listPromByGdsForSolr(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException{
        
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        
        if (promRuleCheckDTO.getShopId() == null) {
             //通过gdsId 获得shopId 待实现
            
        }
       /* PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();*/
        Date date=DateUtil.getSysDate();
      /*  promRuleCheckDTO.setShopId(queryPromDTO.getShopId());
        promRuleCheckDTO.setGdsId(queryPromDTO.getGdsId());
        promRuleCheckDTO.setSkuId(queryPromDTO.getSkuId());
        promRuleCheckDTO.setSiteId(queryPromDTO.getCurrentSiteId());*/
        promRuleCheckDTO.setCalDate(date);
        List<PromInfoDTO>  promList=defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,false,null);
        List<PromInfoDTO>  matchList=promMatchSV.queryMatchPromList(promRuleCheckDTO);
        
        if(CollectionUtils.isEmpty(promList)){
            promList=new ArrayList<PromInfoDTO>();
        }
        if(!CollectionUtils.isEmpty(matchList)){
            for(PromInfoDTO p:matchList){
                //验证渠道来源
                // 默认检核失败 false
                boolean checkValue = false;
                PromConstraintDTO promConstaintDTO = new PromConstraintDTO();
                promConstaintDTO = defaultCheckSV.getPromConstaintInfo(p.getId());// 促销规则
                //来源渠道验证
                if(channelCheckSV.isCheck(promConstaintDTO)){
                    // 返回checkValue==true 表示满足规则 可以购买
                    checkValue= channelCheckSV.check(promConstaintDTO, promRuleCheckDTO);
                    if(checkValue){
                        promList.add(p);
                    }
                }else{
                    promList.add(p);
                }
            }
            //排序促销列表
            if(!CollectionUtils.isEmpty(promList)){
                ComparatorPromInfoDTO comparator = new ComparatorPromInfoDTO();
                Collections.sort(promList, comparator);
            }
        }
        if(CollectionUtils.isEmpty(promList)){
            promList=null;
        }
        return promList;
    }
   
    /**
     * TODO获得参加促销列表 solr引用
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV#listPromForSolr(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromListRespDTO> listPromForSolr(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException{
        
        if(promRuleCheckDTO==null){
            throw new BusinessException("prom.400086");
        }
        
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        
        if (promRuleCheckDTO.getShopId() == null) {
             //通过gdsId 获得shopId 待实现
            
        }
       // PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        Date date=DateUtil.getSysDate();
       // promRuleCheckDTO.setSiteId(queryPromDTO.getCurrentSiteId());
        promRuleCheckDTO.setCalDate(date);
      /*  promRuleCheckDTO.setBuyPrice(queryPromDTO.getBuyPrice());
        promRuleCheckDTO.setBasePrice(queryPromDTO.getBasePrice());
        promRuleCheckDTO.setGdsId(queryPromDTO.getGdsId());
        promRuleCheckDTO.setGdsName(queryPromDTO.getGdsName());
        promRuleCheckDTO.setSkuId(queryPromDTO.getSkuId());
        promRuleCheckDTO.setShopId(queryPromDTO.getShopId());
        promRuleCheckDTO.setShopName(queryPromDTO.getShopName());
        promRuleCheckDTO.setStaffId(queryPromDTO.getStaffId());
        promRuleCheckDTO.setStaff(queryPromDTO.getStaff());*/
        //为空 默认1
        promRuleCheckDTO.setBuyCnt("1");
        
        List<PromInfoDTO>  promList=defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,false,null);
        List<PromInfoDTO>  matchList=promMatchSV.queryMatchPromList(promRuleCheckDTO);
        
        List<PromListRespDTO> l=new ArrayList<PromListRespDTO>();
        
        if(CollectionUtils.isEmpty(promList)){
            promList=new ArrayList<PromInfoDTO>();
        }
        if(!CollectionUtils.isEmpty(matchList)){
            for(PromInfoDTO p:matchList){
            	if(PromConstants.PromType.PROM_TYPE_CODE_1000000011.equals(p.getPromTypeCode())){
            		continue;
            	}
                // 默认检核失败 false
                boolean checkValue = false;
                // 返回checkValue==true 表示满足规则 可以购买
            	PromInfoDTO promInfoDTO1 = promInfoSV.queryPromInfoDTOById(p.getId());
                checkValue=promConstraintSV.checkSolr(p.getId(), promRuleCheckDTO);
                if(checkValue){
                	  promList.add(promInfoDTO1);
                  }
            }
            /*
            //排序促销列表   注释原因：保持搜索与商品详情两边的促销链表顺序一致
            if(!CollectionUtils.isEmpty(promList)){
                ComparatorPromInfoDTO comparator = new ComparatorPromInfoDTO();
                Collections.sort(promList, comparator);
            }*/
        }
        if(CollectionUtils.isEmpty(promList)){
            l=null;
        }else{
            for(int i=0;i<promList.size();i++){
                PromListRespDTO dto=new PromListRespDTO();
                dto.setPromInfoDTO(promList.get(i));
                if(i==0){
                    PromSkuPriceRespDTO priceDTO = new PromSkuPriceRespDTO();
                    priceDTO.setDiscountCaclPrice(BigDecimal.valueOf(promRuleCheckDTO.getBasePrice()));
                    priceDTO.setDiscountFinalPrice(BigDecimal.valueOf(promRuleCheckDTO.getBuyPrice()));
                    //判断促销生失效时间，若促销还处于生效状态，则计算，否则，不计算。
                    Timestamp currentTime = DateUtil.getSysDate();
                    if (currentTime.after(dto.getPromInfoDTO().getStartTime())&&currentTime.before(dto.getPromInfoDTO().getEndTime())) {
                        //计算价格
                        priceDTO = promQuerySV.calSkuPriceByPromId(dto.getPromInfoDTO(),promRuleCheckDTO);
                    }  
                    dto.setPromSkuPriceRespDTO(priceDTO);
                }
                l.add(dto);
            }
        }
        return l;
    }
    /**
     * 下单提交
     * @param ordPromListDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSave(OrdPromListDTO ordPromListDTO)
            throws BusinessException{
        
        //促销下单验证 扣减活动库存 等其他限制条件
        Map<Long,List<Long>> map=promSV.savePromOrdList(ordPromListDTO,PromConstants.PromSys.doAction_SAVE);
         try{
             //扣减（赠品库存）
             this.GdsGiftStockSave(ordPromListDTO,map,GdsConstants.GdsGift.DOWN);
             
         }catch(BusinessException ex){
               LogUtil.error(MODULE, ex.toString());
               promSV.savePromOrdList(ordPromListDTO,PromConstants.PromSys.doAction_ROLLBACK);
               throw new BusinessException("prom.400176");
          }
    }
    /**
     * 下单提交 库存量信息
     * @param rOrdCartsCommRequest
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSave(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException{
 
        OrdPromListDTO  ordPromListDTO=new OrdPromListDTO();
        ordPromListDTO.setOrdPromDTOList(this.convert(rOrdCartsCommRequest));
        this.promOrdSave(ordPromListDTO);
        
    }
    /**
     *  下单提交 回滚
     * @param ordPromListDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSaveRollBack(OrdPromListDTO ordPromListDTO)
            throws BusinessException{
        
        Map<Long,List<Long>> map=promSV.savePromOrdList(ordPromListDTO,PromConstants.PromSys.doAction_ROLLBACK);
   
        try{
            //赠品库存 扣减 回滚
            //调增
            this.GdsGiftStockSave(ordPromListDTO,map,GdsConstants.GdsGift.UP);
            
        }catch(BusinessException ex){
              LogUtil.error(MODULE, ex.toString());
              //如果回滚失败 那就不处理 。处理数据吧。 流程继续
         }
    }
    /**
     * 下单提交 回滚
     * @param rOrdCartsCommRequest
     * @throws BusinessException
     * @author huangjx
     */
    public void promOrdSaveRollBack(ROrdCartsCommRequest rOrdCartsCommRequest)
            throws BusinessException{
       
        OrdPromListDTO  ordPromListDTO=new OrdPromListDTO();
        ordPromListDTO.setOrdPromDTOList(this.convert(rOrdCartsCommRequest));
        this.promOrdSaveRollBack(ordPromListDTO);
    }
    /**
     * rOrdCartsCommRequest 转 prom  dto
     * @param rOrdCartsCommRequest
     * @return
     * @author huangjx
     */
    private List<OrdPromDTO> convert(ROrdCartsCommRequest rOrdCartsCommRequest){
        if(rOrdCartsCommRequest==null){
            throw new BusinessException("prom.400086");
        }
        
        if(CollectionUtils.isEmpty(rOrdCartsCommRequest.getOrdCartsCommList())){
            throw new BusinessException("prom.400086");
        }
        List<OrdPromDTO> ordPromDTOList=new ArrayList<OrdPromDTO>();
        //PromRuleCheckDTO
        //组织参数 转为 ordPromDTO
        for(ROrdCartCommRequest reqDTO:rOrdCartsCommRequest.getOrdCartsCommList()){
            
            if(CollectionUtils.isEmpty(reqDTO.getOrdCartItemCommList())){
                throw new BusinessException("prom.400086");
            }
            //转为促销dto
            OrdPromDTO ordPromDTO=new OrdPromDTO();
            
            ObjectCopyUtil.copyObjValue(reqDTO, ordPromDTO, "", false);
            
            ordPromDTO.setOrderId(reqDTO.getOrderId());
            ordPromDTO.setSiteId(reqDTO.getCurrentSiteId());
            ordPromDTO.setCalDate(DateUtil.getSysDate());//日期配置
            //订单有参与促销id 订单传入  null 0初始化 -1选择不参与促销 正整数选择对应的促销id
            if(!StringUtil.isEmpty(reqDTO.getPromId()) && reqDTO.getPromId().longValue()>0){
                PromInfoDTO promInfoDTO=new PromInfoDTO();
                promInfoDTO.setId(reqDTO.getPromId());
                ordPromDTO.setPromInfoDTO(promInfoDTO);
            }
            //渠道来源  客户组 客户等级 区域
           if(!StringUtil.isEmpty(reqDTO.getChannelValue()) || !StringUtil.isEmpty(reqDTO.getCustGroupValue()) ||!StringUtil.isEmpty(reqDTO.getCustLevelValue()) || !StringUtil.isEmpty(reqDTO.getAreaValue())){
               if( ordPromDTO.getPromRuleCheckDTO()==null){
                   PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                   promRuleCheckDTO.setAreaValue(reqDTO.getAreaValue());
                   promRuleCheckDTO.setChannelValue(reqDTO.getChannelValue());
                   promRuleCheckDTO.setCustGroupValue(reqDTO.getCustGroupValue());
                   promRuleCheckDTO.setCustLevelValue(reqDTO.getCustLevelValue());
                   promRuleCheckDTO.setSiteId(reqDTO.getCurrentSiteId());
                   ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
               }
           }
           //购物车明细列表
            List<OrdPromDetailDTO> detailDTOList=new ArrayList<OrdPromDetailDTO>();
            
            ordPromDTO.setOrdPromDetailDTOList(detailDTOList);
            //转化购物车明细
            for(ROrdCartItemCommRequest reqDetailDTO:reqDTO.getOrdCartItemCommList()){
            
                OrdPromDetailDTO dto=new OrdPromDetailDTO();
                ObjectCopyUtil.copyObjValue(reqDetailDTO, dto, "", false);
                
                dto.setOrderId(reqDetailDTO.getOrderId());
                dto.setSubOrderId(reqDetailDTO.getOrderSubId());
                //有参与促销id 订单传入  null初始化 -1选择不参与促销 正整数选择对应的促销id
                if(!StringUtil.isEmpty(reqDetailDTO.getPromId()) && reqDetailDTO.getPromId().longValue()>0){
                    PromInfoDTO promInfoDTO=new PromInfoDTO();
                    promInfoDTO.setId(reqDetailDTO.getPromId());
                    dto.setPromInfoDTO(promInfoDTO);
                }
                
                detailDTOList.add(dto);
            
             }
            ordPromDTOList.add(ordPromDTO);
        }
        return ordPromDTOList;
    }
    /**
     * 转为DTO模型 
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    private OrdPromRespDTO convert2OrdPromRespDTO(OrdPromDTO ordPromDTO)
            throws BusinessException{
        
        OrdPromRespDTO  ordPromRespDTO=new OrdPromRespDTO();
        //为空 返回空
        if(ordPromDTO ==null){
            return null;
        }
        //明细cartItem设置map 对象 
        Map<Long, OrdPromDetailDTO>  detailMap=new HashMap<Long, OrdPromDetailDTO>();
        for(OrdPromDetailDTO detailDTO:ordPromDTO.getOrdPromDetailDTOList()){
            detailMap.put(detailDTO.getId(), detailDTO);
        }
        ordPromRespDTO.setDetailMap(detailMap);
        //cartId设置 map对象
        ordPromRespDTO.setOrdPromDTO(ordPromDTO);
        
        return ordPromRespDTO;
    }
    
    /**
     * 赠品库存 调增 调减
     * @param ordPromListDTO
     * @param giftMap
     * @param doAction
     * @throws BusinessException
     * @author huangjx
     */
    private void GdsGiftStockSave(OrdPromListDTO ordPromListDTO,Map<Long,List<Long>> giftMap,String doAction)throws BusinessException{

        //赠品库存扣减
          List<GdsGiftReqDTO> list=new ArrayList<GdsGiftReqDTO>();
               //促销列表 不同的店铺+促销编码
            /*     
             * 入参：GdsGiftReqDTO 非空
               id : 赠品编码  非空
               shopId  店铺编码 非空
               changeAmount 变更量   非空
               idUpDown   赠品是否调增调减的标识符  1：调增，0：调减   。  非空。 GdsConstants.GdsGift.UP   GdsConstants.GdsGift.DOWN
               updateStaff  更新操作人  非空
                */
               Iterator i=giftMap.entrySet().iterator();
               
               while(i.hasNext()){
                     Map.Entry e=(Map.Entry)i.next();
                     if(StringUtil.isEmpty(e)||StringUtil.isEmpty(e.getKey())){
                         //不处理
                     }else{
                         //赠品表
                         if(!StringUtil.isEmpty(e) && !StringUtil.isEmpty(e.getValue())){
                             List<Long> promIds=(List<Long> )e.getValue();
                             Long shopId=(Long)e.getKey();
                             for(Long promId:promIds){
                                  List<PromGiftDTO> giftList= promQuerySV.queryPromGift(promId,new Long(e.getKey().toString()));
                                     if(!CollectionUtils.isEmpty(giftList)){
                                         for(PromGiftDTO giftDTO:giftList){
                                             GdsGiftReqDTO reqDTO=new GdsGiftReqDTO();
                                             reqDTO.setShopId(shopId);//店铺编码
                                             reqDTO.setId(giftDTO.getGiftId());//赠品编码
                                             //reqDTO.setIdUpDown(GdsConstants.GdsGift.DOWN);//调减
                                             reqDTO.setIdUpDown(doAction); 
                                             reqDTO.setUpdateStaff(ordPromListDTO.getStaff().getId());//操作人
                                             if(!CollectionUtils.isEmpty(list)&&list.size()!=0){
                                            	 List<GdsGiftReqDTO> oList = new ArrayList<GdsGiftReqDTO>();
                                            	 int count = 0;
                                            	 for (GdsGiftReqDTO gdsGiftReqDTO : list) {
                                            		 if(gdsGiftReqDTO.getId().equals(reqDTO.getId())){
                                            			 gdsGiftReqDTO.setChangeAmount(giftDTO.getEveryTimeCnt()+gdsGiftReqDTO.getChangeAmount());//变更数量
                                            			 count++;
                                            			 break;
                                            		 }
                                            	 }
                                            	 if(count>0){
                                            		 continue;
                                            	 }else{
                                            		 reqDTO.setChangeAmount(giftDTO.getEveryTimeCnt());//变更数量
                                        			 oList.add(reqDTO);
                                            	 }
                                            	 if(!CollectionUtils.isEmpty(oList)){
                                            		 list.addAll(oList);
                                            	 }
                                             }else{
                                            	 reqDTO.setChangeAmount(giftDTO.getEveryTimeCnt());//变更数量
                                    			 list.add(reqDTO);
                                             }
                                         }
                                     }
                                    
                             }
                         }
                     }
               }
              if(!CollectionUtils.isEmpty(list)){
                   GdsGiftReqDTO  giftReqDTO=new GdsGiftReqDTO();
                   giftReqDTO.setList(list);
                   gdsGiftRSV.changeGiftAmount(giftReqDTO);
              }
    }
}
