/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoController.java 
 * Package Name:com.zengshi.ecp.busi.cms.controller 
 * Date:2015-8-14下午2:58:36 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.cms.link.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.zengshi.ecp.busi.cms.link.vo.CmsLinkNode;
import com.zengshi.ecp.busi.cms.link.vo.CmsLinkVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLinkRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLinkRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description:链接管理平台前店controller类<br>
 * Date:2015-8-14下午2:58:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/link")
public class CmsLinkController extends EcpBaseController {

	private static String MODULE = CmsLinkController.class.getName();

	private String URL = "/cms/link/";// 返回页面的基本路径

	@Resource(name = "cmsLinkRSV")
	private ICmsLinkRSV cmsLinkRSV;
	@Resource(name = "cmsSiteRSV")
	private ICmsSiteRSV cmsSiteRSV;
	
	
    @RequestMapping(value="/grid")
    public String init(Model model){
        return URL+"/main-link";
    }
    
    @RequestMapping(value="/loadnodes")
    @ResponseBody
    public String loadNodes(Model model, CmsLinkVO linkVO){
        LogUtil.info(MODULE, "==========linkVO:" + linkVO.toString() + ";");
        
        CmsLinkReqDTO linkReqDTO = new CmsLinkReqDTO();
        ObjectCopyUtil.copyObjValue(linkVO, linkReqDTO, null, false);
        
        linkReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        List<CmsLinkRespDTO> linkResDTOList = cmsLinkRSV.queryCmsLinkList(linkReqDTO);
        
        if(CollectionUtils.isNotEmpty(linkResDTOList))
        {
            List<CmsLinkNode> trees = new ArrayList<CmsLinkNode>(linkResDTOList.size());
            for (CmsLinkRespDTO resDTO:linkResDTOList) 
            {
                CmsLinkNode node = new CmsLinkNode();
                node.setId(String.valueOf(resDTO.getId()));
                node.setName(resDTO.getLinkName());
                node.setpId(String.valueOf(resDTO.getLinkParent()));
                node.setIsParent(false);
                for (CmsLinkRespDTO resDTO2:linkResDTOList){
                	if(node.getId().equals(String.valueOf(resDTO2.getLinkParent()))){
                		node.setIsParent(true);
                		break;
                	}
                } 
                node.setSiteId(String.valueOf(resDTO.getSiteId()));
                trees.add(node);
            }
            String json = JSONObject.toJSONString(trees);
            return json;
        }
        return null;
    }
    
