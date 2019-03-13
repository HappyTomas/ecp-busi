package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCategorySyncSV {
    // 新增分类映射关系记录
    public void addGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 编辑分类映射关系记录
    public void updateGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 删除分类映射关系记录
    public void deleteGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 查询分类映射关系记录
    public PageResponseDTO<GdsCatgSyncRespDTO> queryGdsCatgSyncPageInfo(
            GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 批量取消分类映射关系记录
    public void batchCancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 根据原始分类查询映射分类
    public GdsCatgSyncRespDTO queryGdsCategoryInfoByOriginCatgCode(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception;

    // 根据来源系统查询系统根节点
    public List<GdsCatgSyncRespDTO> queryRootCatgSyncBySrcSys(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception;

    // 根据父节点查询子节点
    public List<GdsCatgSyncRespDTO> querySubCatgSyncByParent(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception;

    // 取消删除分类映射
    public void cancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws Exception;

    // 查询原始分类的分类路径列表
    public List<GdsCatgSyncRespDTO> queryCatgSyncTraceUpon(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws Exception;
    //根据原始分类编码获取分类映射记录
    public GdsCatgSyncRespDTO queryGdsCategorySyncByPK(GdsCatgSyncReqDTO catgSyncReqDTO)throws Exception;
    
    
    public void deleteGdsCategorySyncTable(GdsCatgSyncReqDTO catgSyncReqDTO)throws BusinessException;

}
