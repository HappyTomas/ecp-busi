package com.zengshi.ecp.busi.goods.controller;

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
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.common.vo.CmsGdsVO;
import com.zengshi.ecp.busi.goods.vo.GdsGuessVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGuessYLRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 猜你喜欢<br>
 * Date:2015年9月6日下午17:21:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdsguess")
public class GdsGuessController extends EcpBaseController {
	private static String MODULE = GdsGuessController.class.getName();
	private static String URL = "/goods/gdsGuess";
	@Resource(name = "gdsGuessYLRSV")
	private IGdsGuessYLRSV gdsGuessYLRSV;

	@Resource(name = "gdsInfoQueryRSV")
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	// 初始化跳转到猜你喜欢列表页面
	@RequestMapping()
	public String init() {
		return URL + "/guess-grid";
	}

	// 新增猜你喜欢
	@RequestMapping(value = "/guessadd")
	public String guessAdd(Model model,GdsShopVO gsShopVO) {
		model.addAttribute("shopId", gsShopVO.getShopId());
		return URL + "/guess-add";
	}

	// 批量删除
	@RequestMapping(value = "/gdsbatchremove")
	@ResponseBody
	public EcpBaseResponseVO gdsBatchRemove(GdsGuessVO gdsVo) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsGuessYLReqDTO dto = new GdsGuessYLReqDTO();
		String[] idList = gdsVo.getOperateId().split(",");
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < idList.length; i++) {
			if (StringUtil.isNotBlank(idList[i])) {
				ids.add(Long.parseLong(idList[i]));
			}
		}
		dto.setIds(ids);
		try {
			gdsGuessYLRSV.executeBatchDeleteGdsGuessYL(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "批量删除错误！！", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

	// 获取列表
	@RequestMapping(value = "/guesslist")
	@ResponseBody
	public Model guessList(Model model, @Valid GdsGuessVO vo) throws Exception {
		// / 后场服务所需要的DTO；
		GdsGuessYLReqDTO dto = vo.toBaseInfo(GdsGuessYLReqDTO.class);
		// 组织参数
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		PageResponseDTO<GdsGuessYLRespDTO> t = gdsGuessYLRSV
				.queryGdsGuessYLRespDTOPaging(dto);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsGuessYLRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);

		return super.addPageToModel(model, respVO);
	}

	// 获取新增界面列表
	@RequestMapping(value = "/querygds")
	@ResponseBody
	public Model querygds(Model model, @ModelAttribute("searchVO") GdsManageVO searchVO) throws Exception {
        
        // 1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);// 已上架
        if (StringUtil.isNotEmpty(searchVO.getCatgCode())) {
            reqDTO.setPlatCatgs(searchVO.getCatgCode());
        }
        // 如果是siteId则直接查找是否是积分商品
//        if ("2".equalsIgnoreCase(siteId)) {
//            reqDTO.setIfScoreGds("1");//积分
//        } else {
            reqDTO.setIfScoreGds("0");//人卫
//        }
        PageResponseDTO<CmsGdsVO> pageInfo = null;
        List<GdsInfoRespDTO> respListTemp = null;
        PageResponseDTO<GdsInfoRespDTO> pageInfoTemp = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<CmsGdsVO> respList = new ArrayList<CmsGdsVO>();
        if (pageInfoTemp != null) {
            respListTemp = pageInfoTemp.getResult();
            pageInfo = new PageResponseDTO<CmsGdsVO>();
            pageInfo.setCount(pageInfoTemp.getCount());
            pageInfo.setPageCount(pageInfoTemp.getPageCount());
            pageInfo.setPageNo(pageInfoTemp.getPageNo());
            pageInfo.setPageSize(pageInfoTemp.getPageSize());
            pageInfo.setResult(respList);
        }
        if (!CollectionUtils.isEmpty(respListTemp)) {
            for (GdsInfoRespDTO gdsInfoRespDTO : respListTemp) {
                if(gdsInfoRespDTO != null){
                    CmsGdsVO gdsVO = new CmsGdsVO();
                    gdsVO.setId(gdsInfoRespDTO.getId());
                    gdsVO.setGdsName(gdsInfoRespDTO.getGdsName());
                    gdsVO.setGdsTypeName(gdsInfoRespDTO.getGdsTypeName());
                    gdsVO.setUrl(gdsInfoRespDTO.getUrl());
                    // 调用店铺，返回店铺信息
//                    if (StringUtil.isNotEmpty(gdsInfoRespDTO.getShopId())) {
//                        ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(gdsInfoRespDTO.getShopId());
//                        if (shopInfoRespDTO != null) {
//                            gdsVO.setShopName(shopInfoRespDTO.getShopName());
//                        }
//                    }
                    // 调用商品属性
                    if (StringUtil.isNotEmpty(gdsInfoRespDTO.getId())) {
                        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                        gdsInfoReqDTO.setId(gdsInfoRespDTO.getId());
                        GdsQueryOption[] gdsOptions = new GdsQueryOption[] { GdsQueryOption.BASIC,
                                GdsQueryOption.MAINPIC };
                        SkuQueryOption[] skuOptions = new SkuQueryOption[] { SkuQueryOption.BASIC,
                                SkuQueryOption.PROP };
                        gdsInfoReqDTO.setGdsQueryOptions(gdsOptions);
                        gdsInfoReqDTO.setSkuQuerys(skuOptions);
                        
                        GdsInfoDetailRespDTO gdsInfoDetailRespDTO = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfoReqDTO);
                        if (gdsInfoDetailRespDTO != null) {
                            // 商品图片
                            GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                            if(gdsMediaRespDTO == null){
                                gdsMediaRespDTO = new GdsMediaRespDTO();
                            }
                            gdsVO.setImageUrl(ParamsTool.getImageUrl(gdsMediaRespDTO.getMediaUuid(), "120x50!"));
                            // 作者
                            gdsVO.setProp1001(this.gdsProp(gdsInfoDetailRespDTO,"1001"));
                            // ISBN
                            gdsVO.setProp1002(gdsInfoDetailRespDTO.getIsbn());
                            // 出版日期
                            gdsVO.setProp1005(this.gdsProp(gdsInfoDetailRespDTO,"1005"));
                            // 版次
                            gdsVO.setProp1010(this.gdsProp(gdsInfoDetailRespDTO,"1010"));
                        }
                    }
                    respList.add(gdsVO);
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        // 2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
	private String gdsProp(GdsInfoDetailRespDTO gdsInfoDetailRespDTO,String propName){
        String gdsProp = "";
        if(gdsInfoDetailRespDTO != null 
                && gdsInfoDetailRespDTO.getSkuInfo() != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps() != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName) != null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues()!=null
                && gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues().get(0)!=null){
            gdsProp = gdsInfoDetailRespDTO.getSkuInfo().getAllPropMaps().get(propName).getValues().get(0).getPropValue();
        } 
        return gdsProp;
    }
	// 编辑猜你喜欢
	@RequestMapping(value = "/gdsedit")
	public String gdsEdit(Model model, @RequestParam("id") String id,GdsShopVO gsShopVO) {

		GdsGuessYLReqDTO reqDto = new GdsGuessYLReqDTO();
		reqDto.setId(Long.parseLong(id));
		GdsGuessYLRespDTO dto = gdsGuessYLRSV.queryGdsGuessYLByPK(reqDto);
		model.addAttribute("guess", dto);
		model.addAttribute("shopId", gsShopVO.getShopId());
		return URL + "/guess-edit";
	}

	// 新增保存
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(@Valid GdsGuessVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsGuessYLReqDTO reqDTO = new GdsGuessYLReqDTO();
		VO.setCatgCode(VO.getRelatedCatgCode());
		VO.setSkuName(VO.getRelatedName());

		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
		reqDTO.setGdsName(VO.getRelatedName());
		// 如果排序为空，则默认赋值为1.
		if (reqDTO.getSortNo() == null) {
			reqDTO.setSortNo(1);
		}

		try {
			gdsGuessYLRSV.saveGdsGuessYL(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "保存错误！！", err);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVo.setResultMsg(err.getErrorMessage());
		}

		return respVo;
	}

	@RequestMapping(value = "/editsave")
	@ResponseBody
	public EcpBaseResponseVO editSave(@Valid GdsGuessVO VO) throws Exception {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		LogUtil.info(MODULE, "==========" + JSONObject.toJSONString(VO));
		GdsGuessYLReqDTO reqDTO = new GdsGuessYLReqDTO();
		ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
		
		reqDTO.setCatgCode(VO.getRelatedCatgCode());
		if(reqDTO.getCatgCode()==null)
			reqDTO.setCatgCode("");
		reqDTO.setGdsName(VO.getRelatedName());
		reqDTO.setId(Long.parseLong(VO.getOperateId()));

		// 如果排序为空，则默认赋值为1.
		if (reqDTO.getSortNo() == null) {
			reqDTO.setSortNo(1);
		}

		try {
			gdsGuessYLRSV.editGdsGuessYL(reqDTO);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "保存错误！！", err);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVo.setResultMsg(err.getErrorMessage());
		}

		return respVo;
	}

}
