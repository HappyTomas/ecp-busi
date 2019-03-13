/** 
 * Project Name:ecp-web-mall 
 * File Name:GdsDetailUtil.java 
 * Package Name:com.zengshi.ecp.busi.goods.gdsdetail.utils 
 * Date:2017年10月13日下午3:26:46 
 * Copyright (c) 2017, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.goods.gdsdetail.utils;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.zengshi.ecp.busi.search.vo.AddToCartButtonVO;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 详情展示工具类<br>
 * Date:2017年10月13日下午3:26:46  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsDetailUtil {
    
    private static final String MODULE = GdsDetailUtil.class.getName();
    
    public static final String MSG_KEY_ADDTOCART = "web.gds.addToCart";
    
    public static final String MSG_KEY_COMINGSOON = "web.gds.comingSoon";
    
    public static final String MSG_KEY_OUTOFPRINT = "web.gds.outOfPrint";
    
    
    public static final String ADDTOCART = "加入购物车";
    
    public static final String COMINGSOON = "即将上市";
    
    public static final String OUTOFPRINT = "停印";
    
    public static final String REPRINTING = "正在重印中";
    
    /**
     * 
     * getAddToCartButton:根据是否支持工厂库存返回加入购物车按钮. <br/> 
     * 
     * @author liyong7
     * @param storage
     * @param facStorage
     * @param storageStatus
     * @param publishDate
     * @param zeroStockStarttime
     * @param needStorage
     * @return 
     * @since JDK 1.6
     */
    public static AddToCartButtonVO getAddToCartButton(String gdsId, String skuId, long storage, Long facStock, String storageStatus, String publishDate, String zeroStockStarttime, boolean needStorage){
        long zeroDuration = getZeroStockDuration(zeroStockStarttime, gdsId, skuId);
        AddToCartButtonVO btn = getDefaultAddToCartButton(storageStatus, needStorage);
        // 支持工厂库存.
        if(GdsUtils.isSupportFacStock() && needStorage && zeroDuration != -1){
            BaseSysCfgRespDTO zeroStockDurationCfg = null;
            BaseSysCfgRespDTO thresholdCfg = null;
            try {
               zeroStockDurationCfg = SysCfgUtil.fetchSysCfg("ZERO_STOCK_DURATION");
               thresholdCfg = SysCfgUtil.fetchSysCfg("FACTORY_INVENTORY_THRESHOLD");
               // 零库存持续系统配置时长
               long duration = Long.parseLong(zeroStockDurationCfg.getParaValue());
               // 工厂库存阀值.
               long threshold = Long.parseLong(thresholdCfg.getParaValue());
               
               // 小等于设定时间区间.
               if(zeroDuration <= duration){
                   if(facStock != null && -1L != facStock){
                       btn.setAddToCartEnable(false);
                       // 工厂库存<=阀值
                       if(facStock <= threshold){
                          // btn.setAddToCartEnable(false);
                           // 出版日期为空,展示为即将上市,不能点击
                           if(StringUtils.isBlank(publishDate)){
                               btn.setAddToCartPromp(COMINGSOON);
                           }else{
                            // 出版日期不为空,展示为加入购物车,不能点击  
                               btn.setAddToCartPromp(ADDTOCART);
                           }
                       }else{
                           //btn.setAddToCartEnable(false);
                           // 正在重印中
                               btn.setAddToCartPromp(REPRINTING);
                           
                       }
                   }
               }else{
                   // 大于设定时间区间.
                   btn.setAddToCartEnable(false);
                   btn.setAddToCartPromp(OUTOFPRINT);
               }
            } catch (Exception e) {
                LogUtil.error(GoodSearchResult.class.getName(), "无工厂库存阀值或零库存持续时长配置.");
            }
        }
        return btn;
    }
    
    
    
    /*
     * 
     * getZeroStockDuration:获取零库存持续时长,如果返回值为-1代表非法时间比对. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    private static long getZeroStockDuration(String zeroStockStarttime, String gdsId, String skuId){
        if(StringUtils.isBlank(zeroStockStarttime)){
            return -1L;
        }else{
            try{
               return DateUtil.getTimeDifference(DateUtil.getSysDate(), new Timestamp(DateUtil.str2Timestamp(zeroStockStarttime).getTime()));
            }catch(Exception e){
                LogUtil.error(MODULE, "[商品域]计算零库存持续时长遇到异常:gdsId = "+ gdsId +"; skuId = " + skuId,e);
                return -1L;
            }
        }
    }
    
    
    
    public static AddToCartButtonVO getDefaultAddToCartButton(String statusCode, boolean needStorage) {
        AddToCartButtonVO btn = new AddToCartButtonVO();
        btn.setAddToCartPromp(ADDTOCART);
        
        if(!needStorage){
            btn.setAddToCartEnable(true);
        }else{
            
            // 缺货情况不允许点击
            if(GdsConstants.GdsStock.STOCK_STATUS_LACK.equals(statusCode)){
                btn.setAddToCartEnable(false);
            }else{
                btn.setAddToCartEnable(true);
            }
        }
        return btn;
    }
    

}

