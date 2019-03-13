/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseParamSimpleSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2015-8-19����8:25:35 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.BaseParamSimple;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-19 18:25:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseParamSimpleSV extends IGeneralSQLSV {
    
    /**
     * 
     * fetchParamListByKey:根据参数Key，获取参数列表信息 <br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseParamSimple> fetchParamListByKey(String paramKey) throws BusinessException;
    
    
    /**
     * 
     * fetchParamListByKey:根据参数Key，获取参数列表信息 <br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @param spLang 语言
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseParamSimple> fetchParamListByKeyLang(String paramKey,String spLang) throws BusinessException;
    
    /**
     * 
     * fetchParamListByKeyCode:根据参数Key，参数编码，获取列表信息 <br/> 
     * 
     * @author yugn 
     * @param paramKey
     * @param code
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseParamSimple> fetchParamListByKeyCode(String paramKey , String code) throws BusinessException;
    
    /**
     * 
     * fetchParamListByKeyCode:根据参数Key，参数编码，语言，获取参数信息 <br/> 
     * @author yugn 
     * @param paramKey
     * @param code
     * @param lang
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public BaseParamSimple fetchParamListByKeyCode(String paramKey , String code ,String lang) throws BusinessException;
}

