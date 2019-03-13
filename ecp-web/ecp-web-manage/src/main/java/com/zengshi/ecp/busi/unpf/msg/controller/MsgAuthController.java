package com.zengshi.ecp.busi.unpf.msg.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.unpf.UnpfConst;
import com.zengshi.ecp.busi.unpf.msg.vo.ShopAuthTopicVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfShopAuthTopicRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfShopAuthTopicRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-unpf <br>
 * Description: <br>
 * Date:2016-11-7上午10:51:38 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/msgauth")
public class MsgAuthController extends EcpBaseController {

	@Resource
	private IUnpfShopAuthRSV unpfShopAuthRSV;

	@Resource
    private IShopInfoRSV shopInfoRSV;
	
	
	@Resource
	private IUnpfShopAuthTopicRSV unpfShopAuthTopicRSV;
    /**
     * 平台促销类型授权店铺功能
     */
    private static String MODULE = MsgAuthController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author lisp
     * @return
     * @since JDK 1.7
     */
    @RequestMapping(value="/{id}")
    public String init(Model model,@PathVariable String id) {
    	model.addAttribute("id", id);
        return "/unpf/msg/msgAuth-grid";
    }

    /**
     * 授权列表查询 初始化列表和查询按钮功能调用
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, ShopAuthTopicVO vo) throws Exception {
        
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.valueOf(vo.getShopAuthId()));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	//封装后场所需的入参对象 and 条件
    	UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
    	unpfShopAuthTopicReqDTO.setShopId(unpfShopAuthRespDTO.getShopId());
    	unpfShopAuthTopicReqDTO.setPlatType(unpfShopAuthRespDTO.getPlatType());
    	unpfShopAuthTopicReqDTO.setShopAuthId(unpfShopAuthRespDTO.getId());
    	unpfShopAuthTopicReqDTO.setPageSize(10);
    	PageResponseDTO<UnpfShopAuthTopicRespDTO> page = unpfShopAuthTopicRSV.queryShopAuthTopicForPage(unpfShopAuthTopicReqDTO);
    	 if(CheckPageNull.checkPageNull(page)){
             ShopInfoResDTO  shopDTO=new ShopInfoResDTO ();
               for(UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO:page.getResult()){
                   shopDTO=shopInfoRSV.findShopInfoByShopID(unpfShopAuthTopicRespDTO.getShopId());
                   if(shopDTO!=null){
                	   unpfShopAuthTopicRespDTO.setShopName(shopDTO.getShopName()); 
                   }
                   unpfShopAuthTopicRespDTO.setPlatTypeName(BaseParamUtil.fetchParamValue(UnpfConst.UNPF_PLAT_TYPE, unpfShopAuthTopicRespDTO.getPlatType()));
               }
         }
    	// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }

    /**
     * 授权 新增链接页面
     * 
     * @param model
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/add/{id}")
    public String add(Model model,@PathVariable String id) {
        model.addAttribute("unpfShopAuthVO", new ShopAuthTopicVO());
        model.addAttribute("doAction",UnpfConst.DO_ACTION_CREATE);
        UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.valueOf(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	model.addAttribute("respDTO", unpfShopAuthRespDTO);
        return "/unpf/msg/msgAuth-edit-form";
    }
    
    
    /**
     * 授权 编辑 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
    	model.addAttribute("shopAuthTopicVO", new ShopAuthTopicVO());
    	model.addAttribute("doAction", UnpfConst.DO_ACTION_EDIT);
    	model.addAttribute("topicId", id);
    	UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
    	if(StringUtil.isEmpty(id)){
    		throw new BusinessException("id 为空");
    	}
    	unpfShopAuthTopicReqDTO.setId(Long.valueOf(id));
    	UnpfShopAuthTopicRespDTO unpfShopAuthTopicRespDTO = unpfShopAuthTopicRSV.queryShopAuthTopicById(unpfShopAuthTopicReqDTO);
    	unpfShopAuthTopicRespDTO.setId(unpfShopAuthTopicRespDTO.getShopAuthId());
    	model.addAttribute("respDTO", unpfShopAuthTopicRespDTO);
    	
    	return "/unpf/msg/msgAuth-edit-form";
    }

    
    /**
     * 授权 新增提交保存
     * 
     * @param shopAuthTopicVO
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid @ModelAttribute("shopAuthTopicVO") ShopAuthTopicVO shopAuthTopicVO) {
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	//消息授权入参
    	UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
    	unpfShopAuthTopicReqDTO.setId(null);
    	unpfShopAuthTopicReqDTO.setShopAuthId(Long.valueOf(shopAuthTopicVO.getShopAuthId()));
    	unpfShopAuthTopicReqDTO.setStatus(UnpfConst.msg.STATUS_1);
    	unpfShopAuthTopicReqDTO.setNick(shopAuthTopicVO.getNick());
    	unpfShopAuthTopicReqDTO.setTopic(shopAuthTopicVO.getTopic());
    	try {
    		UnpfShopAuthTopicRespDTO respDTO = unpfShopAuthTopicRSV.saveShopAuthTopic(unpfShopAuthTopicReqDTO);
    		if(respDTO!=null){
    			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
    			vo.setResultMsg("已存在该topic的授权，请修改！");
    		}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
        return vo;
    }

    /**
     * 授权 编辑保存
     * 
     * @param model
     * @param shopAuthTopicVO
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public EcpBaseResponseVO update(@Valid @ModelAttribute("shopAuthTopicVO") ShopAuthTopicVO shopAuthTopicVO) {

    	//结果标志位返回
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
    	unpfShopAuthTopicReqDTO.setId(shopAuthTopicVO.getTopicId());
    	unpfShopAuthTopicReqDTO.setNick(shopAuthTopicVO.getNick());
    	unpfShopAuthTopicReqDTO.setTopic(shopAuthTopicVO.getTopic());
    	unpfShopAuthTopicReqDTO.setStatus(UnpfConst.msg.STATUS_1);
    	unpfShopAuthTopicReqDTO.setShopAuthId(Long.valueOf(shopAuthTopicVO.getShopAuthId()));
    	try {
    		unpfShopAuthTopicRSV.updateShopAuthTopicByExample(unpfShopAuthTopicReqDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
        return vo;
    }
    
    
    
    /**
     * 授权 新增提交保存
     * 
     * @param shopAuthTopicVO
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/close/{id}")
    @ResponseBody
    public EcpBaseResponseVO close(Model model,@PathVariable("id") String id) {
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.valueOf(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	UnpfShopAuthTopicReqDTO unpfShopAuthTopicReqDTO = new UnpfShopAuthTopicReqDTO();
    	ObjectCopyUtil.copyObjValue(unpfShopAuthRespDTO, unpfShopAuthTopicReqDTO, null, false);
    	unpfShopAuthTopicReqDTO.setStatus(UnpfConst.msg.STATUS_0);
    	unpfShopAuthTopicReqDTO.setShopAuthId(Long.valueOf(id));
    	unpfShopAuthTopicReqDTO.setId(null);
    	try {
    		unpfShopAuthTopicRSV.closeShopAuthTopicByExample(unpfShopAuthTopicReqDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
        return vo;
    }

}
