package com.zengshi.ecp.app.resp;

import com.zengshi.butterfly.app.model.IBody;

/**
 * Created by wang on 16/3/17.
 */
public class Ord013Resp extends IBody{
    private Ord00401Resp cartPromDTO;

    private Ord00401Resp cartPromItemDTO;

    public Ord00401Resp getCartPromDTO() {
        return cartPromDTO;
    }

    public void setCartPromDTO(Ord00401Resp cartPromDTO) {
        this.cartPromDTO = cartPromDTO;
    }

    public Ord00401Resp getCartPromItemDTO() {
        return cartPromItemDTO;
    }

    public void setCartPromItemDTO(Ord00401Resp cartPromItemDTO) {
        this.cartPromItemDTO = cartPromItemDTO;
    }

}
