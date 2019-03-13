package com.zengshi.ecp.busi.prom.groupchk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.prom.groupchk.vo.PromChkVO;
import com.zengshi.ecp.busi.prom.groupchk.vo.QueryPromGroupChkVO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkRespPageDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromGroupChkDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

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
@RequestMapping(value="/promgroupchk")
public class PromGroupChkController extends EcpBaseController {
    /**
     * 平台主题促销审核
     */
    private static String MODULE = PromGroupChkController.class.getName();
    
    @Resource
    private IPromGroupChkRSV promGroupChkRSV;
    
    @Resource
	private IPromQueryRSV promQueryRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(){
        return "/prom/groupChk/groupChk-grid";
    }
    
    /**
     * 主题促销页面查询
     * @author huanghe5
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value="/queryChktheme")
    @ResponseBody
    public Model querypromThemeList(Model model,@ModelAttribute("PromChkResponse")QueryPromGroupChkVO vo)throws Exception{
        
    	LogUtil.info(MODULE, "主题促销审核页面查询,入参:{vo="+vo.toString()+"}");
    	
    	  // 后场服务所需要的DTO；
    	QueryPromGroupChkDTO queryPromGroupChkDTO = vo.toBaseInfo(QueryPromGroupChkDTO.class);

    	//1.封装后场入参对象  
    	ObjectCopyUtil.copyObjValue(vo, queryPromGroupChkDTO, "", false);
    	
    	//2.调用后场服务
    	PageResponseDTO<PromChkRespPageDTO> promGroupPage = promGroupChkRSV.listPromInfoByPromGroup(queryPromGroupChkDTO);
    	//3.封装后场数据
    	
    	if(CheckPageNull.checkPageNull(promGroupPage)){
    	    
    		List<PromChkRespPageDTO> list = promGroupPage.getResult();
    		
    		List<PromChkRespPageDTO> result=new ArrayList<PromChkRespPageDTO>();
    		
    		ShopInfoResDTO  shopDTO=new ShopInfoResDTO ();
    		
    		for (PromChkRespPageDTO promChkResponseDTO : list) {
    		    
    			shopDTO=shopInfoRSV.findShopInfoByShopID(promChkResponseDTO.getShopId());
    			
    			if(shopDTO!=null){
    				promChkResponseDTO.setShopName(shopDTO.getShopName());
    			}
    			result.add(promChkResponseDTO);
        	}
    		
    		promGroupPage.setResult(result);
    		
    	}
    	EcpBasePageRespVO<Map> respVO  = EcpBasePageRespVO.buildByPageResponseDTO(promGroupPage);
    	
    	//4.返回
    	return super.addPageToModel(model, respVO);
    	
    }
    
    /**
     * 
     * add:编辑页面；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/add")
    public String add(Model model){
        
    	LogUtil.info(MODULE, "主题促销审核新增页面");
    	
    	PromChkVO promChkGroup = new PromChkVO();
    	model.addAttribute("promChkGroup",promChkGroup);
    	
    	return "/prom/groupChk/groupChk-form";
    }
    
    /**
     * 
     * save:保存页面；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/save")
    @ResponseBody
    public EcpBaseResponseVO save(Model model,PromChkVO vo){
        
    	LogUtil.info(MODULE, "主题促销审核页面查询,入参:{vo="+vo.toString()+"}");
    	
    	PromChkVO promChkGroup = new PromChkVO();
    	model.addAttribute("promChkGroup",promChkGroup);
    	
    	EcpBaseResponseVO ERvo = new EcpBaseResponseVO();
    	
    	PromChkDTO promChkDTO = new PromChkDTO();
    	
    	ObjectCopyUtil.copyObjValue(vo, promChkDTO, "", false);
    	
    	try {
    	    
    		promGroupChkRSV.savePromGroupChk(promChkDTO);
    		ERvo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);//成功
			//成功返回页面信息
	    	return ERvo;
		} catch (BusinessException err) {
			LogUtil.error(MODULE, "promgroupchk.save 报错了啦", err);
			ERvo.setResultMsg(err.getErrorMessage());
			ERvo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		}
    	return ERvo;
    }
    /**
     * 
     * addChk:添加审核信息；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/addChk")
    public String addChk(Model model){
        
    	PromChkVO promChkGroup = new PromChkVO();
    	model.addAttribute("promChkGroup",promChkGroup);
    	
    	return "/prom/groupChk/promChk-form";
    }
    /**
	 * 共用审核店铺促销信息DTO
	 * @param id
	 * @return
	 */
	private PromInfoDTO queryPromInfo(String id){
	    
		if(StringUtil.isBlank(id)){
			LogUtil.error(MODULE, "店铺促销信息查询出错");
			throw new BusinessException("web.prom.400025");
		}
		
		Long rid = Long.valueOf(id);
		PromInfoDTO rdto = new PromInfoDTO();
		rdto.setId(rid);
		
		PromInfoDTO pdto = promQueryRSV.queryPromInfo(rdto);
		
		return pdto;
	}
}


