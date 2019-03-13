package com.zengshi.ecp.app.req;

import com.zengshi.ecp.general.order.dto.ROrdCartChangeRequest;
import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.butterfly.app.model.IBody;

public class Ord004Req extends IBody {

    private ROrdCartItemRequest ordCartItem;

    private ROrdCartChangeRequest ordCartChg;

    public ROrdCartItemRequest getOrdCartItem() {
        return ordCartItem;
    }

    public void setOrdCartItem(ROrdCartItemRequest ordCartItem) {
        this.ordCartItem = ordCartItem;
    }

    public ROrdCartChangeRequest getOrdCartChg() {
        return ordCartChg;
    }

    public void setOrdCartChg(ROrdCartChangeRequest ordCartChg) {
        this.ordCartChg = ordCartChg;
    }
}

