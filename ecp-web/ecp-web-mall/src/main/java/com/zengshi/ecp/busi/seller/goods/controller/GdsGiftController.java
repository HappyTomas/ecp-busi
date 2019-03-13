package com.zengshi.ecp.busi.seller.goods.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.seller.goods.vo.GiftSkuVO;
import com.zengshi.ecp.busi.seller.goods.vo.GiftVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGiftRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.system.filter.SellerLocaleUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 赠品管理。包括添加、删除、编辑赠品<br>
 * Date:2015年8月22日下午5:03:27 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhangjh6
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "seller/gift")
public class GdsGiftController extends EcpBaseController {
	private String MODULE = GdsGiftController.class.getName();
	private static String URL = "/seller/goods/gift";

	@Resource
	private IGdsGiftRSV igdsGiftRSV;
	@Resource
	private IGdsSkuInfoQueryRSV iGdsSkuInfoQueryRSV;

	/**
	 * 
	 * init:(赠品管理初始化). <br/>
	 * 
	 * @author zhangjh6
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init(Model model, GdsShopVO gsShopVO) {
		
		SellerResDTO srd =  SellerLocaleUtil.getSeller();
    	List<ShopInfoResDTO> shopLst = srd.getShoplist();
        Long shopId = shopLst.get(0).getId();
		model.addAttribute("shopId", shopId);
		GdsGiftReqDTO dto = new GdsGiftReqDTO();
		dto.setShopId(shopId);
		dto.setPageSize(10);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		try {
			PageResponseDTO<GdsGiftRespDTO> pageInfo = igdsGiftRSV
					.queryGdsGift(dto);
			model.addAttribute("giftPage", pageInfo);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取赠品列表失败！", e);
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取赠品列表失败！", e);
		}
		return URL + "/gift-grid";
	}

	/**
	 * 
	 * giftAdd:(跳转到添加赠品页面). <br/>
	 * 
	 * @author zhangjh6
	 * @param giftVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/giftadd")
	public String giftAdd(Model model, @RequestParam("shopId") String shopId) {
		model.addAttribute("shopId", shopId);
		return URL + "/gift-add";
	}

	/**
	 * 
	 * giftSkuOpen:(赠品列表弹出窗口). <br/>
	 * 
	 * @author zhangjh6
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/giftskuopen")
	public String giftSkuOpen(Model model,GiftSkuVO giftSkuVO) {
		GdsSkuInfoReqDTO dto = giftSkuVO.toBaseInfo(GdsSkuInfoReqDTO.class);
		EcpBasePageRespVO<Map> respVO = null;
		ObjectCopyUtil.copyObjValue(giftSkuVO, dto, "", false);
//		dto.setShopId(69l);
		// dto.setStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		dto.setGdsStatusArr(GdsUtils.getNoDeleteStatusList());
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		try {
			PageResponseDTO<GdsSkuInfoRespDTO> pageInfo = iGdsSkuInfoQueryRSV
					.queryGdsSkuInfoListPage(dto);
			model.addAttribute("giftSkuPage", pageInfo);
			model.addAttribute("shopId",dto.getShopId());
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取单品列表失败！", e);
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取单品列表失败！", e);
		}
		return URL + "/list/gift-skulist";
	}

	/**
	 * 
	 * giftEdit:(跳转到赠品编辑的页面). <br/>
	 * 
	 * @author zhangjh6
	 * @param giftVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/giftedit")
	public String giftEdit(Model model, GiftVO giftVO) {
		GdsGiftReqDTO dto = giftVO.toBaseInfo(GdsGiftReqDTO.class);
		dto.setId(giftVO.getGiftId());
		dto.setGiftStatus(GdsConstants.Commons.STATUS_VALID);
		GdsGiftRespDTO gdsGiftInfo = igdsGiftRSV.querySingleGiftInfo(dto);
		
		if(gdsGiftInfo == null){
        	return "redirect:/seller/gift";
		}
		boolean ifReturn=shopIdCheck(gdsGiftInfo.getShopId());
		if(ifReturn){
        	return "redirect:/seller/gift";
        }
		
		gdsGiftInfo.setPicUrl(new AiToolUtil().genImageUrl(
				gdsGiftInfo.getGiftPic(), "150x150!"));
		model.addAttribute("gdsGiftInfo", gdsGiftInfo);
		return URL + "/gift-edit";
	}

	/**
	 * 
	 * giftskulist:(获取单品列表). <br/>
	 * 
	 * @author zhangjh6
	 * @param model
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/gridskulist")
	public String giftskulist(Model model, GiftSkuVO giftSkuVO) {
		GdsSkuInfoReqDTO dto = giftSkuVO.toBaseInfo(GdsSkuInfoReqDTO.class);
		EcpBasePageRespVO<Map> respVO = null;
		ObjectCopyUtil.copyObjValue(giftSkuVO, dto, "", false);
		if (!StringUtil.isEmpty(giftSkuVO.getSkuId())) {
			dto.setId(giftSkuVO.getSkuId());
		}
		if (!StringUtil.isBlank(giftSkuVO.getSkuName())) {
			dto.setGdsName(giftSkuVO.getSkuName());
		}

		if (!StringUtil.isBlank(giftSkuVO.getIsbn())) {
			dto.setIsbn(giftSkuVO.getIsbn());
		}
		// dto.setStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
		dto.setGdsStatusArr(GdsUtils.getNoDeleteStatusList());
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		try {
			PageResponseDTO<GdsSkuInfoRespDTO> pageInfo = iGdsSkuInfoQueryRSV
					.queryGdsSkuInfoListPage(dto);
			model.addAttribute("giftSkuPage", pageInfo);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取单品列表失败！", e);
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取单品列表失败！", e);
		}
		return URL + "/list/sku-list";
	}

	/**
	 * 
	 * saveGdsGift:(添加新增赠品). <br/>
	 * 
	 * @author zhangjh6
	 * @param giftVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savegdsgift")
	@ResponseBody
	public EcpBaseResponseVO saveGdsGift(@Valid GiftVO giftVO) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsGiftReqDTO dto = giftVO.toBaseInfo(GdsGiftReqDTO.class);
		ObjectCopyUtil.copyObjValue(giftVO, dto, "", false);
		dto.setGiftValue(MoneyUtil.convertYuanToCent(giftVO.getGiftValue()));
		try {
			igdsGiftRSV.saveGdsGift(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
		return vo;
	}

	/**
	 * 
	 * saveGiftEdit:(保存编辑后的赠品信息). <br/>
	 * 
	 * @author zhangjh6
	 * @param giftVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savegiftedit")
	@ResponseBody
	public EcpBaseResponseVO saveGiftEdit(GiftVO giftVO) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsGiftReqDTO dto = giftVO.toBaseInfo(GdsGiftReqDTO.class);
		ObjectCopyUtil.copyObjValue(giftVO, dto, "", false);
		if (StringUtil.isNotEmpty(giftVO.getGiftId())) {
			dto.setId(giftVO.getGiftId());
		}
		if (StringUtil.isNotBlank(giftVO.getGiftValue())) {
			dto.setGiftValue(MoneyUtil.convertYuanToCent(giftVO.getGiftValue()));
		}
		try {
			igdsGiftRSV.editGdsGift(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {

			LogUtil.error(MODULE, "报错了啦", e);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
		return vo;
	}

	/**
	 * 
	 * giftremove:(删除赠品（逻辑删除）). <br/>
	 * 
	 * @author zhangjh6
	 * @param giftVO
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/giftremove")
	public EcpBaseResponseVO giftremove(GiftVO giftVO) {
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		GdsGiftReqDTO dto = giftVO.toBaseInfo(GdsGiftReqDTO.class);
		ObjectCopyUtil.copyObjValue(giftVO, dto, "", false);
		if (StringUtil.isNotEmpty(giftVO.getGiftId())) {
			dto.setId(giftVO.getGiftId());
		}
		try {
			igdsGiftRSV.delteGdsGift(dto);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				vo.setResultMsg(be.getErrorMessage());
			} else {
				vo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
		return vo;
	}

	/**
	 * 
	 * gridList:(获取赠品列表信息). <br/>
	 * 
	 * @author zhangjh6
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/gridlist")
	public String gridList(Model model, GiftVO vo) {
		GdsGiftReqDTO dto = vo.toBaseInfo(GdsGiftReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, dto, "", false);

		EcpBasePageRespVO<Map> respVO = null;
		if (StringUtil.isNotEmpty(vo.getGiftId())) {
			dto.setId(vo.getGiftId());
		}
		if (!StringUtil.isBlank(vo.getGiftName())) {
			dto.setGiftName(vo.getGiftName());
		}
		// dto.setShopId(69l);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		try {
			PageResponseDTO<GdsGiftRespDTO> pageInfo = igdsGiftRSV
					.queryGdsGift(dto);
			model.addAttribute("giftPage", pageInfo);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "获取赠品列表失败！", e);
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取赠品列表失败！", e);
		}
		return URL + "/list/list";
	}

	/**
	 * 
	 * uploadImage:(上传图片). <br/>
	 * 
	 * @author zhangjh6
	 * @param model
	 * @param req
	 * @param rep
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/uploadimage")
	@ResponseBody
	@NativeJson(true)
	private String uploadImage(Model model, HttpServletRequest req,
			HttpServletResponse rep) {
		JSONObject obj = new JSONObject();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		// 获取图片
		Iterator<MultipartFile> files = multipartRequest.getFileMap().values()
				.iterator();
		MultipartFile file = null;
		if (files.hasNext()) {
			file = files.next();
		}
		Iterator<String> ids = multipartRequest.getFileMap().keySet()
				.iterator();
		String id = null;
		if (ids.hasNext()) {
			id = ids.next();
		}
		try {
			if (file == null) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "请选择上传文件！");
				LogUtil.error(MODULE, "请选择上传文件！");
				return obj.toJSONString();
			}
			String fileName = file.getOriginalFilename();
			String extensionName = "." + getExtensionName(fileName);

			/** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
			boolean flag = Pattern
					.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$")
					.matcher(extensionName.toLowerCase()).find();
			if (!flag) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif)!");
				LogUtil.error(MODULE,
						"上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif)!");
				return obj.toJSONString();
			}
			// 判断图片的长宽（像素）
			/*
			 * BufferedImage bi = ImageIO.read(file.getInputStream());; int
			 * width = bi.getWidth(); int height = bi.getHeight(); if(width !=
			 * height){ obj.put("success",
			 * EcpBaseResponseVO.RESULT_FLAG_FAILURE); obj.put("message",
			 * "上传的图片的宽度和高度像素不一致！"); LogUtil.error(MODULE, "上传的图片的宽度和高度像素不一致！");
			 * return obj.toJSONString(); }
			 */
			byte[] datas = inputStream2Bytes(file.getInputStream());
			if (datas.length > 5 * 1024 * 1024) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "上传的图片不能大于5M！");
				LogUtil.error(MODULE, "图片上传失败，上传的图片必须小于5M！");
				return obj.toJSONString();
			}
			fileName = Math.random() + "";
			String vfsId = ImageUtil.upLoadImage(datas, fileName);
			resultMap.put("vfsId", vfsId);
			resultMap.put("imageName", fileName);
			resultMap.put("id", id);
			resultMap.put("imagePath",
					new AiToolUtil().genImageUrl(vfsId, "150x150!"));
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			obj.put("message", "保存成功!");
			obj.put("map", resultMap);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "上传图片出错,原因---" + e.getMessage(), e);
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
		} catch (IOException e) {
			LogUtil.error(MODULE, "上传图片出错,原因---" + e.getMessage(), e);
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
		}
		return obj.toJSONString();
	}

	/**
	 * 将InputStream转换成byte数组
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 * @author zhangjh6
	 */
	private byte[] inputStream2Bytes(InputStream in) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[4096];
		int count = -1;
		while ((count = in.read(data, 0, 4096)) != -1)
			outStream.write(data, 0, count);
		data = null;
		return outStream.toByteArray();
	}

	/**
	 * 获取文件拓展名
	 * 
	 * @param fileName
	 * @return
	 * @author zhangjh6
	 */
	private String getExtensionName(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length() - 1))) {
				return fileName.substring(dot + 1);
			}
		}
		return fileName;
	}

	private String getHtmlUrl(String vfsId) {
		if (StringUtil.isBlank(vfsId)) {
			return "";
		}
		return ImageUtil.getStaticDocUrl(vfsId, "html");
	}
	
	private boolean shopIdCheck(Long shopId) {
		SellerResDTO srd = SellerLocaleUtil.getSeller();
		List<ShopInfoResDTO> shopLst = srd.getShoplist();
		if (CollectionUtils.isEmpty(shopLst)) {
			return true;
		} else {
			boolean isContain = false;
			for (ShopInfoResDTO shopInfoResDTO : shopLst) {
				if (shopId.longValue() == shopInfoResDTO.getId().longValue()) {
					isContain = true;
				}
			}
			if (!isContain) {
				return true;
			}
		}
		return false;
	}
}
