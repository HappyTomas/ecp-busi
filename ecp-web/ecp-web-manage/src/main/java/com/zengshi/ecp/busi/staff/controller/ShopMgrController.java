package com.zengshi.ecp.busi.staff.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageReqVO;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.staff.vo.ShopCacheListVO;
import com.zengshi.ecp.busi.staff.vo.ShopExpressVO;
import com.zengshi.ecp.busi.staff.vo.ShopInfoVO;
import com.zengshi.ecp.busi.staff.vo.ShopSelectVO;
import com.zengshi.ecp.busi.staff.vo.TmpTestVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShiptempRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsShop2ShipmentReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsShiptemRSV;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopExpressResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopSelectReqDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffUtil;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseExpressRespDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IBaseExpressRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年8月22日下午4:26:46  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author PJieWin
 * @version  
 * @since JDK 1.6
 * 
 * 店铺信息管理Controller类
 */
@Controller
@RequestMapping(value="/shop")
public class ShopMgrController  extends EcpBaseController {
    @Resource
    private IShopInfoRSV shopInfoRSVDubbo;
    @Resource
    private IBaseExpressRSV baseExpressRSV;
    
    @Resource
    private IGdsShiptemRSV iGdsShiptempRVS;
    
    
    private static String MODULE = ShopMgrController.class.getName();
    
    @RequestMapping("/grid")
    public String grid(Model model,@RequestParam(value = "companyId",required=false)String companyId){
        model.addAttribute("companyId", companyId);
        return "/staff/shop/shop-gird";
    }
    
