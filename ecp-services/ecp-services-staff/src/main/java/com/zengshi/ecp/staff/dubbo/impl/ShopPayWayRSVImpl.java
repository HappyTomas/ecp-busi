package com.zengshi.ecp.staff.dubbo.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayShopCfgResDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.PayWayResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopPayWayRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoAideSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopPayWayCfgSV;
import com.zengshi.ecp.staff.service.common.pay.interfaces.IShopPayWaySV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日下午5:07:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class ShopPayWayRSVImpl implements IShopPayWayRSV {
    
    private static final String MODULE = ShopPayWayRSVImpl.class.getName();
    
    @Resource
    private IShopPayWayCfgSV shopPayWayCfgSV;
    
    @Resource
    private IShopPayWaySV shopPayWaySV;

    @Override
    public List<PayWayResDTO> getShopPayWay(PayWayReqDTO payWayReqDTO) throws BusinessException {
        return shopPayWaySV.getShopPayWay(payWayReqDTO);
    }

    @Override
    public PageResponseDTO<PayShopCfgResDTO> queryPayShopCfgList(PayShopCfgReqDTO payShopCfgReqDTO)
            throws BusinessException {
        return shopPayWayCfgSV.queryPayShopCfgList(payShopCfgReqDTO);
    }

    @Override
    public int addPayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException {
        return shopPayWayCfgSV.addPayShopCfg(payShopCfgReqDTO);
    }

    @Override
    public int updatePayShopCfg(PayShopCfgReqDTO payShopCfgReqDTO) throws BusinessException {
        return shopPayWayCfgSV.updatePayShopCfg(payShopCfgReqDTO);
    }
    
   
}

