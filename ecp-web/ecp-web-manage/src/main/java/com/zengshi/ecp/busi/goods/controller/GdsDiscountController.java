package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsDiscountVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 会员分类折扣<br>
 * Date:2015年10月20日下午11:04:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */

@Controller
@RequestMapping("/gdsdiscount")
public class GdsDiscountController extends EcpBaseController {
    private static String MODULE = GdsDiscountController.class.getName();

    private static String URL = "/goods/gdsDiscount";

    @Resource(name = "gdsCatgCustDiscRSV")
    private IGdsCatgCustDiscRSV gdsCatgCustDiscRSV;

    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

    /**
     * 
     * init:(初始化跳转到猜你喜欢列表页面). <br/>
     * 
     * @author tongkai
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model, GdsShopVO gsShopVO) {
        model.addAttribute("shopId", gsShopVO.getShopId());
        return URL + "/discount-grid";
    }

    // 新增目录
    @RequestMapping(value = "/discountadd")
    public String discountAdd() {
        return URL + "/open/discount-add";
    }

    // 编辑目录
    @RequestMapping(value = "/discountedit")
    public String discountEdit(Model model, GdsDiscountVO reqVo) {

        GdsCatgCustDiscReqDTO reqDto = reqVo.toBaseInfo(GdsCatgCustDiscReqDTO.class);

        ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
        // 这些参数不能为空
        reqDto.setPageNo(1);
        reqDto.setPageSize(10);

        List<GdsCatgCustDiscRespDTO> respDtoList = null;
        PageResponseDTO<GdsCatgCustDiscRespDTO> pageRespDto = null;
        try {
            pageRespDto = gdsCatgCustDiscRSV.queryGdsCatgCustDiscByPage(reqDto);
            respDtoList = pageRespDto.getResult();
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取分类折扣失败！", e);
        }
        GdsDiscountVO discountVO = new GdsDiscountVO();
        for (GdsCatgCustDiscRespDTO catgCustDiscRespDTO : respDtoList) {
            if (catgCustDiscRespDTO.getCustLevelCode().equals("01")) {
                discountVO.setDiscount01(catgCustDiscRespDTO.getDiscount());
                discountVO.setId01(catgCustDiscRespDTO.getId());
                discountVO.setCatgCode(catgCustDiscRespDTO.getCatgCode());
                discountVO.setCatgName(catgCustDiscRespDTO.getCatgName());
                discountVO.setShopId(catgCustDiscRespDTO.getShopId());
            } else if (catgCustDiscRespDTO.getCustLevelCode().equals("02")) {
                discountVO.setDiscount02(catgCustDiscRespDTO.getDiscount());
                discountVO.setId02(catgCustDiscRespDTO.getId());

            } else if (catgCustDiscRespDTO.getCustLevelCode().equals("03")) {
                discountVO.setDiscount03(catgCustDiscRespDTO.getDiscount());
                discountVO.setId03(catgCustDiscRespDTO.getId());

            } else if (catgCustDiscRespDTO.getCustLevelCode().equals("04")) {
                discountVO.setDiscount04(catgCustDiscRespDTO.getDiscount());
                discountVO.setId04(catgCustDiscRespDTO.getId());

            }

        }

        model.addAttribute("catgCustDisc", discountVO);
        return URL + "/open/discount-edit";
    }

    // 显示列表
    @RequestMapping(value = "/discountlist")
    @ResponseBody
    public Model discountList(Model model, GdsDiscountVO vo) throws Exception {
        // / 后场服务所需要的DTO；
        GdsCatgCustDiscReqDTO dto = vo.toBaseInfo(GdsCatgCustDiscReqDTO.class);
        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, dto, null, false);
        dto.setStatus(GdsConstants.Commons.STATUS_VALID);
        PageResponseDTO<GdsCatgCustDiscRespDTO> t = gdsCatgCustDiscRSV
                .queryGdsCatgCustDiscByPage(dto);
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if (t.getResult() == null) {
            t.setResult(new ArrayList<GdsCatgCustDiscRespDTO>());
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }

    // 批量删除
    @RequestMapping(value = "/gdsbatchremove")
    @ResponseBody
    public EcpBaseResponseVO gdsBatchRemove(GdsDiscountVO reqVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        JSONArray arrayTemp = JSONArray.fromObject(reqVO.getOperateId());
        @SuppressWarnings({ "unchecked" })
        List<GdsCatgCustDiscReqDTO> newList = JSONArray.toList(arrayTemp,
                GdsCatgCustDiscReqDTO.class);

        GdsCatgCustDiscBatchDelReqDTO batchDTO = new GdsCatgCustDiscBatchDelReqDTO();
        batchDTO.setCatgCustDiscReqDTOs(newList);
        try {
            gdsCatgCustDiscRSV.deleteGdsCatgCustDiscByGroup(batchDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {

            BusinessException be = (BusinessException) e;
            if (e instanceof BusinessException) {
                vo.setResultMsg(be.getErrorMessage());
            } else {
                vo.setResultMsg(e.getMessage());
            }
            LogUtil.error(MODULE, "删除出错！", be);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return vo;
    }

    // 新增保存
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid
    GdsDiscountVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));

        if (shopInfoRSV.findShopInfoByShopID(VO.getShopId()).getShopStatus()
                .equals(GdsConstants.Commons.STATUS_INVALID)) {
            try {
                throw new BusinessException("该店铺已失效！");
            } catch (BusinessException err) {
                LogUtil.error(MODULE, "该店铺已失效！", err);
                respVo.setResultMsg(err.getErrorMessage());
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }

        } else {
            try {
                GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO = new GdsCatgCustDiscListReqDTO();
                List<GdsCatgCustDiscReqDTO> catgCustDiscReqDTOs = new ArrayList<GdsCatgCustDiscReqDTO>();
                GdsCatgCustDiscReqDTO reqDTO01 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO01, null, true);
                reqDTO01.setCustLevelCode("01");
                reqDTO01.setDiscount(VO.getDiscount01());
                catgCustDiscReqDTOs.add(reqDTO01);
                GdsCatgCustDiscReqDTO reqDTO02 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO02, null, true);
                reqDTO02.setCustLevelCode("02");
                reqDTO02.setDiscount(VO.getDiscount02());
                catgCustDiscReqDTOs.add(reqDTO02);
                GdsCatgCustDiscReqDTO reqDTO03 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO03, null, true);
                reqDTO03.setCustLevelCode("03");
                reqDTO03.setDiscount(VO.getDiscount03());
                catgCustDiscReqDTOs.add(reqDTO03);
                GdsCatgCustDiscReqDTO reqDTO04 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO04, null, true);
                reqDTO04.setCustLevelCode("04");
                reqDTO04.setDiscount(VO.getDiscount04());
                catgCustDiscReqDTOs.add(reqDTO04);
                catgCustDiscListReqDTO.setCatgCustDiscReqDTOs(catgCustDiscReqDTOs);
                gdsCatgCustDiscRSV.addGdsCatgCustDisc(catgCustDiscListReqDTO);

                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (BusinessException err) {
                LogUtil.error(MODULE, "保存错误！！", err);
                respVo.setResultMsg(err.getErrorMessage());
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }
        }
        return respVo;
    }

    //
    // // 编辑保存
    @RequestMapping(value = "/editsave")
    @ResponseBody
    public EcpBaseResponseVO editSave(@Valid
    GdsDiscountVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));

        if (shopInfoRSV.findShopInfoByShopID(VO.getShopId()).getShopStatus()
                .equals(GdsConstants.Commons.STATUS_INVALID)) {
            try {
                throw new BusinessException("该店铺已失效！");
            } catch (BusinessException err) {
                LogUtil.error(MODULE, "该店铺已失效！", err);
                respVo.setResultMsg(err.getErrorMessage());
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }

        } else {
            try {
                GdsCatgCustDiscListReqDTO catgCustDiscListReqDTO = new GdsCatgCustDiscListReqDTO();
                List<GdsCatgCustDiscReqDTO> catgCustDiscReqDTOs = new ArrayList<GdsCatgCustDiscReqDTO>();
                GdsCatgCustDiscReqDTO reqDTO01 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO01, null, true);
                reqDTO01.setCustLevelCode("01");
                reqDTO01.setId(VO.getId01());
                reqDTO01.setDiscount(VO.getDiscount01());
                catgCustDiscReqDTOs.add(reqDTO01);
                GdsCatgCustDiscReqDTO reqDTO02 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO02, null, true);
                reqDTO02.setCustLevelCode("02");
                reqDTO02.setId(VO.getId02());
                reqDTO02.setDiscount(VO.getDiscount02());
                catgCustDiscReqDTOs.add(reqDTO02);
                GdsCatgCustDiscReqDTO reqDTO03 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO03, null, true);
                reqDTO03.setCustLevelCode("03");
                reqDTO03.setId(VO.getId03());
                reqDTO03.setDiscount(VO.getDiscount03());
                catgCustDiscReqDTOs.add(reqDTO03);
                GdsCatgCustDiscReqDTO reqDTO04 = new GdsCatgCustDiscReqDTO();
                ObjectCopyUtil.copyObjValue(VO, reqDTO04, null, true);
                reqDTO04.setCustLevelCode("04");
                reqDTO04.setId(VO.getId04());
                reqDTO04.setDiscount(VO.getDiscount04());
                catgCustDiscReqDTOs.add(reqDTO04);
                catgCustDiscListReqDTO.setCatgCustDiscReqDTOs(catgCustDiscReqDTOs);
                gdsCatgCustDiscRSV.editGdsCatgCustDisc(catgCustDiscListReqDTO);
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (BusinessException err) {
                LogUtil.error(MODULE, "保存错误！！", err);
                respVo.setResultMsg(err.getErrorMessage());
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }
        }
        return respVo;
    }

}
