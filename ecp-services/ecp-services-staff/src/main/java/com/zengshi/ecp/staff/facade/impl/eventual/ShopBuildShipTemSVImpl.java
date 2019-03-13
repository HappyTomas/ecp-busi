package com.zengshi.ecp.staff.facade.impl.eventual;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipTempPricesReqDTO;
import com.zengshi.ecp.staff.facade.interfaces.eventual.IShopBuildShipTemSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 新建店铺时创建运费模板子事务<br>
 * Date:2015-10-9上午11:13:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Service("shopBuildShipTemSV")
public class ShopBuildShipTemSVImpl implements IShopBuildShipTemSV{

    @Resource
    private IGdsShiptemRSV gdsShipTemRSV;
    
    private static final String MODULE = ShopBuildShipTemSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transactionName) {
        final ShopInfoResDTO res = JSON.parseObject(message.toString(), ShopInfoResDTO.class);
        try {
            LogUtil.debug(MODULE, "=========新建店铺，同时创建默认运费模板，子事务开始========");
            GdsShiptempReqDTO req = new GdsShiptempReqDTO();
            ObjectCopyUtil.copyObjValue(res.getShopShipTempDto(), req, null, true);
            List<GdsShiptempPriceReqDTO> dtoList = new ArrayList<GdsShiptempPriceReqDTO>();
            //获取新建店铺时，运费模板的相关参数
            if (CollectionUtils.isNotEmpty(res.getShopShipTempDto().getShiptempPriceReqDTOList())) {
                for (ShopShipTempPricesReqDTO shipTempPrice:res.getShopShipTempDto().getShiptempPriceReqDTOList()) {
                    GdsShiptempPriceReqDTO tempDto = new GdsShiptempPriceReqDTO();
                    ObjectCopyUtil.copyObjValue(shipTempPrice, tempDto, null, true);
                    dtoList.add(tempDto);
                }
            }
            req.setGdsShiptempPriceReqDTOList(dtoList);
            req.setShopId(res.getId());
            req.setCreateStaff(res.getCheckStaff());
            req.setUpdateStaff(res.getUpdateStaff());
            gdsShipTemRSV.addShopDefaultShipMent(req);
            LogUtil.debug(MODULE, "=========新建店铺，同时创建默认运费模板，子事务结束========");
        } catch (Exception e) {
            status.setRollbackOnly();//事务回滚
            LogUtil.debug(MODULE, "===================新建店铺，同时创建默认运费模板，子事务失败，启用补偿机制======================",e);
        }
    }

}

