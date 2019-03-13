package com.zengshi.ecp.staff.service.busi.acct.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctInfoHisMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctInfoHisShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctInfoShopIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctTypeMapper;
import com.zengshi.ecp.staff.dao.model.AcctInfo;
import com.zengshi.ecp.staff.dao.model.AcctInfoCriteria;
import com.zengshi.ecp.staff.dao.model.AcctInfoHis;
import com.zengshi.ecp.staff.dao.model.AcctInfoHisShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctInfoKey;
import com.zengshi.ecp.staff.dao.model.AcctInfoShopIDX;
import com.zengshi.ecp.staff.dao.model.AcctTrade;
import com.zengshi.ecp.staff.dao.model.AcctType;
import com.zengshi.ecp.staff.dao.model.AcctTypeCriteria;
import com.zengshi.ecp.staff.dao.model.AcctTypeKey;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctSumResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.FileImportReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopRelatedAcctsReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.TransactionContentReqDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoAideSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月13日下午3:53:20  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AcctInfoSVImpl extends GeneralSQLSVImpl implements IAcctInfoSV {
    private static final String MODULE = AcctInfoSVImpl.class.getName();

    @Resource
    private AcctInfoMapper acctInfoMapper; //资金账户
    
    @Resource 
    private AcctTypeMapper acctTypeMapper; //资金账户类型
    
    @Resource
    private AcctInfoHisMapper acctInfoHisMapper;  //资金账户历史快照
    
    @Resource
    private AcctInfoShopIDXMapper acctShopIDXMapper; //账户之店铺索引
    
    @Resource
    private AcctInfoHisShopIDXMapper acctHisShopIDXMapper; //账户历史之店铺索引
    
    @Resource(name="staffTools")
    private StaffTools staffTools;
    
    @Resource
    private IShopMgrSV shopMgrService; //店铺管理服务
    
    @Resource
    private ICustManageSV custMgrService; //客户管理服务
    
    @Resource
    private IAcctTradeSV acctTradeService; //资金账户交易服务
    
    @Resource
    private IAcctInfoAideSV acctInfoAideSV; //资金账户助手服务
    
    @Resource(name = "seq_acct_info_id")
    private Sequence seqAcctInfo; //资金账户信息表sequence 
    
    @Resource(name = "seq_acct_info_his_id")
    private Sequence seqAcctInfoHis; //资金账户历史快照表sequence
    
    @Resource(name = "seq_acct_info_shop_idx_id")
    private Sequence seqAcctShop; //账户店铺索引表sequence
    
    @Resource(name = "seq_acct_info_his_shop_idx_id")
    private Sequence seqAcctHisShop; //历史账户店铺索引表sequence
    
    @Override
    public long saveAcctInfo(AcctInfo acctInfo) throws BusinessException{
        //TODO 参数有效性验证
        //1.新增账户
        acctInfo.setId(seqAcctInfo.nextValue());
        if(StringUtil.isBlank(acctInfo.getStatus())){//缺省“有效”
            acctInfo.setStatus(StaffConstants.Acct.ACCT_STATUS_ENABLE);
        }
        
        try {
            acctInfoMapper.insertSelective(acctInfo);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "保存资金账户失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "保存资金账户" });
        }
        //2.新增账户之店铺索引
        AcctInfoShopIDX acctShopIDX = new AcctInfoShopIDX();
        acctShopIDX.setId(seqAcctShop.nextValue());
        acctShopIDX.setStaffId(acctInfo.getStaffId());
        acctShopIDX.setAcctType(acctInfo.getAcctType());
        acctShopIDX.setAdaptType(acctInfo.getAdaptType());
        acctShopIDX.setShopId(acctInfo.getShopId());
        acctShopIDX.setStatus(acctInfo.getStatus());
        try {
            acctShopIDXMapper.insert(acctShopIDX);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "保存资金账户之店铺索引失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "保存资金账户之店铺索引" });
        }
        
        return acctInfo.getId();
    }

    @Override
    public void updateAcctInfo(AcctInfo acctInfo) throws BusinessException{
        try {
            acctInfoMapper.updateByPrimaryKeySelective(acctInfo);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "修改资金账户失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{ "修改资金账户" });
        }
    }

    @Override
    public AcctInfo findAcctInfoById(Long id) throws BusinessException{
        AcctInfoCriteria example = new AcctInfoCriteria();
        AcctInfoCriteria.Criteria sql = example.createCriteria();
        sql.andIdEqualTo(id);
        return acctInfoMapper.selectByExample(example).get(0);
    }

    @Override
    public long saveAcctInfoHis(AcctInfoHis acctInfoHis) throws BusinessException {
        //TODO 参数有效性验证
        //1.新增账户历史
        acctInfoHis.setId(seqAcctInfoHis.nextValue());
        try {
            acctInfoHisMapper.insertSelective(acctInfoHis);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "保存资金账户快照失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "保存资金账户快照" });
        }
        //2.新增账户历史之店铺索引
        AcctInfoHisShopIDX acctHisShopIDX = new AcctInfoHisShopIDX();
        acctHisShopIDX.setId(seqAcctHisShop.nextValue());
        acctHisShopIDX.setAcctId(acctInfoHis.getAcctId());
        acctHisShopIDX.setShopId(acctInfoHis.getShopId());
        acctHisShopIDX.setStaffId(acctInfoHis.getStaffId());
        try {
            acctHisShopIDXMapper.insert(acctHisShopIDX);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "保存资金账户之店铺索引失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "保存资金账户快照之店铺索引" });
        }
        
        return acctInfoHis.getId();
    }

    @Override
    public PageResponseDTO<AcctInfoResDTO> queryAcctInfoByShop(ShopRelatedAcctsReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        AcctInfoCriteria aiCriteria = new AcctInfoCriteria();
        AcctInfoCriteria.Criteria sql = aiCriteria.createCriteria();
               
        if(StringUtil.isNotBlank(reqDto.getStaffName())){
            CustInfoReqDTO custReq = new CustInfoReqDTO(); 
            custReq.setStaffCode(reqDto.getStaffName());
            custReq.setPageNo(0);//全集
            PageResponseDTO<CustInfoResDTO> pageListCust = custMgrService.listCustInfo(custReq);
            List<Long> listCustId = new ArrayList<Long>();
            if(pageListCust.getCount()>0){
                for(CustInfoResDTO ciResDto : pageListCust.getResult()){
                    listCustId.add(ciResDto.getId());
                }
                sql.andStaffIdIn(listCustId);
            }else{
                listCustId.add(0L);//不存在
                sql.andStaffIdIn(listCustId);
            }
        }
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());    
        }else{
            sql.andStatusEqualTo(StaffConstants.Acct.ACCT_STATUS_ENABLE);
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }   
        if(StringUtil.isNotBlank(reqDto.getAdaptType())){
            sql.andAdaptTypeEqualTo(reqDto.getAdaptType());
        }
        if(reqDto.getShopId()!=null){
            sql.andShopIdEqualTo(reqDto.getShopId());
        } 
        aiCriteria.setLimitClauseCount(reqDto.getPageSize());
        aiCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        aiCriteria.setOrderByClause("update_time desc ");
        
        return super.queryByPagination(reqDto, aiCriteria, true, new PaginationCallback<AcctInfo, AcctInfoResDTO>() {

            @Override
            public List<AcctInfo> queryDB(BaseCriteria criteria) {
                return acctInfoMapper.selectByExample((AcctInfoCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return acctInfoMapper.countByExample((AcctInfoCriteria)criteria);
            }
            
            @Override
            public List<Comparator<AcctInfo>> defineComparators(){
                List<Comparator<AcctInfo>> ls = new ArrayList<Comparator<AcctInfo>>();
                ls.add(new Comparator<AcctInfo>() {
                	public int compare(AcctInfo o1, AcctInfo o2) {
                		if(o1.getUpdateTime()==null||o2.getUpdateTime()==null){
                			return 0;
                		}
						return o1.getUpdateTime().getTime()>o2.getUpdateTime().getTime()?-1:1;
					}
                });
                return ls;
            }
            
            @Override
            public AcctInfoResDTO warpReturnObject(AcctInfo t) {
                AcctInfoResDTO dto = new AcctInfoResDTO();
                ObjectCopyUtil.copyObjValue(t,dto,null,true);
                //字段转义
                ShopInfo s =shopMgrService.findShopByShopID(t.getShopId());
                CustInfoResDTO c = custMgrService.findCustInfoById(t.getStaffId());
                dto.setAcctTypeName(BaseParamUtil.fetchParamValue(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY, 
                                                                           t.getAcctType()));//资金类型名
                dto.setShopName(s!=null&&StringUtil.isNotBlank(s.getShopName())?s.getShopName():"佚名店铺");//店铺名
                dto.setStaffName(c!=null&&StringUtil.isNotEmpty(c.getStaffCode())?c.getStaffCode():"佚名客户");//账户所属用户名
                return dto;
            }
        });
    }

    @Override
    public void saveImportAcctInfoCapitalIncrementAboutShop(FileImportReqDTO reqDto) throws BusinessException {
        byte[] byteFile = FileUtil.readFile(reqDto.getFileId());//根据文件id得到文件
        InputStream inputs = new ByteArrayInputStream(byteFile);
        //importExcel 调用局部final 变量
        final FileImportReqDTO importDto = new FileImportReqDTO();
        ObjectCopyUtil.copyObjValue(reqDto, importDto, null, false);
        
        //文件解析
        DataInoutUtil.importExcel(new DataImportHandler(inputs, reqDto.getFileName(), "xls", "staff", reqDto.getStaff().getStaffCode()) {
            @Override
            public boolean doCallback(List<List<Object>> datas, String fileId) {
                String adaptType = "03";//资金适用类型:"03"店铺
                String tradeType = "01";//充值类型:"01"充值
                String debitCredit = "1";//借贷方向:"1"收入
                boolean flagFirstRowPass = false;
                for (List<Object> row : datas) {
                    if(!flagFirstRowPass){
                        flagFirstRowPass = true;
                        continue;
                    }
                    // 基础校验：店铺编码，用户编码、金额必须为数字
                    String regex = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";  
                    try {
                        boolean item0 = row.get(0).toString().matches(regex);
                        boolean item1 = row.get(1).toString().matches("[0-9]+");
                        // TODO 验证工号
                        //boolean item2 = row.get(2).toString().matches(regex);
                        boolean item3 = row.get(3).toString().matches(regex);
                        if(!(item0&&item1&&item3)){
                            throw new BusinessException(StaffConstants.Acct.E_IMPORT_NUMBER_ERROR);
                        }
                    } catch (NullPointerException e) {
                        throw new BusinessException(StaffConstants.Acct.E_IMPORT_NULL_CONTENT);
                    }
                    
                    Long shopId = new BigDecimal(row.get(0).toString()).longValue();
                    String acctType = row.get(1).toString();
//                    Long staffId = new BigDecimal(row.get(2).toString()).longValue();
                    String staffCode = row.get(2).toString().trim();
//                    double a = new BigDecimal(row.get(3).toString()).doubleValue();
//                    double a = Double.valueOf(row.get(3).toString());             
                    long amount = MoneyUtil.convertYuanToCent(row.get(3).toString());//导入时单位为“元”，入库前换算成“分”
                    
                    // TODO 对金额进行有效性校验(精确度等)
                    //1.判断[店铺]是否存在、[用户]是否存在、[资金类型]是否存在
                    ShopInfo si = shopMgrService.findShopByShopID(shopId);
                    if(si==null){
                        throw new BusinessException(StaffConstants.Acct.E_IMPORT_SHOP_UNFINDED, new String[] { shopId.toString() });
                    }
                    AuthStaffCIDX staffCustIDX = new AuthStaffCIDX();
                    staffCustIDX.setStaffCode(staffCode);
                    AuthStaffCIDX cidx = custMgrService.findAuthStaffCIDXByCode(staffCustIDX);
                    if(cidx==null){
                        throw new BusinessException(StaffConstants.Acct.E_IMPORT_CUST_UNFINDED, new String[] { staffCode });
                    }
                    Long staffId = cidx.getStaffId();//需要被导入店铺资金的相关用户id
                    
                    AcctTypeKey acctTypeKey = new AcctTypeKey();
                    acctTypeKey.setAcctType(acctType);
                    acctTypeKey.setAdaptType(adaptType);//店铺资金
                    acctTypeKey.setShopId(shopId);
                    AcctTypeReqDTO acctTypeReqDTO = new AcctTypeReqDTO();
                    ObjectCopyUtil.copyObjValue(acctTypeKey, acctTypeReqDTO, null, false);
                    AcctTypeResDTO at = findAcctTypeByKey(acctTypeReqDTO);
                    if(at==null){
                        throw new BusinessException(StaffConstants.Acct.E_IMPORT_ACCTTYPE_UNFINDED, new String[] { shopId.toString(), acctType, staffCode });
                    }
                    //2.1.判断用户账户是否存在
                    AcctInfoKey acctInfoKey = new AcctInfoKey();
                    acctInfoKey.setAcctType(acctType);
                    acctInfoKey.setAdaptType(adaptType);
                    acctInfoKey.setShopId(shopId);
                    acctInfoKey.setStaffId(staffId);
                    AcctInfo ai = findAcctInfoByKey(acctInfoKey);
                    AcctInfo aiOri = new AcctInfo(); //账户状态缓存
                    
                    //2.2.更新账户
                    if(ai==null){//账户不存在则新增，并初始化账户金额
                        ai = new AcctInfo();
                        ai.setAcctType(acctType);
                        ai.setAdaptType(adaptType);
                        ai.setShopId(shopId);
                        ai.setStaffId(staffId);
                        ai.setTotalMoney(amount);
                        ai.setBalance(amount);
                        ai.setFreezeMoney(0L);
                        ai.setCreateStaff(importDto.getStaff().getId());
                        ai.setCreateTime(new Timestamp(System.currentTimeMillis()));
                        ai.setUpdateStaff(importDto.getStaff().getId());
                        ai.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        
                        long resAcctInfoId = saveAcctInfo(ai);
                        ai.setId(resAcctInfoId);
                        ObjectCopyUtil.copyObjValue(ai, aiOri, null, true);
                    }else{//账户存在则更新
                        ObjectCopyUtil.copyObjValue(ai, aiOri, null, true);
                        ai.setTotalMoney(ai.getTotalMoney()+amount);
                        ai.setBalance(ai.getBalance()+amount);
                        ai.setUpdateStaff(importDto.getStaff().getId());
                        ai.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                        
                        updateAcctInfo(ai);
                    }
                    //3.新增资金账户交易明细记录
                    AcctTrade acctTrade = new AcctTrade();
                    acctTrade.setStaffId(staffId);
                    acctTrade.setTradeType(tradeType);//TODO 交易类型，现为“充值”
                    acctTrade.setDebitCredit(debitCredit);//TODO 借贷方向，现为“收入”
                    acctTrade.setOrderId("");//TODO 导入是否具有“订单”
                    acctTrade.setAcctType(acctType);
                    acctTrade.setAdaptType(adaptType);
                    acctTrade.setShopId(shopId);
                    acctTrade.setTradeMoney(amount);
                    acctTrade.setTotalMoney(aiOri!=null?aiOri.getTotalMoney():0L);
                    acctTrade.setBalance(aiOri!=null?aiOri.getBalance():0L);
                    acctTrade.setFreezeMoney(aiOri!=null?aiOri.getFreezeMoney():0L);//TODO 是否修改
                    acctTrade.setCreateStaff(importDto.getStaff().getId());
                    acctTrade.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    acctTrade.setUpdateStaff(importDto.getStaff().getId());
                    acctTrade.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    
                    acctTradeService.saveAcctTrade(acctTrade);
                    //4.新增资金账户历史快照记录 
                    AcctInfoHis acctInfoHis = new AcctInfoHis();
                    acctInfoHis.setAcctId(ai.getId());
                    acctInfoHis.setStaffId(ai.getStaffId());
                    acctInfoHis.setAcctType(ai.getAcctType());
                    acctInfoHis.setAdaptType(ai.getAdaptType());
                    acctInfoHis.setShopId(ai.getShopId());
                    acctInfoHis.setDebitCredit(debitCredit);
                    acctInfoHis.setTradeMoney(amount);
                    acctInfoHis.setTotalMoney(aiOri!=null?aiOri.getTotalMoney():0L);
                    acctInfoHis.setBalance(aiOri!=null?aiOri.getBalance():0L);
                    acctInfoHis.setFreezeMoney(aiOri!=null?aiOri.getFreezeMoney():0L);
                    acctInfoHis.setCreateStaff(importDto.getStaff().getId());
                    acctInfoHis.setCreateTime(new Timestamp(System.currentTimeMillis()));
                    acctInfoHis.setUpdateStaff(importDto.getStaff().getId());
                    acctInfoHis.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                    
                    saveAcctInfoHis(acctInfoHis);
                }
                return true;
            }
        }, 1, 1);
    }
    
    @Override
    public void deleteAcctInfoByKey(AcctInfoKey key) throws BusinessException {
        //TODO 逻辑or物理删除
    }

    @Override
    public AcctInfo findAcctInfoByKey(AcctInfoKey key) throws BusinessException {
        //TODO 入参校验
        AcctInfo ai = null;
        try {
            ai = acctInfoMapper.selectByPrimaryKey(key);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询失败", e);
        }
        return ai;
    }

    @Override
    public List<AcctInfo> queryAcctInfoByStaff(AcctInfo acctInfo) throws BusinessException {
        List<AcctInfo> res = new ArrayList<AcctInfo>();;
        //TODO 入参校验
        AcctInfoCriteria aicCriteria = new AcctInfoCriteria();
        AcctInfoCriteria.Criteria sql = aicCriteria.createCriteria();
        sql.andStaffIdEqualTo(acctInfo.getStaffId());
        if(StringUtil.isNotBlank(acctInfo.getAcctType())){
            sql.andAcctTypeEqualTo(acctInfo.getAcctType());
        }
        if(StringUtil.isNotBlank(acctInfo.getAdaptType())){
            sql.andAdaptTypeEqualTo(acctInfo.getAdaptType());
        }
        if(acctInfo.getShopId()!=null){
            sql.andShopIdEqualTo(acctInfo.getShopId());
        }
        if(StringUtil.isBlank(acctInfo.getStatus())){//账户状态，值空，则缺省为“生效”
            sql.andStatusEqualTo(StaffConstants.Acct.ACCT_STATUS_ENABLE);
        }else{
            sql.andStatusEqualTo(acctInfo.getStatus());
        }
        
        try {
            res = acctInfoMapper.selectByExample(aicCriteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "数据查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "queryAcctInfoByStaff 查询异常" });
        }
        
        return res;
    }

    @Override
    public AcctTypeResDTO findAcctTypeByKey(AcctTypeReqDTO reqDto) throws BusinessException {
        AcctTypeResDTO res = new AcctTypeResDTO();
        AcctTypeKey key = new AcctTypeKey();
        ObjectCopyUtil.copyObjValue(reqDto, key, null, false);
        AcctType find = new AcctType();
        try {
            find = acctTypeMapper.selectByPrimaryKey(key);
            ObjectCopyUtil.copyObjValue(find, res, null, false);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "资金账户类型" });
        }
        if(find==null) return null;
        return res;
    }

    @Override
    public void saveAcctType(AcctTypeReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getAcctType(), reqDto.getAdaptType(), reqDto.getDeductOrderRatio() },
                new String[] {"资金类型", "资金适用类型", "抵扣比"});
        
        //如果适用类型为“店铺资金”，则判断是否存在对应店铺
        if(StaffConstants.Acct.ADAPT_TYPE_SHOP.equals(reqDto.getAdaptType())){
            ShopInfo si = shopMgrService.findShopByShopID(reqDto.getShopId());
            if(si==null){//如果店铺不存在则返回提示
                throw new BusinessException(StaffConstants.shopInfo.E_SHOP_CANT_FIND);
            }
        }
        
        //校验是否已存在
        AcctTypeResDTO find = this.findAcctTypeByKey(reqDto);
        if(find!=null){
            throw new BusinessException(StaffConstants.Acct.E_ACCT_TYPE_EXIST);
        }
        
        AcctType acctType = new AcctType();
        ObjectCopyUtil.copyObjValue(reqDto, acctType, null, false);
        acctType.setCreateStaff(reqDto.getStaff().getId());
        acctType.setCreateTime(new Timestamp(System.currentTimeMillis()));
        acctType.setUpdateStaff(reqDto.getStaff().getId());
        acctType.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //特殊处理，如果适用类型为[“01”平台资金]，则适用对象为0L
        if(StaffConstants.Acct.ADAPT_TYPE_PLATFORM.equals(reqDto.getAdaptType())){
            acctType.setShopId(0L);
        }
        try {
            acctTypeMapper.insertSelective(acctType);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "保存失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "资金账户类型入库失败" });
        }
    }

    @Override
    public void deleteAcctTypeByKey(AcctTypeReqDTO reqDto) throws BusinessException {
        AcctTypeKey key = new AcctTypeKey();
        ObjectCopyUtil.copyObjValue(reqDto, key, null, false);
        try {
            acctTypeMapper.deleteByPrimaryKey(key);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "资金账户类型删除失败" });
        }
    }

    @Override
    public AcctSumResDTO queryAcctSumByShop(AcctInfoReqDTO reqDto) throws BusinessException {
        return queryAcctSumRelatedShops(reqDto);
    }

    @Override
    public BigDecimal queryAcctTypeDOR(AcctInfoReqDTO reqDto) throws BusinessException {
        if(StringUtil.isBlank(reqDto.getAcctType())||StringUtil.isBlank(reqDto.getAcctType())
                ||StringUtil.isBlank(reqDto.getAcctType())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "缺少传参." });
        }
        AcctTypeKey acctTypeKey = new AcctTypeKey();
        acctTypeKey.setAcctType(reqDto.getAcctType());
        acctTypeKey.setAdaptType(reqDto.getAdaptType());
        acctTypeKey.setShopId(reqDto.getShopId());
        AcctTypeReqDTO acctTypeReqDTO = new AcctTypeReqDTO();
        ObjectCopyUtil.copyObjValue(acctTypeKey, acctTypeReqDTO, null, false);
        AcctTypeResDTO at = findAcctTypeByKey(acctTypeReqDTO);
        
        if(at!=null){
            return at.getDeductOrderRatio().divide(new BigDecimal("100")); //除100
        }else{
            return null;
        }
    }

    @Override
    public AcctSumResDTO queryAcctSumRelatedShops(AcctInfoReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getStaffId()==null||StringUtil.isBlank(reqDto.getAdaptType())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"入参异常"});
        }
        AcctInfoCriteria aiCriteria = new AcctInfoCriteria();
        AcctInfoCriteria.Criteria sql = aiCriteria.createCriteria();
        
        sql.andStaffIdEqualTo(reqDto.getStaffId());  //必传
        sql.andAdaptTypeEqualTo(reqDto.getAdaptType());  //必传
        if(reqDto.getShopId()!=null){
            sql.andShopIdEqualTo(reqDto.getShopId());
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }
        
        List<AcctInfo> ls = acctInfoMapper.selectByExample(aiCriteria);
        
        AcctSumResDTO asResDto = new AcctSumResDTO(); 
        if(ls==null){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR, new String[]{ "查无账户." });
        }
        for(AcctInfo ai : ls){
            asResDto.setBalance(asResDto.getBalance()+ai.getBalance());
            asResDto.setFreezeMoney(asResDto.getFreezeMoney()+ai.getFreezeMoney());
            asResDto.setTotalMoney(asResDto.getTotalMoney()+ai.getTotalMoney());
        }
        
        return asResDto;
    }

    @Override
    public List<AcctType> queryAcctType(AcctType acctType) throws BusinessException {
        List<AcctType> res = new ArrayList<AcctType>();
        AcctTypeCriteria atCriteria = new AcctTypeCriteria();
        AcctTypeCriteria.Criteria sql = atCriteria.createCriteria();
        if(acctType.getShopId()!=null){
            sql.andShopIdEqualTo(acctType.getShopId());
        }
        if(StringUtil.isNotBlank(acctType.getAcctType())){
            sql.andAcctTypeEqualTo(acctType.getAcctType());
        }
        if(StringUtil.isNotBlank(acctType.getAdaptType())){
            sql.andAdaptTypeEqualTo(acctType.getAdaptType());
        }
        try {
            res = acctTypeMapper.selectByExample(atCriteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "数据查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "资金账户类型" });
        }
        
        return res;
    }

    @Override
    public PageResponseDTO<AcctTypeResDTO> queryAcctType(AcctTypeReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{ "reqDto" });
        }
        PageResponseDTO<AcctTypeResDTO> res = new PageResponseDTO<AcctTypeResDTO>();
        
        AcctTypeCriteria atCriteria = new AcctTypeCriteria();
        AcctTypeCriteria.Criteria sql = atCriteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getAdaptType())){
            sql.andAdaptTypeEqualTo(reqDto.getAdaptType());
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }
        if(reqDto.getShopId()!=null){
            sql.andShopIdEqualTo(reqDto.getShopId());
        }
        
        atCriteria.setLimitClauseCount(reqDto.getPageSize());
        atCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        atCriteria.setOrderByClause("update_time asc ");
        
        res = super.queryByPagination(reqDto, atCriteria, true, new PaginationCallback<AcctType, AcctTypeResDTO>(){

            @Override
            public List<AcctType> queryDB(BaseCriteria criteria) {
                return acctTypeMapper.selectByExample((AcctTypeCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return acctTypeMapper.countByExample((AcctTypeCriteria)criteria);
            }
            
            @Override
            public List<Comparator<AcctType>> defineComparators(){
                List<Comparator<AcctType>> ls = new ArrayList<Comparator<AcctType>>();
                ls.add(new Comparator<AcctType>() {
                	public int compare(AcctType o1, AcctType o2) {
                		if(o1.getUpdateTime()==null&&o2.getUpdateTime()!=null){
                			return 1;
                		}
                		if(o2.getUpdateTime()==null&&o1.getUpdateTime()!=null){
                			return -1;
                		}
                		if(o2.getUpdateTime()==null&&o1.getUpdateTime()==null){
                			return 0;
                		}
						return o1.getUpdateTime().getTime()>o2.getUpdateTime().getTime()?-1:1;
					}
                });
                return ls;
            }

            @Override
            public AcctTypeResDTO warpReturnObject(AcctType t) {
                AcctTypeResDTO dto = new AcctTypeResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                //1.资金适用对象
                String shopName = "";
                if(StaffConstants.Acct.ADAPT_TYPE_SHOP.equals(dto.getAdaptType())&&dto.getShopId()!=null){
                    ShopInfo si = shopMgrService.findShopByShopID(dto.getShopId());
                    if(si!=null&&StringUtil.isNotBlank(si.getShopFullName())){
                        shopName = si.getShopName();
                    }else{
                        shopName = "佚名店铺";
                    }
                }else{//非店铺：平台，企业
                    shopName = "未定义";
                }
                dto.setShopName(shopName);
                //2.资金类型
                dto.setAcctTypeValue(BaseParamUtil.fetchParamValue(
                        StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY, dto.getAcctType()));
                //3.资金适用类型
                dto.setAdaptTypeValue(BaseParamUtil.fetchParamValue(
                        StaffConstants.Acct.STAFF_ADAPT_TYPE_PARAMKEY, dto.getAdaptType()));
                return dto;
            }
            
        });
        
        return res;
    }

    @Override
    public void updateAcctTypeBykey(AcctTypeReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getAcctType(), reqDto.getAdaptType(), reqDto.getDeductOrderRatio() },
                new String[] {"资金类型", "资金适用类型", "抵扣比"});
        AcctType at = new AcctType();
        ObjectCopyUtil.copyObjValue(reqDto, at, null, false);
        at.setUpdateStaff(reqDto.getStaff().getId());
        at.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        try {
            acctTypeMapper.updateByPrimaryKeySelective(at);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据修改异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{ "资金账户类型" });
        }
    }
    
    @Override
    public List<AcctInfoResDTO> queryAcctWithOrderByStaff(TransactionContentReqDTO reqDto)
            throws BusinessException {
        LogUtil.info(MODULE, "查询买家资金账户信息");
        List<AcctInfoResDTO> res = new ArrayList<AcctInfoResDTO>();
        //参数有效性验证
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "参数异常/交易不存在" });
        }
        if(reqDto.getShopId()==null||reqDto.getStaffId()==null||reqDto.getOrderMoney()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{ "参数异常" });
        }
        
        //资金账户查询
        AcctInfo acctInfo = new AcctInfo();
        ObjectCopyUtil.copyObjValue(reqDto, acctInfo, null, false);
        List<AcctInfo> listAcctInfo = queryAcctInfoByStaff(acctInfo);
