package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsLayoutItemReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsLayoutReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsUpdateLayoutBatchReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutItemPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutTypeRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutItemPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsLayoutTypeRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping(value="/layoutDesign")
public class CmsLayoutDesignController extends EcpBaseController{
    
    private static String MODULE = CmsLayoutDesignController.class.getName();
    
    private String URL = "/pageConfig/pageConfig/";//返回页面的基本路径
    
    @Resource(name = "cmsLayoutPreRSV")
    private ICmsLayoutPreRSV cmsLayoutPreRSV;
    
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsLayoutTypeRSV")
    private ICmsLayoutTypeRSV cmsLayoutTypeRSV;
    
    @Resource(name = "cmsLayoutItemPreRSV")
    private ICmsLayoutItemPreRSV cmsLayoutItemPreRSV;
    /**
     * 
     * init:(初始化布局装修页面). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/init")
    public String init (Model model ,CmsLayoutReqVO layoutVo){
        if(layoutVo==null){
            layoutVo = new CmsLayoutReqVO();
        }
        LogUtil.info(MODULE, "进入页面布局装修初始化  CmsLayoutReqVO:"+layoutVo.toString());
        String url = URL+"edit/layoutDesign";
        //1.验证必传参数
        if(StringUtil.isEmpty(layoutVo.getPageId())){
            LogUtil.info(MODULE, "===========入参 pageId 为空!============");
            model.addAttribute("errorMsg", "入参 pageId 为空!");
            return url;
        }
        if(StringUtil.isEmpty(layoutVo.getPageTypeId())){
            LogUtil.info(MODULE, "===========入参 pageTypeId 为空!============");
            model.addAttribute("errorMsg", "入参 pageTypeId 为空!");
            return url;
        }
        
        //2.查询页面id 对应的布局
        CmsPageInfoReqDTO reqDto = new CmsPageInfoReqDTO();
        //设置页面id
        reqDto.setId(layoutVo.getPageId());
        Map<String,Object> layoutMap = null;
        try{
            List<CmsLayoutPreRespDTO> layoutpreList = null;
            layoutpreList = this.cmsPageConfigRSV.initPageConfigPre(reqDto); 
            layoutMap = this.extendLayout(layoutpreList);
            model.addAttribute("layoutList", layoutMap.get("layoutList"));
            model.addAttribute("leftLayoutList", layoutMap.get("leftLayoutList"));
            model.addAttribute("rightLayoutList", layoutMap.get("rightLayoutList"));
        } catch (Exception e) {
            LogUtil.error(MODULE, "===========查询页面布局异常============", e);
            model.addAttribute("errorMsg", "查询页面布局异常");
            return url;
        }
       
        //3.根据页面类型  获取布局类型
        CmsLayoutTypeReqDTO layoutTypeReqDTO = new CmsLayoutTypeReqDTO();
        layoutTypeReqDTO.setPageTypeId(layoutVo.getPageTypeId());
        layoutTypeReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        Map<String,Object> layoutTypeMap = null;
        try {
            layoutTypeMap = this.extendLayoutType(this.cmsLayoutTypeRSV.queryCmsLayoutTypeList(layoutTypeReqDTO));
            model.addAttribute("layoutTypeList", layoutTypeMap.get("layoutTypeLists"));
            model.addAttribute("leftLayoutTypeList", layoutTypeMap.get("leftLayoutTypeList"));
            model.addAttribute("rightLayoutTypeList", layoutTypeMap.get("rightLayoutTypeList"));
        } catch (Exception e) {
            LogUtil.error(MODULE, "===========查询页面布局类型异常============", e);
            model.addAttribute("errorMsg", "查询页面布局类型异常");
            return url;
        }
        
        model.addAttribute("pageTypeId", layoutVo.getPageTypeId());
        return url;
    }
    /** 
     * saveLayout:(保存). <br/> 
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
    @RequestMapping(value="/saveLayout")
    @ResponseBody
    public Map<String,Object> saveLayout(CmsLayoutReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsLayoutPreReqDTO reqDTO = new CmsLayoutPreReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//预览状态
        
        List<CmsLayoutItemPreReqDTO> layoutItemPreList = new ArrayList<CmsLayoutItemPreReqDTO>();
        if(CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_03.equalsIgnoreCase(VO.getLayoutShowType()) || CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_04.equalsIgnoreCase(VO.getLayoutShowType())){
            CmsLayoutItemPreReqDTO itemDto = new CmsLayoutItemPreReqDTO();
            itemDto.setItemNo(1);
            itemDto.setRowNo(1);
            itemDto.setItemSize(VO.getLayoutItemSize());
            itemDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
            itemDto.setPageId(reqDTO.getPageId());
            layoutItemPreList.add(itemDto);
        }else{
            if(StringUtil.isNotBlank(VO.getLayoutItemSize())){
                List<Integer> itemSize = this.parseInteger(VO.getLayoutItemSize());
                if(CollectionUtils.isNotEmpty(itemSize)){
                    for(int i =0 ; i< itemSize.size();i++){
                        CmsLayoutItemPreReqDTO itemDto = new CmsLayoutItemPreReqDTO();
                        itemDto.setItemNo(i+1);//列
                        itemDto.setRowNo(1);//行
                        itemDto.setItemSize(itemSize.get(i).toString());
                        itemDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                        itemDto.setPageId(reqDTO.getPageId());
                        layoutItemPreList.add(itemDto);
                    }
                }
            }
        }
        reqDTO.setLayoutItemPreList(layoutItemPreList);
        
        Map<String,Object> respMap = new HashMap<String, Object>();
        try {
            CmsLayoutPreRespDTO respDto = this.cmsLayoutPreRSV.addCmsLayoutPre(reqDTO);
            if(respDto!=null && StringUtil.isNotEmpty(respDto.getId())){
                respMap.put("layout", respDto);
                respMap.put("resultFlag","ok");
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存布局预览表：saveLayout 异常", e);
            respMap.put("resultFlag","error");
        }
        return respMap;
    }
    /** 
     * updateLayout:(更新，只有普通布局会有更新操作). <br/> 
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
    @RequestMapping(value="/updateLayout")
    @ResponseBody
    public Map<String,Object> updateLayout(CmsLayoutReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsLayoutPreReqDTO reqDTO = new CmsLayoutPreReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//未发布状态
        
        //组装布局项
        if(StringUtil.isNotBlank(VO.getLayoutItemSize())){
            List<CmsLayoutItemPreReqDTO> layoutItemPreList = new ArrayList<CmsLayoutItemPreReqDTO>();
            List<Integer> itemSize = this.parseInteger(VO.getLayoutItemSize());
            if(CollectionUtils.isNotEmpty(itemSize)){
                for(int i =0 ; i< itemSize.size();i++){
                    CmsLayoutItemPreReqDTO itemDto = new CmsLayoutItemPreReqDTO();
                    itemDto.setLayoutId(reqDTO.getId());
                    itemDto.setItemNo(i+1);//列
                    itemDto.setRowNo(1);//行
                    itemDto.setItemSize(itemSize.get(i).toString());
                    itemDto.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    itemDto.setPageId(reqDTO.getPageId());
                    layoutItemPreList.add(itemDto);
                }
                reqDTO.setLayoutItemPreList(layoutItemPreList);
            }
        }
        
        Map<String,Object> respMap = new HashMap<String, Object>();
        try {
            CmsLayoutPreRespDTO respDto = this.cmsLayoutPreRSV.updateCmsLayoutPre(reqDTO);
            if(respDto!=null && StringUtil.isNotEmpty(respDto.getId())){
                respMap.put("layout", respDto);
                respMap.put("resultFlag","ok");
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存布局预览表：saveLayout 异常", e);
            respMap.put("resultFlag","error");
        }
        return respMap;
    }
    /** 
     * updateLayoutItem:(更新布局项). <br/> 
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
    @RequestMapping(value="/updateLayoutItem")
    @ResponseBody
    public EcpBaseResponseVO updateLayoutItem(CmsLayoutItemReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsLayoutItemPreReqDTO reqDTO = new CmsLayoutItemPreReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//预览状态
        
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        try {
            this.cmsLayoutItemPreRSV.updateCmsLayoutItemPre(reqDTO);
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (Exception e) {
            LogUtil.error(MODULE, "更新布局项预览表：updateLayoutItem 异常", e);
            respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return respVo;
    }
    /** 
     * saveLayoutItem:(新增布局项). <br/> 
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
    @RequestMapping(value="/saveLayoutItem")
    @ResponseBody
    public CmsLayoutItemPreRespDTO saveLayoutItem(CmsLayoutItemReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        CmsLayoutItemPreReqDTO reqDTO = new CmsLayoutItemPreReqDTO();
        ObjectCopyUtil.copyObjValue(VO, reqDTO, null, true);
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);//预览状态
        
        CmsLayoutItemPreRespDTO respDto = new CmsLayoutItemPreRespDTO();
        if(this.isNumeric(reqDTO.getItemSize())){//如果是悬浮 则不支持新增布局项  悬浮的itemSize为字符 不是数字
            try {
                respDto = this.cmsLayoutItemPreRSV.addCmsLayoutItemPre(reqDTO);
            } catch (Exception e) {
                LogUtil.error(MODULE, "新增布局项预览表：saveLayoutItem 异常", e);
            }
        }
        return respDto;
    }
    /** 
     * updateLayoutItemBatch:(批量更新布局). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param layoutVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/updateLayoutItemBatch")
    @ResponseBody
    public EcpBaseResponseVO updateLayoutItemBatch(CmsLayoutItemReqVO VO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(VO));
        if(VO == null){
            VO = new CmsLayoutItemReqVO();
        }
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        List<CmsLayoutItemPreReqDTO> dtoList = VO.getLayoutItemList();
        
        if(CollectionUtils.isNotEmpty(dtoList)){
            for(int i = 0; i < dtoList.size();i++){
                if(dtoList.get(i)==null || StringUtil.isEmpty(dtoList.get(i).getId())){
                    dtoList.remove(i);
                    i--;
                }else{
                    if(StringUtil.isBlank(dtoList.get(i).getStatus())){
                        dtoList.get(i).setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    }
                }
            }
            
            try {
                if(this.cmsLayoutItemPreRSV.updateCmsLayoutItemPreBatch(dtoList)){
                    respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
                }else{
                    respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
                }
            } catch (Exception e) {
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION); 
            }
        }
        return respVo;
    }
    /** 
     * updateLayoutBatch:(批量更新布局). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param layoutVO
     * @return 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/updateLayoutBatch")
    @ResponseBody
    public EcpBaseResponseVO updateLayoutBatch(CmsUpdateLayoutBatchReqVO layoutVO) throws Exception{
        LogUtil.info(MODULE,"==========" + JSONObject.toJSONString(layoutVO));
        if(layoutVO == null){
            layoutVO = new CmsUpdateLayoutBatchReqVO();
        }
        EcpBaseResponseVO respVo = new EcpBaseResponseVO();
        List<CmsLayoutPreReqDTO> dtoList = layoutVO.getLayoutList();
        
        if(CollectionUtils.isNotEmpty(dtoList)){
            for(int i = 0; i < dtoList.size();i++){
                if(dtoList.get(i)==null || StringUtil.isEmpty(dtoList.get(i).getId())){
                    dtoList.remove(i);
                    i--;
                }else{
                    if(StringUtil.isBlank(dtoList.get(i).getStatus())){
                        dtoList.get(i).setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
                    }
                }
            }
            try {
                if(this.cmsLayoutPreRSV.updateCmsLayoutPreBatch(dtoList)){
                    respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS); 
                }else{
                    respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE); 
                }
            } catch (Exception e) {
                respVo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION); 
            }
        }
        return respVo;
    }
    /**
     * 
     * extendLayout:(将页面布局按展示类型分开). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param initPageConfig
     * @return 
     * @since JDK 1.6
     */
    private Map<String, Object> extendLayout(List<CmsLayoutPreRespDTO> layoutpreList) {
        Map<String, Object> layoutMap = new HashMap<String, Object>();
        List<CmsLayoutPreRespDTO> layoutList = new ArrayList<CmsLayoutPreRespDTO>();;
        List<CmsLayoutPreRespDTO> leftLayoutList = new ArrayList<CmsLayoutPreRespDTO>();
        List<CmsLayoutPreRespDTO> rightLayoutList = new ArrayList<CmsLayoutPreRespDTO>();
       
        if(CollectionUtils.isNotEmpty(layoutpreList)){
            for(CmsLayoutPreRespDTO dto : layoutpreList){
                CmsLayoutTypeRespDTO layoutType = dto.getCmsLayoutTypeRespDTO();
                if(layoutType != null && StringUtil.isNotEmpty(layoutType.getId()) /*&& StringUtil.isNotBlank(layoutType.getLayoutShowType())*/){
                    if(CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_03.equalsIgnoreCase(layoutType.getLayoutShowType())){//左悬浮
                        leftLayoutList.add(dto);
                    }else if(CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_04.equalsIgnoreCase(layoutType.getLayoutShowType())){//右悬浮
                        rightLayoutList.add(dto);
                    }else{
                        layoutList.add(dto);
                    }
                }
            }
        }
        
        layoutMap.put("layoutList", layoutList);
        layoutMap.put("leftLayoutList", leftLayoutList);
        layoutMap.put("rightLayoutList", rightLayoutList);
        return layoutMap;
    }
    /**
     * 
     * extendLayoutType:(将页面布局类型封装成三维数组). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param queryCmsLayoutTypeList
     * @return 
     * @throws Exception 
     * @since JDK 1.6
     */
    private Map<String,Object> extendLayoutType(List<CmsLayoutTypeRespDTO> layoutTypeList) throws Exception {
        Map<String,Object> layoutTypeMap = new HashMap<String, Object>();
        List<List<CmsLayoutTypeRespDTO>> layoutTypeLists = null;
        List<CmsLayoutTypeRespDTO> leftLayoutTypeList = new ArrayList<CmsLayoutTypeRespDTO>();
        List<CmsLayoutTypeRespDTO> rightLayoutTypeList = new ArrayList<CmsLayoutTypeRespDTO>();
        
        if(CollectionUtils.isNotEmpty(layoutTypeList)){
            List<CmsLayoutTypeRespDTO> layoutTypes = new ArrayList<CmsLayoutTypeRespDTO>();
            
            //布局单元个数类型集合
            Set<Integer> itemNumSet= new TreeSet<Integer>(new Comparator<Integer>() { 
                @Override //实现正序
                public int compare(Integer int1, Integer int2) {
                   return int1.compareTo(int2); 
                }
                });
            //转化布局单元
            for(int i = 0;i < layoutTypeList.size();i++){ 
                CmsLayoutTypeRespDTO dto = layoutTypeList.get(i);
                if(CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_03.equalsIgnoreCase(dto.getLayoutShowType())){//左浮动
                    leftLayoutTypeList.add(dto);
                    layoutTypeList.remove(i);
                    i--;
                }else if(CmsConstants.LayoutShowType.CMS_LAYOUTSHOWTYPE_04.equalsIgnoreCase(dto.getLayoutShowType())){
                    rightLayoutTypeList.add(dto);
                    layoutTypeList.remove(i);
                    i--;
                }else{
                    dto.setLayoutItemList(this.parseInteger(dto.getLayoutItemSize()));
                    if(CollectionUtils.isNotEmpty(dto.getLayoutItemList())){
                        layoutTypes.add(dto);
                        itemNumSet.add(dto.getLayoutItemList().size());
                    }
                }
            }
            
            //根据布局项个数分组
            if(CollectionUtils.isNotEmpty(itemNumSet)){
                List <Integer> itemsNumList= new ArrayList<Integer>(itemNumSet);
                itemNumSet = null;//释放
                //初始化 layoutTypeLists
                layoutTypeLists = new ArrayList<List<CmsLayoutTypeRespDTO>>(itemsNumList.size());
                for(int i = 0;i < itemsNumList.size(); i ++){
                    layoutTypeLists.add(new ArrayList<CmsLayoutTypeRespDTO>());
                }
                
                for(CmsLayoutTypeRespDTO dto : layoutTypes){
                    int listIndex = itemsNumList.indexOf(dto.getLayoutItemList().size());
                    layoutTypeLists.get(listIndex).add(dto);
                }
            }
             
        }
        
        if(layoutTypeLists == null){
            layoutTypeLists = new ArrayList<List<CmsLayoutTypeRespDTO>>();
        }
        layoutTypeMap.put("layoutTypeLists", layoutTypeLists);
        layoutTypeMap.put("leftLayoutTypeList", leftLayoutTypeList);
        layoutTypeMap.put("rightLayoutTypeList", rightLayoutTypeList);
        return layoutTypeMap;
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

