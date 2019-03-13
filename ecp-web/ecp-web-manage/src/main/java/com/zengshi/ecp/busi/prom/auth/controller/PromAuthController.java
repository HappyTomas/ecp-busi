package com.zengshi.ecp.busi.prom.auth.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.prom.PromConst;
import com.zengshi.ecp.busi.prom.auth.vo.AuthShopVO;
import com.zengshi.ecp.busi.prom.auth.vo.PromType4ShopVO;
import com.zengshi.ecp.busi.prom.auth.vo.QueryPromType4ShopVO;
import com.zengshi.ecp.busi.prom.auth.vo.ShopVO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromTypeResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromAuthRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-prom <br>
 * Description: <br>
 * Date:2015-8-18下午2:51:38 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangjx
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/promauth")
public class PromAuthController extends EcpBaseController {

    @Resource
    private IPromAuthRSV promAuthRSV;

    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private IPromRSV promRSV;
    
    /**
     * 平台促销类型授权店铺功能
     */
    private static String MODULE = PromAuthController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author huangjx
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model) {
        // 初始化加载 促销类型下拉框值（读取缓存）
        List<BaseParamDTO> statusList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_TYPE_STATUS);
        model.addAttribute("statusList", statusList);
        return "/prom/auth/auth-grid";
    }

    /**
     * 授权列表查询 初始化列表和查询按钮功能调用
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author huangjx
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, QueryPromType4ShopVO vo) throws Exception {

        // 后场服务所需要的DTO；
        QueryPromType4ShopDTO queryDTO = vo.toBaseInfo(QueryPromType4ShopDTO.class);

        // 默认查询 有效状态数据
        if (StringUtil.isEmpty(vo.getStatus())) {
            vo.setStatus(PromConst.PromType4Shop.STATUS_1);
        }
        // 99 查询全部状态数据
        if (PromConst.STATUS_99.equals(vo.getStatus())) {
            vo.setStatus(null);
        }
        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, queryDTO, "", false);

        // 调用服务
        PageResponseDTO<PromType4ShopResponseDTO> page = promAuthRSV
                .queryPromType4ShopForPage(queryDTO);

         if(CheckPageNull.checkPageNull(page)){
             ShopInfoResDTO  shopDTO=new ShopInfoResDTO ();
               for(PromType4ShopResponseDTO dto:page.getResult()){
                  // dto.setPromClassName(BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE_PROM_CLASS, dto.getPromClass()));
                   shopDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                   //dto.setPromTypeName( BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE, dto.getPromTypeCode()));
                   if(shopDTO!=null){
                       dto.setShopName(shopDTO.getShopName()); 
                   }
                   //dto.setStatusName(BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE4SHOP_STATUS, dto.getStatus()));
               }
         }
        
        // 调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);

        return super.addPageToModel(model, respVO);
    }

    /**
     * 授权 新增链接页面
     * 
     * @param model
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("promType4ShopVO", new PromType4ShopVO());
        return "/prom/auth/auth-add-form";
    }
    
    /**
     * 授权 新增链接页面
     * 
     * @param model
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/adds")
    public String adds(Model model) {
        model.addAttribute("promType4ShopVO", new PromType4ShopVO());
        PromTypeDTO promTypeDTO = new PromTypeDTO();
        promTypeDTO.setStatus(PromConstants.PromType.STATUS_1);
        List<PromTypeResponseDTO> promTypeList = promRSV.queryPromTypeList(promTypeDTO);
        model.addAttribute("promTypeList", promTypeList);
        return "/prom/auth/auths-add-form";
    }
    
    /**
     * 授权 编辑 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/edit")
    public String edit(Model model, @RequestParam("id") String id) {
        
        // id 授权列表id
        PromType4ShopVO promType4ShopVO = new PromType4ShopVO();

        model.addAttribute("promType4ShopVO", promType4ShopVO);
        
        List<BaseParamDTO> statusList=new ArrayList<BaseParamDTO>();

        try {
            //状态列表 下拉框
            statusList= BaseParamUtil.fetchParamList(PromConst.PromKey.PROM_TYPE_STATUS);
            
            model.addAttribute("statusList", statusList);
            
            // id 为空 时，new long 报错 可被系统统计页面拦截，因此这里可以不处理
            PromType4ShopDTO promType4ShopDTO=new PromType4ShopDTO();
            promType4ShopDTO.setId(new Long(id));
            PromType4ShopResponseDTO p = promAuthRSV.queryPromType4ShopById(promType4ShopDTO);

            ObjectCopyUtil.copyObjValue(p, promType4ShopVO, "", false);
            
            ShopInfoResDTO  shopDTO =shopInfoRSV.findShopInfoByShopID(new Long(promType4ShopVO.getShopId()));
            if(shopDTO!=null){
                promType4ShopVO.setShopName(shopDTO.getShopName());
            }
            //查询店铺信息
            return "/prom/auth/auth-edit-form";

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            return "/prom/auth/auth-edit-form";
        }
    }

    /**
     * 授权 新增提交保存
     * 
     * @param promType4ShopVO
     * @param result
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public AuthShopVO save(@Valid @ModelAttribute("promType4ShopVO") PromType4ShopVO promType4ShopVO) {

        AuthShopVO vo = new AuthShopVO();
        try {
            // 验证店铺 是否选择
            if (StringUtil.isEmpty(promType4ShopVO.getShopIds())) {
                // 请选择店铺
                throw new BusinessException("web.prom.400001");
            }
            
            //参数开始时间小于等于截止时间
            if(new Timestamp(promType4ShopVO.getStartTime().getTime()).compareTo(promType4ShopVO.getEndTime())>0){
                // 开始时间不能大于截止时间
                throw new BusinessException("web.prom.400021");
            }
            // 页面传入a,b,c格式
            String[] arrayShopIdStr = new String[] {};
            String[] arrayPromTypeCodeStr = new String[] {};
            arrayShopIdStr = promType4ShopVO.getShopIds().split(PromConst.SPILT_FH);
            arrayPromTypeCodeStr = promType4ShopVO.getPromTypeCode().split(PromConst.SPILT_FH);
            List<String> shopIdlist = java.util.Arrays.asList(arrayShopIdStr);
            List<String> promTypeCodelist = java.util.Arrays.asList(arrayPromTypeCodeStr);

            // 调用服务参数组织
            List<PromType4ShopDTO> promType4ShopDTOList = new ArrayList<PromType4ShopDTO>();

            List<PromType4ShopDTO> reList = null;
            for (String promTypeCode : promTypeCodelist) {
            	for (String shopId : shopIdlist) {
            		PromType4ShopDTO promType4ShopDTO = new PromType4ShopDTO();
            		promType4ShopDTO.setEndTime(promType4ShopVO.getEndTime());
            		promType4ShopDTO.setStartTime(promType4ShopVO.getStartTime());
            		promType4ShopDTO.setPromTypeCode(promTypeCode);
            		promType4ShopDTO.setShopId(new Long(shopId));
            		promType4ShopDTOList.add(promType4ShopDTO);
            	}
			}
            reList = promAuthRSV.savePromType4Shop(promType4ShopDTOList);
            // 验证不通过 
            if (!CollectionUtils.isEmpty(reList)) {
            	List<ShopVO> l=new ArrayList<ShopVO>();
            	ShopInfoResDTO shopDTO=null;
            	for(PromType4ShopDTO promType4ShopDTO:reList){
            		ShopVO shopVO=new ShopVO();
            		shopVO.setShopId(promType4ShopDTO.getShopId().toString());
            		shopDTO=shopInfoRSV.findShopInfoByShopID(promType4ShopDTO.getShopId());
            		if(shopDTO!=null){
            			shopVO.setShopName(shopDTO.getShopName());
            		}
            		if(StringUtil.isEmpty(promType4ShopDTO.getValidErrorStr())){
            			shopVO.setTips("");
            			int num = 0;
            			for (ShopVO shopVO1 : l) {
            				if(shopVO1.getShopId().equalsIgnoreCase(shopVO.getShopId())){
            					num++;
            				}
            			}
            			if(num ==0){
            				l.add(shopVO);
            			}
            		}else{
            			shopVO.setTips(promType4ShopDTO.getValidErrorStr());
            			l.add(shopVO);
            		}
            	}
            	vo.setShopVOList(l);
            	vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }

    /**
     * 授权 编辑保存
     * 
     * @param model
     * @param promType4ShopVO
     * @param result
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public EcpBaseResponseVO update(Model model, @ModelAttribute("promType4ShopVO")
    PromType4ShopVO promType4ShopVO) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try {

            //参数开始时间小于等于截止时间
            if(new Timestamp(promType4ShopVO.getStartTime().getTime()).compareTo(promType4ShopVO.getEndTime())>0){
                // 开始时间不能大于截止时间
                throw new BusinessException("web.prom.400021");
            }
            
            PromType4ShopDTO promType4ShopDTO = new PromType4ShopDTO();
            // 更新值
            promType4ShopDTO.setEndTime(promType4ShopVO.getEndTime());
            promType4ShopDTO.setStartTime(promType4ShopVO.getStartTime());
            promType4ShopDTO.setShopId(new Long(promType4ShopVO.getShopId()));
            
            // 更新条件设置
            promType4ShopDTO.setId(new Long(promType4ShopVO.getId()));
            
            // 调用服务
            promAuthRSV.updatePromType4ShopById(promType4ShopDTO);

            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
    /**
     * 授权 失效
     * 
     * @param model
     * @param id
     * @return
     * @author huangjx
     */
    @RequestMapping(value = "/invalid")
    @ResponseBody
    public EcpBaseResponseVO invalid(Model model, @RequestParam("id")
    String id) {

        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        // id 授权列表id
        PromType4ShopDTO dto = new PromType4ShopDTO();

        try {
            if (StringUtil.isEmpty(id)) {
                throw new BusinessException("web.prom.400002");
            }
            dto.setId(new Long(id));
            dto.setStatus(PromConst.PromType4Shop.STATUS_0);
            promAuthRSV.updateStatusById(dto);

            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);

        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        return vo;
    }
}
