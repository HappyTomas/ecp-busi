package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsArrmsgVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsArrmsgRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsArrmsgRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 到货通知<br>
 * Date:2015年9月22日下午14:44:20 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author tongkai
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/gdsarrmsg")
public class GdsArrmsgController extends EcpBaseController{
	private static String MODULE = GdsArrmsgController.class.getName();
	private static String URL = "/goods/gdsArrmsg";
	
	@Resource(name = "gdsArrmsgRSV")
	private IGdsArrmsgRSV gdsArrmsgRSV;
	
	//初始界面
	@RequestMapping()
	public String init() {
		return URL + "/arrmsg-grid";
	}
	
	//获取列表
	@RequestMapping(value = "/arrmsglist")
	@ResponseBody
	public Model arrmsgList(Model model, @Valid GdsArrmsgVO vo) throws Exception {
		/// 后场服务所需要的DTO；
		GdsArrmsgReqDTO dto = vo.toBaseInfo(GdsArrmsgReqDTO.class);
		// 组织参数
		ObjectCopyUtil.copyObjValue(vo, dto, null, false);
		dto.setStatus(GdsConstants.Commons.STATUS_VALID);
		PageResponseDTO<GdsArrmsgRespDTO> t =gdsArrmsgRSV.queryGdsArrmsg(dto);
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		if (t.getResult() == null) {
			t.setResult(new ArrayList<GdsArrmsgRespDTO>());
		}
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

		return super.addPageToModel(model, respVO);
	}
	
	
	//删除
	@RequestMapping(value = "/gdsremove")
	@ResponseBody
	public EcpBaseResponseVO gdsRemove(GdsArrmsgVO gdsVo) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsArrmsgReqDTO dto = new GdsArrmsgReqDTO();
        ObjectCopyUtil.copyObjValue(gdsVo, dto, null, false);
        try{
            gdsArrmsgRSV.deleteGdsArrmsg(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch(Exception e){
            BusinessException be = (BusinessException) e;
            if (e instanceof BusinessException) {
                vo.setResultMsg(be.getErrorMessage());
            }else{
                vo.setResultMsg(e.getMessage());
            }
            LogUtil.error(MODULE, "删除错误！！", be);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return vo;
	}
	
	
	/**
	 * 新增到货通知
	 * gdsRemove:(新增到货通知). <br/> 
	 * 
	 * @author linwb3
	 * @param gdsVo
	 * @return 
	 * @since JDK 1.6
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public EcpBaseResponseVO add(GdsArrmsgVO gdsVo) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsArrmsgReqDTO dto = new GdsArrmsgReqDTO();
        ObjectCopyUtil.copyObjValue(gdsVo, dto, null, false);
        try{
            gdsArrmsgRSV.saveGdsArrmsg(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch(Exception e){
            BusinessException be = (BusinessException) e;
            if (e instanceof BusinessException) {
                vo.setResultMsg(be.getErrorMessage());
            }else{
                vo.setResultMsg(e.getMessage());
            }
            LogUtil.error(MODULE, "删除错误！！", be);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
        }
        return vo;
	}


}
