/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoController.java 
 * Package Name:com.zengshi.ecp.busi.cms.controller 
 * Date:2015-8-14下午2:58:36 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.cms.floortemplate.controller;

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
import com.zengshi.ecp.busi.cms.floortemplate.vo.CmsFloorTemplateVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage Maven Webapp <br>
 * Description:楼层模板管理平台前店controller类<br>
 * Date:2015-8-14下午2:58:36 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/floortemplate")
public class CmsFloorTemplateController extends EcpBaseController {

	private static String MODULE = CmsFloorTemplateController.class.getName();

	private String URL = "/cms/floortemplate/";// 返回页面的基本路径

	@Resource(name = "cmsFloorTemplateRSV")
	private ICmsFloorTemplateRSV cmsFloorTemplateRSV;
	@Resource(name = "cmsSiteRSV")
	private ICmsSiteRSV cmsSiteRSV;
	@Resource(name = "cmsAppFloorRSV")
    private ICmsAppFloorRSV cmsAppFloorRSV;
	
	 /** 
     * grid:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        return URL+"floortemplate-grid";
    }
    
	/**
	 *楼层模板新增页面初始化 add:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param model
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/add")
	public String add(Model model,
	        @ModelAttribute("searchParams") String searchParams) {
		return URL + "floortemplate-edit";
	}

	/**
	 * 编辑页面初始化 FloortemplateEdit:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/edit")
	public String edit(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "开始页面编辑初始化,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			String[] keyFloortemplates = new String[1];
			keyFloortemplates[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyFloortemplates);
		}

		/* 1.根据入参调用后场楼层模板查询服务 */
		CmsFloorTemplateRespDTO floortemplateRespDTO = new CmsFloorTemplateRespDTO();
		CmsFloorTemplateReqDTO cmsFloortemplateDTO = new CmsFloorTemplateReqDTO();
		cmsFloortemplateDTO.setId(Long.parseLong(id));
		try {
		    floortemplateRespDTO = cmsFloorTemplateRSV.queryCmsFloorTemplate(cmsFloortemplateDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/* 2.设置页面对象 */
		model.addAttribute("respVO", floortemplateRespDTO);
		if(floortemplateRespDTO != null){
		     model.addAttribute("floorPlaceRespDTOList", floortemplateRespDTO.getFloorPlaceRespDTOList());
		}
		/* 3.跳转到页面的路径 */
		return URL + "floortemplate-edit";
	}
	
    /** 
     * 判断该模板是否已被使用（楼层非删除状态）. <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param id
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/isexistfloor")
    @ResponseBody
    public EcpBaseResponseVO isExistFloor(Model model, 
            @RequestParam(value="id",required=true)String id){
        LogUtil.info(MODULE, "==========id:" + id + ";");
        
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        List<CmsAppFloorRespDTO> list = null;
        CmsAppFloorReqDTO appFloorReqDTO = new CmsAppFloorReqDTO();
        appFloorReqDTO.setFloorTemplateId(Long.valueOf(id));
        appFloorReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        //先查已发布楼层
        list = cmsAppFloorRSV.queryCmsAppFloorList(appFloorReqDTO);
        if(list != null && list.size()>0){
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            return res;
        }
        
        //再查未发布楼层
        appFloorReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
        list = cmsAppFloorRSV.queryCmsAppFloorList(appFloorReqDTO);
        if(list != null && list.size()>0){
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }else{
            res.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }
        return res;
    }

	/**
	 * 保存楼层模板方法 Floortemplate save:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param cmsFloortemplate
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(@Valid CmsFloorTemplateVO cmsFloorTemplateVO)
			throws Exception {
		LogUtil.info(MODULE, "进入save楼层模板方法,入参：{vo=" + cmsFloorTemplateVO.toString() + "}");
		
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		//先判断楼层编码是否存在
		if(StringUtil.isEmpty(cmsFloorTemplateVO.getTemplateNo())){
		    respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
		    respVO.setResultMsg("请填写楼层编码！");
		    return respVO;
		} 
		if(this.isExistTemplateCode(cmsFloorTemplateVO.getId(),cmsFloorTemplateVO.getTemplateNo())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
            respVO.setResultMsg("楼层编码已存在，请更改编码或删除原有模板！");
            return respVO;
        }
		 //如果排序为空，则默认赋值为1.
        if(cmsFloorTemplateVO.getSortNo() == null){
            cmsFloorTemplateVO.setSortNo(1);
        }
		/* 2.封装后场入参 */
		CmsFloorTemplateReqDTO dto = new CmsFloorTemplateReqDTO();
		ObjectCopyUtil.copyObjValue(cmsFloorTemplateVO, dto, "", true);
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);// 新增默认为无效状态

