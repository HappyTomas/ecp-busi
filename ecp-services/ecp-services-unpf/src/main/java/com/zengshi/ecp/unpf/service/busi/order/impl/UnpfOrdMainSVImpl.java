package com.zengshi.ecp.unpf.service.busi.order.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.OrderReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.OrderRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.SubOrder;
import com.zengshi.ecp.aip.third.dubbo.interfaces.IOrdDetialRSV;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.dto.OrdFileImportDTO;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdFileImportRSV;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustThirdCodeResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustThirdCodeRSV;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdMainMapper;
import com.zengshi.ecp.unpf.dao.mapper.busi.UnpfOrdSubMapper;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMain;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdMainCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSub;
import com.zengshi.ecp.unpf.dao.model.UnpfOrdSubCriteria;
import com.zengshi.ecp.unpf.dao.model.UnpfThingLock;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdDeliveryBatchResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdMainResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubReq;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfOrdSubResp;
import com.zengshi.ecp.unpf.dubbo.dto.order.RUnpfQueryOrderReq;
import com.zengshi.ecp.unpf.dubbo.util.LongUtils;
import com.zengshi.ecp.unpf.dubbo.util.UnpfOrdConstants;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.good.send.interfaces.IUnpfGoodSendSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfErpOrderSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdDeliverBatchSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdLogSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdMainSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfOrdSubSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfThingLockSV;
import com.zengshi.ecp.unpf.service.busi.order.interfaces.IUnpfTmOrdMainSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.distribute.DistributeRuleAssist;
import com.db.sequence.Sequence;

/**
 * @author: cbl
 * @date: 2016/11/8.
 */
public class UnpfOrdMainSVImpl extends GeneralSQLSVImpl implements IUnpfOrdMainSV {
	
	private static final String MODULE = UnpfOrdMainSVImpl.class.getName();
    @Resource
    private IUnpfOrdSubSV unpfOrdSubSV; 

    @Resource
    private IUnpfOrdDeliverBatchSV unpfOrdDeliverBatchSV;

    @Resource
    private UnpfOrdMainMapper unpfOrdMainMapper;
    
    @Resource
    private UnpfOrdSubMapper unpfOrdSubMapper;

    @Resource(name = "seq_unpf_ord_main")
    private Sequence seqUnpfOrdMain;
    
	@Resource
	private IOrdDetialRSV ordDetialRSV;
	
	@Resource
	private IUnpfShopAuthSV unpfShopAuthSV;
	
	@Resource
	private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
	
    @Autowired(required = false)
    private IUnpfTmOrdMainSV unpfTmOrdMainSV;
    
    @Autowired(required = false)
    private IUnpfErpOrderSV unpfErpOrderSV;
    
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
	@Resource
	private ICustThirdCodeRSV custThirdCodeRSV;
	
	@Resource
	private IUnpfOrdLogSV unpfOrdLogSV;

	@Resource
	private IUnpfThingLockSV unpfThingLockSV;

	@Resource
	private IUnpfGoodSendSV unpfGoodSendSV;
	
	@Resource
	private IOrdFileImportRSV ordFileImportRSV;
	
    private static Map<Integer, List<UnpfOrdMain>> unpfOrdMainMap = new HashMap<Integer, List<UnpfOrdMain>>();


    @Override
    public RUnpfOrdMainResp queryUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {


        UnpfOrdMain unpfOrdMain = this.queryUnpfOrdMainIn(rUnpfOrdMainReq);
        if(unpfOrdMain == null){
            return null;
        }
        RUnpfOrdMainResp rUnpfOrdMainResp = new RUnpfOrdMainResp();
        ObjectCopyUtil.copyObjValue(unpfOrdMain, rUnpfOrdMainResp,null,false);
        List<RUnpfOrdSubResp> rUnpfOrdSubResps = this.unpfOrdSubSV.queryUnpfOrdSub(rUnpfOrdMainReq);
        if(CollectionUtils.isNotEmpty(rUnpfOrdSubResps)){
            rUnpfOrdMainResp.setrUnpfOrdSubResps(rUnpfOrdSubResps);
        }

        List<RUnpfOrdDeliveryBatchResp> rUnpfOrdDeliveryBatchResps = this.unpfOrdDeliverBatchSV.queryUnpfOrdDeliveryBatch(rUnpfOrdMainReq);
        if(CollectionUtils.isNotEmpty(rUnpfOrdDeliveryBatchResps)){
            rUnpfOrdMainResp.setrUnpfOrdDeliveryBatchResps(rUnpfOrdDeliveryBatchResps);
        }

        return rUnpfOrdMainResp;
    }