    @RequestMapping(value = "/grid/{companyId}")
    public String grids(Model model,@PathVariable(value="companyId") String companyId) {
        model.addAttribute("companyId", companyId);
        return "/staff/shop/shop-gird";
    }
    
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, EcpBasePageReqVO vo,@Valid ShopSelectVO selectVO,@RequestParam("companyId")String companyId) throws Exception{
        
        LogUtil.info(MODULE, "======   店铺列表查询    开始     ======");
        //1.获取查询条件，转换成DTO
        ShopSelectReqDTO dto = vo.toBaseInfo(ShopSelectReqDTO.class);
        dto.setShopName(selectVO.getShopName());
        dto.setHotShowSupported(selectVO.getHotShowSupported());
        if(!StringUtil.isBlank(companyId)){
        dto.setCompanyID(Long.parseLong(companyId));
        }
        PageResponseDTO<ShopInfoResDTO> listDto = shopInfoRSVDubbo.listShopInfoByCond(dto);
        
//        PageResponseDTO<CustAuthstrDTO> t = custCheckRSV.queryCustCheckList(info); 
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(listDto);
        
        LogUtil.info(MODULE, "======   店铺列表查询    结束     ======");
        return super.addPageToModel(model, respVO);
        
    }
    @RequestMapping(value="/valid")
    @ResponseBody
    public EcpBaseResponseVO valid(@RequestParam("id")String id) throws Exception{
        LogUtil.info(MODULE, "======== 店铺失效  开始   =======");
        ShopInfoReqDTO req = new ShopInfoReqDTO();
        req.setId(Long.parseLong(id));
        //1.对店铺状态进行校验，则已经是失效状态，则抛出异常
        shopInfoRSVDubbo.invalidShop(req);
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        
        LogUtil.info(MODULE, "======== 店铺失效  结束   =======");
        return vo;
    }
    @RequestMapping(value="/active")
    @ResponseBody
    public EcpBaseResponseVO active(@RequestParam("id")String id) throws Exception{
        LogUtil.info(MODULE, "======== 店铺生效  开始   =======");
        
        //1.对店铺状态进行校验，则已经是生效状态，则抛出异常
        shopInfoRSVDubbo.activeShop(Long.valueOf(id));
        
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        
        LogUtil.info(MODULE, "======== 店铺生效  结束   =======");
        return vo;
    }
    @RequestMapping(value="/edit")
    public String edit(Model model,@RequestParam("shopId")String shopId) throws Exception{
        LogUtil.info(MODULE, "======== 编辑店铺  开始   =======");
        //数据填充
        ShopInfoResDTO dto =  shopInfoRSVDubbo.findShopInfoByShopID(Long.parseLong(shopId));
        
        //获取店铺配置的配送方式
        Set<String> distributionSet = new HashSet<String>();
        distributionSet.clear();
        String distribution =  dto.getDistribution();
        if(!StringUtil.isBlank(distribution))
        {
            String[] distArray = distribution.split(",");
            for(String da : distArray)
            {
                distributionSet.add(da);
            }
        }
        //获取配送方式系统参数列表
        List<BaseParamDTO> shopDistributeList =BaseParamUtil.fetchParamList("STAFF_SHOP_DISTRIBUTION_WAY");
        
        model.addAttribute("distributionSet", distributionSet);
        model.addAttribute("shopDistributeList", shopDistributeList);
        model.addAttribute("shopdto", dto);
        model.addAttribute("shopId", shopId);
        
        LogUtil.info(MODULE, "======== 编辑店铺  结束   =======");

        return "/staff/shop/shop-edit";
    }
    
    @RequestMapping(value="/editshop")
    @ResponseBody
    public EcpBaseResponseVO editShop(@Valid @ModelAttribute ShopInfoVO infoVO) throws Exception{
        LogUtil.info(MODULE, "======== 编辑更新店铺  开始   =======");
        ShopInfoReqDTO dto = new ShopInfoReqDTO();
        ObjectCopyUtil.copyObjValue(infoVO, dto, null, false);
        
        //将店铺Logo的mongoDb的key值，存储在LogoPath字段中
        if(!StringUtil.isBlank(infoVO.getLogoMongodbKey()))
        {
            dto.setLogoPath(infoVO.getLogoMongodbKey());
        }
        //数据填充
        shopInfoRSVDubbo.updateShopInfo(dto);
        EcpBaseResponseVO vo1 = new EcpBaseResponseVO();
        vo1.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        LogUtil.info(MODULE, "======== 编辑更新店铺  结束   =======");

        return vo1;
    }
    
    @RequestMapping(value="/express")
    public String editShopExpress(Model model,@RequestParam(value = "shopId",required=false)String shopId) throws Exception
    {
        LogUtil.info(MODULE, "========= 编辑店铺物流信息      开始 ============");
        
        //获取所有物流列表信息
        BaseExpressReqDTO sdto = new BaseExpressReqDTO();
        List<BaseExpressRespDTO> expressListBack = baseExpressRSV.fetchActiveExpressInfo(sdto);
        List<TmpTestVO> expressList = new ArrayList<TmpTestVO>(expressListBack.size());
        
        for(BaseExpressRespDTO dto: expressListBack)
        {
        	TmpTestVO vo = new TmpTestVO();
        	vo.setId(String.valueOf(dto.getId()));
        	vo.setExpressFullName(dto.getExpressFullName());
        	vo.setExpressName(dto.getExpressName());
        	expressList.add(vo);
        }
        //获取该店铺对应的物流列表
        Map<String, ShopExpressResDTO> shopexpressMap = shopInfoRSVDubbo.listShopExpress(Long.valueOf(shopId));
        
        
        model.addAttribute("expressList", expressList);
        model.addAttribute("shopexpressMap", shopexpressMap);
        
        LogUtil.info(MODULE, "========= 编辑店铺物流信息      结束 ============");

        return "/staff/shop/express/shop-express";
    }
    @RequestMapping(value="/saveexpress")
    @ResponseBody
    public EcpBaseResponseVO editShopExpress(@Valid @ModelAttribute ShopExpressVO seVo) throws Exception{
    	
    	
        LogUtil.info(MODULE, "======== 保存店铺物流信息  开始   =======");
        
        System.out.println("======= 当前该店铺物流信息： "+seVo.toString());
        //数据填充
        ShopExpressReqDTO reqDTO = new ShopExpressReqDTO();
        ObjectCopyUtil.copyObjValue(seVo, reqDTO, null, false);
        
        shopInfoRSVDubbo.updateShopExpress(reqDTO);
        EcpBaseResponseVO vo1 = new EcpBaseResponseVO();
        vo1.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        LogUtil.info(MODULE, "======== 保存店铺物流信息   结束   =======");

        return vo1;
    }
    @RequestMapping(value = "/shipgrid")
    public String shipgrid(Model model, @RequestParam(value="shopid",required=false)String shopid) {
        
        LogUtil.info(MODULE, "运费模版配置获取到的店铺id："+shopid);
        //查询默认店铺运费模版
        GdsShop2ShipmentReqDTO gdsshop2shipmentreqdto= new GdsShop2ShipmentReqDTO();
        gdsshop2shipmentreqdto.setShopId(Long.valueOf(shopid));
        GdsShiptempRespDTO defaultShiptempRespDTO = null;
        try {
            defaultShiptempRespDTO = iGdsShiptempRVS.queryShopDefaultShipMent(gdsshop2shipmentreqdto);

        } catch (Exception e) {
            // TODO: handle exception
            LogUtil.info(MODULE, "该店铺["+shopid+"]查询默认运费模版为null");
        }
        
        model.addAttribute("tempInfo", defaultShiptempRespDTO);
        model.addAttribute("shopid", shopid);
        return "/staff/shop/ship/ship-grid";
    }
    @RequestMapping("/shiplist")
    @ResponseBody
    public Model shiplist(Model model, EcpBasePageReqVO vo,@RequestParam("shopid")String shopid) throws Exception{
        
        LogUtil.info(MODULE, "======   店铺运费模版列表查询    开始     ======");
        //1.获取查询条件，转换成DTO
        GdsShiptempReqDTO dto = vo.toBaseInfo(GdsShiptempReqDTO.class);
        dto.setShopId(Long.valueOf(shopid));
        PageResponseDTO<GdsShiptempRespDTO> pageinfo =  iGdsShiptempRVS.queryGdsShipTemp(dto);
        
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageinfo);
        
        LogUtil.info(MODULE, "======   店铺运费模版列表查询      结束     ======");
        return super.addPageToModel(model, respVO);
        
    }
    @RequestMapping(value="/setDefaultShip")
    @ResponseBody
    public EcpBaseResponseVO setDefaultShip(@RequestParam("shopid")String shopid, @RequestParam("shipid")String shipid) throws Exception{
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
        
        LogUtil.info(MODULE, "======== 编辑店铺默认运费模版  开始   =======");
        
        LogUtil.debug(MODULE, "获取到的参数信息: shopid="+shopid+",shipid"+shipid);

        GdsShiptempReqDTO req=new GdsShiptempReqDTO();
        req.setShopId(Long.valueOf(shopid));
        req.setId(Long.valueOf(shipid));
        GdsShiptempRespDTO resp=iGdsShiptempRVS.getSingleGdsShipTemp(req);
		if (resp == null) {
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			vo.setResultMsg("不存在对应的运费模板！");
			return vo;
		}else if(StringUtil.isNotBlank(resp.getCatgCode())){
			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
			vo.setResultMsg("分类运费模板不允许设置为店铺默认运费模板！");
			return vo;
		}
		
        GdsShop2ShipmentReqDTO gdsshop2shipmentreqdto = new GdsShop2ShipmentReqDTO();
        gdsshop2shipmentreqdto.setShopId(Long.valueOf(shopid));
        gdsshop2shipmentreqdto.setShipmentId(Long.valueOf(shipid));
        
        iGdsShiptempRVS.editGdsShop2Shiptemp(gdsshop2shipmentreqdto);
        
        vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        LogUtil.info(MODULE, "======== 编辑店铺默认运费模版   结束   =======");

        return vo;
    }
    
    /**
     * 
     * cacheList:(从缓存获取店铺列表). <br/> 
     * 
     * @author huangxl5 
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value="/cache/list")
    @ResponseBody
    public Object cacheList() throws Exception{
    	Map<Long,ShopInfoResDTO> map = new HashMap<Long, ShopInfoResDTO>();
    	List<ShopCacheListVO> list = new ArrayList<ShopCacheListVO>();
        try {
            map = StaffUtil.getShopCache();
            for (ShopInfoResDTO shop : map.values()) {
            	ShopCacheListVO vo = new ShopCacheListVO();
            	vo.setCompanyID(shop.getCompanyId());
            	vo.setId(shop.getId());
            	vo.setShopFullName(shop.getShopFullName());
            	vo.setShopName(shop.getShopName());
            	vo.setShopGrade(shop.getShopGrade());
            	list.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

