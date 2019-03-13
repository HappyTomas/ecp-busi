package com.zengshi.ecp.staff.service.cache.interfaces;

import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.dto.AuthMenuResDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 菜单缓存<br>
 * Date:2015年10月22日上午9:48:30  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public interface IMenuCacheSV {
    
    /**
     * 
     * addRecord:(新增对应菜单的缓存记录). <br/> 
     * 
     * @author linby 
     * @param cache
     * @return 受影响的记录数
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long addRecord(AuthMenuResDTO cache) throws BusinessException;
    
    /**
     * 
     * updateRecord:(修改对应菜单的缓存记录). <br/> 
     * 
     * @author linby 
     * @param cache
     * @return 受影响的记录数
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long updateRecord(AuthMenuResDTO cache) throws BusinessException;
        
    /**
     * 
     * removeRecord:(移除对应菜单的缓存记录). <br/> 
     * 
     * @author linby 
     * @param cache
     * @return 受影响的记录数
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long removeRecord(AuthMenuResDTO cache) throws BusinessException;
    
    /**
     * 
     * clear:(菜单缓存清空). <br/> 
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long clear() throws BusinessException;
    
    /**
     * 
     * init:(初始化菜单缓存). <br/> 
     * <pre>
     * CacheUtil getItem
     * 菜单key:SYS_SUB_SYSTEM_{sysCode}
     * </pre>
     * 
     * @author linby 
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long init() throws BusinessException;

}

