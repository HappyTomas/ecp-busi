package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.vo.MediaVo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsMedia;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 媒体管理，查询媒体，新增媒体，删除媒体， 媒体类型 1为图片 2为视频 <br>
 * Date:2015年9月06日上午10:45:00 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version
 * @since JDK 1.6
 */

@Controller
@RequestMapping(value = "/gdsmedia")
public class GdsMediaController extends GdsBaseController {

	private String MODULE = GdsMediaController.class.getName();
	private static String URL = "/goods/gdsMedia";

	@Resource
	private IGdsMediaRSV iGdsMediaRSV;

	// 媒体分类服务
	@Resource
	private IGdsMediaCategoryRSV iGdsMediaCategoryRSV;

	/**
	 * 
	 * init:(媒体管理初始化). <br/>
	 * 
	 * @author zhanbh 2015.9.6
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init() {
		return URL + "/list/media-grid";
	}// end of method init

	/**
	 * 
	 * gridList:(媒体列表获取). <br/>
	 * 
	 * @author zhanbh 2015.9.6
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	public Model gridList(Model model, MediaVo vo) {
		GdsMediaReqDTO dto = vo.toBaseInfo(GdsMediaReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		EcpBasePageRespVO<Map> respVO = null;
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		try {
			PageResponseDTO<GdsMediaRespDTO> pageInfo = iGdsMediaRSV
					.queryGdsInfoListPage(dto);
			if (pageInfo.getResult() == null) {
				pageInfo.setResult(new ArrayList<GdsMediaRespDTO>());
			}
			for (GdsMediaRespDTO gdsMediaRespDTO : pageInfo.getResult()) {
				// 转化媒体类型编码
				gdsMediaRespDTO.setMediaType(BaseParamUtil.fetchParamValue(
						"GDS_MEDIA_TYPE", gdsMediaRespDTO.getMediaType()));
				// 获取小图url
				gdsMediaRespDTO.setURL(new AiToolUtil().genImageUrl(
						gdsMediaRespDTO.getMediaUuid(), "30x30!"));
			}// 转化结束

			// 将ResponseDTO 转化为ResponseVO
			respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取媒体列表失败！", e);
		}
		return super.addPageToModel(model, respVO);
	}// end of method gripList

	/**
	 * 
	 * mediaAdd:(跳到添加媒体页面). <br/>
	 * 
	 * @author zhanbh 2015.9.7
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "mediaadd")
	private String mediaAdd() {
		return URL + "/open/media-add";
	}// end of method mediaAdd

	/**
	 * 
	 * mediaEdit:(跳转到编辑媒体页面). <br/>
	 * 
	 * @author zhanbh 2015.9.10
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "mediaedit")
	private String mediaEdit(Model model, MediaVo vo) {
		GdsMediaReqDTO reqDto = new GdsMediaReqDTO();
		reqDto.setId(vo.getId());
		// reqDto.setShopId(1234l);
		GdsMediaRespDTO respDto = null;
		GdsMediaCategoryRespDTO catgRespDto = null;

		try {
			respDto = iGdsMediaRSV.queryGdsMediaById(reqDto);
			if (respDto != null) {
				// 设置URL
				if ("1".equals(respDto.getMediaType())) {// 图片URL设置
					respDto.setURL(new AiToolUtil().genImageUrl(respDto.getMediaUuid(),
							"150x150!"));
				} else {// 视频URL设置
					respDto.setURL("");
				}
				//获取分类名称
				if(respDto.getPicCatgCode() != null){
					GdsMediaCategoryReqDTO catgReqDto = new GdsMediaCategoryReqDTO();
					catgReqDto.setCatgCode(respDto.getPicCatgCode());
					catgRespDto = iGdsMediaCategoryRSV.queryGdsMediaCategoryByPK(catgReqDto);
				}
			}// end of if 1
				// 模拟数据
				// respDto = new GdsMediaRespDTO();
				// respDto.setMediaName("test");
				// respDto.setSortNo(12);
				// respDto.setMediaType("2");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取媒体失败！", e);
		}
		model.addAttribute("mediaInfo", respDto);
		model.addAttribute("catgInfo", catgRespDto);
		return URL + "/open/media-edit";
	}// end of method mediaAdd

	/**
	 * 
	 * saveGdsMedia:(添加新增媒体). <br/>
	 * 
	 * @author zhanbh
	 * @param MediaVo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/savemedia")
	@ResponseBody
	public EcpBaseResponseVO saveGdsMedia(@Valid MediaVo vo) {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsMediaReqDTO reqDto = vo.toBaseInfo(GdsMediaReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, reqDto, "", false);
		// reqDto.setCreateStaff(123456l);
		reqDto.setStatus("1");
		try {
			iGdsMediaRSV.saveGdsMedia(reqDto);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
		return respVo;
	}

	/**
	 * 
	 * updateGdsMedia:(保存修改媒体). <br/>
	 * 
	 * @author zhanbh
	 * @param MediaVo
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/updatemedia")
	@ResponseBody
	public EcpBaseResponseVO updateGdsMedia(@Valid MediaVo vo) {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsMediaReqDTO reqDto = vo.toBaseInfo(GdsMediaReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, reqDto, "", false);
		try {
			iGdsMediaRSV.editGdsMedia(reqDto);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
		return respVo;
	}

	/**
	 * 
	 * gdsMediaRemove:(删除媒体). <br/>
	 * 
	 * @author zhanbh
	 * @param MediaVo
	 * @return
	 * @since JDK 1.6 2015.9.9
	 */
	@RequestMapping(value = "/mediaremove")
	@ResponseBody
	public EcpBaseResponseVO gdsMediaRemove(MediaVo vo) {
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsMediaReqDTO reqDto = vo.toBaseInfo(GdsMediaReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, reqDto, "", false);

		try {
			iGdsMediaRSV.disableGdsMedia(reqDto);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}

		// respVo.setResultMsg("test");
		return respVo;
	}// end of method GdsMediaRemove

