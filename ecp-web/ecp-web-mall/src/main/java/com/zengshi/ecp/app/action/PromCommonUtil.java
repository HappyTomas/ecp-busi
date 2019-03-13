package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-3-8 <br>
 * Copyright (c) 2016 AI <br>
 * 
 * @author huangjx
 */
@Service
public class PromCommonUtil {

   
    private static ICustManageRSV custManageRSV;

    private static IShopManageRSV iShopManageRSV;

    private static IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    

    public  ICustManageRSV getCustManageRSV() {
        return custManageRSV;
    }
    @Resource(name="custManageRSV")
    public  void setCustManageRSV(ICustManageRSV custManageRSV) {
        PromCommonUtil.custManageRSV = custManageRSV;
    }

    public  IShopManageRSV getiShopManageRSV() {
        return iShopManageRSV;
    }
    @Resource(name="shopManageRSV")
    public  void setiShopManageRSV(IShopManageRSV iShopManageRSV) {
        PromCommonUtil.iShopManageRSV = iShopManageRSV;
    }

    public  IGdsSkuInfoQueryRSV getGdsSkuInfoQueryRSV() {
        return gdsSkuInfoQueryRSV;
    }
    @Resource(name="gdsSkuInfoQueryRSV")
    public  void setGdsSkuInfoQueryRSV(IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV) {
        PromCommonUtil.gdsSkuInfoQueryRSV = gdsSkuInfoQueryRSV;
    }
    
   public static void setPromRuleCheckDTO(PromRuleCheckDTO promRuleCheckDTO){

       //用户信息查询
       CustInfoResDTO custDTO = custManageRSV.findCustInfoById(Long.valueOf(promRuleCheckDTO.getStaffId()));
       
       if(custDTO!=null){
           promRuleCheckDTO.setAreaValue(custDTO.getProvinceCode());
       }
       
       if(StringUtil.isEmpty(promRuleCheckDTO.getBuyCnt())){
           promRuleCheckDTO.setBuyCnt("1");
       }
      
       // 客户组id
       String custGroupValue = null;
       if (custDTO != null && custDTO.getId() != null
               && custDTO.getId() != 0) {
           ShopStaffGroupReqDTO shopStaffGroupReqDTO=new ShopStaffGroupReqDTO();
           shopStaffGroupReqDTO.setStaffId(custDTO.getId());
           shopStaffGroupReqDTO.setShopId(Long.valueOf(promRuleCheckDTO.getShopId()));
           custGroupValue = iShopManageRSV
                   .queryShopStaffGroup(shopStaffGroupReqDTO);
       }
       
       promRuleCheckDTO.setCustGroupValue(custGroupValue);
       
       if(custDTO!=null){
           promRuleCheckDTO.setCustLevelValue(custDTO.getCustLevelCode());
       }
      
       if(StringUtil.isEmpty(promRuleCheckDTO.getChannelValue())){
           promRuleCheckDTO.setChannelValue("2");//来源 1：WEB、2：APP、 3：其他
       }
       
       promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
       //站点信息
       promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
       //获得单品 basePrice buyPrice
       GdsSkuInfoReqDTO reqDto = new GdsSkuInfoReqDTO();
       reqDto.setId(promRuleCheckDTO.getSkuId());
       SkuQueryOption[] skuQueryOptions1 = new SkuQueryOption[] {SkuQueryOption.BASIC,SkuQueryOption.CAlDISCOUNT};
       reqDto.setSkuQuery(skuQueryOptions1);
       GdsSkuInfoRespDTO skuInfo = gdsSkuInfoQueryRSV
               .querySkuInfoByOptions(reqDto);
       
       promRuleCheckDTO.setGdsName(skuInfo.getGdsName());
       promRuleCheckDTO.setBasePrice(skuInfo.getRealPrice());
       promRuleCheckDTO.setBuyPrice(skuInfo.getDiscountPrice());
   }

}
