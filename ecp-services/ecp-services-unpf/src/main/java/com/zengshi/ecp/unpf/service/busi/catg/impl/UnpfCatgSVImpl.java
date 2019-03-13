package com.zengshi.ecp.unpf.service.busi.catg.impl;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.aip.third.dubbo.dto.req.BaseShopAuthReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.req.CatgReqDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.CatgsRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropRespDTO;
import com.zengshi.ecp.aip.third.dubbo.dto.resp.PropValueRespDTO;
import com.zengshi.ecp.aip.third.dubbo.interfaces.ICatgRSV;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCategorySyncRSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.UnpfPropValueReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;
import com.zengshi.ecp.unpf.service.busi.auth.interfaces.IUnpfShopAuthSV;
import com.zengshi.ecp.unpf.service.busi.catg.interfaces.IUnpfCatgSV;
import com.zengshi.ecp.unpf.service.busi.task.CatgTaskSVImpl;
import com.zengshi.ecp.unpf.service.common.interfaces.IUnpfCategorySV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
* @author  huangjx: 
* @date 创建时间：2016年11月16日 上午9:30:20 
* @version 1.0 
* 
*  */
public class UnpfCatgSVImpl extends GeneralSQLSVImpl implements IUnpfCatgSV {
	
	private final String MODULE = getClass().getName();
 
	@Resource
	private ICatgRSV catgRSV;
	
	@Resource
	private IUnpfShopAuthSV unpfShopAuthSV;
	
	@Resource
	private IGdsCategorySyncRSV gdsCategorySyncRSV;
	
