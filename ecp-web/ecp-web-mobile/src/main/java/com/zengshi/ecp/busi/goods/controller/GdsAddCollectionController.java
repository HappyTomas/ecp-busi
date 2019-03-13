package com.zengshi.ecp.busi.goods.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsDetailVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalReplyRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInterfaceGdsRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPlatRecomRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCollectRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopManageRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: 微商的商品详情页面<br>
 * Date:2016年6月20日下午2:16:19  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/addCollection")
@Controller
public class GdsAddCollectionController extends EcpBaseController{
    private String MODULE = GdsAddCollectionController.class.getName();
    private String URL = "/goods/gdsdetail";
    private static String GDS_E_BOOK_CAT_CODE = "1200";
    private static String GDS_DIGITS_BOOK_CAT_CODE = "1201";
    private static String WEB = "1";
    private static int EVAL_PAGE_SIZE = 3;
    
    private static final String MASK_STRING = "***";

    @Resource
    private IGdsCollectRSV iGdsCollectRSV;
    /**
     * 
     * add:(添加收藏). <br/> 
     * 
     * @author gxq 
     * @param gdsDetailVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public EcpBaseResponseVO add(GdsDetailVO gdsDetailVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            GdsCollectReqDTO dto = new GdsCollectReqDTO();
            if(StringUtil.isNotBlank(gdsDetailVO.getCollectId())){
                dto.setId(Long.valueOf(gdsDetailVO.getCollectId()));
                iGdsCollectRSV.deleteGdsCollectByPK(dto);
                vo.setResultMsg("");
            }else{
                dto.setSkuId(gdsDetailVO.getSkuId());
                Long result = iGdsCollectRSV.addGdsCollect(dto);
                if(result != null){
                    vo.setResultMsg(result+"");
                }
            }
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "无法添加收藏！", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorMessage());
        }
        return vo;
    }
}

