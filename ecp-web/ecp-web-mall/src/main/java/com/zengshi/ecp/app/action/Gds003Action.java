package com.zengshi.ecp.app.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Gds003Req;
import com.zengshi.ecp.app.resp.Gds003Resp;
import com.zengshi.ecp.app.resp.gds.GdsSecKillPromInfoDTO;
import com.zengshi.ecp.app.resp.gds.GdsSeckillDiscountDTO;
import com.zengshi.ecp.app.resp.gds.PromBaseInfo;
import com.zengshi.ecp.app.resp.gds.PromListBaseInfo;
import com.zengshi.ecp.app.resp.gds.PromSkuPriceBaseInfo;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdCartRSV;
import com.zengshi.ecp.prom.dubbo.dto.GdsPromListDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.SeckillDiscountDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 获取商品促销信息
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午10:28:03  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version  
 * @since JDK 1.6
 */
@Service("gds003")
@Action(bizcode = "gds003", userCheck = false)
@Scope("prototype")
public class Gds003Action extends AppBaseAction<Gds003Req, Gds003Resp> {
    
    @Resource
    private IOrdCartRSV ordCartRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private ICustInfoRSV iCustInfoRSV;
    
    private static final String MODULE = Gds003Action.class.getName();
    private static String MOBILE = "2";
    @Resource
    private IPromQueryRSV promQueryRSV;
    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {
        Gds003Req gds003Req = this.request;
        Gds003Resp gds003Resp = this.response; 
        //List<PromListRespDTO> list = new ArrayList<PromListRespDTO>();
        GdsPromListDTO list = new GdsPromListDTO();
        GdsSeckillDiscountDTO gdsSeckillDiscountDTO = new GdsSeckillDiscountDTO();
        try {
            PromRuleCheckDTO promRuleCheckDTO = new PromRuleCheckDTO();
            CustInfoReqDTO custInfoReqDTO = new CustInfoReqDTO();
            
            CustInfoResDTO custInfoResDTO = null;
            ShopStaffGroupReqDTO shopStaffGroupReqDTO = new ShopStaffGroupReqDTO();
            if (custInfoReqDTO.getStaff().getId() == 0) {
            	promRuleCheckDTO.setCustLevelValue(custInfoReqDTO.getStaff().getStaffLevelCode());
            } else {
	            custInfoReqDTO.setId(custInfoReqDTO.getStaff().getId());
	            custInfoResDTO = iCustInfoRSV.getCustInfoById(custInfoReqDTO);
	            promRuleCheckDTO.setCustLevelValue(custInfoResDTO.getCustLevelCode());
	            promRuleCheckDTO.setAreaValue(custInfoResDTO.getProvinceCode());
	            promRuleCheckDTO.setStaffId(custInfoResDTO.getId() + "");
	            shopStaffGroupReqDTO.setStaffId(custInfoResDTO.getId());
            }
            
            promRuleCheckDTO.setSiteId(promRuleCheckDTO.getCurrentSiteId());
//            promRuleCheckDTO.setStaffId(custInfoReqDTO.getStaff().getId() + "");
            promRuleCheckDTO.setGdsId(gds003Req.getGdsId());
            promRuleCheckDTO.setChannelValue(MOBILE);
            promRuleCheckDTO.setShopId(gds003Req.getShopId());
            promRuleCheckDTO.setCalDate(new Date(System.currentTimeMillis()));
            promRuleCheckDTO.setSkuId(gds003Req.getSkuId());
            promRuleCheckDTO.setBasePrice(gds003Req.getRealPrice());
            promRuleCheckDTO.setBuyPrice(gds003Req.getDiscountPrice());
            promRuleCheckDTO.setGdsName(gds003Req.getGdsName());
            promRuleCheckDTO.setShopName(promRuleCheckDTO.getShopName());
            //list = promQueryRSV.listProm(promRuleCheckDTO);
            list = promQueryRSV.listPromNew(promRuleCheckDTO);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取促销列表失败", e);
        }
        List<PromListBaseInfo> listBaseInfos = new ArrayList<PromListBaseInfo>();
        if(list != null && CollectionUtils.isNotEmpty(list.getPromList())){
            for(PromListRespDTO promListRespDTO:list.getPromList()){
            	
            	PromListBaseInfo promListBaseInfo = new PromListBaseInfo();
            	PromBaseInfo promBaseInfo = new PromBaseInfo();
            	
            	PromSkuPriceBaseInfo skuPriceBaseInfo = new PromSkuPriceBaseInfo();
            	ObjectCopyUtil.copyObjValue(promListRespDTO.getPromSkuPriceRespDTO(), skuPriceBaseInfo, null, false);
            	ObjectCopyUtil.copyObjValue(promListRespDTO.getPromInfoDTO(), promBaseInfo, null, false);
            	ObjectCopyUtil.copyObjValue(promListRespDTO, promListBaseInfo, null, false);
            	promListBaseInfo.setPromBaseInfo(promBaseInfo);
            	promListBaseInfo.setPromSkuPriceBaseInfo(skuPriceBaseInfo);
            	listBaseInfos.add(promListBaseInfo);
            }
            
            SeckillDiscountDTO secKill = list.getSeckill();
            if(null != secKill){
                ObjectCopyUtil.copyObjValue(secKill, gdsSeckillDiscountDTO, null, false);
                if(null != secKill.getSeckillProm()){
                    GdsSecKillPromInfoDTO gdsSeckillPromInfoDTO = new GdsSecKillPromInfoDTO();
                    ObjectCopyUtil.copyObjValue(secKill.getSeckillProm(), gdsSeckillPromInfoDTO, null, false);
                    gdsSeckillDiscountDTO.setSeckillProm(gdsSeckillPromInfoDTO);
                }
            }
            
        }
        gds003Resp.setSeckill(gdsSeckillDiscountDTO);
        gds003Resp.setSaleList(listBaseInfos);
    }

}
