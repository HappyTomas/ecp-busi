package com.zengshi.ecp.busi.prom.group.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.prom.PromConst;
import com.zengshi.ecp.busi.prom.group.vo.PromGroupVO;
import com.zengshi.ecp.busi.prom.group.vo.PromInfoVO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-prom <br>
 * Description: <br>
 * Date:2015-8-14下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version  
 * @since JDK 1.7 
 */

@Controller
@RequestMapping(value="/promgroup")
public class PromGroupController extends EcpBaseController {
    /**
     * 平台促销组功能
     */
    private static String MODULE = PromGroupController.class.getName();
    
    @Resource
    private IPromGroupRSV promGroupRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    /**
     * 
     * init:主题促销页面初始化
     * 
     * @author huangjx 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model){
    	
    	LogUtil.info(MODULE, "主题促销页面初始化");
    	
    	// 促销组 状态
        List<BaseParamDTO> statusList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_GROUP_STATUS);
        model.addAttribute("statusList", statusList);
    	
        return "/prom/group/group-grid";
    }
    
    /**
     * 主题促销页面查询
     * @author huanghe5
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value="/querypromtheme")
    @ResponseBody
    public Model querypromThemeList(Model model,@ModelAttribute("promGroup")PromGroupVO vo)throws Exception{
    	LogUtil.info(MODULE, "主题促销页面查询,入参:{vo="+vo.toString()+"}");
    	//封装后场入参对象
    	PromGroupDTO promGroupDTO  = vo.toBaseInfo(PromGroupDTO.class);
    	ObjectCopyUtil.copyObjValue(vo, promGroupDTO, "", false);
    	//调用后场服务
    	PageResponseDTO<PromGroupResponseDTO> promGroupPage = promGroupRSV.queryPromGroupList(promGroupDTO);
		
    	//封装后场返回的数据信息
    	//后场服务已经处理，这里优化少循环处理
    /*	if(CheckPageNull.checkPageNull(promGroupPage)){
    		List<PromGroupResponseDTO> list = promGroupPage.getResult();
    		List<PromGroupResponseDTO> result=new ArrayList<PromGroupResponseDTO>();
    		for (PromGroupResponseDTO promGroupResponseDTO : list) {
        		promGroupResponseDTO.setShopCnt((Long) (promGroupResponseDTO.getShopCnt() != null?promGroupResponseDTO.getShopCnt():0));
        	}
    	}*/
    	 EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(promGroupPage);
    	//返回
    	return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * add:新增页面；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/add")
    public String add(Model model,PromGroupVO  groupVO){
    	
    	LogUtil.info(MODULE, "主题促销新增按钮");
    	
    	if(groupVO !=null && groupVO.getId() != null && groupVO.getId()>0 ){
    		model.addAttribute("id",groupVO.getId());
    	}
    	model.addAttribute("promGroup",groupVO);
    	return "/prom/group/group-form-add";
    }
    
