package com.zengshi.ecp.cms.service.listener;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsAdvertiseRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorGdsReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsRecommendRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsAdvertiseSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorGdsSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsRecommendSV;
import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.paas.message.Message;
import com.zengshi.paas.message.MessageListener;
import com.zengshi.paas.message.MessageStatus;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONObject;

public class CmsGdsDelMessageListener implements MessageListener {
    private static final String MODULE = CmsGdsDelMessageListener.class.getName();

    @Override
    public void receiveMessage(Message message, MessageStatus status) {
        try {
            if (CmsConstants.ParamConfig.TOPIC_GDSDEL.equals(message.getTopic())) {

                // 接收到删除商品消息
                LogUtil.info(MODULE, "接收到删除商品消息：" + (String) message.getMsg());

                this.asyncDelGds((String) message.getMsg());
            }
        } catch (Exception e) {
            LogUtil.error(MODULE, "异步删除CMS模块商品失败！", e);
        } catch (Error e) {
            LogUtil.error(MODULE, "异步删除CMS模块商品失败！", e);
        }

    }

    public void asyncDelGds(String message) throws Exception {
        LogUtil.info(MODULE, "商品域返回删除的商品信息：" + message);
        
        JSONObject json = JSONObject.fromObject(message);
        Object gdsId = json.get("gdsId");
        if(StringUtil.isNotEmpty(gdsId)){
            String gdsIdStr = gdsId.toString().trim();
            //1 根据商品域删除商品  同步  删除CMS楼层下的商品
            ICmsFloorGdsSV floorGdsSV = EcpFrameContextHolder.getBean("cmsFloorGdsSV",ICmsFloorGdsSV.class);
            CmsFloorGdsReqDTO floorGdsRespDTO = new CmsFloorGdsReqDTO();
            floorGdsRespDTO.setGdsId(Long.parseLong(gdsIdStr));
            floorGdsRespDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);//删除
            //floorGdsSV.updateCmsFloorGds(floorGdsRespDTO);
            floorGdsSV.updateCmsFloorGdsByExample(floorGdsRespDTO);
            
            //2 根据商品域删除商品  同步  删除CMS专家推荐下的商品
            ICmsRecommendSV recommendSV = EcpFrameContextHolder.getBean("cmsRecommendSV",ICmsRecommendSV.class);
            CmsRecommendReqDTO recommendReqDTO = new CmsRecommendReqDTO();
            recommendReqDTO.setRecommendGdsId(Long.parseLong(gdsIdStr));
            recommendReqDTO.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);//删除
            //recommendSV.updateCmsRecommend(recommendReqDTO);
            recommendSV.updateCmsRecommendByExample(recommendReqDTO);
            
            //3 根据商品域删除商品  同步  删除CMS专家推荐下的该作者的其他作品
            CmsRecommendReqDTO recommendReqDTO2 = new CmsRecommendReqDTO();
            recommendReqDTO2.setOtherProduction(gdsIdStr);
            List<CmsRecommendRespDTO> recommendRespDTOList = recommendSV.queryCmsRecommendList(recommendReqDTO2);
            if(!CollectionUtils.isEmpty(recommendRespDTOList)){
                for(CmsRecommendRespDTO recommendRespDTO : recommendRespDTOList){
                    if(StringUtil.isNotEmpty(recommendRespDTO) && StringUtil.isNotEmpty(recommendRespDTO.getOtherProduction())){
                        String otherProductionStr = "";
                        String[] otherProductionArray = recommendRespDTO.getOtherProduction().split("、");
                        if(otherProductionArray != null && otherProductionArray.length > 0){
                            for(int i=0;i<otherProductionArray.length;i++){
                                if(StringUtil.isNotBlank(otherProductionArray[i])){
                                    if(!gdsIdStr.equals(otherProductionArray[i].trim())){
                                        otherProductionStr += otherProductionArray[i].trim()+"、";
                                    }
                                }
                            }
                        }
                        recommendReqDTO2.setId(recommendRespDTO.getId());
                        recommendReqDTO2.setOtherProduction(otherProductionStr);
                        recommendSV.updateCmsRecommend(recommendReqDTO2);
                    }
                }
            }
            
