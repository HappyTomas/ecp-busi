/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoController.java 
 * Package Name:com.zengshi.ecp.busi.cms.controller 
 * Date:2015-8-14下午2:58:36 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.cms.siteinfo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.siteinfo.vo.CmsSiteInfoVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description:網站信息管理平台前店controller类<br>
 * Date:2015-8-14下午2:58:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/siteinfo")
public class CmsSiteInfoController extends EcpBaseController {

	private static String MODULE = CmsSiteInfoController.class.getName();

	private String URL = "/cms/siteInfo/";// 返回页面的基本路径

	@Resource(name = "cmsSiteInfoRSV")
	private ICmsSiteInfoRSV cmsSiteInfoRSV;
	@Resource(name = "cmsSiteRSV")
	private ICmsSiteRSV cmsSiteRSV;
	
	 /** 
     * grid:(跳往列表页). <br/> 
     * 
     * @author huangxm9 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
    	model.addAttribute("siteList",this.querySiteList(null));
        return URL+"siteInfo-grid";
    }
	/**
	 * infoAdd:(網站信息新增页面初始化). <br/>
	 * 
	 * @author huangxm9
	 * @param model
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/add")
	public String add(Model model,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "开始新增页面初始化");
		try {
		    List<CmsSiteRespDTO> siteList = this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
			model.addAttribute("siteList", siteList);
			
			if(CollectionUtils.isNotEmpty(siteList)){
			    model.addAttribute("topSiteInfoList", this.qryTopSiteInfo(siteList.get(0).getId(), true));
			}
		} catch (Exception e) {
		    LogUtil.error(MODULE, "页面初始化出现异常:",e);
		}

		/* 3.跳转到页面的路径 */
		return URL + "siteInfo-edit";
	}

	/**
	 *  edit:(编辑页面初始化). <br/>
	 * 
	 * @author huangxm9
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/edit")
	public String edit(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "开始页面编辑初始化,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			String[] keySiteInfos = new String[1];
			keySiteInfos[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keySiteInfos);
		}

		/* 1.根据入参调用后场網站信息查询服务 */
		CmsSiteInfoRespDTO cmsSiteInfoRespDTO = qrySiteInfo(Long.parseLong(id), null);
		if(cmsSiteInfoRespDTO == null){
		    String[] keySiteInfos = new String[1];
            keySiteInfos[0] = "找不到网站信息";
            throw new BusinessException("cms.common.param.null.error", keySiteInfos);
		}
		if (StringUtils.isNotBlank(cmsSiteInfoRespDTO.getStaticId())) {
			cmsSiteInfoRespDTO.setStaticUrl(ImageUtil.getStaticDocUrl(cmsSiteInfoRespDTO.getStaticId(), "html"));
		}
		//查询是否有子级信息
		Long parent = cmsSiteInfoRespDTO.getParent();
		String unLimitEdit = "1";//1:不限制     0：限制修改（站点与父级不可修改）    2：异常（限制修改）
		if(null == parent || CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID.equals(parent)){//作为父级，如果存在子级则不允许修改为子级
		    try {
		        if(CollectionUtils.isNotEmpty(qrySubSiteInfo(cmsSiteInfoRespDTO.getId(),cmsSiteInfoRespDTO.getSiteId()))){
		            unLimitEdit = "0";
		        }
		    } catch (Exception e) {
		        LogUtil.error(MODULE, "获取子级网站信息异常:",e);
		        unLimitEdit = "2";
            }
		}
		model.addAttribute("unLimitEdit", unLimitEdit);
		//可修改时才获取网站列表 及 顶级列表
		if("1".equals(unLimitEdit)){
		    try {
	            model.addAttribute("siteList",this.querySiteList(null));
	        } catch (Exception e) {
	            LogUtil.error(MODULE, "查询站点列表异常:",e);
	        }
		    try {
	            List<CmsSiteInfoRespDTO> topSiteInfoList = this.qryTopSiteInfo(cmsSiteInfoRespDTO.getSiteId(), true);
	            //排除自己
	            for(CmsSiteInfoRespDTO topSiteInfo : topSiteInfoList){
	                if(topSiteInfo.getId().equals(cmsSiteInfoRespDTO.getId())){
	                    topSiteInfoList.remove(topSiteInfo);
	                    break;
	                }
	            }
	            model.addAttribute("topSiteInfoList",topSiteInfoList);
	        } catch (Exception e) {
	            LogUtil.error(MODULE, "根据站点获取顶级网站信息异常:",e);
	        }
		}
		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsSiteInfoRespDTO);

		/* 3.跳转到页面的路径 */
		return URL + "siteInfo-edit";
	}
	/**
     *  edit:(受限的编辑页面初始化). <br/>
     * 
     * @author huangxm9
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/limitedit")
    public String limitEdit(Model model, 
            @RequestParam("id") String id,
            @ModelAttribute("searchParams") String searchParams) {
        LogUtil.info(MODULE, "开始页面编辑初始化,入参：{id=" + id + "}");
        if (StringUtils.isBlank(id)) {
            String[] keySiteInfos = new String[1];
            keySiteInfos[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keySiteInfos);
        }

        /* 1.根据入参调用后场網站信息查询服务 */
        CmsSiteInfoRespDTO cmsSiteInfoRespDTO = qrySiteInfo(Long.parseLong(id), null);
        if(cmsSiteInfoRespDTO == null){
            String[] keySiteInfos = new String[1];
            keySiteInfos[0] = "找不到网站信息";
            throw new BusinessException("cms.common.param.null.error", keySiteInfos);
        }
        if (StringUtils.isNotBlank(cmsSiteInfoRespDTO.getStaticId())) {
            cmsSiteInfoRespDTO.setStaticUrl(ImageUtil.getStaticDocUrl(cmsSiteInfoRespDTO.getStaticId(), "html"));
        }
        String unLimitEdit = "0";//1:不限制     0：限制修改（站点与父级不可修改）    2：异常（限制修改）
        model.addAttribute("unLimitEdit", unLimitEdit);
        model.addAttribute("isLimitEditPage", 1);//标记为受限编辑页面
        /* 2.设置页面对象 */
        model.addAttribute("respVO", cmsSiteInfoRespDTO);

        /* 3.跳转到页面的路径 */
        return URL + "siteInfo-edit";
    }

	/**
	 *  save:(保存網站信息方法). <br/>
	 * 
	 * @author huangxm9
	 * @param cmsSiteInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(@Valid CmsSiteInfoVO cmsSiteInfoVO){
		LogUtil.info(MODULE, "进入修改網站信息方法,入参：{vo=" + cmsSiteInfoVO.toString() + "}");
		cmsSiteInfoVO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
		//如果排序为空，则默认赋值为1.
        if(cmsSiteInfoVO.getSortNo() == null){
            cmsSiteInfoVO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            saveCmsSiteInfo(cmsSiteInfoVO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "网站信息保存入库异常",e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
		return respVO;
	}

	/**
	 *  pubSave:(发布網站信息方法). <br/>
	 * 
	 * @author huangxm9
	 * @param cmsSiteInfo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/pubsave")
	@ResponseBody
	public EcpBaseResponseVO pubSave(@Valid CmsSiteInfoVO cmsSiteInfoVO){
		LogUtil.info(MODULE, "进入修改網站信息方法,入参：{vo=" + cmsSiteInfoVO.toString() + "}");
		cmsSiteInfoVO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		//如果排序为空，则默认赋值为1.
        if(cmsSiteInfoVO.getSortNo() == null){
            cmsSiteInfoVO.setSortNo(1);
        }
		try {
		    saveCmsSiteInfo(cmsSiteInfoVO);
		    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "网站信息保存入库异常",e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
		return respVO;
	}
	/**
     *  pubSave:(限制更新網站信息方法). <br/>
     * 
     * @author huangxm9
     * @param cmsSiteInfo
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @RequestMapping(value = "/limitsave")
    @ResponseBody
    public EcpBaseResponseVO limitSave(CmsSiteInfoVO cmsSiteInfoVO){
        LogUtil.info(MODULE, "进入修改網站信息方法,入参：{vo=" + cmsSiteInfoVO.toString() + "}");
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(null == cmsSiteInfoVO.getId()){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }else{
            cmsSiteInfoVO.setStatus(null);
            cmsSiteInfoVO.setSiteId(null);
            cmsSiteInfoVO.setParent(null);
            try {
                saveCmsSiteInfo(cmsSiteInfoVO);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            } catch (Exception e) {
                LogUtil.error(MODULE, "网站信息保存入库异常",e);
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            } 
        }
        return respVO;
    }
	/**
	 * 
	 * saveCmsSiteInfo:(保存入库 调用前请将参数设置好). <br/> 
	 * 
	 * @author zhanbh 
	 * @param cmsSiteInfoVO
	 * @return 
	 * @since JDK 1.6
	 */
	 private CmsSiteInfoRespDTO saveCmsSiteInfo(CmsSiteInfoVO cmsSiteInfoVO)throws Exception {
	    CmsSiteInfoRespDTO respDTO = null;
	    /* 1.保存静态文件到静态文件服务器 */
        if(cmsSiteInfoVO.getStaticId()!=null){
            String staticId = FileUtil.saveFile(cmsSiteInfoVO.getEditorText().getBytes("utf-8"),"cmsSiteInfo", ".html");
            // String SiteInfoUrl = ImageUtil.getStaticDocUrl(staticId, "html");
            cmsSiteInfoVO.setStaticId(staticId);// 静态文件ID
        }
        
        /* 2.封装后场入参 */
        CmsSiteInfoReqDTO dto = new CmsSiteInfoReqDTO();
        ObjectCopyUtil.copyObjValue(cmsSiteInfoVO, dto, "", true);
        /* 3.调用后场網站信息修改服务 */
        if (dto.getId() != null) {// 修改
            respDTO = cmsSiteInfoRSV.updateCmsSiteInfo(dto);
        } else {// 新增
            respDTO =  cmsSiteInfoRSV.addCmsSiteInfo(dto);
        }
        return respDTO;
	 }
	 /** 
     * pubstatus:(发布). <br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubstatus")
    @ResponseBody
    public EcpBaseResponseVO pubstatus(Model model,
            @RequestParam("id")String id) throws Exception{
        LogUtil.info(MODULE,"==========id:"+id+";");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            CmsSiteInfoRespDTO siteInfo = this.qrySiteInfo(Long.parseLong(id), null);
            if(null != siteInfo){
                //子级需判断父级状态  父级为发布 才允许发布
                boolean justDoId = true;
                if(!CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID.equals(siteInfo.getParent())){
                    CmsSiteInfoRespDTO topSiteInfo = this.qrySiteInfo(siteInfo.getParent(), CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    if(null == topSiteInfo){
                        justDoId = false;
                        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                        vo.setResultMsg("该网站信息的父级信息未发布，请先发布父级信息");
                    }
                }
                if(justDoId){
                    cmsSiteInfoRSV.changeStatusCmsSiteInfo(id, CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
                }
            }else{
                vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
                vo.setResultMsg("网站信息不存在，请重试");
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "将网站网站信息设置为发布状态异常",e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg("发布发生异常");
        }
        return vo;
    }
    /**
     * 
     * pubstatus:(撤销). <br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/cancelstatus")
    @ResponseBody
    public EcpBaseResponseVO cancelstatus(Model model,
            @RequestParam("id")String id) throws Exception{
        LogUtil.info(MODULE,"==========id:"+id+";");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            dispointSiteInfo(Long.parseLong(id), CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "撤销异常", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 
     * delstatus:(失效). <br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/delstatus")
    @ResponseBody
    public EcpBaseResponseVO delstatus(Model model,
            @RequestParam("id")String id) throws Exception{
        LogUtil.info(MODULE,"==========id:"+id+";");
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {
            cmsSiteInfoRSV.deleteCmsSiteInfo(id);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "撤销异常", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 
     * dispointSiteInfo:(对网站信息状态进行撤销或者失效,会失效子级信息). <br/> 
     * 
     * @author zhanbh 
     * @param id 
     * @since JDK 1.6
     */
    private void dispointSiteInfo(Long id , String status) throws BusinessException{
        if(!CmsConstants.ParamStatus.CMS_PARAMSTATUS_0.equals(status) && !CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(status)){
            String[] keySiteInfos = new String[1];
            keySiteInfos[0] = "状态参数不对";
            throw new BusinessException("cms.common.param.status.error", keySiteInfos); 
        }
        CmsSiteInfoRespDTO siteInfo = this.qrySiteInfo(id, null);
        if(null == siteInfo){
            String[] keySiteInfos = new String[1];
            keySiteInfos[0] = "找不到网站信息";
            throw new BusinessException("cms.common.param.null.error", keySiteInfos); 
        }
        //父级信息删除其子级信息
        if(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID.equals(siteInfo.getParent())){
            List<CmsSiteInfoRespDTO> subSiteInfoList = this.qrySubSiteInfo(siteInfo.getId(),siteInfo.getSiteId());
            if(CollectionUtils.isNotEmpty(subSiteInfoList)){
                List<String> subIdList = new ArrayList<String>();
                for(CmsSiteInfoRespDTO subSiteInfo : subSiteInfoList){
                    if(null != subSiteInfo.getId()){
                        subIdList.add(subSiteInfo.getId().toString());
                    }
                }
                if(CollectionUtils.isNotEmpty(subIdList)){
                    cmsSiteInfoRSV.changeStatusCmsSiteInfoBatch(subIdList, CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                }
            }
        }
        cmsSiteInfoRSV.changeStatusCmsSiteInfo(id.toString(),status);
    }
	/**
	 * gridList:(分页查询網站信息列表方法). <br/>
	 * 
	 * @author huangxm9
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/gridlist")
	@ResponseBody
	public Model gridList(Model model,
			@ModelAttribute("searchVO") CmsSiteInfoVO searchVO) throws Exception {
		LogUtil.info(MODULE, "进入網站信息分页查询方法,入参：{vo=" + searchVO.toString() + "}");

		/* 1.封装后场入参对象 */
		CmsSiteInfoReqDTO cmsSiteInfoDTO = searchVO.toBaseInfo(CmsSiteInfoReqDTO.class);
		ObjectCopyUtil.copyObjValue(searchVO, cmsSiteInfoDTO, "MODULE", false);

		/* 2.调用后场服务 */
		PageResponseDTO<CmsSiteInfoRespDTO> pageResponseDTO = cmsSiteInfoRSV
				.queryCmsSiteInfoPage(cmsSiteInfoDTO);
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(pageResponseDTO);

		/* 3.返回 */
		return super.addPageToModel(model, respVO);
	}

	/**
	 * view:(網站信息查看页面初始化方法). <br/>
	 * 
	 * @author huangxm9
	 * @param model
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/view")
	public String view(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "进入網站信息查看初始化,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			String[] keySiteInfos = new String[1];
			keySiteInfos[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keySiteInfos);
		}

		/* 1.根据入参调用后场網站信息查询服务 */
		CmsSiteInfoReqDTO cmsSiteInfoDTO = new CmsSiteInfoReqDTO();
		cmsSiteInfoDTO.setId(Long.parseLong(id));
		CmsSiteInfoRespDTO cmsSiteInfoRespDTO = cmsSiteInfoRSV.queryCmsSiteInfo(cmsSiteInfoDTO);
		if (cmsSiteInfoRespDTO != null&& StringUtils.isNotBlank(cmsSiteInfoRespDTO.getStaticId())) {
			cmsSiteInfoRespDTO.setStaticUrl(ImageUtil.getStaticDocUrl(cmsSiteInfoRespDTO.getStaticId(), "html"));
		}

		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsSiteInfoRespDTO);

		/* 4.返回页面路径 */
		return URL + "siteInfo-view";
	}

	/** 
	 * qrySiteInfoList:(查询网站信息列表). <br/> 
	 * 
	 * @author jiangzh 
	 * @param model
	 * @param siteId
	 * @param status
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6 
	 */ 
	@RequestMapping(value="/qrySiteInfoList")
    @ResponseBody
    public List<CmsSiteInfoRespDTO> qrySiteInfoList(Model model,
            @RequestParam(value="siteId",required=false)String siteId,
            @RequestParam(value="status",required=false)String status) throws Exception{
        LogUtil.info(MODULE,"==========siteId:"+siteId+";status:"+status+";");
        
        CmsSiteInfoReqDTO reqDTO = new CmsSiteInfoReqDTO();
        if(StringUtil.isNotEmpty(siteId)){
            reqDTO.setSiteId(Long.valueOf(siteId));
        }
        if(StringUtil.isNotEmpty(status)){
            reqDTO.setStatus(status);
        }
        return cmsSiteInfoRSV.queryCmsSiteInfoList(reqDTO);
    }
	/**
	 * 
	 * qryTopSiteInfo:(根据站点获取顶级网站信息进行响应). <br/> 
	 * 
	 * @author zhanbh 
	 * @param model
	 * @param siteId
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value="/getTopSiteInfo")
    @ResponseBody
	public Map<String,Object> getTopSiteInfo(Model model,@RequestParam(value="siteId",required=true)Long siteId){
	    Map<String,Object> result = new HashMap<String, Object>();
	    if(StringUtil.isEmpty(siteId)){
	        result.put("code", "paramError");
	        return result;
	    }
	    List<CmsSiteInfoRespDTO> respList = null;
	    try {
	        respList = qryTopSiteInfo(siteId, true);
	        result.put("code", "ok");
            result.put("datas", respList);
        } catch (Exception e) {
            LogUtil.error(MODULE, "根据站点获取顶级网站信息异常", e);
            result.put("code", "exception");
        }
	    return result;
	}
	/**
	 * 
	 * qryTopSiteInfo:(根据站点id从数据库获取顶级网站信息,可配置是否包含虚拟根节点). <br/> 
	 * 
	 * @author zhanbh 
	 * @param siteId
	 * @param containRoot 
	 * @since JDK 1.6
	 */
	private List<CmsSiteInfoRespDTO> qryTopSiteInfo(Long siteId,boolean containRoot){
	    List<CmsSiteInfoRespDTO> respList = null;
	    if(null != siteId){
	        respList = cmsSiteInfoRSV.queryTopSiteInfoBySiteId(siteId);
	    }
        if(CollectionUtils.isEmpty(respList)){
            respList = new ArrayList<CmsSiteInfoRespDTO>();
        }
        if(containRoot){
            CmsSiteInfoRespDTO root = new CmsSiteInfoRespDTO();
            root.setId(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_ID);
            root.setSiteInfoName(CmsConstants.SiteInfoRoot.CMS_SITE_INFO_ROOT_NAME);
            respList.add(0, root);
        }
        return respList;
	}
	/**
	 * 
	 * qrySubSiteInfo:(获取子级信息). <br/> 
	 * 
	 * @author zhanbh 
	 * @param id
	 * @return 
	 * @since JDK 1.6
	 */
	private List<CmsSiteInfoRespDTO> qrySubSiteInfo(Long id,Long siteId){
	    List<CmsSiteInfoRespDTO> respList = null;
	    if(null != id){
	        CmsSiteInfoReqDTO reqDto = new CmsSiteInfoReqDTO();
	        reqDto.setParent(id);
	        reqDto.setSiteId(siteId);
	        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
	        respList = cmsSiteInfoRSV.queryCmsSiteInfoList(reqDto);
	    }
	    if(CollectionUtils.isEmpty(respList)){
            respList = new ArrayList<CmsSiteInfoRespDTO>();
        }
	    return respList;
	}
	/**
	 * 
	 * qrySiteInfo:(获取网站信息). <br/> 
	 * 
	 * @author zhanbh 
	 * @param id
	 * @return 
	 * @since JDK 1.6
	 */
	private CmsSiteInfoRespDTO qrySiteInfo(Long id,String status){
	    CmsSiteInfoRespDTO result = null;
	    if(null != id){
	        CmsSiteInfoReqDTO reqDto = new CmsSiteInfoReqDTO();
	        reqDto.setId(id);
	        result = cmsSiteInfoRSV.queryCmsSiteInfo(reqDto);
	        if(null != result && StringUtil.isNotBlank(status)){
	            if(!status.equalsIgnoreCase(result.getStatus())){
	                result = null;
	            }
	        }
	    }
	    return result;
	}
	/**
	 * querySiteList:(获取网站列表). <br/>
	 * 
	 * @author huangxm9
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
	private List<CmsSiteRespDTO> querySiteList(String status) throws Exception {
		CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
		if (StringUtils.isNotBlank(status)) {
			cmsSiteReqDTO.setStatus(status);
		}
		List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
		return cmsSiteRespDTOList;
	}

}
