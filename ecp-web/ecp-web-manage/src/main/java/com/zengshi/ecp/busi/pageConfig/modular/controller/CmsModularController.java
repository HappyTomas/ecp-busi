package com.zengshi.ecp.busi.pageConfig.modular.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.modular.vo.CmsModularVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

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
@RequestMapping(value="/modular")
public class CmsModularController extends EcpBaseController {
	private String MODUAL = CmsModularController.class.getName();
    private String URL = "/pageConfig/modular/modular";//返回页面的基本路径
    @Resource
    private ICmsPageTypeRSV iCmsPageTypeRSV;
    @Resource
    private ICmsLayoutTypeRSV iCmsLayoutTypeRSV;
    @Resource
    private ICmsPageInfoRSV iCmsPageInfoRSV;
    @Resource
    private ICmsModularRSV iCmsModularRSV;
    @Resource
    private ICmsLayoutItemPreRSV iCmsLayoutItemPreRSV;
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
    	CmsPageTypeReqDTO req=new CmsPageTypeReqDTO();
    	List<CmsPageTypeRespDTO> pageTypes=iCmsPageTypeRSV.queryCmsPageTypeList(req);
    	model.addAttribute("pageTypes", pageTypes);
    	model.addAttribute("ee", "ee");
    	
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
    @SuppressWarnings("rawtypes")
	@RequestMapping("/gridlist")
    @ResponseBody
    public Model gridlist(Model model,CmsModularVO cmsModularVO) throws Exception{
    	CmsModularReqDTO req = cmsModularVO.toBaseInfo(CmsModularReqDTO.class);
		ObjectCopyUtil.copyObjValue(cmsModularVO, req, null, false);
    	PageResponseDTO<CmsModularRespDTO> pageInfo =iCmsModularRSV.queryCmsModularPage(req);
    	List<CmsModularRespDTO> resps=pageInfo.getResult();
    	if(CollectionUtils.isNotEmpty(resps)){
    		for(CmsModularRespDTO resp:resps){
    			//resp.setApplyPageType(this.getPageTypes(resp.getApplyPageType()));
    			//调文件服务器，返回图片
                if(StringUtils.isNotBlank(resp.getShowPic())){
                    resp.setShowPic(ImageUtil.getImageUrl(resp.getShowPic()+"_"+"50x50!"));
                }else{
                	resp.setShowPic(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()+"_"+"50x50!"));
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
    public String editView(Model model,CmsModularVO cmsModularVO,String isRead) throws Exception{
    	if(LongUtils.isNotEmpty(cmsModularVO.getId())){
    		CmsModularReqDTO req=new CmsModularReqDTO();
    		ObjectCopyUtil.copyObjValue(cmsModularVO, req, null, false);
    		CmsModularRespDTO respVO=iCmsModularRSV.queryCmsModular(req);
    		model.addAttribute("respVO", respVO);
    		//model.addAttribute("applyPageTypeNames",this.getPageTypes(respVO.getModularComponentRespDTO().getApplyPageType()));
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
    public EcpBaseResponseVO save(Model model,CmsModularVO cmsModularVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
    	CmsModularRespDTO respDTO= null;
        try {
        	CmsModularReqDTO req = cmsModularVO.toBaseInfo(CmsModularReqDTO.class);
        	ObjectCopyUtil.copyObjValue(cmsModularVO, req, null, false);
        	if(StringUtil.isNotEmpty(cmsModularVO.getId())){
        		CmsModularRespDTO resp=iCmsModularRSV.queryCmsModular(req);
        		ObjectCopyUtil.copyObjValue(resp, req, null, false);
        		ObjectCopyUtil.copyObjValue(cmsModularVO, req, null, false);
        		respDTO=iCmsModularRSV.updateCmsModular(req);
        	}else{
        		if(cmsModularVO.getStatus()==null){
        			req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        		}
        		respDTO=iCmsModularRSV.addCmsModular(req);
        	}
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("模块管理保存成功！");
            ecpBaseResponseVO.setResultMsg(respDTO.getId().toString());
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "模块管理保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块管理保存失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "模块管理保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块管理保存失败！");
        }
        return ecpBaseResponseVO;
    }
    private String getPageTypes(String ids){
    	List<CmsPageTypeRespDTO> resps=new ArrayList<CmsPageTypeRespDTO>();
    	String pageTypeNames="";
    	if(StringUtil.isNotEmpty(ids)){
    		String[] idArr=ids.split("\\|");
    		for(String id:idArr){
    			if(StringUtil.isNotEmpty(id)){
    				CmsPageTypeReqDTO req=new CmsPageTypeReqDTO();  
        			req.setId(Long.valueOf(id));
        			CmsPageTypeRespDTO resp=iCmsPageTypeRSV.queryCmsPageType(req);
        			resps.add(resp);
    			}
    		}
    	}
    	if(CollectionUtils.isNotEmpty(resps)){
    		pageTypeNames+="|";
    		for(CmsPageTypeRespDTO resp:resps){
    			pageTypeNames+=resp.getPageTypeName()+"|";
    		}
    	}
    	return pageTypeNames;
    }
    /**
     * 
     * sameModularName:(可以撤销判断). <br/> 
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
    @RequestMapping("/sameModularName")
    @ResponseBody
    public EcpBaseResponseVO sameModularName(Model model,CmsModularVO cmsModularVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularReqDTO req=new CmsModularReqDTO();
    	  	req.setId(cmsModularVO.getId());
        	req.setModularName(cmsModularVO.getModularName());
        	req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        	boolean isExit=iCmsModularRSV.isExistModular(req);
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
    public EcpBaseResponseVO changestatus(Model model,CmsModularVO cmsModularVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularReqDTO req = cmsModularVO.toBaseInfo(CmsModularReqDTO.class);
        	ObjectCopyUtil.copyObjValue(cmsModularVO, req, null, false);
        	if(StringUtil.isNotEmpty(cmsModularVO.getId())){
        		iCmsModularRSV.changeStatusCmsModular(Long.toString(cmsModularVO.getId()), cmsModularVO.getStatus());
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
            ecpBaseResponseVO.setResultMsg("模块管理发布成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "模块管理发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块管理发布失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "模块管理发布失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块管理发布失败！");
        }
        return ecpBaseResponseVO;
    }
    
    /** 选择页面类型弹出框
     * pageTypeView:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/pageTypeView")
    public String pageTypeView(Model model,String applyPageTypeId){
    	CmsPageTypeReqDTO cmsPageTypeReqDTO = new CmsPageTypeReqDTO();
    	cmsPageTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	List<CmsPageTypeRespDTO> resps =iCmsPageTypeRSV.queryCmsPageTypeList(cmsPageTypeReqDTO);  
    	model.addAttribute("respsVO", resps);
    	model.addAttribute("applyPageTypeId", applyPageTypeId);
    	return "/pageConfig/modular/open/modular-pageTypeView";
    }
    
    /** 适用布局项尺寸
     * itemSizeView:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/itemSizeView")
    public String itemSizeView(Model model,String applyItemSize,String applyPageTypeId){
    //	iCmsLayoutTypeRSV.queryCmsLayoutTypeList("");
    	String[] pageTypeIds=applyPageTypeId.split("\\|");
    	List<String> itemSizes=new ArrayList<String>();
        if(StringUtil.isNotEmpty(pageTypeIds)){
    		CmsLayoutTypeReqDTO req=new CmsLayoutTypeReqDTO();
    		req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    		for(String pageTypeId :pageTypeIds){
			  if(StringUtil.isNotEmpty(pageTypeId)){//没有页面类型  则返回空集合
				req.setPageTypeId(Long.valueOf(pageTypeId));
    			List<CmsLayoutTypeRespDTO> resps=iCmsLayoutTypeRSV.queryCmsLayoutTypeList(req);
    			if(CollectionUtils.isNotEmpty(resps)){
				    for(CmsLayoutTypeRespDTO resp : resps){
				    	String itemSize=resp.getLayoutItemSize();
	        			String[] sizes=itemSize.split("\\|");
	        			for(String size:sizes){
	        				if(StringUtil.isNotEmpty(size)&&!itemSizes.contains(size)){
	        					itemSizes.add(size);
	        				}
	        			}
		            }
    			}
			  }
        	}
    	}
         model.addAttribute("itemSizeResp", itemSizes);
    	 model.addAttribute("applyItemSize", applyItemSize);
    	return "/pageConfig/modular/open/modular-itemSizeView";
    } 
    
    /** 选择组件弹出框
     * opencomponent:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/opencomponent")
    public String opencomponent(Model model){
        return "/pageConfig/modular/open/modular-component";
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
    public EcpBaseResponseVO isCancle(Model model,CmsModularVO cmsModularVO) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	if(LongUtils.isNotEmpty(cmsModularVO.getId())){
        		
        		CmsLayoutItemPreReqDTO req=new CmsLayoutItemPreReqDTO();
        		req.setModularId(cmsModularVO.getId());
        		req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        		List<CmsLayoutItemPreRespDTO> reqs=iCmsLayoutItemPreRSV.queryCmsLayoutItemPreList(req);
        		if(CollectionUtils.isNotEmpty(reqs)){
        	        ecpBaseResponseVO.setResultMsg(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        		}else{
        			ecpBaseResponseVO.setResultFlag("not ok");  
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
}

