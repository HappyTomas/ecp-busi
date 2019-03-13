package com.zengshi.ecp.busi.coupon.batch.controller;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.coupon.couptype.vo.QueryCouponTypeVO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupTypeRSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 功能未完成，待核心功能完成后在开发<br>
 * Date:2015-12-28下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/batchsend")
public class BatchSendController extends EcpBaseController {

    @Resource
    private ICoupTypeRSV coupTypeRSV;
    
    /**
     * 批量发送优惠券列表功能
     */
    private static String MODULE = BatchSendController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model) {
        return "/coupon/batch/send/batch-send-grid";
    }

    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Model grid(Model model, QueryCouponTypeVO vo) throws Exception {

        // 后场服务所需要的DTO；
/*        CoupTypeReqDTO queryDTO = vo.toBaseInfo(CoupTypeReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);

        // 调用服务
        PageResponseDTO<CoupTypeRespDTO> page = coupTypeRSV.queryCoupTypePage(queryDTO);*/
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(null);

        return super.addPageToModel(model, respVO);
    }
}
