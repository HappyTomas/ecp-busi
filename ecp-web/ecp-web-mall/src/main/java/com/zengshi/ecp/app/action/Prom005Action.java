package com.zengshi.ecp.app.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.zengshi.ecp.app.req.Prom005Req;
import com.zengshi.ecp.app.resp.Prom005Resp;
import com.zengshi.ecp.app.resp.PromSkuRespVO;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.busi.search.vo.GoodSearchResult;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalog2SiteRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.SortField;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.search.dubbo.search.translator.GdsTranslator;
import com.zengshi.ecp.search.dubbo.search.util.ESort;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;



@Service("prom005")
@Action(bizcode="prom005",userCheck=false)
@Scope("prototype")
public class Prom005Action extends AppBaseAction<Prom005Req, Prom005Resp>{

	private static String solrCollectionName="promcollection";
	
	private final static String SUFFIX_IMAGE_SIZE = "_220x220!";
	
	private static final String APP = "2";
	
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IPromQueryRSV promQueryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
		
		//店铺编码为空
		if(this.request.getShopId()==null){
			  throw new BusinessException("店铺编码不能为空");
		}
		
		if(this.request.getPageSize()==0){
			this.request.setPageSize(10);
		}
		
		if(this.request.getPageNo()==0){
			this.request.setPageNo(1);
		}
	    SearchParam searchParam = new SearchParam();
        searchParam.setCurrentSiteId(SiteLocaleUtil.getSite());
        searchParam.setCollectionName(solrCollectionName);
        searchParam.setIfRetDocList(true);
        
        List<ExtraFieldQueryField> extraANDFieldQueryList=new ArrayList<ExtraFieldQueryField>();
        
        ExtraFieldQueryField extraFieldQueryField=new ExtraFieldQueryField();
        extraFieldQueryField.setName("shopId");
        extraFieldQueryField.setValue(this.request.getShopId()+"");
        extraANDFieldQueryList.add(extraFieldQueryField);
        
        
        ExtraFieldQueryField extraFieldQueryField1=new ExtraFieldQueryField();
        extraFieldQueryField1.setName("siteId");
        extraFieldQueryField1.setValue(SiteLocaleUtil.getSite()+"");
        extraANDFieldQueryList.add(extraFieldQueryField1);
        
        //非空
        if(StringUtils.isNotBlank(this.request.getPromTypeCode())){
        	 ExtraFieldQueryField extraFieldQueryField2=new ExtraFieldQueryField();
             extraFieldQueryField2.setName("promTypeCode");
             extraFieldQueryField2.setValue(this.request.getPromTypeCode());
             extraANDFieldQueryList.add(extraFieldQueryField2);
        }
       
        
        searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        
        searchParam.setPageNo(this.request.getPageNo());
        searchParam.setPageSize(this.request.getPageSize());
        
        
        //2、调用solr服务
		List<PromSkuRespVO> list=new ArrayList<PromSkuRespVO>();
	    SearchResult<PromSkuRespVO> result = SearchFacade.search(PromSkuRespVO.class,
                searchParam, null, null,null); 
	    
