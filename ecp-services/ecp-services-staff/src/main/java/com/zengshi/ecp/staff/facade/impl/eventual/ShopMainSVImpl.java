package com.zengshi.ecp.staff.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopMainSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICompanyManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionCallback;
import com.distribute.tx.common.TransactionContext;
import com.distribute.tx.common.TransactionStatus;
import com.distribute.tx.eventual.TransactionManager;

@Service("shopMainSV")
public class ShopMainSVImpl implements IShopMainSV {
    
    @Resource(name="transactionManager1")
    private TransactionManager transactionManager;
    
    
    @Resource
    private ICompanyManageSV companyManageSV;
    
    @Resource
    private IShopMgrSV shopMgrSV;
    
    private static final String MODULE = ShopMainSVImpl.class.getName();



    @Override
    public ShopInfoResDTO buildStorck(final ShopInfoReqDTO req) throws BusinessException{
        ShopInfoResDTO res = new ShopInfoResDTO();
        TransactionContext txMsg = new TransactionContext();
        ShopInfoResDTO resShop = new ShopInfoResDTO();
        try {
        	resShop = companyManageSV.saveShopInfo(req);
        	resShop.setShopShipTempDto(req.getShopShipTempDto());
        } catch (BusinessException be) {
            LogUtil.info(MODULE, be.getErrorCode() + "===" + be.getErrorMessage());
            be.printStackTrace();
            throw be;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        txMsg.setName("business-topic-staff-stock");
        txMsg.setContent(JSON.toJSONString(resShop));
        //启动主事务
        res =  (ShopInfoResDTO) transactionManager.startTransaction(txMsg, new TransactionCallback(){

            @Override
            public Object doInTransaction(TransactionStatus status) {
                    return null;
            }});
        
        return res;
    }



    @Override
    public void invalidShop(final ShopInfoReqDTO req) throws BusinessException {
        final TransactionContext txMsg = new TransactionContext();
        txMsg.setContent(JSON.toJSONString(req));
        txMsg.setName("business-topic-shop-invalid");
        //启动主事务
        transactionManager.startTransaction(txMsg, new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    LogUtil.debug(MODULE, "=========店铺失效，同时下架店铺所有商品的分布式事务开始========");
                    ShopInfo shopInfo = new ShopInfo();
                    shopInfo.setId(req.getId());
                    shopInfo.setShopStatus(StaffConstants.shopInfo.SHOP_STATUS_INVALID);
                    shopInfo.setUpdateTime(DateUtil.getSysDate());
                    shopInfo.setUpdateStaff(req.getUpdateStaff());
                    shopMgrSV.updateShopInfo(shopInfo);
                    txMsg.setContent(JSONObject.fromObject(req).toString());
                    
                    //更新店铺搜索数据
                    StaffTools.solrDelete("T_SHOP_INFO", req.getId());
                    LogUtil.debug(MODULE, "=========店铺失效，同时下架店铺所有商品的分布式事务，主事务结束========");
                } catch (Exception e) {
                    status.setRollbackOnly();//事务回滚
                    LogUtil.error(MODULE, "=========店铺失效，同时下架店铺所有商品的分布式事务，主事务异常，回滚========",e);
                    throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{});
                }
                return null;
            }
        });
    }

}

