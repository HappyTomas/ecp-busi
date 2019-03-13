package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds013Req;
import com.zengshi.ecp.app.req.Gds014Req;
import com.zengshi.ecp.app.resp.Gds013Resp;
import com.zengshi.ecp.app.resp.Gds014Resp;
import com.zengshi.ecp.app.resp.gds.GdsPropBaseInfo;
import com.zengshi.ecp.app.resp.gds.GdsPropValueBaseInfo;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants.GdsEvalReply;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdEvaluationRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月12日下午5:16:50 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds014")
@Action(bizcode = "gds014", userCheck = false)
@Scope("prototype")
public class Gds014Action extends AppBaseAction<Gds014Req, Gds014Resp> {

	private static final String MODULE = Gds014Action.class.getName();
	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Gds014Req gds014Req = this.request;
		Gds014Resp gds014Resp = this.response;

		List<GdsPropBaseInfo> gdsPropBaseInfos = new ArrayList<GdsPropBaseInfo>();
		// 构造价格
		GdsPropBaseInfo gdsPropBaseInfoPrice = new GdsPropBaseInfo();
		gdsPropBaseInfoPrice.setId(-1L);
		gdsPropBaseInfoPrice.setPropName("价格区间");
		List<GdsPropValueBaseInfo> gdsPropValueBaseInfos = new ArrayList<GdsPropValueBaseInfo>();
		GdsPropValueBaseInfo gdsPropValueBaseInfo1 = new GdsPropValueBaseInfo();
		gdsPropValueBaseInfo1.setId(1l);
		gdsPropValueBaseInfo1.setPropValue("0-89");
		gdsPropValueBaseInfo1.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo1);
		GdsPropValueBaseInfo gdsPropValueBaseInfo2 = new GdsPropValueBaseInfo();
		gdsPropValueBaseInfo2.setId(2l);
		gdsPropValueBaseInfo2.setPropValue("90-199");
		gdsPropValueBaseInfo2.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo2);
		GdsPropValueBaseInfo gdsPropValueBaseInfo3 = new GdsPropValueBaseInfo();
		gdsPropValueBaseInfo3.setId(3l);
		gdsPropValueBaseInfo3.setPropValue("200-299");
		gdsPropValueBaseInfo3.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo3);
		GdsPropValueBaseInfo gdsPropValueBaseInfo4 = new GdsPropValueBaseInfo();
		gdsPropValueBaseInfo4.setId(4l);
		gdsPropValueBaseInfo4.setPropValue("300-399");
		gdsPropValueBaseInfo4.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo4);
		GdsPropValueBaseInfo gdsPropValueBaseInfo5 = new GdsPropValueBaseInfo();
		gdsPropValueBaseInfo5.setId(5l);
		gdsPropValueBaseInfo5.setPropValue("400以上");
		gdsPropValueBaseInfo5.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo5);
		gdsPropBaseInfoPrice.setValues(gdsPropValueBaseInfos);
		gdsPropBaseInfos.add(gdsPropBaseInfoPrice);
		// 组装分类属性
		if (StringUtil.isNotBlank(gds014Req.getCatgCode())) {
			GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();

			reqDTO.setCatgCode(gds014Req.getCatgCode());
			GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
					.querySearchProps(reqDTO);
			if (gdsCatg2PropRelationRespDTO != null) {

				List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO
						.getSearchProps();

				if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
					for (GdsPropRespDTO gdsPropRespDTO : gdsPropRespDTOList) {
						// 属性和属性值列表可为空
						GdsPropBaseInfo gdsPropBaseInfo = new GdsPropBaseInfo();
						ObjectCopyUtil.copyObjValue(gdsPropRespDTO,
								gdsPropBaseInfo, null, false);
						List<GdsPropValueRespDTO> gdsPropValueRespDTOs = gdsPropRespDTO
								.getValues();
						List<GdsPropValueBaseInfo> propValueBaseInfos = new ArrayList<GdsPropValueBaseInfo>();
						if (gdsPropValueRespDTOs != null) {
							for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropValueRespDTOs) {
								GdsPropValueBaseInfo propValueBaseInfo = new GdsPropValueBaseInfo();
								ObjectCopyUtil.copyObjValue(
										gdsPropValueRespDTO, propValueBaseInfo,
										null, false);
								propValueBaseInfos.add(propValueBaseInfo);
							}

						}
						gdsPropBaseInfo.setValues(propValueBaseInfos);
						gdsPropBaseInfos.add(gdsPropBaseInfo);
					}
				
				}
			}
		}
		gds014Resp.setPropList(gdsPropBaseInfos);
	}

}