package com.zengshi.ecp.busi.goods.type.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.goods.gdscategory.vo.CategoryVO;
import com.zengshi.ecp.busi.goods.type.vo.GdsType2PropReqVO;
import com.zengshi.ecp.busi.goods.type.vo.TypeReqVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsType2PropRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsTypeRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2016年4月25日下午4:22:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/goods/type")
public class GdsTypeController extends GdsBaseController{
    
    private String MODULE = GdsTypeController.class.getName();
    private String URL = "/goods/type";

    @Resource
    private IGdsTypeRSV gdsTypeRSV;
    
    @Resource
    private IGdsType2PropRSV gdsType2PropRSV;
    
    //模块首页跳转
    @RequestMapping()
    public String init(Model model,TypeReqVO typeReqVO){
        return URL+"/gds-type-grid";
    }
    
    //Grid网格数据初始化
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/gridlist")
    public Model gridList(Model model,TypeReqVO typeReqVO){
        
        GdsTypeReqDTO dto = typeReqVO.toBaseInfo(GdsTypeReqDTO.class);
        
        EcpBasePageRespVO<Map> respVO = null;
        try {
            ObjectCopyUtil.copyObjValue(typeReqVO, dto, null, false);
            PageResponseDTO<GdsTypeRespDTO> pageInfo = this.gdsTypeRSV.queryGdsTypePaging(dto);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取商品类型列表失败！",e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取商品类型列表失败！",e);
        }
        return super.addPageToModel(model, respVO);
        
    }
    
    //启用
    @RequestMapping(value="/enableType")
    @ResponseBody
    public EcpBaseResponseVO enableType(TypeReqVO typeReqVO){
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try{
            LongReqDTO longReqDTO=new LongReqDTO();
            longReqDTO.setId(typeReqVO.getId());
            this.gdsTypeRSV.executeEnableGdsTypeByPK(longReqDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getErrorMessage());
        }
        
        return vo;
        
    }
    
    //禁用
    @RequestMapping(value="/disableType")
    @ResponseBody
    public EcpBaseResponseVO disableType(TypeReqVO typeReqVO){
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try{
            LongReqDTO longReqDTO=new LongReqDTO();
            longReqDTO.setId(typeReqVO.getId());
            this.gdsTypeRSV.executeDisableGdsTypeByPK(longReqDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getErrorMessage());
        }
        
        return vo;
        
    }
    
    // 新增类型页面跳转
    @RequestMapping(value = "/openAddType")
    public String openAddType() {
        return URL + "/open/gds-type-add";
    }
    
    // 编辑类型页面跳转
    @RequestMapping(value = "/openEditType")
    public String openEditType(Model model, @RequestParam("id") String id) {

        LongReqDTO reqDTO = new LongReqDTO();
        reqDTO.setId(Long.parseLong(id));
        GdsTypeRespDTO respDTO = this.gdsTypeRSV.queryGdsTypeByPK(reqDTO);
        model.addAttribute("respDTO", respDTO);
        return URL + "/open/gds-type-edit";
    }
    
    //新增类型
    @RequestMapping(value="/addType")
    @ResponseBody
    public EcpBaseResponseVO addType(TypeReqVO typeReqVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try{
            GdsTypeReqDTO gdsTypeReqDTO=new GdsTypeReqDTO();
            ObjectCopyUtil.copyObjValue(typeReqVO, gdsTypeReqDTO, "", false);
            this.gdsTypeRSV.createGdsType(gdsTypeReqDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getErrorMessage());
        }
        
        return vo;
    }
    
    //编辑类型
    @RequestMapping(value="/editType")
    @ResponseBody
    public EcpBaseResponseVO editType(TypeReqVO typeReqVO){
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        try{
            GdsTypeReqDTO gdsTypeReqDTO=new GdsTypeReqDTO();
            ObjectCopyUtil.copyObjValue(typeReqVO, gdsTypeReqDTO, "", false);
            this.gdsTypeRSV.editGdsType(gdsTypeReqDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getErrorMessage());
        }
        
        return vo;
    }
    
    // 属性配置页面跳转
    @RequestMapping(value = "/configProp")
    public String configProp(Model model, @RequestParam("id") String id) {

        LongReqDTO reqDTO = new LongReqDTO();
        reqDTO.setId(Long.parseLong(id));
        GdsTypeRespDTO respDTO = this.gdsTypeRSV.queryGdsTypeByPK(reqDTO);
        model.addAttribute("respDTO", respDTO);
        return URL + "/config-prop";
    }
    
    /**
     * 
     * 已配置属性列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/listconfigedprop")
    @ResponseBody
    public Model configedPropList(Model model, GdsType2PropReqVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取已配置属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsType2PropReqDTO reqDTO = reqVO.toBaseInfo(GdsType2PropReqDTO.class);

        PageResponseDTO<GdsType2PropRespDTO> page = null;
        if (reqVO.getTypeId()==null||reqVO.getTypeId()==0) {
            page = new PageResponseDTO<GdsType2PropRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsType2PropRespDTO>());
        } else {
            reqDTO.setTypeId(reqVO.getTypeId());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setPropName(reqVO.getPropName());
            page = gdsType2PropRSV.queryConfigedPropsPaging(reqDTO);
        }

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }

    /**
     * 
     * 可选属性列表.
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/listoptionalprop")
    @ResponseBody
    public Model optionalPropList(Model model, GdsType2PropReqVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "获取可选配置属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        GdsType2PropReqDTO reqDTO = reqVO.toBaseInfo(GdsType2PropReqDTO.class);

        EcpBasePageRespVO<Map> respVO = null;
        PageResponseDTO<GdsPropRespDTO> page = null;
        if (reqVO.getTypeId()==null||reqVO.getTypeId()==0) {
            page = new PageResponseDTO<GdsPropRespDTO>();
            page.setCount(0L);
            page.setPageSize(10);
            page.setResult(new ArrayList<GdsPropRespDTO>());
        } else {
            reqDTO.setTypeId(reqVO.getTypeId());
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            reqDTO.setPropName(reqVO.getPropName());
            page = gdsType2PropRSV.queryOptionalPropsPaging(reqDTO);

            if (CollectionUtils.isNotEmpty(page.getResult())) {
                for (GdsPropRespDTO gdsPropRespDTO : page.getResult()) {
                    gdsPropRespDTO.setStatus(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS",
                            gdsPropRespDTO.getStatus()));

                    gdsPropRespDTO.setPropType(BaseParamUtil.fetchParamValue("GDS_PROP_TYPE",
                            gdsPropRespDTO.getPropType()));

                    gdsPropRespDTO.setPropValueType(BaseParamUtil.fetchParamValue(
                            "GDS_PROP_VALUE_TYPE", gdsPropRespDTO.getPropValueType()));
                }
            }

        }
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * 添加属性关联关系.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/addprops")
    @ResponseBody
    public EcpBaseResponseVO addPropRelation(Model model, GdsType2PropReqVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "关联属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getTypeId(), reqVO.getPropIds() }, new String[] {
                "typeId", "propIds" });
        try {
            GdsType2PropReqDTO reqDTO = new GdsType2PropReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "propIds", true);
            reqDTO.setPropIds(reqVO.getPropIds());
            this.gdsType2PropRSV.addProps(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "类型关联属性出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }
    
    /**
     * 
     * 删除属性关联.<br/>
     * 
     * @author liyong7
     * @param model
     * @param reqVO
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/delprops")
    @ResponseBody
    public EcpBaseResponseVO delPropRelation(Model model, GdsType2PropReqVO reqVO) throws Exception {
        LogUtil.debug(MODULE, "关联属性,入参:" + ToStringBuilder.reflectionToString(reqVO));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        paramCheck(new Object[] { reqVO.getTypeId(), reqVO.getPropIds() }, new String[] {
                "typeId", "propIds" });
        try {
            GdsType2PropReqDTO reqDTO = new GdsType2PropReqDTO();
            ObjectCopyUtil.copyObjValue(reqVO, reqDTO, "propIds", true);
            reqDTO.setPropIds(reqVO.getPropIds());

            this.gdsType2PropRSV.deleteProps(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "类型关联属性出现异常!", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }
    
    /**
     * 
     * 属性配置:针对是否必填,是否基础属性,是否搜索属性进行设置.<br/>
     * 
     * @author liyong7
     * @param reqVO
     *            catgCode必传
     * @param propertyType
     *            属性类型 1-表示是否基础属性 2-表示是否必填 3-表示是否搜索属性.
     * @param propertyValue
     *            0-表示非 1-表示是
     * @param bindingResult
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/propchange")
    public EcpBaseResponseVO propertyConfig(GdsType2PropReqDTO reqVO,
            @RequestParam(value = "type", required = true)
            Integer type, @RequestParam(value = "value", required = true)
            String value, BindingResult bindingResult) throws Exception {

        LogUtil.info(
                MODULE,
                String.format(
                        "属性配置,入参:",
                        String.format("typeId=[%s],propertyType=[%d],propertyValue=[%s]",
                                reqVO.getTypeId(), type, value)));

        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            paramCheck(new Object[] { reqVO.getTypeId(), type, value }, new String[] {
                    "catgCode", "type", "value" });

            switch (type) {
            case 1:
                reqVO.setIfBasic(value);
                this.gdsType2PropRSV.executeIsBaseConfig(reqVO);
                break;
            case 2:
                reqVO.setIfHaveto(value);
                this.gdsType2PropRSV.executeIsRequireConfig(reqVO);
                break;
            case 3:
                reqVO.setIfSearch(value);
                this.gdsType2PropRSV.executeIsSearchConfig(reqVO);
                break;
            case 4:
                reqVO.setIfGdsInput(value);
                this.gdsType2PropRSV.executeIfGdsInputConfig(reqVO);
                break;
            default:
                throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200098,
                        new String[] { "value" });
            }

            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "属性配置异常", e);
            respVO.setResultMsg(e.getErrorMessage());
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);

        }

        return respVO;
    }
    
    /**
     * 
     * editCheck:类型属性编辑检测. <br/> 
     * 
     * @author liyong7
     * @param categoryVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping("/propDelCheck")
    public EcpBaseResponseVO propsDelCheck(Model model, GdsType2PropReqVO reqVO) throws Exception{
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        LogUtil.info(MODULE, String.format("类型属性关联关系删除前检测,入参:",ToStringBuilder.reflectionToString(reqVO)));
        try {
            GdsType2PropReqDTO reqDTO = new GdsType2PropReqDTO();
            reqDTO.setPropIds(reqVO.getPropIds());
            reqDTO.setTypeId(reqVO.getTypeId());
            Long count = gdsType2PropRSV.queryUneditableType2Prop(reqDTO);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            respVO.setResultMsg(count.toString());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "属性可编辑检测遇到异常！", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            respVO.setResultMsg(e.getErrorMessage());
        }
        return respVO;
    }
    
}

