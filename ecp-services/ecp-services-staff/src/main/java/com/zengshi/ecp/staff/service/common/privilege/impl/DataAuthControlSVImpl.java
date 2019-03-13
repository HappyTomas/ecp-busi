package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.server.auth.AbstractRuleOfDataAuth;
import com.zengshi.ecp.server.auth.RuleObject;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2RuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataAuthMapper;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.DataAuth;
import com.zengshi.ecp.staff.dao.model.DataAuthCriteria;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthControlSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2016年1月19日上午10:32:20  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class DataAuthControlSVImpl extends AbstractRuleOfDataAuth  implements IDataAuthControlSV {
	
	@Resource
	private IDataAuthManageSV dataAuthManageSV;//数据权限服务
	
	@Resource
	private IRoleManageSV roleManageSV;//角色
	
	@Resource
	private IAuthRelManageSV authRelManageSV; //用户关联角色、用户组关系服务
	
	@Resource
	private AuthPrivilege2RuleMapper authPrivilege2RuleMapper;//权限规则关系
	
	@Resource
	private DataAuthMapper dataAuthMapper;//数据规则表
	
	
	@Resource(name="staffTools")
    private StaffTools staffTools;

	@Override
	public List<RuleObject> getRuleObjects(String funcCode, long staffId) {
		//参数校验
		staffTools.paramCheck(new Object[]{staffId,funcCode}, new String[]{"用户id", "数据功能id"});
		List<RuleObject> listRes = new ArrayList<RuleObject>();//返回RuleObject list
		DataFuncReqDTO dataFuncReqDto = new DataFuncReqDTO();
		dataFuncReqDto.setCode(funcCode);
		dataFuncReqDto.setPageNo(0);//查全集
		PageResponseDTO<DataFuncResDTO> pageDataFunc= dataAuthManageSV.listDataFunc(dataFuncReqDto);
		if(pageDataFunc.getCount()!=1){
			//功能编码["+funcCode+"]数量不为'1',存在异常
			throw new BusinessException();//TODO  expt code
		}
		DataFuncResDTO dataFuncResDto = pageDataFunc.getResult().get(0);//对应数据功能
		
		//list ruleObject
		List<List<RuleObject>> listListRuleObject = new ArrayList<List<RuleObject>>();
		
		//map ruleItem
		Map<Long, DataRuleItemResDTO> mapDataRuleItem = new HashMap<Long, DataRuleItemResDTO>();
		
		//得到功能下的“item”
		DataRuleItemReqDTO ruleItemReqDto = new DataRuleItemReqDTO();
		ruleItemReqDto.setFuncId(dataFuncResDto.getId());
		ruleItemReqDto.setPageNo(0);//查全集
		PageResponseDTO<DataRuleItemResDTO> pageDataRuleItem = dataAuthManageSV.listDataRuleItem(ruleItemReqDto);
		if(pageDataRuleItem.getCount()>0){
			for (DataRuleItemResDTO dto : pageDataRuleItem.getResult()) {
				mapDataRuleItem.put(dto.getId(), dto);
			}
		}
		
		//1.通过staffId查找用户相关角色
		//1.1找到用户角色关系
		AuthStaff2Role staffRoleRel = new AuthStaff2Role();
		staffRoleRel.setStaffId(staffId);
		List<AuthStaff2Role> listStaffRoleRel = authRelManageSV.listStaffRoleRel(staffRoleRel);
		if(CollectionUtils.isEmpty(listStaffRoleRel)){//角色关系为空则返回
			return listRes;
		}
		//2.遍历用户关联各角色下的功能规则集合
		for (AuthStaff2Role authStaff2Role : listStaffRoleRel) {//数据功能下的明细
			//list RuleObject
			List<RuleObject> listOfFunc = new ArrayList<RuleObject>();
			//找到角色权限关系
			AuthRole2Privilege authRole2Privilege = new AuthRole2Privilege();
			authRole2Privilege.setRoleId(authStaff2Role.getRoleId());
			List<AuthRole2Privilege> listPrivilegeRoleRel = roleManageSV.listPrivilegeRoleRel(authRole2Privilege);
			if(CollectionUtils.isEmpty(listPrivilegeRoleRel)) continue;//找不到关系，则跳过
			List<Long> listPrivlgId = new ArrayList<Long>();
			for (AuthRole2Privilege objRel : listPrivilegeRoleRel) {
				listPrivlgId.add(objRel.getPrivilegeId());
			}

			//找到规则权限关系
			AuthPrivilege2RuleCriteria privilege2RuleExample = new AuthPrivilege2RuleCriteria();
			privilege2RuleExample.createCriteria().andPrivilegeIdIn(listPrivlgId);
			List<AuthPrivilege2Rule> listPrivilegeRuleRel = authPrivilege2RuleMapper.selectByExample(privilege2RuleExample);
			if(CollectionUtils.isEmpty(listPrivilegeRuleRel)) continue;//找不到关系，则跳过
			List<Long> listRuleId = new ArrayList<Long>();
			for (AuthPrivilege2Rule authPrivilege2Rule : listPrivilegeRuleRel) {
				listRuleId.add(authPrivilege2Rule.getRuleId());
			}
			//list ruleObject
			List<List<RuleObject>> listRuleObjectInner = new ArrayList<List<RuleObject>>();
			
			//遍历规则对象
			DataAuthCriteria dataAuthCriteria = new DataAuthCriteria();
			dataAuthCriteria.createCriteria().andEnabledEqualTo(StaffConstants.PublicParam.STATUS_ACTIVE)//仅“启用”状态
				.andIdIn(listRuleId).andFuncIdEqualTo(dataFuncResDto.getId());
			List<DataAuth> listDataAuth = dataAuthMapper.selectByExample(dataAuthCriteria);
			for (DataAuth dataAuth : listDataAuth) {//规则下的明细
				//list RuleObject
				List<RuleObject> list = new ArrayList<RuleObject>();
				//规则明细
				DataRuleReqDTO dataRuleReqDto = new DataRuleReqDTO();
				dataRuleReqDto.setAuthId(dataAuth.getId());
				dataRuleReqDto.setPageNo(0);//查全集
				PageResponseDTO<DataRuleResDTO> pageDataRule = dataAuthManageSV.listDataRule(dataRuleReqDto);
				if(pageDataRule.getCount()>0){
					for (DataRuleResDTO dataRule : pageDataRule.getResult()) {
						//RuleObject(String name,String field,String op,Object value,boolean isOr,boolean hasLeft,boolean hasRight)
						DataRuleItemResDTO itemDTO = mapDataRuleItem.get(dataRule.getItemId());
						if(itemDTO==null) continue;//无则跳过
						String name = itemDTO.getAttrName();
						String field = itemDTO.getFieldName();
						String op = dataRule.getOperChar();
						String value = dataRule.getInputValue();
						Boolean isOr = StringUtil.isNotBlank(dataRule.getLogicChar())?"or".equals(dataRule.getLogicChar())?true:false:false;
						Boolean hasLeft = StringUtil.isNotBlank(dataRule.getFrontChar());
						Boolean hasRight = StringUtil.isNotBlank(dataRule.getPostChar());
						if("between".equals(dataRule.getOperChar())){
							String[] arrayS = value.split(",");
							list.add(new RuleObject(name,field,op,arrayS,isOr,hasLeft,hasRight));
							continue;
						}
						if("in".equals(dataRule.getOperChar())){
							List<String> listS = new ArrayList<String>();
							listS = Arrays.asList(value.split(","));
							list.add(new RuleObject(name,field,op,listS,isOr,hasLeft,hasRight));
							continue;
						}
						list.add(new RuleObject(name,field,op,value,isOr,hasLeft,hasRight));
					}
				}
				listRuleObjectInner.add(list);
			}
			listOfFunc = super.addRuleObjects(listRuleObjectInner);
			listListRuleObject.add(listOfFunc);
		}
		
		listRes = super.addRuleObjects(listListRuleObject);
		return listRes;
	}

	@Override
	public boolean judgeAuthOfCurrentUser(String funcCode, long staffId) {
		//参数校验
		staffTools.paramCheck(new Object[]{staffId,funcCode}, new String[]{"用户id", "数据功能id"});
		DataFuncReqDTO dataFuncReqDto = new DataFuncReqDTO();
		dataFuncReqDto.setCode(funcCode);
		dataFuncReqDto.setPageNo(0);//查全集
		PageResponseDTO<DataFuncResDTO> pageDataFunc= dataAuthManageSV.listDataFunc(dataFuncReqDto);
		if(pageDataFunc.getCount()!=1){
			//功能编码["+funcCode+"]数量不为'1',存在异常
			throw new BusinessException();//TODO  expt code
		}
		DataFuncResDTO dataFuncResDto = pageDataFunc.getResult().get(0);//对应数据功能
		
		//1.通过staffId查找用户相关角色
		//1.1找到用户角色关系
		AuthStaff2Role staffRoleRel = new AuthStaff2Role();
		staffRoleRel.setStaffId(staffId);
		List<AuthStaff2Role> listStaffRoleRel = authRelManageSV.listStaffRoleRel(staffRoleRel);
		if(CollectionUtils.isEmpty(listStaffRoleRel)){//角色关系为空则返回
			return false;
		}
		//2.找到功能下的规则
		DataAuthCriteria dataAuthCriteria = new DataAuthCriteria();
		dataAuthCriteria.createCriteria().andFuncIdEqualTo(dataFuncResDto.getId());
		List<DataAuth> listDataAuth = dataAuthMapper.selectByExample(dataAuthCriteria);
		List<Long> listAuthIds = new ArrayList<Long>();
		if(listDataAuth!=null&&listDataAuth.size()>0){
			for (DataAuth dataAuthId : listDataAuth) {
				listAuthIds.add(dataAuthId.getId());
			}
		}
		//3.遍历用户关联各角色下的功能规则集合
		for (AuthStaff2Role authStaff2Role : listStaffRoleRel) {
			//找到角色权限关系
			AuthRole2Privilege authRole2Privilege = new AuthRole2Privilege();
			authRole2Privilege.setRoleId(authStaff2Role.getRoleId());
			List<AuthRole2Privilege> listPrivilegeRoleRel = roleManageSV.listPrivilegeRoleRel(authRole2Privilege);
			if(CollectionUtils.isEmpty(listPrivilegeRoleRel)) continue;//找不到关系，则跳过
			List<Long> listPrivlgId = new ArrayList<Long>();
			for (AuthRole2Privilege objRel : listPrivilegeRoleRel) {
				listPrivlgId.add(objRel.getPrivilegeId());
			}

			//找到规则权限关系
			AuthPrivilege2RuleCriteria privilege2RuleExample = new AuthPrivilege2RuleCriteria();
			privilege2RuleExample.createCriteria().andPrivilegeIdIn(listPrivlgId);
			List<AuthPrivilege2Rule> listPrivilegeRuleRel = authPrivilege2RuleMapper.selectByExample(privilege2RuleExample);
			if(CollectionUtils.isEmpty(listPrivilegeRuleRel)) continue;//找不到关系，则跳过
			for (AuthPrivilege2Rule authPrivilege2Rule : listPrivilegeRuleRel) {
				if(listAuthIds.contains(authPrivilege2Rule.getRuleId())){
					return true;
				}
			}
			
		}
		
		return false;
	}
	
	

	

}

