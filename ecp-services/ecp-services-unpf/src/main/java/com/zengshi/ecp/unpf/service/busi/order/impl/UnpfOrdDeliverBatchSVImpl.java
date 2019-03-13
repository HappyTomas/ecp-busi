package com.zengshi.ecp.unpf.service.busi.order.impl;

import com.zengshi.ecp.aip.third.dubbo.constants.AipThirdConstants;
import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderShipReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrdDetialRSV;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrderShipRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdDeliveryBatchMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdMainMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdSubMapper;
import com.zengshi.ecp.unpf.dao.model.*;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.*;
import com.zengshi.ecp.unpf.dubbo.util.UnpfOrdConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.*;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdDeliverBatchSVImpl implements IUnpfOrdDeliverBatchSV {

	public static final String MODULE = UnpfOrdDeliverBatchSVImpl.class.getName();
	
    @Resource
    private IUnpfOrdMainSV unpfOrdMainSV;

    @Resource
    private IUnpfOrdSubSV unpfOrdSubSV;

    @Resource
    private IUnpfOrdDeliverDetailsSV unpfOrdDeliverDetailsSV;

    @Resource(name = "seq_unpf_ord_delivery_batch")
    private Sequence seqUnpfOrdDeliveryBatch;

    @Resource
    private UnpfOrdDeliveryBatchMapper unpfOrdDeliveryBatchMapper;
    
    @Resource
    private IOrderShipRSV orderShipRSV;
    
	@Resource
	private IUnpfShopAuthSV unpfShopAuthSV;
	
	@Resource
	private IOrdDetialRSV ordDetialRSV;
	
    @Resource
    private UnpfOrdMainMapper unpfOrdMainMapper;
    
    @Resource
    private UnpfOrdSubMapper unpfOrdSubMapper;
    
    @Resource
    private IUnpfShopExpressSV unpfShopExpressSV;
	
    @Override
    public List<RUnpfOrdDeliveryBatchResp> queryUnpfOrdDeliveryBatch(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {

        UnpfOrdDeliveryBatchCriteria uosc = new UnpfOrdDeliveryBatchCriteria();
        UnpfOrdDeliveryBatchCriteria.Criteria ca = uosc.createCriteria();
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getId()) ){
            ca.andOrderIdEqualTo(rUnpfOrdMainReq.getId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getOuterId())){
            ca.andOuterIdEqualTo(rUnpfOrdMainReq.getOuterId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getPlatType())){
            ca.andPlatTypeEqualTo(rUnpfOrdMainReq.getPlatType());
        }
        List<UnpfOrdDeliveryBatch> unpfOrdDeliveryBatches = unpfOrdDeliveryBatchMapper.selectByExample(uosc);
        if(CollectionUtils.isEmpty(unpfOrdDeliveryBatches)){
            return  null;
        }
        List<RUnpfOrdDeliveryBatchResp> rUnpfOrdDeliveryBatchResps = new ArrayList<RUnpfOrdDeliveryBatchResp>();
        for(UnpfOrdDeliveryBatch uos :unpfOrdDeliveryBatches){
            RUnpfOrdDeliveryBatchResp rUnpfOrdDeliveryBatchResp = new RUnpfOrdDeliveryBatchResp();
            ObjectCopyUtil.copyObjValue(uos, rUnpfOrdDeliveryBatchResp,null,false);
          
            //匹配物流公司名称
            RUnpfShopExpressReq req = new RUnpfShopExpressReq();
            req.setPlatType(uos.getPlatType());
            req.setCode(uos.getExpressId());
            req.setShopId(uos.getShopId());
            
            RUnpfShopExpressResp expressResp = unpfShopExpressSV.getShopExpressResp(req);
            if(expressResp!=null){
            	rUnpfOrdDeliveryBatchResp.setExpressName(expressResp.getName());
            }
            rUnpfOrdDeliveryBatchResps.add(rUnpfOrdDeliveryBatchResp);
        }
        return rUnpfOrdDeliveryBatchResps;
    }

    protected UnpfOrdDeliveryBatch saveUnpfOrdDeliveryBatchIn(RUnpfSendMainReq rUnpfSendMainReq,UnpfOrdMain unpfOrdMain){

        UnpfOrdDeliveryBatch unpfOrdDeliveryBatch = new UnpfOrdDeliveryBatch();
        unpfOrdDeliveryBatch.setOrderId(unpfOrdMain.getId());
        unpfOrdDeliveryBatch.setOuterId(unpfOrdMain.getOuterId());
        unpfOrdDeliveryBatch.setStaffId(unpfOrdMain.getEcpStaffId());
        unpfOrdDeliveryBatch.setShopId(unpfOrdMain.getShopId());
        unpfOrdDeliveryBatch.setDeliveryType(rUnpfSendMainReq.getDeliveryType());
        unpfOrdDeliveryBatch.setSendDate(DateUtil.getSysDate());
//        unpfOrdDeliveryBatch.setSendName();
//        unpfOrdDeliveryBatch.setSendTel();
//        unpfOrdDeliveryBatch.setSendPhone();
//        unpfOrdDeliveryBatch.setSendAddress();
//        unpfOrdDeliveryBatch.setSendZip();
        unpfOrdDeliveryBatch.setExpressId(rUnpfSendMainReq.getExpressId());
        unpfOrdDeliveryBatch.setExpressNo(rUnpfSendMainReq.getExpressNo());
        unpfOrdDeliveryBatch.setContactName(unpfOrdMain.getContractName());
        unpfOrdDeliveryBatch.setContactTel(unpfOrdMain.getContractTel());
        unpfOrdDeliveryBatch.setContactPhone(unpfOrdMain.getContractNum());
        unpfOrdDeliveryBatch.setContactZip(unpfOrdMain.getContractZipcode());
        unpfOrdDeliveryBatch.setContactAddress(unpfOrdMain.getContractAddr());
//        unpfOrdDeliveryBatch.setBringName();
//        unpfOrdDeliveryBatch.setCardType();
//        unpfOrdDeliveryBatch.setCardId();
//        unpfOrdDeliveryBatch.setBringTel();
//        unpfOrdDeliveryBatch.setBringPhone();
//        unpfOrdDeliveryBatch.setContactDate();
        unpfOrdDeliveryBatch.setRemark(rUnpfSendMainReq.getRemark());
        unpfOrdDeliveryBatch.setCreateTime(unpfOrdDeliveryBatch.getSendDate());
        unpfOrdDeliveryBatch.setCreateStaff(rUnpfSendMainReq.getStaff().getId());
        unpfOrdDeliveryBatch.setUpdateTime(unpfOrdDeliveryBatch.getSendDate());
        unpfOrdDeliveryBatch.setUpdateStaff(rUnpfSendMainReq.getStaff().getId());
        unpfOrdDeliveryBatch.setPlatType(unpfOrdMain.getPlatType());

        unpfOrdDeliveryBatch.setId(this.seqUnpfOrdDeliveryBatch.nextValue());
        this.unpfOrdDeliveryBatchMapper.insert(unpfOrdDeliveryBatch);
        return  unpfOrdDeliveryBatch;
    }

    @Override
    public Long saveUnpfOrdDeliveryBatch(RUnpfSendMainReq rUnpfSendMainReq) throws BusinessException {
        RUnpfOrdMainReq rUnpfOrdMainReq = new RUnpfOrdMainReq();
        rUnpfOrdMainReq.setId(rUnpfSendMainReq.getOrderId());
        UnpfOrdMain unpfOrdMain = this.unpfOrdMainSV.queryUnpfOrdMainIn(rUnpfOrdMainReq);

        RUnpfOrdMainReq updateMain = new RUnpfOrdMainReq();
        updateMain.setId(unpfOrdMain.getId());
        updateMain.setUpdateStaff(rUnpfSendMainReq.getStaff().getId());
        if(rUnpfSendMainReq.isSendAll()){
            updateMain.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
        } else {
            updateMain.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION);
        }
        this.unpfOrdMainSV.updateUnpfOrdMain(updateMain);

        for(RUnpfSendSubReq rUnpfSendSubReq : rUnpfSendMainReq.getUnpfSendSubReqList()){
            RUnpfOrdSubReq rUnpfOrdSubReq = new RUnpfOrdSubReq();
            rUnpfOrdSubReq.setId(rUnpfSendSubReq.getId());
            rUnpfOrdSubReq.setOrderId(unpfOrdMain.getId());
            rUnpfOrdSubReq.setUpdateStaff(rUnpfSendMainReq.getStaff().getId());

            RUnpfOrdSubResp resp = this.unpfOrdSubSV.queryUnpfOrdSubIn(rUnpfOrdSubReq);
            if(resp != null && resp.getDeliveryAmount() == null){
                rUnpfOrdSubReq.setDeliveryAmount(rUnpfSendSubReq.getDeliveryAmount());
            } else {
                rUnpfOrdSubReq.setDeliveryAmount(rUnpfSendSubReq.getDeliveryAmount() + resp.getDeliveryAmount());
            }
            rUnpfOrdSubReq.setOuterSubId(resp.getOuterSubId());
            rUnpfSendSubReq.setOuterSubId(resp.getOuterSubId());
            this.unpfOrdSubSV.updateUnpfOrdSub(rUnpfOrdSubReq);
        }


        UnpfOrdDeliveryBatch unpfOrdDeliveryBatch = saveUnpfOrdDeliveryBatchIn(rUnpfSendMainReq,unpfOrdMain);

        rUnpfSendMainReq.setBatchId(unpfOrdDeliveryBatch.getId());
        this.unpfOrdDeliverDetailsSV.saveUnpfOrdDeliverDetails(rUnpfSendMainReq,unpfOrdDeliveryBatch);
        return unpfOrdDeliveryBatch.getId();
    }

    @Override
    public void updateUnpfOrdDeliveryBatch(RUnpfOrdDeliveryBatchReq rUnpfOrdDeliveryBatchReq) throws BusinessException {

    }
    
    @Override
	public Boolean deliverGoods(OrderShipReqDTO orderShipReq) throws BusinessException {
		// 查询店铺授权基本信息
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setShopId(orderShipReq.getShopId());
		unpfShopAuthReqDTO.setPlatType(orderShipReq.getPlatType());
		unpfShopAuthReqDTO.setStatus("1");
		// 查询店铺授权基本信息
		PageResponseDTO<UnpfShopAuthRespDTO> p = unpfShopAuthSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
        if(p==null){
        	throw new BusinessException("unpf.100008",new String[]{unpfShopAuthReqDTO.getShopId().toString(),unpfShopAuthReqDTO.getPlatType()});
        }      
        if(CollectionUtils.isEmpty(p.getResult())){
        	throw new BusinessException("unpf.100008",new String[]{unpfShopAuthReqDTO.getShopId().toString(),unpfShopAuthReqDTO.getPlatType()});
        }

		BaseShopAuthReqDTO baseShopAuthReqDTO = new BaseShopAuthReqDTO();
		ObjectCopyUtil.copyObjValue(p.getResult().get(0), baseShopAuthReqDTO, null, false);		
		baseShopAuthReqDTO.setAuthId(p.getResult().get(0).getId());
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, orderShipReq, null,false);
        //有赞发货
        if (AipThirdConstants.Commons.YOUZAN.equals(orderShipReq.getPlatType())) {
            return youzanDeliver(orderShipReq);
        }


		OrderReqDTO orderReqDTO = new OrderReqDTO();
		orderReqDTO.setOrderId(""+orderShipReq.getTid());
		orderReqDTO.setShopId(orderShipReq.getShopId());
		orderReqDTO.setPlatType(orderShipReq.getPlatType());
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, orderReqDTO, null,false);
		OrderRespDTO  orderDetial = ordDetialRSV.queryOrderDetail(orderReqDTO);	
		
		//部分发货或者已支付状态
		if(orderDetial.getStatus().equals("SELLER_CONSIGNED_PART")||orderDetial.getStatus().equals("WAIT_SELLER_SEND_GOODS")){
			Map<String,Object> map = orderShipRSV.ship(orderShipReq);//发货	
			Boolean result = (Boolean) map.get("result");
			if(result){
				return result;
			}else{
				String msg= "";
				if(StringUtil.isNotEmpty(map.get("msg"))){
					 msg =  map.get("msg").toString();
				}				
				throw new BusinessException("AIPTHIRD.ERRORS.100001",new String[]{msg});
			}			
		}else{
			//更新当前订单状态，并抛出异常
			RUnpfOrdMainReq req = new RUnpfOrdMainReq();
			List<RUnpfOrdSubReq> subOrders = unpfOrdMainSV.copyDetialToOrderMain(orderDetial, req);
			UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
	    	criteria.createCriteria().andOuterIdEqualTo(""+orderShipReq.getTid());	
	    	List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
	    	if(list!=null){
		    	UnpfOrdMain orderMain = list.get(0);
		    	req.setId(orderMain.getId());
		    	req.setUpdateStaff(1000L);
		    	req.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	  		    criteria.createCriteria().andIdEqualTo(orderMain.getId());
	  		    UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
	  		    ObjectCopyUtil.copyObjValue(req, unpfOrdMain, null,false);	
			    unpfOrdMainMapper.updateByExample(unpfOrdMain, criteria);
			   //修改子订单	   
			    for(RUnpfOrdSubReq subOrder:subOrders){					 
				   UnpfOrdSubCriteria subCriteria = new UnpfOrdSubCriteria();
				   subCriteria.createCriteria().andOuterSubIdEqualTo(subOrder.getOuterSubId());
				   List<UnpfOrdSub> subList = unpfOrdSubMapper.selectByExample(subCriteria);
				   if(subList!=null){
					   UnpfOrdSub ordSub = subList.get(0);
					   subOrder.setId(ordSub.getId());
					   subOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
					   ObjectCopyUtil.copyObjValue(subOrder, ordSub,  null, false);
					   subCriteria.createCriteria().andIdEqualTo(ordSub.getId());
					   unpfOrdSubMapper.updateByExample(ordSub, subCriteria);
				   }
			    }
		    }
		    throw new BusinessException("unpf.100013");
		}
	}

    private boolean youzanDeliver(OrderShipReqDTO orderShipReq) {
        String outerId = orderShipReq.getTradeId();
        String subTids = orderShipReq.getSubTid();
        //subTid 如果为空，存在两种可能，一种是全部发货，另一种是之前已部分发货，再次发货就是全部发货（此时页面没传subTid过来）
        handleSubTid(orderShipReq);
        Map<String, Object> resultMap = orderShipRSV.ship(orderShipReq);
        boolean success = (Boolean) resultMap.get("result");
        if (!success) {
            LogUtil.info("UnpfOrdDeliverBatchSVImpl","有赞发货失败，outerId="+orderShipReq.getTradeId()+" | subTid="+orderShipReq.getSubTid());
            LogUtil.info("UnpfOrdDeliverBatchSVImpl", "有赞发货失败：" + resultMap.get("msg"));
            throw new BusinessException("AIPTHIRD.ERRORS.100001", new String[]{resultMap.get("msg").toString()});
        }
        LogUtil.info("UnpfOrdDeliverBatchSVImpl","有赞发货成功，开始修改订单状态，outerId="+orderShipReq.getTradeId()+" | subTid="+subTids);
        //修改已发货商品状态
        if (StringUtil.isBlank(subTids)) {
            //商品全部改为已发货状态，订单状态也改为已发货状态
            updateOrdMainStatus(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
            List<UnpfOrdSub> list = getUnpfOrdSubByOuterId(outerId);
            updateOrdSubToDeliverStatus(list);
        } else {
            String[] outerSubIds = subTids.split(",");
            for (String outerSubId : outerSubIds) {
                List<UnpfOrdSub> unpfOrdSubList = getUnpfOrdSubByOuterSubId(outerSubId);
                updateOrdSubToDeliverStatus(unpfOrdSubList);
            }
            if (existsUnDeliverGoods(outerId)) {
                updateOrdMainStatus(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION);
            } else {
                updateOrdMainStatus(outerId, UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
            }
        }
        return true;
    }


    /**
     * subTid 如果为空，存在两种可能，一种是全部发货，另一种是之前已部分发货，再次发货就是全部发货（此时页面没传subTid过来）
     * 需要额外处理两种情况，通过判断商品状态来确定是部分发货还是全部发货
     */
    private void handleSubTid(OrderShipReqDTO orderShipReq) {
        if (StringUtil.isBlank(orderShipReq.getSubTid())) {
            String outerId = orderShipReq.getTradeId();
            UnpfOrdSubCriteria criteria = new UnpfOrdSubCriteria();
            criteria.createCriteria().andOuterIdEqualTo(outerId).andPlatTypeEqualTo(AipThirdConstants.Commons.YOUZAN);
            List<UnpfOrdSub> list = unpfOrdSubMapper.selectByExample(criteria);
            if (CollectionUtils.isNotEmpty(list)) {
                int unSendCount = 0;
                StringBuilder sb = new StringBuilder();
                for (UnpfOrdSub sub : list) {
                    if (sub.getStatus().equals(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID)) {
                        unSendCount++;
                        sb.append(sub.getOuterSubId()).append(",");
                    }
                }
                if (unSendCount > 0 && unSendCount < list.size()) {
                    //部分发货，把剩余未发货商品发货
                    String result = sb.toString();
                    orderShipReq.setSubTid(result.substring(0,result.length()-1));
                }
            }
        }
    }

    private void updateOrdSubToDeliverStatus(List<UnpfOrdSub> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (UnpfOrdSub ordSub : list) {
                UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
                unpfOrdSub.setId(ordSub.getId());
                unpfOrdSub.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
                unpfOrdSubMapper.updateByPrimaryKeySelective(unpfOrdSub);
            }
        }
    }

    private void updateOrdMainStatus(String outerId, String status) {
        UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
        criteria.createCriteria().andOuterIdEqualTo(outerId);
        List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(list)) {
            UnpfOrdMain ordMain = list.get(0);
            UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
            unpfOrdMain.setId(ordMain.getId());
            unpfOrdMain.setStatus(status);
            unpfOrdMainMapper.updateByPrimaryKeySelective(unpfOrdMain);
        }
    }

    List<UnpfOrdSub> getUnpfOrdSubByOuterId(String outerId) {
        UnpfOrdSubCriteria criteria = new UnpfOrdSubCriteria();
        criteria.createCriteria().andOuterIdEqualTo(outerId).andPlatTypeEqualTo(AipThirdConstants.Commons.YOUZAN);
        List<UnpfOrdSub> list = unpfOrdSubMapper.selectByExample(criteria);
        return list == null ? Collections.<UnpfOrdSub>emptyList() : list;
    }

    List<UnpfOrdSub> getUnpfOrdSubByOuterSubId(String outerSubId) {
        UnpfOrdSubCriteria criteria = new UnpfOrdSubCriteria();
        criteria.createCriteria().andOuterSubIdEqualTo(outerSubId).andPlatTypeEqualTo(AipThirdConstants.Commons.YOUZAN);
        List<UnpfOrdSub> list = unpfOrdSubMapper.selectByExample(criteria);
        return list == null ? Collections.<UnpfOrdSub>emptyList() : list;
    }


    boolean existsUnDeliverGoods(String outerId){
        UnpfOrdSubCriteria criteria = new UnpfOrdSubCriteria();
        criteria.createCriteria().andOuterIdEqualTo(outerId).andPlatTypeEqualTo(AipThirdConstants.Commons.YOUZAN)
        .andStatusEqualTo(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);
        List<UnpfOrdSub> list = unpfOrdSubMapper.selectByExample(criteria);
        return CollectionUtils.isEmpty(list) ? false : true;
    }

}
