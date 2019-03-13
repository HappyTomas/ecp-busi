package com.zengshi.ecp.busi.staff.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryVO;
import com.zengshi.ecp.busi.goods.vo.CommissionVO;
import com.zengshi.ecp.busi.goods.vo.GdsDiscountVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscBatchDelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscListReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustDiscRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustDiscRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 客户提成比例<br>
 * Date:2017年5月12日下午11:04:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author lincx
 * @version
 * @since JDK 1.6
 */

@Controller
@RequestMapping("/custcommission")
public class CustCommissionController extends EcpBaseController {
    private static String MODULE = CustCommissionController.class.getName();

    private static String URL = "/staff/custcommission";

    @Resource(name = "gdsCatgCustDiscRSV")
    private IGdsCatgCustDiscRSV gdsCatgCustDiscRSV;

    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    
    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;

    /**
     * 
     * init:(初始化列表页面). <br/>
     * 
     * @author lincx
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model, GdsShopVO gsShopVO) {
        model.addAttribute("shopId", gsShopVO.getShopId());
        return URL + "/commission-grid";
    }

    // 新增目录
    @RequestMapping(value = "/commissionadd")
    public String discountAdd() {
        return URL + "/open/commission-add";
    }

    // 编辑目录
    @RequestMapping(value = "/commissionedit")
    public String CommissionEdit(Model model, CommissionVO reqVo) {

    	GdsCategoryReqDTO reqDto = reqVo.toBaseInfo(GdsCategoryReqDTO.class);

        ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
        
        GdsCategoryRespDTO resp = null;
        try {
            resp = gdsCategoryRSV.queryGdsCategoryByPK(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取客户提成比例失败！", e);
        }
        CommissionVO commissionVO = new CommissionVO();
        commissionVO.setCommission(resp.getCommission());
        commissionVO.setCatgCode(resp.getCatgCode());
    	commissionVO.setCatgName(resp.getCatgName());
    	commissionVO.setShopId(resp.getShopId());
        model.addAttribute("catgCustDisc", commissionVO);
        return URL + "/open/commission-edit";
    }

    // 显示列表
    @RequestMapping(value = "/commissionlist")
    @ResponseBody
    public Model discountList(Model model, CommissionVO vo) throws Exception {
        // / 后场服务所需要的DTO；
        GdsCategoryReqDTO dto = vo.toBaseInfo(GdsCategoryReqDTO.class);
        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, dto, null, false);
//        dto.setPageNo(1);
//        dto.setPageSize(20);
        PageResponseDTO<GdsCategoryRespDTO> t = gdsCategoryRSV.queryCustCommission(dto);
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if (t.getResult() == null) {
            t.setResult(new ArrayList<GdsCategoryRespDTO>());
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);
    }

    // 批量删除
    @RequestMapping(value = "/gdsbatchremove")
    @ResponseBody
    public EcpBaseResponseVO gdsBatchRemove(CommissionVO reqVo) {
//        EcpBaseResponseVO vo = new EcpBaseResponseVO();
//        JSONArray arrayTemp = JSONArray.fromObject(reqVO.getOperateId());
//        @SuppressWarnings({ "unchecked" })
//        List<GdsCatgCustDiscReqDTO> newList = JSONArray.toList(arrayTemp,
//                GdsCatgCustDiscReqDTO.class);
//
//        GdsCatgCustDiscBatchDelReqDTO batchDTO = new GdsCatgCustDiscBatchDelReqDTO();
//        batchDTO.setCatgCustDiscReqDTOs(newList);
//        try {
//            gdsCatgCustDiscRSV.deleteGdsCatgCustDiscByGroup(batchDTO);
//            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//        } catch (Exception e) {
//
//            BusinessException be = (BusinessException) e;
//            if (e instanceof BusinessException) {
//                vo.setResultMsg(be.getErrorMessage());
//            } else {
//                vo.setResultMsg(e.getMessage());
//            }
//            LogUtil.error(MODULE, "删除出错！", be);
//            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
//        }
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	JSONArray arrayTemp = JSONArray.fromObject(reqVo.getOperateId());
    	List<GdsCategoryReqDTO> newList = JSONArray.toList(arrayTemp,GdsCategoryReqDTO.class);
    	 try {
         	GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
         	if(CollectionUtils.isNotEmpty(newList)){
         		for(GdsCategoryReqDTO gdsDto : newList){
         			gdsCategoryReqDTO.setCatgCode(gdsDto.getCatgCode());
         			gdsCategoryReqDTO.setCommission(new BigDecimal("0"));
         			gdsCategoryReqDTO.setCatgName(gdsDto.getCatgName());
         			gdsCategoryRSV.editGdsCategory(gdsCategoryReqDTO);
         		}
         	}
             vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
         } catch (BusinessException err) {
             LogUtil.error(MODULE, "删除出错！", err);
             vo.setResultMsg(err.getErrorMessage());
             vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
         }
        return vo;
    }

    // 新增保存
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid
    		CommissionVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
        long shopId=100;//人卫只是单店铺
        VO.setShopId(shopId);

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
            	GdsCategoryReqDTO catgCustDiscReqDTO = new GdsCategoryReqDTO();
                ObjectCopyUtil.copyObjValue(VO, catgCustDiscReqDTO, null, true);
                catgCustDiscReqDTO.setCatgCode(VO.getCatgCode());
                catgCustDiscReqDTO.setCatgName(VO.getCatgName());
                catgCustDiscReqDTO.setCommission(VO.getCommission());
                catgCustDiscReqDTO.setShopId(shopId);
                gdsCategoryRSV.editGdsCategory(catgCustDiscReqDTO);
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (BusinessException err) {
                LogUtil.error(MODULE, "保存错误！！", err);
                respVo.setResultMsg(err.getErrorMessage());
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }
        }
        return respVo;
    }
    
 // 新增保存
    @RequestMapping(value = "/ifExit")
    @ResponseBody
    public EcpBaseResponseVO ifExit(@Valid
    		CommissionVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
        long shopId=100;//人卫只是单店铺
        VO.setShopId(shopId);

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
            	GdsCategoryReqDTO catgCustDiscReqDTO = new GdsCategoryReqDTO();
                ObjectCopyUtil.copyObjValue(VO, catgCustDiscReqDTO, null, true);
                catgCustDiscReqDTO.setCatgCode(VO.getCatgCode());
                catgCustDiscReqDTO.setCatgName(VO.getCatgName());
                catgCustDiscReqDTO.setCommission(VO.getCommission());
                catgCustDiscReqDTO.setShopId(shopId);
                GdsCategoryRespDTO  resp = gdsCategoryRSV.queryGdsCategoryByPK(catgCustDiscReqDTO);
                //gdsCategoryRSV.editGdsCategory(catgCustDiscReqDTO);
                if(resp.getCommission()!=null && resp.getCommission()!=new BigDecimal("0")){
                	respVo.setResultFlag(resp.getCommission().toString());
                }else{
                	respVo.setResultFlag("1");//为1判断为不存在 提成比例
                }
                //respVo.setResultFlag(resp.getCommission().toString());
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
    public EcpBaseResponseVO editSave(@Valid CommissionVO VO) throws Exception {
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
        long shopId=100;//人卫只是单店铺
    	VO.setShopId(shopId);
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
            	GdsCategoryReqDTO catgCustDiscReqDTO = new GdsCategoryReqDTO();
                ObjectCopyUtil.copyObjValue(VO, catgCustDiscReqDTO, null, true);
                catgCustDiscReqDTO.setCatgCode(VO.getCatgCode());
                catgCustDiscReqDTO.setCatgName(VO.getCatgName());
                catgCustDiscReqDTO.setCommission(VO.getCommission());
                gdsCategoryRSV.editGdsCategory(catgCustDiscReqDTO);
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
