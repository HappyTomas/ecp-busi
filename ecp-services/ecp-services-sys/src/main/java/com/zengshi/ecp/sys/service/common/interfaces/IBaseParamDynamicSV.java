/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseParamDynamicSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2015-8-27下午7:03:25 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.BaseParamDynamic;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-27下午7:03:25  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseParamDynamicSV extends IGeneralSQLSV {
    
    /**
     * 
     * fetchDynamicParamResultList: 根据参数Key，语言编码，获取具体的参数信息列表<br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @param spLang 语言编码
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseParamDTO> fetchDynamicParamResultList(String paramKey, String spLang) throws BusinessException;
    
    
    /**
     * 
     * fetchDynamicParamResult: 根据参数Key，参数编码，语言编码，获取具体参数信息<br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @param paramCode
     * @param spLang
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseParamDTO fetchDynamicParamResult(String paramKey, String paramCode, String spLang) throws BusinessException;
    
    /**
     * 
     * fetchDynamicCfg: 获取动态参数的配置信息 <br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseParamDynamic fetchDynamicCfg(String paramKey) throws BusinessException;
}

