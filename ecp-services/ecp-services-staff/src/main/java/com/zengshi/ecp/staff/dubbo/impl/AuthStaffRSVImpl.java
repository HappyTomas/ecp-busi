
package com.zengshi.ecp.staff.dubbo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffMapper;
import com.zengshi.ecp.staff.dao.model.AuthGroup2Role;
import com.zengshi.ecp.staff.dao.model.AuthRole;
import com.zengshi.ecp.staff.dao.model.AuthRole2Privilege;
import com.zengshi.ecp.staff.dao.model.AuthStaff;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Group;
import com.zengshi.ecp.staff.dao.model.AuthStaff2Role;
import com.zengshi.ecp.staff.dao.model.AuthStaffAdmin;
import com.zengshi.ecp.staff.dao.model.AuthStaffGroup;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.CustInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.ICustManageRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.auth.interfaces.IAuthManageSV;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.login.interfaces.IAuthLoginSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAdminManageSV;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IAuthStaffManageSV;
import com.zengshi.ecp.staff.service.busi.register.interfaces.IRegisterSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IPrivlgManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


public class AuthStaffRSVImpl implements IAuthStaffRSV{
    
    
    @Resource
    private IAuthManageSV authManageSV;
    @Resource
    private IPrivlgManageSV privlgManageSV;
    
    @Resource
    private IAdminManageSV adminManageSV;
    
    @Resource
    private ICustInfoSV custInfoSV; //会员信息服务
    
    @Resource
    private IAuthLoginSV authLoginSV;
    
    @Resource
    private IAuthStaffManageSV authStaffManageSV;
    
    @Resource
    private IRegisterSV registerSV;
    
    @Resource
    private AuthStaffMapper authStaffMapper;
    
    @Resource
    private ICustManageRSV custManageRSV;
    
    private static final String MODULE = AuthStaffRSVImpl.class.getName();
    
