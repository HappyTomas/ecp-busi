package com.zengshi.ecp.busi.buyer.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.buyer.vo.FavGdsResult;
import com.zengshi.ecp.busi.buyer.vo.FavGoodsVO;
import com.zengshi.ecp.busi.buyer.vo.GdsCollectRespVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: 商品收藏<br>
 * Date:2016年8月10日下午7:36:20  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "favgoods")
public class FavGoodsController extends EcpBaseController {

	private String MODULE = FavGoodsController.class.getName();
	private static String URL = "/buyer";

	@Resource
	private IGdsCollectRSV iGdsCollectRSV;
	@Resource
	private IGdsTypeRSV gdsTypeRSV;

	/**
	 * 
	 * init:(初始化). <br/> 
	 * 
	 * @author gxq 
	 * @param model
	 * @param reqVo
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping()
	public String init(Model model, FavGoodsVO reqVo) {
	 
      
        try {
            // 将页面参数对象VO转化为DTO
            GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
            ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
            FavGdsResult result = new FavGdsResult();
            EcpBasePageRespVO<Map> pageRespVo = null;
            reqDto.setStaffId(reqDto.getStaff().getId());
            PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
            if(reqVo.getPage() >= 2){
                reqDto.setPageNo(reqVo.getPage());
            }
            // 查询数据
            reqDto.setPageSize(5);
            pageRespDto = getCollData(reqDto);
            if(pageRespDto == null || pageRespDto.getResult() == null ||pageRespDto.getResult().size()==0){
                model.addAttribute("gdsnodata", "true");
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询商品收藏错误！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询商品收藏错误！", e);
        }
        
        
	    model.addAttribute("showWhat", "gds");
		return "/buyer/shopfav/shop-fav";
	}

	/**
	 * 
	 * gridList:(商品收藏列表). <br/> 
	 * 
	 * @author gxq 
	 * @param model
	 * @param reqVo
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "gridlist")
	@ResponseBody
	public FavGdsResult gridList(Model model, FavGoodsVO reqVo) {
	    List<GdsCollectRespVO> resultList = new ArrayList<GdsCollectRespVO>();
		// 将页面参数对象VO转化为DTO
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		FavGdsResult result = new FavGdsResult();
		EcpBasePageRespVO<Map> pageRespVo = null;
		reqDto.setStaffId(reqDto.getStaff().getId());
		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		if(reqVo.getPage() >= 2){
		    reqDto.setPageNo(reqVo.getPage());
		}
		reqDto.setPageSize(5);
		// 查询数据
		pageRespDto = getCollData(reqDto);
		// 查询出错处理
		if (pageRespDto == null) {
			String msg = "获取关注商品列表失败，稍后再来！";
			model.addAttribute("msg", msg);
			pageRespVo = new EcpBasePageRespVO<Map>();
			pageRespVo.setPageNumber(0);
			pageRespVo.setPageSize(reqVo.getPageSize());
			pageRespVo.setTotalPage(0);
			pageRespVo.setTotalRow(0);
			super.addPageToModel(model, pageRespVo);
		} else {

			// 如果返回空集 创建一个
			if (pageRespDto.getResult() == null) {
				pageRespDto.setResult(new ArrayList<GdsCollectRespDTO>());
			} else {
				// 转化参数 增加reduce字段值
				ArrayList<Long> reduceList = new ArrayList<Long>();
				for (GdsCollectRespDTO respDto : pageRespDto.getResult()) {
				    GdsCollectRespVO respVO = new GdsCollectRespVO();
					if (respDto.getGdsPrice() == null) {
						respDto.setGdsPrice(0l);
					}
					if (respDto.getNowPrice() == null) {
						respDto.setNowPrice(0l);
					}
					reduceList.add(respDto.getGdsPrice()
							- respDto.getNowPrice());
					ObjectCopyUtil.copyObjValue(respDto, respVO, null, false);
                    respVO.setNowPriceYun(String.format("%.2f", BigDecimal.valueOf(Long.valueOf(respVO.getNowPrice())).divide(new BigDecimal(100.00))));
                    respVO.setGdsPriceYun(String.format("%.2f", BigDecimal.valueOf(Long.valueOf(respVO.getGdsPrice())).divide(new BigDecimal(100.00))));
                    resultList.add(respVO);
				}

				model.addAttribute("reduceList", reduceList);
			}
		}
		result.setDatas(resultList);
		result.setTotal(pageRespDto.getPageCount());
		return result;
	}

	
	
	/**
	 * 
	 * getCollData:(获取收藏商品数据). <br/> 
	 * 
	 * @author gxq 
	 * @param reqDto
	 * @return 
	 * @since JDK 1.6
	 */
	
