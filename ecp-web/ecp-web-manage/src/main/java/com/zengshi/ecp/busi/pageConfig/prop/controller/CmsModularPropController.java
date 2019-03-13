package com.zengshi.ecp.busi.pageConfig.prop.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.modular.vo.CmsModularParaPropVO;
import com.zengshi.ecp.busi.pageConfig.modular.vo.CmsModularVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropValRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropValRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
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
@RequestMapping(value="/prop")
public class CmsModularPropController extends EcpBaseController {
	private String MODUAL = CmsModularPropController.class.getName();
    private String URL = "/pageConfig/prop/prop";//返回页面的基本路径
    @Resource
    private ICmsModularRSV iCmsModularRSV;
    @Resource
    private ICmsModularParaPropRSV iCmsModularParaPropRSV;
    @Resource
    private ICmsModularPropValRSV iCmsModularPropValRSV;
  
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
    public String init(Model model,String modularId,String isValid) throws Exception{
    	model.addAttribute("modularId", modularId);
    	model.addAttribute("isValid", isValid);
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
    public Model gridlist(Model model,CmsModularParaPropVO cmsModularParaPropVO) throws Exception{
    	CmsModularParaPropReqDTO req = cmsModularParaPropVO.toBaseInfo(CmsModularParaPropReqDTO.class);
		ObjectCopyUtil.copyObjValue(cmsModularParaPropVO, req, null, false);
		PageResponseDTO<CmsModularParaPropRespDTO> pageInfo =iCmsModularParaPropRSV.queryCmsModularParaPropPage(req);
    	List<CmsModularParaPropRespDTO> resps=pageInfo.getResult();
    	if(CollectionUtils.isNotEmpty(resps)){
    		for(CmsModularParaPropRespDTO resp:resps){
    			CmsModularParaPropReqDTO controlPropReq=new CmsModularParaPropReqDTO();
    			if(LongUtils.isNotEmpty(resp.getControlPropId())){
    				controlPropReq.setId(resp.getControlPropId());
        			CmsModularParaPropRespDTO controlPropResp=iCmsModularParaPropRSV.queryCmsModularParaProp(controlPropReq);
        			resp.setControlPropName(controlPropResp.getPropName());
    			}
    			
    		}
    	}
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
    public String editView(Model model,CmsModularParaPropVO cmsModularParaPropVO,String isRead,String isValid,
            @ModelAttribute("searchParams") String searchParams,
            @ModelAttribute("searchModularParams") String searchModularParams) throws Exception{
    	boolean edit=false;
    	if(LongUtils.isNotEmpty(cmsModularParaPropVO.getId())){
    		CmsModularParaPropReqDTO req=new CmsModularParaPropReqDTO();
    		ObjectCopyUtil.copyObjValue(cmsModularParaPropVO, req, null, false);
    		CmsModularParaPropRespDTO respVO=iCmsModularParaPropRSV.queryCmsModularParaProp(req);
    		model.addAttribute("promVO", respVO);
    		model.addAttribute("isRead", isRead);
    		edit=true;
    	}else{
    		cmsModularParaPropVO.setSortNo(1);
    		model.addAttribute("promVO", cmsModularParaPropVO);
    	}
    	/* 获得受控属性 begin */
    	CmsModularParaPropReqDTO req2=new CmsModularParaPropReqDTO();
    	req2.setModularId(cmsModularParaPropVO.getModularId());
    	req2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	List<CmsModularParaPropRespDTO> controlProps=iCmsModularParaPropRSV.queryCmsModularParaPropList(req2);
    	//去除自身属性
		if(CollectionUtils.isNotEmpty(controlProps)){
			for(int i=0;i<controlProps.size();i++){
    			CmsModularParaPropRespDTO prop=controlProps.get(i);
    			if(edit&&(cmsModularParaPropVO.getId().longValue()==prop.getId().longValue())){
    				controlProps.remove(i);
    			}
    		}
			model.addAttribute("controlProps", controlProps);
		}
		/* 获得受控属性 end */
		model.addAttribute("isValid", isValid);
		
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
    public EcpBaseResponseVO save(Model model,CmsModularParaPropVO cmsModularParaPropVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
     	    JSONArray arrayTemp = JSONArray.fromObject(cmsModularParaPropVO.getPropStr());
            @SuppressWarnings({ "unchecked" })
            List<CmsModularPropValReqDTO> propValueReqDTOs = JSONArray.toList(arrayTemp,
            		CmsModularPropValReqDTO.class);
        	CmsModularParaPropReqDTO req = cmsModularParaPropVO.toBaseInfo(CmsModularParaPropReqDTO.class);
           
        	ObjectCopyUtil.copyObjValue(cmsModularParaPropVO, req, null, false);
        	if(LongUtils.isNotEmpty(cmsModularParaPropVO.getId())){
        		CmsModularParaPropRespDTO  cmsPageTypeRespDTO=iCmsModularParaPropRSV.queryCmsModularParaProp(req);
        		ObjectCopyUtil.copyObjValue(cmsPageTypeRespDTO, req, null, false);
        		ObjectCopyUtil.copyObjValue(cmsModularParaPropVO, req, null, false);
        		//CmsModularPropValReqDTO varRep=new CmsModularPropValReqDTO();
        		//查询数据
        		List<CmsModularPropValRespDTO> modularParaPropResps=cmsPageTypeRespDTO.getModularPropValRespDTOList();
        		
        		if(CollectionUtils.isNotEmpty(modularParaPropResps)){
                	 for(CmsModularPropValRespDTO propResp:modularParaPropResps){
            			 for(int k=0;k<propValueReqDTOs.size();k++){
            				 CmsModularPropValReqDTO propReq=propValueReqDTOs.get(k);
            				 Long editId=propReq.getId();
            				 if(editId!=null&&(propResp.getId().longValue()==editId.longValue())){
            					 CmsModularPropValReqDTO daoCopy=new CmsModularPropValReqDTO();
            					 ObjectCopyUtil.copyObjValue(propResp, daoCopy, null, false);
            					 ObjectCopyUtil.copyObjValue(propReq, daoCopy, null, false);
            					 propValueReqDTOs.set(k,daoCopy);
            				 }
            			 }
                	 }
        		}
        	    //设置值
        		 req.setModularPropValReqDTOList(propValueReqDTOs);
        		 if(null==cmsModularParaPropVO.getControlPropId()){
        			 req.setControlPropId(null);
        		 }
        		 CmsModularParaPropRespDTO a= iCmsModularParaPropRSV.updateCmsModularParaProp(req);
        		 System.out.println(a.getControlPropId());
        	}else{
        	    if(StringUtil.isEmpty(req.getStatus())){
        	    	req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        	    }
        	    if(CollectionUtils.isNotEmpty(propValueReqDTOs)){
        	      req.setModularPropValReqDTOList(propValueReqDTOs);
        	    }
        	   
        		iCmsModularParaPropRSV.addCmsModularParaProp(req);
        	}
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("模块属性保存成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "模块属性保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块属性保存失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "模块属性保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块属性保存失败！");
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
    public EcpBaseResponseVO isCancle(Model model,CmsModularParaPropVO cmsModularParaPropVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularParaPropReqDTO req=new CmsModularParaPropReqDTO();
			req.setControlPropId(cmsModularParaPropVO.getId());
			Set<String> statusSet=new HashSet<String>();
			statusSet.add(ParamStatus.CMS_PARAMSTATUS_0);
			statusSet.add(ParamStatus.CMS_PARAMSTATUS_1);
			req.setStatusSet(statusSet);
			List<CmsModularParaPropRespDTO> paraPropResps=iCmsModularParaPropRSV.queryCmsModularParaPropList(req);
			if(CollectionUtils.isNotEmpty(paraPropResps)|| CollectionUtils.isNotEmpty(paraPropResps)){
    			ecpBaseResponseVO.setResultFlag("not ok"); 
    		}else{
    			ecpBaseResponseVO.setResultMsg(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
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
    public EcpBaseResponseVO changestatus(Model model,CmsModularParaPropVO cmsModularParaPropVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularParaPropReqDTO req = cmsModularParaPropVO.toBaseInfo(CmsModularParaPropReqDTO.class);
        	ObjectCopyUtil.copyObjValue(cmsModularParaPropVO, req, null, false);
        	if(LongUtils.isNotEmpty(cmsModularParaPropVO.getId())){
        		iCmsModularParaPropRSV.changeStatusCmsModularParaProp(Long.toString(cmsModularParaPropVO.getId()), cmsModularParaPropVO.getStatus());
        		/* 属性配置使失效begin */
        		if(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equals(cmsModularParaPropVO.getStatus())){
        			CmsModularPropValReqDTO propValReq=new CmsModularPropValReqDTO();
        			propValReq.setPropId(cmsModularParaPropVO.getId());
        			List<CmsModularPropValRespDTO> propValResps=iCmsModularPropValRSV.queryCmsModularPropValList(propValReq);
        		    if(CollectionUtils.isNotEmpty(propValResps)){
        		    	for(CmsModularPropValRespDTO propValResp:propValResps){
        		    		if(!propValResp.getStatus().equals(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2)){
        		    			ObjectCopyUtil.copyObjValue(propValResp, propValReq, null, false);
        		    			propValReq.setStatus(cmsModularParaPropVO.getStatus());
        		    			iCmsModularPropValRSV.updateCmsModularPropVal(propValReq);
        		    		}
        		    	}
        		    }
        		}
        		/* 属性配置使失效end */
        	}
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("ok！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("操作失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "操作失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块属性发布失败！");
        }
        return ecpBaseResponseVO;
    }
    
    /**
     * 
     * samePropName:(判断是否同名). <br/> 
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
    @RequestMapping("/samePropName")
    @ResponseBody
    public EcpBaseResponseVO samePropName(Model model,CmsModularParaPropVO cmsModularParaPropVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularParaPropReqDTO req=new CmsModularParaPropReqDTO();
    	  
        	//应该设置id
        	req.setModularId(cmsModularParaPropVO.getModularId());
        	req.setId(cmsModularParaPropVO.getId());
    	  	req.setPropName(cmsModularParaPropVO.getPropName());
        	req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        	boolean isExit=iCmsModularParaPropRSV.isExistModularParaProp(req);
    		if(isExit){
    			ecpBaseResponseVO.setResultFlag("no ok");
    		}else{
    			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
    		}
          //  ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);  
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
    public EcpBaseResponseVO publish(Model model,CmsModularVO cmsModularVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	if(StringUtil.isNotEmpty(cmsModularVO.getId())){
        		iCmsModularRSV.changeStatusCmsModular(Long.toString(cmsModularVO.getId()), cmsModularVO.getStatus());
        	}
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("模块属性发布成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "模块属性发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块属性发布失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "模块属性发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块属性发布失败！");
        }
        return ecpBaseResponseVO;
    }
    
}

