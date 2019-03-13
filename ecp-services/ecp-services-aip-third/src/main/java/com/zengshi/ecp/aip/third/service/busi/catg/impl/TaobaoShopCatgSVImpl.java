package com.zengshi.ecp.aip.third.service.busi.catg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.catg.interfaces.ICatgSV;
import com.zengshi.ecp.aip.third.service.busi.prop.interfaces.IPropSV;
import com.zengshi.ecp.aip.third.service.busi.token.interfaces.ITokenSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.request.ItemcatsAuthorizeGetRequest;
import com.taobao.api.response.ItemcatsAuthorizeGetResponse;

public class TaobaoShopCatgSVImpl implements ICatgSV {

	public static final String MODULE = TaobaoShopCatgSVImpl.class.getName();

	@Resource
	private IPropSV taobaoPropSV;
	
    @Resource
	private ITokenSV defaultTokenSV;
    
    @Resource
 	private ICatgSV taobaoCatgSV;

	//分类返回字段设置 cid,parent_cid,name,is_parent,status,taosir_cat
	private String fields;
	
	//分类属性返回字段设置cid,parent_cid,name,is_parent,status,features,taosir_cat
	private String fieldsProps;
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	
	public String getFieldsProps() {
		return fieldsProps;
	}
	public void setFieldsProps(String fieldsProps) {
		this.fieldsProps = fieldsProps;
	}
	
	@Override
	public CatgsRespDTO fetch(CatgReqDTO catgReqDTO) throws BusinessException {
		return this.fetch(catgReqDTO, null);
	}

	@Override
	public CatgsRespDTO fetchCatgAndProp(CatgReqDTO catgReqDTO)
			throws BusinessException {
		return this.fetch(catgReqDTO, "1");
	}

	/*
	 * 查询分类
	 */
	private CatgsRespDTO fetch(CatgReqDTO catgReqDTO, String flag)
			throws BusinessException {

		TaobaoClient client = new DefaultTaobaoClient(
				catgReqDTO.getServerUrl(), catgReqDTO.getAppkey(),
				catgReqDTO.getAppscret());

		ItemcatsAuthorizeGetRequest req = new ItemcatsAuthorizeGetRequest();

		// 返回字段设置
		if ("1".equals(flag)) {
			req.setFields(fieldsProps);
		} else {
			req.setFields(fields);
		}
	 
		CatgsRespDTO resps = new CatgsRespDTO();
		try {
			// 验证参数是否正确
			req.check();
			
			//获得token
			String token=defaultTokenSV.fetchShopToken(catgReqDTO.findBaseShopAuth());
			// 调用服务
			ItemcatsAuthorizeGetResponse rsp = client.execute(req,token);
			/*{"itemcats_authorize_get_response":{"seller_authorize":{"brands":{},"item_cats":{"item_cat":[{"cid":33,"is_parent":true,"name":"书籍\/杂志\/报纸","parent_cid":0,"sort_order":190,"status":"normal"},{"cid":50023724,"is_parent":true,"name":"其他","parent_cid":0,"sort_order":268,"status":"normal"}]}},"request_id":"zlycdp15a2us"}}*/
			// 返回结果解析
			if (rsp.isSuccess()) {
				LogUtil.error(MODULE, "天猫返回："+ JSON.toJSONString(rsp));
				List<CatgRespDTO> catgs = new ArrayList<CatgRespDTO>();
				
				if (rsp != null && rsp.getSellerAuthorize()!=null &&!CollectionUtils.isEmpty(rsp.getSellerAuthorize().getItemCats())) {
					
					for (ItemCat c : rsp.getSellerAuthorize().getItemCats()) { 
						catgs.add(tranlate(c,catgReqDTO));
					}
				}
				//新品分类
				if (rsp != null && rsp.getSellerAuthorize()!=null &&!CollectionUtils.isEmpty(rsp.getSellerAuthorize().getXinpinItemCats())) {
					for (ItemCat c : rsp.getSellerAuthorize().getXinpinItemCats()) { 
						catgs.add(tranlate(c,catgReqDTO));
					}
				}
				
			  //需要递归处理 当前授权的节点的分类
				HashMap<String,CatgRespDTO> map=new HashMap<String,CatgRespDTO>();
				if(!CollectionUtils.isEmpty(catgs)){
					for(CatgRespDTO c:catgs){
						//是父节点
						if(c.getIfParent()){ 
							catgReqDTO.setOutParentCatgCode(c.getCatgCode());
							getCatgsByCode(map,catgReqDTO);
						}
					}
				}
				 for (Map.Entry<String, CatgRespDTO> entry : map.entrySet()) {
					 catgs.add(entry.getValue());
				 }
		        resps.setCatgs(catgs);
				
			} else {
				LogUtil.error(MODULE, rsp.getBody().toString());
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{rsp.getBody().toString()});
			}

		} catch (ApiException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		} catch (Exception e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{e.toString()});
		}

		return resps;
	}
	//获得当前分类下 子分类
	private void getCatgsByCode(HashMap<String,CatgRespDTO> map,CatgReqDTO catgReqDTO){
		
		CatgsRespDTO itemCatg=taobaoCatgSV.fetch(catgReqDTO);
		if(itemCatg!=null && !CollectionUtils.isEmpty(itemCatg.getCatgs())){
			for(CatgRespDTO c1:itemCatg.getCatgs()){
				map.put(c1.getCatgCode(), c1);
				if(c1.getIfParent()){
					catgReqDTO.setOutParentCatgCode(c1.getCatgCode());
					getCatgsByCode(map,catgReqDTO);
				}
			}
		}
	}
	//转化数据
	private CatgRespDTO tranlate(ItemCat c ,CatgReqDTO catgReqDTO){

		if(!StringUtil.isBlank(c.getName())){
			CatgRespDTO resp = new CatgRespDTO();
			resp.setCatgCode(c.getCid().toString());
			resp.setIfParent(c.getIsParent());
			resp.setIfSale(c.getTaosirCat());
			resp.setName(c.getName());
			
			resp.setParentCatgCode(c.getParentCid().toString());
			
			resp.setSortOrder(c.getSortOrder());
			// 转化状态 默认有效
			resp.setStatus("1");
			if ("deleted".equalsIgnoreCase(c.getStatus())) {
				resp.setStatus("0");
			}
			if(!resp.getIfParent()){
				//获得分类属性 属性值
				 PropReqDTO propReqDTO=new PropReqDTO();
				 
				 ObjectCopyUtil.copyObjValue(catgReqDTO, propReqDTO, "", false);
				 
				 propReqDTO.setOutCatgCode(resp.getCatgCode());
				 
				 PropsRespDTO ps= taobaoPropSV.fetchPropValue(propReqDTO);
				 
				 List<PropRespDTO> props=new ArrayList<PropRespDTO>();
				 
				 if(ps!=null && !CollectionUtils.isEmpty(ps.getProps())){
					 props.addAll(ps.getProps());
					 resp.setProps(props);
				 }
			}
		
			 return resp;
		}else{
			return null;
		}
	}
}
