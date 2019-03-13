package com.zengshi.ecp.busi.cms.floor.controller;

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
import com.zengshi.ecp.busi.cms.floor.vo.CmsFloorVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTabRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
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
 * Description: <br>
 * Date:2015年8月17日下午6:54:49  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/floor")
public class CmsFloorController extends EcpBaseController {
    
    private static String MODULE = CmsFloorController.class.getName();
    private String URL = "/cms/floor/floor";//返回页面的基本路径 
    private Long PLACEID = 1703L;//客户端模板中多楼层位置
    
    @Resource(name="cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name="cmsPlaceRSV")
    private ICmsPlaceRSV cmsPlaceRSV;
    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource(name="cmsTemplateRSV")
    private ICmsTemplateRSV cmsTemplateRSV;
    @Resource(name="cmsFloorTabRSV")
    private ICmsFloorTabRSV cmsFloorTabRSV;
    @Resource(name = "gdsCategoryRSV")
    private IGdsCategoryRSV gdsCategoryRSV;

    /** 
     * init:页面初始化，requestMapping如果不写的话，访问地址同：Controller类的 requestmapping的URL
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
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
     * @author jiangzh 
     * @return 
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/grid")
    public String grid(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        return URL+"-grid";
    }
   
    /** 
     * add:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param tab
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/add")
    public String add(Model model,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        return URL+"-edit";
    }

    /** 
     * view:(楼层查看). <br/> 
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
    @RequestMapping(value = "/view")
    public String view(Model model, 
            @RequestParam("id") String id,
            @ModelAttribute("searchParams") String searchParams) {
        LogUtil.info(MODULE, "进入站点查看初始化,入参：{id=" + id + "}");
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
        }

        /* 1.根据入参调用后场页面信息查询服务 */
        CmsFloorReqDTO cmsFloorDTO = new CmsFloorReqDTO();
        cmsFloorDTO.setId(Long.parseLong(id));
        CmsFloorRespDTO respVO = cmsFloorRSV.queryCmsFloor(cmsFloorDTO);
        //1.1根据关联商品分类id查出相应的名称
        if (respVO != null  && StringUtil.isNotBlank(respVO.getCatgCode())) {
            GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
            GdsCategoryRespDTO gdsCategoryRespDTO = new GdsCategoryRespDTO();
            gdsCategoryReqDTO.setCatgCode(respVO.getCatgCode());
            gdsCategoryRespDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryReqDTO);
            if (gdsCategoryRespDTO != null)
                respVO.setCatgCodeZH(gdsCategoryRespDTO.getCatgName());
        }else{
//        	throw new BusinessException("cms.common.param.null.error", new String[]{"id"});
//        	respVO.setCatgCodeZH(null);
        }
        
        /* 2.设置页面对象 */
        model.addAttribute("respVO", respVO);

