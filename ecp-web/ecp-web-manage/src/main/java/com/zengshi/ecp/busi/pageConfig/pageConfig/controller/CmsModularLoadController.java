/**
 * CmsParamsRenderController.java	  V1.0   2016年4月26日 上午11:13:16
 *
 * Copyright 2016 AsiaInfoData Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPubRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPubRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;

/**
 * 功能描述：模块基础信息处理
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
@RequestMapping(value="/modular-load")
public class CmsModularLoadController extends EcpBaseController {
	
	@Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
	
	@Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
	
	@Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
	
	@Resource(name = "cmsFloorRSV")
	private ICmsFloorRSV cmsFloorRSV;
	
	@Resource(name="cmsPicHotPreRSV")
	private ICmsPicHotPreRSV cmsPicHotPreRSV;
	
	@Resource(name="cmsPicHotPubRSV")
	private ICmsPicHotPubRSV cmsPicHotPubRSV;
	
	/**
	 * 
	 * 功能描述：初始化方法 
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年4月26日 下午8:19:34</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/init")
	public String init(Model model) throws Exception{
		return "";
	}
	
	/**
	 * 
	 * 功能描述：楼层导航设置
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年4月26日 上午11:15:30</p>
	 *
	 * @param model
	 * @param pageId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/floorNavInit")
    public String floorNavInit(Model model, Long pageId,Long modularId,Long itemId) throws Exception{
		//读取楼层信息，用于在动态添加的导航中，与楼层相关联
    	CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
    	pinfo.setId(pageId);
    	List<CmsLayoutPreRespDTO> pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo);
    	model.addAttribute("pageConfig", pageConfig);
    	model.addAttribute("pageId", pageId);
    	model.addAttribute("modularId", modularId);
    	model.addAttribute("itemId", itemId);
		return "/pageConfig/pageConfig/edit/floornav/floor-nav";
	}
	
	/**
	 * 
	 * 功能描述：宝贝图片管理
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年4月28日 下午7:38:24</p>
	 *
	 * @param model
	 * @param pageId
	 * @param modularId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/goodsPics")
	public String goodsPics(Model model, Long pageId,Long modularId,Long itemId) throws Exception{
		//读取楼层信息，用于在动态添加的导航中，与楼层相关联
    	CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
    	pinfo.setId(pageId);
    	List<CmsLayoutPreRespDTO> pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo);
    	model.addAttribute("pageConfig", pageConfig);
		model.addAttribute("pageId", pageId);
		model.addAttribute("modularId", modularId);
		model.addAttribute("itemId", itemId);
		return "/pageConfig/pageConfig/edit/goodspics/goods-pics";
	}
	/**
	 * 
	 * 功能描述：热点弹窗
	 *
	 * @author  yedw
	 * <p>创建日期 ：2016年4月28日 下午7:38:24</p>
	 *
	 * @param model
	 * @param pageId
	 * @param modularId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/hotImg")
	public String hotImg(Model model, Long pageId,String picId,Long itemPropId,Long id,String relative_coord) throws Exception{
		CmsPicHotPreReqDTO req=new CmsPicHotPreReqDTO();
		req.setPageId(pageId);
		req.setPicId(picId);
		req.setItemPropId(itemPropId);
		CmsPicHotPreRespDTO resp=new CmsPicHotPreRespDTO();
		if(null!=id){
			req.setId(id);
			resp=cmsPicHotPreRSV.queryCmsPicHotPre(req);
		}
		model.addAttribute("pageId", pageId);
		model.addAttribute("picId", picId);
		model.addAttribute("itemPropId", itemPropId);
		model.addAttribute("relative_coord", relative_coord);
		model.addAttribute("resp", resp);
		return "/pageConfig/pageConfig/edit/hotImg/hotImg-dialog";
	}
	/**
	 * 
	 * 功能描述：热点初始化，预览
	 *
	 * @author  yedw
	 * <p>创建日期 ：2016年4月28日 下午7:38:24</p>
	 *
	 * @param model
	 * @param pageId
	 * @param modularId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/loadHotImg")
    public Model loadHotImg(Model model,CmsPicHotPreReqDTO req) throws Exception{
		Set<String> statusSet =new HashSet<String>();
		statusSet.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
		statusSet.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		req.setStatusSet(statusSet);
    	List<CmsPicHotPreRespDTO> list =cmsPicHotPreRSV.queryCmsPicHotPreList(req);
    	model.addAttribute("hotImgList", list);
    	return model;
    }
	/**
	 * 
	 * 功能描述：热点初始化，发布
	 *
	 * @author  yedw
	 * <p>创建日期 ：2016年4月28日 下午7:38:24</p>
	 *
	 * @param model
	 * @param pageId
	 * @param modularId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/loadHotImgPub")
	public Model loadHotImgPub(Model model,CmsPicHotPubReqDTO req) throws Exception{
		Set<String> statusSet =new HashSet<String>();
		statusSet.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
		statusSet.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		req.setStatusSet(statusSet);
		List<CmsPicHotPubRespDTO> list =cmsPicHotPubRSV.queryCmsPicHotPubList(req);
		model.addAttribute("hotImgList", list);
		return model;
	}
	
	/**
	 * 
	 * 功能描述：加载楼层选择器页面
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月7日 下午5:12:23</p>
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/floorLayerSelecter")
	public String floorLayerSelecter(Model model) throws Exception{
		return "/pageConfig/pageConfig/edit/goodspics/floor-grid";
	}
	
	/**
	 * 
	 * 功能描述：楼层选择器表格
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月7日 下午4:32:54</p>
	 *
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/floorLayerLoad")
	@ResponseBody
	public Model floorLayerLoad(Model model, EcpBasePageReqVO vo) throws Exception {
		CmsFloorReqDTO req = vo.toBaseInfo(CmsFloorReqDTO.class);
		req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		req.setTemplateId(new Long(14));
		req.setSiteId(new Long(1));
		PageResponseDTO<CmsFloorRespDTO> list = cmsFloorRSV.queryCmsFloorPage(req);

		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(list);

		return super.addPageToModel(model, respVO);
	}
}
