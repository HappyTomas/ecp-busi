package com.zengshi.ecp.prom.service.busi.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.prom.dao.mapper.busi.PromOrdPresentMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromOrdRelMapper;
import com.zengshi.ecp.prom.dao.model.PromOrdPresent;
import com.zengshi.ecp.prom.dao.model.PromOrdPresentCriteria;
import com.zengshi.ecp.prom.dao.model.PromOrdRel;
import com.zengshi.ecp.prom.dao.model.PromOrdRelCriteria;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDetailDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGiftDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromPresentDTO;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromToOrderRelSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

@Service
public class PromToOrderRelSVImpl implements IPromToOrderRelSV {
    
    private static final String MODULE = PromToOrderRelSVImpl.class.getName();
    
    private static final String ACTIVE_STATUS = "1";//创建
    private static final String CANCEL_STATUS = "9";//取消

    //类型 1积分 2赠品 3优惠券
    private static final String SCORE_TYPE_CODE = "1";
    private static final String GIFT_TYPE_CODE = "2";
    private static final String COUP_TYPE_CODE = "3";

    
    @Resource
    private IPromQuerySV promQuerySV;
    
    @Resource
    private PromOrdPresentMapper promOrdPresentMapper;
    
    @Resource
    private PromOrdRelMapper promOrdRelMapper;
    
    @Resource(name = "seq_prom_to_ord_id")
    private PaasSequence seq_prom_to_ord_id;
    
    @Resource(name = "seq_prom_to_ord_present_id")
    private PaasSequence seq_prom_to_ord_present_id;  
    
    @Override
    public int saveRelation(OrdPromDTO ordPromDTO) throws BusinessException{
        
        if(ordPromDTO == null)
        {
            LogUtil.info(MODULE, "方法saveRelation(OrdPromDTO ordPromDTO)参数为null！");
            return 0;
        }
        
        //1.保存订单级别的促销赠送关系
        if(ordPromDTO.getPromInfoDTO() != null && ordPromDTO.getPromInfoDTO().getId()!=null)
        {
            PromPresentDTO orderPresent = promQuerySV.promPresent(ordPromDTO.getPromInfoDTO().getId(), ordPromDTO);
            //主订单保存促销订单表（T_PROM_ORD）关系记录
            Long relid = saveTPromOrdRel(ordPromDTO);
            //1.积分
            saveScoreRel(relid, ordPromDTO.getShopId(), ordPromDTO.getOrderId(), null, orderPresent); 
            //2.优惠券
            saveCoupRel(relid, ordPromDTO.getShopId(), ordPromDTO.getOrderId(), null, orderPresent);
            //3.赠品
            saveGiftRel(relid, ordPromDTO.getShopId(), ordPromDTO.getOrderId(), null, orderPresent);
        }
        //2.保存子订单级别的促销赠送关系
        if(!CollectionUtils.isEmpty(ordPromDTO.getOrdPromDetailDTOList()) ){ 
            Set<Long> giftIDs = new HashSet<Long>();//记录过赠品赠送记录的promid集合
            giftIDs.clear();
            
            for(OrdPromDetailDTO dto:ordPromDTO.getOrdPromDetailDTOList()){
                if(dto.getPromInfoDTO()!=null && dto.getPromInfoDTO().getId()!=null){
                    PromPresentDTO subOrderPresent = promQuerySV.promPresent(dto.getPromInfoDTO().getId(), ordPromDTO);
                    
                    //子订单保存促销订单表（T_PROM_ORD）关系记录
                    Long relid = saveTPromOrdRel(dto);
                    //1.积分
                    saveScoreRel(relid, dto.getShopId(), dto.getOrderId(), dto.getSubOrderId(), subOrderPresent);   
                    //2.优惠券
                    saveCoupRel(relid, dto.getShopId(), dto.getOrderId(), dto.getSubOrderId(), subOrderPresent);
                    //3.赠品
                    //3.2如果该促销的赠品赠送未记录，则记录
                    if(!giftIDs.contains(dto.getPromInfoDTO().getId()))
                    {
                        saveGiftRel(relid, dto.getShopId(), dto.getOrderId(), dto.getSubOrderId(), subOrderPresent);
                        giftIDs.add(dto.getPromInfoDTO().getId());
                    }
                }
            }
        }
        
        return 0;
    }

