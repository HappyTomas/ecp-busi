package com.zengshi.ecp.goods.service.busi.impl.gdsinfores;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsGds2CatgMapper;
import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dao.model.GdsGds2CatgCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores.IGdsInfo2CatgSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Title: 商品分类关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午5:42:19 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public class GdsInfo2CatgSVImpl extends AbstractSVImpl implements IGdsInfo2CatgSV {
    @Resource
    private GdsGds2CatgMapper gds2CatgMapper;

    /**
     * 查询商品归属的所有分类
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Catg> queryGds2CatgsModelByGdsId(Long gdsId) throws BusinessException {
        GdsGds2CatgReqDTO reqDto = new GdsGds2CatgReqDTO();
        reqDto.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDto.setGdsId(gdsId);
        return queryGdsInfo2Catg(reqDto);
    }

    /**
     * 查询商品归属的所有分类（RESP）
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2CatgRespDTO> queryGds2CatgsByGdsId(Long gdsId) throws BusinessException {
        List<GdsGds2Catg> catgs = queryGds2CatgsModelByGdsId(gdsId);
        if (CollectionUtils.isEmpty(catgs)) {
            return null;
        }
        List<GdsGds2CatgRespDTO> resps = new ArrayList<GdsGds2CatgRespDTO>();
        for (GdsGds2Catg catg : catgs) {
            GdsGds2CatgRespDTO gds2CatgRespDTO = copyInfo2Resp(catg);
            resps.add(gds2CatgRespDTO);
        }
        return resps;
    }

    /**
     * 查询商品的主分类(平台分类)
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    @Override
    public GdsGds2CatgRespDTO queryGdsMainCatgByGdsId(Long gdsId) throws BusinessException {
        GdsGds2CatgReqDTO reqDto = new GdsGds2CatgReqDTO();
        reqDto.setStatus(GdsConstants.Commons.STATUS_VALID);
        reqDto.setGds2catgType(GdsConstants.GdsInfo.GDS_2_CATG_RTYPE_MAIN);
        reqDto.setCatgType(GdsConstants.GdsCategory.CATG_TYPE_1);
        reqDto.setGdsId(gdsId);
        List<GdsGds2Catg> catgs = queryGdsInfo2Catg(reqDto);

        if (CollectionUtils.isNotEmpty(catgs)) {
            GdsGds2Catg catg = catgs.get(0);
            GdsGds2CatgRespDTO gds2CatgRespDTO = copyInfo2Resp(catg);
            return gds2CatgRespDTO;
        }
        return null;
    }

    /**
     * 查询商品分类关系原子服务
     * 
     * @author linwb3
     * @param reqDto
     * @return
     * @since JDK 1.6
     */
    @Override
    public List<GdsGds2Catg> queryGdsInfo2Catg(GdsGds2CatgReqDTO reqDto) throws BusinessException {
        GdsGds2CatgCriteria catgCriteria = new GdsGds2CatgCriteria();
        if (reqDto != null) {
            GdsGds2CatgCriteria.Criteria criteria = catgCriteria.createCriteria();
            initAutormCriteria(reqDto, criteria);
        }
        return gds2CatgMapper.selectByExample(catgCriteria);
    }

    /**
     * 
     * updateGds2Catg:(根据商品，分类编码更新对应的商品分类信息). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void updateGds2Catg(GdsGds2CatgReqDTO reqDTO, GdsGds2CatgReqDTO query) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(query);
        paramNullCheck(query.getGdsId());

        Long staffId = reqDTO.getStaff().getId();

        GdsGds2CatgCriteria example = new GdsGds2CatgCriteria();
        GdsGds2CatgCriteria.Criteria criteria = example.createCriteria();
        initAutormCriteria(query, criteria);
        //criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        GdsGds2Catg gds2Catg = new GdsGds2Catg();
        ObjectCopyUtil.copyObjValue(reqDTO, gds2Catg, null, false);
        gds2Catg.setUpdateStaff(staffId);
        gds2Catg.setUpdateTime(DateUtil.getSysDate());
        gds2CatgMapper.updateByExampleSelective(gds2Catg, example);
    }

    @Override
    public void updateGds2CatgGdsStatus(GdsGds2CatgReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        paramNullCheck(reqDTO.getGdsStatus());

        GdsGds2CatgReqDTO info = new GdsGds2CatgReqDTO();
        info.setGdsStatus(reqDTO.getGdsStatus());
        info.setStaff(reqDTO.getStaff());

        GdsGds2CatgReqDTO query = new GdsGds2CatgReqDTO();
        query.setGdsId(reqDTO.getGdsId());
        updateGds2Catg(info, query);
    }

    /**
     * 
     * saveGds2Catg:(保存商品，分类关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void saveGds2Catg(GdsGds2CatgReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramCheck(new Object[] { reqDTO.getGdsId(), reqDTO.getCatgCode() }, new String[] { "reqDTO.gdsId", "reqDTO.catgCode" });

        Long staffId = reqDTO.getStaff().getId();

        GdsGds2Catg gds2Catg = new GdsGds2Catg();
        ObjectCopyUtil.copyObjValue(reqDTO, gds2Catg, null, false);
        gds2Catg.setStatus(GdsConstants.Commons.STATUS_VALID);
        gds2Catg.setCreateStaff(staffId);
        gds2Catg.setCreateTime(DateUtil.getSysDate());
        gds2Catg.setUpdateStaff(staffId);
        gds2Catg.setUpdateTime(DateUtil.getSysDate());
        gds2CatgMapper.insertSelective(gds2Catg);
    }

    /**
     * 
     * delGds2Catg:(删除商品分类关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void delGds2Catg(GdsGds2CatgReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        reqDTO.setStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsGds2CatgReqDTO query = new GdsGds2CatgReqDTO();
        query.setStatus(GdsConstants.Commons.STATUS_VALID);
        query.setGdsId(reqDTO.getGdsId());
        updateGds2Catg(reqDTO, query);
    }



    /**
     * 
     * realDelGds2Catg:(<font color='red'>物理删除商品与分类关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    @Override
    public void realDelGds2Catg(GdsGds2CatgReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO);
        paramNullCheck(reqDTO.getGdsId());
        GdsGds2CatgCriteria example = new GdsGds2CatgCriteria();
        GdsGds2CatgCriteria.Criteria criteria = example.createCriteria();
        initAutormCriteria(reqDTO, criteria);
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            criteria.andStatusEqualTo(reqDTO.getStatus());
        }
        gds2CatgMapper.deleteByExample(example);
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
    private void initAutormCriteria(GdsGds2CatgReqDTO reqDTO, GdsGds2CatgCriteria.Criteria criteria) {
        if (reqDTO.getGdsId() != null && reqDTO.getGdsId().longValue() != 0) {
            criteria.andGdsIdEqualTo(reqDTO.getGdsId());
        }
        if (StringUtil.isNotBlank(reqDTO.getCatgCode())) {
            criteria.andCatgCodeEqualTo(reqDTO.getCatgCode());
        }
        if (CollectionUtils.isNotEmpty(reqDTO.getCatgs())) {
            criteria.andCatgCodeIn(reqDTO.getCatgs());
        }
        if (StringUtil.isNotBlank(reqDTO.getGds2catgType())) {
            criteria.andGds2catgTypeEqualTo(reqDTO.getGds2catgType());
        }
        if (StringUtil.isNotBlank(reqDTO.getCatgType())) {
            criteria.andCatgTypeEqualTo(reqDTO.getCatgType());
        }
        if (reqDTO.getShopId() != null) {
            criteria.andShopIdEqualTo(reqDTO.getShopId());
        }
        if(StringUtil.isNotBlank(reqDTO.getGdsName())){
            criteria.andGdsNameLike("%"+reqDTO.getGdsName()+"%");
        }
        if(StringUtil.isNotBlank(reqDTO.getCatgPath())){
            criteria.andGdsNameLike("%"+reqDTO.getCatgPath()+"%");
        }
        if (StringUtil.isNotBlank(reqDTO.getStatus())) {
            criteria.andStatusEqualTo(reqDTO.getStatus());
        }
        if(null != reqDTO.getCatlogId()){
            criteria.andCatlogIdEqualTo(reqDTO.getCatlogId());
        }
    }

    private GdsGds2CatgRespDTO copyInfo2Resp(GdsGds2Catg info) {
        GdsGds2CatgRespDTO resp = new GdsGds2CatgRespDTO();
        resp.setGdsId(info.getGdsId());
        resp.setGdsName(info.getGdsName());
        resp.setCatgCode(info.getCatgCode());
        resp.setCatgType(info.getCatgType());
        resp.setGds2catgType(info.getGds2catgType());
        resp.setCatgPath(info.getCatgPath());
        resp.setShopId(info.getShopId());
        resp.setStatus(info.getStatus());
        resp.setGdsStatus(info.getGdsStatus());
        resp.setIsbn(info.getIsbn());
        resp.setCatlogId(info.getCatlogId());
        resp.setCreateStaff(info.getCreateStaff());
        resp.setCreateTime(info.getCreateTime());
        resp.setUpdateStaff(info.getUpdateStaff());
        resp.setUpdateTime(info.getUpdateTime());
        return resp;
    }
}
