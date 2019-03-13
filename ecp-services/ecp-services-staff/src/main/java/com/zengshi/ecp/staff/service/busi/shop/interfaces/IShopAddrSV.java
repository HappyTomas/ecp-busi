/** 
 * Project Name:ecp-services-staff-server 
 * File Name:IShopAddrSV.java 
 * Package Name:com.zengshi.ecp.staff.service.busi.shop.interfaces 
 * Date:2015年9月16日下午6:08:27 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.staff.service.busi.shop.interfaces;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.ShopShipper;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年9月16日下午6:08:27  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6 
 * 
 * 店铺发货、退货地址管理
 */
public interface IShopAddrSV extends IGeneralSQLSV{
    /**
     * 
     * select:(查询一条店铺收货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param shopId
     * @param flag  标志位：10：取默认发货地址信息  11取默认退货地址信息
     * @return 
     * @since JDK 1.6
     */
    public ShopShipper select(Long shopId, String flag);
    /**
     * 
     * saveShopShipper:(新增店铺收货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param ss
     * @return 
     * @since JDK 1.6
     */
    public int save(ShopShipper shipper, long count);
    /**
     * 
     * delete:(根据id删除店铺发货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @return 
     * @since JDK 1.6
     */
    public int delete(Long id);
    /**
     * 
     * update:(更新店铺发货地址信息). <br/> 
     * 
     * @author PJieWin 
     * @param shipper
     * @return 
     * @since JDK 1.6
     */
    public int update(ShopShipper shipper);
    /**
     * 
     * count:(统计店铺当前发货地址信息总数). <br/> 
     * 
     * @author PJieWin 
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    public long count(Long shopId);
    /**
     * 
     * selectShipperAddr:(设置当前发货地址信息为默认发货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    public int installShipperAddr(Long id, Long shopId);
    /**
     * 
     * selectReturnAddr:(设置当前地址信息为默认退货地址). <br/> 
     * 
     * @author PJieWin 
     * @param id
     * @param shopId
     * @return 
     * @since JDK 1.6
     */
    public int installReturnAddr(Long id, Long shopId);
    /**
     * 
     * list:(查询当前店铺地址信息列表). <br/> 
     * 
     * @author PJieWin 
     * @param req
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopShipperResDTO> list(ShopShipperReqDTO req);
}

