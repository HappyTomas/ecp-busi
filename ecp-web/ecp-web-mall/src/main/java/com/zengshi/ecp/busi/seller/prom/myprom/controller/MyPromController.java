package com.zengshi.ecp.busi.seller.prom.myprom.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.seller.prom.myprom.vo.PromCountVO;
import com.zengshi.ecp.busi.seller.prom.myprom.vo.PromQueryVO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCommDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.server.front.dto.BaseParamDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;


/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage <br>
 * Description: <br>
 * Date:2015年9月4日上午11:51:03  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author wang
 * @version  
 * @since JDK 1.6
 */

@Controller
@RequestMapping(value="/seller/myprom")
public class MyPromController extends EcpBaseController {
    /**
     * 我的促销列表
     */
    private static String MODULE = MyPromController.class.getName();
    
    @Resource
    private IPromQueryRSV promQqueryRSV;
    
    @Resource
    private IPromRSV promRSV;
    
    @Resource
    private IShopInfoRSV shopInfoRSV;
    
    @Resource
    private IPromSolrRSV promSolrRSV;
    /** 
     * init:(我的促销). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wangxq 
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping()
    public String init(Model model,PromQueryVO vo){
        //状态
    /*   List<BaseParamDTO> promstatus= BaseParamUtil.fetchParamList(PromConstants.PromKey.PROM_INFO_STATUS);
       model.addAttribute("promstatus", promstatus);*/
       
