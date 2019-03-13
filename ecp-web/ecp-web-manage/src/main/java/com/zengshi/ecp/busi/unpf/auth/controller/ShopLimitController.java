package com.zengshi.ecp.busi.unpf.auth.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.unpf.auth.vo.ShopLimitVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsCatgLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfGdsLimitRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.IUnpfShopLimitRSV;
import com.zengshi.paas.utils.StringUtil;
@Controller
@RequestMapping(value="/platauth/shoplimit")
public class ShopLimitController extends EcpBaseController{
    
    @Resource
    private IUnpfShopLimitRSV unpfShopLimitRSV;

    @RequestMapping(value="/index")
    public String index(Model model) {
        return "/unpf/auth/shopLimit-index";
    }
    
    @RequestMapping(value="/gdsgrid")
    @ResponseBody
    public Model gdsgriddata(Model model, ShopLimitVO shopLimitVO) throws Exception {
        
        UnpfGdsLimitReqDTO gdsLimitReqDTO = new UnpfGdsLimitReqDTO();
        gdsLimitReqDTO = shopLimitVO.toBaseInfo(UnpfGdsLimitReqDTO.class);
        
        if (StringUtil.isNotBlank(shopLimitVO.getShopId())) {
            gdsLimitReqDTO.setShopId(Long.valueOf(shopLimitVO.getShopId()));
        }
        if (StringUtil.isNotBlank(shopLimitVO.getShopAuthId())) {
            gdsLimitReqDTO.setShopAuthId(Long.valueOf(shopLimitVO.getShopAuthId()));
        }
        gdsLimitReqDTO.setPlatType(shopLimitVO.getPlatType());
        gdsLimitReqDTO.setLimitType("2");
        
        PageResponseDTO<UnpfGdsLimitRespDTO> resultPage = unpfShopLimitRSV.queryGdsLimitPage(gdsLimitReqDTO);
        
        EcpBasePageRespVO<Map> resultMap = EcpBasePageRespVO.buildByPageResponseDTO(resultPage);
        
        return super.addPageToModel(model, resultMap);
    }
    @RequestMapping(value="/catggrid")
    @ResponseBody
    public Model catggriddata(Model model, ShopLimitVO shopLimitVO) throws Exception {
        
        UnpfGdsCatgLimitReqDTO gdsLimitReqDTO = new UnpfGdsCatgLimitReqDTO();
        gdsLimitReqDTO = shopLimitVO.toBaseInfo(UnpfGdsCatgLimitReqDTO.class);
        
        if (StringUtil.isNotBlank(shopLimitVO.getShopId())) {
            gdsLimitReqDTO.setShopId(Long.valueOf(shopLimitVO.getShopId()));
        }
        if (StringUtil.isNotBlank(shopLimitVO.getShopAuthId())) {
            gdsLimitReqDTO.setShopAuthId(Long.valueOf(shopLimitVO.getShopAuthId()));
        }
        gdsLimitReqDTO.setPlatType(shopLimitVO.getPlatType());
        
        PageResponseDTO<UnpfGdsCatgLimitRespDTO> resultPage = unpfShopLimitRSV.queryCatgLimitPage(gdsLimitReqDTO);
        
        EcpBasePageRespVO<Map> resultMap = EcpBasePageRespVO.buildByPageResponseDTO(resultPage);
        
        return super.addPageToModel(model, resultMap);
    }
    @RequestMapping(value="/delete")
    @ResponseBody
    public Model delete(Model model, String[] keys, String type) throws Exception {
        
        if (keys != null && keys.length > 0) {
            for(int i=0; i<keys.length;i++){
                switch (type) {
                case "gds":
                    unpfShopLimitRSV.deleteGdsLimitByKey(Long.valueOf(keys[i]));
                    break;
                case "catg":
                    unpfShopLimitRSV.deleteCatgLimitByKey(Long.valueOf(keys[i]));
                    break;
                default:
                    break;
                }
            }
        }
        return model;
        
    }
    @RequestMapping(value="/insertSku")
    @ResponseBody
    public Model insertSku(Model model, String skuIds[], String gdsIds[], ShopLimitVO shopLimitVO) throws Exception {
        
        if (skuIds != null && skuIds.length > 0) {
            for(int i=0; i<skuIds.length;i++){
                UnpfGdsLimitReqDTO gdsLimitReqDTO = new UnpfGdsLimitReqDTO();
                
                if (StringUtil.isNotBlank(shopLimitVO.getShopId())) {
                    gdsLimitReqDTO.setShopId(Long.valueOf(shopLimitVO.getShopId()));
                }
                if (StringUtil.isNotBlank(shopLimitVO.getShopAuthId())) {
                    gdsLimitReqDTO.setShopAuthId(Long.valueOf(shopLimitVO.getShopAuthId()));
                }
                gdsLimitReqDTO.setPlatType(shopLimitVO.getPlatType());
                gdsLimitReqDTO.setSkuId(Long.valueOf(skuIds[i]));
                gdsLimitReqDTO.setGdsId(Long.valueOf(gdsIds[i]));
                gdsLimitReqDTO.setLimitType("1");
                if (!unpfShopLimitRSV.checkLimitExits(gdsLimitReqDTO)) {
                    unpfShopLimitRSV.insertGdsLimit(gdsLimitReqDTO);
                }
            }

        }
        
        return model;
        
    }
    @RequestMapping(value="/insertGds")
    @ResponseBody
    public Model insertGds(Model model, String gdsIds[], ShopLimitVO shopLimitVO) throws Exception {
        
        if (gdsIds != null && gdsIds.length > 0) {
            for(int i=0; i<gdsIds.length;i++){
                UnpfGdsLimitReqDTO gdsLimitReqDTO = new UnpfGdsLimitReqDTO();
                
                if (StringUtil.isNotBlank(shopLimitVO.getShopId())) {
                    gdsLimitReqDTO.setShopId(Long.valueOf(shopLimitVO.getShopId()));
                }
                if (StringUtil.isNotBlank(shopLimitVO.getShopAuthId())) {
                    gdsLimitReqDTO.setShopAuthId(Long.valueOf(shopLimitVO.getShopAuthId()));
                }
                gdsLimitReqDTO.setPlatType(shopLimitVO.getPlatType());
                gdsLimitReqDTO.setGdsId(Long.valueOf(gdsIds[i]));
                gdsLimitReqDTO.setLimitType("2");
                if (!unpfShopLimitRSV.checkLimitExits(gdsLimitReqDTO)) {
                    unpfShopLimitRSV.insertGdsLimit(gdsLimitReqDTO);
                }
            }

        }
        
        return model;
        
    }
    @RequestMapping(value="/insertCatg")
    @ResponseBody
    public Model insertCatg(Model model, String catgs[], ShopLimitVO shopLimitVO) throws Exception {
        
        if (catgs != null && catgs.length > 0) {
            for(int i=0; i<catgs.length;i++){
                UnpfGdsCatgLimitReqDTO catgLimitReqDTO = new UnpfGdsCatgLimitReqDTO();
                
                if (StringUtil.isNotBlank(shopLimitVO.getShopId())) {
                    catgLimitReqDTO.setShopId(Long.valueOf(shopLimitVO.getShopId()));
                }
                if (StringUtil.isNotBlank(shopLimitVO.getShopAuthId())) {
                    catgLimitReqDTO.setShopAuthId(Long.valueOf(shopLimitVO.getShopAuthId()));
                }
                catgLimitReqDTO.setPlatType(shopLimitVO.getPlatType());
                catgLimitReqDTO.setCatgCode((catgs[i]));
                if (!unpfShopLimitRSV.checkLimitExits(catgLimitReqDTO)) {
                    unpfShopLimitRSV.insertCatgLimit(catgLimitReqDTO);
                }
            }

        }
        
        return model;
        
    }
    @RequestMapping("/opengds")
    public String opengds(Model model,  String  shopId, @RequestParam("siteId")String siteId){
        
        model.addAttribute("siteId", siteId);
        model.addAttribute("shopId", shopId);
        
        return "/unpf/goods/open/shop-gds";
    }
}

