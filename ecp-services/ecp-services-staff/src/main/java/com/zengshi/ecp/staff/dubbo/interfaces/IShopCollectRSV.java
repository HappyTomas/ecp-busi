package com.zengshi.ecp.staff.dubbo.interfaces;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

public interface IShopCollectRSV {
    /**
     * 
     * insertShopCollect:(添加关注店铺). <br/> 
     * 
     * @author PJieWin 
     * @param pShopClDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int insertShopCollect(ShopCollectReqDTO pShopClDTO)throws BusinessException;
    /**
     * 
     * deleteShopCollect:(删除店铺关注). <br/> 
     * 
     * @author PJieWin 
     * @param pCollectID
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteShopCollect(Long pCollectID)throws BusinessException;
    /**
     * 
     * listShopCollect:(根据pShopCleDTO.staffID查询关注店铺列表). <br/> 
     * 
     * @author PJieWin 
     * @param pShopCleDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listShopCollect(ShopCollectReqDTO pShopCleDTO)throws BusinessException;

    /**
     * 
     * countByShopId:(查询店铺的收藏人气). <br/> 
     * 即根据店铺id查询店铺收藏表的记录数
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long countByShopId(ShopCollectReqDTO req) throws BusinessException;
    
    /**
     * 
     * deleteShopBySel:(根据用户、店铺id删除收藏的店铺). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int deleteShopBySel(ShopCollectReqDTO req) throws BusinessException;
    
    /**
     * 
     * listShopCollectForFav:(根据pShopCleDTO.staffID查询关注店铺列表). <br/> 
     * 
     * @author huangxl5 
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listShopCollectForFav(ShopCollectReqDTO req)throws BusinessException;

}

