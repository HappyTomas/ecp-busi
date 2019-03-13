package com.zengshi.ecp.staff.service.busi.acct.interfaces;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 资金账户助手服务接口<br>
 * Date:2015年11月21日上午10:42:49  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public interface IAcctInfoAideSV extends IGeneralSQLSV {
	
	/**
	 * 
	 * validateImportAcctInfoCapitalIncrementAboutShop:(验证‘店铺资金’资金账户导入数据有效性). <br/> 
	 * <pre>
	 *  map structure
	 *  res.put("shopId", #String); //店铺编号
     *  res.put("acctType", #String);	//资金类型
     *  res.put("staffCode", #String);	//用户工号
     *  res.put("tradeMoney", #String);	//金额
     *  res.put("relAuthStaff", #AuthStaffCIDX); //相关用户对象
     *  res.put("relShop", #ShopInfo);//相关店铺对象
     *  res.put("isGood", #String); //是否合法
	 * 
	 * </pre>
	 * @author linby
	 * @param shopCapital
	 * @param ifThrowE
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public Map<String, Object> validateImportAcctInfoCapitalIncrementAboutShop(List<Object> shopCapital, boolean ifThrowE) throws BusinessException;
	
	/**
	 * 
	 * batchSavingAcctInfoTemp:(批量保存资金账户临时数据). <br/> 
	 * 
	 * @author linby
	 * @param list
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void batchSavingAcctInfoTemp(List<AcctInfoTempReqDTO> list) throws BusinessException;
	
	/**
	 * 
	 * removeAcctInfoTempsBelongToCreator:(移除资金账户临时数据创建者的所有数据). <br/> 
	 * 
	 * @author linby
	 * @param staffId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void removeAcctInfoTempsBelongToCreator(Long staffId) throws BusinessException;
	
	/**
	 * 
	 * updateHandledAcctInfoTemps:(更新已经处理过的AcctInfoTemp). <br/> 
	 * 修改IS_COMMIT状态为“正式提交[1]”
	 * 
	 * @author linby
	 * @param staffId
	 * @param listId
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public void updateHandledAcctInfoTemps(Long staffId, List<Long> listId) throws BusinessException;
	
	/**
	 * 
	 * listAcctInfoTemp:(查询导入的资金账户临时数据|分页). <br/> 
	 * 
	 * @author linby
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public PageResponseDTO<AcctInfoTempResDTO> listAcctInfoTemp(AcctInfoTempReqDTO reqDto) throws BusinessException;
	
	/**
	 * 
	 * saveAcctInfoTemp:(保存资金账户临时数据对象). <br/> 
	 * 
	 * @author linby
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public long saveAcctInfoTemp(AcctInfoTempReqDTO reqDto) throws BusinessException;
	
}

