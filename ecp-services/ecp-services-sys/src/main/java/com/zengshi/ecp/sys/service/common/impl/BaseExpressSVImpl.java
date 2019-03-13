/** 
 * Project Name:ecp-services-sys 
 * File Name:BaseExpressSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.common.impl 
 * Date:2015-9-1下午5:01:26 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.common.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.sys.dao.mapper.common.BaseExpressMapper;
import com.zengshi.ecp.sys.dao.model.BaseExpress;
import com.zengshi.ecp.sys.dao.model.BaseExpressCriteria;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.util.EcpSysCodeConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IBaseExpressSV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2015-9-1下午5:01:26  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("baseExpressSV")
public class BaseExpressSVImpl extends GeneralSQLSVImpl implements IBaseExpressSV {
    
    @Resource
    private BaseExpressMapper baseExpressMapper;

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseExpressSV#listAllExpress() 
     */
    @Override
    public List<BaseExpress> listAllExpress() {
        BaseExpressCriteria criteria = new BaseExpressCriteria();
        criteria.setOrderByClause(" sort_no asc");
        return this.baseExpressMapper.selectByExample(criteria);
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseExpressSV#listActiveExpress() 
     */
    @Override
    public List<BaseExpress> listActiveExpress() {
        BaseExpressCriteria criteria = new BaseExpressCriteria();
        ///获取有效的物流地址信息；
        criteria.createCriteria().andStatusEqualTo("1");
        criteria.setOrderByClause(" sort_no asc");
        return this.baseExpressMapper.selectByExample(criteria);
        
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.common.interfaces.IBaseExpressSV#queryExpressById(long) 
     */
    @Override
    public BaseExpress queryExpressById(long id) {
        return this.baseExpressMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResponseDTO<BaseExpressRespDTO> listExpressByPage(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_FETCH_PARAM_NULL);
        }
        
        BaseExpressCriteria criteria = new BaseExpressCriteria();
        criteria.setLimitClauseStart(reqDto.getStartRowIndex());
        criteria.setLimitClauseCount(reqDto.getPageSize());
        criteria.setOrderByClause(" sort_no asc");
        if(StringUtils.isEmpty(reqDto.getStatus())){
            
        } else {
            criteria.createCriteria().andStatusEqualTo(reqDto.getStatus());
        }
        
        return super.queryByPagination(reqDto, criteria, false, new PaginationCallback<BaseExpress, BaseExpressRespDTO>(){

            @Override
            public List<BaseExpress> queryDB(BaseCriteria criteria) {
                return baseExpressMapper.selectByExample((BaseExpressCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return baseExpressMapper.countByExample((BaseExpressCriteria)criteria);
            }

            @Override
            public BaseExpressRespDTO warpReturnObject(BaseExpress t) {
                BaseExpressRespDTO dto = new BaseExpressRespDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, true);
                //编码转换展示；
                dto.setStatusCn(BaseParamUtil.fetchParamValue("PUBLIC_PARAM_STATUS", dto.getStatus()));
                return dto;
            }
            
        });
    }
    
    @Resource(name="seq_express_id")
    private PaasSequence seqExpressId;

    @Override
    public long createExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null || StringUtil.isEmpty(reqDto.getExpressFullName()) || StringUtil.isEmpty(reqDto.getExpressName())){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        BaseExpress express = new BaseExpress();
        long id = seqExpressId.nextValue();
        express.setId(id);
        express.setSortNo(new BigDecimal(id));
        express.setExpressFullName(reqDto.getExpressFullName());
        express.setExpressName(reqDto.getExpressName());
        express.setExpressWebsite(reqDto.getExpressWebsite());
        express.setStatus("1");
        this.baseExpressMapper.insert(express);
        return id;
    }

    @Override
    public long updateExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null || StringUtil.isEmpty(reqDto.getExpressFullName()) || StringUtil.isEmpty(reqDto.getExpressName())){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        BaseExpress express = this.baseExpressMapper.selectByPrimaryKey(reqDto.getId());
        if(express == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_UPDATE_NOEXISTS, new String[]{reqDto.getId()+""});
        }
        
        express.setExpressFullName(reqDto.getExpressFullName());
        express.setExpressName(reqDto.getExpressName());
        express.setExpressWebsite(reqDto.getExpressWebsite());
        
        return this.baseExpressMapper.updateByPrimaryKey(express);
    }

    @Override
    public long removeExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null ){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        BaseExpress express = this.baseExpressMapper.selectByPrimaryKey(reqDto.getId());
        if(express == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_UPDATE_NOEXISTS, new String[]{reqDto.getId()+""});
        }
        ///状态设置为失效；
        express.setStatus("0");
        return this.baseExpressMapper.updateByPrimaryKey(express);
    }

    @Override
    public long registExpress(BaseExpressReqDTO reqDto) throws BusinessException {
        if(reqDto == null ){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_SAVE_PARAM_NULL);
        }
        
        BaseExpress express = this.baseExpressMapper.selectByPrimaryKey(reqDto.getId());
        if(express == null){
            throw new BusinessException(EcpSysCodeConstants.SYS_EXPRESS_UPDATE_NOEXISTS, new String[]{reqDto.getId()+""});
        }
        ///状态设置为失效；
        express.setStatus("1");
        return this.baseExpressMapper.updateByPrimaryKey(express);
    }
    
    

}

