/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IShopShipperRSV.java 
 * Package Name:com.zengshi.ecp.staff.dubbo.interfaces 
 * Date:2015年9月16日下午8:35:42 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.dubbo.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午8:35:42  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 */
public interface IShopShipperAddrRSV {
    /**
     * 
     * selectShipperAddr:(取默认发货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public ShopShipperResDTO selectShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * selectShipperBack:(取默认退货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public ShopShipperResDTO selectReturnAddr(ShopShipperReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * selectShipperAddr:(设置默认发货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public int installShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * selectShipperBack:(设置默认退货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public int installReturnBack(ShopShipperReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * deleteShipperAddr:(删除发货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public int deleteShipperAddr(ShopShipperReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * saveShipperAddr:(保存发货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param shipper
     * @return 
     * @since JDK 1.6
     */
    public int saveShipperAddr(ShopShipperReqDTO shipper) throws BusinessException;
    /**
     * 
     * listShipperAddr:(查看发货地址信息列表). <br/> 
     * 
     * @author PJieWin 
     * @param shipper
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopShipperResDTO> listShipperAddr(ShopShipperReqDTO shipper) throws BusinessException;

}

