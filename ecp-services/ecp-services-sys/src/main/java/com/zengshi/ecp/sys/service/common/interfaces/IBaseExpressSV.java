package com.zengshi.ecp.sys.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.sys.dao.model.BaseExpress;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: 物流公司信息<br>
 * Date:2015-9-1下午4:28:35  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6
 */
public interface IBaseExpressSV extends IGeneralSQLSV{
    
    /**
     * 
     * listAllExpress: 获取所有的物流信息<br/> 
     * 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    public List<BaseExpress> listAllExpress();
    
    /**
     * 
     * listActiveExpress: 获取素有有效的物流信息 <br/> 
     * 
     * @author yugn 
     * @return 
     * @since JDK 1.6
     */
    public List<BaseExpress> listActiveExpress();
    
    /**
     * 
     * queryExpressById: 根据Id，获取物流信息<br/> 
     * 
     * @author yugn 
     * @param Id
     * @return 
     * @since JDK 1.6
     */
    public BaseExpress queryExpressById(long id);
    
    /**
     * 
     * listExpressByPage:分页查询 <br/> 
     * @author yugn 
     * @param reqDto
     * @return 
     * @since JDK 1.6
     */
    public PageResponseDTO<BaseExpressRespDTO> listExpressByPage(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * createExpress: 新建物流信息  , 创建成功之后，返回物流ID<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException  入参为空，则抛出异常
     * @since JDK 1.6
     */
    public long createExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * updateExpress: 更新物流信息<br/>  
     *   返回更新的记录数
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException  入参为空、根据ID获取物流信息为空，则抛出异常；
     * @since JDK 1.6
     */
    public long updateExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * removeExpress: 删除物流信息 根据reqDto.id 设置状态为 失效<br/> 
     *    返回更新的记录数；
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 入参为空、根据ID获取物流信息为空，则抛出异常；
     * @since JDK 1.6
     */
    public long removeExpress(BaseExpressReqDTO reqDto) throws BusinessException;
    
    /**
     * 
     * registExpress: 启用物流信息  根据reqDto.id 设置状态为 生效<br/> 
     * 
     * @author yugn 
     * @param reqDto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public long registExpress(BaseExpressReqDTO reqDto) throws BusinessException;
}

