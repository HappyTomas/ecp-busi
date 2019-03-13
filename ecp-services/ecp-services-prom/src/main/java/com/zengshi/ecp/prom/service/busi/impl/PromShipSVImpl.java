package com.zengshi.ecp.prom.service.busi.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.prom.dubbo.dto.OrdPromDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCalRuleDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromShipRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.constraint.interfaces.IPromConstraintSV;
import com.zengshi.ecp.prom.service.busi.discountRule.interfaces.IPromDiscountRuleSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromQuerySV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromShipSV;
import com.zengshi.ecp.prom.service.common.interfaces.IPromTypeSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.paas.utils.DateUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-31 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromShipSVImpl extends GeneralSQLSVImpl implements IPromShipSV {

    private static final String MODULE = PromShipSVImpl.class.getName();
 
    @Resource
    private IPromInfoSV promInfoSV;
    
    @Resource
    private IPromConstraintSV promConstraintSV;
    
    @Resource
    private IPromTypeSV promTypeSV;
    
    @Resource
    private IPromQuerySV promQuerySV;
    /**
     * 验证是否 免邮
     * 1 免邮
     * 非1 不免邮 
     * @param promShipDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromShipRespDTO qryPromShip(PromShipDTO promShipDTO) throws BusinessException{
        
        //1、查询有效的配置促销  有效时间内 指定的没有1000000016类型促销     店铺编码+促销类型+状态+有效时间 查询列表。
        //2、循环列表验证是否满足配置规则   促销规则+金额+区域验证
        PromShipRespDTO  dto=new PromShipRespDTO();
        dto.setMsgCode(PromConstants.PromSys.MSG_CODE_ERROR);
        
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        promInfoDTO.setIfShow(PromConstants.PromType.IF_SHOW_0);
        promInfoDTO.setShopId(promShipDTO.getShopId());
        promInfoDTO.setStatus(PromConstants.PromInfo.STATUS_10);
        promInfoDTO.setEndTime(DateUtil.getSysDate());
        promInfoDTO.setSiteId(promShipDTO.getCurrentSiteId());
        //免邮固定类型
        promInfoDTO.setPromTypeCode(PromConstants.PromType.PROM_TYPE_CODE_1000000016);
       
        //查询促销列表
        List<PromInfoResponseDTO> promInfoRespList = promInfoSV.listPromInfo(promInfoDTO);

        // 没有任何促销信息 return;
        if (CollectionUtils.isEmpty(promInfoRespList)) {
            dto.setMsgDesc("没有任何促销信息哦");
            return dto;
        }
        

        PromRuleCheckDTO  promRuleCheckDTO=new PromRuleCheckDTO();
        
        promRuleCheckDTO.setShopId(promShipDTO.getShopId());
        promRuleCheckDTO.setCalDate(DateUtil.getSysDate());
        promRuleCheckDTO.setSiteId(promShipDTO.getCurrentSiteId());  
        promRuleCheckDTO.setStaffId(String.valueOf(promShipDTO.getStaff().getId()));
        //promRuleCheckDTO.setAreaValue(promShipDTO.getAreaCode());
        
        OrdPromDTO ordPromDTO=new OrdPromDTO();
        ordPromDTO.setOrderMoney(promShipDTO.getMoney());
        ordPromDTO.setPromRuleCheckDTO(promRuleCheckDTO);
        
        //获得促销类型
        PromTypeResponseDTO promTypeResponseDTO = promTypeSV
                .queryPromTypeByCode(promInfoDTO.getPromTypeCode());
        
        //如果没有配置促销类型
        if (promTypeResponseDTO == null) {
            dto.setMsgDesc(promInfoDTO.getPromTypeCode()+"居然没有找到促销类型配置信息。赶紧看数据库或缓存哦");
            return dto;
        }
        //服务id
        String serviceId = promTypeResponseDTO.getServiceId();
        //获得ClassPathXmlApplicationContext context bean
        IPromDiscountRuleSV iPromDiscountRuleService = EcpFrameContextHolder.getBean(serviceId, IPromDiscountRuleSV.class);
        
        for (PromInfoResponseDTO promInfoResponseDTO : promInfoRespList) {
    
            //获得计算规则 json值
            String constraintStr=null;
            Timestamp curDate = DateUtil.getSysDate();
            //在有效期内
            if(curDate.after(promInfoResponseDTO.getStartTime())&&curDate.before(promInfoResponseDTO.getEndTime())){
                PromCalRuleDTO promCalRuleDTO=promQuerySV.queryPromCalRule(promInfoResponseDTO.getId());
                if(promCalRuleDTO!=null){
                    constraintStr=promCalRuleDTO.getCalStr();
                }  
            

             //验证是否免邮
             if(iPromDiscountRuleService.ifFreePost(ordPromDTO, null, constraintStr, "0",promShipDTO)){
                 dto.setMsgCode(PromConstants.PromSys.MSG_CODE_SUCCESS);
                 dto.setMsgDesc("促销编码 ="+promInfoResponseDTO.getId()+"满足免邮要求哦");
                 break;
             }else{
                 dto.setMsgDesc("怎么这么倒霉呢。一共"+promInfoRespList.size()+" 笔促销，居然一个都没有满足。");
             }
            }
        }
        return dto;
    }
}
