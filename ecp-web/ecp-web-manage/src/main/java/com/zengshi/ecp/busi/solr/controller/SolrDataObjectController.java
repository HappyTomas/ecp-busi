package com.zengshi.ecp.busi.solr.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import com.zengshi.ecp.busi.solr.vo.SolrFieldVO;
import com.zengshi.ecp.busi.solr.vo.SolrManageVO;
import com.zengshi.ecp.busi.solr.vo.SolrObjectVO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfig2ObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecConfigRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecFieldTypeRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectProcessorRespDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectReqDTO;
import com.zengshi.ecp.search.dubbo.dto.SecObjectRespDTO;
import com.zengshi.ecp.search.dubbo.interfaces.ISecConfigRSV;
import com.zengshi.ecp.search.dubbo.interfaces.ISecObjectRSV;
import com.zengshi.ecp.search.dubbo.search.util.SearchConstants;
import com.zengshi.ecp.search.dubbo.util.SearchCacheUtils;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;

import net.sf.json.JSONArray;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-manage-xhs <br>
 * Description: solr搜索数据列表【创建、管理列表】<br>
 * Date:2016年3月2日下午2:50:22  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author zhuqr
 * @version  
 * @since JDK 1.6
 */
@RequestMapping(value="/solrdataobj")
@Controller
public class SolrDataObjectController extends EcpBaseController{
    private String MODUAL = SolrDataObjectController.class.getName();
    private String URL = "/solr/dataobj";
    @Resource
    private ISecConfigRSV secConfigRSV;
    @Resource
    private ISecObjectRSV secObjectRSV;
    
    @RequestMapping()
    public String init(Model model,SolrConfigVO solrConfigVO){
        SecConfigReqDTO secConfigReqDTO = solrConfigVO.toBaseInfo(SecConfigReqDTO.class);
        ObjectCopyUtil.copyObjValue(solrConfigVO, secConfigReqDTO, null, false);
        //获取配置对象
        SecConfigRespDTO resp=secConfigRSV.querySecConfigById(secConfigReqDTO);
        model.addAttribute("configResp", resp);
        return URL+"/solr-dataobj-grid";
    }
    
