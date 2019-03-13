package com.zengshi.ecp.busi.order.sub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.sub.vo.RConfirmDeliveReqVO;
import com.zengshi.ecp.busi.order.sub.vo.RConfirmSubInfoVO;
import com.zengshi.ecp.busi.order.sub.vo.RGoodSaleReqVO;
import com.zengshi.ecp.busi.order.sub.vo.RGoodSaleSumResponse;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RConfirmSubInfo;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleSumResp;
import com.zengshi.ecp.order.dubbo.dto.RShopOrderResponse;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdSubRequest;
import com.zengshi.ecp.order.dubbo.dto.RShowOrdSubResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdDeliveryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdSubRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @author wangxq
 *
 * 功能：
 *   确认发货
 */
@Controller
@RequestMapping(value="/ordersub")
public class OrderSubController extends EcpBaseController{

	private static final String MODULE = OrderSubController.class.getName();
	
	@Resource(name="ordDeliveryRSV")
	private IOrdDeliveryRSV ordDeliveryRSV;
	
    @Resource(name="ordSubRSV")
    private IOrdSubRSV ordSubRSV;

	@Resource
	private IStaffUnionRSV staffUnionRSV;

	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	
	@Resource
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

	/**
	 * 确认发货
	 * @param vo
	 * @return
	 */
    @RequestMapping(value="/publish")
    @ResponseBody
    public EcpBaseResponseVO publish(@Valid @ModelAttribute("rconfirmDeliveReqVO")RConfirmDeliveReqVO vo,BindingResult result){
    	LogUtil.info(MODULE,"订单信息：" + JSONObject.toJSONString(vo));
    	RConfirmDeliveRequest rdto = new RConfirmDeliveRequest();
    	EcpBaseResponseVO resp = new EcpBaseResponseVO();
    	try{
    		ObjectCopyUtil.copyObjValue(vo, rdto, null, true);
            LogUtil.info(MODULE,"发布订单"+vo.getOrderId());
            if(StringUtils.isEmpty(vo.getOrderId())){
            	String[] keyInfos = new String[1];
            	keyInfos[0] = "id";
            	throw new BusinessException();
            }
            ordDeliveryRSV.orderDelivery(rdto);
            //调用发布接口
            LogUtil.info(MODULE, "正在发布");
            //调用结束
            resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch(BusinessException e){
    		LogUtil.error(MODULE, "订单发布出错", e);
    		resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
    	}
    	
        return resp;
    }
    
    /**
	 * 确认发货
	 * @param vo
	 * @return
	 */
    @RequestMapping(value="/confirmsend")
    @ResponseBody
    public EcpBaseResponseVO confirmSend(@Valid RConfirmDeliveReqVO vo){
    	EcpBaseResponseVO resp = new EcpBaseResponseVO();
    	List<RConfirmSubInfo> rConfirmSubInfos = new ArrayList<RConfirmSubInfo>();
    	RConfirmDeliveRequest rdto = new RConfirmDeliveRequest();
    	try{
    		ObjectCopyUtil.copyObjValue(vo, rdto, "", false);
        	for(RConfirmSubInfoVO ordsubvo :  vo.getrConfirmSubInfo()){
        		RConfirmSubInfo rsubdto = new RConfirmSubInfo();
        		ObjectCopyUtil.copyObjValue(ordsubvo, rsubdto, "", true);
        		rConfirmSubInfos.add(rsubdto);
        	}
        	rdto.setrConfirmSubInfo(rConfirmSubInfos);
    		ordDeliveryRSV.orderDelivery(rdto);
    		resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch(Exception e){
    		LogUtil.error(MODULE, e.getMessage());
    		resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
    		resp.setResultMsg(e.getMessage()+",不允许发货");
    	}
    	
    	return resp;
    }

    
    @RequestMapping("/fileupload")
    @ResponseBody
    @NativeJson(true)
    public String fileUpload(@RequestParam(value = "entitycodefile", required = false) MultipartFile file,Model model,HttpServletRequest request, HttpServletResponse reponse) throws Exception {
 
    	return null;
    }

    /**
     * 
     * 功能描述：文件上传处理
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2015-8-31 下午4:26:59</p>
     *
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    @RequestMapping("/import")
    @ResponseBody
    @NativeJson(true)
    public String importExcel(@RequestParam(value = "entitycodefile", required = false) MultipartFile file,Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }
    /**
     * 
     * 功能描述：处理错误信息的JSON结果
     *
     * @author  曾海沥(Terry)
     * <p>创建日期 ：2015-8-31 下午5:34:29</p>
     *
     * @param message
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    private String getError(String message) {
    	JSONObject obj = new JSONObject();
    	obj.put("error", 1);
    	obj.put("message", message);
    	return obj.toJSONString();
    }
    
    @RequestMapping(value="/open")
    public String open(){
    	return "/order/orderSub/import/entity-file";
    }
    
    /**
     * 待发货和已发货列表点击发布之后跳转的页面
     */
    @RequestMapping(value="/grid/{orderId}")
    public String orderSubs(Model model,@PathVariable("orderId") String orderId){
    	model.addAttribute("orderId", orderId);
    	return "/order/orderSub/grid";
    }
    
    
    /**
     * 订单发货明细查询
     * 在点击确认发布的前一步
     * @param model 相当于response对象的封装Map结构
     * @param orderId 主订单ID
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/ordersubs/{orderId}")
    @ResponseBody
    public Model queryOrder(Model model, @PathVariable("orderId") String orderId, EcpBasePageReqVO vo) throws Exception{
    	RShowOrdSubRequest r = vo.toBaseInfo(RShowOrdSubRequest.class);
    	//RShowOrdSubRequest r = new RShowOrdSubRequest();
    	
    	if(StringUtil.isBlank(orderId)){
    		throw new BusinessException("order.orderid.null.error");
    	}
    	r.setOrderId(orderId);

    	PageResponseDTO<RShowOrdSubResponse> t = ordSubRSV.queryOrderSub(r);
    	
    	EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
    	return super.addPageToModel(model, respVO);
    }

	@RequestMapping("/salesum")
	@ResponseBody
	public RGoodSaleSumResponse saleSumData(RGoodSaleReqVO vo) throws Exception {

		RGoodSaleSumResponse resp = new RGoodSaleSumResponse();
		resp.setResultFlag(RGoodSaleSumResponse.RESULT_FLAG_SUCCESS);

		RGoodSaleSumResponse sumResp = new RGoodSaleSumResponse();
		sumResp.setResultFlag(RGoodSaleSumResponse.RESULT_FLAG_SUCCESS);
		sumResp.setRealMoney(0l);
		sumResp.setSaleNum(0l);
		sumResp.setOrderNum(0l);
		sumResp.setBasicMoney(0l);

		RGoodSaleRequest rGoodSaleRequest = new RGoodSaleRequest();
		rGoodSaleRequest = vo.toBaseInfo(RGoodSaleRequest.class);
		ObjectCopyUtil.copyObjValue(vo, rGoodSaleRequest, "", false);

		//搜索会员
		if(StringUtil.isNotBlank(vo.getStaffCode())) {
			CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
			CustInfoReqDTO.setStaffCode(vo.getStaffCode());
			CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
			if(custInfoResDTO!=null) {
				rGoodSaleRequest.setStaffId(custInfoResDTO.getId());
			}else {
				return sumResp;
			}
		}

		if(StringUtil.isNotBlank(vo.getIsbn())){
		    List<Long> gdsIds = new ArrayList<Long>();
            GdsSku2PropPropIdxReqDTO  gdsSku2PropPropIdxReq = new GdsSku2PropPropIdxReqDTO();
            gdsSku2PropPropIdxReq.setPropId(1004L);
            gdsSku2PropPropIdxReq.setPropValue(vo.getIsbn());
            gdsSku2PropPropIdxReq.setPageSize(Integer.MAX_VALUE);
            PageResponseDTO<GdsSkuInfoRespDTO> gdsSkuInfo = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(gdsSku2PropPropIdxReq);
            if(CollectionUtils.isNotEmpty(gdsSkuInfo.getResult())){
                for(GdsSkuInfoRespDTO gds: gdsSkuInfo.getResult()){
                    gdsIds.add(gds.getGdsId());
                }
			}else{
				return sumResp;
			}
			if(CollectionUtils.isNotEmpty(gdsIds)) rGoodSaleRequest.setGdsIds(gdsIds);
		}



		try{
			LogUtil.debug(MODULE, "开始查询销售情况统计=========");

			RGoodSaleSumResp sumResp0 = ordSubRSV.queryGoodSaleSum(rGoodSaleRequest);
			ObjectCopyUtil.copyObjValue(sumResp0,resp,"",false);
		}catch (Exception e){
			resp.setResultFlag(RGoodSaleSumResponse.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
		}

		return resp;
	}

	@RequestMapping("/goodsale")
	public String goodSale(Model model){
//		model.addAllAttributes(ParamsTool.params()); //需求要求修改成默认当天 #11561
		model.addAllAttributes(ParamsTool.paramsToday());
		return "/order/orderSub/sale-grid";
	}

	@RequestMapping("/goodsalelist")
	@ResponseBody
	public Model goodSaleList(Model model, RGoodSaleReqVO vo) throws Exception{

		RGoodSaleRequest rGoodSaleRequest = new RGoodSaleRequest();
		rGoodSaleRequest = vo.toBaseInfo(RGoodSaleRequest.class);
		ObjectCopyUtil.copyObjValue(vo, rGoodSaleRequest, "", false);

		//搜索会员
		if(StringUtil.isNotBlank(vo.getStaffCode())) {
			CustInfoReqDTO CustInfoReqDTO = new CustInfoReqDTO();
			CustInfoReqDTO.setStaffCode(vo.getStaffCode());
			CustInfoResDTO custInfoResDTO = staffUnionRSV.findCustInfo(CustInfoReqDTO);
			if(custInfoResDTO!=null) {
				rGoodSaleRequest.setStaffId(custInfoResDTO.getId());
			}else {
				PageResponseDTO<RShopOrderResponse> t = PageResponseDTO.buildByBaseInfo(rGoodSaleRequest, RShopOrderResponse.class);
				// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
				EcpBasePageRespVO<Map> respVO = ParamsTool.ordDetailSiteUrl(bulidPageResp(t));
				return super.addPageToModel(model, respVO);
			}
		}

		if(StringUtil.isNotBlank(vo.getIsbn())){
			List<Long> gdsIds = new ArrayList<Long>();
			GdsSku2PropPropIdxReqDTO  gdsSku2PropPropIdxReq = new GdsSku2PropPropIdxReqDTO();
			gdsSku2PropPropIdxReq.setPropId(1004L);
			gdsSku2PropPropIdxReq.setPropValue(vo.getIsbn());
			gdsSku2PropPropIdxReq.setPageSize(Integer.MAX_VALUE);
			PageResponseDTO<GdsSkuInfoRespDTO> gdsSkuInfo = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(gdsSku2PropPropIdxReq);
			if(CollectionUtils.isNotEmpty(gdsSkuInfo.getResult())){
				for(GdsSkuInfoRespDTO gds: gdsSkuInfo.getResult()){
					gdsIds.add(gds.getGdsId());
				}

				if(CollectionUtils.isNotEmpty(gdsIds)) rGoodSaleRequest.setGdsIds(gdsIds);
			}else{
				PageResponseDTO<RShopOrderResponse> t = PageResponseDTO.buildByBaseInfo(rGoodSaleRequest, RShopOrderResponse.class);
				// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
				EcpBasePageRespVO<Map> respVO = ParamsTool.ordDetailSiteUrl(bulidPageResp(t));
				return super.addPageToModel(model, respVO);
			}
		}

		LogUtil.debug(MODULE, vo.toString());

		PageResponseDTO<RGoodSaleResponse> t = ordSubRSV.queryGoodSaleInfo(rGoodSaleRequest);

		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(t);

		return super.addPageToModel(model, ParamsTool.ordDetailSiteUrl(respVO));

	}

	// 避免空指针异常
	@SuppressWarnings("rawtypes")
	private EcpBasePageRespVO<Map> bulidPageResp(PageResponseDTO<RShopOrderResponse> t)
			throws Exception {
		EcpBasePageRespVO<Map> respVO = new EcpBasePageRespVO<Map>();
		if (t != null) {
			respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
		}
		return respVO;
	}

}
