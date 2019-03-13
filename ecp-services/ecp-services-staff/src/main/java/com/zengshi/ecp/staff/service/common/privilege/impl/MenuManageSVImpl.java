package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.AuthMenuMapper;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2MenuMapper;
import com.zengshi.ecp.staff.dao.model.AuthMenu;
import com.zengshi.ecp.staff.dao.model.AuthMenuCriteria;
import com.zengshi.ecp.staff.dao.model.AuthMenuCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Menu;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2MenuCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthManageReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.staff.service.cache.interfaces.IMenuCacheSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IMenuManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IPrivlgManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 菜单管理实现类<br>
 * Date:2015年8月29日上午10:10:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class MenuManageSVImpl extends GeneralSQLSVImpl implements IMenuManageSV {
    
    private static final String MODULE = MenuManageSVImpl.class.getName();
    
    @Resource
    private AuthMenuMapper authMenuMapper; //菜单
    
    @Resource
    private AuthPrivilege2MenuMapper authPrivlg2MenuMapper; //权限菜单关系
    
    @Resource
    private IPrivlgManageSV privlgManageSV; //“权限”管理
    
    @Resource
    private IMenuCacheSV menuCacheSV; //菜单缓存
    
    @Resource(name="staffTools")
    private StaffTools staffTools;
    
    @Resource(name = "seq_auth_menu_id")
    private Sequence seqAuthMenu; //菜单表sequence

    @Override
    public long saveAuthMenu(AuthMenu am) throws BusinessException {
        if(am==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //1.增加菜单
        am.setId(seqAuthMenu.nextValue());
        am.setMenuCode(am.getId().toString());//TODO 功能编码
        if(StringUtil.isBlank(am.getStatus())){//缺省“有效”
            am.setStatus(StaffConstants.Menu.STATUS_ACTIVE);
        }
        /*记录排序数*/
        AuthMenuReqDTO reqDto = new AuthMenuReqDTO();
        reqDto.setSysCode(am.getSysCode());
        reqDto.setParentMenuId(am.getParentMenuId());
        Long countMenu = countAuthMenu(reqDto);
        countMenu += 1L;//已有总数加1即为新记录排序数
        am.setSortOrder(countMenu.shortValue());
        try {
            authMenuMapper.insertSelective(am);
        } catch (Exception e) {
            LogUtil.error(MODULE, "新增菜单失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        //2.增加权限
        AuthPrivilege ap = new AuthPrivilege();
        ap.setRoleAdmin(am.getMenuTitle()+StaffConstants.Privilege.NAME_SUFFIX);//后缀“权限”
        ap.setPrivilegeType(StaffConstants.Privilege.TYPE_MENU);
        ap.setSysCode(am.getSysCode());
        long apId = privlgManageSV.saveAuthPrivilege(ap);
        //3.增加菜单权限关系
        AuthPrivilege2Menu ap2m = new AuthPrivilege2Menu();
        ap2m.setMenuId(am.getId());
        ap2m.setPrivilegeId(apId);
        ap2m.setSysCode(am.getSysCode());
        savePrivilegeMenuRel(ap2m);
        
        //4.更新缓存
        AuthMenuReqDTO reqMenu = new AuthMenuReqDTO();
        reqMenu.setId(am.getId());
        AuthMenuResDTO cache = this.findAuthMenuById(reqMenu);
        menuCacheSV.addRecord(cache);
        
        return am.getId();
    }

    @Override
    public List<AuthMenuResDTO> listAuthMenuFromDB(List<String> sysCodes) throws BusinessException {
    	List<AuthMenuResDTO> res = new ArrayList<AuthMenuResDTO>();
        List<AuthMenu> selectMenu = new ArrayList<AuthMenu>();
        AuthMenuCriteria amCriteria = new AuthMenuCriteria();
        amCriteria.setOrderByClause("SORT_ORDER asc");
        Criteria sql = amCriteria.createCriteria();
        sql.andStatusEqualTo(StaffConstants.Menu.STATUS_ACTIVE);
        if(CollectionUtils.isNotEmpty(sysCodes)){
            sql.andSysCodeIn(sysCodes);
        }
        try {
            selectMenu = authMenuMapper.selectByExample(amCriteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        
        //1.得到菜单集合
        List<AuthMenu> listAuthMenu = selectMenu;
        //2.得到权限集合
        List<AuthPrivilege> listAuthPrivlg = privlgManageSV.listAuthPrivilege(sysCodes, null);
        //3.权限菜单关系集合
        List<AuthPrivilege2Menu>  listPrivlgMenuRel = this.listPrivilegeMenuRel(sysCodes);
        Map<String,AuthPrivilege> mapAuthPrivlg = new HashMap<String, AuthPrivilege>();
        Map<String,String> mapAuthPrivlgl2Menu = new HashMap<String,String>();
        
        //空值判断
        if(CollectionUtils.isEmpty(listAuthMenu)){
            return res;
        }
        if(CollectionUtils.isEmpty(listAuthPrivlg)){
            return res;
        }
        if(CollectionUtils.isEmpty(listPrivlgMenuRel)){
            return res;
        }
        
        for (AuthPrivilege authPrivlg : listAuthPrivlg) {
            mapAuthPrivlg.put(authPrivlg.getId().toString(), authPrivlg);
        }
        
        for (AuthPrivilege2Menu authPrivilege2Menu : listPrivlgMenuRel) {
            mapAuthPrivlgl2Menu.put(authPrivilege2Menu.getMenuId().toString(),
                    authPrivilege2Menu.getPrivilegeId().toString());
        }
        
        for (AuthMenu authMenu : listAuthMenu) {
            AuthMenuResDTO amResDto = new AuthMenuResDTO();
            ObjectCopyUtil.copyObjValue(authMenu, amResDto, null, true);
            String authMenuId = mapAuthPrivlgl2Menu.get(authMenu.getId().toString());
            
            if(StringUtil.isEmpty(authMenuId)){
                continue;
            }
            AuthPrivilege authPrivilege = mapAuthPrivlg.get(authMenuId);
            
            //增加空值校验；
            if(authPrivilege == null){
                continue;
            }

            String roleAdmin = authPrivilege.getRoleAdmin();
            Long privlgId = authPrivilege.getId();
            String privlgType = authPrivilege.getPrivilegeType();
            String privlgSysCode = authPrivilege.getSysCode();
            String privlgStatus = authPrivilege.getStatus();
            amResDto.setRoleAdmin(roleAdmin);
            amResDto.setPrivilegeId(privlgId);
            amResDto.setPrivilegeType(privlgType);
            amResDto.setPrivilegeSysCode(privlgSysCode);
            amResDto.setPrivilegeStatus(privlgStatus);
            res.add(amResDto);
        }
        //写缓存
        //当且只取“一个”子系统的数据集时
        if(CollectionUtils.isNotEmpty(sysCodes)&&sysCodes.size()==1){
        	CacheUtil.addItem(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+sysCodes.get(0),res);
        }
        return res;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<AuthMenuResDTO> listAuthMenu(AuthManageReqDTO reqDto) throws BusinessException {
    	List<String> sysCodes = reqDto.getSysCodes();
    	List<AuthMenuResDTO> res = new ArrayList<AuthMenuResDTO>();
    	if(CollectionUtils.isEmpty(sysCodes)){
    		return res;
    	}
    	//先查缓存，查无则查库
    	for (String sc : sysCodes) {
	        List<AuthMenuResDTO> listToRes = new ArrayList<AuthMenuResDTO>();
	        listToRes = (List<AuthMenuResDTO>) CacheUtil.getItem(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY+"_"+sc);
	        if(CollectionUtils.isEmpty(listToRes)){
	        	//空，则查库
				List<String> reqList = new ArrayList<String>();
				reqList.add(sc);
				listToRes = this.listAuthMenuFromDB(reqList);
	        }
		    res.addAll(listToRes);
		}
        return res;
    }

    @Override
    public List<AuthPrivilege2Menu> listPrivilegeMenuRel(List<String> sysCodes)
            throws BusinessException {
        List<AuthPrivilege2Menu> res = new ArrayList<AuthPrivilege2Menu>();
        AuthPrivilege2MenuCriteria ap2mCriteria = new AuthPrivilege2MenuCriteria();
        AuthPrivilege2MenuCriteria.Criteria sql = ap2mCriteria.createCriteria();
        if(CollectionUtils.isNotEmpty(sysCodes)){
            sql.andSysCodeIn(sysCodes);
        }
        try {
            res = authPrivlg2MenuMapper.selectByExample(ap2mCriteria);
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return res;
    }
    @Override
    public void savePrivilegeMenuRel(AuthPrivilege2Menu ap2m) throws BusinessException {
        try {
            authPrivlg2MenuMapper.insertSelective(ap2m);
        } catch (Exception e) {
            LogUtil.error(MODULE, "保存失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
    }

    @Override
    public PageResponseDTO<AuthMenuResDTO> listAuthMenu(AuthMenuReqDTO reqDto)
            throws BusinessException {
        PageResponseDTO<AuthMenuResDTO> res = new PageResponseDTO<AuthMenuResDTO>();
        
        AuthMenuCriteria amCriteria = new AuthMenuCriteria();
        AuthMenuCriteria.Criteria sql = amCriteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getMenuTitle())){
        	if (StringUtil.isNotBlank(reqDto.getMenuTitleFull())) {
        		sql.andMenuTitleEqualTo(reqDto.getMenuTitle());
        	} else {
        		sql.andMenuTitleLike("%"+reqDto.getMenuTitle()+"%");
        	}
        }
        if(StringUtil.isNotBlank(reqDto.getStatus())){
            sql.andStatusEqualTo(reqDto.getStatus());
        }else{//缺省查询“有效”
            sql.andStatusEqualTo(StaffConstants.Menu.STATUS_ACTIVE);
        }
        if(StringUtil.isNotBlank(reqDto.getMenuType())){
            sql.andMenuTypeEqualTo(reqDto.getMenuType());
        }
        if(StringUtil.isNotBlank(reqDto.getSysCode())){
            sql.andSysCodeEqualTo(reqDto.getSysCode());
        }
        if(reqDto.getParentMenuId()!=null){
            sql.andParentMenuIdEqualTo(reqDto.getParentMenuId());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        
        amCriteria.setLimitClauseCount(reqDto.getPageSize());
        amCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        amCriteria.setOrderByClause(" sort_order asc");
        
        res = super.queryByPagination(reqDto, amCriteria, false, new PaginationCallback<AuthMenu, AuthMenuResDTO>() {

            @Override
            public List<AuthMenu> queryDB(BaseCriteria criteria) {
                return authMenuMapper.selectByExample((AuthMenuCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return authMenuMapper.countByExample((AuthMenuCriteria) criteria);
            }
            
            @Override
            public List<Comparator<AuthMenu>> defineComparators(){
                List<Comparator<AuthMenu>> ls = new ArrayList<Comparator<AuthMenu>>();
                ls.add(new Comparator<AuthMenu>() {
                    public int compare(AuthMenu o1, AuthMenu o2) {
                        return o1.getSortOrder()<o2.getSortOrder()?1:-1;
                    }
                });
                return ls;
            }

            @Override
            public AuthMenuResDTO warpReturnObject(AuthMenu t) {
                AuthMenuResDTO dto = new AuthMenuResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, false);
                return dto;
            }
        });
        
        return res;
    }

    @Override
    public AuthMenuResDTO findAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        AuthMenuResDTO res = new AuthMenuResDTO();
        AuthMenu am = new AuthMenu();
        try {
            am = authMenuMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[] {"菜单"});
        }
        ObjectCopyUtil.copyObjValue(am, res, null, false);
        
        if(am == null){
            return null;
        }else{//增加权限信息
            AuthPrivilege2MenuCriteria relCriteria = new AuthPrivilege2MenuCriteria();
            relCriteria.createCriteria().andSysCodeEqualTo(res.getSysCode()).andMenuIdEqualTo(res.getId());
            List<AuthPrivilege2Menu> listRel = authPrivlg2MenuMapper.selectByExample(relCriteria);
            if(CollectionUtils.isNotEmpty(listRel)){
                AuthPrivilege2Menu onlyRel = listRel.get(0);
                AuthPrivilege findpriv = privlgManageSV.findAuthPrivilegeById(onlyRel.getPrivilegeId());
                res.setPrivilegeId(findpriv.getId());
                res.setPrivilegeStatus(findpriv.getStatus());
                res.setPrivilegeSysCode(findpriv.getSysCode());
                res.setPrivilegeType(findpriv.getPrivilegeType());
                res.setRoleAdmin(findpriv.getRoleAdmin());
            }
        }
        return res;
    }
    

    @Override
    public void updateAuthMenuById(AuthMenu update) throws BusinessException {
        if(update==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        update.setUpdateTime(DateUtil.getSysDate());
        //1.修改菜单
        try {
            authMenuMapper.updateByPrimaryKeySelective(update);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库修改异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[] {"菜单"});
        }
        //2.同时修改相关权限状态
        AuthPrivilege2MenuCriteria ap2mCriteria = new AuthPrivilege2MenuCriteria();
        ap2mCriteria.createCriteria().andMenuIdEqualTo(update.getId());
        List<AuthPrivilege2Menu> listRel = authPrivlg2MenuMapper.selectByExample(ap2mCriteria);
        if(CollectionUtils.isNotEmpty(listRel)){
            AuthPrivilege2Menu rel = listRel.get(0);
            Long privilegeId = rel.getPrivilegeId();
            AuthPrivilege findPriv = privlgManageSV.findAuthPrivilegeById(privilegeId);
            if(StringUtil.isNotBlank(update.getMenuTitle())) findPriv.setRoleAdmin(update.getMenuTitle()+StaffConstants.Privilege.NAME_SUFFIX);
            if(StringUtil.isNotBlank(update.getSysCode())) findPriv.setSysCode(update.getSysCode());
            if(StringUtil.isNotBlank(update.getStatus())) findPriv.setStatus(update.getStatus());
            //修改权限
            privlgManageSV.updateAuthPrivilegeById(findPriv);
            if(StringUtil.isNotBlank(update.getSysCode())) rel.setSysCode(update.getSysCode());
            //修改权限关系
            authPrivlg2MenuMapper.updateByPrimaryKeySelective(rel);
        }
        
        //3.更新缓存
        AuthMenuReqDTO reqMenu = new AuthMenuReqDTO();
        reqMenu.setId(update.getId());
        AuthMenuResDTO cache = this.findAuthMenuById(reqMenu);
        menuCacheSV.updateRecord(cache);
        StaffUtil.clearMenuCache();//初始化一个缓存，以便更新排序
    }

    @Override
    public void deleteAuthMenuById(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //还有子节点则不可删除
        AuthMenuReqDTO reqListMenu = new AuthMenuReqDTO();
        reqListMenu.setParentMenuId(reqDto.getId());
        reqListMenu.setStatus(StaffConstants.Menu.STATUS_ACTIVE);
        reqListMenu.setPageNo(0);//不分页
        PageResponseDTO<AuthMenuResDTO> pageListMenu = listAuthMenu(reqListMenu);
        if(CollectionUtils.isNotEmpty(pageListMenu.getResult())){
            throw new BusinessException(StaffConstants.STAFF_EXECUTE_ERROR_C, new String[]{"删除前，先清空子节点"});
        }
        AuthMenu delete = new AuthMenu();
        delete.setId(reqDto.getId());
        delete.setStatus(StaffConstants.Menu.STATUS_INVALID);//设置为失效
        delete.setUpdateStaff(reqDto.getStaff().getId());
        delete.setSysCode(reqDto.getSysCode());
        try {
            updateAuthMenuById(delete);
        } catch (Exception e) {
            LogUtil.error(MODULE, "逻辑删除异常", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[] {"菜单"});
        }
        
        //同步缓存
        AuthMenuResDTO cache = this.findAuthMenuById(reqDto);
        menuCacheSV.removeRecord(cache);
    }

    @Override
    public List<AuthMenuResDTO> generateEntireTree(AuthMenuReqDTO reqDto) throws BusinessException {
        if(reqDto==null||StringUtil.isBlank(reqDto.getSysCode())){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"全量菜单树入参异常"});
        }
        List<AuthMenuResDTO> res = new ArrayList<AuthMenuResDTO>();
        AuthMenuCriteria amCriteria = new AuthMenuCriteria();
        AuthMenuCriteria.Criteria sql = amCriteria.createCriteria();
        sql.andStatusEqualTo(StaffConstants.Menu.STATUS_ACTIVE);
        sql.andSysCodeEqualTo(reqDto.getSysCode());
//        sql.andMenuTypeEqualTo(reqDto.getMenuType());
        
        List<AuthMenu> listMenu = new ArrayList<AuthMenu>();
        try {
            listMenu = authMenuMapper.selectByExample(amCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据库异常,全量菜单树查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"全量菜单树"});
        }
        //1.准备数据缓存对象，获取子系统权限集合
        Map<Long, AuthPrivilegeResDTO> mapAuthprivlg = new HashMap<Long, AuthPrivilegeResDTO>(); //缓存“权限”
        Map<Long, Long> mapPrivlgMenuRel = new HashMap<Long, Long>();//缓存“菜单权限关系”
        AuthPrivilegeReqDTO privlgDto = new AuthPrivilegeReqDTO();
        privlgDto.setSysCode(reqDto.getSysCode());
        privlgDto.setPageNo(0);//不分页，查全集
        PageResponseDTO<AuthPrivilegeResDTO> pageListPrivlg = privlgManageSV.listAuthPrivilege(privlgDto);
        List<AuthPrivilegeResDTO> listPrivlg = pageListPrivlg.getResult();
        if(CollectionUtils.isEmpty(listPrivlg)){
            return res;
        }
        for (AuthPrivilegeResDTO authPrivilegeResDTO : listPrivlg) {
            mapAuthprivlg.put(authPrivilegeResDTO.getId(), authPrivilegeResDTO);
        }
        //2.准备菜单权限关系
        List<String> listSysCode = new ArrayList<String>();
        listSysCode.add(reqDto.getSysCode());
        List<AuthPrivilege2Menu> listpmRel = listPrivilegeMenuRel(listSysCode);
        if(CollectionUtils.isEmpty(listpmRel)){
            return res;
        }
        for (AuthPrivilege2Menu pmRel : listpmRel) {
            mapPrivlgMenuRel.put(pmRel.getMenuId(),pmRel.getPrivilegeId());
        }
        //3.封装返回树
        if(CollectionUtils.isNotEmpty(listMenu)){
            for (AuthMenu authMenu : listMenu) {
                AuthMenuResDTO resDto = new AuthMenuResDTO();
                ObjectCopyUtil.copyObjValue(authMenu, resDto, null, false);
                //a.关联“权限”
                AuthPrivilegeResDTO privlg = new AuthPrivilegeResDTO();
                privlg = mapAuthprivlg.get(mapPrivlgMenuRel.get(resDto.getId()));
                if(privlg==null){
                    continue;
                }
                resDto.setPrivilegeId(privlg.getId());
                resDto.setRoleAdmin(privlg.getRoleAdmin());
                //b.验证是否为父节点  , 是否需要在库表设计上增加“是否为父节点属性”以应对遍历带来的效率问题
                AuthMenuReqDTO countIsParent = new AuthMenuReqDTO();
                countIsParent.setParentMenuId(resDto.getId());
                countIsParent.setSysCode(reqDto.getSysCode());
                resDto.setIsParent(countAuthMenu(countIsParent)>0);
                
                res.add(resDto);//add
            }
        }
        return res;
    }
    
    /**
     * 
     * countAuthMenu:(统计指定系统中父菜单下的子节点总数). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private Long countAuthMenu(AuthMenuReqDTO reqDto) throws BusinessException{
        //过滤？ 归属子系统、菜单类型、有效标识
        AuthMenuCriteria amCriteria = new AuthMenuCriteria();
        amCriteria.createCriteria().andStatusEqualTo(StaffConstants.Menu.STATUS_ACTIVE)
                .andParentMenuIdEqualTo(reqDto.getParentMenuId())
                .andSysCodeEqualTo(reqDto.getSysCode());
        Long count = 0L;
        try {
            count = authMenuMapper.countByExample(amCriteria);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "统计菜单总数", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return count;
    }

    @Override
    public List<AuthMenuResDTO> listAuthMenuByPrivList(AuthManageReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getPrivList() }, new String[] {"菜单权限集"});
        
        List<Long> privList = reqDto.getPrivList();
        List<AuthMenuResDTO> res = new ArrayList<AuthMenuResDTO>();
        List<AuthMenuResDTO> ress = new ArrayList<AuthMenuResDTO>();
        List<BaseParamDTO> listSysSubSystemParam = BaseParamUtil.fetchParamList(StaffConstants.PublicParam.SYS_SUB_SYSTEM_PARAMKEY);
        if(CollectionUtils.isNotEmpty(listSysSubSystemParam))
        {
            for (BaseParamDTO baseParamDTO : listSysSubSystemParam) {
            	List<String> reqMenu = new ArrayList<String>();
            	reqMenu.add(baseParamDTO.getSpCode());
            	AuthManageReqDTO authManageReqDto = new AuthManageReqDTO();
            	authManageReqDto.setSysCodes(reqMenu);
            	List<AuthMenuResDTO> item = this.listAuthMenu(authManageReqDto);
                if(item!=null&&CollectionUtils.isNotEmpty(item)){
                    for (AuthMenuResDTO authMenuResDTO : item) {
                        if(privList.contains(authMenuResDTO.getPrivilegeId())){
                                res.add(authMenuResDTO);
                        }
                    }
                 /*   for (AuthMenuResDTO authMenuResDTO : res) {
                        List<AuthMenuResDTO> sonlist = new ArrayList<AuthMenuResDTO>();
                        for (AuthMenuResDTO authMenuResDTOs : item) {
                            if(authMenuResDTO.getId().longValue()==authMenuResDTOs.getParentMenuId().longValue()){
                                sonlist.add(authMenuResDTOs);
                            }
                        }
                        authMenuResDTO.setSonList(sonlist);
                        ress.add(authMenuResDTO);
                    }*/
                }
            }
        }
        ress =  getStaffMenu(res);
     
        return ress;
    }
    
    public List<AuthMenuResDTO> getStaffMenu(List<AuthMenuResDTO> res){
        List<AuthMenuResDTO> Pmenu = new ArrayList<AuthMenuResDTO>();
        List<AuthMenuResDTO> ress = new ArrayList<AuthMenuResDTO>();
        for (AuthMenuResDTO authMenuResDTO : res) {
            if(StaffConstants.Menu.MENU_TYPE_DIRECTORY.equals(authMenuResDTO.getMenuType())){
                Pmenu.add(authMenuResDTO);
            }
        }
        for (AuthMenuResDTO authMenuResDTO : Pmenu) {
            List<com.zengshi.ecp.server.front.security.AuthMenuResDTO> sonlist = new ArrayList<com.zengshi.ecp.server.front.security.AuthMenuResDTO>();
            for (AuthMenuResDTO authMenuResDTOs : res) {
                if(authMenuResDTO.getId().longValue()==authMenuResDTOs.getParentMenuId().longValue()){
                    sonlist.add(authMenuResDTOs);
                }
            }
            authMenuResDTO.setSonList(sonlist);
            ress.add(authMenuResDTO);
        }
        
        
        return ress;
        
    }
    
    
    
    
    
}

