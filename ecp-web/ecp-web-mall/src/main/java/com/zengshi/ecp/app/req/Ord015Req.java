package com.zengshi.ecp.app.req;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.butterfly.app.model.IBody;

public class Ord015Req extends IBody {

    /** 
     * ordCartItems:更新的组合商品列表. 
     * @since JDK 1.6 
     */ 
    private List<ROrdCartItemRequest> ordCartItems;

    public List<ROrdCartItemRequest> getOrdCartItems() {
        return ordCartItems;
    }

    public void setOrdCartItems(List<ROrdCartItemRequest> ordCartItems) {
        this.ordCartItems = ordCartItems;
    }

}

