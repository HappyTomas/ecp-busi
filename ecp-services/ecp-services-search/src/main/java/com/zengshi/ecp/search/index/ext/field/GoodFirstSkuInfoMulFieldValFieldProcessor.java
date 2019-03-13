package com.zengshi.ecp.search.index.ext.field;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.general.solr.message.DeltaMessage;
import com.zengshi.ecp.general.solr.util.DeltaUtils;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.CalCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectCntRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoManageRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPriceRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsScoreExtRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.dto.RQueryGoodPayedRequest;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.UncertainField;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.index.IndexException;
import com.zengshi.ecp.search.index.ext.MulFieldValFieldProcessor;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;

/**
 * 获取任意一个单品的相关信息：默认价格、单品库存。保证一致。 Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年10月14日下午7:34:50 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class GoodFirstSkuInfoMulFieldValFieldProcessor extends MulFieldValFieldProcessor {

    public final static String MODULE = "【搜索引擎】GoodFirstSkuInfoMulFieldValFieldProcessor";

    @Resource
    private IGdsPriceRSV gdsPriceRSV;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IGdsScoreExtRSV gdsScoreExtRSV;

    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource
    private com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV reportGoodPayedRSV;

    @Resource
    private IGdsCatgCustDiscRSV gdsCatgCustDiscRSV;

    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IGdsInfoManageRSV gdsInfoManageRSV;
    
    
    @Resource
    private IGdsCollectCntRSV gdsCollectCntRSV; 
    
    private static String GDS_E_BOOK_CAT_CODE = "1200";
    private static String GDS_DIGITS_BOOK_CAT_CODE = "1201";
    
    private static int MAX_RETRY = 10;
    
	@Override
	public Map<String, Object> process(SecConfigRespDTO secConfigRespDTO,SecObjectRespDTO secObjectRespDTO, SecFieldRespDTO curSecFieldRespDTO,
                                       List<SecFieldRespDTO> secFieldRespDTOList, Map<String, Object> fieldValueMap) throws Exception {

        this.nullCheck(fieldValueMap.get(secObjectRespDTO.getObjectUniquefieldName()), "主键编码");
        Long id = this.parseLong(fieldValueMap.get(secObjectRespDTO.getObjectUniquefieldName()), "主键编码");
        
        // 是否开启店铺搜索
        String ifShopSearch=SysCfgUtil.fetchSysCfg("SHOP_SHOW_LOCK").getParaValue();

        Map<String, Object> map = new HashMap<String, Object>();
        List<String> gdsStatusArr = new ArrayList<String>();
        gdsStatusArr.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        // 查询上架任意单品
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setId(id);
        gdsInfoReqDTO.setGdsStatusArr(gdsStatusArr);
        List<Long> skuId = gdsInfoQueryRSV.querySkuIdsByGdsId(gdsInfoReqDTO);
        
        if (CollectionUtils.isEmpty(skuId)) {
            
           DeltaMessage deltaMsg = secObjectRespDTO.getDeltaMessage();
           // 是否重试。
           if(null != deltaMsg && deltaMsg.isNeedRetry()){
               int retryCnt = deltaMsg.getRetryCnt();
               if(++retryCnt <= deltaMsg.getMaxRetry()){
                   TimeUnit.SECONDS.sleep(deltaMsg.getRetryIntervalSec());
                   deltaMsg.setRetryCnt(retryCnt);
                   LogUtil.error(MODULE,"查询不到单品信息:" + fieldValueMap + "开始第"+retryCnt + "次重试!重发增量消息!deltaMsg = " + deltaMsg);
                   DeltaUtils.sendDeltaMessage(deltaMsg);
               }
           }

            // 查询不到单品直接抛出异常
            LogUtil.error(MODULE,"查询不到单品信息:" + fieldValueMap);
            throw new IndexException("查询不到单品信息:" + fieldValueMap);

        } 
      
        
        // 取第一个单品
        Long firstSkuId = skuId.get(0);

        // 单品编码
        map.put("firstSkuId", firstSkuId);

        // 查询单品信息
        GdsSkuInfoReqDTO skuInfoReqDTO = new GdsSkuInfoReqDTO();
        skuInfoReqDTO.setGdsId(id);
        skuInfoReqDTO.setId(firstSkuId);
        SkuQueryOption[] skuQuery = new SkuQueryOption[] { SkuQueryOption.BASIC,
                SkuQueryOption.MAINPIC, SkuQueryOption.SHOWSTOCK, SkuQueryOption.PROP };
        skuInfoReqDTO.setSkuQuery(skuQuery);
        GdsSkuInfoRespDTO gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV
                .querySkuInfoByOptions(skuInfoReqDTO);

        // 单品存在
        if (gdsSkuInfoRespDTO == null) {

            // 查询不到单品直接抛出异常
            LogUtil.error(MODULE,"查询不到单品信息:" + fieldValueMap);
            throw new IndexException("查询不到单品信息:" + fieldValueMap);

        } 
        Long shopId =  gdsSkuInfoRespDTO.getShopId();
        Long gdsId = gdsSkuInfoRespDTO.getGdsId();
        // 积分商品
        if (GdsUtils.isEqualsValid(gdsSkuInfoRespDTO.getIfScoreGds())) {
            GdsScoreExtReqDTO reqDTO = new GdsScoreExtReqDTO();
            reqDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
            reqDTO.setShopId(gdsSkuInfoRespDTO.getShopId());
            List<GdsScoreExtRespDTO> scDtos = gdsScoreExtRSV.queryGdsScoreExtByGds(reqDTO);
            if (CollectionUtils.isNotEmpty(scDtos)) {
                GdsScoreExtRespDTO defaultScore = null;
                for (GdsScoreExtRespDTO gdsScoreExtRespDTO : scDtos) {
                    if (GdsUtils.isEqualsValid(gdsScoreExtRespDTO.getIfDefault())) {
                        defaultScore = gdsScoreExtRespDTO;
                        break;
                    }
                }

                if (defaultScore == null) {
                    defaultScore = scDtos.get(0);
                }

                if (defaultScore.getPrice() == null) {
                    map.put("defaultPrice", 0);
                } else {
                    map.put("defaultPrice", defaultScore.getPrice());
                }

                if (defaultScore.getScore() == null) {
                    map.put("score", 0);
                } else {
                    map.put("score", defaultScore.getScore());
                }
            } else {

                // TODO 价格和积分都为0处理
                map.put("defaultPrice", 0);
                map.put("score", 0);
            }
        } else {// 人卫商品
            map.put("defaultPrice", gdsPriceRSV.getDeaultPrice(skuInfoReqDTO));
        }

        // 查询库存
        map.put("storage", gdsSkuInfoRespDTO.getRealAmount());
        
        // 工厂库存
        map.put("facStock", gdsSkuInfoRespDTO.getStockInfoRespDTO().getFacStock());
        // 零库存持续标识.
        map.put("zeroStockFlag", gdsSkuInfoRespDTO.getStockInfoRespDTO().getZeroStockFlag());
        
        Timestamp zeroStockStarttime = gdsSkuInfoRespDTO.getStockInfoRespDTO().getZeroStockStarttime();
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // 零库存持续起始时间
        if(null != zeroStockStarttime){
           map.put("zeroStockStarttime", sdf.format(zeroStockStarttime));
        }else{
            map.put("zeroStockStarttime", null);
        }
        
        // 电子书编码
 		String eBookCateCode = SysCfgUtil.fetchSysCfg("GDS_E_BOOK_CAT_CODE").getParaValue();
 		if (StringUtil.isBlank(eBookCateCode)) {
 			eBookCateCode = GDS_E_BOOK_CAT_CODE;
 		}
 		// 数字教材
 		String gdsDigitsBookCatCode = SysCfgUtil.fetchSysCfg("GDS_DIGITS_BOOK_CAT_CODE").getParaValue();
 		if (StringUtil.isBlank(gdsDigitsBookCatCode)) {
 			gdsDigitsBookCatCode = GDS_DIGITS_BOOK_CAT_CODE;
 		}
 		
 		//获取当前商品本身类型
  		GdsCategoryReqDTO dto = new GdsCategoryReqDTO();
  		dto.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
  		
  		List<GdsCategoryRespDTO> list = gdsCategoryRSV.queryCategoryTraceUpon(dto);
  		//获取主分类是否为(纸质书，电子书，数字教材)
  		String itselfType = "";
  		if (list != null && list.size() > 0) {
  			for (GdsCategoryRespDTO categoryRespDTO : list) {
  				if (eBookCateCode.equals(categoryRespDTO.getCatgCode()) || gdsDigitsBookCatCode.equals(categoryRespDTO.getCatgCode())) {
  					itselfType = "isEBookOrDigit";
  				}
  			}
  		}
        //是否有库存
        Long storage = gdsSkuInfoRespDTO.getRealAmount();
        if(StringUtils.isNotEmpty(itselfType)){
        	map.put("ifStorage", "1");
        } else if(storage != null){
        	if (storage <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_LACK_THRESHOLD).getParaValue())) {
            	map.put("ifStorage", "0");
    		}else{
            	map.put("ifStorage", "1");
            }
        }else{
        	map.put("ifStorage", "0");
        }

        //查询手机专享价
        map.put("appSpecPrice", gdsSkuInfoRespDTO.getAppSpecPrice());
        
        //是否手机专享价
        Long appSpecPrice = gdsSkuInfoRespDTO.getAppSpecPrice();
        if(appSpecPrice!=null){
        	if(appSpecPrice > 0){
            	map.put("ifAppSpecPrice", "1");
            }else{
            	map.put("ifAppSpecPrice", "0");
            }
        }else{
        	map.put("ifAppSpecPrice", "0");
        }
        // 查询单品属性串
        map.put("skuProps", gdsSkuInfoRespDTO.getSkuProps());

        // 主图
        if (gdsSkuInfoRespDTO.getMainPic() != null) {
            map.put("mainPic", gdsSkuInfoRespDTO.getMainPic().getMediaUuid());
        }
        
        // 查询所有属性
        Map<String, GdsPropRespDTO> allProp = gdsSkuInfoRespDTO.getAllPropMaps();
        if (MapUtils.isNotEmpty(allProp)) {
            List<String> propValueList;

            //fieldParam存属性key而不是属性名，如1007
            for(SecFieldRespDTO f:secFieldRespDTOList) {

                //属性字段
                if (StringUtils.isNotBlank(f.getFieldParams())) {

                    //默认属性保存的是未经处理的多值列表
                    propValueList = getPropValueList(allProp.get(f.getFieldParams()));

                    if (propValueList != null && propValueList.size() > 0) {
                        //多值
                        if(StringUtils.equals(SearchConstants.STATUS_1,f.getFieldIfMutlivalue())){
                            map.put(f.getFieldBeanFieldName(), propValueList);
                        }else{//单值
                            map.put(f.getFieldBeanFieldName(), propValueList.get(0));
                        }
                    }

                }

            }

        }

        GdsCategoryReqDTO reqCatg = new GdsCategoryReqDTO();
        reqCatg.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
        GdsCategoryRespDTO gdsCategory = gdsCategoryRSV.queryGdsCategoryByPK(reqCatg);
        
        if (gdsCategory == null) {

            LogUtil.error(MODULE, "主分类不存在:" + fieldValueMap);
            throw new IndexException("主分类不存在:" + fieldValueMap);

        }
        
        // 主分类信息---一定是平台分类
        map.put("mainCategoryCode", gdsCategory.getCatgCode());
        map.put("mainCategoryValue", gdsCategory.getCatgName());
        map.put("mainCategoryCodeAndValue", gdsCategory.getCatgCode()
                + SearchConstants.SEPERATOR + gdsCategory.getCatgName());
        
//        // 若找不到顶级分类，则该主分类的顶级分类就是他自己
//        String topCateValue = gdsCategory.getCatgName();
//        String topCateCode = gdsCategory.getCatgCode();
//
//        GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
//        gdsCategoryReqDTO.setCatgCode(gdsCategory.getCatgCode());
//        GdsCategoryRespDTO gdsCategoryRespDTO = gdsCategoryRSV.queryRootCategoryByPK(gdsCategoryReqDTO);
//        if (gdsCategoryRespDTO != null) {
//
//            topCateValue = gdsCategoryRespDTO.getCatgName();
//            topCateCode = gdsCategoryRespDTO.getCatgCode();
//
//            // 顶级分类信息
//            map.put("topCategoryCode", gdsCategoryRespDTO.getCatgCode());
//            map.put("topCategoryValue", gdsCategoryRespDTO.getCatgName());
//            map.put("topCategoryCodeAndValue", gdsCategoryRespDTO.getCatgCode()
//                    + SearchConstants.SEPERATOR + gdsCategoryRespDTO.getCatgName());
//        }

//        // 若找不到二级分类，则该主分类的二级分类就是他自己
//        String secondCateValue = gdsCategory.getCatgName();
//        String secondCateCode = gdsCategory.getCatgCode();
//
//        gdsCategoryReqDTO = new GdsCategoryReqDTO();
//        gdsCategoryReqDTO.setCatgCode(gdsCategory.getCatgCode());
//        gdsCategoryReqDTO.setTargetLevel((short) 2);
//        gdsCategoryRespDTO = gdsCategoryRSV
//                .queryCategoryLimitByCatgCodeAndTargetLevel(gdsCategoryReqDTO);
//        if (gdsCategoryRespDTO != null) {
//
//            secondCateValue = gdsCategoryRespDTO.getCatgName();
//            secondCateCode = gdsCategoryRespDTO.getCatgCode();
//
//        }

//        // 顶级分类到二级分类路径
//        String categoryPath = topCateCode + SearchConstants.SEPERATOR + topCateValue
//                + SearchConstants.SEPERATOR + secondCateCode
//                + SearchConstants.SEPERATOR + secondCateValue;
//        map.put("categoryPath", categoryPath);
        
        GdsCategoryReqDTO gdsCategoryReqDTO;
        GdsCategoryRespDTO gdsCategoryRespDTO;
        
        //顶级分类
        //List<GdsCategoryRespDTO> leafCatg=gdsSkuInfoRespDTO.getPlatformCategory();//平台分类（叶子分类）
        String[] platecategories = getCatgs(gdsSkuInfoRespDTO.getPlatCatgs());//平台分类轨迹
        if(org.apache.commons.lang3.ArrayUtils.isNotEmpty(platecategories)){
            Map<String,String> top2SecondCatgCodeMap=new HashMap<String,String>();
            Map<String,String> code2NameMap=new HashMap<String,String>();
            for(String lc:platecategories){
                gdsCategoryReqDTO = new GdsCategoryReqDTO();
                gdsCategoryReqDTO.setCatgCode(lc);
                gdsCategoryRespDTO = gdsCategoryRSV.queryRootCategoryByPK(gdsCategoryReqDTO);
                if (gdsCategoryRespDTO != null) {
                    top2SecondCatgCodeMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgCode());
                    code2NameMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgName());
                }
            }
            
            //二级分类
            if(MapUtils.isNotEmpty(top2SecondCatgCodeMap)){
                List<String> categoryPathList=new ArrayList<String>();
                for(String tc:top2SecondCatgCodeMap.keySet()){
                    gdsCategoryReqDTO = new GdsCategoryReqDTO();
                    gdsCategoryReqDTO.setCatgCode(tc);
                    gdsCategoryReqDTO.setTargetLevel((short) 2);
                    gdsCategoryRespDTO = gdsCategoryRSV
                            .queryCategoryLimitByCatgCodeAndTargetLevel(gdsCategoryReqDTO);
                    if (gdsCategoryRespDTO != null) {
                        top2SecondCatgCodeMap.put(tc, gdsCategoryRespDTO.getCatgCode());
                        code2NameMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgName());
                    }
                    
                    // 顶级分类到二级分类路径
                    categoryPathList.add(tc + SearchConstants.SEPERATOR + code2NameMap.get(tc)
                            + SearchConstants.SEPERATOR + top2SecondCatgCodeMap.get(tc)
                            + SearchConstants.SEPERATOR + code2NameMap.get(top2SecondCatgCodeMap.get(tc)));
                }
                
                map.put("categoryPath", categoryPathList);

            }

        }
        
        // 店铺分类关系保存
        if(StringUtils.equals(ifShopSearch, SearchConstants.STATUS_1)){
            
            //顶级分类
            //leafCatg=gdsSkuInfoRespDTO.getShopCategory();//店铺分类（叶子分类）
            String[] shopcategories = getCatgs(gdsSkuInfoRespDTO.getShopCatgs());//店铺分类轨迹
            if(org.apache.commons.lang3.ArrayUtils.isNotEmpty(shopcategories)){
                Map<String,String> top2SecondCatgCodeMap=new HashMap<String,String>();
                Map<String,String> code2NameMap=new HashMap<String,String>();
                for(String lc:shopcategories){
                    gdsCategoryReqDTO = new GdsCategoryReqDTO();
                    gdsCategoryReqDTO.setCatgCode(lc);
                    gdsCategoryRespDTO = gdsCategoryRSV.queryRootCategoryByPK(gdsCategoryReqDTO);
                    if (gdsCategoryRespDTO != null) {
                        top2SecondCatgCodeMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgCode());
                        code2NameMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgName());
                    }
                }
                
                //二级分类
                if(MapUtils.isNotEmpty(top2SecondCatgCodeMap)){
                    List<String> categoryPathList=new ArrayList<String>();
                    for(String tc:top2SecondCatgCodeMap.keySet()){
                        gdsCategoryReqDTO = new GdsCategoryReqDTO();
                        gdsCategoryReqDTO.setCatgCode(tc);
                        gdsCategoryReqDTO.setTargetLevel((short) 2);
                        gdsCategoryRespDTO = gdsCategoryRSV
                                .queryCategoryLimitByCatgCodeAndTargetLevel(gdsCategoryReqDTO);
                        if (gdsCategoryRespDTO != null) {
                            top2SecondCatgCodeMap.put(tc, gdsCategoryRespDTO.getCatgCode());
                            code2NameMap.put(gdsCategoryRespDTO.getCatgCode(), gdsCategoryRespDTO.getCatgName());
                        }
                        
                        // 顶级分类到二级分类路径
                        categoryPathList.add(tc + SearchConstants.SEPERATOR + code2NameMap.get(tc)
                                + SearchConstants.SEPERATOR + top2SecondCatgCodeMap.get(tc)
                                + SearchConstants.SEPERATOR + code2NameMap.get(top2SecondCatgCodeMap.get(tc)));
                    }
                    
                    map.put("shopCategoryPath", categoryPathList);
                    
                }

            }
            
            // 从上之下分类全路径----平台分类、店铺分类
            //String[] platecategories = getCatgs(gdsSkuInfoRespDTO.getPlatCatgs());//平台分类轨迹
            //String[] shopcategories = getCatgs(gdsSkuInfoRespDTO.getShopCatgs());//店铺分类轨迹
            String[] categories=concat(platecategories, shopcategories);
            map.put("categories", categories);
            
        }else{
            // 从上之下分类全路径----平台分类
            //String[] categories = getCatgs(gdsSkuInfoRespDTO.getPlatCatgs());//平台分类轨迹
            map.put("categories", platecategories);
        }

        // 属性处理
        if (MapUtils.isNotEmpty(gdsSkuInfoRespDTO.getAllPropMaps())) {
            List<String> propCodes = new ArrayList<String>();
            List<String> propValues = new ArrayList<String>();
            for (GdsPropRespDTO prop : gdsSkuInfoRespDTO.getAllPropMaps().values()) {
                if (CollectionUtils.isNotEmpty(prop.getValues())) {
                    for (GdsPropValueRespDTO value : prop.getValues()) {
                        // 属性编码+属性值编码组合串
                        // 手工录入属性，属性有编码但会出现属性值编码为null。手工录入属性不会被分类查询出来。
                        propCodes.add(prop.getId() + SearchConstants.UNDERLINE
                                + value.getId());
                        // 属性名+属性值组合串
                        propValues.add(prop.getPropName() + SearchConstants.UNDERLINE
                                + value.getPropValue());
                    }
                }
            }
            map.put("propertycodes", propCodes.toArray(new String[] {}));
            map.put("propertyValues", propValues.toArray(new String[] {}));
        }

        // 创建时间(最新产品)，存更新时间或出版时间。默认存更新时间（没有出版时间）
        if (map.containsKey("publishDate")&&StringUtils.isNotBlank(String.valueOf(map.get("publishDate")))) {// 出版日期不为空，则存出版日期
            map.put("newProductTime", String.valueOf(map.get("publishDate")).trim()+" 00:00:00");
        } else {

            // 更新时间格式转换：2015/10/14 11:11:14--->2015-10-14
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String updateTime = formatter.format(gdsSkuInfoRespDTO.getUpdateTime());
                map.put("newProductTime", updateTime);
            } catch (Exception e) {
                LogUtil.error(MODULE, "更新时间格式转换异常", e);
            }

        }

        try {

            // BaseStaff staff=new BaseStaff();
            // staff.setId(0l);
            // staff.setStaffLevelCode("01");

            PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            promRuleCheckDTO.setSiteId(gdsSkuInfoRespDTO.getCatlogId());
            promRuleCheckDTO.setCurrentSiteId(gdsSkuInfoRespDTO.getCatlogId());
            promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
            promRuleCheckDTO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
            promRuleCheckDTO.setChannelValue("1");
            promRuleCheckDTO.setShopId(gdsSkuInfoRespDTO.getShopId());
            promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
            promRuleCheckDTO.setSkuId(gdsSkuInfoRespDTO.getId());
            // queryPromDTO.setStaff(staff);

            List<PromInfoDTO> promInfoDTOList = promRSV.listPromByGdsForSolr(promRuleCheckDTO);

            String[] promTypes = null;
            List<String> keys = new ArrayList<String>();

            if (CollectionUtils.isNotEmpty(promInfoDTOList)) {
                promTypes = new String[promInfoDTOList.size()];
                for (int i = 0; i < promInfoDTOList.size(); i++) {
                    promTypes[i] = promInfoDTOList.get(i).getPromTypeShow();
                    String keyWords = promInfoDTOList.get(i).getKeyWord();
                    if (StringUtil.isNotBlank(keyWords)) {
                        String[] keyWord = keyWords.split(" ");
                        if (!ArrayUtils.isEmpty(keyWord)) {
                            for (String key : keyWord) {
                                if (StringUtil.isNotBlank(key)) {
                                    keys.add(key);
                                }
                            }
                        }
                    }
                }
                map.put("ifPromotion", "1");
            } else {
                map.put("ifPromotion", "0");
            }
            map.put("promotionType", promTypes);
            map.put("promotionDesc", keys.toArray(new String[keys.size()]));

        } catch (Exception e) {
            LogUtil.error(MODULE, "查询促销信息错误【" + fieldValueMap + "】", e);
        }

        // 查询单品已付款数
        // Random random=new Random();
        // map.put("sales", random.nextInt(1000));
        try {
            RQueryGoodPayedRequest rqueryGoodPayedRequest = new RQueryGoodPayedRequest();
            rqueryGoodPayedRequest.setSkuId(firstSkuId);
            // TODO 搜索数据对象需要添加站点Id参数
            rqueryGoodPayedRequest.setSiteId(gdsSkuInfoRespDTO.getCatlogId());
            map.put("sales",
                    reportGoodPayedRSV.querySumBuyNumBySkuId(rqueryGoodPayedRequest));

        } catch (Exception e) {
            map.put("sales", 0l);
            LogUtil.error(MODULE, "查询已付款数错误【" + fieldValueMap + "】", e);
        }
        GdsCollectReqDTO collectReqDTO = new GdsCollectReqDTO(); 
        collectReqDTO.setShopId(shopId);
        collectReqDTO.setGdsId(gdsId);
        try{
        LongRespDTO longRespDTO =  gdsCollectCntRSV.executeCount(collectReqDTO);
        map.put("collectCount", longRespDTO.getValue());
        }catch(Exception e){      	
		   map.put("collectCount", 0l);
	       LogUtil.error(MODULE, "查询已收藏数错误【" + fieldValueMap + "】", e);
        }
        
        
        long gdsNamelength = 0l;
        if (fieldValueMap.containsKey("gdsName")) {
            String gdsName = String.valueOf(fieldValueMap.get("gdsName"));

            // 商品标题，用于展示，不高亮
            map.put("gdsNameSrc", gdsName);

            gdsNamelength = gdsName.length();
        }
        map.put("gdsNameBoost", gdsNamelength);

        if (fieldValueMap.containsKey("gdsSubHead")) {
            String gdsSubHead = String.valueOf(fieldValueMap.get("gdsSubHead"));

            // 商品副标题，用于展示，不高亮
            map.put("gdsSubHeadSrc", gdsSubHead);
        }

        // GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
        // gdsInfo.setId(id);
        // gdsInfo.setGdsQueryOptions(options);
        // GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfo);

        Long price=0L;
        // 折扣计算
        if(map.get("defaultPrice")!=null){
            price = Long.parseLong(String.valueOf(map.get("defaultPrice")));
            // 默认会员等级下的折扣计算
            calDedaultDiscount(id, map, price);
            // 所有会员等级下的折扣计算
            calAllMemberDiscount(id, map, price);
        }else{
            map.put("discountPrice", price);
        }
        return map;
    }
	
	private String[] concat(String[] a, String[] b) {
        if(a!=null&&b!=null&&a.length>0&&b.length>0){
            String[] c= new String[a.length+b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            return c;
        }else{
            if(a!=null&&a.length>0){
                return a;
            }else{
                return b;
            }
        }

	}  

    private void calAllMemberDiscount(Long id, Map<String, Object> map, Long price) {
        List<BaseParamDTO> levelList = BaseParamUtil.fetchParamList("STAFF_CUST_LEVEL");
        if (CollectionUtils.isNotEmpty(levelList)) {
            List<UncertainField> fieldList = new ArrayList<UncertainField>();
            List<String> valueList = null;
            for (BaseParamDTO baseParamDTO : levelList) {
                String priceLevel = "discountPriceOfLevel" + baseParamDTO.getSpCode();
                String discountLevel = "discountOfLevel" + baseParamDTO.getSpCode();
                try {
                    CalCatgCustDiscReqDTO reqDto = new CalCatgCustDiscReqDTO();
                    reqDto.setGdsId(id);
                    reqDto.setCustLevelCode(baseParamDTO.getSpCode());
                    BigDecimal discount = gdsCatgCustDiscRSV.calCatgCustDisc(reqDto);

                    if (discount.setScale(2).doubleValue() == 100d) {
                        map.put(priceLevel, price);
                        valueList = new ArrayList<String>();
                        valueList.add(price + "");
                        fieldList.add(new UncertainField(priceLevel, valueList));
                    } else {
                        Long disCountPrice = discount.divide(new BigDecimal(100d)).setScale(4)
                                .multiply(new BigDecimal(price))
                                .setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
                        map.put(priceLevel, disCountPrice);
                        valueList = new ArrayList<String>();
                        valueList.add(disCountPrice + "");
                        fieldList.add(new UncertainField(priceLevel, valueList));
                        map.put(discountLevel, discount.setScale(2).doubleValue() + "");
                        valueList = new ArrayList<String>();
                        valueList.add(discount.setScale(2).doubleValue() + "");
                        fieldList.add(new UncertainField(discountLevel, valueList));
                    }
                } catch (Exception e) {
                    map.put(priceLevel, price);
                    valueList = new ArrayList<String>();
                    valueList.add(price + "");
                    fieldList.add(new UncertainField(priceLevel, valueList));
                    LogUtil.error(MODULE, "分类折扣计算抛出异常", e);
                }
            }

            // 以JSONArray方式输出
            map.put("uncertainfield_discountInfo", JSONArray.toJSONString(fieldList));
        }
    }

    private void calDedaultDiscount(Long id, Map<String, Object> map, Long price) {
        try {
            CalCatgCustDiscReqDTO reqDto = new CalCatgCustDiscReqDTO();
            reqDto.setGdsId(id);
            reqDto.setCustLevelCode("01");
            BigDecimal discount = gdsCatgCustDiscRSV.calCatgCustDisc(reqDto);

            if (discount.setScale(2).doubleValue() == 100d) {
                map.put("discountPrice", price);
            } else {
                Long discountPrice = discount.divide(new BigDecimal(100d)).setScale(4,BigDecimal.ROUND_UP).multiply(new BigDecimal(price)).setScale(0, BigDecimal.ROUND_HALF_UP).longValue();
                if(discountPrice.longValue() <= 0){
                    discountPrice=1L;
                }
                map.put("discountPrice", discountPrice);
                map.put("discount", discount.setScale(2).doubleValue() + "");
            }
        } catch (Exception e) {
            map.put("discountPrice", price);
            LogUtil.error(MODULE, "分类折扣计算抛出异常", e);
        }
    }

    private List<String> getPropValueList(GdsPropRespDTO prop) {
        List<String> arr = null;

        if (prop != null && CollectionUtils.isNotEmpty(prop.getValues())) {
            arr = new ArrayList<String>();

            //存值编码：2 单选 3 多选
            if(StringUtils.equals(prop.getPropValueType(),GdsConstants.GdsProp.PROP_VALUE_TYPE_2)
                    ||StringUtils.equals(prop.getPropValueType(),GdsConstants.GdsProp.PROP_VALUE_TYPE_3)){
                for (GdsPropValueRespDTO value : prop.getValues()) {
                    arr.add(value.getId()+"");
                }
            }else //存值：1 手工输入 4富文本 或 未匹配到正确类型的
            {
                for (GdsPropValueRespDTO value : prop.getValues()) {
                    arr.add(value.getPropValue());
                }
            }
        }

        return arr;
    }

    private String[] getCatgs(String catg) {

        if (StringUtils.isNotBlank(catg) && catg.length() > 2) {
            catg = catg.substring(1, catg.length());
            catg = catg.substring(0, catg.length() - 1);
            return catg.split("><");
        }
        return null;
    }
    
  

}
