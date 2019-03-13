package com.zengshi.ecp.staff.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.facade.interfaces.eventual.ICompanyBuildRepSV;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 企业新增，同时新增仓库子事务接口实现类<br>
 * Date:2015-9-4下午5:43:21  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
@Service("companyTxBuildRepSV")
public class CompanyBuildRepSVImpl implements ICompanyBuildRepSV {

    @Resource
    private IGdsStockRSV gdsStockRSV;
    
    private static final String MODULE = CompanyBuildRepSVImpl.class.getName();

    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transctionName) {
        final CompanyInfoResDTO res= JSON.parseObject(message.toString(), CompanyInfoResDTO.class);
        try {
            LogUtil.debug(MODULE, "=========新建企业，同时创建仓库的分布式事务，新增仓库子事务开始========");
            StockRepMainReqDTO repMain = new StockRepMainReqDTO();
            repMain.setStaffId(res.getUpdateStaff());//操作人
            StockRepReqDTO rep = new StockRepReqDTO();
            rep.setRepName(res.getCompanyName() + "的仓库");//仓库名称
            rep.setCompanyId(res.getId());//企业ID
            rep.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_BUYER);//买家仓库
            rep.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_BUYER);//买家共仓
            rep.setRemark("企业新增");//备注
            rep.setStatus("1");
            repMain.setStockRepDTO(rep);
            gdsStockRSV.addStockRep(repMain);
            LogUtil.debug(MODULE, "=========新建企业，同时创建仓库的分布式事务，新增仓库子事务结束========");
        } catch (Exception e) {
            e.printStackTrace();
            status.setRollbackOnly();//事务回滚
            LogUtil.debug(MODULE, "===================新建企业，同时创建仓库的分布式事务，新增仓库子事务失败，启用补偿机制======================",e);
        }
    
    }

}

