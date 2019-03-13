package com.zengshi.ecp.busi.unpf.auth.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.unpf.UnpfConst;
import com.zengshi.ecp.busi.unpf.auth.vo.UnpfShopAuthVO;
import com.zengshi.ecp.server.front.dto.BaseSysCfgRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.catg.IUnpfCatgRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-web-unpf <br>
 * Description: <br>
 * Date:2016-11-7上午10:51:38 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.7
 */

@Controller
@RequestMapping(value = "/platauth")
public class PlatAuthController extends EcpBaseController {

	@Resource
	private IUnpfShopAuthRSV unpfShopAuthRSV;

	@Resource
    private IShopInfoRSV shopInfoRSV;
	
	@Resource
	private IUnpfCatgRSV unpfCatgRSV;
	
    /**
     * 平台促销类型授权店铺功能
     */
    private static String MODULE = PlatAuthController.class.getName();

    /**
     * 
     * init:页面初始化
     * 
     * @author lisp
     * @return
     * @since JDK 1.7
     */
    @RequestMapping()
    public String init(Model model) {
        return "/unpf/auth/shopAuth-grid";
    }

    /**
     * 授权列表查询 初始化列表和查询按钮功能调用
     * 
     * @param model
     * @param vo
     * @param queryDTO
     * @return
     * @throws Exception
     * @author lisp
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, UnpfShopAuthVO vo) throws Exception {

        // 后场服务所需要的DTO；
        UnpfShopAuthReqDTO unpfShopAuthReqDTO = vo.toBaseInfo(UnpfShopAuthReqDTO.class);
        // 默认查询 有效状态数据
        if (StringUtil.isEmpty(vo.getStatus())) {
            vo.setStatus(UnpfConst.STATUS_1);
        }
        // 99 查询全部状态数据
        if (UnpfConst.STATUS_99.equals(vo.getStatus())) {
            vo.setStatus(null);
        }
        // 组织参数
        ObjectCopyUtil.copyObjValue(vo, unpfShopAuthReqDTO, "", false);

        // 调用服务
        PageResponseDTO<UnpfShopAuthRespDTO> page = unpfShopAuthRSV.queryPlatType4ShopForPage(unpfShopAuthReqDTO);
        if(page!=null&&!CollectionUtils.isEmpty(page.getResult())){
            ShopInfoResDTO  shopDTO=new ShopInfoResDTO ();
              for(UnpfShopAuthRespDTO dto:page.getResult()){
                 // dto.setPromClassName(BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE_PROM_CLASS, dto.getPromClass()));
                  shopDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                  //dto.setPromTypeName( BaseParamUtil.fetchParamValue(PromConst.PromKey.PROM_TYPE, dto.getPromTypeCode()));
                  if(shopDTO!=null){
                      dto.setShopName(shopDTO.getShopName()); 
                  }
                  dto.setPlatTypeName(BaseParamUtil.fetchParamValue(UnpfConst.UNPF_PLAT_TYPE, dto.getPlatType()));
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
     * @author lisp
     */
    @RequestMapping(value = "/add")
    public String add(Model model) {
        model.addAttribute("unpfShopAuthVO", new UnpfShopAuthVO());
        UnpfShopAuthRespDTO unpfShopAuthRespDTO = new UnpfShopAuthRespDTO();
        unpfShopAuthRespDTO.setExpiredTime(new Timestamp(15256000000000l));
        model.addAttribute("unpfShopAuthRespDTO", unpfShopAuthRespDTO);
        return "/unpf/auth/shopAuth-add-form";
    }
    
    
    /**
     * 授权 编辑 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
    	model.addAttribute("unpfShopAuthVO", new UnpfShopAuthVO());
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.parseLong(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	model.addAttribute("unpfShopAuthRespDTO", unpfShopAuthRespDTO);
    	return "/unpf/auth/shopAuth-edit-form";
    }

    
    /**
     * 授权 编辑 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/loginAuth/{id}")
    @ResponseBody
    public Map login(Model model, @PathVariable("id") String id) {
    	Map vo = new HashMap();
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.parseLong(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	BaseSysCfgRespDTO  sysAUthDTO=SysCfgUtil.fetchSysCfg(unpfShopAuthRespDTO.getPlatType()+UnpfConst.AUTH_URI);
    	BaseSysCfgRespDTO  sysRedirectDTO=SysCfgUtil.fetchSysCfg(unpfShopAuthRespDTO.getPlatType()+UnpfConst.REDIRECT_URI);
    	vo.put("url", sysAUthDTO.getParaValue()+"?response_type=code&client_id="+unpfShopAuthRespDTO.getAppkey()+"&redirect_uri="+sysRedirectDTO.getParaValue()+id+"&state=1212&view=web");
    	return vo;
    }
    
    
    /**
     * 授权 详情 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/view/{id}")
    public String view(Model model, @PathVariable("id") String id) {
    	model.addAttribute("doAction",UnpfConst.DO_ACTION_VIEW);
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.parseLong(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	model.addAttribute("unpfShopAuthRespDTO", unpfShopAuthRespDTO);
    	return "/unpf/auth/shopAuth-edit-form";
    }
    
    
    /**
     * 授权 详情 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/del/{id}")
    @ResponseBody
    public EcpBaseResponseVO del(Model model, @PathVariable("id") String id) {
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.parseLong(id));
    	try {
    		unpfShopAuthRSV.deletePlatType4Shop(unpfShopAuthReqDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
    	 return vo;
    }
    
    /**
     * 授权 新增提交保存
     * 
     * @param UnpfShopAuthVO
     * @param result
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public EcpBaseResponseVO save(@Valid @ModelAttribute("unpfShopAuthVO") UnpfShopAuthVO unpfShopAuthVO) {

    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO =  new UnpfShopAuthReqDTO();
    	ObjectCopyUtil.copyObjValue(unpfShopAuthVO, unpfShopAuthReqDTO, null, false);
    	try {
    		UnpfShopAuthReqDTO unpf = unpfShopAuthRSV.savePlatType4Shop(unpfShopAuthReqDTO);
    		if(StringUtil.isEmpty(unpf)){
    			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    		}else{
    			 vo.setResultMsg("已经存在该时间的有效授权");
    	         vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
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
     * @param unpfShopAuthVO
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public EcpBaseResponseVO update(@Valid @ModelAttribute("unpfShopAuthVO") UnpfShopAuthVO unpfShopAuthVO) {

    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
        UnpfShopAuthReqDTO unpfShopAuthReqDTO =  new UnpfShopAuthReqDTO();
    	ObjectCopyUtil.copyObjValue(unpfShopAuthVO, unpfShopAuthReqDTO, null, false);
    	try {
    		unpfShopAuthRSV.updatePlatType4Shop(unpfShopAuthReqDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (BusinessException e) {
			LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
		}
        return vo;
    }
    
    /**
     * 授权 详情 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/sync/{id}")
    public String sync(Model model, @PathVariable("id") String id) {
    	model.addAttribute("doAction",UnpfConst.DO_ACTION_SYNC);
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.parseLong(id));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	switch(unpfShopAuthRespDTO.getPlatType()){
		case "youzan":
			unpfShopAuthRespDTO.setPlatTypeName("有赞");
			break;
		case "jd":
			unpfShopAuthRespDTO.setPlatTypeName("京东");
			break;
		case "taobao":
			unpfShopAuthRespDTO.setPlatTypeName("天猫&淘宝");
			break;
		}
    	model.addAttribute("unpfShopAuthRespDTO", unpfShopAuthRespDTO);
    	return "/unpf/sync/sync-tab";
    }
    /**
     * 授权 详情 链接页面
     * 
     * @param model
     * @param id
     * @return
     * @author lisp
     */
    @RequestMapping(value = "/catgSave/{id}")
    @ResponseBody
    public EcpBaseResponseVO catgSave(Model model, @PathVariable("id") String id) {
    	EcpBaseResponseVO vo = new EcpBaseResponseVO();
    	CatgReqDTO catgReqDTO = new CatgReqDTO();
    	catgReqDTO.setAuthId(Long.valueOf(id));
    	try {
    		unpfCatgRSV.saveCatgAndProp(catgReqDTO);
    		vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
    	}catch (BusinessException e) {
    		LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
    	}
    	return vo;
    }
    
}
