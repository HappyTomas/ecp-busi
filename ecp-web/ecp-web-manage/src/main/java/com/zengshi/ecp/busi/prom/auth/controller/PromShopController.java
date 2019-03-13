package com.zengshi.ecp.busi.prom.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.prom.auth.vo.QueryShopVO;
import com.zengshi.ecp.busi.prom.auth.vo.ShopVO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-prom <br>
 * Description: <br>
 * Date:2015-8-26下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/promshop")
public class PromShopController extends EcpBaseController {

    @Resource
    private IPromAuthRSV promAuthRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
    /**
     * 平台促销类型授权   店铺查询功能
     */
    private static String MODULE = PromShopController.class.getName();

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
        
        HashMap<String,Object> shop = (HashMap<String, Object>)CacheUtil.getItem("STAFF_SHOP_CACHE_DATA");
        
        return "/prom/auth/shop/query-shop-grid";
    }

    /**
     * 授权列表查询 初始化列表和查询按钮功能调用
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/shoplist")
    @ResponseBody
    public Model shopList(Model model, QueryShopVO queryShopVO) throws Exception {

        // 组织参数
        ShopSelectReqDTO  conds=new ShopSelectReqDTO();
        conds=queryShopVO.toBaseInfo(ShopSelectReqDTO.class);
        ObjectCopyUtil.copyObjValue(queryShopVO, conds, "", false);
      
        if(!StringUtil.isEmpty(queryShopVO.getStatus())){
             //待实现
        }
        PageResponseDTO<ShopInfoResDTO> page=shopInfoRSV.listShopInfoByCond(conds);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }

    /**
     * 促销已选择店铺列表
     * @param model
     * @param promId
     * @param gdsList
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/shoptable")
    public String shoptable(Model model, @RequestParam("shopIds") String[] shopIds) throws Exception {
        
           List<ShopVO> shopList=new ArrayList<ShopVO>();
           model.addAttribute("shopList", shopList);
        
            if(!StringUtil.isEmpty(shopIds) && shopIds.length>0){
                
                //页面传入参数 组织
                List<String> shopss = java.util.Arrays.asList(shopIds);
                //去重 过滤
                Set set = new HashSet(shopss);
                shopss=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(shopss)){
                    //设置列表值
                    ShopInfoResDTO shopDTO=null;
                    for(String shopId:shopss){
                        ShopVO vo=new ShopVO();
                        vo.setShopId(shopId);
                        shopDTO=shopInfoRSV.findShopInfoByShopID(new Long(shopId));
                        if(shopDTO!=null){
                            vo.setShopName(shopDTO.getShopName()); 
                            vo.setStatus(shopDTO.getShopStatus());
                        }
                       
                        vo.setTips("");
                        shopList.add(vo);
                    }
            }
        }
            return "/prom/auth/authshop/auth-shop-table";
    }
 
}
