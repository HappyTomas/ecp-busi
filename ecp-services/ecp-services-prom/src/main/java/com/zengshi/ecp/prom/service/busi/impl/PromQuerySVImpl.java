package com.zengshi.ecp.prom.service.busi.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dao.mapper.busi.PromCalRuleMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromConstraintMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromGiftMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuLimitMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.model.PromCalRule;
import com.zengshi.ecp.prom.dao.model.PromCalRuleCriteria;
import com.zengshi.ecp.prom.dao.model.PromConstraint;
import com.zengshi.ecp.prom.dao.model.PromConstraintCriteria;
import com.zengshi.ecp.prom.dao.model.PromGift;
import com.zengshi.ecp.prom.dao.model.PromGiftCriteria;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSkuLimit;
import com.zengshi.ecp.prom.dao.model.PromSkuLimitCriteria;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromDiscountDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuPriceRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.db.distribute.DistributeRuleAssist;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromQuerySVImpl extends GeneralSQLSVImpl implements IPromQuerySV {

    private static final String MODULE = PromQuerySVImpl.class.getName();

    // 赠品
    @Resource
    private PromGiftMapper promGiftMapper;
    
    @Resource
    private PromConstraintMapper promConstraintMapper;
    
    @Resource
    private PromCalRuleMapper promCalRuleMapper;
    
    @Resource
    private Converter<PromGift, PromGiftDTO> promGiftDTOConverter;
    
    // 促销类型
    @Resource
    private IPromTypeSV promTypeSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private Converter<PromConstraint,PromConstraintDTO> promConstraintDTOConverter;
    
    @Resource
    private Converter<PromCalRule,PromCalRuleDTO> promCalRuleDTOConverter;
    
    // 促销类型
    @Resource
    private PromSkuMapper promSkuMapper;
    
    @Resource
    private Converter<PromSku,PromSkuDTO> promSkuDTOConverter;
    
    @Resource
    private PromSkuLimitMapper promSkuLimitMapper;
    
    @Resource
    private Converter<PromSkuLimit,PromSkuLimitDTO> promSkuLimitDTOConverter;
    
    @Resource
    private Converter<PromSku,PromSkuRespDTO> promSkuRespDTOConverter;
    
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
    
    //部分促销类型不参与计算价格 列表 设值在ecp-application-context-prom.xml中
    private List noCheckPriceList;
    
    public List getNoCheckPriceList() {
		return noCheckPriceList;
	}
	public void setNoCheckPriceList(List noCheckPriceList) {
		this.noCheckPriceList = noCheckPriceList;
	}
	/**
     * TODO获得赠品列表
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#queryPromGift(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> queryPromGift(Long promId) throws BusinessException{
        
        //查询参数为空 直接返回为空
        if(promId==null){
            return null;
        }
        PromGiftCriteria example=new PromGiftCriteria();
        PromGiftCriteria.Criteria cr=example.createCriteria();
        cr.andPromIdEqualTo(promId);
        
        List<PromGift> promGiftList=promGiftMapper.selectByExample(example);
        
        if(CollectionUtils.isEmpty(promGiftList)){
            return null;
        }
        List<PromGiftDTO> reList=new ArrayList<PromGiftDTO>();
        for(PromGift promGift:promGiftList){
            reList.add(promGiftDTOConverter.convert(promGift));
        }
        return reList;
    }
    /**
     * TODO获得赠品列表
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#queryPromGift(java.lang.Long, java.lang.Long)
     * @param promId
     * @param shopId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGiftDTO> queryPromGift(Long promId,Long shopId) throws BusinessException{
        
        //查询参数为空 直接返回为空
        if(promId==null){
            return null;
        }
        PromGiftCriteria example=new PromGiftCriteria();
        PromGiftCriteria.Criteria cr=example.createCriteria();
        cr.andPromIdEqualTo(promId);
        cr.andShopIdEqualTo(shopId);
        List<PromGift> promGiftList=promGiftMapper.selectByExample(example);
        
        if(CollectionUtils.isEmpty(promGiftList)){
            return null;
        }
        List<PromGiftDTO> reList=new ArrayList<PromGiftDTO>();
        for(PromGift promGift:promGiftList){
            reList.add(promGiftDTOConverter.convert(promGift));
        }
        return reList;
    }
    /**
     * 促销赠送 积分 优惠券
     * @param promId
     * @param ordPromDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromPresentDTO promPresent(Long promId,OrdPromDTO ordPromDTO) throws BusinessException{
        
        //促销id为空
        if(promId==null){
            return null;
        }
        
        //通过促销id 获得促销代码
        PromInfoDTO promInfoDTO=promInfoSV.queryPromInfoDTOById(promId);
       
     /*   if (promInfoDTO == null) {
             String[] e=new String[1];
             e[0]=promId.toString();
            throw new BusinessException("prom.400083",e);
        }*/
      if (promInfoDTO == null) {
           return null;
       }
        //获得促销类型
        PromTypeResponseDTO promTypeResponseDTO = promTypeSV
                .queryPromTypeByCode(promInfoDTO.getPromTypeCode());
        
        //如果没有配置促销类型
        if (promTypeResponseDTO == null) {
            throw new BusinessException("prom.400041");
        }
        //服务id
        String serviceId = promTypeResponseDTO.getServiceId();
        //获得ClassPathXmlApplicationContext context bean
        IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(serviceId, IPromDiscountRuleSV.class);
        
        return iPromDiscountRuleService.promPresent(promId,ordPromDTO);
    }
    /**
     * TODO促销规则信息
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#queryPromConstraint(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromConstraintDTO queryPromConstraint(Long promId) throws BusinessException {
        
        //参数为空 返回null
        if(promId==null){
            return null;
        }

        //组织条件 
         PromConstraintCriteria example =new PromConstraintCriteria();
         PromConstraintCriteria.Criteria cr=example.createCriteria();
         cr.andPromIdEqualTo(promId);
         long t=System.currentTimeMillis();
         //查询clob字段值
         List<PromConstraint> blobList=promConstraintMapper.selectByExampleWithBLOBs(example);
         LogUtil.debug(MODULE, "promConstraintMapper.selectByExampleWithBLOBs(example)"+(System.currentTimeMillis()-t));
         //无此限制配置
         if(CollectionUtils.isEmpty(blobList)){
             return null;
         }
         //限制字段为空
         if(StringUtil.isEmpty(blobList.get(0).getConstraintStr())){
             return null;
         }
         PromConstraintDTO promConstraintDTO=new PromConstraintDTO();
         promConstraintDTO=JSON.parseObject(blobList.get(0).getConstraintStr(), PromConstraintDTO.class);
        return promConstraintDTO;
    }
    /**
     * TODO优惠规则
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#queryPromCalRule(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromCalRuleDTO queryPromCalRule(Long promId) throws BusinessException{
        
            //参数为空 返回null
            if(promId==null){
                return null;
            }
           //组织条件
            PromCalRuleCriteria example =new PromCalRuleCriteria();
            PromCalRuleCriteria.Criteria cr=example.createCriteria();
            cr.andPromIdEqualTo(promId);
            //查询clob字段值
            List<PromCalRule> blobList=promCalRuleMapper.selectByExampleWithBLOBs(example);
            //无此限制配置
            if(CollectionUtils.isEmpty(blobList)){
                return null;
            }
            //限制字段为空
            if(StringUtil.isEmpty(blobList.get(0).getCalStr())){
                return null;
            }
            
           return promCalRuleDTOConverter.convert(blobList.get(0));
    }
    /**
     * TODO获得促销商品列表
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#listPromotionSku(java.lang.Long)
     * @param promId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuDTO> listPromotionSku(PromSkuDTO promSkuDTO) throws BusinessException{
        
        //查询参数组织
        PromSkuCriteria example =new PromSkuCriteria();
        this.initPromSkuContion(promSkuDTO, example);
        
        if(promSkuDTO.getTableIndex()!=null && promSkuDTO.getTableIndex()>0){
            DistributeRuleAssist.setTableIndex(promSkuDTO.getTableIndex());
        }
        //查询功能
        List<PromSku> queryList=promSkuMapper.selectByExample(example);
        
        if(promSkuDTO.getTableIndex()!=null && promSkuDTO.getTableIndex()>0){
            DistributeRuleAssist.clearTableIndex();
        }
        
        //为空返回
        if(CollectionUtils.isEmpty(queryList)){
            return null;
        }
         //非空 返回列表值   
         List<PromSkuDTO> reList=new ArrayList<PromSkuDTO>();
         
         for( PromSku promSku:queryList){
             reList.add(promSkuDTOConverter.convert(promSku));
         }
         return reList;
    }
    /**
     * 促销参与商品 查询 分页
     * 
     * @param queryPromType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromSkuRespDTO> pagePromotionSku(
            PromSkuDTO promSkuDTO) throws BusinessException {

        //查询参数组织
        PromSkuCriteria example =new PromSkuCriteria();
        //初始化查询页数 数量
        example.setLimitClauseCount(promSkuDTO.getPageSize());
        example.setLimitClauseStart(promSkuDTO.getStartRowIndex());
        //排序
        example.setOrderByClause("id desc");
        //初始化查询条件
        this.initPromSkuContion(promSkuDTO, example);
        
       if(promSkuDTO.getTableIndex()!=null && promSkuDTO.getTableIndex()>0){
            DistributeRuleAssist.setTableIndex(promSkuDTO.getTableIndex());
        }
        //返回查询分页结果集
        PageResponseDTO<PromSkuRespDTO> pages =super.queryByPagination(promSkuDTO, example, true,
                new PaginationCallback<PromSku, PromSkuRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromSku> queryDB(BaseCriteria example) {

                        return promSkuMapper
                                .selectByExample((PromSkuCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promSkuMapper.countByExample((PromSkuCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromSkuRespDTO warpReturnObject(PromSku t) {
                        return promSkuRespDTOConverter.convert(t);
                    }
                });
       if(promSkuDTO.getTableIndex()!=null && promSkuDTO.getTableIndex()>0){
           DistributeRuleAssist.clearTableIndex();
        }
       return pages;
    }

    /**
     * TODO获得促销商品黑名单列表
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#listPromotionSkuLimit(com.zengshi.ecp.prom.dubbo.dto.PromSkuLimitDTO)
     * @param promSkuLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSkuLimitDTO> listPromotionSkuLimit(PromSkuLimitDTO promSkuLimitDTO) throws BusinessException{
       //查询参数组织
        PromSkuLimitCriteria example =new PromSkuLimitCriteria();
        PromSkuLimitCriteria.Criteria cr=example.createCriteria();
        //编码id
        if(!StringUtil.isEmpty(promSkuLimitDTO.getId())){
            cr.andIdEqualTo(promSkuLimitDTO.getId());
        }
        //促销编码id
        if(!StringUtil.isEmpty(promSkuLimitDTO.getPromId())){
            cr.andPromIdEqualTo(promSkuLimitDTO.getPromId());
        }
        //分类编码
        if(!StringUtil.isEmpty(promSkuLimitDTO.getCatgCode())){
            cr.andCatgCodeEqualTo(promSkuLimitDTO.getCatgCode());
        }
        //商品
        if(!StringUtil.isEmpty(promSkuLimitDTO.getGdsId())){
            cr.andGdsIdEqualTo(promSkuLimitDTO.getGdsId());
        }
        //单品
        if(!StringUtil.isEmpty(promSkuLimitDTO.getSkuId())){
            cr.andSkuIdEqualTo(promSkuLimitDTO.getSkuId());
        }
        //参与类型  1 分类 2单品
        if(!StringUtil.isEmpty(promSkuLimitDTO.getJoinType())){
            cr.andJoinTypeEqualTo(promSkuLimitDTO.getJoinType());
        }
        //查询功能
        List<PromSkuLimit> queryList=promSkuLimitMapper.selectByExample(example);
        
        //为空返回
        if(CollectionUtils.isEmpty(queryList)){
            return null;
        }
         //非空 返回列表值   
         List<PromSkuLimitDTO> reList=new ArrayList<PromSkuLimitDTO>();
         
         for( PromSkuLimit promSkuLimit:queryList){
             reList.add(promSkuLimitDTOConverter.convert(promSkuLimit));
         }
         return reList;
    }
    /**
     * TODO 根据促销id 计算单品价格
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV#calSkuPriceByPromId(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO)
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromSkuPriceRespDTO calSkuPriceByPromId(PromInfoDTO promInfoDTO,
            PromRuleCheckDTO promRuleCheckDTO) throws BusinessException {

        PromSkuPriceRespDTO resDTO = new PromSkuPriceRespDTO();
        resDTO.setDiscountCaclPrice(BigDecimal.valueOf(promRuleCheckDTO.getBasePrice()));
        resDTO.setDiscountFinalPrice(BigDecimal.valueOf(promRuleCheckDTO.getBuyPrice()));
        if (promInfoDTO != null && promInfoDTO.getId() != null) {
            // 满足促销id 或者选择对应的促销id
            if (StringUtil.isEmpty(promInfoDTO.getPromTypeCode())) {
                promInfoDTO = promInfoSV.queryPromInfoDTOById(promInfoDTO.getId());
            }
          
            if(!promRuleCheckDTO.getIfCalPrice()){
            	   //判断促销生失效时间，若促销还处于生效状态，则计算，否则，不计算。
                Timestamp currentTime = DateUtil.getSysDate();
                if (!(currentTime.after(promInfoDTO.getStartTime()) && currentTime.before(promInfoDTO.getEndTime()))) {
                	return resDTO;
                }
            }
         
            //订单级别促销 不参与计算价格
            if (this.ifCalPricePromType(promInfoDTO)) {
                // 获得促销类型
                PromTypeResponseDTO promTypeResponseDTO = null;
                promTypeResponseDTO = promTypeSV.queryPromTypeByCode(promInfoDTO.getPromTypeCode());

                // 如果没有配置促销类型
                if (promTypeResponseDTO == null) {
                    throw new BusinessException("prom.400041");
                }
                // 服务id
                String serviceId = promTypeResponseDTO.getServiceId();
                // 获得ClassPathXmlApplicationContext context bean
                IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(
                        serviceId, IPromDiscountRuleSV.class);

                // 获得计算规则 json值
                PromCalRuleDTO promCalRuleDTO = this.queryPromCalRule(promInfoDTO.getId());
                String json = null;
                if (promCalRuleDTO != null) {
                    json = promCalRuleDTO.getCalStr();
                }
                OrdPromDetailDTO ordPromDetailDTO = new OrdPromDetailDTO();
                ordPromDetailDTO.setBuyPrice(promRuleCheckDTO.getBuyPrice());
                ordPromDetailDTO.setBasePrice(promRuleCheckDTO.getBasePrice());
                ordPromDetailDTO.setGdsId(promRuleCheckDTO.getGdsId());
                ordPromDetailDTO.setGdsName(promRuleCheckDTO.getGdsName());
                ordPromDetailDTO.setSkuId(promRuleCheckDTO.getSkuId());
                ordPromDetailDTO.setShopId(promRuleCheckDTO.getShopId());
                ordPromDetailDTO.setStaffId(promRuleCheckDTO.getStaffId());
                ordPromDetailDTO.setStaff(promRuleCheckDTO.getStaff());
                ordPromDetailDTO.setPromInfoDTO(promInfoDTO);
                ordPromDetailDTO.setIfFulfillProm(Boolean.TRUE);
                //为空 默认1
                if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
                    ordPromDetailDTO.setOrderAmount(Long.valueOf("1"));
                }else{
                    ordPromDetailDTO.setOrderAmount(Long.valueOf(promRuleCheckDTO.getBuyCnt()));
                }
                // 计算优惠信息
                PromDiscountDTO promDiscountDTO = iPromDiscountRuleService.calculatePromotion(
                        null, ordPromDetailDTO, json);
                // 优惠金额
                resDTO.setDiscountMoney(promDiscountDTO.getDiscountMoney());
                resDTO.setDiscountPrice(promDiscountDTO.getDiscountPrice());
                resDTO.setDiscountAmount(promDiscountDTO.getDiscountAmount());
                resDTO.setDiscountPriceMoney(promDiscountDTO.getDiscountPriceMoney());
                resDTO.setDiscountCaclPrice(promDiscountDTO.getDiscountCaclPrice());
                resDTO.setDiscountFinalPrice(promDiscountDTO.getDiscountFinalPrice());
               
            }
        }
        return resDTO;
    }
    private void initPromSkuContion(PromSkuDTO promSkuDTO, PromSkuCriteria example){
      //查询参数组织
        PromSkuCriteria.Criteria cr=example.createCriteria();
        //编码id
        if(!StringUtil.isEmpty(promSkuDTO.getId())){
            cr.andIdEqualTo(promSkuDTO.getId());
        }
        //促销编码id
        if(!StringUtil.isEmpty(promSkuDTO.getPromId())){
            cr.andPromIdEqualTo(promSkuDTO.getPromId());
        }
        //分类编码
        if(!StringUtil.isEmpty(promSkuDTO.getCatgCode())){
            cr.andCatgCodeEqualTo(promSkuDTO.getCatgCode());
        }
        //商品
        if(!StringUtil.isEmpty(promSkuDTO.getGdsId())){
            cr.andGdsIdEqualTo(promSkuDTO.getGdsId());
        }
        //单品
        if(!StringUtil.isEmpty(promSkuDTO.getSkuId())){
            cr.andSkuIdEqualTo(promSkuDTO.getSkuId());
        }
        //是否叠加
        if(!StringUtil.isEmpty(promSkuDTO.getIfComposit())){
            cr.andIfCompositEqualTo(promSkuDTO.getIfComposit());
        }
        //是否展示
        if(!StringUtil.isEmpty(promSkuDTO.getIfShow())){
            cr.andIfShowEqualTo(promSkuDTO.getIfShow());
        }
        //参与类型  1 分类 2单品
        if(!StringUtil.isEmpty(promSkuDTO.getJoinType())){
            cr.andJoinTypeEqualTo(promSkuDTO.getJoinType());
        }
        //搭配类型
        if(!StringUtil.isEmpty(promSkuDTO.getMatchType())){
            cr.andMatchTypeEqualTo(promSkuDTO.getMatchType());
        }
        //店铺
        if(!StringUtil.isEmpty(promSkuDTO.getShopId())){
            cr.andShopIdEqualTo(promSkuDTO.getShopId());
        }
        //站点
        if(!StringUtil.isEmpty(promSkuDTO.getSiteId())){
            cr.andSiteIdEqualTo(promSkuDTO.getSiteId());
        }
        //状态
        if(!StringUtil.isEmpty(promSkuDTO.getStatus())){
            cr.andStatusEqualTo(promSkuDTO.getStatus());
        }
        //是否有效
        if(!StringUtil.isEmpty(promSkuDTO.getIfValid())){
            cr.andIfValidEqualTo(promSkuDTO.getIfValid());
        }else{
            cr.andIfValidEqualTo(PromConstants.PromSku.IF_VALID_1);
        }
    }
    /*
     * 验证是否计算促销类型价格
     */
    private boolean ifCalPricePromType(PromInfoDTO promInfoDTO){
    	boolean re=false;
    	if(!CollectionUtils.isEmpty(this.noCheckPriceList)){
    		if (promInfoDTO.getId() > 0 && PromConstants.PromType.PROM_ClASS_20.equals(promInfoDTO.getPromClass()) && !CollectionUtils.contains(this.noCheckPriceList.iterator(), promInfoDTO.getPromTypeCode())) {
        		re=true;
        	}
    	}else{
    		if (promInfoDTO.getId() > 0 && PromConstants.PromType.PROM_ClASS_20.equals(promInfoDTO.getPromClass())) {
        		re=true;
        	}
    	}
		return re;
    }
}