    @Override
    public UnpfOrdMain queryUnpfOrdMainIn(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException {

        UnpfOrdMainCriteria uodc = new UnpfOrdMainCriteria();
        UnpfOrdMainCriteria.Criteria ca = uodc.createCriteria();
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getId()) ){
            ca.andIdEqualTo(rUnpfOrdMainReq.getId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getOuterId())){
            ca.andOuterIdEqualTo(rUnpfOrdMainReq.getOuterId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getPlatType())){
            ca.andPlatTypeEqualTo(rUnpfOrdMainReq.getPlatType());
        }
        List<UnpfOrdMain> unpfOrdMains = unpfOrdMainMapper.selectByExample(uodc);
        if(CollectionUtils.isEmpty(unpfOrdMains)){
            return null;
        }
        return unpfOrdMains.get(0);
    }

	@Override
	public void saveUnpfOrdMain(OrderReqDTO orderReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setShopId(orderReqDTO.getShopId());
		unpfShopAuthReqDTO.setPlatType(orderReqDTO.getPlatType());
		unpfShopAuthReqDTO.setStatus("1");
		unpfShopAuthReqDTO.setId(orderReqDTO.getAuthId());
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
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, orderReqDTO, null,false);
		
		OrderRespDTO orderDetial = ordDetialRSV.queryOrderDetail(orderReqDTO);
		
		//保存日志
		unpfOrdLogSV.saveUnpfOrdLog(orderDetial);
		
		RUnpfOrdMainReq req = new RUnpfOrdMainReq();
		List<RUnpfOrdSubReq> subOrders = this.copyDetialToOrderMain(orderDetial, req);	
		//保存主订单信息
		UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
        ObjectCopyUtil.copyObjValue(req,unpfOrdMain,null,false);

		UnpfThingLock thingLock = new UnpfThingLock();
		thingLock.setId(unpfOrdMain.getOuterId());
		thingLock.setType(UnpfOrdConstants.LockType.LOCK_TYPE_TMALL_ORD);
		//加锁
		this.unpfThingLockSV.addThingLock(thingLock);
    
       try{
    	//查询该订单是否存在，若存在则修改
    	   UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
    	   criteria.createCriteria().andOuterIdEqualTo(unpfOrdMain.getOuterId());	
    	  
    	   Long num = unpfOrdMainMapper.countByExample(criteria);
    	   if(num.longValue()>0l){    		  
    		   List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
        	   if(list!=null){
        		   UnpfOrdMain orderMain = list.get(0);
        		   unpfOrdMain.setId(orderMain.getId());
	    		   unpfOrdMain.setUpdateStaff(1000L);
	    		   unpfOrdMain.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	    		   criteria.createCriteria().andIdEqualTo(orderMain.getId());
				   unpfOrdMainMapper.updateByExample(unpfOrdMain, criteria);
				 
				   //修改子订单	   
				   for(int i=0;i<subOrders.size();i++){
		    		   RUnpfOrdSubReq subOrder = subOrders.get(i);
					   UnpfOrdSubCriteria subCriteria = new UnpfOrdSubCriteria();
					   subCriteria.createCriteria().andOuterSubIdEqualTo(subOrder.getOuterSubId());
					   List<UnpfOrdSub> subList = unpfOrdSubMapper.selectByExample(subCriteria);
					   if(subList!=null){
						   UnpfOrdSub ordSub = subList.get(0);
						   subOrder.setId(ordSub.getId());
						   subOrder.setUpdateTime(new Timestamp(System.currentTimeMillis()));
						   ObjectCopyUtil.copyObjValue(subOrder, ordSub,  null, false);
						   if(StringUtils.isNotEmpty(subOrder.getDiscountFee())){
							   Long discountMoney = new Double(Double.parseDouble(subOrder.getDiscountFee())*100).longValue();
							   ordSub.setDiscountMoney(discountMoney);
						   }
						   if(StringUtils.isNotEmpty(subOrder.getRealMoney())){
							   Long realMoney = new Double(Double.parseDouble(subOrder.getRealMoney())*100).longValue();
							   ordSub.setRealMoney(realMoney);
						   }
						   if(StringUtils.isNotEmpty(subOrder.getOrderMoney())){
							   Long orderMoney = new Double(Double.parseDouble(subOrder.getOrderMoney())*100).longValue();
							   ordSub.setOrderMoney(orderMoney);
						   }
						   subCriteria.createCriteria().andIdEqualTo(ordSub.getId());
						   unpfOrdSubMapper.updateByExample(ordSub, subCriteria);	
						   subOrders.get(i).setId(ordSub.getId());
						   subOrders.get(i).setOrderId(orderMain.getId());
					   }
				   }				  
				   //保存天猫订单表
				   unpfTmOrdMainSV.updateUnpfTmOrdMain(orderDetial);				
        	   }
    	   }else{    
    		   DateFormat dfmt = new SimpleDateFormat("yyMMdd");
    		   String nowDate = dfmt.format(new Date());
    		   unpfOrdMain.setId("UN" + nowDate + StringUtil.lPad(seqUnpfOrdMain.nextValue().toString(), "0", 8));
    		   
    		   this.unpfOrdMainMapper.insert(unpfOrdMain);
    		  
    		   //保存子订单信息
    		   for(int i=0;i<subOrders.size();i++){
    			   RUnpfOrdSubReq subOrder = subOrders.get(i);
    		       subOrder.setOrderId(unpfOrdMain.getId());
    		       unpfOrdSubSV.saveUnpfOrdSub(subOrder);
    		       subOrders.get(i).setId(subOrder.getId());
    	       }    	      
			   //保存天猫订单表
			   unpfTmOrdMainSV.saveUnpfTmOrdMain(orderDetial);
    	   }
    	   //保存到erp
		   unpfErpOrderSV.saveErpOrder(unpfOrdMain,subOrders,orderDetial.getBuyerNick());

       }catch(Exception e){
    	   LogUtil.error(MODULE,"=============UnpfOrdMainSVImpl.saveUnpfOrdMain error =============",e);
       }
		//解锁
		this.unpfThingLockSV.removeThingLock(thingLock);
	}
	
	@Override
	public void saveSimpleUnpfOrdMain(OrderReqDTO orderReqDTO) throws BusinessException {
		// TODO Auto-generated method stub
		UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
		unpfShopAuthReqDTO.setShopId(orderReqDTO.getShopId());
		unpfShopAuthReqDTO.setPlatType(orderReqDTO.getPlatType());
		unpfShopAuthReqDTO.setStatus("1");
		unpfShopAuthReqDTO.setId(orderReqDTO.getAuthId());
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
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, orderReqDTO, null,false);		
		
		OrderRespDTO orderDetial = ordDetialRSV.querySimpleOrderDetail(orderReqDTO);
				
		//保存日志
		unpfOrdLogSV.saveUnpfOrdLog(orderDetial);
				
		RUnpfOrdMainReq req = new RUnpfOrdMainReq();
		List<RUnpfOrdSubReq> subOrders = this.copyDetialToOrderMain(orderDetial, req);	
		//保存主订单信息
		UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
        ObjectCopyUtil.copyObjValue(req,unpfOrdMain,null,false);
       
       try{
    	//查询该订单是否存在，若存在则修改
    	   UnpfOrdMainCriteria criteria = new UnpfOrdMainCriteria();
    	   criteria.createCriteria().andOuterIdEqualTo(unpfOrdMain.getOuterId());	
    	  
    	   Long num = unpfOrdMainMapper.countByExample(criteria);
    	   if(num.longValue()>0l){    		  
    		   List<UnpfOrdMain> list = unpfOrdMainMapper.selectByExample(criteria);
        	   if(list!=null&&list.size()>0){
        		   UnpfOrdMain orderMain = list.get(0);
        		   unpfOrdMain.setId(orderMain.getId());
	    		   unpfOrdMain.setUpdateStaff(1000L);
	    		   unpfOrdMain.setUpdateTime(new Timestamp(System.currentTimeMillis()));
	    		   criteria.createCriteria().andIdEqualTo(orderMain.getId());
	    		   unpfOrdMainMapper.updateByExample(unpfOrdMain, criteria);
				   //保存天猫订单表
				   unpfTmOrdMainSV.updateUnpfTmOrdMain(orderDetial);
				   //修改子订单	   
	    		   for(int i=0;i<subOrders.size();i++){
	    			   RUnpfOrdSubReq subOrder = subOrders.get(i);				 
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
						   subOrders.get(i).setId(subOrder.getId());
						   subOrders.get(i).setOrderId(orderMain.getId());
					   }
				   }				   
        	   }        	  
    	   }else{    
    		   DateFormat dfmt = new SimpleDateFormat("yyMMdd");
    		   String nowDate = dfmt.format(new Date());
    		   unpfOrdMain.setId("UN" + nowDate + StringUtil.lPad(seqUnpfOrdMain.nextValue().toString(), "0", 8));
    		   
    		   this.unpfOrdMainMapper.insert(unpfOrdMain);
    		  
    		   //保存子订单信息
    		 //保存子订单信息
    		   for(int i=0;i<subOrders.size();i++){
    			   RUnpfOrdSubReq subOrder = subOrders.get(i);
    		       subOrder.setOrderId(unpfOrdMain.getId());
    		       unpfOrdSubSV.saveUnpfOrdSub(subOrder);
    		       UnpfOrdSub unpfOrdSub = new UnpfOrdSub();
    		       ObjectCopyUtil.copyObjValue(subOrder,unpfOrdSub,null,false);
    		       subOrders.get(i).setId(subOrder.getId());
    	       }
    	       //保存天猫订单表
			   unpfTmOrdMainSV.saveUnpfTmOrdMain(orderDetial);    	      
    	   }
    	   //保存到erp
		   unpfErpOrderSV.saveErpOrder(unpfOrdMain,subOrders,orderDetial.getBuyerNick());
       }catch(Exception e){
    	   LogUtil.error(MODULE,"=============UnpfOrdMainSVImpl.saveSimpleUnpfOrdMain error =============",e);
       }

	}
	
	@Override
	public List<RUnpfOrdSubReq> copyDetialToOrderMain(OrderRespDTO orderDetial,RUnpfOrdMainReq unpfOrdMainReq){
		List<RUnpfOrdSubReq> list = new ArrayList<RUnpfOrdSubReq>();
		
		//构造主订单
		unpfOrdMainReq.setOuterId(orderDetial.getOrderId());//交易订单号
		//unpfOrdMainReq.setAppFlag(appFlag);
		//unpfOrdMainReq.setAppName(appName);
		//unpfOrdMainReq.setBegDate(begDate);
		unpfOrdMainReq.setBuyerMsg(orderDetial.getBuyerMemo());
		//unpfOrdMainReq.setCloseReason(closeReason);
		unpfOrdMainReq.setContractAddr(orderDetial.getReceiverAddress());
		unpfOrdMainReq.setContractCity(orderDetial.getReceiverCity());
		unpfOrdMainReq.setContractDistrict(orderDetial.getReceiverDistrict());
		unpfOrdMainReq.setContractName(orderDetial.getReceiverName());
		unpfOrdMainReq.setContractNum(orderDetial.getReceiverMobile());
		unpfOrdMainReq.setContractProvince(orderDetial.getReceiverState());
		unpfOrdMainReq.setContractTel(orderDetial.getReceiverPhone());
		unpfOrdMainReq.setContractZipcode(orderDetial.getReceiverZip());
		if(StringUtil.isNotEmpty(orderDetial.getCreated())){
			unpfOrdMainReq.setCreateTime(new Timestamp(orderDetial.getCreated().getTime()));
		}
		if(StringUtil.isNotEmpty(orderDetial.getConsignTime())){
			unpfOrdMainReq.setDispatchTime(new Timestamp(orderDetial.getConsignTime().getTime()));
		}
		
		//unpfOrdMainReq.setEndDate(endDate);
		//unpfOrdMainReq.setExpressCompany(expressCompany);
		//unpfOrdMainReq.setExpressNo(expressNo);
		unpfOrdMainReq.setImportTime(new Timestamp(System.currentTimeMillis()));//导入时间
		unpfOrdMainReq.setInvoiceTitle(orderDetial.getInvoiceName());//发票抬头
		
		//unpfOrdMainReq.setOrderCode(orderCode);
		//
		
		unpfOrdMainReq.setParamid(orderDetial.getParamId());
		if(StringUtil.isNotEmpty(orderDetial.getPayTime())){
			unpfOrdMainReq.setPayTime(new Timestamp(orderDetial.getPayTime().getTime()));			
		}
		if(StringUtil.isNotEmpty(orderDetial.getCreated())){
			unpfOrdMainReq.setOrderTime(new Timestamp(orderDetial.getCreated().getTime()));
		}
		unpfOrdMainReq.setPlatType(orderDetial.getPlatType());
		if(StringUtil.isNotEmpty(orderDetial.getPostFee())){
			Long postFee = new Double(Double.parseDouble(orderDetial.getPostFee())*100).longValue();
			unpfOrdMainReq.setRealExpressFee(postFee);//邮费
		}
		if(StringUtil.isNotEmpty(orderDetial.getPayment())){
			Long payment = new Double(Double.parseDouble(orderDetial.getPayment())*100).longValue();
			unpfOrdMainReq.setRealMoney(payment);
		}
		
		unpfOrdMainReq.setShopId(orderDetial.getShopId());
		unpfOrdMainReq.setShopName(orderDetial.getSellerNick());//店铺昵称
		if(orderDetial.getBuyerNick()!=null){
			CustThirdCodeReqDTO custThirdCodeReqDTO = new CustThirdCodeReqDTO();
			custThirdCodeReqDTO.setThirdCode(orderDetial.getBuyerNick());
			custThirdCodeReqDTO.setThirdCodeType("10");
			CustThirdCodeResDTO  custThirdCodeResDTO = custThirdCodeRSV.queryThirdCode(custThirdCodeReqDTO);
			if(StringUtil.isNotEmpty(custThirdCodeResDTO)){
				unpfOrdMainReq.setCreateStaff(custThirdCodeResDTO.getStaffId());
				unpfOrdMainReq.setStaffCode(custThirdCodeResDTO.getStaffCode());
			}else{
				unpfOrdMainReq.setCreateStaff(1000L);
			}				
		}

		//订单状态转换
		if(orderDetial.getPlatType().equals("taobao")){
			unpfOrdMainReq.setStatus(convertTaoBaoOrderStatus(orderDetial.getStatus()));
		}else if(orderDetial.getPlatType().equals("youzan")){
			unpfOrdMainReq.setStatus(convertYouZanOrderStatus(orderDetial.getStatus()));
		}else if(orderDetial.getPlatType().equals("jd")){
			unpfOrdMainReq.setStatus(convertJDOrderStatus(orderDetial.getStatus()));
		}
		
		if(StringUtil.isNotBlank(orderDetial.getShippingType())){
			if(orderDetial.getShippingType().equals("post")){//平邮
				unpfOrdMainReq.setDispatchType("0");
			}else if(orderDetial.getShippingType().equals("virtual")){//虚拟发货
				unpfOrdMainReq.setStatus(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);
				//虚拟发货物流方式置空
			}else{//其他方式则为快递
				unpfOrdMainReq.setDispatchType("1");
			}
		}
		
		//unpfOrdMainReq.setSysFlag(sysFlag);
		//unpfOrdMainReq.setUpdateStaff(updateStaff);
		//unpfOrdMainReq.setUpdateTime(updateTime);
		
		boolean isSysFlag = false;
		boolean isOuterSysFlag = false;
		Long orderAmount = 0L;
		//构造子订单
		if(CollectionUtils.isNotEmpty(orderDetial.getSubOrders())){
			for(SubOrder order:orderDetial.getSubOrders()){
				RUnpfOrdSubReq subOrder = new RUnpfOrdSubReq();
				
				//subOrder.setCreateStaff(createStaff);

				if(StringUtil.isNotEmpty(orderDetial.getCreated())){
					subOrder.setCreateTime(new Timestamp(orderDetial.getCreated().getTime()));
				}
				subOrder.setCreateStaff(unpfOrdMainReq.getCreateStaff());
				subOrder.setOuterId(orderDetial.getOrderId());
				subOrder.setOuterSubId(""+order.getOid());
				//subOrder.setDeliveryAmount(deliveryAmount);
				//subOrder.setDeliveryStatus(deliveryStatus);
				subOrder.setGdsId(order.getOuterIid());
				subOrder.setGdsName(order.getTitle());
				subOrder.setPicUrl(order.getPicPath());
				if(order.getNum()!=null){
					orderAmount = orderAmount+order.getNum();
					subOrder.setOrderAmount(""+order.getNum());
				}
				subOrder.setOrderPrice(order.getPrice());
				if(StringUtil.isNotEmpty(order.getPrice())){
					Long price = new Double(Double.parseDouble(order.getPrice())*100).longValue();
					subOrder.setOrderPrice(""+price);
				}
				if(StringUtil.isNotEmpty(orderDetial.getPayTime())){
					subOrder.setOrderTime(new Timestamp(orderDetial.getPayTime().getTime()));
				}
				//subOrder.setOuterId(order.getOuterIid());
				//subOrder.setOuterSubId(order.getOuterIid());
				subOrder.setPlatType(orderDetial.getPlatType());
				//subOrder.setRemark(remark);
				subOrder.setShopId(orderDetial.getShopId());
				subOrder.setSkuId(order.getOuterSkuId());
				subOrder.setDiscountFee(order.getDiscountFee());
				subOrder.setRealMoney(order.getPayment());
				subOrder.setOrderMoney(order.getTotalFee());
				//判断是否本系统的商品
				UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
				unpfGdsSendReqDTO.setPlatType(orderDetial.getPlatType());
				unpfGdsSendReqDTO.setOutGdsId(String.valueOf(orderDetial.getNumIid()));
				List<UnpfGdsSend> unpfGdsSendList = unpfGoodSendSV.queryGdsSendByOuterId(unpfGdsSendReqDTO);
				if(CollectionUtils.isNotEmpty(unpfGdsSendList)){
					isSysFlag = true;
					subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
					subOrder.setGdsId(""+unpfGdsSendList.get(0).getGdsId());
					subOrder.setGdsName(order.getTitle());
//					//获取商品信息
//					GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
//					gdsInfo.setId(unpfGdsSendList.get(0).getGdsId());
//					GdsInfoDetailRespDTO gdsinfo = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfo);
//					if(gdsinfo != null){
//						subOrder.setGdsName(""+gdsinfo.getGdsName());
//					}
					LogUtil.debug(MODULE, "numIid"+orderDetial.getNumIid()+"----本系统商品"+"----");
				} else {
					isOuterSysFlag = true;
					subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
					subOrder.setGdsId(order.getNumIid());
					subOrder.setGdsName(order.getTitle());
					LogUtil.debug(MODULE, "numIid"+orderDetial.getNumIid()+"----外系统商品"+"----");
				}
//				if(StringUtil.isNotBlank(order.getOuterIid())){
//					if(StringUtils.isNumeric(order.getOuterIid())){
//						//获取商品信息
//						GdsInfoReqDTO gdsInfo = new GdsInfoReqDTO();
//						gdsInfo.setId(Long.parseLong(order.getOuterIid()));
//						GdsInfoDetailRespDTO gdsinfo = gdsInfoQueryRSV.queryGdsInfoDetail(gdsInfo);
//						if(gdsinfo==null){
//							//若为空则为系统外部商品
//							isSysFlag = false;
//							subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//							subOrder.setGdsId(order.getOuterIid());
//							subOrder.setGdsName(order.getTitle());
//							LogUtil.debug(MODULE, "----外系统商品，商品gdsId为："+order.getOuterIid()+"，商品名称为"+order.getTitle()+"----");
//						}else{
//							subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
//							//保存gdsId和gdsName
//							subOrder.setGdsId(""+gdsinfo.getId());
//							subOrder.setGdsName(""+gdsinfo.getGdsName());
//						}
//					}else if(order.getOuterIid().startsWith("P")||order.getOuterIid().startsWith("p")){//防止旧数据有小写p存在
//						GdsSku2PropPropIdxReqDTO reqDTO = new GdsSku2PropPropIdxReqDTO();
//						String propValue = order.getOuterIid().substring(1, order.getOuterIid().length());
//						reqDTO.setPropValue(propValue);
//						reqDTO.setPropId(Long.parseLong(UnpfConstants.ZpProp.PROP_ID));
//
//						PageResponseDTO<GdsSkuInfoRespDTO> page = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(reqDTO);
//						if(page!=null&&page.getResult()!=null&&page.getResult().size()>0){
//							GdsSkuInfoRespDTO skuInfo = page.getResult().get(0);
//							if(skuInfo==null||skuInfo.getId()==null){
//								isSysFlag = false;
//								subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//								subOrder.setGdsId(order.getOuterIid());
//								subOrder.setGdsName(order.getTitle());
//								LogUtil.debug(MODULE, "----外系统商品，商品gdsId为："+order.getOuterIid()+"，商品名称为"+order.getTitle()+"----");
//							}else{
//								subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
//								//保存gdsId和gdsName
//								subOrder.setGdsId(""+skuInfo.getGdsId());
//								subOrder.setGdsName(""+order.getTitle());
//							}
//						}else{
//							isSysFlag = false;
//							subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//							subOrder.setGdsId(order.getOuterIid());
//							subOrder.setGdsName(order.getTitle());
//							LogUtil.debug(MODULE, "----外系统商品，商品gdsId为："+order.getOuterIid()+"，商品名称为"+order.getTitle()+"----");
//						}
//					}else{
//						isSysFlag = false;
//						subOrder.setGdsName(order.getTitle());
//						subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//						LogUtil.debug(MODULE, "----外系统商品，商品gdsId为空，商品名称为"+order.getTitle()+"----");
//					}
//			    }else{
//					isSysFlag = false;
//					subOrder.setGdsName(order.getTitle());
//					subOrder.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//					LogUtil.debug(MODULE, "----外系统商品，商品gdsId为空，商品名称为"+order.getTitle()+"----");
//				}
				
				//订单状态转换
				if(orderDetial.getPlatType().equals("taobao")){
					subOrder.setStatus(convertTaoBaoOrderStatus(order.getStatus()));
				}else if(orderDetial.getPlatType().equals("youzan")){
					subOrder.setStatus(convertYouZanOrderStatus(order.getStatus()));
				}else if(orderDetial.getPlatType().equals("jd")){
					subOrder.setStatus(convertJDOrderStatus(order.getStatus()));
				}			
				//subOrder.setUpdateStaff(updateStaff);
				//subOrder.setUpdateTime(updateTime);

				list.add(subOrder);
			}
			if(StringUtil.isEmpty(orderDetial.getNum())){
				unpfOrdMainReq.setOrderAmount(""+orderAmount);//订单商品数量
			}else{
				unpfOrdMainReq.setOrderAmount(""+orderDetial.getNum());
			}
		}
		//判断是否系统商品
		if(isSysFlag && !isOuterSysFlag){ //全部本系统商品
			unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
		} else if(isSysFlag && isOuterSysFlag){ //部分是本系统商品
			unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.PART_SYSORDER);
		} else {  //都不是本系统商品
			unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
		}
