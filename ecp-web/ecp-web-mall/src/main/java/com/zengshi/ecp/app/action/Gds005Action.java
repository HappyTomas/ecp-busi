package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds005Req;
import com.zengshi.ecp.app.resp.Gds005Resp;
import com.zengshi.ecp.app.resp.gds.GdsEvalRespBaseInfo;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsEvalRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsEvalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 用户评论 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午11:31:11 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("gds005")
@Action(bizcode = "gds005", userCheck = false)
@Scope("prototype")
public class Gds005Action extends AppBaseAction<Gds005Req, Gds005Resp> {

	@Resource
	private IOrdCartRSV ordCartRSV;

	@Resource
	private IShopInfoRSV shopInfoRSV;

	@Resource
	private IGdsEvalRSV iGdsEvalRSV;
	@Resource
	private ICustInfoRSV iCustInfoRSV;
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;

	private static final String MODULE = Gds005Action.class.getName();

	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		Gds005Req gds005Req = this.request;
		Gds005Resp gds005Resp = this.response;
		new HashMap<String, Object>();
		GdsEvalReqDTO gdsEvalReqDTO = new GdsEvalReqDTO();
		gdsEvalReqDTO.setPageSize(gds005Req.getPageSize());
		gdsEvalReqDTO.setPageNo(gds005Req.getPageNo());
		ObjectCopyUtil.copyObjValue(gds005Req, gdsEvalReqDTO, "", false);
		GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
		gdsInfo.setId(gds005Req.getGdsId());
		GdsInfoRespDTO gdsInfoRespDTO =  gdsInfoQueryRSV.queryGdsInfo(gdsInfo);
		gdsEvalReqDTO.setShopId(gdsInfoRespDTO.getShopId());
		gdsEvalReqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
		if(StringUtil.isNotBlank(gds005Req.getEvalType())){
			
			if(gds005Req.getEvalType().equals("00")){
				short score = 5;
				gdsEvalReqDTO.setScore(score);
			}else if(gds005Req.getEvalType().equals("01")){
				
				short scoreFrom = 2;
				short scoreTo = 4;
				gdsEvalReqDTO.setScoreFrom(scoreFrom);
				gdsEvalReqDTO.setScoreTo(scoreTo);
			}else if(gds005Req.getEvalType().equals("02")){
				short score = 1;
				gdsEvalReqDTO.setScore(score);
			}
				
			
		}

		PageResponseDTO<GdsEvalRespDTO> pageInfo = null;
		// 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
		EcpBasePageRespVO<Map> respVO = null;
		try {
			pageInfo = iGdsEvalRSV.queryGdsEvalRespDTOPagingForGdsDetail(gdsEvalReqDTO);

			if (StringUtil.isNotEmpty(pageInfo)) {
				List<GdsEvalRespDTO> resp = pageInfo.getResult();
				if (resp != null && resp.size() > 0) {
					for (GdsEvalRespDTO gdsEvalRespDTO : resp) {
						// 解析评价内容
						try {
							gdsEvalRespDTO.setDetail(FileUtil.readFile2Text(gdsEvalRespDTO.getContent(), "UTF-8"));
						} catch (Exception e) {
							continue;
						}
//						// 每条评价的对应回复的内容
//						if (gdsEvalRespDTO.getEvalReplyRespDTOs() != null
//								&& gdsEvalRespDTO.getEvalReplyRespDTOs().size() > 0) {
//							for (GdsEvalReplyRespDTO subDto : gdsEvalRespDTO.getEvalReplyRespDTOs()) {
//								// 解析回复内容
//								try {
//									subDto.setDetail(FileUtil.readFile2Text(subDto.getContent(), "UTF-8"));
//								} catch (Exception e) {
//									continue;
//								}
//							}
//						}
						// 获取评价客户的客户信息。
						CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
						custInfoReqDTO.setId(gdsEvalRespDTO.getStaffId());
						CustInfoResDTO custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
						gdsEvalRespDTO.setStaffName(custInfoResDTO.getStaffCode());
						gdsEvalRespDTO.setStaffLevelName(custInfoResDTO.getCustLevelName());
						gdsEvalRespDTO.setStaffLevelCode(custInfoResDTO.getCustLevelCode());
						gdsEvalRespDTO.setCustPic(ParamsTool.getImageUrl(custInfoResDTO.getCustPic(),"200x200!"));
					}
				}

				List<GdsEvalRespBaseInfo> gdsEvalRespBaseInfos = new ArrayList<GdsEvalRespBaseInfo>();
				if(!CollectionUtils.isEmpty(pageInfo.getResult())){
				for (GdsEvalRespDTO gdsEvalRespDTO : pageInfo.getResult()) {
					GdsEvalRespBaseInfo evalBaseInfo = new GdsEvalRespBaseInfo();
					ObjectCopyUtil.copyObjValue(gdsEvalRespDTO, evalBaseInfo, null, false);
					evalBaseInfo.setEvalReplyRespDTOs(null);
//					List<GdsEvalReplyRespBaseInfo> evalReplyRespBaseInfos = new ArrayList<GdsEvalReplyRespBaseInfo>();
//					for (GdsEvalReplyRespDTO gdsEvalReplyRespDTO : gdsEvalRespDTO.getEvalReplyRespDTOs()) {
//
//						GdsEvalReplyRespBaseInfo evalReplyRespBaseInfo = new GdsEvalReplyRespBaseInfo();
//
//						ObjectCopyUtil.copyObjValue(gdsEvalReplyRespDTO, evalReplyRespBaseInfo, null, false);
//						evalReplyRespBaseInfos.add(evalReplyRespBaseInfo);
//					}
//					evalBaseInfo.setEvalReplyRespDTOs(evalReplyRespBaseInfos);
					gdsEvalRespBaseInfos.add(evalBaseInfo);
				}
				}
				gds005Resp.setGdsEvalRespList(gdsEvalRespBaseInfos);
				gds005Resp.setCount(pageInfo.getCount());
				gds005Resp.setPageCount(pageInfo.getPageCount());
			}
		} catch (Exception e) {
			LogUtil.error(MODULE, "获取评价列表失败！", e);
			gds005Resp.setCount(0);
			gds005Resp.setGdsEvalRespList(null);
			gds005Resp.setPageCount(0);
		}
	}


	
	
}
