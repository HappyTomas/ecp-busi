package com.zengshi.ecp.staff.service.busi.manage.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月10日下午3:40:13  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 店铺管理类，管理店铺相关的信息
 */
public interface IShopMgrSV extends IGeneralSQLSV{
    /**
     * 
     * findShopByShopID:(根据shopID查询店铺信息). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param sShopID
     * @return 
     * @since JDK 1.6
     */
    public ShopInfo findShopByShopID(Long sShopID);
    /**
     * 
     * listShopByCond:(根据查询条件，查询店铺列表信息). <br/> 
     * 
     * @author PJieWin 
     * @param pSelectCond
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listShopByCond(ShopSelectReqDTO pSelectCond);
    
    /**
     * 
     * listShop:(根据店铺ID列表，找出店铺信息). <br/> 
     * 
     * @author PJieWin 
     * @param baseInfo
     * @param idList
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listShop(BaseInfo baseInfo, List<Long> idList);
    
    /**
     * 
     * listShopByCompanyID:(根据企业ID，找出企业下面的所有店铺信息). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param sCompanyID
     * @return 
     * @since JDK 1.6
     */
    public List<ShopInfoResDTO> listShopByCompanyID(Long  sCompanyID);
    /**
     * 
     * listShop:(找出所有的店铺信息，慎用). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public List<ShopInfo> listShop();
    /**
     * 
     * updateShopInfo:(更新店铺信息). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param sShopInfo 
     * @since JDK 1.6
     */
    public void updateShopInfo(ShopInfo sShopInfo) throws BusinessException;
    /**
     * 
     * insertShopInfo:(保存店铺信息). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param sShopInfo 
     * @since JDK 1.6
     */
    public Long insertShopInfo(ShopInfo sShopInfo);
    /**
     * 
     * findShopIdByName:(根据店铺名称查找索引表，找出店铺ID). <br/> 
     * service层服务
     * 
     * @author PJieWin 
     * @param sName
     * @return 
     * @since JDK 1.6
     */
    public Long findShopIdByName(String sName);
}

