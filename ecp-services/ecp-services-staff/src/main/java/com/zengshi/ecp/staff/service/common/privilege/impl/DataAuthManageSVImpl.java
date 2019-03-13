package com.zengshi.ecp.staff.service.common.privilege.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.common.AuthPrivilege2RuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataAuthMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataFieldItemMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataFieldRuleMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataFuncMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataRuleItemMapper;
import com.zengshi.ecp.staff.dao.mapper.common.DataRuleMapper;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2RuleCriteria;
import com.zengshi.ecp.staff.dao.model.DataAuth;
import com.zengshi.ecp.staff.dao.model.DataAuthCriteria;
import com.zengshi.ecp.staff.dao.model.DataFieldItem;
import com.zengshi.ecp.staff.dao.model.DataFieldItemCriteria;
import com.zengshi.ecp.staff.dao.model.DataFieldRule;
import com.zengshi.ecp.staff.dao.model.DataFieldRuleCriteria;
import com.zengshi.ecp.staff.dao.model.DataFunc;
import com.zengshi.ecp.staff.dao.model.DataFuncCriteria;
import com.zengshi.ecp.staff.dao.model.DataRule;
import com.zengshi.ecp.staff.dao.model.DataRuleCriteria;
import com.zengshi.ecp.staff.dao.model.DataRuleItem;
import com.zengshi.ecp.staff.dao.model.DataRuleItemCriteria;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.AuthPrivilegeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataAuthResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFieldRuleResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataFuncZTreeNodeDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleItemResDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.DataRuleResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IDataAuthManageSV;
import com.zengshi.ecp.staff.service.common.privilege.interfaces.IPrivlgManageSV;
import com.zengshi.ecp.staff.service.util.StaffTools;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 数据权限功能管理实现类<br>
 * Date:2015年10月9日上午11:19:17  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class DataAuthManageSVImpl extends GeneralSQLSVImpl implements IDataAuthManageSV {
    
    private static final String MODULE = DataAuthManageSVImpl.class.getName();
    
    @Resource
    private DataAuthMapper dataAuthMapper; //数据权限
    
    @Resource
    private DataRuleMapper dataRuleMapper; //数据规则配置
    
    @Resource
    private DataRuleItemMapper dataRuleItemMapper; //数据规则项目
    
    @Resource
    private DataFuncMapper dataFuncMapper; //数据功能
    
    @Resource
    private AuthPrivilege2RuleMapper authPrivilege2RuleMapper;  //权限规则关系
    
    @Resource
    private DataFieldRuleMapper dataFieldRuleMapper;//过滤规则明细
    
    @Resource
    private DataFieldItemMapper dataFieldItemMapper;//属性结果集
    
    @Resource
    private IPrivlgManageSV privlgManageSV; //“权限”管理
    
    @Resource(name="staffTools")
    private StaffTools staffTools;
    
    @Resource(name = "seq_data_rule_item_id")
    private PaasSequence seqDataRuleItem; //数据权限规则项目sequence
    
    @Resource(name = "seq_data_rule_id")
    private PaasSequence seqDataRule; //数据权限规则配置sequence
    
    @Resource(name = "seq_data_auth_id")
    private PaasSequence seqDataAuth; //数据权限规则权限sequence
    
    @Resource(name = "seq_data_func_id")
    private PaasSequence seqDataFunc; //数据功能sequence
    
    @Resource(name = "seq_data_func_code")
    private PaasSequence seqDataFuncCode; //数据功能sequence code
    
    @Resource(name = "seq_data_field_rule_id")
    private PaasSequence seqDataFieldRule; //数据过滤规则明细
    
    @Resource(name = "seq_data_field_item_id")
    private PaasSequence seqDataFieldItem; //结果集属性表
    
    
    @Override
    public long saveDataRuleItem(DataRuleItemReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getName() }, new String[] {"数据规则项目名称"});
        
        //名称不重复
        this.countDataRuleItemByName(reqDto, true);
        
        DataRuleItem dri = new DataRuleItem();
        ObjectCopyUtil.copyObjValue(reqDto, dri, null, false);
        dri.setId(seqDataRuleItem.nextValue());//设置id
        if(reqDto.getOrderBy()==null){
            //计算序数
            DataRuleItemReqDTO countOrder = new DataRuleItemReqDTO();
            countOrder.setFuncId(reqDto.getFuncId());
            countOrder.setPageNo(0);//查全集
            PageResponseDTO<DataRuleItemResDTO> listItem = listDataRuleItem(countOrder);
            BigDecimal orderBy = null;//功能点下序数最大值加1
            if(CollectionUtils.isNotEmpty(listItem.getResult())){
                orderBy = listItem.getResult().get(0).getOrderBy();
                dri.setOrderBy(new BigDecimal(orderBy.longValue()+1));
            }else{
                dri.setOrderBy(new BigDecimal(1));//初始值为"1"
            }
        }
        
        try {
            dataRuleItemMapper.insertSelective(dri);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则项目新增失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{"规则项目"});
        }
        
        return dri.getId();
    }
    
    /**
     * 
     * countDataRuleItemByName:(由规则项目名称统计记录数). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @param notEmptyWarning
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private Long countDataRuleItemByName(DataRuleItemReqDTO reqDto, Boolean notEmptyWarning) throws BusinessException {
    	if(notEmptyWarning==null){
    		notEmptyWarning = false;
    	}
    	DataRuleItemCriteria nameOnlyCriteria = new DataRuleItemCriteria();
    	DataRuleItemCriteria.Criteria sql = nameOnlyCriteria.createCriteria().andFuncIdEqualTo(reqDto.getFuncId()).andNameEqualTo(reqDto.getName());
    	if(reqDto.getId()!=null && notEmptyWarning){
    		sql.andIdNotEqualTo(reqDto.getId());
    	}
        Long nameOnlyCount = dataRuleItemMapper.countByExample(nameOnlyCriteria);
        if(nameOnlyCount>0&&notEmptyWarning){
        	//对象所属数据功能下已存在该名称
        	throw new BusinessException("staff.100056", new String[]{reqDto.getName()});
        }
        return nameOnlyCount;
    }
    
    /**
     * 
     * countDataFieldItemByName:(由名称统计记录数). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @param notEmptyWarning
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private Long countDataFieldItemByName(DataFieldItemReqDTO reqDto, Boolean notEmptyWarning) throws BusinessException {
    	if(notEmptyWarning==null){
    		notEmptyWarning = false;
    	}
    	DataFieldItemCriteria nameOnlyCriteria = new DataFieldItemCriteria();
    	//同一个数据功能下的统计
    	DataFieldItemCriteria.Criteria sql = nameOnlyCriteria.createCriteria().andFuncIdEqualTo(reqDto.getFuncId()).andNameEqualTo(reqDto.getName());
    	if(reqDto.getId()!=null && notEmptyWarning){
    		sql.andIdNotEqualTo(reqDto.getId());
    	}
        Long nameOnlyCount = dataFieldItemMapper.countByExample(nameOnlyCriteria);
        if(nameOnlyCount>0&&notEmptyWarning){
        	//对象所属数据功能下已存在该名称
        	throw new BusinessException("staff.100056", new String[]{reqDto.getName()});
        }
        return nameOnlyCount;
    }

    @Override
    public void updateDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getName() }, new String[] {"数据规则项目名称"});
        
        //名称不重复
        this.countDataRuleItemByName(reqDto, true);
        
        DataRuleItem dri = new DataRuleItem();
        ObjectCopyUtil.copyObjValue(reqDto, dri, null, false);
        try {
            dataRuleItemMapper.updateByPrimaryKeySelective(dri);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则项目更新失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR, new String[]{"规则项目"});
        }
    }

    @Override
    public void deleteDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        try {
            dataRuleItemMapper.deleteByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则项目删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{"规则项目"});
        }
        
    }

    @Override
    public DataRuleItemResDTO findDataRuleItemById(DataRuleItemReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null||reqDto.getId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        DataRuleItemResDTO res = new DataRuleItemResDTO();
        DataRuleItem find = new DataRuleItem();
        try {
            find = dataRuleItemMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则项目查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"规则项目"});
        }
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        if(find==null) return null;
        return res;
    }

    @Override
    public PageResponseDTO<DataRuleItemResDTO> listDataRuleItem(DataRuleItemReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        
        PageResponseDTO<DataRuleItemResDTO> res = new PageResponseDTO<DataRuleItemResDTO>();
        DataRuleItemCriteria driCriteria = new DataRuleItemCriteria();
        DataRuleItemCriteria.Criteria sql = driCriteria.createCriteria();

        if(StringUtil.isNotBlank(reqDto.getName())){
            sql.andNameLike("%"+reqDto.getName()+"%");
        }
        if(reqDto.getFuncId()!=null){
            sql.andFuncIdEqualTo(reqDto.getFuncId());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        
        driCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        driCriteria.setLimitClauseCount(reqDto.getPageSize());
        driCriteria.setOrderByClause("order_by asc");
        
        res = super.queryByPagination(reqDto, driCriteria, false, new PaginationCallback<DataRuleItem, DataRuleItemResDTO>() {

            @Override
            public List<DataRuleItem> queryDB(BaseCriteria arg0) {
                return dataRuleItemMapper.selectByExample((DataRuleItemCriteria) arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return dataRuleItemMapper.countByExample((DataRuleItemCriteria) arg0);
            }
            
            @Override
            public List<Comparator<DataRuleItem>> defineComparators() {
                List<Comparator<DataRuleItem>> ls = new ArrayList<Comparator<DataRuleItem>>();
                ls.add(new Comparator<DataRuleItem>(){
                    @Override
                    public int compare(DataRuleItem o1, DataRuleItem o2){
                        return o1.getOrderBy().longValue()<o2.getOrderBy().longValue()?1:-1;
                    }
                });
                return ls;
            }

            @Override
            public DataRuleItemResDTO warpReturnObject(DataRuleItem arg0) {
                DataRuleItemResDTO dto = new DataRuleItemResDTO();
                ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
                return dto;
            }
        });
        
        return res;
    }

    @Override
    public long saveDataRule(DataRuleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getItemId(), reqDto.getAuthId() },
                new String[] {"规则项目id", "权限id"});
        
        DataRule dr = new DataRule();
        ObjectCopyUtil.copyObjValue(reqDto, dr, null, false);
        dr.setId(seqDataRule.nextValue());
        if(reqDto.getOrderBy()==null){
            //计算序数
            DataRuleReqDTO countOrder = new DataRuleReqDTO();
            countOrder.setAuthId(reqDto.getAuthId());
            countOrder.setPageNo(0);//查全集
            PageResponseDTO<DataRuleResDTO> listRule = listDataRule(countOrder);
            BigDecimal orderBy = null;//权限下的规则配置项序数最大值加1
            if(CollectionUtils.isNotEmpty(listRule.getResult())){
                orderBy = listRule.getResult().get(0).getOrderBy();
                dr.setOrderBy(new BigDecimal(orderBy.longValue()+1));
            }else{
                dr.setOrderBy(new BigDecimal(1));//初始值为"1"
            }
        }
        try {
            dataRuleMapper.insertSelective(dr);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        
        return dr.getId();
    }

    @Override
    public DataRuleResDTO findDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId() }, new String[] {"规则配置id"});
        DataRuleResDTO res = new DataRuleResDTO();
        DataRule find = new DataRule();
        try {
            find = dataRuleMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        ObjectCopyUtil.copyObjValue(find, res, null, false);
        
        if(find==null) return null;
        return res;
    }

    @Override
    public void updateDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId() }, new String[] {"规则配置id"});
        DataRule update = new DataRule();
        ObjectCopyUtil.copyObjValue(reqDto, update, null, false);
        try {
            dataRuleMapper.updateByPrimaryKeySelective(update);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置修改失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteDataRuleById(DataRuleReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId() }, new String[] {"规则配置id"});
        try {
            dataRuleMapper.deleteByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
        }
    }

    @Override
    public PageResponseDTO<DataRuleResDTO> listDataRule(DataRuleReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
           throw new BusinessException(StaffConstants.STAFF_NULL_ERROR); 
        }
        
        PageResponseDTO<DataRuleResDTO> res = new PageResponseDTO<DataRuleResDTO>();
        DataRuleCriteria drCriteria = new DataRuleCriteria();
        DataRuleCriteria.Criteria sql = drCriteria.createCriteria();
        
        if(reqDto.getAuthId()!=null){
            sql.andAuthIdEqualTo(reqDto.getAuthId());
        }
        if(reqDto.getItemId()!=null){
            sql.andItemIdEqualTo(reqDto.getItemId());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        
        drCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        drCriteria.setLimitClauseCount(reqDto.getPageSize());
        drCriteria.setOrderByClause("order_by asc");
        
        res = super.queryByPagination(reqDto, drCriteria, false, new PaginationCallback<DataRule, DataRuleResDTO>() {

            @Override
            public List<DataRule> queryDB(BaseCriteria arg0) {
                return dataRuleMapper.selectByExample((DataRuleCriteria) arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return dataRuleMapper.countByExample((DataRuleCriteria) arg0);
            }
            
            @Override
            public List<Comparator<DataRule>> defineComparators() {
                List<Comparator<DataRule>> ls = new ArrayList<Comparator<DataRule>>();
                ls.add(new Comparator<DataRule>(){
                    @Override
                    public int compare(DataRule o1, DataRule o2){
                        return o1.getOrderBy().longValue()>o2.getOrderBy().longValue()?1:-1;
                    }
                });
                return ls;
            }

            @Override
            public DataRuleResDTO warpReturnObject(DataRule arg0) {
                DataRuleResDTO dto = new DataRuleResDTO();
                ObjectCopyUtil.copyObjValue(arg0, dto, null, false);
                //补充DataRuleItem 名称
                DataRuleItemReqDTO itemReq = new DataRuleItemReqDTO();
                itemReq.setId(dto.getItemId());
                DataRuleItemResDTO itemFind = findDataRuleItemById(itemReq);
                dto.setItemName(itemFind.getName());
                return dto;
            }
        });
        
        return res;
    }

    /**
     * 
     * countDataAuthByCode:(由规则编码统计数据规则记录数). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @param notEmptyWarning  true：当不为空时警告
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private Long countDataAuthByCode(DataAuthReqDTO reqDto, Boolean notEmptyWarning) throws BusinessException {
    	if(notEmptyWarning==null){
    		notEmptyWarning = false;
    	}
    	DataAuthCriteria codeOnlyCriteria = new DataAuthCriteria();
    	DataAuthCriteria.Criteria sql = codeOnlyCriteria.createCriteria().andAuthCodeEqualTo(reqDto.getAuthCode());
    	if(reqDto.getId()!=null && notEmptyWarning){
    		sql.andIdNotEqualTo(reqDto.getId());
    	}
        Long codeOnlyCount = dataAuthMapper.countByExample(codeOnlyCriteria);
        if(codeOnlyCount>0&&notEmptyWarning){
        	//规则编码已存在
        	throw new BusinessException("staff.100055", new String[]{reqDto.getAuthCode()});
        }
        return  codeOnlyCount;
    }
    
    /**
     * 
     * countDataAuthByName:(由规则名称统计数据规则记录数). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @param notEmptyWarning  true：当不为空时警告
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private Long countDataAuthByName(DataAuthReqDTO reqDto, Boolean notEmptyWarning) throws BusinessException {
    	if(notEmptyWarning==null){
    		notEmptyWarning = false;
    	}
    	DataAuthCriteria nameOnlyCriteria = new DataAuthCriteria();
    	DataAuthCriteria.Criteria sql = nameOnlyCriteria.createCriteria().andFuncIdEqualTo(reqDto.getFuncId()).andNameEqualTo(reqDto.getName());
    	if(reqDto.getId()!=null && notEmptyWarning){
    		sql.andIdNotEqualTo(reqDto.getId());
    	}
        Long nameOnlyCount = dataAuthMapper.countByExample(nameOnlyCriteria);
        if(nameOnlyCount>0&&notEmptyWarning){
        	//对象所属数据功能下已存在该名称
        	throw new BusinessException("staff.100056", new String[]{reqDto.getName()});
        }
        return nameOnlyCount;
    }
    
    @Override
    public long saveDataAuth(DataAuthReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getAuthCode(),reqDto.getName() },
        		new String[] {"数据规则编码","数据规则名称"});
        
        //0.验证
        //0.1验证“规则编码”,编码唯一
        this.countDataAuthByCode(reqDto, true);
        //0.2验证“规则名称”,对应唯一“数据功能”不能有相同的规则名称
        this.countDataAuthByName(reqDto, true);
        //1.保存“数据规则”
        DataAuth da = new DataAuth();
        ObjectCopyUtil.copyObjValue(reqDto, da, null, false);
        da.setId(seqDataAuth.nextValue());
        try {
            dataAuthMapper.insertSelective(da);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        //2.增加权限
        AuthPrivilege ap = new AuthPrivilege();
        ap.setRoleAdmin(da.getName()+StaffConstants.Privilege.NAME_SUFFIX_RULE);//后缀“规则权限”
        ap.setPrivilegeType(StaffConstants.Privilege.TYPE_RULE);
        ap.setSysCode(da.getSysCode());
        long apId = privlgManageSV.saveAuthPrivilege(ap);
        //3.增加规则权限关系
        AuthPrivilege2Rule ap2r = new AuthPrivilege2Rule();
        ap2r.setPrivilegeId(apId);
        ap2r.setRuleId(da.getId());
        ap2r.setSysCode(da.getSysCode());
        this.savePrivilegeDataAuthRel(ap2r);
        
        return da.getId();
    }

    @Override
    public DataAuthResDTO findDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId() }, new String[] {"数据权限id"});
        
        DataAuthResDTO res = new DataAuthResDTO();
        DataAuth find = new DataAuth();
        try {
            find = dataAuthMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        ObjectCopyUtil.copyObjValue(find, res, null, false);
//        //补充权限配置信息
//        if(find!=null){
//            DataRuleReqDTO reqRule = new DataRuleReqDTO();
//            reqRule.setPageNo(0);//查全集
//            reqRule.setAuthId(reqDto.getId());
//            PageResponseDTO<DataRuleResDTO> listRes = this.listDataRule(reqRule);
//            if(CollectionUtils.isNotEmpty(listRes.getResult())){
//                res.setRuleArr(listRes.getResult());
//            }
//        }
        
        if(find==null) return null;
        return res;
    }

    @Override
    public void updateDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId(),reqDto.getAuthCode(),reqDto.getName() },
        		new String[] {"数据权限规则id","数据规则编码","数据规则名称"});
        
        //0验证“规则名称”,对应唯一“数据功能”不能有相同的规则名称
        this.countDataAuthByName(reqDto, true);
        
        //1.新增
        DataAuth da = new DataAuth();
        ObjectCopyUtil.copyObjValue(reqDto, da, null, false);
        try {
            dataAuthMapper.updateByPrimaryKeySelective(da);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限修改失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
        //2.同时修改相关权限状态
        AuthPrivilege2RuleCriteria ap2rCriteria = new AuthPrivilege2RuleCriteria();
        ap2rCriteria.createCriteria().andRuleIdEqualTo(da.getId());
        List<AuthPrivilege2Rule> listRel = authPrivilege2RuleMapper.selectByExample(ap2rCriteria);
        if(CollectionUtils.isNotEmpty(listRel)){
            AuthPrivilege2Rule rel = listRel.get(0);
            Long privilegeId = rel.getPrivilegeId();
            AuthPrivilege findPriv = privlgManageSV.findAuthPrivilegeById(privilegeId);
            if(StringUtil.isNotBlank(da.getName())) findPriv.setRoleAdmin(da.getName()+StaffConstants.Privilege.NAME_SUFFIX_RULE);
            if(StringUtil.isNotBlank(da.getSysCode())) findPriv.setSysCode(da.getSysCode());
            if(StringUtil.isNotBlank(da.getEnabled())) findPriv.setStatus(da.getEnabled());
            //修改权限
            privlgManageSV.updateAuthPrivilegeById(findPriv);
            if(StringUtil.isNotBlank(da.getSysCode())) rel.setSysCode(da.getSysCode());
            //修改权限关系
            authPrivilege2RuleMapper.updateByPrimaryKeySelective(rel);
        }
    }

    @Override
    public void deleteDataAuthById(DataAuthReqDTO reqDto) throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        //参数校验
        staffTools.paramCheck(new Object[] { reqDto.getId() }, new String[] {"数据权限id"});
        
        try {
            dataAuthMapper.deleteByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限物理删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
        }
        
    }

    @Override
    public PageResponseDTO<DataAuthResDTO> listDataAuth(DataAuthReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR); 
        }
        
        PageResponseDTO<DataAuthResDTO> res = new PageResponseDTO<DataAuthResDTO>();
        DataAuthCriteria daCriteria = new DataAuthCriteria();
        DataAuthCriteria.Criteria sql = daCriteria.createCriteria();
        
        if(StringUtil.isNotBlank(reqDto.getEnabled())){
            sql.andEnabledEqualTo(reqDto.getEnabled());
        }
        if(StringUtil.isNotBlank(reqDto.getAuthType())){
            sql.andAuthTypeEqualTo(reqDto.getAuthType());
        }
        if(StringUtil.isNotBlank(reqDto.getName())){
            sql.andNameLike("%"+reqDto.getName()+"%");//模糊查询
        }
        if(StringUtil.isNotBlank(reqDto.getAuthCode())){
            sql.andAuthCodeLike("%"+reqDto.getAuthCode()+"%");//模糊查询
        }
        if(reqDto.getFuncId()!=null){
        	sql.andFuncIdEqualTo(reqDto.getFuncId());
        }
        if(reqDto.getId()!=null){
            sql.andIdEqualTo(reqDto.getId());
        }
        if(reqDto.getAuthSrc()!=null){
        	sql.andAuthSrcEqualTo(reqDto.getAuthSrc());
        }
        
        daCriteria.setLimitClauseCount(reqDto.getPageSize());
        daCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
        daCriteria.setOrderByClause(" id desc");
        
        res = super.queryByPagination(reqDto, daCriteria, false, new PaginationCallback<DataAuth, DataAuthResDTO>() {

            @Override
            public List<DataAuth> queryDB(BaseCriteria arg0) {
                return dataAuthMapper.selectByExample((DataAuthCriteria) arg0);
            }

            @Override
            public long queryTotal(BaseCriteria arg0) {
                return dataAuthMapper.countByExample((DataAuthCriteria) arg0);
            }

            @Override
            public DataAuthResDTO warpReturnObject(DataAuth arg0) {
                DataAuthResDTO t = new DataAuthResDTO();
                ObjectCopyUtil.copyObjValue(arg0, t, null, false);
                return t;
            }
        });
        
        return res;
    }
    
    /**
     * 
     * buildDataFuncCode:(生成数据功能编码). <br/> 
     * 准备：定制规则前缀
     * 
     * @author linby
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    private String buildDataFuncCode() throws BusinessException{
    	StringBuffer sb = new StringBuffer();
    	sb.append("DA");//TODO 硬编码
    	sb.append(seqDataFuncCode.nextValue().toString());
    	return sb.toString();
    }

	@Override
	public long saveDataFunc(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto, new String[] {"数据功能对象"});
        staffTools.paramCheck(new Object[] {reqDto.getName(), reqDto.getSysCode() },
                new String[] {"数据功能名称", "所属子系统"});
        
        DataFunc save = new DataFunc();
        ObjectCopyUtil.copyObjValue(reqDto, save, null, true);
        save.setId(seqDataFunc.nextValue());
        //初始化code
        save.setCode(this.buildDataFuncCode());
        //校验功能编码唯一性
        long codeAmount = this.countDataFuncByCode(save.getCode());
        if(codeAmount>0){
        	//功能编码{0}已存在！
			throw new BusinessException("staff.100052",new String[]{save.getCode()});
		}
        
        if(StringUtil.isBlank(save.getStatus())){
        	save.setStatus(StaffConstants.PublicParam.STATUS_ACTIVE);
        }
        if(save.getParentId()==null){//设置默认parentId为0
        	save.setParentId(0L);
    	}
        if(save.getSortOrder()==null){
        	//计算序数
        	DataFuncReqDTO counter = new DataFuncReqDTO();
        	counter.setParentId(save.getParentId());
        	counter.setPageNo(0);//查全集
        	PageResponseDTO<DataFuncResDTO> pagelist = this.listDataFunc(counter);
        	List<DataFuncResDTO> listRes = pagelist.getResult();
        	if(CollectionUtils.isNotEmpty(listRes)){//查到的最大值加1
        		Collections.sort(listRes, new Comparator<DataFuncResDTO>() {

					@Override
					public int compare(DataFuncResDTO o1, DataFuncResDTO o2) {
						if(o1.getSortOrder()==o2.getSortOrder()) return 0;
						return o1.getSortOrder()>o2.getSortOrder()?-1:1;//降序
					}
        			
				});
        		Short so = (short)(listRes.get(0).getSortOrder()+1); //加1
        		save.setSortOrder(so);
        	}else{//初始化为1
        		save.setSortOrder((short)1);
        	}
        }
        
        try {
        	dataFuncMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据功能入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        
		return save.getId();
	}

	@Override
	public void deleteDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId()}, new String[]{"数据功能ID"});
		DataFunc delLogic = new DataFunc();
		delLogic.setId(reqDto.getId());
		delLogic.setStatus(StaffConstants.PublicParam.STATUS_INVALID);//设置为失效
		
		try {
			dataFuncMapper.updateByPrimaryKeySelective(delLogic);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能逻辑删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
		}
	}

	@Override
	public void updateDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId()}, new String[]{"数据功能ID"});
		
		DataFunc update = new DataFunc();
		ObjectCopyUtil.copyObjValue(reqDto, update, null, true);
		
		update.setCode(null);//code 不可修改
		
		try {
			dataFuncMapper.updateByPrimaryKeySelective(update);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能修改失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
		}
	}

	@Override
	public void chstaDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId(), reqDto.getStatus()}, 
				new String[]{"数据功能ID", "数据功能对象状态"});
		
		DataFunc chsta = new DataFunc();
		chsta.setId(reqDto.getId());
		chsta.setStatus(reqDto.getStatus());
		
		try {
			dataFuncMapper.updateByPrimaryKeySelective(chsta);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能状态修改失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
		}
		
		//1.子功能状态变更
		this.chstaDataFuncRecursion(reqDto);
		//2.相关数据规则状态变更
		this.chstaDataAuthByFuncId(reqDto);
		
	}
	
	/**
	 * 
	 * chstaDataFuncRecursion:(递归修改功能子集体状态). <br/> 
	 * 
	 * @author linby
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private List<DataFuncReqDTO> chstaDataFuncRecursion(DataFuncReqDTO reqDto) throws BusinessException{
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId(),reqDto.getStatus()}, 
				new String[]{"数据功能ID","数据功能状态"});
		
		List<DataFuncReqDTO> list = new ArrayList<DataFuncReqDTO>();
		DataFuncCriteria dataFuncCriteria = new DataFuncCriteria();
		DataFuncCriteria.Criteria sql = dataFuncCriteria.createCriteria();
		sql.andParentIdEqualTo(reqDto.getId());
		DataFunc update = new DataFunc();
		update.setStatus(reqDto.getStatus());
		dataFuncMapper.updateByExampleSelective(update, dataFuncCriteria);
		List<DataFunc> funcList = dataFuncMapper.selectByExample(dataFuncCriteria);
		if(CollectionUtils.isNotEmpty(funcList)){
			for (DataFunc dataFunc : funcList) {
				DataFuncReqDTO reqFunc = new DataFuncReqDTO();
				ObjectCopyUtil.copyObjValue(dataFunc, reqFunc, null, true);
				list.add(reqFunc);
				this.chstaDataFuncRecursion(reqFunc);
				this.chstaDataAuthByFuncId(reqFunc);
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * chstaDataAuthByFuncId:(通过功能id批量修改规则状态). <br/> 
	 * 
	 * @author linby
	 * @param reqDto
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private void chstaDataAuthByFuncId(DataFuncReqDTO reqDto) throws BusinessException{
		DataAuthCriteria dataAuthCriteria = new DataAuthCriteria();
		DataAuthCriteria.Criteria sql = dataAuthCriteria.createCriteria();
		sql.andFuncIdEqualTo(reqDto.getId());
		DataAuth dataAuth = new DataAuth();
		dataAuth.setEnabled(reqDto.getStatus());//func的status 与 auth的enabled 的状态一致
		dataAuthMapper.updateByExampleSelective(dataAuth, dataAuthCriteria);
	}
	
	/**
	 * 
	 * countDataFuncByCode:(通过数据功能编码统计记录数). <br/> 
	 * 
	 * @author linby
	 * @param code
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private long countDataFuncByCode(String code) throws BusinessException {
		DataFuncCriteria dataFuncCriteria = new DataFuncCriteria();
		DataFuncCriteria.Criteria sql = dataFuncCriteria.createCriteria();
		sql.andCodeEqualTo(code);
		long count = 0L;
		try {
			count =  dataFuncMapper.countByExample(dataFuncCriteria);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能统计失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
		}
		return count;
	}

	@Override
	public DataFuncResDTO findDataFuncById(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId()}, new String[]{"数据功能对象ID"});
		
		DataFuncResDTO res = new DataFuncResDTO();
		DataFunc find = new DataFunc();
				
		try {
			find = dataFuncMapper.selectByPrimaryKey(reqDto.getId());
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能状态修改失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
		}
		ObjectCopyUtil.copyObjValue(find, res, null, true);
		
		if(find==null) return null;
		return res;
	}

	@Override
	public PageResponseDTO<DataFuncResDTO> listDataFunc(final DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		
		PageResponseDTO<DataFuncResDTO> res = new PageResponseDTO<DataFuncResDTO>();
		DataFuncCriteria dataFuncCriteria = new DataFuncCriteria();
		DataFuncCriteria.Criteria sql = dataFuncCriteria.createCriteria();
		
		if(StringUtil.isNotBlank(reqDto.getName())){
			sql.andNameLike("%"+reqDto.getName()+"%");
		}
		if(StringUtil.isNotBlank(reqDto.getSysCode())){
			sql.andSysCodeEqualTo(reqDto.getSysCode());
		}
		if(StringUtil.isNotBlank(reqDto.getStatus())){
			sql.andStatusEqualTo(reqDto.getStatus());
		}
		if(StringUtil.isNotBlank(reqDto.getCode())){
			sql.andCodeEqualTo(reqDto.getCode());
		}
		if(reqDto.getParentId()!=null){
			sql.andParentIdEqualTo(reqDto.getParentId());
		}
		if(reqDto.getId()!=null){
			sql.andIdEqualTo(reqDto.getId());
		}
		
		dataFuncCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
		dataFuncCriteria.setLimitClauseCount(reqDto.getPageSize());
		dataFuncCriteria.setOrderByClause("sort_order asc");
		
		res = super.queryByPagination(reqDto, dataFuncCriteria, false, new PaginationCallback<DataFunc, DataFuncResDTO>() {

			@Override
			public List<DataFunc> queryDB(BaseCriteria criteria) {
				return dataFuncMapper.selectByExample((DataFuncCriteria) criteria);
			}

			@Override
			public long queryTotal(BaseCriteria criteria) {
				return dataFuncMapper.countByExample((DataFuncCriteria) criteria);
			}

			@Override
			public DataFuncResDTO warpReturnObject(DataFunc t) {
				DataFuncResDTO dto = new DataFuncResDTO();
				ObjectCopyUtil.copyObjValue(t, dto, null, true);
				//是否父节点
				DataFuncReqDTO countChild = new DataFuncReqDTO();
				countChild.setSysCode(dto.getSysCode());
				countChild.setParentId(dto.getId());
				dto.setIsParent(countDataFuncByParent(countChild)>0);
				
				return dto;
			}
		});
		
		return res;
	}
	
	/**
	 * 
	 * countDataFuncByParent:(统计数据功能对象子节点数量). <br/> 
	 * 
	 * @author linby
	 * @param reqDto
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	private Long countDataFuncByParent(DataFuncReqDTO reqDto) throws BusinessException{
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getParentId(), reqDto.getSysCode()}, 
						new String[]{"数据功能ID", "数据功能所属子系统"});
		
		Long res = 0L;
		
		DataFuncCriteria example = new DataFuncCriteria();
		DataFuncCriteria.Criteria sql = example.createCriteria();
		sql.andSysCodeEqualTo(reqDto.getSysCode()).andParentIdEqualTo(reqDto.getParentId());
		try {
			res = dataFuncMapper.countByExample(example);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
		}
		
		return res;
	}

	@Override
	public void savePrivilegeDataAuthRel(AuthPrivilege2Rule ap2r) throws BusinessException {
		try {
			authPrivilege2RuleMapper.insertSelective(ap2r);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB数据功能查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
		}
	}

	@Override
	public void saveDataRuleBatch(DataAuthReqDTO reqDto) throws BusinessException {
        if(CollectionUtils.isNotEmpty(reqDto.getRuleArr())){
            for (DataRuleReqDTO dataRuleReqDTO : reqDto.getRuleArr()) {
                if(dataRuleReqDTO.getId()==null){//保存
                    dataRuleReqDTO.setAuthId(reqDto.getId());
                    this.saveDataRule(dataRuleReqDTO);
                }else{//修改 ; 
                    this.updateDataRuleById(dataRuleReqDTO);
                }
            }
        }
	}

	@Override
	public List<DataFuncZTreeNodeDTO> generateEntireTreeDataFunc(DataFuncReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getSysCode()},new String[]{"所属子系统"});
		List<DataFuncZTreeNodeDTO> res = new ArrayList<DataFuncZTreeNodeDTO>();
		
		//ztree
		Map<String, String> fontValid = new HashMap<String, String>();
		fontValid.put("color", "red");
		Map<String, String> fontActive = new HashMap<String, String>();
//		fontActive.put("color", "red");
		//1.查询数据功能
		DataFuncCriteria dataFuncCriteria = new DataFuncCriteria();
		dataFuncCriteria.createCriteria().andSysCodeEqualTo(reqDto.getSysCode());
		
		List<DataFunc> listDataFunc = new ArrayList<DataFunc>();
		try {
			listDataFunc = dataFuncMapper.selectByExample(dataFuncCriteria);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "数据库异常,全量数据规则树查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"全量数据规则树"});
		}
		
		if(CollectionUtils.isEmpty(listDataFunc)){//没有数据功能，直接返回
			return res;
		}
		//2.准备缓存数据
        Map<Long, AuthPrivilegeResDTO> mapAuthprivlg = new HashMap<Long, AuthPrivilegeResDTO>(); //缓存“权限”
        Map<Long, Long> mapPrivlgAuthRel = new HashMap<Long, Long>();//缓存“规则权限关系”
        //2.1 准备“权限”集合
        AuthPrivilegeReqDTO privlgDto = new AuthPrivilegeReqDTO();
        privlgDto.setSysCode(reqDto.getSysCode());
        privlgDto.setPrivilegeType(StaffConstants.Privilege.TYPE_RULE);
        privlgDto.setPageNo(0);//不分页，查全集
        PageResponseDTO<AuthPrivilegeResDTO> pageListPrivlg = privlgManageSV.listAuthPrivilege(privlgDto);
        if(CollectionUtils.isNotEmpty(pageListPrivlg.getResult())){
	        for (AuthPrivilegeResDTO authPrivilegeResDTO : pageListPrivlg.getResult()) {
	        	mapAuthprivlg.put(authPrivilegeResDTO.getId(), authPrivilegeResDTO);
			}
        }
        //2.2 准备“规则权限关系”
		AuthPrivilege2RuleCriteria authPrivilege2RuleCriteria = new AuthPrivilege2RuleCriteria();
		authPrivilege2RuleCriteria.createCriteria().andSysCodeEqualTo(reqDto.getSysCode());
		List<AuthPrivilege2Rule> listAuthprivilege2Rule = authPrivilege2RuleMapper.selectByExample(authPrivilege2RuleCriteria);
		if(CollectionUtils.isNotEmpty(listAuthprivilege2Rule)){
			for (AuthPrivilege2Rule authPrivilege2Rule : listAuthprivilege2Rule) {
				mapPrivlgAuthRel.put(authPrivilege2Rule.getRuleId(),authPrivilege2Rule.getPrivilegeId());
			}
		}
		//1.1封装数据功能树节点
		for (DataFunc dataFunc : listDataFunc) {
			DataFuncZTreeNodeDTO node = new DataFuncZTreeNodeDTO();
			ObjectCopyUtil.copyObjValue(dataFunc, node, null, true);
			node.setzType(StaffConstants.Ztree.NODE_TYPE_FUNC);
			node.setzId(node.getzType().concat("_").concat(node.getId().toString()));
			node.setzPId(node.getzType().concat("_").concat(node.getParentId().toString()));
			node.setzStatus(node.getStatus());
			node.setFont(StaffConstants.PublicParam.STATUS_ACTIVE.equals(node.getzStatus())?fontActive:fontValid);
			node.setIsParent(true);//数据功能，父节点；规则权限，叶子节点
			res.add(node);
			//1.2.1查询数据功能下对应规则权限
			DataAuthCriteria dataAuthCriteria = new DataAuthCriteria();
			dataAuthCriteria.createCriteria().andSysCodeEqualTo(reqDto.getSysCode()).andFuncIdEqualTo(dataFunc.getId());
			
			List<DataAuth> listDataAuth = new ArrayList<DataAuth>();
			try {
				listDataAuth = dataAuthMapper.selectByExample(dataAuthCriteria);
			} catch (DataAccessException e) {
				LogUtil.error(MODULE, "数据库异常,全量数据规则树查询失败", e);
	            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{"全量数据规则树"});
			}
			//1.2.2封装数据功能树节点
			if(CollectionUtils.isNotEmpty(listDataAuth)){
				for (DataAuth dataAuth : listDataAuth) {
					DataFuncZTreeNodeDTO leaf = new DataFuncZTreeNodeDTO();
					ObjectCopyUtil.copyObjValue(dataAuth, leaf, null, true);
					leaf.setzType(StaffConstants.Ztree.NODE_TYPE_RULE);
					leaf.setzId(leaf.getzType().concat("_").concat(leaf.getId().toString()));
					leaf.setzPId(node.getzId());
					leaf.setzStatus(leaf.getEnabled());
					leaf.setFont(StaffConstants.PublicParam.STATUS_ACTIVE.equals(leaf.getzStatus())?fontActive:fontValid);
					leaf.setIsParent(false);//数据功能，父节点；规则权限，叶子节点
					//规则-权限
					AuthPrivilegeResDTO authPrivDTO = new AuthPrivilegeResDTO();
					authPrivDTO = mapAuthprivlg.get(mapPrivlgAuthRel.get(leaf.getId()));
					if(authPrivDTO==null){//规则没有相关权限，无效并continue；
						continue;
					}
					leaf.setPrivilegeId(authPrivDTO.getId());//权限id
					leaf.setRoleAdmin(authPrivDTO.getRoleAdmin());//权限名称
					res.add(leaf);
				}
			}
		}
		//处理树节点open状态
		if(CollectionUtils.isNotEmpty(res)){
			List<String> listZid = new ArrayList<String>();
			List<String> listZpid = new ArrayList<String>();
			for (DataFuncZTreeNodeDTO zTreeNodeDTO : res) {
				if(zTreeNodeDTO.getIsParent()){
					listZid.add(zTreeNodeDTO.getzId());
					listZpid.add(zTreeNodeDTO.getzPId());
				}
			}
			listZid.removeAll(listZpid);
			for (DataFuncZTreeNodeDTO zTreeNodeDTO : res) {
				if(listZid.contains(zTreeNodeDTO.getzId())){
					zTreeNodeDTO.setOpen(true);
				}
			}
		}
		
		return res;
	}

	@Override
	public long saveDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[] {reqDto.getAuthId(),reqDto.getItemId()}, 
							  new String[]{ "权限id","结果集属性id"});
		
		DataFieldRule dfr = new DataFieldRule();
		ObjectCopyUtil.copyObjValue(reqDto, dfr, null, false);
		dfr.setId(seqDataFieldRule.nextValue());
		try {
            dataFieldRuleMapper.insertSelective(dfr);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
		
		return dfr.getId();
	}

	@Override
	public DataFieldRuleResDTO findDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{ reqDto.getId() }, new String[]{ "数据过滤规则明细id" });
		
		DataFieldRuleResDTO res = new DataFieldRuleResDTO();
		DataFieldRule find = new DataFieldRule();
		
		try {
            find = dataFieldRuleMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
		if(find == null) return null;
		
		ObjectCopyUtil.copyObjValue(find, res, null, false);
		
		return res;
	}

	@Override
	public void updateDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{ reqDto.getId() }, new String[]{ "过滤规则明细id" });
		
		DataFieldRule dfr = new DataFieldRule();
		ObjectCopyUtil.copyObjValue(reqDto, dfr, null, false);
		
		try {
			dataFieldRuleMapper.updateByPrimaryKeySelective(dfr);
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB过滤规则明细更新失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
		}
		
	}

	@Override
	public void deleteDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId()}, new String[]{ "过滤规则id" });
		
		try {
			dataFieldRuleMapper.deleteByPrimaryKey(reqDto.getId());
		} catch (DataAccessException e) {
			LogUtil.error(MODULE, "DB过滤规则删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
		}
	}

	@Override
	public PageResponseDTO<DataFieldRuleResDTO> listDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		
		PageResponseDTO<DataFieldRuleResDTO> res = new PageResponseDTO<DataFieldRuleResDTO>();
		DataFieldRuleCriteria ruleCriteria = new DataFieldRuleCriteria();
		DataFieldRuleCriteria.Criteria sql = ruleCriteria.createCriteria();
		
		if(reqDto.getItemId()!=null){
			sql.andItemIdEqualTo(reqDto.getItemId());
		}
		if(reqDto.getAuthId()!=null){
			sql.andAuthIdEqualTo(reqDto.getAuthId());
		}
		
		ruleCriteria.setLimitClauseCount(reqDto.getStartRowIndex());
		ruleCriteria.setLimitClauseCount(reqDto.getPageSize());
		ruleCriteria.setOrderByClause("id desc");
		
		res = super.queryByPagination(reqDto, ruleCriteria, false, new PaginationCallback<DataFieldRule, DataFieldRuleResDTO>() {

			@Override
			public List<DataFieldRule> queryDB(BaseCriteria arg0) {
				return dataFieldRuleMapper.selectByExample((DataFieldRuleCriteria) arg0);
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {
				return dataFieldRuleMapper.countByExample((DataFieldRuleCriteria) arg0);
			}

			@Override
			public DataFieldRuleResDTO warpReturnObject(DataFieldRule arg0) {
				DataFieldRuleResDTO t = new DataFieldRuleResDTO();
				ObjectCopyUtil.copyObjValue(arg0, t, null, false);
				return t;
			}
		});
		
		return res;
	}

	@Override
	public long saveDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException {
		
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[] {reqDto.getName(),reqDto.getFuncId()}, 
							  new String[]{ "结果集属性名称","所属数据功能id"});
		
		//名称不重复
		this.countDataFieldItemByName(reqDto, true);
		
		DataFieldItem dfi = new DataFieldItem();
		ObjectCopyUtil.copyObjValue(reqDto, dfi, null, false);
		dfi.setId(seqDataFieldItem.nextValue());
		try {
            dataFieldItemMapper.insertSelective(dfi);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB规则配置入库失败", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
		
		return dfi.getId();
	}

	@Override
	public DataFieldItemResDTO findDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{ reqDto.getId() }, new String[]{ "结果集属性id" });
		
		DataFieldItemResDTO res = new DataFieldItemResDTO();
		DataFieldItem find = new DataFieldItem();
		
		try {
            find = dataFieldItemMapper.selectByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB数据权限查询失败", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
		if(find == null) return null;
		
		ObjectCopyUtil.copyObjValue(find, res, null, false);
		
		return res;
	}

	@Override
	public void updateDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{reqDto.getId()}, new String[]{ "结果集属性id" });
		
		//名称不重复
		this.countDataFieldItemByName(reqDto, true);
		
		DataFieldItem dfi = new DataFieldItem();
		ObjectCopyUtil.copyObjValue(reqDto, dfi, null, false);
		try {
			dataFieldItemMapper.updateByPrimaryKeySelective(dfi);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB结果集属性对象更新失败", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
	}

	@Override
	public void deleteDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{ reqDto.getId() }, new String[]{ "结果集属性id" });
		
		//删除前校验，确保没有被引用
		//TODO
		
		try {
            dataFieldItemMapper.deleteByPrimaryKey(reqDto.getId());
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "DB结果集属性对象[ "+reqDto.getId()+" ]物理删除失败", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR);
        }
	}

	@Override
	public PageResponseDTO<DataFieldItemResDTO> listDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException {
		//参数校验
		staffTools.paramNullCheck(reqDto);
		
		PageResponseDTO<DataFieldItemResDTO> res = new PageResponseDTO<DataFieldItemResDTO>();
		DataFieldItemCriteria itemCriteria = new DataFieldItemCriteria();
		DataFieldItemCriteria.Criteria sql = itemCriteria.createCriteria();
		
		if(StringUtil.isNotBlank(reqDto.getName())){
			sql.andNameLike("%"+reqDto.getName()+"%");
		}
		if(StringUtil.isNotBlank(reqDto.getAttrName())){
			sql.andAttrNameLike("%"+reqDto.getAttrName()+"%");
		}
		if(StringUtil.isNotBlank(reqDto.getValueType())){
			sql.andValueTypeEqualTo(reqDto.getValueType());
		}
		if(reqDto.getFuncId()!=null){
			sql.andFuncIdEqualTo(reqDto.getFuncId());
		}
		
		itemCriteria.setLimitClauseStart(reqDto.getStartRowIndex());
		itemCriteria.setLimitClauseCount(reqDto.getPageSize());
		itemCriteria.setOrderByClause("id desc");
		
		res = super.queryByPagination(reqDto, itemCriteria, false, new PaginationCallback<DataFieldItem, DataFieldItemResDTO>() {

			@Override
			public List<DataFieldItem> queryDB(BaseCriteria arg0) {
				return dataFieldItemMapper.selectByExample((DataFieldItemCriteria) arg0);
			}

			@Override
			public long queryTotal(BaseCriteria arg0) {
				return dataFieldItemMapper.countByExample((DataFieldItemCriteria) arg0);
			}

			@Override
			public DataFieldItemResDTO warpReturnObject(DataFieldItem arg0) {
				DataFieldItemResDTO t = new DataFieldItemResDTO();
				ObjectCopyUtil.copyObjValue(arg0, t, null, false);
				return t;
			}
		});
		
		return res;
	}

	@Override
	public void saveDataFieldRuleBatch(DataAuthReqDTO reqDto) throws BusinessException {
		//传参校验
		staffTools.paramNullCheck(reqDto);
		staffTools.paramCheck(new Object[]{ reqDto.getId() }, new String[]{ "数据规则id" });
		
		//1.清空原有记录
		DataFieldRuleCriteria ruleCriteria = new DataFieldRuleCriteria();
		DataFieldRuleCriteria.Criteria sql = ruleCriteria.createCriteria();
		sql.andAuthIdEqualTo(reqDto.getId());//数据规则id
		dataFieldRuleMapper.deleteByExample(ruleCriteria);
		//2.创建新的记录
		if(CollectionUtils.isNotEmpty(reqDto.getFieldRuleArr())){
			for (DataFieldRuleReqDTO rule : reqDto.getFieldRuleArr()) {
				rule.setAuthId(reqDto.getId());
				this.saveDataFieldRule(rule);
			}
		}
	}

}

