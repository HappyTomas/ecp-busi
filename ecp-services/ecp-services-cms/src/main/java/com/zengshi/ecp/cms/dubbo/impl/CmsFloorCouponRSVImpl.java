package com.zengshi.ecp.cms.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO;
import com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponRespDTO;
import com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV;
import com.zengshi.ecp.cms.dubbo.util.CmsConstants;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsFloorCouponSV;
import com.zengshi.ecp.cms.service.common.interfaces.ICmsPlaceSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

public class CmsFloorCouponRSVImpl implements ICmsFloorCouponRSV{
	 	@Resource
	    private ICmsFloorCouponSV sv;
	    @Resource
	    private ICmsPlaceSV cmsPlaceSV;
	    
	    //定义个常量，用于表示模块名称，可以使用：当前类的类名
	    private static final String MODULE = CmsFloorCouponRSVImpl.class.getName();


	    /** 
	     * TODO 新增楼层促销. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#saveCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public CmsFloorCouponRespDTO addCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
	        LogUtil.info(MODULE, "调用新增楼层促销开始，入参：{dto="+dto.toString()+"}");
	        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
	        /*1.验证前店入参*/
	        if(StringUtil.isEmpty(dto.getCouponId())){
	            LogUtil.error(MODULE, "入参CouponId为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="CouponId";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        
	        /*2.调service层接口*/
	        try{   
	            respDTO = sv.addCmsFloorCoupon(dto);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "新增楼层促销失败:",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500101);
	        }
	        return respDTO;
	    }

