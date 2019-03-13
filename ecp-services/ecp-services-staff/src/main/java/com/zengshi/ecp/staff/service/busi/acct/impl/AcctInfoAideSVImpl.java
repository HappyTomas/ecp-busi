package com.zengshi.ecp.staff.service.busi.acct.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AcctInfoTempMapper;
import com.zengshi.ecp.staff.dao.model.AcctInfoTemp;
import com.zengshi.ecp.staff.dao.model.AcctInfoTempCriteria;
import com.zengshi.ecp.staff.dao.model.AcctTypeKey;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctInfoTempResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AcctTypeResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoAideSV;
import com.zengshi.ecp.staff.service.busi.acct.interfaces.IAcctInfoSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.ICustManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2015年11月21日上午10:42:44  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class AcctInfoAideSVImpl extends GeneralSQLSVImpl implements IAcctInfoAideSV {

	private static final String MODULE = AcctInfoAideSVImpl.class.getName();
	
	@Resource
	private AcctInfoTempMapper acctInfoTempMapper;  //资金账户临时对象Mapper
	
	@Resource(name="staffTools")
    private StaffTools staffTools;	//staff工具服务
	
	@Resource
    private IShopMgrSV shopMgrService; //店铺管理服务
    
    @Resource
    private ICustManageSV custMgrService; //客户管理服务
    
    @Resource
    private IAcctInfoSV acctInfoSV; //资金账户服务
	
	@Resource(name="seq_acct_info_temp_id")
	private PaasSequence seqAcctInfoTemp;
	
	@Override
	public void batchSavingAcctInfoTemp(List<AcctInfoTempReqDTO> list) throws BusinessException {
		//参数校验
		staffTools.paramCheck(new Object[] {list}, new String[] {"入参"});
		for (AcctInfoTempReqDTO dto : list) {
			this.saveAcctInfoTemp(dto);
		}
		
	}

	@Override
	public void removeAcctInfoTempsBelongToCreator(Long staffId) throws BusinessException {
		//参数校验
		staffTools.paramCheck(new Object[] {staffId}, new String[] {"入参"});
		AcctInfoTempCriteria aiTempCriteria = new AcctInfoTempCriteria();
		AcctInfoTempCriteria.Criteria sql = aiTempCriteria.createCriteria();
		sql.andCreateStaffEqualTo(staffId);
		try {
			acctInfoTempMapper.deleteByExample(aiTempCriteria);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "批量删除资金账户临时数据对象异常", e);
			throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
		}
	}

	@Override
	public PageResponseDTO<AcctInfoTempResDTO> listAcctInfoTemp(AcctInfoTempReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramCheck(new Object[] {reqDto,reqDto.getCreateStaff()}, new String[] {"入参","数据导入人ID"});
		
		PageResponseDTO<AcctInfoTempResDTO> res = new PageResponseDTO<AcctInfoTempResDTO>();
		AcctInfoTempCriteria aiTempCriteria = new AcctInfoTempCriteria();
		AcctInfoTempCriteria.Criteria sql = aiTempCriteria.createCriteria();
		sql.andCreateStaffEqualTo(reqDto.getCreateStaff());
		
		aiTempCriteria.setLimitClauseCount(reqDto.getPageSize());
		aiTempCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
		
		res = super.queryByPagination(reqDto, aiTempCriteria, true, new PaginationCallback<AcctInfoTemp, AcctInfoTempResDTO>() {

			@Override
			public List<AcctInfoTemp> queryDB(BaseCriteria criteria) {
				return acctInfoTempMapper.selectByExample((AcctInfoTempCriteria) criteria);
			}

			@Override
			public long queryTotal(BaseCriteria criteria) {
				return acctInfoTempMapper.countByExample((AcctInfoTempCriteria) criteria);
			}

			@Override
			public AcctInfoTempResDTO warpReturnObject(AcctInfoTemp t) {
				AcctInfoTempResDTO dto = new AcctInfoTempResDTO();
				ObjectCopyUtil.copyObjValue(t, dto, null, true);
				return dto;
			}
			
		});
		
		return res;
	}

	@Override
	public long saveAcctInfoTemp(AcctInfoTempReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramCheck(new Object[] {reqDto}, new String[] {"入参"});
		AcctInfoTemp aiTemp = new AcctInfoTemp();
		ObjectCopyUtil.copyObjValue(reqDto, aiTemp, null, true);
		aiTemp.setId(seqAcctInfoTemp.nextValue());
		aiTemp.setCreateStaff(reqDto.getStaff().getId());
		aiTemp.setCreateTime(DateUtil.getSysDate());
		try {
			acctInfoTempMapper.insertSelective(aiTemp);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "资金账户临时数据对象保存异常", e);
			throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
		}
		return aiTemp.getId();
	}

	@Override
	public void updateHandledAcctInfoTemps(Long staffId, List<Long> listId) throws BusinessException {
		//参数校验
		staffTools.paramCheck(new Object[] {staffId, listId}, new String[] {"创建人id","acctInfoTempId List"});
		AcctInfoTemp record = new AcctInfoTemp();
		record.setIsCommit(StaffConstants.Acct.ACCT_TEMP_IS_COMMIT_Y);
		AcctInfoTempCriteria example = new AcctInfoTempCriteria();
		example.createCriteria().andIdIn(listId).andCreateStaffEqualTo(staffId);
		try {
			acctInfoTempMapper.updateByExampleSelective(record, example);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "资金账户临时数据对象更新异常", e);
			throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
		}
	}

	@Override
	public Map<String, Object> validateImportAcctInfoCapitalIncrementAboutShop(List<Object> shopCapital, boolean ifThrowE)
			throws BusinessException {
		
		Map<String, Object> res = new HashMap<String, Object>();
		boolean isGood = true;
		StringBuilder recordDesc = new StringBuilder();
		
		// 基础校验：店铺编码，金额必须为数字
		Map<String, String> mapData = new HashMap<String, String>();//是否进一步进行业务数据有效性校验
		mapData.put("cell0", "");
		mapData.put("cell1", "");
		mapData.put("cell2", "");
		mapData.put("cell3", "");
        String regexNumeric = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";//为数值
//        String regexNumber = "[0-9]+";//为数字
		String[] regexs = new String[] {regexNumeric,"","",regexNumeric};
		int[] cellLengths = {16,16,32,16};//店铺编码，资金类型，登录工号，金额
		Map<String, Boolean> mapValidateFlag = new HashMap<String, Boolean>();//是否进一步进行业务数据有效性校验
		mapValidateFlag.put("cell0", true);
		mapValidateFlag.put("cell1", true);
		mapValidateFlag.put("cell2", true);
		mapValidateFlag.put("cell3", true);
		
		//资金账户类型简单参数list  STAFF_ACCT_TYPE
		List<BaseParamDTO> listBaseParam = BaseParamUtil.fetchParamList(StaffConstants.Acct.STAFF_ACCT_TYPE_PARAMKEY);
		
		for (int i = 0; i < 4; i++) {
			if(shopCapital.get(i)==null){
				isGood = false;
				mapValidateFlag.put("cell"+i, false);
				recordDesc.append(",cell "+(i+1)+" 不能为空");
			}else {
				if(shopCapital.get(i).toString().length()>cellLengths[i]){
					throw new BusinessException(StaffConstants.Acct.E_IMPORT_CELL_TOOLONG, new String[]{String.valueOf(i+1)});
				}
				mapData.put("cell"+i,shopCapital.get(i).toString());
				if(regexs[i]!=""&&!(shopCapital.get(i).toString().matches(regexs[i]))){//格式正则验证
					isGood = false;
					mapValidateFlag.put("cell"+i, false);
					recordDesc.append(",cell "+(i+1)+" 不符合要求");
				}else{
					if(i==0){//处理店铺id。由于excel单元格式，而使“数字”有：“1”或者“1.0”的两种不同形态，统一为“1”格式
						int indexPos = mapData.get("cell0").indexOf(".");
						if(indexPos>0){
							mapData.put("cell0", mapData.get("cell0").substring(0, indexPos));
						}
					}
				}
			}
		}
        
        String acctType = mapData.get("cell1");
        String staffCode = mapData.get("cell2");
        //long amount = MoneyUtil.convertYuanToCent(mapData.get("cell3"));//导入时单位为“元”，入库前换算成“分”
        
        // TODO 对金额进行有效性校验(精确度等)
        //1.判断[店铺]是否存在、[用户]是否存在、[资金类型]是否存在
        ShopInfo si = null;
        if(mapValidateFlag.get("cell0")){
        	Long shopId = Long.valueOf(mapData.get("cell0"));
	        si = shopMgrService.findShopByShopID(shopId);
	        if(si==null){
	        	if(ifThrowE){
	        		throw new BusinessException(StaffConstants.Acct.E_IMPORT_SHOP_UNFINDED, new String[] { shopId.toString() });
	        	}else{
	        		isGood = false;
	        		recordDesc.append(", 店铺不存在");
	        	}
	        }
	        //店铺存在，再查“资金类型”
	        if(mapValidateFlag.get("cell1")){
	        	//类型参数验证，资金账户转义
				String acctTypeCode = "";
				for (BaseParamDTO baseParamDTO : listBaseParam) {
					if(acctType.equals(baseParamDTO.getSpValue())){
						acctTypeCode = baseParamDTO.getSpCode();
						break;
					}
				}
				if(StringUtil.isNotBlank(acctTypeCode)){
					//类型对象验证
			        AcctTypeKey acctTypeKey = new AcctTypeKey();
			        acctTypeKey.setAcctType(acctTypeCode);
			        acctTypeKey.setAdaptType(StaffConstants.Acct.ADAPT_TYPE_SHOP);//店铺资金
			        acctTypeKey.setShopId(shopId);
			        AcctTypeReqDTO acctTypeReqDTO = new AcctTypeReqDTO();
			        ObjectCopyUtil.copyObjValue(acctTypeKey, acctTypeReqDTO, null, false);
			        AcctTypeResDTO at = acctInfoSV.findAcctTypeByKey(acctTypeReqDTO);
			        if(at==null){
			        	if(ifThrowE){
			        		throw new BusinessException(StaffConstants.Acct.E_IMPORT_ACCTTYPE_UNFINDED, new String[] { shopId.toString(), acctType, staffCode });
			        	}else{
			        		isGood = false;
			        		recordDesc.append(", 店铺"+shopId.toString()+"的资金账户类型未定义");
			        	}
			        }
				}else{
					isGood = false;
	        		recordDesc.append(", 资金账户类型不存在");
				}
				
	        }
        }
        
        AuthStaffCIDX cidx = null;
        if(mapValidateFlag.get("cell2")){
	        AuthStaffCIDX staffCustIDX = new AuthStaffCIDX();
	        staffCustIDX.setStaffCode(staffCode);
	        cidx = custMgrService.findAuthStaffCIDXByCode(staffCustIDX);
	        if(cidx==null){
	        	if(ifThrowE){
	        		throw new BusinessException(StaffConstants.Acct.E_IMPORT_CUST_UNFINDED, new String[] { staffCode });
	        	}else{
	        		isGood = false;
	        		recordDesc.append(", 客户不存在");
	        	}
	        }
        }
        
        //设置响应数据
        res.put("shopId", mapData.get("cell0")); //店铺编号
        res.put("acctType", mapData.get("cell1"));	//资金类型
        res.put("staffCode", mapData.get("cell2"));	//用户工号
        res.put("tradeMoney", mapData.get("cell3"));	//金额
        res.put("relAuthStaff", cidx); //相关用户对象
        res.put("relShop", si);//相关店铺对象
        res.put("isGood", isGood?StaffConstants.Acct.ACCT_TEMP_IS_GOOD_Y:StaffConstants.Acct.ACCT_TEMP_IS_GODD_N);
        String recordDescStr = "合法数据";
        if(recordDesc.length()>0){
        	recordDescStr = recordDesc.toString().substring(1);
        }
        res.put("recordDesc", recordDescStr);
        
		return res;
	}

}

