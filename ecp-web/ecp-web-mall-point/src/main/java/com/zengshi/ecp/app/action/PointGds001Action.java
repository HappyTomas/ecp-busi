package com.zengshi.ecp.app.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zengshi.ecp.app.req.Pointmgds001Req;
import com.zengshi.ecp.app.resp.Pointmgds001Resp;
import com.zengshi.ecp.app.resp.gds.PointGdsDetailBaseInfo;
import com.zengshi.ecp.app.resp.gds.PointGdsPropBaseInfo;
import com.zengshi.ecp.app.resp.gds.PointGdsScoreExtRespInfo;
import com.zengshi.ecp.app.resp.gds.PointGdsSkuBaseInfo;
import com.zengshi.ecp.cms.dubbo.dto.CmsSiteRespDTO;
import com.zengshi.ecp.cms.dubbo.util.CmsCacheUtil;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.GdsQueryOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsScoreExtRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategoryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCollectRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsMessageUtil;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.order.dubbo.interfaces.IReportGoodPayedRSV;
import com.zengshi.ecp.server.front.dto.BaseStaff;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.StaffLocaleUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
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
@Service("pointmgds001")
@Action(bizcode = "pointmgds001", userCheck = false)
@Scope("prototype")
public class PointGds001Action extends AppBaseAction<Pointmgds001Req, Pointmgds001Resp> {

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
    
    @Resource
    private IScoreInfoRSV scoreInfoRSV;

    private static final String MODULE = PointGds001Action.class.getName();

    private static String KEY = "GDS_BROWSE_HIS";

    @Override
    protected void getResponse() throws BusinessException, SystemException, Exception {

    	Pointmgds001Req gds001Req = this.request;
        Pointmgds001Resp gds001Resp = this.response;

        GdsInfoReqDTO dto = new GdsInfoReqDTO();
        if(StringUtil.isNotEmpty(gds001Req.getGdsId())){
            dto.setId(gds001Req.getGdsId());
        }
        if(StringUtil.isNotEmpty(gds001Req.getSkuId())){
            dto.setSkuId(gds001Req.getSkuId());
        }
        GdsQueryOption[] gdsQueryOptions = new GdsQueryOption[] {
                GdsQueryOption.BASIC, GdsQueryOption.PROP,GdsQueryOption.PRICE,GdsQueryOption.MEDIA, 
                GdsQueryOption.SCORE};
        SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] {
                SkuQueryOption.BASIC, SkuQueryOption.PROP,SkuQueryOption.SHOWSTOCK,SkuQueryOption.PRICE,SkuQueryOption.MAINPIC};
        dto.setGdsQueryOptions(gdsQueryOptions);
        dto.setSkuQuerys(skuQueryOptions);
        GdsInfoDetailRespDTO resultDto = null;
        try {
            resultDto = gdsInfoQueryRSV.queryGdsInfoDetail(dto);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "无法获取商品详情信息！", e);
        }
        String shopName = "";
        String stockStatus = "";
        String stockStatusDesc = "";
        if(StringUtil.isNotEmpty(resultDto)){
//            if (GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES.equals(resultDto
//                    .getSkuInfo().getGdsStatus())
//                    || GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES
//                            .equals(resultDto.getSkuInfo().getGdsStatus())) {
//                //获取相关分类
//                getGdsDownCommonCat(resultDto,model);
//            }
            //获取库存状态
            if(resultDto.getSkuInfo() != null){
                stockStatus =  GdsUtils.checkScoreStcokStatus(resultDto.getSkuInfo().getRealAmount());
                stockStatusDesc = this.checkStcokStatusDesc(resultDto.getSkuInfo().getRealAmount());

                // 3.1.1 商品图片
                GdsMediaRespDTO gdsMediaRespDTO = resultDto.getSkuInfo().getMainPic();
                if (gdsMediaRespDTO != null
                        && StringUtil.isNotEmpty(gdsMediaRespDTO.getMediaUuid())) {
                    gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO
                            .getMediaUuid().toString(), "150x140!"));
                }else{
                	gdsMediaRespDTO = new GdsMediaRespDTO();
                	gdsMediaRespDTO.setMediaUuid("null");
                	gdsMediaRespDTO.setURL(ParamsTool.getImageUrl("null", "150x140!"));
                }
                resultDto.setMainPic(gdsMediaRespDTO);
                
                
            }
            
           
            GdsCatg2PropReqDTO reqDto = new GdsCatg2PropReqDTO();
            reqDto.setCatgCode(resultDto.getMainCatgs());
            //只获取基本属性，其他不要
            reqDto.setIfBasic(GdsConstants.GdsCatg2Prop.IS_BASIC_1);
            reqDto.setIfContainTopProp(true);
//            GdsCatg2PropRelationRespDTO rspDto = igdsCategoryRSV.queryCategoryProps(reqDto);
//            List<GdsPropRespDTO> basics = rspDto.getBasics();
//            if(basics!=null && basics.size() >0){
//                model.addAttribute("gdsBasicParam", basics);
//            }
            resultDto.setGdsDesc(getHtmlUrl(resultDto.getGdsDesc()));
            resultDto.setGdsPartlist(getHtmlUrl(resultDto.getGdsPartlist()));
            
           
            
            ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto.getShopId());
            if(StringUtil.isNotEmpty(shopInfo)){
                shopName = shopInfo.getShopName();
            }else{
                throw new BusinessException("web.gds.2000012");
            }
        }else{
            resultDto = new GdsInfoDetailRespDTO();
            GdsSkuInfoRespDTO gdsSkuInfoRespDTO = new GdsSkuInfoRespDTO();
            gdsSkuInfoRespDTO
                    .setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_DELETE);
            resultDto.setSkuInfo(gdsSkuInfoRespDTO);
        }
