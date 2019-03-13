package com.zengshi.ecp.busi.main.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.busi.main.vo.ComponentReqVO;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsSku2PropPropIdxReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsInfoQueryRSV;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsSkuInfoQueryRSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 商城-新书预售
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: <br>
 * Date:2015年11月7日下午4:23:15  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author zhanbh
 * @version  
 * @since JDK 1.6
 */
@Controller
@RequestMapping(value = "/newbook")
public class NewBookController extends EcpBaseController {
    
    private static String MODULE = NewBookController.class.getName();
    
//    private final int GDSDESCCONTENT_LENGTH = 115;
    
    //查询新书预售服务
    @Resource(name="gdsSkuInfoQueryRSV")
    private IGdsSkuInfoQueryRSV gdsSkuInfoQueryRSV;
    @Resource(name = "gdsInfoQueryRSV")
    private IGdsInfoQueryRSV gdsInfoQueryRSV;
    
    
    /**
     * qryNewBookList:(查询新书预售列表,用于组件). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/newbooksalelist")
    public String newBookSaleList(Model model, ComponentReqVO reqVO) {
        // 1.声明参数
        PageResponseDTO<GdsSkuInfoRespDTO> newBookPageResDto = new PageResponseDTO<GdsSkuInfoRespDTO>();
        ArrayList<ArrayList<GdsSkuInfoRespDTO>> newBookDatas =new  ArrayList<ArrayList<GdsSkuInfoRespDTO>>();//新书预售数据的二维数组，便于组件渲染
        List<GdsSkuInfoRespDTO> skuInfoRespDto = null;
        String standard = "";// 图片规格
        // 2.给参数设置
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())
                && StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        
        try {
            //查询数据
            newBookPageResDto = this.qryNewBookSale(reqVO);
            //扩充数据
            skuInfoRespDto = this.change2GdsInfoDetail(newBookPageResDto.getResult(), standard);
        } catch (Exception e) {
            // TODO: handle exception\
            LogUtil.error(MODULE, "获取数据异常", e);
            model.addAttribute("newBookList", null);
        }
        if(CollectionUtils.isEmpty(skuInfoRespDto)){
            skuInfoRespDto = new ArrayList<GdsSkuInfoRespDTO>();
        }
        //封装数据为二维数组
        int listSize = skuInfoRespDto.size();
        int step = reqVO.getPlaceSize();
        if(step<1){
            step = 1;
        }
        for(int i = 0 ; i < listSize; i+=step){
            ArrayList<GdsSkuInfoRespDTO> data = new ArrayList<GdsSkuInfoRespDTO>();
            for(int j = i ; (j < i + step) && (j < listSize); j++){
                data.add(skuInfoRespDto.get(j));
            }
            newBookDatas.add(data);
        }
        if(StringUtil.isBlank(reqVO.getMenuType())){//0 为首页  1为二级页面
            reqVO.setMenuType("0");
        }
        model.addAttribute("menuType", Integer.parseInt(reqVO.getMenuType()));
        model.addAttribute("versionType", reqVO.getVersionType());
        model.addAttribute("newBookList", newBookDatas);
        return "/main/newbooksale/newbookshader/new-book-shader";
    }
    
    /**
     * 
     * newBookPageInit:(新书预售分页初始化). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="newbookpageinit")
    public String newBookPageInit(Model model,ComponentReqVO reqVO){
        //1.声明必须的字段
        PageResponseDTO<GdsSkuInfoRespDTO> newBookPageResDto = new PageResponseDTO<GdsSkuInfoRespDTO>();
        EcpBasePageRespVO<Map> respVO = null;//分页数据容器
        String standard ="";//图片规格
        // 2.给参数设置
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())
                && StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }else{
            standard = "200x200!";
        }
        try{
             //查询新书预售数据
             newBookPageResDto = this.qryNewBookSale(reqVO);
             //扩充详情
             newBookPageResDto.setResult(this.change2GdsInfoDetail(newBookPageResDto.getResult(), standard));
             
             respVO = EcpBasePageRespVO.buildByPageResponseDTO(newBookPageResDto);
        }catch(Exception e){//错误处理
            e.printStackTrace();
            String errorMessage = "获取新书预售失败！";//错误信息
            model.addAttribute("versionType", errorMessage);
            super.addPageToModel(model, respVO);
        }
        model.addAttribute("versionType", reqVO.getVersionType());
        super.addPageToModel(model, respVO);
        return "/main/newbooksale/newbookpage/new-book-list";
    }
    
    /**
     * 
     * qryNewBookPage:(查询分页列表数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param model
     * @param reqVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="qrynewbookpage")
    public String qryNewBookPage(Model model,ComponentReqVO reqVO){
      //1.声明必须的字段
        PageResponseDTO<GdsSkuInfoRespDTO> newBookPageResDto = new PageResponseDTO<GdsSkuInfoRespDTO>();
        EcpBasePageRespVO<Map> respVO = null;//分页数据容器
        String standard ="";//图片规格
        String errorMessage = null; //错误信息
        // 2.给参数设置
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())
                && StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        }
        try{
             //查询新书预售数据
             newBookPageResDto = this.qryNewBookSale(reqVO);
             //扩充详情
             newBookPageResDto.setResult(this.change2GdsInfoDetail(newBookPageResDto.getResult(), standard));
             
             respVO = EcpBasePageRespVO.buildByPageResponseDTO(newBookPageResDto);
        }catch(Exception e){//错误处理
            LogUtil.error(MODULE, "获取新书预售异常",e);
            errorMessage = "获取新书预售失败！";
            model.addAttribute("errorMessage", errorMessage);
            super.addPageToModel(model, respVO);
        }
        super.addPageToModel(model, respVO);
        return "/main/newbooksale/newbookpage/child/child-list";
    }
    
    /**
     * 
     * qryNewBookSale:(查询新书预售的数据). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanbh 
     * @param reqVO
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    private PageResponseDTO<GdsSkuInfoRespDTO> qryNewBookSale(ComponentReqVO reqVO) throws Exception {
        //1.声明参数
        long publishTimeId = 1005l; // 出版日期id
        //限制商品个数
        if (reqVO.getGdsSize()!=null) {
            reqVO.setPageSize(reqVO.getGdsSize());
        }
        GdsSku2PropPropIdxReqDTO gdsSku2PropPropIdxReqDTO = reqVO.toBaseInfo(GdsSku2PropPropIdxReqDTO.class);
        GdsSkuInfoReqDTO gdsSkuInfoReqDTO = reqVO.toBaseInfo(GdsSkuInfoReqDTO.class);
        //2.设置参数
        //指定按出版日期查询
        gdsSku2PropPropIdxReqDTO.setPropId(publishTimeId);
        // 设置状态
        if (StringUtil.isNotBlank(reqVO.getStatus())) {
            gdsSku2PropPropIdxReqDTO.setGdsStatus(reqVO.getStatus());
            gdsSkuInfoReqDTO.setGdsStatus(reqVO.getStatus());
        } else {// 默认为待上架状态
            gdsSku2PropPropIdxReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
            gdsSkuInfoReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_WAITSHELVES);
        }
        //忽略propValue,beginTime以及endTime的值查询条件  达到查询出版日期为空的目的
        gdsSku2PropPropIdxReqDTO.setPropValueNullQuery(true);
        
        
        //3.查询数据
        PageResponseDTO<GdsSkuInfoRespDTO> gdsSkuRespDtoList = null;
        if("0".equals(reqVO.getVersionType())){//用于核心演示版本组件（新品预售）
            gdsSkuInfoReqDTO.setSkuQuery(new GdsOption.SkuQueryOption[]{SkuQueryOption.MAINPIC,SkuQueryOption.CAlDISCOUNT});            
            gdsSkuRespDtoList = gdsSkuInfoQueryRSV.queryGdsSkuInfoListPage(gdsSkuInfoReqDTO);
        }else{//用于人卫组件（新书预售）
            gdsSkuRespDtoList = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(gdsSku2PropPropIdxReqDTO);
        }
        return gdsSkuRespDtoList;
    }
    
    /**
     * 
     * change2GdsInfoDetail:(将商品数据扩充). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhanabh
     * @param list
     * @return 
     * @since JDK 1.6
     */
    private List<GdsSkuInfoRespDTO> change2GdsInfoDetail(List<GdsSkuInfoRespDTO> list,String standard)throws Exception{
        if (!CollectionUtils.isEmpty(list)) {
            for (GdsSkuInfoRespDTO dto : list) {
                if (dto != null) {
                    CmsGoodsDetailUtil.extendSkuInfo(dto, null, false, true, standard);
                    CmsGoodsDetailUtil.getSkuPromPrice(dto, CmsConstants.PlatformType.CMS_PLATFORMTYPE_01, null);
                }
            }
        }
        return list;
    }
    
