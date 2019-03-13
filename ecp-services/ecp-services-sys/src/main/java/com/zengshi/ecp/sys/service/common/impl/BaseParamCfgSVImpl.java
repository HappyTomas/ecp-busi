/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseParamCfgSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-8-19下午9:59:54 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.sys.dao.mapper.common.BaseParamConfigMapper;
import com.zengshi.ecp.sys.dao.model.BaseParamConfig;
import com.zengshi.ecp.sys.dao.model.BaseParamConfigCriteria;
import com.zengshi.ecp.sys.dao.model.BaseParamSimple;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamCfgSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamDynamicSV;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseParamSimpleSV;
import com.zengshi.paas.utils.LocaleUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 用于参数信息获取<br>
 * Date:2015-8-19下午9:59:54  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseParamCfgSV")
public class BaseParamCfgSVImpl extends GeneralSQLSVImpl implements IBaseParamCfgSV {

    private static final String MODULE = BaseParamCfgSVImpl.class.getName();
    
    @Resource
    private  BaseParamConfigMapper baseParamConfigMapper;

    /** 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseParamCfgSV#fetchParamListByKey(java.lang.String) 
     */
    @Override
    public List<BaseParamDTO> fetchParamListByKey(String paramKey , String spLang) throws BusinessException {
        if(StringUtil.isEmpty(paramKey)){
            LogUtil.error(MODULE, "根据参数Key，获取参数列表信息，入参为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        
        if(StringUtil.isEmpty(spLang)){
            spLang = LocaleUtil.getLocalString();
        }
        
        BaseParamConfig paramConfig = this.fetchParamCfgByKey(paramKey);
        if(paramConfig == null){
            return null;
        }
        
        return this.fetchParamListByConfig(paramConfig, spLang);
    }

    
    @Override
    public BaseParamDTO fetchParamByKeyCode(String paramKey, String spCode, String spLang)
            throws BusinessException {
        if(StringUtil.isEmpty(paramKey) || StringUtil.isEmpty(spCode)){
            LogUtil.error(MODULE, "根据参数Key,参数编码，获取参数信息，入参为空");
            throw new BusinessException(EcpSysCodeConstants.SYS_PARAMCFG_PARAM_NULL);
        }
        if(StringUtil.isEmpty(spLang)){
            spLang = LocaleUtil.getLocalString();
        }
        BaseParamConfig paramConfig = this.fetchParamCfgByKey(paramKey);
        if(paramConfig == null){
            return null;
        }
        
        
        return this.fetchParamByConfig(paramConfig, spCode, spLang);
    }

    @Override
    public List<BaseParamConfig> fetchActiveParamConfig() throws BusinessException {
        
        BaseParamConfigCriteria criteria = new BaseParamConfigCriteria();
        criteria.createCriteria().andStatusEqualTo("1");
        return this.baseParamConfigMapper.selectByExample(criteria);
    }




    private static final String ParamTypeSimple = "SIMPLE";
    
    private static final String ParamTypeDynamic = "DYNAMIC";
    
    /**
     * 
     * fetchParamListByConfig: 根据参数配置信息，获取参数信息列表<br/> 
     * 
     * @author yugn 
     * @param paramConfig
     * @return 
     * @since JDK 1.6
     */
    public  List<BaseParamDTO> fetchParamListByConfig(BaseParamConfig paramConfig, String spLang){
        
        String paramType = paramConfig.getParamType();
        if (ParamTypeSimple.equalsIgnoreCase(paramType)){
            ////简单参数
            return this.fetchParamListFromSimple(paramConfig.getParamLinkKey(), spLang);
            
        } else if (ParamTypeDynamic.equalsIgnoreCase(paramType)){
            
            return this.fetchParamListFromDynamic(paramConfig.getParamLinkKey(), spLang);
        } else {
            LogUtil.info(MODULE, "从数据库中获取的 参数定义信息的参数类型不对；paramType:"+paramType+"；无法加载参数信息！");
            return null;
        }
        
    }
    
    /**
     * 
     * fetchParamByConfig:根据参数配置信息，获取参数信息 <br/> 
     * 
     * @author yugn 
     * @param paramConfig
     * @param spCode
     * @param spLang
     * @return 
     * @since JDK 1.6
     */
    public BaseParamDTO fetchParamByConfig(BaseParamConfig paramConfig, String spCode, String spLang){
        if (ParamTypeSimple.equalsIgnoreCase(paramConfig.getParamType())){
            ////简单参数
            return this.fetchParamFromSimple(paramConfig.getParamLinkKey(), spLang, spCode);
            
        } else  if (ParamTypeDynamic.equalsIgnoreCase(paramConfig.getParamType())){
            return this.fetchParamFromDynamic(paramConfig.getParamLinkKey() , spLang, spCode);
        } else {
            LogUtil.info(MODULE, "从数据库中获取的 参数定义信息的参数类型不对；paramType:"+paramConfig.getParamLinkKey()+"；无法加载参数信息！");
            return null;
        }
    }
    
    @Resource
    private IBaseParamSimpleSV baseParamSimpleSV;
    
    /**
     * 
     * @author yugn 
     * @param paramKey
     * @return 
     * @since JDK 1.6
     */
    private List<BaseParamDTO> fetchParamListFromSimple(String paramKey, String spLang){
        List<BaseParamSimple> lst = this.baseParamSimpleSV.fetchParamListByKeyLang(paramKey, spLang);
        if(lst == null || lst.size() == 0){
            return null;
        } else {
            List<BaseParamDTO> dtos = new ArrayList<BaseParamDTO>();
            for(BaseParamSimple simple : lst){
                BaseParamDTO dto = new BaseParamDTO();
                dto.setSpCode(simple.getSpCode());
                dto.setSpLang(simple.getSpLang());
                dto.setSpValue(simple.getSpValue());
                dto.setSpOrder(simple.getSpOrder().intValue());
                
                dtos.add(dto);
            }
            
            return dtos;
        }
    }
    
    /**
     * 
     * fetchParamFromSimple:根据参数Key、编码、语言 获取参数信息<br/> 
     * 
     * @author yugn 
     * @param paramKey 参数Key
     * @param spLang 语言
     * @param spCode 参数编码
     * @return 
     * @since JDK 1.6
     */
    private BaseParamDTO fetchParamFromSimple(String paramKey, String spLang ,String spCode){
        BaseParamSimple simple = this.baseParamSimpleSV.fetchParamListByKeyCode(paramKey, spCode, spLang);
        if(simple == null){
            return null;
        } else {
            BaseParamDTO dto = new BaseParamDTO();
            dto.setSpCode(simple.getSpCode());
            dto.setSpLang(simple.getSpLang());
            dto.setSpValue(simple.getSpValue());
            dto.setSpOrder(simple.getSpOrder().intValue());
            
            return dto;
        }
    }
    
    /**
     * 
     * fetchParamCfgByKey: 根据参数Key，获取参数配置信息 <br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @return 
     * @since JDK 1.6
     */
    public BaseParamConfig fetchParamCfgByKey(String paramKey){
        
        return this.baseParamConfigMapper.selectByPrimaryKey(paramKey);
    }
    
    @Resource
    private IBaseParamDynamicSV baseParamDynamicSV;
    /**
     * 
     * fetchParamListFromDynamic: 根据参数Key，获取参数列表信息<br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @return 
     * @since JDK 1.6
     */
    private List<BaseParamDTO> fetchParamListFromDynamic(String paramKey, String spLang){
        return this.baseParamDynamicSV.fetchDynamicParamResultList(paramKey, spLang);
    }
    
    /**
     * 
     * fetchParamFromDynamic: 根据参数Key，编码，语言编码，获取参数信息<br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @param spLang
     * @param spCode
     * @return 
     * @since JDK 1.6
     */
    private BaseParamDTO fetchParamFromDynamic(String paramKey, String spLang ,String spCode){
        
        return this.baseParamDynamicSV.fetchDynamicParamResult(paramKey, spCode, spLang);
    }
}