       //状态 默认展示
       model.addAttribute("status", vo.getStatus());
       //店铺 默认展示
       model.addAttribute("shopId", vo.getShopId());
       //是否全站点  默认为全部
       model.addAttribute("isAllSite", true);
 /*      PromInfoDTO promInfoDTO = vo.toBaseInfo(PromInfoDTO.class);
       
       ObjectCopyUtil.copyObjValue(vo,promInfoDTO, "", false);
       PageResponseDTO<PromInfoResponseDTO> promPage = promQqueryRSV.queryPromInfoList(promInfoDTO);
       
       
       if(CheckPageNull.checkPageNull(promPage)){
           ShopInfoResDTO  shopInfoDTO=new ShopInfoResDTO();
           for(PromInfoResponseDTO dto:promPage.getResult()){
                shopInfoDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                if(shopInfoDTO!=null){
                    dto.setShopName(shopInfoDTO.getShopName()); 
                }
            }
       }
       model.addAttribute("promPage", promPage);*/
       return "/seller/prom/myprom/myprom-grid";
   }
     

    /** 
     * promlist:(我的促销列表). <br/> 
     * 
     * @author 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    
    @RequestMapping(value="/promlist")
    public String promlist(Model model, PromQueryVO vo) throws Exception{
    	       
    	
		PromInfoDTO promInfoDTO = vo.toBaseInfo(PromInfoDTO.class);
        ObjectCopyUtil.copyObjValue(vo,promInfoDTO, "", false);
		
    	//状态
    	       List<BaseParamDTO> promstatus= BaseParamUtil.fetchParamList(PromConstants.PromKey.PROM_INFO_STATUS);
    	       model.addAttribute("promstatus", promstatus);
    	        
    	        PageResponseDTO<PromInfoResponseDTO> promPage = promQqueryRSV.queryPromInfoList(promInfoDTO);
    	        
    	        if(CheckPageNull.checkPageNull(promPage)){
    	            ShopInfoResDTO  shopInfoDTO=new ShopInfoResDTO();
    	            for(PromInfoResponseDTO dto:promPage.getResult()){
    	                 shopInfoDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
    	                 if(shopInfoDTO!=null){
    	                     dto.setShopName(shopInfoDTO.getShopName()); 
    	                 }
    	             }
    	        }
    	        model.addAttribute("promPage", promPage);
    	        return "/seller/prom/myprom/list/myprom-list" ;
    	    }
    
    /** 
     * publish:(发布). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wangxq 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/publish")
    @ResponseBody
    public EcpBaseResponseVO publish(@RequestParam("id")String id){
        LogUtil.info(MODULE,"发布促销信息"+id);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(StringUtil.isBlank(id)){
                throw new BusinessException("web.prom.400018");
            }
            
            PromCommDTO promCommDTO=new PromCommDTO();
            promCommDTO.setPromId(new Long(id));
            //promCommDTO.setStaff(staff);//待实现
            promRSV.publishProm(promCommDTO);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }
        
        return vo;
    }
    
    /** 
     * valid:(失效). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wangxq 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/valid")
    @ResponseBody
    public EcpBaseResponseVO valid(@RequestParam("id")String id){
        LogUtil.info(MODULE,"失效促销信息"+id);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(StringUtil.isBlank(id)){
                throw new BusinessException("web.prom.400018");
            }

            PromCommDTO promCommDTO=new PromCommDTO();
            promCommDTO.setPromId(new Long(id));
            //promCommDTO.setStaff(staff);//待实现
            promRSV.invalidProm(promCommDTO);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        
        return vo;
    }
    

    /** 
     * valid:(删除). <br/> 
     * @author huangjx 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/del")
    @ResponseBody
    public EcpBaseResponseVO delete(@RequestParam("id")String id){
        
        LogUtil.info(MODULE,"删除促销信息"+id);
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(StringUtil.isBlank(id)){
                throw new BusinessException("web.prom.400018");
            }

            PromCommDTO promCommDTO=new PromCommDTO();
            promCommDTO.setPromId(new Long(id));
            //promCommDTO.setStaff(staff);//待实现
            promRSV.delProm(promCommDTO);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        return vo;
    }
    /** 
     * fresh:(更新搜索关键字)
     * 1、促销编码、展示开始时间 满足 并且 未发送消息
     * @author huangjx 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.7
     */ 
    @RequestMapping(value="/fresh")
    @ResponseBody
    public EcpBaseResponseVO fresh(@RequestParam("id")String id){
    	
        EcpBaseResponseVO vo = new EcpBaseResponseVO();
        try{
            if(StringUtil.isBlank(id)){
                throw new BusinessException("web.prom.400018");
            }
            //发送促销关键字到solr
            Prom2SolrReqDTO prom2SolrReqDTO=new Prom2SolrReqDTO();
            prom2SolrReqDTO.setPromId(new Long(id));
            prom2SolrReqDTO.setShowStartTime(DateUtil.getSysDate());
            prom2SolrReqDTO.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_1);
            promSolrRSV.sendData(prom2SolrReqDTO);
            
            //发送促销单品关系到solr
            Prom2SolrReqDTO prom2SolrReqDTO1=new Prom2SolrReqDTO();
            prom2SolrReqDTO1.setPromId(new Long(id));
            prom2SolrReqDTO1.setSolrType(PromConstants.Prom2Solr.SOLR_TYPE_2);
            promSolrRSV.sendData(prom2SolrReqDTO1);
            
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            
        }catch(BusinessException e){
            LogUtil.error(MODULE, "报错", e);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
            vo.setResultMsg(e.getMessage());
        }
        
        return vo;
    }
    
    /**
     * 
     * importGds:(跳转到商品导入页面). <br/> 
     * 
     * @author huangjx 
     * @return 
     * @since JDK 1.7
     */
    @RequestMapping(value="/importGds")
    public String importGds(Model model,String promId){
        model.addAttribute("promId", promId);
        return "/prom/createprom/importdata/prom-importdata";
    }
    /** 
     * promCount:(促销活动数量统计). <br/> 
     * 
     * @author huangjx 
     * @param id
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/promcount")
    @ResponseBody
    public PromCountVO promCount(Model model, PromQueryVO ivo) throws Exception{
        //1、店铺编码非空
        PromCountVO vo=new PromCountVO();
        Long promCount=new Long(0l);
        try {
          //1、店铺编码非空
            if(ivo==null){
                throw new BusinessException("web.prom.400031");
            }
            if (ivo.getShopId()==null) {
                throw new BusinessException("web.prom.400031");
            }
            if (ivo.getShopId().equals(0)) {
                throw new BusinessException("web.prom.400031");
            }
            //2、状态失效
            if (StringUtils.isBlank(ivo.getStatus())) {
                throw new BusinessException("web.prom.400032");
            }
            
            PromInfoDTO promInfoDTO = ivo.toBaseInfo(PromInfoDTO.class);
            ObjectCopyUtil.copyObjValue(ivo, promInfoDTO, "", false);

            //不用count 单独方法获得，使用原来的方法 数据准确，工作量小。（如果改造统计口径，也仅仅统一修改便可）
            promInfoDTO.setPageNo(1);
            promInfoDTO.setPageSize(1);
          
            PageResponseDTO<PromInfoResponseDTO> promPage = promQqueryRSV
                    .queryPromInfoList(promInfoDTO);
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            if(promPage!=null){
                promCount=promPage.getCount();
            }
            vo.setPromCount(promCount);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "报错了啦", e);
            vo.setResultMsg(e.getErrorMessage());
            vo.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
        }

        return vo;
    }
}


