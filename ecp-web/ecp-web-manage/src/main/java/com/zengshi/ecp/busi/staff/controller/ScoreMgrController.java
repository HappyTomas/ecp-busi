/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.ScoreAdjustVO;
import com.zengshi.ecp.busi.staff.vo.ScoreSelVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/scoremgr")
public class ScoreMgrController extends EcpBaseController {
    
    private static String MODULE = ScoreMgrController.class.getName();
    
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    
    /**
     * 
     * grid:(待审核页). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */

    @RequestMapping(value="/grid")
    public String grid(){
    	return "/staff/score/score-grid";
    }
    /**
     * 
     * exchangeGrid:(跳转到消费明细列表). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/exchange/grid")
    public String exchangeGrid(Model model){
        model.addAttribute("dateFrom", DateUtil.getTimeThisMonthFirstSec(DateUtil.getSysDate()));
        model.addAttribute("dateEnd", DateUtil.getSysDate());
        return "/staff/score/score-exchange-grid";
    }
    /**
     * 
     * exchangeGrid:(跳转到消费明细列表). <br/> 
     * 
     * @author huangxl 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/detail/grid")
    public String detailGrid(Model model){
       // model.addAttribute("dateFrom", DateUtil.getTimeThisMonthFirstSec(DateUtil.getSysDate()));
       // model.addAttribute("dateEnd", DateUtil.getSysDate());
        ScoreInfoReqDTO req = new ScoreInfoReqDTO();
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(req.getStaff().getId());
        model.addAttribute("scoreBalance", scoreInfo.getScoreBalance());//可用积分
        return "/staff/score/score-detail-grid";
    }

    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo, @ModelAttribute("staffCode") String staffCode,@ModelAttribute("status") String status) throws Exception{
        
        ScoreInfoReqDTO req = vo.toBaseInfo(ScoreInfoReqDTO.class);
        req.setStaffCode(staffCode);
        req.setStatus(status);
        PageResponseDTO<ScoreInfoResDTO> t = scoreInfoRSV.listScoreInfo(req);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
        
    }
    
    @RequestMapping("/exchange/gridlist")
    @ResponseBody
    public Model exchangeGridList(Model model, EcpBasePageReqVO vo,ScoreSelVO scoreSelVO) throws Exception{
        ScoreExchangeReqDTO req = vo.toBaseInfo(ScoreExchangeReqDTO.class);
        /*1、先根据staffCode查询出用户的id，再根据id去查询积分消费明细。所以这里staffCode采用的是完全匹配查询*/
        if (StringUtil.isNotBlank(scoreSelVO.getStaffCode())) {
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setStaffCode(scoreSelVO.getStaffCode());
            CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
            if (custRes != null && custRes.getId() != null && custRes.getId() != 0L) {
                req.setStaffId(custRes.getId());
            } else {
                PageResponseDTO<ScoreExchangeResDTO> t = new PageResponseDTO<ScoreExchangeResDTO>();
                //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
                EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
                return super.addPageToModel(model, respVO);
            }
        }
        //设置时间区间条件
        if (StringUtil.isNotBlank(scoreSelVO.getDateFrom())) {
            req.setSelDateFrom(DateUtil.getTimestamp(scoreSelVO.getDateFrom()));
        }
        if (StringUtil.isNotBlank(scoreSelVO.getDateEnd())) {
            req.setSelDateEnd(DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(scoreSelVO.getDateEnd()), 1));
        }
        req.setOrderId(scoreSelVO.getOrderId());
        PageResponseDTO<ScoreExchangeResDTO> t = scoreInfoRSV.listScoreExchange(req);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
        
    }
    /**
     * 
     * custInvalid:(积分账户列表：冻结). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/scoreinvalid")
    @ResponseBody
    public EcpBaseResponseVO scoreInvalid(Model model,  @RequestParam("id")Long id) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        ScoreInfoReqDTO req = new ScoreInfoReqDTO();
        req.setId(id);
        req.setStatus(StaffConstants.Score.SCORE_STATUS_FROZEN);
        try {
            scoreInfoRSV.updateScoreInfo(req);
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } catch (Exception e) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        
        return result;
    }
    /**
     * 
     * custInvalid:(积分账户列表：解冻). <br/> 
     * 
     * @author huangxl 
     * @param model
     * @param staffId
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/gridlist/scorevalid")
    @ResponseBody
    public EcpBaseResponseVO scoreValid(Model model,  @RequestParam("id")Long id) throws Exception {
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        ScoreInfoReqDTO req = new ScoreInfoReqDTO();
        req.setId(id);
        req.setStatus(StaffConstants.Score.SCORE_STATUS_NORMAL);
        try {
            scoreInfoRSV.updateScoreInfo(req);
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        } catch (Exception e) {
            result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101000", new String[]{}));
        }
        return result;
    }

    @RequestMapping(value="/adjust")
    public String adjust(Model model,@RequestParam(value = "staffId")Long staffId){
        model.addAttribute("staffId", staffId);
        return "/staff/score/adjust/score-adjust";
    }
    
    @RequestMapping(value="/adjust/save")
    @ResponseBody
    public EcpBaseResponseVO scoreAdjust(@Valid @ModelAttribute ScoreAdjustVO adjustVO) throws Exception{
        EcpBaseResponseVO result = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "======== 保存积分调整信息  开始   =======");
        
        /*积分调增*/
        if ("add".equals(adjustVO.getAdjustType())) {
            ScoreSourceReqDTO req = new ScoreSourceReqDTO();
            req.setCreateStaff(req.getStaff().getId());//后续要改为取当前操作人
            req.setUpdateStaff(req.getStaff().getId());//后续要改为取当前操作人
            req.setCreateTime(DateUtil.getSysDate());
            req.setUpdateTime(DateUtil.getSysDate());
            req.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_5);
            req.setStaffId(adjustVO.getStaffId());
            req.setScore(adjustVO.getScore());
            req.setSourceType(adjustVO.getScoreAdjustType());
            req.setRemark(adjustVO.getRemark());
            req.setSiteId(req.getCurrentSiteId());
            scoreInfoRSV.saveScoreAdd(req);
            
        /*积分调减*/
        } else if ("reduce".equals(adjustVO.getAdjustType())) {
            ScoreExchangeReqDTO req = new ScoreExchangeReqDTO();
            req.setCreateStaff(req.getStaff().getId());//后续要改为取当前操作人
            req.setCreateTime(DateUtil.getSysDate());
            req.setExchangeMode("3");
            //积分操作类型
            req.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_6);
            req.setScoreType(adjustVO.getScoreAdjustType());
            req.setScoreUsing(adjustVO.getScore());
            req.setStaffId(adjustVO.getStaffId());
            req.setRemark(adjustVO.getRemark());
            req.setSiteId(req.getCurrentSiteId());
            scoreInfoRSV.scoreUse(req);
        }
        result.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        LogUtil.info(MODULE, "======== 保存积分调整信息  结束   =======");

        return result;
    }
    @RequestMapping("/detail/gridlist")
    @ResponseBody
    public Model detailGridList(Model model, EcpBasePageReqVO vo,ScoreSelVO scoreSelVO) throws Exception{
        ScoreDetailSelReqDTO req = vo.toBaseInfo(ScoreDetailSelReqDTO.class);
        /*1、先根据staffCode查询出用户的id，再根据id去查询积分消费明细。所以这里staffCode采用的是完全匹配查询*/
        if (StringUtil.isNotBlank(scoreSelVO.getStaffCode())) {
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setStaffCode(scoreSelVO.getStaffCode());
            CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
            if (custRes != null && custRes.getId() != null && custRes.getId() != 0L) {
                req.setStaffId(custRes.getId());
            } else {
                PageResponseDTO<ScoreDetailSelResDTO> t = new PageResponseDTO<ScoreDetailSelResDTO>();
                //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
                EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
                return super.addPageToModel(model, respVO);
            }
        } else {
            PageResponseDTO<ScoreDetailSelResDTO> t = new PageResponseDTO<ScoreDetailSelResDTO>();
            //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
            return super.addPageToModel(model, respVO);
        }
        //设置时间区间条件
        if (StringUtil.isNotBlank(scoreSelVO.getDateFrom())) {
            req.setSelDateFrom(DateUtil.getTimestamp(scoreSelVO.getDateFrom()));
        }
        if (StringUtil.isNotBlank(scoreSelVO.getDateEnd())) {
            req.setSelDateEnd(DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(scoreSelVO.getDateEnd()), 1));
        }
        req.setScoreType(scoreSelVO.getScoreType());
        req.setOrderId(scoreSelVO.getOrderId());
        req.setStaffCode(scoreSelVO.getStaffCode());
        PageResponseDTO<ScoreDetailSelResDTO> t = scoreInfoRSV.listScoreDetail(req);
        //获取站点对应的url链接
        if (t != null && t.getResult() != null) {
            List<ScoreDetailSelResDTO> detailList = new ArrayList<ScoreDetailSelResDTO>();
            for (ScoreDetailSelResDTO detail : t.getResult()) {
                if (detail.getSiteId() != null) {
                    CmsSiteRespDTO site = CmsCacheUtil.getCmsSiteCache(detail.getSiteId());
                    if (site != null) {
                    	detail.setSiteUrl(site.getSiteUrl());
                    }
                    detailList.add(detail);
                } else {
                	detailList.add(detail);
                }
            }
            t.setResult(detailList);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
        
    }
    
    /**
     * 
     * detailTotal:(获取积分汇总信息). <br/> 
     * 
     * @author huangxl5
     * @param model
     * @param scoreSelVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/detail/total")
    @ResponseBody
    public Model detailTotal(Model model,ScoreSelVO scoreSelVO) throws Exception{
        
        ScoreDetailSelReqDTO req = new ScoreDetailSelReqDTO();
        /*1、先根据staffCode查询出用户的id，再根据id去查询积分消费明细。所以这里staffCode采用的是完全匹配查询*/
        if (StringUtil.isNotBlank(scoreSelVO.getStaffCode())) {
            CustInfoReqDTO custReq = new CustInfoReqDTO();
            custReq.setStaffCode(scoreSelVO.getStaffCode());
            CustInfoResDTO custRes = custManageRSV.findCustInfo(custReq);
            if (custRes != null && custRes.getId() != null && custRes.getId() != 0L) {
                req.setStaffId(custRes.getId());
            } else {
                return model;
            }
        } else {
            return model;
        }
        //设置时间区间条件
        if (StringUtil.isNotBlank(scoreSelVO.getDateFrom())) {
            req.setSelDateFrom(DateUtil.getTimestamp(scoreSelVO.getDateFrom()));
        }
        if (StringUtil.isNotBlank(scoreSelVO.getDateEnd())) {
            req.setSelDateEnd(DateUtil.getOffsetDaysTime(DateUtil.getTimestamp(scoreSelVO.getDateEnd()), 1));
        }
        req.setScoreType(scoreSelVO.getScoreType());
        req.setOrderId(scoreSelVO.getOrderId());
        req.setStaffCode(scoreSelVO.getStaffCode());
        ScoreDetailSelResDTO scoreDetailRes = scoreInfoRSV.sumScoreByExample(req);
        ScoreInfoResDTO scoreInfoRes = scoreInfoRSV.findScoreInfoByStaffId(req.getStaffId());
        model.addAttribute("scoreBalance", scoreInfoRes.getScoreBalance());//可用积分
        model.addAttribute("consumeScore", scoreDetailRes.getConsumeScore()==null?0:scoreDetailRes.getConsumeScore());//消费积分
        model.addAttribute("modifyAddScore", scoreDetailRes.getModifyAddScore()==null?0:scoreDetailRes.getModifyAddScore());//调增积分
        model.addAttribute("exchangeScore", scoreDetailRes.getExchangeScore()==null?0:scoreDetailRes.getExchangeScore());//已用积分
        model.addAttribute("modifyReduceScore", scoreDetailRes.getModifyReduceScore()==null?0:scoreDetailRes.getModifyReduceScore());//调减积分
        return model;
        
    }
}


