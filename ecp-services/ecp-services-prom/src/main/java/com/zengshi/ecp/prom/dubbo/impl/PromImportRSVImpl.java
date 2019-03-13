package com.zengshi.ecp.prom.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.prom.dubbo.dto.ImprotPromResultDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromImportRespDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromImportRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.importdata.interfaces.IPromImportSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-12-03 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromImportRSVImpl implements IPromImportRSV {
    
    @Resource
    private  IPromImportSV promImportSV;

    /**
     * 导入中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void save(PromImportReqDTO promImportReqDTO) throws BusinessException{
        promImportSV.save(promImportReqDTO);
    }
    
    /**
     * 更新
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public long update(PromImportReqDTO promImportReqDTO) throws BusinessException{
        return promImportSV.update(promImportReqDTO);
    }
 
    /**
     * 删除中间表保存
     * @param promImportReqDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void deleteById(PromImportReqDTO promImportReqDTO) throws BusinessException{
        promImportSV.deleteById(promImportReqDTO);
    }
    
    
    /**
     * 促销中间表数据  不分页
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromImportRespDTO> queryPromImportList(PromImportReqDTO promImportReqDTO) throws BusinessException{
        return promImportSV.queryPromImportList(promImportReqDTO);
    }
 
    /**
     * 促销中间表数据  批量保存
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void saveList(List<PromImportReqDTO> importList) throws BusinessException{
        promImportSV.saveMulitThread(importList);
    }
    /**
     * 促销中间表数据  分页
     * @param promImportReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromImportRespDTO> queryPromImportForPage(PromImportReqDTO promImportReqDTO)
            throws BusinessException{
        return promImportSV.queryPromImportForPage(promImportReqDTO);
    }
    
    /**
     * 导入中间表保存  通过monogo id解析文件 
     * @param fileId
     * @throws BusinessException
     * @author huangjx
     */
    public void saveByFile(final PromImportReqDTO promImportReqDTO) throws BusinessException{
        
        long t=System.currentTimeMillis();
        if(promImportReqDTO==null){
            throw new BusinessException("prom.400076");
        }
        if(StringUtil.isEmpty(promImportReqDTO.getFileId())){
            throw new BusinessException("prom.400201");
        }
        if(StringUtil.isEmpty(promImportReqDTO.getFileName())){
            throw new BusinessException("prom.400202");
        }
        
        if(StringUtil.isEmpty(promImportReqDTO.getImportType())){
            promImportReqDTO.setImportType(PromConstants.PromImportData.IMPORT_TYPE_0);
        }
        
        promImportSV.saveByFile(promImportReqDTO);
    }
    public ImprotPromResultDTO saveByFileFJ(final PromImportReqDTO promImportReqDTO) throws BusinessException{
        
        long t=System.currentTimeMillis();
        if(promImportReqDTO==null){
            throw new BusinessException("prom.400076");
        }
        if(StringUtil.isEmpty(promImportReqDTO.getFileId())){
            throw new BusinessException("prom.400201");
        }
        if(StringUtil.isEmpty(promImportReqDTO.getFileName())){
            throw new BusinessException("prom.400202");
        }
        
        if(StringUtil.isEmpty(promImportReqDTO.getImportType())){
            promImportReqDTO.setImportType(PromConstants.PromImportData.IMPORT_TYPE_0);
        }
        
        return promImportSV.saveByFileFJ(promImportReqDTO);
    }
    /**
     * TODO 验证是否上传成功
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromImportRSV#checkOver(java.lang.String)
     * @param fileId
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public String checkOver(String fileId) throws BusinessException{
        return promImportSV.checkOver(fileId);
    }
}
