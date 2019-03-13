package com.zengshi.ecp.busi.main.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.main.vo.InfoScrollObjVO;
import com.zengshi.ecp.busi.main.vo.InfoScrollResultVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorGdsRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsInfoRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageAttrPubRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageConfigRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPageInfoRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**微信商城-信信模块（人卫快报）
 * Title: ECP <br>
 * Project Name:ecp-web-mobile <br>
 * Description: <br>
 * Date:2016年8月16日下午5:32:01  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yedw
 * @version  
 * @since JDK 1.6 
 */  
@Controller
@RequestMapping(value = "/info")
public class InfoController extends EcpBaseController {

    private static String MODULE = InfoController.class.getName();

    @Resource(name = "cmsPageInfoRSV")
    private ICmsPageInfoRSV cmsPageInfoRSV;
    
    @Resource(name = "cmsPageAttrPubRSV")
    private ICmsPageAttrPubRSV cmsPageAttrPubRSV;
    
    @Resource(name = "cmsPageConfigRSV")
    private ICmsPageConfigRSV cmsPageConfigRSV;
    
    @Resource(name = "cmsFloorGdsRSV")
    private ICmsFloorGdsRSV cmsFloorGdsRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource(name = "cmsInfoRSV")
    private ICmsInfoRSV cmsInfoRSV;
    
    
    /**
     * 
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/infoList")
    public String infoList(Model model,HttpSession session) {
    	String url = "/main/info/info-list";// 返回页面
    	try {
    		CmsInfoReqDTO reqDTO=new CmsInfoReqDTO();
    		reqDTO.setSiteId(reqDTO.getCurrentSiteId());
            //设置有效
            reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
            //设置发布截至时间
            reqDTO.setThisTime(DateUtil.getSysDate());
            //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
            PageResponseDTO<CmsInfoRespDTO> pageInfo = this.cmsInfoRSV.queryCmsInfoPage(reqDTO);
            EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
            super.addPageToModel(model, respVO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return url;
    }
    /**
     * 
     * @param model
     * @param reqDTO
     * @return
     */
    @RequestMapping("/infoDetail")
    public String infoDetail(Model model,CmsInfoReqDTO reqDTO) {
    	model.addAttribute("isWap", true);
    	return this.infoDetail4App(model, reqDTO);
    }
    /**
     * 
     * @param model
     * @param reqDTO
     * @return
     */
    @RequestMapping("/infoDetail4App")
    public String infoDetail4App(Model model,CmsInfoReqDTO reqDTO) {
    	String url = "/main/info/info-detail";// 返回页面
    	try {
    		CmsInfoRespDTO respVO=this.cmsInfoRSV.queryCmsInfo(reqDTO);
    		// 静态文件
    		if (respVO != null && StringUtils.isNotBlank(respVO.getStaticId())) {
    			respVO.setStaticUrl(ImageUtil.getStaticDocUrl(respVO.getStaticId(), "html"));
    		}
    		// 附件
    		if (respVO != null && StringUtils.isNotBlank(respVO.getVfsId())) {
    			respVO.setVfsUrl(ImageUtil.getStaticDocUrl(respVO.getVfsId(), "doc"));
    		}

    		model.addAttribute("respVO", respVO);
    	} catch (Exception e) {
    		// TODO: handle exception
    		e.printStackTrace();
    	}
    	return url;
    }
    
    @RequestMapping(value="/data")
    @ResponseBody
    public InfoScrollResultVO data(Model model, EcpBasePageReqVO coupVO,int page)
    {
    	coupVO.setPageNumber(page);
    	InfoScrollResultVO resultVo = new InfoScrollResultVO();
        CmsInfoReqDTO reqDTO = coupVO.toBaseInfo(CmsInfoReqDTO.class);
        // 0:已过期 1:可使用 2:已使用  3:已冻结
        reqDTO.setSiteId(reqDTO.getCurrentSiteId());
        //设置有效
        reqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
        //设置发布截至时间
        reqDTO.setThisTime(DateUtil.getSysDate());
        //reqDTO.setThisTime(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
      
        PageResponseDTO<CmsInfoRespDTO> pageInfo = this.cmsInfoRSV.queryCmsInfoPage(reqDTO);
        if (pageInfo != null && CollectionUtils.isNotEmpty(pageInfo.getResult())) {
            resultVo.setDatas(new ArrayList<InfoScrollObjVO>(pageInfo.getResult().size()));
            for(CmsInfoRespDTO  info : pageInfo.getResult())
            {
            	InfoScrollObjVO infoObj=new InfoScrollObjVO();
            	infoObj.setId(info.getId());
            	infoObj.setInfoTitle(info.getInfoTitle());
            	infoObj.setPubTime(DateUtil.getDateString(info.getPubTime(), "yyyy-MM-dd"));
                resultVo.getDatas().add(infoObj);
            }
        }
        resultVo.setTotal(pageInfo.getPageCount());
        return resultVo;
    }
}
