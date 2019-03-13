package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord014Req;
import com.zengshi.ecp.app.resp.Ord00203Resp;
import com.zengshi.ecp.app.resp.Ord00401Resp;
import com.zengshi.ecp.app.resp.Ord014Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
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
 * Description: 勾选、不选择商品<br>
 * Date:2016年3月5日上午10:29:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord014")
@Action(bizcode = "ord014", userCheck = true)
@Scope("prototype")
public class Ord014Action extends AppBaseAction<Ord014Req, Ord014Resp> {

    private static final String MODULE = Ord014Action.class.getName();
    @Resource
    private IOrdCartRSV ordCartRSV;

    @Resource
    private IPromRSV promRSV;
    
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        ROrdCartChgRequest chg = new ROrdCartChgRequest();
        chg.setrOrdCartItemRequest(this.request.getOrdCartItem());
        chg.setrOrdCartChangeRequest(this.request.getOrdCartChg());
        chg.getrOrdCartChangeRequest().getStaff().setId(chg.getStaff().getId());
        ROrdCartChgResponse out = ordCartRSV.assembleParamForProm(chg);
        setResponse(out);
    }

    private void setResponse(ROrdCartChgResponse out) throws BusinessException, SystemException, Exception{
        if(out==null) {
            this.response.setCartPromItemDTO(null);
            this.response.setCartPromDTO(null);
        }else{
            if(out.getrOrdCartChangeRequest() != null && out.getrOrdCartChangeRequest().getrOrdCartCommRequest() != null){
                out.getrOrdCartChangeRequest().getrOrdCartCommRequest().setSource(CommonConstants.SOURCE.SOURCE_APP);
            }
            
            CartPromBeanRespDTO cartPromBeanRespDTO = promRSV.selectProm(out.getrOrdCartChangeRequest());
            Ord00401Resp appCartPromResp = getAppCartProm(cartPromBeanRespDTO.getCartPromDTO());
            Ord00401Resp appCartItemPromResp = getAppCartProm(cartPromBeanRespDTO.getCartPromItemDTO());
            appCartItemPromResp.setOrdPromId(cartPromBeanRespDTO.getCartPromItemDTO() != null
                    ? cartPromBeanRespDTO.getCartPromItemDTO().getOrdPromId() : null);
            this.response.setCartPromDTO(appCartPromResp);
            this.response.setCartPromItemDTO(appCartItemPromResp);
        }
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
