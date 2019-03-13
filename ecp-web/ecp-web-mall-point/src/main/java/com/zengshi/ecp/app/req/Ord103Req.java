package com.zengshi.ecp.app.req;

import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.butterfly.app.model.IBody;

public class Ord103Req extends IBody{
    
    /** 
     * cartItemChg:变更数量的明细. 
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
