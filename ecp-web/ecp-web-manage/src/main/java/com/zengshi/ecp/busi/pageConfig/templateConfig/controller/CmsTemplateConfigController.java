package com.zengshi.ecp.busi.pageConfig.templateConfig.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsModularVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularComponentRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsTemplateLibRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularComponentRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsTemplateLibRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Controller
@RequestMapping(value="/templateConfig")
public class CmsTemplateConfigController  extends EcpBaseController {
    
    private static String MODULE = CmsTemplateConfigController.class.getName();
    
    private String URL = "/pageConfig/templateConfig/";//返回模板的基本路径
    private String WAP_URL = "/pageConfig/templateConfigWap/";//返回页面的基本路径
    
    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    @Resource(name = "cmsPageTypeRSV")
    private ICmsPageTypeRSV cmsPageTypeRSV;
    @Resource(name = "cmsLayoutTypeRSV")
    private ICmsLayoutTypeRSV cmsLayoutTypeRSV;
    @Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
    @Resource(name = "cmsTemplateLibRSV")
    private ICmsTemplateLibRSV cmsTemplateLibRSV;
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    @Resource(name = "cmsModularComponentRSV")
    private ICmsModularComponentRSV cmsModularComponentRSV;  
    
    /**
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
    public String init(Model model,String reqType,Long templateLibId) throws Exception{
        LogUtil.info(MODULE, "进入模板配置初始化函数 pageId:"+templateLibId);
        model.addAttribute("reqType",reqType);
        if(StringUtil.isEmpty(templateLibId)){//返回错误页面
            throw new Exception("入参模板id为空");
        }
        //1.根据模板id查询模板信息
        CmsTemplateLibReqDTO infoReqDto = new CmsTemplateLibReqDTO();
        infoReqDto.setId(templateLibId);
        //infoReqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        CmsTemplateLibRespDTO templateLib = this.cmsTemplateLibRSV.queryCmsTemplateLib(infoReqDto);
        
        //2.查询模板对应布局尺寸类型
        if("edit".equalsIgnoreCase(reqType)){
            if(templateLib == null || 
                    StringUtil.isEmpty(templateLib.getId()) || 
                    CmsConstants.ParamStatus.CMS_PARAMSTATUS_2.equalsIgnoreCase(templateLib.getStatus())){//该模板无效 返回错误模板
                throw new Exception("入参模板id为"+templateLibId+"无效！");
            }
        }
        List<Integer> itemSizes = qryItemSizeByPgType(templateLib.getPageTypeId());
        
        model.addAttribute("itemSizes",itemSizes);
        model.addAttribute("templateLib",templateLib);
        
        //WEB端页面
        if(CmsConstants.PlatformType.CMS_PLATFORMTYPE_01.equals(templateLib.getPlatformType())){
            return URL+"templateConfig-index";
        }else{//移动端页面
            return WAP_URL+"templateConfig-index-wap";
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
    public Map<String,Object> qryModular(Model model,CmsModularVO modularVo){
        if(modularVo == null){
            modularVo = new CmsModularVO();
        }
        LogUtil.info(MODULE, "进入查询模块qryModular:"+modularVo.toString());
        Map<String,Object> returnInfo = new HashMap<String, Object>();
        List<CmsModularRespDTO> modularList = new ArrayList<CmsModularRespDTO>();
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
            //modulars = this.cmsModularRSV.queryCmsModularList(reqDto);
            modularComponentRespDTOList = cmsModularComponentRSV.queryCmsModularComponentList(modularComponentReqDTO);
            if(CollectionUtils.isNotEmpty(modularComponentRespDTOList)){
                for (CmsModularComponentRespDTO dto:modularComponentRespDTOList) {
                    CmsModularReqDTO reqDto = new CmsModularReqDTO();
                    reqDto.setId(dto.getModularId());
                    CmsModularRespDTO modularRespDTO = this.cmsModularRSV.queryCmsModular(reqDto);
                    if(modularRespDTO != null){
                        modularRespDTO.setShowPic(new AiToolUtil().genImageUrl(modularRespDTO.getShowPic(),""));
                        modularRespDTO.setModularComponentRespDTO(dto);
                        /*modularRespDTO.setApplyPageType(dto.getApplyPageType());
                        modularRespDTO.setApplyItemSize(dto.getApplyItemSize());
                        modularRespDTO.setModularClass(dto.getModularClass());
                        modularRespDTO.setComponentId(dto.getComponentId());*/
                        modularList.add(modularRespDTO);
                    }
                }
            }
            returnInfo.put("msg", "1");
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块qryModular异常",e);
            returnInfo.put("msg", "0");
        }
        returnInfo.put("modulars", modularList);
        return returnInfo;
    }
    /*public Map<String,Object> qryModular(Model model,CmsModularVO modularVo){
        if(modularVo == null){
            modularVo = new CmsModularVO();
        }
        LogUtil.info(MODULE, "进入查询模块qryModular:"+modularVo.toString());
        Map<String,Object> returnInfo = new HashMap<String, Object>();
        List<CmsModularRespDTO> modulars = null;
        //1. 设置查询参数
        CmsModularReqDTO reqDto = new CmsModularReqDTO();
        if(StringUtil.isNotBlank(modularVo.getApplyItemSize())){//模块适用布局尺寸
            reqDto.setApplyItemSize(modularVo.getApplyItemSize());
        }
        if(StringUtil.isNotBlank(modularVo.getApplyPageType())){//模块适用布局尺寸页面类型
            reqDto.setApplyPageType(modularVo.getApplyPageType());
        }
        reqDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        
        try {
            modulars = this.cmsModularRSV.queryCmsModularList(reqDto);
            returnInfo.put("msg", "1");
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询模块qryModular异常",e);
            returnInfo.put("msg", "0");
        }
        modulars = this.cmsModularRSV.queryCmsModularList(reqDto);
        if(CollectionUtils.isNotEmpty(modulars)){
            for (CmsModularRespDTO dto:modulars) {
                dto.setShowPic(new AiToolUtil().genImageUrl(dto.getShowPic(),""));
            }
        }
        
        returnInfo.put("modulars", modulars);
        return returnInfo;
    }*/

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
        
        Set<Integer> itemSizeSet= new TreeSet<Integer>(new Comparator<Integer>(){ 
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
}