        /* 4.返回页面路径 */
        return URL + "-view";
    }
    
    /** 
     * edit:(楼层编辑). <br/> 
     * TODO(页面中楼层广告、楼层商品、楼层页签、楼层标签、楼层属性的操作完成通过回调此方法).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/edit")
    public String edit(Model model,
            @RequestParam("floorId")String floorId,
            @ModelAttribute("searchParams") String searchParams) throws Exception{
        /*1.验证入参*/
        if(StringUtils.isBlank(floorId)){
           throw new BusinessException("cms.common.param.null.error",new String[]{"floorId"}); 
        }
        
        /*2.根据楼层ID获取楼层信息*/
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        reqDTO.setId(Long.valueOf(floorId));
        CmsFloorRespDTO respVO = cmsFloorRSV.queryCmsFloor(reqDTO);
        
        //2.1根据关联商品分类id查出相应的名称
        if (respVO != null  && StringUtil.isNotBlank(respVO.getCatgCode())) {
            GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
            GdsCategoryRespDTO gdsCategoryRespDTO = new GdsCategoryRespDTO();
            gdsCategoryReqDTO.setCatgCode(respVO.getCatgCode());
            gdsCategoryRespDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryReqDTO);
            if (gdsCategoryRespDTO != null)
                respVO.setCatgCodeZH(gdsCategoryRespDTO.getCatgName());
        }
        
        model.addAttribute("respVO", respVO);
        
        /*3.返回信息*/
        return URL+"-edit";
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
    public EcpBaseResponseVO changestatus(
            Model model, 
            @RequestParam("ids") String ids,
            @RequestParam("status") String status) throws Exception {
        LogUtil.info(MODULE, "==========ids:" + ids + ";");
        if (StringUtils.isBlank(ids)) {
            throw new BusinessException("cms.common.param.null.error", new String[] { "ids" });
        }
        if (StringUtils.isBlank(status)) {
            throw new BusinessException("cms.common.param.null.error", new String[] { "status" });
        }
        EcpBaseResponseVO vo = new EcpBaseResponseVO();

        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("入参ids为空！");
        }
        // 如果状态为1，则为发布，需进行判断该内容位置下是否已经存在楼层，
        if (status.equalsIgnoreCase(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1)) {
            CmsFloorRespDTO cmsFloorRespDTO = new CmsFloorRespDTO();
            // 遍历循环判断
            for (int i = 0; i < list.size(); i++) {
                String id = list.get(i);
                if(StringUtil.isNotEmpty(id)){
                    //根据楼层ID查询楼层
                    CmsFloorReqDTO cmsFloorReqDTO = new CmsFloorReqDTO();
                    cmsFloorReqDTO.setId(Long.valueOf(id));
                    cmsFloorRespDTO = cmsFloorRSV.queryCmsFloor(cmsFloorReqDTO);
                    if(cmsFloorRespDTO != null && StringUtil.isNotEmpty(cmsFloorRespDTO.getPlaceId())){
                        //根据内容位置查询有效楼层
                        CmsFloorReqDTO cmsFloorReqDTO2 = new CmsFloorReqDTO();
                        cmsFloorReqDTO2.setPlaceId(cmsFloorRespDTO.getPlaceId());
                        cmsFloorReqDTO2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                        if(!PLACEID.equals(cmsFloorRespDTO.getPlaceId())){//内容位置ID为"1703"时，可以配置多个楼层。
                            List<CmsFloorRespDTO> list2 = cmsFloorRSV.queryCmsFloorList(cmsFloorReqDTO2);
                            if (!CollectionUtils.isEmpty(list2)) {
                                vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                                return vo;
                            } 
                        }
                    }
                }
            }
        }
        cmsFloorRSV.changeStatusCmsFloorBatch(list, status);
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
     * @author jiangzh 
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
            throw new BusinessException("cms.common.param.null.error",new String[] { "ids" }); 
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        cmsFloorRSV.deleteCmsFloorBatch(list);
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
     * @param cmsFloorVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/pubsave")
    @ResponseBody
    public CmsFloorRespDTO pubsave(@Valid CmsFloorVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);//发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        
        CmsFloorReqDTO reqDTO2 = new CmsFloorReqDTO();
        reqDTO2.setPlaceId(reqDTO.getPlaceId());
        reqDTO2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        if(!PLACEID.equals(reqDTO.getPlaceId())){//内容位置ID为"1703"时，可以配置多个楼层。
            List<CmsFloorRespDTO> list = cmsFloorRSV.queryCmsFloorList(reqDTO2);
            if (list.size() > 0) {
                CmsFloorRespDTO cmsFloorRespDTO = new CmsFloorRespDTO();
                return cmsFloorRespDTO;
            } 
        }
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        if(reqDTO.getId() != null){
            respDTO = cmsFloorRSV.updateCmsFloor(reqDTO);
        }else{
            respDTO = cmsFloorRSV.addCmsFloor(reqDTO);
        }
        return respDTO;
    }
   
    /** 
     * save:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param cmsAdvertiseVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/save")
    @ResponseBody
    public CmsFloorRespDTO save(@Valid CmsFloorVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsFloorReqDTO reqDTO = new CmsFloorReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布
        //如果排序为空，则默认赋值为1.
        if(reqDTO.getSortNo() == null){
            reqDTO.setSortNo(1);
        }
        CmsFloorRespDTO respDTO = new CmsFloorRespDTO();
        if(VO.getId() != null){
            respDTO = cmsFloorRSV.updateCmsFloor(reqDTO);
        }else{
            respDTO = cmsFloorRSV.addCmsFloor(reqDTO);
        }
        return respDTO;
    }
    
    /** 
     * gridList:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @param model
     * @param searchVO
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @SuppressWarnings("rawtypes")
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsFloorVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsFloorReqDTO reqDTO = searchVO.toBaseInfo(CmsFloorReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsFloorRespDTO> pageInfo = cmsFloorRSV.queryCmsFloorPage(reqDTO);
        List<CmsFloorRespDTO> floorRespDTOList = pageInfo.getResult();
        if(CollectionUtils.isNotEmpty(floorRespDTOList)){
            for(CmsFloorRespDTO floorRespDTO:floorRespDTOList){
                //2.1根据关联商品分类id查出相应的名称
                if (floorRespDTO != null  && StringUtil.isNotBlank(floorRespDTO.getCatgCode())) {
                    GdsCategoryReqDTO gdsCategoryReqDTO = new GdsCategoryReqDTO();
                    GdsCategoryRespDTO gdsCategoryRespDTO = new GdsCategoryRespDTO();
                    gdsCategoryReqDTO.setCatgCode(floorRespDTO.getCatgCode());
                    gdsCategoryRespDTO = this.gdsCategoryRSV.queryGdsCategoryByPK(gdsCategoryReqDTO);
                    if (gdsCategoryRespDTO != null)
                        floorRespDTO.setCatgCodeZH(gdsCategoryRespDTO.getCatgName());
                }
            }
        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }

}
