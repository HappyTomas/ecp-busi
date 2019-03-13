package com.zengshi.ecp.goods.service.common.impl.dataimport;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySyncSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description:分类导入抽象类. <br>
 * Date:2015-10-27下午2:31:41 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public abstract class AbstractCategorySyncImportSV extends AbstractSVImpl{
	
	@Resource(name = "seq_gds_category_sync")
	protected PaasSequence seqGdsInterfaceCatgr;
	
	@Resource
	protected IGdsCategorySyncSV gdsCategorySyncSV;


	public void executeImport(Map<String, Object> map) throws BusinessException {
		// 创建分类同步中间表信息。
		GdsCatgSyncReqDTO gdsCatgSyncReqDTO = buildGdsCatgSyncReqDTO(map);
		// 参数检查.
		paramsCheck(gdsCatgSyncReqDTO);
		// 执行增删改查.
		try{
		  executeCRUD(gdsCatgSyncReqDTO);
		}catch (Exception e) {
		  LogUtil.error(MODULE, "外系统分类同步异常!gdsCatgSyncReqDTO信息:"+ToStringBuilder
					.reflectionToString(gdsCatgSyncReqDTO),e);
		  throw new BusinessException("error.categorysync.dataimport.1");
		}
		
	}
	
	    // ************************
		// ****private methods*****
		// ************************

		/**
		 * 
		 * 针对同步数据执行增删改。
		 * 
		 * @author liyong7
		 * @param categoryReqDTO
		 * @param interfaceCatgReqDTO
		 * @since JDK 1.6
		 */
		private void executeCRUD(GdsCatgSyncReqDTO reqDTO) throws Exception{
			
			GdsCatgSyncReqDTO query = new GdsCatgSyncReqDTO();
			
			query.setCatgCode(reqDTO.getCatgCode());
			query.setSrcSystem(reqDTO.getSrcSystem());

			// 根据来源与源分类查询已存在关联。
			GdsCatgSyncRespDTO existRecord = gdsCategorySyncSV.queryGdsCategoryInfoByOriginCatgCode(query);

			// 存在原始关联关联（说明之前同步过）
			switch (reqDTO.getActionType()) {
			case GdsDataImportConstants.ActionType.ADD:
			    if(isSkipProcess(reqDTO)){
                    break;
                }
				addCategorySync(reqDTO, existRecord);
				break;
			case GdsDataImportConstants.ActionType.DELETE:
				deleteCategorySync(reqDTO, existRecord);
				break;
			case GdsDataImportConstants.ActionType.UPDATE:
			    if(isSkipProcess(reqDTO)){
                    break;
                }
				updateCategorySync(reqDTO,existRecord);
				break;
			default:
				throw new BusinessException("error.goods.dataimport.1",new String[]{reqDTO.getActionType().toString()});
			}

		}

		/**
		 * updateCategory:(这里用一句话描述这个方法的作用). <br/>
		 * TODO(这里描述这个方法适用条件 – 可选).<br/>
		 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
		 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
		 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
		 * 
		 * @author liyong7
		 * @param interfaceCatgReqDTO
		 * @param existRecord
		 * @since JDK 1.6
		 */
		private void updateCategorySync(GdsCatgSyncReqDTO reqDTO, GdsCatgSyncRespDTO existRecord) throws Exception{
			// 不存在原始关联关系(代表未同步过)
			if (null == existRecord) {
				LogUtil.warn(MODULE, "外系统原始分类不存在,执行Insert操作!reqDTO="+ToStringBuilder.reflectionToString(reqDTO));
				reqDTO.setMapCatgCode(GdsDataImportConstants.Commons.DEFAULT_MAP_CATG_CODE);
				gdsCategorySyncSV.addGdsCatgSyncInfo(reqDTO);
//				throw new BusinessException(
//						GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303);
			}else{
				reqDTO.setId(existRecord.getId());
				gdsCategorySyncSV.updateGdsCatgSyncInfo(reqDTO);
			}
		}
		

		/**
		 * deleteCategory:(这里用一句话描述这个方法的作用). <br/>
		 * TODO(这里描述这个方法适用条件 – 可选).<br/>
		 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
		 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
		 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
		 * 
		 * @author liyong7
		 * @param interfaceCatgReqDTO
		 * @param existRecord
		 * @since JDK 1.6
		 */
		protected void deleteCategorySync(GdsCatgSyncReqDTO reqDTO,
				GdsCatgSyncRespDTO existRecord) throws Exception{
				LogUtil.info(MODULE, "执行外系统分类删除操作,原始分类来源:["+reqDTO.getSrcSystem()+"]原始分类ID["+reqDTO.getCatgCode()+"]");
				gdsCategorySyncSV.deleteGdsCatgSyncInfo(reqDTO);
			}
		
		
		private void addCategorySync(GdsCatgSyncReqDTO reqDTO,
				GdsCatgSyncRespDTO existRecord) throws Exception{
			LogUtil.info(
					MODULE,
					"执行分类添加...,reqDTO:"
							+ ToStringBuilder
									.reflectionToString(reqDTO));
			if(null != existRecord){
				updateCategorySync(reqDTO, existRecord);
			}else{
				reqDTO.setMapCatgCode(GdsDataImportConstants.Commons.DEFAULT_MAP_CATG_CODE);
				gdsCategorySyncSV.addGdsCatgSyncInfo(reqDTO);
			}
			
		}

		/*
		 * 
		 * 返回分类中间表映射对象。
		 * 
		 * @author liyong7
		 * 
		 * @param map
		 * 
		 * @return
		 * 
		 * @since JDK 1.6
		 */
		protected abstract GdsCatgSyncReqDTO buildGdsCatgSyncReqDTO(Map<String, Object> map);

		private void paramsCheck(GdsCatgSyncReqDTO reqDTO) {
			paramNullCheck(reqDTO);
			paramCheck(
					new Object[] { reqDTO.getActionType(),
							reqDTO.getCatgCode(), reqDTO.getCatgName() },
					new String[] { "reqDTO.actionType", "reqDTO.catgCode",
							"reqDTO.catgName" });
		}
		
		/**
		 * 
		 * isSkipProcess:是否跳过当前信息不作处理. <br/> 
		 * 
		 * @author liyong7 
		 * @param reqDTO
		 * @return 
		 * @since JDK 1.6
		 */
		protected boolean isSkipProcess(GdsCatgSyncReqDTO reqDTO){
		    return false;
		}

		/**
         * 
         * getStaffId:获取员工ID. <br/> 
         * 
         * @author liyong7
         * @return 
         * @since JDK 1.6
         */
        protected abstract Long getStaffId();

}
