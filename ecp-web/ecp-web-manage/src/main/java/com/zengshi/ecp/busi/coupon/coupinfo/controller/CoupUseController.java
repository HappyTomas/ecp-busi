package com.zengshi.ecp.busi.coupon.coupinfo.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.coupon.coupinfo.vo.QueryCoupUseVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016-01-14下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/coupuse")
public class CoupUseController extends EcpBaseController {
    
    /**
     * 优惠券使用功能
     */
    private static String MODULE = CoupUseController.class.getName();

    @Resource
    private ICoupDetailRSV coupDetailRSV;
  
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
        return "/coupon/use/coup-use-grid";
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
    public Model grid(Model model, QueryCoupUseVO vo) throws Exception {

        // 后场服务所需要的DTO；
        CoupConsumeReqDTO queryDTO = vo.toBaseInfo(CoupConsumeReqDTO.class);

        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        
        // 调用服务
         PageResponseDTO<CoupConsumeRespDTO> page = coupDetailRSV.queryCoupConsumePage(queryDTO);
         
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }
}
