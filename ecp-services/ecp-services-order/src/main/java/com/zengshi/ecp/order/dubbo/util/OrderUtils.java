package com.zengshi.ecp.order.dubbo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by wang on 15/12/5.
 */
public class OrderUtils {

    /**
     * @author wangxq
     * @param isbn 978-7-115-34232-1 取 34232
     * @return
     */
    public static String getISBN(String isbn){
        String ordIsbn = isbn;
        if(StringUtil.isBlank(ordIsbn)){
            return ordIsbn;
        }

        if(ordIsbn.contains("-")){
            String[] ibsns = ordIsbn.split("-");
            if(ibsns.length>=4){
                return ibsns[3];
            }
        }

        return ordIsbn;
    }

//    public static void main(String[] args){
//        System.out.print(OrderUtils.getISBN("ISBN: SFS9787-115-34232-1"));
//    }
    public static GdsSkuInfoReqDTO codeGdsRequest(OrdSub ordSub){
        GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsInfoReqDTO.setId(ordSub.getSkuId());
        gdsInfoReqDTO.setGdsId(ordSub.getGdsId());
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1004l);
        propIds.add(1050l);
        gdsInfoReqDTO.setPropIds(propIds);
        gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[]{GdsOption.SkuQueryOption.BASIC,
                GdsOption.SkuQueryOption.MAINPIC, GdsOption.SkuQueryOption.PROP});
        return gdsInfoReqDTO;
    }
    
    public static GdsSkuInfoReqDTO codeGdsRequest(OrdSubShopIdx ordSub){
        GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsInfoReqDTO.setId(ordSub.getSkuId());
        gdsInfoReqDTO.setGdsId(ordSub.getGdsId());
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1004l);
        propIds.add(1050l);
        gdsInfoReqDTO.setPropIds(propIds);
        gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[]{GdsOption.SkuQueryOption.BASIC,
                GdsOption.SkuQueryOption.MAINPIC, GdsOption.SkuQueryOption.PROP});
        return gdsInfoReqDTO;
    }

    private static String getCode(GdsSkuInfoRespDTO gdsInfoRespDTO,String key){
        if(StringUtil.isEmpty(gdsInfoRespDTO)) return "";
        if(StringUtil.isEmpty(gdsInfoRespDTO.getAllPropMaps())){
            return "";
        }
        if(StringUtil.isEmpty(gdsInfoRespDTO.getAllPropMaps().get(key))){
            return "";
        }
        if(StringUtil.isEmpty(gdsInfoRespDTO.getAllPropMaps().get(key).getValues()) || gdsInfoRespDTO.getAllPropMaps().get(key).getValues().size() == 0){
            return "";
        }
        return gdsInfoRespDTO.getAllPropMaps().get(key).getValues().get(0).getPropValue();
    }

    public static String getTxCode(GdsSkuInfoRespDTO gdsInfoRespDTO){
        return getCode(gdsInfoRespDTO, "1050");
    }

    public static String getZsCode(GdsSkuInfoRespDTO gdsInfoRespDTO){
        return getCode(gdsInfoRespDTO,"1004");
    }

    public static Double doubleDiv(Long l){
        BigDecimal bigDecimal = new BigDecimal(l).divide(new BigDecimal(100l), 2, RoundingMode.HALF_UP);
        return  bigDecimal.doubleValue();
    }
    
    public static String getXWXT_URL()
    {
        return "http://shoptest1.pmph.com:19419/ecp_httpsv/service/dailyTradeData";
    }
    
    /**
     * 
     * getImageUrl:(根据上传到mongoDB的图片ID 从mongoDB上获取图片路径). <br/>  
     * 
     * @author panjs 
     * @param vfsId 图片ID
     * @param param 图片规格
     * @return 
     * @since JDK 1.6
     */
    public static String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
      //入参的图片ID为空，那么使用默认图片；
        if(StringUtil.isBlank(vfsId)){
            sb.append(ImageUtil.getDefaultImageId());
        } else {
            sb.append(vfsId);
        }
        if (StringUtil.isNotBlank(param)) {
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
}
