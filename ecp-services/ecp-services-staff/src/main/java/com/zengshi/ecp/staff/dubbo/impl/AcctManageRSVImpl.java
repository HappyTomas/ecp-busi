package com.zengshi.ecp.staff.dubbo.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.datainout.DataImportHandler;
import com.zengshi.ecp.server.util.DataInoutUtil;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
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
import com.zengshi.ecp.staff.dubbo.interfaces.IAcctManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoAideSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctTradeSV;
import com.zengshi.paas.utils.FileUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015年8月14日下午5:07:23  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class AcctManageRSVImpl implements IAcctManageRSV {
    
    private static final String MODULE = AcctManageRSVImpl.class.getName();
    
    @Resource
    private IAcctInfoSV acctInfoService;
    
    @Resource
    private IAcctTradeSV acctTradeService;
    
    @Resource
    private IAcctInfoAideSV acctInfoAideSV;

    @Override
    public Long queryDORAmountForOrder(TransactionContentReqDTO reqDto) throws BusinessException {
        return acctInfoService.queryDORAmountForOrder(reqDto);
    }

    @Override
    public AcctSumResDTO queryAcctSumRelatedShops(AcctInfoReqDTO reqDto) throws BusinessException {
        
        return acctInfoService.queryAcctSumRelatedShops(reqDto);
    }

    @Override
    public AcctSumResDTO queryAcctSumByShop(AcctInfoReqDTO reqDto) throws BusinessException {
        
        return acctInfoService.queryAcctSumByShop(reqDto);
    }

    @Override
    public PageResponseDTO<AcctInfoResDTO> listAccountByShop(ShopRelatedAcctsReqDTO reqDto) throws BusinessException {
        return acctInfoService.queryAcctInfoByShop(reqDto);
    }

    @Override
    public List<AcctInfoResDTO> queryAcctWithOrderByStaff(TransactionContentReqDTO reqDto)
            throws BusinessException {
        
        return acctInfoService.queryAcctWithOrderByStaff(reqDto);
    }

    @Override
    public void updateAcctBalance(TransactionContentReqDTO reqDto) throws BusinessException {
        
        acctTradeService.updateAcctBalance(reqDto);
    }
    
    @Override
    public void updateAcctBalance(ListReqDTO<TransactionContentReqDTO> listReqDto) throws BusinessException {
        
        acctTradeService.updateAcctBalance(listReqDto);
    }

    @Override
    public void importAcctInfoCapitalIncrementAboutShop(FileImportReqDTO reqDto) throws BusinessException {
    	Long operatorId = reqDto.getStaff().getId();//当前登录用户 
    	
    	AcctInfoTempReqDTO reqRec = new AcctInfoTempReqDTO();
    	reqRec.setCreateStaff(operatorId);
    	reqRec.setPageNo(0);//全局查询
    	PageResponseDTO<AcctInfoTempResDTO> resRec = acctInfoAideSV.listAcctInfoTemp(reqRec);
    	List<AcctInfoTempResDTO> listRec = resRec.getResult();
    	//总记录数
    	Long countRec = resRec.getCount();
    	if(countRec==0){
    		//请先导入数据文件
    		throw new BusinessException("staff.100047");
    	}
    	
    	//验证是否可正式录入
    	Long countCommitRec = 0L;
    	for (AcctInfoTempResDTO acctInfoTempResDTO : listRec) {
    		if(StaffConstants.Acct.ACCT_TEMP_IS_GODD_N.equals(acctInfoTempResDTO.getIsGood())){
    			//存在不合法数据，请查看列表，重新整理后导入新的数据文件
    			throw new BusinessException("staff.100048");
    		}
    		if(StaffConstants.Acct.ACCT_TEMP_IS_COMMIT_Y.equals(acctInfoTempResDTO.getIsCommit())){
    			countCommitRec++;
    		}
		}
    	if(countCommitRec>0){//导入成功，核对成功数与总数是否持平
    		if(countCommitRec==countRec){//成功，提示(上一次的导入操作已成功提交，请导入新的数据文件)，清空
    			acctInfoAideSV.removeAcctInfoTempsBelongToCreator(operatorId);
    			throw new BusinessException("staff.100049");
    		}else{//部分成功，提示(上一次的导入操作部分提交成功，请核查后继续操作)，不清空
    			throw new BusinessException("staff.100050");
    		}
    	}
    	//资金账户类型简单参数list  STAFF_ACCT_TYPE
    	List<BaseParamDTO> listBaseParam = BaseParamUtil.fetchParamList(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY);
    	//正式导入
    	List<AcctInfoTempResDTO> listBeSave = new ArrayList<AcctInfoTempResDTO>();
    	Long beCount = 1L;
    	for (AcctInfoTempResDTO acctInfoTempResDTO : listRec) {
    		//转义
    		for (BaseParamDTO baseParamDTO : listBaseParam) {
				if(acctInfoTempResDTO.getAcctType().equals(baseParamDTO.getSpValue())){
					acctInfoTempResDTO.setAcctType(baseParamDTO.getSpCode());
					break;
				}
			}
    		listBeSave.add(acctInfoTempResDTO);
    		if(beCount%10==0||beCount==countRec){//每10条打包保存，查到最一条直接打包
    			acctInfoService.batchUpdatingAcctInfo(listBeSave, operatorId);
    			listBeSave = new ArrayList<AcctInfoTempResDTO>();
            }
    		beCount++;
    	}
    }

    @Override
    public PageResponseDTO<AcctTypeResDTO> queryAcctType(AcctTypeReqDTO reqDto)
            throws BusinessException {
        
        return acctInfoService.queryAcctType(reqDto);
    }

    @Override
    public void saveAcctType(AcctTypeReqDTO reqDto) throws BusinessException {
        
        acctInfoService.saveAcctType(reqDto);
    }

    @Override
    public void updateAcctType(AcctTypeReqDTO reqDto) throws BusinessException {
        
        acctInfoService.updateAcctTypeBykey(reqDto);
    }

    @Override
    public void deleteAcctType(AcctTypeReqDTO reqDto) throws BusinessException {
        
        acctInfoService.deleteAcctTypeByKey(reqDto);
    }

    @Override
    public AcctTypeResDTO findAcctType(AcctTypeReqDTO reqDto) throws BusinessException {
        
        return acctInfoService.findAcctTypeByKey(reqDto);
    }

    @Override
    public void executeOrderRefund(TransactionContentReqDTO reqDto) throws BusinessException {
        
        acctTradeService.executeOrderRefund(reqDto);
    }

	@Override
	public PageResponseDTO<AcctTradeResDTO> listAcctTrade(AcctTradeReqDTO reqDto) throws BusinessException {
		return acctTradeService.listAcctTrade(reqDto);
	}

	@Override
	public PageResponseDTO<AcctInfoResDTO> queryAcctInfoByStaff(AcctInfoReqDTO reqDto) throws BusinessException {
		return acctInfoService.queryAcctInfoByStaff(reqDto);
	}

	@Override
	public void importAcctInfoTempsAboutShop(FileImportReqDTO reqDto) throws BusinessException {
		byte[] byteFile = FileUtil.readFile(reqDto.getFileId());//根据文件id得到文件
		LogUtil.info(MODULE, "fileid"+reqDto.getFileId());
		
        InputStream inputs = new ByteArrayInputStream(byteFile);
        //importExcel 调用局部final 变量
        final FileImportReqDTO importDto = new FileImportReqDTO();
        ObjectCopyUtil.copyObjValue(reqDto, importDto, null, false);
        
        //导入前清空上次数据
        acctInfoAideSV.removeAcctInfoTempsBelongToCreator(reqDto.getStaff().getId());
        
        //文件解析
        DataInoutUtil.importExcel(new DataImportHandler(inputs, reqDto.getFileName(), "xls", "staff", reqDto.getStaff().getStaffCode()) {
			
			@Override
			public boolean doCallback(List<List<Object>> datas, String fileId) {
                int beCount = 0;//excel记录行记数
                List<AcctInfoTempReqDTO> listAcctInfoTemp = new ArrayList<AcctInfoTempReqDTO>();
                for (List<Object> row : datas) {
                    if(beCount==0){//跳过标题行
                    	beCount++;
                        continue;
                    }
                    Map<String, Object> mapValidate = acctInfoAideSV.validateImportAcctInfoCapitalIncrementAboutShop(row, false);
                    AcctInfoTempReqDTO reqDtoAIT = new AcctInfoTempReqDTO();
                    if(mapValidate.get("relAuthStaff")!=null){
                    	reqDtoAIT.setStaffId(((AuthStaffCIDX)mapValidate.get("relAuthStaff")).getStaffId());
                    }
                    reqDtoAIT.setAcctType((String)mapValidate.get("acctType"));
                    reqDtoAIT.setShopId((String)mapValidate.get("shopId"));
                    reqDtoAIT.setStaffCode((String)mapValidate.get("staffCode"));
                    reqDtoAIT.setTradeMoney((String)mapValidate.get("tradeMoney"));
                    reqDtoAIT.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);
                    reqDtoAIT.setIsGood((String)mapValidate.get("isGood"));
                    reqDtoAIT.setIsCommit(StaffConstants.Acct.ACCT_TEMP_IS_COMMIT_N);
                    reqDtoAIT.setRecordDesc((String)mapValidate.get("recordDesc"));
                    listAcctInfoTemp.add(reqDtoAIT);
                    if(beCount%10==0||beCount==(datas.size()-1)){//每10条打包保存，查到最一条直接打包
                    	acctInfoAideSV.batchSavingAcctInfoTemp(listAcctInfoTemp);
                    	listAcctInfoTemp = new ArrayList<AcctInfoTempReqDTO>();
                    }
                    
                    beCount++;
                }
				
				return true;
			}
		}, 1, 1);
	}

	@Override
	public PageResponseDTO<AcctInfoTempResDTO> listAcctInfoTemp(AcctInfoTempReqDTO reqDto) throws BusinessException {
		return acctInfoAideSV.listAcctInfoTemp(reqDto);
	}
    
}

