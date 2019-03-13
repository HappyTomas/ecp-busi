package com.zengshi.ecp.busi.buyer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.buyer.vo.BuyerGdsEvalResult;
import com.zengshi.ecp.busi.buyer.vo.EvalVO;
import com.zengshi.ecp.busi.buyer.vo.EvalutionVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdEvaluationResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description:个人中心 已评价 待评价 <br>
 * Date:2016年8月11日上午9:20:45  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/buyereval")
public class BuyerEvalController extends EcpBaseController {

    private String MODULE = BuyerEvalController.class.getName();
    private static String URL = "/buyer/eval";

    @Resource(name = "gdsEvalRSV")
    private IGdsEvalRSV gdsEvalRSV;

    @Resource(name = "ordEvaluationRSV")
    private IOrdEvaluationRSV ordEvaluationRSV;

    @Resource(name = "gdsSkuInfoQueryRSV")
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource(name = "gdsMediaRSV")
    private IGdsMediaRSV gdsMediaRSV;

    /**
     * 
     * init:(买家评价 初始化). <br/>
     * 
     * @author tongkai 2015.9.25
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/{status}")
    public String init(Model model, EvalVO reqVo, @PathVariable(value = "status") String status) {

        return URL + "/buyer-gds-eval";
    }

    /**
     * 
     * evaledList:(买家评价 已评价 列表获取). <br/>
     * 
     * @author tongkai 2015.9.22
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "evaledlist")
    @ResponseBody
    public BuyerGdsEvalResult evaledList(Model model, EvalVO reqVo) {
        // 将页面参数对象VO转化为DTO
        GdsEvalReqDTO reqDto = reqVo.toBaseInfo(GdsEvalReqDTO.class);
        ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);

        // 模拟买家ID
        reqDto.setStaffId(reqDto.getStaff().getId());

        EcpBasePageRespVO<Map> pageRespVO = null;
//        if (reqDto.getPageNo() == 0 || reqDto.getPageSize() == 0) {
            reqDto.setPageNo(1);
            
//        }
//        if(reqVo.getPage() >= 2){
            reqDto.setPageNo(reqVo.getPage());
//        }
        reqDto.setPageSize(5);
        PageResponseDTO<GdsEvalRespDTO> pageRespDto = null;
        BuyerGdsEvalResult result = new BuyerGdsEvalResult();
        try {
            // 查询数据
            pageRespDto = gdsEvalRSV.queryPagingForStaff(reqDto);
            // pageRespDto =pageInit();
            // 如果返回空集 创建一个
            if (pageRespDto.getResult() == null) {
                pageRespDto.setResult(new ArrayList<GdsEvalRespDTO>());
            }

            List<EvalutionVO> newDto = new ArrayList<EvalutionVO>();
            SkuQueryOption[] skuQuery = new SkuQueryOption[2];
            skuQuery[0] = SkuQueryOption.BASIC;
            skuQuery[1] = SkuQueryOption.MAINPIC;

            // 遍历
            for (GdsEvalRespDTO gdsEvalRespDTO : pageRespDto.getResult()) {
                GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                EvalutionVO evalutionVO = new EvalutionVO();
                // 查询单品信息
                gdsSkuInfoReqDTO.setId(gdsEvalRespDTO.getSkuId());
                gdsSkuInfoReqDTO.setGdsId(gdsEvalRespDTO.getGdsId());
                gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                gdsSkuInfoReqDTO.setStatus("1");
                gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);

                evalutionVO.setSkuUrl(gdsSkuInfoRespDTO.getUrl());
                evalutionVO.setDetail(FileUtil.readFile2Text(gdsEvalRespDTO.getContent(), "UTF-8"));
                evalutionVO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
                evalutionVO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
                evalutionVO.setBuyTime(gdsEvalRespDTO.getEvaluationTime());
                evalutionVO.setIntScore(gdsEvalRespDTO.getIntScore());
                evalutionVO.setOrderId(gdsEvalRespDTO.getOrderId());
                evalutionVO.setOrderSubId(gdsEvalRespDTO.getOrderSubId());
                evalutionVO.setGdsId(gdsEvalRespDTO.getGdsId());
                evalutionVO.setSkuId(gdsEvalRespDTO.getSkuId());
                // 设置图片
                GdsMediaRespDTO mediaDTO = gdsSkuInfoRespDTO.getMainPic();
                try {
                    if (mediaDTO != null && StringUtil.isNotBlank(mediaDTO.getMediaUuid())) {// 设置URL
                        // 图片URL设置
                        mediaDTO.setURL(getImageUrl(mediaDTO.getMediaUuid(), "200x200!"));
                    } else {
                        mediaDTO = new GdsMediaRespDTO();
                        mediaDTO.setURL(getImageUrl("null", "200x200!"));
                    }
                    evalutionVO.setUrl(mediaDTO.getURL());
                } catch (Exception e) {

                    LogUtil.error(MODULE, "获取图片失败！", e);
                }
                newDto.add(evalutionVO);

            }
            result.setTotal(pageRespDto.getPageCount());
            result.setDatas(newDto);

        } catch (Exception e) {
            LogUtil.error(MODULE, "获取已评价列表失败！", e);
            String msg = "出错啦！";
            model.addAttribute("msg", msg);
        }

        return result;
    }

    /**
     * 
     * evaledList:(买家评价 待评价 列表获取). <br/>
     * 
     * @author tongkai 2015.9.22
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "unevallist")
    @ResponseBody
    public BuyerGdsEvalResult unevalList(Model model, EvalVO reqVo) {
        // 将页面参数对象VO转化为DTO
        ROrdEvaluationRequest reqDto = reqVo.toBaseInfo(ROrdEvaluationRequest.class);
        ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
        // 模拟买家ID
        reqDto.setStaffId(reqDto.getStaff().getId());
        EcpBasePageRespVO<Map> pageRespVO = null;
//        if (reqDto.getPageNo() == 0 || reqDto.getPageSize() == 0) {
            reqDto.setPageNo(1);
//        }
        if(reqVo.getPage() >= 2){
            reqDto.setPageNo(reqVo.getPage());
        }
        reqDto.setPageSize(5);
        PageResponseDTO<ROrdEvaluationResponse> pageRespDto = null;
        BuyerGdsEvalResult result = new BuyerGdsEvalResult();
        try {
            // 查询数据
            pageRespDto = ordEvaluationRSV.queryEvaluationWait(reqDto);
            // pageRespDto=initpage();
            if (pageRespDto.getResult() == null) {
                pageRespDto.setResult(new ArrayList<ROrdEvaluationResponse>());
            }

        } catch (Exception e) {

            LogUtil.error(MODULE, "获取待评价列表失败！", e);
        }

        List<EvalutionVO> newDto = this.unevalDetail(pageRespDto.getResult());
        result.setTotal(pageRespDto.getPageCount());
        result.setDatas(newDto);

        return result;
    }
    
    /**
     * 
     * toEval:(去评价). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/toeval")
    public String toEval(Model model,EvalVO vo){
        model.addAttribute("gdsEval", vo);
        return URL + "/buyer-toeval";
    }
    
    /**
     * 
     * save:(保存评价). <br/> 
     * 
     * @author gxq 
     * @param VO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(EvalVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
        GdsEvalReqDTO reqDTO = VO.toBaseInfo(GdsEvalReqDTO.class);
        VO.setContent(FileUtil.saveFile(VO.getDetail().getBytes("utf-8"), GdsEvalReply.DEFAULT_FILE_NAME, GdsEvalReply.FILE_TYPE_HTML));
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setIsAnonymous(VO.getIfAnonymous());
        reqDTO.setStaffId(reqDTO.getStaff().getId());

        try {
            gdsEvalRSV.saveGdsEval(reqDTO);
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception err) {
            LogUtil.error(MODULE, "保存评价出错！！", err);
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            // respVo.setResultMsg(err.getErrorMessage());
        }

        return respVo;
    }

    private String getImageUrl(String vfsId, String param) {
        StringBuilder sb = new StringBuilder();
        sb.append(vfsId);
        if (!StringUtil.isBlank(param)) {
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }

    // 查询为评价订单单品详情
    private List<EvalutionVO> unevalDetail(List<ROrdEvaluationResponse> pageRespDto) {
        List<EvalutionVO> newDto = new ArrayList<EvalutionVO>();
        SkuQueryOption[] skuQuery = new SkuQueryOption[2];
        skuQuery[0] = SkuQueryOption.BASIC;
        skuQuery[1] = SkuQueryOption.MAINPIC;

        for (ROrdEvaluationResponse ordEvaluationResponse : pageRespDto) {
            GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
            gdsEvalReqDTO.setOrderId(ordEvaluationResponse.getOrderId());
            gdsEvalReqDTO.setOrderSubId(ordEvaluationResponse.getSubId());
            // 查询订单是否已评价
            if (!gdsEvalRSV.queryHaveEval(gdsEvalReqDTO)) {

                GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
                GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
                EvalutionVO evalutionVO = new EvalutionVO();
                gdsSkuInfoReqDTO.setId(ordEvaluationResponse.getSkuId());
                gdsSkuInfoReqDTO.setGdsId(ordEvaluationResponse.getGdsId());
                gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
                gdsSkuInfoReqDTO.setStatus("1");
                // 查询单品信息
                gdsSkuInfoRespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
                evalutionVO.setSkuUrl(gdsSkuInfoRespDTO.getUrl());
                evalutionVO.setGdsName(gdsSkuInfoRespDTO.getGdsName());
                evalutionVO.setSkuProps(gdsSkuInfoRespDTO.getSkuProps());
                evalutionVO.setOrderId(ordEvaluationResponse.getOrderId());
                evalutionVO.setBuyTime(ordEvaluationResponse.getOrderTime());
                evalutionVO.setOrderSubId(ordEvaluationResponse.getSubId());
                evalutionVO.setGdsId(gdsSkuInfoRespDTO.getGdsId());
                evalutionVO.setShopId(gdsSkuInfoRespDTO.getShopId());
                evalutionVO.setSkuId(ordEvaluationResponse.getSkuId());
                // 设置图片
                GdsMediaRespDTO mediaDTO = gdsSkuInfoRespDTO.getMainPic();
                try {
                    if (mediaDTO != null && StringUtil.isNotBlank(mediaDTO.getMediaUuid())) {// 设置URL
                        // 图片URL设置
                        mediaDTO.setURL(getImageUrl(mediaDTO.getMediaUuid(), "200x200!"));
                    } else {
                        mediaDTO = new GdsMediaRespDTO();
                        mediaDTO.setURL(getImageUrl("null", "200x200!"));
                    }
                    evalutionVO.setUrl(mediaDTO.getURL());
                } catch (Exception e) {
                    LogUtil.error(MODULE, "获取图片失败！", e);
                }
                newDto.add(evalutionVO);
            }
        }
        return newDto;
    }

}

