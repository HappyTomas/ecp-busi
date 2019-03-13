package com.zengshi.ecp.test.client.gdssend;

import javax.annotation.Resource;
import org.junit.Test;

import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsSendRSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-11-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class UnpfGdsSendRSVTest extends EcpServicesTest {

    @Resource
    private IUnpfGdsSendRSV unpfGdsSendRSV;
 
    @Test
    public void testSend() {
    	 GdsSendReqDTO gdsSendReqDTO=new GdsSendReqDTO();
    	 BaseStaff baseStaff=new BaseStaff();
    	 baseStaff.setId(100L);
    	 gdsSendReqDTO.setStaff(baseStaff);
    	 gdsSendReqDTO.setGdsId(21081L);//商品编码
    	 gdsSendReqDTO.setShopId(35L);//店铺编码
    	 gdsSendReqDTO.setPlatType("taobao"); //非空
    	 gdsSendReqDTO.setShopAuthId(45L);
    	 GdsSendRespDTO resp= unpfGdsSendRSV.send(gdsSendReqDTO);
    	 //map结果
    	 /*{"ifRecomm":"0","ifSeniorPrice":"0","catlogId":1010,"location":{"prov":"山西","city":"晋城市"},"cityCode":"140500","has_warranty":"false","ifBuyMore":true,"city":"晋城市","scores":[],"shopId":100,"description":{},"shipTemplateId":-1,"quantity":"222","item_type":"b","prop_46602357":"铁楸","allPropMaps":{"80":{"createStaff":178,"createTime":1465354472000,"gdsStatus":"11","id":80,"ifBasic":"0","propName":"0517属性001-实物","propType":"2","propValueType":"3","status":"1","updateStaff":0,"updateTime":1465354571000,"values":[{}]}},"updateStaff":0,"stuff_status":"5","status":"onsale","updateTime":1465354571000,"ifSalealone":"0","delivery_way":"2","expired_refund":{"expired_refund_rate":"100","support_expired_refund_rate":"false"},"ifDisperseStock":"0","sku":[],"item_status":"0","url":"/gdsdetail/46984-","richTextPropMap":{},"ifScoreGds":"0","platformCategory":[],"valid_thru":"17","price":1500,"ifNeedStock":true,"medias":[{"createStaff":178,"createTime":1465354472000,"gdsId":46984,"mediaRtype":"2","mediaType":"1","mediaUuid":"57578b7fe4b085b9f8cd8589","shopId":100,"sortNo":1,"status":"1","updateStaff":178,"updateTime":1465354472000}],"skus":[{"allPropMaps":{"80":{"id":80,"ifBasic":"0","propName":"0517属性001-实物","propType":"2","propValueType":"3","values":[{"propValue":""}]}},"catlogId":1010,"commonPrice":1400,"createStaff":178,"createTime":1465354472000,"discountPrice":1400,"gdsApprove":"22","gdsId":46984,"gdsName":"铁楸","gdsProps":[{"$ref":"$.skus[0].allPropMaps.80"}],"gdsStatus":"11","gdsStatusName":"已上架","gdsTypeId":1,"gdsTypeName":"实物商品","guidePrice":1500,"id":87385,"ifBuyMore":true,"ifDisperseStock":"0","ifEntityCode":"0","ifFree":"0","ifLadderPrice":"0","ifNeedStock":true,"ifNew":"0","ifRecomm":"0","ifSalealone":"0","ifScoreGds":"0","ifSendscore":"0","ifSeniorPrice":"0","mainCatgs":"3392","platCatgs":"<3392>","props":[],"realAmount":222,"realPrice":1400,"richTextPropMap":{},"shipTemplateId":-1,"shopId":100,"sku2MediaRespDTOs":[{"createStaff":178,"createTime":1465354472000,"gdsId":46984,"mediaRtype":"2","mediaType":"1","mediaUuid":"57578b7fe4b085b9f8cd8589","shopId":100,"skuId":87385,"sortNo":1,"status":"1","updateStaff":178,"updateTime":1465354472000}],"stockInfoDTO":{"availCount":222,"catgCode":"3392","companyId":1,"createStaff":178,"createTime":1465354472000,"gdsId":46984,"id":13127,"preOccupyCount":0,"realCount":222,"repCode":4090,"repType":"01","sendCount":0,"shopId":100,"skuId":87385,"status":"1","stockRepAdapts":[],"stockType":"01","typeId":1,"updateStaff":178,"updateTime":1465354472000},"stockInfoRespDTO":{"$ref":"$.skus[0].stockInfoDTO"},"stockRepDTOs":[{"companyId":1,"id":13127,"repName":"总仓","repType":"01","shopId":100,"stockInfoDTOs":[{"availCount":222,"repCode":"4090","repType":"01","skuId":87385}],"stockType":"01"}],"updateStaff":0,"updateTime":1465354571000,"url":"/gdsdetail/46984-87385"}],"platCatgs":"<3392>","createTime":1465354472000,"gdsTypeId":1,"gdsTypeName":"实物商品","provinceCode":"140000","express":"33","ems":"33","ifFree":"0","title":"铁楸","item_images":{},"refund":"","freight_by_buyer":{"express_fee":"33","ems_fee":"33","post_fee":"332"},"prov":"山西","post":"332","mainCatgs":"3392","ifLadderPrice":"0","outer_id":46984,"gdsStatus":"11","postage_id":"","shopCategory":[],"prop_46398806":1500,"createStaff":178,"freight_payer":"1","has_invoice":"true","gdsApprove":"22","ifNew":"0","ifEntityCode":"0","ifSendscore":"0","gdsStatusName":"已上架","props":[{"$ref":"$.allPropMaps.80"}]}*/
         System.out.println("ok");
    }
}