    /** 
     * onenode:(加载栏目树). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param link_id
     * @param siteId
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/onenode")
    @ResponseBody
    public CmsLinkVO onenode(Model model, 
            @RequestParam(value="id",required=true)String link_id){
        LogUtil.info(MODULE, "==========link_id:" + link_id + ";");
        
        CmsLinkVO linkVO = new CmsLinkVO();
        
        CmsLinkReqDTO arg0 = new CmsLinkReqDTO();
        arg0.setId(Long.valueOf(link_id));
        
        CmsLinkRespDTO res = cmsLinkRSV.queryCmsLink(arg0);
        if(res != null){
        	 String vfsUrl = ConstantTool.getImageUrl(res.getMediaUuid(),"120x130!");
        	 ObjectCopyUtil.copyObjValue(res, linkVO, null, false);
        	 linkVO.setVfsUrl(vfsUrl);
        }
        return linkVO;
    }
    /** 
     * save:(保存栏目). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param linkVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/saveNode")
    @ResponseBody
    public CmsLinkNode saveNode(Model model, CmsLinkVO linkVO){
        LogUtil.info(MODULE, "==========linkVO:" + linkVO + ";");
        
        CmsLinkNode node = new CmsLinkNode();
        CmsLinkReqDTO arg0 = new CmsLinkReqDTO();
        ObjectCopyUtil.copyObjValue(linkVO, arg0, null, false);
        if(StringUtil.isEmpty(arg0.getSortNo()))
        {
        	arg0.setSortNo(1);
        }
        if(StringUtil.isEmpty(arg0.getStatus()))
        {
        	arg0.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        }
        if(null==linkVO.getLinkParent()){
        	linkVO.setLinkParent(0L);
        }
        if(linkVO.getId() == null || linkVO.getId() == 0){
            //新增栏目
            CmsLinkRespDTO addLink = cmsLinkRSV.addCmsLink(arg0);
            linkVO.setId(addLink.getId());
            linkVO.setNewCreate(true);
        }else {
            //更新栏目
            cmsLinkRSV.updateCmsLink(arg0);
            linkVO.setNewCreate(false);
        }
        node.setId(String.valueOf(linkVO.getId()));
        node.setName(linkVO.getLinkName());
        node.setpId(String.valueOf(linkVO.getLinkParent()));
        node.setIsParent(false);
        node.setNewCreate(linkVO.isNewCreate());
        
        return node;
    }
    
    /** 
     * deleteNode:(删除节点). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param link_id
     * @return 
     * @since JDK 1.6 
     *
     *@修改：huangxm9
     *@原因:若栏目被删除，则应该将管理的文章管理记录撤销，栏目位置管理关联的记录设置成已失效
     */ 
    @RequestMapping(value="/deleteNode")
    @ResponseBody
    public EcpBaseResponseVO deleteNode(Model model, 
            @RequestParam(value="id",required=true)String link_id){
        LogUtil.info(MODULE, "==========link_id:" + link_id + ";");
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();

        //调用删除函数
        CmsLinkReqDTO arg0 = new CmsLinkReqDTO();
        arg0.setId(Long.valueOf(link_id));
        cmsLinkRSV.deleteCmsLink(link_id);
        res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return res;
    }
 
    
///////////////////////////////////////////////////////改造成树之 前的方法/////////////////////////////////////////////////////////////////    
	 /** 
     * grid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
//    @RequestMapping(value="/grid")  //原有功能入口
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
    	model.addAttribute("siteList",this.querySiteList(null));
        return URL+"link-grid";
    }
    
	/**
	 *链接新增页面初始化 add:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
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
			model.addAttribute("siteList", this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
		} catch (Exception e) {
		    LogUtil.error(MODULE, "页面初始化出现异常:",e);
			e.printStackTrace();
		}

		/* 3.跳转到页面的路径 */
		return URL + "link-edit";
	}

	/**
	 * 编辑页面初始化 LinkEdit:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
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
			String[] keyLinks = new String[1];
			keyLinks[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyLinks);
		}

		/* 1.根据入参调用后场链接查询服务 */
		CmsLinkReqDTO cmsLinkDTO = new CmsLinkReqDTO();
		cmsLinkDTO.setId(Long.parseLong(id));
		CmsLinkRespDTO cmsLinkRespDTO = cmsLinkRSV.queryCmsLink(cmsLinkDTO);
		try {
			model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsLinkRespDTO);

		/* 3.跳转到页面的路径 */
		return URL + "link-edit";
	}

	/**
	 * 保存链接方法 Link save:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @param cmsLink
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/save")
	public EcpBaseResponseVO save(@Valid CmsLinkVO cmsLinkVO)
			throws Exception {
		LogUtil.info(MODULE, "进入save链接方法,入参：{vo=" + cmsLinkVO.toString() + "}");

		
		 //如果排序为空，则默认赋值为1.
        if(cmsLinkVO.getSortNo() == null){
            cmsLinkVO.setSortNo(1);
        }
		/* 2.封装后场入参 */
		CmsLinkReqDTO dto = new CmsLinkReqDTO();
		ObjectCopyUtil.copyObjValue(cmsLinkVO, dto, "", true);
		

		/* 3.调用后场链接修改服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		if (dto.getId() != null) {// 修改
			cmsLinkRSV.updateCmsLink(dto);
		} else {// 新增
			dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);// 新增默认为无效状态
			cmsLinkRSV.addCmsLink(dto);
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 发布链接方法 pubSave:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @param cmsLink
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/pubsave")
	public EcpBaseResponseVO pubSave(@Valid CmsLinkVO cmsLinkVO)
			throws Exception {
		LogUtil.info(MODULE, "进入pubsave接方法,入参：{vo=" + cmsLinkVO.toString() + "}");
		
		 //如果排序为空，则默认赋值为1.
        if(cmsLinkVO.getSortNo() == null){
            cmsLinkVO.setSortNo(1);
        }
		/* 2.封装后场入参 */
		CmsLinkReqDTO dto = new CmsLinkReqDTO();
		ObjectCopyUtil.copyObjValue(cmsLinkVO, dto, "", true);
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 发布默认为有效状态

		/* 3.调用后场链接修改服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		if (dto.getId() != null) {// 修改
			cmsLinkRSV.updateCmsLink(dto);
		} else {// 新增
			cmsLinkRSV.addCmsLink(dto);
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	 /** 
     * changestatus:(生效、失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param ids
     * @param status
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model,@RequestParam("ids")String ids,@RequestParam("status")String status) throws Exception{
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
         if(StringUtils.isBlank(status)){
             String[] keyInfos = new String[1];
             keyInfos[0]="status";
             throw new BusinessException("cms.common.param.null.error",keyInfos); 
         }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            throw new BusinessException("入参ids为空！");
        }
        cmsLinkRSV.changeStatusCmsLinkBatch(list, status);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
	/**
	 *链接删除 LinkDelete:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author huangxm9
	 * @param cmsLink
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/delete")
	public EcpBaseResponseVO delete(@RequestParam("ids") String id)
			throws Exception {
		LogUtil.info(MODULE, "进入删除链接方法,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			String[] keyLinks = new String[1];
			keyLinks[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyLinks);
		}

		/* 1.封装后场入参 */
		CmsLinkReqDTO dto = new CmsLinkReqDTO();
		dto.setId(Long.parseLong(id));

		/* 2.调用后场服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			cmsLinkRSV.deleteCmsLink(id);
		} catch (BusinessException err) {
			throw new BusinessException(err.getErrorMessage());
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 分页查询链接列表方法 gridList:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
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
			@ModelAttribute("searchVO") CmsLinkVO searchVO) throws Exception {
		LogUtil.info(MODULE, "进入链接分页查询方法,入参：{vo=" + searchVO.toString() + "}");

		/* 1.封装后场入参对象 */
		CmsLinkReqDTO cmsLinkDTO = searchVO.toBaseInfo(CmsLinkReqDTO.class);
		ObjectCopyUtil.copyObjValue(searchVO, cmsLinkDTO, "MODULE", false);

		/* 2.调用后场服务 */
		PageResponseDTO<CmsLinkRespDTO> pageResponseDTO = cmsLinkRSV.queryCmsLinkPage(cmsLinkDTO);
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageResponseDTO);

		/* 3.返回 */
		return super.addPageToModel(model, respVO);
	}

	/**
	 *链接查看页面初始化方法 view:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
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
		LogUtil.info(MODULE, "进入链接查看初始化,入参：{id=" + id + "}");
		
		if (StringUtils.isBlank(id)) {
			String[] keyLinks = new String[1];
			keyLinks[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyLinks);
		}

		/* 1.根据入参调用后场链接查询服务 */
		CmsLinkReqDTO cmsLinkDTO = new CmsLinkReqDTO();
		cmsLinkDTO.setId(Long.parseLong(id));
		CmsLinkRespDTO cmsLinkRespDTO = cmsLinkRSV.queryCmsLink(cmsLinkDTO);

		/* 2.设置页面对象 */
		model.addAttribute("respVO", cmsLinkRespDTO);

		/* 4.返回页面路径 */
		return URL + "link-view";
	}

	/**
	 * querySiteList:(获取内容位置列表). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
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