    /**
     * newBookExpress:(获取新书速递商品信息). <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     * 
     * @author zhanbh 2015.10.26
     * @param model
     * @param gdsSize
     * @param placeWidth
     * @param placeHeight
     * @param status
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping(value = "/newbookexpress")
    @ResponseBody
    public Map<String, Object> newBookExpress(Model model,ComponentReqVO reqVO) throws Exception {
        LogUtil.info(MODULE, "==========reqVO :" + reqVO.toString() + ";" );

        // 1.声明必须的字段
        long publishTimeId = 1005l; // 出版日期id
        Timestamp endTime = null; // 结束时间
        Timestamp beginTime = null; // 开始时间

        // 图片规格
        String standard = "";
        if (StringUtil.isNotEmpty(reqVO.getPlaceWidth())&& StringUtil.isNotEmpty(reqVO.getPlaceHeight())) {
            standard = reqVO.getPlaceWidth() + "x" + reqVO.getPlaceHeight() + "!";
        } 

        // 2. 查询后场 获取商品信息
        GdsSku2PropPropIdxReqDTO gdsSku2PropPropIdxReqDTO = new GdsSku2PropPropIdxReqDTO();

        // 设置状态
        if (StringUtil.isNotEmpty(reqVO.getStatus())) {// 状态
            gdsSku2PropPropIdxReqDTO.setStatus(reqVO.getStatus());
        }else {// 默认为上架状态
            gdsSku2PropPropIdxReqDTO.setGdsStatus(GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES);
        }

        // 设置出版日期
        gdsSku2PropPropIdxReqDTO.setPropId(publishTimeId);
        endTime = DateUtil.getSysDate();
        beginTime = DateUtil.getOffsetYearsTime(endTime, -1);
        
        gdsSku2PropPropIdxReqDTO.setBeginTime(beginTime);
        gdsSku2PropPropIdxReqDTO.setEndTime(endTime);

        // 商品分页
        if (StringUtil.isNotEmpty(reqVO.getGdsSize())) {
            gdsSku2PropPropIdxReqDTO.setPageNo(1);
            gdsSku2PropPropIdxReqDTO.setPageSize(reqVO.getGdsSize());
        } else {// 默认8个
            gdsSku2PropPropIdxReqDTO.setPageNo(1);
            gdsSku2PropPropIdxReqDTO.setPageSize(8);
        }

        PageResponseDTO<GdsSkuInfoRespDTO> pageDdsSkuRespDto = gdsSkuInfoQueryRSV.queryGdsSkuInfoPaging(gdsSku2PropPropIdxReqDTO);
        // 扩展信息
        pageDdsSkuRespDto.setResult(this.change2GdsInfoDetail(pageDdsSkuRespDto.getResult(), standard));
            
        // 4.返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("gdsList", pageDdsSkuRespDto.getResult());
        return resultMap;
    }
    
}