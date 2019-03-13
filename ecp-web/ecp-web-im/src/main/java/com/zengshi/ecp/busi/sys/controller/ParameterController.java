package com.zengshi.ecp.busi.sys.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.sys.vo.ParameterVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.ISysCfgRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 关于系统参数的一系列处理<br>
 */
@Controller
@RequestMapping(value = "/parameter")
public class ParameterController extends EcpBaseController {
	
	@Resource
	private ISysCfgRSV sysCfgRSV;

	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数页面跳转<br>
	 */
	@RequestMapping()
	public String init(Model model) {
		return "/sys/cfg/parameter-grid";
	}

	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数列表初始化<br>
	 */
	@RequestMapping("/gridlist")
	@ResponseBody
	public Model gridList(Model model, ParameterVO vo) throws Exception {

		// 后场服务所需要的DTO；
		SysCfgReqDTO sysCfgReqDTO = vo.toBaseInfo(SysCfgReqDTO.class);
		ObjectCopyUtil.copyObjValue(vo, sysCfgReqDTO, null, false);
		PageResponseDTO<SysCfgResDTO> t = this.sysCfgRSV.querySysCfgForPage(sysCfgReqDTO);

		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
		return super.addPageToModel(model, respVO);
	}

	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数添加页面跳转<br>
	 */
	@RequestMapping(value = "/add")
	public String add() {
		return "/sys/cfg/parameter-edit";
	}

	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数添加保存<br>
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public EcpBaseResponseVO save(Model model, ParameterVO vo) {
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		ObjectCopyUtil.copyObjValue(vo, sysCfgReqDTO, null, false);
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
		if (StringUtil.isEmpty(vo.getSearchParams())) {
			sysCfgReqDTO.setSearchParams(vo.getSearchParams());
			int i = sysCfgRSV.saveSysCfg(sysCfgReqDTO);
			if (i >= 1) {
				respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			}
			else{
				respVO.setResultMsg(ResourceMsgUtil.getMessage("web.sys.exception.002", null));
			}
		} else {
			int i = sysCfgRSV.updateSysCfg(sysCfgReqDTO);
			if (i >= 1) {
				respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			}else{
				respVO.setResultMsg(ResourceMsgUtil.getMessage("web.sys.exception.001", null));
			}
		}
		return respVO;
	}

	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 删除<br>
	 */
		@RequestMapping("remove")
		@ResponseBody
	public EcpBaseResponseVO remove(@RequestParam("paraCode")String paraCode)throws Exception{
			SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
			sysCfgReqDTO.setParaCode(paraCode);
			sysCfgRSV.removeSysCfg(sysCfgReqDTO);
		EcpBaseResponseVO vo = new EcpBaseResponseVO();
		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		return vo;
	}
	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数查看<br>
	 */
	@RequestMapping(value = "/view/{paraCode}")
	public String view(Model model, @PathVariable("paraCode") String paraCode) {
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setParaCode(paraCode);
		SysCfgResDTO sysCfgResDTO = this.sysCfgRSV.fetchByparaCode(sysCfgReqDTO);
		model.addAttribute("sysCfgResDTO", sysCfgResDTO);
		return "/sys/cfg/parameter-view";
	}
	/**
	 * Project Name:ecp-web-manage <br>
	 * Description: 系统参数编辑<br>
	 */
	@RequestMapping(value = "/edit/{paraCode}")
	public String edit(Model model, @PathVariable("paraCode") String paraCode) {
		SysCfgReqDTO sysCfgReqDTO = new SysCfgReqDTO();
		sysCfgReqDTO.setParaCode(paraCode);
		SysCfgResDTO sysCfgResDTO = this.sysCfgRSV.fetchByparaCode(sysCfgReqDTO);
		model.addAttribute("sysCfgResDTO", sysCfgResDTO);
		return "/sys/cfg/parameter-edit";
	}
}