    @Override
    public int deleteRelation(OrdPromDTO ordPromDTO) throws BusinessException{
        
        //1.根据主订单id将促销订单表（T_PROM_ORD）、促销订单赠送表（T_PROM_ORD_PRESENT）记录失效掉
        if(ordPromDTO == null || ordPromDTO.getOrderId() == null)
            return 0;
        PromOrdRel promOrdRel = new PromOrdRel();
        promOrdRel.setStatus(CANCEL_STATUS);
        
        PromOrdRelCriteria criteria = new PromOrdRelCriteria();
        criteria.createCriteria().andOrderIdEqualTo(ordPromDTO.getOrderId()).andShopIdEqualTo(ordPromDTO.getShopId());
        promOrdRelMapper.updateByExampleSelective(promOrdRel, criteria);
        
        PromOrdPresent promOrdPresent = new PromOrdPresent();
        promOrdPresent.setStatus(CANCEL_STATUS);

        PromOrdPresentCriteria criteria2 = new PromOrdPresentCriteria();
        criteria2.createCriteria().andOrderIdEqualTo(ordPromDTO.getOrderId()).andShopIdEqualTo(ordPromDTO.getShopId());
        promOrdPresentMapper.updateByExampleSelective(promOrdPresent, criteria2);
        return 0;
    }
    private Long saveTPromOrdRel(OrdPromDTO ordPromDTO){
        
        if(ordPromDTO == null || ordPromDTO.getPromInfoDTO() == null)
        {
            return 0L;
        }
        
        PromOrdRel promOrdRel = new PromOrdRel();
        promOrdRel.setId(seq_prom_to_ord_id.nextValue());
        
        promOrdRel.setPromId(ordPromDTO.getPromInfoDTO().getId());
        promOrdRel.setShopId(ordPromDTO.getShopId());
        promOrdRel.setOrderId(ordPromDTO.getOrderId());
        promOrdRel.setStatus(ACTIVE_STATUS);
        promOrdRel.setCreateStaff(ordPromDTO.getStaff().getId());
        promOrdRel.setCreateTime(DateUtil.getSysDate());
        promOrdRel.setUpdateStaff(ordPromDTO.getStaff().getId());
        promOrdRel.setUpdateTime(DateUtil.getSysDate());
        
        promOrdRelMapper.insertSelective(promOrdRel);

        return promOrdRel.getId();
        
    }
    private Long saveTPromOrdRel(OrdPromDetailDTO ordPromDetailDTO){
        if(ordPromDetailDTO == null || ordPromDetailDTO.getPromInfoDTO() == null)
        {
            return 0L;
        }
        
        PromOrdRel promOrdRel = new PromOrdRel();
        promOrdRel.setId(seq_prom_to_ord_id.nextValue());
        
        promOrdRel.setPromId(ordPromDetailDTO.getPromInfoDTO().getId());
        promOrdRel.setShopId(ordPromDetailDTO.getShopId());
        promOrdRel.setOrderId(ordPromDetailDTO.getOrderId());
        promOrdRel.setOrderSubId(ordPromDetailDTO.getSubOrderId());
        promOrdRel.setRelaMainPromId(ordPromDetailDTO.getOrdPromId());
        promOrdRel.setStatus(ACTIVE_STATUS);
        promOrdRel.setCreateStaff(ordPromDetailDTO.getStaff().getId());
        promOrdRel.setCreateTime(DateUtil.getSysDate());
        promOrdRel.setUpdateStaff(ordPromDetailDTO.getStaff().getId());
        promOrdRel.setUpdateTime(DateUtil.getSysDate());

        promOrdRelMapper.insertSelective(promOrdRel);
        
        return promOrdRel.getId();
        
    }
    private void saveScoreRel(Long relid, Long shopId, String orderId, String ordSubId, PromPresentDTO present){
        if(present == null || present.getPoints() == null || present.getPoints().compareTo(BigDecimal.ZERO) <= 0)
        {
            return ;
        }
        PromOrdPresent promOrdPresent = new PromOrdPresent();
        promOrdPresent.setId(seq_prom_to_ord_present_id.nextValue());
        promOrdPresent.setRelaId(relid);
        
        promOrdPresent.setShopId(shopId);
        promOrdPresent.setOrderId(orderId);
        promOrdPresent.setOrdSubId(ordSubId);
        promOrdPresent.setPromId(present.getPromId());
        promOrdPresent.setStatus(ACTIVE_STATUS);
        promOrdPresent.setTypeCode(SCORE_TYPE_CODE);
        promOrdPresent.setPresentValue1(String.valueOf(present.getPoints().toString()));
        promOrdPresent.setPresentValue2(present.getSendType());
        promOrdPresent.setRemark("积分");
        promOrdPresent.setCreateStaff(present.getStaff().getId());
        promOrdPresent.setCreateTime(DateUtil.getSysDate());
        promOrdPresent.setUpdateStaff(present.getStaff().getId());
        promOrdPresent.setUpdateTime(DateUtil.getSysDate());
        
        promOrdPresentMapper.insertSelective(promOrdPresent);

    }
    private void saveCoupRel(Long relid, Long shopId, String orderId, String ordSubId, PromPresentDTO present){
        if(present == null || StringUtil.isBlank(present.getCoupId()))
        {
            return ;
        }
        PromOrdPresent promOrdPresent = new PromOrdPresent();
        promOrdPresent.setId(seq_prom_to_ord_present_id.nextValue());
        promOrdPresent.setRelaId(relid);
        
        promOrdPresent.setShopId(shopId);
        promOrdPresent.setOrderId(orderId);
        promOrdPresent.setOrdSubId(ordSubId);
        promOrdPresent.setPromId(present.getPromId());
        promOrdPresent.setStatus(ACTIVE_STATUS);
        promOrdPresent.setTypeCode(COUP_TYPE_CODE);
        promOrdPresent.setPresentValue1(present.getCoupId());
        promOrdPresent.setPresentValue2(String.valueOf(present.getSendAmount().toString()));
        promOrdPresent.setRemark("优惠券");
        promOrdPresent.setCreateStaff(present.getStaff().getId());
        promOrdPresent.setCreateTime(DateUtil.getSysDate());
        promOrdPresent.setUpdateStaff(present.getStaff().getId());
        promOrdPresent.setUpdateTime(DateUtil.getSysDate());
        
        promOrdPresentMapper.insertSelective(promOrdPresent);
    }

