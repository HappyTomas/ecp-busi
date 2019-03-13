package com.zengshi.ecp.busi.unpf.good.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.goods.util.GdsParamsTool;
import com.zengshi.ecp.busi.goods.vo.GdsShopVO;
import com.zengshi.ecp.busi.unpf.UnpfConst;
import com.zengshi.ecp.busi.unpf.good.vo.UnpfGdsSendLongVO;
import com.zengshi.ecp.busi.unpf.good.vo.UnpfGdsSendVO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.GdsSendRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.interfaces.auth.IUnpfShopAuthRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGdsSendRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfGoodSendRSV;
import com.zengshi.ecp.unpf.dubbo.interfaces.gdssend.IUnpfSendLogRSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: 商品推送日志查询<br>
 * Date:2016年11月17日下午14:21:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author lisp
 * @version
 * @since JDK 1.6
 */
@Controller
@RequestMapping("/unpf/gdssendlog")
public class UnpfGdsSendLogController extends EcpBaseController {
    private static String MODULE = UnpfGdsSendLogController.class.getName();
    
    @Resource
    private IUnpfGoodSendRSV unpfGoodSendRSV;//
    
    @Resource
    private IUnpfGdsSendRSV unpfGdsSendRSV;
    
    @Resource
    private IUnpfSendLogRSV unpfSendLogRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
	private IUnpfShopAuthRSV unpfShopAuthRSV;
    /**
     * 
     * init:(初始化跳转到商品推送日志查询列表页面). <br/>
     * 
     * @author lisp
     * @return
     * @since JDK 1.6
     */
    @RequestMapping()
    public String init(Model model, GdsShopVO gsShopVO) {
        return "/unpf/goods/log/query/gdsSendLog";
    }
    
    
    /**
     * 
     * gridList:(获取商品管理页的商品列表). <br/>
     * 
     * @author lisp
     * @param model
     * @param vo
     * @return
     * @throws Exception
     * @since JDK 1.6
     */
    @RequestMapping("/gridlist")
    @ResponseBody
    public Model gridList(Model model, UnpfGdsSendLongVO vo) throws Exception {
    	
        PageResponseDTO<UnpfGdsSendLogRespDTO> list = new PageResponseDTO<UnpfGdsSendLogRespDTO>();
        EcpBasePageRespVO<Map> respVO = null;
        UnpfGdsSendReqDTO unpfGdsSendReqDTO = new UnpfGdsSendReqDTO();
        ObjectCopyUtil.copyObjValue(vo, unpfGdsSendReqDTO, null, false);
        unpfGdsSendReqDTO.setPageNo(vo.getPageNumber());
        try {
            list = unpfGoodSendRSV.queryGdsLogInfoListPage(unpfGdsSendReqDTO);
            if (list!=null&&CollectionUtils.isNotEmpty(list.getResult())) {
                for (UnpfGdsSendLogRespDTO gdsInfoRespDTO : list.getResult()) {
                	gdsInfoRespDTO.setPlatTypeName(BaseParamUtil.fetchParamValue(UnpfConst.UNPF_PLAT_TYPE, gdsInfoRespDTO.getPlatType()));
                	gdsInfoRespDTO.setStatus(BaseParamUtil.fetchParamValue(UnpfConst.UNPF_GDS_SEND_STATUS, gdsInfoRespDTO.getStatus()));
                }
            }
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(list);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询列表失败", e);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(list);
            return super.addPageToModel(model, GdsParamsTool.batchGdsDetailUrl(respVO, "url"));
        }
        return super.addPageToModel(model, GdsParamsTool.batchGdsDetailUrl(respVO, "url"));
    }