	private PageResponseDTO<GdsCollectRespDTO> getCollData(
			GdsCollectReqDTO reqDto) {

		PageResponseDTO<GdsCollectRespDTO> pageRespDto = null;
		try {
			// 查询数据
			pageRespDto = iGdsCollectRSV.queryGdsCollectRespDTOPagingByStaff(reqDto);
			
			if(CollectionUtils.isNotEmpty(pageRespDto.getResult())){
				List<GdsCollectRespDTO> result=pageRespDto.getResult();
				for (GdsCollectRespDTO gdsCollectRespDTO : result) {
					String imageUrl=getImageUrl(gdsCollectRespDTO.getSkuMainPic(),"127x127!");
					gdsCollectRespDTO.setSkuMainPic(imageUrl);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(MODULE, "获取买家我的收藏列表失败！", e);
			pageRespDto = null;
		}

		return pageRespDto;
	}

	
	/**
	 * 
	 * collRemove:(取消关注). <br/> 
	 * 
	 * @author gxq 
	 * @param reqVo
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/collremove")
	@ResponseBody
	public EcpBaseResponseVO collRemove(FavGoodsVO reqVo) {
		// 转化页面参数对象VO成DTO
		EcpBaseResponseVO respVo = new EcpBaseResponseVO();
		GdsCollectReqDTO reqDto = reqVo.toBaseInfo(GdsCollectReqDTO.class);
		ObjectCopyUtil.copyObjValue(reqVo, reqDto, null, false);
		// 设置额外参数
		reqDto.setStaffId(reqDto.getStaff().getId());
		try {
			int delete = iGdsCollectRSV.deleteGdsCollectByPK(reqDto);
			if (delete > 0) {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			} else {
				respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}

		} catch (Exception e) {
			BusinessException be = (BusinessException) e;
			if (e instanceof BusinessException) {
				respVo.setResultMsg(be.getErrorMessage());
			} else {
				respVo.setResultMsg(e.getMessage());
			}
			LogUtil.error(MODULE, "报错了啦", be);
			respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			return respVo;
		}
		return respVo;
	}
	
	/**
	 * 
	 * wetherBuyOnce:(根基gdstypeid判断是否允许多次购买). <br/> 
	 * 
	 * @author gxq 
	 * @param model
	 * @param gdsTypeId
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/wetherbuyonce")
	@ResponseBody
	public Model wetherBuyOnce(Model model,@RequestParam("gdsTypeId") String gdsTypeId){
	    LongReqDTO gdsTypeQuery = new LongReqDTO();
	    if(StringUtil.isNotBlank(gdsTypeId)){
	        gdsTypeQuery.setId(Long.parseLong(gdsTypeId));
	    }
	    try {
	        GdsTypeRespDTO gdsTypeRespDTO = gdsTypeRSV.queryGdsTypeByPK(gdsTypeQuery);
	        model.addAttribute("ifBuyOnce", gdsTypeRespDTO.getIfBuyonce());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "报错了啦", e);
        }
	    return model;
	}

	
	  /**
     * 根据上传到mongoDB的图片ID 从mongoDB上获取图片路径
     * @param vfsId
     * @param param
     * @return
     * @author gxq
     */
    private String getImageUrl(String vfsId,String param){
        StringBuilder sb=new StringBuilder();
        sb.append(vfsId);
        if(!StringUtil.isBlank(param)){
            sb.append("_");
            sb.append(param);
        }
        return ImageUtil.getImageUrl(sb.toString());
    }
}

