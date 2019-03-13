package com.zengshi.ecp.busi.goods.catalog.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.cms.site.vo.CmsSiteVO;
import com.zengshi.ecp.busi.goods.catalog.controller.vo.GdsCatlog2SiteVO;
import com.zengshi.ecp.busi.goods.common.GdsBaseController;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsSiteRSV;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SitePageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalog2SiteRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;


@Controller
@RequestMapping("/goods/catlog2site")
public class GdsCatlog2SiteController extends GdsBaseController {
	
	private static final String BASE_URL = "/goods/catlog2site/";


    @Resource(name="cmsSiteRSV")
    private ICmsSiteRSV cmsSiteRSV;
    @Resource(name = "gdsCatalog2SiteRSV")
    private IGdsCatalog2SiteRSV gdsCatalog2SiteRSV;
    @Resource(name = "gdsCatalogRSV")
    private IGdsCatalogRSV gdsCatalogRSV;
    
    @RequestMapping
    public String pageInit() throws Exception {
        return "/goods/catlog2site/catlog2site-grid";
    }

    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, @ModelAttribute("searchVO") CmsSiteVO searchVO) throws Exception{
        LogUtil.info(MODULE,"======   站点列表查询    开始     ======");
        //1. 调用后场服务所需要的DTO；
        CmsSiteReqDTO reqDTO = searchVO.toBaseInfo(CmsSiteReqDTO.class);
        reqDTO.setSiteName(searchVO.getSiteName());
        //ObjectCopyUtil.copyObjValue(searchVO, reqDTO, "MODULE", true);
        
        //2. 调用后场服务，将后场返回的分页对象封装为前店所需要的分页对象；
        PageResponseDTO<CmsSiteRespDTO> pageInfo = cmsSiteRSV.queryCmsSitePage(reqDTO);
        PageResponseDTO<GdsCatalog2SitePageRespDTO> result = doConvert(pageInfo);
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO
				.buildByPageResponseDTO(result);
        
        LogUtil.info(MODULE,"======   站点列表查询    结束     ======");
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
	public String toEdit(Model model,GdsCatlog2SiteVO vo) {
    	LogUtil.info(MODULE, "========开始弹出站点目录关联编辑页面=========");
    	// 关联关系编辑页面需要站点信息,目录信息,已经关联目录ID.
    	CmsSiteReqDTO reqDTO = new CmsSiteReqDTO();
    	reqDTO.setId(vo.getSiteId());
    	CmsSiteRespDTO site = cmsSiteRSV.queryCmsSite(reqDTO);
    	GdsCatalogReqDTO catalogQuery = new GdsCatalogReqDTO();
    	catalogQuery.setPageSize(Integer.MAX_VALUE);
        PageResponseDTO<GdsCatalogRespDTO> catalogs = gdsCatalogRSV.queryGdsCatalogRespDTOPaging(catalogQuery);
        
        GdsCatalog2SiteReqDTO catlog2SiteQuery = new GdsCatalog2SiteReqDTO();
        catlog2SiteQuery.setSiteId(vo.getSiteId());
        catlog2SiteQuery.setPageSize(Integer.MAX_VALUE);
        PageResponseDTO<GdsCatalog2SiteRespDTO> relations = gdsCatalog2SiteRSV.queryPrdRelBySiteId(catlog2SiteQuery);
        String relationString = relationToString(relations.getResult());
        
        model.addAttribute("site",site);
        // model.addAttribute("catalogs",catalogs.getResult());
        model.addAttribute("relations",relationString);
        
        
    	LogUtil.info(MODULE, "========结束弹出站点目录关联编辑页面=========");
		return BASE_URL+"/open/catalog-select-grid";
	}
    
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO doSave(GdsCatlog2SiteVO vo){
    	LogUtil.info(MODULE, "========开始保存站点目录关联编辑页面=========");
    	EcpBaseResponseVO respVO = new EcpBaseResponseVO();
    	
    	try{
    		String catlogIdStr = vo.getCatlogIds();
    		GdsCatalog2SiteReqDTO save = new GdsCatalog2SiteReqDTO();
    		if(StringUtil.isNotBlank(catlogIdStr)){
    		   String[] idAry = catlogIdStr.split(",");
    		   Long[] ids = (Long[])ConvertUtils.convert(idAry, Long.class);
    		   if(ArrayUtils.isNotEmpty(ids)){
    			   save.setSiteId(vo.getSiteId());
    			   save.setCatlogIds(Arrays.asList(ids));
        		   gdsCatalog2SiteRSV.batchSaveGdsCatlog2Site(save);
    		   }
    		}else{
     		   save.setSiteId(vo.getSiteId());
     		  gdsCatalog2SiteRSV.deleteRelationBySiteId(save);
 		   }
    		respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch (BusinessException e) {
			LogUtil.error(MODULE, "保存站点与目录关联关系发生异常!",e);
			respVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			respVO.setResultMsg(e.getErrorMessage());
		}
    	
    	LogUtil.info(MODULE, "========结束保存站点目录关联编辑页面=========");
        return respVO;    	
    }
    
    
    private String relationToString(
			List<GdsCatalog2SiteRespDTO> relations) {
    	StringBuffer buf = new StringBuffer();
    	if(CollectionUtils.isNotEmpty(relations)){
    		for(GdsCatalog2SiteRespDTO relation : relations){
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
    
    private PageResponseDTO<GdsCatalog2SitePageRespDTO> doConvert(PageResponseDTO<CmsSiteRespDTO> page)throws Exception{
    	PageResponseDTO<GdsCatalog2SitePageRespDTO> result = new PageResponseDTO<GdsCatalog2SitePageRespDTO>();
    	initPage(page, result);
    	Map<Long, GdsCatalogRespDTO> memoMap = new HashMap<Long, GdsCatalogRespDTO>();
    	ObjectCopyUtil.copyObjValue(page, result, null, false);
    	if(CollectionUtils.isNotEmpty(page.getResult())){
    		List<GdsCatalog2SitePageRespDTO> resultLst = new ArrayList<GdsCatalog2SitePageRespDTO>();
    		for(CmsSiteRespDTO si : page.getResult()){
    		   GdsCatalog2SiteReqDTO dto = new GdsCatalog2SiteReqDTO();
    		   dto.setSiteId(si.getId());
    		   dto.setPageSize(Integer.MAX_VALUE);
    		   PageResponseDTO<GdsCatalog2SiteRespDTO> relationPage = gdsCatalog2SiteRSV.queryPrdRelBySiteId(dto);
    		   StringBuffer nameBuf = new StringBuffer();
			   StringBuffer idBuf = new StringBuffer();
    		   if(CollectionUtils.isNotEmpty(relationPage.getResult())){
    			   for(GdsCatalog2SiteRespDTO resp : relationPage.getResult()){
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
    		   GdsCatalog2SitePageRespDTO pageRespDTO = new GdsCatalog2SitePageRespDTO();
    		   ObjectCopyUtil.copyObjValue(si, pageRespDTO, null, false);
    		   pageRespDTO.setCatlogNames(nameBuf.toString());
    		   pageRespDTO.setCatlogIds(idBuf.toString());
    		   resultLst.add(pageRespDTO);
    		}
    		result.setResult(resultLst);
    	}
    	return result;
    }
    private void initPage(PageResponseDTO<CmsSiteRespDTO> page,
			PageResponseDTO<GdsCatalog2SitePageRespDTO> result) {
    	result.setCount(page.getCount());
    	result.setPageCount(page.getPageCount());
    	result.setPageNo(page.getPageNo());
    	result.setPageSize(page.getPageSize());
	}
    
    /**
     * delComma:删除StringBuilder里的最后一个逗号. <br/> 
     * 
     * @author liyong7
     * @param buf 
     * @since JDK 1.6
    **/ 
	private void delComma(StringBuffer buf){
    	if(null != buf && 0 != buf.length()){
    	  buf =	buf.deleteCharAt(buf.length() - 1);
    	}
    }
}
