package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.IM003Req;
import com.zengshi.ecp.app.resp.IM003Resp;
import com.zengshi.ecp.app.resp.vo.IM00301VO;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsBrowseHisRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.DefaultExceptionCode;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: 浏览商品 <br>
 * Project Name:ecp-web-im <br>
 * Description: <br>
 * Date:2017年1月11日下午4:54:26  <br>
 * Copyright (c) 2017 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
@Service("im003")
@Action(bizcode="im003", userCheck=true)
@Scope("prototype")
public class IM003Action extends AppBaseAction<IM003Req, IM003Resp> {

	@Resource
	private IGdsBrowseHisRSV gdsBrowseHisRSV; 
	@Resource
	private IGdsInfoQueryRSV gdsInfoQueryRSV;
	
	@Override
	protected void getResponse() throws BusinessException, SystemException, Exception {
		this.checkRequest();//校验入参
		
		//请求
		Long shopId = this.request.getShopId();
		Integer pageNo = this.request.getPageNumber();
		Integer pageSize = this.request.getPageSize();
		BaseStaff baseStaff = StaffLocaleUtil.getStaff();
		
		GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();
		gdsBrowseHisReqDTO.setStaffId(baseStaff.getId());//当前用户
		gdsBrowseHisReqDTO.setShopId(shopId);	//访问店铺
		gdsBrowseHisReqDTO.setStatus("1");
		gdsBrowseHisReqDTO.setPageNo(pageNo);	//当前页
		gdsBrowseHisReqDTO.setPageSize(pageSize);//分页大小
		PageResponseDTO<GdsBrowseHisRespDTO> pageGdsBrowseHisRespDTO = gdsBrowseHisRSV.queryGdsBrowseHisByPage(gdsBrowseHisReqDTO);
		if(pageGdsBrowseHisRespDTO == null){
			throw new BusinessException(new DefaultExceptionCode("2001","浏览商品历史服务查询失败"));
		}
		List<GdsBrowseHisRespDTO> listGdsBrowseHisRespDTO = pageGdsBrowseHisRespDTO.getResult();
		List<IM00301VO> listvo = new ArrayList<IM00301VO>(); 
		if(CollectionUtils.isNotEmpty(listGdsBrowseHisRespDTO)){
			for (GdsBrowseHisRespDTO rspDTO : listGdsBrowseHisRespDTO) {
				IM00301VO vo01 = new IM00301VO();
				GdsInfoDetailRespDTO gdsInfo = this.getGdsInfo(rspDTO.getGdsId());
				if(gdsInfo!=null){
					vo01.setId(gdsInfo.getId());
					vo01.setFirstSkuId(gdsInfo.getSkuInfo()==null?null:gdsInfo.getSkuInfo().getId());
					vo01.setImageUrl(gdsInfo.getMainPic()==null?"":gdsInfo.getMainPic().getURL());
					vo01.setGdsName(gdsInfo.getGdsName());
					vo01.setGdsSubHead(gdsInfo.getGdsSubHead());
					vo01.setGdsDesc(StringUtil.isBlank(gdsInfo.getGdsDesc())?"":FileUtil.readFile2Text(gdsInfo.getGdsDesc(), "UTF-8"));
					vo01.setShopId(gdsInfo.getShopId());
					vo01.setDefaultPrice(gdsInfo.getSkuInfo().getRealPrice());//默认
					listvo.add(vo01);
				}
				
			}
		}
		
		//响应
		this.response.setCount(pageGdsBrowseHisRespDTO.getCount());
		this.response.setPageCount(pageGdsBrowseHisRespDTO.getPageCount());
		this.response.setPageResp(listvo);
		
	}

	/**
	 * 
	 * checkRequest:(校验入参). <br/> 
	 * 
	 * @author linby
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void checkRequest() throws BusinessException{
		if(this.request.getShopId() == null){
			throw new BusinessException(new DefaultExceptionCode("1001","店铺id不能为空"));
		}
	}
	
	/**
	 * 
	 * getGdsInfo:(获取商品信息). <br/> 
	 * 
	 * @author linby
	 * @param gdsId
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private GdsInfoDetailRespDTO getGdsInfo(Long gdsId) throws BusinessException{
		GdsInfoReqDTO dto = new GdsInfoReqDTO();
        dto.setId(gdsId);
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[2];
        SkuQueryOption[] skuQuerys = new SkuQueryOption[2];
        gdsQueryOptions[0] = GdsQueryOption.BASIC;
        gdsQueryOptions[1] = GdsQueryOption.MAINPIC;
        skuQuerys[0] = SkuQueryOption.BASIC;
        skuQuerys[1] = SkuQueryOption.PRICE;
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQuerys);
        GdsInfoDetailRespDTO resultDto = gdsInfoQueryRSV.queryGdsInfoDetail(dto);

		return resultDto;
	}
}
