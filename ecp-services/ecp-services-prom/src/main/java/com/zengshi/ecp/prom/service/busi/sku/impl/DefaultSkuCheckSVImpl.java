package com.zengshi.ecp.prom.service.busi.sku.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.GdsCategoryCompareRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dao.mapper.busi.PromSkuMapper;
import com.zengshi.ecp.prom.dao.model.PromInfo;
import com.zengshi.ecp.prom.dao.model.PromSku;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria;
import com.zengshi.ecp.prom.dao.model.PromSkuCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.sort.ComparatorPromInfoDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintCheckSV;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class DefaultSkuCheckSVImpl extends GeneralSQLSVImpl  implements IPromSkuCheckSV {

    private static final String MODULE = DefaultSkuCheckSVImpl.class.getName();

    @Resource
    private SkuCheckSVImpl skuCheckSV;

    @Resource
    private IPromConstraintSV promConstraintSV;

    @Resource
    private PromSkuMapper promSkuMapper;

    @Resource
    private IPromInfoSV promInfoSV;

    @Resource
    private Converter<PromInfo, PromInfoDTO> promInfoDTOConverter;
    
    @Resource
    private IGdsInfoQueryRSV  gdsInfoQueryRSV;
    
    @Resource
    private GdsCatgSVImpl  gdsCatgSV;
    
    @Resource(name = "skuBlackCheckSV")
    private IPromSkuCheckSV skuBlackCheckSV;
    
    @Resource(name = "catgBlackCheckSV")
    private IPromSkuCheckSV catgBlackCheckSV;
    
    @Resource
    private IPromConstraintCheckSV channelCheckSV;
    
    @Resource(name = "defaultCheckSV")
    private IPromConstraintCheckSV defaultCheckSV;
    

    /**
     * TODO销 商品信息是否需要验证.
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#isCheck(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean isCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        return Boolean.TRUE;
    }
 
    /**
     * TODO促销信息验证 促销信息 验证是否能参与促销
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#check(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean check(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        //(其他实现类，实现)
        return Boolean.FALSE;
    }
    /**
     * TODO 促销基本信息 验证
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#checkPromInfo(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean checkPromInfo(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException{
        // 是否展示 0 不展示
        if (PromConstants.PromInfo.IF_SHOW_0.equals(promInfoDTO.getIfShow())) {
            
            if(PromConstants.PromSys.IF_THROWS.equals(promRuleCheckDTO.getIfThrows())){
                //促销已过期
                throw new BusinessException("prom.400099");
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    /**
     * TODO促销商品验证
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#gdsCheck(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean gdsCheck(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException {
        return skuCheckSV.gdsCheck(promInfoDTO,promRuleCheckDTO);

    }
    
    /**
     * TODO促销黑名单验证
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#checkBlackLimit(com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO, com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO)
     * @param promInfoDTO
     * @param promRuleCheckDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public boolean checkBlackLimit(PromInfoDTO promInfoDTO,PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException{
            boolean chkValue=false;
            //验证是否能参与促销
            if (!this.checkPromInfo(promInfoDTO, promRuleCheckDTO)) {
                return Boolean.FALSE;
            }
            // 2 判断1全场 参与 还是0部分参与  
            if (PromConstants.PromInfo.JOIN_RANGE_1.equals(promInfoDTO.getJoinRange())) {
                if (PromConstants.PromInfo.IF_BLACKLIST_1.equals(promInfoDTO.getIfBlacklist())) {
                    // 3 全场参与 判断是否商品黑名单
                    if (skuBlackCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
                        chkValue = skuBlackCheckSV.check(promInfoDTO,promRuleCheckDTO);
                        // 4是否黑名单
                        if (chkValue) {
                            return Boolean.FALSE;
                        }
                    }
                }
                if (PromConstants.PromInfo.IF_BLACKLIST_1.equals(promInfoDTO.getIfBlacklist())) {
                    // 3 全场参与 判断是否分类黑名单
                    if (catgBlackCheckSV.isCheck(promInfoDTO,promRuleCheckDTO)) {
                        chkValue = catgBlackCheckSV.check(promInfoDTO,promRuleCheckDTO);
                        // 4是否黑名单
                        if (chkValue) {
                            return Boolean.FALSE;
                        }
                    }
                }
            } 
        return Boolean.TRUE;
    }
    /**
     * TODO促销列表
     * @see com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV#listGdsPromInfo(com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO, boolean)
     * @param promRuleCheckDTO
     * @param ifConstCheck
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public List<PromInfoDTO> listGdsPromInfo(PromRuleCheckDTO promRuleCheckDTO,boolean ifConstCheck,String promClass) throws BusinessException {
        
        //促销id集合
        Set<String> promIdSet = new HashSet<String>();
        
        // 通过gds sku date status获得 相关促销ID 列表
        List<PromSku> promSkuList = new ArrayList<PromSku>();
        
        //单品、商品 相关促销列表 (部分参与 t_prom_sku设置对应单品信息)
        PromSku promSkuQ=new PromSku();
        promSkuQ.setShopId(promRuleCheckDTO.getShopId());
        promSkuQ.setPromClass(promClass);
        promSkuQ.setGdsId(promRuleCheckDTO.getGdsId());
        promSkuQ.setSkuId( promRuleCheckDTO.getSkuId());
        promSkuQ.setMatchType(promRuleCheckDTO.getMatchType());//搭配接口传入 其他可空
        promSkuQ.setSiteId(promRuleCheckDTO.getSiteId());
        promSkuQ.setJoinType(PromConstants.PromSku.JOIN_TYPE_2);//单品参与
        promSkuQ.setIfShow(PromConstants.PromInfo.IF_SHOW_1);//展示
        
        if(promRuleCheckDTO.getPromId()!=null){
            promSkuQ.setPromId(promRuleCheckDTO.getPromId());
        }
        
        //根据gdsid +skuId 获得参与促销列表
       promSkuList = this.querySkuPromList(promSkuQ, promRuleCheckDTO);
        
        //根据gdsid +skuId 的分类编码获得参与促销列表(不需要sql 查询 当子查询分类多是 拼接sql超出 范围)
        promSkuQ.setGdsId(null);
        promSkuQ.setSkuId( null);
        promSkuQ.setJoinType(PromConstants.PromSku.JOIN_TYPE_1);//分类参与
        
        // 通过条件 获得 相关分类促销ID 列表
        List<PromSku> promCatgList = new ArrayList<PromSku>();
        promCatgList= this.querySkuPromList(promSkuQ, promRuleCheckDTO);
        
        //为空处理
        if(CollectionUtils.isEmpty(promSkuList)){
            promSkuList=new ArrayList<PromSku>();
        }
        if(!CollectionUtils.isEmpty(promCatgList)){
            
            //调用接口 获得gds sku当前分类 
            GdsInfoReqDTO dto=new GdsInfoReqDTO();
            dto.setId(promRuleCheckDTO.getGdsId());
            
            GdsQueryOption[] gdsQuery=new GdsQueryOption[1];
            gdsQuery[0]=GdsOption.GdsQueryOption.CATG;//分类
            dto.setGdsQueryOptions(gdsQuery);
            
            GdsInfoRespDTO gdsDTO= gdsInfoQueryRSV.queryGdsInfoByOption(dto);
            
            List<String> platformCatgList=new ArrayList<String>();
           
            if(gdsDTO!=null){
            	//如果下架 删除等 gdsDTO为null
	            if(!CollectionUtils.isEmpty(gdsDTO.getPlatformCategory())){
	                  for(GdsCategoryRespDTO p:gdsDTO.getPlatformCategory()){
	                      platformCatgList.add(p.getCatgCode());
	                  }
	            }
            }
            //注释说明 原来主分类和副分类 没有关系 当促销选择的是副分类时 不能匹配
          /*  //分类代码
            String catg=null;
             if(gdsDTO!=null){
                 if(gdsDTO.getMainCategory()!=null){
                     catg=gdsDTO.getMainCategory().getCatgCode();
                 }
             }
            // 验证 当前获得列表对应的分类 属于当前商品+单品对应分类或者子分类。 如果是加入列表，否则不加入
            for(PromSku promSku:promCatgList){
                //调用接口 获得 当前分类下子节点 
              if(promSku.getCatgCode().equals(catg)){
                  promSkuList.add(promSku);
              }else{
                  Integer result=  gdsCatgSV.compareCatg(catg,promSku.getCatgCode());
                  if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
                    //返回结果关系
                    promSkuList.add(promSku);
                  }
              }
              
            }*/
            // 验证 当前获得列表对应的分类 属于当前商品+单品对应分类或者子分类。 如果是加入列表，否则不加入
            for(PromSku promSku:promCatgList){
                //调用接口 获得 当前分类下子节点 
                Integer result=  gdsCatgSV.compareCatg(platformCatgList,promSku.getCatgCode());
                if(GdsCategoryCompareRespDTO.RESULT_EQUAL.equals(result) || GdsCategoryCompareRespDTO.RESULT_GREAT_THAN.equals(result) ){
                  //返回结果关系
                  promSkuList.add(promSku);
                }
            }
        }

        // 需要新增 全场促销 并且排除黑名单（商品+分类）
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        promInfoDTO.setIfShow(PromConstants.PromType.IF_SHOW_1);
        promInfoDTO.setJoinRange(PromConstants.PromInfo.JOIN_RANGE_1);
        promInfoDTO.setShopId(promRuleCheckDTO.getShopId());
        promInfoDTO.setStatus(PromConstants.PromInfo.STATUS_10);
        promInfoDTO.setPromClass(promClass);
        promInfoDTO.setSiteId(promRuleCheckDTO.getSiteId());
        
        if(promRuleCheckDTO.getPromId()!=null){
            promInfoDTO.setId(promRuleCheckDTO.getPromId());
        }
        
        if(!StringUtil.isEmpty(promRuleCheckDTO.getMatchType())){//搭配接口传入
            promInfoDTO.setIfMatch(PromConstants.PromInfo.IF_MATCH_1);
        }
        //promRuleCheckDTO.getIfshowTime()==1表示需要查询 生效时间
        if (PromConstants.PromSys.PROM_USE_IF_SHOW_TIME.equals(promRuleCheckDTO.getIfshowTime())){
            promInfoDTO.setEndTime(new Timestamp(promRuleCheckDTO.getCalDate().getTime()));
            if(promInfoDTO.getEndTime()==null){
                promInfoDTO.setEndTime(DateUtil.getSysDate());
            }
        }else{
            promInfoDTO.setShowEndTime(new Timestamp(promRuleCheckDTO.getCalDate().getTime()));
            promInfoDTO.setShowStartTime(new Timestamp(promRuleCheckDTO.getCalDate().getTime()));
        }
        //查询促销列表
        List<PromInfoResponseDTO> promInfoRespList = promInfoSV.listPromInfo(promInfoDTO);

        // 没有任何促销信息 return;
        if (CollectionUtils.isEmpty(promSkuList) && CollectionUtils.isEmpty(promInfoRespList)) {
            return null;
        }
        
        // 合并promSkuList +promInfoRespList 并过滤promId相同
        if (!CollectionUtils.isEmpty(promSkuList)) {
            for (PromSku promSku : promSkuList) {
                promIdSet.add(promSku.getPromId().toString());
            }
        }
        
        if (!CollectionUtils.isEmpty(promInfoRespList)) {
            for (PromInfoResponseDTO promInfoResponseDTO : promInfoRespList) {
                promIdSet.add(promInfoResponseDTO.getId().toString());
            }
        }
        
        //set过滤后集合 转为list
        List<String> promIdList = new ArrayList<String>();
        promIdList.addAll(promIdSet);
        
        List<PromInfoDTO> list = new ArrayList<PromInfoDTO>();
        
        for (String promIdStr : promIdList) {
            
            Long promId = new Long(promIdStr);
            PromInfoDTO promInfoDTO1 = promInfoSV.queryPromInfoDTOById(promId);
            
            //solr暂时不要 验证
            if(!ifConstCheck){
                // 默认检核失败 false
                boolean checkValue = false;
                checkValue=promConstraintSV.checkSolr(promId, promRuleCheckDTO);
                if(checkValue){
                    //验证黑名单  
                      if(this.checkBlackLimit(promInfoDTO1, promRuleCheckDTO)){
                          list.add(promInfoDTO1);
                      }
                  }
            }else{
                promRuleCheckDTO.setPromId(promId);
                //重叠过滤
                if(!PromConstants.PromInfo.IF_COMPOSIT_0.equals(promInfoDTO1.getIfComposit())){
                    //验证是否能参与促销
                    if (this.checkPromInfo(promInfoDTO1, promRuleCheckDTO) && this.gdsCheck(promInfoDTO1,promRuleCheckDTO)
                            && promConstraintSV.check(promId, promRuleCheckDTO)) {
                        list.add(promInfoDTO1);
                    }
                }
            }
        }
        
        // 排序
        if(!CollectionUtils.isEmpty(list)){
            ComparatorPromInfoDTO comparator = new ComparatorPromInfoDTO();
            Collections.sort(list, comparator);
        }
        return list;
    }

    /**
     * 参加促销列表
     * @param promSku
     * @param date
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromSku> querySkuPromList(PromSku promSku, PromRuleCheckDTO promRuleCheckDTO)
            throws BusinessException{
        PromSkuCriteria example = new PromSkuCriteria();
        this.initPromSkuParm(promSku,promRuleCheckDTO, example);
        return promSkuMapper.selectByExample(example);
    }
    /**
     * 查询构造条件
     * @param promSku
     * @param date
     * @param example
     * @author huangjx
     */
    private void initPromSkuParm(PromSku promSku, PromRuleCheckDTO promRuleCheckDTO,
            PromSkuCriteria example) {

        Criteria cr = example.createCriteria();
        //促销编码
        if (!StringUtil.isEmpty(promSku.getPromId())) {
            cr.andPromIdEqualTo(promSku.getPromId());
        }
        //是否展示
    /*    if(!StringUtil.isEmpty(promSku.getIfShow())){
             cr.andIfShowEqualTo(promSku.getIfShow());
        }*/
       //skuId
        if (!StringUtil.isEmpty(promSku.getSkuId())) {
            cr.andSkuIdEqualTo(promSku.getSkuId());
        }
        //店铺编码
        if(!StringUtil.isEmpty(promSku.getShopId())){
            cr.andShopIdEqualTo(promSku.getShopId());
        }
        //促销分类
        if(!StringUtil.isEmpty(promSku.getPromClass())){
            cr.andPromClassEqualTo(promSku.getPromClass());
        }
        //商品编码
        if(!StringUtil.isEmpty(promSku.getGdsId())){
            cr.andGdsIdEqualTo(promSku.getGdsId());
        }
        //搭配类型
        if(!StringUtil.isEmpty(promSku.getMatchType())){
           cr.andMatchTypeEqualTo(promSku.getMatchType());
        }
        //站点
        if(!StringUtil.isEmpty(promSku.getSiteId())){
            cr.andSiteIdEqualTo(promSku.getSiteId());
         }
        //分类
        if(!StringUtil.isEmpty(promSku.getJoinType())){
            cr.andJoinTypeEqualTo(promSku.getJoinType());
         }
        //分类编码
        if(!StringUtil.isEmpty(promSku.getCatgCode())){
            cr.andCatgCodeEqualTo(promSku.getCatgCode());
         }
        cr.andStatusEqualTo(PromConstants.PromInfo.STATUS_10);// 有效
        cr.andIfShowEqualTo(PromConstants.PromType.IF_SHOW_1);
        
        
        //是否有效
        if(!StringUtil.isEmpty(promSku.getIfValid())){
            cr.andIfValidEqualTo(promSku.getIfValid());
         }else{
            cr.andIfValidEqualTo(PromConstants.PromSku.IF_VALID_1);
         }
        
        //根据ifshowtime=1使用展示时间  其他使用生效时间
        Date date=null;
        String ifshowTime=null;
        if(promRuleCheckDTO==null){
            date=DateUtil.getSysDate();
        }else{
            date=promRuleCheckDTO.getCalDate();
            ifshowTime=promRuleCheckDTO.getIfshowTime();
        }
         //生效时间
        if(PromConstants.PromSys.PROM_USE_IF_SHOW_TIME.equals(ifshowTime)){
            cr.andStartTimeLessThanOrEqualTo(new Timestamp(date.getTime()));
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(date.getTime()));
        }else{
            cr.andShowStartTimeLessThanOrEqualTo(new Timestamp(date.getTime()));
            cr.andShowEndTimeGreaterThanOrEqualTo(new Timestamp(date.getTime()));
        }
        //排序
        example.setOrderByClause(" id asc ");
    }
}
