package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecConfig2ObjectMapper;
import com.zengshi.ecp.search.dao.model.SecConfig2Object;
import com.zengshi.ecp.search.dao.model.SecConfig2ObjectCriteria;
import com.zengshi.ecp.search.dao.model.SecConfig2ObjectCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectRespDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.service.common.interfaces.ISecConfig2ObjectSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:27 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecConfig2ObjectSVImpl extends GeneralSQLSVImpl implements ISecConfig2ObjectSV {

    @Autowired
    private SecConfig2ObjectMapper secConfig2ObjectMapper;

    @Override
    public void saveSecConfig2Object(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO)
            throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecConfig2Object secConfig2Object = new SecConfig2Object();
        BeanTransfermationUtils.dto2bo(secConfig2Object, secConfig2ObjectReqDTO);

        // 补值操作
        secConfig2Object.setStatus(SearchConstants.STATUS_VALID);

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secConfig2Object.setCreateStaff(secConfig2ObjectReqDTO.getStaff().getId());
        secConfig2Object.setCreateTime(t);
        secConfig2Object.setUpdateStaff(secConfig2ObjectReqDTO.getStaff().getId());
        secConfig2Object.setUpdateTime(t);

        this.secConfig2ObjectMapper.insert(secConfig2Object);

    }

    @Override
    public void deleteSecConfig2ObjectBySecConfigId(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException {

        if (secConfig2ObjectReqDTO.getConfigId() == null || secConfig2ObjectReqDTO.getConfigId() == 0) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_CONFIG_DELETE,new String[]{"索引配置Id未设置值！"});
        }

        SecConfig2ObjectCriteria criteria = new SecConfig2ObjectCriteria();
        
        Criteria cr = criteria.createCriteria();
        cr.andConfigIdEqualTo(secConfig2ObjectReqDTO.getConfigId());
        
        cr.andStatusEqualTo(SearchConstants.STATUS_VALID);

        //逻辑删除
        SecConfig2Object secConfig2Object=new SecConfig2Object();
        secConfig2Object.setStatus(SearchConstants.STATUS_INVALID);
     
        // 记录创建和修改信息的补值操作
        secConfig2Object.setUpdateStaff(secConfig2ObjectReqDTO.getStaff().getId());
        secConfig2Object.setUpdateTime(DateUtil.getSysDate());

        this.secConfig2ObjectMapper.updateByExample(secConfig2Object, criteria);

    }

    @Override
    public void deleteSecConfig2Object(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException {
        
        if (secConfig2ObjectReqDTO.getConfigId()== null ||secConfig2ObjectReqDTO.getConfigId() == 0
                ||secConfig2ObjectReqDTO.getObjectId()== null ||secConfig2ObjectReqDTO.getObjectId() == 0){
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_CONFIG_DELETE,new String[]{"索引配置Id或数据对象Id未设置值！"});
        }

        // 逻辑删除
        SecConfig2Object secConfig2Object = new SecConfig2Object();
        secConfig2Object.setConfigId(secConfig2ObjectReqDTO.getConfigId());
        secConfig2Object.setObjectId(secConfig2ObjectReqDTO.getObjectId());
        secConfig2Object.setStatus(SearchConstants.STATUS_INVALID);

        // 记录创建和修改信息的补值操作
        secConfig2Object.setUpdateStaff(secConfig2ObjectReqDTO.getStaff().getId());
        secConfig2Object.setUpdateTime(DateUtil.getSysDate());
        
        this.secConfig2ObjectMapper.updateByPrimaryKeySelective(secConfig2Object);

    }

    @Override
    public List<SecConfig2Object> querySecConfig2ObjectByConfigId(SecConfig2ObjectReqDTO secConfig2ObjectReqDTO)
            throws BusinessException {
        SecConfig2ObjectCriteria criteria = new SecConfig2ObjectCriteria();
        criteria.setOrderByClause( "object_id asc");
        
        Criteria cr = criteria.createCriteria();
        cr.andConfigIdEqualTo(secConfig2ObjectReqDTO.getConfigId());

        if(StringUtils.equals(secConfig2ObjectReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecConfig2Object> secConfigList = this.secConfig2ObjectMapper
                .selectByExample(criteria);

        return secConfigList;

    }
    
    @Override
    public PageResponseDTO<SecConfig2ObjectRespDTO> querySecConfig2ObjectPage(
            SecConfig2ObjectReqDTO secConfig2ObjectReqDTO) throws BusinessException {
        SecConfig2ObjectCriteria dcriteria=new SecConfig2ObjectCriteria();
        dcriteria.setLimitClauseCount(secConfig2ObjectReqDTO.getPageSize());
        dcriteria.setLimitClauseStart(secConfig2ObjectReqDTO.getStartRowIndex());
        dcriteria.setOrderByClause("object_id asc");
        
        Criteria criteria=dcriteria.createCriteria();
        
        if(StringUtils.equals(secConfig2ObjectReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
        
        if (secConfig2ObjectReqDTO.getConfigId()!= null&&secConfig2ObjectReqDTO.getConfigId() != 0){
            criteria.andConfigIdEqualTo(secConfig2ObjectReqDTO.getConfigId());
        }
        
        if(StringUtils.isNotBlank(secConfig2ObjectReqDTO.getObjectType())){
            criteria.andObjectTypeEqualTo(secConfig2ObjectReqDTO.getObjectType());
        }
        
        return super.queryByPagination(secConfig2ObjectReqDTO, dcriteria,false, new PaginationCallback<SecConfig2Object, SecConfig2ObjectRespDTO>() {
            
            //查询记录集
            @Override
            public List<SecConfig2Object> queryDB(BaseCriteria criteria) {
                
                return secConfig2ObjectMapper.selectByExample((SecConfig2ObjectCriteria) criteria);
            }
            
            //查询总记录数
            @Override
            public long queryTotal(BaseCriteria criteria) {
                
                return secConfig2ObjectMapper.countByExample((SecConfig2ObjectCriteria)criteria);
            }

            //查询结果转换
            @Override
            public SecConfig2ObjectRespDTO warpReturnObject(SecConfig2Object t) {
                SecConfig2ObjectRespDTO dto=new SecConfig2ObjectRespDTO();
                BeanUtils.copyProperties(t, dto);
                return dto;
            }
        });
    }

}
