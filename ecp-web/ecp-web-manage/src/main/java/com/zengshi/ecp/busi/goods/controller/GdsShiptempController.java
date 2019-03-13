package com.zengshi.ecp.busi.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.zengshi.ecp.system.util.Escape;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.vo.GdsShipAddress;
import com.zengshi.ecp.busi.goods.vo.GdsShiptempVO;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.prom.PromConst;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.AreaCodeDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempPriceReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV;
import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 运费模板<br>
 * Date:2015年9月5日下午3:23:56 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version
 * @since JDK 1.6
 */
@RequestMapping(value = "/gdsshiptemp")
@Controller
public class GdsShiptempController extends EcpBaseController {
    private static String MODULE = GdsShiptempController.class.getName();

    private static String URL = "/goods/gdsShiptemp";

    @Resource
    private IGdsShiptemRSV iGdsShiptempRVS;

    @Resource
    private IShopInfoRSV iShopInfoRSV;

    @RequestMapping()
    public String init(Model model, GdsShopVO gsShopVO) {
        model.addAttribute("shopId", gsShopVO.getShopId());
        model.addAttribute("shipTempTypeList", BaseParamUtil.fetchParamList("GDS_SHIP_TEMP_TYPE"));
        return URL + "/gds-shiptemp-grid";
    }