		/* 3.调用后场楼层模板修改服务 */
		if (dto.getId() != null) {// 修改
			cmsFloorTemplateRSV.updateCmsFloorTemplate(dto);
		} else {// 新增
			cmsFloorTemplateRSV.addCmsFloorTemplate(dto);
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 发布楼层模板方法 pubSave:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param cmsFloortemplate
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/pubsave")
	@ResponseBody
	public EcpBaseResponseVO pubSave(@Valid CmsFloorTemplateVO cmsFloorTemplateVO)
			throws Exception {
		LogUtil.info(MODULE, "进入pubsave接方法,入参：{vo=" + cmsFloorTemplateVO.toString() + "}");
		
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		//先判断楼层编码是否存在
        if(StringUtil.isEmpty(cmsFloorTemplateVO.getTemplateNo())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
            respVO.setResultMsg("请填写楼层编码！");
            return respVO;
        } 
        if(this.isExistTemplateCode(cmsFloorTemplateVO.getId(),cmsFloorTemplateVO.getTemplateNo())){
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
            respVO.setResultMsg("楼层编码已存在，请更改编码或删除原有模板！");
            return respVO;
        }
		 //如果排序为空，则默认赋值为1.
        if(cmsFloorTemplateVO.getSortNo() == null){
            cmsFloorTemplateVO.setSortNo(1);
        }
		/* 2.封装后场入参 */
		CmsFloorTemplateReqDTO dto = new CmsFloorTemplateReqDTO();
		ObjectCopyUtil.copyObjValue(cmsFloorTemplateVO, dto, "", true);
		dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 发布默认为有效状态

		/* 3.调用后场楼层模板修改服务 */
		if (dto.getId() != null) {// 修改
			cmsFloorTemplateRSV.updateCmsFloorTemplate(dto);
		} else {// 新增
			cmsFloorTemplateRSV.addCmsFloorTemplate(dto);
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
     * @author jiangzh 
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
        cmsFloorTemplateRSV.changeStatusCmsFloorTemplateBatch(list, status);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
	/**
	 *楼层模板删除 FloortemplateDelete:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param cmsFloortemplate
	 * @return
	 * @throws Exception
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/delete")
	public EcpBaseResponseVO delete(@RequestParam("ids") String id)
			throws Exception {
		LogUtil.info(MODULE, "进入删除楼层模板方法,入参：{id=" + id + "}");
		if (StringUtils.isBlank(id)) {
			String[] keyFloortemplates = new String[1];
			keyFloortemplates[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyFloortemplates);
		}

		/* 1.封装后场入参 */
		CmsFloorTemplateReqDTO dto = new CmsFloorTemplateReqDTO();
		dto.setId(Long.parseLong(id));

		/* 2.调用后场服务 */
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		try {
			cmsFloorTemplateRSV.deleteCmsFloorTemplate(id);
		} catch (BusinessException err) {
			throw new BusinessException(err.getErrorMessage());
		}
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return respVO;
	}

	/**
	 * 分页查询楼层模板列表方法 gridList:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
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
			@ModelAttribute("searchVO") CmsFloorTemplateVO searchVO) throws Exception {
		LogUtil.info(MODULE, "进入楼层模板分页查询方法,入参：{vo=" + searchVO.toString() + "}");

		/* 1.封装后场入参对象 */
		CmsFloorTemplateReqDTO cmsFloortemplateDTO = searchVO.toBaseInfo(CmsFloorTemplateReqDTO.class);
		ObjectCopyUtil.copyObjValue(searchVO, cmsFloortemplateDTO, "MODULE", false);

		/* 2.调用后场服务 */
		PageResponseDTO<CmsFloorTemplateRespDTO> pageInfo = cmsFloorTemplateRSV.queryCmsFloorTemplatePage(cmsFloortemplateDTO);
		
		//3. 调文件服务器，返回图片，调用店铺，返回店铺信息
        List<CmsFloorTemplateRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsFloorTemplateRespDTO dto:respList){
                //3.1调文件服务器，返回图片
                if(StringUtils.isNotBlank(dto.getVfsId())){
                    dto.setVfsUrl(ImageUtil.getImageUrl(dto.getVfsId()+""));
                }
            }
        }

        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
		/* 3.返回 */
		return super.addPageToModel(model, respVO);
	}

	/**
	 *楼层模板查看页面初始化方法 view:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author jiangzh
	 * @param model
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/view")
	public String view(Model model, 
	        @RequestParam("id") String id,
	        @ModelAttribute("searchParams") String searchParams) {
		LogUtil.info(MODULE, "进入楼层模板查看初始化,入参：{id=" + id + "}");
		
		if (StringUtils.isBlank(id)) {
			String[] keyFloortemplates = new String[1];
			keyFloortemplates[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyFloortemplates);
		}

		/* 1.根据入参调用后场楼层模板查询服务 */
		CmsFloorTemplateReqDTO cmsFloortemplateDTO = new CmsFloorTemplateReqDTO();
		cmsFloortemplateDTO.setId(Long.parseLong(id));
		CmsFloorTemplateRespDTO floortemplateRespDTO = cmsFloorTemplateRSV.queryCmsFloorTemplate(cmsFloortemplateDTO);

		/* 2.设置页面对象 */
        model.addAttribute("respVO", floortemplateRespDTO);
        if(floortemplateRespDTO != null){
             model.addAttribute("floorPlaceRespDTOList", floortemplateRespDTO.getFloorPlaceRespDTOList());
        }

		/* 4.返回页面路径 */
		return URL + "floortemplate-view";
	}
	/**
	 * 
	 * isExistTemplateCode:(判断是否有存在楼层模板编码). <br/> 
	 * TODO(这里描述这个方法适用条件 – 可选).<br/> 
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
	 * 
	 * @author zhanbh 
	 * @param long1 
	 * @param templateCode
	 * @return
	 * @throws Exception 
	 * @since JDK 1.6
	 */
	private boolean isExistTemplateCode(Long id, Short templateCode) throws Exception{
        boolean isExist = false;
        if(StringUtil.isNotEmpty(templateCode)){
            CmsFloorTemplateReqDTO reqDTO = new CmsFloorTemplateReqDTO();
            reqDTO.setTemplateNo(templateCode);
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
            List<CmsFloorTemplateRespDTO> floorTemplateList = null;
            floorTemplateList = this.cmsFloorTemplateRSV.queryCmsFloorTemplateList(reqDTO);
            
            CmsFloorTemplateRespDTO rusultDto = null;
            if(CollectionUtils.isNotEmpty(floorTemplateList)){
                rusultDto = floorTemplateList.get(0);
                isExist = true;
            }else{
                reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1); 
                floorTemplateList = this.cmsFloorTemplateRSV.queryCmsFloorTemplateList(reqDTO);
                if(CollectionUtils.isNotEmpty(floorTemplateList)){
                    rusultDto = floorTemplateList.get(0);
                    isExist = true;
                }
            }
            
            if(id!=null && rusultDto!=null && rusultDto.getId() != null && rusultDto.getId().longValue() == id.longValue()){
                isExist = false;
            }
            
        }
        return isExist;
    }
	
	/**
	 * syncList:(全量加载树节点). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author yedw
	 * @return
	 * @throws Exception
	 * @since JDK 1.6
	 */
    @RequestMapping(value="/syncList")
    @ResponseBody
    public List<CmsFloorTemplateRespDTO> syncList(CmsFloorTemplateVO vo){
        // 1.封装后场入参对象
    	CmsFloorTemplateReqDTO reqDto = vo.toBaseInfo(CmsFloorTemplateReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, reqDto, "MODULE", false);
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        // 2.调用后场服务
        List<CmsFloorTemplateRespDTO> list=cmsFloorTemplateRSV.queryCmsFloorTemplateList(reqDto);
        return list;
    }
    /**
	 * 楼层模板化配置页面 wap端使用:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author yedw
	 * @param model
	 * @param id
	 * @return
	 * @since JDK 1.7
	 */
	@RequestMapping(value = "/goFloorContent")
	public String goFloorContent(Model model, 
	        @RequestParam("id") String id,Map<String,CmsItemPropPreRespDTO> propPre ,Map<Integer,CmsModularParaPropRespDTO> attrs) {
		LogUtil.info(MODULE, "进入楼层模板查看初始化,入参：{id=" + id + "}");
		
		if (StringUtils.isBlank(id)) {
			String[] keyFloortemplates = new String[1];
			keyFloortemplates[0] = "id";
			throw new BusinessException("cms.common.param.null.error", keyFloortemplates);
		}

		/* 1.根据入参调用后场楼层模板查询服务 */
		CmsFloorTemplateReqDTO cmsFloortemplateDTO = new CmsFloorTemplateReqDTO();
		cmsFloortemplateDTO.setId(Long.parseLong(id));
		CmsFloorTemplateRespDTO floortemplateRespDTO = cmsFloorTemplateRSV.queryCmsFloorTemplate(cmsFloortemplateDTO);

		/* 2.设置页面对象 */
        model.addAttribute("respVO", floortemplateRespDTO);
        if(floortemplateRespDTO != null){
             model.addAttribute("floorPlaceRespDTOList", floortemplateRespDTO.getFloorPlaceRespDTOList());
        }

		/* 4.返回页面路径 */
		return  "/pageConfig/pageConfigWap/modular/wap/floor/floorContent-"+id;
	}
}
