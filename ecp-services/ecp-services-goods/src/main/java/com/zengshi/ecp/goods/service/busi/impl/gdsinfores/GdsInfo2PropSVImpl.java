package com.zengshi.ecp.goods.service.busi.impl.gdsinfores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2PropMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dao.model.GdsGds2PropCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2PropSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPropSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: 商品属性关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午5:42:30 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfo2PropSVImpl extends AbstractSVImpl implements IGdsInfo2PropSV {

    @Resource
    private GdsGds2PropMapper gds2PropMapper;

    @Resource
    private IGdsPropSV gdsPropSV;

    /**
     * 
     * 根据商品Id，属性组Id获取对应的商品属性关系
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2PropRespDTO> queryGds2PropsByGdsId(Long gdsId) throws BusinessException {
        List<GdsGds2Prop> props=queryGds2PropsModelByGdsId(gdsId);
        return copyGdsProps2Resp(props);
    }

    /**
     * 根据商品Id查询多该商品的所有属性关系
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Prop> queryGds2PropsModelByGdsId(Long gdsId) throws BusinessException {
        GdsGds2PropReqDTO reqDTO = new GdsGds2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        return queryGdsInfo2Prop(reqDTO);
    }

    /**
     * 
     * 根据gdsId,propId查询出有效状态商品属性关联关系。
     * 
     * @author linwb3
     * @param gdsId
     * @param propId
     * @return
     * @since JDK 1.6
     */
    @Override
    public GdsGds2Prop queryGds2PropModel(Long gdsId, Long propId) throws BusinessException {
        GdsGds2PropReqDTO reqDTO = new GdsGds2PropReqDTO();
        reqDTO.setGdsId(gdsId);
        reqDTO.setPropId(propId);
        reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);
        List<GdsGds2Prop> gds2PropLst = queryGdsInfo2Prop(reqDTO);
        if (CollectionUtils.isNotEmpty(gds2PropLst)) {
            return gds2PropLst.get(0);
        }
        return null;
    }

    /**
     * 商品属性关系原子查询服务
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Prop> queryGdsInfo2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException {
        GdsGds2PropCriteria propCriteria = new GdsGds2PropCriteria();
        if (reqDTO != null) {
            GdsGds2PropCriteria.Criteria criteria = propCriteria.createCriteria();
            initCriteria(reqDTO, criteria);
        }
        return gds2PropMapper.selectByExample(propCriteria);
    }

   

    /**
     * 
     * updateGds2Prop:(更新商品属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO  更新对象
     * @param query   查询条件
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void updateGds2Prop(GdsGds2PropReqDTO reqDTO,GdsGds2PropReqDTO query) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(query);
        paramNullCheck(query.getGdsId());

        GdsGds2PropCriteria example = new GdsGds2PropCriteria();
        GdsGds2PropCriteria.Criteria criteria = example.createCriteria();
        
        initAutormCriteria(query, criteria);
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        Long staffId = reqDTO.getStaff().getId();
        
        //填充信息
        GdsGds2Prop gdsGds2Prop = new GdsGds2Prop();
        
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGds2Prop, null, false);
        
        
        gdsGds2Prop.setUpdateStaff(staffId);
        gdsGds2Prop.setUpdateTime(DateUtil.getSysDate());
        gds2PropMapper.updateByExampleSelective(gdsGds2Prop, example);
    }
    
    /**
     * 
     * updateGds2PropGdsStatus:(批量更新商品属性关系商品状态). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void updateGds2PropGdsStatus(GdsGds2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getGdsStatus());
        GdsGds2PropReqDTO query=new GdsGds2PropReqDTO();
        query.setGdsId(reqDTO.getGdsId());
        GdsGds2PropReqDTO info=new GdsGds2PropReqDTO();
        info.setGdsStatus(reqDTO.getGdsStatus());
        info.setStaff(reqDTO.getStaff());
        updateGds2Prop(info,query);
    }

    /**
     * 
     * saveGds2Prop:(保存商品，属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void saveGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getGdsId(), reqDTO.getPropId() }, new String[] { "reqDTO.gdsId", "reqDTO.propId" });
        Long staffId = reqDTO.getStaff().getId();

        GdsGds2Prop gdsGds2Prop = new GdsGds2Prop();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGds2Prop, null, false);
        
        gdsGds2Prop.setCreateStaff(staffId);
        gdsGds2Prop.setCreateTime(DateUtil.getSysDate());
        gdsGds2Prop.setUpdateStaff(staffId);
        gdsGds2Prop.setUpdateTime(DateUtil.getSysDate());
        gdsGds2Prop.setStatus(GdsConstants.Commons.STATUS_VALID);
        gds2PropMapper.insertSelective(gdsGds2Prop);
    }

    /**
     * 
     * delGds2Prop:(逻辑删除商品属性关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    @Override
    public void delGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
        updateGds2Prop(reqDTO,reqDTO);
    }

    /**
     * 
     * realDelGds2Prop:(<font color='red'>物理删除商品与属性关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void realDelGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        GdsGds2PropCriteria example = new GdsGds2PropCriteria();
        GdsGds2PropCriteria.Criteria criteria = example.createCriteria();
        initAutormCriteria(reqDTO, criteria);
        gds2PropMapper.deleteByExample(example);
    }
    
    /**
     *  
     * initCriteria:(初始化查询条件). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @param criteria 
     * @since JDK 1.6
     */
    private void initCriteria(GdsGds2PropReqDTO reqDTO, GdsGds2PropCriteria.Criteria criteria) {
        initAutormCriteria(reqDTO, criteria);
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            criteria.andStatusEqualTo(reqDTO.getStatus());
        }
    }

    /**
     * 
     * initAutormCriteria:(查询条件拼接). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @param criteria 
     * @since JDK 1.6
     */
    private void initAutormCriteria(GdsGds2PropReqDTO reqDTO, GdsGds2PropCriteria.Criteria criteria) {
        if (reqDTO.getGdsId() != null && reqDTO.getGdsId().longValue() != 0) {
            criteria.andGdsIdEqualTo(reqDTO.getGdsId());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropIds())) {
            criteria.andPropIdIn(reqDTO.getPropIds());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropInputTypes())) {
            criteria.andPropInputTypeIn(reqDTO.getPropInputTypes());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropTypes())) {
            criteria.andPropTypeIn(reqDTO.getPropTypes());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getPropValueTypes())) {
            criteria.andPropValueTypeIn(reqDTO.getPropValueTypes());
        }
        if (reqDTO.getPropId() != null) {
            criteria.andPropIdEqualTo(reqDTO.getPropId());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropName())) {
            criteria.andPropNameEqualTo(reqDTO.getPropName());
        }
        if (reqDTO.getPropValueId() != null) {
            criteria.andPropValueIdEqualTo(reqDTO.getPropValueId());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropValue())) {
            criteria.andPropValueEqualTo(reqDTO.getPropValue());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropType())) {
            criteria.andPropTypeEqualTo(reqDTO.getPropType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropValueType())) {
            criteria.andPropValueTypeEqualTo(reqDTO.getPropValueType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropInputType())) {
            criteria.andPropInputTypeEqualTo(reqDTO.getPropInputType());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropRtype())) {
            criteria.andPropRtypeEqualTo(reqDTO.getPropRtype());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfBasic())) {
            criteria.andIfBasicEqualTo(reqDTO.getIfBasic());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfCheck())) {
            criteria.andIfCheckEqualTo(reqDTO.getIfCheck());
        }
        if (StringUtil.isNotBlank(reqDTO.getIfMust())) {
            criteria.andIfHavetoEqualTo(reqDTO.getIfMust());
        }
        if (StringUtil.isNotBlank(reqDTO.getPropInputRule())) {
            criteria.andPropInputRuleEqualTo(reqDTO.getPropInputRule());
        }
        if (StringUtil.isNotBlank(reqDTO.getGdsStatus())) {
            criteria.andGdsStatusEqualTo(reqDTO.getGdsStatus());
        }
    }

    
    private List<GdsGds2PropRespDTO> copyGdsProps2Resp(List<GdsGds2Prop> props) {
        List<GdsGds2PropRespDTO> resps=new ArrayList<GdsGds2PropRespDTO>(); 
        for (GdsGds2Prop prop : props) {
            GdsGds2PropRespDTO resp = copyInfo2Resp(prop);
            resps.add(resp);
        }
        return resps;
    }

    private GdsGds2PropRespDTO copyInfo2Resp(GdsGds2Prop info) {
        GdsGds2PropRespDTO resp=new GdsGds2PropRespDTO();
        resp.setGdsId(info.getGdsId());
        resp.setPropId(info.getPropId());
        resp.setShopId(info.getShopId());
        resp.setPropName(info.getPropName());
        resp.setPropValue(info.getPropValue());
        resp.setPropValueId(info.getPropValueId());
        resp.setPropType(info.getPropType());
        resp.setPropValueType(info.getPropValueType());
        resp.setPropMediaUuid(info.getPropMediaUuid());
        resp.setPropRtype(info.getPropRtype());
        resp.setIfHaveto(info.getIfHaveto());
        resp.setIfBasic(info.getIfBasic());
        resp.setIfCheck(info.getIfCheck());
        resp.setPropInputType(info.getPropInputType());
        resp.setPropInputRule(info.getPropInputRule());
        resp.setGdsStatus(info.getGdsStatus());
        resp.setStatus(info.getStatus());
        resp.setCreateStaff(info.getCreateStaff());
        resp.setCreateTime(info.getCreateTime());
        resp.setUpdateStaff(info.getUpdateStaff());
        resp.setUpdateTime(info.getUpdateTime());
        return resp;
    }

}
