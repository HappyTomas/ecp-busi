package com.zengshi.ecp.busi.goods.catalog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.catalog.controller.vo.GdsCatlog2ShopVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.busi.staff.vo.ShopSelectVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

@Controller
@RequestMapping("/goods/catlog2shop")
public class GdsCatlog2ShopController extends GdsBaseController {
	
	private static final String BASE_URL = "/goods/catlog2shop/";

    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;
    @Resource(name = "gdsCatlog2ShopRSV")
    private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;

    @RequestMapping
    public String pageInit() throws Exception {
        return "/goods/catlog2shop/catlog2shop-grid";
    }

   
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo,@Valid ShopSelectVO selectVO) throws Exception{
        
		LogUtil.info(MODULE, "======   店铺列表查询    开始     ======");
		// 1.获取查询条件，转换成DTO
		ShopSelectReqDTO dto = vo.toBaseInfo(ShopSelectReqDTO.class);
		dto.setShopName(selectVO.getShopName());
		PageResponseDTO<ShopInfoResDTO> page = shopInfoRSV
				.listShopInfoByCond(dto);
		PageResponseDTO<GdsCatlog2ShopPageRespDTO> result = doConvert(page);

		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(result);

		LogUtil.info(MODULE, "======   店铺列表查询    结束     ======");
		return super.addPageToModel(model, respVO);
    }
    
    
    /**
     * 
     * editRelation:. <br/> 
     * 
     * @author liyong7
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/edit")
	public String toEdit(Model model,GdsCatlog2ShopVO vo) {
    	LogUtil.info(MODULE, "========开始弹出店铺目录关联编辑页面=========");
    	// 关联关系编辑页面需要店铺信息,目录信息,已经关联目录ID.
    	ShopInfoResDTO shop = shopInfoRSV.findShopInfoByShopID(vo.getShopId());
    	GdsCatalogReqDTO catalogQuery = new GdsCatalogReqDTO();
    	catalogQuery.setPageSize(Integer.MAX_VALUE);
        PageResponseDTO<GdsCatalogRespDTO> catalogs = gdsCatalogRSV.queryGdsCatalogRespDTOPaging(catalogQuery);
        GdsCatlog2ShopReqDTO catlog2ShopQuery = new GdsCatlog2ShopReqDTO();
        catlog2ShopQuery.setShopId(vo.getShopId());
        catlog2ShopQuery.setPageSize(Integer.MAX_VALUE);
        PageResponseDTO<GdsCatlog2ShopRespDTO> relations = gdsCatlog2ShopRSV.queryRelationByShopId(catlog2ShopQuery);
        String relationString = relationToString(relations.getResult());
        
        model.addAttribute("shop",shop);
        // model.addAttribute("catalogs",catalogs.getResult());
        model.addAttribute("relations",relationString);
        
        
    	LogUtil.info(MODULE, "========结束弹出店铺目录关联编辑页面=========");
		return BASE_URL+"/open/catalog-select-grid";
	}
    
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO doSave(GdsCatlog2ShopVO vo){
    	LogUtil.info(MODULE, "========开始保存店铺目录关联编辑页面=========");
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	
    	try{
    		String catlogIdStr = vo.getCatlogIds();
    		GdsCatlog2ShopReqDTO save = new GdsCatlog2ShopReqDTO();
    		if(StringUtil.isNotBlank(catlogIdStr)){
    		   String[] idAry = catlogIdStr.split(",");
    		   Long[] ids = (Long[])ConvertUtils.convert(idAry, Long.class);
    		   if(ArrayUtils.isNotEmpty(ids)){
        		   save.setShopId(vo.getShopId());
        		   save.setCatlogIds(Arrays.asList(ids));
        		   gdsCatlog2ShopRSV.batchSaveGdsCatlog2Shop(save); 
    		   }
    		}else{
     		   save.setShopId(vo.getShopId());
     		   gdsCatlog2ShopRSV.deleteRelationByShopId(save);  
 		   }
    		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch (BusinessException e) {
			LogUtil.error(MODULE, "保存店铺与目录关联关系发生异常!",e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg(e.getErrorMessage());
		}
    	
    	LogUtil.info(MODULE, "========结束保存店铺目录关联编辑页面=========");
        return respVO;    	
    }
    
    
    private String relationToString(
			List<GdsCatlog2ShopRespDTO> relations) {
    	StringBuffer buf = new StringBuffer();
    	if(CollectionUtils.isNotEmpty(relations)){
    		for(GdsCatlog2ShopRespDTO relation : relations){
    			buf.append(relation.getCatlogId());
    			buf.append(",");
    		}
    		delComma(buf);
    	}
		return buf.toString();
	}


	/**
     * 
     * doConvert:数据转换. <br/> 
     * 
     * @author liyong7
     * @param page
     * @throws Exception 
     * @since JDK 1.6
     */
    private PageResponseDTO<GdsCatlog2ShopPageRespDTO> doConvert(PageResponseDTO<ShopInfoResDTO> page)throws Exception{
    	PageResponseDTO<GdsCatlog2ShopPageRespDTO> result = new PageResponseDTO<GdsCatlog2ShopPageRespDTO>();
    	initPage(page, result);
    	Map<Long, GdsCatalogRespDTO> memoMap = new HashMap<Long, GdsCatalogRespDTO>();
    	ObjectCopyUtil.copyObjValue(page, result, null, false);
    	if(CollectionUtils.isNotEmpty(page.getResult())){
    		List<GdsCatlog2ShopPageRespDTO> resultLst = new ArrayList<GdsCatlog2ShopPageRespDTO>();
    		for(ShopInfoResDTO si : page.getResult()){
    		   GdsCatlog2ShopReqDTO dto = new GdsCatlog2ShopReqDTO();
    		   dto.setShopId(si.getId());
    		   dto.setPageSize(Integer.MAX_VALUE);
    		   PageResponseDTO<GdsCatlog2ShopRespDTO> relationPage = gdsCatlog2ShopRSV.queryRelationByShopId(dto);
    		   StringBuffer nameBuf = new StringBuffer();
			   StringBuffer idBuf = new StringBuffer();
    		   if(CollectionUtils.isNotEmpty(relationPage.getResult())){
    			   for(GdsCatlog2ShopRespDTO resp : relationPage.getResult()){
    				   GdsCatalogRespDTO catalog = memoMap.get(resp.getCatlogId());
    				   if(null == memoMap.get(resp.getCatlogId())){
    					   GdsCatalogReqDTO query = new GdsCatalogReqDTO();
        				   query.setId(resp.getCatlogId());
        				   catalog = gdsCatalogRSV.queryGdsCatalogByPK(query);
        				   if(null != catalog){
        					   memoMap.put(resp.getCatlogId(), catalog);  
        				   }
    				   }
    				   if(null != catalog){
    					   nameBuf.append(catalog.getCatlogName());
    					   nameBuf.append(",");
    					   idBuf.append(catalog.getId());
    					   idBuf.append(",");
    				   }
    			   }
    			   delComma(nameBuf);
    			   delComma(idBuf);
    		   }
    		   GdsCatlog2ShopPageRespDTO pageRespDTO = new GdsCatlog2ShopPageRespDTO();
    		   ObjectCopyUtil.copyObjValue(si, pageRespDTO, null, false);
    		   pageRespDTO.setCatlogNames(nameBuf.toString());
    		   pageRespDTO.setCatlogIds(idBuf.toString());
    		   resultLst.add(pageRespDTO);
    		}
    		result.setResult(resultLst);
    	}
    	return result;
    }
    
    
    private void initPage(PageResponseDTO<ShopInfoResDTO> page,
			PageResponseDTO<GdsCatlog2ShopPageRespDTO> result) {
    	result.setCount(page.getCount());
    	result.setPageCount(page.getPageCount());
    	result.setPageNo(page.getPageNo());
    	result.setPageSize(page.getPageSize());
	}
    /*
     * 
     * delComma:删除StringBuilder里的最后一个逗号. <br/> 
     * 
     * @author liyong7
     * @param buf 
     * @since JDK 1.6
     */
	private void delComma(StringBuffer buf){
    	if(null != buf && 0 != buf.length()){
    	  buf =	buf.deleteCharAt(buf.length() - 1);
    	}
    }
}
