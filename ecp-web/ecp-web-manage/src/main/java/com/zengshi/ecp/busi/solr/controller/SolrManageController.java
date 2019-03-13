package com.zengshi.ecp.busi.solr.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.solr.vo.SolrManageVO;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.search.dubbo.dto.IndexReusltRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigRSV;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage-xhs <br>
 * Description: solr管理<br>
 * Date:2016年3月2日上午9:42:10  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/solrmanage")
@Controller
public class SolrManageController extends EcpBaseController{
    private String URL = "/solr";
    private String MODULE = SolrManageController.class.getName();
    private static String INDEX_STATUS = "2";//正在创建索引中
    private static String UN_COLLECT_STATUS = "0";//集合未初始化
    private ISecConfigRSV iSecConfigRSV = ApplicationContextUtil.getBean("secConfigRSV", ISecConfigRSV.class);
    private ISecConfigPlanRSV iSecConfigPlanRSV = ApplicationContextUtil.getBean("secConfigPlanRSV", ISecConfigPlanRSV.class);
    
    
    @RequestMapping()
    public String init(){
        return URL+"/solr-grid";
    }
    
    /**
     * 
     * gridList:(获取solr索引配置列表). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridlist")
    public Model gridList(Model model,SolrManageVO solrManageVO){
        EcpBasePageRespVO<Map> respVO = null;
        try {
        	
            SecConfigReqDTO secConfigReqDTO = solrManageVO.toBaseInfo(SecConfigReqDTO.class);
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
            secConfigReqDTO.setStatus("1");
            PageResponseDTO<SecConfigRespDTO> pageInfo = iSecConfigRSV.querySecConfigPage(secConfigReqDTO);
            respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "获取solr索引配置列表失败！",e);
            respVO = new EcpBasePageRespVO<Map>();
        } catch (Exception e) {
            LogUtil.error(MODULE, "获取solr索引配置列表失败！",e);
            respVO = new EcpBasePageRespVO<Map>();
        }
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * deleteSoleConfig:(根据索引配置记录id删除solr索引配置). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param solrManageVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/clearindex")
    @ResponseBody
    public EcpBaseResponseVO clearIndex(SolrManageVO solrManageVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
            iSecConfigPlanRSV.cleanIndex(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("删除索引配置成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除solr索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除索引配置失败！");
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除solr索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除索引配置失败！");
        }
        return ecpBaseResponseVO;
    }
    @RequestMapping(value="/initcollect")
    @ResponseBody
    public EcpBaseResponseVO initCollect(SolrManageVO solrManageVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
           // secConfigReqDTO.setCollectionStatus("1");
            iSecConfigPlanRSV.createCollection(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("初始化索引集合成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "初始化索引集合失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("初始化索引集合失败！"+e.getErrorMessage());
        } catch (Exception e) {
            LogUtil.error(MODULE, "初始化索引集合失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("初始化索引集合失败！"+e.getMessage());
        }
        return ecpBaseResponseVO;
    }
    
    /**
     * 
     * doSolrConfig:(根据索引记录重建索引配置). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param solrManageVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/restartindex")
    @ResponseBody
    public EcpBaseResponseVO restartIndex(SolrManageVO solrManageVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
            boolean cleanFlag = false;
            if(GdsConstants.Commons.STATUS_VALID.equals(solrManageVO.getClear())){
                //1，表示，创建索引前，需要清楚数据
                cleanFlag = true;
            }
            IndexReusltRespDTO indexReusltRespDTO=iSecConfigPlanRSV.reFullImportIndex(secConfigReqDTO, cleanFlag);    
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg(indexReusltRespDTO.getMessage());
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "重建索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("重建索引配置失败！");
        } catch (Exception e) {
            LogUtil.error(MODULE, "重建索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("重建索引配置失败！");
        }
        return ecpBaseResponseVO;
    }
    
    /**
     * 
     * tosolrconfirmwindow:(跳转到确认窗口). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/tosolrconfirmwindow")
    public String tosolrConfirmWindow(){
        return URL +"/list/solr-confirm-window";
    }
    
    
    /**
     * 
     * whetherCanDo:(判断当前记录是否可以进行其他的操作【条件是：是否尚处于创建索引中】). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param solrManagaeVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/whethercando")
    @ResponseBody
    public EcpBaseResponseVO whetherCanDo(SolrManageVO solrManageVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
            SecConfigRespDTO secConfigRespDTO = iSecConfigRSV.querySecConfigById(secConfigReqDTO);
            if(secConfigRespDTO != null){
            	if(UN_COLLECT_STATUS.equals(secConfigRespDTO.getCollectionStatus())){
            		 ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                     ecpBaseResponseVO.setResultMsg("该集合还没创建，暂时无法进行其他操作！");
            	}
            	else if(INDEX_STATUS.equals(secConfigRespDTO.getIndexStatus())){
                    ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
                    ecpBaseResponseVO.setResultMsg("该集合正在创建索引中，暂时无法进行其他操作！");
                }else{
                    ecpBaseResponseVO.setResultFlag("ok");
                }
            }
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "查询索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("该集合正在创建索引中，暂时无法进行其他操作！");
        } catch (Exception e) {
            LogUtil.error(MODULE, "查询索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("该集合正在创建索引中，暂时无法进行其他操作！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * delCollect:(删除集合). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @param solrManagaeVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/delCollect")
    @ResponseBody
    public EcpBaseResponseVO delCollect(SolrManageVO solrManageVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
            ObjectCopyUtil.copyObjValue(solrManageVO, secConfigReqDTO, null, false);
            iSecConfigPlanRSV.deleteCollection(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("删除集合成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODULE, "删除集合失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除集合失败！");
        } catch (Exception e) {
            LogUtil.error(MODULE, "删除集合失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除集合失败！");
        }
        return ecpBaseResponseVO;
    }
    
}

