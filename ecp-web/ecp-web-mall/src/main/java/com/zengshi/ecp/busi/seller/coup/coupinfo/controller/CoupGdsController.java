package com.zengshi.ecp.busi.seller.coup.coupinfo.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.seller.prom.goods.vo.GdsVO;
import com.zengshi.ecp.busi.seller.prom.goods.vo.QueryGdsReqVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-10-13下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/seller/coupgds")
public class CoupGdsController extends EcpBaseController {
    
    private static String MODULE = CoupGdsController.class.getName();
    
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;//商品
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;//分类
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;
     
    /**
     * 商品选择 按钮 弹出页面
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gdsselect")
    public String gdsSelect(Model model,Long shopId,Long siteId) throws Exception { 
        model.addAttribute("shopId", shopId);
        model.addAttribute("siteId", siteId);
        return "/seller/coupon/userule/gdsselect/gds-select-grid";
    }
    /**
     * 商品选择列表 
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gdsgrid")
    public String gdsgrid(Model model, QueryGdsReqVO queryGdsReqVO) throws Exception {
        
        
        GdsInfoReqDTO queryDTO=queryGdsReqVO.toBaseInfo(GdsInfoReqDTO.class);
        
        ObjectCopyUtil.copyObjValue(queryGdsReqVO, queryDTO, "", false);
        
        if(StringUtil.isEmpty(queryGdsReqVO.getGdsStatus())){
            //默认全部为待上架 和 已上架
            List<String> gdsStList=new ArrayList<String>();
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
            gdsStList.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
            queryDTO.setGdsStatusArr(gdsStList);
        }
       
        if(queryGdsReqVO!=null && queryGdsReqVO.getSiteId()!=null){
      	  List<Long> siteIds=new ArrayList<Long>();
      	  siteIds.add(queryGdsReqVO.getSiteId());
      	  queryDTO.setSiteIds(siteIds);
      }
        PageResponseDTO<GdsInfoRespDTO>  page=gdsInfoQueryRSV.queryGdsInfoListPage(queryDTO);
        
        //转化分类名称
        if(CheckPageNull.checkPageNull(page)){
            for(GdsInfoRespDTO dto:page.getResult()){
                //非空 不需要查询
                if(StringUtil.isEmpty(dto.getMainCatgName())){
                    
                    if(!StringUtil.isEmpty(dto.getMainCatgs())){
                        GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                        reqDTO.setCatgCode(dto.getMainCatgs());
                        //分类id 查询
                        GdsCategoryRespDTO respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
                        if(respDTO!=null){
                            dto.setMainCatgName(respDTO.getCatgName());
                        }
                    }    
                }
            }
        }
        
        model.addAttribute("page",page );
        return "/seller/coupon/userule/gdsselect/gds-list";
    }
     
    /**
     * 已选择商品列表 
     * @param model
     * @param id
     * @param gdsList
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gdsList")
    public String skuList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("gdsIds") String[] gdsIds) throws Exception {
        
        List<GdsVO> gdsList=new ArrayList<GdsVO>();
        model.addAttribute("gdsList", gdsList);
        
        //非空 需要查询商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && gdsIds.length==0){
            
            CoupCatgLimitReqDTO reqdto=new CoupCatgLimitReqDTO();
            reqdto.setCoupId(new Long(coupId));
            reqdto.setCategoryType(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_1);//商品查询
            reqdto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupCatgLimitRespDTO> list= coupLimitRSV.queryCoupCatg(reqdto);
            
            if(!CollectionUtils.isEmpty(list)){
                
                GdsInfoReqDTO dto=new GdsInfoReqDTO();
                GdsInfoRespDTO  respDTO=new GdsInfoRespDTO();
                
                 for(CoupCatgLimitRespDTO catgDTO:list){
                     
                     GdsVO gdsVO=new GdsVO();
                     dto.setId(new Long(catgDTO.getGdsId()));
                     //调用商品接口
                     respDTO=gdsInfoQueryRSV.queryGdsInfoByOption(dto);
                     if(respDTO!=null){
                         ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
                         gdsVO.setGdsId(respDTO.getId().toString());
                     }
                     gdsList.add(gdsVO);
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(gdsIds) && gdsIds.length>0){
                
                //页面传入参数 组织
                List<String> gdss = java.util.Arrays.asList(gdsIds);
                //去重 过滤
                Set set = new HashSet(gdss);
                gdss=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(gdss)){
                    //设置列表值
                 
                    GdsInfoReqDTO dto=new GdsInfoReqDTO();
                    GdsInfoRespDTO  respDTO=new GdsInfoRespDTO();
                    
                    for(String gdsId:gdss){
                        if(!StringUtil.isEmpty(gdsId)){
                            //单品信息
                            dto.setId(new Long(gdsId));
                            //调用商品接口
                            respDTO=gdsInfoQueryRSV.queryGdsInfoByOption(dto);
                            GdsVO gdsVO=new GdsVO();
                            if(respDTO!=null){
                                ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
                                gdsVO.setGdsId(respDTO.getId().toString());
                            }
                            gdsList.add(gdsVO);
                        }
                    }
                  
                }
            }
        }
        return "/seller/coupon/userule/gdstable/coup-gds-table";
    }

    /**
     * 已选择商品黑名单列表 
     * @param model
     * @param id
     * @param gdsList
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gdsblackList")
    public String skublackList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("gdsIds") String[] gdsIds) throws Exception {
        
        List<GdsVO> gdsList=new ArrayList<GdsVO>();
        model.addAttribute("gdsList", gdsList);
        
        //非空 需要查询商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && gdsIds.length==0){
            
            CoupBlackLimitReqDTO reqdto=new CoupBlackLimitReqDTO();
            reqdto.setCoupId(new Long(coupId));
            reqdto.setCategoryType(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_1);//商品查询
            reqdto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupBlackLimitRespDTO> list= coupLimitRSV.queryCoupBlack(reqdto);
            
            if(!CollectionUtils.isEmpty(list)){
                
                GdsInfoReqDTO dto=new GdsInfoReqDTO();
                GdsInfoRespDTO  respDTO=new GdsInfoRespDTO();
                
                 for(CoupBlackLimitRespDTO coupBlackLimitRespDTO:list){
                     
                     GdsVO gdsVO=new GdsVO();
                     dto.setId(new Long(coupBlackLimitRespDTO.getGdsId()));
                     //调用商品接口
                     respDTO=gdsInfoQueryRSV.queryGdsInfoByOption(dto);
                     if(respDTO!=null){
                         ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
                         gdsVO.setGdsId(respDTO.getId().toString());
                     }
                     gdsList.add(gdsVO);
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(gdsIds) && gdsIds.length>0){
                
                //页面传入参数 组织
                List<String> gdss = java.util.Arrays.asList(gdsIds);
                //去重 过滤
                Set set = new HashSet(gdss);
                gdss=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(gdss)){
                    //设置列表值
                 
                    GdsInfoReqDTO dto=new GdsInfoReqDTO();
                    GdsInfoRespDTO  respDTO=new GdsInfoRespDTO();
                    
                    for(String gdsId:gdss){
                        if(!StringUtil.isEmpty(gdsId)){
                            //单品信息
                            dto.setId(new Long(gdsId));
                            //调用商品接口
                            respDTO=gdsInfoQueryRSV.queryGdsInfoByOption(dto);
                            GdsVO gdsVO=new GdsVO();
                            if(respDTO!=null){
                                ObjectCopyUtil.copyObjValue(respDTO, gdsVO, "", false);
                                gdsVO.setGdsId(respDTO.getId().toString());
                            }
                            gdsList.add(gdsVO);
                        }
                    }
                  
                }
            }
        }
        return "/seller/coupon/userule/gdstable/coup-blackgds-table";
    }
}


