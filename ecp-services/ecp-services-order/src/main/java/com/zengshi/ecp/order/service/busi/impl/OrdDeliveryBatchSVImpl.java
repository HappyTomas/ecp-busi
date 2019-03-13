package com.zengshi.ecp.order.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.mapper.busi.OrdDeliveryBatchMapper;
import com.zengshi.ecp.order.dao.model.OrdDeliveryBatch;
import com.zengshi.ecp.order.dao.model.OrdDeliveryBatchCriteria;
import com.zengshi.ecp.order.dao.model.OrdDeliveryDetails;
import com.zengshi.ecp.order.dao.model.OrdDeliveryEntity;
import com.zengshi.ecp.order.dao.model.OrdEntityImport;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdTrack;
import com.zengshi.ecp.order.dao.model.ThingLock;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.dto.RConfirmSubInfo;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintRequest;
import com.zengshi.ecp.order.dubbo.dto.RDeliveryPrintResponse;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndBatchInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndStatusInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseAndSubInfo;
import com.zengshi.ecp.order.dubbo.dto.SBaseSplitInfo;
import com.zengshi.ecp.order.dubbo.dto.SDelyBatchInfo;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsComm;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsDelivery;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsMain;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsSub;
import com.zengshi.ecp.order.dubbo.dto.SOrderDetailsTax;
import com.zengshi.ecp.order.dubbo.util.CommonConstants;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryBatchSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryChkSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryDetailsSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdDeliveryEntitySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdEntityImportSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceCommSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdInvoiceTaxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainShopIdxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainStaffIdxSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdTrackSV;
import com.zengshi.ecp.order.service.busi.interfaces.IThingLockSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipperResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopShipperAddrRSV;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月6日下午2:49:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author cbl
 * @version
 * @since JDK 1.6
 */
public class OrdDeliveryBatchSVImpl implements IOrdDeliveryBatchSV {
    
    @Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

    @Resource
    private OrdDeliveryBatchMapper ordDeliveryBatchMapper;

    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IOrdMainSV ordMainSV;
    
    @Resource
    private IOrdInvoiceCommSV ordInvoiceCommSV;
    
    @Resource
    private IOrdInvoiceTaxSV ordInvoiceTaxSV;

    @Resource
    private IOrdMainShopIdxSV ordMainShopIdxSV;

    @Resource
    private IOrdMainStaffIdxSV ordMainStaffIdxSV;

    @Resource
    private IOrdDeliveryDetailsSV ordDeliveryDetailsSV;

    @Resource
    private IOrdDeliveryEntitySV ordDeliveryEntitySV;

    @Resource
    private IOrdEntityImportSV ordEntityImportSV;

//    @Resource
//    private IOrdDeliveAddrSV ordDeliveAddrSV;

    @Resource
    private IOrdTrackSV ordTrackSV;
    
    @Resource
    private IOrdDeliveryChkSV ordDeliveryChkSV;
    
    @Resource
    private IThingLockSV thingLockSV;
    
    @Resource
    private IBaseExpressRSV baseExpressRSV;


    // 客户域接口
    @Resource
    private IShopShipperAddrRSV shopShipperAddrRSV;

    private static final String MODULE = OrdDeliveryBatchSVImpl.class.getName();

    @Resource(name = "seq_ord_delivery_batch")
    private Sequence seqOrdDeliveryBatch;

    @Override
    public OrdDeliveryBatch saveOrdDeliveryBatch(final OrdDeliveryBatch ordDeliveryBatch) {
        ordDeliveryBatch.setId(this.seqOrdDeliveryBatch.nextValue());
        this.ordDeliveryBatchMapper.insert(ordDeliveryBatch);
        return ordDeliveryBatch;
    }

