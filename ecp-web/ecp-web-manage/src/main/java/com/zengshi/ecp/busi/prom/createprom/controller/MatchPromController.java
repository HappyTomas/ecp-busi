package com.zengshi.ecp.busi.prom.createprom.controller;

import java.util.ArrayList;
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
import com.zengshi.ecp.busi.prom.createprom.vo.MatchGdsVO;
import com.zengshi.ecp.busi.prom.createprom.vo.QueryGdsReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromMatchSkuDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromStockLimitDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromUtilRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-9-9下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/matchprom")
public class MatchPromController extends EcpBaseController {
    /**
     * 搭配商品选择
     */
    private static String MODULE = MatchPromController.class.getName();
     
    
    @Resource
    private IPromQueryRSV promQueryRSV;
    
    @Resource
    private IPromUtilRSV promUtilRSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
     
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
   
    /**
     * 促销已选择搭配商品列表
     * @param model
     * @param promId
     * @param gdsList
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/matchList")
    public String matchList(Model model, @RequestParam("promId") String promId ,@RequestParam("skuIds") String[] skuIds) throws Exception {
        
        List<MatchGdsVO> matchList=new ArrayList<MatchGdsVO>();
        model.addAttribute("matchList", matchList);
        
        //非空 需要查询促销商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(promId) && skuIds.length==0){
            
            PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
            promMatchSkuDTO.setPromId(new Long(promId));
            
            List<PromMatchSkuDTO> promMatchSkuList=promQueryRSV.listPromMatchSku(promMatchSkuDTO);
            
            if(!CollectionUtils.isEmpty(promMatchSkuList)){
                
                //查询条件
                GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
                //返回结果值
                GdsSkuInfoRespDTO  gdsSkuInfoRespDTO =null;
                
                 for(PromMatchSkuDTO dto:promMatchSkuList){
                     
                     MatchGdsVO matchGdsVO=new MatchGdsVO();
                     PromStockLimitDTO stocklimit = null;
                     
                     gdsSkuInfoReqDTO.setId(dto.getSkuId());
                     
                     //库存查询 设置条件
                     SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.STOCK,SkuQueryOption.SHOWPRICE};
                     gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                     
                     //gdsSkuInfoRespDTO= gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                     gdsSkuInfoRespDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                     
                     stocklimit = promUtilRSV.queryPromStockLimit(promId, String.valueOf(dto.getSkuId()));
                     if (stocklimit != null) {
                         matchGdsVO.setBuyCnt(stocklimit.getBuyCnt()); 
                         model.addAttribute("showBuyFlage", true);
                    }
                     if(gdsSkuInfoRespDTO!=null){
                        ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, matchGdsVO, "", false);
                        //调用商品接口,验证是否需要库存
						LongReqDTO longReqDTO = new LongReqDTO();
						longReqDTO.setId(new Long(matchGdsVO.getGdsTypeId()));
						matchGdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
                        matchGdsVO.setStockCnt(gdsSkuInfoRespDTO.calcAvalidStocks());
                     }
                     //默认0
                     if(StringUtil.isEmpty(matchGdsVO.getStockCnt())){
                         matchGdsVO.setStockCnt(new Long(0));
                     }
                     matchGdsVO.setGdsId(dto.getGdsId().toString());
                     matchGdsVO.setSkuId(dto.getSkuId().toString());
                     matchGdsVO.setPromId(dto.getPromId());
                     matchGdsVO.setPromCnt(dto.getPromCnt());
                     matchGdsVO.setPrice(dto.getPrice());
                     matchGdsVO.setSortNum(dto.getSortNum());
                    // matchGdsVO.setGdsStatusName(gdsSkuInfoRespDTO.getGdsStatusName())
                     matchList.add(matchGdsVO);
                     
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(skuIds) && skuIds.length>0){
                
                //页面传入参数 组织
                List<String> skus = java.util.Arrays.asList(skuIds);
                //去重 过滤
                Set set = new HashSet(skus);
                skus=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(skus)){
                    
                    //查询条件
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
                    //返回结果值
                    GdsSkuInfoRespDTO  gdsSkuInfoRespDTO =null;
                    
                    //设置列表值
                    for(String skuId:skus){
                        if(!StringUtil.isEmpty(skuId)){
                            
                            MatchGdsVO matchGdsVO=new MatchGdsVO();
                            gdsSkuInfoReqDTO.setId(new Long(skuId));
                            
                            //库存查询 设置条件
                            SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.STOCK,SkuQueryOption.SHOWPRICE};
                            gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                            
                            //gdsSkuInfoRespDTO= gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                            gdsSkuInfoRespDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                            
                            
                            if(gdsSkuInfoRespDTO!=null){
                               ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, matchGdsVO, "", false);
                               //调用商品接口,验证是否需要库存
       						   LongReqDTO longReqDTO = new LongReqDTO();
       					   	   longReqDTO.setId(new Long(matchGdsVO.getGdsTypeId()));
       						   matchGdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
                               matchGdsVO.setStockCnt(gdsSkuInfoRespDTO.calcAvalidStocks());
                            }
                            //默认0
                            if(StringUtil.isEmpty(matchGdsVO.getStockCnt())){
                                matchGdsVO.setStockCnt(new Long(0));
                            }
                            matchGdsVO.setSkuId(skuId);
                            matchList.add(matchGdsVO);
                            
                       }
                    }
                  
                }
            }
        }
        return "/prom/createprom/matchtable/prom-match-table";
    }
    /**
     * 促销已选择搭配商品列表
     * @param model
     * @param promId
     * @param gdsList
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/bindmatchList")
    public String bindList(Model model, @RequestParam("promId") String promId ,@RequestParam("skuIds") String[] skuIds) throws Exception {
        
        List<MatchGdsVO> matchList=new ArrayList<MatchGdsVO>();
        model.addAttribute("matchList", matchList);
        
        //非空 需要查询促销商品列表(编辑 初始化进入非空，重新编辑商品列表也是非空)
        //为空 表示新增
        if(!StringUtil.isEmpty(promId) && skuIds.length==0){
            
            PromMatchSkuDTO promMatchSkuDTO=new PromMatchSkuDTO();
            promMatchSkuDTO.setPromId(new Long(promId));
            
            List<PromMatchSkuDTO> promMatchSkuList=promQueryRSV.listPromMatchSku(promMatchSkuDTO);
            
            if(!CollectionUtils.isEmpty(promMatchSkuList)){
                
                //查询条件
                GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
                //返回结果值
                GdsSkuInfoRespDTO  gdsSkuInfoRespDTO =null;
                
                 for(PromMatchSkuDTO dto:promMatchSkuList){
                     
                     MatchGdsVO matchGdsVO=new MatchGdsVO();
                     PromStockLimitDTO stocklimit = null;
                     //库存查询 设置条件
                     SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.STOCK,SkuQueryOption.SHOWPRICE};
                     gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                     gdsSkuInfoReqDTO.setId(dto.getSkuId());

                     //gdsSkuInfoRespDTO= gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                     gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                     stocklimit = promUtilRSV.queryPromStockLimit(promId, String.valueOf(dto.getSkuId()));
                     if (stocklimit != null) {
                         matchGdsVO.setBuyCnt(stocklimit.getBuyCnt()); 
                         model.addAttribute("showBuyFlage", true);
                    }
                     if(gdsSkuInfoRespDTO!=null){
                        ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, matchGdsVO, "", false);
                           //调用商品接口,验证是否需要库存
						   LongReqDTO longReqDTO = new LongReqDTO();
						   longReqDTO.setId(new Long(matchGdsVO.getGdsTypeId()));
						   matchGdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
                           matchGdsVO.setStockCnt(gdsSkuInfoRespDTO.calcAvalidStocks());

                        //非空 不需要查询
                        if(StringUtil.isEmpty(gdsSkuInfoRespDTO.getMainCatgName())){
                            
                             GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                             reqDTO.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
                             //分类id 查询
                             GdsCategoryRespDTO respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
                             if(respDTO!=null){
                                 matchGdsVO.setMainCatgName(respDTO.getCatgName());
                             }
                        }
                        
                     }
                     matchGdsVO.setGdsId(dto.getGdsId().toString());
                     matchGdsVO.setSkuId(dto.getSkuId().toString());
                     matchGdsVO.setPromId(dto.getPromId());
                     matchGdsVO.setPromCnt(dto.getPromCnt());
                     matchGdsVO.setPrice(dto.getPrice());
                     matchGdsVO.setSortNum(dto.getSortNum());
                     matchList.add(matchGdsVO);
                     
                }
            }
        }else{
            //新增 
            if(!StringUtil.isEmpty(skuIds) && skuIds.length>0){
                
                //页面传入参数 组织
                List<String> skus = java.util.Arrays.asList(skuIds);
                //去重 过滤
                Set set = new HashSet(skus);
                skus=new ArrayList<String>(set);
                if(!CollectionUtils.isEmpty(skus)){
                    
                    //查询条件
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO=new GdsSkuInfoReqDTO();
                    //返回结果值
                    GdsSkuInfoRespDTO  gdsSkuInfoRespDTO =null;
                    
                    //设置列表值
                    for(String skuId:skus){
                        if(!StringUtil.isEmpty(skuId)){
                            
                            MatchGdsVO matchGdsVO=new MatchGdsVO();
                            //库存查询 设置条件
                            SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.STOCK,SkuQueryOption.SHOWPRICE};
                            gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                            gdsSkuInfoReqDTO.setId(new Long(skuId));
                            //gdsSkuInfoRespDTO= gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                            gdsSkuInfoRespDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                            
                            if(gdsSkuInfoRespDTO!=null){
                               ObjectCopyUtil.copyObjValue(gdsSkuInfoRespDTO, matchGdsVO, "", false);
                               //调用商品接口,验证是否需要库存
      						   LongReqDTO longReqDTO = new LongReqDTO();
      						   longReqDTO.setId(new Long(matchGdsVO.getGdsTypeId()));
      						   matchGdsVO.setIsNeedStock(gdsInfoExternalRSV.isNeedStockAmount(longReqDTO));
                               matchGdsVO.setStockCnt(gdsSkuInfoRespDTO.calcAvalidStocks());

                               //非空 不需要查询
                               if(StringUtil.isEmpty(gdsSkuInfoRespDTO.getMainCatgName())){
                                   
                                    GdsCategoryReqDTO reqDTO=new GdsCategoryReqDTO();
                                    reqDTO.setCatgCode(gdsSkuInfoRespDTO.getMainCatgs());
                                    //分类id 查询
                                    GdsCategoryRespDTO respDTO= gdsCategoryRSV.queryGdsCategoryByPK(reqDTO);
                                    if(respDTO!=null){
                                        matchGdsVO.setMainCatgName(respDTO.getCatgName());
                                    }
                               }
                               
                            }
                            matchGdsVO.setSkuId(skuId);
                            
                     
                            matchList.add(matchGdsVO);
                            
                       }
                    }
                  
                }
            }
        }
        return "/prom/createprom/matchtable/prom-bind-match-table";
    }
    /**
     * 搭配商品选择列表 
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Model grid(Model model, QueryGdsReqVO vo) throws Exception {
        
        GdsSkuInfoReqDTO queryDTO=vo.toBaseInfo(GdsSkuInfoReqDTO.class);
        
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);
        
        if(vo!=null && vo.getSiteId()!=null){
      	  List<Long> siteIds=new ArrayList<Long>();
      	  siteIds.add(vo.getSiteId());
      	  queryDTO.setSiteIds(siteIds);
      }
        
        PageResponseDTO<GdsSkuInfoRespDTO>  page=gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(queryDTO);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }
}


