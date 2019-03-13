package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Ord004Req;
import com.zengshi.ecp.app.resp.Ord00203Resp;
import com.zengshi.ecp.app.resp.Ord00401Resp;
import com.zengshi.ecp.app.resp.Ord004Resp;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartChgResponse;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartItemRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.prom.dubbo.dto.CartPromBeanRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.CartPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.BusinessException;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.fastjson.JSON;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 修改店铺优惠<br>
 * Date:2016年3月5日上午10:28:22 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
@Service("ord004")
@Action(bizcode = "ord004", userCheck = true)
@Scope("prototype")
public class Ord004Action extends AppBaseAction<Ord004Req, Ord004Resp> {
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IOrdCartItemRSV ordCartItemRSV;

    @Resource
    private IOrdCartRSV ordCartRSV;
    
    private static final String MODULE = Ord004Action.class.getName();

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        appCartPromHandler();
    }



    private ROrdCartChgRequest genCartChg(){
        ROrdCartChgRequest chg = new ROrdCartChgRequest();

        chg.setrOrdCartItemRequest(this.request.getOrdCartItem());
        chg.getrOrdCartItemRequest().setStaffId(chg.getStaff().getId());
        chg.setrOrdCartChangeRequest(this.request.getOrdCartChg());
        chg.getrOrdCartChangeRequest().getStaff().setId(chg.getStaff().getId());

        return chg;
    }

    private void appCartPromHandler() throws BusinessException, SystemException, Exception{
        ROrdCartChgRequest chg = genCartChg();
        chg.setSource(CommonConstants.SOURCE.SOURCE_APP);

        ROrdCartChgResponse out = ordCartRSV.updateOrdCartProm(chg);
        setResponse(out);
    }

    private void setResponse(ROrdCartChgResponse out) throws BusinessException, SystemException, Exception{
        CartPromBeanRespDTO cartPromBeanRespDTO = promRSV.selectProm(out.getrOrdCartChangeRequest());
        LogUtil.error(MODULE, "促销出参:"+JSON.toJSONString(cartPromBeanRespDTO));
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
