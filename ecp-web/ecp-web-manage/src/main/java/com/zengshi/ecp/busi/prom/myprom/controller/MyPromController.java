package com.zengshi.ecp.busi.prom.myprom.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.prom.CheckPageNull;
import com.zengshi.ecp.busi.prom.myprom.vo.PromQueryVO;
import com.zengshi.ecp.prom.dubbo.dto.Prom2SolrReqDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromCommDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromQueryRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromRSV;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromSolrRSV;
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
@RequestMapping(value="/myprom")
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
    public String init(){
        return "redirect:/myprom/grid";
    }
     
    /** 
     * grid:(我的促销). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wangxq 
     * @param model
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="grid")
    public String grid(Model model){
        //状态
        List<BaseParamDTO> promstatus= BaseParamUtil.fetchParamList(PromConstants.PromKey.PROM_INFO_STATUS);
        model.addAttribute("promstatus", promstatus);

        return "/prom/myprom/myprom-grid";
    }

    /** 
     * gridList:(我的促销列表). <br/> 
     * TODO(这里描述这个方法适用条件 – 可选).<br/> 
     * TODO(这里描述这个方法的执行流程 – 可选).<br/> 
     * TODO(这里描述这个方法的使用方法 – 可选).<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author wangxq 
     * @param model
     * @param vo
     * @return
     * @throws Exception 
     * @since JDK 1.6 
     */ 
    @RequestMapping(value="/gridlist")
    @ResponseBody
    public Model gridList(Model model,PromQueryVO vo) throws Exception{
        
        PromInfoDTO promInfoDTO = vo.toBaseInfo(PromInfoDTO.class);
        
        ObjectCopyUtil.copyObjValue(vo,promInfoDTO, "", false);
        PageResponseDTO<PromInfoResponseDTO> t = promQqueryRSV.queryPromInfoList(promInfoDTO);
        
        
        if(CheckPageNull.checkPageNull(t)){
            ShopInfoResDTO  shopInfoDTO=new ShopInfoResDTO();
            for(PromInfoResponseDTO dto:t.getResult()){
                 shopInfoDTO=shopInfoRSV.findShopInfoByShopID(dto.getShopId());
                 if(shopInfoDTO!=null){
                     dto.setShopName(shopInfoDTO.getShopName()); 
                 }
             }
        }
        
        //调用，并结果返回；从后场返回的分页对象，封装为前店所需要的分页对象；
        EcpBasePageRespVO<Map> respVO = EcpBasePageRespVO.buildByPageResponseDTO(t);
        
        return super.addPageToModel(model, respVO);
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
    
}