	    if(result!=null){
	    	if(CollectionUtils.isNotEmpty(result.getResultList())){
	    		list=result.getResultList();
	    		 PromTypeDTO promTypeDTO = new PromTypeDTO();
	    		for(PromSkuRespVO v:list){
	    			
	    		     // 获得促销类型对象
	                 promTypeDTO.setPromTypeCode(v.getPromTypeCode());
	                 PromTypeResponseDTO p = promQueryRSV.queryPromType(promTypeDTO);
	                 v.setNameShort(p.getNameShort());
	                 v.setPromTypeName(p.getPromTypeName());
	                 
	    			 v.setSkuId(Long.valueOf(v.getChildId()));
	    			 v.setPromId(Long.valueOf(v.getParentId()));
	    			
	    			 
	    			    SearchParam searchParam1 = new SearchParam();
	    		        searchParam1.setCurrentSiteId(SiteLocaleUtil.getSite());
	    		        searchParam1.setIfRetDocList(true);
	    		        searchParam1.setId(v.getGdsId()+"");
	    		        
    		           List<ExtraFieldQueryField> extraANDFieldQueryList1=new ArrayList<ExtraFieldQueryField>();
				        ExtraFieldQueryField extraFieldQueryFieldt=new ExtraFieldQueryField();
				        extraFieldQueryFieldt.setName("firstSkuId");
				        extraFieldQueryFieldt.setValue(v.getSkuId()+"");
				        extraANDFieldQueryList1.add(extraFieldQueryFieldt);
				        searchParam1.setExtraANDFieldQueryList(extraANDFieldQueryList1);
        
        
	    		        searchParam1.setPageNo(1);
	    		        searchParam1.setPageSize(10);
	    		        
	    		        //排序
	    		        List<SortField> sortFieldList=new ArrayList<SortField>();
	    		        SortField s=new SortField("sales", ESort.DESC);
	    		        sortFieldList.add(s);
	    		        searchParam1.setSortFieldList(sortFieldList);
	    		        
	    		        
	    		        //2、调用solr服务
	    		        SearchResult<GoodSearchResult> result1 = SearchFacade.search(GoodSearchResult.class,
	    		                searchParam1, null, null,new GdsTranslator(ApplicationContextUtil.getBean("gdsCatalog2SiteRSV",
	    		                        IGdsCatalog2SiteRSV.class))); 
	    			    
	    			    if(result1!=null){
	    			    	if(CollectionUtils.isNotEmpty(result1.getResultList())){
	    			    		GoodSearchResult r=result1.getResultList().get(0);
	    			    		r.render();//初始化数据
	    			    		v.setPrice(r.getDefaultPrice());
	    			    		v.setPromPrice(r.getDiscountPrice());
	    			    		//如何获得url
	    			    		 v.setImageUrl(r.getImageUrl());
	    			    		v.setSales(r.getSales());
	    			    	}
	    			    }
                     if(v.getPrice()==null){
                    	 //价格获得
	    			     GdsSkuInfoReqDTO dto=new GdsSkuInfoReqDTO();
	    			     dto.setId(v.getSkuId());
	                     //库存查询 设置条件
	                     SkuQueryOption[] skuQuery={SkuQueryOption.BASIC,SkuQueryOption.MAINPIC,SkuQueryOption.SHOWPRICE};
	                     dto.setSkuQuery(skuQuery);
	                     //调用商品接口
	                     GdsSkuInfoRespDTO  respDTO=new GdsSkuInfoRespDTO();
	                     respDTO=gdsSkuInfoQueryRSV.querySkuInfoByOptions(dto);
	                     v.setPrice(respDTO.getRealPrice());
	                     v.setPromPrice(v.getPrice());
	                     v.setSales(0l);
	                     //如何获得url
	                     v.setImageUrl(respDTO.getMainPic()==null?ImageUtil.getImageUrl(null):ImageUtil.getImageUrl(respDTO.getMainPic().getMediaUuid()+SUFFIX_IMAGE_SIZE));
                     }
                     PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
                     CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
                     promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
                     promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
                     promRuleCheckDTO.setGdsId(v.getGdsId());
                     promRuleCheckDTO.setChannelValue(APP);
                     promRuleCheckDTO.setShopId(this.request.getShopId());
                     promRuleCheckDTO.setSkuId(v.getSkuId());
                     promRuleCheckDTO.setBasePrice(v.getPrice());
                     promRuleCheckDTO.setBuyPrice(v.getPromPrice());
                     promRuleCheckDTO.setShopName(" ");
                     promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                     promRuleCheckDTO.setPromId(v.getPromId());
                     
                    List<PromListRespDTO> promInfoList= promRSV.listPromForSolr(promRuleCheckDTO);
                    
                    if(CollectionUtils.isNotEmpty(promInfoList)){
                    	if(promInfoList.get(0)!=null){
                    		if(promInfoList.get(0).getPromSkuPriceRespDTO()!=null){
                    			BigDecimal value=promInfoList.get(0).getPromSkuPriceRespDTO().getDiscountFinalPrice();
                    			v.setPromPrice(value!=null?value.longValue():v.getPrice());
                    		}
                    	}
                    }
	    			
	    		}
	    	}
	    }
        //3、封装返回页面参数
		
        this.response.setResList(list);
    }
	//test data
	private List<PromSkuRespVO> testData(){
		List<PromSkuRespVO> list=new ArrayList<PromSkuRespVO>();
		PromSkuRespVO v=new PromSkuRespVO();
		v.setNameShort("满赠");
		v.setPromTypeCode("1000000017");
		v.setPromTypeName("赠送");
		v.setGdsId(123l);
		v.setGdsName("test123");
		v.setPrice(12l);
		v.setPromId(1000l);
		v.setPromPrice(900l);
		v.setSkuId(222l);
		v.setSkuName("testskuName");
		
		list.add(v);
		
		PromSkuRespVO v1=new PromSkuRespVO();
		v1.setNameShort("满减");
		v1.setPromTypeCode("1000000007");
		v1.setPromTypeName("满减");
		v1.setGdsId(1223l);
		v1.setGdsName("test2123");
		v1.setPrice(122l);
		v1.setPromId(10020l);
		v1.setPromPrice(9002l);
		v1.setSkuId(2222l);
		v1.setSkuName("testskuName2");
		list.add(v1);
		
		PromSkuRespVO v2=new PromSkuRespVO();
		v2.setNameShort("打折");
		v2.setPromTypeCode("1000000006");
		v2.setPromTypeName("打折");
		v2.setGdsId(1243l);
		v2.setGdsName("tes4t123");
		v2.setPrice(124l);
		v2.setPromId(10040l);
		v2.setPromPrice(9400l);
		v2.setSkuId(2242l);
		v2.setSkuName("testskuName4");
		list.add(v2);
		
		return list;
		
	}

}