//        model.addAttribute("STOCK_SCORE_LACK_THRESHOLD", Integer.parseInt(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue()));
        PointGdsDetailBaseInfo gdsDetailBaseInfo = new PointGdsDetailBaseInfo();
        ObjectCopyUtil.copyObjValue(resultDto, gdsDetailBaseInfo, null, false);
        List<PointGdsScoreExtRespInfo> scores = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(resultDto.getScores())){
        for(GdsScoreExtRespDTO gdsScoreExtRespDTO: resultDto.getScores()){
        	PointGdsScoreExtRespInfo gdsScoreExtRespInfo = new PointGdsScoreExtRespInfo();
        	ObjectCopyUtil.copyObjValue(gdsScoreExtRespDTO, gdsScoreExtRespInfo, null, false);
        	scores.add(gdsScoreExtRespInfo);
        }
        }
        gdsDetailBaseInfo.setScores(scores);
        gdsDetailBaseInfo.setMainPicUrl(resultDto.getMainPic().getURL());
        PointGdsSkuBaseInfo gdsSkuBaseInfo = new PointGdsSkuBaseInfo();
        ObjectCopyUtil.copyObjValue(resultDto.getSkuInfo(), gdsSkuBaseInfo, null, false);
        gdsDetailBaseInfo.setGdsSkuBaseInfo(gdsSkuBaseInfo);
        // 获取库存阈值的配置参数
        gdsDetailBaseInfo.setShopName(shopName);
        gdsDetailBaseInfo.setStockStatus(stockStatus);
        gdsDetailBaseInfo.setStockStatusDesc(stockStatusDesc);
        //图片列表
        List<String> imageUrlList = new ArrayList<String>();
        if (resultDto.getMedias() != null && resultDto.getMedias().size() > 0) {
            for (GdsGds2MediaRespDTO media : resultDto.getMedias()) {
                if (media.getMediaUuid() != null) {
                    String mediaUrl = ImageUtil.getImageUrl(media.getMediaUuid());
                    imageUrlList.add(mediaUrl);

                }

            }

        }
        gdsDetailBaseInfo.setParams(new ArrayList<PointGdsPropBaseInfo>());
        gdsDetailBaseInfo.setImageUrlList(imageUrlList);
        gds001Resp.setGdsDetailBaseInfo(gdsDetailBaseInfo);
        BaseStaff baseStaff = StaffLocaleUtil.getStaff();
        ScoreInfoResDTO scoreInfo = scoreInfoRSV.findScoreInfoByStaffId(baseStaff.getId());
        if(scoreInfo!= null){
        	
        	gds001Resp.setRemainScore(scoreInfo.getScoreBalance());
        	
        }
        
        //设置缺货阈值数量
        Long num=0L;
        try {
            num=Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue());
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取库存缺货阈值失败",e);
        }
        gdsDetailBaseInfo.setStockLackNum(num);
        
        
        gdsDetailBaseInfo.setParams(new ArrayList<PointGdsPropBaseInfo>());
        gds001Resp.setGdsDetailBaseInfo(gdsDetailBaseInfo);
    }

    public void sendRecentlyBrowMsg(GdsInfoDetailRespDTO resultDto) {
        GdsBrowseHisReqDTO gdsBrowseHisReqDTO = new GdsBrowseHisReqDTO();
        try {
            // GDS_BROWSE_HIS_用户ID_单品ID
            String key = KEY + "_" + gdsBrowseHisReqDTO.getStaff().getId() + "_" + resultDto.getSkuInfo().getId();
            if (CacheUtil.getItem(key) == null) {
                // 不存在则发消息
                gdsBrowseHisReqDTO.setGdsId(resultDto.getSkuInfo().getGdsId());
                gdsBrowseHisReqDTO.setSkuId(resultDto.getSkuInfo().getId());
                gdsBrowseHisReqDTO.setShopId(resultDto.getShopId());
                gdsBrowseHisReqDTO.setStaffId(gdsBrowseHisReqDTO.getStaff().getId());
                gdsBrowseHisReqDTO.setBrowsePrice(resultDto.getSkuInfo().getRealPrice());
                GdsMessageUtil.sendGdsBrowseMessage(gdsBrowseHisReqDTO);
                // 缓存到redis
                CacheUtil.addItem(key, key, 1 * 60 * 60 * 6);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "发送消息失败！", e);
        }
    }

    private String getHtmlUrl(String vfsId) {
        if (StringUtil.isBlank(vfsId)) {
            return "";
        }
        CmsSiteRespDTO cms = CmsCacheUtil.getCmsSiteCache(2L);
        if (cms != null && StringUtil.isNotBlank(cms.getSiteUrl())) {
            return cms.getSiteUrl() + "/gdspointdetail/html/" + vfsId;
        }
        return "";
    }
    
    
	/**
	 * 
	 * checkStcokStatusDesc:(获取库存描述). <br/>
	 * 
	 * @author zhangjh
	 * @param count
	 * @return
	 * @since JDK 1.6
	 */
	public  String checkStcokStatusDesc(Long count) {
	    if(count == null){
            count = 0L;
        }
		if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_LACK_THRESHOLD).getParaValue())) {
			return "无货";
		} else if (count <= Long.parseLong(SysCfgUtil.fetchSysCfg(GdsConstants.GdsStock.STOCK_SCORE_HARDTOGET_THRESHOLD).getParaValue())) {
			return "紧张";
		} else {
			return "充足";
		}
	}
	
}
