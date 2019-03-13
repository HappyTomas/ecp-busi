package com.zengshi.ecp.busi.cms.list.controller;

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
import com.zengshi.ecp.busi.cms.list.vo.CmsListVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsListReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsListRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsListRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description:组件管理 <br>
 * Date:2015.09.14
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxm9
 * @version  
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value="/list")
public class CmsListController extends EcpBaseController {
    
    private static String MODULE = CmsListController.class.getName();
    
    private String URL = "/cms/list/list";//返回页面的基本路径 
    private String URL_OPEN = "/cms/list/open/list";//返回页面的弹出窗路径
    
    @Resource(name="cmsListRSV")
    private ICmsListRSV cmsListRSV;
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    /** 
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping()
    public String init(){
        return URL+"-init";
    }
    
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
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception {
        return URL + "-grid";
    }
   
    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/add")
    public String add(Model model,
            @RequestParam(value="listClass",required=false) String listClass,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        CmsListRespDTO respDTO = null;
        if(StringUtil.isNotEmpty(listClass)){
            respDTO = new CmsListRespDTO();
            respDTO.setListClass(listClass);
        }
        model.addAttribute("respVO",respDTO);
        return URL+"-edit";
    }

    /** 
     * edit:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/edit")
    public String edit(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        CmsListReqDTO reqDTO = new CmsListReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsListRespDTO cmsListRespDTO = cmsListRSV.queryCmsList(reqDTO);
            if(cmsListRespDTO.getGdsId() != null){
          	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
          	gdsInfoReqDTO.setId(cmsListRespDTO.getGdsId());
          	GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
              if(gdsInfoRespDTO != null){
            	  cmsListRespDTO.setGdsName(gdsInfoRespDTO.getGdsName());
              } 
          }
            model.addAttribute("respVO",cmsListRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        return URL+"-edit";
    }
    
    
    /** 
     * view:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * 2015.10.16 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/view")
    public String view(Model model,
            @RequestParam("id")String id,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        if(StringUtils.isBlank(id)){
            String[] keyInfos = new String[1];
            keyInfos[0]="id";
           throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        
        CmsListReqDTO reqDTO = new CmsListReqDTO();
        reqDTO.setId(Long.valueOf(id));
        try {
            CmsListRespDTO cmsListRespDTO = cmsListRSV.queryCmsList(reqDTO);
            if(cmsListRespDTO.getGdsId() != null){
          	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
          	gdsInfoReqDTO.setId(cmsListRespDTO.getGdsId());
          	GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
              if(gdsInfoRespDTO != null){
            	  cmsListRespDTO.setGdsName(gdsInfoRespDTO.getGdsName());
              } 
          }
            model.addAttribute("respVO",cmsListRespDTO);
        } catch (BusinessException e) {
            // TODO: handle exception
            throw new BusinessException(e.getErrorCode());
        }
        return URL+"-view";
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
        cmsListRSV.changeStatusCmsListBatch(list, status);
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    /** 
     * delete:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/delete")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("ids")String ids) throws Exception{
        
        LogUtil.info(MODULE,"==========ids:"+ids+";");
        if(StringUtils.isBlank(ids)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.common.param.null.error",keyInfos); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if(CollectionUtils.isEmpty(list)){
            String[] keyInfos = new String[1];
            keyInfos[0]="ids";
            throw new BusinessException("cms.list.param.null.error",keyInfos); 
        }
        cmsListRSV.deleteCmsListBatch(list);
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }

    /** 
     * save:(新增/编辑 发布保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsListVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public EcpBaseResponseVO pubsave(@Valid CmsListVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsListReqDTO reqDTO = new CmsListReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(reqDTO.getId() != null){
            cmsListRSV.updateCmsList(reqDTO);
        }else{
            cmsListRSV.addCmsList(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    /** 
     * save:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param cmsListVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsListVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsListReqDTO reqDTO = new CmsListReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if(VO.getId() != null){
            cmsListRSV.updateCmsList(reqDTO);
        }else{
            cmsListRSV.addCmsList(reqDTO);
        }
        respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return respVO;
    }
    
    /** 
     * gridList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author huangxm9 
     * @param model
     * @param cmsListSearchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsListVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsListReqDTO reqDTO = searchVO.toBaseInfo(CmsListReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsListRespDTO> pageInfo = cmsListRSV.queryCmsListPage(reqDTO);
        
        //3. 调文件服务器，返回商品信息
        List<CmsListRespDTO> respList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(respList)){
            for(CmsListRespDTO dto:respList){
                if(dto.getGdsId() != null){
                	GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
                	gdsInfoReqDTO.setId(dto.getGdsId());
                	GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfo(gdsInfoReqDTO);
                    if(gdsInfoRespDTO != null){
                        dto.setGdsName(gdsInfoRespDTO.getGdsName());
                    } 
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
   
    /** 选择商品内容弹出框
     * opengds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/opengds")
    public String opengds(){
        return URL_OPEN + "-gds";
    }
    
    /** 
     * querygds:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param ids
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/querygds")
    public Model querygds(Model model, @ModelAttribute("searchVO") GdsManageVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = new GdsInfoReqDTO();
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);//已上架
        
        PageResponseDTO<GdsInfoRespDTO> pageInfo = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
     
}
