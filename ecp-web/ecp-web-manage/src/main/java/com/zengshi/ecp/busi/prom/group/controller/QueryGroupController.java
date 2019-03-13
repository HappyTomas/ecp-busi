/** 
 * Project Name:ecp-web-manage 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.prom.group.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.prom.group.vo.PromGroupVO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-14下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/querygroup")
public class QueryGroupController extends EcpBaseController {
    /**
     * 平台促销组功能
     */
    private static String MODULE = QueryGroupController.class.getName();
    
    @Resource
    private IPromGroupRSV promGroupRSV;
    
    /**
     * 
     * init:主题促销页面初始化
     * 
     * @author huangjx 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model,String siteId){
        
    	LogUtil.info(MODULE, "主题促销查询初始化");
    	//站点编码
    	model.addAttribute("siteId", siteId);
        return "/prom/group/query/select-group-grid";
    }
    
    /**
     * 主题促销页面查询
     * @author huangjx
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value="/grid")
    @ResponseBody
    public Model querypromThemeList(Model model,@ModelAttribute("promGroupVO")PromGroupVO vo)throws Exception{
        
    	LogUtil.info(MODULE, "主题促销页面查询,入参:{vo="+vo.toString()+"}");
    	
    	//1.封装后场入参对象
    	PromGroupDTO promGroupDTO  = vo.toBaseInfo(PromGroupDTO.class);
    	
    	ObjectCopyUtil.copyObjValue(vo, promGroupDTO, "", false);
    	
    	promGroupDTO.setStatus(PromConstants.PromGroup.STATUS_1);
    	promGroupDTO.setCalDate(DateUtil.getSysDate());
    	
    	//2.调用后场服务
    	PageResponseDTO<PromGroupResponseDTO> promGroupPage = promGroupRSV.queryPromGroupList(promGroupDTO);
    	EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(promGroupPage);
    	
    	//返回
    	return super.addPageToModel(model, respVO);
    }
     
}


