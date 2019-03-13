package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecConfigMapper;
import com.zengshi.ecp.search.dao.model.SecConfig;
import com.zengshi.ecp.search.dao.model.SecConfig2Object;
import com.zengshi.ecp.search.dao.model.SecConfigCriteria;
import com.zengshi.ecp.search.dao.model.SecConfigCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfig2ObjectSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfigSV;
import com.zengshi.ecp.search.service.common.interfaces.ISecObjectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月10日上午10:19:22 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecConfigSVImpl extends GeneralSQLSVImpl implements ISecConfigSV {

    @Autowired
    private SecConfigMapper secConfigMapper;

    @Resource(name = "seq_sec_config")
    private Sequence seqSecConfig;

    @Resource
    private ISecObjectSV secObjectSV;

    @Resource
    private ISecConfig2ObjectSV secConfig2ObjectSV;

    @Override
    public long saveSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecConfig secConfig = new SecConfig();
        BeanTransfermationUtils.dto2bo(secConfig, secConfigReqDTO);

        // 补值操作
        long id = this.seqSecConfig.nextValue();
        secConfig.setId(id);
        secConfig.setStatus(SearchConstants.STATUS_VALID);
        secConfig.setCollectionStatus(SearchConstants.STATUS_0);
        secConfig.setIndexStatus(SearchConstants.STATUS_0);
        secConfig.setConfigIfActive(SearchConstants.STATUS_0);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secConfig.setCreateStaff(secConfigReqDTO.getStaff().getId());
        secConfig.setCreateTime(t);
        secConfig.setUpdateStaff(secConfigReqDTO.getStaff().getId());
        secConfig.setUpdateTime(t);

        this.secConfigMapper.insert(secConfig);
        
        return id;
    }

    @Override
    public void deleteSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        
        if(secConfigReqDTO.getId()<=10){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_CONFIG_DELETE, 
                    new String[]{"内置索引配置不允许删除！"});
        }

        // 逻辑删除
        SecConfig secConfig = new SecConfig();
        secConfig.setId(secConfigReqDTO.getId());
        secConfig.setStatus(SearchConstants.STATUS_INVALID);

        // 记录创建和修改信息的补值操作
        secConfig.setUpdateStaff(secConfigReqDTO.getStaff().getId());
        secConfig.setUpdateTime(DateUtil.getSysDate());

        this.secConfigMapper.updateByPrimaryKeySelective(secConfig);

        SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        secConfig2ObjectReqDTO.setConfigId(secConfigReqDTO.getId());
        secConfig2ObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
        List<SecConfig2Object> secConfig2ObjectList = this.secConfig2ObjectSV
                .querySecConfig2ObjectByConfigId(secConfig2ObjectReqDTO);
        if (secConfig2ObjectList != null && secConfig2ObjectList.size() > 0) {
            for (SecConfig2Object secConfig2Object : secConfig2ObjectList) {
                
                // 关联删除SecObject
                SecObjectReqDTO secObjectReqDTO=new SecObjectReqDTO();
                secObjectReqDTO.setId(secConfig2Object.getObjectId());
                secObjectReqDTO.setConfigId(secConfigReqDTO.getId());
                this.secObjectSV.deleteSecObjectAll(secObjectReqDTO);
            }

        }
        
    }

    @Override
    public void updateSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecConfig secConfig = new SecConfig();
        BeanTransfermationUtils.dto2bo(secConfig, secConfigReqDTO);

        // 记录创建和修改信息的补值操作
        secConfig.setUpdateStaff(secConfigReqDTO.getStaff().getId());
        secConfig.setUpdateTime(DateUtil.getSysDate());
        
        this.secConfigMapper.updateByPrimaryKey(secConfig);
        
    }
    
    @Override
    public void updateSecConfigSelective(SecConfigReqDTO secConfigReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecConfig secConfig = new SecConfig();
        BeanTransfermationUtils.dto2bo(secConfig, secConfigReqDTO);

        // 记录创建和修改信息的补值操作
        secConfig.setUpdateStaff(secConfigReqDTO.getStaff().getId());
        secConfig.setUpdateTime(DateUtil.getSysDate());

        this.secConfigMapper.updateByPrimaryKeySelective(secConfig);
        
    }
    
    @Override
    public SecConfigRespDTO querySecConfigById(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        SecConfig secConfig = this.secConfigMapper.selectByPrimaryKey(secConfigReqDTO.getId());
        if (secConfig == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secConfigReqDTO.getId()});
        }
        
        if(StringUtils.equals(secConfigReqDTO.getStatus(), SearchConstants.STATUS_VALID)
                &&StringUtils.equals(secConfig.getStatus(), SearchConstants.STATUS_INVALID)){
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secConfigReqDTO.getId()});
        }
        
        SecConfigRespDTO secConfigRespDTO = new SecConfigRespDTO();
        BeanTransfermationUtils.bo2dto(secConfigRespDTO, secConfig);
        
        return secConfigRespDTO;
    }
    
    @Override
    public SecConfigRespDTO querySecConfigByIdAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        SecConfig secConfig = this.secConfigMapper.selectByPrimaryKey(secConfigReqDTO.getId());
        if (secConfig == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secConfigReqDTO.getId()});
        }
        
        if(StringUtils.equals(secConfigReqDTO.getStatus(), SearchConstants.STATUS_VALID)
                &&StringUtils.equals(secConfig.getStatus(), SearchConstants.STATUS_INVALID)){
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secConfigReqDTO.getId()});
        }
        
        SecConfigRespDTO secConfigRespDTO = new SecConfigRespDTO();
        BeanTransfermationUtils.bo2dto(secConfigRespDTO, secConfig);

        // 关联查询SecObject
        SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        secConfig2ObjectReqDTO.setConfigId(secConfigReqDTO.getId());
        secConfig2ObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
        List<SecConfig2Object> secConfig2ObjectList = this.secConfig2ObjectSV
                .querySecConfig2ObjectByConfigId(secConfig2ObjectReqDTO);
        if (secConfig2ObjectList != null && secConfig2ObjectList.size() > 0) {

            List<SecObjectRespDTO> secObjectRespDTOList = new ArrayList<SecObjectRespDTO>(
                    secConfig2ObjectList.size());
            for (SecConfig2Object secConfig2Object : secConfig2ObjectList) {
                SecObjectReqDTO secObjectReqDTO=new SecObjectReqDTO();
                secObjectReqDTO.setId(secConfig2Object.getObjectId());
                secObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
                SecObjectRespDTO secObjectRespDTO=this.secObjectSV.querySecObjectByIdAll(secObjectReqDTO);
                if(secObjectRespDTO!=null){
                    secObjectRespDTOList.add(secObjectRespDTO);
                }
            }

            secConfigRespDTO.setSecObjectRespDTOList(secObjectRespDTOList);
        }

        return secConfigRespDTO;
        
    }
    
    @Override
    public List<SecConfigRespDTO> listSecConfigAll(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        
        List<SecConfig> secConfigList=this.listSecConfig(secConfigReqDTO);
        
        if(secConfigList!=null&&!secConfigList.isEmpty()){
            
            List<SecConfigRespDTO> secConfigRespDTOList=new ArrayList<SecConfigRespDTO>(secConfigList.size());
            
            for(SecConfig secConfig:secConfigList){
                secConfigReqDTO.setId(secConfig.getId());
                SecConfigRespDTO secConfigRespDTO=this.querySecConfigByIdAll(secConfigReqDTO);
                if(secConfigRespDTO!=null){
                    secConfigRespDTOList.add(secConfigRespDTO);
                }
            }
            
            return secConfigRespDTOList;
        }
        
        return null;
        
    }

    @Override
    public List<SecConfig> listSecConfig(SecConfigReqDTO secConfigReqDTO) throws BusinessException {
        SecConfigCriteria criteria = new SecConfigCriteria();
        criteria.setOrderByClause("id asc");
        Criteria cr = criteria.createCriteria();

        if(StringUtils.equals(secConfigReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecConfig> secConfigList = this.secConfigMapper.selectByExample(criteria);
        
        return secConfigList;
        
    }
    
    @Override
    public boolean isDupConfigName(SecConfigReqDTO secConfigReqDTO) throws BusinessException{
        SecConfigCriteria criteria = new SecConfigCriteria();
        Criteria cr = criteria.createCriteria();

        cr.andConfigIfActiveEqualTo(GdsConstants.Commons.STATUS_VALID);
        cr.andConfigCollectionNameEqualTo(secConfigReqDTO.getConfigCollectionName());

        List<SecConfig> secConfigList = this.secConfigMapper.selectByExample(criteria);
        
        if(secConfigList!=null&&secConfigList.size()>0){
            return true;
        }
        
        return false;
        
    }

    @Override
    public PageResponseDTO<SecConfigRespDTO> querySecConfigPage(SecConfigReqDTO secConfigReqDTO)
            throws BusinessException {
        SecConfigCriteria dcriteria=new SecConfigCriteria();
        dcriteria.setLimitClauseCount(secConfigReqDTO.getPageSize());
        dcriteria.setLimitClauseStart(secConfigReqDTO.getStartRowIndex());
        dcriteria.setOrderByClause("id asc");
        
        Criteria criteria=dcriteria.createCriteria();
        
        if(StringUtils.equals(secConfigReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getConfigName())){
            criteria.andConfigNameLike("%" + secConfigReqDTO.getConfigName() + "%");
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getConfigDesc())){
            criteria.andConfigDescLike("%" + secConfigReqDTO.getConfigDesc() + "%");
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getConfigCollectionName())){
            criteria.andConfigCollectionNameLike("%" + secConfigReqDTO.getConfigCollectionName() + "%");
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getConfigIfActive())){
            criteria.andConfigIfActiveEqualTo(secConfigReqDTO.getConfigIfActive());
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getCollectionStatus())){
            criteria.andCollectionStatusEqualTo(secConfigReqDTO.getCollectionStatus());
        }
        
        if(StringUtils.isNotBlank(secConfigReqDTO.getIndexStatus())){
            criteria.andIndexStatusEqualTo(secConfigReqDTO.getIndexStatus());
        }
        
        return super.queryByPagination(secConfigReqDTO, dcriteria,false, new PaginationCallback<SecConfig, SecConfigRespDTO>() {
            
            //查询记录集
            @Override
            public List<SecConfig> queryDB(BaseCriteria criteria) {
                
                return secConfigMapper.selectByExample((SecConfigCriteria) criteria);
            }
            
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return secConfigMapper.countByExample((SecConfigCriteria)criteria);
            }

            //查询结果转换
            @Override
            public SecConfigRespDTO warpReturnObject(SecConfig t) {
                SecConfigRespDTO dto=new SecConfigRespDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
    }

}