	@Resource
	private IUnpfCategorySV unpfCategorySV;
	
	
	/**
	* 分类保存
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	@Override
	public void saveCatg(CatgReqDTO catgReqDTO) throws BusinessException{
		
		//0组织参数 传入授权id
		//1 调用分类aip接口
		//2 保存数据到数据  直接原来数据失效掉  insert新数据
		
		//组织参数 传入授权id
		if(catgReqDTO==null || catgReqDTO.getAuthId()==null){
			throw new BusinessException("unpf.100003");
		}
	 
		UnpfShopAuthReqDTO req=new UnpfShopAuthReqDTO();
		req.setId( catgReqDTO.getAuthId());
		//查询店铺授权基本信息
		UnpfShopAuthRespDTO resp=unpfShopAuthSV.queryPlatType4ShopById(req);
		
		if(resp==null || resp.getId()==null){
			throw new BusinessException("unpf.100005");
		}
		
		BaseShopAuthReqDTO baseShopAuthReqDTO=new BaseShopAuthReqDTO();
		
		ObjectCopyUtil.copyObjValue(resp, baseShopAuthReqDTO, null, false);
		
		ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, catgReqDTO, null, false);
		catgReqDTO.setAuthId(resp.getId());
		
		CatgsRespDTO catgs=catgRSV.fetch(catgReqDTO);
		   
	    //先清空分类数据
		GdsCatgSyncReqDTO reqDto=new GdsCatgSyncReqDTO();
		reqDto.setShopId(catgReqDTO.getShopId());
		reqDto.setShopAuthId(catgReqDTO.getAuthId());
		reqDto.setSrcSystem(catgReqDTO.getPlatType());//系统类型
		
		gdsCategorySyncRSV.emptyGdsCategorySyncTable(reqDto);
		
		//由于获得外部接口dubbo方式 递归调用外部接口 时间较长 导致在insert数据 会dubbo超时
		
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        try{
      
            ArrayList<Future<String>> al=new ArrayList<Future<String>>();  
            if(catgs!=null){
    			if(!CollectionUtils.isEmpty(catgs.getCatgs())){
		    				for(CatgRespDTO c:catgs.getCatgs()){
		            //线程池队列
		            al.add(threadExecutor.submit(new CatgTaskSVImpl(gdsCategorySyncRSV,unpfCategorySV,catgReqDTO,c)));  
    				}
    			}
            }
            //结果值 返回处理
            threadExecutor.shutdown();   
        }catch(Exception ex){
            LogUtil.error("线程业务处理失败",ex.toString());
        }finally{
            if(threadExecutor!=null && !threadExecutor.isShutdown()){
                threadExecutor.shutdown();  
            }
        }
	
        
/*		//insert 数据
		if(catgs!=null){
			if(CollectionUtils.isNotEmpty(catgs.getCatgs())){
				for(CatgRespDTO c:catgs.getCatgs()){
					GdsCatgSyncReqDTO catgSyncReqDTO=new GdsCatgSyncReqDTO();
					ObjectCopyUtil.copyObjValue(c, catgSyncReqDTO, "", false);
					catgSyncReqDTO.setActionType(0);//添加
					catgSyncReqDTO.setCatgCode(c.getCatgCode());
					catgSyncReqDTO.setCatgName(c.getName());
					catgSyncReqDTO.setCatgParent(c.getParentCatgCode());
					catgSyncReqDTO.setCatgParentName(c.getParentCatgName());
					//catgSyncReqDTO.setCatgType(catgType); //无
					catgSyncReqDTO.setShopId(catgReqDTO.getShopId());
					catgSyncReqDTO.setShopAuthId(catgReqDTO.getAuthId());
					catgSyncReqDTO.setSrcSystem(catgReqDTO.getPlatType());//系统类型
					catgSyncReqDTO.setStatus("1");//有效
					try{
					    gdsCategorySyncRSV.addGdsCatgSyncInfo(catgSyncReqDTO);
					}catch(BusinessException bx){
						LogUtil.error(MODULE, bx.toString());
					}
				}
				
			}
		}*/
	}
	/**
	* 分类 属性  属性值 保存
	* 
	* @author huangjx
	* @param
	* @return 
	* @throws 
	*/
	@Override
	public void saveCatgAndProp(CatgReqDTO catgReqDTO) throws BusinessException{
		//0组织参数 传入授权id
		//1 调用分类 属性aip接口
		//2 保存数据到数据  直接原来数据(分类 属性 属性值)失效掉  insert新数据
		
			if(catgReqDTO==null || catgReqDTO.getAuthId()==null){
				throw new BusinessException("unpf.100003");
			}
		 
			UnpfShopAuthReqDTO req=new UnpfShopAuthReqDTO();
			req.setId( catgReqDTO.getAuthId());
			//查询店铺授权基本信息
			UnpfShopAuthRespDTO resp=unpfShopAuthSV.queryPlatType4ShopById(req);
			
			if(resp==null || resp.getId()==null){
				throw new BusinessException("unpf.100005");
			}
			
			BaseShopAuthReqDTO baseShopAuthReqDTO=new BaseShopAuthReqDTO();
			
			ObjectCopyUtil.copyObjValue(resp, baseShopAuthReqDTO, null, false);
			
			ObjectCopyUtil.copyObjValue(baseShopAuthReqDTO, catgReqDTO, null, false);
			catgReqDTO.setAuthId(resp.getId());
			CatgsRespDTO catgs=catgRSV.fetchCatgAndProp(catgReqDTO);
			
			 //先清空分类数据
			GdsCatgSyncReqDTO reqDto=new GdsCatgSyncReqDTO();
			reqDto.setShopId(catgReqDTO.getShopId());
			reqDto.setShopAuthId(catgReqDTO.getAuthId());
			reqDto.setSrcSystem(catgReqDTO.getPlatType());//系统类型
			gdsCategorySyncRSV.emptyGdsCategorySyncTable(reqDto);
			
			//属性清空
			UnpfPropReqDTO unpfPropReqDTO=new UnpfPropReqDTO();
			unpfPropReqDTO.setShopAuthId(catgReqDTO.getAuthId());
			unpfPropReqDTO.setShopId(catgReqDTO.getShopId());
			unpfPropReqDTO.setPlatType(catgReqDTO.getPlatType());
			unpfCategorySV.deleteUnpfPropTable(unpfPropReqDTO);
			
			//属性值清空
			UnpfPropValueReqDTO pv=new UnpfPropValueReqDTO();
			pv.setShopAuthId(catgReqDTO.getAuthId());
			pv.setShopId(catgReqDTO.getShopId());
			pv.setPlatType(catgReqDTO.getPlatType());
			unpfCategorySV.deleteUnpfPropValueTable(pv);
			
			//由于获得外部接口dubbo方式 递归调用外部接口 时间较长 导致在insert数据 会dubbo超时
			
	        ExecutorService threadExecutor = Executors.newCachedThreadPool();
	        try{
	      
	        	int k=0;
	            ArrayList<Future<String>> al=new ArrayList<Future<String>>();  
	            if(catgs!=null){
	    			if(!CollectionUtils.isEmpty(catgs.getCatgs())){
						for(CatgRespDTO c:catgs.getCatgs()){
							if(c.getCatgCode().equals(c.getParentCatgCode())){
								continue;
							}
							//线程池队列
							al.add(threadExecutor.submit(new CatgTaskSVImpl(gdsCategorySyncRSV,unpfCategorySV,catgReqDTO,c)));
	    				}
	    			}
	            }
	            //结果值 返回处理
	            threadExecutor.shutdown();   
	        }catch(Exception ex){
	            LogUtil.error("线程业务处理失败",ex.toString());
	        }finally{
	            if(threadExecutor!=null && !threadExecutor.isShutdown()){
	                threadExecutor.shutdown();  
	            }
	        }
	}
}


