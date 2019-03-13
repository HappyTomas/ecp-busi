package com.zengshi.ecp.busi.pageConfig.modular.controller;

import java.util.ArrayList;
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
import com.zengshi.ecp.busi.pageConfig.modular.vo.CmsModularComponentVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularComponentRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 模板化 模块管理 组件配置<br>
 * Date:2016年3月28日下午3:59:40  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/modularComponent")
public class CmsModularComponentController extends EcpBaseController {
	private String MODUAL = CmsModularComponentController.class.getName();
    private String URL = "/pageConfig/modular/modularComponent/modularComponent";//返回页面的基本路径
    @Resource
    private ICmsModularRSV iCmsModularRSV;
    @Resource
    private ICmsModularComponentRSV iCmsModularComponentRSV;
    @Resource
    private ICmsPageTypeRSV iCmsPageTypeRSV;
  
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
    	CmsModularReqDTO cmsModularReqDTO=new CmsModularReqDTO();
    	cmsModularReqDTO.setId(Long.valueOf(modularId));
    	model.addAttribute("modularName", iCmsModularRSV.queryCmsModular(cmsModularReqDTO).getModularName());
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
    public Model gridlist(Model model,CmsModularComponentVO vo) throws Exception{
    	CmsModularComponentReqDTO req = vo.toBaseInfo(CmsModularComponentReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, req, null, false);
    	PageResponseDTO<CmsModularComponentRespDTO> pageInfo =iCmsModularComponentRSV.queryCmsModularComponentPage(req);
    	/*List<CmsModularComponentRespDTO> resps=pageInfo.getResult();
    	if(CollectionUtils.isNotEmpty(resps)){
    		for(CmsModularComponentRespDTO resp:resps){
    			resp.setApplyPageType(this.getPageTypes(resp.getApplyPageType()));
    			//调文件服务器，返回图片
                if(StringUtils.isNotBlank(resp.getShowPic())){
                    resp.setShowPic(ImageUtil.getImageUrl(resp.getShowPic()+"_"+"50x50!"));
                }else{
                	resp.setShowPic(ImageUtil.getImageUrl(ImageUtil.getDefaultImageId()+"_"+"50x50!"));
                }
    		}
    	}*/
  
    	EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
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
    		//pageTypeNames+="|";
    		for(CmsPageTypeRespDTO resp:resps){
    			pageTypeNames+=resp.getPageTypeName();//+"|"
    		}
    	}
    	return pageTypeNames;
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
    public String editView(Model model,CmsModularComponentVO vo,String isRead) throws Exception{
    	CmsModularComponentReqDTO req=new CmsModularComponentReqDTO();
    	ObjectCopyUtil.copyObjValue(vo, req, null, false);
    	CmsModularComponentRespDTO respVO=new CmsModularComponentRespDTO();
    	if(LongUtils.isNotEmpty(vo.getId())){
    		respVO=iCmsModularComponentRSV.queryCmsModularComponent(req);
    		model.addAttribute("isRead", isRead);
    	}else{
    		respVO.setModularId(vo.getModularId());
    	}
    	model.addAttribute("respVO", respVO);
    	//模块
    	CmsModularReqDTO cmsModularReqDTO=new CmsModularReqDTO();
    	cmsModularReqDTO.setId(vo.getModularId());
    	CmsModularRespDTO modularRespDTO=iCmsModularRSV.queryCmsModular(cmsModularReqDTO);
    	
    	//页面类型
    	CmsPageTypeReqDTO cmsPageTypeReqDTO=new CmsPageTypeReqDTO();
    	cmsPageTypeReqDTO.setPlatformType(modularRespDTO.getPlatformType());
    	cmsPageTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	List<CmsPageTypeRespDTO> allPageTypeList=iCmsPageTypeRSV.queryCmsPageTypeList(cmsPageTypeReqDTO);
    	
    	CmsModularComponentReqDTO selectedModularComponent=new CmsModularComponentReqDTO();
    	selectedModularComponent.setModularId(vo.getModularId());
    	selectedModularComponent.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    	List<CmsModularComponentRespDTO> selectedList =iCmsModularComponentRSV.queryCmsModularComponentList(selectedModularComponent);
    	List<CmsPageTypeRespDTO> pageTypeList=new ArrayList<>();
    	for (CmsPageTypeRespDTO typeRespDTO: allPageTypeList) {
    		boolean isSelect=false;
			for (int j = 0; j < selectedList.size(); j++) {
				if(typeRespDTO.getId().equals(Long.parseLong(selectedList.get(j).getApplyPageType()))){
					if(LongUtils.isNotEmpty(vo.getId())&&selectedList.get(j).getId().equals(vo.getId())){
						continue;
					}else{
						isSelect=true;
					}
				}
			}
			if(!isSelect){
				pageTypeList.add(typeRespDTO);
			}
		}
    	model.addAttribute("pageTypeList", pageTypeList);
    	
    	
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
    public EcpBaseResponseVO save(Model model,CmsModularComponentVO vo) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
    	CmsModularComponentRespDTO respDTO= null;
        try {
        	CmsModularComponentReqDTO req = vo.toBaseInfo(CmsModularComponentReqDTO.class);
        	ObjectCopyUtil.copyObjValue(vo, req, null, false);
        	CmsModularComponentReqDTO selectedModularComponent=new CmsModularComponentReqDTO();
        	selectedModularComponent.setModularId(vo.getModularId());
        	selectedModularComponent.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        	selectedModularComponent.setApplyPageType(vo.getApplyPageType());
        	List<CmsModularComponentRespDTO> selectedList =iCmsModularComponentRSV.queryCmsModularComponentList(selectedModularComponent);
        	
        	if(StringUtil.isNotEmpty(vo.getId())){
        		if(selectedList!=null &&selectedList.size()>0){
	        		for (int i = 0; i < selectedList.size(); i++) {
						if(selectedList.get(i).getId().equals(vo.getId())){
							selectedList.remove(i);
						}
					}
        		}
        		if(selectedList!=null && selectedList.size()>0){
        			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    ecpBaseResponseVO.setResultMsg("该页面类型的模块组件已存在，请勿重复创建！");
                    return ecpBaseResponseVO;
        		}
        		
        		CmsModularComponentRespDTO resp=iCmsModularComponentRSV.queryCmsModularComponent(req);
        		ObjectCopyUtil.copyObjValue(resp, req, null, false);
        		ObjectCopyUtil.copyObjValue(vo, req, null, false);
        		respDTO=iCmsModularComponentRSV.updateCmsModularComponent(req);
        	}else{
        		if(selectedList!=null && selectedList.size()>0){
        			ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    ecpBaseResponseVO.setResultMsg("该页面类型的模块组件已存在，请勿重复创建！");
                    return ecpBaseResponseVO;
        		}
        		if(vo.getStatus()==null){
        			req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        		}
        		respDTO=iCmsModularComponentRSV.addCmsModularComponent(req);
        	}
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("模块组件管理保存成功！");
            ecpBaseResponseVO.setResultMsg(respDTO.getId().toString());
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "模块组件管理保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块组件管理保存失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "模块组件管理保存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("模块组件管理保存失败！");
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
    public EcpBaseResponseVO changestatus(Model model,CmsModularComponentVO vo) throws Exception{
    	EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	CmsModularComponentReqDTO req = vo.toBaseInfo(CmsModularComponentReqDTO.class);
        	ObjectCopyUtil.copyObjValue(vo, req, null, false);
        	if(StringUtil.isNotEmpty(vo.getId())){
        		iCmsModularComponentRSV.changeStatusCmsModularComponent(Long.toString(vo.getId()), vo.getStatus());
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
}

