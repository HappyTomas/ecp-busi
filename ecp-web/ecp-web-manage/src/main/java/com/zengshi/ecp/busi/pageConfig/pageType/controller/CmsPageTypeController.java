package com.zengshi.ecp.busi.pageConfig.pageType.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageType.vo.CmsPageTypeVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONArray;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 模板化 模板管理<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/pageType")
public class CmsPageTypeController extends EcpBaseController {
	private String MODUAL = CmsPageTypeController.class.getName();
    private String URL = "/pageConfig/pageType/pageType";//返回页面的基本路径
    @Resource
    private ICmsPageTypeRSV iCmsPageTypeRSV;
    @Resource
    private ICmsLayoutTypeRSV iCmsLayoutTypeRSV;
    @Resource
    private ICmsPageInfoRSV iCmsPageInfoRSV;
    @Resource
    private ICmsModularRSV iCmsModularRSV;
	/**
     * 
     * init:(页面初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/init")
    public String init(Model model) throws Exception{
        return URL+"-grid";
    }
    /**
     * 
     * gridlist:(页面初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridlist(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	CmsPageTypeReqDTO cmsPageTypeReqDTO = cmsPageTypeVO.toBaseInfo(CmsPageTypeReqDTO.class);
    	ObjectCopyUtil.copyObjValue(cmsPageTypeVO, cmsPageTypeReqDTO, null, false);
    	PageResponseDTO<CmsPageTypeRespDTO> pageInfo =iCmsPageTypeRSV.queryCmsPageTypePage(cmsPageTypeReqDTO);    
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * editview:(页面编辑). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/editview")
    public String editView(Model model,CmsPageTypeVO cmsPageTypeVO,String isRead) throws Exception{
    	if(LongUtils.isNotEmpty(cmsPageTypeVO.getId())){
    		CmsPageTypeReqDTO cmsPageTypeReqDTO=new CmsPageTypeReqDTO();
    		ObjectCopyUtil.copyObjValue(cmsPageTypeVO, cmsPageTypeReqDTO, null, false);
    		CmsPageTypeRespDTO respVO=iCmsPageTypeRSV.queryCmsPageType(cmsPageTypeReqDTO);
    	
    		model.addAttribute("respVO", respVO);
    		model.addAttribute("isRead", isRead);
    	}
        return URL+"-editview";
    }
    /**
     * 
     * save:(页面保存修改). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/save")
    @ResponseBody
    public EcpBaseResponseVO save(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
    	   JSONArray arrayTemp = JSONArray.fromObject(cmsPageTypeVO.getFieldStr());
           @SuppressWarnings({ "unchecked" })
           List<CmsLayoutTypeReqDTO> propValueReqDTOs = JSONArray.toList(arrayTemp,CmsLayoutTypeReqDTO.class);
        	CmsPageTypeReqDTO cmsPageTypeReqDTO = cmsPageTypeVO.toBaseInfo(CmsPageTypeReqDTO.class);
          	ObjectCopyUtil.copyObjValue(cmsPageTypeVO, cmsPageTypeReqDTO, null, false);
        	if(StringUtil.isNotEmpty(cmsPageTypeVO.getId())){
        		CmsPageTypeRespDTO  cmsPageTypeRespDTO=iCmsPageTypeRSV.queryCmsPageType(cmsPageTypeReqDTO);
        		ObjectCopyUtil.copyObjValue(cmsPageTypeRespDTO, cmsPageTypeReqDTO, null, false);
        		ObjectCopyUtil.copyObjValue(cmsPageTypeVO, cmsPageTypeReqDTO, null, false);
        		/* 布局类型取值 begin */
        		List<CmsLayoutTypeRespDTO> layoutTypeRespDTOs=cmsPageTypeRespDTO.getLayoutTypeRespDTOList();
        		if(CollectionUtils.isNotEmpty(layoutTypeRespDTOs)){
                	 for(CmsLayoutTypeRespDTO layoutTypeRespDTO:layoutTypeRespDTOs){
                		 if(CollectionUtils.isNotEmpty(propValueReqDTOs)){
	            			 for(int k=0;k<propValueReqDTOs.size();k++){
	            				 CmsLayoutTypeReqDTO cmsLayoutTypeReqDTO=propValueReqDTOs.get(k);
	            				 Long editId=cmsLayoutTypeReqDTO.getId();
	            				 if(editId!=null&&(layoutTypeRespDTO.getId().longValue()==editId.longValue())){
	            					 CmsLayoutTypeReqDTO daoCopy=new CmsLayoutTypeReqDTO();
	            					 ObjectCopyUtil.copyObjValue(layoutTypeRespDTO, daoCopy, null, false);
	            					 ObjectCopyUtil.copyObjValue(cmsLayoutTypeReqDTO, daoCopy, null, false);
	            					 propValueReqDTOs.set(k,daoCopy);
	            				 }
	            			 }
	            			
            			 }
                	 }
        		}
        		cmsPageTypeReqDTO.setLayoutTypeReqDTOList(propValueReqDTOs);
        		/* 布局类型取值 end */
        		iCmsPageTypeRSV.updateCmsPageType(cmsPageTypeReqDTO);
        	}else{
        		if(cmsPageTypeReqDTO.getStatus()==null){
             		cmsPageTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        		}
        		cmsPageTypeReqDTO.setLayoutTypeReqDTOList(propValueReqDTOs);
        		iCmsPageTypeRSV.addCmsPageType(cmsPageTypeReqDTO);
        	}
        	model.addAttribute("respVO", cmsPageTypeVO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("页面类型保存成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "页面类型保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("页面类型保存失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "页面类型保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("页面类型保存失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * samePageTypeName:(是否同名判断). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/samePageTypeName")
    @ResponseBody
    public EcpBaseResponseVO samePageTypeName(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsPageTypeReqDTO req=new CmsPageTypeReqDTO();
        	req.setId(cmsPageTypeVO.getId());
        	req.setPageTypeName(cmsPageTypeVO.getPageTypeName());
        	req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        	boolean isExit=iCmsPageTypeRSV.isExistPageType(req);
    		if(isExit){
    			ecpBaseResponseVO.setResultFlag("no ok");
    		}else{
    			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
    		}
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * isCancle:(可以撤销判断). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/isCancle")
    @ResponseBody
    public EcpBaseResponseVO isCancle(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	if(LongUtils.isNotEmpty(cmsPageTypeVO.getId())){
        		CmsPageInfoReqDTO req=new CmsPageInfoReqDTO();
        		req.setPageTypeId(cmsPageTypeVO.getId());
        		req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        		List<CmsPageInfoRespDTO> reqs=iCmsPageInfoRSV.queryCmsPageInfoList(req);
        	
        		CmsModularReqDTO modularReq=new CmsModularReqDTO();
        		//modularReq.setApplyPageType(String.valueOf(cmsPageTypeVO.getId()));
        		modularReq.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        		
        		List<CmsModularRespDTO> modularList=iCmsModularRSV.queryCmsModularList(modularReq);
        		if(CollectionUtils.isNotEmpty(reqs)|| CollectionUtils.isNotEmpty(modularList)){
        			ecpBaseResponseVO.setResultFlag("not ok"); 
        		}else{
        			ecpBaseResponseVO.setResultMsg(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        		}
        	}
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        }
        return ecpBaseResponseVO;
    }
    
    /**
     * 
     * changeLayoutTypeStatus:(修改布局类型状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private void changeLayoutTypeStatus(CmsPageTypeVO cmsPageTypeVO){
    	CmsLayoutTypeReqDTO layoutReq=new CmsLayoutTypeReqDTO();
		layoutReq.setPageTypeId(cmsPageTypeVO.getId());
		List<CmsLayoutTypeRespDTO> layoutResps=iCmsLayoutTypeRSV.queryCmsLayoutTypeList(layoutReq);
	    if(layoutResps!=null){
	    	for(int i=0;i<layoutResps.size();i++){
	    		CmsLayoutTypeRespDTO layoutResp=layoutResps.get(i);
	    		if(!layoutResp.getStatus().equals(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2)){
	    			ObjectCopyUtil.copyObjValue(layoutResp, layoutReq, null, false);
	    			layoutReq.setStatus(cmsPageTypeVO.getStatus());
	        		iCmsLayoutTypeRSV.updateCmsLayoutType(layoutReq);
	    		}
	    	}
	    }
    }
    /**
     * 
     * changestatus:(页面发布 撤销发布 失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsPageTypeReqDTO cmsPageTypeReqDTO = cmsPageTypeVO.toBaseInfo(CmsPageTypeReqDTO.class);
        	ObjectCopyUtil.copyObjValue(cmsPageTypeVO, cmsPageTypeReqDTO, null, false);
        	if(LongUtils.isNotEmpty(cmsPageTypeVO.getId())){
        		iCmsPageTypeRSV.changeStatusCmsPageType(Long.toString(cmsPageTypeVO.getId()), cmsPageTypeVO.getStatus());
        		/* 页面类型失效时 同时失效布局类型 */
        		if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(cmsPageTypeVO.getStatus())){
        			this.changeLayoutTypeStatus(cmsPageTypeVO);
        		}
        	}
        	model.addAttribute("respVO", cmsPageTypeVO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("ok！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("页面类型发布失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * publish:(页面发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/publish")
    @ResponseBody
    public EcpBaseResponseVO publish(Model model,CmsPageTypeVO cmsPageTypeVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	if(StringUtil.isNotEmpty(cmsPageTypeVO.getId())){
        		iCmsPageTypeRSV.changeStatusCmsPageType(Long.toString(cmsPageTypeVO.getId()), CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);	
        	}
        	model.addAttribute("respVO", cmsPageTypeVO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("页面类型发布成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "页面类型发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("页面类型发布失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "页面类型发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("页面类型发布失败！");
        }
        return ecpBaseResponseVO;
    }
    
}

