package com.zengshi.ecp.busi.search.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoDetailRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsGuessYLRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.system.util.ParamsTool;
import com.zengshi.paas.utils.StringUtil;

@Controller
@RequestMapping(value = "/searchExtern")
public class SearchExternController extends EcpBaseController {
    @Resource
    private IGdsInfoQueryRSV gdsInfoQueryRSV;

    @Resource
    private IGdsGuessYLRSV gdsGuessYLRSV;

    // @Resource
    // private IReportGoodPayedRSV
    @RequestMapping("/rank")
    public String rank(Model model) {

        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setTopNum(7);
        gdsInfoReqDTO.setCurrentSiteId(2L);
        List<GdsInfoDetailRespDTO> detailRespDTOs = gdsInfoQueryRSV
                .queryGdsSkuInfoListByRank(gdsInfoReqDTO);
        if (!CollectionUtils.isEmpty(detailRespDTOs)) {
            for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO : detailRespDTOs) {
                // GdsGuessYLReqDTO gdsGuessYLReqDTO = new GdsGuessYLReqDTO();
                // GdsGuessHomePageRespDTO gdsGuessHomePageRespDTO =
                // gdsGuessYLRSV.queryGdsGuessForHomePage(gdsGuessYLReqDTO);
                // if(!CollectionUtils.isEmpty(gdsGuessHomePageRespDTO.getDetailRespDTOs())){
                // for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO :
                // gdsGuessHomePageRespDTO.getDetailRespDTOs()){
                if (gdsInfoDetailRespDTO != null) {
                    // 3.1.1 商品图片
                    GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                    if (gdsMediaRespDTO != null
                            && StringUtil.isNotEmpty(gdsMediaRespDTO.getMediaUuid())) {
                        gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO
                                .getMediaUuid().toString(), "150x140!"));
                    }else{
                    	gdsMediaRespDTO = new GdsMediaRespDTO();
                    	gdsMediaRespDTO.setMediaUuid("null");
                    	gdsMediaRespDTO.setURL(ParamsTool.getImageUrl("null", "150x140!"));
                    }
                    gdsInfoDetailRespDTO.setMainPic(gdsMediaRespDTO);
                    // 根据ID读取商品描述内容
                    // byte[] content=FileUtil.readFile(gdsInfoDetailRespDTO.getGdsDesc());
                    // if(ArrayUtils.isNotEmpty(content)){
                    // String gdsDescContent=Jsoup.parse(new String(content)).text();
                    // if(gdsDescContent.length()>GDSDESCCONTENT_LENGTH){
                    // gdsDescContent=gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH)+"...";
                    // }
                    // gdsInfoDetailRespDTO.setGdsDesc(gdsDescContent);
                    // }
                }
            }
        }

        // model.addAttribute("rankGdsList",gdsGuessHomePageRespDTO.getDetailRespDTOs());
        model.addAttribute("rankGdsList", detailRespDTOs);
        return "/search/page/search-sale-rank";
    }

    @RequestMapping("/hotSale")
    public String hotSale(Model model) {
        GdsInfoReqDTO gdsInfoReqDTO = new GdsInfoReqDTO();
        gdsInfoReqDTO.setTopNum(2);
        gdsInfoReqDTO.setCurrentSiteId(2L);
        List<GdsInfoDetailRespDTO> detailRespDTOs = gdsInfoQueryRSV
                .queryGdsSkuInfoListByRank(gdsInfoReqDTO);

        if (!CollectionUtils.isEmpty(detailRespDTOs)) {
            for (GdsInfoDetailRespDTO gdsInfoDetailRespDTO : detailRespDTOs) {
                if (gdsInfoDetailRespDTO != null) {
                    // 3.1.1 商品图片
                    GdsMediaRespDTO gdsMediaRespDTO = gdsInfoDetailRespDTO.getMainPic();
                    if (gdsMediaRespDTO != null
                            && StringUtil.isNotEmpty(gdsMediaRespDTO.getMediaUuid())) {
                        gdsMediaRespDTO.setURL(ParamsTool.getImageUrl(gdsMediaRespDTO
                                .getMediaUuid().toString(), "150x150!"));
                    }else{
                    	gdsMediaRespDTO = new GdsMediaRespDTO();
                    	gdsMediaRespDTO.setMediaUuid("null");
                    	gdsMediaRespDTO.setURL(ParamsTool.getImageUrl("null", "150x140!"));
                    }
                    gdsInfoDetailRespDTO.setMainPic(gdsMediaRespDTO);

                    // 根据ID读取商品描述内容
                    // byte[] content=FileUtil.readFile(gdsInfoDetailRespDTO.getGdsDesc());
                    // if(ArrayUtils.isNotEmpty(content)){
                    // String gdsDescContent=Jsoup.parse(new String(content)).text();
                    // if(gdsDescContent.length()>GDSDESCCONTENT_LENGTH){
                    // gdsDescContent=gdsDescContent.substring(0, GDSDESCCONTENT_LENGTH)+"...";
                    // }
                    // gdsInfoDetailRespDTO.setGdsDesc(gdsDescContent);
                    // }
                }
            }
        }
        model.addAttribute("hotGdsList", detailRespDTOs);
        return "/search/page/search-hot";
    }

}
