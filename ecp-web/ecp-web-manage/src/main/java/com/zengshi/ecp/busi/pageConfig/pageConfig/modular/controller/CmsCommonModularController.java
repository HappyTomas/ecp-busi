package com.zengshi.ecp.busi.pageConfig.pageConfig.modular.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.velocity.AiToolUtil;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.controller.CmsModularLoadController;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsModularLoadVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorTemplateRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropGroupPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsLayoutPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPageInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorTemplateRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularPropRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants.ParamStatus;
import com.zengshi.ecp.prom.dubbo.dto.KillGdsInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.SecondKillPromRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-manage <br>
 * Description: 公共的模块开发调用的接口<br>
 * Date:2016年6月6日上午9:36:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value="/commonmodular")
public class CmsCommonModularController extends EcpBaseController {
	
	@Resource(name = "promQueryRSV")
	private IPromQueryRSV promQueryRSV;
    @Resource(name = "cmsModularRSV")
    private ICmsModularRSV cmsModularRSV;
    @Resource(name = "cmsPageAttrPreRSV")
    private ICmsPageAttrPreRSV cmsPageAttrPreRSV;
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    @Resource(name = "cmsFloorRSV")
    private ICmsFloorRSV cmsFloorRSV;
    @Resource(name = "cmsModularParaPropRSV")
    private ICmsModularParaPropRSV cmsModularParaPropRSV;
    @Resource
    private ICmsItemPropPreRSV cmsItemPropPreRSV;
    @Resource
    private ICmsModularPropRSV iCmsModularPropRSV;
	@Resource(name = "cmsFloorTemplateRSV")
	private ICmsFloorTemplateRSV cmsFloorTemplateRSV;
    
    private String URL = "/pageConfig/pageConfig/modular/";
    private String WAP_URL = "/pageConfig/pageConfigWap/modular/";
    
    /**
     * 图片扩展名类型正则。
     */
    public static final Pattern IMG_TYPE_PATTERN = Pattern.compile("\\.(jpg)$|\\.(png)$|\\.(jpeg)$|\\.(gif)$|\\.(bmp)$");
    
