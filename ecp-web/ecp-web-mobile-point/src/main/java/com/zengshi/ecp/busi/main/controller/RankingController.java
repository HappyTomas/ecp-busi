package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**微信积分商城-排行榜
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月16日下午5:31:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/ranking")
public class RankingController extends EcpBaseController {

    private static String MODULE = RankingController.class.getName();
    
    private static Long JFSITEID = 2L;//积分商城的站点id
    
    @Resource
    private IReportGoodPayedRSV reportGoodPayedRSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    /**
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL TODO(这里描述这个方法适用条件 –
     * 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model,HttpSession session) {
        Map<String,Object> resultMap = getRankListFromSalesChart(0,7);
        if(resultMap != null){
            model.addAttribute("topIndex", resultMap.get("topIndex"));
            model.addAttribute("gdsList", resultMap.get("gdsList"));
        }
        String url = "/main/ranking/ranking";// 返回页面
        return url;
    }
    /**
     * 
     * getRankListFromSalesChart:(从订单域的reportGoodPayedRSV获取排行榜). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    private Map<String,Object> getRankListFromSalesChart(int start, int size){
        Map<String,Object> resultMap = new HashMap<String, Object>();//结果映射     1：topIndex:取到的最后一个单品id下标，2：skuInfoList：单品信息列表
        int index = start;//单品id下标
        
        //参数校验s--------------------------//
        if(start < 0 && size <= 0){
            resultMap.put("topIndex", -1);
            return null;
        }
        //参数校验e--------------------------//
        
        List<GdsSkuInfoRespDTO> skuInfoList = new ArrayList<GdsSkuInfoRespDTO>();//结果集
        int resultSize = skuInfoList.size();//有效单品值个数
        RSalesChartRequest arg0 = new RSalesChartRequest();
        arg0.setSiteId(JFSITEID);
        int topNum = start + size;//预取的单品id条数
        
        //页数循环  尽可能满所要的条数
        for(;resultSize < size ;topNum += size,resultSize = skuInfoList.size()){
            arg0.setTopNum(topNum);
            RSalesChartResponse rSalesChartResponse = reportGoodPayedRSV.querySkuSalesChart(arg0);
            
            //取出需要的信息，提高效率s-------------
            List<Long> skuIdList = null;
            int skuIdSize = 0;
            if(rSalesChartResponse != null){
                skuIdList = rSalesChartResponse.getSkuIds();
                if(skuIdList != null){
                    skuIdSize = skuIdList.size();
                }
            }
           //取出需要的信息，提高效率e-------------
            
            if ( skuIdSize  > 0 && skuIdSize > index) {
                //单品id循环 过滤掉非上架的
                for (;index < skuIdSize;index++) {
                    Long skuId = skuIdList.get(index);
                    //获取单品信息
                    GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                    gdsSkuInfoReqDTO.setId(skuId);
                    GdsSkuInfoRespDTO detailRespDTO = null;
                    try {
                        detailRespDTO = gdsSkuInfoQueryRSV.queryGdsSkuInfoResp(gdsSkuInfoReqDTO);
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "id为"+skuId.toString()+"的单品获取基本信息异常！", e);
                    }
                    //上架才加入结果集
                    if(detailRespDTO!=null && StringUtil.isNotEmpty(detailRespDTO.getId()) && GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES.equals(detailRespDTO.getGdsStatus())){
                        skuInfoList.add(detailRespDTO);
                        if(skuInfoList.size() == size){
                            resultMap.put("topIndex", index);//当前单品id下标就是读到的最大下标
                            break;
                        }
                    }
                }
            }else{
                resultMap.put("topIndex", index-1);//由于是读到查出来的尽头，所以index需减1
                break;
            }
        }
        //扩展信息
        resultMap.put("gdsList", extendSkuInfo(skuInfoList));
        return resultMap;
    }
    /**
     * 
     * extendSkuInfo:(扩展所需的单品信息). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param skuInfoList 
     * @since JDK 1.6
     */
    private List<GdsInfoDetailRespDTO> extendSkuInfo(List<GdsSkuInfoRespDTO> skuInfoList){
        List<GdsInfoDetailRespDTO> gdsList= null;
        if(CollectionUtils.isNotEmpty(skuInfoList)){
            gdsList = new ArrayList<GdsInfoDetailRespDTO>();
            for(GdsSkuInfoRespDTO skuInfo : skuInfoList){
                if(skuInfo!=null && StringUtil.isNotEmpty(skuInfo.getGdsId())){
                    GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                    GdsQueryOption[] gdsOptions = new GdsQueryOption[] { 
                            GdsQueryOption.BASIC,GdsQueryOption.PRICE, GdsQueryOption.MAINPIC,GdsQueryOption.SCORE
                            };
                    gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                    gdsInfoReqDTO.setId(skuInfo.getGdsId());
                    GdsInfoDetailRespDTO gdsInfoDetailRespDTO = null;
                    try {
                        gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "id为"+skuInfo.getGdsId().toString()+"的商品获取详细信息异常！", e);
                    }
                    //扩展商品主图
                    GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                    if(gdsMediaRespDTO == null){
                        gdsMediaRespDTO = new GdsMediaRespDTO();
                        gdsInfoDetailRespDTO.setMainPic(gdsMediaRespDTO);
                    }
                    try {
                        gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), "283x283!"));
                    } catch (Exception e) {
                        LogUtil.error(MODULE, "id为"+skuInfo.getGdsId().toString()+"的商品获取主图地址异常！", e);
                    }
                    
                    gdsList.add(gdsInfoDetailRespDTO);
                }
            }
        }
        return gdsList;
    }
}
