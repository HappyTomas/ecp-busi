package com.zengshi.ecp.order.dubbo.impl.pay;

import com.zengshi.ecp.order.dao.model.PayWay;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayItem;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest;
import com.zengshi.ecp.order.dubbo.dto.pay.PayWayResponse;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants.PayInputMsgCode;
import com.zengshi.ecp.order.service.busi.interfaces.pay.IPayWaySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class PayWayRSVImpl implements IPayWayRSV {
    
    private static final String MODULE = PayWayRSVImpl.class.getName();

    @Autowired(required=false)
    private IPayWaySV payWaySV;
    
    
    
    /**
     * 
     * TODO 获取店铺可用支付通道dubbo服务. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.IPayWayRSV#getPayWay(com.zengshi.ecp.order.dubbo.dto.PayWayRequest)
     */
    @Override
    public List<PayWayResponse> getPayWay(PayWayRequest payWay) throws BusinessException {
        if(payWay==null){
            LogUtil.info(MODULE, "入参不能为空");
            throw new BusinessException(MsgConstants.PayInputMsgCode.PAY_INPUT_300000); 
        }
        List<PayWayResponse> list = new ArrayList<PayWayResponse>();
        List<PayWay> payways = payWaySV.getPayWayByShopId(payWay);
        for(PayWay p:payways){
            
            boolean flag = true;
            
            for(PayWayResponse payWayResponse:list){
                if(!StringUtil.isBlank(payWayResponse.getPayWayType())
                        &&payWayResponse.getPayWayType().equals(p.getPayWayType())){
                    flag=false;
                    PayWayItem item = new PayWayItem();
                    ObjectCopyUtil.copyObjValue(p, item, null, false);
                    String imageURL = ImageUtil.getImageUrl(item.getPayImage());
                    item.setPayImage(imageURL);
                    payWayResponse.getPayWayList().add(item);
                }
            }
            
            if(flag){
                PayWayResponse response= new PayWayResponse();
                response.setPayWayType(p.getPayWayType());
                PayWayItem item = new PayWayItem();
                ObjectCopyUtil.copyObjValue(p, item, null, false);
                String imageURL = ImageUtil.getImageUrl(item.getPayImage());
                item.setPayImage(imageURL);
                List<PayWayItem> payWayList = new ArrayList<PayWayItem>();
                payWayList.add(item);
                response.setPayWayList(payWayList);
                list.add(response);
            }
        }
        return list;
    }

    /**
     * 
     * TODO 分页查询支付方式dubbo服务. 
     * @author lwy
     * @see com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV#queryPayWayPage(com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest)
     */
    @Override
    public PageResponseDTO<PayWayResponse> queryPayWayPage(PayWayRequest payWayRequest)
            throws BusinessException {
        PageResponseDTO<PayWayResponse> pwdo = null;
        try{
            pwdo = this.payWaySV.queryPayWayPage(payWayRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
        return pwdo;
    }

    /**
     * 
     * TODO 根据ID查询支付方式记录（可选）. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV#queryOnePayWay(com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest)
     */
    @Override
    public PayWayResponse queryOnePayWay(PayWayRequest payWayReq) throws BusinessException {
        if(payWayReq==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }
        if(StringUtil.isBlank(payWayReq.getId())){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300010);
        }
        PayWayResponse paywayResp = new PayWayResponse();
        try{
            PayWay payway = this.payWaySV.getPayWayById(payWayReq.getId());           
            ObjectCopyUtil.copyObjValue(payway, paywayResp, "", false);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
        return paywayResp;
    }

    /**
     * 
     * TODO 新增支付方式（可选）. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV#savePayWay(com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest)
     */
    @Override
    public void addPayWay(PayWayRequest payWayRequest) throws BusinessException {
        if(payWayRequest==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }       
        try{
            this.payWaySV.addPayWay(payWayRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
    }
    
    /**
     * 
     * TODO 修改支付方式（可选）. 
     * @see com.zengshi.ecp.order.dubbo.interfaces.pay.IPayWayRSV#savePayWay(com.zengshi.ecp.order.dubbo.dto.pay.PayWayRequest)
     */
    @Override
    public void updatePayWay(PayWayRequest payWayRequest) throws BusinessException {
        if(payWayRequest==null){
            throw new BusinessException(PayInputMsgCode.PAY_INPUT_300000);
        }       
        try{
            this.payWaySV.editPayWay(payWayRequest);
        }catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===", be);
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===", e);
            throw new BusinessException(MsgConstants.ServiceMsgCode.ORD_SERVER_310004);
        }
    }
    
}

