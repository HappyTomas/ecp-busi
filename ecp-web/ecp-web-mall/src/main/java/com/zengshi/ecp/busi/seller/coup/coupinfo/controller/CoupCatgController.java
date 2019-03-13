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
import com.zengshi.ecp.busi.seller.prom.PromConst;
import com.zengshi.ecp.busi.seller.prom.goods.vo.CatgVO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupBlackLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupCatgLimitReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupBlackLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupCatgLimitRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupLimitRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016-4-26上午9:32:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/seller/coupcatg")
public class CoupCatgController extends EcpBaseController {
    
    private static String MODULE = CoupCatgController.class.getName();
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private ICoupLimitRSV coupLimitRSV;
     
    /**
     * 已选择分类列表
     * @param model
     * @param catgId
     * @param catgIds
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/catgList")
    public String catgList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("catgIds") String[] catgIds) throws Exception {
        
        List<CatgVO> catgList=new ArrayList<CatgVO>();
        model.addAttribute("catgList", catgList);
        
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && catgIds.length==0){
          
            CoupCatgLimitReqDTO reqdto=new CoupCatgLimitReqDTO();
            reqdto.setCoupId(new Long(coupId));
            reqdto.setCategoryType(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_2);//分类查询
            reqdto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupCatgLimitRespDTO> list= coupLimitRSV.queryCoupCatg(reqdto);
            
            if(!CollectionUtils.isEmpty(list)){
           
                 for(CoupCatgLimitRespDTO dto:list){
                     
                     CatgVO  catgVO=new CatgVO();
                     GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                     reqDTO.setCatgCode(dto.getCatgCode());
                     GdsCategoryRespDTO respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
                     catgVO.setCatgCode(dto.getCatgCode());
                     
                     if(respDTO!=null){
                         catgVO.setCatgName(respDTO.getCatgName());
                         catgVO.setStatus(respDTO.getStatus());
                     }
                     catgList.add(catgVO);
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(catgIds) && catgIds.length>0){
                
                //页面传入参数 组织
                List<String> catgs = java.util.Arrays.asList(catgIds);
                //去重 过滤
                Set set = new HashSet(catgs);
                catgs=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(catgs)){
                    //设置列表值
                    for(String catgCode:catgs){
                        if(!StringUtil.isEmpty(catgCode)){
                            //分类信息信息
                            CatgVO  catgVO=new CatgVO();
                            catgVO.setCatgCode(catgCode.split(PromConst.SPILT_HX)[0]);
                            catgVO.setCatgName(catgCode.split(PromConst.SPILT_HX)[1]);
                            catgList.add(catgVO);
                        }
                    }
                }
            }
        }
        return "/seller/coupon/userule/catgtable/coup-catg-table";
    } 
    
    /**
     * 已选择分类黑名单列表
     * @param model
     * @param catgId
     * @param catgIds
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/catgblackList")
    public String catgblackList(Model model, @RequestParam("coupId") String coupId ,@RequestParam("catgIds") String[] catgIds) throws Exception {
        
        List<CatgVO> catgList=new ArrayList<CatgVO>();
        model.addAttribute("catgList", catgList);
        
        //为空 表示新增
        if(!StringUtil.isEmpty(coupId) && catgIds.length==0){
          
            CoupBlackLimitReqDTO reqdto=new CoupBlackLimitReqDTO();
            reqdto.setCoupId(new Long(coupId));
            reqdto.setCategoryType(CouponConstants.CoupCatgLimit.CATEGORY_TYPE_2);//分类查询
            reqdto.setStatus(CouponConstants.CoupSys.status_1);
            List<CoupBlackLimitRespDTO> list= coupLimitRSV.queryCoupBlack(reqdto);
            
            
            if(!CollectionUtils.isEmpty(list)){
           
                 for(CoupBlackLimitRespDTO coupBlackLimitRespDTO:list){
                     
                     CatgVO  catgVO=new CatgVO();
                     GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                     reqDTO.setCatgCode(coupBlackLimitRespDTO.getCatgCode());
                     GdsCategoryRespDTO respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
                     
                     catgVO.setCatgCode(coupBlackLimitRespDTO.getCatgCode());
                     
                     if(respDTO!=null){
                         catgVO.setCatgName(respDTO.getCatgName());
                         catgVO.setStatus(respDTO.getStatus());
                     }
                     catgList.add(catgVO);
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(catgIds) && catgIds.length>0){
                
                //页面传入参数 组织
                List<String> catgs = java.util.Arrays.asList(catgIds);
                //去重 过滤
                Set set = new HashSet(catgs);
                catgs=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(catgs)){
                    //设置列表值
                    for(String catgCode:catgs){
                        if(!StringUtil.isEmpty(catgCode)){
                            //分类信息信息
                            CatgVO  catgVO=new CatgVO();
                            catgVO.setCatgCode(catgCode.split(PromConst.SPILT_HX)[0]);
                            catgVO.setCatgName(catgCode.split(PromConst.SPILT_HX)[1]);
                            catgList.add(catgVO);
                        }
                    }
                }
            }
        }
        return "/seller/coupon/userule/catgtable/coup-blackcatg-table";
    } 
}


