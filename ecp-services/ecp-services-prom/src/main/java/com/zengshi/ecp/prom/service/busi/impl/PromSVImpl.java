package com.zengshi.ecp.prom.service.busi.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;


import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuLimitMapper;
import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListTaskBeanDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeMsgResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.SeckillDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.sort.ComparatorPromInfoDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromUserLimitSV;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromToOrderRelSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.prom.service.busi.valid.impl.DefaultPromValidSVImpl;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.prom.service.common.msg.interfaces.IPromMsgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

import net.sf.jasperreports.engine.util.ObjectUtils;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromSVImpl extends GeneralSQLSVImpl implements IPromSV {

    private static final String MODULE = PromSVImpl.class.getName();

    // 提交验证规则实现
    @Resource
    private DefaultPromValidSVImpl defaultPromValidSV;

    @Resource(name = "defaultSkuCheckSV")
    private IPromSkuCheckSV defaultSkuCheckSV;

    // 促销提醒信息
    @Resource
    private IPromMsgSV promMsgSV;

    // 促销类型
    @Resource
    private IPromTypeSV promTypeSV;

    // 促销黑名单
    @Resource
    private PromSkuLimitMapper promSkuLimitMapper;

    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private IPromQuerySV promQuerySV;

    @Resource
    private IPromConstraintSV promConstraintSV;
    
    @Resource
    private IPromUserLimitSV promUserLimitSV;
    
    // 搭配
    @Resource
    private IPromMatchSV promMatchSV;
    
    // 促销提醒信息
    @Resource
    private IPromSkuSV promSkuSV;
    
    @Resource
    private IPromToOrderRelSV promToOrderRelSV;
    
    @Resource(name="defaultPromDiscountRuleSV")
    private IPromDiscountRuleSV promDiscountRuleSV;

    /**
     * TODO促销信息--验证
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#validProm(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void validProm(PromDTO promDTO) throws BusinessException {
        // 验证前台传入参数正确性
        defaultPromValidSV.valid(promDTO);
    }

    /**
     * TODO促销信息--保存草稿
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#saveProm(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void saveProm(PromDTO promDTO) throws BusinessException {

        promDTO.setStatus(PromConstants.PromInfo.STATUS_00);
        // 验证页面传入信息
        defaultPromValidSV.valid(promDTO);
        
        //如果有搭配商品 t_prom_info.if_match=1  && t_prom_sku.match_type =2
        if(CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
            promDTO.setIfMatch(PromConstants.PromInfo.IF_MATCH_0);
        }else{
            promDTO.setIfMatch(PromConstants.PromInfo.IF_MATCH_1);
        }
        Long promIdOld=promDTO.getId();
        // 保存促销基本信息
        PromInfo promInfo = promInfoSV.savePromInfo(promDTO);
        
        // 保存促销黑名单
        promInfoSV.savePromSkuLimt(promDTO);
        // 保存促销规则
        promInfoSV.savePromConstraint(promDTO);
        // 保存优惠规则
        promInfoSV.savePromCalRule(promDTO);
        
        //赠品保存
        promInfoSV.savePromGift(promDTO);
        
        //搭配商品
        promMatchSV.saveMatchSku(promDTO);
        
        // 保存促销单品
        //编辑保存 特别处理
        if (!StringUtil.isEmpty(promIdOld)) {
            //更新分类信息，将原分类信息失效,新增一份新的数据
            //晕了，在这里面已经做了失效处理promInfoSV.savePromInfo(promDTO);
            //这里新增分类数据
            promInfoSV.savePromCatg(promDTO);
            //包含搭配商品库存量
            promInfoSV.savePromMatchStockLimit(promDTO); 
            //更新商品信息
            promSkuSV.savePromSkuThread(promIdOld,promInfo);
        }else{
            promInfoSV.savePromSku(promDTO);
            //库存量配置表
            promInfoSV.savePromSkuStockLimit(promDTO); 
            //包含搭配商品库存量
            promInfoSV.savePromMatchStockLimit(promDTO); 
        }

    }

    /**
     * TODO促销信息--提交发布
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#createProm(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromInfo createProm(PromDTO promDTO) throws BusinessException {
       
        promDTO.setStatus(PromConstants.PromInfo.STATUS_10);
        
        //是否开启店铺促销审核机制
        if(SysCfgUtil.checkSysCfgValue(PromConstants.PromKey.IF_PROM_CHECK_KEY, PromConstants.PromSys.IF_PROM_CHECK)){
            promDTO.setStatus(PromConstants.PromInfo.STATUS_40);
        }
       //如果是主题促销 必须 提交审核
        if(promDTO.getGroupId()!=null){
            promDTO.setStatus(PromConstants.PromInfo.STATUS_40);
        }
        // 验证页面传入信息
        defaultPromValidSV.valid(promDTO);
        
        //如果有搭配商品 t_prom_info.if_match=1  && t_prom_sku.match_type =2
        if(CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())){
            promDTO.setIfMatch(PromConstants.PromInfo.IF_MATCH_0);
        }else{
            promDTO.setIfMatch(PromConstants.PromInfo.IF_MATCH_1);
        }
        
        Long promIdOld=promDTO.getId();
        
        // 保存促销基本信息
        PromInfo promInfo = promInfoSV.savePromInfo(promDTO);
       
        // 保存促销黑名单
        promInfoSV.savePromSkuLimt(promDTO);
        // 保存促销规则
        promInfoSV.savePromConstraint(promDTO);
        // 保存优惠规则
        promInfoSV.savePromCalRule(promDTO);
        
         //赠品保存
         promInfoSV.savePromGift(promDTO);
         
         //搭配商品
         promMatchSV.saveMatchSku(promDTO);
         
         // 保存促销单品
         //编辑保存 特别处理
         if (!StringUtil.isEmpty(promIdOld)) {
        	 //这里新增分类数据
             promInfoSV.savePromCatg(promDTO);
             //搭配库存量设置
             promInfoSV.savePromMatchStockLimit(promDTO); 
             //单品信息添加
             promSkuSV.savePromSkuThread(promIdOld,promInfo);
         }else{
             promInfoSV.savePromSku(promDTO);
             //库存量配置表
             promInfoSV.savePromSkuStockLimit(promDTO); 
             //包含搭配商品库存量
             promInfoSV.savePromMatchStockLimit(promDTO); 
              
         }
         return promInfo;
    }

    /**
     * TODO购物车初始化展示 初始化产品促销
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#initOrdPromByGds(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void initOrdPromByGds(OrdPromDTO ordPromDTO) throws BusinessException {
        // 传入参数验证
        // 循环vo 每个商品满足哪些促销规则 如果promId为空 需要计算能否参加促销 以及默认选中 并计算优惠 提醒信息
        // 如果非空 判断是否能参加 并计算优惠 提醒信息
        if (ordPromDTO == null) {
            throw new BusinessException("prom.400097");
        }
        if (ordPromDTO.getOrdPromDetailDTOList() == null) {
            throw new BusinessException("prom.400098");
        }
        
        //系统配置参数 是否执行多线程代码 KEY= PROM_SET_MULITTHREAD
        boolean ifOpenMulti=false;
        try{
            ifOpenMulti= SysCfgUtil.checkSysCfgValue(PromConstants.PromKey.PROM_SET_MULITTHREAD_CART, "1");
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常提醒信息 没有配置购物车多线程数据key=PROM_SET_MULITTHREAD_CART，SysCfgUtil.fetchSysCfg报错啦 "+ex.toString());
        }
        
        int cnt=ordPromDTO.getOrdPromDetailDTOList().size();
        //只有一个单品 不开启
        if(ifOpenMulti && cnt>1){
            //默认10
            ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
            
            try{
                
                ArrayList<Future<PromListTaskBeanDTO>> al=new ArrayList<Future<PromListTaskBeanDTO>>();  
                
                //线程池队列
                for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                    PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                    if(ordPromDTO.getPromRuleCheckDTO()!=null){
                       ObjectCopyUtil.copyObjValue(ordPromDTO.getPromRuleCheckDTO(), promRuleCheckDTO, "", false);
                    }
                    promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());
                    promRuleCheckDTO.setShopName(ordPromDTO.getShopName());
                    promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());
                    promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());
                    promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());
                    promRuleCheckDTO.setCalDate(ordPromDTO.getCalDate());
                    promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());
                    promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
                    promRuleCheckDTO.setSiteId(ordPromDTO.getSiteId());
                    al.add(threadExecutor.submit(new PromGdsListTaskSVImpl(ordPromDetailDTO,ordPromDTO,promRuleCheckDTO)));  
                }  
                //结果值 返回处理
                for(Future<PromListTaskBeanDTO> fs:al){  
                    try {
                        PromListTaskBeanDTO r=fs.get();
                        //异常 不处理
                        if(!r.isIfSuccess()){
                            if((r.getException() instanceof BusinessException)){
                                throw new BusinessException(((BusinessException) r.getException()).getErrorMessage());
                            }else{
                                throw new Exception(r.getException());
                            }
                        }
                        if(r.isIfSuccess()){
                            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                                //返回的结果 赋值到列表中
                                if(r!=null && r.getOrdPromDetailDTO()!=null && r.getOrdPromDetailDTO().getId().equals(ordPromDetailDTO.getId()))
                                {
                                    ObjectCopyUtil.copyObjValue(r.getOrdPromDetailDTO(), ordPromDetailDTO, "", true);
                                    break;
                                }
                            } 
                        }
                       
                    } catch (InterruptedException e) {  
                        LogUtil.error(MODULE, "异常信息InterruptedException"+e.toString());
                    } catch (ExecutionException e) {  
                        LogUtil.error(MODULE, "异常信息ExecutionException"+e.toString());
                    }  
                }
                  threadExecutor.shutdown();   
                  
                  
            }catch(Exception ex){
                LogUtil.error(MODULE, "异常信息"+ex.toString());
                if((ex instanceof BusinessException)){
                    throw new BusinessException(((BusinessException) ex).getErrorMessage());
                }else{
                    throw new BusinessException("prom.400208");
                }
            }finally{
                if(threadExecutor!=null && !threadExecutor.isShutdown()){
                    threadExecutor.shutdown();  
                }
            }
        }else{
            //不开启多线程或者只有一个单品列表
            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                if(ordPromDTO.getPromRuleCheckDTO()!=null){
                   ObjectCopyUtil.copyObjValue(ordPromDTO.getPromRuleCheckDTO(), promRuleCheckDTO, "", false);
                }
                promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());
                promRuleCheckDTO.setShopName(ordPromDTO.getShopName());
                promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());
                promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());
                promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());
                promRuleCheckDTO.setCalDate(ordPromDTO.getCalDate());
                promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());
                promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
                promRuleCheckDTO.setSiteId(ordPromDTO.getSiteId());
                
                this.promByGds(ordPromDTO, ordPromDetailDTO,promRuleCheckDTO);
            }
        }
    }

    /**
     * TODO购物车初始化展示 初始化订单促销
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#initOrdPromByOrder(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void initOrdPromByOrder(OrdPromDTO ordPromDTO) throws BusinessException {
        // 循环vo 每个商品满足哪些促销规则 如果promId为空 需要计算能否参加促销 以及默认选中 并计算优惠 提醒信息
        // 如果非空 判断是否能参加 并计算优惠 提醒信息
        if (ordPromDTO == null) {
            throw new BusinessException("prom.400097");
        }
        if (ordPromDTO.getOrdPromDetailDTOList() == null) {
            throw new BusinessException("prom.400098");
        }
        this.promByOrd(ordPromDTO);
    }

 
    /**
     * TODO促销订单提交 审核
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#checkPromOrd(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param promRuleCheckDTO
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void checkPromOrd(PromRuleCheckDTO promRuleCheckDTO, OrdPromDTO ordPromDTO)
            throws BusinessException {
        // 每个单品审核是否满足
        // 整个订单审核 是否满足
        if (ordPromDTO == null) {
            // "购物车列表参数为空！"
            throw new BusinessException("prom.400097");
        }
        if (ordPromDTO.getOrdPromDetailDTOList() == null) {
            // "购物车明细列表参数为空！"
            throw new BusinessException("prom.400098");
        }

        promRuleCheckDTO.setIfThrows(PromConstants.PromSys.IF_THROWS);

        PromInfoDTO promInfoDTO = null;
        // 循环取值判断 单品级别验证
        for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {

            if (ordPromDetailDTO.getPromInfoDTO() != null && ordPromDetailDTO.getPromInfoDTO().getId()!=null) {
                // 促销基本信息
                promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDetailDTO.getId());
                // 购买量
                promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());

                if (promRuleCheckDTO.getCalDate() == null) {
                    promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                }

                promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());
                promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());
                promRuleCheckDTO.setPromId(ordPromDetailDTO.getPromInfoDTO().getId());
                promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());
                promRuleCheckDTO.setShopName(ordPromDetailDTO.getShopName());
                promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());

                if (StringUtil.isEmpty(promRuleCheckDTO.getStaffId())) {
                    promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
                }

                // 验证是否能参与促销
                if (defaultSkuCheckSV.gdsCheck(promInfoDTO, promRuleCheckDTO)
                        && promConstraintSV.check(promInfoDTO.getId(), promRuleCheckDTO)) {

                    // 促销类型代码 获得促销类型信息
                    PromTypeResponseDTO promTypeResponseDTO = promTypeSV
                            .queryPromTypeByCode(promInfoDTO.getPromTypeCode());
                    // 后台没有配置 抛出错误
                    if (promTypeResponseDTO == null) {
                        throw new BusinessException("prom.400041");
                    }
                    // 服务ID
                    String serviceId = promTypeResponseDTO.getServiceId();
                    // 获得ClassPathXmlApplicationContext context bean
                    IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(
                            serviceId, IPromDiscountRuleSV.class);

                    // 获得计算规则 json值
                    PromCalRuleDTO promCalRuleDTO = promQuerySV.queryPromCalRule(promInfoDTO
                            .getId());
                    String json = null;
                    if (promCalRuleDTO != null) {
                        json = promCalRuleDTO.getCalStr();
                    }

                    // 是否满足促销
                    if (!iPromDiscountRuleService.isFulFilPromByGds(ordPromDTO, null, json,
                            PromConstants.PromSys.IF_THROWS,null)) {
                        throw new BusinessException("prom.400110");
                    }

                } else {
                    throw new BusinessException("prom.400110");
                }

            }
        }

        // 订单级别验证
        if (ordPromDTO.getPromInfoDTO() != null && ordPromDTO.getPromInfoDTO().getId() !=null) {

            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {

                if (ordPromDetailDTO.getOrdPromId() != null) {
                    // 促销基本信息
                    promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDetailDTO.getOrdPromId());

                    promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());

                    if (promRuleCheckDTO.getCalDate() == null) {
                        promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                    }

                    promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());
                    promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());
                    promRuleCheckDTO.setPromId(ordPromDetailDTO.getOrdPromId());
                    promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());
                    promRuleCheckDTO.setShopName(ordPromDetailDTO.getShopName());
                    promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());

                    if (StringUtil.isEmpty(promRuleCheckDTO.getStaffId())) {
                        promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
                    }

                    // 验证是否能参与促销
                    if (defaultSkuCheckSV.gdsCheck(promInfoDTO, promRuleCheckDTO)
                            && promConstraintSV.check(promInfoDTO.getId(), promRuleCheckDTO)) {

                        // 促销类型代码 获得促销类型信息
                        PromTypeResponseDTO promTypeResponseDTO = promTypeSV
                                .queryPromTypeByCode(promInfoDTO.getPromTypeCode());
                        // 后台没有配置 抛出错误
                        if (promTypeResponseDTO == null) {
                            throw new BusinessException("prom.400041");
                        }
                        // 服务ID
                        String serviceId = promTypeResponseDTO.getServiceId();
                        // 获得ClassPathXmlApplicationContext context bean
                        IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder
                                .getBean(serviceId, IPromDiscountRuleSV.class);

                        // 获得计算规则 json值
                        PromCalRuleDTO promCalRuleDTO = promQuerySV.queryPromCalRule(promInfoDTO
                                .getId());
                        String json = null;
                        if (promCalRuleDTO != null) {
                            json = promCalRuleDTO.getCalStr();
                        }

                        // 是否满足促销
                        if (!iPromDiscountRuleService.isFulFilPromByGds(ordPromDTO, null, json,
                                PromConstants.PromSys.IF_THROWS,null)) {
                            throw new BusinessException("prom.400110");
                        }
                    } else {
                        throw new BusinessException("prom.400110");
                    }

                }
            }
        }
    }
    /**
     * TODO购物车 实例 单品选择某个促销
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#selectPromByGds(java.lang.String,
     *      java.lang.String, java.lang.String, com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO)
     * @param promId
     * @param gdsId
     * @param skuId
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public void selectPromByGds(Long promId, Long gdsId, Long skuId,
            OrdPromDTO ordPromDTO) throws BusinessException {
        // 1 参数验证 非空
        if (gdsId ==null || skuId==null) {
            throw new BusinessException("prom.400130");
        }
        // 2 promId与 gdsid skuId 无效 没有值 过期等等验证 即是否能参与促销
        // 3 验证通过以后 计算gdsId skuId 优惠情况 提醒信息
          for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
              //选择单品、商品
              if ( (gdsId.equals(ordPromDetailDTO.getGdsId()) && (skuId.equals(ordPromDetailDTO
                              .getSkuId())))) {
                  //促销编码
                  if (ordPromDetailDTO.getPromInfoDTO() != null && ordPromDetailDTO.getPromInfoDTO().getId()!=null) {
                      
                      PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
                      if(ordPromDTO.getPromRuleCheckDTO()!=null){
                         ObjectCopyUtil.copyObjValue(ordPromDTO.getPromRuleCheckDTO(), promRuleCheckDTO, "", false);
                      }
                      promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());
                      promRuleCheckDTO.setShopName(ordPromDTO.getShopName());
                      promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());
                      promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());
                      promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());
                      promRuleCheckDTO.setCalDate(ordPromDTO.getCalDate());
                      promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());
                      promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());
                      promRuleCheckDTO.setSiteId(ordPromDTO.getSiteId());
                      
                      
                        this.promByGds(ordPromDTO, ordPromDetailDTO,promRuleCheckDTO);
                        break;
                  }else{
                      //初始化gds优惠信息  提醒信息
                      ordPromDetailDTO.setDiscountAmount(BigDecimal.ZERO);
                      ordPromDetailDTO.setDiscountMoney(BigDecimal.ZERO);
                      ordPromDetailDTO.setDiscountPrice(BigDecimal.ZERO);
                      ordPromDetailDTO.setPromInfoDTO(null);
                      ordPromDetailDTO.setIfFulfillProm(Boolean.FALSE);
                      ordPromDetailDTO.setPromTypeMsgResponseDTO(null);
                      break;
                  }
              }
            }
        // 4 关于订单级别的活动也需要调整 调整单品影响订单
       // this.promByOrd(ordPromDTO);
    }

    /**
     * 购物车 实例   订单选择某个促销
     * @param promId
     * @param ordPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public OrdPromDTO selectPromByOrd(Long promId,
            OrdPromDTO ordPromDTO) throws BusinessException{
        
          if(promId!=null){
              //表示选择某个促销id
              this.promByOrd(ordPromDTO); 
              //设置detail明细关于订单促销信息
              
              for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                    
                  if(!CollectionUtils.isEmpty(ordPromDetailDTO.getOrdPromInfoDTOList())){
                           for(PromInfoDTO dto:ordPromDetailDTO.getOrdPromInfoDTOList()){
                                if(dto.getId().equals(promId)){
                                    //设置订单参与促销id
                                     ordPromDetailDTO.setOrdPromId(promId);
                                     break;
                                }
                           }
                     }
                    
              }
          }else{
              //初始化gds优惠信息  提醒信息
              ordPromDTO.setDiscountAmount(BigDecimal.ZERO);
              ordPromDTO.setDiscountMoney(BigDecimal.ZERO);
              ordPromDTO.setPromInfoDTO(null);
              ordPromDTO.setIfFulfillProm(Boolean.FALSE);
              ordPromDTO.setPromTypeMsgResponseDTO(null);
              //清空detail明细关于订单促销信息
               for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                      ordPromDetailDTO.setOrdPromId(null);
                }
          }
          return ordPromDTO;
    }
    /**
     * 购物车 每个明细参与促销验证
     * 
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @throws IllegalAccessBusinessException
     * @throws BusinessException
     * @author huangjx
     */
    public void promByGds(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {

        // 1、promId 为空
        // 2、计算gdsid skuid 是否参加促销 获得列表 （区别产品促销和订单促销）
        // 3、选择默认选中 优先级高
        // 4、是否满足优惠规则
        // 5、计算优惠金额 并提取提醒信息
        
    	//促销新增参数初始化，（防止不满足促销活动，discountFinalPrice，discountCaclPrice不正确）
    	ordPromDetailDTO.setDiscountCaclPrice(BigDecimal.valueOf(ordPromDetailDTO.getBasePrice()));
    	ordPromDetailDTO.setDiscountFinalPrice(BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice()));
    	
        //数字印刷 不需要计算
        if(ordPromDetailDTO.getPromInfoDTO()!=null && ordPromDetailDTO.getPromInfoDTO().getId().longValue()==-3){
            return;
        }
        //if (ordPromDetailDTO.getPromInfoDTO() == null || ordPromDetailDTO.getPromInfoDTO().getId()==null) {
        //所有的初始化 都要重新计算列表
        /*    if(ordPromDTO.getPromRuleCheckDTO()==null){
                ordPromDTO.setPromRuleCheckDTO(new PromRuleCheckDTO());
            }
            ordPromDTO.getPromRuleCheckDTO().setShopId(ordPromDetailDTO.getShopId());
            ordPromDTO.getPromRuleCheckDTO().setShopName(ordPromDTO.getShopName());
            ordPromDTO.getPromRuleCheckDTO().setGdsId(ordPromDetailDTO.getGdsId());
            ordPromDTO.getPromRuleCheckDTO().setGdsName(ordPromDetailDTO.getGdsName());
            ordPromDTO.getPromRuleCheckDTO().setSkuId(ordPromDetailDTO.getSkuId());
            ordPromDTO.getPromRuleCheckDTO().setCalDate(ordPromDTO.getCalDate());
            ordPromDTO.getPromRuleCheckDTO().setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());
            ordPromDTO.getPromRuleCheckDTO().setStaffId(ordPromDTO.getStaffId());
            ordPromDTO.getPromRuleCheckDTO().setSiteId(ordPromDTO.getSiteId());*/
            
            long t1=System.currentTimeMillis();
            // 初始化购物车列表为空。如果购物车，选择某个促销id 即非空
            List<PromInfoDTO> list = defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_20);
            LogUtil.debug(MODULE, "defaultSkuCheckSV.listGdsPromInfo结束执行时间"+(System.currentTimeMillis()-t1));
            
            //初始化促销信息
            if(ordPromDetailDTO.getPromInfoDTO()!=null){
                if(ordPromDetailDTO.getPromInfoDTO().getId()>0){
                    if(StringUtil.isEmpty(ordPromDetailDTO.getPromInfoDTO().getPromTypeCode())){
                        PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDetailDTO.getPromInfoDTO().getId());
                        ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
                    }
                }
            }
            
            // 页面列表如何展示 产品展示产品活动 订单展示订单活动
            // 单品级别就只是展示单品活动列表 订单级别展示订单级别活动列表
      /*      if (list != null && list.size() > 0) {
                // 默认取第一个促销id
                ordPromDetailDTO.setPromInfoDTO(list.get(0));
                ordPromDetailDTO.setPromInfoDTOList(list);
            }*/
             this.setDetailPromInfo(list, ordPromDetailDTO) ;
             if (ordPromDTO.isCartInitAction()) {
                 SeckillDiscountDTO seckillDiscountDTO = dealSeckillPromList(list);
                 if (seckillDiscountDTO.isExist()&&seckillDiscountDTO.isStart()) {
                    ordPromDetailDTO.setPromInfoDTO(list.get(0));
                }
            }

       // }
             
       //处理秒杀促销，判断是否存在秒杀活动
             
       // TODO : 秒杀
             

        // 6、promId 非空 (直接)计算优惠金额 并
        // 通过配置表获得service 执行约定方法 返回金额
        ordPromDetailDTO.setIfFulfillProm(false);
        if (ordPromDetailDTO.getPromInfoDTO() != null && ordPromDetailDTO.getPromInfoDTO().getId() !=null) {
            //满足促销id 或者选择对应的促销id
            if(ordPromDetailDTO.getPromInfoDTO().getId()>0){
                    //获得促销类型
                    PromTypeResponseDTO promTypeResponseDTO =null;
                    long t2=System.currentTimeMillis();
                    if(StringUtil.isEmpty(ordPromDetailDTO.getPromInfoDTO().getPromTypeCode())){
                        PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDetailDTO.getPromInfoDTO().getId());
                        ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
                    }
                    LogUtil.debug(MODULE, "promInfoSV.queryPromInfoDTOById结束执行时间"+(System.currentTimeMillis()-t2));
                    
                    long t3=System.currentTimeMillis();
                     promTypeResponseDTO = promTypeSV
                            .queryPromTypeByCode(ordPromDetailDTO.getPromInfoDTO().getPromTypeCode());
                     
                     LogUtil.debug(MODULE, "promTypeSV.queryPromTypeByCode结束执行时间"+(System.currentTimeMillis()-t3));
                    //如果没有配置促销类型
                    if (promTypeResponseDTO == null) {
                        throw new BusinessException("prom.400041");
                    }
                    //服务id
                    String serviceId = promTypeResponseDTO.getServiceId();
                    //获得ClassPathXmlApplicationContext context bean
                    IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(serviceId, IPromDiscountRuleSV.class);
                    
                    long t4=System.currentTimeMillis();
                    //获得计算规则 json值
                    PromCalRuleDTO promCalRuleDTO=promQuerySV.queryPromCalRule(ordPromDetailDTO.getPromInfoDTO().getId());
                    String json=null;
                    if(promCalRuleDTO!=null){
                        json=promCalRuleDTO.getCalStr();
                    }
                    LogUtil.debug(MODULE, "promQuerySV.queryPromCalRule结束执行时间"+(System.currentTimeMillis()-t4));
                    // 提取提醒信息
                    PromTypeMsgResponseDTO promTypeMsgResponseDTO = promMsgSV.queryPromMsgByCode(
                            ordPromDetailDTO.getPromInfoDTO().getPromTypeCode(),
                            PromConstants.PromTypeMsg.STATUS_1, PromConstants.PromTypeMsg.POSITION_10);
                    
                    ordPromDetailDTO.setPromTypeMsgResponseDTO(promTypeMsgResponseDTO);
                    // 是否满足促销
                    ordPromDetailDTO.setIfFulfillProm(iPromDiscountRuleService.isFulFilPromByGds(
                            ordPromDTO, ordPromDetailDTO, json,null,promTypeMsgResponseDTO));
                    
                    if (ordPromDetailDTO.isIfFulfillProm()) {
                        
                        long t5=System.currentTimeMillis();
                        // 计算优惠信息
                        PromDiscountDTO promDiscountDTO  = iPromDiscountRuleService.calculatePromotion(ordPromDTO,
                                ordPromDetailDTO, json);
                        // 优惠金额
                        ordPromDetailDTO.setDiscountMoney(promDiscountDTO.getDiscountMoney());
                        ordPromDetailDTO.setDiscountPrice(promDiscountDTO.getDiscountPrice());
                        ordPromDetailDTO.setDiscountAmount(promDiscountDTO.getDiscountAmount());
                        ordPromDetailDTO.setDiscountPriceMoney(promDiscountDTO.getDiscountPriceMoney());
                        ordPromDetailDTO.setDiscountCaclPrice(promDiscountDTO.getDiscountCaclPrice());
                        ordPromDetailDTO.setDiscountFinalPrice(promDiscountDTO.getDiscountFinalPrice());
                        //赠品信息列表
                        ordPromDetailDTO.setPromGiftDTOList(iPromDiscountRuleService.promGiftList(ordPromDetailDTO.getPromInfoDTO().getId()));
                        LogUtil.debug(MODULE, "iPromDiscountRuleService.calculatePromotion结束执行时间"+(System.currentTimeMillis()-t5));
                    }else{
//                        ordPromDetailDTO.setDiscountAmount(BigDecimal.ZERO);
//                        ordPromDetailDTO.setDiscountMoney(BigDecimal.ZERO);
//                        ordPromDetailDTO.setDiscountPrice(BigDecimal.ZERO);
//                        ordPromDetailDTO.setDiscountPriceMoney(BigDecimal.ZERO);
//                        ordPromDetailDTO.setIfFulfillProm(Boolean.FALSE);
                    }
            }
            //选择不参与促销
            if(ordPromDetailDTO.getPromInfoDTO().getId()==-1){
                //初始化gds优惠信息  提醒信息
                ordPromDetailDTO.setOrdPromDetailInfo(ordPromDetailDTO.getPromInfoDTO().getId());
            }
        }

    }

    /**
     * 购物车 订单参与促销验证
     * 
     * @param ordPromDTO
     * @throws IllegalAccessBusinessException
     * @throws BusinessException
     * @author huangjx
     */
    private void promByOrd(OrdPromDTO ordPromDTO) throws BusinessException {
        
        //初始化全部要执行
        //if (ordPromDTO.getPromInfoDTO() == null || ordPromDTO.getPromInfoDTO().getId()==null) {
            // 1、promId 为空
            // 2、计算gdsid skuid 是否参加促销 获得列表 （订单促销）
            // 根据购物车gds sku 计算能否参加哪些促销
            // 3、选择默认选中 优先级高
            // 4、是否满足优惠规则
            // 5、计算优惠金额 并提取提醒信息
            Long pid=null;
            if(ordPromDTO.getPromInfoDTO()!=null){
                pid=ordPromDTO.getPromInfoDTO().getId();
            }
            
            List<PromInfoDTO> promLists = this.fullFilPromotionListByOrd(ordPromDTO,
                    ordPromDTO.getCalDate(),pid);
            
            if (ordPromDTO.getPromInfoDTO() != null
                    && !StringUtil.isEmpty(ordPromDTO.getPromInfoDTO().getId())
                    && ordPromDTO.getPromInfoDTO().getId() > 0) {
                if(StringUtil.isEmpty(ordPromDTO.getPromInfoDTO().getPromTypeCode())){
                    PromInfoDTO promInfoDTO=null;
                         promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDTO
                                .getPromInfoDTO().getId());
                         ordPromDTO.setPromInfoDTO(promInfoDTO);
                }
            }
            
            this.setOrdPromInfo(promLists, ordPromDTO);
            // 单品级别就只是展示单品活动列表 订单级别展示订单级别活动列表
      /*      if (!CollectionUtils.isEmpty(listClass)) {
                if(ordPromDTO.getPromInfoDTO()==null){
                    ordPromDTO.setPromInfoDTO(listClass.get(0));
                }
                ordPromDTO.setPromInfoDTOList(listClass);
            }*/
       // }
            //初始化不需要运行
        if(!StringUtil.isEmpty(pid) && pid>0){
            //如果是订单促销修改  那么需要计算当前订单下面的所有明细优惠金额
           for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
	           	//促销新增参数初始化，（防止不满足促销活动，discountFinalPrice，discountCaclPrice不正确）
	           	ordPromDetailDTO.setDiscountCaclPrice(BigDecimal.valueOf(ordPromDetailDTO.getBasePrice()));
	           	ordPromDetailDTO.setDiscountFinalPrice(BigDecimal.valueOf(ordPromDetailDTO.getBuyPrice()));
	           	
                if (ordPromDetailDTO.getPromInfoDTO() != null
                        && !StringUtil.isEmpty(ordPromDetailDTO.getPromInfoDTO().getId())
                        && ordPromDetailDTO.getPromInfoDTO().getId() > 0) {
                    // 促销类型代码 获得促销类型信息
                    PromTypeResponseDTO promTypeResponseDTO = null;
                    PromInfoDTO promInfoDTO=null;
                         promInfoDTO = promInfoSV.queryPromInfoDTOById(ordPromDetailDTO
                                .getPromInfoDTO().getId());
    
                    promTypeResponseDTO = promTypeSV.queryPromTypeByCode(promInfoDTO
                            .getPromTypeCode());
                    // 后台没有配置 抛出错误
                    if (promTypeResponseDTO == null) {
                        throw new BusinessException("prom.400041");
                    }
                    // 服务ID
                    String serviceId = promTypeResponseDTO.getServiceId();
                    // 获得ClassPathXmlApplicationContext context bean
                    IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(
                            serviceId, IPromDiscountRuleSV.class);
    
                    //获得计算规则 json值
                    PromCalRuleDTO promCalRuleDTO=promQuerySV.queryPromCalRule(promInfoDTO.getId());
                    String json=null;
                    if(promCalRuleDTO!=null){
                        json=promCalRuleDTO.getCalStr();
                    }
                    // 是否满足促销
                    ordPromDetailDTO.setIfFulfillProm(iPromDiscountRuleService.isFulFilPromByGds(
                            ordPromDTO, ordPromDetailDTO, json,null,null));
                    
                    if (ordPromDetailDTO.isIfFulfillProm()) {
                        // 计算优惠信息
                        PromDiscountDTO promDiscountDTO = iPromDiscountRuleService.calculatePromotion(
                                ordPromDTO, ordPromDetailDTO, json);
                        // 优惠金额
                        ordPromDetailDTO.setDiscountMoney(promDiscountDTO.getDiscountMoney());
                        ordPromDetailDTO.setDiscountPrice(promDiscountDTO.getDiscountPrice());
                        ordPromDetailDTO.setDiscountAmount(promDiscountDTO.getDiscountAmount());
                        ordPromDetailDTO.setDiscountPriceMoney(promDiscountDTO.getDiscountPriceMoney());
                        ordPromDetailDTO.setDiscountCaclPrice(promDiscountDTO.getDiscountCaclPrice());
                        ordPromDetailDTO.setDiscountFinalPrice(promDiscountDTO.getDiscountFinalPrice());
                    }

                }
    
            }
        }

        ordPromDTO.setIfFulfillProm(false);

        if (ordPromDTO.getPromInfoDTO() != null && ordPromDTO.getPromInfoDTO().getId()!=null && StringUtil.isNotEmpty(pid) && pid != -1 || ordPromDTO.isCartInitAction()) {

            boolean isFullProm = false;
            
            if (ordPromDTO.isCartInitAction()) {
                int i = 0;
                boolean isNeedAdjust = false;
                for(PromInfoDTO curPromDto : promLists)
                {
                    if (isFullProm = isMeet(ordPromDTO,curPromDto)) {
                        isNeedAdjust = true;
                        break;
                    }
                    i++;
                }
                if (isNeedAdjust) {
                    PromInfoDTO willChang = promLists.get(i);
                    promLists.set(i, promLists.get(0));
                    promLists.set(0, willChang);
                    ordPromDTO.setPromInfoDTO(willChang);
                }
            }
            if (isFullProm || isMeet(ordPromDTO, ordPromDTO.getPromInfoDTO())) {
                
                    PromInfoDTO curPromDto = ordPromDTO.getPromInfoDTO();
                    PromDiscountDTO promDiscountDTO = calculatePromotion(ordPromDTO, curPromDto);
                    ordPromDTO.setDiscountMoney(promDiscountDTO.getDiscountMoney());
                    ordPromDTO.setDiscountAmount(promDiscountDTO.getDiscountAmount());
                    ordPromDTO.setPromGiftDTOList(calculateGiftList(ordPromDTO, curPromDto));
            }
            else {
                if (!CollectionUtils.isEmpty(promLists)) {
                    boolean isExist = false;
                    for(PromInfoDTO curPromDto : promLists)
                    {
                        if (curPromDto != null && curPromDto.getId()!= null && curPromDto.getId().equals(pid)) {
                            isExist = true;
                            break;
                        }
                    }
                    PromInfoDTO oldPromInfo=null;
                    if (!isExist) {
                        
                        oldPromInfo = promInfoSV.queryPromInfoDTOById(promLists.get(0).getId());
                        ordPromDTO.setPromInfoDTO(oldPromInfo);
                    }else{
                        oldPromInfo = promInfoSV.queryPromInfoDTOById(pid);     
                    }
                    isMeet(ordPromDTO, oldPromInfo);
                }
            }
        }
        //选择不参与促销
        else if(ordPromDTO.getPromInfoDTO()!=null && ordPromDTO.getPromInfoDTO().getId()==-1){
            //优惠信息  提醒信息
            ordPromDTO.setOrdPromDTOInfo(ordPromDTO.getPromInfoDTO().getId());
        }
    }

    //计算赠品信息
    private List<PromGiftDTO> calculateGiftList(OrdPromDTO ordPromDTO, PromInfoDTO curPromDto)
    {
        IPromDiscountRuleSV iPromDiscountRuleService = getPromDiscountRuleService(curPromDto);
        
        return iPromDiscountRuleService.promGiftList(ordPromDTO.getPromInfoDTO().getId());
    }
    
    //计算优惠信息
    private PromDiscountDTO calculatePromotion(OrdPromDTO ordPromDTO, PromInfoDTO curPromDto)
    {
        IPromDiscountRuleSV iPromDiscountRuleService = getPromDiscountRuleService(curPromDto);
        String json = getCalRuleJson(curPromDto);
        return iPromDiscountRuleService.calculatePromotion(ordPromDTO, null, json);
    }
    //判断该订单促销是否满足
    private boolean isMeet(OrdPromDTO ordPromDTO, PromInfoDTO curPromDto)
    {
        ordPromDTO.setPromInfoDTO(curPromDto);
        if(curPromDto != null && curPromDto.getId()>0){
            
            //促销类型代码 获得促销类型信息
            IPromDiscountRuleSV iPromDiscountRuleService = getPromDiscountRuleService(curPromDto);
            if(StringUtil.isEmpty(curPromDto.getPromTypeCode())){
                PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(curPromDto.getId());
                ordPromDTO.setPromInfoDTO(promInfoDTO);
            }
            //获得计算规则 json值
            String json = getCalRuleJson(curPromDto);

            // 提取提醒信息
            PromTypeMsgResponseDTO promTypeMsgResponseDTO = promMsgSV.queryPromMsgByCode(curPromDto.getPromTypeCode(),
                    PromConstants.PromTypeMsg.STATUS_1, PromConstants.PromTypeMsg.POSITION_10);
            ordPromDTO.setPromTypeMsgResponseDTO(promTypeMsgResponseDTO);
            
            // 是否满足促销
            ordPromDTO.setIfFulfillProm(iPromDiscountRuleService.isFulFilPromByGds(ordPromDTO,
                    null, json,null,promTypeMsgResponseDTO));
        }
        return ordPromDTO.isIfFulfillProm();
    }
    //获取促销计算实例
    private IPromDiscountRuleSV getPromDiscountRuleService(PromInfoDTO curPromDto)
    {
        PromTypeResponseDTO promTypeResponseDTO =null;

        promTypeResponseDTO = promTypeSV.queryPromTypeByCode(curPromDto.getPromTypeCode());
       //后台没有配置 抛出错误
       if (promTypeResponseDTO == null) {
           throw new BusinessException("prom.400041");
       }
       //服务ID
       String serviceId = promTypeResponseDTO.getServiceId();
       //获得ClassPathXmlApplicationContext context bean
       IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(serviceId, IPromDiscountRuleSV.class);
       
       return iPromDiscountRuleService;
    }
    //获取计算json串
    private String getCalRuleJson(PromInfoDTO curPromDto)
    {
        PromCalRuleDTO promCalRuleDTO=promQuerySV.queryPromCalRule(curPromDto.getId());
        String json=null;
        if(promCalRuleDTO!=null){
            json=promCalRuleDTO.getCalStr();
        }
        return json;
    }
    /**
     * TODO满足哪些促销规则 返回促销规则list
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#fullFilPromotionListByGds(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromInfoDTO> fullFilPromotionListByGds(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_20);
    }
    /**
     * TODO满足哪些促销规则 返回促销规则list
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#fullFilPromotionListBySku(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromInfoDTO> fullFilPromotionListBySku(PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {
        return defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_20);
    }
    /**
     * TODO 订单明细 满足哪些促销规则 返回当前明细满足订单促销的list
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#fullFilPromOrd(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO, com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO, java.util.Date, java.lang.Long)
     * @param ordPromDTO
     * @param ordPromDetailDTO
     * @param date
     * @param ordPromId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public  OrdPromDetailDTO fullFilPromOrd(OrdPromDTO ordPromDTO, OrdPromDetailDTO ordPromDetailDTO, Date date,Long ordPromId)
            throws BusinessException{

        //验证促销是否可以叠加
        if( ordPromDetailDTO.getPromInfoDTO()!=null){
            if(PromConstants.PromInfo.IF_COMPOSIT_0.equals(ordPromDetailDTO.getPromInfoDTO().getIfComposit())){
                return ordPromDetailDTO;
            }
            //数字印刷 不需要计算
            if(ordPromDetailDTO.getPromInfoDTO().getId().longValue()==-3){
                return ordPromDetailDTO;
            }
            //验证if_composit 为空 并且 存在促销编码
            if(ordPromDetailDTO.getPromInfoDTO().getId().longValue()>0 && !PromConstants.PromInfo.IF_COMPOSIT_1.equals(ordPromDetailDTO.getPromInfoDTO().getIfComposit())){
                
                PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(ordPromDetailDTO.getPromInfoDTO().getId());
                
                if(PromConstants.PromInfo.IF_COMPOSIT_0.equals(promInfoDTO.getIfComposit())){
                    return ordPromDetailDTO;
                }
            }
        }

        PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
        if(ordPromDTO.getPromRuleCheckDTO()!=null){
           ObjectCopyUtil.copyObjValue(ordPromDTO.getPromRuleCheckDTO(), promRuleCheckDTO, "", false);
        }
        promRuleCheckDTO.setShopId(ordPromDetailDTO.getShopId());//店铺编码
        promRuleCheckDTO.setShopName(ordPromDTO.getShopName());//店铺名称
        promRuleCheckDTO.setGdsId(ordPromDetailDTO.getGdsId());//商品id
        promRuleCheckDTO.setGdsName(ordPromDetailDTO.getGdsName());//商品名称
        promRuleCheckDTO.setSkuId(ordPromDetailDTO.getSkuId());//单品id
        promRuleCheckDTO.setCalDate(ordPromDTO.getCalDate());//计算时间
        promRuleCheckDTO.setBuyCnt(ordPromDetailDTO.getOrderAmount().toString());//下单数量
        promRuleCheckDTO.setStaffId(ordPromDTO.getStaffId());//操作人
        promRuleCheckDTO.setIfComposit(PromConstants.PromInfo.IF_COMPOSIT_1);//允许叠加
        promRuleCheckDTO.setSiteId(ordPromDTO.getSiteId());
        
        List<PromInfoDTO> list = defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,PromConstants.PromType.PROM_ClASS_10);
        // 获得订单促销列表
        if(!CollectionUtils.isEmpty(list)){
            //设置单品 是否能参与订单促销(已经排序)
            if(ordPromId==null){
                //初始化
                ordPromDetailDTO.setOrdPromId(list.get(0).getId());
                ordPromDetailDTO.setOrdPromInfoDTOList(list);  
            }else{
                //传入有值
                //传入订单促销id和 明细比较
                ordPromDetailDTO.setOrdPromId(-1l);//默认-1
                ordPromDetailDTO.setOrdPromInfoDTOList(list); 
                
                for(PromInfoDTO dto:list){
                    if(dto.getId().compareTo(ordPromId)==0){
                        ordPromDetailDTO.setOrdPromId(ordPromId);
                        break;
                    } 
                }
            }
            if(ordPromDetailDTO.getOrdPromId()!=null){
              //加入缓存中 后续取关系  25分钟时间
                CacheUtil.addItem((PromConstants.PromSys.PROM_ORD_RELA+String.valueOf(ordPromDetailDTO.getId())), ordPromDetailDTO.getOrdPromId(), PromConstants.PromSys.cacheSeconds);
            }
        }
       return ordPromDetailDTO;
    }
    /**
     * TODO订单满足哪些促销规则 返回促销规则list
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#fullFilPromotionListByOrd(com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO, java.util.Date, java.lang.Long)
     * @param ordPromDTO
     * @param date
     * @param ordPromId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromInfoDTO> fullFilPromotionListByOrd(OrdPromDTO ordPromDTO, Date date,Long ordPromId)
            throws BusinessException {
        
        HashSet<PromInfoDTO> set = new HashSet<PromInfoDTO>();

        //系统配置参数 是否执行多线程代码 KEY= PROM_SET_MULITTHREAD
        boolean ifOpenMulti=false;
        try{
            ifOpenMulti= SysCfgUtil.checkSysCfgValue(PromConstants.PromKey.PROM_SET_MULITTHREAD_CART, "1");
        }catch(Exception ex){
            LogUtil.error(MODULE, "异常提醒信息 没有配置购物车多线程数据key=PROM_SET_MULITTHREAD_CART，SysCfgUtil.fetchSysCfg报错啦 "+ex.toString());
        }
        
        int cnt=ordPromDTO.getOrdPromDetailDTOList().size();
        //只有一个单品 不开启
        if(ifOpenMulti && cnt>1){
            //默认10
            ExecutorService threadExecutor = Executors.newFixedThreadPool(10);
            
            try{
                
                ArrayList<Future<PromListTaskBeanDTO>> al=new ArrayList<Future<PromListTaskBeanDTO>>();  
                
                //线程池队列
                for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                    al.add(threadExecutor.submit(new PromOrdListTaskSVImpl(ordPromDetailDTO,ordPromDTO,date,ordPromId)));  
                }  
                //结果值 返回处理
                for(Future<PromListTaskBeanDTO> fs:al){  
                    try {
                        PromListTaskBeanDTO r=fs.get();
                        //异常 不处理
                        if(!r.isIfSuccess()){
                            if((r.getException() instanceof BusinessException)){
                                throw new BusinessException(((BusinessException) r.getException()).getErrorMessage());
                            }else{
                                throw new Exception(r.getException());
                            }
                        }
                        if(r.isIfSuccess()){
                            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                                //返回的结果 赋值到列表中
                                if(r!=null && r.getOrdPromDetailDTO()!=null && r.getOrdPromDetailDTO().getId().equals(ordPromDetailDTO.getId()))
                                {
                                    ObjectCopyUtil.copyObjValue(r.getOrdPromDetailDTO(), ordPromDetailDTO, "", true);
                                    if(ordPromDetailDTO!=null && ordPromDetailDTO.getOrdPromInfoDTOList()!=null){
                                        set.addAll(ordPromDetailDTO.getOrdPromInfoDTOList());
                                    }
                                    break;
                                }
                            } 
                        }
                       
                    } catch (InterruptedException e) {  
                        LogUtil.error(MODULE, "异常信息InterruptedException"+e.toString());
                    } catch (ExecutionException e) {  
                        LogUtil.error(MODULE, "异常信息ExecutionException"+e.toString());
                    }  
                }
                  threadExecutor.shutdown();   
                  
                  
            }catch(Exception ex){
                LogUtil.error(MODULE, "异常信息"+ex.toString());
                if((ex instanceof BusinessException)){
                    throw new BusinessException(((BusinessException) ex).getErrorMessage());
                }else{
                    throw new BusinessException("prom.400208");
                }
            }finally{
                if(threadExecutor!=null && !threadExecutor.isShutdown()){
                    threadExecutor.shutdown();  
                }
            }
        }else{
            //不开启多线程或者只有一个单品列表
            for (OrdPromDetailDTO ordPromDetailDTO : ordPromDTO.getOrdPromDetailDTOList()) {
                ordPromDetailDTO=this.fullFilPromOrd(ordPromDTO, ordPromDetailDTO, date, ordPromId);
                if(ordPromDetailDTO!=null && ordPromDetailDTO.getOrdPromInfoDTOList()!=null){
                    set.addAll(ordPromDetailDTO.getOrdPromInfoDTOList());
                }
            }
        }
        //set 转为list
        List<PromInfoDTO> promInfoDTOlist = new ArrayList<PromInfoDTO>();
        promInfoDTOlist.addAll(set);
        // 排序
        if(!CollectionUtils.isEmpty(promInfoDTOlist)){
            ComparatorPromInfoDTO comparator = new ComparatorPromInfoDTO();
            Collections.sort(promInfoDTOlist, comparator);
        }
        return promInfoDTOlist;
    }

    /**
     * TODO获得提醒信息 返回提醒list
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#remindPromotion(java.math.BigDecimal)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public PromTypeMsgResponseDTO remindPromMsg(Long promId) throws BusinessException {
        
        //1 promId 查询promInfoDTO 
        PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promId);
        
        //2 promInfoDTO.promTypeCode + status+ position 获得PromTypeMsgResponseDTO
        PromTypeMsgResponseDTO promTypeMsgResponseDTO=promMsgSV.queryPromMsgByCode(promInfoDTO.getPromTypeCode(),  PromConstants.PromTypeMsg.STATUS_1, PromConstants.PromTypeMsg.POSITION_10);
        
        return promTypeMsgResponseDTO;
    }
    
    /**
     * 单品促销类型集合
     * 
     * @param promClass
     *            10订单 20单品 30外围活动--无优惠规则，只有限制条件
     * @param promList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    private List<PromInfoDTO> queryPromListByPromClass(String promClass, List<PromInfoDTO> promList)
            throws BusinessException {
        List<PromInfoDTO> returnList = new ArrayList<PromInfoDTO>();
        if (promList == null) {
            return returnList;
        }
        for (PromInfoDTO promInfoDTO : promList) {
            if (promClass.equals(promInfoDTO.getPromClass())) {
                returnList.add(promInfoDTO);
            }
        }
        return returnList;
    }
    /**
     * 下单提交  回滚
     * @param ordPromDTO
     * @param doAction
     * @throws BusinessException
     * @author huangjx
     */
    public List<Long> savePromOrd(OrdPromDTO ordPromDTO,String doAction)
            throws BusinessException{
        
        Set setPromId=new HashSet();
        //List<Long> promIds = new ArrayList<Long>();
        Set<Long> promIds=new HashSet<Long>();
           if(ordPromDTO!=null){
               if(!CollectionUtils.isEmpty(ordPromDTO.getOrdPromDetailDTOList()) ){
                   
                   for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
                       //promId 表示单品参加促销
                        if(dto.getPromInfoDTO()!=null && dto.getPromInfoDTO().getId()!=null){
                            //库存
                            PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
                            promStockLimitDTO.setBuyCnt(dto.getOrderAmount().longValue());
                            promStockLimitDTO.setGdsId(dto.getGdsId());
                            //promStockLimitDTO.setPromCnt(promCnt);
                            promStockLimitDTO.setPromId(dto.getPromInfoDTO().getId());
                            //promStockLimitDTO.setRemaindCnt(remaindCnt);
                            promStockLimitDTO.setSkuId(dto.getSkuId());
                            promStockLimitDTO.setCreateStaff(ordPromDTO.getStaff().getId());
                            promStockLimitDTO.setCreateTime(DateUtil.getSysDate());
                            
                          
                            if(PromConstants.PromSys.doAction_SAVE.equals(doAction)){
                                //sku促销库存
                                promInfoSV.updatePromStockLimitDeduce(promStockLimitDTO);                     
                            }
                            if(PromConstants.PromSys.doAction_ROLLBACK.equals(doAction)){
                                promInfoSV.updatePromStockLimitAdd(promStockLimitDTO);
                            }
                            promIds.add(dto.getPromInfoDTO().getId());
                            setPromId.add(dto.getPromInfoDTO().getId());
                        }
                   }
                   
                   if(ordPromDTO.getPromInfoDTO()!=null && ordPromDTO.getPromInfoDTO().getId()!=null){
                       
                       for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
                           //promId 表示订单参加促销
                            if(dto.getOrdPromId()!=null && ordPromDTO.getPromInfoDTO().getId().equals(dto.getOrdPromId())){
                                    PromStockLimitDTO promStockLimitDTO=new PromStockLimitDTO();
                                    promStockLimitDTO.setBuyCnt(dto.getOrderAmount().longValue());
                                    promStockLimitDTO.setGdsId(dto.getGdsId());
                                    //promStockLimitDTO.setPromCnt(promCnt);
                                    promStockLimitDTO.setPromId(dto.getOrdPromId());
                                    //promStockLimitDTO.setRemaindCnt(remaindCnt);
                                    promStockLimitDTO.setSkuId(dto.getSkuId());
                                    promStockLimitDTO.setCreateStaff(ordPromDTO.getStaff().getId());
                                    promStockLimitDTO.setCreateTime(DateUtil.getSysDate());
                                    
                                    if(PromConstants.PromSys.doAction_SAVE.equals(doAction)){
                                        promInfoSV.updatePromStockLimitDeduce(promStockLimitDTO);

                                    }
                                    if(PromConstants.PromSys.doAction_ROLLBACK.equals(doAction)){
                                        promInfoSV.updatePromStockLimitAdd(promStockLimitDTO);
                                    }
                                    promIds.add(dto.getOrdPromId());
                                    setPromId.add(dto.getOrdPromId());
                            }
                       }
                   }
               }
            
           }
          //过滤重复
           List<Long> list = new ArrayList<Long>();
           list.addAll(setPromId);
           List<PromGiftDTO> giftAllList = new ArrayList<PromGiftDTO>();
           if(!CollectionUtils.isEmpty(list)){
                 for (Long promId:list){
                     PromUserLimitDTO promUserLimitDTO=new PromUserLimitDTO();
                     promUserLimitDTO.setPromId(promId);
                     promUserLimitDTO.setCreateStaff(ordPromDTO.getStaff().getId());
                     promUserLimitDTO.setCreateTime(DateUtil.getSysDate());
                     
                     //下单时间
                     Date orderDate=ordPromDTO.getOrderTime();
                     Long buyCnt=new Long(0);
                     //计算购买量
                     Long tmpId=null;
                     for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
                         
                             if(orderDate==null){
                                 orderDate=dto.getCreateTime();
                             }
                              if(dto.getPromInfoDTO()!=null){
                                  tmpId=dto.getPromInfoDTO().getId();
                              }
                              if(promId.equals(tmpId) || promId.equals(dto.getOrdPromId())){
                                  buyCnt=buyCnt.longValue()+dto.getOrderAmount().longValue();
                         }
                     }
                     promUserLimitDTO.setBuyCnt(buyCnt);//购买量
                     //购买用户
                     promUserLimitDTO.setStaffId(Long.valueOf(ordPromDTO.getStaffId()));
                     
                     if(orderDate==null){
                         orderDate=DateUtil.getSysDate();
                     }
                     //下单时间
                      promUserLimitDTO.setOptValue(DateUtil.getDateString(new Timestamp(orderDate.getTime()), DateUtil.YYYYMMDD));
                       
                      for (Long id : promIds) {
                     	 if(promId.equals(id)){
                     		 //赠品表
                     		 List<PromGiftDTO> giftList= promQuerySV.queryPromGift(id);
                     		List<PromGiftDTO> gList = new ArrayList<PromGiftDTO>();
                     		 if(!CollectionUtils.isEmpty(giftList)&&giftList.size()!=0){
                     			 if(!CollectionUtils.isEmpty(giftAllList)&&giftAllList.size()!=0){
                     				 for (PromGiftDTO promGift : giftList) {
                     					 int i = 0;
                     					 for (PromGiftDTO promGiftDTO : giftAllList) {
                     						 if(promGiftDTO.getGiftId().equals(promGift.getGiftId())&&promGift.getPromId().equals(promGiftDTO.getPromId())){
                     							 promGiftDTO.setPresentCnt(promGiftDTO.getPresentCnt()+promGift.getEveryTimeCnt());
                     							 i++;
                     							 break;
                     						 }
                     					 }
                     					 if(i>0){
                     						 continue;
                     					 }else{
                     						 promGift.setPresentCnt(promGift.getEveryTimeCnt());
                     						 gList.add(promGift);
                     					 }
                     				 }
                     				 if(!CollectionUtils.isEmpty(gList)){
                     					 giftAllList.addAll(gList);
                     				 }
                     			 }else{
                     				 for (PromGiftDTO promGift : giftList) {
                     					 promGift.setPresentCnt(promGift.getEveryTimeCnt());
                     					 gList.add(promGift);
                     				 }
                     				 if(!CollectionUtils.isEmpty(gList)){
                     					 giftAllList.addAll(gList);
                     				 }
                     			 }
                     		 }
                     	 }
                     	 
						}
                     if(PromConstants.PromSys.doAction_SAVE.equals(doAction)){
                         promUserLimitSV.insert(promUserLimitDTO);
                     }
                     if(PromConstants.PromSys.doAction_ROLLBACK.equals(doAction)){
                         promUserLimitSV.update(promUserLimitDTO);
                     }
                 }
                	 if(!CollectionUtils.isEmpty(giftAllList)){
                		 for(PromGiftDTO promGiftDTO:giftAllList){
                			 //赠品
                			 promGiftDTO.setUpdateStaff(ordPromDTO.getStaff().getId());
                		   //promGiftDTO.setPresentCnt(promGiftDTO.getEveryTimeCnt());
                			 if(PromConstants.PromSys.doAction_SAVE.equals(doAction)){
                				 promInfoSV.updatePromGiftDeduce(promGiftDTO);
                			 }else if(PromConstants.PromSys.doAction_ROLLBACK.equals(doAction)){
                				 promInfoSV.updatePromGiftAdd(promGiftDTO);
                			 }
                		 }
                	 }
           }
           return new ArrayList<Long>(promIds);
    }
    /**
     * TODO下单提交  回滚
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#savePromOrdList(com.zengshi.ecp.prom.dubbo.dto.OrdPromListDTO, java.lang.String)
     * @param ordPromListDTO
     * @param doAction
     * @throws BusinessException
     * @author huangjx
     */
    public Map<Long,List<Long>> savePromOrdList(OrdPromListDTO ordPromListDTO,String doAction)
            throws BusinessException{
        
        Map<Long,List<Long>> map=new HashMap<Long,List<Long>>();
        if(!CollectionUtils.isEmpty(ordPromListDTO.getOrdPromDTOList())){
            for(OrdPromDTO ordPromDTO:ordPromListDTO.getOrdPromDTOList()){
                
                if(PromConstants.PromSys.doAction_ROLLBACK.equals(doAction))
                {
                    //若订单已经回滚，则不需要再次回滚
                    if(isRollBack(ordPromDTO))
                    {
                        continue;
                    }
                }
                this.savePromToOrderRel(ordPromDTO, doAction);
                List<Long> promIds=this.savePromOrd(ordPromDTO, doAction);
                map.put(ordPromDTO.getShopId(), promIds);
            }
        }

        
        return map;
    }
