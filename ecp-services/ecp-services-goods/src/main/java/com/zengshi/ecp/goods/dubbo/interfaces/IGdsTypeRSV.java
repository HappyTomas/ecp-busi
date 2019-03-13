/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsTypeRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015年8月27日下午5:43:39 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsType;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsTypeRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.server.front.dto.BaseInfo;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月27日下午5:43:39  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsTypeRSV {
    /**
     * 
     * 创建类型.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public void createGdsType(GdsTypeReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 编辑类型.<br/> 
     * 
     * @author liyong7
     * @param reqDTO 
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void editGdsType(GdsTypeReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据类型主键查询类型 .<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public GdsTypeRespDTO queryGdsTypeByPK(LongReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据分页条件分页查询类型信息.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsTypeRespDTO> queryGdsTypePaging(GdsTypeReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * queryGdsTypes:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public List<GdsTypeRespDTO> queryAllGdsTypes()throws BusinessException;
    
    /**
     * 
     * queryGdsTypes:(这里用一句话描述这个方法的作用). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public List<GdsTypeRespDTO> queryAllGdsTypesFromCache()throws BusinessException;
    
    /**
     * 
     * 根据类型名称或者结合类型状态判断是否已经存在类型.<br/>
     * 新创类型时如果用户需要对类型名称进行重复检测时可以调用此方法<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public boolean isExist(GdsTypeReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 编辑前重复检则,如果类型名称发生变更,在编辑保存之前如果需要可以触发此验证以保证类型的唯一性.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException
     * @return 
     * @since JDK 1.6
     */
    public boolean existChkBeforeEdit(GdsTypeReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据主键禁用类型.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeDisableGdsTypeByPK(LongReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据主键启用类型.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeEnableGdsTypeByPK(LongReqDTO reqDTO)throws BusinessException;
    
    /**
     * 根据id获取商品类型 缓存
     * @param id
     * @return
     * @throws BusinessException
     */
	public GdsTypeRespDTO queryGdsTypeByPKFromCache(LongReqDTO id) throws BusinessException;

}

