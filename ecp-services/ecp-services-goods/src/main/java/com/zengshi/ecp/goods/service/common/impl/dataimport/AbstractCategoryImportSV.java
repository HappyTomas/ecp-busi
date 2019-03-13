package com.zengshi.ecp.goods.service.common.impl.dataimport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceCatg;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgSyncReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.DataImportConstants;
import com.zengshi.ecp.goods.dubbo.dto.category.dataimport.GdsInterfaceCatgReqDTO;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsInterfaceCatgSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;

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
public abstract class AbstractCategoryImportSV extends AbstractSVImpl{
	
	@Resource(name = "seq_gds_interface_catgr")
	protected PaasSequence seqGdsInterfaceCatgr;

	@Resource
	protected IGdsCategorySV gdsCategorySV;

	@Resource
	protected IGdsInterfaceCatgSV gdsInterfaceCatgSV;
	
	

	public void executeImport(Map<String, Object> map) throws BusinessException {
		// 创建中间表信息。
		GdsInterfaceCatgReqDTO interfaceCatgReqDTO = buildGdsInterfaceCatg(map);
		interfaceCatgReqDTOCheck(interfaceCatgReqDTO);
		// 创建分类信息。
		GdsCategoryReqDTO catgReqDTO = buildGdsCategoryReqDTO(interfaceCatgReqDTO);

		executeCRUD(catgReqDTO, interfaceCatgReqDTO);
		
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
		private void executeCRUD(GdsCategoryReqDTO categoryReqDTO,
				GdsInterfaceCatgReqDTO interfaceCatgReqDTO) {

			GdsInterfaceCatgReqDTO condition = new GdsInterfaceCatgReqDTO();

			condition.setOriginCatgCode(interfaceCatgReqDTO.getOriginCatgCode());
			condition.setOrigin(interfaceCatgReqDTO.getOrigin());

			// 根据来源与源分类查询已存在关联。
			GdsInterfaceCatg existRecord = gdsInterfaceCatgSV
					.queryInterfaceCatgByOriginAndOriginCatgCode(condition);

			// 存在原始关联关联（说明之前同步过）
			switch (interfaceCatgReqDTO.getActionType()) {
			case GdsDataImportConstants.ActionType.ADD:
			    if(isSkipProcess(interfaceCatgReqDTO)){
			        break;
			    }
				addCategory(categoryReqDTO, interfaceCatgReqDTO, existRecord);
				break;
			case GdsDataImportConstants.ActionType.DELETE:
				deleteCategory(interfaceCatgReqDTO, existRecord);
				break;
			case GdsDataImportConstants.ActionType.UPDATE:
			    if(isSkipProcess(interfaceCatgReqDTO)){
                    break;
                }
				updateCategory(categoryReqDTO,interfaceCatgReqDTO, existRecord);
				break;
			default:
				throw new BusinessException("error.goods.dataimport.1",new String[]{interfaceCatgReqDTO.getActionType().toString()});
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
		private void updateCategory(GdsCategoryReqDTO categoryReqDTO, GdsInterfaceCatgReqDTO interfaceCatgReqDTO,
				GdsInterfaceCatg existRecord) {
			// 不存在原始关联关系(代表未同步过)
			if (null == existRecord) {
				LogUtil.info(MODULE, "执行分类更新,无原始对应记录，执行新增!interfaceCatgReqDTO:"
						+ ToStringBuilder.reflectionToString(interfaceCatgReqDTO));
				this.addCategory(categoryReqDTO, interfaceCatgReqDTO, existRecord);
			} else {
				GdsInterfaceCatgReqDTO dto = new GdsInterfaceCatgReqDTO();
				dto.setOriginCatgCode(interfaceCatgReqDTO.getOriginCatgCode());
				dto.setOrigin(interfaceCatgReqDTO.getOrigin());
				// 查询当前分类信息.
				GdsCategoryRespDTO catg = gdsInterfaceCatgSV
						.queryCategoryByOriginCatgCode(dto);
				if (null == catg) {
					LogUtil.error(MODULE, "分类不存在,更新失败!");
					throw new BusinessException(
							GdsErrorConstants.GdsCategory.ERROR_GOODS_CATEGORY_200303);
				}
				
				categoryReqDTO.setCatgCode(catg.getCatgCode());

				// TODO 父节点变更逻辑后继编写.
				GdsCategoryReqDTO updateReqDTO = new GdsCategoryReqDTO();
				ObjectCopyUtil.copyObjValue(categoryReqDTO, updateReqDTO, null, true);
                
                String updateRule = existRecord.getUpdateRule();
				if (StringUtil.isNotBlank(updateRule)) {
					List<String> lst = JSONObject.parseObject(updateRule, List.class);
					if (lst.contains(DataImportConstants.GdsCategoryUpdateChkFields.CATG_NAME)) {
						updateReqDTO.setCatgName(catg.getCatgName());
					}
					if (lst.contains(DataImportConstants.GdsCategoryUpdateChkFields.SORT_NO)) {
						updateReqDTO.setSortNo(catg.getSortNo());
					}
				}
				gdsCategorySV.editGdsCategory(updateReqDTO);
				dto.setOriginCatgName(updateReqDTO.getCatgName());
				dto.setUpdateStaff(getStaffId());
				gdsInterfaceCatgSV.updateInterfaceCatg(dto);
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
		protected void deleteCategory(GdsInterfaceCatgReqDTO interfaceCatgReqDTO,
				GdsInterfaceCatg existRecord) {
			   
			LogUtil.info(MODULE, "执行分类删除操作,原始分类来源:["+interfaceCatgReqDTO.getOrigin()+"]原始分类ID["+interfaceCatgReqDTO.getOriginCatgCode()+"]");
			/*if(null == existRecord){
				throw new BusinessException("关系映射表找不到origin=["+interfaceCatgReqDTO.getOrigin()+"];originCode=["+interfaceCatgReqDTO.getOriginCatgCode()+"]的分类ID,删除操作失败");
			}*/
			/*// 不存在原始关联关系(代表未同步过)
			if (null == existRecord) {
				LogUtil.info(MODULE, "执行分类删除,无原始对应记录，删除失败!interfaceCatgReqDTO:"
						+ ToStringBuilder.reflectionToString(interfaceCatgReqDTO));
				throw new BusinessException();
			} else {*/
				/*GdsInterfaceCatgReqDTO dto = new GdsInterfaceCatgReqDTO();
				dto.setOriginCatgCode(interfaceCatgReqDTO.getOriginCatgCode());
				dto.setOrigin(interfaceCatgReqDTO.getOrigin());
				*/
				GdsCategoryRespDTO catg = gdsInterfaceCatgSV
						.queryCategoryByOriginCatgCode(interfaceCatgReqDTO);
				if (null != catg) {
					/*GdsCategoryReqDTO delCondition = new GdsCategoryReqDTO();
					delCondition.setCatgCode(catg.getCatgCode());
					gdsCategorySV.deleteGdsCategory(delCondition);*/
					gdsCategorySV.executeDisableGdsCategory(catg.getCatgCode(), getStaffId());
					GdsInterfaceCatgReqDTO interfaceCatgDel = new GdsInterfaceCatgReqDTO();
					interfaceCatgDel.setCatgCode(catg.getCatgCode());
					gdsInterfaceCatgSV.deleteInterfaceCatgByCatgCode(interfaceCatgDel);
				}
			}
		//}

		/**
		 * addCategory:(这里用一句话描述这个方法的作用). <br/>
		 * TODO(这里描述这个方法适用条件 – 可选).<br/>
		 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
		 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
		 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
		 * 
		 * @author liyong7
		 * @param categoryReqDTO
		 * @param interfaceCatgReqDTO
		 * @param existRecord
		 * @since JDK 1.6
		 */
		private void addCategory(GdsCategoryReqDTO categoryReqDTO,
				GdsInterfaceCatgReqDTO interfaceCatgReqDTO,
				GdsInterfaceCatg existRecord) {
			LogUtil.info(
					MODULE,
					"执行分类添加...,interfaceCatgReqDTO:"
							+ ToStringBuilder
									.reflectionToString(interfaceCatgReqDTO));
			if(null != existRecord){
				updateCategory(categoryReqDTO, interfaceCatgReqDTO, existRecord);
			}else{
				GdsCategoryRespDTO saveResult = gdsCategorySV
						.saveGdsCategory(categoryReqDTO);
				interfaceCatgReqDTO.setCatgCode(saveResult.getCatgCode());
				gdsInterfaceCatgSV.saveGdsInterfaceCatg(interfaceCatgReqDTO);
			}
			
		}


		protected abstract GdsCategoryReqDTO buildGdsCategoryReqDTO(
				GdsInterfaceCatgReqDTO reqDTO) ;

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
		protected abstract GdsInterfaceCatgReqDTO buildGdsInterfaceCatg(Map<String, Object> map);
		
		/**
         * 
         * isSkipProcess:是否跳过当前信息不作处理. <br/> 
         * 
         * @author liyong7 
         * @param reqDTO
         * @return 
         * @since JDK 1.6
         */
        protected boolean isSkipProcess(GdsInterfaceCatgReqDTO reqDTO){
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

		private void interfaceCatgReqDTOCheck(GdsInterfaceCatgReqDTO reqDTO) {
			paramNullCheck(reqDTO);
			paramCheck(
					new Object[] { reqDTO.getActionType(),
							reqDTO.getOriginCatgCode(), reqDTO.getOriginCatgName() },
					new String[] { "reqDTO.actionType", "reqDTO.originCatgCode",
							"reqDTO.originCatgName" });
		}


}