    private void saveGiftRel(Long relid, Long shopId, String orderId, String ordSubId, PromPresentDTO present){
        if(present == null || CollectionUtils.isEmpty(present.getPromGiftDTOList()))
        {
            return ;
        }
        for(PromGiftDTO gift: present.getPromGiftDTOList())
        {
            PromOrdPresent promOrdPresent = new PromOrdPresent();
            promOrdPresent.setId(seq_prom_to_ord_present_id.nextValue());
            promOrdPresent.setRelaId(relid);
    
            promOrdPresent.setShopId(shopId);
            promOrdPresent.setOrderId(orderId);
            promOrdPresent.setOrdSubId(ordSubId);
            promOrdPresent.setPromId(present.getPromId());
            promOrdPresent.setStatus(ACTIVE_STATUS);
            promOrdPresent.setTypeCode(GIFT_TYPE_CODE);
            promOrdPresent.setPresentValue1(String.valueOf(gift.getGiftId()));
            promOrdPresent.setPresentValue2(String.valueOf(gift.getEveryTimeCnt()));
            promOrdPresent.setRemark("赠品");
            promOrdPresent.setCreateStaff(present.getStaff().getId());
            promOrdPresent.setCreateTime(DateUtil.getSysDate());
            promOrdPresent.setUpdateStaff(present.getStaff().getId());
            promOrdPresent.setUpdateTime(DateUtil.getSysDate());
            
            promOrdPresentMapper.insertSelective(promOrdPresent);
        }

    }

    @Override
    public boolean isRollBack(OrdPromDTO ordPromDTO) throws BusinessException {
        
        if(ordPromDTO == null || ordPromDTO.getShopId() ==null || ordPromDTO.getOrderId() == null)
        {
            return false;
        }
        
        PromOrdRelCriteria criteria = new PromOrdRelCriteria();
        criteria.createCriteria().andShopIdEqualTo(ordPromDTO.getShopId()).andOrderIdEqualTo(ordPromDTO.getOrderId());
        
        List<PromOrdRel> rsList = promOrdRelMapper.selectByExample(criteria);
        
        if(!CollectionUtils.isEmpty(rsList))
        {
            return CANCEL_STATUS.equals(rsList.get(0).getStatus())?true:false;
        }
        
        return false;
    }

}

