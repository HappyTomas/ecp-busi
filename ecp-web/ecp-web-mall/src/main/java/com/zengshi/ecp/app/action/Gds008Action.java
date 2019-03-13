package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds008Req;
import com.zengshi.ecp.app.req.gds.GdsSku2PropPropIdxBaseInfo;
import com.zengshi.ecp.app.resp.Gds008Resp;
import com.zengshi.ecp.app.resp.gds.GdsDetailBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsSkuBaseInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;

/**
 * 根据属性获取单品
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日下午2:11:31  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
@Service("gds008")
@Action(bizcode = "gds008", userCheck = false)
@Scope("prototype")
public class Gds008Action extends AppBaseAction<Gds008Req, Gds008Resp> {
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    @Resource
    private IGdsInfoQueryRSV iGdsInfoQueryRSV;
    
    private static final String MODULE = Gds008Action.class.getName();
    
    @Override
    protected void getResponse() throws  Exception {
        
        Gds008Req gds008Req = this.request;
        Gds008Resp gds008Resp = this.response; 

        GdsInfoDetailRespDTO respDto = new GdsInfoDetailRespDTO();
        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if (StringUtil.isNotEmpty(gds008Req.getGdsId())) {
            dto.setId(gds008Req.getGdsId());
        }
        if (StringUtil.isNotEmpty(gds008Req.getShopId())) {
            dto.setShopId(gds008Req.getShopId());
        }
        
        if (StringUtil.isNotEmpty(gds008Req.getSkuId())) {
            dto.setSkuId(gds008Req.getSkuId());
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] {
                GdsQueryOption.BASIC};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] {
                SkuQueryOption.BASIC, SkuQueryOption.MAINPIC,
                SkuQueryOption.PROP, SkuQueryOption.SHOWSTOCK,SkuQueryOption.CAlDISCOUNT };
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        parsetSkuPropParam(dto, gds008Req);
        try {
            respDto = iGdsInfoQueryRSV.queryGdsInfoDetail(dto);
            String stockStatus = "";
            String princePrice = "";
            if (StringUtil.isEmpty(respDto)) {
                respDto = new GdsInfoDetailRespDTO();
            } else {
                if (respDto.getSkuInfo() != null) {
                    stockStatus = GdsUtils.checkStcokStatus(respDto.getSkuInfo()
                            .getRealAmount());
                    //数字印刷的价格
                    if(respDto.getSkuInfo().getAllPropMaps()!=null && respDto.getSkuInfo().getAllPropMaps().get("1029")!= null){
                        for(GdsPropValueRespDTO gdsPropValueRespDTO :respDto.getSkuInfo().getAllPropMaps().get("1029").getValues()){
                            if(!StringUtil.isBlank(gdsPropValueRespDTO.getPropValue())){
                                princePrice = MoneyUtil.convertCentToYuan(gdsPropValueRespDTO.getPropValue());
                            }else{
                                princePrice = "0";
                            }
                        }
                    }
                }
                
            };
           GdsDetailBaseInfo gdsDetailBaseInfo = new GdsDetailBaseInfo();
           GdsSkuBaseInfo gdsSkuBaseInfo = new GdsSkuBaseInfo();
           ObjectCopyUtil.copyObjValue(respDto, gdsDetailBaseInfo, null, false);
           ObjectCopyUtil.copyObjValue(respDto.getSkuInfo(), gdsSkuBaseInfo, null, false);
           gdsDetailBaseInfo.setParams(null);
           gdsDetailBaseInfo.setGdsSkuBaseInfo(gdsSkuBaseInfo);
            gds008Resp.setGdsDetailBaseInfo(gdsDetailBaseInfo);
            gds008Resp.setDigitsPrinPrice(princePrice);
            gds008Resp.setStockStatus(stockStatus);
            if(respDto.getSkuInfo() != null && respDto.getSkuInfo().getMainPic() !=null){
            gds008Resp.setMainPicUrl(ImageUtil.getImageUrl(respDto.getSkuInfo().getMainPic() .getMediaUuid()));
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "单品信息切换错误！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "单品信息切换错误！", e);
        }
     
    
        
    }
    private void parsetSkuPropParam(GdsInfoReqDTO dto, Gds008Req gds008Req) {
        List<GdsSku2PropReqDTO> skuProps = new ArrayList<GdsSku2PropReqDTO>();
        List<GdsSku2PropPropIdxBaseInfo> gdsPropValueReqDTOs =  gds008Req.getGdsPropValueReqDTOs();
        if (gdsPropValueReqDTOs != null && gdsPropValueReqDTOs.size() >= 1) {
            GdsSku2PropReqDTO reqDto = null;
            for (GdsSku2PropPropIdxBaseInfo gdsPropValueReqDTO: gdsPropValueReqDTOs) {
                reqDto = new GdsSku2PropReqDTO();
            
                Long propId = gdsPropValueReqDTO.getPropId();
                String propName = gdsPropValueReqDTO.getPropName();
                Long valueId = gdsPropValueReqDTO.getPropValueId();
                String value = gdsPropValueReqDTO.getPropValue();
                if (propId != null) {
                    reqDto.setPropId(propId);
                }
                reqDto.setPropName(propName);
                if (valueId != null) {
                    reqDto.setPropValueId(valueId);
                }
                reqDto.setPropValue(value);
                skuProps.add(reqDto);
            }
        }
        dto.setSkuProps(skuProps);
    }
}
