package com.zengshi.ecp.busi.main.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.main.vo.CmsInfoVO;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**商城-行业资讯
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月17日下午6:54:49 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author jiangzh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/info")
public class InfoController extends EcpBaseController {

    private static String MODULE = InfoController.class.getName();
    
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    /**
     * qryInfoList:(查询信息列表组件). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/infolistplug")
    @ResponseBody
    public Map<String,Object> infolistplug(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO:" + reqVO.toString() + ";");
        
        Map<String,Object> resultMap = new HashMap<String,Object>();
    	List<CmsInfoRespDTO> respList = null;
    	
    	try{
        	// 1. 判断入参
    		if (StringUtil.isNotEmpty(reqVO.getPlaceId())) {
    		    CmsInfoReqDTO reqDTO = new CmsInfoReqDTO();
    			reqDTO.setPlaceId(reqVO.getPlaceId());
    			reqDTO.setThisTime(DateUtil.getSysDate());//设置当前时间
    			//reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
    			if (StringUtil.isNotEmpty(reqVO.getStatus())) {
    				reqDTO.setStatus(reqVO.getStatus());
    			} else {// 默认查有效
    				reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
    			}
    			if (StringUtil.isNotEmpty(reqVO.getPlaceSize())) {
    				reqDTO.setPageNo(1);
    				reqDTO.setPageSize(reqVO.getPlaceSize());
    			} else {// 默认3条
    				reqDTO.setPageNo(1);
    				reqDTO.setPageSize(3);
    			}
    			// 2. 调用信息服务，无分页
    			respList = new ArrayList<CmsInfoRespDTO>();
    			PageResponseDTO<CmsInfoRespDTO> pageInfo = cmsInfoRSV.queryCmsInfoPage(reqDTO);
    			if (pageInfo != null) {
    				respList = pageInfo.getResult();
    			}
    		}
        }catch(Exception err){
            LogUtil.error(MODULE, "查询信息出现异常:",err);
        }
  
        // 4. 返回结果
		resultMap.put("respList", respList);
        return resultMap;
    }
    
    /**
     * infoList:(查询信息列表). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/infolist")
    public String infolist(Model model,CmsInfoVO cmsInfoVO) throws Exception {
        model.addAttribute("placeId", cmsInfoVO.getPlaceId());
        return "/main/info/info-list";
    }
    
    
    /**
     * qryinfolist:(查询信息列表). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author jiangzh
     * @param model
     * @param placeId
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/qryinfolist")
    public String qryinfolist(Model model,CmsInfoVO cmsInfoVO) throws Exception {
        
        CmsInfoReqDTO reqDTO = cmsInfoVO.toBaseInfo(CmsInfoReqDTO.class);
        if (StringUtil.isNotEmpty(cmsInfoVO.getPlaceId())) {
            reqDTO.setPlaceId(cmsInfoVO.getPlaceId());
        }
        //设置有效
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置发布截至时间
        reqDTO.setEndPubTime(DateUtil.getSysDate());
        PageResponseDTO<CmsInfoRespDTO> respDTO = cmsInfoRSV.queryCmsInfoPage(reqDTO);
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(respDTO);
        model.addAttribute("respVO", respVO);
        return "/main/info/child/child-list";
    }
    
    /** 
     * infodetail:(这里用一句话描述这个方法的作用). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wenyf  
     * @since JDK 1.7 
     */
    @RequestMapping(value = "/infodetail")
    public String infodetail(Model model, CmsInfoVO cmsInfoVO) throws Exception{

        /* 1.根据入参调用后场页面信息查询服务 */
        CmsInfoReqDTO cmsInfoDTO = new CmsInfoReqDTO();
        if (StringUtil.isNotEmpty(cmsInfoVO.getId())) {
            cmsInfoDTO.setId(cmsInfoVO.getId());
        }
        try{
            // 静态文件
            CmsInfoRespDTO cmsInfoRespDTO = cmsInfoRSV.queryCmsInfo(cmsInfoDTO);
            if (cmsInfoRespDTO != null && StringUtils.isNotBlank(cmsInfoRespDTO.getStaticId())) {
                cmsInfoRespDTO.setStaticUrl(ImageUtil.getStaticDocUrl(cmsInfoRespDTO.getStaticId(), "html"));
            }
            // 附件
            if (cmsInfoRespDTO != null && StringUtils.isNotBlank(cmsInfoRespDTO.getVfsId())) {
                cmsInfoRespDTO.setVfsUrl(ImageUtil.getStaticDocUrl(cmsInfoRespDTO.getVfsId(), "doc"));
            }
            /* 2.设置页面对象 */
            model.addAttribute("respVO", cmsInfoRespDTO);
        }catch(Exception err){
            LogUtil.error(MODULE, "查询信息详情出现异常:",err);
        }
        /* 3.跳转到页面的路径 */
        return "/main/info/info-detail";
    }

}
