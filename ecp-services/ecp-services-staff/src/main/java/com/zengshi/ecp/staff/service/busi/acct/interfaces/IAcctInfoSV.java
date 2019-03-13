package com.zengshi.ecp.staff.service.busi.acct.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctInfoHis;
import com.zengshi.ecp.staff.dao.model.AcctInfoKey;
import com.zengshi.ecp.staff.dao.model.AcctType;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月13日下午3:11:17  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IAcctInfoSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveAcctInfo:(资金账户信息保存). <br/> 
     * 
     * @author linby 
     * @param acctInfo 
     * @since JDK 1.6
     */
    public long saveAcctInfo(AcctInfo acctInfo) throws BusinessException;
    
    public void deleteAcctInfoByKey(AcctInfoKey key) throws BusinessException;
    
    /**
     * 
     * updateAcctInfo:(更新资金账户信息). <br/> 
     * 
     * @author linby 
     * @param acctInfo
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctInfo(AcctInfo acctInfo) throws BusinessException;
    
    
    /**
     * 
     * findAcctInfoById:(通过资金账户id查找资金账户信息). <br/> 
     * 
     * @author linby 
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctInfo findAcctInfoById(Long id) throws BusinessException;
    
    /**
     * 
     * findAcctInfoByKey:(通过资金账户key查找资金账户信息). <br/> 
     * 
     * @author linby 
     * @param key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctInfo findAcctInfoByKey(AcctInfoKey key) throws BusinessException;
    
    /**
     * 
     * saveAcctType:(保存资金账户类型信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveAcctType(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteAcctTypeByKey:(通过key删除资金账户类型). <br/> 
     * 
     * @author linby 
     * @param key
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteAcctTypeByKey(AcctTypeReqDTO key) throws BusinessException;
    
    /**
     * 
     * updateAcctTypeBykey:(通过key修改资金账户类型). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateAcctTypeBykey(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findAcctTypeByKey:(通过联合键查找资金账户类型信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctTypeResDTO findAcctTypeByKey(AcctTypeReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctInfoForStaff:(获取用户资金账户金额明细). <br/> 
     * 
     * @author linby 
     * @param acctInfo
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AcctInfo> queryAcctInfoByStaff(AcctInfo acctInfo) throws BusinessException;
    
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
     * queryAcctInfoByShop:(可查询店铺所有用户各资金类型的资金账户余额情况). <br/> 
     * 提供店铺下拉框查询条件，资金类型下拉框查询条件，及用户名称模糊查询。<br/> 
     * 
     * @author linby 
     * @param  reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<AcctInfoResDTO> queryAcctInfoByShop(ShopRelatedAcctsReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveAcctInfoHis:(新增历史快照). <br/>
     *  
     * @author linby 
     * @param acctInfoHis
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveAcctInfoHis(AcctInfoHis acctInfoHis) throws BusinessException;
    
    /**
     * 
     * importAcctInfoCapitalIncrementAboutShop:(导入店铺账户资金). <br/> 
     * 店铺资金.<br/> 
     * 
     * @author linby 
     * @param reqDto 
     * <pre>
     * shopId  店铺编码
     * acctType  资金类型
     * staffId  用户编码
     * amount  账户增加金额
     * </pre>
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveImportAcctInfoCapitalIncrementAboutShop(FileImportReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctSumRelatedShops:(获取用户所有店铺的资金账户金额汇总信息). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * <pre>
     * STAFF_ID
     * ADAPT_TYPE
     * </pre>
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
     * ADAPT_TYPE
     * </pre>
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public AcctSumResDTO queryAcctSumByShop(AcctInfoReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctTypeDOR:(获取店铺每笔订单使用资金账户的比例). <br/> 
     * DEDUCT_ORDER_RATIO
     * 
     * @author linby 
     * @param reqDto
     * <pre>
     * ACCT_TYPE     ---资金类型   必传
     * ADAPT_TYPE   ---资金适用类型   必传
     * SHOP_ID       ----店铺ID     必传
     * </pre>
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BigDecimal queryAcctTypeDOR(AcctInfoReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * queryAcctType:(查询账户类型). <br/> 
     * 
     * @author linby 
     * @param acctType
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<AcctType> queryAcctType(AcctType acctType) throws BusinessException;
    
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
     * batchUpdatingAcctInfo:(批量更新资金账户信息|导入使用). <br/> 
     * 
     * @author linby
     * @param listResDto  资金账户临时数据对象列表
     * @param operatorId   操作人id
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void batchUpdatingAcctInfo(List<AcctInfoTempResDTO> listResDto, Long operatorId) throws BusinessException;
}