    /**
     * 
     * dataGridList:(获取solr索引数据对象列表). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @param model
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/datagridlist")
    public Model dataGridList(Model model,SolrObjectVO solrObjectVO){
        EcpBasePageRespVO<Map> respVO = null;
        try {
            //获取数据库数据对象
        	SecConfig2ObjectReqDTO secConfig2ObjectReqDTO=new SecConfig2ObjectReqDTO();
        	ObjectCopyUtil.copyObjValue(solrObjectVO, secConfig2ObjectReqDTO, null, false);
        	 //  设置状态
        	secConfig2ObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
            PageResponseDTO<SecObjectRespDTO> pageInfo=secObjectRSV.querySecObjectPage(secConfig2ObjectReqDTO);
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
     * toReadView:(跳转到索引配置添加页面). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/toreadview")
    public String toReadView(){
        return URL+"/solr-dataobj-read";
    }
    
    /**
     * 
     * toAddView:(跳转到索引配置添加页面). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/toaddview")
    public String toAddView(Model model,SolrObjectVO solrObjectVO,String isRead){
    	//1、获取字段类型列表
    	 List<SecFieldTypeRespDTO> secFieldTypeRespDTO=SearchCacheUtils.getSecFieldTypeList();
    	 List<String> fieldTypeResp=new ArrayList<String>();
    	 if(secFieldTypeRespDTO!=null){
    		 for(int i=0;i<secFieldTypeRespDTO.size();i++){
        		 fieldTypeResp.add(secFieldTypeRespDTO.get(i).getTypeName());
        	 }
    	 }
    	
    	 model.addAttribute("fieldTypeResp", fieldTypeResp);
    	 //2、获取字段处理器列表
    	 List<SecFieldProcessorRespDTO> secFieldProcessorRespDTO=SearchCacheUtils.getSecFieldProcessorList();
    	 List<String> fieldProcessorResp=new ArrayList<String>();
    	 if(secFieldProcessorRespDTO!=null){
    		 for(int i=0;i<secFieldProcessorRespDTO.size();i++){
    			 fieldProcessorResp.add(secFieldProcessorRespDTO.get(i).getProcessorName());
        	 }
    	 }
    	 model.addAttribute("fieldProcessorResp", fieldProcessorResp);
    	//3、获取对象处理器列表
     	List<SecObjectProcessorRespDTO> secObjectProcessorRespDTO=SearchCacheUtils.getSecObjectProcessorList();
     	 List<String> objectProcessorResp=new ArrayList<String>();
    	 if(secObjectProcessorRespDTO!=null){
    		 for(int i=0;i<secObjectProcessorRespDTO.size();i++){
    			 objectProcessorResp.add(secObjectProcessorRespDTO.get(i).getProcessorName());
        	 }
    	 }
     	model.addAttribute("objectProcessorResp", objectProcessorResp);
    	model.addAttribute("solrObject", solrObjectVO);
    	
    	//跳转至编辑页面
    	if(solrObjectVO.getId()!=null){
    		SecObjectReqDTO secObjectReqDTO = solrObjectVO.toBaseInfo(SecObjectReqDTO.class);
        	ObjectCopyUtil.copyObjValue(solrObjectVO, secObjectReqDTO, null, false);
        	secObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
        	SecObjectRespDTO secObjectRespDTO=secObjectRSV.querySecObjectByIdAll(secObjectReqDTO);
        	model.addAttribute("solrObject", secObjectRespDTO);
        	if("1".equals(isRead)){
        		model.addAttribute("isRead", isRead);
        	}
    	}
    	model.addAttribute("configId", solrObjectVO.getConfigId());
        if("1".equals(solrObjectVO.getObjectType())){
    		return URL+"/solr-dataobj-datasave";
    	}else{
    		return URL+"/solr-dataobj-selfsave";
    	}
    }

    /**
     * 
     * saveobj:(保存页面). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zhuqr 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/saveobj")
    @ResponseBody
    public EcpBaseResponseVO saveObj(Model model,SolrObjectVO solrObjectVO){
    	 EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
         try {
             JSONArray arrayTemp = JSONArray.fromObject(solrObjectVO.getFieldStr());
             @SuppressWarnings({ "unchecked" })
             List<SecFieldReqDTO> propValueReqDTOs = JSONArray.toList(arrayTemp,
            		 SecFieldReqDTO.class);
          
             SecObjectReqDTO secObjectReqDTO=new SecObjectReqDTO();
             ObjectCopyUtil.copyObjValue(solrObjectVO, secObjectReqDTO, null, false);
             List<SecFieldReqDTO> secFieldReqDTOList=new ArrayList<SecFieldReqDTO>();
             if(!"1".equals(secObjectReqDTO.getInsidepager())){
            	 secObjectReqDTO.setInsidepager("0");
             }
          /*   if(!"1".equals(secObjectReqDTO.getObjectIfMultilan())){
            	 secObjectReqDTO.setObjectIfMultilan("0");
             }*/
             
             //保存修改
             SecObjectRespDTO secObjectRespDTO=null;
             if(secObjectReqDTO.getId()!=null){     	
                	secObjectReqDTO.setStatus(SearchConstants.STATUS_VALID);
                	secObjectRespDTO=secObjectRSV.querySecObjectByIdAll(secObjectReqDTO);
                    ObjectCopyUtil.copyObjValue(secObjectRespDTO, secObjectReqDTO, null, false);
             }
             ObjectCopyUtil.copyObjValue(solrObjectVO, secObjectReqDTO, null, false);
             List<Long> delFieldIds=new ArrayList<Long>();
             List<Long> editFieldIds=new ArrayList<Long>();
             int index=0;
             /*数据库取值赋值 being*/
             if(secObjectRespDTO!=null){
            	 List<SecFieldRespDTO> secFieldRespDTOs=secObjectRespDTO.getSecFieldRespDTOList();
            	 if(secFieldRespDTOs!=null){
            		 for(int i=0;i<secFieldRespDTOs.size();i++){
            			 SecFieldRespDTO secFieldRespDTO=secFieldRespDTOs.get(i);
            			 if(propValueReqDTOs!=null){
	            			 for(int k=0;k<propValueReqDTOs.size();k++){
	            				 SecFieldReqDTO secFieldReqDTO=propValueReqDTOs.get(k);
	            				 /*修改的Field begin*/
	            				 Long editId=secFieldReqDTO.getId();
	            				 if(editId!=null&&(secFieldRespDTO.getId().longValue()==editId.longValue())){
	            					 SecFieldReqDTO daoCopy=new SecFieldReqDTO() ;
	            					 ObjectCopyUtil.copyObjValue(secFieldRespDTO, daoCopy, null, false);
	            					 ObjectCopyUtil.copyObjValue(secFieldReqDTO, daoCopy, null, false);
	            					 propValueReqDTOs.set(k,daoCopy);
	            				 }
	            				 /*修改的Field end*/
	            				if(editId!=null&&index==0){
	            					editFieldIds.add(editId);
	            				}
	            			 }
	            			 index++;
            			 }
            			 /*获取已删除id*/
                         if(!editFieldIds.contains(secFieldRespDTO.getId())){
                        	 delFieldIds.add(secFieldRespDTO.getId());
                         }

                	 }
            	 }
             }
             /*数据库取值赋值 end*/
             if(propValueReqDTOs!=null){
                 secFieldReqDTOList.addAll(propValueReqDTOs);
             }
        
