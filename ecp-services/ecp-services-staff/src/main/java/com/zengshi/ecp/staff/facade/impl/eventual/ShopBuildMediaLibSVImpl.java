package com.zengshi.ecp.staff.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatalogRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatlog2ShopReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaLibReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatalogRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatlog2ShopRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsMediaRSV;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopBuildMediaLibSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 新建店铺时创建媒体库子事务<br>
 * Date:2015-10-9上午11:13:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Service("shopBuildMediaSV")
public class ShopBuildMediaLibSVImpl implements IShopBuildMediaLibSV{

    @Resource
    private IGdsMediaRSV gdsMediaRSV;
    
    @Resource
    private IGdsCatalogRSV gdsCatalogRSV;
    
    @Resource
    private IGdsCatlog2ShopRSV gdsCatlog2ShopRSV;
    
    private static final String MODULE = ShopBuildMediaLibSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        final ShopInfoResDTO res= JSON.parseObject(message.toString(), ShopInfoResDTO.class);
        try {
            LogUtil.debug(MODULE, "=========新建店铺，同时创建媒体库，创建店铺目录关联，子事务开始========");
            /*1、创建媒体库*/
            GdsMediaLibReqDTO req = new GdsMediaLibReqDTO();
            req.setShopId(res.getId());
            req.setShopName(res.getShopName());
            gdsMediaRSV.saveGdsMediaLib(req);
            
            /*2、创建店铺目录关联*/
            //获取默认目录
            GdsCatalogRespDTO resp = gdsCatalogRSV.queryDefaultCatalog(new BaseInfo());
            GdsCatlog2ShopReqDTO shopReq = new GdsCatlog2ShopReqDTO();
            shopReq.setShopId(res.getId());
            List<Long> list = new ArrayList<Long>();
            list.add(resp.getId());
            shopReq.setCatlogIds(list);
            //保存店铺目录
            gdsCatlog2ShopRSV.batchSaveGdsCatlog2Shop(shopReq);

            LogUtil.debug(MODULE, "=========新建店铺，同时创建媒体库，创建店铺目录关联，子事务结束========");
        } catch (Exception e) {
            status.setRollbackOnly();//事务回滚
            LogUtil.debug(MODULE, "===================新建店铺，同时创建媒体库，创建店铺目录关联，子事务失败，启用补偿机制======================" + e.getMessage());
        }
    }

}

