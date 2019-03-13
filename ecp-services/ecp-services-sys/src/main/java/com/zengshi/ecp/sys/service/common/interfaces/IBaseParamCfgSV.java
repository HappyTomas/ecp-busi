/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseParamCfgSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2015-8-19����7:58:55 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.BaseParamConfig;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-19����7:58:55  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseParamCfgSV extends IGeneralSQLSV{
    
    /**
     * 
     * fetchParamListByKey:根据参数Key，获取参数列表信息<br/> 
     * 
     * @author yugn 
     * @param paramKey 参数Key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<BaseParamDTO> fetchParamListByKey(String paramKey , String spLang) throws BusinessException;
    
    /**
     * 
     * fetchParamByKeyCode:根据参数Key，编码，语言，获取对应的参数信息<br/> 
     * 
     * @author yugn 
     * @param paramKey 参数Key
     * @param spCode  参数编码
     * @param spLang  参数语言
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    BaseParamDTO fetchParamByKeyCode(String paramKey,String spCode, String spLang) throws BusinessException;
    
    /**
     * 
     * fetchActiveParamConfig: 获取所有有效的配置信息 <br/>  
     * 
     * @author yugn 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<BaseParamConfig> fetchActiveParamConfig() throws BusinessException;

}

