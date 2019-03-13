package com.zengshi.ecp.order.dubbo.impl.orderSubmit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProInitRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IOrderSubmitProRSV;

import javax.annotation.Resource;

public class OrderSubmitProInitRSVImpl implements IOrderSubmitProInitRSV {
    
    @Resource
    private List<IOrderSubmitProRSV> orderSubmitProRSVs;   //进行预占的Bean

    public List<IOrderSubmitProRSV> getOrderSubmitProRSVs() {
        return orderSubmitProRSVs;
    }

    public void setOrderSubmitProRSVs(List<IOrderSubmitProRSV> orderSubmitProRSVs) {
        this.orderSubmitProRSVs = orderSubmitProRSVs;
    }
    
    public OrderSubmitProInitRSVImpl(){
        if(orderSubmitProRSVs == null){
            Map<String, IOrderSubmitProRSV>  resp = EcpFrameContextHolder.getContext().getBeansOfType(IOrderSubmitProRSV.class);
            if(resp != null){
                orderSubmitProRSVs = new ArrayList<IOrderSubmitProRSV>(resp.values()); 
            }
        }
    }
}

