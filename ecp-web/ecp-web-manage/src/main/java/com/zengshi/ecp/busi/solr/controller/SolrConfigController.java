package com.zengshi.ecp.busi.solr.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.controller.EcpBaseController;
import com.zengshi.ecp.base.util.ApplicationContextUtil;
import com.zengshi.ecp.base.vo.EcpBasePageRespVO;
import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.solr.vo.SolrConfigVO;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigPlanRSV;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigRSV;
import com.zengshi.ecp.search.dubbo.interfaces.ISecObjectRSV;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage-xhs <br>
 * Description: solr索引配置信息【创建、管理列表】<br>
 * Date:2016年3月2日下午2:50:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author gxq
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/solrconfig")
@Controller
public class SolrConfigController extends EcpBaseController{
    private String MODUAL = SolrConfigController.class.getName();
    private String URL = "/solr/config";
    @Resource
    private ISecConfigRSV secConfigRSV;
    @Resource
    private ISecConfigPlanRSV iSecConfigPlanRSV;
    @Resource
    private ISecObjectRSV secObjectRSV;
    
    @RequestMapping()
    public String init(){
        return URL+"/solr-config-grid";
    }
    /**
     * 
     * toSolrConfigAdd:(跳转到索引配置添加页面). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/tosolrconfigadd")
    public String toSolrConfigAdd(){
        return URL+"/solr-config-add";
    }
    
    /**
     * 
     * gridList:(获取索引配置信息列表). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/gridlist")
    public Model gridList(Model model,SolrConfigVO solrConfigVO){
    	EcpBasePageRespVO<Map> respVO = null;
    	try {
    		solrConfigVO.setStatus(SearchConstants.STATUS_VALID);
    		if(solrConfigVO.getConfigIfActive()==null){
    			//solrConfigVO.setConfigIfActive(SearchConstants.STATUS_VALID);
    		}
    		SecConfigReqDTO secConfigReqDTO = solrConfigVO.toBaseInfo(SecConfigReqDTO.class);
            ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
        	PageResponseDTO<SecConfigRespDTO>  pageInfo=secConfigRSV.querySecConfigPage(secConfigReqDTO);
			respVO = EcpBasePageRespVO.buildByPageResponseDTO(pageInfo);
		} catch (BusinessException e) {
            LogUtil.error(MODUAL, "获取solr索引配置列表失败！",e);
            respVO = new EcpBasePageRespVO<Map>();
        } catch (Exception e) {
            LogUtil.error(MODUAL, "获取solr索引配置列表失败！",e);
            respVO = new EcpBasePageRespVO<Map>();
        }
        return super.addPageToModel(model, respVO);
    }
    
    /**
     * 
     * addSolrConfig:(新增索引配置信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/addsolrconfig")
    @ResponseBody
    public EcpBaseResponseVO addSolrConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecConfigReqDTO secConfigReqDTO = solrConfigVO.toBaseInfo(SecConfigReqDTO.class);
        	ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
            if(!"1".equals(secConfigReqDTO.getConfigQueryIfSpellcheck())){
            	secConfigReqDTO.setConfigQueryIfSpellcheck("0");
            }
            if(!"1".equals(secConfigReqDTO.getConfigQueryIfHl())){
        		secConfigReqDTO.setConfigQueryIfHl("0");
            }
            if(!"1".equals(secConfigReqDTO.getConfigIfMultilan())){
        		secConfigReqDTO.setConfigIfMultilan("0");
            }
        	secConfigRSV.saveSecConfig(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("新增索引配置信息成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "新增索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("新增索引配置信息失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "新增索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("新增索引配置信息失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * clearCash:(清除搜索缓存). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @param solrManagaeVO
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/clearCash")
    @ResponseBody
    public EcpBaseResponseVO clearCash(){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SearchCacheUtils.clean();
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("清除搜索缓存成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "清除搜索缓存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("清除搜索缓存失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "清除搜索缓存失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("清除搜索缓存失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * editSolrConfig:(更新索引配置信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/eidtsolrconfig")
    @ResponseBody
    public EcpBaseResponseVO editSolrConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
        	secConfigReqDTO.setId(solrConfigVO.getId());
        	SecConfigRespDTO secConfigRespDTO=secConfigRSV.querySecConfigById(secConfigReqDTO);
        	ObjectCopyUtil.copyObjValue(secConfigRespDTO, secConfigReqDTO, null, false);
        	ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
        	if(!"1".equals(secConfigReqDTO.getConfigQueryIfSpellcheck())){
        		secConfigReqDTO.setConfigQueryIfSpellcheck("0");
            }
        	if(!"1".equals(secConfigReqDTO.getConfigQueryIfHl())){
        		secConfigReqDTO.setConfigQueryIfHl("0");
            }
        	 if(!"1".equals(secConfigReqDTO.getConfigIfMultilan())){
         		secConfigReqDTO.setConfigIfMultilan("0");
             }
        	secConfigRSV.updateSecConfig(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("更新索引配置信息成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "更新索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置信息失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "更新索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置信息失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * samenameconfig:(查询是否有启动的同名索引). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/samenameconfig")
    @ResponseBody
    public EcpBaseResponseVO sameNameConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecConfigReqDTO secConfigReqDTO = new SecConfigReqDTO();
        	secConfigReqDTO.setConfigCollectionName(solrConfigVO.getConfigCollectionName());
        	secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        	secConfigReqDTO.setConfigIfActive(SearchConstants.STATUS_VALID);
        	boolean isSame=secConfigRSV.isDupConfigName(secConfigReqDTO);
        	//PageResponseDTO<SecConfigRespDTO> pageInfo=secConfigRSV.querySecConfigPage(secConfigReqDTO);
        	ecpBaseResponseVO.setResultMsg("0");
        	if(isSame){
        		ecpBaseResponseVO.setResultMsg("1");
            }
        	ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "查询索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("查询索引配置失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "查询索引配置失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("查询索引配置失败！");
        }
        return ecpBaseResponseVO;
    }
    /**
     * 
     * forgitsolrconfig:(进行索引配置信息的操作【禁用、启用】). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/forgitsolrconfig")
    @ResponseBody
    public EcpBaseResponseVO forgitSolrConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecConfigReqDTO secConfigReqDTO = solrConfigVO.toBaseInfo(SecConfigReqDTO.class);
            ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
            if(solrConfigVO.getConfigIfActive().trim().equals("1")){
                secConfigReqDTO.setConfigIfActive(SearchConstants.STATUS_VALID);
            }
            else{
                secConfigReqDTO.setConfigIfActive(SearchConstants.STATUS_INVALID);
            }
            secConfigRSV.updateSecConfigSelective(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "更新索引配置状态失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置状态失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "更新索引配置状态失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置状态失败！");
        }
        return ecpBaseResponseVO;
    }
    
   /* *//**
     * 
     * dosolrconfig:(进行索引配置信息的操作<br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     *//*
    @RequestMapping(value="/dosolrconfig")
    @ResponseBody
    public EcpBaseResponseVO doSolrConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "更新索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置信息失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "更新索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("更新索引配置信息失败！");
        }
        return ecpBaseResponseVO;
    }*/
    
