package com.zengshi.ecp.busi.seller.prom.shop.controller;


import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.prom.PromConst;
import com.zengshi.ecp.busi.seller.prom.shop.vo.PromChkVO;
import com.zengshi.ecp.busi.seller.prom.shop.vo.PromInfoReqVO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value="/seller/shopchk")
public class ShopChkController extends EcpBaseController {

	private static final String MODULE = ShopChkController.class.getName();
	
	@Resource
	private IPromQueryRSV promQueryRSV;
	
	@Resource
	private IPromGroupChkRSV promGroupChkRSV;
	
	@Resource
	private IShopInfoRSV shopInfoRSV;
	
	@RequestMapping()
	public String init(Model model,PromInfoReqVO vo){
		
		return grid(model,vo);
	}
	
	/**
	 * 审核店铺促销查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/grid")
	public String grid(Model model,PromInfoReqVO vo){
		// 初始化加载 促销状态（读取缓存）
        List<BaseParamDTO> statusList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_TYPE_STATUS);
        model.addAttribute("statusList", statusList);
		return "/seller/prom/shop/shopchk-grid";
	}
	
	/**
	 * 获取审核店铺促销列表
	 * @param model
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/gridlist")
	public String gridList(Model model,PromInfoReqVO vo) throws Exception{
		// 初始化加载 促销状态（读取缓存）
        List<BaseParamDTO> statusList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_TYPE_STATUS);
        model.addAttribute("statusList", statusList);
        //店铺下拉列表
		HashMap<String,Object> shopList = (HashMap<String, Object>)CacheUtil.getItem("STAFF_SHOP_CACHE_DATA");
        model.addAttribute("shopList", shopList);
        
		PromInfoDTO rdto = vo.toBaseInfo(PromInfoDTO.class);
		//查询条件
		ObjectCopyUtil.copyObjValue(vo, rdto, "", false);
		
		PageResponseDTO<PromInfoResponseDTO> shopCheckPage = promQueryRSV.queryPromInfoListByPlat(rdto);
		
		if(CheckPageNull.checkPageNull(shopCheckPage)){
		    ShopInfoResDTO  shopInfoDTO=new ShopInfoResDTO();
              for(PromInfoResponseDTO dto:shopCheckPage.getResult()){
                 // dto.setPromClassName(BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE_PROM_CLASS, dto.getPromClass()));
                 // dto.setPromTypeName( BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE, dto.getPromTypeCode()));
                 // dto.setStatusName(BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_INFO_STATUS, dto.getStatus()));
                  shopInfoDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                  if(shopInfoDTO!=null){
                      dto.setShopName(shopInfoDTO.getShopName()); 
                  }
              }
        }
		
		model.addAttribute("shopCheckPage",shopCheckPage );
		
		return"/seller/prom/shop/list/shopchk-grid-list";
	}
	
	/**
	 * 编辑审核店铺促销
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/edit/{id}")
	public String edit(Model model,@PathVariable("id")String id) throws Exception{
	    //促销基本信息
		PromInfoDTO pdto = queryPromInfo(id);
		Long shopId = pdto.getShopId();
		//店铺基本信息
		ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(shopId);
		
		  if(shopInfo==null){
	            shopInfo=new ShopInfoResDTO();
	        }
	        
		  
		model.addAttribute("shopName", shopInfo.getShopName());
	    model.addAttribute("promInfo", pdto);
	    
		return "/seller/prom/shop/shopchk-eord";
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
	
	/**
	 * 审核店铺促销详细
	 * @param model
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/detail/{id}")
	public String detail(Model model,@PathVariable("id")String id) throws Exception{
	    //促销基本信息
		PromInfoDTO pdto = queryPromInfo(id);
		//查店铺名称和店铺编码
		Long shopId = pdto.getShopId();
		ShopInfoResDTO shopInfo = shopInfoRSV.findShopInfoByShopID(shopId);
		if(shopInfo==null){
		    shopInfo=new ShopInfoResDTO();
		}
		
		List<PromChkResponseDTO> respDTOList=this.queryPromChkDTOByPromId(new Long(id));
		model.addAttribute("shopName", shopInfo.getShopName());
		model.addAttribute("promInfo", pdto);
		model.addAttribute("type", "detail");
		PromChkVO promChkVO=new PromChkVO();
		if(!CollectionUtils.isEmpty(respDTOList)){
		        promChkVO.setChkDesc(respDTOList.get(0).getChkDesc());
		        promChkVO.setId(respDTOList.get(0).getId());
		        promChkVO.setPromId(respDTOList.get(0).getPromId());
		        promChkVO.setStatus(respDTOList.get(0).getStatus());
		}
		  model.addAttribute("promChkVO",promChkVO);
		return "/seller/prom/shop/shopchk-eord";
	}
	
	/**
	 * 审核店铺促销
	 * @param model
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="/verify")
	@ResponseBody
	public EcpBaseResponseVO verify(Model model,@Valid @ModelAttribute("promChkVO")PromChkVO vo){
	    
		EcpBaseResponseVO resp = new EcpBaseResponseVO();

		//createStaff这是直接注入进来的
		
		PromChkDTO rdto = new PromChkDTO();
		ObjectCopyUtil.copyObjValue(vo, rdto, null, false);
		
		try{
		    
			promGroupChkRSV.savePromGroupChk(rdto);
			 
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
			
			
		}catch(BusinessException e){
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
			LogUtil.error(MODULE, "促销店铺审核异常", e);
		}
		
		return resp;
		
	}
	
	/**
	 * 根据促销编码获得审核结果
	 * @param promId
	 * @return
	 * @author huangjx
	 */
	private List<PromChkResponseDTO> queryPromChkDTOByPromId(Long promId){
	    PromChkDTO reqDTO=new PromChkDTO();
	    reqDTO.setPromId(promId);
	   return promGroupChkRSV.queryPromGroupChkByPromId(reqDTO);
	}
}
