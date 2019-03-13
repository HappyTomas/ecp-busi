package com.zengshi.ecp.busi.im.serv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.history.vo.SessionReqVO;
import com.zengshi.ecp.busi.im.onlinestatus.vo.OnlineReqVO;
import com.zengshi.ecp.busi.im.serv.vo.FaqVO;
import com.zengshi.ecp.busi.im.serv.vo.HotlineInfoReqVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsUtil;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2016年8月4日上午11:30:47  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/serv")
public class ServChatController extends EcpBaseController {
	private static String MODULE = ServChatController.class.getName();
	@Resource
	private IStaffHotlineRSV iStaffHotlineRSV;
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	@Resource
	private ICustManageRSV iCustManageRSV;
	
	@Resource
	private IShopInfoRSV iShopInfoRSV;
	
	@Resource
    private ICmsChannelRSV cmsChannelRSV;
	
	@Resource
    private ICmsArticleRSV cmsArticleRSV;
	
	/**
	 * 客服初始化-wangbh
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/chat")
	public String chat(Model model) throws Exception{
		//获得客服基本信息
		ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
		dto.setStaffCode(dto.getStaff().getStaffCode());
		ImStaffHotlineResDTO hotlineResDTO = iStaffHotlineRSV.getStaffHotline(dto);
		model.addAttribute("boshService", BizUtil.getBOSH());

		model.addAttribute("ofStaffCode",hotlineResDTO.getOfStaffCode()+BizUtil.getOfServer());
		model.addAttribute("staffCode", dto.getStaff().getStaffCode());
		model.addAttribute("shopId", hotlineResDTO.getShopId());
		model.addAttribute("hotlinePerson", hotlineResDTO.getHotlinePerson());
		model.addAttribute("moduleType", hotlineResDTO.getModuleType());
		String orderEdit=hotlineResDTO.getOrderEdit();
		if(StringUtil.isEmpty(orderEdit)){
			orderEdit="0";
		}
		model.addAttribute("orderEdit",orderEdit); //是否可编辑订单
		ShopInfoResDTO shopInfoResDTO = iShopInfoRSV.findShopInfoByShopID(hotlineResDTO.getShopId());
		model.addAttribute("shopInfo", shopInfoResDTO);
	/*	ImStaffHotlineReqDTO dto2 = new ImStaffHotlineReqDTO();
		dto2.setCsaCode(hotlineResDTO.getOfStaffCode());
		dto2.setShopId(hotlineResDTO.getShopId());
		custServiceMgrRSV.staffLogin(dto2);*/
		
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_CSA_PIC");
		model.addAttribute("servPic", baseSysCfgRespDTO.getParaValue());
		
		/*获取常见问题列表*/
		List<FaqVO> resultList = new ArrayList<FaqVO>();
        PageResponseDTO<CmsArticleRespDTO> resultPage = null;
        //栏目
        CmsChannelReqDTO channelReqDTO = new CmsChannelReqDTO();
        channelReqDTO.setSiteId(channelReqDTO.getCurrentSiteId());
        channelReqDTO.setChannelType("05");
        channelReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        channelReqDTO.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
        List<CmsChannelResDTO> channelList=null;
        try {
            channelList = cmsChannelRSV.listChannel(channelReqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询常见问题列表异常：查询帮助中心栏目异常", e);
            throw e;
        }
        List<Long> channelIds = new ArrayList<Long>();
        if(null != channelList && channelList.size() > 0){
            for (CmsChannelResDTO channelResDTO: channelList) {
            	if ("常见问题".equals(channelResDTO.getChannelName())) {
            		channelIds.add(channelResDTO.getId());
            		break;
            	}
            }
            //文章
            CmsArticleReqDTO articleReqDTO = new CmsArticleReqDTO();
            articleReqDTO.setSiteId(channelReqDTO.getCurrentSiteId());
            articleReqDTO.setChannelIds(channelIds);
            articleReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            articleReqDTO.setThisTime(DateUtil.getSysDate());
            articleReqDTO.setPageNo(1);
            articleReqDTO.setPageSize(100);
            try {
                resultPage = cmsArticleRSV.queryCmsArticlePage(articleReqDTO);
            } catch (Exception e) {
                LogUtil.error(MODULE, "查询常见问题列表异常：查询帮助中心文章异常", e);
                throw e;
            }
            if(null != resultPage && CollectionUtils.isNotEmpty(resultPage.getResult())){
                for (CmsArticleRespDTO resp : resultPage.getResult()) {
                	FaqVO vo = new FaqVO();
                	vo.setArtName(resp.getArticleTitle());//标题
                	vo.setArtId(resp.getId().toString());
                	vo.setArtUrl(CmsCacheUtil.getCmsSiteCache(resp.getSiteId()).getSiteUrl()+"/helpcenter/article/"+resp.getId());
                	resultList.add(vo);
                }
            }
        }
        model.addAttribute("faqList", resultList);
		return "/im/serv/index";
	}
	
