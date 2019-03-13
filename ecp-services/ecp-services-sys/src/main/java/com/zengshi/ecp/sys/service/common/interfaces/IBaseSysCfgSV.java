/** 
 * Project Name:ecp-services-sys 
 * File Name:IBaseSysCfgSV.java 
 * Package Name:com.zengshi.ecp.sys.service.common.interfaces 
 * Date:2015-8-19下午9:56:31 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.BaseSysCfg;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-8-19下午9:56:31  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
public interface IBaseSysCfgSV extends IGeneralSQLSV {
    /**
     * 
     * saveCfg:系统参数配置 <br/> 
     *  
     * @author yugn 
     * @param sysCfg 带保存的系统参数
     *   sysCfg为空，或者sysCfg.paraCode为空，则抛出异常
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void saveCfg(BaseSysCfg sysCfg) throws BusinessException;
    
    /**
     * 
     * listCfg： 获取系统参数列表信息<br/> 
     *  
     * @author yugn 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<BaseSysCfg> listCfg() throws BusinessException;
    
    /**
     * 
     * updateCfg: 更新系统参数 <br/> 
     * 
     * @author yugn 
     * @param sysCfgDto
     * @throws BusinessException 
     *    sysCfgDto 为空，或者sysCfgDto.paraCode 为空，则抛出异常
     * @since JDK 1.6
     */
    public void updateCfg(BaseSysCfg sysCfg) throws BusinessException;
    
    /**
     * 
     * querySysCfgByCode:根据系统编码，查询系统参数<br/> 
     *  
     * @author yugn 
     * @throws BusinessException 
     * paraCode 为空，则抛出异常
     * @since JDK 1.6
     */
    public BaseSysCfg querySysCfgByCode(String paraCode) throws BusinessException;
    

}