//		if(isSysFlag){
//			unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.SYSORDER);
//		}else{
//			boolean no_sysorder = true;
//			for(RUnpfOrdSubReq subOrder:list){
//				if(!subOrder.getSysFlag().equals(UnpfOrdConstants.IsSysFlag.NO_SYSORDER)){
//					no_sysorder=false;
//					break;
//				}
//			}
//			if(no_sysorder){
//				unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.NO_SYSORDER);
//			}else{
//				unpfOrdMainReq.setSysFlag(UnpfOrdConstants.IsSysFlag.PART_SYSORDER);
//			}
//		}
		return list;
	}

    @Override
    public void saveUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException{
        UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
        ObjectCopyUtil.copyObjValue(rUnpfOrdMainReq,unpfOrdMain,null,false);
        DateFormat dfmt = new SimpleDateFormat("yyMMdd");
        String nowDate = dfmt.format(new Date());
        unpfOrdMain.setId("UN" + nowDate + StringUtil.lPad(seqUnpfOrdMain.nextValue().toString(), "0", 8));
        this.unpfOrdMainMapper.insert(unpfOrdMain);
    }

    @Override
    public void updateUnpfOrdMain(RUnpfOrdMainReq rUnpfOrdMainReq) throws BusinessException{
        UnpfOrdMain unpfOrdMain = new UnpfOrdMain();
        ObjectCopyUtil.copyObjValue(rUnpfOrdMainReq,unpfOrdMain,null,false);
        unpfOrdMain.setUpdateTime(DateUtil.getSysDate());

        UnpfOrdMainCriteria uodc = new UnpfOrdMainCriteria();
        uodc.createCriteria().andIdEqualTo(rUnpfOrdMainReq.getId());
        this.unpfOrdMainMapper.updateByExampleSelective(unpfOrdMain,uodc);
    }

    @Override
    public PageResponseDTO<RUnpfOrdMainResp> queryUnpfOrdMainByPage(RUnpfOrdMainReq rUnpfOrdMainReq,boolean isDeliver) throws BusinessException {

        UnpfOrdMainCriteria uodc = new UnpfOrdMainCriteria();
        uodc.setLimitClauseCount(rUnpfOrdMainReq.getPageSize());
        uodc.setLimitClauseStart(rUnpfOrdMainReq.getStartRowIndex());
        uodc.setOrderByClause("order_time desc");
        UnpfOrdMainCriteria.Criteria ca = uodc.createCriteria();
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getId())){
            ca.andIdEqualTo(rUnpfOrdMainReq.getId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getOuterId())){
            ca.andOuterIdEqualTo(rUnpfOrdMainReq.getOuterId());
        }
        if(StringUtil.isNotBlank(rUnpfOrdMainReq.getPlatType())){
            ca.andPlatTypeEqualTo(rUnpfOrdMainReq.getPlatType());
        }
        if(rUnpfOrdMainReq.getShopId() != null){
            ca.andShopIdEqualTo(rUnpfOrdMainReq.getShopId());
        }
        if(rUnpfOrdMainReq.getBegDate() != null){
            ca.andOrderTimeGreaterThan(rUnpfOrdMainReq.getBegDate());
        }
        if(rUnpfOrdMainReq.getEndDate() != null){
            ca.andOrderTimeLessThan(rUnpfOrdMainReq.getEndDate());
        }
		if(StringUtil.isNotBlank(rUnpfOrdMainReq.getDispatchType())){
			ca.andDispatchTypeEqualTo(rUnpfOrdMainReq.getDispatchType());
		}
		if(StringUtil.isNotBlank(rUnpfOrdMainReq.getSysFlag())){
			if("1".equals(rUnpfOrdMainReq.getSysFlag())) {
				ca.andSysFlagEqualTo(rUnpfOrdMainReq.getSysFlag());
			} else if("2".equals(rUnpfOrdMainReq.getSysFlag())){
				List<String> flag  = new ArrayList<>();
				flag.add("0");
				flag.add("2");
				ca.andSysFlagIn(flag);
			}

		}
        //发货订单查询专用
        if(isDeliver){
            List<String> status = new ArrayList<String>();
            if("0".equals(rUnpfOrdMainReq.getStatus())){ //待发货订单查询
                status.add(UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID);  //支付完成
                status.add(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION); //部分发货
                ca.andStatusIn(status);
            } else if("1".equals(rUnpfOrdMainReq.getStatus())){  //已发货订单查询
                status.add(UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL);  //已发货
                status.add(UnpfOrdConstants.OrderStatus.ORDER_STATUS_RECEPT); //已收货
                status.add(UnpfOrdConstants.OrderStatus.ORDER_STATUS_CLOSE); //关闭
                ca.andStatusIn(status);
            }
        } else {
			if(StringUtil.isNotBlank(rUnpfOrdMainReq.getStatus())){
				ca.andStatusEqualTo(rUnpfOrdMainReq.getStatus());
			}
		}

        return super.queryByPagination(rUnpfOrdMainReq, uodc, true, new PaginationCallback<UnpfOrdMain, RUnpfOrdMainResp>() {
            @Override
            public List<Comparator<UnpfOrdMain>> defineComparators() {
                List<Comparator<UnpfOrdMain>> ls = new ArrayList<Comparator<UnpfOrdMain>>();
                ls.add(new Comparator<UnpfOrdMain>(){
                    @Override
                    public int compare(UnpfOrdMain o1, UnpfOrdMain o2) {
                        return o2.getOrderTime().compareTo(o1.getOrderTime());
                    }
                });
                return ls;
            }

            @Override
            public List<UnpfOrdMain> queryDB(BaseCriteria baseCriteria) {
                return unpfOrdMainMapper.selectByExample((UnpfOrdMainCriteria)baseCriteria);
            }

            @Override
            public long queryTotal(BaseCriteria baseCriteria) {
                return unpfOrdMainMapper.countByExample((UnpfOrdMainCriteria)baseCriteria);
            }

            @Override
            public RUnpfOrdMainResp warpReturnObject(UnpfOrdMain unpfOrdMain) {
                RUnpfOrdMainResp rUnpfOrdMainResp = new RUnpfOrdMainResp();
                ObjectCopyUtil.copyObjValue(unpfOrdMain,rUnpfOrdMainResp,null,false);
                return rUnpfOrdMainResp;
            }
        });
    }
    
    //转换天猫订单状态
    private String convertTaoBaoOrderStatus(String status){
    	String orderStatus = "";
    	switch (status){
	    	case  "TRADE_NO_CREATE_PAY" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_OTHER;
	    		break;
	    	}
	    	case  "WAIT_BUYER_PAY" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT;
	    		break;
	    	}
	    	case  "SELLER_CONSIGNED_PART" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_SECTION;
	    		break;
	    	}
	    	case  "WAIT_SELLER_SEND_GOODS" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_PAID;
	    		break;
	    	}
	    	case  "WAIT_BUYER_CONFIRM_GOODS" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_SEND_ALL;
	    		break;
	    	}
	    	case  "TRADE_BUYER_SIGNED" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_RECEPT;
	    		break;
	    	}
	    	case  "TRADE_CLOSED" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_CLOSE;
	    		break;
	    	}
	    	case  "TRADE_CLOSED_BY_TAOBAO" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_CANCLE;
	    		break;
    		}
	    	case  "PAY_PENDING" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_SUBMIT;
	    		break;
	    	}
	    	case  "TRADE_FINISHED" :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_RECEPT;
	    		break;
	    	}
	    	default :{
	    		orderStatus = UnpfOrdConstants.OrderStatus.ORDER_STATUS_OTHER;
	    		break;
	    	}
    	}
    	return orderStatus;
    }
    @Override
    public List<UnpfOrdMain> queryUnpfOrdMainByThread(RUnpfQueryOrderReq rUnpfQueryOrderReq) throws BusinessException {
    	UnpfOrdMainCriteria uomsic = createCriteria(rUnpfQueryOrderReq);

        BaseSysCfgRespDTO sysCfg = SysCfgUtil.fetchSysCfg("SYS_TABLE_INDEX_COUNT");
        int cnt = Integer.parseInt(sysCfg.getParaValue());

        List<UnpfOrdMain> ordMainList = new ArrayList<>();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(16,32,5, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(64));
        List<Future> fus = new ArrayList<Future>();
        for (int i = 1; i < cnt+1; i++) {
            Future f = executor.submit(new UnpfOrdMainSVImpl.DealQueryUnpfOrderThread(i,uomsic,rUnpfQueryOrderReq.getExportKey()));
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
        for (List<UnpfOrdMain> v : unpfOrdMainMap.values()) {
            LogUtil.info(MODULE,"子列表数量："+v.size());
            ordMainList.addAll(v);
        }
        LogUtil.info(MODULE,"总数量："+ordMainList.size());

        return ordMainList;
    }
    
    private UnpfOrdMainCriteria createCriteria(RUnpfQueryOrderReq rUnpfQueryOrderReq){
    	UnpfOrdMainCriteria uomsic = new UnpfOrdMainCriteria();
    	uomsic.setLimitClauseCount(rUnpfQueryOrderReq.getPageSize());
        uomsic.setLimitClauseStart(rUnpfQueryOrderReq.getStartRowIndex());
        uomsic.setOrderByClause("id desc");
        UnpfOrdMainCriteria.Criteria ca = uomsic.createCriteria();
        if(rUnpfQueryOrderReq.getBegDate() != null){
            ca.andOrderTimeGreaterThanOrEqualTo(rUnpfQueryOrderReq.getBegDate());
        }
        if(rUnpfQueryOrderReq.getEndDate() != null){
            ca.andOrderTimeLessThanOrEqualTo(rUnpfQueryOrderReq.getEndDate());
        }
        if(StringUtil.isNotBlank(rUnpfQueryOrderReq.getOuterId())){
            ca.andOuterIdEqualTo(rUnpfQueryOrderReq.getOuterId());
        }
        if(StringUtil.isNotBlank(rUnpfQueryOrderReq.getStatus())){
        	ca.andStatusEqualTo(rUnpfQueryOrderReq.getStatus());
        }
      
        if (LongUtils.isNotEmpty(rUnpfQueryOrderReq.getShopId())) {
            ca.andShopIdEqualTo(rUnpfQueryOrderReq.getShopId());
        }
        if (StringUtil.isNotBlank(rUnpfQueryOrderReq.getPlatType())) {
            ca.andPlatTypeEqualTo(rUnpfQueryOrderReq.getPlatType());
        }
       
        return uomsic;
    }
    //转换有赞订单状态
    private String convertYouZanOrderStatus(String status){
    	return "";
    }
    
    //转换京东订单状态
    private String convertJDOrderStatus(String status){
    	return "";
    }

    public class DealQueryUnpfOrderThread extends Thread  {

        private int tableIndex;

        private UnpfOrdMainCriteria unpfOrdMainCriteria;

        private Long key;

        public DealQueryUnpfOrderThread(int tableIndex,UnpfOrdMainCriteria unpfOrdMainCriteria,Long key){
            this.tableIndex = tableIndex;
            this.unpfOrdMainCriteria = unpfOrdMainCriteria;
            this.key = key;
        }

        @Override
        public void run() {
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":开始处理");
            DistributeRuleAssist.setTableIndex(tableIndex);
            List<UnpfOrdMain> unpfOrdMains = unpfOrdMainMapper.selectByExample(unpfOrdMainCriteria);
            unpfOrdMainMap.put(tableIndex,unpfOrdMains);
            DistributeRuleAssist.clearTableIndex();
            OrdFileImportDTO ordFile = new OrdFileImportDTO();
            ordFile.setId(key);
            ordFile.setShopId(8L);  //文件进度
            ordFileImportRSV.updateById(ordFile);
            LogUtil.info(MODULE,"销售明细导出子线程"+tableIndex+":结束处理");
        }
    }

}
