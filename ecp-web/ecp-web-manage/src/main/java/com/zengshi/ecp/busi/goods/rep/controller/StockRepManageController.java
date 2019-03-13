package com.zengshi.ecp.busi.goods.rep.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.goods.rep.vo.AreaInfo;
import com.zengshi.ecp.busi.goods.rep.vo.StockRepAddInitInfo;
import com.zengshi.ecp.busi.goods.rep.vo.StockRepDelInfo;
import com.zengshi.ecp.busi.goods.rep.vo.StockRepEditInfo;
import com.zengshi.ecp.busi.goods.rep.vo.StockRepInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptMainDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepAdaptRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepMainReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepPageRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.stock.StockRepRespDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsStockRSV;
import com.zengshi.ecp.server.front.dto.BaseAreaAdminRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.BaseAreaAdminUtil;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

import net.sf.json.JSONArray;

/**
 * 
 * Title: 仓库管理 <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015-8-26下午8:50:43 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zjh
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/goods/stockrep")
public class StockRepManageController extends EcpBaseController {

    @Resource(name = "gdsStockRSV")
    private IGdsStockRSV gdsStockRSV;

    @Resource(name = "shopInfoRSV")
    private IShopInfoRSV shopInfoRSV;

    private static final String COUNTRY_DEFAULT = "156";

    private static String MODULE = StockRepManageController.class.getName();

    /**
     * 
     * add:(仓库新增页面初始化). <br/>
     * 
     * @author zjh
     * @param model
     * @param stockRepInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/add")
    public String add(Model model, StockRepInfo stockRepInfo) throws Exception {
       LogUtil.debug(MODULE, "请求参数为：" + stockRepInfo.toString());

        return "/goods/rep/rep-add";
    }
    
    @RequestMapping(value = "/addAdaptListInit")
    public String addAdaptListInit(Model model, StockRepInfo stockRepInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + stockRepInfo.toString());
        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setShopId(stockRepInfo.getShopId());
        // stockRepAdaptDTO.setAdaptCountry("ZH");
        List<AreaInfo> areaInfos = this.getProvinceList();

        StockRepAdaptMainDTO stockRepAdaptMainDTO = gdsStockRSV.tagProvinceLevel(stockRepAdaptDTO);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = stockRepAdaptMainDTO.getStockRepAdaptDTOs();
        // 组装省份，标记该店铺已经覆盖省份

        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptProvince().equals(areaInfo.getProvinceCode())) {
                    areaInfo.setIfHasOver(true);
                    areaInfo.setIfCityLevel(repAdaptDTO.getIfCityLevel());

                }

            }

        }
        model.addAttribute("areaInfos", areaInfos);
        return "/goods/rep/repPop/rep-adapt";
    }

    /**
     * 
     * pageInit:(仓库管理界面初始化). <br/>
     * 
     * @author zjh
     * @param stockRepInfo
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/pageInit")
    public String pageInit(StockRepInfo stockRepInfo) {
        LogUtil.debug(MODULE, "请求参数为：" + stockRepInfo.toString());
        return "/goods/rep/rep-grid";
    }

    /**
     * 
     * listRep:(返回仓库列表). <br/>
     * 
     * @author zjh
     * @param model
     * @param stockRepInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/listRep")
    @ResponseBody
    public Model listRep(Model model, @Valid
    StockRepInfo stockRepInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + stockRepInfo.toString());

        // /后场服务所需要的DTO；
        StockRepReqDTO stockRepDTO = stockRepInfo.toBaseInfo(StockRepReqDTO.class);

        stockRepDTO.setShopId(stockRepInfo.getShopId());
        if (stockRepInfo.getRepName() != null) {
            stockRepDTO.setRepName(stockRepInfo.getRepName());
        }
        if (stockRepInfo.getStatus() == null) {
            stockRepDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        } else {
            stockRepDTO.setStatus(stockRepInfo.getStatus());
        }
        // 模拟一个后场返回的列表信息；
        PageResponseDTO<StockRepPageRespDTO> t = gdsStockRSV.queryStockRepInfoByShopId(stockRepDTO);

        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        if (t.getResult() == null) {
            t.setResult(new ArrayList<StockRepPageRespDTO>());
        }
        for (StockRepPageRespDTO stockRep : t.getResult()) {
            if (stockRep.getStatus() != null && stockRep.getStatus().equals("1")) {
                stockRep.setStatus("有效");

            } else {
                stockRep.setStatus("失效");
            }
            stockRep.setRepType(BaseParamUtil.fetchParamValue("GDS_REP_TYPE", stockRep.getRepType()));

        }
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);

        return super.addPageToModel(model, respVO);

    }

    /**
     * 
     * selCityAdapt:(初始化选择地市列表页面). <br/>
     * 
     * @author zjh
     * @param model
     * @param provinceCode
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/selAddCityAdapt")
    public String selCityAdapt(Model model, @RequestParam("provinceCode")
    String provinceCode, @RequestParam("shopId")
    Long shopId) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：provinceCode:" + provinceCode + ",shopId:" + shopId);

        List<AreaInfo> areaInfos = this.getCityList(provinceCode);

        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setAdaptProvince(provinceCode);
        stockRepAdaptDTO.setShopId(shopId);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = gdsStockRSV
                .queryStockRepAdaptCityByProvince(stockRepAdaptDTO);
        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptCity().equals(areaInfo.getCityCode())) {
                    areaInfo.setIfHasOver(true);

                }

            }

        }
        model.addAttribute("cityAreas", areaInfos);
        return "/goods/rep/repPop/rep-add-selcity";
    }

    /**
     * 
     * saveAddRep:(保存新增仓库). <br/>
     * 
     * @author zjh
     * @param stockAddInitInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/saveAddRep")
    public String saveAddRep(@Valid
    StockRepAddInitInfo stockAddInitInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + stockAddInitInfo.toString());
        StockRepMainReqDTO stockRepMainDTO = new StockRepMainReqDTO();
        JSONArray arrayTemp = JSONArray.fromObject(stockAddInitInfo.getNewStockRepAdaptVOsStr());
        @SuppressWarnings({ "unchecked" })
        List<StockRepAdaptReqDTO> newRepAdaptList = JSONArray.toList(arrayTemp,
                StockRepAdaptReqDTO.class);
        StockRepReqDTO stockRepDTO = new StockRepReqDTO();
        stockRepDTO.setRepName(stockAddInitInfo.getRepName());
        // stockRepMainDTO.setStaffId(stockAddInitInfo.getStaff().getId());
        stockRepMainDTO.setStaffId(stockAddInitInfo.getStaff().getId());
        stockRepDTO.setShopId(stockAddInitInfo.getShopId());

        stockRepDTO.setCodeType(GdsConstants.GdsStock.STOCK_CODE_TYPE_SELLER);
        stockRepDTO.setRepType(GdsConstants.GdsStock.STOCK_REP_TYPE_SEPERATE);
        ShopInfoResDTO shopInfoResDTO = shopInfoRSV.findShopInfoByShopID(stockRepDTO.getShopId());
        stockRepDTO.setCompanyId(shopInfoResDTO.getCompanyId());
        stockRepMainDTO.setStockRepDTO(stockRepDTO);
        stockRepMainDTO.setNewRepAdaptList(newRepAdaptList);
        stockRepMainDTO.setIfNew(true);
        gdsStockRSV.addStockRep(stockRepMainDTO);
        return "/goods/rep/rep-grid";

    }

    /**
     * 
     * delRep:(删除仓库). <br/>
     * 
     * @author zjh
     * @param delInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/delRep")
    public String delRep(StockRepDelInfo delInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + delInfo.toString());

        StockRepReqDTO stockRepDTO = new StockRepReqDTO();
        stockRepDTO.setId(delInfo.getId());
        // stockRepDTO.setStaffId(delInfo.getStaff().getId());
        stockRepDTO.setStaffId(delInfo.getStaff().getId());
        stockRepDTO.setShopId(delInfo.getShopId());
        ShopInfoResDTO shopInfoResDTO = shopInfoRSV.findShopInfoByShopID(delInfo.getShopId());
        stockRepDTO.setCompanyId(shopInfoResDTO.getCompanyId());
        gdsStockRSV.deleteStockRep(stockRepDTO);

        return "/goods/rep/rep-grid";

    }

    /**
     * 
     * editRep:(编辑仓库页面初始化). <br/>
     * 
     * @author zjh
     * @param model
     * @param editInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/editRep")
    public String editRep(Model model, StockRepEditInfo editInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + editInfo.toString());

        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setShopId(editInfo.getShopId());
        stockRepAdaptDTO.setRepCode(editInfo.getId());
        List<AreaInfo> areaInfos = this.getProvinceList();
        StockRepReqDTO stockRepDTO = new StockRepReqDTO();
        stockRepDTO.setId(editInfo.getId());
        StockRepRespDTO stockRep = gdsStockRSV.queryStockRepInfoById(stockRepDTO);
        StockRepAdaptMainDTO stockRepAdaptMainDTO = gdsStockRSV.tagProvinceLevel(stockRepAdaptDTO);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = stockRepAdaptMainDTO.getStockRepAdaptDTOs();
        // 组装省份，标记该店铺已经覆盖省份

        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptProvince().equals(areaInfo.getProvinceCode())) {
                    areaInfo.setIfHasOver(true);
                    areaInfo.setIfCityLevel(repAdaptDTO.getIfCityLevel());
                    areaInfo.setIfCRepHasOver(repAdaptDTO.getIfCurrentHas());
                }

            }

        }
        model.addAttribute("areaInfos", areaInfos);
        model.addAttribute("repName", stockRep.getRepName());
        model.addAttribute("id", stockRep.getId());
        model.addAttribute("shopId", stockRep.getShopId());

        return "/goods/rep/rep-edit";

    }

    // 模拟获取省份编码列表服务
    private List<AreaInfo> getProvinceList() throws Exception {
        List<AreaInfo> provinceList = new ArrayList<AreaInfo>();
        List<BaseAreaAdminRespDTO> areaAdminRespDTOs = BaseAreaAdminUtil
                .fetchChildAreaInfos(COUNTRY_DEFAULT);
        for (BaseAreaAdminRespDTO areaAdminRespDTO : areaAdminRespDTOs) {

            AreaInfo areaInfo = new AreaInfo();
            areaInfo.setAreaName(areaAdminRespDTO.getAreaName());
            areaInfo.setProvinceCode(areaAdminRespDTO.getAreaCode());
            areaInfo.setCountryCode(COUNTRY_DEFAULT);
            provinceList.add(areaInfo);
        }
        return provinceList;

    }

    private List<AreaInfo> getCityList(String provinceCode) throws Exception {
        List<AreaInfo> cityList = new ArrayList<AreaInfo>();
        List<BaseAreaAdminRespDTO> areaAdminRespDTOs = BaseAreaAdminUtil
                .fetchChildAreaInfos(provinceCode);

      //当是香港，澳门，台湾的时候此处返回值是Null
        if(StringUtil.isNotEmpty(areaAdminRespDTOs)){
	        for (BaseAreaAdminRespDTO areaAdminRespDTO : areaAdminRespDTOs) {
	            AreaInfo info = new AreaInfo();
	            info.setProvinceCode(provinceCode);
	            info.setCityCode(areaAdminRespDTO.getAreaCode());
	            info.setAreaName(areaAdminRespDTO.getAreaName());
	            info.setCountryCode(COUNTRY_DEFAULT);
	            cityList.add(info);
	        }
        }
        return cityList;

    }

    /**
     * 
     * selEditCityAdapt:(编辑页面选择城市页面初始化). <br/>
     * 
     * @author zjh
     * @param model
     * @param provinceCode
     * @param repCode
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/selEditCityAdapt")
    public String selEditCityAdapt(Model model, @RequestParam("provinceCode")
    String provinceCode, @RequestParam("repCode")
    Long repCode, @RequestParam("shopId")
    Long shopId) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + "provinceCode:" + provinceCode + ",repCode:" + repCode
                + ",shopId:" + shopId);

        List<AreaInfo> areaInfos = this.getCityList(provinceCode);

        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setAdaptProvince(provinceCode);
        stockRepAdaptDTO.setShopId(shopId);
        stockRepAdaptDTO.setRepCode(repCode);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = gdsStockRSV
                .queryStockRepAdaptCityByProvince(stockRepAdaptDTO);
        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptCity().equals(areaInfo.getCityCode())) {
                    areaInfo.setIfHasOver(true);
                    areaInfo.setIfCRepHasOver(repAdaptDTO.getIfCurrentHas());
                }

            }

        }
        model.addAttribute("cityAreas", areaInfos);
        return "/goods/rep/repPop/rep-edit-selcity";
    }

    /**
     * 
     * saveEditRep:(保存编辑仓库). <br/>
     * 
     * @author zjh
     * @param stockEditInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/saveEditRep")
    public String saveEditRep(@Valid
    StockRepEditInfo stockEditInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + stockEditInfo.toString());
        StockRepMainReqDTO stockRepMainDTO = new StockRepMainReqDTO();
        // 获取新增的仓库适用范围
        JSONArray arrayTemp = JSONArray.fromObject(stockEditInfo.getNewStockRepAdaptVOsStr());
        @SuppressWarnings({ "unchecked" })
        List<StockRepAdaptReqDTO> newRepAdaptList = JSONArray.toList(arrayTemp,
                StockRepAdaptReqDTO.class);
        // 获取取消的仓库适用范围
        JSONArray arrayTempDel = JSONArray.fromObject(stockEditInfo.getDelStockRepAdaptVOsStr());
        @SuppressWarnings({ "unchecked" })
        List<StockRepAdaptReqDTO> delRepAdaptList = JSONArray.toList(arrayTempDel,
                StockRepAdaptReqDTO.class);
        StockRepReqDTO stockRepDTO = new StockRepReqDTO();
        stockRepDTO.setRepName(stockEditInfo.getRepName());
        // stockRepMainDTO.setStaffId(stockEditInfo.getStaff().getId());
        stockRepMainDTO.setStaffId(stockEditInfo.getStaff().getId());
        stockRepDTO.setShopId(stockEditInfo.getShopId());
        ShopInfoResDTO shopInfoResDTO = shopInfoRSV.findShopInfoByShopID(stockEditInfo.getShopId());
        stockRepDTO.setCompanyId(shopInfoResDTO.getCompanyId());
        stockRepDTO.setId(stockEditInfo.getId());
        stockRepMainDTO.setStockRepDTO(stockRepDTO);
        stockRepMainDTO.setNewRepAdaptList(newRepAdaptList);
        stockRepMainDTO.setDelRepAdaptList(delRepAdaptList);
        stockRepMainDTO.setIfNew(true);
        gdsStockRSV.editStockRep(stockRepMainDTO);
        return "/goods/rep/rep-grid";
    }

    /**
     * 
     * check:(查看仓库页面初始化). <br/>
     * 
     * @author zjh
     * @param model
     * @param stockEditInfo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/check")
    public String check(Model model, StockRepEditInfo stockEditInfo) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + stockEditInfo.toString());

        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setShopId(stockEditInfo.getShopId());

        stockRepAdaptDTO.setRepCode(stockEditInfo.getId());

        StockRepReqDTO stockRepDTO = new StockRepReqDTO();
        stockRepDTO.setId(stockEditInfo.getId());
        StockRepRespDTO stockRep = gdsStockRSV.queryStockRepInfoById(stockRepDTO);
        List<AreaInfo> areaInfos = this.getProvinceList();

        StockRepAdaptMainDTO stockRepAdaptMainDTO = gdsStockRSV.tagProvinceLevel(stockRepAdaptDTO);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = stockRepAdaptMainDTO.getStockRepAdaptDTOs();
        // 组装省份，标记该店铺已经覆盖省份

        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptProvince().equals(areaInfo.getProvinceCode())) {
                    areaInfo.setIfHasOver(true);
                    areaInfo.setIfCityLevel(repAdaptDTO.getIfCityLevel());
                    areaInfo.setIfCRepHasOver(repAdaptDTO.getIfCurrentHas());
                }

            }

        }
        model.addAttribute("areaInfos", areaInfos);
        model.addAttribute("repName", stockRep.getRepName());
        model.addAttribute("id", stockRep.getId());
        model.addAttribute("shopId", stockRep.getShopId());

        return "/goods/rep/rep-check";
    }

    /**
     * 
     * selCheckCityAdapt:(查看某个省份下城市列表页面初始化). <br/>
     * 
     * @author zjh
     * @param model
     * @param provinceCode
     * @param repCode
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/selCheckCityAdapt")
    public String selCheckCityAdapt(Model model, @RequestParam("provinceCode")
    String provinceCode, @RequestParam("repCode")
    Long repCode, @RequestParam("shopId")
    Long shopId) throws Exception {
        LogUtil.debug(MODULE, "请求参数为：" + "provinceCode:" + provinceCode + ",repCode:" + repCode
                + ",shopId:" + shopId);
        List<AreaInfo> areaInfos = this.getCityList(provinceCode);

        StockRepAdaptReqDTO stockRepAdaptDTO = new StockRepAdaptReqDTO();
        stockRepAdaptDTO.setAdaptProvince(provinceCode);
        stockRepAdaptDTO.setShopId(shopId);
        stockRepAdaptDTO.setRepCode(repCode);
        List<StockRepAdaptRespDTO> stockRepAdaptDTOs = gdsStockRSV
                .queryStockRepAdaptCityByProvince(stockRepAdaptDTO);
        for (AreaInfo areaInfo : areaInfos) {
            for (StockRepAdaptRespDTO repAdaptDTO : stockRepAdaptDTOs) {
                if (repAdaptDTO.getAdaptCity().equals(areaInfo.getCityCode())) {
                    areaInfo.setIfHasOver(true);
                    areaInfo.setIfCRepHasOver(repAdaptDTO.getIfCurrentHas());
                }

            }

        }
        model.addAttribute("cityAreas", areaInfos);
        return "/goods/rep/repPop/rep-check-selcity";
    }

}
