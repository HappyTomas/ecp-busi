/**
 * 
 */
package com.zengshi.ecp.order.service.busi.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

import javax.annotation.Resource;

import com.zengshi.ecp.order.dao.model.*;
import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.order.service.busi.interfaces.*;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.db.distribute.DistributeRuleAssist;
import org.apache.bcel.classfile.Constant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
 
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubShopIdxMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubStaffIdxMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdSubShopIdxUalMapper;
import com.zengshi.ecp.order.dao.mapper.busi.manual.OrdSubUalMapper;
import com.zengshi.ecp.order.dao.model.OrdSubCriteria.Criteria;
import com.zengshi.ecp.order.dubbo.util.DelyConstants;
import com.zengshi.ecp.order.dubbo.util.DelyConstants.DeliverStatus;
import com.zengshi.ecp.order.dubbo.util.LongUtils;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.dubbo.util.OrderUtils;
import com.zengshi.ecp.prom.dubbo.dto.CartPromItemDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IStaffUnionRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-order <br>
 * Description: <br>
 * Date:2015年8月10日下午2:44:54 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linwei
 * @version
 * @since JDK 1.6
 */
@Service("ordSubSV")
public class OrdSubSVImpl extends GeneralSQLSVImpl implements IOrdSubSV {

    @Resource
    private OrdSubMapper orderSubMapper;
    
    @Resource
    private OrdSubShopIdxMapper ordSubShopIdxMapper;
    
    @Resource
    private OrdSubStaffIdxMapper ordSubStaffIdxMapper;

    @Resource
    private OrdSubUalMapper ordSubUalMapper;
    
//    @Resource
//    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    
    @Resource
    private IOrdSubShopIdxSV ordSubShopIdxSV;
    
    @Resource
    private IOrdSubStaffIdxSV ordSubStaffIdxSV;
    
    @Resource
    private IGdsCategoryRSV gdsCategoryRSV;

    @Resource(name = "seq_ord_sub")
    private Sequence seqOrdSub;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IOrdMainSV ordMainSV;

    @Resource
    private IOrdSubSV ordSubSV;

    @Resource
    private IStaffUnionRSV staffUnionRSV;

    @Resource
    private IOrdPromSV ordPromSV;

    @Resource
    private IOrdPresentSV ordPresentSV;

    @Resource
    private IOrdDiscountSV ordDiscountSV;

    @Resource
    private IOrdFileImportSV ordFileImportSV;
    
    @Resource
    private IGdsSkuInfoQueryRSV iGdsSkuInfoQueryRSV;
    
    @Resource
    private OrdSubShopIdxUalMapper ordSubShopIdxUalMapper;


    private static final String MODULE = OrdSubSVImpl.class.getName();

    @Override
    public void saveOrdSub(OrdSub ordSub,String payType) {
        this.orderSubMapper.insert(ordSub);
        OrdSubShopIdx ordSubShopIdx=new OrdSubShopIdx();
        ObjectCopyUtil.copyObjValue(ordSub,ordSubShopIdx,null,false);
        ordSubShopIdx.setOrderSubId(ordSub.getId());
        ordSubShopIdx.setPayType(payType);
        ordSubShopIdx.setBasePrice(ordSub.getBasePrice());
        ordSubShopIdx.setRealMoney(ordSub.getRealMoney());
        ordSubShopIdx.setGdsName(ordSub.getGdsName());
        ordSubShopIdx.setGdsType(ordSub.getGdsType());
        ordSubShopIdx.setCategoryCode(ordSub.getCategoryCode());
        this.ordSubShopIdxSV.saveOrdSubShopIdx(ordSubShopIdx);
        OrdSubStaffIdx OrdSubStaffIdx=new OrdSubStaffIdx();
        ObjectCopyUtil.copyObjValue(ordSub,OrdSubStaffIdx,null,false);
        OrdSubStaffIdx.setOrderSubId(ordSub.getId());
        this.ordSubStaffIdxSV.saveOrdSubStaffIdx(OrdSubStaffIdx);           
    }