	    /** 
	     * TODO 新增楼层促销. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#saveCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public List<CmsFloorCouponRespDTO> addCmsFloorCouponBatch(List<CmsFloorCouponReqDTO> list) throws BusinessException {
	        LogUtil.info(MODULE, "调用新增楼层促销开始，入参：{dto="+list.toString()+"}");
//	        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
	        /*1.验证前店入参*/
			for (int i = 0; i < list.size(); i++) {
				if (StringUtil.isEmpty(list.get(i).getCouponId())) {
					LogUtil.error(MODULE, "入参CouponId为空！");
					String[] keyInfos = new String[1];
					keyInfos[0] = "CouponId";
					throw new BusinessException(
							CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
				}
			}
	        /*2.调service层接口*/
			List<CmsFloorCouponRespDTO> cmsFloorCouponRespDTOs = null;
	        try{   
	        	cmsFloorCouponRespDTOs = sv.addCmsFloorCouponBatch(list);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "新增楼层促销失败:",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500101);
	        }
	        return cmsFloorCouponRespDTOs;
	    }
	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#updateCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public CmsFloorCouponRespDTO updateCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
	        LogUtil.info(MODULE, "调用更新楼层促销开始，入参：{dto="+dto.toString()+"}");
	        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
	        /*1.验证前店入参*/
	        if(StringUtil.isEmpty(dto.getId())){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="id";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        if(StringUtil.isEmpty(dto.getCouponId())){
	            LogUtil.error(MODULE, "入参CouponId为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="CouponId";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        /*if(dto.getTabId()==null){
	            LogUtil.error(MODULE, "入参TabId为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="TabId";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }*/
	        
	        /*2.调service层接口*/
	        try{    
	            respDTO = sv.updateCmsFloorCoupon(dto);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "更新楼层促销失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500102);
	        }
	        return respDTO;
	    }
	    
	    
	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#updateCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public List<CmsFloorCouponRespDTO> updateCmsFloorCouponBatch(List<CmsFloorCouponReqDTO> list) throws BusinessException {
	        LogUtil.info(MODULE, "调用更新楼层促销开始，入参：{dto="+list.toString()+"}");
//	        CmsFloorCouponRespDTO respDTO = new CmsFloorCouponRespDTO();
	        /*1.验证前店入参*/
			for (int i = 0; i < list.size(); i++) {
				if (StringUtil.isEmpty(list.get(i).getId())) {
					LogUtil.error(MODULE, "入参ID为空！");
					String[] keyInfos = new String[1];
					keyInfos[0] = "id";
					throw new BusinessException(
							CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
				}
				if (StringUtil.isEmpty(list.get(i).getCouponId())) {
					LogUtil.error(MODULE, "入参CouponId为空！");
					String[] keyInfos = new String[1];
					keyInfos[0] = "CouponId";
					throw new BusinessException(
							CmsConstants.MsgCode.CMS_COMMON_500102, keyInfos);
				}
			}
	        /*2.调service层接口*/
			List<CmsFloorCouponRespDTO> cmsFloorCouponRespDTOs = null;
	        try{    
	            cmsFloorCouponRespDTOs = sv.updateCmsFloorCouponBatch(list);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "更新楼层促销失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500102);
	        }
	        return cmsFloorCouponRespDTOs;
	    }
	    
	    /** 
	     * TODO 查询楼层促销，分页（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#queryCmsFloorCouponPage(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public PageResponseDTO<CmsFloorCouponRespDTO> queryCmsFloorCouponPage(CmsFloorCouponReqDTO dto) throws BusinessException {
	        LogUtil.info(MODULE, "调用分页查询楼层促销开始，入参：{dto="+dto.toString()+"}");
	        /* 1.验证前店入参 */
	        
	        /* 2.根据条件检索楼层促销  */
	        PageResponseDTO<CmsFloorCouponRespDTO> pageInfo = PageResponseDTO.buildByBaseInfo(dto, CmsFloorCouponRespDTO.class);
	        try{
	            pageInfo =  sv.queryCmsFloorCouponPage(dto);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "查询楼层促销失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500103);
	        }
	        return pageInfo;
	    }
	    
	    /** 
	     * TODO 查询楼层促销  无分页（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsPlaceRSV#queryCmsPlaceList(com.zengshi.ecp.cms.dubbo.dto.CmsPlaceReqDTO) 
	     */
	    @Override
	    public List<CmsFloorCouponRespDTO> queryCmsFloorCouponList(CmsFloorCouponReqDTO dto)
	            throws BusinessException {
	        LogUtil.info(MODULE, "调用查询楼层促销开始，入参：{dto="+dto.toString()+"}");
	        /* 1.验证前店入参 */
	        
	        /* 2.根据条件检索内容位置  */
	        List<CmsFloorCouponRespDTO> list = new ArrayList<CmsFloorCouponRespDTO>();
	        try {
	            list = sv.queryCmsFloorCouponList(dto);
	        } catch (Exception e) {
	            LogUtil.error(MODULE, "查询楼层促销出现异常！",e);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500103);
	        }
	        
	        return list;
	    }
	    
	    /** 
	     * TODO 查询单个楼层促销（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#queryCmsFloorCoupon(com.zengshi.ecp.cms.dubbo.dto.CmsFloorCouponReqDTO) 
	     */
	    @Override
	    public CmsFloorCouponRespDTO queryCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
	        LogUtil.info(MODULE, "调用查询楼层促销开始，入参：{dto="+dto.toString()+"}");
	        /* 1.验证前店入参 */
	        if(StringUtil.isEmpty(dto.getId())){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="id";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        
	        /* 2.根据条件检索楼层促销  */
	        CmsFloorCouponRespDTO cmsFloorCouponRespDTO = new CmsFloorCouponRespDTO();
	        try {
	            cmsFloorCouponRespDTO = sv.queryCmsFloorCoupon(dto);
	        } catch (Exception e) {
	            LogUtil.error(MODULE, "根据ID查询楼层促销出现异常！",e);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500103);
	        }
	        
	        return cmsFloorCouponRespDTO;
	    }
	    
	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#deleteCmsFloorCoupon(java.lang.Long) 
	     */
	    @Override
	    public void deleteCmsFloorCoupon(String id) throws BusinessException {
	        LogUtil.info(MODULE, "调用删除楼层促销开始，入参：{id="+id+"}");
	        /* 1.验证前店入参 */
	        if(StringUtil.isBlank(id)){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="id";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        
	        /* 2.逻辑删除楼层促销  */
	        try{
	            sv.deleteCmsFloorCoupon(id);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "删除楼层促销失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500101);
	        }
	    }

	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#deleteCmsFloorCouponBatch(java.util.List) 
	     */
	    @Override
	    public void deleteCmsFloorCouponBatch(List<String> list) throws BusinessException {
	        LogUtil.info(MODULE, "调用批量删除楼层促销开始，入参：{list="+list.toArray()+"}");
	        /* 1.验证前店入参 */
	        if(CollectionUtils.isEmpty(list)){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="list";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        
	        /* 2.逻辑批量删除楼层促销  */
	        try{
	            sv.deleteCmsFloorCouponBatch(list);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "批量删除楼层促销失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500104);
	        }
	    }

	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#changeStatusCmsFloorCoupon(java.lang.Long, java.lang.String) 
	     */
	    @Override
	    public void changeStatusCmsFloorCoupon(String id,String status) throws BusinessException {
	        LogUtil.info(MODULE, "调用批量删除楼层促销开始，入参：{id="+id+",status="+status+"}");
	        /* 1.验证前店入参 */
	        if(StringUtil.isBlank(id)){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="id";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        if(StringUtils.isBlank(status)){
	            LogUtil.error(MODULE, "入参status为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="status";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        /* 2.更新楼层促销状态  */
	        try{
	            sv.changeStatusCmsFloorCoupon(id, status);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "更新楼层促销状态失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500106);
	        }
	    }

	    /** 
	     * TODO 简单描述该方法的实现功能（可选）. 
	     * @see com.zengshi.ecp.cms.dubbo.interfaces.ICmsFloorCouponRSV#changeStatusCmsFloorCouponBatch(java.util.List, java.lang.String) 
	     */
	    @Override
	    public void changeStatusCmsFloorCouponBatch(List<String> list,String status) throws BusinessException {
	        LogUtil.info(MODULE, "调用批量删除楼层促销开始，入参：{list="+list.toArray()+",status="+status+"}");
	        /* 1.验证前店入参 */
	        if(CollectionUtils.isEmpty(list)){
	            LogUtil.error(MODULE, "入参ID为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="list";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        if(StringUtils.isBlank(status)){
	            LogUtil.error(MODULE, "入参status为空！");
	            String[] keyInfos = new String[1];
	            keyInfos[0]="status";
	            throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
	        }
	        
	        /* 2.更新楼层促销状态  */
	        try{
	            sv.changeStatusCmsFloorCouponBatch(list,status);
	        } catch(Exception err){
	            LogUtil.error(MODULE, "批量更新楼层促销状态失败！",err);
	            throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500107);
	        }
	    }

        @Override
        public void deleteCmsFloorCoupon(CmsFloorCouponReqDTO dto) throws BusinessException {
            /* 1.验证前店入参 */
            if(StringUtil.isEmpty(dto)){
                LogUtil.error(MODULE, "入参条件为空！");
                String[] keyInfos = new String[1];
                keyInfos[0]="id";
                throw new BusinessException(CmsConstants.MsgCode.CMS_COMMON_500102,keyInfos); 
            }
            LogUtil.info(MODULE, "调用根据查询条件删除楼层促销开始，入参：{dto="+dto.toString()+"}");
            /* 2.逻辑删除楼层促销  */
            try{
                sv.deleteCmsFloorCoupon(dto);
            } catch(Exception err){
                LogUtil.error(MODULE, "根据查询条件删除楼层促销失败！",err);
                throw new BusinessException(CmsConstants.MsgCode.CMS_FLOORCOUPON_500101);
            }
        }



}
