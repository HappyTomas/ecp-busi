package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-cms-server <br>
 * Description: <br>
 * Date:2016年2月23日下午3:45:07  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
public interface ICmsAppFloorSV extends IGeneralSQLSV{
    /**
     * 
     * addCmsAppFloor:(添加app楼层). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param dto 保存的数据
     * @return  app楼层对象
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorRespDTO addCmsAppFloor(CmsAppFloorReqDTO dto)throws BusinessException;

    /**
     * 
     * updateCmsAppFloor:(更新app楼层数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto  修改了的数据
     * @return app楼层对象
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorRespDTO updateCmsAppFloor(CmsAppFloorReqDTO dto)throws BusinessException; 
   
    /**
     * 
     * queryCmsAppFloorList:(查询app楼层列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return app楼层对象列表
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<CmsAppFloorRespDTO> queryCmsAppFloorList(CmsAppFloorReqDTO dto)throws BusinessException;
    
    /**
     * 
     * queryCmsAppFloorPage:(查询app楼层分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return  app楼层对象分页数据
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<CmsAppFloorRespDTO> queryCmsAppFloorPage(CmsAppFloorReqDTO dto)throws BusinessException;

    /**
     * 
     * queryCmsAppFloor:(查询单个app楼层对象). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return app楼层数据
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorRespDTO queryCmsAppFloor (CmsAppFloorReqDTO dto)throws BusinessException;

    /**
     * 
     * deleteCmsAppFloor:(根据id删除app楼层数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhnabh 
     * @param id
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteCmsAppFloor(String id)throws BusinessException;
    
    /**
     * 
     * deleteCmsAppFloorBatch:(批量删除). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param  list   id列表
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteCmsAppFloorBatch(List<String> list)throws BusinessException;

    /**
     * 
     * changeStatusCmsAppFloor:(改变app楼层状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param id
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void changeStatusCmsAppFloor(String id,String status) throws BusinessException;

    /**
     * 
     * changeStatusCmsAppFloorBatch:(批量改变app楼层状态). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param list
     * @param status
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void changeStatusCmsAppFloorBatch(List<String> list,String status) throws BusinessException;
}

