package com.zengshi.ecp.prom.dubbo.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.order.dto.ROrdCartCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartItemCommRequest;
import com.zengshi.ecp.general.order.dto.ROrdCartsChkResponse;
import com.zengshi.ecp.general.order.dto.ROrdCartsCommRequest;
import com.zengshi.ecp.general.order.interfaces.IOrderChkRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.sku.interfaces.IPromSkuCheckSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-9-28 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * 
 * IOrdCartsChkRSV 订单提供公共接口
 * 
 * @author huangjx
 */
public class PromOrdChkRSVImpl implements IOrderChkRSV {

    private static final String MODULE = PromOrdChkRSVImpl.class.getName();

    @Resource(name = "defaultSkuCheckSV")
    private IPromSkuCheckSV defaultSkuCheckSV;
    
    @Resource
    private IPromConstraintSV promConstraintSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    /**
     * TODO 下单
     * @see com.zengshi.ecp.general.order.interfaces.IOrdCartsChkRSV#checkOrdCart(com.zengshi.ecp.general.order.dto.ROrdCartsChkRequest)
     * @param arg0
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    @Override
    public ROrdCartsChkResponse checkOrder(ROrdCartsCommRequest info) throws BusinessException{
       return  this.check(info);
    }
    /**
     * 下单check
     * @param info
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    private ROrdCartsChkResponse check(ROrdCartsCommRequest info) throws BusinessException{
        
        //1、参数验证
        //2、必需验证规则  促销规则  比如区域 客户等级  抛出错误
        //3、计算规则验证 仅仅影响是否满足和金额
        if(info==null){
            throw new BusinessException("prom.400086");
        }
        if(info.getOrdCartsCommList()==null){
            throw new BusinessException("prom.400086");
        }
      
        ROrdCartsChkResponse resp=new ROrdCartsChkResponse();
        //默认通过
        resp.setStatus(Boolean.TRUE);
        
        for(ROrdCartCommRequest cart:info.getOrdCartsCommList()){
            //单品促销id验证
            if(!CollectionUtils.isEmpty(cart.getOrdCartItemCommList())){
                for(ROrdCartItemCommRequest item:cart.getOrdCartItemCommList()){
                    //单品促销ID为空 不验证
                    if(StringUtil.isEmpty(item.getPromId())){
                        continue;
                    }
                    //单品有促销ID
                    if(item.getPromId()>0){
                        try{
                             checkItem(cart,item);
                        }catch(BusinessException ex){
                            LogUtil.error(MODULE, ex.toString());
                            resp.setStatus(Boolean.FALSE);
                            resp.setMsg(ex.getErrorMessage());
                            break;
                        }
                    }
                }
            }
            //存在错误 后续不执行哦
            if(!resp.isStatus()){
                break;
            }
            //订单促销id验证
            //为空 不验证
            if(StringUtil.isEmpty(cart.getPromId())){
                continue;
            }
            //订单有促销ID
            if(cart.getPromId()>0){
                //订单级别验证
                try{
                     checkOrd(cart);
                }catch(BusinessException ex){
                    LogUtil.error(MODULE, ex.toString());
                    resp.setStatus(Boolean.FALSE);
                    resp.setMsg(ex.getErrorMessage());
                    break;
                }
            }
        }
        return resp;
        
    }
    /**
     * 订单促销验证
     * @param cart
     * @throws BusinessException
     * @author huangjx
     */
    private void checkOrd(ROrdCartCommRequest cart) throws BusinessException{
        //有促销ID
        if(cart.getPromId()>0){
            PromInfoDTO promInfoDTO= promInfoSV.queryPromInfoDTOById(cart.getPromId());
            
            //失效不通过
            if(!PromConstants.PromInfo.STATUS_10.equals(promInfoDTO.getStatus())){
                String[] keys=new String[1];
                keys[0]=promInfoDTO.getNameShort();
                throw new BusinessException("prom.400164",keys);
            }
          //失效时间小于当前系统时间
            Date sysdate=DateUtil.getSysDate();
            if(sysdate.compareTo(promInfoDTO.getEndTime())>0){
                String[] keys=new String[1];
                keys[0]=promInfoDTO.getNameShort();
                throw new BusinessException("prom.400178",keys);
            }
          //是否取到关联关系
            for(ROrdCartItemCommRequest item:cart.getOrdCartItemCommList()){
                
                if (cart.getPromId().equals(item.getOrdPromId())){
                    //获得有效的关系
                    PromRuleCheckDTO promRuleCheckDTO =new PromRuleCheckDTO();
                    promRuleCheckDTO.setShopId(cart.getShopId());
                    promRuleCheckDTO.setShopName(cart.getShopName());
                    promRuleCheckDTO.setStaffId(cart.getStaffId().toString());
                    promRuleCheckDTO.setSiteId(cart.getCurrentSiteId());
                    promRuleCheckDTO.setIfThrows(PromConstants.PromSys.IF_THROWS);
                    
                    promRuleCheckDTO.setGdsId(item.getGdsId());
                    promRuleCheckDTO.setGdsName(item.getGdsName());
                    promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
                    promRuleCheckDTO.setPromId(cart.getPromId());
                    promRuleCheckDTO.setSkuId(item.getSkuId());
                    //当前促销id对应的单品购买数量之和；
                   // promRuleCheckDTO.setBuyCnt(this.calAmount(cart).toString());
                    promRuleCheckDTO.setBuyCnt(item.getOrderAmount().toString());
                    promRuleCheckDTO.setAreaValue(cart.getAreaValue());
                    promRuleCheckDTO.setChannelValue(cart.getChannelValue());
                    promRuleCheckDTO.setCustGroupValue(cart.getCustGroupValue());
                    promRuleCheckDTO.setCustLevelValue(cart.getCustLevelValue());
                    
                    //验证是否能参与促销
                    if (defaultSkuCheckSV.gdsCheck(promInfoDTO,promRuleCheckDTO)
                            && promConstraintSV.check(cart.getPromId(), promRuleCheckDTO)) {
                    } 
                }
            }
        }
    }
    /**
     * 单品促销验证
     * @param cart
     * @throws BusinessException
     * @author huangjx
     */
    private void checkItem(ROrdCartCommRequest cart,ROrdCartItemCommRequest cartItem) throws BusinessException{
        //有促销ID
        if(cartItem.getPromId()>0){
            PromInfoDTO promInfoDTO= promInfoSV.queryPromInfoDTOById(cartItem.getPromId());
            
            //失效不通过
            if(!PromConstants.PromInfo.STATUS_10.equals(promInfoDTO.getStatus())){
                String[] keys=new String[2];
                keys[0]=cartItem.getGdsName();
                keys[1]=promInfoDTO.getNameShort();
                throw new BusinessException("prom.400163",keys);
            }
          //失效时间小于当前系统时间
            Date sysdate=DateUtil.getSysDate();
            if(sysdate.compareTo(promInfoDTO.getEndTime())>0){
                String[] keys=new String[2];
                keys[0]=cartItem.getGdsName();
                keys[1]=promInfoDTO.getNameShort();
                throw new BusinessException("prom.400177",keys);
            }
            PromRuleCheckDTO promRuleCheckDTO =new PromRuleCheckDTO();
            promRuleCheckDTO.setShopId(cartItem.getShopId());
            promRuleCheckDTO.setShopName(cartItem.getShopName());
             promRuleCheckDTO.setGdsId(cartItem.getGdsId());
            promRuleCheckDTO.setGdsName(cartItem.getGdsName());
            promRuleCheckDTO.setSkuId(cartItem.getSkuId());
            promRuleCheckDTO.setCalDate(DateUtil.getDate());
            promRuleCheckDTO.setBuyCnt(cartItem.getOrderAmount().toString());
            promRuleCheckDTO.setStaffId(cartItem.getStaffId().toString());
            promRuleCheckDTO.setSiteId(cartItem.getCurrentSiteId());
            promRuleCheckDTO.setIfThrows(PromConstants.PromSys.IF_THROWS);
            
            promRuleCheckDTO.setAreaValue(cart.getAreaValue());
            promRuleCheckDTO.setChannelValue(cart.getChannelValue());
            promRuleCheckDTO.setCustGroupValue(cart.getCustGroupValue());
            promRuleCheckDTO.setCustLevelValue(cart.getCustLevelValue());
            
                //验证是否能参与促销
                if (defaultSkuCheckSV.gdsCheck(promInfoDTO,promRuleCheckDTO)
                        && promConstraintSV.check(cartItem.getPromId(), promRuleCheckDTO)) {
                } 
        }
    }
    /**
     * 计算当前促销id 购买数量
     * @param cart
     * @return
     * @author huangjx
     */
    private Long calAmount(ROrdCartCommRequest cart){
        Long amount=new Long(0);
        for(ROrdCartItemCommRequest item:cart.getOrdCartItemCommList()){
               if(cart.getPromId()>0){
                /*   if(item.getOrdPromId()>0 && item.getOrdPromId().equals(cart.getPromId())){
                       amount=amount+item.getOrderAmount();
                   }*/
                   amount=amount+item.getOrderAmount();
               }
        }
        return amount;
    }
}
