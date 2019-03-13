package com.zengshi.ecp.staff.service.common.privilege.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.staff.dao.model.AuthPrivilege2Rule;
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

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 数据权限管理功能接口<br>
 * Date:2015年10月9日上午11:17:58  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IDataAuthManageSV extends IGeneralSQLSV {
    
    /**
     * 
     * saveDataRuleItem:(保存数据规则项目). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveDataRuleItem(DataRuleItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataRuleItem:(更新数据规则项目). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataRuleItemById:(根据ID删除数据规则项目). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataRuleItemById:(根据ID查找数据规则项目). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public DataRuleItemResDTO findDataRuleItemById(DataRuleItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataRuleItem:(根据既定条件查询数据规则项目|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<DataRuleItemResDTO> listDataRuleItem(DataRuleItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveDataRule:(保存规则配置). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveDataRule(DataRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataRuleById:(根据id删除规则配置). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public DataRuleResDTO findDataRuleById(DataRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataRuleById:(通过id修改规则配置). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateDataRuleById(DataRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataRuleById:(通过id删除规则配置). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteDataRuleById(DataRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataRule:(通过既定条件查询规则配置列表|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<DataRuleResDTO> listDataRule(DataRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveDataAuth:(保存数据规则权限). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long saveDataAuth(DataAuthReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataAuthById:(根据id查询数据规则权限). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public DataAuthResDTO findDataAuthById(DataAuthReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataAuthById:(根据id更新数据规则权限). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void updateDataAuthById(DataAuthReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataAuthById:(根据id删除数据规则权限). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteDataAuthById(DataAuthReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataAuth:(根据既定条件查询数据规则权限|分页). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<DataAuthResDTO> listDataAuth(DataAuthReqDTO reqDto) throws BusinessException;
    
    
    /**
     * 
     * saveDataFunc:(保存数据功能). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long saveDataFunc(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataFuncById:(通过id删除数据功能). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteDataFuncById(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataFuncById:(通过id修改数据功能对象). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateDataFuncById(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * chstaDataFuncById:(启用或禁用数据功能[修改状态]). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void chstaDataFuncById(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataFuncById:(根据id查找数据功能). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public DataFuncResDTO findDataFuncById(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataFunc:(根据既定条件查询数据功能|分页). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<DataFuncResDTO> listDataFunc(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * savePrivilegeDataAuthRel:(新增规则权限关系). <br/> 
     * 
     * @author linby
     * @param ap2r
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void savePrivilegeDataAuthRel(AuthPrivilege2Rule ap2r) throws BusinessException;
    
    
    /**
     * 
     * saveDataRuleBatch:(保存规则明细配置[批量]). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveDataRuleBatch(DataAuthReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * generateEntireTreeDataFunc:(生成数据规则权限功能树). <br/> 
     * 含功能以及功能相关规则
     * ztree 简单模式 
     * 必传: 1.sysCode
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public List<DataFuncZTreeNodeDTO> generateEntireTreeDataFunc(DataFuncReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveDataFieldRule:(保存数据过滤规则明细). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long saveDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataFieldRuleById:(根据id查找数据过滤规则明细). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public DataFieldRuleResDTO findDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataFieldRuleById:(根据id修改数据过滤规则明细). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataFieldRuleById:(根据id删除数据过滤规则明细). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteDataFieldRuleById(DataFieldRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataFieldRule:(根据既定条件查询数据规则明细). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<DataFieldRuleResDTO> listDataFieldRule(DataFieldRuleReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveDataFieldItem:(保存结果集属性). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public long saveDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * findDataFieldItemById:(根据id查找结果集属性). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public DataFieldItemResDTO findDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateDataFieldItemById:(根据id修改结果集属性). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void updateDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * deleteDataFieldItem:(根据id删除结果集属性). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteDataFieldItemById(DataFieldItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * listDataFieldItem:(根据既定条件查询结果集属性). <br/> 
     * 
     * @author linby
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public PageResponseDTO<DataFieldItemResDTO> listDataFieldItem(DataFieldItemReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * saveDataFieldRuleBatch:(保存过滤规则明细（列规则）配置[批量]). <br/> 
     * 
     * @author linby 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveDataFieldRuleBatch(DataAuthReqDTO reqDto) throws BusinessException;
}