    /**
     * 
     * removesolrconfig:(删除索引配置信息). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/removesolrconfig")
    @ResponseBody
    public EcpBaseResponseVO removeSolrConfig(SolrConfigVO solrConfigVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecConfigReqDTO secConfigReqDTO = solrConfigVO.toBaseInfo(SecConfigReqDTO.class);
            ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
            //删除集合
            if("1".equals(secConfigReqDTO.getCollectionStatus())){
            	   iSecConfigPlanRSV.deleteCollection(secConfigReqDTO);
            }
            secConfigRSV.deleteSecConfigAll(secConfigReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("删除索引配置信息成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "删除索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除索引配置信息失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "删除索引配置信息失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除索引配置信息失败！");
        }
        return ecpBaseResponseVO;
    }
    
    /**
     * 
     * querySolrConfigInfo:(获取单条索引配置信息的信息内容【用于进行编辑 和查看的信息内容展示】). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author gxq 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/querysolrconfiginfo")
    public String querySolrConfigInfo(Model model,SolrConfigVO solrConfigVO,String isRead){
        try {
        	SecConfigReqDTO secConfigReqDTO=new SecConfigReqDTO();
        	secConfigReqDTO.setId(solrConfigVO.getId());
        	secConfigReqDTO.setStatus(SearchConstants.STATUS_VALID);
        	SecConfigRespDTO secConfigRespDTO=secConfigRSV.querySecConfigById(secConfigReqDTO);
        	model.addAttribute("isRead",isRead);
        	model.addAttribute("promVO",secConfigRespDTO);
        } catch (BusinessException e) {
        	 LogUtil.error(MODUAL, "删除索引配置信息失败！", e);
        } catch (Exception e) {
        	 LogUtil.error(MODUAL, "删除索引配置信息失败！", e);
        }
        return URL+"/solr-config-edit";
    }
    
}