    /**
     * 
     * gdssend:(单个商品推送). <br/>
     * 
     * @author lisp
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/gdssend")
    @ResponseBody
    public EcpBaseResponseVO gdssend(UnpfGdsSendVO unpfGdsSendVO) {
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        // 页面传入a,b,c格式
        String[] arrayGdsSendStr = new String[] {};
        String[] arrayStr = new String[] {};
        arrayGdsSendStr = unpfGdsSendVO.getSendGds().split(",");
        List<String> gdsSendlist = java.util.Arrays.asList(arrayGdsSendStr);
        List<String> list = new ArrayList<>();
        GdsSendReqDTO gdsSendReqDTO=new GdsSendReqDTO();
        if(gdsSendlist.size()>0){
        	String s = new String();
        	for (String string : gdsSendlist) {
        		arrayStr = string.split("@");
        		list= java.util.Arrays.asList(arrayStr);
        		gdsSendReqDTO.setGdsId(Long.valueOf(list.get(0)));
        		gdsSendReqDTO.setPlatType(list.get(1));
        		gdsSendReqDTO.setShopId(Long.valueOf(list.get(2)));
        		gdsSendReqDTO.setIfThrow("0");
        		GdsSendRespDTO resp= unpfGdsSendRSV.send(gdsSendReqDTO);
        		if(!"1".equals(resp.getIfResult())){
        			vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        			s+="商品编码:"+list.get(0)+",错误信息:"+resp.getMsg();
    			}
			}
        	vo.setResultMsg(s);
        }
        return vo;
    }

    
    /**
     * 
     * 商品推送详细信息 <br/>
     * 
     * @author lisp
     * @return
     * @since JDK 1.6
     */
    @RequestMapping(value = "/detailLog/{gdsId}/{shopAuthId}")
    public String detailLog(Model model,@PathVariable String gdsId
    		,@PathVariable String shopAuthId){
    	UnpfShopAuthReqDTO unpfShopAuthReqDTO = new UnpfShopAuthReqDTO();
    	unpfShopAuthReqDTO.setId(Long.valueOf(shopAuthId));
    	UnpfShopAuthRespDTO unpfShopAuthRespDTO = unpfShopAuthRSV.queryPlatType4ShopById(unpfShopAuthReqDTO);
    	if(StringUtil.isNotEmpty(unpfShopAuthRespDTO)){
    		ShopInfoResDTO shopDTO=shopInfoRSV.findShopInfoByShopID(unpfShopAuthRespDTO.getShopId());
    		model.addAttribute("shopName", shopDTO.getShopName());
    		model.addAttribute("shopId", unpfShopAuthRespDTO.getShopId());
    		model.addAttribute("platType", unpfShopAuthRespDTO.getPlatType());
    	}
    	model.addAttribute("shopAuthId", shopAuthId);
    	model.addAttribute("gdsId", gdsId);
    	return "/unpf/goods/log/detail/gdsSendDetailLog";
    }
    /**
     * 
     * 商品推送详细信息 <br/>
     * 
     * @author lisp
     * @return
     * @throws Exception 
     * @since JDK 1.6
     */
    @RequestMapping(value = "/detailLog/gridList")
 public Model detaillist(Model model,UnpfGdsSendVO unpfGdsSendVO) throws Exception {
    	UnpfSendLogReqDTO unpfSendLogReqDTO = new UnpfSendLogReqDTO();
    	unpfSendLogReqDTO.setShopId(unpfGdsSendVO.getShopId());
    	unpfSendLogReqDTO.setPlatType(unpfGdsSendVO.getPlatType());
    	unpfSendLogReqDTO.setShopAuthId(unpfGdsSendVO.getShopAuthId());
    	unpfSendLogReqDTO.setGdsId(unpfGdsSendVO.getGdsId());
    	unpfSendLogReqDTO.setPageSize(10);
    	 EcpBasePageRespVO<Map> respVO = null;
    	 PageResponseDTO<UnpfSendLogRespDTO> page= null;
    	 try {
    		  page = unpfSendLogRSV.queryGdsSendLogForPage(unpfSendLogReqDTO);
             respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
         } catch (BusinessException e) {
             LogUtil.error(MODULE, "查询列表失败", e);
             respVO = EcpBasePageRespVO.buildByPageResponseDTO(page);
             return super.addPageToModel(model, respVO);
         }
         return super.addPageToModel(model, respVO);
     }
}
