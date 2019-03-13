package com.zengshi.ecp.search.service.common.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dao.mapper.common.SecFieldMapper;
import com.zengshi.ecp.search.dao.model.SecField;
import com.zengshi.ecp.search.dao.model.SecFieldCriteria;
import com.zengshi.ecp.search.dao.model.SecFieldCriteria.Criteria;
import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.BeanTransfermationUtils;
import com.zengshi.ecp.search.dubbo.util.SearchMessageKeyContants;
import com.zengshi.ecp.search.index.util.SolrUtils;
import com.zengshi.ecp.search.service.common.interfaces.ISecFieldSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-search <br>
 * Description: <br>
 * Date:2015年8月11日下午5:01:40 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangdf
 * @version
 * @since JDK 1.6
 */
public class SecFieldSVImpl extends GeneralSQLSVImpl implements ISecFieldSV {

    @Autowired
    private SecFieldMapper secFieldMapper;

    @Resource(name = "seq_sec_field")
    private Sequence seqSecField;

    @Override
    public long saveSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecField secField = new SecField();
        BeanTransfermationUtils.dto2bo(secField, secFieldReqDTO);

        // 补值操作
        long id = this.seqSecField.nextValue();
        secField.setId(id);
        secField.setStatus(SearchConstants.STATUS_VALID);
        
        secField.setFieldIndexName(SolrUtils.generateSolrFieldName(secFieldReqDTO));
        
        //字段初始化顺序，配置了字段扩展处理器的需要设置大值，保证在最后被初始化，防止扩展返回数据被污染。默认值为1，最前初始化。
        if(StringUtils.isNotBlank(secFieldReqDTO.getFieldProcessorName())){
            secField.setFieldInitSort((short)10);
        }else{
            secField.setFieldInitSort((short)1);
        }

        // 记录创建和修改信息的补值操作
        Timestamp t=DateUtil.getSysDate();
        secField.setCreateStaff(secFieldReqDTO.getStaff().getId());
        secField.setCreateTime(t);
        secField.setUpdateStaff(secFieldReqDTO.getStaff().getId());
        secField.setUpdateTime(t);

        this.secFieldMapper.insert(secField);

        return id;

    }

    @Override
    public void deleteSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException {

        // 逻辑删除
        SecField secField = new SecField();
        secField.setId(secFieldReqDTO.getId());
        secField.setStatus(SearchConstants.STATUS_INVALID);

        // 记录创建和修改信息的补值操作
        secField.setUpdateStaff(secFieldReqDTO.getStaff().getId());
        secField.setUpdateTime(DateUtil.getSysDate());
        
        this.secFieldMapper.updateByPrimaryKeySelective(secField);

    }

    @Override
    public void updateSecField(SecFieldReqDTO secFieldReqDTO) throws BusinessException {

        // DTO转BO,同名属性自动赋值
        SecField secField = new SecField();
        BeanTransfermationUtils.dto2bo(secField, secFieldReqDTO);
        
        secField.setFieldIndexName(SolrUtils.generateSolrFieldName(secFieldReqDTO));
        
        //字段初始化顺序，配置了字段扩展处理器的需要设置大值，保证在最后被初始化，防止扩展返回数据被污染。默认值为1，最前初始化。
        if(StringUtils.isNotBlank(secFieldReqDTO.getFieldProcessorName())){
            secField.setFieldInitSort((short)10);
        }else{
            secField.setFieldInitSort((short)1);
        }

        // 记录创建和修改信息的补值操作
        secField.setUpdateStaff(secFieldReqDTO.getStaff().getId());
        secField.setUpdateTime(DateUtil.getSysDate());

        this.secFieldMapper.updateByPrimaryKey(secField);

    }

    @Override
    public SecField querySecFieldById(SecFieldReqDTO secFieldReqDTO) throws BusinessException {

        SecField secField = this.secFieldMapper.selectByPrimaryKey(secFieldReqDTO.getId());
        if (secField == null) {
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secFieldReqDTO.getId()});
        }
        
        if(StringUtils.equals(secFieldReqDTO.getStatus(), SearchConstants.STATUS_VALID)
                &&StringUtils.equals(secField.getStatus(), SearchConstants.STATUS_INVALID)){
            throw new BusinessException(SearchMessageKeyContants.Info.KEY_INFO_ROW_NOTFOUND,new String[]{""+secFieldReqDTO.getId()});
        }
        
        return secField;

    }

    @Override
    public List<SecField> querySecFieldByObjectId(SecFieldReqDTO secFieldReqDTO) throws BusinessException {

        SecFieldCriteria criteria = new SecFieldCriteria();
        criteria.setOrderByClause("FIELD_INIT_SORT asc");
        
        Criteria cr = criteria.createCriteria();
        cr.andObjectIdEqualTo(secFieldReqDTO.getObjectId());

        if(StringUtils.equals(secFieldReqDTO.getStatus(), SearchConstants.STATUS_VALID)){
            cr.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }

        List<SecField> secFieldList = this.secFieldMapper.selectByExample(criteria);

        return secFieldList;

    }
    
    @Override
    public void deleteSecFieldByObjectId(SecFieldReqDTO secFieldReqDTO) throws BusinessException {
        
        if (secFieldReqDTO.getObjectId() == null || secFieldReqDTO.getObjectId() == 0) {
            throw new BusinessException(SearchMessageKeyContants.Error.KEY_ERROR_FIELD_DELETE,new String[]{"对象Id没设置值"});
        }

        SecFieldCriteria criteria = new SecFieldCriteria();
        
        Criteria cr = criteria.createCriteria();
        cr.andObjectIdEqualTo(secFieldReqDTO.getObjectId());

        //逻辑删除
        SecField secField=new SecField();
        secField.setStatus(SearchConstants.STATUS_INVALID);
     
        // 记录创建和修改信息的补值操作
        secField.setUpdateStaff(secFieldReqDTO.getStaff().getId());
        secField.setUpdateTime(DateUtil.getSysDate());

        this.secFieldMapper.updateByExampleSelective(secField, criteria);

    }

}
