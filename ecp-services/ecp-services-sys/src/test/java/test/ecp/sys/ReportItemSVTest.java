/** 
 * Project Name:ecp-services-sys 
 * File Name:BusiDynamicSqlSVTest.java 
 * Package Name:test.ecp.sys 
 * Date:2015-8-27下午8:45:01 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package test.ecp.sys;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;

import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.test.EcpServicesTest;
import com.zengshi.ecp.sys.dubbo.dto.ReportItemReqDTO;
import com.zengshi.ecp.sys.service.common.interfaces.IReportItemSV;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午8:45:01 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version
 * @since JDK 1.6
 */
public class ReportItemSVTest extends EcpServicesTest {

	@Resource
	private IReportItemSV reportItemSV;

	@Test
	public void test() {
		// 定义日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = simpleDateFormat.parse(simpleDateFormat.format(date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Timestamp timestamp = new Timestamp(date.getTime());

		for (int i = 0; i < 6; i++) {
			ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
			reportItemReqDTO.setCalDate(timestamp);
			switch (i) {
			case 0:
				reportItemReqDTO.setItemCode("ITEM_ORD_SUM_MONEY");
				break;
			case 1:
				reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");
				break;

			case 2:
				reportItemReqDTO.setItemCode("ITEM_ORD_SUM_COUNT");
				break;

			case 3:
				reportItemReqDTO.setItemCode("ITEM_ORD_PAY_COUNT");
				break;

			case 4:
				reportItemReqDTO.setItemCode("ITEM_ORD_SUCESS_RATE");
				break;

			case 5:
				reportItemReqDTO.setItemCode("ITEM_ORD_NEW_MEMBER");
				break;
			default:
				reportItemReqDTO.setItemCode("测试");
				break;
			}

			reportItemReqDTO.setShopId(-1L);
			reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
			reportItemReqDTO.setItemValue(i + "");
			try {
				reportItemSV.addReportItem(reportItemReqDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 订单额
	@Test
	public void testAddOrderSum() {

		for (int i = 30; i < 60; i++) {
			int j = i - 30;
			// 定义日期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -j);
			Date date = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = simpleDateFormat.parse(simpleDateFormat.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp timestamp = new Timestamp(date.getTime());

			ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
			reportItemReqDTO.setCalDate(timestamp);

			reportItemReqDTO.setItemCode("ITEM_ORD_SUM_MONEY");

			// reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

			reportItemReqDTO.setShopId(-1l);
			reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
			reportItemReqDTO.setItemValue(i + "");
			try {
				reportItemSV.addReportItem(reportItemReqDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 销售额
	@Test
	public void testAddSale() {

		for (int i = 0; i < 30; i++) {
			// int j = i-30;
			// 定义日期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -i);
			Date date = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = simpleDateFormat.parse(simpleDateFormat.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp timestamp = new Timestamp(date.getTime());

			ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
			reportItemReqDTO.setCalDate(timestamp);

			reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

			// reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

			reportItemReqDTO.setShopId(-1l);
			reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
			reportItemReqDTO.setItemValue(i + "");
			try {
				reportItemSV.addReportItem(reportItemReqDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 订单量
	@Test
	public void testAddOrderAmount() {

		for (int i = 0; i < 30; i++) {
			// int j = i-30;
			// 定义日期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -i);
			Date date = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = simpleDateFormat.parse(simpleDateFormat.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp timestamp = new Timestamp(date.getTime());

			ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
			reportItemReqDTO.setCalDate(timestamp);

			reportItemReqDTO.setItemCode("ITEM_ORD_SUM_COUNT");

			// reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

			reportItemReqDTO.setShopId(-1l);
			reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
			int max = 100;
			int min = 50;
			Random random = new Random();

			int s = random.nextInt(max) % (max - min + 1) + min;
			reportItemReqDTO.setItemValue(s + "");
			try {
				reportItemSV.addReportItem(reportItemReqDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	// 支付成功订单量
	@Test
	public void testAddPayedOrderAmount() {

		for (int i = 0; i < 30; i++) {
			// int j = i-30;
			// 定义日期
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -i);
			Date date = calendar.getTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = simpleDateFormat.parse(simpleDateFormat.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Timestamp timestamp = new Timestamp(date.getTime());

			ReportItemReqDTO reportItemReqDTO = new ReportItemReqDTO();
			reportItemReqDTO.setCalDate(timestamp);

			reportItemReqDTO.setItemCode("ITEM_ORD_PAY_COUNT");

			// reportItemReqDTO.setItemCode("ITEM_ORD_SALE_MONEY");

			reportItemReqDTO.setShopId(-1l);
			reportItemReqDTO.setItemSource(SysCfgUtil.fetchSysCfg("SYS_ADMIN_ITEM_SOURCE").getParaValue());
			
			int max = 50;
			int min = 0;
			Random random = new Random();

			int s = random.nextInt(max) % (max - min + 1) + min;
			reportItemReqDTO.setItemValue(s + "");
			try {
				reportItemSV.addReportItem(reportItemReqDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
