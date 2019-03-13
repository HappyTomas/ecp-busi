package com.zengshi.ecp.order.facade.impl.eventual;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.aip.dubbo.dto.ExpressRssReq;
import com.zengshi.ecp.aip.dubbo.dto.ExpressRssResp;
import com.zengshi.ecp.aip.dubbo.interfaces.IExpressRSV;
import com.zengshi.ecp.order.dao.mapper.common.BaseExpressKdMapper;
import com.zengshi.ecp.order.dao.mapper.common.BaseExpressKdSetMapper;
import com.zengshi.ecp.order.dao.model.BaseExpressKd;
import com.zengshi.ecp.order.dao.model.BaseExpressKdSet;
import com.zengshi.ecp.order.dao.model.BaseExpressKdSetCriteria;
import com.zengshi.ecp.order.dubbo.dto.OrdExpressLogReq;
import com.zengshi.ecp.order.dubbo.dto.RConfirmDeliveRequest;
import com.zengshi.ecp.order.dubbo.interfaces.IOrdExpressReqLogRSV;
import com.zengshi.ecp.order.dubbo.util.MsgConstants;
import com.zengshi.ecp.order.dubbo.util.OrdConstants;
import com.zengshi.ecp.order.facade.interfaces.eventual.IOrdDeliverExpressSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

import net.sf.json.JSONObject;

public class OrdDeliverExpressSVImpl implements IOrdDeliverExpressSV{

    private static final String MODULE = OrdDeliverExpressSVImpl.class.getName();
    
    @Resource
    private IExpressRSV expressRSV;
    
    @Resource
	private IOrdExpressReqLogRSV ordExpressReqLogRSV;
    
    @Resource
    private BaseExpressKdSetMapper baseExpressKdSetMapper;
    
    @Resource
    private BaseExpressKdMapper baseExpressKdMapper;
    
	@Override
	public void joinTransaction(JSONObject message, TransactionStatus status, String transctionName) {
		// TODO Auto-generated method stub
		try {
            final RConfirmDeliveRequest confirmDeliveRequest = JSON.parseObject(message.toString(), RConfirmDeliveRequest.class);
            //自提，则无需订阅
            if(confirmDeliveRequest.getDeliveryType().equals(OrdConstants.Order.ORDER_DELIVER_FLAG_FALSE)){
            	return;
            }
            LogUtil.info(MODULE,"OrdDeliverExpressSVImpl============="+confirmDeliveRequest.toString());
            dealMethod(confirmDeliveRequest);
        } catch (BusinessException be) {
            LogUtil.error(MODULE, "快递100接口订阅物流信息处理异常", be);
            be.printStackTrace();
            status.setRollbackOnly();
            throw new BusinessException(be.getErrorCode());
        } catch (Exception e) {
            LogUtil.error(MODULE, "快递100接口订阅物流信息处理异常", e);
            status.setRollbackOnly();
            throw new BusinessException(MsgConstants.OtherSysMsgCode.CALL_PROM_SERVER_341000);
        }
	}

	@Override
	public void dealMethod(RConfirmDeliveRequest confirmDeliveRequest) {
		// TODO Auto-generated method stub	
		ExpressRssReq req = null;
		ExpressRssResp resp = null;
		try {
			req = new ExpressRssReq();
			req.setOrderId(confirmDeliveRequest.getOrderId());
			req.setShopId(confirmDeliveRequest.getShopId());
			req.setNumber(confirmDeliveRequest.getExpressNo());
			req.setResultv2("1");
			//根据物流公司查找在快递100中对应代码
			BaseExpressKd baseExpressKd = queryBaseExpressKdSet(confirmDeliveRequest.getExpressId());
			if(baseExpressKd!=null){
				req.setCompany(baseExpressKd.getExpressCode());	
			}else{
				throw new BusinessException("该物流公司无法查询到快递100对应的代码!");
			}
			
			//系统参数常量
			req.setCallbackurl(SysCfgUtil.fetchSysCfg("ORD_KD100_CALLBACK_URL").getParaValue());
			req.setKey(SysCfgUtil.fetchSysCfg("ORD_KD100_KEY").getParaValue());
			req.setRequestUrl(SysCfgUtil.fetchSysCfg("ORD_KD100_REQ_URL").getParaValue());
			
			req.setStaff(confirmDeliveRequest.getStaff());
			resp = expressRSV.rss(req);
			//订阅成功，保存相关日志
			writeLog(req, resp);
		}catch(BusinessException be){
			LogUtil.error(MODULE, "该物流公司无法查询到快递100对应的代码!");
			//处理回调日志
			writeLog( req, resp);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogUtil.error(MODULE, "系统执行出错!");
		    writeLog( req, resp);
		}
	}

	
	/**
	 * 写日志
	 * @param param
	 * @param req
	 * @param resp
	 */
	private void writeLog(ExpressRssReq req,ExpressRssResp resp){
		try{
			OrdExpressLogReq log = new OrdExpressLogReq();
			log.setOrderId(req.getOrderId());
			log.setShopId(req.getShopId());
			
			log.setExpressInterface("ExpressRss");//快递100物流消息订阅
			log.setStaffId(req.getStaff().getId());
			log.setExpressNo(req.getNumber());			
			
			if(StringUtil.isEmpty(req.getCompany())){//没有对于物流公司
				log.setExpressCode(req.getCompany());			
				log.setRespParam("该物流公司无法查询到快递100对应的代码".getBytes());
			}else{
				if(StringUtil.isNotBlank(resp.getReqParam())){
					log.setRespParam(resp.getRespParam().getBytes());					
				}
				if(StringUtil.isNotBlank(req.getJsonParam())){
					log.setReqParam(req.getJsonParam().getBytes());
				}
			}
			if(resp!=null&&StringUtil.isNotBlank(resp.getResult())&&resp.getResult().equals("true")){
				log.setResult("1");//成功
			}else{
				log.setResult("2");//失败
			}
			ordExpressReqLogRSV.saveOrdExpressLog(log);
		}catch(Exception e){
			LogUtil.error(MODULE, "OrdDeliverExpressSVImpl=============write log error!");
		}
	}
	
	/**
	 * 根据系统中的物流公司ID查找对于快递100中的物流公司代码编号
	 * @param expressId
	 * @return
	 */
	private BaseExpressKd queryBaseExpressKdSet(Long expressId ){
		BaseExpressKd baseExpressKd = null;
		BaseExpressKdSetCriteria setCriteria = new BaseExpressKdSetCriteria();
		//setCriteria.setOrderByClause(orderByClause);
		setCriteria.createCriteria().andExpressIdEqualTo(expressId);
		List<BaseExpressKdSet> expressKdSets = baseExpressKdSetMapper.selectByExample(setCriteria);
		if(CollectionUtils.isNotEmpty(expressKdSets)&&expressKdSets.size()>0){
			BaseExpressKdSet baseExpressKdSet = expressKdSets.get(expressKdSets.size()-1);
			baseExpressKd = baseExpressKdMapper.selectByPrimaryKey(baseExpressKdSet.getKdExpressId());
		}
		return baseExpressKd;
	}
}
