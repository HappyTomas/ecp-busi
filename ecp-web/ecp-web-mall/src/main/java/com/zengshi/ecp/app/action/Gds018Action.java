package com.zengshi.ecp.app.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds018Req;
import com.zengshi.ecp.app.resp.Gds018Resp;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月12日下午5:16:50 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds018")
@Action(bizcode = "gds018", userCheck = false)
@Scope("prototype")
public class Gds018Action extends AppBaseAction<Gds018Req, Gds018Resp> {

	private static final String MODULE = Gds017Action.class.getName();
    @Resource
    private IPromRSV promRSV;

    public static final String APP="2";

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Gds018Req gds018Req = this.request;
		Gds018Resp gds018Resp = this.response;

        // 获取促销信息
		
        PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
        CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
        promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
        promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
        promRuleCheckDTO.setGdsId(gds018Req.getGdsId());
        promRuleCheckDTO.setChannelValue(APP);
        promRuleCheckDTO.setShopId(gds018Req.getShopId());
        promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
        promRuleCheckDTO.setSkuId(gds018Req.getSkuId());
        promRuleCheckDTO.setBasePrice(gds018Req.getRealPrice());
        promRuleCheckDTO.setBuyPrice(gds018Req.getDiscountPrice());
        promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
        promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
        List<PromListRespDTO> promInfoDTOList = promRSV.listPromForSolr(promRuleCheckDTO);

        String[] promTypes = null;

        if (CollectionUtils.isNotEmpty(promInfoDTOList)) {
            promTypes = new String[promInfoDTOList.size()];
            for (int i = 0; i < promInfoDTOList.size(); i++) {
                //取第一条价格
                if(i==0){
                    if(promInfoDTOList.get(i).getPromSkuPriceRespDTO() != null){
                    	gds018Resp.setPrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountFinalPrice());
                    	gds018Resp.setGuidePrice(promInfoDTOList.get(i).getPromSkuPriceRespDTO().getDiscountCaclPrice());
                    }
                }
                if(promInfoDTOList.get(i).getPromInfoDTO() != null){
                    promTypes[i] = promInfoDTOList.get(i).getPromInfoDTO().getPromTypeShow();
                }
            }
        }

        List<String> typeList = new ArrayList<String>();
        if (promTypes != null) {
            for (String type : promTypes) {
                if (!typeList.contains(type)) {
                    typeList.add(type);
                }
            }
            // 取前三的促销类型
            if (typeList.size() > 3) {
                List<String> greatThan3Type = new ArrayList<String>();
                for (int i = 3; i < typeList.size(); i++) {
                    greatThan3Type.add(typeList.get(i));
                }
                typeList.removeAll(greatThan3Type);
            }

        }
//        // 获取用户对商品的收藏信息
//        GdsCollectReqDTO collectReqDTO = new GdsCollectReqDTO();
//        collectReqDTO.setGdsId(goodSearchPromVO.getGdsId());
//        collectReqDTO.setStaffId(collectReqDTO.getStaff().getId());
//        collectReqDTO.setPageNo(0);
//        collectReqDTO.setPageSize(1);
//        PageResponseDTO<GdsCollectRespDTO> pageResponseDTO = gdsCollectRSV
//                .queryGdsCollectRespDTOPagingByStaff(collectReqDTO);
//      
//        if(goodSearchPromVO.getIsLogin()){
//        if ( pageResponseDTO.getCount() > 0) {
//            bean.setIfHavFav("1");
//        } else {
//            bean.setIfHavFav("0");
//        }
//        }else{
//            bean.setIfHavFav("0");
//        }
        gds018Resp.setPromTypes(typeList);

    
	}

}