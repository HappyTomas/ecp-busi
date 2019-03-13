package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;

public interface IShopInfoRSV {

    /**
     * 
     * listShopInfoByCond:(根据查询条件参数sCond，找出对应的店铺信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param sCond
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listShopInfoByCond(ShopSelectReqDTO sCond) throws BusinessException;
    /**
     * 
     * invalidShop:(根据shopID使该店铺失效). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param shopID 
     * @since JDK 1.6
     */
    public void invalidShop(ShopInfoReqDTO req) throws BusinessException;
    /**
     * 
     * activeShop:(根据shopID使该店铺生效). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param shopID 
     * @since JDK 1.6
     */
    public void activeShop(Long shopID) throws BusinessException;
    /**
     * 
     * insertShopInfo:(保存插入店铺信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param sReqDTO 
     * @since JDK 1.6
     */
    public void insertShopInfo(ShopInfoReqDTO sReqDTO) throws BusinessException;
    
    /**
     * 
     * updateShopInfo:(更新店铺信息，根据入参属性值进行更新). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param sReqDTO
     * @return 
     * @since JDK 1.6
     */
    public int updateShopInfo(ShopInfoReqDTO sReqDTO) throws BusinessException;
    /**
     * 
     * findShopInfoByShopID:(根据店铺ID，查找对应的店铺信息). <br/> 
     * dubbo层服务
     * 
     * @author PJieWin 
     * @param shopID
     * @return 
     * @since JDK 1.6
     */
    public ShopInfoResDTO findShopInfoByShopID(Long shopID) throws BusinessException;
    
    /**
     * 
     * list:(根据店铺ID获取店铺物流信息). <br/> 
     * 
     * @author PJieWin 
     * @param shopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Map<String, ShopExpressResDTO> listShopExpress(Long shopId) throws BusinessException;
    
    /**
     * 
     * updateShopExpress:(更新店铺物流信息). <br/> 
     * 
     * @author PJieWin 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int updateShopExpress(ShopExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listExpress:(获取该店铺物流公司的信息，<key物流公司ID, value>物流公司名称). <br/> 
     * 
     * @author PJieWin 
     * @param pShopId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Map<Long, String> listExpress(Long pShopId) throws BusinessException;
}

