package com.zengshi.ecp.app.req;

import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.butterfly.app.model.IBody;

public class Ord003Req extends IBody {
    
    /** 
     * ordCartItem:删除的明细. 
     * @since JDK 1.6 
     */ 
    private ROrdCartItemRequest ordCartItem;

    public ROrdCartItemRequest getOrdCartItem() {
        return ordCartItem;
    }

    public void setOrdCartItem(ROrdCartItemRequest ordCartItem) {
        this.ordCartItem = ordCartItem;
    }
    
    
}

