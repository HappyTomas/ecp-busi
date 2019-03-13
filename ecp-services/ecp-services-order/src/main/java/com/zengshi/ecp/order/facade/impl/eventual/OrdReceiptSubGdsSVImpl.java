package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.goods.dubbo.dto.AffirmStockDTO;
import com.zengshi.ecp.goods.dubbo.dto.AffirmStockMainDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoExternalRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.order.dubbo.dto.ROrdReceiptRequest;
import com.zengshi.ecp.order.dubbo.dto.SReceiptInfo;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdReceiptSubGdsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class OrdReceiptSubGdsSVImpl implements IOrdReceiptSubGdsSV {

    private static final String MODULE = OrdReceiptSubGdsSVImpl.class.getName();
    
    @Resource
    private IGdsStockRSV gdsStockRSV;
    
    @Resource
    private IOrdSubSV ordSubSV;
    
    @Resource
    private IGdsInfoExternalRSV gdsInfoExternalRSV;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        final ROrdReceiptRequest rorr= JSON.parseObject(message.toString(), ROrdReceiptRequest.class);
        LogUtil.info(MODULE,"============="+rorr.toString());
        try {
//            this.gdsStockRSV.updateDeliverySkuStcok(dsmd);
            CustInfoResDTO custInfoResDTO = this.custManageRSV.findCustInfoById(rorr.getStaffId());
            if(custInfoResDTO != null && custInfoResDTO.getStaffCode() != null){
                rorr.setCompanyId(custInfoResDTO.getCompanyId());
            }
            LogUtil.error(MODULE, "用户信息"+JSON.toJSONString(custInfoResDTO));
            if(rorr.getCompanyId() ==null || rorr.getCompanyId() < 1){
                LogUtil.info(MODULE,"企业ID为空");
                return ;
            }
            List<SReceiptInfo> sris = ordSubSV.queryReceiptInfo(rorr);
            
            AffirmStockMainDTO asm = new AffirmStockMainDTO();
            asm.setStaffId(rorr.getStaff().getId());
            asm.setShopId(rorr.getShopId());
            asm.setCompanyId(rorr.getCompanyId());
            List<AffirmStockDTO> asds = new ArrayList<AffirmStockDTO>();
            for(SReceiptInfo sri :sris){
                AffirmStockDTO asd = new AffirmStockDTO();
                asd.setAffirmCount(sri.getOrderAmount());
                asd.setOrderId(sri.getOrderId());
                asd.setSubOrderId(sri.getOrderSubId());
                asd.setSkuId(sri.getSkuId());
                asd.setStockId(sri.getStockId());
                asd.setStaffId(rorr.getStaff().getId());
                asd.setCatgCode(sri.getCategoryCode());
                asd.setGdsId(sri.getGdsId());
                asd.setTypeId(sri.getGdsType());
                asds.add(asd);
            }
            asm.setAffirmStockDTOs(asds);
            LogUtil.error(MODULE, "调用商品域入参"+JSON.toJSONString(asm));
            gdsInfoExternalRSV.batchConfirmReceipt(asm);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "===业务异常===",be);
            status.setRollbackOnly();
            throw be;
        } catch (Exception e) {
            LogUtil.error(MODULE, "===系统异常===",e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_GOODS_SERVER_340000);
        }
    }

}