    /**
     * 
     * detail:详情页面；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/detail")
    public String detail(Model model,PromGroupVO  groupVO){
        
    	LogUtil.info(MODULE, "主题促销详情页面,入参:{vo="+groupVO.toString()+"}");
    	
    	//1.封装后场入参对象
    	PromGroupDTO promGroupDTO = new PromGroupDTO();
    	if(groupVO != null){
    	    //详情
    		if(groupVO.getId()!= null&&groupVO.getId()>0){
    			promGroupDTO.setId(groupVO.getId());
    			model.addAttribute("type", PromConst.PG_DETAIL);
    		}
    	}
    	//2.调用后场服务
    	PromGroupResponseDTO dto = promGroupRSV.queryPromGroupById(promGroupDTO);
    	
    	//3.返回页面
    	model.addAttribute("promGroup", dto);
    	return "/prom/group/group-form";
    }
    
    /**
     * 
     * edit:编辑页面；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/edit")
    public String edit(Model model,PromGroupVO  groupVO){
        
    	LogUtil.info(MODULE, "主题促销详情页面,入参:{vo="+groupVO.toString()+"}");
    	
    	//1.封装后场入参对象
    	PromGroupDTO promGroupDTO = new PromGroupDTO();
    	if(groupVO != null){
    		if(groupVO.geteId()!= null&&groupVO.geteId()>0){//编辑
    			promGroupDTO.setId(groupVO.geteId());
    			model.addAttribute("id", groupVO.geteId());
    		}
    	}
    	//2.调用后场服务
    	PromGroupResponseDTO dto = promGroupRSV.queryPromGroupById(promGroupDTO);
    	if(dto !=null && "2".equals(dto.getStatus())){
    		model.addAttribute("type", PromConst.PG_Status_2);//如果是草稿模式
    	}
    	
    	//3.返回页面
    	model.addAttribute("promGroup", dto);
    	return "/prom/group/group-form";
    }
    
    /**
     * 失效
     * @author huanghe5
     * @param id
     * @return
     */
    @RequestMapping(value="/valid")
    @ResponseBody
    public EcpBaseResponseVO valid(@RequestParam("id")String id){
        
    	LogUtil.info(MODULE,"失效主题促销信息"+id);
    	
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	try{
    		if(StringUtil.isBlank(id)){
    			throw new BusinessException("web.prom.400023");
    		}
        	//调用后场
        	PromGroupDTO promGroupDTO = new PromGroupDTO();
        	promGroupDTO.setId(Long.valueOf(id));
        	promGroupDTO.setStatus(PromConst.PG_STATUS_0);//无效
        	promGroupRSV.createEditPromGroup(promGroupDTO);
        	
    		//调用发布接口
        	LogUtil.info(MODULE, "失效发布");
        	//调用结束
        	vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        	return vo;
    	}catch(BusinessException e){
    		LogUtil.error(MODULE, "PromGroupController.valid 报错", e);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
    		vo.setResultMsg(e.getMessage());
    	}
    	
    	return vo;
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
    public EcpBaseResponseVO save(@Valid PromGroupVO  vo){
	    
    	LogUtil.info(MODULE, "主题促销保存页面,入参:{vo="+vo.toString()+"}");
    	
    	if(vo != null&& vo.getShowStartTime().after(vo.getShowEndTime())){
			throw new BusinessException("web.prom.400027");
		}
    	
    	//1.封装后场入参
    	PromGroupDTO promGroupDTO  = vo.toBaseInfo(PromGroupDTO.class);
    	ObjectCopyUtil.copyObjValue(vo, promGroupDTO, "", false);
    	
    	EcpBaseResponseVO ERvo = new EcpBaseResponseVO();
    	
    	//2调用后场服务
		if (vo != null) {
			try {
				if (vo.getId()!=null && vo.getId() > 0) {
				    // 编辑
					if("1".equals(vo.getType())){
					    //保存草稿  1:为保存草稿的标识符
						promGroupRSV.saveEditPromGroup(promGroupDTO);
					}else{
					    //提交信息
						promGroupRSV.createEditPromGroup(promGroupDTO);
					}
				} else {
				    // 新增
					if("1".equals(vo.getType())){
					    //保存草稿  1:为保存草稿的标识符
						promGroupRSV.savePromGroup(promGroupDTO);
					}else{
					    //提交信息
						promGroupRSV.createPromGroup(promGroupDTO);
					}
				}
				ERvo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);//成功
				//成功返回页面信息
		    	return ERvo;
			} catch (BusinessException err) {
				LogUtil.error(MODULE, "PromGroupController.save 报错了啦", err);
				ERvo.setResultMsg(err.getErrorMessage());
				ERvo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			}
		}
		//返回页面信息
    	return ERvo;
    }
    /**
     * 
     * detailsShop:查询参与店铺促销信息；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/detailsShop")
    public Model detailsShop(Model model,@ModelAttribute("vo")PromInfoVO vo)throws Exception{
        
    	LogUtil.info(MODULE, "主题促销页面查询,入参:{vo="+vo.toString()+"}");
    	
    	//1.封装后场入参对象
    	PromInfoDTO promInfoDTO  = vo.toBaseInfo(PromInfoDTO.class);
    	
    	ObjectCopyUtil.copyObjValue(vo, promInfoDTO, "", false);
    	
    	//2.调用后场服务
    	EcpBasePageRespVO<Map> respVO = null;
    	//2.1促销状态 t_prom_info.status
       try {
        	PageResponseDTO<PromInfoResponseDTO> promResponse = promGroupRSV.queryPromGroup4Shop(promInfoDTO.getGroupId(),promInfoDTO);
        	
        	if(CheckPageNull.checkPageNull(promResponse)){
        		List<PromInfoResponseDTO> list = promResponse.getResult();
        		List<PromInfoResponseDTO> result=new ArrayList<PromInfoResponseDTO>();
        		ShopInfoResDTO  shopDTO=new ShopInfoResDTO ();
        		for (PromInfoResponseDTO romInfoResponseDTO : list) {
        			shopDTO=shopInfoRSV.findShopInfoByShopID(romInfoResponseDTO.getShopId());
        			if(shopDTO != null){
        				romInfoResponseDTO.setShopName(shopDTO.getShopName()); 
        			}
        			result.add(romInfoResponseDTO);
            		promResponse.setResult(result);
            	}
        	}
        	
        	respVO = EcpBasePageRespVO.buildByPageResponseDTO(promResponse);
        	
		} catch (BusinessException err) {
			// TODO Auto-generated catch block
		    LogUtil.error(MODULE, err.toString());
			throw new BusinessException(err.getErrorMessage());
		}
    	//返回
    	return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * tab:详情促销；
     *  
     * @author huanghe5
     * @return 
     */
    @RequestMapping(value="/tab")
    public String tab(Model model,@ModelAttribute("promGroup")PromGroupVO  vo){
        
    	LogUtil.info(MODULE, "主题促销促销信息,入参:{vo="+vo.toString()+"}");
    	//1.封装后场入参对象
    	PromGroupDTO promGroupDTO  = new PromGroupDTO();
    	promGroupDTO.setId(vo.getId());
    	//2.调用后场服务
    	PromGroupResponseDTO dto = promGroupRSV.queryPromGroupById(promGroupDTO);
    	// 促销组 状态
    	List<BaseParamDTO> statusList = BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_GROUP_STATUS);
    	// 促销状态
    	List<BaseParamDTO> promstatus= BaseParamUtil.fetchParamList(PromConstants.PromKey.PROM_INFO_STATUS);

    	//3返回页面
    	model.addAttribute("statusList", statusList);
    	model.addAttribute("promstatus", promstatus);//促销状态
    	model.addAttribute("promGroup",dto);
    	return "/prom/group/group-tab";
    }
    
}


