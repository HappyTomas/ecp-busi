package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCategorySyncRSV {
    // 新增分类映射关系记录
    public void addGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException;

    // 编辑分类映射关系记录
    public void updateGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException;

    // 删除分类映射关系记录
    public void deleteGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException;

    // 查询分类映射关系记录
    public PageResponseDTO<GdsCatgSyncRespDTO> queryGdsCatgSyncPageInfo(
            GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException;

    // 批量删除分类映射关系记录
    public void batchCancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;

    // 根据原始分类编码查询现系统分类编码
    public GdsCatgSyncRespDTO queryGdsCategoryInfoByOriginCatgCode(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;

    // 根据来源系统查询系统根节点
    public List<GdsCatgSyncRespDTO> queryRootCatgSyncBySrcSys(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;

    // 根据父节点查询子节点
    public List<GdsCatgSyncRespDTO> querySubCatgSyncByParent(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;

    public void cancelGdsCatgSyncInfo(GdsCatgSyncReqDTO catgSyncReqDTO) throws BusinessException;

    public List<GdsCatgSyncRespDTO> queryCatgSyncTraceUpon(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;

    public GdsCatgSyncRespDTO queryGdsCategorySyncByPK(GdsCatgSyncReqDTO catgSyncReqDTO)
            throws BusinessException;
    
    public void emptyGdsCategorySyncTable(GdsCatgSyncReqDTO catgSyncReqDTO)throws BusinessException;
}
