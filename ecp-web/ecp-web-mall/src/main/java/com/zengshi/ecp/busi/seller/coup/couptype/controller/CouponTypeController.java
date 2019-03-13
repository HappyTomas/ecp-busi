package com.zengshi.ecp.busi.seller.coup.couptype.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.coup.couptype.vo.QueryCouponTypeVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupTypeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupTypeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.core.util.DateUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-26上午10:41:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/seller/coupontype")
public class CouponTypeController extends EcpBaseController {

    @Resource
    private ICoupTypeRSV coupTypeRSV;
    
    /**
     * 促销类型功能
     */
    private static String MODULE = CouponTypeController.class.getName();



    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/typelist")
    public String grid(Model model, QueryCouponTypeVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupTypeReqDTO queryDTO = vo.toBaseInfo(CoupTypeReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        
        if(!StringUtil.isEmpty(queryDTO.getCreateTimeEnd())){
            queryDTO.setCreateTimeEnd(DateUtils.addDay(queryDTO.getCreateTimeEnd(), 1));
        }

        // 调用服务
        PageResponseDTO<CoupTypeRespDTO> page = coupTypeRSV.queryCoupTypePage(queryDTO);
        
        model.addAttribute("coupPage", page);

        return "/seller/coupon/list/coup-type-list";
    }
  
}
