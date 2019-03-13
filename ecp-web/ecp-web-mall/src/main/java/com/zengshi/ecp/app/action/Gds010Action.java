package com.zengshi.ecp.app.action;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds010Req;
import com.zengshi.ecp.app.resp.Gds010Resp;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 收藏商品 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日下午2:40:37 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds010")
@Action(bizcode = "gds010", userCheck = true)
@Scope("prototype")
public class Gds010Action extends AppBaseAction<Gds010Req, Gds010Resp> {

	@Resource
	private IOrdCartRSV ordCartRSV;

	@Resource
	private IGdsCollectRSV iGdsCollectRSV;

	private static final String MODULE = Gds010Action.class.getName();

	@Override
	protected void getResponse() throws BusinessException, SystemException,
			Exception {

		Gds010Req gds010Req = this.request;
		Gds010Resp gds010Resp = this.response;
		if (gds010Req.getSkuId() == null) {
			gds010Resp.setFlag("0");
			gds010Resp.setMsg("系统错误，单品主键为空");

		}

		GdsCollectReqDTO gdsCollectReqDTO = new GdsCollectReqDTO();
		//判断是否是取消收藏
		if (gds010Req.getIfCancel() != null && gds010Req.getIfCancel()
				&& gds010Req.getCollectId() != null) {

			gdsCollectReqDTO.setId(gds010Req.getCollectId());
			iGdsCollectRSV.deleteGdsCollectByPK(gdsCollectReqDTO);
			gds010Resp.setFlag("1");
            gds010Resp.setMsg("取消收藏成功");
		} else {

			try {
				Long _skuId = gds010Req.getSkuId();
				gdsCollectReqDTO.setSkuId(_skuId);

				Long collectId = iGdsCollectRSV.addGdsCollect(gdsCollectReqDTO);
				gds010Resp.setFlag("1");
				gds010Resp.setMsg("收藏成功");
				gds010Resp.setCollectId(collectId);

			} catch (BusinessException e) {
				if (StringUtils
						.equals(e.getErrorCode(),
								GdsErrorConstants.GdsCollect.ERROR_GOODS_COLLECT_251000)) {
					gds010Resp.setFlag("2");
					gds010Resp.setMsg("已收藏该商品");
				}
			} catch (NumberFormatException e) {
				gds010Resp.setFlag("0");
				gds010Resp.setMsg("系统错误，单品主键不是Long类型");

			}
		}

	}

}