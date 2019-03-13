package com.zengshi.ecp.busi.staff.controller;


import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.busi.staff.vo.AcctInfoResVO;
import com.zengshi.ecp.busi.staff.vo.BuyerWalletResult;
import com.zengshi.ecp.busi.staff.vo.BuyerWalletVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-busi-ecp-web-mobile <br>
 * Description: 我的钱包<br>
 * Date:2016年8月2日上午9:23:49  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/wallet")
@Controller
public class BuyerWalletController extends EcpBaseController{
    private static final String URL = "/staff/wallet";
    private static String MODULE = BuyerWalletController.class.getName();
    @Resource
    private IAcctManageRSV acctManageRSV;   //资金帐户RSV
    @Resource
    private IShopInfoRSV iShopInfoRSV;
    
    /**
     * 
     * index:(我的钱包首页). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping()
    public String index(Model model, EcpBasePageReqVO vo) throws Exception{
        return URL + "/wallet";
    }

    /**
     * 
     * queryWallet:(我的钱包列表信息页查询). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @param shopId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querywallet")
    @ResponseBody
    public BuyerWalletResult queryWallet(Model model, BuyerWalletVO vo) throws Exception{
        BuyerWalletResult result = new BuyerWalletResult();
        this.getWalletBalanceList(model, vo, vo.getShopId(),result);
        return result;
    }
    
    
    /**
     * 
     * getWalletBalanceList:(获取并重装用户资金帐户详情结果集). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param vo
     * @param shopId
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @SuppressWarnings("rawtypes")
    private void getWalletBalanceList(Model model, BuyerWalletVO vo, Long shopId
            ,BuyerWalletResult result) throws Exception{
        AcctInfoReqDTO dto = vo.toBaseInfo(AcctInfoReqDTO.class);
        if(shopId != null && shopId != 0l){
            dto.setShopId(shopId);
        }
        dto.setPageSize(6);
        dto.setPageNo(vo.getPage());
        Map<String, String> shopMap = new HashMap<String, String>();
        PageResponseDTO<AcctInfoResDTO> res = acctManageRSV.queryAcctInfoByStaff(dto);
        List<AcctInfoResVO> resultList = new ArrayList<AcctInfoResVO>();
        if(CollectionUtils.isNotEmpty(res.getResult())){
            
            for (AcctInfoResDTO ie : res.getResult()) {
                AcctInfoResVO rslVO = new AcctInfoResVO();
                ObjectCopyUtil.copyObjValue(ie, rslVO, null, false);
                rslVO.setStatus(StaffConstants.Acct.ACCT_STATUS_ENABLE.equals(ie.getStatus())?"生效":"失效");//TODO 文字
                rslVO.setShopName(ie.getShopName());
                try {   
                    if(ie.getCreateTime() != null){
                        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
                        rslVO.setCreateTimeStr(sdf.format(ie.getCreateTime()));
                    }
                } catch (Exception e){
                    LogUtil.error(MODULE, "时间转换失败！", e);
                }
                try {
                    ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(ie.getShopId());
                    if(shopInfo != null){
                        rslVO.setLogoPath(shopInfo.getLogoPathURL());
                    }
                    rslVO.setBalanceYun(String.format("%.2f", BigDecimal.valueOf(Long.valueOf(rslVO.getBalance())).divide(new BigDecimal(100.00))));
                } catch (BusinessException e) {
                    LogUtil.error(MODULE, "获取店铺信息失败！", e);
                } catch (Exception e) {
                    LogUtil.error(MODULE, "获取店铺信息失败！", e);
                }
                resultList.add(rslVO);
                if(!shopMap.containsKey(ie.getShopId().toString()))
                {
                    shopMap.put(ie.getShopId().toString(), ie.getShopName());
                }
            }
        }
        model.addAttribute("shopMap", shopMap);
        result.setDatas(resultList);
        result.setTotal(res.getPageCount());
    }
}

