package com.zengshi.ecp.order.service.busi.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.interfaces.IStaffHotlineRSV;
import com.zengshi.ecp.order.dao.mapper.busi.OrdMainMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdOfflineMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubMapper;
import com.zengshi.ecp.order.dao.mapper.busi.OrdSubShopIdxMapper;
import com.zengshi.ecp.order.dao.mapper.common.ImOrdBelongMapper;
import com.zengshi.ecp.order.dao.model.ImOrdBelong;
import com.zengshi.ecp.order.dao.model.ImOrdBelongCriteria;
import com.zengshi.ecp.order.dao.model.OrdMain;
import com.zengshi.ecp.order.dao.model.OrdOffline;
import com.zengshi.ecp.order.dao.model.OrdOfflineCriteria;
import com.zengshi.ecp.order.dao.model.OrdSub;
import com.zengshi.ecp.order.dao.model.OrdSubShopIdx;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleRequest;
import com.zengshi.ecp.order.dubbo.dto.RGoodSaleResponse;
import com.zengshi.ecp.order.dubbo.impl.OrdOfflineBelongRSVImpl;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdBackApplySV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdMainSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdOfflineBelongSV;
import com.zengshi.ecp.order.service.busi.interfaces.IOrdSubShopIdxSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class OrdOfflineBelongSVImpl implements IOrdOfflineBelongSV{

	public static final String MODULE = OrdOfflineBelongRSVImpl.class.getName();

	@Resource
	private OrdOfflineMapper ordOfflineMapper;

	@Resource
	private IAuthStaffRSV iAuthStaffRSV;

	@Resource
	private IOrdMainSV ordMainSV;

	@Resource
	private ImOrdBelongMapper imOrdBelongMapper;

	@Resource
	private IStaffHotlineRSV staffHotlineRSV;
	
	@Resource
	private IOrdSubShopIdxSV iOrdSubShopIdxSV;
	
	@Resource
	private OrdMainMapper ordMainMapper;
	
	@Resource
	private OrdSubShopIdxMapper ordSubShopIdxMapper;
	
	@Resource
	private OrdSubMapper ordSubMapper;
	
	@Autowired(required = false)
    private IOrdBackApplySV ordBackApplySV;

	@Override
	public void dealOrdOffline() {
		OrdOfflineCriteria oocc = new OrdOfflineCriteria();
		oocc.createCriteria().andStatusEqualTo("1");
		List<OrdOffline> oos = this.ordOfflineMapper.selectByExample(oocc);
		if (oos != null && oos.size() != 0) {
			LogUtil.info(MODULE, OrdOfflineBelongRSVImpl.class + ":-----开始-----");
			for (OrdOffline o : oos) {
				try { 
					AuthStaffResDTO adto = iAuthStaffRSV.findAuthStaffById(o.getUpdateStaff());
					ImStaffHotlineReqDTO dto = new ImStaffHotlineReqDTO();
					dto.setStaffCode(adto.getStaffCode());
					ImStaffHotlineResDTO resp = staffHotlineRSV.getStaffHotline(dto);
					// 判断当前staffId是否为客服，若为客服择保存
					if (resp != null && resp.getStaffId() != null && resp.getStaffId() > 0L) {
						//判断是否存在ImOrdBelong
						ImOrdBelongCriteria iobc = new ImOrdBelongCriteria();
						iobc.createCriteria().andOrdIdEqualTo(o.getOrderId());
						List<ImOrdBelong> lists = imOrdBelongMapper.selectByExample(iobc);
						if(lists!=null&&lists.size()>0){
							continue;
						}
						OrdMain ordMain = ordMainSV.queryOrderByOrderId(o.getOrderId());
						if (ordMain == null || ordMain.getId() == null) {
							LogUtil.info(MODULE, o.getOrderId() + "该订单不存在!");
							continue;
						}
						ImOrdBelong imOrdBelong = new ImOrdBelong();
						imOrdBelong.setOrdId(ordMain.getId());
						imOrdBelong.setBelongSerCode(resp.getOfStaffCode());
						imOrdBelong.setBelongSerName(resp.getHotlinePerson());
						imOrdBelong.setContactName(ordMain.getContactName());
						imOrdBelong.setMobileNumber(ordMain.getContactNumber());
						imOrdBelong.setOrdTime(ordMain.getOrderTime());
						imOrdBelong.setPayTime(ordMain.getPayTime());
						imOrdBelong.setRealMoney(ordMain.getRealMoney());
						imOrdBelong.setStaffCode(ordMain.getStaffCode());
						imOrdBelong.setStaffId(ordMain.getStaffId());
						imOrdBelongMapper.insertSelective(imOrdBelong);
					}
				} catch (Exception e) {
					//e.printStackTrace();
					LogUtil.info(MODULE,"id为:"+o.getId()+"异常");
				}
			}
			LogUtil.info(MODULE, OrdOfflineBelongRSVImpl.class + ":-----结束-----");
		}
	}

	@Override
	public long updateOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest) {
		PageResponseDTO<RGoodSaleResponse> page = iOrdSubShopIdxSV.queryOrdSubShopIdx1(rGoodSaleRequest);
		List<RGoodSaleResponse> rgsrList = page.getResult();
		if(rgsrList!=null&&rgsrList.size()!=0){
			LogUtil.info(MODULE,"第"+rGoodSaleRequest.getPageNo()+"页开始循环...");
			for(RGoodSaleResponse rgsr:rgsrList){
				LogUtil.info(MODULE,"数据orderId："+rgsr.getOrderId()+"开始执行");
				try{
					OrdMain om = ordMainMapper.selectByPrimaryKey(rgsr.getOrderId());
					if (om == null || om.getId() == null) {
						LogUtil.info(MODULE, rgsr.getOrderId() + "该订单不存在，略过!");
						continue;
					}
					OrdSub ordsub = ordSubMapper.selectByPrimaryKey(rgsr.getOrderSubId());
					if (ordsub == null || ordsub.getId() == null) {
						LogUtil.info(MODULE, rgsr.getOrderId() + "该订单不存在子订单，略过!");
						continue;
					}
					OrdSubShopIdx ordSubShopIdx = new OrdSubShopIdx();
					ordSubShopIdx.setId(rgsr.getIndexId());
					ordSubShopIdx.setPayType(om.getPayType());
					ordSubShopIdx.setBasePrice(ordsub.getBasePrice());
					ordSubShopIdx.setRealMoney(ordsub.getRealMoney());
					ordSubShopIdx.setHasBackNum(ordsub.getHasBackNum());
					ordSubShopIdx.setCategoryCode(ordsub.getCategoryCode());
					//计算已退款金额
					 if(StringUtil.isNotEmpty(ordsub.getHasBackNum())&&ordsub.getHasBackNum()>0l){
						 try{ 
							 double hadBackScale = ordBackApplySV.calCulateScaleApply(ordsub ,ordsub.getHasBackNum());				   		
							 //计算单品实际退款金额=实付金额*比例     
							 Long hadBackMoney = new BigDecimal(om.getRealMoney()*hadBackScale/1000000).longValue();    
							 ordSubShopIdx.setBackMoney(hadBackMoney);
						 }catch (Exception e) {
							 LogUtil.info(MODULE,"id为:"+rgsr.getIndexId()+"BackMoney异常");
							 ordSubShopIdx.setBackMoney(0L);
						}
				   	 }else{
				   		ordSubShopIdx.setBackMoney(0L);
				   	 }
					ordSubShopIdxMapper.updateByPrimaryKeySelective(ordSubShopIdx);
				}catch (Exception e) {
					//e.printStackTrace();
					LogUtil.info(MODULE,"id为:"+rgsr.getIndexId()+"异常");
				}
				LogUtil.info(MODULE,"数据orderId："+rgsr.getOrderId()+"结束执行");
			}
			LogUtil.info(MODULE,"第"+rGoodSaleRequest.getPageNo()+"页结束循环...");
		}	
		return page.getPageCount();
	}

	@Override
	public long updateAllOrdSubShopIdx(RGoodSaleRequest rGoodSaleRequest) {
		PageResponseDTO<RGoodSaleResponse> page = iOrdSubShopIdxSV.queryOrdSubShopIdx(rGoodSaleRequest);
		List<RGoodSaleResponse> rgsrList = page.getResult();
		if(rgsrList!=null&&rgsrList.size()!=0){
			LogUtil.info(MODULE,"第"+rGoodSaleRequest.getPageNo()+"页开始循环...");
			for(RGoodSaleResponse rgsr:rgsrList){
				LogUtil.info(MODULE,"数据orderId："+rgsr.getOrderId()+"开始执行");
				try{
					OrdMain om = ordMainMapper.selectByPrimaryKey(rgsr.getOrderId());
					if (om == null || om.getId() == null) {
						LogUtil.info(MODULE, rgsr.getOrderId() + "该订单不存在，略过!");
						continue;
					}
					OrdSub ordsub = ordSubMapper.selectByPrimaryKey(rgsr.getOrderSubId());
					if (ordsub == null || ordsub.getId() == null) {
						LogUtil.info(MODULE, rgsr.getOrderId() + "该订单不存在子订单，略过!");
						continue;
					}
					OrdSubShopIdx ordSubShopIdx = new OrdSubShopIdx();
					ordSubShopIdx.setId(rgsr.getIndexId());
					ordSubShopIdx.setPayType(om.getPayType());
					ordSubShopIdx.setBasePrice(ordsub.getBasePrice());
					ordSubShopIdx.setRealMoney(ordsub.getRealMoney());
					ordSubShopIdx.setHasBackNum(ordsub.getHasBackNum());
					ordSubShopIdx.setCategoryCode(ordsub.getCategoryCode());
					//计算已退款金额
					 if(StringUtil.isNotEmpty(ordsub.getHasBackNum())&&ordsub.getHasBackNum()>0l){
						 try{ 
							 double hadBackScale = ordBackApplySV.calCulateScaleApply(ordsub ,ordsub.getHasBackNum());				   		
							 //计算单品实际退款金额=实付金额*比例     
							 Long hadBackMoney = new BigDecimal(om.getRealMoney()*hadBackScale/1000000).longValue();    
							 ordSubShopIdx.setBackMoney(hadBackMoney);
						 }catch (Exception e) {
							 LogUtil.info(MODULE,"id为:"+rgsr.getIndexId()+"BackMoney异常");
							 ordSubShopIdx.setBackMoney(0L);
						}
				   	 }else{
				   		ordSubShopIdx.setBackMoney(0L);
				   	 }
					ordSubShopIdxMapper.updateByPrimaryKeySelective(ordSubShopIdx);
				}catch (Exception e) {
					//e.printStackTrace();
					LogUtil.info(MODULE,"id为:"+rgsr.getIndexId()+"异常");
				}
				LogUtil.info(MODULE,"数据orderId："+rgsr.getOrderId()+"结束执行");
			}
			LogUtil.info(MODULE,"第"+rGoodSaleRequest.getPageNo()+"页结束循环...");
		}	
		return page.getPageCount();
	}

}
