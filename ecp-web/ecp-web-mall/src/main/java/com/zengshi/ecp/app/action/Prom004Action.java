package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.util.CollectionUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Prom004Req;
import com.zengshi.ecp.app.resp.Prom004Resp;
import com.zengshi.ecp.app.resp.PromTypeRespVO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.search.dubbo.search.ExtraFieldQueryField;
import com.zengshi.ecp.search.dubbo.search.SearchFacade;
import com.zengshi.ecp.search.dubbo.search.SearchParam;
import com.zengshi.ecp.search.dubbo.search.result.FieldFacetResult;
import com.zengshi.ecp.search.dubbo.search.result.SearchResult;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SiteLocaleUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;



@Service("prom004")
@Action(bizcode="prom004",userCheck=false)
@Scope("prototype")
public class Prom004Action extends AppBaseAction<Prom004Req, Prom004Resp>{
	
	private static String solrCollectionName="promcollection";
	
    @Resource
    private IPromQueryRSV promQueryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
        
        // 1、入参组织参数
		//店铺编码为空
		if(this.request.getShopId()==null){
			  throw new BusinessException("店铺编码不能为空");
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
        
        searchParam.setExtraANDFieldQueryList(extraANDFieldQueryList);
        
        List<String> facetFieldList=new ArrayList<String>();
        facetFieldList.add("promTypeCode");
        searchParam.setFieldFacetFieldList(facetFieldList);
        
        searchParam.setFacetLimit(Integer.MAX_VALUE);
        
        searchParam.setPageNo(1);
        searchParam.setPageSize(1);
        //2、调用solr服务
		List<PromTypeRespVO> list=new ArrayList<PromTypeRespVO>();
	    SearchResult<PromTypeRespVO> result = SearchFacade.search(PromTypeRespVO.class,
                searchParam, null, null,null); 
	    
	    if(result!=null){
	    	 Map m=result.getFieldFacetResultMap();
	    	 //当没有分组时 支持代码健壮 不报错null
	    	 if(m!=null){
		    	 PromTypeDTO promTypeDTO = new PromTypeDTO();
		    	 FieldFacetResult r= null;
		    	 if(m.get("promTypeCode")!=null){
		    		 r=(FieldFacetResult)m.get("promTypeCode");
		    	 }
		    	 List totalList=new ArrayList();
		    	 if(r!=null){
		    		 totalList=r.getValue();
		    	 }
		    	if(CollectionUtils.isNotEmpty(totalList)){
		    		for(int i=0;i<totalList.size();i++){
		    			 PromTypeRespVO t=new PromTypeRespVO();
		                 t.setPromTypeCode(((FieldFacetResult.Count)totalList.get(i)).getValue());
		                 // 获得促销类型对象
		                 promTypeDTO.setPromTypeCode(t.getPromTypeCode());
		                 PromTypeResponseDTO p = promQueryRSV.queryPromType(promTypeDTO);
		                 t.setNameShort(p.getNameShort());
		                 t.setPromTypeName(p.getPromTypeName());
		                 list.add(t);
		    		}
		    	}
	    	 }
	    }
	    
        //3、封装返回页面参数
		
        this.response.setResList(list);
    }
	//test data
	private List<PromTypeRespVO> testData(){
		List<PromTypeRespVO> list=new ArrayList<PromTypeRespVO>();
		PromTypeRespVO v=new PromTypeRespVO();
		v.setNameShort("满赠");
		v.setPromTypeCode("1000000017");
		v.setPromTypeName("赠送");
		list.add(v);
		
		PromTypeRespVO v1=new PromTypeRespVO();
		v1.setNameShort("满减");
		v1.setPromTypeCode("1000000007");
		v1.setPromTypeName("满减");
		list.add(v1);
		
		PromTypeRespVO v2=new PromTypeRespVO();
		v2.setNameShort("打折");
		v2.setPromTypeCode("1000000006");
		v2.setPromTypeName("打折");
		list.add(v2);
		
		return list;
		
	}

}