            //4 根据商品域删除商品  同步  删除CMS专家推荐下的该作者推荐的作品
            CmsRecommendReqDTO recommendReqDTO3 = new CmsRecommendReqDTO();
            recommendReqDTO3.setRecommendProduction(gdsIdStr);
            List<CmsRecommendRespDTO> recommendProductionList = recommendSV.queryCmsRecommendList(recommendReqDTO3);
            if(!CollectionUtils.isEmpty(recommendProductionList)){
                for(CmsRecommendRespDTO recommendRespDTO : recommendProductionList){
                    if(StringUtil.isNotEmpty(recommendRespDTO) && StringUtil.isNotEmpty(recommendRespDTO.getRecommendProduction())){
                        String recommendProductionStr = "";
                        String[] recommendProductionArray = recommendRespDTO.getRecommendProduction().split("、");
                        if(recommendProductionArray != null && recommendProductionArray.length > 0){
                            for(int i=0;i<recommendProductionArray.length;i++){
                                if(StringUtil.isNotBlank(recommendProductionArray[i])){
                                    if(!gdsIdStr.equals(recommendProductionArray[i].trim())){
                                        recommendProductionStr += recommendProductionArray[i].trim()+"、";
                                    }
                                }
                            }
                        }
                        recommendReqDTO3.setId(recommendRespDTO.getId());
                        recommendReqDTO3.setRecommendProduction(recommendProductionStr);
                        recommendSV.updateCmsRecommend(recommendReqDTO3);
                    }
                }
            }
            
            //5 根据商品域删除商品  同步  删除CMS专家推荐下的该作者还喜欢的作品
            CmsRecommendReqDTO recommendReqDTO4 = new CmsRecommendReqDTO();
            recommendReqDTO4.setOtherLike(gdsIdStr);
            List<CmsRecommendRespDTO> otherLikeList = recommendSV.queryCmsRecommendList(recommendReqDTO4);
            if(!CollectionUtils.isEmpty(otherLikeList)){
                for(CmsRecommendRespDTO recommendRespDTO : otherLikeList){
                    if(StringUtil.isNotEmpty(recommendRespDTO) && StringUtil.isNotEmpty(recommendRespDTO.getOtherLike())){
                        String otherLikeStr = "";
                        String[] otherLikeArray = recommendRespDTO.getOtherLike().split("、");
                        if(otherLikeArray != null && otherLikeArray.length > 0){
                            for(int i=0;i<otherLikeArray.length;i++){
                                if(StringUtil.isNotBlank(otherLikeArray[i])){
                                    if(!gdsIdStr.equals(otherLikeArray[i].trim())){
                                        otherLikeStr += otherLikeArray[i].trim()+"、";
                                    }
                                }
                            }
                        }
                        recommendReqDTO4.setId(recommendRespDTO.getId());
                        recommendReqDTO4.setOtherLike(otherLikeStr);
                        recommendSV.updateCmsRecommend(recommendReqDTO4);
                    }
                }
            }
            
            //6 根据商品域删除商品  同步  删除CMS广告
            ICmsAdvertiseSV advertiseSV = EcpFrameContextHolder.getBean("cmsAdvertiseSV",ICmsAdvertiseSV.class);
            CmsAdvertiseReqDTO advertiseReqDTO = new CmsAdvertiseReqDTO();
            advertiseReqDTO.setLinkType(CmsConstants.LinkType.CMS_LINKTYPE_01);//商品
            advertiseReqDTO.setLinkUrl(gdsIdStr);
            List<CmsAdvertiseRespDTO> advertiseRespDTOList = advertiseSV.queryCmsAdvertiseList(advertiseReqDTO);
            if(!CollectionUtils.isEmpty(advertiseRespDTOList)){
                for(CmsAdvertiseRespDTO advertiseRespDTO : advertiseRespDTOList){
                    CmsAdvertiseReqDTO advertiseReqDTO2 = new CmsAdvertiseReqDTO();
                    advertiseReqDTO2.setId(advertiseRespDTO.getId());
                    advertiseReqDTO2.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);//删除
                    advertiseSV.updateCmsAdvertise(advertiseReqDTO2);
                }
            }
        }
    }
    
}
