package com.zengshi.ecp.staff.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTradeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ListReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层  账户管理服务接口<br>
 * Date:2015年8月14日下午5:04:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAcctManageRSV {
    
    /**
     * 
     * listAccountByShop:(通过店铺查询资金账户). <br/> 
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctInfoResDTO> listAccountByShop(ShopRelatedAcctsReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctInfoForStaff:(获取用户资金账户金额明细|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctInfoResDTO> queryAcctInfoByStaff(AcctInfoReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAcctBalance:(操作资金账户余额). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAcctBalance:(操作资金账户余额|批量). <br/> 
     * 
     * @author linby 
     * @param listReqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctBalance(ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException;
    
    /**
     * 
     * queryDORAmountByOrder:(获取店铺每笔订单最多可使用的账户资金金额). <br/> 
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long queryDORAmountForOrder(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctSumRelatedShops:(获取用户所有店铺的资金账户金额汇总信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctSumResDTO queryAcctSumRelatedShops(AcctInfoReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctSumByShop:(获取用户指定店铺的资金账户金额信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * <pre>
     * STAFF_ID
     * SHOP_ID
     * ACCT_TYPE
     * </pre>
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctSumResDTO queryAcctSumByShop(AcctInfoReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctWithOrderByStaff:(查询买家资金账户信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AcctInfoResDTO> queryAcctWithOrderByStaff(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * importAcctInfoCapitalIncrementAboutShop:(资金账户正式导入). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void importAcctInfoCapitalIncrementAboutShop(FileImportReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * importAcctInfoTempsAboutShop:(资金账户导入文件上传到TEMP表). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void importAcctInfoTempsAboutShop(FileImportReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctType:(查询账户类型|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctTypeResDTO> queryAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveAcctType:(保存资金账户类型). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateAcctType:(修改资金账户类型). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAcctType:(根据类型键找到资金账户类型). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctTypeResDTO findAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteAcctType:(删除资金账户类型). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * executeOrderRefund:(资金账户中的订单退款). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeOrderRefund(TransactionContentReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listAcctTrade:(通过既定条件查询交易信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctTradeResDTO> listAcctTrade(AcctTradeReqDTO reqDto) throws BusinessException;
    
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
}

