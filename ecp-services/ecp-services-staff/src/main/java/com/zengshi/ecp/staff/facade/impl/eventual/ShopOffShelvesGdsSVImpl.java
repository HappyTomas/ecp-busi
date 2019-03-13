package com.zengshi.ecp.staff.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoManageRSV;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopOffShelvesGdsSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺失效后，同时下架店铺的所有商品子事务接口实现类<br>
 * Date:2015-9-6下午2:30:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Service("shopTxOffShelvesGdsSV")
public class ShopOffShelvesGdsSVImpl implements IShopOffShelvesGdsSV{
    
    @Resource
    private IGdsInfoManageRSV gdsInfoManageRSV;

    private static final String MODULE = ShopOffShelvesGdsSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transctionName) {
        final ShopInfoResDTO res= JSON.parseObject(message.toString(), ShopInfoResDTO.class);
        try {
            LogUtil.debug(MODULE, "=========店铺失效，同时下架店铺商品的分布式事务，批量下架商品子事务开始========");
            GdsInfoReqDTO req = new GdsInfoReqDTO();
            req.setShopId(res.getId());//店铺ID
            gdsInfoManageRSV.batchOffShelvesGdsInfoByShopId(req);
            LogUtil.debug(MODULE, "=========店铺失效，同时下架店铺商品的分布式事务，批量下架商品子事务结束========");
        } catch (Exception e) {
            status.setRollbackOnly();//事务回滚
            LogUtil.debug(MODULE, "===================店铺失效，同时下架店铺商品的分布式事务，批量下架商品子事务失败，启用补偿机制======================",e);
        }
    }

}

