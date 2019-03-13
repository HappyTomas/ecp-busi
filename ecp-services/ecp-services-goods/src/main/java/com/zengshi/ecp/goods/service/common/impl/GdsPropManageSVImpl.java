package com.zengshi.ecp.goods.service.common.impl;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.*;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCatg2PropSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropManageSV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsType2PropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HDF on 2016/8/10.
 */
public class GdsPropManageSVImpl implements IGdsPropManageSV {

    @Resource(name="gdsCatg2PropSV")
    private IGdsCatg2PropSV gdsCatg2PropSV;

    @Resource(name="gdsType2PropSV")
    private IGdsType2PropSV gdsType2PropSV;

    private List<GdsPropRespDTO> propMerge(List<GdsPropRespDTO> oldProp,List<GdsPropRespDTO> newProp){

        //属性合并：覆盖优先级从低到高为 类型-->分类-->子分类
        List<GdsPropRespDTO> list;
        if(CollectionUtils.isNotEmpty(oldProp)){
            list=oldProp;
        }else{
            list=new ArrayList<GdsPropRespDTO>();
        }
        for(GdsPropRespDTO o:newProp){

            boolean contains=false;
            for(GdsPropRespDTO i:oldProp){
                if(o.getId().longValue()==i.getId().longValue()){
                    contains=true;
                    break;
                }
            }

            if(!contains){
                list.add(o);
            }

        }

        return list;
    }

    private List<GdsPropRespDTO> propMerge(List<GdsPropRespDTO> oldProp,List<GdsPropRespDTO> addtionalCheckedOldProp,List<GdsPropRespDTO> newProp){

        //属性合并：覆盖优先级从低到高为 类型-->分类-->子分类
        List<GdsPropRespDTO> list;
        if(CollectionUtils.isNotEmpty(oldProp)){
            list=oldProp;
        }else{
            list=new ArrayList<GdsPropRespDTO>();
        }
        for(GdsPropRespDTO o:newProp){

            boolean contains=false;
            for(GdsPropRespDTO i:oldProp){
                if(o.getId().longValue()==i.getId().longValue()){
                    contains=true;
                    break;
                }
            }

            if(!contains){

                for(GdsPropRespDTO i:addtionalCheckedOldProp){
                    if(o.getId().longValue()==i.getId().longValue()){
                        contains=true;
                        break;
                    }
                }

                if(!contains){
                    list.add(o);
                }
            }

        }

        return list;
    }

    @Override
    public GdsPropManageRespDTO queryAllMergedProps(GdsCatg2PropReqDTO reqDTO, GdsType2PropReqDTO reqDTO2) throws BusinessException {

        if(null != reqDTO){
            if(null == reqDTO.getStatus()){
                reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
            }
        }

        if(null != reqDTO2){
            if(null == reqDTO2.getStatus()){
                reqDTO2.setStatus(GdsConstants.Commons.STATUS_VALID);
            } 
        }
        

        GdsCatg2PropRelationRespDTO rspDto = null;
        
        if(null != reqDTO){
            rspDto = gdsCatg2PropSV.queryCategoryProps(reqDTO); 
        }
        
        
        GdsType2PropRelationRespDTO gdsType2PropRelationRespDTO=null;
        if(null != reqDTO2){
            gdsType2PropRelationRespDTO=gdsType2PropSV.queryTypeProps(reqDTO2);
        }

        GdsPropManageRespDTO result=new GdsPropManageRespDTO();

        //属性合并
        if(rspDto==null&&gdsType2PropRelationRespDTO==null){
            List<GdsPropRespDTO> list=new ArrayList<GdsPropRespDTO>();
            result.setBasics(list);
            result.setParams(list);
            result.setOthers(list);
            result.setSpecs(list);
            result.setFileParam(list);
            result.setEditoParam(list);
        }else{

            //基础属性和参数属性合并需要特殊处理start

            List<GdsPropRespDTO> list=propMerge(rspDto.getBasics(),rspDto.getParams(), gdsType2PropRelationRespDTO.getBasics());
            result.setBasics(list);

            list=propMerge(rspDto.getParams(),rspDto.getBasics(), gdsType2PropRelationRespDTO.getParams());
            result.setParams(list);

            //基础属性和参数属性合并需要特殊处理end

            list=propMerge(rspDto.getOthers(), gdsType2PropRelationRespDTO.getOthers());
            result.setOthers(list);

            list=propMerge(rspDto.getSpecs(), gdsType2PropRelationRespDTO.getSpecs());
            result.setSpecs(list);

            list=propMerge(rspDto.getFileParam(), gdsType2PropRelationRespDTO.getFileParam());
            result.setFileParam(list);

            list=propMerge(rspDto.getEditoParam(), gdsType2PropRelationRespDTO.getEditoParam());
            result.setEditoParam(list);

        }

        return result;

    }
}
