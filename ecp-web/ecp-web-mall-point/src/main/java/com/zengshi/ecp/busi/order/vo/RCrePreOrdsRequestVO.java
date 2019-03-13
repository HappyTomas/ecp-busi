package com.zengshi.ecp.busi.order.vo;

import java.util.List;

import com.zengshi.ecp.base.vo.EcpBasePageReqVO;

/**
 * 
 * 功能描述：收取购物车列表项目
 *
 * @author  曾海沥(zenghl)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class RCrePreOrdsRequestVO extends EcpBasePageReqVO {

    /** 
     * serialVersionUID:TODO(用一句话描述这个变量表示什么). 
     * @since JDK 1.6 
     */ 
    private static final long serialVersionUID = 1L;
    private Long staffId;
    private String source;
    private List<RCrePreOrdReqVO> carList;
    
    private List<RCrePreOrdItemReqVO> cartItemList;
    
    public Long getStaffId() {
        return staffId;
    }
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public List<RCrePreOrdReqVO> getCarList() {
        return carList;
    }
    public void setCarList(List<RCrePreOrdReqVO> carList) {
        this.carList = carList;
    }
	public List<RCrePreOrdItemReqVO> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<RCrePreOrdItemReqVO> cartItemList) {
		this.cartItemList = cartItemList;
	}
    
}