             secObjectReqDTO.setSecFieldReqDTOList(secFieldReqDTOList);
             secObjectReqDTO.setDelFieldIds(delFieldIds);
             
             if(secFieldReqDTOList!=null){
            	 for(int i=0;i<secFieldReqDTOList.size();i++){
                	SecFieldReqDTO secFieldReqDTO=secFieldReqDTOList.get(i);
            		 if(!"1".equals(secFieldReqDTO.getFieldIfFacet())){
            			 secFieldReqDTO.setFieldIfFacet("0");
            		 }
            		 if(!"1".equals(secFieldReqDTO.getFieldIfMutlivalue())){
            			 secFieldReqDTO.setFieldIfMutlivalue("0");
            		 }
            		 if(!"1".equals(secFieldReqDTO.getFieldIfSpellcheck())){
            			 secFieldReqDTO.setFieldIfSpellcheck("0");
            		 }
            		 if(!"1".equals(secFieldReqDTO.getFieldIfBelongtoDf())){
            			 secFieldReqDTO.setFieldIfBelongtoDf("0");
            		 }
            		 if(!"1".equals(secFieldReqDTO.getFieldIfHlfield())){
            			 secFieldReqDTO.setFieldIfHlfield("0");
            		 }
            		 if(!"1".equals(secFieldReqDTO.getFieldIfMultilan())){
            			 secFieldReqDTO.setFieldIfMultilan("0");
            		 }
            	 }
             } 
             //2108
             if(secObjectReqDTO.getId()!=null){
            	 secObjectRSV.updateSecObjectAll(secObjectReqDTO);
            	 
             }else{
            	 secObjectRSV.saveSecObjectAll(secObjectReqDTO);
             }
             model.addAttribute("configId", solrObjectVO.getConfigId());
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
             ecpBaseResponseVO.setResultMsg("保存搜索数据对象成功！");
         } catch (BusinessException e) {
             LogUtil.error(MODUAL, "保存搜索数据对象失败！", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("保存搜索数据对象失败！");
         } catch (Exception e) {
             LogUtil.error(MODUAL, "保存搜索数据对象失败！", e);
             ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
             ecpBaseResponseVO.setResultMsg("保存搜索数据对象失败！");
         }
         return ecpBaseResponseVO;
    }
    /**
     * 
     * removesolrobj:(删除搜索数据对象). <br/> 
     * TODO(这里描述这个方法的注意事项 – 可选).<br/> 
     * 
     * @author zqr 
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping(value="/removesolrobj")
    @ResponseBody
    public EcpBaseResponseVO removeSolrObj(SolrObjectVO solrObjectVO){
        EcpBaseResponseVO ecpBaseResponseVO = new EcpBaseResponseVO();
        try {
        	SecObjectReqDTO secObjectReqDTO = solrObjectVO.toBaseInfo(SecObjectReqDTO.class);
        	ObjectCopyUtil.copyObjValue(solrObjectVO, secObjectReqDTO, null, false);
             //删除搜索数据对象
        	secObjectRSV.deleteSecObjectAll(secObjectReqDTO);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
            ecpBaseResponseVO.setResultMsg("删除搜索数据对象成功！");
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "删除搜索数据对象失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除搜索数据对象失败！");
        } catch (Exception e) {
            LogUtil.error(MODUAL, "删除搜索数据对象失败！", e);
            ecpBaseResponseVO.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_FAILURE);
            ecpBaseResponseVO.setResultMsg("删除搜索数据对象失败！");
        }
        return ecpBaseResponseVO;
    }
}