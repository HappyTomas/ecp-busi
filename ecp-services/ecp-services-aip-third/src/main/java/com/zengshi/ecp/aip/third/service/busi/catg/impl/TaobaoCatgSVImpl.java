package com.zengshi.ecp.aip.third.service.busi.catg.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.ItemCat;
import com.taobao.api.request.ItemcatsGetRequest;
import com.taobao.api.response.ItemcatsGetResponse;

public class TaobaoCatgSVImpl implements ICatgSV {

	public static final String MODULE = TaobaoCatgSVImpl.class.getName();

	@Resource
	private IPropSV taobaoPropSV;

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

		ItemcatsGetRequest req = new ItemcatsGetRequest();
		// 分类编码
		if (StringUtil.isNotBlank(catgReqDTO.getOutCatgCode())) {
			req.setCids(catgReqDTO.getOutCatgCode());
		}
		// req.setDatetime(StringUtils.parseDateTime("2015-01-01 00:00:00"));

		// 返回字段设置
		if ("1".equals(flag)) {
			req.setFields(fieldsProps);
		} else {
			req.setFields(fields);
		}
		// 父分类编码
		if (StringUtil.isNotBlank(catgReqDTO.getOutParentCatgCode())) {
			// req.setParentCid(50011992L);
			req.setParentCid(Long.valueOf(catgReqDTO.getOutParentCatgCode()));
		}
		CatgsRespDTO resps = new CatgsRespDTO();
		try {
			// 验证参数是否正确
			req.check();
			// 调用服务
			ItemcatsGetResponse rsp = client.execute(req);
			// 返回结果解析
			if (rsp.isSuccess()) {
				List<CatgRespDTO> catgs = new ArrayList<CatgRespDTO>();
				
				HashMap<String,String> map=new HashMap<String,String>();
				LogUtil.error(MODULE, "天猫返回："+JSON.toJSONString(rsp));
				if (rsp != null && !CollectionUtils.isEmpty(rsp.getItemCats())) {
					for (ItemCat c : rsp.getItemCats()) {
						CatgRespDTO resp = new CatgRespDTO();
						resp.setCatgCode(c.getCid().toString());
						resp.setIfParent(c.getIsParent());
						resp.setIfSale(c.getTaosirCat());
						resp.setName(c.getName());
						resp.setParentCatgCode(c.getParentCid().toString());
						resp.setSortOrder(c.getSortOrder());
						
						map.put(resp.getCatgCode(), resp.getName());
						
						// 转化状态 默认有效
						resp.setStatus("1");
						if ("deleted".equalsIgnoreCase(c.getStatus())) {
							resp.setStatus("0");
						}

						// 转化属性
					/*	if (!CollectionUtils.isEmpty(c.getFeatures())) {
							List<PropRespDTO> props=new ArrayList<PropRespDTO>();
							for (Feature f : c.getFeatures()) {
								 PropReqDTO propReqDTO=new PropReqDTO();
								 propReqDTO.setPropId(f.getAttrKey());
								 PropsRespDTO ps= taobaoPropSV.fetchPropValue(propReqDTO);
								 if(ps!=null && !CollectionUtils.isEmpty(ps.getProps())){
									 props.addAll(ps.getProps());
								 }
							}
							resp.setProps(props);
						}*/
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
						catgs.add(resp);
					}
					resps.setCatgs(catgs);
				}
				
				//转化父分类名称
				if(!CollectionUtils.isEmpty(resps.getCatgs())){
					for(CatgRespDTO c:resps.getCatgs()){
						if(StringUtil.isBlank(c.getParentCatgName())){
							c.setParentCatgName(map.get(c.getParentCatgCode()));
						}
					}
				}
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
}
