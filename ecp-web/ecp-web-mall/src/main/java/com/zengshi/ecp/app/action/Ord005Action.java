package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord005Req;
import com.zengshi.ecp.app.resp.Ord00203Resp;
import com.zengshi.ecp.app.resp.Ord00401Resp;
import com.zengshi.ecp.app.resp.Ord005Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 修改明细优惠<br>
 * Date:2016年3月5日上午10:28:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version  
 * @since JDK 1.6 
 */  
@Service("ord005")
@Action(bizcode = "ord005", userCheck = true)
@Scope("prototype")
public class Ord005Action extends AppBaseAction<Ord005Req, Ord005Resp> {
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IOrdCartItemRSV ordCartItemRSV;
    
    private static final String MODULE = Ord005Action.class.getName();
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        appCartItemPromHandler();
    }

    private void appCartItemPromHandler() throws BusinessException, SystemException, Exception{

        ROrdCartChgRequest chg = genCartChg();
        chg.setSource(CommonConstants.SOURCE.SOURCE_APP);
        ROrdCartChgResponse out = ordCartItemRSV.updateOrdCartItemProm(chg);
        setResponse(out);
    }

    private ROrdCartChgRequest genCartChg(){
        ROrdCartChgRequest chg = new ROrdCartChgRequest();

        chg.setrOrdCartItemRequest(this.request.getOrdCartItem());
        chg.getrOrdCartItemRequest().getStaff().setId(chg.getStaff().getId());
        chg.setrOrdCartChangeRequest(this.request.getOrdCartChg());
        chg.getrOrdCartChangeRequest().getStaff().setId(chg.getStaff().getId());
        return chg;
    }

    private void setResponse(ROrdCartChgResponse out) throws BusinessException, SystemException, Exception{
        if(out.getrOrdCartChangeRequest() != null && out.getrOrdCartChangeRequest().getrOrdCartCommRequest() != null){
            out.getrOrdCartChangeRequest().getrOrdCartCommRequest().setSource(CommonConstants.SOURCE.SOURCE_APP);
        }
        CartPromBeanRespDTO cartPromBeanRespDTO = promRSV.selectProm(out.getrOrdCartChangeRequest());
        Ord00401Resp appCartPromResp = getAppCartProm(cartPromBeanRespDTO.getCartPromDTO());
        Ord00401Resp appCartItemPromResp = getAppCartProm(cartPromBeanRespDTO.getCartPromItemDTO());
        appCartItemPromResp.setOrdPromId(cartPromBeanRespDTO.getCartPromItemDTO() != null ? cartPromBeanRespDTO.getCartPromItemDTO().getOrdPromId() : null);
        this.response.setCartPromDTO(appCartPromResp);
        this.response.setCartPromItemDTO(appCartItemPromResp);
    }

    private Ord00401Resp getAppCartProm(CartPromDTO cartPromDTO){

        Ord00401Resp appCartPromResp = new Ord00401Resp();
        if(cartPromDTO!=null) {
            ObjectCopyUtil.copyObjValue(cartPromDTO, appCartPromResp, "", false);
            List<Ord00203Resp> appPromInfoList = new ArrayList<Ord00203Resp>();
            if(CollectionUtils.isNotEmpty(cartPromDTO.getPromInfoDTOList())){
                for (PromInfoDTO promInfoDTO : cartPromDTO.getPromInfoDTOList()) {
                    Ord00203Resp appPromInfo = new Ord00203Resp();
                    ObjectCopyUtil.copyObjValue(promInfoDTO, appPromInfo, "", false);
                    appPromInfoList.add(appPromInfo);
                }
            }

            appCartPromResp.setPromInfoDTOList(appPromInfoList);
            if (appCartPromResp.isIfFulfillProm()) {
                appCartPromResp.setFulfilMsg(cartPromDTO.getPromTypeMsgResponseDTO() != null ?
                        cartPromDTO.getPromTypeMsgResponseDTO().getFulfilMsg() : "");
            } else {
                appCartPromResp.setNoFulfilMsg(cartPromDTO.getPromTypeMsgResponseDTO() != null ?
                        cartPromDTO.getPromTypeMsgResponseDTO().getNoFulfilMsg() : "");
            }
        }
        return appCartPromResp;
    }

}
