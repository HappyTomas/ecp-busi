package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.app.req.Pointmgds002Req;
import com.zengshi.ecp.app.resp.Pointmgds002Resp;
import com.zengshi.ecp.app.resp.gds.PointGdsDetailBaseInfo;
import com.zengshi.ecp.app.resp.gds.PointGdsScoreExtRespInfo;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.butterfly.app.annotation.Action;
import com.zengshi.butterfly.core.exception.SystemException;

/**
 * 获取商品信息 Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2016年3月10日上午10:18:08 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Service("pointmgds002")
@Action(bizcode = "pointmgds002", userCheck = false)
@Scope("prototype")
public class PointGds002Action extends AppBaseAction<Pointmgds002Req, Pointmgds002Resp> {

    @Resource
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;

    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;

    @Resource
    private IGdsCollectRSV gdsCollectRSV;
    
    @Resource
    private IGdsCategoryRSV igdsCategoryRSV;

    @Resource
    private IReportGoodPayedRSV reportGoodPayedRSV;
    
    @Resource
    private IShopInfoRSV iShopInfoRSV;

    private static final String MODULE = PointGds002Action.class.getName();

    private static String KEY = "GDS_BROWSE_HIS";

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

        Pointmgds002Req gds002Req = this.request;
        Pointmgds002Resp gds002Resp = this.response;



        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setTopNum(7);
        gdsInfoReqDTO.setCurrentSiteId(2L);
        List<GdsInfoDetailRespDTO> detailRespDTOs = gdsInfoQueryRSV
                .queryGdsSkuInfoListByRank(gdsInfoReqDTO);
        List<PointGdsDetailBaseInfo> gdsDetailBaseInfos = new ArrayList<PointGdsDetailBaseInfo>();
        if (!CollectionUtils.isEmpty(detailRespDTOs)) {
            for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO : detailRespDTOs) {
     
                if (gdsInfoDetailRespDTO != null) {
                	PointGdsDetailBaseInfo detailBaseInfo =  new PointGdsDetailBaseInfo();
                	ObjectCopyUtil.copyObjValue(gdsInfoDetailRespDTO, detailBaseInfo, null, false);
                    // 3.1.1 商品图片
                    GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                    if (gdsMediaRespDTO != null
                            && StringUtil.isNotEmpty(gdsMediaRespDTO.getMediaUuid())) {
                        gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO
                                .getMediaUuid().toString(), "150x140!"));
                        detailBaseInfo.setMainPicUrl(gdsMediaRespDTO.getURL());
                    }else{
                    	gdsMediaRespDTO = new GdsMediaRespDTO();
                    	gdsMediaRespDTO.setMediaUuid("null");
                    	gdsMediaRespDTO.setURL(ParamsTool.getImageUrl("null", "150x140!"));
                        detailBaseInfo.setMainPicUrl(gdsMediaRespDTO.getURL());

                    }
                    List<PointGdsScoreExtRespInfo> gdsScoreExtRespInfos = new ArrayList<PointGdsScoreExtRespInfo>();
                    for(GdsScoreExtRespDTO gdsScoreExtRespDTO: gdsInfoDetailRespDTO.getScores()){
                    	PointGdsScoreExtRespInfo gdsScoreExtRespInfo = new PointGdsScoreExtRespInfo();
                    	ObjectCopyUtil.copyObjValue(gdsScoreExtRespDTO, gdsScoreExtRespInfo, null, false);
                    	gdsScoreExtRespInfos.add(gdsScoreExtRespInfo);
                    }
                    
                    detailBaseInfo.setScores(gdsScoreExtRespInfos);
                    detailBaseInfo.setParams(null);
                    gdsDetailBaseInfos.add(detailBaseInfo);
                }
            }
        }
        gds002Resp.setRankGdsList(gdsDetailBaseInfos);
       
    
    }



    private String getHtmlUrl(String vfsId) {
        if (StringUtil.isBlank(vfsId)) {
            return "";
        }
        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(1L);
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
            return cms.getSiteUrl() + "/gdspointdetail/html/" + vfsId;
        }
        return "";
    }
    
    
  
}
