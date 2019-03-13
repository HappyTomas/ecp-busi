package com.zengshi.ecp.busi.goods.propgroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.propgroup.vo.GdsPropGroupVO;
import com.zengshi.ecp.busi.goods.propgroup.vo.GdsPropVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropGroupRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropGroupRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsPropRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 产品属性组管理页面（包括新增、编辑等操作）<br>
 * Date:2015年8月21日上午9:51:41  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/gdspropgroup")
@Controller
public class GdsPropGroupController extends EcpBaseController{
    private static String MODULE = GdsPropGroupController.class.getName();
    private static final String URL = "/goods/gdsPropGroup";
    @Resource
    private IGdsPropGroupRSV iGdsPropGroupRSV;
    @Resource
    private IGdsPropRSV iGdsPropRSV;
    
    
    @RequestMapping()
    public String init(){
        return URL+"/propgroup-grid";
    }
    
    /**
     * 
     * gridList:(属性组管理列表). <br/> 
     * @author gxq 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="gridpropgrouplist")
    @ResponseBody
    public Model gridList(Model model,GdsPropGroupVO vo) throws Exception{
        GdsPropGroupReqDTO dto = vo.toBaseInfo(GdsPropGroupReqDTO.class);
        ObjectCopyUtil.copyObjValue(vo, dto, "", false);
        PageResponseDTO<GdsPropGroupRespDTO> pageInfo = new PageResponseDTO<GdsPropGroupRespDTO>();
        try {
            //后场返回的列表信息；
            pageInfo = iGdsPropGroupRSV.queryGdsPropGroupRespDTOPaging(dto);
        } catch (BusinessException e) {
            //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
            EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
            return super.addPageToModel(model, respVO);
        }
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        return super.addPageToModel(model, respVO);
    }
    /**
     * 
     * propAdd:(添加). <br/> 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/propadd")
    public String propAdd(){
        return URL+"/propgroup-add";
    }
    /**
     * 
     * propEdit:(编辑). <br/> 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/propedit")
    public String propEdit(Model model,GdsPropVO gdsPropVO){
        GdsPropGroupReqDTO dto = new GdsPropGroupReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropVO, dto, "", false);
        GdsPropGroupRespDTO resultDto = iGdsPropGroupRSV.queryGdsPropGroupAndProps(dto);
        model.addAttribute("propInfo", resultDto);
        return URL+"/propgroup-edit";
    }
    /**
     * 
     * propStartUp:(启用). <br/> 
     * @author gxq 
     * @param model
     * @param gdsVo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/propstartup")
    @ResponseBody
    public EcpBaseResponseVO propStartUp(Model model,@RequestParam("propId") String propId){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsPropGroupReqDTO dto = new GdsPropGroupReqDTO();
        if(StringUtil.isBlank(propId)){
            throw new BusinessException("web.gds.200005");
        }
        dto.setId(Long.parseLong(propId));
        try{
            iGdsPropGroupRSV.executeEnableGdsPropGroup(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch(BusinessException e){
            BusinessException be = (BusinessException) e;
            if (e instanceof BusinessException) {
                vo.setResultMsg(be.getErrorMessage());
            }else{
                vo.setResultMsg(e.getMessage());
            }
            LogUtil.error(MODULE, "报错了啦", be);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    
    /**
     * 
     * saveGroupInfo:(新增保存属性组). <br/> 
     * @author gxq 
     * @param gdsPropGroupVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/savegroupinfo")
    public EcpBaseResponseVO saveGroupInfo(@Valid GdsPropGroupVO gdsPropGroupVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsPropGroupReqDTO dto = new GdsPropGroupReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropGroupVO, dto, "", false);
        String propIdParam = gdsPropGroupVO.getPropIdParam();
        JSONArray skuParamList = JSONArray.fromObject(propIdParam);
        List<Long> propIds = new ArrayList<Long>();
        for(int i =0;i<skuParamList.size();i++){
            JSONObject object = (JSONObject) skuParamList.get(i);
            propIds.add(object.getLong("propId"));
        }
        dto.setPropIds(propIds);
        try {
            iGdsPropGroupRSV.createGdsPropGroup(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            
            LogUtil.error(MODULE, "添加属性组失败！", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorCode());
        }
        return vo;
    }
    
    @RequestMapping(value="/editgroupinfo")
    public EcpBaseResponseVO editGroupInfo(@Valid GdsPropGroupVO gdsPropGroupVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsPropGroupReqDTO dto = new GdsPropGroupReqDTO();
        ObjectCopyUtil.copyObjValue(gdsPropGroupVO, dto, "", false);
        String propIdParam = gdsPropGroupVO.getPropIdParam();
        JSONArray skuParamList = JSONArray.fromObject(propIdParam);
        List<Long> propIds = new ArrayList<Long>();
        for(int i =0;i<skuParamList.size();i++){
            JSONObject object = (JSONObject) skuParamList.get(i);
            propIds.add(object.getLong("propId"));
        }
        dto.setPropIds(propIds);
        try {
            iGdsPropGroupRSV.editGdsPropGroup(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            
            LogUtil.error(MODULE, "添加属性组失败！", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return vo;
    }
    
    /**
     * 
     * propShutDown:(禁用). <br/> 
     * @author gxq 
     * @param model
     * @param gdsVo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/propshutdown")
    @ResponseBody
    public EcpBaseResponseVO propShutDown(Model model,@RequestParam("propId") String propId){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsPropGroupReqDTO dto = new GdsPropGroupReqDTO();
        if(StringUtil.isBlank(propId)){
            throw new BusinessException("web.gds.200005");
        }
        dto.setId(Long.parseLong(propId));
        try{
            iGdsPropGroupRSV.executeDisableGdsPropGroup(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch(BusinessException e){
            BusinessException be = (BusinessException) e;
            if (e instanceof BusinessException) {
                vo.setResultMsg(be.getErrorMessage());
            }else{
                vo.setResultMsg(e.getMessage());
            }
            LogUtil.error(MODULE, "报错了啦", be);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    
    /**
     * 
     * gridPropList:(获取属性列表。用于添加或者编辑属性组列表的时候进行的查询). <br/> 
     * @author gxq 
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridproplist")
    @ResponseBody
    public Model gridPropList(Model model,GdsPropVO gdsPropVO) throws Exception{
        GdsPropReqDTO gdsPropReqDTO = gdsPropVO.toBaseInfo(GdsPropReqDTO.class);
        PageResponseDTO<GdsPropRespDTO> pageInfo = new  PageResponseDTO<GdsPropRespDTO>();
        ObjectCopyUtil.copyObjValue(gdsPropVO, gdsPropReqDTO, "", false);
        gdsPropReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        EcpBasePageRespVO<Map> respVO = null;
        try {
            pageInfo = iGdsPropRSV.queryGdsPropPaging(gdsPropReqDTO);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取属性列表失败！", e);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
            return super.addPageToModel(model, respVO);
        }
        return super.addPageToModel(model, respVO);
    }
}

