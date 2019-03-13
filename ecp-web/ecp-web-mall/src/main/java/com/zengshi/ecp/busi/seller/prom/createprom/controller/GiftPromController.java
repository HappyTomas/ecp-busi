package com.zengshi.ecp.busi.seller.prom.createprom.controller;

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
import com.zengshi.ecp.busi.seller.prom.createprom.vo.GiftReqVO;
import com.zengshi.ecp.busi.seller.prom.createprom.vo.GiftRespVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;



/**
 * 赠品Controller
 * roject Name:ecp-web-mall <br>
 *  Description: <br>
 * Date:2016-4-20下午7:21:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * @author lisp
 *
 */
@Controller
@RequestMapping(value="/seller/giftprom")
public class GiftPromController extends EcpBaseController {
	
    @Resource
    private IGdsGiftRSV gdsGiftRSV;
	
    @Resource
    private IPromQueryRSV promQueryRSV;

    /**
     * 赠品初始化
     * @return
     * @throws Exception
     * @author lisp
     */

    @RequestMapping()
    public String giftGrid(Model model,GiftReqVO vo) throws Exception { 
        model.addAttribute("shopId", vo.getShopId());
        return "/seller/prom/giftselect/gift-select";
    }
    
	
    /**
     * 赠品选择 按钮 弹出页面
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/giftselect")
    public String giftSelect(Model model,GiftReqVO vo) throws Exception { 
        
        GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
        
        gdsGiftReqDTO=vo.toBaseInfo(GdsGiftReqDTO.class);
        
        ObjectCopyUtil.copyObjValue(vo, gdsGiftReqDTO, "", false);
        
        PageResponseDTO<GdsGiftRespDTO>  giftPage=gdsGiftRSV.queryGdsGift(gdsGiftReqDTO);
        model.addAttribute("giftPage", giftPage);
        return "/seller/prom/giftselect/gift-list";
    }
     
    /**
     * 促销已选择赠品列表
     * @param model
     * @param promId
     * @param giftList
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/giftList")
    public String giftList(Model model, @RequestParam("promId") String promId ,@RequestParam("giftIds") String[] giftIds) throws Exception {
        
        List<GiftRespVO> giftList=new ArrayList<GiftRespVO>();
        model.addAttribute("giftList", giftList);
        
        //非空 需要查询促销商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(promId) && giftIds.length==0){
            
            PromGiftDTO giftDTO=new PromGiftDTO();
            giftDTO.setPromId(new Long(promId));
            
            List<PromGiftDTO> promGiftList=promQueryRSV.listPromotionGift(giftDTO);
            
            if(!CollectionUtils.isEmpty(promGiftList)){
                
                GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
                GdsGiftRespDTO gdsGiftRespDTO=null;
                
                 for(PromGiftDTO dto:promGiftList){
                     
                     GiftRespVO giftRespVO=new GiftRespVO();
                     gdsGiftReqDTO.setId(dto.getGiftId());
                     gdsGiftRespDTO= gdsGiftRSV.querySingleGiftInfo(gdsGiftReqDTO);
                     if(gdsGiftRespDTO!=null){
                        ObjectCopyUtil.copyObjValue(gdsGiftRespDTO, giftRespVO, "", false);
                     }
                     giftRespVO.setGdsId(dto.getGdsId());
                     giftRespVO.setSkuId(dto.getSkuId());
                     giftRespVO.setPresentAllCnt(dto.getPresentAllCnt());
                     giftRespVO.setPresentCnt(dto.getPresentCnt());
                     giftRespVO.setPromId(dto.getPromId());
                     giftRespVO.setEveryTimeCnt(dto.getEveryTimeCnt());
                     giftList.add(giftRespVO);
                     
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(giftIds) && giftIds.length>0){
                
                //页面传入参数 组织
                //giftId的格式为 id-总赠送量-每次赠送量 或者直接id
                List<String> gifts = java.util.Arrays.asList(giftIds);
                //去重 过滤
                Set set = new HashSet(gifts);
                gifts=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(gifts)){
                    
                    GdsGiftReqDTO gdsGiftReqDTO=new GdsGiftReqDTO();
                    GdsGiftRespDTO gdsGiftRespDTO=null;
                    
                    //设置列表值
                    for(String giftId:gifts){
                        if(!StringUtil.isEmpty(giftId)){
                            //处理页面传入数据  并且需要回填页面
                            String[] tmp=giftId.split("-");
                            String id="";
                            String everyTimeCnt="";
                            String promCnt="";
                            if(tmp.length==1){
                                id=tmp[0];
                            }else if(tmp.length==2){
                                id=tmp[0]; 
                                promCnt=tmp[1];
                            }else{
                                id=tmp[0]; 
                                promCnt=tmp[1];
                                everyTimeCnt=tmp[2];
                            }
                            //处理赠品编码
                            GiftRespVO giftRespVO=new GiftRespVO();
                            gdsGiftReqDTO.setId(new Long(id));
                            gdsGiftRespDTO= gdsGiftRSV.querySingleGiftInfo(gdsGiftReqDTO);
                            if(gdsGiftRespDTO!=null){
                               ObjectCopyUtil.copyObjValue(gdsGiftRespDTO, giftRespVO, "", false);
                            }
                            if(!StringUtil.isEmpty(promCnt)){
                                giftRespVO.setPresentAllCnt(Long.valueOf(promCnt));
                            }
                            if(!StringUtil.isEmpty(everyTimeCnt)){
                                giftRespVO.setEveryTimeCnt(Long.valueOf(everyTimeCnt));
                            }
                            giftRespVO.setPresentCnt(Long.valueOf(0));
                            giftList.add(giftRespVO);
                            
                       }
                    }
                  
                }
            }
        }
        return "/seller/prom/giftselect/prom-gift-table";
    }
}
