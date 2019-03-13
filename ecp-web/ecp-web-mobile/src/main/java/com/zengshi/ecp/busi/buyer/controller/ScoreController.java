package com.zengshi.ecp.busi.buyer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.busi.buyer.vo.ScoreChangeRecodeVO;
import com.zengshi.ecp.busi.buyer.vo.ScoreDetailVO;
import com.zengshi.ecp.busi.buyer.vo.ScoreScrollResult;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAdvertiseRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RCustomerOrdResponse;
import com.zengshi.ecp.order.dubbo.dto.RQueryOrderRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartRequest;
import com.zengshi.ecp.order.dubbo.dto.RSalesChartResponse;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdMainRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSignCheckRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: 用户积分<br>
 * Date:2016年7月27日下午7:35:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/score")
public class ScoreController  extends EcpBaseController{
	
	private static String MODULE = ScoreController.class.getName();
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
	@Resource
	private IScoreCaclRSV scoreCaclRSV;
	
	@Resource
	private ICmsAdvertiseRSV cmsAdvertiseRSV;
	
	@Resource
	private IOrdMainRSV iOrdMainRSV;
	
	@Resource
	private ICmsPageInfoRSV cmsPageInfoRSV;
	
	     
    private final int PAGE_SIZE = 6;//积分收支明细默认一页展示的数据
    
    
    private static Long JFSITEID = 2L;//积分商城的站点id
    
    @Resource
    private IReportGoodPayedRSV reportGoodPayedRSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
   
    
    @RequestMapping(value="/index")
    public String index(Model model) {
    	ScoreInfoReqDTO scoreInfoReq = new ScoreInfoReqDTO();
    	/*1、调用业务查询接口，查询用户积分账户*/
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(scoreInfoReq.getStaff().getId());
        model.addAttribute("scoreBalance", scoreInfo.getScoreBalance()==null?0:scoreInfo.getScoreBalance());
        
        /*2、调用业务查询接口，查询用户积分收支明细*/
        ScoreDetailSelReqDTO scoreReq = new ScoreDetailSelReqDTO();
        scoreReq.setStaffId(scoreReq.getStaff().getId());
        //积分展示的首页，固定显示4条
        scoreReq.setPageNo(1);
        scoreReq.setPageSize(4);
        
        PageResponseDTO<ScoreDetailSelResDTO> page = scoreInfoRSV.listScoreDetailForApp(scoreReq);
        //scoreTypeName积分类型名称
        //orderId订单编码
        //createTime创建时间
        //consumeScore积分 
        model.addAttribute("scorePage", page);
        
    	return "/buyer/score/score-index";
    }
    
    /**
     * 
     * list:(查看更多积分明细). <br/> 
     * 
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/list")
    public String list(Model model) {
        /*1、调用业务查询接口，查询用户积分收支明细*/
        ScoreDetailSelReqDTO scoreReq = new ScoreDetailSelReqDTO();
        scoreReq.setStaffId(scoreReq.getStaff().getId());
        scoreReq.setPageNo(1);
        scoreReq.setPageSize(4);
        
        PageResponseDTO<ScoreDetailSelResDTO> page = scoreInfoRSV.listScoreDetailForApp(scoreReq);
        //scoreTypeName积分类型名称
        //orderId订单编码
        //createTime创建时间
        //consumeScore积分 
        model.addAttribute("scorePage", page);
        
