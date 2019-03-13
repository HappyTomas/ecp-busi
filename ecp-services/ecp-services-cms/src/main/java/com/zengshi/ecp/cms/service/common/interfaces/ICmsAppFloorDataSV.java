package com.zengshi.ecp.cms.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAppFloorDataRespDTO;
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
public interface ICmsAppFloorDataSV extends IGeneralSQLSV{
    /**
     * 
     * addCmsAppFloorData:(添加app楼层数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh
     * @param dto 保存的数据
     * @return  app楼层数据对象
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorDataRespDTO addCmsAppFloorData(CmsAppFloorDataReqDTO dto)throws BusinessException;

    /**
     * 
     * updateCmsAppFloorData:(更新app楼层数据数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto  修改了的数据
     * @return app楼层数据对象
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorDataRespDTO updateCmsAppFloorData(CmsAppFloorDataReqDTO dto)throws BusinessException; 
   
    /**
     * 
     * queryCmsAppFloorDataList:(查询app楼层数据列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return app楼层数据对象列表
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<CmsAppFloorDataRespDTO> queryCmsAppFloorDataList(CmsAppFloorDataReqDTO dto)throws BusinessException;
    
    /**
     * 
     * queryCmsAppFloorDataPage:(查询app楼层数据分页). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return  app楼层数据对象分页数据
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<CmsAppFloorDataRespDTO> queryCmsAppFloorDataPage(CmsAppFloorDataReqDTO dto)throws BusinessException;

    /**
     * 
     * queryCmsAppFloorData:(查询单个app楼层数据对象). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param dto 查询条件
     * @return app楼层数据数据
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public CmsAppFloorDataRespDTO queryCmsAppFloorData (CmsAppFloorDataReqDTO dto)throws BusinessException;

    /**
     * 
     * deleteCmsAppFloorData:(根据id删除app楼层数据数据). <br/> 
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
    public void deleteCmsAppFloorData(String id)throws BusinessException;
    
    /**
     * 
     * deleteCmsAppFloorDataBatch:(批量删除). <br/> 
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
    public void deleteCmsAppFloorDataBatch(List<String> list)throws BusinessException;

    /**
     * 
     * changeStatusCmsAppFloorData:(改变app楼层数据状态). <br/> 
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
    public void changeStatusCmsAppFloorData(String id,String status) throws BusinessException;

    /**
     * 
     * changeStatusCmsAppFloorDataBatch:(批量改变app楼层数据状态). <br/> 
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
    public void changeStatusCmsAppFloorDataBatch(List<String> list,String status) throws BusinessException;
}

