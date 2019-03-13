package com.zengshi.ecp.busi.im.cust.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.im.cust.vo.CustFaqVO;
import com.zengshi.ecp.busi.im.cust.vo.CustInfoReqVO;
import com.zengshi.ecp.busi.im.cust.vo.CustInfoRespVO;
import com.zengshi.ecp.busi.im.cust.vo.CustServSatisfyReqVO;
import com.zengshi.ecp.busi.im.cust.vo.CustServSatisfyRespVO;
import com.zengshi.ecp.busi.im.history.vo.SessionReqVO;
import com.zengshi.ecp.busi.im.history.vo.SessionRespVO;
import com.zengshi.ecp.busi.im.util.BizUtil;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsArticleRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsChannelResDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsArticleRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsChannelRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImOfuserRelResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IOfuserRSV;
import com.zengshi.ecp.im.dubbo.interfaces.ISatisfyEvaluateRSV;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.util.ConstantTool;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
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
@RequestMapping(value = "/cust")
public class CustChatController extends EcpBaseController {
	private static String MODULE = CustChatController.class.getName();
	
	@Resource
	private ICustManageRSV iCustManageRSV;
	
	@Resource
	private IOfuserRSV iOfuserRSV;
	
	@Resource
	private ICustServiceMgrRSV custServiceMgrRSV;
	
	@Resource
	private IStaffHotlineRSV iStaffHotlineRSV;
	
	@Resource
    private ISatisfyEvaluateRSV satisfyEvaluateRSV;
	
	@Resource
    private ICmsChannelRSV cmsChannelRSV;
	
	@Resource
    private ICmsArticleRSV cmsArticleRSV;
	
