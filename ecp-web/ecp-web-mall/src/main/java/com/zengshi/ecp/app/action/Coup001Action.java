package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Coup001Req;
import com.zengshi.ecp.app.resp.Coup001Resp;
import com.zengshi.ecp.app.resp.CoupDetailResp;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupMeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeCountRespDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.coupon.dubbo.interfaces.ICoupDetailRSV;
import com.zengshi.ecp.coupon.dubbo.util.CouponConstants;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;

/**
 *   
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 我的优惠券列表<br>
 * Date:2016年3月7日上午10:36:56  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
@Service("coup001")
@Action(bizcode = "coup001", userCheck = true)
@Scope("prototype")
public class Coup001Action extends AppBaseAction<Coup001Req, Coup001Resp> {

	private static final String MODULE = Coup001Action.class.getName();
	@Resource
    private ICoupDetailRSV coupDetailRSV;
	
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
    	//01 获取入参
    	Long staffId = this.request.getStaffId();//用户id
    	String opeType = this.request.getOpeType();
        int pageNo = this.request.getPageNo();//页数
        int pageSize = this.request.getPageSize();//页面展现的数量
        CoupMeReqDTO coupMeReqDTO = new CoupMeReqDTO();
        coupMeReqDTO.setStaffId(staffId);
        coupMeReqDTO.setPageNo(pageNo);
        coupMeReqDTO.setPageSize(pageSize);
        coupMeReqDTO.setOpeType(opeType); Map<String, String> mapSort = new HashMap<String, String>();

        //1:可使用 2:已使用  0:已过期 3:已冻结
        switch (coupMeReqDTO.getOpeType()) {
        
        case CouponConstants.CoupDetail.opeType_0:
            mapSort.put("UPDATE_TIME", "0");
            coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_0);
            break;
        case CouponConstants.CoupDetail.opeType_1:
            mapSort.put("CREATE_TIME", "0");
            coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_1);
            break;
        case CouponConstants.CoupDetail.opeType_2:
            mapSort.put("USE_TIME", "0");
            coupMeReqDTO.setOpeType(CouponConstants.CoupDetail.opeType_2);
            break;
        default:
            mapSort.put("CREATE_TIME", "0");
        }
        coupMeReqDTO.setMapSort(mapSort);
        //02 调用接口查询我的优惠券
        List<CoupDetailResp> respList = new ArrayList<CoupDetailResp>();
        CoupMeCountRespDTO beanDto = coupDetailRSV.queryCoupDetail(coupMeReqDTO);
        PageResponseDTO<CoupMeRespDTO>  coupPageBean = beanDto.getBeanPage();
        List<CoupMeRespDTO> coupBean = coupPageBean.getResult();
        if(coupPageBean != null && CollectionUtils.isNotEmpty(coupBean)){
        	for (CoupMeRespDTO coupMeRespDTO : coupBean) {
        		CoupDetailResp  bean= new CoupDetailResp();
        		bean.setId(coupMeRespDTO.getId());
        		bean.setCoupId(coupMeRespDTO.getCoupId());
        		bean.setCoupValue(coupMeRespDTO.getCoupValue());
        		bean.setCoupName(coupMeRespDTO.getCoupName());
        		bean.setCoupNo(coupMeRespDTO.getCoupNo());
        		bean.setActiveTime(coupMeRespDTO.getActiveTime());
        		bean.setInactiveTime(coupMeRespDTO.getInactiveTime());
        		bean.setStaffId(coupMeRespDTO.getStaffId());
        		bean.setStatus(coupMeRespDTO.getStatus());
        		bean.setShopId(coupMeRespDTO.getShopId());
        		bean.setShopName(coupMeRespDTO.getShopName());
        		bean.setConditions(coupMeRespDTO.getConditions());
        		bean.setIfUse(coupMeRespDTO.getIfUse());
        		bean.setCoupStatus(coupMeRespDTO.getCoupStatus());
        		respList.add(bean);
        	}
        }
        
        LogUtil.info(MODULE, "Coup001Action:"+JSON.toJSONString(respList));

        //03.返回结果
        this.response.setRespList(respList);//优惠券明细
        this.response.setCoupCount(beanDto.getCoupCount());//优惠券总数量
        // 1:可使用 2:已使用 0:已过期
        this.response.setCoupCount_0(beanDto.getCoupCount_0());
        this.response.setCoupCount_1(beanDto.getCoupCount_1());
        this.response.setCoupCount_2(beanDto.getCoupCount_2());
        
    }

}
