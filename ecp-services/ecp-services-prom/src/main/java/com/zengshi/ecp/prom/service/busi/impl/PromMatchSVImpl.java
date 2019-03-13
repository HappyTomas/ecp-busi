package com.zengshi.ecp.prom.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dao.mapper.busi.PromMatchSkuMapper;
import com.zengshi.ecp.prom.dao.model.PromMatchSku;
import com.zengshi.ecp.prom.dao.model.PromMatchSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dubbo.dto.PromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV;
import com.zengshi.ecp.prom.service.busi.sku.impl.GdsCatgSVImpl;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-9 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromMatchSVImpl extends GeneralSQLSVImpl implements IPromMatchSV {

    private static final String MODULE = PromMatchSVImpl.class.getName();

    @Resource
    private PromMatchSkuMapper promMatchSkuMapper;

    @Resource
    private Converter<PromMatchSkuDTO, PromMatchSku> promMatchSkuConverter;

    @Resource(name = "defaultSkuCheckSV")
    private IPromSkuCheckSV defaultSkuCheckSV;

    @Resource
    private Converter<PromMatchSku, PromMatchSkuDTO> promMatchSkuDTOConverter;

    @Resource
    private Converter<PromMatchSku, PromMatchSkuRespDTO> promMatchSkuRespDTOConverter;

    @Resource
    private IPromInfoSV promInfoSV;

    @Resource
    private IPromConstraintSV promConstraintSV;

    @Resource
    private IPromSkuSV promSkuSV;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private GdsCatgSVImpl gdsCatgSV;

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource(name = "seq_prom_match_sku_id")
    private PaasSequence seq_prom_match_sku_id;

    /**
     * TODO促销搭配商品 保存
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV#saveMatchSku(com.zengshi.ecp.prom.dubbo.dto.PromDTO)
     * @param promDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMatchSku(PromDTO promDTO) throws BusinessException {

        if (!CollectionUtils.isEmpty(promDTO.getMatchSkuDTOList())) {

            List<PromMatchSkuDTO> promMatchSkuDTOList = (List<PromMatchSkuDTO>) promDTO
                    .getMatchSkuDTOList();
            // dto 转为 model
            for (PromMatchSkuDTO dto : promMatchSkuDTOList) {
                dto.setId(seq_prom_match_sku_id.nextValue());
                dto.setPromId(promDTO.getId());
                dto.setCreateStaff(promDTO.getCreateStaff());
                dto.setCreateTime(new Timestamp(promDTO.getCreateTime().getTime()));
                dto.setStatus(PromConstants.PromMatchSku.STATUS_1);
                dto.setSiteId(promDTO.getSiteId());
                dto.setShopId(promDTO.getShopId());
                dto.setStartTime(promDTO.getStartTime());
                dto.setEndTime(promDTO.getEndTime());
                dto.setPromStatus(promDTO.getStatus());
                promMatchSkuMapper.insert(promMatchSkuConverter.convert(dto));
            }
        }
    }

    /**
     * TODO搭配商品
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromSV#queryMatchList(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromMatchDTO> queryMatchList(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {

        // 返回值 列表定义
        List<PromMatchDTO> reList = new ArrayList<PromMatchDTO>();
        // 入参非空验证
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400063 = 传入参数 商品id必需要有值！
            throw new BusinessException("prom.400063");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400064 = 传入参数 计算日期必需要有值！
            throw new BusinessException("prom.400064");
        }
        // 店铺编码
        if (promRuleCheckDTO.getShopId() == null) {
            throw new BusinessException("prom.400069");
        }
        // 站点编码
        if (promRuleCheckDTO.getSiteId() == null) {
            throw new BusinessException("prom.400155");
        }
        /*
         * if (promRuleCheckDTO.getSkuId() == null) { // prom.400063 = 传入参数 单品id必需要有值！ throw new
         * BusinessException("prom.400210"); }
         */
        // 搭配类型 1 可选搭配 2 固定搭配
        if (StringUtil.isEmpty(promRuleCheckDTO.getMatchType())) {
            throw new BusinessException("prom.400156");
        }

        // 促销编码集合 过滤重复 promId
        Set<Long> promIdSet = new HashSet<Long>();

        // 1通过 siteid shopId gds sku status promstatus starttime end time 获得 促销id列表
        // 2 通过promId 验证是否能参与
        // 3 根据优先级排序

        // 调用接口 获得gds sku当前分类
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(promRuleCheckDTO.getGdsId());

        GdsQueryOption[] gdsQuery = new GdsQueryOption[1];
        gdsQuery[0] = GdsOption.GdsQueryOption.CATG;// 分类
        gdsInfoReqDTO.setGdsQueryOptions(gdsQuery);

        GdsInfoRespDTO gdsDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
        // 分类代码
   /*     String catg = null;
        if (gdsDTO != null) {
            if (gdsDTO.getMainCategory() != null) {
                catg = gdsDTO.getMainCategory().getCatgCode();
            }
        }*/
        List<String> platformCatgList=new ArrayList<String>();
        
        if(gdsDTO!=null){
        	//下架 删除 为null
	        if(!CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
	              for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
	                  platformCatgList.add(p.getCatgCode());
	              }
	        }
        }
        // 获得搭配促销编码
        PromMatchSkuDTO promMatchSkuDTO = new PromMatchSkuDTO();
        promMatchSkuDTO.setStartTime(promRuleCheckDTO.getCalDate());
        promMatchSkuDTO.setEndTime(promRuleCheckDTO.getCalDate());
        promMatchSkuDTO.setShopId(promRuleCheckDTO.getShopId());
        promMatchSkuDTO.setSiteId(promRuleCheckDTO.getSiteId());
        promMatchSkuDTO.setGdsId(promRuleCheckDTO.getGdsId());
        promMatchSkuDTO.setSkuId(promRuleCheckDTO.getSkuId());
        promMatchSkuDTO.setMatchType(promRuleCheckDTO.getMatchType());
        promMatchSkuDTO.setPromStatus(PromConstants.PromInfo.STATUS_10);// 生效

        List<PromMatchSkuDTO> promMatchSkuDTOList = this.queryMatchSkuList(promMatchSkuDTO);

        if(!CollectionUtils.isEmpty(promMatchSkuDTOList)){
            for (PromMatchSkuDTO dto : promMatchSkuDTOList) {
                promIdSet.add(dto.getPromId());
            }
        }
        
        if(!PromConstants.PromMatchSku.MATCH_TYPE_2.equals(promRuleCheckDTO.getMatchType())){
            // 1、主搭配商品
           
            List<PromSku> promSkuMList = new ArrayList<PromSku>();
    
            // 单品、商品 相关促销列表 (部分参与 t_prom_sku设置对应单品信息)
            PromSku promSkuQ = new PromSku();
            promSkuQ.setShopId(promRuleCheckDTO.getShopId());
            promSkuQ.setPromClass(PromConstants.PromType.PROM_ClASS_20);
            promSkuQ.setGdsId(promRuleCheckDTO.getGdsId());
            promSkuQ.setSkuId(promRuleCheckDTO.getSkuId());
            promSkuQ.setMatchType(promRuleCheckDTO.getMatchType());
            promSkuQ.setSiteId(promRuleCheckDTO.getSiteId());
            promSkuQ.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);// 单品参与
            promSkuQ.setIfShow(PromConstants.PromInfo.IF_SHOW_1);// 展示
            promSkuQ.setMatchType("2");
    
            // 1.1、主搭配商品促销获得
            // 根据gdsid +skuId 获得参与促销列表
            promSkuMList = defaultSkuCheckSV.querySkuPromList(promSkuQ, promRuleCheckDTO);
    
            if (!CollectionUtils.isEmpty(promSkuMList)) {
                for (PromSku sku : promSkuMList) {
                    promIdSet.add(sku.getPromId());
                }
            }
            // 1.2、主搭配商品(分类)促销获得
            // 根据gdsid +skuId 的分类编码获得参与促销列表(不需要sql 查询 当子查询分类多是 拼接sql超出 范围)
            promSkuQ.setGdsId(null);
            promSkuQ.setSkuId(null);
            promSkuQ.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);// 分类参与
    
            // 通过条件 获得 相关分类促销ID 列表
            List<PromSku> promCatgList = new ArrayList<PromSku>();
            promCatgList = defaultSkuCheckSV.querySkuPromList(promSkuQ, promRuleCheckDTO);
    
    /*        if (!CollectionUtils.isEmpty(promCatgList)) {
                for (PromSku catgDTO : promCatgList) {
                    Integer result = gdsCatgSV.compareCatg(catg, catgDTO.getCatgCode());
                    if (GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result)
                            || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result)) {
                        // 返回结果关系
                        promIdSet.add(catgDTO.getPromId());
                    }
                }
            }*/
            if (!CollectionUtils.isEmpty(promCatgList)) {
                // 验证 当前获得列表对应的分类 属于当前商品+单品对应分类或者子分类。 如果是加入列表，否则不加入
                for (PromSku catgDTO : promCatgList) {
                    //调用接口 获得 当前分类下子节点 
                    Integer result=  gdsCatgSV.compareCatg(platformCatgList,catgDTO.getCatgCode());
                    if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
                      //返回结果关系
                        promIdSet.add(catgDTO.getPromId());
                    }
                }
            }
        }

        List<PromInfoDTO> promInfoDTOList = new ArrayList<PromInfoDTO>();
        // 验证客户是否能参与
        for (Long promId : promIdSet) {
            PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(promId);
            promRuleCheckDTO.setPromId(promId);
            // 验证是否能参与促销
            if (defaultSkuCheckSV.gdsCheck(promInfoDTO, promRuleCheckDTO)
                    && promConstraintSV.check(promId, promRuleCheckDTO)) {
                promInfoDTOList.add(promInfoDTO);
            }
        }
        // 过滤 promInfoDTOList 不能参与的促销
        if (CollectionUtils.isEmpty(promInfoDTOList)) {
            reList = null;
        } else {
            // 促销id
            for (PromInfoDTO promDTO : promInfoDTOList) {
                PromMatchDTO promMatchDTO = new PromMatchDTO();
                promMatchDTO.setPromInfoDTO(promDTO);

                List<PromMatchSkuDTO> list = new ArrayList<PromMatchSkuDTO>();
                List<PromMatchSkuDTO> listMatch = new ArrayList<PromMatchSkuDTO>();
             
                PromMatchSkuDTO queryDTO = new PromMatchSkuDTO();
                queryDTO.setPromId(promDTO.getId());
                // 搭配列表
                listMatch.addAll(this.queryMatchSkuList(queryDTO));
                
                // 查找主搭配商品(详情进入主商品 才可展示，附商品进入不展示)
                if(PromConstants.PromType.PROM_TYPE_CODE_1000000011.equals(promDTO.getPromTypeCode())){
                    List<PromSku> promSkuList = promSkuSV.querySkuPromByPromId(promDTO.getId(),
                            PromConstants.PromSku.IF_VALID_1);
                    
                    PromMatchSkuDTO d = new PromMatchSkuDTO();
                    
                    if (!CollectionUtils.isEmpty(promSkuList)) {
                        for (PromSku promSku : promSkuList) {
                            // 单品直接copy值
                            if (PromConstants.PromSku.JOIN_TYPE_2.equals(promSku.getJoinType())) {
                                if(promRuleCheckDTO.getSkuId().equals(promSku.getSkuId())){
                                    d.setGdsId(promSku.getGdsId());
                                    d.setMatchType(promSku.getMatchType());
                                    //d.setPrice(gdsSkuInforespDTO.getDiscountPrice());
                                    d.setPromCnt(promSku.getPromCnt());
                                    d.setPromId(promSku.getPromId());
                                    d.setPromStatus(promDTO.getStatus());
                                    d.setShopId(promSku.getShopId());
                                    d.setSiteId(promSku.getSiteId());
                                    d.setSkuId(promSku.getSkuId());
                                    d.setStartTime(promSku.getStartTime());
                                    d.setStatus(promSku.getStatus());
                                    d.setEndTime(promSku.getEndTime());
                                    break;
                                }
                            }
                            // 分类
                            if (PromConstants.PromSku.JOIN_TYPE_1.equals(promSku.getJoinType())) {
                                    // 当前传入单品是否为该分类以及子分类的单品
                                        Integer result = gdsCatgSV.compareCatg(platformCatgList, promSku.getCatgCode());
                                        if (GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result)
                                                || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN
                                                        .equals(result)) {
                                            // 返回结果关系
                                            // ObjectCopyUtil.copyObjValue(promSku, d, "", false);
                                            d.setGdsId(promSku.getGdsId());
                                            d.setMatchType(promSku.getMatchType());
                                           // d.setPrice(gdsSkuInforespDTO.getDiscountPrice());
                                            d.setPromCnt(promSku.getPromCnt());
                                            d.setPromId(promSku.getPromId());
                                            d.setPromStatus(promDTO.getStatus());
                                            d.setShopId(promSku.getShopId());
                                            d.setSiteId(promSku.getSiteId());
                                            d.setSkuId(promRuleCheckDTO.getSkuId());
                                            d.setStartTime(promSku.getStartTime());
                                            d.setStatus(promSku.getStatus());
                                            d.setEndTime(promSku.getEndTime());
                                            break;
                                        }
                            }
                        }
                    }
                    //如果主商品 为空 不展示
                    if(StringUtil.isEmpty(d.getSkuId())){
                        continue;
                    }else{
                        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                        gdsSkuInfoReqDTO.setId(Long.valueOf(promRuleCheckDTO.getSkuId()));
                        SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.CAlDISCOUNT };
                        gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                        gdsSkuInfoReqDTO.setStaff(promRuleCheckDTO.getStaff());
                        GdsSkuInfoRespDTO gdsSkuInforespDTO = new GdsSkuInfoRespDTO();
                        gdsSkuInforespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                        d.setPrice(gdsSkuInforespDTO.getDiscountPrice());
                        list.add(d);
                    }
                    //过滤重复
                    if(!CollectionUtils.isEmpty(listMatch)){
                          for(PromMatchSkuDTO pdto:listMatch){
                                  if(pdto.getSkuId().equals(d.getSkuId())){
                                      list.clear();
                                      break;
                                  }
                        }
                    }
                }
                list.addAll(listMatch);
                promMatchDTO.setPromMatchSkuDTOList(list);
                reList.add(promMatchDTO);
            }
        }
        return reList;
    }

    /**
     * 获得搭配促销列表
     * 
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromInfoDTO> queryMatchPromList(PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {

        List<PromInfoDTO> promInfoDTOList = new ArrayList<PromInfoDTO>();
        // 入参非空验证
        if (promRuleCheckDTO.getGdsId() == null) {
            // prom.400063 = 传入参数 商品id必需要有值！
            throw new BusinessException("prom.400063");
        }
        if (promRuleCheckDTO.getCalDate() == null) {
            // prom.400064 = 传入参数 计算日期必需要有值！
            throw new BusinessException("prom.400064");
        }
        // 店铺编码
        if (promRuleCheckDTO.getShopId() == null) {
            throw new BusinessException("prom.400069");
        }
        // 站点编码
        if (promRuleCheckDTO.getSiteId() == null) {
            throw new BusinessException("prom.400155");
        }
        // 1通过 siteid shopId gds sku status promstatus starttime end time 获得 促销id列表
        // 2 通过promId 验证是否能参与
        // 3 根据优先级排序

        PromMatchSkuDTO promMatchSkuDTO = new PromMatchSkuDTO();
        promMatchSkuDTO.setStartTime(promRuleCheckDTO.getCalDate());
        promMatchSkuDTO.setEndTime(promRuleCheckDTO.getCalDate());
        promMatchSkuDTO.setShopId(promRuleCheckDTO.getShopId());
        promMatchSkuDTO.setSiteId(promRuleCheckDTO.getSiteId());
        promMatchSkuDTO.setGdsId(promRuleCheckDTO.getGdsId());
        promMatchSkuDTO.setSkuId(promRuleCheckDTO.getSkuId());
        // promMatchSkuDTO.setMatchType(promRuleCheckDTO.getMatchType());
        promMatchSkuDTO.setPromStatus(PromConstants.PromInfo.STATUS_10);// 生效

        List<PromMatchSkuDTO> promMatchSkuDTOList = this.queryMatchSkuList(promMatchSkuDTO);

        if (!CollectionUtils.isEmpty(promMatchSkuDTOList)) {

            // 过滤重复 promId
            Set<Long> promIdSet = new HashSet<Long>();
            for (PromMatchSkuDTO dto : promMatchSkuDTOList) {
                promIdSet.add(dto.getPromId());
            }
            // 验证客户是否能参与
            for (Long promId : promIdSet) {
                PromInfoDTO promInfoDTO = promInfoSV.queryPromInfoDTOById(promId);
                promInfoDTOList.add(promInfoDTO);
            }
        }
        return promInfoDTOList;
    }

    /**
     * TODO搭配商品列表查询
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV#queryMatchSkuList(com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO)
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromMatchSkuDTO> queryMatchSkuList(PromMatchSkuDTO promMatchSkuDTO)
            throws BusinessException {

        // 返回值 列表定义
        List<PromMatchSkuDTO> reList = new ArrayList<PromMatchSkuDTO>();
        // 入参非空验证
        /*
         * if (promMatchSkuDTO.getPromId() == null) { throw new BusinessException("prom.400149"); }
         */
        PromMatchSkuCriteria example = new PromMatchSkuCriteria();
        PromMatchSkuCriteria.Criteria cr = example.createCriteria();

        // 促销编码
        if (!StringUtil.isEmpty(promMatchSkuDTO.getPromId())) {
            cr.andPromIdEqualTo(promMatchSkuDTO.getPromId());
        }
        // 商品编码
        if (!StringUtil.isEmpty(promMatchSkuDTO.getGdsId())) {
            cr.andGdsIdEqualTo(promMatchSkuDTO.getGdsId());
        }
        // id
        if (!StringUtil.isEmpty(promMatchSkuDTO.getId())) {
            cr.andIdEqualTo(promMatchSkuDTO.getId());
        }
        // 1可选搭配 2固定搭配
        if (!StringUtil.isEmpty(promMatchSkuDTO.getMatchType())) {
            cr.andMatchTypeEqualTo(promMatchSkuDTO.getMatchType());
        }
        // 单品编码
        if (!StringUtil.isEmpty(promMatchSkuDTO.getSkuId())) {
            cr.andSkuIdEqualTo(promMatchSkuDTO.getSkuId());
        }
        // 状态 0无效 1有效
        if (!StringUtil.isEmpty(promMatchSkuDTO.getStatus())) {
            cr.andStatusEqualTo(promMatchSkuDTO.getStatus());
        } else {
            cr.andStatusEqualTo(PromConstants.PromMatchSku.STATUS_1);
        }
        // 站点
        if (!StringUtil.isEmpty(promMatchSkuDTO.getSiteId())) {
            cr.andSiteIdEqualTo(promMatchSkuDTO.getSiteId());
        }
        // 店铺
        if (!StringUtil.isEmpty(promMatchSkuDTO.getShopId())) {
            cr.andShopIdEqualTo(promMatchSkuDTO.getShopId());
        }
        // 促销状态
        if (!StringUtil.isEmpty(promMatchSkuDTO.getPromStatus())) {
            cr.andPromStatusEqualTo(promMatchSkuDTO.getPromStatus());
        }/*
          * else{ cr.andPromStatusEqualTo(PromConstants.PromInfo.STATUS_10); }
          */
        // 开始时间 table.start_time <= date
        if (!StringUtil.isEmpty(promMatchSkuDTO.getStartTime())) {
            cr.andStartTimeLessThanOrEqualTo(new Timestamp(promMatchSkuDTO.getStartTime().getTime()));
        }
        // 截止时间 table.end_time>=date
        if (!StringUtil.isEmpty(promMatchSkuDTO.getEndTime())) {
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(promMatchSkuDTO.getEndTime().getTime()));
        }
        example.setOrderByClause("sort_num asc");
        List<PromMatchSku> l = promMatchSkuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(l)) {
            return null;
        }
        for (PromMatchSku promMatchSku : l) {
            reList.add(promMatchSkuDTOConverter.convert(promMatchSku));
        }
        return reList;
    }

    /**
     * TODO搭配商品列表查询
     * 
     * @see com.zengshi.ecp.prom.service.busi.interfaces.IPromMatchSV#queryMatchSkuList(com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO)
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromMatchSkuDTO> queryMatchSkuListByPromId(Long promId) throws BusinessException {

        // 返回值 列表定义
        List<PromMatchSkuDTO> reList = new ArrayList<PromMatchSkuDTO>();

        PromMatchSkuCriteria example = new PromMatchSkuCriteria();
        PromMatchSkuCriteria.Criteria cr = example.createCriteria();
        cr.andPromIdEqualTo(promId);
        example.setOrderByClause("sort_num asc");
        List<PromMatchSku> l = promMatchSkuMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(l)) {
            return null;
        }
        for (PromMatchSku promMatchSku : l) {
            reList.add(promMatchSkuDTOConverter.convert(promMatchSku));
        }
        return reList;
    }

    /**
     * 搭配商品列表 分页功能
     * 
     * @param promMatchSkuDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromMatchSkuRespDTO> queryPromMatchSkuForPage(
            PromMatchSkuDTO promMatchSkuDTO) throws BusinessException {

        if (promMatchSkuDTO == null) {
            return null;
        }
        PromMatchSkuCriteria example = new PromMatchSkuCriteria();
        // 初始化查询页数 数量
        example.setLimitClauseCount(promMatchSkuDTO.getPageSize());
        example.setLimitClauseStart(promMatchSkuDTO.getStartRowIndex());
        // 排序
        example.setOrderByClause("sort_num asc");
        // 初始化查询条件

        PromMatchSkuCriteria.Criteria cr = example.createCriteria();

        if (promMatchSkuDTO.getPromId() != null) {
            // 促销编码
            cr.andPromIdEqualTo(promMatchSkuDTO.getPromId());
        }

        if (promMatchSkuDTO.getGdsId() != null) {
            cr.andGdsIdEqualTo(promMatchSkuDTO.getGdsId());
        }
        if (promMatchSkuDTO.getId() != null) {
            cr.andIdEqualTo(promMatchSkuDTO.getId());
        }
        if (!StringUtil.isEmpty(promMatchSkuDTO.getMatchType())) {
            cr.andMatchTypeEqualTo(promMatchSkuDTO.getMatchType());
        }
        if (!StringUtil.isEmpty(promMatchSkuDTO.getSkuId())) {
            cr.andSkuIdEqualTo(promMatchSkuDTO.getSkuId());
        }
        if (!StringUtil.isEmpty(promMatchSkuDTO.getStatus())) {
            cr.andStatusEqualTo(promMatchSkuDTO.getStatus());
        }
        if (!StringUtil.isEmpty(promMatchSkuDTO.getSiteId())) {
            cr.andSiteIdEqualTo(promMatchSkuDTO.getSiteId());
        }
        // 返回查询分页结果集
        return super.queryByPagination(promMatchSkuDTO, example, true,
                new PaginationCallback<PromMatchSku, PromMatchSkuRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromMatchSku> queryDB(BaseCriteria example) {

                        return promMatchSkuMapper.selectByExample((PromMatchSkuCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promMatchSkuMapper.countByExample((PromMatchSkuCriteria) example);
                    }

                    // 查询结果转换
                    @Override
                    public PromMatchSkuRespDTO warpReturnObject(PromMatchSku t) {
                        return promMatchSkuRespDTOConverter.convert(t);
                    }
                });
    }
}