    	return "/buyer/score/score-list-scroll";
    }
    
    
    @RequestMapping(value="/listdata")
    @ResponseBody
    public ScoreScrollResult listdata(Model model,@RequestParam(value="page",required=false)int pageNo) {
    	
    	
    	/*1、调用业务查询接口，查询用户积分收支明细*/
        ScoreDetailSelReqDTO scoreReq = new ScoreDetailSelReqDTO();
        scoreReq.setStaffId(scoreReq.getStaff().getId());
        scoreReq.setPageNo(pageNo);
        scoreReq.setPageSize(PAGE_SIZE);
        
        PageResponseDTO<ScoreDetailSelResDTO> page = scoreInfoRSV.listScoreDetailForApp(scoreReq);
        ScoreScrollResult result = new ScoreScrollResult();
        List<ScoreDetailVO> scoreList = new ArrayList<ScoreDetailVO>();
        if (page != null && CollectionUtils.isNotEmpty(page.getResult())) {
        	for (ScoreDetailSelResDTO score : page.getResult()) {
        		ScoreDetailVO vo = new ScoreDetailVO();
        		vo.setScoreTypeName(score.getScoreTypeName());
        		//处理订单的展示
        		if (StringUtil.isNotBlank(score.getOrderId())) {
        			vo.setOrderId("订单号：" + score.getOrderId());
        		} else {
        			vo.setOrderId("");
        		}
        		//处理时间的展示
        		if (score.getCreateTime() != null) {
        			vo.setCreateTime(DateUtil.getDateString(score.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        		}
        		//处理积分的正负
        		if ("使用积分".equals(score.getScoreTypeName())) {
        			vo.setConsumeScore("-" + score.getConsumeScore());
        		} else {
        			vo.setConsumeScore("+" + score.getConsumeScore());
        		}
        		scoreList.add(vo);
        	}
        }
        result.setTotal(page.getPageCount());
        result.setDatas(scoreList);
    	return result;
    }
    
    /**
     * 
     * giveIndex:(赠积分页面). <br/> 
     * 
     * @author zhuqr 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/giveIndex")
    public String giveIndex(Model model) {
    	
    	Long scoreNum= 0L;	
    	Long giveDay= 0L;		
    	/*1、入参*/
    	ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        //查询时间为今天开始~今天结束
        req.setSelDateFrom(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        req.setSelDateEnd(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
        //用户id
        req.setStaffId(req.getStaff().getId());
        req.setSourceType("7001");
        req.setPageNo(0);
        //   2、调用业务方法，查询当天签到是否送了积分 0则是还没签到
        PageResponseDTO<ScoreSourceResDTO> scoreSourcePage = scoreInfoRSV.listScoreSource(req);
        if (scoreSourcePage != null && CollectionUtils.isNotEmpty(scoreSourcePage.getResult())) {
        	scoreNum=scoreSourcePage.getResult().get(0).getScore();
        } 
        
        ScoreSignCheckRespDTO sign = scoreInfoRSV.findScoreSignByStaffId(req.getStaff().getId());
        if (sign!=null && sign.getSignCnt()!=null) {
        	giveDay=sign.getSignCnt();
        }
        
        /********* 获取广告 begin **************/
        CmsAdvertiseReqDTO advertiseReq = new CmsAdvertiseReqDTO();
        CmsAdvertiseRespDTO advertise=new CmsAdvertiseRespDTO(); 
        advertiseReq.setPlaceId(1701L);//1701
        advertiseReq.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        advertiseReq.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03);
        advertiseReq.setVfsName(ParamsTool.getImageUrl(advertiseReq.getVfsId(),""));
        // 设置当前时间  查询当前时间有效的广告
        advertiseReq.setThisTime(DateUtil.getSysDate());
        //advertiseReq.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));

        List<CmsAdvertiseRespDTO> advertiseRespDTOs=cmsAdvertiseRSV.queryCmsAdvertiseList(advertiseReq);
        if(CollectionUtils.isNotEmpty(advertiseRespDTOs)){
       
        	advertise=advertiseRespDTOs.get(0);
        	getLinkUrl(advertise,BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(1l)));
        	advertise.setVfsUrl(ParamsTool.getImageUrl(advertise.getVfsId(),"640x260!"));
        }
        /********* 获取广告 end **************/
  	 
        
        model.addAttribute("advertise", advertise);
        model.addAttribute("scoreNum", scoreNum);
        model.addAttribute("giveDay", giveDay);
        
    	return "/buyer/score/score-give";
    }
    
    /**
     * 
     * sign:(赠积分-签到 ). <br/> 
     * 
     * @author zhuqr 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/sign")
    @ResponseBody
    public Map<String,Object> sign(Model model,String signFlag) {
       Long scoreNum= 0L;	
	   Long giveDay= 0L;	
	   Map<String,Object>  resultMap=new HashMap<String,Object>();
          	try {
          		 if(StringUtil.isNotEmpty(signFlag)){
          			 CustInfoReqDTO req = new CustInfoReqDTO();
    				 req.setId(req.getStaff().getId());
    				 ScoreSourceResDTO curDaySign = scoreInfoRSV.signAlready(req.getStaff().getId());
    				 if (curDaySign == null) {
                         /*1、签到获取签到分数 */
                         ScoreResultResDTO result = scoreCaclRSV.updateScore("07", req, null);
                         if (result != null && result.getScore() != null && result.getScore() != 0L) {
                            scoreNum=result.getScore();
                          }  
                    }else {
                        scoreNum = curDaySign.getScore();
                    }

    			   //  2、获取签到天数 
    			    ScoreSignCheckRespDTO sign = scoreInfoRSV.findScoreSignByStaffId(req.getStaff().getId());
			        if (sign!=null && sign.getSignCnt()!=null) {
			        	giveDay=sign.getSignCnt();
			        }
			        
			        resultMap.put("scoreNum", scoreNum);
			        resultMap.put("giveDay", giveDay);
			        resultMap.put("resultMsg", "积分签到成功。");
			        resultMap.put("resultFlag", "ok");
          		 }
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				resultMap.put("resultMsg", "积分签到失败。");
		        resultMap.put("resultFlag", "fail");
				e.printStackTrace();
			}
    	return resultMap;
    }
    
    /**
     * 
     * mallpoint:(赠积分-积分商城列表). <br/> 
     * 
     * @author zhuqr 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/mallpoint")
    public String mallpoint(Model model){
    	  Map<String,Object> resultMap = getRankListFromSalesChart(0,10);
          if(resultMap != null){
        	  model.addAttribute("gdsList", resultMap.get("gdsList"));
  
          }
          return "/buyer/score/giveScoreAjax/mallList";
    }
    /**
     * 
     * myPoinList:(赠积分-我的兑换记录). <br/> 
     * 
     * @author zhuqr 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/myChangeList")
    public String myChangeList(Model model){
    	 RQueryOrderRequest rdor = new RQueryOrderRequest();
    	RQueryOrderRequest qQueryOrder=new RQueryOrderRequest();
    	qQueryOrder.setStaffId(qQueryOrder.getStaff().getId());
    	qQueryOrder.setSiteId(JFSITEID);
    	qQueryOrder.setSysType("00");
    	qQueryOrder.setStatus("04");
    	qQueryOrder.setPageNo(1);
    	qQueryOrder.setPageSize(10);
    	PageResponseDTO<RCustomerOrdResponse> ordResps=iOrdMainRSV.queryOrderByStaffId(qQueryOrder);
    	List<ScoreChangeRecodeVO> recodeList=new ArrayList<ScoreChangeRecodeVO>();
    	if(ordResps!=null){
    	  List<RCustomerOrdResponse> result=ordResps.getResult();
  	      if(CollectionUtils.isNotEmpty(result)){
  	    	 for(RCustomerOrdResponse rord : result){
                 for(SOrderDetailsSub sds : rord.getsOrderDetailsSubs()){
                	 ScoreChangeRecodeVO recode = new ScoreChangeRecodeVO();
                     ObjectCopyUtil.copyObjValue(sds, recode, "", false);
                     recode.setOrderTime(rord.getsCustomerOrdMain().getOrderTime());
                     recode.setPicUrl(ParamsTool.getImageUrl(recode.getPicId(),"283x283!"));
                     recodeList.add(recode);
                 }
             }
  	      }
    		
    	}
    	model.addAttribute("recodeList", recodeList);
    
          return "/buyer/score/giveScoreAjax/myChangeList";
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
                            break;
                        }
                    }
                }
            }else{
                break;
            }
        }
        //扩展信息
        resultMap.put("gdsList", extendSkuInfo(skuInfoList));
        return resultMap;
    }
    /**
     * 
     * getLinkUrl:(获取广告的链接地址). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto
     * @param baseUrl 
     * @since JDK 1.6
     */
    private void getLinkUrl(CmsAdvertiseRespDTO dto,String baseUrl){
        if(StringUtil.isBlank(baseUrl)){
            baseUrl = "";
        }
        if (dto != null && StringUtil.isNotBlank(dto.getLinkUrl())) {
            String linkUrl = dto.getLinkUrl();
            
            if(CmsConstants.LinkType.CMS_LINKTYPE_01.equalsIgnoreCase(dto.getLinkType())){//商品
                if(this.isNumeric(linkUrl)){
                    linkUrl = "/gdsdetail/"+linkUrl+"-";
                }
                linkUrl = baseUrl + linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_02.equalsIgnoreCase(dto.getLinkType())){//公告
                if(this.isNumeric(linkUrl)){
                    linkUrl = "/info/infodetail?id="+ linkUrl;
                }
                linkUrl = baseUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_03.equalsIgnoreCase(dto.getLinkType())){
                if(this.isNumeric(linkUrl)){//页面
                    CmsPageInfoReqDTO pageInfoReqDTO = new CmsPageInfoReqDTO();
                    pageInfoReqDTO.setId(Long.valueOf(linkUrl.trim()));
                    CmsPageInfoRespDTO cmsPageInfoRespDTO = cmsPageInfoRSV.queryCmsPageInfo(pageInfoReqDTO);
                    if (cmsPageInfoRespDTO != null && StringUtil.isNotEmpty(cmsPageInfoRespDTO.getSiteUrl())) {
                        linkUrl = cmsPageInfoRespDTO.getSiteUrl();
                    } else {
                        linkUrl = "/modularcommon?pageId=" + linkUrl;
                    }
                }
                linkUrl = baseUrl+linkUrl;
            }else if(CmsConstants.LinkType.CMS_LINKTYPE_04.equalsIgnoreCase(dto.getLinkType())){//其他
                String regex = "^([a-zA-Z]+:(/|\\\\){2})|([a-z0-9A-Z]+(?:-[a-z0-9A-Z]+)*\\.){2,}";//常见的 绝对url格式  并非全部
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(linkUrl);
                if(m.lookingAt()){//绝对地址
                    if(linkUrl.indexOf("://") < 0){
                        linkUrl = "http://"+linkUrl; 
                    }
                }else{//相对地址
                    if(!linkUrl.startsWith("/"));{
                        linkUrl = "/"+linkUrl;
                    }
                    linkUrl = baseUrl+linkUrl;
                }
            }
            
            //加adid  用于广告行为分析   add by zhanbh  2016.9.5
            String linkUrlPro = linkUrl;
            if(StringUtil.isNotEmpty(dto.getId())){
                String [] urlParts = linkUrlPro.split("\\?");
                linkUrlPro = "";
                if(urlParts != null && urlParts.length > 0){
                    int len = urlParts.length;
                    String adidStr = "?adid=";
                    adidStr += String.valueOf(dto.getId());
                    if(len > 1){
                        adidStr += "&"; 
                    }
                    
                    for (int i = 0; i < len ; i++){
                        linkUrlPro += urlParts[i];
                        if(i==0){
                            linkUrlPro += adidStr;
                        }else if(i < len - 1){
                            linkUrlPro += "?";
                        }
                    }
                }
                if(StringUtil.isNotBlank(linkUrlPro)){
                    linkUrl =   linkUrlPro;
                }
            }
            
            dto.setLinkUrl(linkUrl);
        }
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private boolean isNumeric(String str){
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
         
        return false; 
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

