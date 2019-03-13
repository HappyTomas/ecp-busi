package com.zengshi.ecp.busi.unpf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.unpf.vo.UnpfShopCfgInfoVO;
import com.zengshi.ecp.busi.unpf.vo.UnpfShopCfgVO;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.cfg.UnpfShopCfgRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopCfgRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/shopcfg")
public class UnpfShopCfgController extends EcpBaseController {

	 public static final String TAOBAO="taobao";
	 
	@Resource
	private IUnpfShopCfgRSV unpfShopCfgRSV;

	@RequestMapping(value = "/girdlist")
	public String girdList(Model model, UnpfShopCfgVO vo) throws Exception {

		List<BaseParamDTO> list = BaseParamUtil.fetchParamList("UNPF_BASE_PARAM");
		if(!CollectionUtils.isEmpty(list)){
			List<BaseParamDTO> newList = new ArrayList<>();
			String[] Str = new String[] {};
			for (BaseParamDTO baseParamDTO : list) {
				Str = baseParamDTO.getSpCode().split("@");
				List<String> Strlist = java.util.Arrays.asList(Str);
//				if(TAOBAO.equals(Strlist.get(1))){
				if(vo.getPlatType().equals(Strlist.get(1))){
					baseParamDTO.setSpCode(Strlist.get(0));
					newList.add(baseParamDTO);
				}
			}
			model.addAttribute("list", newList);
		}

		UnpfShopCfgReqDTO unpfShopCfgReqDTO = new UnpfShopCfgReqDTO();
		ObjectCopyUtil.copyObjValue(vo, unpfShopCfgReqDTO, null, false);
		List<UnpfShopCfgRespDTO> ubnpf = this.unpfShopCfgRSV.queryShopCfgList(unpfShopCfgReqDTO);
		UnpfShopCfgRespDTO unpfShopCfgRespDTO = new UnpfShopCfgRespDTO();
		if (!CollectionUtils.isEmpty(ubnpf)) {
			unpfShopCfgRespDTO = ubnpf.get(0);
			model.addAttribute("shopCfgId", unpfShopCfgRespDTO.getId());
		} else {
			model.addAttribute("shopCfgId", 0);
		}
		UnpfShopCfgInfoVO unpfShopCfgInfoVO = new UnpfShopCfgInfoVO();
		JSONObject obj = new JSONObject().fromObject(unpfShopCfgRespDTO.getInputValue());// 将json字符串转换为json对象
		UnpfShopCfgInfoVO infoVO = (UnpfShopCfgInfoVO) JSONObject.toBean(obj, UnpfShopCfgInfoVO.class);
		if (StringUtil.isNotEmpty(infoVO)) {
			unpfShopCfgInfoVO = infoVO;
		}
		model.addAttribute("unpfShopCfgInfoVO", unpfShopCfgInfoVO);
		return "/unpf/shopcfg/shop-ship/shop-ship-form";
	}

	@RequestMapping(value = "/submit/{shopCfgId}")
	@ResponseBody
	public EcpBaseResponseVO submit(Model model, UnpfShopCfgVO vo, @PathVariable String shopCfgId) {
		UnpfShopCfgInfoVO unpfShopCfgInfoVO = vo.getUnpfShopCfgInfoVO();
		if("2".equals(unpfShopCfgInfoVO.getShipType())){
			unpfShopCfgInfoVO.setFreight_by_buyer("");
		}
		unpfShopCfgInfoVO.setCityCode(vo.getCityCode());
		unpfShopCfgInfoVO.setProvinceCode(vo.getProvinceCode());
		JSONObject jsObj = JSONObject.fromObject(unpfShopCfgInfoVO);
		UnpfShopCfgReqDTO unpfShopCfgReqDTO = new UnpfShopCfgReqDTO();
		unpfShopCfgReqDTO.setInputValue(jsObj.toString());
		ObjectCopyUtil.copyObjValue(vo, unpfShopCfgReqDTO, null, false);
		EcpBaseResponseVO respVO = new EcpBaseResponseVO();
		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
//		LogUtil.info(this.getClass().getName(), JSON.toJSONString(vo));
		Long i = Long.valueOf(shopCfgId);
		if (i <= 0) {
			unpfShopCfgReqDTO.setId(null);
			unpfShopCfgRSV.saveShopCfg(unpfShopCfgReqDTO);
		} else {
			unpfShopCfgReqDTO.setId(i);
			unpfShopCfgRSV.updateShopCfg(unpfShopCfgReqDTO);
		}

		return respVO;

	}
}