//        Long deductibleCap = 0L; //总抵扣金额
//        boolean capFlag = false; //超额标志
        for (AcctInfo ai : listAcctInfo) {
            AcctInfoResDTO aiResDto = new AcctInfoResDTO();
            ObjectCopyUtil.copyObjValue(ai, aiResDto, "id", false);
            aiResDto.setAcctName(BaseParamUtil.fetchParamValue(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY, aiResDto.getAcctType()));
            //计算可用金额
            TransactionContentReqDTO deuctReqDto = new TransactionContentReqDTO();
            ObjectCopyUtil.copyObjValue(ai, deuctReqDto, null, false);
            deuctReqDto.setOrderMoney(reqDto.getOrderMoney());
            Long deuctOrderMoney = queryDORAmountForOrder(deuctReqDto);
            //避免订单金额为负的情况
            if(deuctOrderMoney==null || deuctOrderMoney <= 0L){
                deuctOrderMoney = 0L;
            }else{//订单可用该帐户金额大于帐户余额，则直接取余额
                if(deuctOrderMoney>ai.getBalance()){
                    deuctOrderMoney = ai.getBalance();
                }
            }
            aiResDto.setDeductOrderMoney(deuctOrderMoney);//设定抵扣金额
            /*
            //总抵扣金额判断，不超交易金额（规则）
            //TODO 用户选择使用指定帐户？
            deductibleCap += deuctOrderMoney;//累积
            if(deductibleCap>reqDto.getOrderMoney()){
                if(capFlag){
                    aiResDto.setDeductOrderMoney(0L);
                }else{
                    Long diff = deuctOrderMoney-(deductibleCap-reqDto.getOrderMoney());//计算首次超额差值
                    aiResDto.setDeductOrderMoney(diff);
                }
                capFlag = true;
            }else{
                aiResDto.setDeductOrderMoney(deuctOrderMoney);
            }
            */
            res.add(aiResDto);
        }
        return res;
    }
    
    @Override
    public Long queryDORAmountForOrder(TransactionContentReqDTO reqDto) throws BusinessException {
        //1.得到订单关系账户类型相关抵扣比例
        AcctInfoReqDTO aiReqDto = new AcctInfoReqDTO();
        aiReqDto.setAcctType(reqDto.getAcctType());
        aiReqDto.setAdaptType(reqDto.getAdaptType());
        aiReqDto.setShopId(reqDto.getShopId());
        BigDecimal dor = queryAcctTypeDOR(aiReqDto);
        if(dor==null){
            return null;
        }
        //2.计算
        dor = dor.multiply(new BigDecimal(reqDto.getOrderMoney().toString()));
        return dor.longValue();
    }

	@Override
	public PageResponseDTO<AcctInfoResDTO> queryAcctInfoByStaff(AcctInfoReqDTO reqDto) throws BusinessException {
		if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        AcctInfoCriteria aiCriteria = new AcctInfoCriteria();
        AcctInfoCriteria.Criteria sql = aiCriteria.createCriteria();
               
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());    
        }else{
            sql.andStatusEqualTo(StaffConstants.Acct.ACCT_STATUS_ENABLE);
        }
        if(StringUtil.isNotBlank(reqDto.getAcctType())){
            sql.andAcctTypeEqualTo(reqDto.getAcctType());
        }   
        if(StringUtil.isNotBlank(reqDto.getAdaptType())){
            sql.andAdaptTypeEqualTo(reqDto.getAdaptType());
        }
        if(reqDto.getShopId()!=null){
            sql.andShopIdEqualTo(reqDto.getShopId());
        } 
        if(reqDto.getStaffId()!=null){
        	sql.andStaffIdEqualTo(reqDto.getStaffId());
        }else{
        	sql.andStaffIdEqualTo(reqDto.getStaff().getId());
        }
        aiCriteria.setLimitClauseCount(reqDto.getPageSize());
        aiCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        aiCriteria.setOrderByClause("create_time desc");
        
        return super.queryByPagination(reqDto, aiCriteria, true, new PaginationCallback<AcctInfo, AcctInfoResDTO>() {

            @Override
            public List<AcctInfo> queryDB(BaseCriteria criteria) {
                return acctInfoMapper.selectByExample((AcctInfoCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return acctInfoMapper.countByExample((AcctInfoCriteria)criteria);
            }
            
            @Override
            public List<Comparator<AcctInfo>> defineComparators(){
                List<Comparator<AcctInfo>> ls = new ArrayList<Comparator<AcctInfo>>();
                ls.add(new Comparator<AcctInfo>() {
                    public int compare(AcctInfo o1, AcctInfo o2) {
                    	//这里int的精度，会是个隐藏问题，不过时间跨度几千年，才有可能
                        return (int)(o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
                    }
                });
                return ls;
            }
            
            @Override
            public AcctInfoResDTO warpReturnObject(AcctInfo t) {
                AcctInfoResDTO dto = new AcctInfoResDTO();
                ObjectCopyUtil.copyObjValue(t,dto,null,true);
                //字段转义
                ShopInfo s =shopMgrService.findShopByShopID(t.getShopId());
                CustInfoResDTO c = custMgrService.findCustInfoById(t.getStaffId());
                dto.setAcctTypeName(BaseParamUtil.fetchParamValue(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY, 
                                                                           t.getAcctType()));//资金类型名
                dto.setAdpatTypeName(BaseParamUtil.fetchParamValue(StaffConstants.Acct.STAFF_ADAPT_TYPE_PARAMKEY, 
                															t.getAdaptType()));//资金适用类型名
                dto.setShopName(s!=null&&StringUtil.isNotBlank(s.getShopName())?s.getShopName():"佚名店铺");//店铺名
                dto.setStaffName(c!=null&&StringUtil.isNotEmpty(c.getStaffCode())?c.getStaffCode():"佚名客户");//账户所属用户名
                return dto;
            }
        });
	}

	@Override
	public void batchUpdatingAcctInfo(List<AcctInfoTempResDTO> listResDto, Long operatorId) throws BusinessException {
		
		if(CollectionUtils.isNotEmpty(listResDto)){
			String tradeType = StaffConstants.Trade.TRADE_TYPE_RECHARGE;
			String debitCredit = StaffConstants.Trade.ACCT_DC_INCOME;
			
			 List<Long> listId = new ArrayList<Long>();
			//1.更新账户
			for (AcctInfoTempResDTO acctInfoTempResDTO : listResDto) {
				listId.add(acctInfoTempResDTO.getId());
				
				String acctType = acctInfoTempResDTO.getAcctType();
				String adaptType = acctInfoTempResDTO.getAdaptType();
				Long shopId = Long.valueOf(acctInfoTempResDTO.getShopId());
				Long staffId = acctInfoTempResDTO.getStaffId();
				Long amount = MoneyUtil.convertYuanToCent(acctInfoTempResDTO.getTradeMoney());//导入时单位为“元”，入库前换算成“分”
				
				//2.1.判断用户账户是否存在
		        AcctInfoKey acctInfoKey = new AcctInfoKey();
		        acctInfoKey.setAcctType(acctType);
		        acctInfoKey.setAdaptType(adaptType);
		        acctInfoKey.setShopId(shopId);
		        acctInfoKey.setStaffId(staffId);
		        AcctInfo ai = this.findAcctInfoByKey(acctInfoKey);
		        AcctInfo aiOri = new AcctInfo(); //账户状态缓存
		        
		        //2.2.更新账户
		        if(ai==null){//账户不存在则新增，并初始化账户金额
		            ai = new AcctInfo();
		            ai.setAcctType(acctType);
		            ai.setAdaptType(adaptType);
		            ai.setShopId(shopId);
		            ai.setStaffId(staffId);
		            ai.setTotalMoney(amount);
		            ai.setBalance(amount);
		            ai.setFreezeMoney(0L);
		            ai.setCreateStaff(operatorId);
		            ai.setCreateTime(new Timestamp(System.currentTimeMillis()));
		            ai.setUpdateStaff(operatorId);
		            ai.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		            
		            long resAcctInfoId = this.saveAcctInfo(ai);
		            ai.setId(resAcctInfoId);
		            ObjectCopyUtil.copyObjValue(ai, aiOri, null, true);
		        }else{//账户存在则更新
		            ObjectCopyUtil.copyObjValue(ai, aiOri, null, true);
		            ai.setTotalMoney(ai.getTotalMoney()+amount);
		            ai.setBalance(ai.getBalance()+amount);
		            ai.setUpdateStaff(operatorId);
		            ai.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		            
		            this.updateAcctInfo(ai);
		        }
		        //3.新增资金账户交易明细记录
		        AcctTrade acctTrade = new AcctTrade();
		        acctTrade.setStaffId(staffId);
		        acctTrade.setTradeType(tradeType);//TODO 交易类型，现为“充值”
		        acctTrade.setDebitCredit(debitCredit);//TODO 借贷方向，现为“收入”
		        acctTrade.setOrderId("");//TODO 导入是否具有“订单”
		        acctTrade.setAcctType(acctType);
		        acctTrade.setAdaptType(adaptType);
		        acctTrade.setShopId(shopId);
		        acctTrade.setTradeMoney(amount);
		        acctTrade.setTotalMoney(aiOri!=null?aiOri.getTotalMoney():0L);
		        acctTrade.setBalance(aiOri!=null?aiOri.getBalance():0L);
		        acctTrade.setFreezeMoney(aiOri!=null?aiOri.getFreezeMoney():0L);//TODO 是否修改
		        acctTrade.setCreateStaff(operatorId);
		        acctTrade.setCreateTime(new Timestamp(System.currentTimeMillis()));
		        acctTrade.setUpdateStaff(operatorId);
		        acctTrade.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		        
		        acctTradeService.saveAcctTrade(acctTrade);
		        //4.新增资金账户历史快照记录 
		        AcctInfoHis acctInfoHis = new AcctInfoHis();
		        acctInfoHis.setAcctId(ai.getId());
		        acctInfoHis.setStaffId(ai.getStaffId());
		        acctInfoHis.setAcctType(ai.getAcctType());
		        acctInfoHis.setAdaptType(ai.getAdaptType());
		        acctInfoHis.setShopId(ai.getShopId());
		        acctInfoHis.setDebitCredit(debitCredit);
		        acctInfoHis.setTradeMoney(amount);
		        acctInfoHis.setTotalMoney(aiOri!=null?aiOri.getTotalMoney():0L);
		        acctInfoHis.setBalance(aiOri!=null?aiOri.getBalance():0L);
		        acctInfoHis.setFreezeMoney(aiOri!=null?aiOri.getFreezeMoney():0L);
		        acctInfoHis.setCreateStaff(operatorId);
		        acctInfoHis.setCreateTime(new Timestamp(System.currentTimeMillis()));
		        acctInfoHis.setUpdateStaff(operatorId);
		        acctInfoHis.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		        
		        this.saveAcctInfoHis(acctInfoHis);
			}
	        
	        //2.更新资金账户临时数据
			acctInfoAideSV.updateHandledAcctInfoTemps(operatorId, listId);
		}
		
	}
}

