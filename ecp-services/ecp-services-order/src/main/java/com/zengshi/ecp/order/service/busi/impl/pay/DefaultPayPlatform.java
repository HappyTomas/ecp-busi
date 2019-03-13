package com.zengshi.ecp.order.service.busi.impl.pay;

import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dubbo.dto.pay.BindRequestData;
import com.zengshi.ecp.order.dubbo.dto.pay.PayBindDTO;
import com.zengshi.ecp.order.dubbo.dto.pay.PayBindReqDTO;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayBindReqSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayBindSV;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayPlatform;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order-server <br>
 * Description: 支付平台默认实现类<br>
 * Date:2015年10月10日下午3:21:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author weijq
 * @version
 * @since JDK 1.6
 */
public class DefaultPayPlatform extends DefaultPayWay implements IPayPlatform {

    @Resource
    private IPayBindReqSV payBindReqSV;

    @Resource
    private IPayBindSV payBindSV;

    public static final String module = DefaultPayPlatform.class.getName();

    @Override
    public BindRequestData bindBankCard(Long staffId, Map<String, String> extendProps)
            throws Exception {
        return null;
    }

    @Override
    public void shiftBindBankCard(Long OldStaffId, Long newStaffId) throws Exception {
    }

    @Override
    public Map<String, String> bindBankCardCallback(Map<String, String> responseMap)
            throws Exception {
        return null;
    }

    /**
     * 
     * addPayBindRequest:保存绑卡请求记录. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author weijq
     * @param request
     * @since JDK 1.6
     */
    public void addPayBindRequest(PayBindReqDTO request) {
        payBindReqSV.addPayBindRequest(request);
    }

    /**
     * 
     * dealPayBind:处理绑卡逻辑. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author weijq
     * @param payBind
     * @since JDK 1.6
     */
    public void dealPayBind(PayBindDTO payBind) {
        payBindSV.dealPayBind(payBind);
    }

    //
    // /**
    // * 公共的绑卡转移后续处理方法（此方法为一个转发）
    // * @param oldChnlId 老渠道编码
    // * @param newChnlId 新渠道编码
    // * @param provinceCode 省份编码
    // * @return
    // * @throws Exception
    // */
    // public boolean handleShiftSucc(String payWay,String oldChnlId, String newChnlId, String
    // provinceCode) throws Exception{
    // if(StringUtil.isBlank(payWay)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "支付通道不能为空");
    // }
    // if(StringUtil.isBlank(oldChnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "绑卡转移的老渠道编码不能为空");
    // }
    // if(StringUtil.isBlank(newChnlId)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "绑卡转移的新渠道编码不能为空");
    // }
    // if(StringUtil.isBlank(provinceCode)){
    // throw new BusinessException(Special.PARAM_IS_NULL, "省份编码不能为空");
    // }
    // try {
    // ITPayBindSV payBindSV =ServiceUtil.getService(ITPayBindSV.class, provinceCode);
    // payBindSV.dealShiftBind(payWay,oldChnlId,newChnlId,provinceCode);
    // } catch (Exception e) {
    // Debug.logError(e,"保存银行卡绑定明细失败", module);
    // throw new BusinessException(Pay.PAY_SAVE_BIND_BANK_CARD_INFO_ERROR, "保存银行卡绑定明细失败");
    // }
    // return false;
    // }
    //
    // public IBOTShopMainInfoValue getShopInfoBean(String shopId,String provinceCode) throws
    // Exception{
    // ITShopMainInfoDAO shopInfoDao = ServiceUtil.getService(ITShopMainInfoDAO.class,
    // provinceCode);
    // Criteria sql = new Criteria();
    // sql.addEqual(IBOTShopMainInfoValue.S_ShopId, shopId);
    // IBOTShopMainInfoValue shopInfoBean = null;
    // IBOTShopMainInfoValue[] shopInfoBeans = shopInfoDao.getBeans(sql);
    // if(shopInfoBeans!=null && shopInfoBeans.length > 0){
    // shopInfoBean = shopInfoBeans[0];
    // } else {
    // throw new BusinessException(Special.NO_RESULT,"商铺在系统中不存在，商铺编码:"+shopId);
    // }
    // return shopInfoBean;
    // }
    // public String getSignNoSequence(String provinceCode){
    // String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
    //
    // return provinceCode+date+SeqUtil.getNewId("SEQ_PAY_BIND_REQ_SIGN_NO",8);
    // }
    //
    @Override
    public Map<String, String> delivery(String orderId, Map<String, String> extendProps)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, String> shipments(String orderId, Map<String, String> extendProps)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}