/**
 * 
 * savePromToOrderRel:(保存订单中的促销赠送关系). <br/> 
 * 
 * @author PJieWin 
 * @param ordPromDTO
 * @param doAction
 * @return 
 * @since JDK 1.6
 */
private int savePromToOrderRel(OrdPromDTO ordPromDTO, String doAction){
    
    switch (doAction) {
    case PromConstants.PromSys.doAction_SAVE:
        try {
            promToOrderRelSV.saveRelation(ordPromDTO);
        } catch (Exception e) {
            // TODO: 记录可以丢失，不影响主流程
            LogUtil.error(MODULE, "保存订单-促销关系报错了"+e.toString());
        }
        
        break;
    case PromConstants.PromSys.doAction_ROLLBACK:
        //回滚
        try {
            promToOrderRelSV.deleteRelation(ordPromDTO);

        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.error(MODULE, "回滚订单-促销关系报错了"+e.toString());
        }
        break;
    default:
        break;
    }

    
    return 0;
    
}
public boolean isRollBack(OrdPromDTO ordPromDTO)throws BusinessException
{
    return promToOrderRelSV.isRollBack(ordPromDTO);
}
    /**
     * 设置 是否参与明细促销
     * @param list
     * @param ordPromDetailDTO
     * @author huangjx
     */
    private void setDetailPromInfo(List<PromInfoDTO> list,OrdPromDetailDTO ordPromDetailDTO){
        
            Long promId=null;
            PromInfoDTO pinfo=ordPromDetailDTO.getPromInfoDTO();
            if(ordPromDetailDTO.getPromInfoDTO()!=null){
                promId=ordPromDetailDTO.getPromInfoDTO().getId();
            }
            //在购物车中过滤单品调搭配促销
            if (list == null) {
                list = new ArrayList<PromInfoDTO>();
            }
            Iterator<PromInfoDTO> iterator = list.iterator();
            while (iterator.hasNext()) {
                PromInfoDTO curinfo = iterator.next();
                if (PromConstants.PromInfo.IF_MATCH_1.equals(curinfo.getIfMatch())) {
                    iterator.remove();
                }
            }
            //非空
            if(!CollectionUtils.isEmpty(list)){
                
                
                
                 ordPromDetailDTO.setPromInfoDTO(list.get(0));
                 ordPromDetailDTO.setPromInfoDTOList(list);
                
                 if(StringUtil.isEmpty(promId)){
                     return;
                 }
                 
                 if(!StringUtil.isEmpty(promId) && promId.longValue()==-1){
                     PromInfoDTO  dto=new PromInfoDTO();
                     dto.setId(promId);
                     ordPromDetailDTO.setPromInfoDTO(dto);
                     return;
                 }
                  //印刷数字购买 修改
                 if(!StringUtil.isEmpty(promId) && promId.longValue()==-3){
                     PromInfoDTO  dto=new PromInfoDTO();
                     dto.setId(promId);
                     ordPromDetailDTO.setPromInfoDTO(dto);
                     ordPromDetailDTO.setPromInfoDTOList(null);
                     return;
                 }
               //如果促销编码 不在列表中  promInfoDTO为传入dto  
                boolean ifSelect=false;
               for(PromInfoDTO dto:list){
                   if(dto.getId().equals(promId)){
                       //取当前传入值
                       ifSelect=true;
                       ordPromDetailDTO.setPromInfoDTO(dto);
                       break;
                   }
               }
               //返回原来促销信息
               if(!ifSelect){
                   ordPromDetailDTO.setPromInfoDTO(pinfo);
                   //失效
               /*    if(!PromConstants.PromInfo.STATUS_10.equals(pinfo.getStatus())){
                       PromInfoDTO p=new PromInfoDTO();
                       p.setId(Long.valueOf(-1));
                       ordPromDetailDTO.setPromInfoDTO(p);
                   }*/
                   //特别处理 自由搭配A 过滤掉
                   if(!PromConstants.PromType.PROM_TYPE_CODE_1000000011.equals(pinfo.getPromTypeCode())){
                       //传入促销id 但是促销已经失效
                       if(promId>0 && !PromConstants.PromInfo.IF_COMPOSIT_0.equals(pinfo.getIfComposit())){
                           PromInfoDTO p=new PromInfoDTO();
                           p.setId(Long.valueOf(-1));
                           ordPromDetailDTO.setPromInfoDTO(p);
                        }
                   }
               }
            }else{
                //当传入有值时需要返回
                if(StringUtil.isEmpty(promId)){
                    ordPromDetailDTO.setPromInfoDTO(null);
                }else{
                    //特别处理 自由搭配A 过滤掉
                    if(!PromConstants.PromType.PROM_TYPE_CODE_1000000011.equals(pinfo.getPromTypeCode())){
                        //失效
                        /*    if(!PromConstants.PromInfo.STATUS_10.equals(pinfo.getStatus())){
                                PromInfoDTO p=new PromInfoDTO();
                                p.setId(Long.valueOf(-1));
                                ordPromDetailDTO.setPromInfoDTO(p);
                            }*/
                        //传入促销id 但是促销已经失效
                        if(promId>0 && !PromConstants.PromInfo.IF_COMPOSIT_0.equals(pinfo.getIfComposit())){
                            PromInfoDTO p=new PromInfoDTO();
                            p.setId(Long.valueOf(-1));
                            ordPromDetailDTO.setPromInfoDTO(p);
                        }
                    }
                }
                ordPromDetailDTO.setPromInfoDTOList(null);
            }
    }
    /**
     * 设置 是否参与订单促销
     * @param list
     * @param ordPromDTO
     * @author huangjx
     */
    private void setOrdPromInfo(List<PromInfoDTO> list,OrdPromDTO ordPromDTO){
        
            Long promId=null;
            PromInfoDTO pinfo=ordPromDTO.getPromInfoDTO();
            if(ordPromDTO.getPromInfoDTO()!=null){
                promId=ordPromDTO.getPromInfoDTO().getId();
            }
            
            //非空
            if(!CollectionUtils.isEmpty(list)){
                ordPromDTO.setPromInfoDTO(list.get(0));
                ordPromDTO.setPromInfoDTOList(list);
                
                if(StringUtil.isEmpty(promId)){
                    return;
                }
                
                if(!StringUtil.isEmpty(promId) && promId.longValue()==-1){
                    PromInfoDTO  dto=new PromInfoDTO();
                    dto.setId(promId);
                    ordPromDTO.setPromInfoDTO(dto);
                    return;
                }
                //数字印刷购买 不能参加活动
                 if(!StringUtil.isEmpty(promId) && promId.longValue()==-3){
                     PromInfoDTO  dto=new PromInfoDTO();
                     dto.setId(promId);
                     ordPromDTO.setPromInfoDTO(dto);
                     ordPromDTO.setPromInfoDTOList(null);
                     return;
                 }
                 
               for(PromInfoDTO dto:list){
                   if(dto.getId().equals(promId)){
                       //取当前传入值
                       ordPromDTO.setPromInfoDTO(dto);
                       break;
                   }
               }
               
             //如果促销编码 不在列表中  promInfoDTO为传入dto  
               boolean ifSelect=false;
              for(PromInfoDTO dto:list){
                  if(dto.getId().equals(promId)){
                      //取当前传入值
                      ifSelect=true;
                      ordPromDTO.setPromInfoDTO(dto);
                      break;
                  }
              }
              //返回原来促销信息
              if(!ifSelect){
                  ordPromDTO.setPromInfoDTO(pinfo);
                  //传入促销id 但是促销已经失效
                  //失效
             /*     if(!PromConstants.PromInfo.STATUS_10.equals(pinfo.getStatus())){
                      PromInfoDTO p=new PromInfoDTO();
                      p.setId(Long.valueOf(-1));
                  }*/
                  if(promId>0 && !PromConstants.PromInfo.IF_COMPOSIT_0.equals(pinfo.getIfComposit())){
                      PromInfoDTO p=new PromInfoDTO();
                      p.setId(Long.valueOf(-1));
                      ordPromDTO.setPromInfoDTO(p);
                  }
              }
              
              
            }else{
                //当传入有值时需要返回
                if(StringUtil.isEmpty(promId)){
                    ordPromDTO.setPromInfoDTO(null);
                }else{
                    //失效
                    /*     if(!PromConstants.PromInfo.STATUS_10.equals(pinfo.getStatus())){
                             PromInfoDTO p=new PromInfoDTO();
                             p.setId(Long.valueOf(-1));
                         }*/
                    //传入促销id 但是促销已经失效
                    if(promId>0 && !PromConstants.PromInfo.IF_COMPOSIT_0.equals(pinfo.getIfComposit())){
                        PromInfoDTO p=new PromInfoDTO();
                        p.setId(Long.valueOf(-1));
                        ordPromDTO.setPromInfoDTO(p);
                    }
                }
                ordPromDTO.setPromInfoDTOList(null);
            
            }
    }
    /**
     * 促销列表 以及价格信息
     * @param promRuleCheckDTO
     * @param ifConstCheck
     * @param promClass
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public  List<PromListRespDTO> listPromInfo(PromRuleCheckDTO promRuleCheckDTO,boolean ifConstCheck,String promClass) throws BusinessException {
        
        List<PromListRespDTO> reList=new ArrayList<PromListRespDTO>();
        //获得促销列表
        List<PromInfoDTO>  l= defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,null);
        
         if(!CollectionUtils.isEmpty(l)){
             for(int i=0;i<l.size();i++){
                 PromListRespDTO  respDTO=new PromListRespDTO();
                 respDTO.setPromInfoDTO(l.get(i));
                 if(i==0){
                     //计算价格
                     PromSkuPriceRespDTO priceDTO= promQuerySV.calSkuPriceByPromId(l.get(0),promRuleCheckDTO);
                     respDTO.setPromSkuPriceRespDTO(priceDTO);
                 }
                 reList.add(respDTO);
             }
             return reList;
        }else{
            return null;
        }
    }
    @Override
    public GdsPromListDTO listPromInfoNew(PromRuleCheckDTO promRuleCheckDTO, boolean ifConstCheck,
            String promClass) throws BusinessException {
        
        GdsPromListDTO result = new GdsPromListDTO();
        
        List<PromListRespDTO> promlist=new ArrayList<PromListRespDTO>();
        //获得促销列表
        List<PromInfoDTO>  allprominfo= defaultSkuCheckSV.listGdsPromInfo(promRuleCheckDTO,true,null);
        //处理秒杀促销情况，调整秒杀促销优先级
        //判断该促销列表是否存在秒杀活动（可能存在多个，根据开始时间排序，我们只取第一个进行处理），
        //此时说明该秒杀活动已到展示时间，必须要展示；则置秒杀活动存在标识位为true。
        //秒杀活动是否已开始，若已开始，则将秒杀活动放到促销列表第一位，并置秒杀活动开始标识为true；
        //若未开始，则将秒杀活动放到促销列表最后一位，并置秒杀活动开始标识为false。  
        //TODO :something
        SeckillDiscountDTO seckillflag = dealSeckillPromList(allprominfo);;
        result.setSeckill(seckillflag);
        
         if(!CollectionUtils.isEmpty(allprominfo)){
             for(int i=0;i<allprominfo.size();i++){
                 PromListRespDTO  respDTO=new PromListRespDTO();
                 respDTO.setPromInfoDTO(allprominfo.get(i));
                 if(i==0){
                     //计算价格
                     PromSkuPriceRespDTO priceDTO= promQuerySV.calSkuPriceByPromId(allprominfo.get(0),promRuleCheckDTO);
                     respDTO.setPromSkuPriceRespDTO(priceDTO);
                 }
                 promlist.add(respDTO);
             }
             result.setPromList(promlist);
             return result;
        }else{
            return null;
        }
    }
    /**
     * 促销验证是否免邮
     * 
     * @param promIds
     * @throws Exception
     * @author huangjx
     */
    public boolean checkFreePost(List<Long> promIds) throws BusinessException{
    	boolean reValue= Boolean.FALSE;
    	if(!CollectionUtils.isEmpty(promIds)){
    		for(Long p:promIds){
    			reValue=promDiscountRuleSV.ifFreePost(p, null);
    			if(reValue){
    				break;
    			}
    		}
    	} 
    	return reValue;
    }
    
    private boolean existSeckillProm(List<PromInfoDTO> promList){
        if (CollectionUtils.isEmpty(promList)) {
            return false;
        }
        for (PromInfoDTO promInfoDTO : promList) {
            if ("1000000019".equals(promInfoDTO.getPromTypeCode())) {
                return true;
            }
        }
        return false;
    }

    private void adjustSeckillPromToHead(List<PromInfoDTO> promList, int index){
        if (index == 0) {
            return;
        }
        PromInfoDTO newhead = promList.get(index);
        PromInfoDTO oldhead = promList.get(0);
        promList.set(0, newhead);
        promList.set(index, oldhead);
    }
    private void adjustSeckillPromToTail(List<PromInfoDTO> promList, int index){
        int tailindex = promList.size()-1;
        if (index == tailindex) {
            return;
        }
        PromInfoDTO newtail = promList.get(index);
        PromInfoDTO oldtail = promList.get(tailindex);
        promList.set(tailindex, newtail);
        promList.set(index, oldtail);
    }
    private boolean checkSeckillPromStart(PromInfoDTO seckillprom){
        if (seckillprom == null) {
            return false;
        }
        Timestamp now = DateUtil.getSysDate();
        if (now.after(seckillprom.getStartTime())&&now.before(seckillprom.getEndTime())) {
            return true;
        }
        return false;
    }
    private boolean checkSeckillPromActive(PromInfoDTO seckillprom){
        if (seckillprom == null) {
            return false;
        }
        Timestamp now = DateUtil.getSysDate();
        if (now.after(seckillprom.getEndTime())) {
            return false;
        }
        return true;
    }
    private SeckillDiscountDTO dealSeckillPromList(List<PromInfoDTO> promList){
        SeckillDiscountDTO seckillflag = new SeckillDiscountDTO();
        

        if (CollectionUtils.isNotEmpty(promList)) {
            List<PromInfoDTO> newPromList = new ArrayList<PromInfoDTO>(promList.size());           
            List<PromInfoDTO> seckillPromList = new ArrayList<PromInfoDTO>(promList.size());
            List<PromInfoDTO> otherPromList = new ArrayList<PromInfoDTO>(promList.size());

            for (PromInfoDTO promInfoDTO : promList) {
                if ("1000000019".equals(promInfoDTO.getPromTypeCode())) {
                    if (checkSeckillPromActive(promInfoDTO)) {
                        seckillPromList.add(promInfoDTO);
                    }
                }
                else {
                    otherPromList.add(promInfoDTO);
                }
            }
            promList.clear();
            if (CollectionUtils.isNotEmpty(seckillPromList)) {
                
                Collections.sort(seckillPromList, new Comparator<PromInfoDTO>(){

                    @Override
                    public int compare(PromInfoDTO o1, PromInfoDTO o2) {
                        return o1.getStartTime().compareTo(o2.getStartTime());
                    }
                    
                });
                for(int i=0;i<seckillPromList.size();i++){
                    if (!checkSeckillPromActive(seckillPromList.get(i))) {
                        seckillPromList.remove(i);
                    }
                }
                if (CollectionUtils.isNotEmpty(seckillPromList)) {
                    seckillflag.setExist(true);
                    seckillflag.setSystemTime(DateUtil.getSysDate());
                    seckillflag.setSeckillProm(seckillPromList.get(0));
                    if (checkSeckillPromStart(seckillPromList.get(0))) {
                        seckillflag.setStart(true);
                    } 
                }
                //若存在未开始或未结束的秒杀，将秒杀促销放在链表头部
                promList.addAll(seckillPromList);
            }
            promList.addAll(otherPromList);
        }  
        return seckillflag;
    }
}
