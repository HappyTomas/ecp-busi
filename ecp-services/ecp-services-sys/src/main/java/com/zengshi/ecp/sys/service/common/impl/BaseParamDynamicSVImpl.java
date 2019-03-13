/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamDynamicSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-8-27下午7:48:53 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.common.BaseParamDynamicMapper;
import com.zengshi.ecp.sys.dao.mapper.common.manual.CommDynamicSqlMapper;
import com.zengshi.ecp.sys.dao.model.BaseParamDynamic;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IBusiDynamicSqlSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamDynamicSV;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午7:48:53  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseParamDynamicSV")
public class BaseParamDynamicSVImpl extends GeneralSQLSVImpl implements IBaseParamDynamicSV {
    
    private static final String MODULE = BaseParamDynamicSVImpl.class.getName();
    
    private static final String DB_TYPE_COMMON = "01";
    private static final String DB_TYPE_BUSI = "02";
    
    @Resource(name="baseParamDynamicMapper")
    private BaseParamDynamicMapper baseParamDynamicMapper;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamDynamicSV#fetchDynamicParamResultList(java.lang.String) 
     */
    @Override
    public List<BaseParamDTO> fetchDynamicParamResultList(String paramKey, String spLang)
            throws BusinessException {
        
        if(StringUtil.isEmpty(paramKey)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数信息列表，需要查询的参数Key，入参为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(spLang)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数信息列表，语言为空，取默认语言");
            spLang = LocaleUtil.getLocalString();
        }
        
        BaseParamDynamic dynamic = this.fetchDynamicCfg(paramKey);
        if(dynamic == null){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息为空；参数Key：" + paramKey);
            return null;
        }
        
        String db = dynamic.getDbName();
        String sql = dynamic.getSqlLoad();
        if(StringUtil.isEmpty(db) || StringUtil.isEmpty(sql)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，DBName或者SQL为空，无法获取参数信息，请检查配置；参数Key：" + paramKey);
            return null;
        }
        
        ///增加 语言的查询条件；
        sql = "select t.* from ( " + sql +" )  t where lang = '"+spLang+"'";
        LogUtil.info(MODULE, "根据语言，转换之后的查询脚本："+sql);
        
        return fetchResult(db,sql,paramKey);
    }
    
    
    @Override
    public BaseParamDTO fetchDynamicParamResult(String paramKey, String paramCode, String spLang)
            throws BusinessException {
        if(StringUtil.isEmpty(paramKey) || StringUtil.isEmpty(paramCode)){
            LogUtil.info(MODULE, "根据参数Key、参数编码，获取动态参数信息，参数Key 或者编码的入参为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(spLang)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数信息列表，语言为空，取默认语言");
            spLang = LocaleUtil.getLocalString();
        }
        
        BaseParamDynamic dynamic = this.fetchDynamicCfg(paramKey);
        if(dynamic == null){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息为空；参数Key：" + paramKey);
            return null;
        }
        
        String db = dynamic.getDbName();
        String sql = dynamic.getSqlLoad();
        if(StringUtil.isEmpty(db) || StringUtil.isEmpty(sql)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，DBName或者SQL为空，无法获取参数信息，请检查配置；参数Key：" + paramKey);
            return null;
        }
        
        ///增加 语言 \ 编码 的查询条件；
        sql = "select t.* from ( " + sql +" )  t where lang = '"+spLang+"' and code = '"+paramCode+"'";
        LogUtil.info(MODULE, "根据语言，转换之后的查询脚本："+sql);
        
        List<BaseParamDTO> dtos = fetchResult(db,sql,paramKey);
        if(dtos == null || dtos.size() == 0){
            return null;
        } else {
            return dtos.get(0);
        }
        
    }



    /** 
     * 根据参数Key 获取动态参数的配置信息；
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamDynamicSV#fetchDynamicCfg(java.lang.String) 
     */
    @Override
    public BaseParamDynamic fetchDynamicCfg(String paramKey) throws BusinessException {
        if(StringUtil.isEmpty(paramKey)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，入参为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
            
        return this.baseParamDynamicMapper.selectByPrimaryKey(paramKey);
    }
    
    @Resource(name="commDynamicSqlMapper")
    private CommDynamicSqlMapper commDynamicSqlMapper;
    
    private List<BaseParamDTO> fetchResultFromCommon(String sql){
        return this.commDynamicSqlMapper.dynamicSqlSelect(sql);
    }
    
    @Resource(name="busiDynamicSqlSV")
    private IBusiDynamicSqlSV busiDynamicSqlSV;
    
    private List<BaseParamDTO> fetchResultFromBusi(String sql){
        return this.busiDynamicSqlSV.fetchParamList(sql);
    }
    
    /**
     * 
     * fetchResult: 根据数据库和脚本，获取参数信息； <br/> 
     * 
     * @author yugn 
     * @param db
     * @param sql
     * @return 
     * @since JDK 1.6
     */
    private List<BaseParamDTO> fetchResult(String db, String sql, String paramKey){
        if(DB_TYPE_COMMON.equalsIgnoreCase(db)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，DBName = 01 ,从公共库获取，；参数Key：" + paramKey+"; sql:"+sql);
            return fetchResultFromCommon(sql);
            
        } else if(DB_TYPE_BUSI.equalsIgnoreCase(db)){
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，DBName = 02 ,从业务库获取，；参数Key：" + paramKey+"; sql:"+sql);
            return fetchResultFromBusi(sql);
            
        } else {
            LogUtil.info(MODULE, "根据参数Key，获取动态参数配置信息，DBName 不符合要求，无法获取参数信息，请检查配置；参数Key：" + paramKey+"; DB值："+db);
            return null;
        }
    }

}
