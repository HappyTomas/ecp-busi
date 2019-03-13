package com.zengshi.ecp.staff.dubbo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.zengshi.ecp.server.auth.attribute.AbstractRuleOfDataFilter;
import com.zengshi.ecp.server.auth.attribute.FilterRule;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2RuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataAuthMapper;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleCriteria;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.DataAuth;
import com.zengshi.ecp.staff.dao.model.DataAuthCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthRoleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthRelManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IRoleManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: <br>
 * Date:2016年5月7日下午4:42:38  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.7
 */
public class DataFilterControlRSVImpl extends AbstractRuleOfDataFilter  {

	@Resource
	private IDataAuthManageSV dataAuthManageSV;//数据权限服务
	
	@Resource
	private IRoleManageSV roleManageSV;//角色
	
	@Resource
	private IAuthManageSV authManageSV;//用户
	
	@Resource
	private IAuthRelManageSV authRelManageSV; //用户关联角色、用户组关系服务
	
	@Resource
	private AuthPrivilege2RuleMapper authPrivilege2RuleMapper;//权限规则关系
	
	@Resource
	private DataAuthMapper dataAuthMapper;//数据规则表
	
	@Resource(name="staffTools")
    private StaffTools staffTools;
	
	
	@Override
	public List<FilterRule> getRules(String funcCode, Long staffId) {
		//参数校验
		staffTools.paramCheck(new Object[]{staffId, funcCode}, new String[]{"用户id", "数据功能id"});
		List<FilterRule> listRes = new ArrayList<FilterRule>();//返回FilterRule list
		Set<FilterRule> setRes = new HashSet<FilterRule>();//返回FilterRule set
		DataFuncReqDTO dataFuncReqDto = new DataFuncReqDTO();
		if (staffId != 0L) {
			StaffLocaleUtil.getStaff().setId(staffId);
		}
		LogUtil.info("DataAuthControlRSVImpl","方法：getRuleObjects-->规则权限用户id改为从DTO里取:" + staffId);
		dataFuncReqDto.setCode(funcCode);
		dataFuncReqDto.setPageNo(0);//查全集
		PageResponseDTO<DataFuncResDTO> pageDataFunc= dataAuthManageSV.listDataFunc(dataFuncReqDto);
		if(pageDataFunc.getCount()!=1){
			//功能编码["+funcCode+"]数量不为'1',存在异常
			throw new BusinessException("staff.100061", new String[]{ funcCode });
		}
		DataFuncResDTO dataFuncResDto = pageDataFunc.getResult().get(0);//对应数据功能
		
		//list FilterRule
		List<List<FilterRule>> listListRuleObject = new ArrayList<List<FilterRule>>();
		
		//map fieldItem
		Map<Long, DataFieldItemResDTO> mapDataFieldItem = new HashMap<Long, DataFieldItemResDTO>();
		
		//得到功能下的“item”
		DataFieldItemReqDTO fieldItemReqDto = new DataFieldItemReqDTO();
		fieldItemReqDto.setFuncId(dataFuncResDto.getId());
		fieldItemReqDto.setPageNo(0);//查全集
		PageResponseDTO<DataFieldItemResDTO> pageDataFieldItem = dataAuthManageSV.listDataFieldItem(fieldItemReqDto);
		if(pageDataFieldItem.getCount()>0){
			for (DataFieldItemResDTO dto : pageDataFieldItem.getResult()) {
				mapDataFieldItem.put(dto.getId(), dto);
			}
		}
		
		//1.通过staffId查找用户相关角色
		List<Long> listStaffRole = new ArrayList<Long>();
		//1.1找到用户角色关系
		AuthStaff2Role staffRoleRel = new AuthStaff2Role();
		staffRoleRel.setStaffId(staffId);
		List<AuthStaff2Role> listStaffRoleRel = authRelManageSV.listStaffRoleRel(staffRoleRel);
		if(!CollectionUtils.isEmpty(listStaffRoleRel)){//角色关系为空则返回
			for (AuthStaff2Role authStaff2Role : listStaffRoleRel) {
				if(!listStaffRole.contains(authStaff2Role.getRoleId())){
					listStaffRole.add(authStaff2Role.getRoleId());
				}
			}
		}
		
		//1.2找到用户下用户组的关联角色
		AuthStaffResDTO authStaffResDto = new AuthStaffResDTO();
		authStaffResDto.setId(staffId);
		List<AuthRoleResDTO> listRoleOfGroup = authManageSV.findAuthRoleList(authStaffResDto);
		if (!CollectionUtils.isEmpty(listRoleOfGroup)) {
			for (AuthRoleResDTO authRoleResDTO : listRoleOfGroup) {
				if(!listStaffRole.contains(authRoleResDTO.getId())){
					listStaffRole.add(authRoleResDTO.getId());
				}
			}
		}
		//如果既没有角色，也没用用户组，直接返回
		if (listStaffRole.size() == 0) {
			return listRes;
		}
		
		//2.遍历用户关联各角色下的功能规则集合
		for (Long staffRoleId : listStaffRole) {//数据功能下的明细
			//list FilterRule
			List<FilterRule> listOfFunc = new ArrayList<FilterRule>();
			//找到角色权限关系
			AuthRole2Privilege authRole2Privilege = new AuthRole2Privilege();
			authRole2Privilege.setRoleId(staffRoleId);
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
			//list filterRule
			List<List<FilterRule>> listFilterRuleInner = new ArrayList<List<FilterRule>>();
			
			//遍历规则对象
			DataAuthCriteria dataAuthCriteria = new DataAuthCriteria();
			dataAuthCriteria.createCriteria().andEnabledEqualTo(StaffConstants.PublicParam.STATUS_ACTIVE)//仅“启用”状态
				.andIdIn(listRuleId).andAuthSrcEqualTo(StaffConstants.DataAuth.AUTH_SRC_COLUMN).andFuncIdEqualTo(dataFuncResDto.getId());
			List<DataAuth> listDataAuth = dataAuthMapper.selectByExample(dataAuthCriteria);
			for (DataAuth dataAuth : listDataAuth) {//规则下的明细
				//list FilterRule
				List<FilterRule> list = new ArrayList<FilterRule>();
				//规则明细
				DataFieldRuleReqDTO fieldRuleReqDto = new DataFieldRuleReqDTO();
				fieldRuleReqDto.setAuthId(dataAuth.getId());
				fieldRuleReqDto.setPageNo(0);//查全集
				PageResponseDTO<DataFieldRuleResDTO> pageFieldRule = dataAuthManageSV.listDataFieldRule(fieldRuleReqDto);
				if(pageFieldRule.getCount()>0){
					for (DataFieldRuleResDTO fieldRule : pageFieldRule.getResult()) {
						DataFieldItemResDTO itemDTO = mapDataFieldItem.get(fieldRule.getItemId());
						if(itemDTO==null) continue;//无则跳过
						
						String name = itemDTO.getAttrName();
						String clazz = itemDTO.getValueType();
						String formatter = fieldRule.getValueFormate();
						String value = fieldRule.getInputValue();
						
						FilterRule fr = new FilterRule(name, clazz, formatter, value);
						
						//list.add(fr);
						setRes.add(fr);
					}
				}
//				listFilterRuleInner.add(list);
				//listRes.addAll(list);
				
			}
//			listOfFunc = super.addRuleObjects(listRuleObjectInner);
//			listListRuleObject.add(listOfFunc);
		}
		
//		listRes = super.addRuleObjects(listListRuleObject);
		listRes.addAll(setRes);
		return listRes;
		
	}

}

