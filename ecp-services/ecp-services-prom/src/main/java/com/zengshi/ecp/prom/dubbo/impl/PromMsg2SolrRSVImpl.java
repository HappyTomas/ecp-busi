package com.zengshi.ecp.prom.dubbo.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.general.prom.dto.GdsPromDTO;
import com.zengshi.ecp.general.prom.interfaces.IPromMsg2SolrRSV;
import com.zengshi.ecp.general.prom.util.PromMsgUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.prom.dubbo.dto.PromListRespDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromRuleCheckDTO;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-13 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromMsg2SolrRSVImpl implements IPromMsg2SolrRSV {
    
    private static final String MODULE = PromMsg2SolrRSVImpl.class.getName();
    
    @Resource
    private IPromSV promSV;
    

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    /**
     * TODO根据gdsId skuId shopId 获得参与促销列表，并发送消息到solr
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#sendMsg2SolrByNewSku(com.zengshi.ecp.prom.dubbo.dto.QueryPromDTO)
     * @param queryPromDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void sendMsg2SolrByNewSku(GdsPromDTO gdsPromDTO) throws BusinessException{
        //1获得skuId 参与促销列表
        //2 发送消息到solr
        // 参数验证 必填
        if(gdsPromDTO==null){
            throw new BusinessException("prom.400086");
        }
        if (gdsPromDTO.getShopId() == null) {
            // prom.400069 = 店铺编码 不能为空！
            throw new BusinessException("prom.400069");
        }
        if (gdsPromDTO.getGdsId() == null) {
            // prom.400070 = 商品编码 不能为空！
            throw new BusinessException("prom.400070");
        }
        gdsPromDTO.setCalDate(DateUtil.getSysDate());
        PromRuleCheckDTO promRuleCheckDTO=new PromRuleCheckDTO();
        ObjectCopyUtil.copyObjValue(gdsPromDTO, promRuleCheckDTO, "", false);
        
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = new GdsSkuInfoReqDTO();
        gdsSkuInfoReqDTO.setId(Long.valueOf(promRuleCheckDTO.getSkuId()));
        SkuQueryOption[] skuQuery = { SkuQueryOption.BASIC, SkuQueryOption.CAlDISCOUNT };
        gdsSkuInfoReqDTO.setSkuQuery(skuQuery);
        GdsSkuInfoRespDTO gdsSkuInforespDTO = new GdsSkuInfoRespDTO();
        gdsSkuInforespDTO = gdsSkuInfoQueryRSV.querySkuInfoByOptions(gdsSkuInfoReqDTO);
        
        promRuleCheckDTO.setBasePrice(gdsSkuInforespDTO.getRealPrice());
        promRuleCheckDTO.setBuyPrice(gdsSkuInforespDTO.getDiscountPrice());
        promRuleCheckDTO.setBuyCnt("1");
        
      //1获得skuId 参与促销列表
        List<PromListRespDTO> promList=promSV.listPromInfo(promRuleCheckDTO,true,null);
        if(!CollectionUtils.isEmpty(promList)){
               for(PromListRespDTO dto:promList){
                   //2 发送消息到solr
                   PromMsgUtil.sendPromIndexMsg(dto.getPromInfoDTO().getSiteId(), dto.getPromInfoDTO().getId(), gdsPromDTO.getSkuId(), PromConstants.PromInfo.STATUS_10, PromMsg2SolrRSVImpl.class.toString());
               }
        }
    }
    /**
     * TODO根据gdsId skuId shopId 获得参与促销列表，并发送消息到solr
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV#sendMsg2SolrByNewSkuList(java.util.List)
     * @param queryPromDTOList
     * @throws BusinessException
     * @author huangjx
     */
    public void sendMsg2SolrByNewSkuList(List<GdsPromDTO> gdsPromDTOList) throws BusinessException{
        if(!CollectionUtils.isEmpty(gdsPromDTOList)){
            for(GdsPromDTO gdsPromDTO:gdsPromDTOList){
                //每条数据不影响其他发送
                try{
                   this.sendMsg2SolrByNewSku(gdsPromDTO);
                }catch(BusinessException bx){
                    LogUtil.error(MODULE, "获得促销列表或者发送消息到solr发送错误啦"+bx.toString());
                }catch(Exception ex){
                    LogUtil.error(MODULE, "获得促销列表或者发送消息到solr发送错误啦"+ex.toString());
                    
                }
            }
        }
    }
}
