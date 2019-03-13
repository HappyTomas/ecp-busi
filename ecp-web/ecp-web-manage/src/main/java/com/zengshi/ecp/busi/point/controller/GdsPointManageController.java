package com.zengshi.ecp.busi.point.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.goods.controller.GdsManageController;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 积分商城商品的管理<br>
 * Date:2015年10月27日下午5:48:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdspointmanage")
public class GdsPointManageController extends EcpBaseController{
    private static String MODULE = GdsManageController.class.getName();
    private static final String URL = "/goods/gdsManage";
    private static final String IF_GDS_SCORE = "1";//是积分商城的商品
    @Resource
    private IGdsTypeRSV iGdsTypeRSV;
    /**
     * 
     * init:(初始化跳转到积分商城的商品管理). <br/> 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,GdsShopVO gsShopVO){
        List<GdsTypeRespDTO> gdsTypeList = iGdsTypeRSV.queryAllGdsTypesFromCache();
        model.addAttribute("gdsTypeList", gdsTypeList);
        model.addAttribute("shopId", gsShopVO.getShopId());
        BaseSysCfgRespDTO cfg=SysCfgUtil.fetchSysCfg("GDS_SCORE_SHOP_DEFAULT");
        if(cfg!=null){
            model.addAttribute("shopId", cfg.getParaValue());
        }
        model.addAttribute("ifGdsScore", IF_GDS_SCORE);
        return URL+"/gdsManage";
    }

}

