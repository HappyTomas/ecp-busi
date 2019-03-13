package com.zengshi.ecp.busi.seller.goods.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.coup.shop.vo.ShopVO;
import com.zengshi.ecp.busi.seller.goods.vo.GdsCountVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping("/seller/goods/dashboard")
public class GdsDashboardController extends GdsBaseController {
    
    @Resource(name = "gdsEvalRSV")
    private IGdsEvalRSV gdsEvalRSV;
    
    @Resource(name = "reportGoodPayedRSV")
    private IReportGoodPayedRSV reportGoodPayedRSV;
    
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
    private IGdsStockRSV gdsStockRSV;
    
    @RequestMapping("/toShopEvaluateMgr")
    public String toShopEvaluateMgr(Long shopId) throws Exception {
        return "";
    }
    
    @RequestMapping("/goodEvaluateCount")
    @ResponseBody
    public Long goodEvaluateCount(Long shopId) throws Exception {
        GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
        gdsEvalReqDTO.setShopId(shopId);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return this.gdsEvalRSV.countGoodEval(gdsEvalReqDTO);
    }
    
    @RequestMapping("/middleEvaluateCount")
    @ResponseBody
    public Long middleEvaluateCount(Long shopId) throws Exception {
        GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
        gdsEvalReqDTO.setShopId(shopId);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return this.gdsEvalRSV.countMiddleEval(gdsEvalReqDTO);
    }
    
    @RequestMapping("/badEvaluateCount")
    @ResponseBody
    public Long badEvaluateCount(Long shopId) throws Exception {
        GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
        gdsEvalReqDTO.setShopId(shopId);
        gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return this.gdsEvalRSV.countBadEval(gdsEvalReqDTO);
    }
    
    @RequestMapping("/goodEvalRate")
    @ResponseBody
    public String goodEvalRate(Long shopId) throws Exception {
        GdsEvalReqDTO gdsEvalReqDTO=new GdsEvalReqDTO();
        gdsEvalReqDTO.setShopId(shopId);
        return this.gdsEvalRSV.calCulateShopGoodEvalRate(gdsEvalReqDTO)+"";
    }
    
    @RequestMapping("/sales")
    @ResponseBody
    public Long sales(Long shopId) throws Exception {
        RGoodSaleRequest rgoodSaleRequest=new RGoodSaleRequest();
        rgoodSaleRequest.setShopId(shopId);
        return this.reportGoodPayedRSV.querySumBuyNumByShopId(rgoodSaleRequest);
    }
    
    @RequestMapping("/waitShelvesGds")
    @ResponseBody
    public Long waitShelvesGds(Long shopId) throws Exception {
        GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(shopId);
        gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        return gdsInfoQueryRSV.countGdsInfoByShopIDAndStatus(gdsInfoReqDTO);
    }
    
    @RequestMapping("/onShelvesGds")
    @ResponseBody
    public Long onShelvesGds(Long shopId) throws Exception {
        GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(shopId);
        gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        return gdsInfoQueryRSV.countGdsInfoByShopIDAndStatus(gdsInfoReqDTO);
    }
    
    @RequestMapping("/offShelvesGds")
    @ResponseBody
    public Long offShelvesGds(Long shopId) throws Exception {
        GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(shopId);
        gdsInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
        return gdsInfoQueryRSV.countGdsInfoByShopIDAndStatus(gdsInfoReqDTO);
    }
    
    @RequestMapping("/legalGds")
    @ResponseBody
    public Long legalGds(Long shopId) throws Exception {
        GdsInfoReqDTO gdsInfoReqDTO=new GdsInfoReqDTO();
        gdsInfoReqDTO.setShopId(shopId);
        List<String> list=new ArrayList<String>();
        list.add(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        list.add(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        list.add(GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES);
        gdsInfoReqDTO.setGdsStatusArr(list);
        return gdsInfoQueryRSV.countGdsInfoByShopIDAndStatusArr(gdsInfoReqDTO);
    }
    
    
    
    @RequestMapping(value="/gdscount")
    @ResponseBody
    public GdsCountVO gdsCount(Model model, ShopVO sv) throws Exception{
    	GdsCountVO vo = new GdsCountVO();
    	vo.setGdsCount(0L);
    	Long sid = 0L;
    	try{
    		if(null == sv || null == sv.getShopId()){
    			// 店铺ID为空.
    			throw new BusinessException("web.gds.2000019");
    		}
    		if(StringUtil.isBlank(sv.getStatus())){
    			// 商品状态为空.
    			throw new BusinessException("web.gds.2000018");
    		}
    		
    		try{
    			sid = Long.parseLong(sv.getShopId());
    		}catch (Exception e) {
				LogUtil.error(MODULE, "无效店铺ID,shopId="+sv.getShopId());
				throw new BusinessException("web.gds.2000020");
			}
    		
    		GdsInfoReqDTO queryDTO = new GdsInfoReqDTO();
    		queryDTO.setShopId(sid);
    		queryDTO.setGdsStatus(sv.getStatus());
    		Long cnt = gdsInfoQueryRSV.countGdsInfoByShopIDAndStatus(queryDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    		vo.setGdsCount(cnt);
    		
    	}catch (BusinessException e) {
    		LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}

        return vo;
    }
    
    @RequestMapping("/lackStockGds")
    @ResponseBody
    public Long lackStockGds(Long shopId) throws Exception {
    	StockInfoReqDTO stockInfoReqDTO = new StockInfoReqDTO(); 
    	stockInfoReqDTO.setShopId(shopId);
        return gdsStockRSV.statisticStockLack(stockInfoReqDTO);
    }
    
}

