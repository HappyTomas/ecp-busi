/** 
 * Project Name:ecp-web-manage Maven Webapp 
 * File Name:CmsInfoController.java 
 * Package Name:com.zengshi.ecp.busi.cms.controller 
 * Date:2015-8-14下午2:58:36 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.busi.cms.appfloor.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.appfloor.vo.CmsAppFloorDataVO;
import com.zengshi.ecp.busi.cms.appfloor.vo.CmsAppFloorVO;
import com.zengshi.ecp.busi.cms.info.vo.CmsInfoVO;
import com.zengshi.ecp.busi.goods.vo.GdsManageVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorPlaceRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorDataRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsAppFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorPlaceRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
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
 * @author jiangzh
 * @version
 * @since JDK 1.7
 */
@Controller
@RequestMapping(value = "/appfloor")
public class CmsAppFloorController extends EcpBaseController {

    private static String MODULE = CmsAppFloorController.class.getName();

    private String URL = "/cms/appfloor/";// 返回页面的基本路径
    private String URL_OPEN = "/cms/appfloor/open/appfloor";//返回页面的弹出窗路径

    @Resource(name = "cmsAppFloorRSV")
    private ICmsAppFloorRSV cmsAppFloorRSV;

    @Resource(name = "cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;

    @Resource(name = "cmsFloorTemplateRSV")
    private ICmsFloorTemplateRSV cmsFloorTemplateRSV;
    
    @Resource(name = "cmsFloorPlaceRSV")
    private ICmsFloorPlaceRSV cmsFloorPlaceRSV;
    
    @Resource(name = "cmsAppFloorDataRSV")
    private ICmsAppFloorDataRSV cmsAppFloorDataRSV;
    
    @Resource(name="cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    @Resource(name="gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    @Resource(name="shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    /**
     * grid:(初始化app楼层列表). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/grid")
    public String grid(Model model, @ModelAttribute("searchParams") String searchParams)
            throws Exception {
        model.addAttribute("siteList", this.querySiteList(null));
        model.addAttribute("floorTemplateList", this.queryfloorTemplateList(null));
        return URL + "appfloor-grid";
    }

    /**
     * app楼层新增页面初始化 add:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh
     * @param model
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value = "/add")
    public String add(Model model, @ModelAttribute("searchParams") String searchParams) {
        LogUtil.info(MODULE, "开始新增页面初始化");
        try {
            model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("floorTemplateList",this.queryfloorTemplateList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1)); 
        } catch (Exception e) {
            LogUtil.error(MODULE, "页面初始化出现异常:", e);
            e.printStackTrace();
        }

        // 3.跳转到页面的路径
        return URL + "appfloor-edit";
    }

    /**
     * 编辑页面初始化 AppFloorEdit:(这里用一句话描述这个方法的作用). <br/>
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
    public String edit(Model model, @RequestParam("id") String id,
            @ModelAttribute("searchParams") String searchParams) {
        LogUtil.info(MODULE, "开始页面编辑初始化,入参：{id=" + id + "}");
        if (StringUtil.isBlank(id)) {
            String[] keyAppFloors = new String[1];
            keyAppFloors[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keyAppFloors);
        }

        // 1.根据入参调用后场链接查询服务
        CmsAppFloorReqDTO cmsAppFloorDTO = new CmsAppFloorReqDTO();
        cmsAppFloorDTO.setId(Long.parseLong(id));
        CmsAppFloorRespDTO cmsAppFloorRespDTO = cmsAppFloorRSV.queryCmsAppFloor(cmsAppFloorDTO);
        try {
            model.addAttribute("siteList",this.querySiteList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
            model.addAttribute("floorTemplateList",this.queryfloorTemplateList(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        // 2.设置页面对象
        model.addAttribute("respVO", cmsAppFloorRespDTO);

        // 3.跳转到页面的路径
        return URL + "appfloor-edit";
    }

    /**
     * 保存方法 AppFloor save:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param cmsAppFloor
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid CmsAppFloorVO cmsAppFloorVO){
        LogUtil.info(MODULE, "进入save app楼层方法,入参：{vo=" + cmsAppFloorVO.toString() + "}");

        // 如果排序为空，则默认赋值为1.
        if (cmsAppFloorVO.getSortNo() == null) {
            cmsAppFloorVO.setSortNo(1);
        }
        // 2.封装后场入参
        CmsAppFloorReqDTO dto = new CmsAppFloorReqDTO();
        ObjectCopyUtil.copyObjValue(cmsAppFloorVO, dto, "", true);
        dto.setCmsAppFloorDataReqDTOList(new ArrayList<CmsAppFloorDataReqDTO>());
        
        List<CmsAppFloorDataVO> dataVoList = cmsAppFloorVO.getCmsAppFloorDataVOList();
        if(CollectionUtils.isNotEmpty(dataVoList)){
            for(CmsAppFloorDataVO dataVo : dataVoList){
                CmsAppFloorDataReqDTO dataReqDto = new CmsAppFloorDataReqDTO();
                ObjectCopyUtil.copyObjValue(dataVo, dataReqDto, "", true);
                dto.getCmsAppFloorDataReqDTOList().add(dataReqDto);
            }
        }
        
        // 3.调用后场链接修改服务
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            if (dto.getId() != null) {// 修改
                cmsAppFloorRSV.updateCmsAppFloor(dto);
            } else {// 新增
                cmsAppFloorRSV.addCmsAppFloor(dto);
            }
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存AppFloor   save方法  异常"+e.getMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg("保存AppFloor   save方法  异常");
        }
        return respVO;
    }

    /**
     * 发布链接方法 pubSave:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param cmsAppFloor
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @RequestMapping(value = "/pubsave")
    public EcpBaseResponseVO pubSave(@Valid CmsAppFloorVO cmsAppFloorVO) throws Exception {
        LogUtil.info(MODULE, "进入pubsave接方法,入参：{vo=" + cmsAppFloorVO.toString() + "}");

        // 如果排序为空，则默认赋值为1.
        if (cmsAppFloorVO.getSortNo() == null) {
            cmsAppFloorVO.setSortNo(1);
        }
        // 2.封装后场入参
        CmsAppFloorReqDTO dto = new CmsAppFloorReqDTO();
        ObjectCopyUtil.copyObjValue(cmsAppFloorVO, dto, "", true);
        dto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);// 发布默认为有效状态

        // 3.调用后场链接修改服务
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        if (dto.getId() != null) {// 修改
            cmsAppFloorRSV.updateCmsAppFloor(dto);
        } else {// 新增
            cmsAppFloorRSV.addCmsAppFloor(dto);
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
    @RequestMapping(value = "/changestatus")
    @ResponseBody
    public EcpBaseResponseVO changestatus(Model model, @RequestParam("ids") String ids,
            @RequestParam("status") String status) throws Exception {
        LogUtil.info(MODULE, "==========ids:" + ids + ";");
        if (StringUtil.isBlank(ids)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "ids";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        if (StringUtil.isBlank(status)) {
            String[] keyInfos = new String[1];
            keyInfos[0] = "status";
            throw new BusinessException("cms.common.param.null.error", keyInfos);
        }
        String[] idsArray = ids.split(",");
        List<String> list = Arrays.asList(idsArray);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException("入参ids为空！");
        }
        cmsAppFloorRSV.changeStatusCmsAppFloorBatch(list, status);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }

    /**
     * 链接删除 AppFloorDelete:(这里用一句话描述这个方法的作用). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param cmsAppFloor
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @RequestMapping(value = "/delete")
    public EcpBaseResponseVO delete(@RequestParam("ids") String id) throws Exception {
        LogUtil.info(MODULE, "进入删除链接方法,入参：{id=" + id + "}");
        if (StringUtil.isBlank(id)) {
            String[] keyAppFloors = new String[1];
            keyAppFloors[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keyAppFloors);
        }

        // 1.封装后场入参
        CmsAppFloorReqDTO dto = new CmsAppFloorReqDTO();
        dto.setId(Long.parseLong(id));

        // 2.调用后场服务
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            cmsAppFloorRSV.deleteCmsAppFloor(id);
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
     * @author zhanbh
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @since JDK 1.7
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsAppFloorVO searchVO)
            throws Exception {
        LogUtil.info(MODULE, "进入app楼层分页查询方法,入参：{vo=" + searchVO.toString() + "}");

        // 1.封装后场入参对象
        CmsAppFloorReqDTO cmsAppFloorDTO = searchVO.toBaseInfo(CmsAppFloorReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, cmsAppFloorDTO, "MODULE", false);

        // 2.调用后场服务
        PageResponseDTO<CmsAppFloorRespDTO> pageResponseDTO = cmsAppFloorRSV
                .queryCmsAppFloorPage(cmsAppFloorDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageResponseDTO);

        // 3.返回
        return super.addPageToModel(model, respVO);
    }

    /**
     * 链接查看页面初始化方法 view:(这里用一句话描述这个方法的作用). <br/>
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
    public String view(Model model, @RequestParam("id") String id,
            @ModelAttribute("searchParams") String searchParams) {
        LogUtil.info(MODULE, "进入app楼层查看初始化,入参：{id=" + id + "}");

        if (StringUtil.isBlank(id)) {
            String[] keyAppFloors = new String[1];
            keyAppFloors[0] = "id";
            throw new BusinessException("cms.common.param.null.error", keyAppFloors);
        }
        //楼层模板数据
        CmsFloorTemplateRespDTO floorTemplate = null;
        //内容位置数据
        List<CmsFloorPlaceRespDTO> placeRespDTOList = null;
        //楼层数据数据
        List<CmsAppFloorDataRespDTO> floorDataRespDTOList = null;
        Map<Long,CmsAppFloorDataRespDTO> floorDataRespDTOMap = new HashMap<Long,CmsAppFloorDataRespDTO>();
        
        // 1.根据入参调用后场链接查询服务
        CmsAppFloorReqDTO cmsAppFloorDTO = new CmsAppFloorReqDTO();
        cmsAppFloorDTO.setId(Long.parseLong(id));
        CmsAppFloorRespDTO cmsAppFloorRespDTO = cmsAppFloorRSV.queryCmsAppFloor(cmsAppFloorDTO);

        //2.查询对应的模板
        if(cmsAppFloorRespDTO!=null && StringUtil.isNotEmpty(cmsAppFloorRespDTO.toString())){
            CmsFloorTemplateReqDTO templateReqDTO = new CmsFloorTemplateReqDTO();
            templateReqDTO.setId(cmsAppFloorRespDTO.getFloorTemplateId());
            templateReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            floorTemplate = cmsFloorTemplateRSV.queryCmsFloorTemplate(templateReqDTO);
        }
        // 3.查询内容位置
        if(floorTemplate!=null && StringUtil.isNotEmpty(floorTemplate.getId())){
            CmsFloorPlaceReqDTO placeReqDTO = new CmsFloorPlaceReqDTO();
            placeReqDTO.setFloorTemplateId(floorTemplate.getId());
            placeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            placeRespDTOList = cmsFloorPlaceRSV.queryCmsFloorPlaceList(placeReqDTO);
        }
        
        //3.查询楼层数据
        if(CollectionUtils.isNotEmpty(placeRespDTOList)){
            CmsAppFloorDataReqDTO floorDataReqDTO = new CmsAppFloorDataReqDTO();
            floorDataReqDTO.setAppFloorId(cmsAppFloorRespDTO.getId());
            floorDataReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            for(CmsFloorPlaceRespDTO dto : placeRespDTOList){
                floorDataReqDTO.setFloorPlaceId(dto.getId());
                floorDataRespDTOList = cmsAppFloorDataRSV.queryCmsAppFloorDataList(floorDataReqDTO);
                if(CollectionUtils.isNotEmpty(floorDataRespDTOList)){
                    floorDataRespDTOMap.put(dto.getId(), floorDataRespDTOList.get(0));
                }
            }
        }
        model.addAttribute("respVO", cmsAppFloorRespDTO);
        model.addAttribute("floorTemplate", floorTemplate);
        model.addAttribute("placeList", placeRespDTOList);
        model.addAttribute("floorDataMap", floorDataRespDTOMap);
        // 4.返回页面路径
        return URL + "appfloor-view";
    }

    /**
     * querySiteList:(获取内容位置列表). <br/>
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
    private List<CmsSiteRespDTO> querySiteList(String status) throws Exception {
        CmsSiteReqDTO cmsSiteReqDTO = new CmsSiteReqDTO();
        if (StringUtil.isNotBlank(status)) {
            cmsSiteReqDTO.setStatus(status);
        }
        List<CmsSiteRespDTO> cmsSiteRespDTOList = cmsSiteRSV.queryCmsSiteList(cmsSiteReqDTO);
        return cmsSiteRespDTOList;
    }

    /**
     * queryfloorTemplateList:(获取楼层模板列表). <br/>
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
    private List<CmsFloorTemplateRespDTO> queryfloorTemplateList(String status) {
        CmsFloorTemplateReqDTO cmsFloorTemplateReqDTO = new CmsFloorTemplateReqDTO();
        if (StringUtil.isNotBlank(status)) {
            cmsFloorTemplateReqDTO.setStatus(status);
        }
        List<CmsFloorTemplateRespDTO> cmsFloorTemplateRespDTOList = cmsFloorTemplateRSV.queryCmsFloorTemplateList(cmsFloorTemplateReqDTO);
        return cmsFloorTemplateRespDTOList;
    }
    
    /**
     * 
     * queryFloorPlace:(查询指定app楼层关联的模板的内容位置，且如果有数据则返回数据！). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param vo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/queryFloorPlace")
    public String queryFloorPlace (Model model,CmsAppFloorVO vo){
        //模板数据
        CmsFloorTemplateRespDTO floorTemplate = null;
        
        //内容位置数据
        List<CmsFloorPlaceRespDTO> placeRespDTOList = null;
        
        //楼层数据数据
        List<CmsAppFloorDataRespDTO> floorDataRespDTOList = null;
        Map<Long,CmsAppFloorDataRespDTO> floorDataRespDTOMap = new HashMap<Long,CmsAppFloorDataRespDTO>();
        
        //1.查询模板
        if(StringUtil.isNotEmpty(vo.getFloorTemplateId())){
            CmsFloorTemplateReqDTO templateReqDTO = new CmsFloorTemplateReqDTO();
            templateReqDTO.setId(vo.getFloorTemplateId());
            templateReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            floorTemplate = cmsFloorTemplateRSV.queryCmsFloorTemplate(templateReqDTO);
        }
        
        //2.查询模板对应的内容位置
        if(floorTemplate!=null && StringUtil.isNotEmpty(floorTemplate.getId())){
            CmsFloorPlaceReqDTO placeReqDTO = new CmsFloorPlaceReqDTO();
            placeReqDTO.setFloorTemplateId(vo.getFloorTemplateId());
            placeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            placeRespDTOList = cmsFloorPlaceRSV.queryCmsFloorPlaceList(placeReqDTO);
        }
        
        //3.如果楼层id有值，即处于编辑状态，查询楼层数据
        if(StringUtil.isNotEmpty(vo.getId()) && CollectionUtils.isNotEmpty(placeRespDTOList)){
            CmsAppFloorDataReqDTO floorDataReqDTO = new CmsAppFloorDataReqDTO();
            floorDataReqDTO.setAppFloorId(vo.getId());
            floorDataReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            for(CmsFloorPlaceRespDTO dto : placeRespDTOList){
                floorDataReqDTO.setFloorPlaceId(dto.getId());
                floorDataRespDTOList = cmsAppFloorDataRSV.queryCmsAppFloorDataList(floorDataReqDTO);
                if(CollectionUtils.isNotEmpty(floorDataRespDTOList)){
                    floorDataRespDTOMap.put(dto.getId(), floorDataRespDTOList.get(0));
                }
            }
        }
        
        //4.返回数据
        model.addAttribute("floorTemplate", floorTemplate);
        model.addAttribute("placeList", placeRespDTOList);
        model.addAttribute("floorDataMap", floorDataRespDTOMap);
        
        return URL + "floorPlace/floorPlace";
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
    public String opengds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam("siteId")String siteId){
        model.addAttribute("siteId", siteId);
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
    public Model querygds(Model model, 
            @ModelAttribute("searchVO") GdsManageVO searchVO,
            @RequestParam("siteId")String siteId) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        GdsInfoReqDTO reqDTO = searchVO.toBaseInfo(GdsInfoReqDTO.class);
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);//已上架
        //选择相应站点的商品
        if(siteId.equalsIgnoreCase("1")){
            reqDTO.setIfScoreGds("0");
        }else{
            reqDTO.setIfScoreGds("1");
        }
        PageResponseDTO<GdsInfoRespDTO> pageInfo = gdsInfoQueryRSV.queryGdsInfoListPage(reqDTO);
        List<GdsInfoRespDTO> respList = null;
        if(pageInfo != null){
            respList = pageInfo.getResult();
        } 
        if(!CollectionUtils.isEmpty(respList)){
            for(GdsInfoRespDTO gdsInfoRespDTO:respList){
                if(gdsInfoRespDTO != null && StringUtil.isNotEmpty(gdsInfoRespDTO.getShopId())){
                    //3.2调用店铺，返回店铺信息
                    ShopInfoResDTO shopInfoRespDTO = shopInfoRSV.findShopInfoByShopID(gdsInfoRespDTO.getShopId());
                    if(shopInfoRespDTO != null){
                        gdsInfoRespDTO.setGdsSubHead(shopInfoRespDTO.getShopName());//将商品副标题，当成店铺名称用。
                    } 
                }
            }
        }
        
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        return super.addPageToModel(model, respVO);
    }
    
    /** 选择商品内容弹出框
     * openinfo:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author jiangzh 
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping("/openinfo")
    public String openinfo(){
        return URL_OPEN + "-info";
    } 
    
    /** 
     * queryinfo:(这里用一句话描述这个方法的作用). <br/> 
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
    @RequestMapping("/queryinfo")
    @ResponseBody
    public Model queryinfo(Model model, @ModelAttribute("searchVO") CmsInfoVO searchVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(searchVO));
        //1. 调用后场服务所需要的DTO；
        CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
        ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        reqDTO.setStatus(searchVO.getStatus());
        reqDTO.setThisTime(DateUtil.getSysDate());
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsInfoRespDTO> pageInfo = cmsInfoRSV.queryCmsInfoPage(reqDTO);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
}
