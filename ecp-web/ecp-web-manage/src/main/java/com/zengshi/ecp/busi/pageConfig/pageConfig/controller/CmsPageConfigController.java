package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsModularVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsPageAttrReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsTemplateLibReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageAttrPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageColorRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularComponentRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageColorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.system.util.ConstantTool;
import com.zengshi.ecp.system.util.MatrixToImageWriter;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/pageConfig")
public class CmsPageConfigController  extends EcpBaseController {
    
    private static String MODULE = CmsPageConfigController.class.getName();
    
    private String URL = "/pageConfig/pageConfig/";//返回页面的基本路径
    private String WAP_URL = "/pageConfig/pageConfigWap/";//返回页面的基本路径
    
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    @Resource(name = "cmsLayoutTypeRSV")
    private ICmsLayoutTypeRSV cmsLayoutTypeRSV;
    @Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
    @Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
    @Resource(name = "cmsTemplateLibRSV")
    private ICmsTemplateLibRSV cmsTemplateLibRSV;
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    @Resource(name = "cmsPageColorRSV")
    private ICmsPageColorRSV cmsPageColorRSV;
    @Resource(name = "cmsModularComponentRSV")
    private ICmsModularComponentRSV cmsModularComponentRSV;   
    /**
     * 
     * init:(页面初始化). <br/> 
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
    @RequestMapping("/init")
    public String init(Model model,Long pageId,String pageType,String mallskintomanage) throws Exception{
        LogUtil.info(MODULE, "进入页面配置初始化函数 pageId:"+pageId+",pageType="+pageType);
        if(StringUtil.isEmpty(pageId)){//返回错误页面
            throw new Exception("入参页面id为空");
        }
        if(StringUtil.isEmpty(pageType)){//
            pageType = "layout";
        }
        //1.根据页面id查询页面信息
        CmsPageInfoReqDTO infoReqDto = new CmsPageInfoReqDTO();
        infoReqDto.setId(pageId);
        //infoReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsPageInfoRespDTO pageInfo = this.cmsPageInfoRSV.queryCmsPageInfo(infoReqDto);
        
        //2.查询页面对应布局尺寸类型
        if(pageInfo == null || StringUtil.isEmpty(pageInfo.getId()) || CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equalsIgnoreCase(pageInfo.getStatus())){//该页面无效 返回错误页面
            throw new Exception("入参页面id为"+pageId+"无效！");
        }
        List<Integer> itemSizes = qryItemSizeByPgType(pageInfo.getPageTypeId());
        
        //3.查询页面属性
        CmsPageColorReqDTO pageColorReq=new CmsPageColorReqDTO();
        pageColorReq.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsPageColorRespDTO> pageColorList=this.cmsPageColorRSV.queryCmsPageColorList(pageColorReq);
        
        CmsPageAttrPreRespDTO pageAttrPre = this.qryPageAttrPreByPgId(pageId);
        for (CmsPageColorRespDTO respPageColor: pageColorList) {
			if(respPageColor.getId().toString().equals(pageAttrPre.getMatchingColour())){
				 model.addAttribute("respPageColor",respPageColor);
			}
		}
        model.addAttribute("itemSizes",itemSizes);
        model.addAttribute("pageAttrPre",pageAttrPre);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("pageColorList",pageColorList);
        model.addAttribute("pageType",pageType);
        model.addAttribute("mallskintomanage", mallskintomanage);
        //WEB端页面
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(pageInfo.getPlatformType())){
        	return URL+"pageConfig-index";
        }else{//移动端页面
        	return WAP_URL+"pageConfig-index-wap";
        }
    }
    /**
     * 
     * qryModular:(查询模块). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param model
     * @param modularVo
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/qryModular")
    @ResponseBody
    public Map<String,Object> qryModular(Model model,CmsModularVO modularVo){
        if(modularVo == null){
            modularVo = new CmsModularVO();
        }
        LogUtil.info(MODULE, "进入查询模块qryModular:"+modularVo.toString());
        Map<String,Object> returnInfo = new HashMap<String, Object>();
        List<CmsModularRespDTO> modulars = new ArrayList<CmsModularRespDTO>();
        List<CmsModularComponentRespDTO> modularComponentRespDTOList = null;
        //1. 设置查询参数
        CmsModularComponentReqDTO modularComponentReqDTO = new CmsModularComponentReqDTO();
        if(StringUtil.isNotBlank(modularVo.getApplyItemSize())){//模块适用布局尺寸
            modularComponentReqDTO.setApplyItemSize(modularVo.getApplyItemSize());
        }
        if(StringUtil.isNotBlank(modularVo.getApplyPageType())){//模块适用布局尺寸页面类型
            modularComponentReqDTO.setApplyPageType(modularVo.getApplyPageType());
        }
        modularComponentReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        try {
            modularComponentRespDTOList = cmsModularComponentRSV.queryCmsModularComponentList(modularComponentReqDTO);
            if(CollectionUtils.isNotEmpty(modularComponentRespDTOList)){
                for (CmsModularComponentRespDTO dto:modularComponentRespDTOList) {
                    CmsModularReqDTO reqDto = new CmsModularReqDTO();
                    reqDto.setId(dto.getModularId());
                    reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
                    List<CmsModularRespDTO> modularRespDTOList = this.cmsModularRSV.queryCmsModularList(reqDto);
                    if(CollectionUtils.isNotEmpty(modularRespDTOList)){
                        CmsModularRespDTO modularRespDTO = modularRespDTOList.get(0);
                        if(modularRespDTO!=null && StringUtil.isNotEmpty(modularRespDTO.getId())){
                            modularRespDTO.setShowPic(new AiToolUtil().genImageUrl(modularRespDTO.getShowPic(),""));
                            modularRespDTO.setModularComponentRespDTO(dto);
                            /*modularRespDTO.setApplyPageType(dto.getApplyPageType());
                            modularRespDTO.setApplyItemSize(dto.getApplyItemSize());
                            modularRespDTO.setModularClass(dto.getModularClass());
                            modularRespDTO.setComponentId(dto.getComponentId());*/
                            modulars.add(modularRespDTO);
                        }
                    }
                }
            }
            returnInfo.put("msg", "1");
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块qryModular异常",e);
            returnInfo.put("msg", "0");
        }
        returnInfo.put("modulars", modulars);
        return returnInfo;
    }
    
    /**
     * 
     * isExistTemplateName:(判断模板名称是否存在). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/isExistTemplateName")
    @ResponseBody
    public Map<String,Object> isExistTemplateName(CmsTemplateLibReqVO vo)throws Exception{
        if(vo == null){
            vo = new CmsTemplateLibReqVO();
        }
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(vo));
        
        Map<String,Object> returnMap  = new HashMap<String, Object>();
        CmsTemplateLibReqDTO reqDto = new CmsTemplateLibReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        
        if(StringUtil.isBlank(reqDto.getTemplateName())){//名称为空则
            returnMap.put("resultFlag", "00");
            returnMap.put("resultMsg", "模板名称不能为空！");
            return returnMap;
        }
        reqDto.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        
        List<CmsTemplateLibRespDTO> templateLibList = null;
        try {
            templateLibList = this.cmsTemplateLibRSV.queryExactCmsTemplateLibList(reqDto);
        } catch (Exception e) {
            LogUtil.error(MODULE, "判断模板名称是否存在 isExistTemplateName 异常",e);
            returnMap.put("resultFlag", "09");
            returnMap.put("resultMsg", "远程服务发生异常，请刷新页面！");
            return returnMap;
        }
       
        if(CollectionUtils.isNotEmpty(templateLibList)){
            returnMap.put("template", templateLibList.get(0));
            returnMap.put("resultMsg", "\""+reqDto.getTemplateName()+"\""+"与现有模板重名，请重新填写模板名称！");
            returnMap.put("resultFlag", "01");
        }else{
            returnMap.put("resultFlag", "02");
        }
        
        return returnMap;
     }
    /** 
     * saveToTemplate:(保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param CmsTemplateLibReqVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/saveToTemplate")
    @ResponseBody
    public EcpBaseResponseVO saveToTemplate(@Valid CmsTemplateLibReqVO vo, HttpServletRequest request) throws Exception{
    	Long shopId=(Long) request.getSession().getAttribute("shopfishing_");//把店铺id放到session里面
        if(vo == null){
            vo = new CmsTemplateLibReqVO();
        }
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(vo));
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        
        CmsTemplateLibReqDTO reqDto = new CmsTemplateLibReqDTO();
        ObjectCopyUtil.copyObjValue(vo, reqDto, null, false);
        
        if(StringUtil.isBlank(reqDto.getTemplateName()) && StringUtil.isEmpty(reqDto.getPageInfoId())){//名称为空则
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            return respVO;
        }
        if(null==shopId){
        	reqDto.setTemplateType(CmsConstants.TemplateType.CMS_TEMPLATE_TYPE_SYS);
        	reqDto.setShopId(null);
        }else{
        	reqDto.setTemplateType(CmsConstants.TemplateType.CMS_TEMPLATE_TYPE_SHOP);
        	reqDto.setShopId(shopId);
        }
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        reqDto.setIsDefTemplate(CmsConstants.IsNot.CMS_ISNOT_0);
        
        //设置平台类型 为 web端
        if(StringUtil.isBlank(reqDto.getPlatformType())){
        	reqDto.setPlatformType(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01);
        }
        
        try {
            CmsTemplateLibRespDTO template = this.cmsPageConfigRSV.savePageConfigToTemplate(reqDto);
            if(template !=null){
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            }else{
                respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }
            respVO.setResultMsg(shopId==null?"":shopId.toString());
        } catch (Exception e) {
            e.printStackTrace();
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        
        return respVO;
    }
    /** 
     * savePageAttrbutePre:(新增/编辑 保存). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param cmsPlaceVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/savePageAttrbutePre")
    @ResponseBody
    public EcpBaseResponseVO savePageAttrbutePre(@Valid CmsPageAttrReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsPageAttrPreReqDTO reqDTO = new CmsPageAttrPreReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//预览状态
        
        CmsPageAttrPreRespDTO resp = null;
        EcpBaseResponseVO respVO = new EcpBaseResponseVO();
        try {
            if(VO.getId() != null){
                this.cmsPageAttrPreRSV.updateCmsAttrPre(reqDTO);
            }else{
            	resp = this.cmsPageAttrPreRSV.addCmsAttrPre(reqDTO);
            }
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            //新增模式返回ID
            if(resp != null) respVO.setResultMsg(resp.getId().toString());
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存页面属性预览表：savePageAttrbutePre 异常", e);
            respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVO;
    }
    /**
     * 
     * qryPageAttrPreByPgId:(通过页面id获取页面属性预览). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageId
     * @return 
     * @since JDK 1.6
     */
    private CmsPageAttrPreRespDTO qryPageAttrPreByPgId(Long pageId) throws Exception{
        CmsPageAttrPreRespDTO pageAttrPre = null;
        if(StringUtil.isNotEmpty(pageId)){
            CmsPageAttrPreReqDTO reqDto = new CmsPageAttrPreReqDTO();
            reqDto.setPageId(pageId);
            
            List<CmsPageAttrPreRespDTO> pageAttrPres = null;
            pageAttrPres = this.cmsPageAttrPreRSV.queryCmsAttrPreList(reqDto);
            if(CollectionUtils.isNotEmpty(pageAttrPres)){
                pageAttrPre = pageAttrPres.get(0);
            }
            //扩展数据
            if(pageAttrPre !=null ){
                //扩展背景图展示方式
                if(StringUtil.isBlank(pageAttrPre.getBackgroupShowType())){//空则设置为不平铺
                    pageAttrPre.setBackgroupShowType(CmsConstants.BackgroupShowType.CMS_BACKGROUPSHOWTYPE_02);
                }
            }
        }
        return pageAttrPre;
    }
    /**
     * 
     * qryItemSizeByPgId:(通过页面id获取页面所属的布局尺寸类型). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param pageId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private List<Integer> qryItemSizeByPgId(Long pageId) throws Exception{
        List<Integer> itemSizes = null;
        if(StringUtil.isEmpty(pageId)){//没有页面id  则返回空集合
            return new ArrayList<Integer>();
        }
        //1.查询页面信息。
        CmsPageInfoReqDTO infoReqDto = new CmsPageInfoReqDTO();
        infoReqDto.setId(pageId);
        infoReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsPageInfoRespDTO pageInfo = this.cmsPageInfoRSV.queryCmsPageInfo(infoReqDto);
        
        //2.查询 页面拥有的布局尺寸类型
        if(pageInfo !=null && StringUtil.isNotEmpty(pageInfo.getId())){//该页面有效
            itemSizes = qryItemSizeByPgType(pageInfo.getPageTypeId());
        }
        
        //返回数据
        if(itemSizes == null){
            itemSizes = new ArrayList<Integer>();
        }
        return itemSizes;
    }

    /**
     * 
     * qryMeasTypeByPgType:(通过页面类型获取所属的布局尺寸类型). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param pageType
     * @return 
     * @since JDK 1.6
     */
    private List<Integer> qryItemSizeByPgType(Long pageTypeId)throws Exception {
        List<Integer> itemSizes = null;
        if(StringUtil.isEmpty(pageTypeId)){//没有页面类型  则返回空集合
            return new ArrayList<Integer>();
        }
        
        Set<Integer> itemSizeSet= new TreeSet<Integer>(new Comparator<Integer>() { 
            @Override //实现倒序
            public int compare(Integer int1, Integer int2) {
               return int2.compareTo(int1); 
            }
            });
        
        //1 查询布局类型
        CmsLayoutTypeReqDTO reqDTO = new CmsLayoutTypeReqDTO();
        reqDTO.setPageTypeId(pageTypeId);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        List<CmsLayoutTypeRespDTO> layoutTypes = this.cmsLayoutTypeRSV.queryCmsLayoutTypeList(reqDTO);
        
        //2 转化布局中的布局尺寸类型
        if(CollectionUtils.isNotEmpty(layoutTypes)){
            for(CmsLayoutTypeRespDTO dto : layoutTypes){
                itemSizeSet.addAll(this.parseInteger(dto.getLayoutItemSize()));
            }
        }
        
        itemSizes = new ArrayList<Integer>(itemSizeSet);
        return itemSizes;
    }
    
    /**
     * 
     * parseInteger:(将字符串转化为List<Integer> 格式：“23|232|243”). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private List<Integer> parseInteger(String str) throws Exception {
        List<Integer> ints = new ArrayList<Integer>();
        if(StringUtil.isNotBlank(str)){
            String[] strs = str.split("\\|");
            if(StringUtil.isNotEmpty(strs)){
                for(String s : strs){
                    if(this.isNumeric(s)){
                        ints.add(Integer.parseInt(s));
                    }
                }
            }
        }
        
        return ints;
    }
    /**
     * 
     * isNumeric:(判断字符串是否为纯数字). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param str
     * @return 
     * @since JDK 1.6
     */
    private boolean isNumeric(String str)throws Exception{
        if(StringUtil.isNotEmpty(str)){
            Pattern pattern = Pattern.compile("[0-9]*"); 
            Matcher isNum = pattern.matcher(str);
            if( isNum.matches() ){
                return true; 
            }
        }
         
        return false; 
     }
    
    /**
     * 
     * 功能描述：发布页面
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2016年5月23日 下午8:11:33</p>
     *
     * @param model
     * @param pageId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping(value="/publishTemplate")
    @ResponseBody
    public EcpBaseResponseVO publishTemplate(Model model,Long pageId,String platformType, HttpServletRequest request) throws Exception{
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	try {
    		CmsPageInfoReqDTO req = new CmsPageInfoReqDTO();
    		String qrcodeUrl=null;
    		String qrcodePic=null;
    		req.setId(pageId);
    		req.setPlatformType(platformType);
    		//wap 生成二维码图片
    		if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_03.equals(req.getPlatformType())){
    			String lastIndexUrl="/homepage";
    			CmsPageInfoRespDTO pageInfoRespDto=this.cmsPageInfoRSV.queryCmsPageInfo(req);
    			if(50L==pageInfoRespDto.getPageTypeId()){
    				lastIndexUrl="/homepage";
    			}else if(51L==pageInfoRespDto.getPageTypeId()){
    				lastIndexUrl="/modularcommon/prom/"+pageInfoRespDto.getId();
    			}
    			qrcodeUrl= BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(pageInfoRespDto.getSiteId()))+lastIndexUrl;  
    			qrcodePic=this.getQRCode(qrcodeUrl, request);
    			req.setQrcodePic(qrcodePic);
    			req.setPageTypeId(pageInfoRespDto.getPageTypeId());
    			qrcodePic=ImageUtil.getImageUrl(qrcodePic+"_"+"250x250!");
    		}
    		boolean result = cmsPageConfigRSV.savePageConfigPub(req);
    		if(result){
    			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    			if(null!=qrcodePic){
    				List<String> list=new ArrayList<>();
    				list.add(qrcodeUrl);
    				list.add(qrcodePic);
    				vo.setResultMsg(JSONObject.toJSONString(list));
    			}
    		}else{
    			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}    	
    	return vo;
    }
    /**
     * 二维码图片
     * @param url
     * @param request
     * @return
     */
    @SuppressWarnings({ "deprecation" })
	private String getQRCode(String url, HttpServletRequest request) {
    	String appId=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_APP_ID);
		String response_type=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_RESPONSE_TYPE);
		String scope=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_SCOPE);
		String state=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_STATE);
		String connect_redirect=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG,CmsConstants.ParamConfig.WECHAT_QRCODE_CONFIG_CONNECT_REDIRECT);
		url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri="+url
				+"&response_type="+response_type+"&scope="+scope+"&state="+state+"&connect_redirect="+connect_redirect;
		try {
			File imageTempFile = new File(request.getRealPath("/")
					+ File.separator + "pageconfig_qrcode.jpg");
			MatrixToImageWriter.encode(url, 300, 300, imageTempFile);
			InputStream in = new FileInputStream(imageTempFile);
			byte[] datas = ConstantTool.inputStream2Bytes(in);
			String vfsId = ImageUtil.upLoadImage(datas, "image");
			imageTempFile.delete();
			return vfsId;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
