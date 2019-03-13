package com.zengshi.ecp.aip.third.service.busi.prop.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.PropReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropValueRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropsRespDTO;
import com.zengshi.ecp.aip.third.service.busi.prop.interfaces.IPropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Feature;
import com.taobao.api.domain.ItemProp;
import com.taobao.api.domain.PropValue;
import com.taobao.api.request.ItempropsGetRequest;
import com.taobao.api.response.ItempropsGetResponse;

public class TaobaoPropSVImpl implements IPropSV {

	public static final String MODULE = TaobaoPropSVImpl.class.getName();

	//属性返回字段设置 pid,name,must,multi
	private String fields;
	
	//属性值返回字段设置pid,name,must,multi,prop_values
	private String fieldsValue;
	
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	public String getFieldsValue() {
		return fieldsValue;
	}
	public void setFieldsValue(String fieldsValue) {
		this.fieldsValue = fieldsValue;
	}

	
	@Override
	public PropsRespDTO fetch(PropReqDTO propReqDTO) throws BusinessException { 
		return this.fetch(propReqDTO, null);
	}

	@Override
	public PropsRespDTO fetchPropValue(PropReqDTO propReqDTO)
			throws BusinessException {
		return this.fetch(propReqDTO, "1");
	}
	
	private PropsRespDTO fetch(PropReqDTO propReqDTO,String flag) throws BusinessException {

		TaobaoClient client = new DefaultTaobaoClient(
				propReqDTO.getServerUrl(), propReqDTO.getAppkey(),
				propReqDTO.getAppscret());

		ItempropsGetRequest req = new ItempropsGetRequest();
		
		req.setFields(fields);
		if("1".equals(flag)){
			req.setFields(fieldsValue);
		}
		
		//外部分类
		if(StringUtil.isNotBlank(propReqDTO.getOutCatgCode())){
			req.setCid(Long.valueOf(propReqDTO.getOutCatgCode()));
		}
		if(StringUtil.isNotBlank(propReqDTO.getPropId())){
			req.setPid(Long.valueOf(propReqDTO.getPropId()));
		}
		if(StringUtil.isNotBlank(propReqDTO.getParentPropId())){
			req.setParentPid(Long.valueOf(propReqDTO.getParentPropId()));
		}
	/*	req.setIsKeyProp(true);
		req.setIsSaleProp(true);
		req.setIsColorProp(true);
		req.setIsEnumProp(true);
		req.setIsInputProp(true);
		req.setIsItemProp(true);*/
		//req.setDatetime(StringUtils.parseDateTime("2010-01-01 00:00:00"));
		//req.setChildPath("3932:13221;2113:2345");
		//req.setType(2L);
		//req.setAttrKeys("");
		
		PropsRespDTO resps = new PropsRespDTO();
		try {
			// 验证参数是否正确
			req.check();
			// 调用服务
			ItempropsGetResponse rsp = client.execute(req);
			// 返回结果解析
			if (rsp.isSuccess()) {
				List<PropRespDTO> props = new ArrayList<PropRespDTO>();
				PropRespDTO resp = new PropRespDTO();
				if (rsp != null && !CollectionUtils.isEmpty(rsp.getItemProps())) {
					for (ItemProp c : rsp.getItemProps()) {
						
						if(c.getCid()==null && req.getCid()!=null){
							resp.setCatgCode(req.getCid().toString());
						}
						resp.setPid(c.getPid());
						resp.setPname(c.getName());
						resp.setSortOrder(c.getSortOrder());
						resp.setIfColorProp(c.getIsColorProp());
						resp.setIfEnumProp(c.getIsEnumProp());
						resp.setIfInputProp(c.getIsInputProp());
						resp.setIfItemProp(c.getIsItemProp());
						resp.setIfKeyProp(c.getIsKeyProp());
						resp.setIfSaleProp(c.getIsSaleProp());
						resp.setMult(c.getMulti());
						resp.setMust(c.getMust());
						resp.setPropType(c.getType());
						resp.setRequired(c.getRequired());
						// 转化状态 默认有效
						resp.setStatus("1");
						 
						if ("deleted".equalsIgnoreCase(c.getStatus())) {
							resp.setStatus("0");
						}
						
						if(!CollectionUtils.isEmpty(c.getFeatures())){
							HashMap features=new HashMap();
							for(Feature f:c.getFeatures()){
								features.put(f.getAttrKey(), f.getAttrValue());
							}
							resp.setFeatures(features);
						}
						
						if(!CollectionUtils.isEmpty(c.getPropValues())){
							List<PropValueRespDTO> propValues=new ArrayList<PropValueRespDTO>();
							for(PropValue v:c.getPropValues()){
								PropValueRespDTO d=new PropValueRespDTO();
								d.setName(v.getName());
								d.setNameAlias(v.getNameAlias());
								d.setPid(v.getPid());
								d.setVid(v.getVid());
								d.setPname(v.getPropName());
								d.setSortOrder(v.getSortOrder());
								d.setStatus("1");
								if ("deleted".equalsIgnoreCase(v.getStatus())) {
									d.setStatus("0");
								}
								d.setVid(v.getVid());
								propValues.add(d);
							}
							resp.setPropValues(propValues);
						}
						props.add(resp);
					}
					resps.setProps(props);
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