    /**
     * saveDealDeliveryBatch:处理批次表信息. <br/>
     * 
     * @author cbl
     * @param rConfirmDeliveRequest
     * @return
     * @since JDK 1.6
     */
    private SDelyBatchInfo saveDealDeliveryBatch(RConfirmDeliveRequest rConfirmDeliveRequest) {

        
        OrdMain om = this.ordMainSV.queryOrderByOrderId(rConfirmDeliveRequest.getOrderId());
        if (om == null) {
            LogUtil.info(MODULE, "未找到[" + rConfirmDeliveRequest.getOrderId() + "]该订单的信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312001);
        }
        OrdDeliveryBatch ordDeliveryBatch = new OrdDeliveryBatch();
        ordDeliveryBatch.setOrderId(rConfirmDeliveRequest.getOrderId());
        ObjectCopyUtil.copyObjValue(om, ordDeliveryBatch, null, false);
        ordDeliveryBatch.setDeliveryType(rConfirmDeliveRequest.getDeliveryType());
        ordDeliveryBatch.setContactAddress(om.getChnlAddress());
        
//        if(CollectionUtils.isNotEmpty(odas)){
//            ordDeliveryBatch.setBringName(od.getBringName());
//            ordDeliveryBatch.setBringPhone(od.getBringPhone());
//            ordDeliveryBatch.setBringTel(od.getBringNumber());
//            ordDeliveryBatch.setCardId(od.getCardId());
//            ordDeliveryBatch.setCardType(od.getCardType());
//            ordDeliveryBatch.setContactAddress(od.getChnlAddress());
//            ordDeliveryBatch.setContactName(od.getContactName());
//            ordDeliveryBatch.setContactPhone(od.getContactPhone());
//            ordDeliveryBatch.setContactTel(od.getContactNumber());
//            ordDeliveryBatch.setContactZip(od.getPostCode());
//        }
        
        

        /*------调用店铺域接口----start---*/
        // 调用店铺域接口获取 发货人相关信息
        ShopShipperResDTO ssr = null;
        try {
            ShopShipperReqDTO ssrd = new ShopShipperReqDTO();
            ssrd.setShopId(rConfirmDeliveRequest.getShopId());
            ssr = shopShipperAddrRSV.selectShipperAddr(ssrd);
            if(ssr != null){
                ordDeliveryBatch.setSendName(ssr.getShipperName());
                ordDeliveryBatch.setSendPhone(ssr.getShipperMobile());
                ordDeliveryBatch.setSendTel(ssr.getShipperPhone());
                ordDeliveryBatch.setSendZip(ssr.getPostCode());
                ordDeliveryBatch.setSendAddress(ssr.getShipperAddress()); 
            }
            
        } catch (Exception e) {
            LogUtil.info(MODULE, "--------------处理客户域出错，不影响本业务处理---------------");
            e.printStackTrace();
            LogUtil.info(MODULE, "--------------处理客户域出错---------------");
        }
        if(ssr != null){
            LogUtil.info(MODULE,ssr.toString());
        }

        /*------调用店铺域接口----end---*/

        ordDeliveryBatch.setContactDate(null);
        ordDeliveryBatch.setExpressId(rConfirmDeliveRequest.getExpressId());
        ordDeliveryBatch.setExpressNo(rConfirmDeliveRequest.getExpressNo());
        ordDeliveryBatch.setProxyShopId(0l);
        ordDeliveryBatch.setRemark(rConfirmDeliveRequest.getRemark());

        ordDeliveryBatch.setSendDate(DateUtil.getSysDate());
//        ordDeliveryBatch.setShopId(rConfirmDeliveRequest.getShopId());
//        ordDeliveryBatch.setStaffId(rConfirmDeliveRequest.getStaffId());

        ordDeliveryBatch.setCreateStaff(rConfirmDeliveRequest.getStaff().getId());
        ordDeliveryBatch.setCreateTime(DateUtil.getSysDate());
        ordDeliveryBatch.setUpdateStaff(rConfirmDeliveRequest.getStaff().getId());
        ordDeliveryBatch.setUpdateTime(ordDeliveryBatch.getCreateTime());

        OrdDeliveryBatch odb = saveOrdDeliveryBatch(ordDeliveryBatch);
        SDelyBatchInfo sdb = new SDelyBatchInfo();
        sdb.setBatchNo(odb.getId());
        sdb.setSendDate(odb.getSendDate());
        return sdb;
    }

    private Boolean saveDealOrdDeliveryDetails(RConfirmDeliveRequest rConfirmDeliveRequest,
            SDelyBatchInfo sDelyBatchInfo) {
        Boolean isImport = false;
        List<OrdDeliveryDetails> ordDeliveryDetails = new ArrayList<OrdDeliveryDetails>();
        for (RConfirmSubInfo rcsi : rConfirmDeliveRequest.getrConfirmSubInfo()) {
            // 是否有发货时导入实体编号的商品
            if (DelyConstants.IsDelyImportEntity.IS_DELY_IMPORT_ENTITY_TRUE.equals(rcsi.getIsImport())) {
                isImport = true;
            }
            SBaseAndSubInfo sba = new SBaseAndSubInfo();
            sba.setOrderId(rConfirmDeliveRequest.getOrderId());
            sba.setOrderSubId(rcsi.getOrderSubId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sba);
            if (os == null) {
                LogUtil.info(MODULE, "未找到[" + rcsi.getOrderSubId() + "]该子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312006);
            }

            OrdDeliveryDetails odd = new OrdDeliveryDetails();
            odd.setBatchId(sDelyBatchInfo.getBatchNo());
            odd.setCategoryCode(os.getCategoryCode());
            odd.setDeliveryAmount(rcsi.getDeliveryAmount());
            odd.setGdsId(os.getGdsId());
            odd.setGdsName(os.getGdsName());
            odd.setOrderSubId(rcsi.getOrderSubId());
            odd.setSkuId(os.getSkuId());
            odd.setSkuInfo(os.getSkuInfo());
            odd.setIsImport(rcsi.getIsImport());
            odd.setSendDate(sDelyBatchInfo.getSendDate());
            odd.setShopId(rConfirmDeliveRequest.getShopId());
            odd.setStaffId(rConfirmDeliveRequest.getStaffId());
            odd.setOrderId(rConfirmDeliveRequest.getOrderId());
            odd.setExpressNo(rConfirmDeliveRequest.getExpressNo());

            odd.setCreateStaff(rConfirmDeliveRequest.getStaff().getId());
            odd.setCreateTime(DateUtil.getSysDate());
            odd.setUpdateStaff(rConfirmDeliveRequest.getStaff().getId());
            odd.setUpdateTime(odd.getCreateTime());

            ordDeliveryDetails.add(odd);
        }
        this.ordDeliveryDetailsSV.saveOrdDeliveryDetailsList(ordDeliveryDetails);
        return isImport;
    }

    @Override
    public Long saveConfirmDeliveSV(RConfirmDeliveRequest rConfirmDeliveRequest) {

        ThingLock thingLock = new ThingLock();
        thingLock.setId(rConfirmDeliveRequest.getOrderId());
        thingLock.setType(CommonConstants.LockType.LOCK_TYPE_DELY);
        //加锁
        this.thingLockSV.addThingLock(thingLock);
        
        //主要校验发货数据
        this.ordDeliveryChkSV.chkDeliverBathcInput(rConfirmDeliveRequest);

        /*
         * 更新T_ORD_MAIN-订单主表 更新T_ORD_MAIN_SHOP_IDX-店铺订单索引表 更新T_ORD_MAIN_STAFF_IDX-买家订单索引表
         */
        SBaseAndStatusInfo sosi = new SBaseAndStatusInfo();
        sosi.setOrderId(rConfirmDeliveRequest.getOrderId());
        sosi.setOperatorId(rConfirmDeliveRequest.getStaff().getId());
        sosi.setShopId(rConfirmDeliveRequest.getShopId());
        sosi.setStaffId(rConfirmDeliveRequest.getStaffId());
        
        // 判断是否全部发货
        if (rConfirmDeliveRequest.getIsSendAll()) {
            sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL); // 全部发货
            sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_SEND_ALL);
            sosi.setDeliverDate(DateUtil.getSysDate());
        } else {
            sosi.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); // 部分发货
            sosi.setOrderTwoStatus(OrdConstants.OrderTwoStatus.STATUS_SEND_SECTION);
        }
        updateOrderInfo(sosi);

