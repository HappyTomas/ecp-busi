/**
 * CmsModularDynamicController.java	  V1.0   2016年5月1日 下午9:30:15
 *
 * Copyright 2016 AsiaInfoData Technology Co.,Ltd. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package com.zengshi.ecp.busi.pageConfig.pageConfig.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zengshi.ecp.base.vo.EcpBaseResponseVO;
import com.zengshi.ecp.busi.order.util.ParamsTool;
import com.zengshi.ecp.busi.pageConfig.pageConfig.modular.utils.CmsGoodsDetailUtil;
import com.zengshi.ecp.busi.pageConfig.pageConfig.vo.CmsItemPropReqVO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsItemPropPreRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsModularParaPropRespDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsPicHotPreRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsItemPropPreRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsModularParaPropRSV;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsPicHotPreRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * 功能描述：模块动态表单处理
 *
 * @author  曾海沥(Terry)
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
@Controller
@RequestMapping(value="/modular-dynamic")
public class CmsModularDynamicController extends CmsModularFormBase {
	
	@Resource(name = "cmsItemPropPreRSV")
	private ICmsItemPropPreRSV cmsItemPropPreRSV;
	
	@Resource(name = "cmsPicHotPreRSV")
	private ICmsPicHotPreRSV cmsPicHotPreRSV;
	
	@Resource
	private ICmsModularParaPropRSV iCmsModularParaPropRSV;
	
	private String MODUAL = CmsModularDynamicController.class.getName();
	/**
	 * 表单数据保存处理
	 * 执行数据保存到数据库处理
	 */
	@Override
	protected EcpBaseResponseVO modularFormSave(Model model,List<CmsItemPropPreReqDTO> items) throws Exception{
		EcpBaseResponseVO respResult = new EcpBaseResponseVO();
		try {
			if(items != null && items.size() > 0){
				cmsItemPropPreRSV.updateCmsItemPropPreList(items);
				//整合，先删除，后录入  放入同一事务中
//				for (CmsItemPropPreReqDTO item : items) {
//					item.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
//					CmsItemPropPreRespDTO resp;
//					if(item.getId() == null)
//						//新增
//						resp = cmsItemPropPreRSV.addCmsItemPropPre(item);
//					else
//						//修改
//						resp = cmsItemPropPreRSV.updateCmsItemPropPre(item);
//					
//				
//				}
			}
			respResult.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			respResult.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			respResult.setResultMsg(e.getMessage());
			e.printStackTrace();
		}
		return respResult;
	}
	
	/**
	 * 
	 * 功能描述：转向公用属性设置界面
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年4月28日 下午5:25:08</p>
	 *
	 * @param model
	 * @param pageId
	 * @param modularId
	 * @return
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/publicSet")
	public String publicSet(Model model, Long pageId,Long modularId,Long itemId,String modularType) throws Exception{
		model.addAttribute("modularId", modularId);
		model.addAttribute("pageId", pageId);
		model.addAttribute("itemId", itemId);
		model.addAttribute("modularType", modularType);
		return "/pageConfig/pageConfig/edit/public/public-set";
	}
	
	/**
	 * 
	 * 功能描述：动态表单数据保存（非定制化页面）
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月1日 下午9:43:15</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/publicFormSave")
	@ResponseBody
	public EcpBaseResponseVO publicFormSave(Model model,CmsItemPropReqVO forms) throws Exception{
		EcpBaseResponseVO resp = this.doDynamicFormSave(model,forms);
		return resp;
	}
	/**
	 * 
	 * 功能描述：热点保存
	 *
	 * @author  yedw
	 * <p>创建日期 ：2016年5月1日 下午9:43:15</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/saveHotImg")
	@ResponseBody
	public EcpBaseResponseVO saveHotImg(Model model,CmsPicHotPreReqDTO req) throws Exception{
		EcpBaseResponseVO resp = new EcpBaseResponseVO();
		try {
			CmsPicHotPreRespDTO respDto =null;
			CmsItemPropPreReqDTO itemPropPreReq=new CmsItemPropPreReqDTO();
			itemPropPreReq.setId(req.getItemPropId());
			CmsItemPropPreRespDTO itemPropPreRsep= cmsItemPropPreRSV.queryCmsItemPropPre(itemPropPreReq);
			req.setPicId(itemPropPreRsep.getPropValue());
			if (null != req.getId()) {
				respDto=this.cmsPicHotPreRSV.updateCmsPicHotPre(req);
			} else {
				req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
				respDto=this.cmsPicHotPreRSV.addCmsPicHotPre(req);
			}
			resp.setResultMsg(respDto.getId().toString());
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
			e.printStackTrace();
		}
		return resp;
	}
	/**
	 * 
	 * 功能描述：删除热点
	 *
	 * @author  yedw
	 * <p>创建日期 ：2016年5月1日 下午9:43:15</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/delHotImg")
	@ResponseBody
	public EcpBaseResponseVO delHotImg(Model model,CmsPicHotPreReqDTO req) throws Exception{
		EcpBaseResponseVO resp = new EcpBaseResponseVO();
		try {
			CmsPicHotPreRespDTO respDto =null;
			req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_2);
			respDto=this.cmsPicHotPreRSV.updateCmsPicHotPre(req);
			resp.setResultMsg(respDto.getId().toString());
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_SUCCESS);
		} catch (Exception e) {
			resp.setResultFlag(EcpBaseResponseVO.RESULT_FLAG_EXCEPTION);
			resp.setResultMsg(e.getMessage());
			e.printStackTrace();
		}
		return resp;
	}
	
	/**
	 * 
	 * 功能描述：动态表单数据读取
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月1日 下午9:55:12</p>
	 *
	 * @param model
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/publicFormLoad")
	@ResponseBody
	public JSONObject publicFormLoad(Model model,Long pageId,Long itemId){
		CmsItemPropPreReqDTO req = new CmsItemPropPreReqDTO();
		req.setPageId(pageId);
		req.setItemId(itemId);
		Set<String> status = new HashSet<String>();
		status.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
		status.add(CmsConstants.ParamStatus.CMS_PARAMSTATUS_1);
		req.setStatusSet(status);
		//req.setStatus(CmsConstants.ParamStatus.CMS_PARAMSTATUS_0);
		List<CmsItemPropPreRespDTO> props = cmsItemPropPreRSV.queryCmsItemPropPreList(req);
		JSONObject obj = new JSONObject();
		obj.put("props", props);
		return obj;
	}
	/**
	 * 
	 * 功能描述：根据文件ID获得图片完整URL
	 *
	 * @author  曾海沥(Terry)
	 * <p>创建日期 ：2016年5月5日 上午10:48:49</p>
	 *
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@RequestMapping("/loadImgById")
	@ResponseBody
	public JSONObject loadImgById(Model model,String fileId,String width,String height){
		JSONObject obj = new JSONObject();
		String imgUrl = "";
		String standard = "";
		if(CmsGoodsDetailUtil.isNumeric(width)){
		    standard += width + "x";
		    if(CmsGoodsDetailUtil.isNumeric(height)){
		        standard += height;
		    }
		    standard += "!";
		}
		if(StringUtils.isNotEmpty(fileId)) {
		    imgUrl = ParamsTool.getImageUrl(fileId,standard);
		}
		obj.put("imgUrl", imgUrl);
		return obj;
	}
	
	/**
     * 
     * commonModularFormSave:(另一种公共保存预览布局项属性). <br/> 
     * 
     * @author gxq 
     * @param model
     * @param forms
     * @return 
     * @since JDK 1.6
     */
    @RequestMapping("/commonModularFormSave")
    @ResponseBody
    public EcpBaseResponseVO commonModularFormSave(Model model,CmsItemPropReqVO forms){
        //根据模块属性id propId 获取属性的其他信息
//    	System.out.println("----------------提交个数：------"+forms.getForms().size());
        for(CmsItemPropPreReqDTO propVO : forms.getForms()){
            CmsModularParaPropReqDTO dto = new CmsModularParaPropReqDTO();
            dto.setId(propVO.getPropId());
            CmsModularParaPropRespDTO paraDto = iCmsModularParaPropRSV.queryCmsModularParaProp(dto);
            if(paraDto != null){
                propVO.setPropName(paraDto.getPropName());
                propVO.setIfHaveto(paraDto.getIfHaveto());
                propVO.setPropValueType(paraDto.getPropValueType());
                if(null==propVO.getControlPropId()){
                	propVO.setControlPropId(paraDto.getControlPropId());
                }
                if(null==propVO.getSortNo()){
                	propVO.setSortNo(paraDto.getSortNo());
                }
                propVO.setIsAutobuild(paraDto.getIsAutobuild());
            }
        }
        EcpBaseResponseVO resp = new EcpBaseResponseVO();
        try {
            resp = modularFormSave(model,forms.getForms());
        } catch (BusinessException e) {
            LogUtil.error(MODUAL, "保存失败！", e);
        } catch (Exception e) {
            LogUtil.error(MODUAL, "保存失败！", e);
        }
        return resp;
    }
}
