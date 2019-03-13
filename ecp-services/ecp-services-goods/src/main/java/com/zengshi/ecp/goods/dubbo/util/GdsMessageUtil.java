package com.zengshi.ecp.goods.dubbo.util;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.paas.utils.MessageUtil;

public class GdsMessageUtil {
    public static final String TOPIC_GDSBROWSE = "gdsBrowseTopic";
    public static final String TOPIC_GDSDEL = "gdsDelTopic";
    public static final String TOPIC_STOCKCATG_CHANGE = "gdsCatgChangeTopic";


    public static void sendGdsBrowseMessage(GdsBrowseHisReqDTO gdsBrowseHisReqDTO) {

        StringBuffer sb = new StringBuffer();
        sb.append("{" + GdsConstants.GdsBrowseMessage.BROWSE_STAFFID + ":\"");
        sb.append(gdsBrowseHisReqDTO.getStaff().getId());
        if(gdsBrowseHisReqDTO.getBrowsePrice() != null){
        sb.append("\","  + GdsConstants.GdsBrowseMessage.BROWSE_BROWSEPRICE + ":\"");
        sb.append(gdsBrowseHisReqDTO.getBrowsePrice());
        }
        sb.append("\","  + GdsConstants.GdsBrowseMessage.BROWSE_GDSID + ":\"");
        sb.append(gdsBrowseHisReqDTO.getGdsId());
        sb.append("\","  + GdsConstants.GdsBrowseMessage.BROWSE_SKUID + ":\"");
        sb.append(gdsBrowseHisReqDTO.getSkuId());
        sb.append("\","  + GdsConstants.GdsBrowseMessage.BROWSE_SHOPID + ":\"");
        sb.append(gdsBrowseHisReqDTO.getShopId());  
        sb.append("\"}");
        MessageUtil.send(sb.toString(), TOPIC_GDSBROWSE);
    }
    //发送商品删除信息
    public static void sendGdsDelMessage(GdsInfoReqDTO gdsInfoReqDTO) {
        StringBuffer sb = new StringBuffer();
        sb.append("{" + GdsConstants.GdsDelMessage.DEL_GDSID + ":\"");
        sb.append(gdsInfoReqDTO.getId());
        sb.append("\"}");
        MessageUtil.send(sb.toString(), TOPIC_GDSDEL);
    }
    
    //发送库存同步修改商品分类信息
    public static void sendStockCatgChangeMessage(StockInfoReqDTO stockInfoReqDTO) {
        StringBuffer sb = new StringBuffer();
        sb.append("{" + GdsConstants.StockCatgChangeMessage.SHOP_ID + ":\"");
        sb.append(stockInfoReqDTO.getShopId());
        sb.append("\","  + GdsConstants.StockCatgChangeMessage.GDS_ID + ":\"");
        sb.append(stockInfoReqDTO.getGdsId());  
        sb.append("\","  + GdsConstants.StockCatgChangeMessage.SKU_ID + ":\"");
        sb.append(stockInfoReqDTO.getSkuId());  
        sb.append("\","  + GdsConstants.StockCatgChangeMessage.CATG_CODE + ":\"");
        sb.append(stockInfoReqDTO.getCatgCode());  
        sb.append("\","  + GdsConstants.StockCatgChangeMessage.COMPANY_ID + ":\"");
        sb.append(stockInfoReqDTO.getCompanyId());  
        sb.append("\"}");
        MessageUtil.send(sb.toString(), TOPIC_STOCKCATG_CHANGE);
    }
}
