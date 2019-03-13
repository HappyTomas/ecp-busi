package com.zengshi.ecp.unpf.service.busi.task;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropValueRespDTO;
import com.zengshi.ecp.frame.context.EcpFrameContextHolder;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueRespDTO;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2016-2-17 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CatgTaskSVImpl implements Callable<String> {
	
	private final static Logger logger = LoggerFactory
			.getLogger(CatgTaskSVImpl.class);

    private final CatgRespDTO catgRespDTO;
    
    private final CatgReqDTO catgReqDTO;
    
	private final IGdsCategorySyncRSV gdsCategorySyncRSV;
	
	private final IUnpfCategorySV unpfCategorySV;
   
    
    public CatgTaskSVImpl(IGdsCategorySyncRSV gdsCategorySyncRSV,IUnpfCategorySV unpfCategorySV,CatgReqDTO catgReqDTO,CatgRespDTO catgRespDTO)

    {
       this.gdsCategorySyncRSV=gdsCategorySyncRSV;
       this.unpfCategorySV=unpfCategorySV;
       this.catgRespDTO=catgRespDTO;
       this.catgReqDTO=catgReqDTO;
    }

    public String call() throws Exception  
    {
        try
        {
        	
			GdsCatgSyncReqDTO catgSyncReqDTO=new GdsCatgSyncReqDTO();
			ObjectCopyUtil.copyObjValue(catgRespDTO, catgSyncReqDTO, "", false);
			catgSyncReqDTO.setActionType(0);//添加
			catgSyncReqDTO.setCatgCode(catgRespDTO.getCatgCode());
			catgSyncReqDTO.setCatgName(catgRespDTO.getName());
			catgSyncReqDTO.setCatgParent(catgRespDTO.getParentCatgCode());
			catgSyncReqDTO.setCatgParentName(catgRespDTO.getParentCatgName());
			//catgSyncReqDTO.setCatgType(catgType); //无
			catgSyncReqDTO.setShopId(catgReqDTO.getShopId());
			catgSyncReqDTO.setShopAuthId(catgReqDTO.getAuthId());
			catgSyncReqDTO.setSrcSystem(catgReqDTO.getPlatType());//系统类型
			catgSyncReqDTO.setStatus("1");//有效
			gdsCategorySyncRSV.addGdsCatgSyncInfo(catgSyncReqDTO);
			
			//属性 以及属性值 存储
			if(catgRespDTO!=null && catgRespDTO.getProps()!=null && catgRespDTO.getProps().size()>0){
				for( PropRespDTO p:catgRespDTO.getProps()){
					UnpfPropReqDTO reqDTO =new UnpfPropReqDTO();
					reqDTO.setCatgCode(catgRespDTO.getCatgCode());
					
					if(p.getIfInputProp()!=null && p.getIfInputProp()){
						reqDTO.setIfAbleEidt("1");
						reqDTO.setPropInputType("1");
						reqDTO.setPropValueType("1");
					}
					
					if(p.getMult()!=null && p.getMult()){
						reqDTO.setPropValueType("3");
					}
					reqDTO.setPropType("2");//参数属性
					if(p.getIfSaleProp()!=null && p.getIfSaleProp()){
						reqDTO.setPropType("1");//规格属性
					} 
					reqDTO.setPropCode(p.getPid().toString());
					reqDTO.setPropDesc(p.getPname());
					//reqDTO.setPropInputRule(propInputRule);
					reqDTO.setPropName(p.getPname());
					//reqDTO.setPropSname(propSname);
					if(p.getSortOrder()!=null){
						reqDTO.setSortNo(p.getSortOrder().intValue());
					}else{
						reqDTO.setSortNo(0);
					}
					reqDTO.setStatus(p.getStatus());
					reqDTO.setShopAuthId(catgReqDTO.getAuthId());
					reqDTO.setShopId(catgReqDTO.getShopId());
					reqDTO.setPlatType(catgReqDTO.getPlatType());
					
					
					List<UnpfPropRespDTO> listPropRespDTOs=unpfCategorySV.queryUnpfProp(reqDTO);
					
					if(listPropRespDTOs==null || CollectionUtils.isEmpty(listPropRespDTOs)){
					
						Long id=unpfCategorySV.insertUnpfProp(reqDTO);
						
						//属性值处理
						if(catgRespDTO!=null && p.getPropValues()!=null && p.getPropValues().size()>0){
							
							for( PropValueRespDTO pv:p.getPropValues()){
								UnpfPropValueReqDTO pvalue=new UnpfPropValueReqDTO();
								pvalue.setCatgCode(reqDTO.getCatgCode());
								pvalue.setPropCode(reqDTO.getPropCode());
								pvalue.setPropRelaId(id);
								if(pv.getVid()!=null){
									pvalue.setPropVCode(pv.getVid().toString());
								}
								pvalue.setPropVDesc(pv.getName());
								pvalue.setPropVName(pv.getName());
								if(StringUtils.isNotEmpty(pv.getNameAlias())){
								     pvalue.setPropVSname(pv.getNameAlias());
								}else{
									 pvalue.setPropVSname(pv.getName());	
								}
								if(pv.getSortOrder()!=null){
									pvalue.setSortNo(pv.getSortOrder().intValue());
								}else{
									pvalue.setSortNo(0);
								}
								pvalue.setStatus(pv.getStatus());
								pvalue.setShopAuthId(catgReqDTO.getAuthId());
								pvalue.setShopId(catgReqDTO.getShopId());
								pvalue.setPlatType(catgReqDTO.getPlatType());
								
								List<UnpfPropValueRespDTO> propValueList=unpfCategorySV.queryUnpfPropValue(pvalue);
								if(propValueList==null || CollectionUtils.isEmpty(propValueList)){
								     unpfCategorySV.insertUnpfPropValue(pvalue);
								  }
								}
							}
					}
				}
				
			}
			
        }
        catch (Exception ex)
        {
             logger.error("分类 线程入库数据 业务处理失败"+ex.getMessage());
		 
        }
		return null;
    }
}
