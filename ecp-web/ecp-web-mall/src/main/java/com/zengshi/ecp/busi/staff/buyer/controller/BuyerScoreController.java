/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.buyer.controller;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.buyer.vo.CustAddrVO;
import com.zengshi.ecp.busi.staff.buyer.vo.ScoreParamVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreTypeResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreCaclRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall Maven Webapp <br>
 * Description: 会员积分<br>
 * Date:2015-9-16上午11:01:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 包括签到送积分
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/buyerscore")
public class BuyerScoreController extends EcpBaseController {
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Resource
    private IScoreCaclRSV scoreCaclRSV;
    
    /**
     * 
     * buyerCenter:(跳转到买家积分中心). <br/> 
     * 同时初始化积分来源列表相应数据
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/index")
    public String buyerScoreInit(Model model,ScoreParamVO scoreParam) throws BusinessException{
        //获取当前登录用户
        BaseStaff staff = new BaseInfo().getStaff();
        CustInfoReqDTO custReq = new CustInfoReqDTO();
        custReq.setId(staff.getId());
        //获取用户信息
        CustInfoResDTO custInfo = custInfoRSV.getCustInfoById(custReq);
        
        //获取用户积分信息
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(staff.getId());
        if (scoreInfo.getStaffId() == null) {
            scoreInfo.setScoreBalance(0L);
            scoreInfo.setScoreTotal(0L);
            scoreInfo.setScoreUsed(0L);
            scoreInfo.setScoreFrozen(0L);
        }
        //获取积分类型列表
        PageResponseDTO<ScoreTypeResDTO> scoreTypePage = scoreInfoRSV.queryScoreTypeList(new ScoreTypeReqDTO());
        model.addAttribute("scoreInfo", scoreInfo);//积分账户
        model.addAttribute("custLevel", custInfo.getCustLevelName());//会员等级
        model.addAttribute("custPic",custInfo.getCustPic());//会员头像
        model.addAttribute("beginDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        if (scoreTypePage != null) {
            model.addAttribute("scoreTypeList", scoreTypePage.getResult());//积分类型
        }
        //查询积分来源明细列表
        ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
        sourceReq.setPageSize(10);//初始化一页10条记录
        sourceReq.setPageNo(1);//初始化查第1页
        sourceReq.setSelDateFrom(DateUtil.getOffsetDaysTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        sourceReq.setSelDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd") + " 23:59:59","yyyy-MM-dd HH:mm:ss"));
        sourceReq.setStaffId(staff.getId());
        PageResponseDTO<ScoreSourceResDTO> scoreSourcePage = scoreInfoRSV.listScoreSource(sourceReq);
        model.addAttribute("scoreSourcePage", scoreSourcePage);//积分来源列表明
        //根据条件，查询汇总积分
        long scoreSourceSum = scoreInfoRSV.sumScoreSourceExample(sourceReq);
        //查询用户所有积分来源汇总
        ScoreSourceReqDTO sourceTotalReq = new ScoreSourceReqDTO();
        sourceTotalReq.setStaffId(staff.getId());
        long scoreSourceTotalSum = scoreInfoRSV.sumScoreSourceExample(sourceTotalReq);
        
        //积分消费：积分汇总
        ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
        exchangeReq.setStaffId(staff.getId());
        long scoreExchangeTotalSum = scoreInfoRSV.sumScoreExchangeExample(exchangeReq);
        model.addAttribute("scoreExchangeTotalSum", scoreExchangeTotalSum);
        model.addAttribute("scoreSourceSum", scoreSourceSum);//积分来源：积分汇总
        model.addAttribute("scoreSourceTotalSum", scoreSourceTotalSum);//积分来源：所有的来源积分汇总
        return "/staff/buyer/buyer-score";
    }
    
    /**
     * 
     * scoreSourceTab:(加载积分来源列表tab页). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/sourcetab")
    public String scoreSourceTab(Model model,ScoreParamVO scoreParam) throws BusinessException{
        //获取积分类型列表
        PageResponseDTO<ScoreTypeResDTO> scoreTypePage = scoreInfoRSV.queryScoreTypeList(new ScoreTypeReqDTO());
        model.addAttribute("beginDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        if (scoreTypePage != null) {
            model.addAttribute("scoreTypeList", scoreTypePage.getResult());//积分类型
        }
        //查询积分来源明细列表
        ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
        sourceReq.setPageNo(scoreParam.getPageNumber());
        sourceReq.setPageSize(scoreParam.getPageSize());
        sourceReq.setSelDateFrom(DateUtil.getOffsetDaysTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        sourceReq.setSelDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd") + " 23:59:59","yyyy-MM-dd HH:mm:ss"));

        sourceReq.setStaffId(sourceReq.getStaff().getId());
        
        PageResponseDTO<ScoreSourceResDTO> scoreSourcePage = scoreInfoRSV.listScoreSource(sourceReq);
        model.addAttribute("scoreSourcePage", scoreSourcePage);//积分来源列表
        
        //根据条件，查询汇总积分
        long scoreSourceSum = scoreInfoRSV.sumScoreSourceExample(sourceReq);
        model.addAttribute("scoreSourceSum", scoreSourceSum);//积分来源：积分汇总
        return "/staff/buyer/score/buyer-score-source";
    }
    
    /**
     * 
     * scoreSourceList:(积分来源列表). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/sourcelist")
    public String scoreSourceList(Model model,ScoreParamVO scoreParam,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate,@RequestParam("scoreType") String scoreType) throws BusinessException{
        //查询积分来源明细列表
        ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
        sourceReq.setPageNo(scoreParam.getPageNumber());
        sourceReq.setPageSize(scoreParam.getPageSize());
        sourceReq.setStaffId(sourceReq.getStaff().getId());
        //查询条件：开始时间
        if (StringUtil.isNotBlank(beginDate)) {
            sourceReq.setSelDateFrom(DateUtil.getTimestamp(beginDate));
        }
        //查询条件：结束时间
        if (StringUtil.isNotBlank(endDate)) {
            sourceReq.setSelDateEnd(DateUtil.getTimestamp(endDate + " 23:59:59","yyyy-MM-dd HH:mm:ss"));
        }
        //查询条件：积分类型
        sourceReq.setSourceType(scoreType);
        
        PageResponseDTO<ScoreSourceResDTO> scoreSourcePage = scoreInfoRSV.listScoreSource(sourceReq);
        model.addAttribute("scoreSourcePage", scoreSourcePage);//积分来源列表
        
        //根据条件，查询汇总积分
        long scoreSourceSum = scoreInfoRSV.sumScoreSourceExample(sourceReq);
        model.addAttribute("scoreSourceSum", scoreSourceSum);//积分来源：积分汇总
        return "/staff/buyer/score/source-list";
    }
    
    /**
     * 
     * scoreExchangeList:(积分消费列表). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/exchangetab")
    public String scoreExchangeTab(Model model,ScoreParamVO scoreParam) throws BusinessException{
        model.addAttribute("beginDate", DateUtil.getOffsetDaysDate(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        model.addAttribute("endDate", DateUtil.getDate());
        //查询积分来源明细列表
        ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
        exchangeReq.setPageNo(scoreParam.getPageNumber());
        exchangeReq.setPageSize(scoreParam.getPageSize());
        exchangeReq.setStaffId(exchangeReq.getStaff().getId());
        
        exchangeReq.setSelDateFrom(DateUtil.getOffsetDaysTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()), -90));
        exchangeReq.setSelDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd") + " 23:59:59","yyyy-MM-dd HH:mm:ss"));

        //积分消费列表
        PageResponseDTO<ScoreExchangeResDTO> scoreExchangePage = scoreInfoRSV.listScoreExchange(exchangeReq);
        //获取站点对应的url链接
        if (scoreExchangePage != null && scoreExchangePage.getResult() != null) {
            List<ScoreExchangeResDTO> exchangeList = new ArrayList<ScoreExchangeResDTO>();
            for (ScoreExchangeResDTO exchange : scoreExchangePage.getResult()) {
                if (exchange.getSiteId() != null) {
                    CmsSiteRespDTO site = CmsCacheUtil.getCmsSiteCache(exchange.getSiteId());
                    if (site != null) {
                        exchange.setSiteUrl(site.getSiteUrl());
                    }
                    exchangeList.add(exchange);
                } else {
                    exchangeList.add(exchange);
                }
            }
            scoreExchangePage.setResult(exchangeList);
        }
        model.addAttribute("scoreExchangePage", scoreExchangePage);
        
        
        //积分消费：积分汇总
        long scoreExchangeSum = scoreInfoRSV.sumScoreExchangeExample(exchangeReq);
        model.addAttribute("scoreExchangeSum", scoreExchangeSum);
        return "/staff/buyer/score/buyer-score-exchange";
    }
    /**
     * 
     * scoreExchangeList:(加载积分消费列表tab页). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/exchangelist")
    public String scoreExchangeList(Model model,ScoreParamVO scoreParam,@RequestParam("beginDate") String beginDate,@RequestParam("endDate") String endDate) throws BusinessException{
        //查询积分来源明细列表
        ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
        exchangeReq.setPageNo(scoreParam.getPageNumber());
        exchangeReq.setPageSize(scoreParam.getPageSize());
        exchangeReq.setStaffId(exchangeReq.getStaff().getId());
        //查询条件：开始时间
        if (StringUtil.isNotBlank(beginDate)) {
            exchangeReq.setSelDateFrom(DateUtil.getTimestamp(beginDate));
        }
        //查询条件：结束时间
        if (StringUtil.isNotBlank(endDate)) {
            exchangeReq.setSelDateEnd(DateUtil.getTimestamp(endDate + " 23:59:59","yyyy-MM-dd HH:mm:ss"));
        }
        PageResponseDTO<ScoreExchangeResDTO> scoreExchangePage = scoreInfoRSV.listScoreExchange(exchangeReq);
        //获取站点对应的url链接
        if (scoreExchangePage != null && scoreExchangePage.getResult() != null) {
            List<ScoreExchangeResDTO> exchangeList = new ArrayList<ScoreExchangeResDTO>();
            for (ScoreExchangeResDTO exchange : scoreExchangePage.getResult()) {
                if (exchange.getSiteId() != null) {
                    CmsSiteRespDTO site = CmsCacheUtil.getCmsSiteCache(exchange.getSiteId());
                    if (site != null) {
                        exchange.setSiteUrl(site.getSiteUrl());
                    }
                    exchangeList.add(exchange);
                } else {
                    exchangeList.add(exchange);
                }
            }
            scoreExchangePage.setResult(exchangeList);
        }
        model.addAttribute("scoreExchangePage", scoreExchangePage);//积分消费列表
        
        long scoreExchangeSum = scoreInfoRSV.sumScoreExchangeExample(exchangeReq);
        model.addAttribute("scoreExchangeSum", scoreExchangeSum);//积分消费：积分汇总
        return "/staff/buyer/score/exchange-list";
    }
    
    /**
     * 
     * scoreRule:(加载到积分规则tab页). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/scorerule")
    public String scoreRule() throws BusinessException{
        return "/staff/buyer/score/buyer-score-rule";
    }
    
    @RequestMapping(value="/checklogin")
    @ResponseBody
    public EcpBaseResponseVO saveaddr(Model model, @ModelAttribute CustAddrVO custaddr){   
    	EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        CustInfoReqDTO req = new CustInfoReqDTO();
        if (req.getStaff().getId() == 0L) {
        	respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        } else {
        	respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }
        return respVo;
    }
    
    /**
     * 
     * sign:(签到送积分). <br/> 
     * 一天签到只会送一次积分
     * @author huangxl5 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/sign")
    @ResponseBody
    public EcpBaseResponseVO sign(Model model){   
    	EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        CustInfoReqDTO req = new CustInfoReqDTO();
        req.setId(req.getStaff().getId());
        req.setStaffCode(req.getStaff().getStaffCode());
        //调用业务方法，赠送积分，签到送积分行为类型：07
        scoreCaclRSV.updateScore("07", req, null);
        respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVo;
    }
}


