package com.zengshi.ecp.staff.service.busi.shop.interfaces;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.ShopCollect;
import com.zengshi.ecp.staff.dubbo.dto.ShopCollectReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;

public interface IShopCollectSV extends IGeneralSQLSV{
    /**
     * 
     * insertShopCollect:(保持关注的店铺). <br/> 
     * service服务
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
     * service服务
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
     * listShopCollect:(根据用户ID<pShopClDTO.staffID>)查看关注店铺列表. <br/> 
     * service服务
     * 
     * @author PJieWin 
     * @param pShopClDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<ShopCollect> listShopCollect(ShopCollectReqDTO req)throws BusinessException;
    
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
     * listCollectShopForFav:(根据用户、店铺id删除收藏的店铺). <br/> 
     * 
     * @author huangxl5
     * @param req
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<ShopInfoResDTO> listCollectShopForFav(ShopCollectReqDTO req) throws BusinessException;
}

