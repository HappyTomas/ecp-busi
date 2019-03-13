package com.zengshi.ecp.app.req;

import java.util.List;

import com.zengshi.ecp.busi.order.vo.RCrePreOrdReqVO;
import com.zengshi.butterfly.app.model.IBody;

public class Ord008Req extends IBody {
    
    /** 
     * ord00801Req:购物车列表. 
     * @since JDK 1.6 
     */ 
    private List<RCrePreOrdReqVO> carList;
    public List<RCrePreOrdReqVO> getCarList() {
        return carList;
    }
    public void setCarList(List<RCrePreOrdReqVO> carList) {
        this.carList = carList;
    }
    
}