	@RequestMapping(value = "/chat/{info}")
	public String chat(Model model, @PathVariable(value="info")String info) throws Exception{
		/*加密后的字符串解码*/
		info = new String(BizUtil.Base64Decode(info),"UTF-8");
		String ap[] = info.split("#");
		String staffCode = ap[0].trim();
		String shopId = ap[1].trim();
		String issueType = ap[2].trim();
		String uuid = "";
		model.addAttribute("issueType", issueType);
		model.addAttribute("boshService", BizUtil.getBOSH());
		CustInfoReqDTO arg0 = new CustInfoReqDTO();
		CustInfoResDTO custInfoResDTO = null;
		if(StringUtil.isNotBlank(staffCode)){
			arg0.setStaffCode(staffCode);
			custInfoResDTO = iCustManageRSV.findCustInfo(arg0);
		}else{
		custInfoResDTO = iCustManageRSV.findCustInfoById(arg0.getStaff().getId());
		}
		
		if(null!=custInfoResDTO){
			ImOfuserRelReqDTO dto = new ImOfuserRelReqDTO();
			dto.setStaffCode(custInfoResDTO.getStaffCode());
			dto.setStaffId(arg0.getStaff().getId());
			ImOfuserRelResDTO imOfuserRelResDTO = iOfuserRSV.findofuserByCust(dto);
			model.addAttribute("uName", StringUtil.isBlank(imOfuserRelResDTO.getOfStaffCode())?"":imOfuserRelResDTO.getOfStaffCode()+BizUtil.getOfServer());
			model.addAttribute("staffCode", custInfoResDTO.getStaffCode());
			model.addAttribute("uNameForLogout", imOfuserRelResDTO.getOfStaffCode());
			model.addAttribute("custLevel", custInfoResDTO.getCustLevelCode());
			/*请求创建session*/
			SessionReqVO reqVO = new SessionReqVO();
			ImCustReqDTO imCustReqDTO = new ImCustReqDTO();
			
			//从队列中获取客服
			imCustReqDTO.setOfStaffCode(imOfuserRelResDTO.getOfStaffCode());
			imCustReqDTO.setBusinessType(Long.parseLong(issueType));
			imCustReqDTO.setShopId(Long.parseLong(shopId));
			imCustReqDTO.setCustLevel(custInfoResDTO.getCustLevelCode());
			if(ap.length>3){
				if(issueType.equals(BizUtil.issueType_1)){
					reqVO.setOrdId(ap[3].trim());
					imCustReqDTO.setBusinessId(ap[3].trim());
				}else if(issueType.equals(BizUtil.issueType_2)){
					reqVO.setGdsId(ap[3].trim());
					imCustReqDTO.setBusinessId(ap[3].trim());
				}
			}
		
/*			ImStaffHotlineResDTO imStaffHotlineResDTO = getStaffHotline(imCustReqDTO);
			//等待人数为0时，获取客服
			if(imStaffHotlineResDTO.getWaitCount()==0&&StringUtil.isNotBlank(imStaffHotlineResDTO.getCsaCode())){
				reqVO.setCsaCode(imStaffHotlineResDTO.getCsaCode());
				reqVO.setIssueType(issueType);
			
				reqVO.setStatus(BizUtil.status_1);
				reqVO.setUserCode(imOfuserRelResDTO.getOfStaffCode());
				reqVO.setSessionTime(DateUtil.getSysDate());
				reqVO.setShopId(Long.parseLong(shopId));
				SessionRespVO respVO = getSession(reqVO);
				if(StringUtil.isBlank(respVO.getId())){
				 uuid = saveSession(reqVO);
				}else{
				 uuid = respVO.getId();
				}
				model.addAttribute("sessionId", uuid);
				model.addAttribute("csaCode", imStaffHotlineResDTO.getCsaCode()+BizUtil.getOfServer());
				model.addAttribute("csaCodeForLogout", imStaffHotlineResDTO.getCsaCode());
				ImStaffHotlineReqDTO dto1 = new ImStaffHotlineReqDTO();
				ImStaffHotlineResDTO hotlineResDTO = iStaffHotlineRSV.getStaffHotline(dto1);
				model.addAttribute("serName", hotlineResDTO.getHotlinePerson());
				BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_CSA_PIC");
				model.addAttribute("servPic", baseSysCfgRespDTO.getParaValue());
				
			}
			//返回等待人数
			model.addAttribute("waitCount", imStaffHotlineResDTO.getWaitCount());*/
			if(StringUtil.isNotBlank(custInfoResDTO.getCustPic())){
				String imageUrl = ImageUtil.getImageUrl(custInfoResDTO.getCustPic()+"_50x50!");
				model.addAttribute("custPic", imageUrl);
			}else{
				BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_USER_PIC");
				String imageUrl = ImageUtil.getImageUrl(baseSysCfgRespDTO.getParaValue()+"_50x50!");
				model.addAttribute("custPic", imageUrl);
			}
		}
		if(ap.length>3){
			String info3 = ap[3].trim();
			if("1".equals(issueType)){
				model.addAttribute("ordId",info3);
			}else if("2".equals(issueType)){
				model.addAttribute("gdsId",info3);
			}
		}
		//目前只有一家店铺
		model.addAttribute("shopId", StringUtil.isBlank(shopId)?"人民卫生出版社":"人民卫生出版社");
		model.addAttribute("shopIdForLogout",shopId);

		
		BaseSysCfgRespDTO baseSysCfgRespDTO = SysCfgUtil.fetchSysCfg("IM_CSA_PIC");
		if(null!=baseSysCfgRespDTO){
			model.addAttribute("servPic", baseSysCfgRespDTO.getParaValue());
		}
		BaseSysCfgRespDTO baseSysCfgRespDTO1 = SysCfgUtil.fetchSysCfg("IM_USER_SESSION_TIME");
		if(null!=baseSysCfgRespDTO1){
		model.addAttribute("sessionTime", baseSysCfgRespDTO1.getParaValue());
		}
		
		/*获取常见问题列表*/
		List<CustFaqVO> resultList = new ArrayList<CustFaqVO>();
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
                	CustFaqVO vo = new CustFaqVO();
                	vo.setArtName(resp.getArticleTitle());//标题
                	vo.setArtId(resp.getId().toString());
                	vo.setArtUrl(CmsCacheUtil.getCmsSiteCache(resp.getSiteId()).getSiteUrl()+"/helpcenter/article/"+resp.getId());
                	resultList.add(vo);
                }
            }
        }
        model.addAttribute("faqList", resultList);
		return "/im/cust/index";
	}
	
	@RequestMapping(value = "/info")
	public EcpBaseResponseVO getInfo(HttpServletRequest request, CustInfoReqVO vo) throws Exception{
		EcpBaseResponseVO custVO = new CustInfoRespVO(); 
		
		return custVO;
	}
	
	/**
	 * 请求创建session
	 * @param respVO
	 * @return
	 */
	public String saveSession(SessionReqVO reqVO){
		String uuid = UUID.randomUUID().toString();
		reqVO.setId(uuid);
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(reqVO);
		MongoUtil.insert("T_IM_SESSION_HISTORY", doc);
		return uuid;
	}
	
	
	/**
	 * 获取session会话
	 * @param reqVO
	 * @return
	 */
	public SessionRespVO getSession(SessionReqVO reqVO){
		SessionRespVO respVO = new SessionRespVO();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder doc = new QueryBuilder();  
		
		doc.and("csaCode").is(reqVO.getCsaCode());
		doc.and("userCode").is(reqVO.getUserCode());
		doc.and("status").is(reqVO.getStatus());
		doc.and("source").is(reqVO.getSource());
		
		DBCursor cursor= collection.find(doc.get());
		try {
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				ConstantTool.dbObjectToBean(obj, respVO);
			}
		} finally {
			cursor.close();
		}
		return respVO;
	}
	
	/**
	 * 每次接入都将之前的session置为失效
	 * @param reqVO
	 * @return
	 */
	public int updateSession(SessionRespVO reqVO){
		try {
			BasicDBObject query = new BasicDBObject();
			query.put("id", reqVO.getId());
			DBObject stuFound = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").findOne(query);
			stuFound.put("status", ImConstants.STATE_0);
			WriteResult result = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").update(query, stuFound);
		/*	if(result.getN()>0){
				ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
				dto.setCsaCode(reqVO.getCsaCode());
				dto.setSessionId(reqVO.getId());
				custServiceMgrRSV.finishChat(dto);
			}*/
			return result.getN();
		} catch (Exception e) {
			throw new BusinessException("会话结束失败");
		}
		
		
	}
	
	public ImStaffHotlineResDTO getStaffHotline(ImCustReqDTO imCustReqDTO){
		ImStaffHotlineResDTO dto = new ImStaffHotlineResDTO();
		try {
			dto =	custServiceMgrRSV.getStaffHotline(imCustReqDTO);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return dto;
	}
	
	
	/**
	 * 排队获取等待人数，为0时接入
	 * @param custInfoReqVO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/getHotlineQueue")
	@ResponseBody
	public Map<String,Object> getHotlineQueue(CustInfoReqVO custInfoReqVO)throws Exception{
		Map<String,Object> map = new HashMap<>();
		ImCustReqDTO imCustReqDTO = new ImCustReqDTO();
		SessionReqVO reqVO = new SessionReqVO();
		imCustReqDTO.setOfStaffCode(custInfoReqVO.getOfStaffCode());
		imCustReqDTO.setBusinessType(custInfoReqVO.getBusinessType());
		imCustReqDTO.setShopId(custInfoReqVO.getShopId());
		imCustReqDTO.setCustLevel(custInfoReqVO.getCustLevelCode());
	    String issueType = String.valueOf(custInfoReqVO.getBusinessType());
		if(issueType.equals(BizUtil.issueType_1)){
			reqVO.setOrdId(custInfoReqVO.getOrderId());
			imCustReqDTO.setBusinessId(custInfoReqVO.getOrderId());
		}else if(issueType.equals(BizUtil.issueType_2)){
			reqVO.setGdsId(custInfoReqVO.getGoodsId());
			imCustReqDTO.setBusinessId(custInfoReqVO.getGoodsId());
		}
		ImStaffHotlineResDTO imStaffHotlineResDTO = getStaffHotline(imCustReqDTO);
		if(imStaffHotlineResDTO.getWaitCount()==0&&StringUtil.isNotBlank(imStaffHotlineResDTO.getCsaCode())){
			ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
			dto.setOfStaffCode(imStaffHotlineResDTO.getCsaCode());
			ImStaffHotlineResDTO hotlineResDTO = iStaffHotlineRSV.getStaffHotline(dto);
			map.put("serName", hotlineResDTO.getHotlinePerson());
			String csaCode = imStaffHotlineResDTO.getCsaCode();
			map.put("csaCode", csaCode);
			map.put("shopId", hotlineResDTO.getShopId());
			map.put("ofserver", BizUtil.getOfServer());
			map.put("waitCount", imStaffHotlineResDTO.getWaitCount());
		        String uuid="";
				reqVO.setCsaCode(imStaffHotlineResDTO.getCsaCode());
				reqVO.setIssueType(issueType);
				reqVO.setStatus(BizUtil.status_1);
				reqVO.setUserCode(custInfoReqVO.getOfStaffCode());
				reqVO.setSessionTime(DateUtil.getSysDate());
				reqVO.setShopId(custInfoReqVO.getShopId());
				reqVO.setSource("WEB");
				/*SessionRespVO respVO = getSession(reqVO);
				if(StringUtil.isBlank(respVO.getId())){
				 uuid = saveSession(reqVO);
				}else{
				 uuid = respVO.getId();
				}*/
				SessionRespVO respVO = getSession(reqVO);
				if(StringUtil.isNotBlank(respVO.getId())){
					updateSession(respVO);
				}
				
				uuid = saveSession(reqVO);
				map.put("sessionId", uuid);
				
		}else{
			map.put("waitCount", imStaffHotlineResDTO.getWaitCount());
		}
		
		return map;
		
	}
	
	/**
     * 
     * saveEvaluate:(保存客户评价). <br/>  
     * 
     * @author panjs 
     * @param saveEvaluate
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveEvaluate")
    @ResponseBody
    public CustServSatisfyRespVO saveEvaluate(CustServSatisfyReqVO vo){
        CustServSatisfyRespVO respVo = new CustServSatisfyRespVO();
        CustServSatisfyReqDTO reqDto = new CustServSatisfyReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false); 
        try{
            CustServSatisfyResDTO respDto = satisfyEvaluateRSV.addSatisfyEvaluate(reqDto);
            ObjectCopyUtil.copyObjValue(respDto, respVo, null, false);
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(Exception e){
            LogUtil.error(MODULE, "提交评价出错");
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg(e.getMessage());
        }
        return respVo;
    } 
	
    /**
     * 
     * evaluateCheck:(查询客户是否已评价). <br/>  
     * 
     * @author panjs 
     * @param evaluateCheck
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/evaluateCheck")
    @ResponseBody
    public CustServSatisfyRespVO evaluateCheck(CustServSatisfyReqVO vo){
        CustServSatisfyRespVO respVo = new CustServSatisfyRespVO();
        CustServSatisfyReqDTO reqDto = new CustServSatisfyReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false); 
        CustServSatisfyResDTO respDto = satisfyEvaluateRSV.qrySatisfyEvaluate(reqDto);
       ObjectCopyUtil.copyObjValue(respDto, respVo, null, false);
        if(StringUtil.isEmpty(respDto)){
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }else{
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            respVo.setResultMsg("您已提交评价~");
        }
        return respVo;
         
    }
    
    /**
     * 
     * findCustStatus:(通过OF用户，查询用户是否已接入客服). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxl5 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/chat/findCustStatus")
    @ResponseBody
    public EcpBaseResponseVO findCustStatus(String custCode){
    	EcpBaseResponseVO resp = new EcpBaseResponseVO();
    	//2017-3-22 先注释掉，队列那边不支持查询是哪个端接入的，以至于出现WEB端接入了，APP端无法接入
//    	ImCustReqDTO custReqDTO = new ImCustReqDTO();
//		custReqDTO.setOfStaffCode("user_" + custReqDTO.getStaff().getStaffCode());
//		boolean isCustJoinIn = custServiceMgrRSV.isCustJoinIn(custReqDTO);
//		if (isCustJoinIn) {
//			resp.setResultFlag("fail");
//			resp.setResultMsg("您好，您已经接入了客服，无法进行重复接入。");
//		} else {
//			resp.setResultFlag("ok");
//		}
		resp.setResultFlag("ok");
        return resp;
         
    }
    
}