    /**
     * 
     * gridList:(获取运费模板列表). <br/>
     * 
     * @author gxq
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/gridlist")
    public Model gridList(Model model, GdsShiptempVO gdsShiptempVO) {
        GdsShiptempReqDTO dto = gdsShiptempVO.toBaseInfo(GdsShiptempReqDTO.class);
        EcpBasePageRespVO<Map> respVO = null;
        ObjectCopyUtil.copyObjValue(gdsShiptempVO, dto, "", false);
        if (StringUtil.isNotEmpty(gdsShiptempVO.getShipTemplateId())) {
            dto.setId(gdsShiptempVO.getShipTemplateId());
        }
        PageResponseDTO<GdsShiptempRespDTO> pageInfo = null;
        try {
            pageInfo = iGdsShiptempRVS.queryGdsShipTemp(dto);
            if (StringUtil.isNotEmpty(pageInfo) && StringUtil.isNotEmpty(pageInfo.getResult())) {
                for (GdsShiptempRespDTO resultDto : pageInfo.getResult()) {
                    if (StringUtil.isNotEmpty(resultDto.getShopId())) {
                        ShopInfoResDTO shopInfo = iShopInfoRSV.findShopInfoByShopID(resultDto
                                .getShopId());
                        if (StringUtil.isNotEmpty(shopInfo)) {
                            resultDto.setShopName(shopInfo.getShopName());
                        }
                    }
                }
            }
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取运费模板列表失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取运费模板列表失败！", e);
        }
        return super.addPageToModel(model, respVO);
    }

    @RequestMapping(value = "/toshiptempadd")
    public String toShiptempAdd(Model model, @RequestParam("shopId")
    String shopId) {
        model.addAttribute("shopId", shopId);
        model.addAttribute("shipTempTypeList", BaseParamUtil.fetchParamList("GDS_SHIP_TEMP_TYPE"));
        return URL + "/gds-shiptemp-add";
    }

    /**
     * 
     * addShiptemp:(这里用一句话描述这个方法的作用). <br/>
     * 
     * @author gxq
     * @param model
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/addshiptemp")
    @ResponseBody
    public EcpBaseResponseVO addShiptemp(Model model, @Valid
    GdsShiptempVO gdsShiptempVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsShiptempReqDTO dto = new GdsShiptempReqDTO();
        ObjectCopyUtil.copyObjValue(gdsShiptempVO, dto, "", false);
        getSaveInitParam(dto, gdsShiptempVO);
        dto.setShipTemplateName(Escape.unescape(dto.getShipTemplateName()));
        try {
            iGdsShiptempRVS.saveGdsShipTemp(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorMessage());
            LogUtil.error(MODULE, "运费模板保存失败！", e);
        }
        return vo;
    }

    private void getSaveInitParam(GdsShiptempReqDTO dto, GdsShiptempVO gdsShiptempVO) {
        List<GdsShiptempPriceReqDTO> gdsShiptempPriceReqDTOList = new ArrayList<GdsShiptempPriceReqDTO>();
        dto.setGdsShiptempPriceReqDTOList(gdsShiptempPriceReqDTOList);
        if (StringUtil.isNotBlank(gdsShiptempVO.getSeniorFreeParam())) {
            // 解析高级运费模板配置
            parseSeniorFree(dto, gdsShiptempVO.getSeniorFreeParam(), gdsShiptempPriceReqDTOList);
        }
        if (StringUtil.isNotBlank(gdsShiptempVO.getDefaultFreeParam())) {
            // 解析默认运费模板配置
            parseDeFaultFree(dto, gdsShiptempVO.getDefaultFreeParam(), gdsShiptempPriceReqDTOList);
        }
    }

    private void parseSeniorFree(GdsShiptempReqDTO dto, String str,
            List<GdsShiptempPriceReqDTO> gdsShiptempPriceReqDTOList) {
        JSONArray seniorlist = JSONArray.fromObject(str);
        int size = seniorlist.size();
        GdsShiptempPriceReqDTO reqDto = null;
        Map<String,String> map = new HashMap<String,String>();
        if (size >= 1) {
            for (int i = 0; i < size; i++) {
                reqDto = new GdsShiptempPriceReqDTO();
                // 放进"{firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
                // "continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:"+areaCodeList+"},";
                JSONObject object = (JSONObject) seniorlist.get(i);
                String firstAmount = object.getString("firstAmount");
                String firstPrice = object.getString("firstPrice");
                String continueAmount = object.getString("continueAmount");
                String continuePrice = object.getString("continuePrice");
                String freeAmount = object.getString("freeAmount");
                String areaCodeList = object.getString("areaCodeList");
                String pricingMode = object.getString("pricingMode");
                if ("[]".equals(areaCodeList)) {
                    throw new BusinessException("web.gds.200009");
                } else if (StringUtil.isNotBlank(areaCodeList)) {
                    parseAddress(reqDto, areaCodeList,map);
                }
                
                reqDto.setCountryCode(PromConst.SysModel.COUNTRY_DEFAULT);
                reqDto.setPricingMode(pricingMode);
                if (StringUtil.isNotBlank(firstAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setFirstAmount(MoneyUtil.convertYuanToCent(firstAmount));

                    } else {
                        reqDto.setFirstAmount(Long.parseLong(firstAmount));
                    }
                }
                if (StringUtil.isNotBlank(firstPrice)) {
                    reqDto.setFirstPrice(MoneyUtil.convertYuanToCent(firstPrice));
                }
                if (StringUtil.isNotBlank(continueAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setContinueAmount(MoneyUtil.convertYuanToCent(continueAmount));
                    } else {
                        reqDto.setContinueAmount(Long.parseLong(continueAmount));

                    }
                }
                if (StringUtil.isNotBlank(continuePrice)) {
                    reqDto.setContinuePrice(MoneyUtil.convertYuanToCent(continuePrice));
                }
                if (StringUtil.isNotBlank(freeAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setFreeAmount(MoneyUtil.convertYuanToCent(freeAmount));

                    } else {
                        reqDto.setFreeAmount(Long.parseLong(freeAmount));
                    }
                }
                gdsShiptempPriceReqDTOList.add(reqDto);
            }
        }
        map.clear();
    }

    private void parseAddress(GdsShiptempPriceReqDTO reqDto, String areaCodeList,Map<String,String> map) {
        JSONArray seniorlist = JSONArray.fromObject(areaCodeList);
        List<AreaCodeDTO> list = new ArrayList<AreaCodeDTO>();
        AreaCodeDTO area = null;
        int size = seniorlist.size();
        if (size >= 1) {
            for (int i = 0; i < size; i++) {

                JSONObject object = (JSONObject) seniorlist.get(i);
                String provinceCode = object.getString("provinceCode");
                if(map.containsKey(provinceCode)){
                    throw new BusinessException("同一地区不能配置多条运费模板！");
                }else{
                    map.put(provinceCode, "a");
                }
                // String provinceName = object.getString("provinceName");
                // String areaLevl = object.getString("areaLevel");
                String cityList = object.getString("cityList");
                if (StringUtil.isNotBlank(cityList)) {
                    JSONArray clist = JSONArray.fromObject(cityList);
                    if(clist.size() == 0){
                        area = new AreaCodeDTO();
                        area.setProvinceCode(provinceCode);
//                        area.setAreaLevel(areaLevel);
                        list.add(area);
                        continue;
                    }else{
                    for (int k = 0; k < clist.size(); k++) {
                        area = new AreaCodeDTO();
                        JSONObject object1 = (JSONObject) clist.get(k);
                        area.setProvinceCode(provinceCode);
                        area.setCityCode(object1.getString("cityCode"));
                        if (!StringUtil.isBlank(object1.getString("areaLevel"))) {
                            area.setAreaLevel(Long.parseLong(object1.getString("areaLevel")));
                        }
                        list.add(area);
                    }
                    }
                }
            }
        }
        reqDto.setAreaCodeList(list);
    }

    private void parseDeFaultFree(GdsShiptempReqDTO dto, String freeList,
            List<GdsShiptempPriceReqDTO> gdsShiptempPriceReqDTOList) {
        JSONArray seniorlist = JSONArray.fromObject(freeList);
        int size = seniorlist.size();
        GdsShiptempPriceReqDTO reqDto = null;
        if (size >= 1) {
            for (int i = 0; i < size; i++) {
                reqDto = new GdsShiptempPriceReqDTO();
                // 放进"{firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
                // "continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:"+areaCodeList+"},";
                JSONObject object = (JSONObject) seniorlist.get(i);
                String firstAmount = object.getString("firstAmount");
                String firstPrice = object.getString("firstPrice");
                String continueAmount = object.getString("continueAmount");
                String continuePrice = object.getString("continuePrice");
                String freeAmount = object.getString("freeAmount");
                String areaCodeList = object.getString("areaCodeList");
                String pricingMode = object.getString("pricingMode");
                if (StringUtil.isNotBlank(areaCodeList)) {
                    parseAddress(reqDto, areaCodeList,new HashMap<String,String>());
                }
                reqDto.setPricingMode(pricingMode);
                reqDto.setProvinceCode(GdsConstants.GdsShiptemp.DEFAULT_AREA_CODE);
                if (StringUtil.isNotBlank(firstAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setFirstAmount(MoneyUtil.convertYuanToCent(firstAmount));
                    } else {
                        reqDto.setFirstAmount(Long.parseLong(firstAmount));
                    }
                }
                if (StringUtil.isNotBlank(firstPrice)) {
                    reqDto.setFirstPrice(MoneyUtil.convertYuanToCent(firstPrice));
                }
                if (StringUtil.isNotBlank(continueAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setContinueAmount(MoneyUtil.convertYuanToCent(continueAmount));

                    } else {
                        reqDto.setContinueAmount(Long.parseLong(continueAmount));
                    }
                }
                if (StringUtil.isNotBlank(continuePrice)) {
                    reqDto.setContinuePrice(MoneyUtil.convertYuanToCent(continuePrice));
                }
                if (StringUtil.isNotBlank(freeAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(dto
                            .getShipTemplateType())) {
                        reqDto.setFreeAmount(MoneyUtil.convertYuanToCent(freeAmount));
                    } else {
                        reqDto.setFreeAmount(Long.parseLong(freeAmount));
                    }
                }
                gdsShiptempPriceReqDTOList.add(reqDto);
            }
        }
    }

    /**
     * 
     * deleteShiptemp:(删除运费模板). <br/>
     * 
     * @author gxq
     * @param shopId
     * @param tempId
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/deleteshiptemp")
    public EcpBaseResponseVO deleteShiptemp(@RequestParam("shopId")
    String shopId, @RequestParam("tempId")
    String tempId) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsShiptempReqDTO dto = new GdsShiptempReqDTO();
        if (!StringUtil.isBlank(tempId)) {
            dto.setId(Long.parseLong(tempId));
        } else {
            throw new BusinessException("运费模板编码入参为空");
        }
        if (!StringUtil.isBlank(shopId)) {
            dto.setShopId(Long.parseLong(shopId));
        } else {
            throw new BusinessException("店铺编码入参为空");
        }
        long result = 0;
        try {
            result = iGdsShiptempRVS.delteGdsShipTemp(dto);
            if (result == -1) {
                vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                vo.setResultMsg("不允许删除店铺的默认运费模板！");
            }else if(result == -2){
                vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                vo.setResultMsg("不允许删除已关联商品的运费模板！"); 
            } 
            else {
                vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            }
        } catch (BusinessException e) {
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorMessage());
            LogUtil.error(MODULE, "运费模板删除失败！", e);
        }
        return vo;
    }

    /**
     * 
     * toTempEdit:(跳转到模板编辑页面). <br/>
     * 
     * @author gxq
     * @param templateId
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/totempedit")
    public String toTempEdit(Model model, @RequestParam("templateId")
    String templateId) {
        GdsShiptempReqDTO dto = new GdsShiptempReqDTO();
        if (StringUtil.isNotBlank(templateId)) {
            dto.setId(Long.parseLong(templateId));
        } else {
            throw new BusinessException("运费模板编码入参为空");
        }
        GdsShiptempRespDTO tempInfo = iGdsShiptempRVS.getSingleGdsShipTemp(dto);
        model.addAttribute("areaJson", JSONArray.fromObject(tempInfo.getGdsShipTempPriceRespDTO())
                .toString().replaceAll("\"", "\'"));
        model.addAttribute("tempInfo", tempInfo);
        model.addAttribute("shipTempTypeList", BaseParamUtil.fetchParamList("GDS_SHIP_TEMP_TYPE"));
        return URL + "/gds-shiptemp-edit";
    }

    /**
     * 
     * editShiptemp:(编辑保存运费模板). <br/>
     * 
     * @author gxq
     * @param gdsShiptempVO
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/editshiptemp")
    @ResponseBody
    public EcpBaseResponseVO editShiptemp(@Valid
    GdsShiptempVO gdsShiptempVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        GdsShiptempReqDTO dto = new GdsShiptempReqDTO();
        ObjectCopyUtil.copyObjValue(gdsShiptempVO, dto, "", false);
        getSaveInitParam(dto, gdsShiptempVO);
        dto.setId(gdsShiptempVO.getShipTemplateId());
        if (StringUtil.isNotEmpty(gdsShiptempVO.getShopId())) {
            dto.setShopId(gdsShiptempVO.getShopId());
        } else {
            throw new BusinessException("web.gds.200008");
        }
        dto.setShipTemplateName(Escape.unescape(dto.getShipTemplateName()));
        try {
            iGdsShiptempRVS.editGdsShipTemp(dto);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "编辑保存失败！", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            vo.setResultMsg(e.getErrorMessage());
        }
        return vo;
    }

    /**
     * 
     * selectArea:(跳转到选择区域弹出窗口). <br/>
     * 
     * @author gxq
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/selectarea")
    public String selectArea(Model model) {
        List<GdsShipAddress> result = new ArrayList<GdsShipAddress>();
        List<BaseAreaAdminRespDTO> provinceList = BaseAreaAdminUtil
                .fetchChildAreaInfos(PromConst.SysModel.COUNTRY_DEFAULT);
        GdsShipAddress gdsShipAddress = null;
        if (StringUtil.isNotEmpty(provinceList)) {
            for (BaseAreaAdminRespDTO dto : provinceList) {
                gdsShipAddress = new GdsShipAddress();
                gdsShipAddress.setAreaCode(dto.getAreaCode());
                gdsShipAddress.setAreaName(dto.getAreaName());
                List<BaseAreaAdminRespDTO> a = BaseAreaAdminUtil.fetchChildAreaInfos(dto
                        .getAreaCode());
                gdsShipAddress.setList(a);
                result.add(gdsShipAddress);
            }
        }
        model.addAttribute("provinceList", result);
        return URL + "/list/address-open";
    }
}