	/**
	 * 
	 * mediaShow:(跳转到编辑媒体页面). <br/>
	 * 
	 * @author zhanbh 2015.9.10
	 * @return
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "meidashow")
	private String mediaShow(Model model, MediaVo vo) {
		GdsMediaReqDTO reqDto = new GdsMediaReqDTO();
		reqDto.setId(vo.getId());
		// reqDto.setShopId(1234l);
		GdsMediaRespDTO respDto = null;

		try {
			respDto = iGdsMediaRSV.queryGdsMediaById(reqDto);
			if (respDto != null) {// 设置URL
				if ((GdsMedia.MEDIA_TYPE_PIC).equals(respDto.getMediaType())) {
					respDto.setURL(new AiToolUtil().genImageUrl(respDto.getMediaUuid(),
							"300x300!"));
				} else {// 视频URl设置
					respDto.setURL("/ecp-web-manage/images/goods/videoView.jpg");
				}
			}// end of if 1
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取媒体失败！", e);
		}
		model.addAttribute("mediaInfo", respDto);
		return URL + "/open/media-show";
	}// end of method mediaAdd

	/**
	 * uploadMedia:(接收媒体文件). <br/>
	 * 
	 * @param request
	 * @param response
	 * @author zhanbh
	 * @return
	 * @since JDK 1.6 2015.9.10
	 */
	@RequestMapping(value = "/uploadmedia", method = RequestMethod.POST)
	@ResponseBody
	@NativeJson(true)
	public String uploadMedia(HttpServletRequest req, HttpServletResponse resp) {
		JSONObject obj = new JSONObject();// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		String vfsId = null; // 文件存储返回id
		// 获取媒体
		Iterator<MultipartFile> files = multipartRequest.getFileMap().values()
				.iterator();
		MultipartFile file = null;
		if (files.hasNext()) {
			file = files.next();
		}
		Iterator<String> ids = multipartRequest.getFileMap().keySet()
				.iterator();
		String id = "";
		if (ids.hasNext()) {
			id = ids.next();
		}
		if (file == null) {
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			obj.put("message", "请选择上传文件！");
			LogUtil.info(MODULE, "请选择上传文件！");
			return obj.toJSONString();
		}
		String bmpName = file.getOriginalFilename();
		// 去掉空格
		Pattern p = Pattern.compile("\\s+");
		Matcher m = p.matcher(bmpName);
		//String bmpNamenospa = m.replaceAll("");
		String bmpNamenospa = "132556";
		String extensionName = "." + getExtensionName(bmpName);
		try {
			// 转化为byte
			byte[] datas = inputStream2Bytes(file.getInputStream());

			if (datas.length > 5 * 1024 * 1024) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "文件上传失败，上传的文件必须小于5M!");
				LogUtil.error(MODULE, "文件上传失败，上传的文件必须小于5M!");
				return obj.toJSONString();
			}

			/** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
			boolean imgFlag = Pattern
					.compile(
							"\\.(jpg)$|\\.(png)$|\\.(jpeg)$")
					.matcher(extensionName.toLowerCase()).find();
			boolean vdoFlag = Pattern
					.compile(
							"\\.(avi)$|\\.(wma)$|\\.(rmvb)$|\\.(rm)$|\\.(flash)$|\\.(mp4)$|\\.(mid)$|\\.(3gp)")
					.matcher(extensionName.toLowerCase()).find();
			boolean adoFlag = Pattern
					.compile(
							"\\.(mp3)$|\\.(wav)$|\\.(ogg)$|\\.(ape)$|\\.(acc)$")
					.matcher(extensionName.toLowerCase()).find();

			if (imgFlag) {// 图片
				vfsId = ImageUtil.upLoadImage(datas, bmpNamenospa);

				resultMap.put("bmpUrl", new AiToolUtil().genImageUrl(vfsId, "150x150!"));

			} else if (vdoFlag) {// 视频
				vfsId = FileUtil.saveFile(datas, bmpNamenospa, extensionName);

				resultMap.put("bmpUrl",
						"/ecp-web-manage/images/goods/videoView.jpg");
			} else {// 格式出错
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,)！");
				LogUtil.error(MODULE,
						"上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,)!");
				return obj.toJSONString();
			}
			if (vfsId == null) {
				obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
				obj.put("message", "失败");
				LogUtil.error(MODULE, "文件上传失败");
				return obj.toJSONString();
			}
			resultMap.put("vfsId", vfsId);
			resultMap.put("bmpName", bmpName);
			resultMap.put("id", id);
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			obj.put("message", "文件上传成功!");
			obj.put("resultMap", resultMap);
		} catch (Exception e) {
			LogUtil.info(MODULE, "文件上传失败,原因---" + e.getMessage(), e);
			obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			obj.put("message", "文件上传失败，文件服务器异常，请联系管理员!");
		}
		return obj.toJSONString();
	}

}// end of class GdsMediaController
