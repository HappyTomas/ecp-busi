package com.zengshi.ecp.staff.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IStockBuildSubStaffSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

@Service("stockBuildSubStaffSV")
public class StockBuildSubGdsSVImpl implements IStockBuildSubStaffSV {
    

    @Resource
    private IGdsStockRSV gdsStockRSV;
    private static final String MODULE = StockBuildSubGdsSVImpl.class.getName();
    
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        final ShopInfoResDTO res= JSON.parseObject(message.toString(), ShopInfoResDTO.class);
        try {
            LogUtil.debug(MODULE, "======================新增店铺，新增仓库子事务开始===========================");
            StockRepMainReqDTO dto = new StockRepMainReqDTO();
            long id = res.getId();
            StockRepReqDTO rep = new StockRepReqDTO();
            rep.setShopId(id);
            rep.setCompanyId(res.getCompanyId());
            rep.setRepName(res.getShopName()+"共仓");
            rep.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_SELLER);
            rep.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_PUBLIC);
            rep.setRemark("店铺新增");
            dto.setStaffId(res.getCreateStaff());
            dto.setStockRepDTO(rep);
            this.gdsStockRSV.addStockRep(dto);
            LogUtil.debug(MODULE, "======================新增店铺，新增仓库子事务结束===========================");
        } catch (Exception e) {
            e.printStackTrace();
            status.setRollbackOnly();//事务回滚
            LogUtil.error(MODULE, e.getMessage());
        }
     
    }

}

