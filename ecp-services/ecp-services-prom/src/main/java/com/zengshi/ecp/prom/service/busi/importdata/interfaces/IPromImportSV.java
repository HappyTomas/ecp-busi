package com.zengshi.ecp.prom.service.busi.importdata.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dao.model.PromImport;
import com.zengshi.ecp.prom.dubbo.dto.CommonFileDTO;
import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromImportSV extends IGeneralSQLSV{
 
    /**
     * 导入中间表保存  通过monogo id解析文件 
     * @param fileDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveByFile(final PromImportReqDTO promImportReqDTO) throws BusinessException;
    
    public ImprotPromResultDTO saveByFileFJ(final PromImportReqDTO promImportReqDTO) throws BusinessException;

    /**
     * 导入中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(PromImportReqDTO promImportReqDTO) throws BusinessException;
    
    /**
     * 导入中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMulitThread(List<PromImportReqDTO> list) throws BusinessException;
    /**
     * 促销正式导入商品
     * @param list
     * @throws BusinessException
     * @author huangjx
     */
    public void saveMulitThreadSku(List<PromImportReqDTO> list,PromInfoDTO promInfoDTO) throws BusinessException;
    /**
     * 更新
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public long update(PromImportReqDTO promImportReqDTO) throws BusinessException;
 
    /**
     * 删除中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void deleteById(PromImportReqDTO promImportReqDTO) throws BusinessException;
    
    
    /**
     * 促销中间表数据  不分页
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromImportRespDTO> queryPromImportList(PromImportReqDTO promImportReqDTO) throws BusinessException;
 
    /**
     * 促销中间表数据  批量保存
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void saveList(List<PromImportReqDTO> importList) throws BusinessException;
    /**
     * 促销中间表数据  分页
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromImportRespDTO> queryPromImportForPage(PromImportReqDTO promImportReqDTO)
            throws BusinessException;
    /**
     * excel数据转为bean 列表
     * @param datas
     * @param fileId
     * @param shopId
     * @param siteId
     * @param promImportReqDTO
     * @return
     * @author huangjx
     */
    public PromImportReqDTO readExcel(List<Object> datas, String fileId, Long shopId, Long siteId,
            PromImportReqDTO promImportReqDTO) throws BusinessException;
    /**
     * 上传执行是否完成
     * @param fileId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public String checkOver(String fileId) throws BusinessException;
}
