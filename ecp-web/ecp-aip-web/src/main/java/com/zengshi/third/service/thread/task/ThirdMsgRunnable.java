package com.zengshi.third.service.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dubbo.dto.msgSyc.UnpfMsgSycReqDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.msgSyc.IUnpfMsgSycRSV;
import com.zengshi.model.TaobaoOrderReq;
import com.zengshi.third.service.msgHandler.interfaces.IThirdMsgHandlerSV;
import com.alibaba.fastjson.JSON;

public class ThirdMsgRunnable implements Runnable {

	private final static Logger logger = LoggerFactory
			.getLogger(ThirdMsgRunnable.class);

	private String message;//消息体

	private final IThirdMsgHandlerSV sv;// 执行处理接口

	private final IUnpfMsgSycRSV unpfMsgSycRSV;//log记录dubbo服务

	public ThirdMsgRunnable(String message, IThirdMsgHandlerSV sv,
			IUnpfMsgSycRSV unpfMsgSycRSV) {
		this.message = message;
		this.sv = sv;
		this.unpfMsgSycRSV = unpfMsgSycRSV;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	@Override
	public void run() {
		Long id=null;
		try {
			 id=unpfMsgSycRSV.getMsgSycId();
			 TaobaoOrderReq orderMsgReq = JSON.parseObject(message,
						TaobaoOrderReq.class);
			
			UnpfMsgSycReqDTO unpfMsgSycReqDTO = new UnpfMsgSycReqDTO();
			unpfMsgSycReqDTO
					.setCreateStaff(unpfMsgSycReqDTO.getStaff().getId());
			unpfMsgSycReqDTO.setMsg(orderMsgReq.getContent());
			unpfMsgSycReqDTO.setPlatType(orderMsgReq.getPlatType());
			unpfMsgSycReqDTO.setShopId(orderMsgReq.getShopId());
			unpfMsgSycReqDTO.setStatus("9");// 默认初始化数据
			unpfMsgSycReqDTO.setTradeType(orderMsgReq.getTopic());
			
			orderMsgReq.setSysLogId(id);
			unpfMsgSycReqDTO.setId(orderMsgReq.getSysLogId());
			

			if (sv == null) {
				logger.debug("没有实现对应的topic服务" + orderMsgReq.getTopic());
				unpfMsgSycReqDTO.setErrors("没有实现对应的topic服务"
						+ orderMsgReq.getTopic());
				unpfMsgSycReqDTO.setStatus("7");
				
			}
			unpfMsgSycRSV.saveUnpfMsgSyc(unpfMsgSycReqDTO);
			
			if(sv!=null){
				//核心业务调用
				sv.doAction(orderMsgReq);
	
				// 执行成功后 消息log 移除表处理
				UnpfMsgSycReqDTO reqDTO = new UnpfMsgSycReqDTO();
				reqDTO.setId(orderMsgReq.getSysLogId());
				reqDTO.setUpdateStaff(reqDTO.getStaff().getId());
				unpfMsgSycRSV.deleteUnpfMsgSycNoThrows(reqDTO);
			}
		} catch (BusinessException ex) {
			logger.error(ex.toString());
			logger.error("业务处理失败BusinessException" + ex.getMessage());
			UnpfMsgSycReqDTO unpfMsgSycReqDTO1 = new UnpfMsgSycReqDTO();
			unpfMsgSycReqDTO1.setUpdateStaff(unpfMsgSycReqDTO1.getStaff()
					.getId());
			unpfMsgSycReqDTO1.setErrors("业务处理失败BusinessException"
					+ ex.getMessage());
			unpfMsgSycReqDTO1.setStatus("7");
			unpfMsgSycReqDTO1.setId(id);
			unpfMsgSycRSV.updateUnpfMsgSycNoThrows(unpfMsgSycReqDTO1);

		} catch (Exception ex) {
			logger.error(ex.toString());
			logger.error("业务处理失败Exception" + ex.toString());
			UnpfMsgSycReqDTO unpfMsgSycReqDTO1 = new UnpfMsgSycReqDTO();
			unpfMsgSycReqDTO1.setUpdateStaff(unpfMsgSycReqDTO1.getStaff()
					.getId());
			unpfMsgSycReqDTO1.setErrors("业务处理失败Exception" + ex.toString());
			unpfMsgSycReqDTO1.setStatus("7");
			unpfMsgSycReqDTO1.setId(id);
			unpfMsgSycRSV.updateUnpfMsgSycNoThrows(unpfMsgSycReqDTO1);
		}
	}
}
