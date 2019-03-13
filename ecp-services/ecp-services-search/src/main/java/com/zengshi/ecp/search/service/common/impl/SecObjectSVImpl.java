package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.search.dao.mapper.common.SecObjectMapper;
import com.zengshi.ecp.search.dao.model.SecField;
import com.zengshi.ecp.search.dao.model.SecObject;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfig2ObjectSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:52 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecObjectSVImpl extends GeneralSQLSVImpl implements ISecObjectSV {

    @Autowired
    private SecObjectMapper secObjectMapper;

    @Resource(name = "seq_sec_object")
    private Sequence seqSecObject;

    @Resource
    private ISecFieldSV secFieldSV;
    
    @Resource
    private ISecConfig2ObjectSV secConfig2ObjectSV;

    @Override
    public long saveSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {
        
        //必须指定配置编码
        if (secObjectReqDTO.getConfigId() == null || secObjectReqDTO.getConfigId()== 0) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_OBJECT_ADD,new String[]{"未指定索引配置Id！"});
        }

        // DTO转BO,同名属性自动赋值
        SecObject secObject = new SecObject();
        BeanTransfermationUtils.dto2bo(secObject, secObjectReqDTO);

        // 补值操作
        long id = this.seqSecObject.nextValue();
        secObject.setId(id);
        secObject.setStatus(SearchConstants.STATUS_VALID);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secObject.setCreateStaff(secObjectReqDTO.getStaff().getId());
        secObject.setCreateTime(t);
        secObject.setUpdateStaff(secObjectReqDTO.getStaff().getId());
        secObject.setUpdateTime(t);

        this.secObjectMapper.insert(secObject);
        
        //配置-对象映射关系保存
        SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        secConfig2ObjectReqDTO.setConfigId(secObjectReqDTO.getConfigId());
        secConfig2ObjectReqDTO.setObjectId(id);
        secConfig2ObjectReqDTO.setObjectType(secObjectReqDTO.getObjectType());
        this.secConfig2ObjectSV.saveSecConfig2Object(secConfig2ObjectReqDTO);

        // 关联保存SecField
        List<SecFieldReqDTO> secFieldReqDTOList = secObjectReqDTO.getSecFieldReqDTOList();
        if (secFieldReqDTOList != null && secFieldReqDTOList.size() > 0) {
            for (SecFieldReqDTO secFieldReqDTO : secFieldReqDTOList) {
                secFieldReqDTO.setObjectId(id);
                this.secFieldSV.saveSecField(secFieldReqDTO);
            }
        }

        return id;

    }

    @Override
    public void deleteSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {
        
        if(secObjectReqDTO.getId()<=10){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_OBJECT_DELETE, 
                    new String[]{"内置数据对象不允许删除！"});
        }
        
        if (secObjectReqDTO.getConfigId()== null ||secObjectReqDTO.getConfigId() == 0
                ||secObjectReqDTO.getId()== null ||secObjectReqDTO.getId() == 0){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_OBJECT_DELETE, 
                    new String[]{"未指定索引配置Id或数据对象Id！"});
        }

        // 逻辑删除
        SecObject secObject = new SecObject();
        secObject.setId(secObjectReqDTO.getId());
        secObject.setStatus(SearchConstants.STATUS_INVALID);

        // 记录创建和修改信息的补值操作
        secObject.setUpdateStaff(secObjectReqDTO.getStaff().getId());
        secObject.setUpdateTime(DateUtil.getSysDate());

        this.secObjectMapper.updateByPrimaryKeySelective(secObject);
        
        //删除config-object映射关系
        SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        secConfig2ObjectReqDTO.setConfigId(secObjectReqDTO.getConfigId());
        secConfig2ObjectReqDTO.setObjectId(secObjectReqDTO.getId());
        this.secConfig2ObjectSV.deleteSecConfig2Object(secConfig2ObjectReqDTO);
        
        // 关联删除SecField
        SecFieldReqDTO secFieldReqDTO=new SecFieldReqDTO();
        secFieldReqDTO.setObjectId(secObjectReqDTO.getId());
        this.secFieldSV.deleteSecFieldByObjectId(secFieldReqDTO);

    }

    @Override
    public void updateSecObjectAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecObject secObject = new SecObject();
        BeanTransfermationUtils.dto2bo(secObject, secObjectReqDTO);

        // 记录创建和修改信息的补值操作
        secObject.setUpdateStaff(secObjectReqDTO.getStaff().getId());
        secObject.setUpdateTime(DateUtil.getSysDate());
        
        this.secObjectMapper.updateByPrimaryKey(secObject);
        
        // 关联保存SecField
        List<SecFieldReqDTO> secFieldReqDTOList = secObjectReqDTO.getSecFieldReqDTOList();
        if (secFieldReqDTOList != null && secFieldReqDTOList.size() > 0) {
            for (SecFieldReqDTO secFieldReqDTO : secFieldReqDTOList) {

                secFieldReqDTO.setObjectId(secObjectReqDTO.getId());

                // 修改
                if (secFieldReqDTO.getId() != null && secFieldReqDTO.getId() != 0) {
                    this.secFieldSV.updateSecField(secFieldReqDTO);
                } else {// 新增
                    this.secFieldSV.saveSecField(secFieldReqDTO);
                }
            }
        }
        
        //删除字段
        if(CollectionUtils.isNotEmpty(secObjectReqDTO.getDelFieldIds())){
            SecFieldReqDTO secFieldReqDTO=new SecFieldReqDTO();
            for(Long l:secObjectReqDTO.getDelFieldIds()){
                secFieldReqDTO.setId(l);
                this.secFieldSV.deleteSecField(secFieldReqDTO);
            }
        }

    }
    
    @Override
    public SecObjectRespDTO querySecObjectById(SecObjectReqDTO secObjectReqDTO)
            throws BusinessException {
        SecObject secObject = this.secObjectMapper.selectByPrimaryKey(secObjectReqDTO.getId());
        if (secObject == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secObjectReqDTO.getId()});
        }
        
        if(StringUtils.equals(secObjectReqDTO.getStatus(), SearchConstants.STATUS_VALID)
                &&StringUtils.equals(secObject.getStatus(), SearchConstants.STATUS_INVALID)){
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secObjectReqDTO.getId()});
        }

        SecObjectRespDTO secObjectRespDTO=new SecObjectRespDTO();
        BeanTransfermationUtils.bo2dto(secObjectRespDTO, secObject);
        
        return secObjectRespDTO;
    }

    @Override
    public SecObjectRespDTO querySecObjectByIdAll(SecObjectReqDTO secObjectReqDTO) throws BusinessException {

        SecObject secObject = this.secObjectMapper.selectByPrimaryKey(secObjectReqDTO.getId());
        if (secObject == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secObjectReqDTO.getId()});
        }
        
        if(StringUtils.equals(secObjectReqDTO.getStatus(), SearchConstants.STATUS_VALID)
                &&StringUtils.equals(secObject.getStatus(), SearchConstants.STATUS_INVALID)){
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secObjectReqDTO.getId()});
        }

        SecObjectRespDTO secObjectRespDTO=new SecObjectRespDTO();
        BeanTransfermationUtils.bo2dto(secObjectRespDTO, secObject);
        
        // 关联查询SecField
        SecFieldReqDTO secFieldReqDTO=new SecFieldReqDTO();
        secFieldReqDTO.setObjectId(secObjectReqDTO.getId());
        secFieldReqDTO.setStatus(secObjectReqDTO.getStatus());
        List<SecField> secFieldList = this.secFieldSV.querySecFieldByObjectId(secFieldReqDTO);
        if (secFieldList != null && secFieldList.size() > 0) {
            
            List<SecFieldRespDTO> secFieldRespDTOList=new ArrayList<SecFieldRespDTO>(secFieldList.size()); 
            for (SecField secField : secFieldList) {

                SecFieldRespDTO secFieldRespDTO=new SecFieldRespDTO();
                BeanTransfermationUtils.bo2dto(secFieldRespDTO, secField);
                secFieldRespDTOList.add(secFieldRespDTO);
            }
            
            secObjectRespDTO.setSecFieldRespDTOList(secFieldRespDTOList);
        }
        
        return secObjectRespDTO;
        
    }

    @Override
    public PageResponseDTO<SecObjectRespDTO> querySecObjectPage(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO)
            throws BusinessException {
        
        PageResponseDTO<SecConfig2ObjectRespDTO> resp=this.secConfig2ObjectSV.querySecConfig2ObjectPage(secConfig2ObjectReqDTO);
        
        PageResponseDTO<SecObjectRespDTO> resp2=new PageResponseDTO<SecObjectRespDTO>();
        resp2.setCount(resp.getCount());
        resp2.setPageCount(resp.getPageCount());
        resp2.setPageNo(resp.getPageNo());
        resp2.setPageSize(resp.getPageSize());
        
        if(CollectionUtils.isNotEmpty(resp.getResult())){
            List<SecObjectRespDTO> result=new ArrayList<SecObjectRespDTO>();
            for(SecConfig2ObjectRespDTO secConfig2ObjectRespDTO:resp.getResult()){
                SecObjectReqDTO secObjectReqDTO=new SecObjectReqDTO();
                secObjectReqDTO.setId(secConfig2ObjectRespDTO.getObjectId());
                secObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
                SecObjectRespDTO secObjectRespDTO=this.querySecObjectById(secObjectReqDTO);
                if(secObjectRespDTO!=null){
                    result.add(secObjectRespDTO);
                }
            }
            resp2.setResult(result);
        }
        
        return resp2;
                
    }
    
}
