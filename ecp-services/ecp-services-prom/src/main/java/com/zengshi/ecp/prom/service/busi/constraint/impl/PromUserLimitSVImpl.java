package com.zengshi.ecp.prom.service.busi.constraint.impl;

import javax.annotation.Resource;


import com.zengshi.ecp.prom.dao.mapper.busi.PromUserLimitMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.manual.PromUserLimitManualMapper;
import com.zengshi.ecp.prom.dao.model.PromUserLimit;
import com.zengshi.ecp.prom.dao.model.PromUserLimitKey;
import com.zengshi.ecp.prom.dubbo.dto.PromConstraintDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromUserLimitDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromUserLimitSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-29 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromUserLimitSVImpl extends GeneralSQLSVImpl implements IPromUserLimitSV {

    private static final String MODULE = PromUserLimitSVImpl.class.getName();

    @Resource
    private PromUserLimitMapper promUserLimitMapper;

    @Resource
    private Converter<PromUserLimit, PromUserLimitDTO> promUserLimitDTOConverter;

    @Resource
    private Converter<PromUserLimitDTO, PromUserLimit> promUserLimitConverter;

    @Resource
    private IPromQuerySV promQuerySV;

    @Resource
    private PromUserLimitManualMapper promUserLimitManualMapper;
    
    @Resource
    private IPromInfoSV promInfoSV;

    /**
     * 查询
     * 
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromUserLimitDTO query(PromUserLimitDTO promUserLimitDTO) throws BusinessException {

        PromUserLimitKey key = new PromUserLimitKey();
        key.setLimitType(promUserLimitDTO.getLimitType());
        key.setLimitTypeValue(promUserLimitDTO.getLimitTypeValue());
        key.setPromId(promUserLimitDTO.getPromId());
        key.setStaffId(promUserLimitDTO.getStaffId());
        key.setOptValue(promUserLimitDTO.getOptValue());
        
        PromUserLimit p = promUserLimitMapper.selectByPrimaryKey(key);

        if (p == null) {
            return null;
        }
        return promUserLimitDTOConverter.convert(p);
    }

    /**
     * 新增 （订单调用  需要新增 和减少 配合使用）
     * 
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public void insert(PromUserLimitDTO promUserLimitDTO) throws BusinessException {

        PromConstraintDTO dto = promQuerySV.queryPromConstraint(promUserLimitDTO.getPromId());

        if(dto==null){
            return ;
        }
        if (!StringUtil.isEmpty(dto.getLimitTotalType())
                && !dto.getLimitTotalType().equals(PromConstants.PromConstraint.LIMITTOTALTYPE_0)) {
            // 数量验证
            promUserLimitDTO.setLimitType("2");
            promUserLimitDTO.setLimitTypeValue(dto.getLimitTotalType());
            promUserLimitDTO.setPromCntLimit(new Long(dto.getLimitTotalTypeValue()));
            promUserLimitDTO.setRemaindCnt(new Long(dto.getLimitTotalTypeValue()).longValue()-promUserLimitDTO.getBuyCnt());
            
            String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
            
            if(StringUtil.isNotEmpty(promUserLimitDTO.getOptValue())){
                optValue=promUserLimitDTO.getOptValue();
            }
            //天控制
            if(PromConstants.PromConstraint.LIMITTOTALTYPE_1.equals(dto.getLimitTotalType())){
                
            }
            //月控制
           if(PromConstants.PromConstraint.LIMITTOTALTYPE_2.equals(dto.getLimitTotalType())){
               optValue=optValue.substring(0, 6);
            }
           //年控制
           if(PromConstants.PromConstraint.LIMITTOTALTYPE_3.equals(dto.getLimitTotalType())){
               optValue=optValue.substring(0, 4);
           }
            promUserLimitDTO.setOptValue(optValue);
            
            //查询 是否存在值
            PromUserLimitDTO reValue=this.query(promUserLimitDTO);
            
            if(reValue!=null){
                // 做更新
                int re = 0;
                re = promUserLimitManualMapper.updateCntBySelective(promUserLimitConverter
                        .convert(promUserLimitDTO));
                if (re <= 0) {
                    //超过购买数量限制
                    String[] key=new String[1];
                    key[0]=dto.getLimitTotalTypeValue();
                    throw new BusinessException("prom.400094",key);
                }
            }else{
                try {
                    promUserLimitMapper.insert(promUserLimitConverter.convert(promUserLimitDTO));
                } catch (Exception ex) {
                    LogUtil.error(MODULE, "insert 报错啦", ex);
                    // 做更新
                    int re = 0;
                    re = promUserLimitManualMapper.updateCntBySelective(promUserLimitConverter
                            .convert(promUserLimitDTO));
                    if (re <= 0) {
                        //超过购买数量限制
                        String[] key=new String[1];
                        key[0]=dto.getLimitTotalTypeValue();
                        throw new BusinessException("prom.400094",key);
                    }
                }
            }
        }
        if (!StringUtil.isEmpty(dto.getLimitTimesType())
                && !dto.getLimitTimesType().equals(PromConstants.PromConstraint.LIMITTIMESTYPE_0)) {
            // 次数验证
            promUserLimitDTO.setBuyCnt(new Long(1));
            promUserLimitDTO.setLimitType("1");
            promUserLimitDTO.setLimitTypeValue(dto.getLimitTimesType());
            promUserLimitDTO.setPromCntLimit(new Long(dto.getLimitTimesTypeValue()));
            promUserLimitDTO.setRemaindCnt(new Long(dto.getLimitTimesTypeValue()).longValue()-promUserLimitDTO.getBuyCnt());
            
            String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
            
            if(StringUtil.isNotEmpty(promUserLimitDTO.getOptValue())){
                optValue=promUserLimitDTO.getOptValue();
            }
            
            //天控制
            if(PromConstants.PromConstraint.LIMITTIMESTYPE_1.equals(dto.getLimitTimesType())){
                
            }
            //月控制
           if(PromConstants.PromConstraint.LIMITTIMESTYPE_2.equals(dto.getLimitTimesType())){
               optValue=optValue.substring(0, 6);
            }
           //年控制
           if(PromConstants.PromConstraint.LIMITTIMESTYPE_3.equals(dto.getLimitTimesType())){
               optValue=optValue.substring(0, 4);
           }
            promUserLimitDTO.setOptValue(optValue);
            
            //查询 是否存在值
            PromUserLimitDTO reValue=this.query(promUserLimitDTO);
            
            if(reValue!=null){
                // 做更新
                int re = 0;
                re = promUserLimitManualMapper.updateCntBySelective(promUserLimitConverter
                        .convert(promUserLimitDTO));
                if (re <= 0) {
                    //超过购买次数限制
                    String[] key=new String[1];
                    key[0]=dto.getLimitTimesTypeValue();
                    throw new BusinessException("prom.400093",key);
                }
            }else{
                try {
                    promUserLimitMapper.insert(promUserLimitConverter.convert(promUserLimitDTO));
                } catch (Exception ex) {
                    LogUtil.error(MODULE, "insert 报错啦", ex);
                    // 做更新
                    int re = 0;
                    re = promUserLimitManualMapper.updateCntBySelective(promUserLimitConverter
                            .convert(promUserLimitDTO));
                    if (re <= 0) {
                        //超过购买次数限制
                        String[] key=new String[1];
                        key[0]=dto.getLimitTimesTypeValue();
                        throw new BusinessException("prom.400093",key);
                    }
                }
            }
        }
    }
    /**
     * 减少(补偿机制) 下单扣减成功后 其他原因导致报错 需要补偿机制
     * @param promUserLimitDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public int update(PromUserLimitDTO promUserLimitDTO)
            throws BusinessException{
        
        PromConstraintDTO dto = promQuerySV.queryPromConstraint(promUserLimitDTO.getPromId());
        int re = 0;
        
        if(dto==null){
            return re;
        }
        if (!StringUtil.isEmpty(dto.getLimitTotalType())
                && !dto.getLimitTotalType().equals(PromConstants.PromConstraint.LIMITTOTALTYPE_0)) {
            promUserLimitDTO.setLimitType("2");
            promUserLimitDTO.setLimitTypeValue(dto.getLimitTotalType());
            promUserLimitDTO.setPromCntLimit(new Long(dto.getLimitTotalTypeValue()));
            promUserLimitDTO.setRemaindCnt(new Long(dto.getLimitTotalTypeValue()));
            
            String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
            
            if(StringUtil.isNotEmpty(promUserLimitDTO.getOptValue())){
                optValue=promUserLimitDTO.getOptValue();
            }
            
            //天控制
            if(PromConstants.PromConstraint.LIMITTOTALTYPE_1.equals(dto.getLimitTotalType())){
                
            }
            //月控制
           if(PromConstants.PromConstraint.LIMITTOTALTYPE_2.equals(dto.getLimitTotalType())){
               optValue=optValue.substring(0, 6);
            }
           //年控制
           if(PromConstants.PromConstraint.LIMITTOTALTYPE_3.equals(dto.getLimitTotalType())){
               optValue=optValue.substring(0, 4);
           }
            promUserLimitDTO.setOptValue(optValue);
            
            // 做更新
            re = promUserLimitManualMapper.addCntBySelective(promUserLimitConverter
                    .convert(promUserLimitDTO));
            if (re <= 0) {
                //超过数量限制
                throw new BusinessException("prom.400095");
            }
        }

        if (!StringUtil.isEmpty(dto.getLimitTimesType())
                && !dto.getLimitTimesType().equals(PromConstants.PromConstraint.LIMITTIMESTYPE_0)) {
            promUserLimitDTO.setBuyCnt(new Long(1));
            promUserLimitDTO.setLimitType("1");
            promUserLimitDTO.setLimitTypeValue(dto.getLimitTimesType());
            promUserLimitDTO.setPromCntLimit(new Long(dto.getLimitTimesTypeValue()));
            promUserLimitDTO.setRemaindCnt(new Long(dto.getLimitTimesTypeValue()));
            

            String optValue=DateUtil.getDateString(DateUtil.YYYYMMDD);
            
            if(StringUtil.isNotEmpty(promUserLimitDTO.getOptValue())){
                optValue=promUserLimitDTO.getOptValue();
            }
            //天控制
            if(PromConstants.PromConstraint.LIMITTIMESTYPE_1.equals(dto.getLimitTimesType())){
                
            }
            //月控制
           if(PromConstants.PromConstraint.LIMITTIMESTYPE_2.equals(dto.getLimitTimesType())){
               optValue=optValue.substring(0, 6);
            }
           //年控制
           if(PromConstants.PromConstraint.LIMITTIMESTYPE_3.equals(dto.getLimitTimesType())){
               optValue=optValue.substring(0, 4);
           }
            promUserLimitDTO.setOptValue(optValue);
            // 做更新
           
            re = promUserLimitManualMapper.addCntBySelective(promUserLimitConverter
                    .convert(promUserLimitDTO));
            if (re <= 0) {
                //超过数量限制
                throw new BusinessException("prom.400095");
            }
        }
       return re;
    }
}
