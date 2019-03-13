package com.zengshi.ecp.app.req;

import java.util.List;

import com.zengshi.ecp.order.dubbo.dto.ROrdCartItemRequest;
import com.zengshi.butterfly.app.model.IBody;

public class Ord104Req extends IBody {
    private List<ROrdCartItemRequest> ordCartItems;

    public List<ROrdCartItemRequest> getOrdCartItems() {
        return ordCartItems;
    }

    public void setOrdCartItems(List<ROrdCartItemRequest> ordCartItems) {
        this.ordCartItems = ordCartItems;
    }
}

