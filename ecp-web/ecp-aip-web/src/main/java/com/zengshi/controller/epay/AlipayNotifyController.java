package com.zengshi.controller.epay;

import com.zengshi.ecp.aip.dao.model.AipSvLogWithBLOBs;
import com.zengshi.ecp.order.dubbo.interfaces.pay.IPaymentRSV;
import com.zengshi.epay.utils.ConstantsForEpay;
import com.zengshi.util.RequestParamUtil;
import com.zengshi.service.AipEpayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AlipayNotifyController {
	private final static Logger logger = LoggerFactory.getLogger(AlipayNotifyController.class);
	
	@Autowired
	AipEpayService aipEpayService;//本地服务
	
	@Autowired
	IPaymentRSV paymentRSV; //dubbo服务
	
	String flag=ConstantsForEpay.NotifyInfo.FLAG;
	String message=ConstantsForEpay.NotifyInfo.RETURN_MSG;
	String success_status=ConstantsForEpay.NotifyInfo.SUCCES_STATUS;


	@RequestMapping(value = "/alipaynotify", method = { RequestMethod.POST,RequestMethod.GET })
	public void alipayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		logger.info("Begin to deal AlipayNotifyController data!");
		String payWay = "9003";
		Map<String, String> payInfo = RequestParamUtil.getParamsMap(request);
		Timestamp requestTime = new Timestamp(new Date().getTime());
		logger.info("请求数据payInfo：" + payInfo.toString());
		
		//日志记录aip_sv_log
		AipSvLogWithBLOBs tAipSvLogWithBLOBs=new AipSvLogWithBLOBs();
		tAipSvLogWithBLOBs.setAppKey("AlipayNotifyController9003");
		tAipSvLogWithBLOBs.setServiceName("AlipayNotifyController");
		tAipSvLogWithBLOBs.setRequest(payInfo.toString());
		tAipSvLogWithBLOBs.setRequestTime(requestTime);
		Map<String, String> returnMap = new HashMap<String, String>();
		//调用dubbo服务，获取回参Map
		try {
			returnMap = paymentRSV.paymentCallback(payWay, payInfo);
			// returnMap中含有两个参数，flag：0：成功，>0：其他 ；message:返回信息
			if (returnMap != null && returnMap.get(flag) != null&& returnMap.get(message) != null) {
				logger.info("ReturnMap" + returnMap.toString());
				if (returnMap.get(flag).equalsIgnoreCase(success_status)) {
					out.write(returnMap.get(message));
					logger.info("AlipayNotifyController处理成功！");
				} else {
					out.write(returnMap.get(message));
					logger.error("AlipayNotifyController处理失败！失败信息："+ returnMap.get(message));
				}
			} else {
				logger.error("返回的Map、或Map中的flag或message为空！");
				out.write("Get wrong result,not having necessary parameters!Please contact us!");
				//若returnMap为空则初始化
				if(returnMap==null){
					returnMap = new HashMap<String, String>();
					returnMap.put("result", "返回的Map为null！");
				}
			}
		} catch (Exception e) {
		    response.setStatus(500);
			out.write("Fail to call back service!Please try again later!");
			logger.error("调用沃易购后场异常：",e);
	        Writer result = new StringWriter();
	        PrintWriter printWriter = new PrintWriter(result);
	        e.printStackTrace(printWriter);
	        //将异常信息记录至日志表中
	        returnMap.put("exception", result.toString());
			throw e;
		} finally {
			out.flush();
			out.close();
			try {
				Timestamp responseTime = new Timestamp(new Date().getTime());
				tAipSvLogWithBLOBs.setResponseTime(responseTime);
				tAipSvLogWithBLOBs.setResponse(returnMap.toString());
				// 插入日志
				aipEpayService.insertEpayLog(tAipSvLogWithBLOBs);
			} catch (Exception e) {
				logger.error("数据库异常！", e);
				throw new Exception("数据库异常！请稍后重试！");
			}
		}
	}

}
