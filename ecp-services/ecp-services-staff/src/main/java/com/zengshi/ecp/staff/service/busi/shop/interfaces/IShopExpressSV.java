package com.zengshi.ecp.staff.service.busi.shop.interfaces;

import java.util.List;

import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.ShopExpressRel;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO;

public interface IShopExpressSV {
        
    /**
     * 
     * list:(通过店铺ID，查找店铺物流列表信息). <br/> 
     * 
     * @author PJieWin 
     * @param pShopId
     * @return 
     * @since JDK 1.6
     */
    public List<ShopExpressRel> list(Long pShopId) ;
    /**
     * 
     * insert:(保存物流关系记录). <br/> 
     * 
     * @author PJieWin 
     * @param record
     * @return 
     * @since JDK 1.6
     */
    public int insert(ShopExpressRel record);
    /**
     * 
     * delete:(删除店铺物流信息). <br/> 
     * 
     * @author PJieWin 
     * @param pShopId
     * @return 
     * @since JDK 1.6
     */
    public int delete(Long pShopId);
    
    public int update(ShopExpressReqDTO reqDto);
    /**
     * 
     * nextId:(返回物流关系表ID序列). <br/> 
     * 
     * @author PJieWin 
     * @return 
     * @since JDK 1.6
     */
    public long nextId();
}

