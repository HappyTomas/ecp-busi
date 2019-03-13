package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pointmgds014Req;
import com.zengshi.ecp.app.resp.Pointmgds014Resp;
import com.zengshi.ecp.app.resp.gds.PointGdsPropBaseInfo;
import com.zengshi.ecp.app.resp.gds.PointGdsPropValueBaseInfo;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

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
@Service("pointmgds014")
@Action(bizcode = "pointmgds014", userCheck = false)
@Scope("prototype")
public class PointGds014Action extends AppBaseAction<Pointmgds014Req, Pointmgds014Resp> {

	private static final String MODULE = PointGds014Action.class.getName();
	@Resource
	private IGdsCategoryRSV gdsCategoryRSV;

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {
		Pointmgds014Req gds014Req = this.request;
		Pointmgds014Resp gds014Resp = this.response;

		List<PointGdsPropBaseInfo> gdsPropBaseInfos = new ArrayList<PointGdsPropBaseInfo>();
		// 构造价格
		PointGdsPropBaseInfo gdsPropBaseInfoPrice = new PointGdsPropBaseInfo();
		gdsPropBaseInfoPrice.setId(-1L);
		gdsPropBaseInfoPrice.setPropName("价格区间");
		List<PointGdsPropValueBaseInfo> gdsPropValueBaseInfos = new ArrayList<PointGdsPropValueBaseInfo>();
		PointGdsPropValueBaseInfo gdsPropValueBaseInfo5 = new PointGdsPropValueBaseInfo();
		gdsPropValueBaseInfo5.setId(0l);
		gdsPropValueBaseInfo5.setPropValue("全部");
		gdsPropValueBaseInfo5.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo5);
		
		PointGdsPropValueBaseInfo gdsPropValueBaseInfo1 = new PointGdsPropValueBaseInfo();
		gdsPropValueBaseInfo1.setId(1l);
		gdsPropValueBaseInfo1.setPropValue("0-4999");
		gdsPropValueBaseInfo1.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo1);
		PointGdsPropValueBaseInfo gdsPropValueBaseInfo2 = new PointGdsPropValueBaseInfo();
		gdsPropValueBaseInfo2.setId(2l);
		gdsPropValueBaseInfo2.setPropValue("5000-9999");
		gdsPropValueBaseInfo2.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo2);
		PointGdsPropValueBaseInfo gdsPropValueBaseInfo3 = new PointGdsPropValueBaseInfo();
		gdsPropValueBaseInfo3.setId(3l);
		gdsPropValueBaseInfo3.setPropValue("10000-14999");
		gdsPropValueBaseInfo3.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo3);
		PointGdsPropValueBaseInfo gdsPropValueBaseInfo4 = new PointGdsPropValueBaseInfo();
		gdsPropValueBaseInfo4.setId(4l);
		gdsPropValueBaseInfo4.setPropValue("15000以上");
		gdsPropValueBaseInfo4.setPropId(-1l);
		gdsPropValueBaseInfos.add(gdsPropValueBaseInfo4);
		gdsPropBaseInfoPrice.setValues(gdsPropValueBaseInfos);
		gdsPropBaseInfos.add(gdsPropBaseInfoPrice);
		// 组装分类属性
//		if (StringUtil.isNotBlank(gds014Req.getCatgCode())) {
//			GdsCatg2PropReqDTO reqDTO = new GdsCatg2PropReqDTO();
//
//			reqDTO.setCatgCode(gds014Req.getCatgCode());
//			GdsCatg2PropRelationRespDTO gdsCatg2PropRelationRespDTO = gdsCategoryRSV
//					.querySearchProps(reqDTO);
//			if (gdsCatg2PropRelationRespDTO != null) {
//
//				List<GdsPropRespDTO> gdsPropRespDTOList = gdsCatg2PropRelationRespDTO
//						.getSearchProps();
//
//				if (CollectionUtils.isNotEmpty(gdsPropRespDTOList)) {
//					for (GdsPropRespDTO gdsPropRespDTO : gdsPropRespDTOList) {
//						// 属性和属性值列表可为空
//						GdsPropBaseInfo gdsPropBaseInfo = new GdsPropBaseInfo();
//						ObjectCopyUtil.copyObjValue(gdsPropRespDTO,
//								gdsPropBaseInfo, null, false);
//						List<GdsPropValueRespDTO> gdsPropValueRespDTOs = gdsPropRespDTO
//								.getValues();
//						List<GdsPropValueBaseInfo> propValueBaseInfos = new ArrayList<GdsPropValueBaseInfo>();
//						if (gdsPropValueRespDTOs != null) {
//							for (GdsPropValueRespDTO gdsPropValueRespDTO : gdsPropValueRespDTOs) {
//								GdsPropValueBaseInfo propValueBaseInfo = new GdsPropValueBaseInfo();
//								ObjectCopyUtil.copyObjValue(
//										gdsPropValueRespDTO, propValueBaseInfo,
//										null, false);
//								propValueBaseInfos.add(propValueBaseInfo);
//							}
//
//						}
//						gdsPropBaseInfo.setValues(propValueBaseInfos);
//						gdsPropBaseInfos.add(gdsPropBaseInfo);
//					}
//				
//				}
//			}
//		}
		gds014Resp.setPropList(gdsPropBaseInfos);
	}

}