    private String MODUAL = CmsModularLoadController.class.getName();
    /**
     * 
     * modularCommonLoad:(用于公共调用请求模块). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/modularcommonload")
    public String modularCommonLoad(Model model,CmsModularLoadVO cmsModularLoadVO){
        try {
            CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
            pinfo.setId(cmsModularLoadVO.getPageId());
            List<CmsLayoutPreRespDTO> pageConfig = cmsPageConfigRSV.initPageConfigPre(pinfo);
            CmsModularPropReqDTO prop = new CmsModularPropReqDTO();
            prop.setModularId(cmsModularLoadVO.getModularId());
            List<CmsModularParaPropRespDTO> attrs = iCmsModularPropRSV.queryCmsModularParaPropList(prop);
            Map<Integer,CmsModularParaPropRespDTO> map = new HashMap<Integer,CmsModularParaPropRespDTO>();
            for(CmsModularParaPropRespDTO dto : attrs){
                map.put(dto.getId().intValue(), dto);
            }
            model.addAttribute("attrs", map);
            model.addAttribute("pageConfig", pageConfig);
            CmsItemPropPreReqDTO  req = new CmsItemPropPreReqDTO();
            req.setPageId(cmsModularLoadVO.getPageId());
            req.setItemId(cmsModularLoadVO.getItemId());
            req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
            List<CmsItemPropPreRespDTO> props =null;
            props = cmsItemPropPreRSV.queryCmsItemPropValuePreList(req);
            model.addAttribute("props", props);//楼层导航不需要去重，改用这个属性取值
            if("wap".equals(cmsModularLoadVO.getPlatFormType())){
            	List<CmsItemPropPreRespDTO> propsItem=new ArrayList<CmsItemPropPreRespDTO>();
            	Map<String,CmsItemPropPreRespDTO> mapPropPre = new HashMap<String,CmsItemPropPreRespDTO>();
            	for(CmsItemPropPreRespDTO dto : props){
                	if(null!=dto.getPropGroupId()&&0!=dto.getPropGroupId()&&null!=dto.getControlPropId()&&0!=dto.getControlPropId()){
                		if(null!=dto.getItemPropGroupPreRespDTOList() && dto.getItemPropGroupPreRespDTOList().size()>0){
	                		for (List<CmsItemPropGroupPreRespDTO> groupDtoList: dto.getItemPropGroupPreRespDTOList()) {
	                			if(null!=groupDtoList && groupDtoList.size()>0){
	                				for (CmsItemPropGroupPreRespDTO groupDto:groupDtoList) {
	                					CmsItemPropPreRespDTO itemGroupDto=new CmsItemPropPreRespDTO();
	                					ObjectCopyUtil.copyObjValue(groupDto, itemGroupDto, null, false);
	                					mapPropPre.put(itemGroupDto.getPropId().intValue()+"_"+itemGroupDto.getPropGroupId()+"_"+itemGroupDto.getControlPropId(), itemGroupDto);
	                					propsItem.add(itemGroupDto);
	                				}
	                			}
	    					}
                		}
                	}else{
                		mapPropPre.put(dto.getPropId().intValue()+"_0_0", dto);
                		propsItem.add(dto);
                	}
                }
            	model.addAttribute("propPre", mapPropPre);
            	JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.setIgnoreDefaultExcludes(false);
                jsonConfig.setAllowNonStringKeys(true);
                jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
                String propPreStr = JSONArray.fromObject(propsItem,jsonConfig).toString().replaceAll("\"", "\'");
                 // 先过滤对set集合的拆解
                model.addAttribute("propPreStr", propPreStr);
            }else{
            	Map<Integer,CmsItemPropPreRespDTO> mapPropPre = new HashMap<Integer,CmsItemPropPreRespDTO>();
            	for(CmsItemPropPreRespDTO dto : props){
            		mapPropPre.put(dto.getPropId().intValue(), dto);
            	}
            	JsonConfig jsonConfig = new JsonConfig();
                jsonConfig.setIgnoreDefaultExcludes(false);
                jsonConfig.setAllowNonStringKeys(true);
                jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
                String propPreStr = JSONArray.fromObject(props,jsonConfig).toString().replaceAll("\"", "\'");
                 // 先过滤对set集合的拆解
                model.addAttribute("propPre", mapPropPre);
                model.addAttribute("propPreStr", propPreStr);
            }
            model.addAttribute("pageTypeId", cmsModularLoadVO.getPageTypeId());
            
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "获取数据错误！", e);
        } catch (Exception e) {
            LogUtil.error(MODUAL, "获取数据错误！", e);
        }
        model.addAttribute("pageId", cmsModularLoadVO.getPageId());
        model.addAttribute("modularId", cmsModularLoadVO.getModularId());
        model.addAttribute("itemId", cmsModularLoadVO.getItemId());
        /*if("wap".equals(cmsModularLoadVO.getPlatFormType())){
        	return WAP_URL + cmsModularLoadVO.getRequestVmName();
        }else{
        	return URL + cmsModularLoadVO.getRequestVmName();
        }*/
        return cmsModularLoadVO.getRequestVmName();
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
   	public String goFloorContent(Model model, String templateNo,Long pageId,Long itemId,Long modularId) {
   		String urlType="floor/floorContent-";
   		if(104L==modularId){
   			urlType="floor/floorContent-";
   		}else if(107L==modularId){
   			urlType="floorGds/floorGdsContent-";
   		}
		/* 1.根据入参调用后场楼层模板查询服务 */
		CmsFloorTemplateReqDTO cmsFloortemplateDTO = new CmsFloorTemplateReqDTO();
		cmsFloortemplateDTO.setTemplateNo(Short.parseShort(templateNo));
		cmsFloortemplateDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		CmsFloorTemplateRespDTO floortemplateRespDTO=new CmsFloorTemplateRespDTO();
		List<CmsFloorTemplateRespDTO> list=cmsFloorTemplateRSV.queryCmsFloorTemplateList(cmsFloortemplateDTO);
		if(list!=null&&list.size()>0){
			CmsFloorTemplateReqDTO cmsFloortemplateReqDTO = new CmsFloorTemplateReqDTO();
			cmsFloortemplateReqDTO.setId(list.get(0).getId());
			floortemplateRespDTO=cmsFloorTemplateRSV.queryCmsFloorTemplate(cmsFloortemplateReqDTO);
		}else{
			return  "/pageConfig/pageConfigWap/modular/wap/floor/floorContent-"+templateNo.toString();
		}
   		/* 2.设置页面对象 */
		model.addAttribute("respVO", floortemplateRespDTO);
		if(floortemplateRespDTO != null){
			model.addAttribute("floorPlaceRespDTOList", floortemplateRespDTO.getFloorPlaceRespDTOList());
		}
//        CmsPageInfoReqDTO pinfo = new CmsPageInfoReqDTO();
//        pinfo.setId(pageId);
//        List<CmsLayoutPreRespDTO> pageConfig = cmsPageConfigRSV.initPageConfig(pinfo);
        CmsModularPropReqDTO prop = new CmsModularPropReqDTO();
        prop.setModularId(modularId);
        List<CmsModularParaPropRespDTO> attrs = iCmsModularPropRSV.queryCmsModularParaPropList(prop);
        Map<Integer,CmsModularParaPropRespDTO> map = new HashMap<Integer,CmsModularParaPropRespDTO>();
        for(CmsModularParaPropRespDTO dto : attrs){
            map.put(dto.getId().intValue(), dto);
        }
        model.addAttribute("attrs", map);
        CmsItemPropPreReqDTO  req = new CmsItemPropPreReqDTO();
        req.setPageId(pageId);
        req.setItemId(itemId);
        req.setStatusSet(new HashSet<String>(Arrays.asList(ParamStatus.CMS_PARAMSTATUS_0,ParamStatus.CMS_PARAMSTATUS_1)));
        List<CmsItemPropPreRespDTO> props = cmsItemPropPreRSV.queryCmsItemPropValuePreList(req);
        List<CmsItemPropPreRespDTO> propsItem=new ArrayList<CmsItemPropPreRespDTO>();
    	Map<String,CmsItemPropPreRespDTO> mapPropPre = new HashMap<String,CmsItemPropPreRespDTO>();
    	for(CmsItemPropPreRespDTO dto : props){
        	if(null!=dto.getPropGroupId()&&0!=dto.getPropGroupId()&&null!=dto.getControlPropId()&&0!=dto.getControlPropId()){
        		if(null!=dto.getItemPropGroupPreRespDTOList() && dto.getItemPropGroupPreRespDTOList().size()>0){
            		for (List<CmsItemPropGroupPreRespDTO> groupDtoList: dto.getItemPropGroupPreRespDTOList()) {
            			if(null!=groupDtoList && groupDtoList.size()>0){
            				for (CmsItemPropGroupPreRespDTO groupDto:groupDtoList) {
            					CmsItemPropPreRespDTO itemGroupDto=new CmsItemPropPreRespDTO();
            					ObjectCopyUtil.copyObjValue(groupDto, itemGroupDto, null, false);
            					mapPropPre.put(itemGroupDto.getPropId().intValue()+"_"+itemGroupDto.getPropGroupId()+"_"+itemGroupDto.getControlPropId(), itemGroupDto);
            					propsItem.add(itemGroupDto);
            				}
            			}
					}
        		}
        	}else{
        		mapPropPre.put(dto.getPropId().intValue()+"_0_0", dto);
        		propsItem.add(dto);
        	}
        }
    	model.addAttribute("mapPropPre", mapPropPre); 
           
   		/* 4.返回页面路径 */
   		return  "/pageConfig/pageConfigWap/modular/wap/"+urlType+floortemplateRespDTO.getTemplateNo().toString();
   	}
    /**
     * 
     * openLocationConten:(跳转到内容位置选择页面。). <br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/openlocationconten")
    public String openLocationConten(Model model,HttpServletRequest request){
        if(request.getSession().getAttribute("shopfishing_") != null){
            model.addAttribute("mallskintomanage", true);//卖家中心跳转过来的
        }
        return URL+"open/content-location";
    }
    
    /**
     * 
     * uploadImage:(上传图片). <br/> 
     * @author gxq 
     * @param model
     * @param req
     * @param rep
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/uploadimage")
    @ResponseBody
    protected String uploadImage(@RequestParam(value="detailPic",required=false)MultipartFile file, HttpServletResponse rep) {
        JSONObject obj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        try {
            if (file == null) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择上传文件！");
                LogUtil.info(MODUAL, "请选择上传文件！");
                return obj.toString();
            }
            String fileName = file.getOriginalFilename();
            String extensionName = "." + getExtensionName(fileName);

            /** 支持文件拓展名：.jpg,.png,.jpeg,.gif,.bmp */
            boolean flag = IMG_TYPE_PATTERN.matcher(extensionName.toLowerCase()).find();
            if (!flag) {
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                LogUtil.error(MODUAL, "上传图片失败,原因---请选择图片文件(.jpg,.png,.jpeg,.gif,.bmp)!");
                return obj.toString();
            }
            //判断图片的长宽（像素）暂时不用，请勿删除
            /*BufferedImage bi = ImageIO.read(file.getInputStream());
            int width = bi.getWidth(); 
            int height = bi.getHeight();
            if(width<700 || height <700){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "请上传图片规格为：</br>宽度：≥700像素；</br>高度：≥700像素的图片！");
                LogUtil.error(MODULE, "上传的图片必须按1:1上传！");
                return obj.toJSONString();
            }
            if(width != height){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "上传的图片的宽度和高度像素不一致！");
                LogUtil.error(MODULE, "上传的图片的宽度和高度像素不一致！");
                return obj.toJSONString();
            }*/
            byte[] datas = inputStream2Bytes(file.getInputStream());
            if(datas.length>5*1024*1024){
                obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                obj.put("message", "上传的图片大于5M!");
                LogUtil.error(MODUAL, "图片上传失败，上传的图片必须小于5M!");
                return obj.toString();
            }
            fileName = Math.random()+"";
            String vfsId = ImageUtil.upLoadImage(datas, fileName);
            resultMap.put("vfsId", vfsId);
            resultMap.put("imageName", fileName);
//            resultMap.put("id", id);
            resultMap.put("imagePath", new AiToolUtil().genImageUrl(vfsId,"150x150!"));
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            obj.put("message", "保存成功!");
            obj.put("map", resultMap);
        } catch (Exception e) {
            LogUtil.info(MODUAL,"上传图片出错,原因---"+e.getMessage(), e);
            obj.put("success", EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            obj.put("message", "保存失败，图片服务器异常，请联系管理员!");
        }
        return obj.toString();
    }
    
    /**
     * 
     * inputStream2Bytes:(将InputStream转换成byte数组). <br/> 
     * @author gxq 
     * @param in
     * @return
     * @throws IOException 
     * @since JDK 1.6
     */
    protected byte[] inputStream2Bytes(InputStream in) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int count = -1;
        while ((count = in.read(data, 0, 4096)) != -1)
            outStream.write(data, 0, count);
        data = null;
        return outStream.toByteArray();
    }

    /**
     * 
     * getExtensionName:(获取文件拓展名). <br/> 
     * @author gxq 
     * @param fileName
     * @return 
     * @since JDK 1.6
     */
    protected String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }
    /**
     * getPromTabList: 获取秒杀tab列表
     * @param siteId
     * @return
     */
    @RequestMapping(value = "/getPromTabList")
    @ResponseBody 
    public List<PromInfoResponseDTO> getPromTabList(Long siteId){
    	PromInfoDTO promInfoDTO=new PromInfoDTO();
    	promInfoDTO.setSiteId(siteId);
    	promInfoDTO.setPageNo(1);
    	promInfoDTO.setPageSize(1000);
    	PageResponseDTO<PromInfoResponseDTO> pageResponseDTO= promQueryRSV.listSecondPromInfoForPage(promInfoDTO);
    	return pageResponseDTO.getResult();
    }
    
    /**
     * getSecKillInfo 获取秒杀商品详情列表
     * @param promId
     * @param pageSize
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getSecKillInfo")
    @ResponseBody 
    public SecondKillPromRespDTO getSecKillInfo(Long promId,Integer pageSize,Integer pageNo,String imgType,String isWeb){
    	SecondKillPromRespDTO secondKillPromRespDTO=null;
    	try {
    		PromInfoDTO promInfoDTO=new PromInfoDTO();
    		promInfoDTO.setId(promId);
    		promInfoDTO.setPageNo(pageNo);
    		promInfoDTO.setPageSize(pageSize);
    		secondKillPromRespDTO= promQueryRSV.listSkuOfSecondKillProm(promInfoDTO);
    		List<KillGdsInfoDTO> list=secondKillPromRespDTO.getPage().getResult();
    		if(null!=list&&list.size()>0){
    			for (KillGdsInfoDTO gdsInfoDTO:list) {
    				gdsInfoDTO.getMediaUuid();
    				String imgUrl = ImageUtil.getImageUrl(gdsInfoDTO.getMediaUuid()+imgType);
    				gdsInfoDTO.setURL(imgUrl);
    				if("true".equals(isWeb)){
    					//String detailUrl=BaseParamUtil.fetchParamValue(CmsConstants.ParamConfig.CMS_SITE_MAPPING, String.valueOf(secondKillPromRespDTO.getPromInfoResponseDTO().getSiteId()))+gdsInfoDTO.getDetailURL();
    					String detailUrl=CmsCacheUtil.getCmsSiteCache(secondKillPromRespDTO.getPromInfoResponseDTO().getSiteId()).getSiteUrl()+gdsInfoDTO.getDetailURL();
    					gdsInfoDTO.setDetailURL(detailUrl);
    				} 
    				gdsInfoDTO.setGdsDesc(this.delHTMLTag(gdsInfoDTO.getGdsDesc()));
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    	return secondKillPromRespDTO;
    }
    
    /** 
     * @param htmlStr 
     * @return 
     *  删除Html标签 
     */  
    private String delHTMLTag(String htmlStr) {  
    	if(null==htmlStr||"".equals(htmlStr)){
    		return "";
    	}
    	String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    	String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    	String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    	String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
    	
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
        if(htmlStr.length()>32){
        	htmlStr=htmlStr.substring(0, 32);
        	htmlStr+="...";
        }
        return htmlStr.trim(); // 返回文本字符串  
    }  
}