    @Override
    public List<OrdSub> queryOrderSubByOrderId(String orderID) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(orderID);
        return this.orderSubMapper.selectByExample(osc);
    }

    @Override
    public List<RShowOrdSubResponse> listOrderSubInfoByOrderId(
            RShowOrdSubRequest sListOrderSubRequest) {
        List<RShowOrdSubResponse> slosrs = new ArrayList<RShowOrdSubResponse>();
        /* 根据订单号查询子订单明细列表 */
        List<OrdSub> oss = queryOrderSubByOrderId(sListOrderSubRequest.getOrderId());
        RShowOrdSubResponse slosr = new RShowOrdSubResponse();
        for (OrdSub os : oss) {
            slosr.setGdsName(os.getGdsName());
            slosr.setOrderMoney(os.getOrderMoney());
            slosr.setOrderSubId(os.getId());
            slosr.setRemainAmount(os.getRemainAmount());
            slosr.setSkuInfo(os.getSkuInfo());

            slosrs.add(slosr);
        }
        return slosrs;
    }

    @Override
    public void updateOrderSubDeliveryInfo(SBaseAndStatusInfo sBaseAndStatusInfo,
            List<RConfirmSubInfo> rConfirmSubInfos) {
        // 剩余未发货数量
        Long remainAmount = 0l;
        // 已提货数量
        Long deliverAmount = 0l;

        for (RConfirmSubInfo rcsi : rConfirmSubInfos) {

            OrdSubCriteria osc = new OrdSubCriteria();
            osc.createCriteria().andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId())
                    .andIdEqualTo(rcsi.getOrderSubId());

            SBaseAndSubInfo soaosi = new SBaseAndSubInfo();
            soaosi.setOrderId(sBaseAndStatusInfo.getOrderId());
            soaosi.setOrderSubId(rcsi.getOrderSubId());
            OrdSub os = findByOrderSubId(soaosi);
            if (os == null) {
                LogUtil.info(MODULE, "未找到[" + soaosi.getOrderId() + "]该订单的子订单信息");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
            }
            deliverAmount = os.getDeliverAmount() + rcsi.getDeliveryAmount();

            remainAmount = os.getRemainAmount() - rcsi.getDeliveryAmount();
            if (remainAmount < 0) {
                LogUtil.debug(MODULE, "剩余发货数量小0");
                throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
            }
            os.setDeliverAmount(deliverAmount); // 更新已发货数量
            os.setRemainAmount(remainAmount); // 更新剩余未发货数量
            os.setStatus(sBaseAndStatusInfo.getStatus()); // 更新订单状态
            os.setUpdateStaff(sBaseAndStatusInfo.getOperatorId());
            os.setUpdateTime(DateUtil.getSysDate());
            if (remainAmount == 0) { // 更新子订单发货状态状态
                os.setDeliverStatus(DelyConstants.DeliverStatus.DELIVER_STATUS_TRUE); // 该子订单已全部发货
            }
            this.orderSubMapper.updateByExampleSelective(os, osc);
        }

    }

    @Override
    public OrdSub findByOrderSubId(SBaseAndSubInfo sBaseAndSubInfo) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(sBaseAndSubInfo.getOrderId())
                .andIdEqualTo(sBaseAndSubInfo.getOrderSubId());
        List<OrdSub> ordSub = this.orderSubMapper.selectByExample(osc);
        if(CollectionUtils.isEmpty(ordSub)){
            return null;
        }
        return ordSub.get(0);
    }

    @Override
    public PageResponseDTO<RShowOrdSubResponse> queryOrderByOrderIdPage(
            RShowOrdSubRequest rShowOrdSubRequest) {
        final OrdSubCriteria osc = new OrdSubCriteria();
        osc.setLimitClauseCount(rShowOrdSubRequest.getPageSize());
        osc.setLimitClauseStart(rShowOrdSubRequest.getStartRowIndex());
        osc.createCriteria().andOrderIdEqualTo(rShowOrdSubRequest.getOrderId())
                .andDeliverStatusEqualTo(DelyConstants.DeliverStatus.DELIVER_STATUS_FLASE);
        osc.setOrderByClause("order_id asc");
        return super.queryByPagination(rShowOrdSubRequest, osc, true,
                new PaginationCallback<OrdSub, RShowOrdSubResponse>() {

                    @Override
                    public List<OrdSub> queryDB(BaseCriteria bCriteria) {
                        return orderSubMapper.selectByExample((OrdSubCriteria) bCriteria);
                    }

                    @Override
                    public long queryTotal(BaseCriteria bCriteria) {
                        return orderSubMapper.countByExample((OrdSubCriteria) bCriteria);
                    }

                    @Override
                    public List<Comparator<OrdSub>> defineComparators() {
                        List<Comparator<OrdSub>> ls = new ArrayList<Comparator<OrdSub>>();
                        ls.add(new Comparator<OrdSub>() {

                            @Override
                            public int compare(OrdSub o1, OrdSub o2) {
                                return o1.getOrderId().compareTo(o2.getOrderId());
                            }

                        });
                        return ls;
                    }

                    @Override
                    public RShowOrdSubResponse warpReturnObject(OrdSub ordSub) {
                        RShowOrdSubResponse sdoi = new RShowOrdSubResponse();
                        sdoi.setOrderSubId(ordSub.getId());
                        BeanUtils.copyProperties(ordSub, sdoi);
                        GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
                        gdsInfoReqDTO.setId(ordSub.getSkuId());
                        gdsInfoReqDTO.setGdsId(ordSub.getGdsId());
                        List<Long> propIds = new ArrayList<Long>();
                        propIds.add(1004l);
                        propIds.add(1050l);
                        gdsInfoReqDTO.setPropIds(propIds);
                        gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[]{GdsOption.SkuQueryOption.BASIC,
                                GdsOption.SkuQueryOption.MAINPIC, GdsOption.SkuQueryOption.PROP});
                        GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                                .querySkuInfoByOptions(gdsInfoReqDTO);

                        if (gdsInfoRespDTO != null) {
                            sdoi.setIsbn(gdsInfoRespDTO.getIsbn());
                            sdoi.setTxCode(OrderUtils.getTxCode(gdsInfoRespDTO)); //条形码
                            sdoi.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO)); //5位
                            sdoi.setGdsUrl(gdsInfoRespDTO.getUrl());
                        }
                        if (gdsInfoRespDTO.getMainPic() != null && gdsInfoRespDTO.getMainPic().getMediaUuid() != null) {
                            sdoi.setImageUrl(OrderUtils.getImageUrl(gdsInfoRespDTO.getMainPic().getMediaUuid(), "100x150!"));
                        }
                        
                        return sdoi;
                    }
                });
    }
     
    @Override
    public Long queryCountByOrdSubId(OrdEntityImportGroup ordEntityImportGroup) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(ordEntityImportGroup.getOrderId())
                .andIdEqualTo(ordEntityImportGroup.getOrderSubId());
        return this.orderSubMapper.countByExample(osc);
    }

    @Override
    public void updateStatusByOrderId(SBaseAndStatusInfo sBaseAndStatusInfo) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdSub os = new OrdSub();
        os.setStatus(sBaseAndStatusInfo.getStatus());
        os.setUpdateTime(DateUtil.getSysDate());
        os.setUpdateStaff(sBaseAndStatusInfo.getOperatorId());
        this.orderSubMapper.updateByExampleSelective(os, osc);
        
        this.ordSubShopIdxSV.updateOrderStatus(sBaseAndStatusInfo);
        
        this.ordSubStaffIdxSV.updateOrderStatus(sBaseAndStatusInfo);
    }
    
    @Override
    public void updateStatusInfo(RPreOrdSubResponse ordsub,SBaseAndStatusInfo sBaseAndStatusInfo) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andIdEqualTo(ordsub.getOrdSubId()).andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdSub os = new OrdSub();
        os.setDeliverAmount(ordsub.getOrderAmount());
        os.setRemainAmount(0L);
        os.setDeliverStatus(DeliverStatus.DELIVER_STATUS_TRUE);
        os.setStatus(sBaseAndStatusInfo.getStatus());
        os.setUpdateTime(DateUtil.getSysDate());
        os.setUpdateStaff(sBaseAndStatusInfo.getOperatorId()); 
        this.orderSubMapper.updateByExampleSelective(os, osc);
        
        OrdSubShopIdxCriteria osShopIdxc = new OrdSubShopIdxCriteria();
        osShopIdxc.createCriteria().andOrderSubIdEqualTo(ordsub.getOrdSubId()).andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdSubShopIdx osShopIdx = new OrdSubShopIdx();
        osShopIdx.setDeliverAmount(ordsub.getOrderAmount());
        osShopIdx.setRemainAmount(0L);
        osShopIdx.setDeliverStatus(DeliverStatus.DELIVER_STATUS_TRUE);
        osShopIdx.setStatus(sBaseAndStatusInfo.getStatus());
        osShopIdx.setUpdateTime(DateUtil.getSysDate());
        osShopIdx.setUpdateStaff(sBaseAndStatusInfo.getOperatorId()); 
        this.ordSubShopIdxMapper.updateByExampleSelective(osShopIdx, osShopIdxc);
        
        OrdSubStaffIdxCriteria osStaffIdxc = new OrdSubStaffIdxCriteria(); 
        osStaffIdxc.createCriteria().andOrderSubIdEqualTo(ordsub.getOrdSubId()).andOrderIdEqualTo(sBaseAndStatusInfo.getOrderId());
        OrdSubStaffIdx osStaffIdx = new OrdSubStaffIdx();
        osStaffIdx.setDeliverAmount(ordsub.getOrderAmount());
        osStaffIdx.setRemainAmount(0L);
        osStaffIdx.setDeliverStatus(DeliverStatus.DELIVER_STATUS_TRUE);
        osStaffIdx.setStatus(sBaseAndStatusInfo.getStatus());
        osStaffIdx.setUpdateTime(DateUtil.getSysDate());
        osStaffIdx.setUpdateStaff(sBaseAndStatusInfo.getOperatorId()); 
        this.ordSubStaffIdxMapper.updateByExampleSelective(osStaffIdx, osStaffIdxc);
        
    }

    @Override
    public void saveOrdSubInfo(RSumbitSubRequest rSumbitSubRequest,RSumbitMainsRequest rSumbitMainsRequest,ROrdMainResponse rOrdMainResponse) {
        OrdSub ordSub = new OrdSub();
        ordSub.setId(rSumbitSubRequest.getOrderSubId());
        ordSub.setOrderId(rSumbitSubRequest.getOrderId());
        ordSub.setOrderCode(rSumbitSubRequest.getOrderId());
        ordSub.setGdsId(rSumbitSubRequest.getGdsId());
        ordSub.setGdsName(rSumbitSubRequest.getGdsName());
        ordSub.setSkuId(rSumbitSubRequest.getSkuId());
        ordSub.setStatus(OrdConstants.OrderStatus.ORDER_STATUS_SUBMIT);
        ordSub.setSkuInfo(rSumbitSubRequest.getSkuInfo());
        ordSub.setOrderAmount(rSumbitSubRequest.getOrderAmount());
        ordSub.setGroupType(rSumbitSubRequest.getGroupType());
        ordSub.setGroupDetail(rSumbitSubRequest.getGroupDetail());
        
        ordSub.setBasePrice(rSumbitSubRequest.getBasePrice());
        ordSub.setBuyPrice(rSumbitSubRequest.getBuyPrice());

        ordSub.setCompanyName(rOrdMainResponse.getCompanyName());
        ordSub.setCompanyId(rOrdMainResponse.getCompanyId());
        ordSub.setStaffName(rOrdMainResponse.getStaffName());
        ordSub.setStaffCode(rOrdMainResponse.getStaffCode());
        ordSub.setStartTime(rOrdMainResponse.getStartTime());
        ordSub.setEndTime(rOrdMainResponse.getEndTime());
        ordSub.setOrderType(rOrdMainResponse.getOrderType());
        ordSub.setShopName(rOrdMainResponse.getShopName());
        ordSub.setSource(rOrdMainResponse.getSource());

        //价格改造
//        ordSub.setStandardPrice(rSumbitSubRequest.getStandardPrice());
        
        // 子订单级别优惠
        Map<Long, CartPromItemDTO> ordSubMap = null;
        CartPromItemDTO cpid  = null;
        if(rSumbitMainsRequest.getCartPromRespDTO() != null){
            ordSubMap = rSumbitMainsRequest.getCartPromRespDTO().getCartPromItemDTOMap();
            if(ordSubMap != null ){
                cpid = ordSubMap.get(rSumbitSubRequest.getCartItemId());
            }
        }
        if (cpid != null && cpid.isIfFulfillProm()){
            
            ordSub.setDiscountPrice(cpid.getDiscountFinalPrice().longValue());
            ordSub.setReduceMoney(cpid.getDiscountPriceMoney().longValue()+cpid.getDiscountMoney().longValue());
//            ordSub.setDiscountMoney(cpid.getDiscountFinalPrice().longValue()*rSumbitSubRequest.getOrderAmount() - cpid.getDiscountPriceMoney().longValue());
            ordSub.setDiscountMoney(cpid.getDiscountCaclPrice().longValue()*rSumbitSubRequest.getOrderAmount() - ordSub.getReduceMoney().longValue());
            ordSub.setRealMoney(ordSub.getDiscountMoney());
          //价格改造
            ordSub.setStandardPrice(cpid.getDiscountCaclPrice().longValue());
        } else {
            ordSub.setDiscountPrice(rSumbitSubRequest.getBuyPrice());
            ordSub.setDiscountMoney(rSumbitSubRequest.getBuyPrice()*rSumbitSubRequest.getOrderAmount());
            ordSub.setReduceMoney((rSumbitSubRequest.getBasePrice()-rSumbitSubRequest.getBuyPrice())*rSumbitSubRequest.getOrderAmount());
            ordSub.setRealMoney(ordSub.getDiscountMoney());
            ordSub.setStandardPrice(rSumbitSubRequest.getBasePrice());
        }
        ordSub.setOrderMoney(ordSub.getDiscountPrice()*ordSub.getOrderAmount());        
        
        ordSub.setDeliverStatus("0");
        ordSub.setDeliverAmount(0l);
        ordSub.setOrderTime(DateUtil.getSysDate());
        ordSub.setStaffId(rSumbitSubRequest.getStaffId());
        ordSub.setShopId(rSumbitSubRequest.getShopId());
        ordSub.setCategoryCode(rSumbitSubRequest.getCategoryCode());
        ordSub.setSysType(OrdConstants.SysType.SYS_TYPE_BASE);
        ordSub.setSiteId(rSumbitSubRequest.getCurrentSiteId());
        ordSub.setEvalFlag(OrdConstants.Common.COMMON_INVALID);
        ordSub.setGdsType(rSumbitSubRequest.getGdsType());
        ordSub.setRemainAmount(rSumbitSubRequest.getOrderAmount());
        ordSub.setCreateTime((DateUtil.getSysDate()));
        ordSub.setCreateStaff(rSumbitSubRequest.getStaff().getId());
        ordSub.setUpdateStaff(rSumbitSubRequest.getStaff().getId());
        ordSub.setUpdateTime(DateUtil.getSysDate());
        ordSub.setRepCode(rSumbitSubRequest.getRepCode());
        ordSub.setStockId(rSumbitSubRequest.getStockId());
        ordSub.setScore(rSumbitSubRequest.getScore());
        ordSub.setOrderSubScore(rSumbitSubRequest.getOrderScore());
        ordSub.setPrnFlag(rSumbitSubRequest.getPrnFlag());
        this.saveOrdSub(ordSub,rSumbitMainsRequest.getPayType());
    }

    @Override
    public List<SOrderDetailsSub> queryOrderDetailsSub(String orderId) {

        List<OrdSub> oss = queryOrderSubByOrderId(orderId);
        if (CollectionUtils.isEmpty(oss)) {
            LogUtil.info(MODULE, "未找到[" + orderId + "]该订单的子订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        List<SOrderDetailsSub> sods = new ArrayList<SOrderDetailsSub>();
        for (OrdSub os : oss) {
            SOrderDetailsSub sod = new SOrderDetailsSub();
            ObjectCopyUtil.copyObjValue(os, sod, null, false);
            sod.setOrderSubId(os.getId());
            // 补充页面展示明细信息----调用商品信息的接口，获取商品的图片，URL
//            GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
//            gdsInfoReqDTO.setId(os.getGdsId());
//            gdsInfoReqDTO.setGdsQueryOptions(new GdsQueryOption[] { GdsQueryOption.BASIC,
//                    GdsQueryOption.MAINPIC });
//            GdsInfoRespDTO gdsInfoRespDTO = gdsInfoQueryRSV.queryGdsInfoByOption(gdsInfoReqDTO);
            GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
            gdsInfoReqDTO.setId(os.getSkuId());
            gdsInfoReqDTO.setGdsId(os.getGdsId());
            List<Long> propIds = new ArrayList<Long>();
            propIds.add(1004l);
            propIds.add(1050l);
            gdsInfoReqDTO.setPropIds(propIds);
            gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] { GdsOption.SkuQueryOption.BASIC,
                    GdsOption.SkuQueryOption.MAINPIC,GdsOption.SkuQueryOption.PROP});
            GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                    .querySkuInfoByOptions(gdsInfoReqDTO);
            if(gdsInfoRespDTO != null) {
                if (gdsInfoRespDTO.getMainPic() != null && gdsInfoRespDTO.getMainPic().getURL() != null) {
                    sod.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                    sod.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
                }
                if (gdsInfoRespDTO.getUrl() != null)
                    sod.setGdsUrl(gdsInfoRespDTO.getUrl());
                sod.setIsbn(gdsInfoRespDTO.getIsbn());
                sod.setTxCode(OrderUtils.getTxCode(gdsInfoRespDTO)); //条形码
                sod.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO));
            }
            String skuInfo="";
            if(!StringUtil.isBlank(sod.getSkuInfo())){
                skuInfo=sod.getSkuInfo();
            }
            //增加对商品属性展示的优化，加上商品的2级分类
            GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
            categoryDTO.setCatgCode(os.getCategoryCode());
            List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
            if(CollectionUtils.isNotEmpty(categoryRespList)){
                if(categoryRespList.size()>1){
                    GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                    GdsCategoryRespDTO categoryRes2=categoryRespList.get(1);    
                    //sod.setSkuInfo(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName()+"    "+sod.getSkuInfo());   
                    sod.setGdsCateName(categoryRes1.getCatgName()+"-"+categoryRes2.getCatgName());
                }else{
                    GdsCategoryRespDTO categoryRes1=categoryRespList.get(0);
                    //sod.setSkuInfo(categoryRes1.getCatgName()+"    "+sod.getSkuInfo());      
                    sod.setGdsCateName(categoryRes1.getCatgName());
                }
            }

            LogUtil.info(MODULE, JSON.toJSONString(sod));
            sods.add(sod);
        }
        return sods;
    }

    @Override
    public List<SReceiptInfo> queryReceiptInfo(ROrdReceiptRequest rOrdReceiptRequest) {

        List<OrdSub> oss = queryOrderSubByOrderId(rOrdReceiptRequest.getOrderId());
        if (CollectionUtils.isEmpty(oss)) {
            LogUtil.info(MODULE, "未找到[" + rOrdReceiptRequest.getOrderId() + "]该订单的子订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        List<SReceiptInfo> sris = new ArrayList<SReceiptInfo>();
        for (OrdSub os : oss) {
            SReceiptInfo sri = new SReceiptInfo();
            ObjectCopyUtil.copyObjValue(os, sri, null, false);
            sri.setOrderSubId(os.getId());
            LogUtil.info(MODULE, sri.toString());
            sris.add(sri);
        }
        return sris;
    }

    @Override
    public Long queryCountRemainAmount(String orderId) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(orderId);
        return this.ordSubUalMapper.countRemainAmountByExample(osc);
    }
    
    @Override
    public Long querySumDiscountMoney(String orderId) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(orderId);
        return this.ordSubUalMapper.sumDiscountMoneyByExample(osc);
    }    
    /*
     * 生成子订单的方法
     */
    public String createOrdSubId(){
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        return OrdConstants.Common.SRW_CODE+nowDate+StringUtil.lPad(seqOrdSub.nextValue().toString(), "0", 8);
    }

    @Override
    public List<RPreOrdSubResponse> findOrdSubByOrderId(String orderId) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(orderId);
        List<OrdSub> ordSubs=this.orderSubMapper.selectByExample(osc);
        if(CollectionUtils.isEmpty(ordSubs)){
            return null;
        }
        List<RPreOrdSubResponse> rPreOrdSubResponses=new ArrayList<RPreOrdSubResponse>();
        for(OrdSub ordSub:ordSubs){
            RPreOrdSubResponse sod = new RPreOrdSubResponse();
            ObjectCopyUtil.copyObjValue(ordSub, sod, null, false); 
            sod.setOrdSubId(ordSub.getId());
            rPreOrdSubResponses.add(sod);
        }
       return rPreOrdSubResponses;
    }

    @Override
    public PageResponseDTO<ROrdEvaluationResponse> queryEvaluationWait(
            ROrdEvaluationRequest rOrdEvaluationRequest) {
        PageResponseDTO<ROrdEvaluationResponse> roer = null;
        roer = this.ordSubStaffIdxSV.querySubForEvaluationWait(rOrdEvaluationRequest);
        return roer;
    }
    @Override
    public Long queryEvaluationWaitCount(ROrdEvaluationRequest rOrdEvaluationRequest) {
        OrdSubCriteria osc = new OrdSubCriteria();
        Criteria ca = osc.createCriteria().andStaffIdEqualTo(rOrdEvaluationRequest.getStaffId())
                .andEvalFlagEqualTo(OrdConstants.Common.COMMON_INVALID)
                .andStatusEqualTo(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT)
                .andSiteIdEqualTo(1l);
        if(rOrdEvaluationRequest.getGdsId() != null && rOrdEvaluationRequest.getGdsId() > 0){
            ca.andGdsIdEqualTo(rOrdEvaluationRequest.getGdsId());
        }
        if(rOrdEvaluationRequest.getSkuId() != null && rOrdEvaluationRequest.getSkuId() > 0){
            ca.andSkuIdEqualTo(rOrdEvaluationRequest.getSkuId());
        }
        return this.ordSubUalMapper.countEvaluationWait(osc);
    }
    /** 
     * updateEvalFlag:更新已评价标识字段. <br/> 
     * @author cbl 
     * @param rOrdEvaluatedRequest 
     * @since JDK 1.6 
     */ 
    private void updateEvalFlag(ROrdEvaluatedRequest rOrdEvaluatedRequest) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(rOrdEvaluatedRequest.getOrderId())
                            .andIdEqualTo(rOrdEvaluatedRequest.getSubId());
        OrdSub os = new OrdSub();