	/**
	 * 客服登出
	 * @param reqVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public EcpBaseResponseVO logout(OnlineReqVO onlineReqDTO)throws Exception{
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
		try {
			dto.setCsaCode(onlineReqDTO.getCsaCode());
			custServiceMgrRSV.staffLogout(dto);
			/*2017-02-14 下面的逻辑，移到custServiceMgrRSV.staffLogout(dto)，这个rsv里面去了*/
//			BasicDBObject query = new BasicDBObject();
//			query.put("csaCode", onlineReqDTO.getCsaCode());
//			DBObject stuFound = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").findOne(query);
//			stuFound.put("onlineStatus", onlineReqDTO.getOnlineStatus());
//			stuFound.put("updateTime", DateUtil.getSysDate());
//			WriteResult result = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").update(query, stuFound);
//
//			if (result.getN() > 0) {
//				JSONObject doc = new JSONObject();
//				onlineReqDTO.setCreateTime(DateUtil.getSysDate());
//				onlineReqDTO.setUpdateTime(DateUtil.getSysDate());
//				Map map = stuFound.toMap();
//				onlineReqDTO.setHotlineId((Long) map.get("hotlineId"));
//				doc = (JSONObject) JSON.toJSON(onlineReqDTO);
//				MongoUtil.insert("T_IM_HOTLINE_ONLINE_LOG", doc);
//
//			}
			
			vo.setResultFlag("ok");
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		return vo;
		
	}
	
	
	/**
	 * 客服登录
	 * @param reqVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginQueue")
	@ResponseBody
	public EcpBaseResponseVO loginQueue(HotlineInfoReqVO hotlineInfoReqVO)throws Exception{
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		ImStaffHotlineReqDTO dto2 = new ImStaffHotlineReqDTO();
		dto2.setCsaCode(hotlineInfoReqVO.getCsaCode());
		dto2.setShopId(hotlineInfoReqVO.getShopId());
		custServiceMgrRSV.staffLogin(dto2);
		OnlineReqVO onlineReqDTO = new OnlineReqVO();
		BasicDBObject query = new BasicDBObject();
		query.put("csaCode", hotlineInfoReqVO.getCsaCode());
		DBObject stuFound = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").findOne(query);
		stuFound.put("onlineStatus", ImConstants.ONLINE);
		stuFound.put("updateTime", DateUtil.getSysDate());
		WriteResult result = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").update(query, stuFound);
		onlineReqDTO.setOnlineStatus(String.valueOf(ImConstants.ONLINE));
		onlineReqDTO.setCsaCode(hotlineInfoReqVO.getCsaCode());
		if (result.getN() > 0) {
			JSONObject doc = new JSONObject();
			onlineReqDTO.setCreateTime(DateUtil.getSysDate());
			onlineReqDTO.setUpdateTime(DateUtil.getSysDate());
			Map map = stuFound.toMap();
			onlineReqDTO.setHotlineId((Long) map.get("hotlineId"));
			doc = (JSONObject) JSON.toJSON(onlineReqDTO);
			MongoUtil.insert("T_IM_HOTLINE_ONLINE_LOG", doc);

		}
		
		vo.setResultFlag("ok");
		return vo;
	}
	
	
	
	public EcpBaseResponseVO staffFlash(SessionReqVO reqVO)throws Exception{
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		return vo;
	}
	
	
}