        /* 更新T_ORD_SUB-订单子表 */
        updateOrderSubInfo(sosi, rConfirmDeliveRequest.getrConfirmSubInfo());

        /* 新增T_ORD_TRAC--各种-订单跟踪表 */
        dealOrderTrack(rConfirmDeliveRequest);

        /* 新增T_ORD_DELIVERY_BATCH--发货批次表 */
        SDelyBatchInfo sdb = saveDealDeliveryBatch(rConfirmDeliveRequest);

        /*
         * 新增T_ORD_DELIVERY_DETAILS-发货批次详情isImport 标识是否有发货时录入实体编号的商品
         */
        Boolean isImport = saveDealOrdDeliveryDetails(rConfirmDeliveRequest, sdb);

        /* 新增T_ORD_DELIVERY_ENTITY-发货批次实体表 */
        if (isImport) {
            SBaseAndBatchInfo sbsi = new SBaseAndBatchInfo();
            sbsi.setOrderId(rConfirmDeliveRequest.getOrderId());
            sbsi.setShopId(rConfirmDeliveRequest.getShopId());
            sbsi.setBatchId(sdb.getBatchNo());
            dealOrdDeliveryEntity(sbsi);
        }
        
        //解锁
        this.thingLockSV.removeThingLock(thingLock);
        return sdb.getBatchNo();
    }

    /**
     * delOrdDeliveryEntity:处理实体编号相关方法. <br/>
     * 
     * @author cbl
     * @param orderId
     * @since JDK 1.6
     */
    private void dealOrdDeliveryEntity(SBaseAndBatchInfo sBaseAndBatchInfo) {
        List<OrdEntityImport> ordEntityImports = this.ordEntityImportSV
                .findByOrderId((SBaseSplitInfo) sBaseAndBatchInfo);
        if (CollectionUtils.isEmpty(ordEntityImports)) {
            LogUtil.info(MODULE, "未找到实体编号相关数据");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312006);
        }
        List<OrdDeliveryEntity> odes = new ArrayList<OrdDeliveryEntity>();
        for (OrdEntityImport oei : ordEntityImports) {
            OrdDeliveryDetails odd = this.ordDeliveryDetailsSV.findByOrderSubId(
                    sBaseAndBatchInfo.getOrderId(), oei.getOrderSubId()).get(0);
            OrdDeliveryEntity ode = new OrdDeliveryEntity();
            // 组装实体编号表数据
            ode.setBatchId(sBaseAndBatchInfo.getBatchId());
            ode.setImportNo(oei.getImportNo());
            ode.setCategoryCode(odd.getCategoryCode());
            ode.setDetailsId(odd.getId());
            ode.setEntityCode(oei.getEntityCode());
            ode.setEntityStatus(OrdConstants.Common.COMMON_VALID);
            ode.setExpressNo(odd.getExpressNo());
            ode.setGdsId(odd.getGdsId());
            ode.setGdsName(odd.getGdsName());
            ode.setOrderId(odd.getOrderId());
            ode.setOrderSubId(odd.getOrderSubId());
            ode.setSendDate(odd.getSendDate());
            ode.setShopId(odd.getShopId());
            ode.setSkuId(odd.getSkuId());
            ode.setSkuInfo(odd.getSkuInfo());
            ode.setStaffId(odd.getStaffId());

            ode.setCreateStaff(odd.getCreateStaff());
            ode.setCreateTime(new Timestamp(System.currentTimeMillis()));
            ode.setUpdateStaff(odd.getUpdateStaff());
            ode.setUpdateTime(ode.getCreateTime());

            odes.add(ode);
        }
        /* 新增T_ORD_DELIVERY_ENTITY-发货批次实体表 */
        this.ordDeliveryEntitySV.saveOrdDeliveryEntityList(odes);
        /* 删除T_ORD_ENTITY_IMPORT--实体编号录入中间表中该批次数据 */
        this.ordEntityImportSV.deleteByOrderId((SBaseSplitInfo) sBaseAndBatchInfo);
    }

    /**
     * updateOrderSubInfo:更新子订单发货数量，剩余发货数量. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void updateOrderSubInfo(SBaseAndStatusInfo sOrderStatusInfo,
            List<RConfirmSubInfo> rConfirmSubInfos) {
        this.ordSubSV.updateOrderSubDeliveryInfo(sOrderStatusInfo, rConfirmSubInfos);
    }

    /**
     * updateOrderInfo:更新订单状态 包含索引表的状态. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void updateOrderInfo(SBaseAndStatusInfo sOrderStatusInfo) {
        // 更新主订单表状态
        this.ordMainSV.updateOrderStatus(sOrderStatusInfo);
    }

    /**
     * delOrderTrack:生成订单跟踪表信息. <br/>
     * 
     * @author cbl
     * @since JDK 1.6
     */
    private void dealOrderTrack(RConfirmDeliveRequest rConfirmDeliveRequest) {
        OrdTrack ot = new OrdTrack();
        if (rConfirmDeliveRequest.getIsSendAll()) {
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_SEND_ALL);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_SEND_ALL);
        } else {
            ot.setNode(OrdConstants.OrderTwoStatus.STATUS_SEND_SECTION);
            ot.setNodeDesc(OrdConstants.NodeDesc.STATUS_SEND_SECTION);
        }

        ot.setOrderId(rConfirmDeliveRequest.getOrderId());
        ot.setCreateStaff(rConfirmDeliveRequest.getStaff().getId());
        ot.setCreateTime(DateUtil.getSysDate());
        this.ordTrackSV.saveOrdTrack(ot);
    }

    @Override
    public List<SOrderDetailsDelivery> queryExpressInfoByOrderId(String orderId) {
        OrdDeliveryBatchCriteria cra = new OrdDeliveryBatchCriteria();
        cra.createCriteria().andOrderIdEqualTo(orderId);
        List<OrdDeliveryBatch> ordDeliveryBatchs = this.ordDeliveryBatchMapper.selectByExample(cra);
        List<SOrderDetailsDelivery>  SOrderDetailsDeliverys = new ArrayList<SOrderDetailsDelivery>();
        if(ordDeliveryBatchs!=null){
	        for(OrdDeliveryBatch ordDeliveryBatch:ordDeliveryBatchs){
	            SOrderDetailsDelivery sOrderDetailsDelivery = new SOrderDetailsDelivery();
	            ObjectCopyUtil.copyObjValue(ordDeliveryBatch, sOrderDetailsDelivery, null, false);
	            if(sOrderDetailsDelivery.getExpressId() != null){
	                BaseExpressReqDTO baseExpressReqDTO = new BaseExpressReqDTO();
	                baseExpressReqDTO.setId(sOrderDetailsDelivery.getExpressId());
	                BaseExpressRespDTO baseExpressRespDTO = this.baseExpressRSV.fetchExpressInfo(baseExpressReqDTO);
	                sOrderDetailsDelivery.setExpressName(baseExpressRespDTO.getExpressName());
	            }
	            SOrderDetailsDeliverys.add(sOrderDetailsDelivery);
	        }
        }
        return SOrderDetailsDeliverys;
    }

    private List<SOrderDetailsSub> queryDetailsSubBySended(String orderId){
        
        List<OrdDeliveryDetails> OrdDelivery = this.ordDeliveryDetailsSV.findByOrderId(orderId);
        
        List<SOrderDetailsSub> sods = new ArrayList<SOrderDetailsSub>();
        for(OrdDeliveryDetails ordDeliveryDetails:OrdDelivery){
            SOrderDetailsSub sod = new SOrderDetailsSub();
            SBaseAndSubInfo sBaseAndSubInfo = new SBaseAndSubInfo();
            sBaseAndSubInfo.setOrderSubId(ordDeliveryDetails.getOrderSubId());
            sBaseAndSubInfo.setOrderId(ordDeliveryDetails.getOrderId());
            OrdSub os = this.ordSubSV.findByOrderSubId(sBaseAndSubInfo);
            ObjectCopyUtil.copyObjValue(os, sod, null, false);
            ObjectCopyUtil.copyObjValue(ordDeliveryDetails, sod, null, false);
            sod.setOrderAmount(ordDeliveryDetails.getDeliveryAmount());
            sod.setDiscountMoney((sod.getDiscountPrice() == null ?0l : sod.getDiscountPrice()) * sod.getOrderAmount());
            sods.add(sod);
        }
        return sods;
    }
    
    @Override
    public RDeliveryPrintResponse queryInvoiceInfo(RDeliveryPrintRequest rDeliveryPrintRequest) {
        RDeliveryPrintResponse rDeliveryPrintResponse = new RDeliveryPrintResponse();
        // 主订单相关字段
        SOrderDetailsMain sOrderDetailsMain = this.ordMainSV.queryOrderDetailsMain(rDeliveryPrintRequest.getOrderId());
        // 子订单相关字段
        List<SOrderDetailsSub> sOrderDetailsSubs = queryDetailsSubBySended(rDeliveryPrintRequest.getOrderId());
        // 订单普通发票相关字段
        SOrderDetailsComm sOrderDetailsComm = this.ordInvoiceCommSV.queryOrderDetailsComm(rDeliveryPrintRequest.getOrderId());
        // 订单增值税发票相关字段
        SOrderDetailsTax sOrderDetailsTax = this.ordInvoiceTaxSV.queryOrderDetailsTax(rDeliveryPrintRequest.getOrderId());
        // 订单收货地址相关字段
//        SOrderDetailsAddr sOrderDetailsAddr = this.ordDeliveAddrSV.queryOrderDetailsAddr(rDeliveryPrintRequest.getOrderId());
        rDeliveryPrintResponse.setsOrderDetailsMain(sOrderDetailsMain);
        rDeliveryPrintResponse.setsOrderDetailsSubs(sOrderDetailsSubs);
//        rDeliveryPrintResponse.setsOrderDetailsAddr(sOrderDetailsAddr);
        rDeliveryPrintResponse.setsOrderDetailsComm(sOrderDetailsComm);
        rDeliveryPrintResponse.setsOrderDetailsTax(sOrderDetailsTax);
        
        return rDeliveryPrintResponse;
    }

	@Override
	public List<OrdDeliveryBatch> queryExpressInfoByexpressNo(String expressNo) {
		// TODO Auto-generated method stub
		 OrdDeliveryBatchCriteria cra = new OrdDeliveryBatchCriteria();
	        cra.createCriteria().andExpressNoEqualTo(expressNo);
	        cra.setOrderByClause(" CREATE_TIME desc");
	        List<OrdDeliveryBatch> ordDeliveryBatchs = this.ordDeliveryBatchMapper.selectByExample(cra);	        
	        return ordDeliveryBatchs;
	}
}
