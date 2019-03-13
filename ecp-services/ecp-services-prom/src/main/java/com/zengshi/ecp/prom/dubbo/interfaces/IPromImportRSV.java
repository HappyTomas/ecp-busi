package com.zengshi.ecp.prom.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface IPromImportRSV {


    /**
     * 导入中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(PromImportReqDTO promImportReqDTO) throws BusinessException;
    
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
     * 导入中间表保存  通过monogo id解析文件 
     * @param fileId
     * @throws BusinessException
     * @author huangjx
     */
    public void saveByFile(final PromImportReqDTO promImportReqDTO) throws BusinessException;
    
    public ImprotPromResultDTO saveByFileFJ(final PromImportReqDTO promImportReqDTO) throws BusinessException;

    
    /**
     * 上传执行是否完成
     * @param fileId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public String checkOver(String fileId) throws BusinessException;
}