//        os.setStatus(sBaseAndStatusInfo.getStatus());
        os.setEvalFlag(OrdConstants.Common.COMMON_VALID);
        this.orderSubMapper.updateByExampleSelective(os, osc);
        
        this.ordSubStaffIdxSV.updateEvalFlag(rOrdEvaluatedRequest);
       
    }
    @Override
    public void updateEvaluated(List<ROrdEvaluatedRequest> rOrdEvaluatedRequests) {
        
        for(ROrdEvaluatedRequest roer:rOrdEvaluatedRequests){
            updateEvalFlag(roer);
        }
    }

    @Override
    public PageResponseDTO<SOrderIdx> queryOrderByShopIdPage(final RQueryOrderRequest rQueryOrderRequest) {
        final OrdSubCriteria osc = new OrdSubCriteria();
        osc.setLimitClauseCount(rQueryOrderRequest.getPageSize());
        osc.setLimitClauseStart(rQueryOrderRequest.getStartRowIndex());
        osc.setOrderByClause("order_id desc");
        Criteria ca = osc.createCriteria().andShopIdEqualTo(rQueryOrderRequest.getShopId());
        if(StringUtil.isNotBlank(rQueryOrderRequest.getSysType())){
            ca.andSysTypeEqualTo(rQueryOrderRequest.getSysType());
        }
        if(rQueryOrderRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rQueryOrderRequest.getBegDate());
        }
        if(rQueryOrderRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rQueryOrderRequest.getEndDate());
        }
        if(rQueryOrderRequest.getSiteId()!= null){
            ca.andSiteIdEqualTo(rQueryOrderRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rQueryOrderRequest.getOrderId())){
            ca.andOrderIdEqualTo(rQueryOrderRequest.getOrderId());
        }
        if(rQueryOrderRequest.getStaffId() != null && rQueryOrderRequest.getStaffId() > 0){
            ca.andStaffIdEqualTo(rQueryOrderRequest.getStaffId());
        }
        
        List<String> status = new ArrayList<String>();
        if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){   //01-待发货
            osc.setOrderByClause("order_id asc");
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
            ca.andStatusIn(status);
            
        } else if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_DELY.equals(rQueryOrderRequest.getStatus())){ //02-已发货
            
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);  //已发货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_RECEPT); //已收货
            status.add(OrdConstants.OrderStatus.ORDER_STATUS_CLOSE); //关闭
            ca.andStatusIn(status);
            
        }  
        if(CollectionUtils.isNotEmpty(rQueryOrderRequest.getCategoryCodes())){
            ca.andCategoryCodeIn(rQueryOrderRequest.getCategoryCodes());
        }
        return super.queryByPagination(rQueryOrderRequest, osc, true, new PaginationCallback<OrdSubOrderIdUal, SOrderIdx>() {

            @Override
            public List<OrdSubOrderIdUal> queryDB(BaseCriteria bCriteria) {
                return ordSubUalMapper.selectOrderIdByExample((OrdSubCriteria)bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordSubUalMapper.countOrderIdByExample((OrdSubCriteria)bCriteria);
            }


            @Override
            public List<Comparator<OrdSubOrderIdUal>> defineComparators() {
                List<Comparator<OrdSubOrderIdUal>> ls = new ArrayList<Comparator<OrdSubOrderIdUal>>();
                ls.add(new Comparator<OrdSubOrderIdUal>(){

                    @Override
                    public int compare(OrdSubOrderIdUal o1, OrdSubOrderIdUal o2) {
                        if(OrdConstants.ShopRequestStatus.REQUEST_STATUS_SEND.equals(rQueryOrderRequest.getStatus())){
                            return o1.getOrderId().compareTo(o2.getOrderId());
                        }else {
                            return o2.getOrderId().compareTo(o1.getOrderId());
                        }
                        
                    }
                    
                });
                return ls;
            }

            @Override
            public SOrderIdx warpReturnObject(OrdSubOrderIdUal ordSub) {
                    SOrderIdx sdoi = new SOrderIdx();
                    BeanUtils.copyProperties(ordSub, sdoi);
                    return sdoi;
            }

            
        });
    }

    
    private List<RShowOrdSubResponse> queryByOrderSubId(List<SOrderSubIdx> sOrderSubIdxs){
        
        List<RShowOrdSubResponse> rcors = new ArrayList<RShowOrdSubResponse>();
        for (SOrderSubIdx soi : sOrderSubIdxs) {
            OrdSubCriteria osc = new OrdSubCriteria();
            osc.createCriteria().andIdEqualTo(soi.getOrderSubId());
            List<OrdSub> ordSubs=this.orderSubMapper.selectByExample(osc);
            if(CollectionUtils.isEmpty(ordSubs)){
                continue;
            }
            OrdSub os = ordSubs.get(0);
            RShowOrdSubResponse rShowOrdSubResponse = new RShowOrdSubResponse();
            ObjectCopyUtil.copyObjValue(os, rShowOrdSubResponse, null, false);
            rShowOrdSubResponse.setOrderSubId(os.getId());
            rcors.add(rShowOrdSubResponse);
        }
        return rcors;
    }
    @Override
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSubByStaffId(
            RQueryOrderRequest rQueryOrderRequest) {
        PageResponseDTO<RShowOrdSubResponse> pageResponse = PageResponseDTO
                .buildByBaseInfo(rQueryOrderRequest, RShowOrdSubResponse.class);
        pageResponse.setResult(new ArrayList<RShowOrdSubResponse>());

        PageResponseDTO<SOrderSubIdx> psoi = this.ordSubStaffIdxSV.queryOrderSubByStaffId(rQueryOrderRequest);
        pageResponse.setCount(psoi.getCount());
        pageResponse.setPageCount(psoi.getPageCount());
        if (CollectionUtils.isEmpty(psoi.getResult())) {
            LogUtil.info(MODULE, "未找到订单数据");
            pageResponse.setResult(null);
            return pageResponse;
            // throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312000);
        } else {
            List<RShowOrdSubResponse> rcor = queryByOrderSubId(psoi.getResult());
            pageResponse.getResult().addAll(rcor);
        }
        return pageResponse;
    }

    private PageResponseDTO<RGoodSaleResponse> queryOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest){
        final OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);

        return super.queryByPagination(rGoodSaleRequest, osc, true, new PaginationCallback<OrdSubShopIdx, RGoodSaleResponse>() {

            @Override
            public List<OrdSubShopIdx> queryDB(BaseCriteria bCriteria) {
                return ordSubShopIdxUalMapper.selectBySellGdsExample((OrdSubShopIdxCriteria) bCriteria) ;
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return ordSubShopIdxUalMapper.countBySellGdsExample((OrdSubShopIdxCriteria) bCriteria);
            }


            /*@Override
            public List<Comparator<OrdSubShopIdx>> defineComparators() {
                List<Comparator<OrdSubShopIdx>> ls = new ArrayList<Comparator<OrdSubShopIdx>>();
                ls.add(new Comparator<OrdSubShopIdx>() {

                    @Override
                    public int compare(OrdSubShopIdx o1, OrdSubShopIdx o2) {
                        return o1.getOrderSubId().compareTo(o2.getOrderSubId());
                    }

                });
                return ls;
            }*/

            @Override
            public RGoodSaleResponse warpReturnObject(OrdSubShopIdx ordSubShopIdx) {

                return getGoodSale(ordSubShopIdx);
            }

        });
    }


    private PageResponseDTO<RGoodSaleResponse> queryOrdSub(RGoodSaleRequest rGoodSaleRequest){
        final OrdSubCriteria osc = saleSubReqCriteria(rGoodSaleRequest);

        return super.queryByPagination(rGoodSaleRequest, osc, true, new PaginationCallback<OrdSub, RGoodSaleResponse>() {

            @Override
            public List<OrdSub> queryDB(BaseCriteria bCriteria) {
                return orderSubMapper.selectByExample((OrdSubCriteria) bCriteria);
            }

            @Override
            public long queryTotal(BaseCriteria bCriteria) {
                return orderSubMapper.countByExample((OrdSubCriteria) bCriteria);
            }


            /*@Override
            public List<Comparator<OrdSub>> defineComparators() {
                List<Comparator<OrdSub>> ls = new ArrayList<Comparator<OrdSub>>();
                ls.add(new Comparator<OrdSub>() {

                    @Override
                    public int compare(OrdSub o1, OrdSub o2) {
                        return o1.getId().compareTo(o2.getId());
                    }

                });
                return ls;
            }*/

            @Override
            public RGoodSaleResponse warpReturnObject(OrdSub ordSub) {

                return getGoodSale(ordSub);
            }

        });
    }

    @Override
    public PageResponseDTO<RGoodSaleResponse> queryGoodSaleInfo(final RGoodSaleRequest rGoodSaleRequest) {
       /* if(LongUtils.isNotEmpty(rGoodSaleRequest.getGdsType()) || StringUtil.isNotBlank(rGoodSaleRequest.getGdsName())){
            return queryOrdSub(rGoodSaleRequest);
        }*/
        return queryOrdSubShopIdx(rGoodSaleRequest);
    }
    
    @Override
    public List<RGoodSaleResponse> queryGoodSaleExport(RGoodSaleRequest rGoodSaleRequest) {
        /*if(LongUtils.isNotEmpty(rGoodSaleRequest.getGdsType()) || StringUtil.isNotBlank(rGoodSaleRequest.getGdsName())){
            return queryOrdSubExport(rGoodSaleRequest);
        }*/
        return queryOrdSubShopIdxExport(rGoodSaleRequest);
    }
    



    private List<RGoodSaleResponse> queryOrdSubShopIdxExport(RGoodSaleRequest rGoodSaleRequest) {
        final OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);

        List<OrdSubShopIdx> subShopIdxes = ordSubShopIdxUalMapper.selectBySellGdsExample(osc);

        final List<RGoodSaleResponse> goodSaleResponses = new ArrayList<>();

        for(final OrdSubShopIdx ordSubShopIdx: subShopIdxes){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    goodSaleResponses.add(getGoodSale(ordSubShopIdx));
                }
            });
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException e){
                LogUtil.info(MODULE, e.getMessage(),e);
            }
        }

        return goodSaleResponses;
    }

    private List<RGoodSaleResponse> queryOrdSubExport(RGoodSaleRequest rGoodSaleRequest) {
        final OrdSubCriteria osc = saleSubReqCriteria(rGoodSaleRequest);

        List<OrdSub> ordSubs = orderSubMapper.selectByExample(osc);

        final List<RGoodSaleResponse> goodSaleResponses = new ArrayList<>();

        for(final OrdSub ordSub: ordSubs){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    goodSaleResponses.add(getGoodSale(ordSub));
                }
            });
            thread.start();
            try{
                thread.join();
            }catch (InterruptedException e){
                LogUtil.info(MODULE, e.getMessage(),e);
            }
        }

        return goodSaleResponses;
    }

    @Override
    public RGoodSaleSumResp queryGoodSaleSum(RGoodSaleRequest rGoodSaleRequest) {
        RGoodSaleSumResp rGoodSaleSumResp = new RGoodSaleSumResp();

        Long orderNum = this.queryOrderCount(rGoodSaleRequest);
        rGoodSaleSumResp.setOrderNum(orderNum);

        Long saleNum = this.querySaleNum(rGoodSaleRequest);
        rGoodSaleSumResp.setSaleNum(saleNum);

        Long realMoney = this.sumDiscountMoney(rGoodSaleRequest);
        //查下该条件下已进行退款退货的
        
        rGoodSaleSumResp.setRealMoney(realMoney);

        Long basicMoney = this.sumBasicMoney(rGoodSaleRequest);
        rGoodSaleSumResp.setBasicMoney(basicMoney);

        return rGoodSaleSumResp;
    }


    private OrdSubCriteria saleSubReqCriteria(RGoodSaleRequest rGoodSaleRequest){

        OrdSubCriteria osc = new OrdSubCriteria();
        osc.setLimitClauseCount(rGoodSaleRequest.getPageSize());
        osc.setLimitClauseStart(rGoodSaleRequest.getStartRowIndex());
        osc.setOrderByClause("order_time desc");

        OrdSubCriteria.Criteria ca = osc.createCriteria();
        if(rGoodSaleRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rGoodSaleRequest.getBegDate());
        }
        if(rGoodSaleRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rGoodSaleRequest.getEndDate());
        }
        if(rGoodSaleRequest.getSiteId()!= null){
            ca.andSiteIdEqualTo(rGoodSaleRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getOrderId())){
            ca.andOrderIdEqualTo(rGoodSaleRequest.getOrderId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getGdsName())){
            ca.andGdsNameLike("%" + rGoodSaleRequest.getGdsName() + "%");
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getGdsType())){
            ca.andGdsTypeEqualTo(rGoodSaleRequest.getGdsType());
        }
        if(rGoodSaleRequest.getStaffId() != null && rGoodSaleRequest.getStaffId() > 0){
            ca.andStaffIdEqualTo(rGoodSaleRequest.getStaffId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getPayFlag())){
            ca.andStatusEqualTo(rGoodSaleRequest.getPayFlag());
        }
        if(CollectionUtils.isNotEmpty(rGoodSaleRequest.getCategoryCodes())){
            ca.andCategoryCodeIn(rGoodSaleRequest.getCategoryCodes());
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getShopId())){
            ca.andShopIdEqualTo(rGoodSaleRequest.getShopId());
        }
        if(CollectionUtils.isNotEmpty(rGoodSaleRequest.getGdsIds())){
            ca.andGdsIdIn(rGoodSaleRequest.getGdsIds());
        }

        ca.andStatusIn(payedList());

        return osc;
    }

    private List<String> payedList(){
        List<String> payedList = new ArrayList<>();
        payedList.add("02");
        payedList.add("03");
        payedList.add("04");
        payedList.add("05");
        payedList.add("06");
        return payedList;
    }


    /**
     * 封装搜索条件
     * @param rGoodSaleRequest
     * @return
     */
    private OrdSubShopIdxCriteria saleReqCriteria(RGoodSaleRequest rGoodSaleRequest){
        OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();

        osc.setLimitClauseCount(rGoodSaleRequest.getPageSize());
        osc.setLimitClauseStart(rGoodSaleRequest.getStartRowIndex());
        osc.setOrderByClause("order_time desc");

        OrdSubShopIdxCriteria.Criteria ca = osc.createCriteria();
        if(rGoodSaleRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rGoodSaleRequest.getBegDate());
        }
        if(rGoodSaleRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rGoodSaleRequest.getEndDate());
        }
        if(rGoodSaleRequest.getSiteId()!= null){
            ca.andSiteIdEqualTo(rGoodSaleRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getOrderId())){
            ca.andOrderIdEqualTo(rGoodSaleRequest.getOrderId());
        }
        if(rGoodSaleRequest.getStaffId() != null && rGoodSaleRequest.getStaffId() > 0){
            ca.andStaffIdEqualTo(rGoodSaleRequest.getStaffId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getPayFlag())){
            ca.andStatusEqualTo(rGoodSaleRequest.getPayFlag());
        }
        if(CollectionUtils.isNotEmpty(rGoodSaleRequest.getGdsIds())){
            ca.andGdsIdIn(rGoodSaleRequest.getGdsIds());
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getShopId())){
            ca.andShopIdEqualTo(rGoodSaleRequest.getShopId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getPayType())){
        	if(rGoodSaleRequest.getPayType().equals("0")){
        		ca.andPayTypeEqualTo(rGoodSaleRequest.getPayType());
        	}else{
        		ca.andPayTypeNotEqualTo("0");
        	}
        }

        ca.andStatusIn(payedList());
        return osc;
    }

    private Long sumBasicMoney(RGoodSaleRequest rGoodSaleRequest) {
        final  OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);
        return ordSubShopIdxUalMapper.sumBasicMoneyByExample(osc);
    }

    private Long queryOrderCount(RGoodSaleRequest rGoodSaleRequest){
    	final  OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);
        return ordSubShopIdxUalMapper.countOrderIdByExample(osc);
    }

    private Long querySaleNum(RGoodSaleRequest rGoodSaleRequest){
    	final  OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);
        return ordSubShopIdxUalMapper.sumOrderAmountByExample(osc);
    }

    private Long sumDiscountMoney(RGoodSaleRequest rGoodSaleRequest){
    	final  OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);
        return this.ordSubShopIdxUalMapper.sumDiscountMoneyByExample(osc);
    }
    
    /**
     * 封统计装搜索条件
     * @param rGoodSaleRequest
     * @return
     */
    private OrdSubShopIdxCriteria saleReqCountCriteria(RGoodSaleRequest rGoodSaleRequest){
        OrdSubShopIdxCriteria osc = new OrdSubShopIdxCriteria();

        osc.setLimitClauseCount(rGoodSaleRequest.getPageSize());
        osc.setLimitClauseStart(rGoodSaleRequest.getStartRowIndex());
        osc.setOrderByClause("order_time desc");

        OrdSubShopIdxCriteria.Criteria ca = osc.createCriteria();
        if(rGoodSaleRequest.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rGoodSaleRequest.getBegDate());
        }
        if(rGoodSaleRequest.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rGoodSaleRequest.getEndDate());
        }
        if(rGoodSaleRequest.getSiteId()!= null){
            ca.andSiteIdEqualTo(rGoodSaleRequest.getSiteId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getOrderId())){
            ca.andOrderIdEqualTo(rGoodSaleRequest.getOrderId());
        }
        if(rGoodSaleRequest.getStaffId() != null && rGoodSaleRequest.getStaffId() > 0){
            ca.andStaffIdEqualTo(rGoodSaleRequest.getStaffId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getPayFlag())){
            ca.andStatusEqualTo(rGoodSaleRequest.getPayFlag());
        }
        if(CollectionUtils.isNotEmpty(rGoodSaleRequest.getGdsIds())){
            ca.andGdsIdIn(rGoodSaleRequest.getGdsIds());
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getShopId())){
            ca.andShopIdEqualTo(rGoodSaleRequest.getShopId());
        }
        if(StringUtil.isNotBlank(rGoodSaleRequest.getPayType())){
        	if(rGoodSaleRequest.getPayType().equals("0")){
        		ca.andPayTypeEqualTo(rGoodSaleRequest.getPayType());
        	}else{
        		ca.andPayTypeNotEqualTo("0");
        	}
        }
        if(LongUtils.isNotEmpty(rGoodSaleRequest.getGdsType())){
        	ca.andGdsTypeEqualTo(rGoodSaleRequest.getGdsType());
        }
        if(StringUtil.isNotEmpty(rGoodSaleRequest.getGdsName())){
        	ca.andGdsNameLike(rGoodSaleRequest.getGdsName());
        }
        ca.andStatusIn(payedList());
        return osc;
    }

    private RGoodSaleResponse getGoodSale(OrdSub ordSub){
        return getGoodSaleResponse(ordSub);
    }

    private RGoodSaleResponse getGoodSale(OrdSubShopIdx ordSubShopIdx){
        RGoodSaleResponse goodSaleResponse = new RGoodSaleResponse();
        String payType = ordSubShopIdx.getPayType();
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(ordSubShopIdx.getOrderId())
                .andIdEqualTo(ordSubShopIdx.getOrderSubId());
        List<OrdSub> ordSubs = this.orderSubMapper.selectByExample(osc);

        if(CollectionUtils.isEmpty(ordSubs)){
            return goodSaleResponse;
        }
        OrdSub ordSub = ordSubs.get(0);
        goodSaleResponse = getGoodSaleResponse(ordSub);
        goodSaleResponse.setPayType(payType);
        if(ordSubShopIdx.getBackMoney()!=null){
        	 //剩余实付金额=单品实付金额-单品实际退款金额
        	goodSaleResponse.setRealMoney(ordSub.getRealMoney()-ordSubShopIdx.getBackMoney());
        }
        return goodSaleResponse;
    }

    private RGoodSaleResponse getGoodSaleResponse(OrdSubShopIdx ordSubIdx){
        RGoodSaleResponse goodSaleResponse = new RGoodSaleResponse();
        BeanUtils.copyProperties(ordSubIdx, goodSaleResponse);

        OrdMain ordMain = ordMainSV.queryOrderByOrderId(ordSubIdx.getOrderId());
        goodSaleResponse.setOrderSubId(ordSubIdx.getOrderSubId());
        //主表基本信息
        goodSaleResponse.setContactName(ordMain.getContactName());
        goodSaleResponse.setContactPhone(ordMain.getContactPhone());
        goodSaleResponse.setChnlAddress(ordMain.getChnlAddress());
        //书号制式编码

        GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                .querySkuInfoByOptions(OrderUtils.codeGdsRequest(ordSubIdx));
        goodSaleResponse.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO));
        goodSaleResponse.setIsbn(OrderUtils.getTxCode(gdsInfoRespDTO));

        //一级分类，二级分类
        GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
        categoryDTO.setCatgCode(ordSubIdx.getCategoryCode());
        List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
        if(CollectionUtils.isNotEmpty(categoryRespList)) {
//            String categoryRes = null;
//            for(int i = 0; i <categoryRespList.size(); i++ ){
//                if(i== 0){
//                    categoryRes = categoryRespList.get(i).getCatgName();
//                } else {
//                    categoryRes += (">" + categoryRespList.get(i).getCatgName());
//                }
//            }
            if(categoryRespList.size()>=1){
                goodSaleResponse.setCategory01(categoryRespList.get(0).getCatgName());
            }
            if(categoryRespList.size()>=2){
                goodSaleResponse.setCategory02(categoryRespList.get(1).getCatgName());
            }
            if(categoryRespList.size()>=3){
                goodSaleResponse.setCategory03(categoryRespList.get(2).getCatgName());
            }
            if(categoryRespList.size()>=4){
                goodSaleResponse.setCategory04(categoryRespList.get(3).getCatgName());
            }
//            if (categoryRespList.size() > 1) {
//                GdsCategoryRespDTO categoryRes1 = categoryRespList.get(0);
//                goodSaleResponse.setCategory01(categoryRes1.getCatgName());
//                GdsCategoryRespDTO categoryRes2 = categoryRespList.get(1);
//                goodSaleResponse.setCategory02(categoryRes2.getCatgName());
//            }else{
//                GdsCategoryRespDTO categoryRes1 = categoryRespList.get(0);
//                goodSaleResponse.setCategory01(categoryRes1.getCatgName());
//            }
        }

        //会员名
        Long staffId = ordSubIdx.getStaffId();
        CustInfoResDTO custRes = this.staffUnionRSV.findCustOrAdminByStaffId(staffId);
        if(custRes!=null) goodSaleResponse.setStaffCode(custRes.getStaffCode());

        //活动商品
        boolean isDiscount = this.isOrderDiscount(ordMain.getId(), ordSubIdx.getOrderSubId());
        goodSaleResponse.setIsProm(isDiscount);

        //处理金额
        Long hadBackMoney=0l;          
	   	 //计算退款金额    	 
	   	 if(StringUtil.isNotEmpty(ordSubIdx.getHasBackNum())&&ordSubIdx.getHasBackNum()>0l){
	   		 if(ordSubIdx.getBackMoney()!=null){
	   			 hadBackMoney = ordSubIdx.getBackMoney();
	   		 }
	   		 goodSaleResponse.setHasBackNum(ordSubIdx.getHasBackNum());
	   	 }else{
	   		 goodSaleResponse.setHasBackNum(0L);
	   	 }
		
		 goodSaleResponse.setRealMoney(ordSubIdx.getRealMoney()-hadBackMoney);
	   	 //剩余单品数量
	   	goodSaleResponse.setOrderAmount(ordSubIdx.getOrderAmount()-goodSaleResponse.getHasBackNum());
	   
	   	
        return goodSaleResponse;
    }
    
    private RGoodSaleResponse getGoodSaleResponse(OrdSub ordSub){
        RGoodSaleResponse goodSaleResponse = new RGoodSaleResponse();
        BeanUtils.copyProperties(ordSub, goodSaleResponse);

        OrdMain ordMain = ordMainSV.queryOrderByOrderId(ordSub.getOrderId());
        goodSaleResponse.setOrderSubId(ordSub.getId());
        //主表基本信息
        goodSaleResponse.setContactName(ordMain.getContactName());
        goodSaleResponse.setContactPhone(ordMain.getContactPhone());
        goodSaleResponse.setChnlAddress(ordMain.getChnlAddress());
        //书号制式编码

        GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                .querySkuInfoByOptions(OrderUtils.codeGdsRequest(ordSub));
        goodSaleResponse.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO));
        goodSaleResponse.setIsbn(OrderUtils.getTxCode(gdsInfoRespDTO));

        //一级分类，二级分类
        GdsCategoryReqDTO categoryDTO=new GdsCategoryReqDTO();
        categoryDTO.setCatgCode(ordSub.getCategoryCode());
        List<GdsCategoryRespDTO> categoryRespList=gdsCategoryRSV.queryCategoryTraceUpon(categoryDTO);
        if(CollectionUtils.isNotEmpty(categoryRespList)) {
//            String categoryRes = null;
//            for(int i = 0; i <categoryRespList.size(); i++ ){
//                if(i== 0){
//                    categoryRes = categoryRespList.get(i).getCatgName();
//                } else {
//                    categoryRes += (">" + categoryRespList.get(i).getCatgName());
//                }
//            }
            if(categoryRespList.size()>=1){
                goodSaleResponse.setCategory01(categoryRespList.get(0).getCatgName());
            }
            if(categoryRespList.size()>=2){
                goodSaleResponse.setCategory02(categoryRespList.get(1).getCatgName());
            }
            if(categoryRespList.size()>=3){
                goodSaleResponse.setCategory03(categoryRespList.get(2).getCatgName());
            }
            if(categoryRespList.size()>=4){
                goodSaleResponse.setCategory04(categoryRespList.get(3).getCatgName());
            }
//            if (categoryRespList.size() > 1) {
//                GdsCategoryRespDTO categoryRes1 = categoryRespList.get(0);
//                goodSaleResponse.setCategory01(categoryRes1.getCatgName());
//                GdsCategoryRespDTO categoryRes2 = categoryRespList.get(1);
//                goodSaleResponse.setCategory02(categoryRes2.getCatgName());
//            }else{
//                GdsCategoryRespDTO categoryRes1 = categoryRespList.get(0);
//                goodSaleResponse.setCategory01(categoryRes1.getCatgName());
//            }
        }

        //会员名
        Long staffId = ordSub.getStaffId();
        CustInfoResDTO custRes = this.staffUnionRSV.findCustOrAdminByStaffId(staffId);
        if(custRes!=null) goodSaleResponse.setStaffCode(custRes.getStaffCode());

        //活动商品
        boolean isDiscount = this.isOrderDiscount(ordMain.getId(), ordSub.getId());
        goodSaleResponse.setIsProm(isDiscount);

        //处理金额
        //Long hadBackMoney=0l;          
	   	 //计算退款金额    	 
	   	 if(StringUtil.isNotEmpty(ordSub.getHasBackNum())&&ordSub.getHasBackNum()>0l){
	   		 //double hadBackScale = ordBackApplySV.calCulateScaleApply(ordSub ,ordSub.getHasBackNum());
	   		
	   		 //计算单品实际退款金额=实付金额*比例     
	   		// hadBackMoney = new BigDecimal(ordMain.getRealMoney()*hadBackScale/1000000).longValue();    
	   		 goodSaleResponse.setHasBackNum(ordSub.getHasBackNum());
	   	 }else{
	   		 goodSaleResponse.setHasBackNum(0L);
	   	 }
		
		 goodSaleResponse.setRealMoney(ordSub.getRealMoney());
	   	 //剩余单品数量
	   	goodSaleResponse.setOrderAmount(ordSub.getOrderAmount()-goodSaleResponse.getHasBackNum());
	   
	   	
        return goodSaleResponse;
    }

    //参与活动
    private boolean isOrderDiscount(String orderId,String orderSubId){

        boolean isDiscount = false;
        if(ordDiscountSV.queryOrdSubIsDiscount(orderId,orderSubId)){
            isDiscount = true;
        }

        SBaseAndSubInfo info = new SBaseAndSubInfo();
        info.setOrderSubId(orderSubId);
        if(ordPromSV.queryOrdProm(info)!=null){
            isDiscount = true;
        }
        return isDiscount;
    }

    @Override
    public SOrderDetailsSub queryOrderDetailsSubBySubId(SBaseAndSubInfo sOrderAOrderSubInfo) {
        OrdSub os = this.findByOrderSubId(sOrderAOrderSubInfo);
        if(os == null){
            LogUtil.info(MODULE, "未找到[" + sOrderAOrderSubInfo.getOrderId() + "]该订单的子订单信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        SOrderDetailsSub sod = new SOrderDetailsSub();
        ObjectCopyUtil.copyObjValue(os, sod, null, false);
        sod.setOrderSubId(os.getId());
        GdsSkuInfoReqDTO gdsInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsInfoReqDTO.setId(os.getSkuId());
        gdsInfoReqDTO.setGdsId(os.getGdsId());
        List<Long> propIds = new ArrayList<Long>();
        propIds.add(1004l);
        propIds.add(1050l);
        gdsInfoReqDTO.setPropIds(propIds);
        gdsInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[] { GdsOption.SkuQueryOption.BASIC,
                GdsOption.SkuQueryOption.MAINPIC,GdsOption.SkuQueryOption.PROP});
        GdsSkuInfoRespDTO gdsInfoRespDTO = gdsSkuInfoQueryRSV
                .querySkuInfoByOptions(gdsInfoReqDTO);
        if(gdsInfoRespDTO != null) {
            if (gdsInfoRespDTO.getMainPic() != null && gdsInfoRespDTO.getMainPic().getURL() != null) {
                sod.setPicUrl(gdsInfoRespDTO.getMainPic().getURL());
                sod.setPicId(gdsInfoRespDTO.getMainPic().getMediaUuid());
            }
            if (gdsInfoRespDTO.getUrl() != null)
                sod.setGdsUrl(gdsInfoRespDTO.getUrl());
            sod.setIsbn(gdsInfoRespDTO.getIsbn());
            sod.setTxCode(OrderUtils.getTxCode(gdsInfoRespDTO)); //条形码
            sod.setZsCode(OrderUtils.getZsCode(gdsInfoRespDTO));
        }
        return sod;
    }

    @Override
    public List<ROrdSubStaffIdxResp> queryOrderSubByStaffIdAndSkuid(ROrdSubStaffIdxReq rOrdSubStaffIdxReq) throws BusinessException {
        return this.ordSubStaffIdxSV.queryOrdSubStaffIdx(rOrdSubStaffIdxReq);
    }

    @Override
    public Long querySumOrderAmount(String orderId) {
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(orderId);
        return this.ordSubUalMapper.sumOrderAmountByExample(osc);
    }

    @Override
    public void updateOrderSubHasBackNum(OrdSub ordSub) {
        if (ordSub == null) {
            LogUtil.info(MODULE, "未找到该订单的子订单数据信息");
            throw new BusinessException(MsgConstants.ProMsgCode.ORD_PRO_312002);
        }
        OrdSubCriteria osc = new OrdSubCriteria();
        osc.createCriteria().andOrderIdEqualTo(ordSub.getOrderId())
                .andIdEqualTo(ordSub.getId());
        ordSub.setId(null);
        ordSub.setOrderId(null);
        this.orderSubMapper.updateByExampleSelective(ordSub, osc);
    }

    protected List<OrdSubShopIdx> saveOrdSubByThread(OrdSubShopIdxCriteria ordSubCriteria,RGoodSaleRequest rGoodSaleRequest) {

        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());
        Map<Integer, List<OrdSubShopIdx>> ordSubMap = new HashMap<Integer, List<OrdSubShopIdx>>();

        List<OrdSubShopIdx> ordSubList = new ArrayList<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        List<Future> fus = new ArrayList<Future>();
        for (int i = 1; i < cnt+1; i++) {
            Future f = executor.submit(new DealSubThread(i,ordSubCriteria,rGoodSaleRequest.getKey(),ordSubMap));
            fus.add(f);
        }
        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                LogUtil.info(MODULE,"销售明细导出线程查询异常",e);
            } catch (ExecutionException e) {
                LogUtil.info(MODULE,"销售明细导出线程查询异常",e);
            }
        }
        executor.shutdown();
        for (List<OrdSubShopIdx> v : ordSubMap.values()) {
            LogUtil.info(MODULE,"子列表数量："+v.size());
            ordSubList.addAll(v);
        }
        LogUtil.info(MODULE,"总数量："+ordSubList.size());
        return  ordSubList;
    }

    protected List<RGoodSaleResponse> saveOrdSubInfoByThread(List<OrdSubShopIdx> ordSubs,RGoodSaleRequest rGoodSaleRequest){
        List<RGoodSaleResponse>  saleResponses = new ArrayList<>();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        Map<Integer, List<RGoodSaleResponse>> saleMap = new HashMap<Integer, List<RGoodSaleResponse>>();

        int group = 60;  //查数据点40%，剩余占用60%,分成60个线程
        int groupNum = ordSubs.size() / group;
        int lastNum = ordSubs.size() % group;
        if(ordSubs.size() <= 60){
            group = ordSubs.size();
            groupNum = 1;
        } else {
            group += 1;
        }
        List<Future> fus = new ArrayList<Future>();
        for (int i = 0; i < group; i++) {
            List<OrdSubShopIdx> obs  = null;
            if(ordSubs.size() <= 60){
                obs = new ArrayList<>();
                obs.add(ordSubs.get(i));
            } else {
                if(i != group-1){
                    obs = ordSubs.subList(i*groupNum, (i+1)*groupNum);
                } else {
                    obs = ordSubs.subList(i*groupNum, i*groupNum+lastNum);
                }
            }
            Future f = executor.submit(new DealSubInfoThread(obs, rGoodSaleRequest.getKey(),i,saleMap));
            fus.add(f);
        }

        for (Future f : fus) {
            try {
                f.get();
            } catch (InterruptedException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            } catch (ExecutionException e) {
                LogUtil.info(MODULE,"销售明细补全信息线程查询异常",e);
            }
        }
        executor.shutdown();
        for (List<RGoodSaleResponse> v : saleMap.values()) {
            LogUtil.info(MODULE,"补全信息子列表数量："+v.size());
            saleResponses.addAll(v);
        }

        return saleResponses;
    }

    @Override
    public List<RGoodSaleResponse> queryGoodSaleExportNew(RGoodSaleRequest rGoodSaleRequest) {
        LogUtil.info(MODULE,"销售明细导出开始："+JSON.toJSONString(rGoodSaleRequest));

        //final OrdSubCriteria osc = saleSubReqCriteria(rGoodSaleRequest);
        final OrdSubShopIdxCriteria osc = saleReqCountCriteria(rGoodSaleRequest);
        
        //查询子订单号
        List<OrdSubShopIdx> ordSubShopIdxs = saveOrdSubByThread(osc,rGoodSaleRequest);
        LogUtil.info(MODULE,"销售明细导出sql查询结束："+ordSubShopIdxs.size());
        List<RGoodSaleResponse>  saleResponses = new ArrayList<>();

        if(CollectionUtils.isEmpty(ordSubShopIdxs)){
            return saleResponses;
        }
        saleResponses = saveOrdSubInfoByThread(ordSubShopIdxs,rGoodSaleRequest);

        LogUtil.info(MODULE,"销售明细导出补全数据结束");
        return saleResponses;
    }

    public class DealSubThread extends Thread  {

        private int tableIndex;

        private OrdSubShopIdxCriteria ordSubCriteria;

        private Long key;

        private Map<Integer, List<OrdSubShopIdx>> ordSubMap;

        public DealSubThread(int tableIndex,OrdSubShopIdxCriteria ordSubCriteria,Long key,Map<Integer, List<OrdSubShopIdx>> ordSubMap){
            this.tableIndex = tableIndex;
            this.ordSubCriteria = ordSubCriteria;
            this.key = key;
            this.ordSubMap = ordSubMap;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":开始处理");
            DistributeRuleAssist.setTableIndex(tableIndex);           
            List<OrdSubShopIdx> ordSubShopIdxs = ordSubShopIdxUalMapper.selectBySellGdsExample(ordSubCriteria);
            ordSubMap.put(tableIndex,ordSubShopIdxs);
            DistributeRuleAssist.clearTableIndex();
            OrdFileImport ordFile = new OrdFileImport();
            ordFile.setId(key);
            ordFile.setShopId(8L);
            ordFileImportSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":结束处理");
        }
    }
    public class DealSubInfoThread extends Thread  {

        private List<OrdSubShopIdx> ordSubs;

        private Long key;

        private int group;

        private Map<Integer, List<RGoodSaleResponse>> saleMap;

        public DealSubInfoThread(List<OrdSubShopIdx> ordSubs,Long key,int group,Map<Integer, List<RGoodSaleResponse>> saleMap){
            this.ordSubs = ordSubs;
            this.key = key;
            this.group = group;
            this.saleMap = saleMap;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细补全线程开始处理");
            List<RGoodSaleResponse>  saleResponses = new ArrayList<>();
            for(final OrdSubShopIdx ordSubIdx: ordSubs){
                RGoodSaleResponse response =  getGoodSaleResponse(ordSubIdx);
                saleResponses.add(response);
            }
            saleMap.put(group,saleResponses);
            OrdFileImport ordFile = new OrdFileImport();
            ordFile.setId(key);
            ordFile.setShopId(1L);
            ordFileImportSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细补全线程结束处理");
        }
    }
}
