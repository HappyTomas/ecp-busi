/** 
 * Project Name:ecp-web-demo 
 * File Name:DemoController.java 
 * Package Name:com.zengshi.ecp.busi.demo.controller 
 * Date:2015-8-5下午2:51:38 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.busi.staff.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zengshi.ecp.base.controller.EcpBaseUpload;
import com.zengshi.ecp.base.mvc.JsonResultThreadLocal;
import com.zengshi.ecp.base.mvc.UploadResultModal;
import com.zengshi.ecp.base.mvc.annotation.NativeJson;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.PromConst;
import com.zengshi.ecp.busi.staff.vo.CompanyInfoVO;
import com.zengshi.ecp.busi.staff.vo.ShopInfoVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.AreaCodeDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.CompanyInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipTempPricesReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopShipTempReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.ICompanyManageRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopCacheRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.MoneyUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * Title: ECP <br>
 * Project Name:ecp-web-demo <br>
 * Description: <br>
 * Date:2015-8-5下午2:51:38  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Controller
@RequestMapping(value="/company")
public class CompanyMgrController extends EcpBaseUpload {
    
    private static String MODULE = CompanyMgrController.class.getName();
    
    
    @Resource
    private ICompanyManageRSV companyManageRSV;
    
    @Resource
    private IShopCacheRSV shopCacheRSV;
    
    
    /**
     * 
     * grid:(待审核页). <br/> 
     * 
     * @author wangbh
     * @return 
     * @since JDK 1.7
     */

    @RequestMapping(value="/grid")
    public String grid(){
    	return "/staff/company/company-grid";
    }
    
    @RequestMapping(value="/add")
    public String add(Model model){
        return "/staff/company/company-add";
    }
    
    @RequestMapping(value="/edit")
    public String edit(Model model,@RequestParam("id")String ids){
        CompanyInfoResDTO dto = companyManageRSV.findCompanyInfoById(Long.parseLong(ids));
        model.addAttribute("company", dto);
        model.addAttribute("LicencePath", ImageUtil.getImageUrl(dto.getLicencePath()));
        model.addAttribute("LegalPersonImage", ImageUtil.getImageUrl(dto.getLegalPersonImage()));
        model.addAttribute("taxPath", ImageUtil.getStaticDocUrl(dto.getTaxPath(), "doc"));
        model.addAttribute("companyId", ids);
        return "/staff/company/company-edit";
    }
    
    @RequestMapping(value="/addshop")
    public String addShop(Model model,@RequestParam("companyId")String companyId){
        //获取配送方式系统参数列表
        List<BaseParamDTO> shopDistributeList =BaseParamUtil.fetchParamList("STAFF_SHOP_DISTRIBUTION_WAY");
        
        model.addAttribute("shopDistributeList", shopDistributeList);
        model.addAttribute("companyId", companyId);
        return "/staff/company/company-addshop";
    }
    
    
    @RequestMapping(value="/addshopmsg")
    @ResponseBody
    public EcpBaseResponseVO addshopmsg(@Valid ShopInfoVO shopInfo) throws Exception{
        LogUtil.info(this.getClass().getName(), "========addshop");
        if (StringUtil.isNotBlank(shopInfo.getIfFree())) {
        	shopInfo.setIfFree(shopInfo.getIfFree().substring(shopInfo.getIfFree().length() - 1));
        }
        ShopInfoReqDTO infodto = new ShopInfoReqDTO();
        ObjectCopyUtil.copyObjValue(shopInfo, infodto, null, false);
        infodto.setCompanyId(Long.parseLong(shopInfo.getCompanyId()));
        infodto.setCreateStaff(infodto.getStaff().getId());
        infodto.setShopStatus(StaffConstants.shopInfo.SHOP_STATUS_NOMAL);
        infodto.setOnlineSupported(StaffConstants.shopInfo.SHOP_ONLINE_SUPPORTED_YES);
        infodto.setStaffCode(shopInfo.getStaffCode());
        infodto.setStaffPasswd(shopInfo.getStaffPasswd());
        infodto.setLogoPath(shopInfo.getLogoMongodbKey());//保存图片地址
        infodto.setOfflineSupported(shopInfo.getOfflineSupported());
        
        //解析运费模版信息
        ShopShipTempReqDTO shipTemReqDto = new ShopShipTempReqDTO();
        ObjectCopyUtil.copyObjValue(shopInfo, shipTemReqDto, null, false);
        
        parseShopShipTempInfo(shipTemReqDto, shopInfo);
        
        infodto.setShopShipTempDto(shipTemReqDto);
        companyManageRSV.saveShopInfo(infodto);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    private void parseShopShipTempInfo(ShopShipTempReqDTO infodto, ShopInfoVO shopInfo)
    {
        if(StringUtil.isNotBlank(shopInfo.getSeniorFreeParam())){
            //解析高级运费模板配置
            parseSeniorFree(infodto,shopInfo.getSeniorFreeParam());
        }
        if(StringUtil.isNotBlank(shopInfo.getDefaultFreeParam())){
            //解析默认运费模板配置
            parseDeFaultFree(infodto,shopInfo.getDefaultFreeParam());
        }
    }

    private void parseSeniorFree(ShopShipTempReqDTO shopshipdto,String str){
        JSONArray seniorlist = JSONArray.parseArray(str);
        int size = seniorlist.size();
        ShopShipTempPricesReqDTO reqDto = null;
        if(size >= 1){
            for(int i = 0;i < size ;i++){
                reqDto = new ShopShipTempPricesReqDTO();
                // 放进"{firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
                //"continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:"+areaCodeList+"},";
                JSONObject object = (JSONObject) seniorlist.get(i);
                String firstAmount = object.getString("firstAmount");
                String firstPrice = object.getString("firstPrice");
                String continueAmount = object.getString("continueAmount");
                String continuePrice = object.getString("continuePrice");
                String freeAmount = object.getString("freeAmount");
                String areaCodeList = object.getString("areaCodeList");
                String pricingMode = object.getString("pricingMode");
                if("[]".equals(areaCodeList)){
                    throw new BusinessException("web.gds.200009");
                }else if(StringUtil.isNotBlank(areaCodeList)){
                    parseAddress(reqDto,areaCodeList);
                }
                reqDto.setCountryCode(PromConst.SysModel.COUNTRY_DEFAULT);
                reqDto.setPricingMode(pricingMode);
                if (StringUtil.isNotBlank(firstAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shopshipdto.getShipTemplateType())) {
                        reqDto.setFirstAmount(MoneyUtil.convertYuanToCent(firstAmount));

                    } else {
                        reqDto.setFirstAmount(Long.parseLong(firstAmount));
                    }
                }
                if (StringUtil.isNotBlank(firstPrice)) {
                    reqDto.setFirstPrice(MoneyUtil.convertYuanToCent(firstPrice));
                }
                if (StringUtil.isNotBlank(continueAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shopshipdto
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
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shopshipdto
                            .getShipTemplateType())) {
                        reqDto.setFreeAmount(MoneyUtil.convertYuanToCent(freeAmount));

                    } else {
                        reqDto.setFreeAmount(Long.parseLong(freeAmount));
                    }
                }
                shopshipdto.getShiptempPriceReqDTOList().add(reqDto);
            }
        }
    }
    
    private void parseAddress(ShopShipTempPricesReqDTO reqDto, String areaCodeList){
        JSONArray seniorlist = JSONArray.parseArray(areaCodeList);
        List<AreaCodeDTO> list = new ArrayList<AreaCodeDTO>();
        AreaCodeDTO area = null;
        int size = seniorlist.size();
        if (size >= 1) {
            for (int i = 0; i < size; i++) {

                JSONObject object = (JSONObject) seniorlist.get(i);
                String provinceCode = object.getString("provinceCode");
                // String provinceName = object.getString("provinceName");
                // String areaLevl = object.getString("areaLevel");
                String cityList = object.getString("cityList");
                if (StringUtil.isNotBlank(cityList)) {
                    JSONArray clist = JSONArray.parseArray(cityList);
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
    
    private void parseDeFaultFree(ShopShipTempReqDTO shipdto, String freeList){
        JSONArray seniorlist = JSONArray.parseArray(freeList);
        int size = seniorlist.size();
        ShopShipTempPricesReqDTO reqDto = null;
        if(size >= 1){
            for(int i = 0;i < size ;i++){
                reqDto = new ShopShipTempPricesReqDTO();
                // 放进"{firstAmount:'"+first+"',firstPrice:'"+free+"',continueAmount:'"+second+"'," +
                //"continuePrice:'"+secondFree+"',freeAmount:'"+noFree+"',areaCodeList:"+areaCodeList+"},";
                JSONObject object = (JSONObject) seniorlist.get(i);
                String firstAmount = object.getString("firstAmount");
                String firstPrice = object.getString("firstPrice");
                String continueAmount = object.getString("continueAmount");
                String continuePrice = object.getString("continuePrice");
                String freeAmount = object.getString("freeAmount");
                String areaCodeList = object.getString("areaCodeList");
                String pricingMode = object.getString("pricingMode");
                if(StringUtil.isNotBlank(areaCodeList)){
                    parseAddress(reqDto,areaCodeList);
                }
                reqDto.setPricingMode(pricingMode);
                reqDto.setProvinceCode(GdsConstants.GdsShiptemp.DEFAULT_AREA_CODE);
                if (StringUtil.isNotBlank(firstAmount)) {
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shipdto
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
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shipdto
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
                    if (GdsConstants.GdsShiptemp.GDS_SHIPMENT_BY_MONEY.equals(shipdto
                            .getShipTemplateType())) {
                        reqDto.setFreeAmount(MoneyUtil.convertYuanToCent(freeAmount));
                    } else {
                        reqDto.setFreeAmount(Long.parseLong(freeAmount));
                    }
                }
                shipdto.getShiptempPriceReqDTOList().add(reqDto);
            }
        }
    }
    
    @RequestMapping(value="/editcompany")
    @ResponseBody
    public EcpBaseResponseVO editCompany(@Valid CompanyInfoVO companyVo,@RequestParam("companyId")String companyId) throws Exception{
        CompanyInfoReqDTO companyInfoIN = new CompanyInfoReqDTO();
        ObjectCopyUtil.copyObjValue(companyVo, companyInfoIN, null, false);
        companyInfoIN.setUpdateStaff(companyInfoIN.getStaff().getId());
        companyInfoIN.setId(Long.parseLong(companyId));
        companyManageRSV.updateCompanyInfo(companyInfoIN);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    
    @RequestMapping(value="/addcompany")
    @ResponseBody
    public EcpBaseResponseVO addCompany(@Valid CompanyInfoVO companyVo) throws Exception{
        CompanyInfoReqDTO companyInfoIN = new CompanyInfoReqDTO();
        ObjectCopyUtil.copyObjValue(companyVo, companyInfoIN, null, false);
        companyInfoIN.setSource(StaffConstants.companyInfo.SOURCE_FROM_MANAGE);
        companyInfoIN.setCreateStaff(companyInfoIN.getStaff().getId());
        companyInfoIN.setUpdateStaff(companyInfoIN.getStaff().getId());
        companyInfoIN.setCompanyCreateStaff(companyInfoIN.getStaff().getId());
        companyManageRSV.saveCompanyInfo(companyInfoIN);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        return vo;
    }
    
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo,@Valid @ModelAttribute("companyName") String companyName) throws Exception{
        CompanyInfoReqDTO info = vo.toBaseInfo(CompanyInfoReqDTO.class);
        if(!StringUtil.isBlank(companyName)){
        info.setCompanyName(companyName);
        }
        PageResponseDTO<CompanyInfoResDTO> t = companyManageRSV.listCompanyInfo(info);
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        return super.addPageToModel(model, respVO);
        
    }
    
    @RequestMapping(value = "/getshoplist")
    @ResponseBody
    public Map<Long,ShopInfoResDTO> getShopList(@RequestParam("companyId")String companyId) throws Exception{
        Map<Long,ShopInfoResDTO> map = new HashMap<Long, ShopInfoResDTO>();
        if(StringUtil.isNotBlank(companyId)){
        map = shopCacheRSV.getCompanyShop(Long.parseLong(companyId));
        }
       return  map;
    }
    
    /**
     * 
     * updateAdminFlag:(更新企业状态). <br/> 
     * 
     * @author huangxl 
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping(value="/updatestatus")
    @ResponseBody
    public EcpBaseResponseVO updateCompanyStatus(@RequestParam("status")String status,@RequestParam("companyId")Long companyId) throws Exception{
        EcpBaseResponseVO res = new EcpBaseResponseVO();
        CompanyInfoReqDTO req = new CompanyInfoReqDTO();
        req.setId(companyId);
        if ("invalid".equals(status)) {
            req.setStatus(StaffConstants.companyInfo.COMPANY_STATUS_INVALID);//失效
        } else if ("valid".equals(status)) {
            req.setStatus(StaffConstants.companyInfo.COMPANY_STATUS_VALID);//生效
        }
        companyManageRSV.updateCompanyStatus(req);
        res.setResultMsg(ResourceMsgUtil.getMessage("web.staff.101001", new String[]{}));
        return res;
    }
    @RequestMapping(value = "/taxpath")
    @ResponseBody
    public String licenceImg1(@RequestParam("fileId") String fileId) throws Exception {
    	String Url = ImageUtil.getStaticDocUrl(fileId, "doc");
        return Url;
    }
    
    /**
     * 
     * fileUpload:(上传图片). <br/> 
     * 
     * @author linby
     * @param file
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */
    @RequestMapping("/shoplogoUpload")
    @ResponseBody
    @NativeJson(true)
    public String fileUpload(@RequestParam(value = "uploadFileObj", required = false) MultipartFile file,
    		Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	JsonResultThreadLocal.set(false);
    	
    	String result = this.doUpload(file, request, response);
    	return result;
    }

    /**
     * 文件上传自定义校验
     * 限制店铺logo图片宽高为 300X200
     */
	@Override
	protected UploadResultModal customUploadHandle(MultipartFile file, HttpServletRequest request) throws Exception {
		UploadResultModal result = null;
		//限制店铺logo图片宽高为 300X200
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(file.getInputStream());
		} catch (IOException e) {
			LogUtil.error(MODULE, e.getMessage());
			return getErrorModal("图片读取异常");
		}   
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight(); 
		
		if(width>300 && height>200){
			return getErrorModal("图片限制宽高规格小于：300x200,请置换为合格文件");
		}else{
			return null;
		}		
	}

	@Override
	protected String customEditorUploadHandle(MultipartFile file, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}


