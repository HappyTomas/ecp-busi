package com.zengshi.ecp.prom.dubbo.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.prom.dubbo.dto.PromChkDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkRespPageDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromChkResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromGroupChkDTO;
import com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupChkSV;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.prom.service.util.PromSiteUtil;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-12 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromGroupChkRSVImpl implements IPromGroupChkRSV {

    private static final String MODULE = PromGroupChkRSVImpl.class.getName();

    @Resource
    private IPromGroupChkSV promGroupChkSV;

    @Resource
    private IPromGroupSV promGroupSV;
    
    @Resource
    private IPromInfoSV promInfoSV;
    /**
     * 保存促销审核
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroupChk(PromChkDTO promChkDTO) throws BusinessException {

        if (promChkDTO == null) {
            // 传入参数不能为空
            throw new BusinessException("prom.400076");
        }
        if(promChkDTO.getCreateStaff()==null){
            promChkDTO.setCreateStaff(promChkDTO.getStaff().getId());
        }
        if(promChkDTO.getCreateTime()==null){
            promChkDTO.setCreateTime(DateUtil.getSysDate());
        }
        promGroupChkSV.savePromGroupChk(promChkDTO);
    }
 
    /**
     * TODO获得促销审核列表
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV#queryPromGroupChkByPromId(com.zengshi.ecp.prom.dubbo.dto.PromChkDTO)
     * @param promChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromChkResponseDTO> queryPromGroupChkByPromId(PromChkDTO promChkDTO) throws BusinessException {

        if (promChkDTO == null) {
            // 传入参数不能为空
            throw new BusinessException("prom.400086");
        }
        if (promChkDTO.getPromId() == null) {
            // 传入参数不能为空
            throw new BusinessException("prom.400076");
        }
        return promGroupChkSV.queryPromGroupChkByPromId(promChkDTO.getPromId());
    }
    /**
     * TODO促销审核信息
     * @see com.zengshi.ecp.prom.dubbo.interfaces.IPromGroupChkRSV#queryPromGroupChkById(com.zengshi.ecp.prom.dubbo.dto.PromChkDTO)
     * @param promChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromChkResponseDTO queryPromGroupChkById(PromChkDTO promChkDTO) throws BusinessException {

        if (promChkDTO == null) {
            // 传入参数不能为空
            throw new BusinessException("prom.400086");
        }
        if (promChkDTO.getId() == null) {
            // 传入参数不能为空
            throw new BusinessException("prom.400076");
        }
        return promGroupChkSV.queryPromGroupChkById(promChkDTO.getId());
    }
    
    /**
     * 主题促销审核列表
     * @param queryPromGroupChkDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromChkRespPageDTO> listPromInfoByPromGroup(QueryPromGroupChkDTO queryPromGroupChkDTO)
            throws BusinessException{
        
        //如果主题促销状态为空 或者全部时 该条件不参与查找。如果非空时，需要扫描出t_prom_group列表 id
        PromGroupDTO promGroupDTO=new PromGroupDTO();
        promGroupDTO.setStatus(queryPromGroupChkDTO.getStatusGroup());
       /* promGroupDTO.setPageNo(queryPromGroupChkDTO.getPageNo());
        promGroupDTO.setPageSize(queryPromGroupChkDTO.getPageSize());*/
        promGroupDTO.setPageNo(1);
        promGroupDTO.setPageSize(Integer.MAX_VALUE);
        promGroupDTO.setSiteId(queryPromGroupChkDTO.getSiteId());
        promGroupDTO.setPromTheme(queryPromGroupChkDTO.getPromTheme());
        
        //存在 groupid 很多数据的情况  导致数据超出bug 待解决  可使用索引表
        PageResponseDTO<PromGroupResponseDTO> listGroupIdPage=promGroupSV.queryPromGroupForPage(promGroupDTO,null);
        
        if(!CheckPageNull.checkPageNull(listGroupIdPage)){
            PageResponseDTO<PromChkRespPageDTO> rePage=new PageResponseDTO<PromChkRespPageDTO>();
            rePage.setCount(0);
            rePage.setPageCount(0);
            rePage.setPageNo(queryPromGroupChkDTO.getPageNo());
            rePage.setPageSize(queryPromGroupChkDTO.getPageSize());
            return rePage;
        }
        //组织主题促销ID列表 参数
        List<Long> groupIdList=new ArrayList<Long>();
        Set<Long> groupIds=new HashSet<Long>();
        
        if(listGroupIdPage!=null && !CollectionUtils.isEmpty(listGroupIdPage.getResult())){
            
            for(PromGroupResponseDTO promGroupResponseDTO:listGroupIdPage.getResult()){
                groupIds.add(promGroupResponseDTO.getId());
            }
            //set转为list 过滤重复值
            groupIdList.addAll(groupIds);
            
        }else{
            //为空 null
            groupIdList=null;
        }
        //促销查询条件
        PromInfoDTO promInfoDTO=new PromInfoDTO();
        
        if(queryPromGroupChkDTO!=null){
            promInfoDTO.setShopId(queryPromGroupChkDTO.getShopId());
            promInfoDTO.setGroupId(queryPromGroupChkDTO.getGroupId());
            promInfoDTO.setStatus(queryPromGroupChkDTO.getChkStatus());
            promInfoDTO.setSiteId(queryPromGroupChkDTO.getSiteId());
        }
        promInfoDTO.setPageSize(queryPromGroupChkDTO.getPageSize());
        promInfoDTO.setPageNo(queryPromGroupChkDTO.getPageNo());
        //促销基本信息列表
        PageResponseDTO<PromInfoResponseDTO> page=promInfoSV.queryPromInfoForPage(promInfoDTO, groupIdList);

        //定义返回page
        PageResponseDTO<PromChkRespPageDTO> rePage=new PageResponseDTO<PromChkRespPageDTO>();
        rePage.setCount(page.getCount());
        rePage.setPageCount((int) page.getPageCount());
        rePage.setPageNo(page.getPageNo());
        rePage.setPageSize(page.getPageSize());
        
        //定义result list
        List<PromChkRespPageDTO> returnResult=new ArrayList<PromChkRespPageDTO>();
        
        PromGroupResponseDTO promGroupResponseDTO=new PromGroupResponseDTO();
        
        if(CheckPageNull.checkPageNull(page)){
            for(PromInfoResponseDTO promInfoResponseDTO:page.getResult()){
                PromChkRespPageDTO promChkRespPageDTO=new PromChkRespPageDTO();
                //查阅促销组信息
                promGroupResponseDTO=promGroupSV.queryPromGroupById(promInfoResponseDTO.getGroupId());
                //赋值属性
                //BeanUtils.copyProperties(promGroupResponseDTO, promChkResponseDTO);
                BeanUtils.copyProperties(promInfoResponseDTO, promChkRespPageDTO);
                //主题促销信息
                promChkRespPageDTO.setGroupName(promGroupResponseDTO.getPromTheme());
                promChkRespPageDTO.setGroupContent(promGroupResponseDTO.getPromContent());
                promChkRespPageDTO.setShowStartTimeG(promGroupResponseDTO.getShowStartTime());
                promChkRespPageDTO.setShowEndTimeG(promGroupResponseDTO.getShowEndTime());
                promChkRespPageDTO.setStatusGroup(promGroupResponseDTO.getStatus());
                promChkRespPageDTO.setStatusGroupName(promGroupResponseDTO.getStatusName());
                promChkRespPageDTO.setSiteId(promGroupResponseDTO.getSiteId());
                if(StringUtil.isEmpty(promChkRespPageDTO.getSiteName())){
                    //siteName 根据siteId 获得展示
                   // promChkRespPageDTO.setSiteName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.CMS_SITE, promChkRespPageDTO.getSiteId().toString()));
                    promChkRespPageDTO.setSiteName(PromSiteUtil.getSiteInfo(promChkRespPageDTO.getSiteId()).getSiteName());  
                }
                
                
                //审核信息
                List<PromChkResponseDTO> chkList= promGroupChkSV.queryPromGroupChkByPromId(promInfoResponseDTO.getId());
                if(!CollectionUtils.isEmpty(chkList)){
                    promChkRespPageDTO.setChkDesc(chkList.get(0).getChkDesc());
                    promChkRespPageDTO.setStatusChk(chkList.get(0).getStatus());
                    promChkRespPageDTO.setStatusChkName(BaseParamUtil.fetchParamValue(PromConstants.PromKey.PROM_CHK_STATUS, promChkRespPageDTO.getStatusChk()));
                }
                returnResult.add(promChkRespPageDTO);
            }
        }
        
        rePage.setResult(returnResult);
        
        return rePage;
    }
}