    /**
     * 邮箱验证正则表达式
     */
    String _eMail = "^\\w+[-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$ ";
    /**
     * 验证手机号正则表达式
     */
    String _PhoneNumber = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[0-9])|(18[0,5-9]))\\d{8}$";
    

    @Override
    public AuthPrivilegeResDTO findPrivilByStaffCode(String pStaffCode,String pStaffClass) throws BusinessException {
        
        AuthPrivilegeResDTO resultDTO = new AuthPrivilegeResDTO();
        Long sStaffID = null;
        String custLevelCode = ""; //会员等级
        if(StaffConstants.authStaff.STAFF_CLASS_N.equals(pStaffClass)){
        	//1.通过登陆名找出用户ID
           // sStaffID = authLoginSV.findIDByStaffCode(pStaffCode);
            //1.首先判断是用哪种方式进行登陆：1.用户编码，2.用户手机号，3.用户邮箱
            
            if(StaffTools.checkRegx(pStaffCode, _eMail))
            {
                //如果是邮箱登陆，根据邮箱找到用户ID
                sStaffID = authLoginSV.findIDByeEmail(pStaffCode);
            }
            //2.由于用户编码与手机号码存在相同的可能，所以先匹配手机号的正则表达式
            if(StaffTools.checkRegx(pStaffCode, _PhoneNumber))
            {
                //匹配手机号成功,根据手机号找到用户ID
                sStaffID = authLoginSV.findIDByPhoneNumber(pStaffCode);
            }
            if(sStaffID == null)
            {
                //根据用户账号找到用户ID
                sStaffID = authLoginSV.findIDByStaffCode(pStaffCode);
            }
            if(sStaffID==null){
                return null;
            }
            //2.查找会员等级
            CustInfo ci = custInfoSV.findCustInfoById(sStaffID);
            if(ci!=null){
            	if(StringUtil.isNotBlank(ci.getCustLevelCode())){
            		custLevelCode = ci.getCustLevelCode();
            	}
            }
        }
        if(StaffConstants.authStaff.STAFF_CLASS_M.equals(pStaffClass)){
	        AuthStaffAdmin authstaff = adminManageSV.findAuthStaffAdminByCode(pStaffCode);
	        
	        if(authstaff == null)
	        {
	            LogUtil.info(MODULE, "===========通过StaffCode:["+pStaffCode+"]没有找到对应的用户ID=============");
	            return null;
	        }
	         sStaffID = authstaff.getId();
        }
        if(sStaffID == null)
        {
            LogUtil.info(MODULE, "===========通过StaffCode:["+pStaffCode+"]没有找到对应的用户ID=============");
            return null;
        }
        AuthStaff sAuthStaff =  authManageSV.findStaff(sStaffID);
        
        //构造结果集
        if(sAuthStaff != null)
        {
            resultDTO.setStaffId(sAuthStaff.getId());//用户ID
            resultDTO.setStaffCode(pStaffCode);//登陆名/用户编码
            resultDTO.setStaffClass(sAuthStaff.getStaffClass());//用户类型
            resultDTO.setPassword(sAuthStaff.getStaffPasswd());//密码
            resultDTO.setStaffStatus(sAuthStaff.getStaffFlag());//用户状态
            resultDTO.setPrivList(new ArrayList<Long>());//权限编码集合
            resultDTO.setCustLevelCode(custLevelCode);
            //如果状态是4，则需要看是否超过时间，已自动解冻
            if (StaffConstants.authStaff.STAFF_FLAG_FREEZE.equals(sAuthStaff.getStaffFlag())) {
            	Long freeze_time = DateUtil.getSysDate().getTime()- sAuthStaff.getLastLoginFailureTime().getTime();
                String LoginFreezeTime = BaseParamUtil.fetchParamValue("LOGIN_FAIL_FREEZE_TIME", "FREEZE_TIME");
            	if (freeze_time > Long.parseLong(LoginFreezeTime)*60*60*1000) {
                	resultDTO.setStaffStatus(StaffConstants.authStaff.STAFF_FLAG);//用户状态
                }
            }
        }
        else {
            return null;
        }
        //2.获取该用户对应的用户组有效的角色编码列表
        List<Long> sRoleIdList1 = findRoleByStaffGroup(sStaffID);
        //3.获取该用户默认的角色编码列表
        List<Long> sRoleIdList2 = findRoleByStaffId(sStaffID);
        //合并两个链表
        List<Long> sRoleIdList = new ArrayList<Long> ();
        if(!CollectionUtils.isEmpty(sRoleIdList1))
        {
            for(Long sId : sRoleIdList1)
            {
                sRoleIdList.add(sId);
            }
        }
        if(!CollectionUtils.isEmpty(sRoleIdList2))
        {
            for(Long sId : sRoleIdList2)
            {
                sRoleIdList.add(sId);
            }
        }
        if(CollectionUtils.isEmpty(sRoleIdList))
        {
            LogUtil.info(MODULE, "===========没有找到对应的该用户权限集合=============");
            return resultDTO;
        } 
        //3.通过sRoleIdList列表，获取角色详细信息
        ArrayList<AuthRole> pRoleList = (ArrayList<AuthRole>) privlgManageSV.listRole(sRoleIdList);
        //角色ID列表查完晴空，作为下一次查询条件使用
        sRoleIdList.clear();
        if(CollectionUtils.isEmpty(pRoleList))
        {
            return resultDTO;
        } 
        //4.判断角色状态是否有效，剔除无效角色状态 
        for(AuthRole role : pRoleList)
        {
            if(Integer.valueOf(role.getStatus()) == 1)
            {
                //如果角色状态有效，则填加入查询条件
                sRoleIdList.add(role.getId());
            }
        }
        //角色信息列表用完清空
        pRoleList.clear();
        
        //4.获取权限编码列表
        List<AuthRole2Privilege> sRole2PrivilList= privlgManageSV.listPrivil(sRoleIdList);
        if(CollectionUtils.isEmpty(sRole2PrivilList))
        {
            return resultDTO;
        } 
        //5.设置结果集，并返回
      //  List<Long> privList = resultDTO.getPrivList();
        ArrayList<Long> privList = new ArrayList<Long>();
        for(AuthRole2Privilege role2Priv : sRole2PrivilList)
        {
            privList.add(role2Priv.getPrivilegeId());
        }
        resultDTO.setPrivList(privList);
        return resultDTO;
    }

    private List<Long> findRoleByStaffGroup(Long pStaffId)
    {
        ArrayList<Long> sRoleIdList = new ArrayList<Long>();
        //2.1通过用户ID找出用户与用户组关系
        List<AuthStaff2Group> sStaff2Groups = authManageSV.listGroup(pStaffId);
        //2.1.1判断用户组是否有效
        ArrayList<Long> sGroupIDList = new ArrayList<Long>();
        for(AuthStaff2Group sGroup : sStaff2Groups)
        {
            sGroupIDList.add(sGroup.getGroupId());
        }
        if(!CollectionUtils.isEmpty(sGroupIDList))
        {
            //2.1.2找出有效的用户组ID
            List<AuthStaffGroup> sStaffGroups = privlgManageSV.listGroup(sGroupIDList);
            sGroupIDList.clear();
            for(AuthStaffGroup sGroup : sStaffGroups)
            {
                sGroupIDList.add(sGroup.getId());
            }
        }
        if(!CollectionUtils.isEmpty(sGroupIDList))
        {
            List<AuthGroup2Role> sGroup2Roles = privlgManageSV.listGroup2Role(sGroupIDList);
            for(AuthGroup2Role sRole : sGroup2Roles)
            {
                sRoleIdList.add(sRole.getRoleId());
            }
        }
        return sRoleIdList;
        
    }
    private List<Long> findRoleByStaffId(Long pStaffId)
    {
        //2.2通过用户ID找出对应的角色列表
        List<AuthStaff2Role> sStaffRoleList = authManageSV.listRole(pStaffId); 
        ArrayList<Long> sRoleIdList = new ArrayList<Long>();
        for(AuthStaff2Role role: sStaffRoleList)
        {
            sRoleIdList.add(role.getRoleId());
        }
        //用户与角色关系列表用完清空
        sStaffRoleList.clear();
        //若没有对应的角色信息，则直接返回
        if(CollectionUtils.isEmpty(sRoleIdList))
        {
            return null;
        }  
        
        return sRoleIdList;
        
    }

    @Override
    public PageResponseDTO<AuthStaffResDTO> listAuthStaff(AuthStaffReqDTO reqDto)
            throws BusinessException {
        return authStaffManageSV.listAuthStaff(reqDto);
    }

    @Override
    public AuthStaffResDTO findAuthStaffById(Long staffId) throws BusinessException {
        return authStaffManageSV.findAuthStaffById(staffId);
    }

    @Override
    public void updatePwdById(AuthStaffReqDTO req) throws BusinessException {
        authStaffManageSV.updatePwdById(req);
    }

    @Override
    public long saveRegister(AuthStaffReqDTO req) throws BusinessException {
        return registerSV.saveAuthStaff(req);
    }

	@Override
	public int updateStaffById(String staffCode, String loginFlag) throws BusinessException {
		//查询用户信息
		Long sStaffID = authLoginSV.findIDByStaffCode(staffCode);
		AuthStaffResDTO authStaff = this.findAuthStaffById(sStaffID);
		
		AuthStaffReqDTO info = new AuthStaffReqDTO();
		ObjectCopyUtil.copyObjValue(authStaff, info, null, false);
		//当天最大登录失败次数
        String loginFailTimes = BaseParamUtil.fetchParamValue("LOGIN_FAIL_MAX_TIMES", "MAX_TIMES");
        //当天登录失败超过次数限制时，是否临时冻结账户
        String onOff = BaseParamUtil.fetchParamValue("LOGIN_FAIL_ON_OFF", "ON_OFF");
        
        int loginTime = 0;//还有几次登录失败的机会
		/*1、登录成功*/
		if ("1".equals(loginFlag)) {
			 //当天登录失败次数置0
            info.setLoginFailureCntToday(new BigDecimal(0));
            //登录成功次数加1
            BigDecimal succCnt = info.getLoginSuccessCnt();
            if (succCnt == null) {
            	succCnt = new BigDecimal(0);
            }
            info.setLoginSuccessCnt(succCnt.add(new BigDecimal(1)));
            //这里是为了把状态为4时，超过了冻结时间，登录后，把状态重置为正常。
            info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG);
            //更新上次登录时间
            info.setLastLoginTime(DateUtil.getSysDate());
            //更新最后操作时间
            info.setUpdateTime(DateUtil.getSysDate());
            info.setUpdateStaff(info.getId());
            //另启一个线程，更新用户相关数据
            this.updateStaffInfo(info);
		/*2、登录失败*/
		} else {
			//能执行到这里，说明用户状态正常
			info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG); 
			//当天登录失败次数
	        BigDecimal loginFailCnt = info.getLoginFailureCntToday();
	        if (loginFailCnt == null) {
	        	loginFailCnt = new BigDecimal(0); 
	        }
	        
	        //次数加1
	        loginFailCnt = loginFailCnt.add(new BigDecimal(1));
	        loginTime = Integer.parseInt(loginFailTimes) - loginFailCnt.intValue();
	        info.setLoginFailureCntToday(loginFailCnt);
	        //如果当天登录失败次数大于最大失败限制次数，且开关开启，则把登录表的状态设为：4，临时锁定，次数清0
	        if (loginFailCnt.intValue() >= Integer.parseInt(loginFailTimes) && "ON".equals(onOff)) {
	           info.setStaffFlag(StaffConstants.authStaff.STAFF_FLAG_FREEZE); 
	           info.setLoginFailureCntToday(new BigDecimal(0));
	        } else if (!"ON".equals(onOff)) {
	        	loginTime = -1;//开关未开启，这里返回-1
	        }
	        
	        //更新最后登录失败时间
	        info.setLastLoginFailureTime(DateUtil.getSysDate());
	        info.setUpdateStaff(info.getId());
	        //另启一个线程，更新用户相关数据
	        //更新用户信息表登录失败次数及最后登录失败时间
	        //更新最后操作时间
            info.setUpdateTime(DateUtil.getSysDate());
	        this.updateStaffInfo(info);
		}
        
		return loginTime;
	}
	
	/**
     * 
     * updateStaffInfo:(另启一个线程，更新用户登录相关信息). <br/> 
     * 
     * @author huangxl 
     * @param authStaff 
     * @since JDK 1.7
     */
    private void updateStaffInfo(final AuthStaffReqDTO authStaff) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            public void run() {
                try {
                	AuthStaffRSVImpl.this.updateAuthStaff(authStaff);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private int updateAuthStaff(AuthStaffReqDTO authStaff) throws BusinessException {
    	CustInfoReqDTO req = new CustInfoReqDTO();
    	req.setId(authStaff.getId());
    	req.setStatus(authStaff.getStaffFlag());
    	AuthStaff staff = new AuthStaff();
    	ObjectCopyUtil.copyObjValue(authStaff, staff, null, true);
    	authStaffMapper.updateByPrimaryKeySelective(staff);
        return custManageRSV.updateCustStatus(req);
    }

}
