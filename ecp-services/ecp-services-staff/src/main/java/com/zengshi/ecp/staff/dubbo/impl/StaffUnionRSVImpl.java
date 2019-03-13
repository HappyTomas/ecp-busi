package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctMainResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderAcctSubResDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackMainReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.OrderBackSubReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreFrozenBackReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.SellerResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.union.interfaces.IStaffUnionSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层：用户域对外统一服务接口实现类<br>
 * Date:2015-10-9下午5:43:40  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class StaffUnionRSVImpl implements IStaffUnionRSV{

    @Resource
    private IStaffUnionSV staffUnionSV;
    
    @Resource
    private IShopMgrSV iShopMgrSV;
    
    @Resource
    private ICustInfoRSV custInfoRSV;
    
    @Override
    public boolean saveStaffRelInfoForOrder(ListReqDTO<ScoreExchangeReqDTO> scoreReq,
            ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        return staffUnionSV.saveStaffRelInfoForOrder(scoreReq, listReqDto);
    }

    @Override
    public boolean saveStaffRelInfoForOrderRollBack(ListReqDTO<ScoreExchangeReqDTO> scoreReq, ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        return staffUnionSV.saveStaffRelInfoForOrderRollBack(scoreReq, listReqDto);
    }

    public boolean saveStaffRelInfoForPay(PayQuartzInfoRequest req) throws BusinessException {
        
        if(null==req.getStaffId()||req.getStaffId()==0){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"staffId"});
        }
        if(null==req.getPayQuartzInfoId()||req.getStaffId()==0){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"payQuartzInfoId"});
        }
        if(StringUtil.isBlank(req.getSourceType())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"sourceType"});
        }
        
        return staffUnionSV.saveStaffRelInfoForPay(req);
    }

    @Override
    public CustInfoResDTO findCustOrAdminByStaffId(Long staffId) throws BusinessException {
        if(staffId == null || staffId == 0L){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"staffId"});
        }
        return staffUnionSV.findCustOrAdminByStaffId(staffId);
    }

    @Override
    public CustInfoResDTO findCustInfo(CustInfoReqDTO req) throws BusinessException {
        return staffUnionSV.findCustInfo(req);
    }

    @Override
    public boolean saveScoreAcctForOrderRefund(OrderBackSubReqDTO reqDto) throws BusinessException {
        return staffUnionSV.saveScoreAcctForOrderRefund(reqDto);
    }

    @Override
    public ScoreInfoResDTO findScoreInfoByStaffId(Long staffId) throws BusinessException {
        return staffUnionSV.findScoreInfoByStaffId(staffId);
    }

    @Override
    public Long saveScoreFrozenForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req)
            throws BusinessException {
        return staffUnionSV.saveScoreFrozenForOrderBack(req);
    }


    @Override
    public boolean saveScoreAcctForOrderBack(OrderBackMainReqDTO<OrderBackSubReqDTO> req,OrderBackSubReqDTO orderReq)
            throws BusinessException {
        return staffUnionSV.saveScoreAcctForOrderBack(req,orderReq);
    }



    @Override
    public long selTotalScoreByOrderId(String orderId) throws BusinessException {
        return staffUnionSV.selTotalScoreByOrderId(orderId);
    }

    @Override
    public long selTotalScoreByBackSubOrder(OrderBackMainReqDTO<OrderBackSubReqDTO> req)
            throws BusinessException {
        return staffUnionSV.selTotalScoreByBackSubOrder(req);
    }

    @Override
    public OrderAcctMainResDTO<OrderAcctSubResDTO> selAcctByBackOrder(
            OrderBackMainReqDTO<OrderBackSubReqDTO> req) throws BusinessException {
        return staffUnionSV.selAcctByBackOrder(req);
    }

	@Override
	public boolean saveScoreFrozenBack(ScoreFrozenBackReqDTO req)
			throws BusinessException {
		if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"员工id"});
		}
		//如果没有积分传入，直接返回true
		if (req.getBackScore() == null || req.getBackScore() == 0L) {
			return true;
		}
		return staffUnionSV.saveScoreFrozenBack(req);
	}

    @Override
    public SellerResDTO getSellerInfo(CustInfoReqDTO custInfoReqDTO) throws BusinessException {
        SellerResDTO sellerResDTO = new SellerResDTO();
        CustInfoResDTO custInfoResDTO = custInfoRSV.getCustInfoById(custInfoReqDTO);
        if(StaffConstants.custInfo.SHOP_FLAG_NO.equals(custInfoResDTO.getCustShopFlag()) || StringUtil.isBlank(custInfoResDTO.getCustShopFlag())){
            sellerResDTO.setShopFlag(StaffConstants.custInfo.SHOP_FLAG_NO);
            return sellerResDTO;
        }
        sellerResDTO.setCompanyId(custInfoResDTO.getCompanyId());
        sellerResDTO.setCustType(custInfoResDTO.getCustType());
        if(StaffConstants.custInfo.CUST_TYPE_ADMIN.equals(custInfoResDTO.getCustType())){
        	sellerResDTO.setShoplist(iShopMgrSV.listShopByCompanyID(custInfoResDTO.getCompanyId()));
        }else{
        	List<ShopInfoResDTO> list = new ArrayList<ShopInfoResDTO>();
        	ShopInfoResDTO shopInfoResDTO  = new ShopInfoResDTO();
        	ShopInfo shopinfo = iShopMgrSV.findShopByShopID(custInfoResDTO.getShopId());
        	ObjectCopyUtil.copyObjValue(shopinfo, shopInfoResDTO, null, false);
        	list.add(shopInfoResDTO);
        	sellerResDTO.setShoplist(list);
        }
        
        sellerResDTO.setShopFlag(custInfoResDTO.getCustShopFlag());
        return sellerResDTO;
    }

}

