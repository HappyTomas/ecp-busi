/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsPlatRecomSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.common.impl 
 * Date:2015年8月20日上午11:18:23 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.common.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.common.GdsPlatRecomMapper;
import com.zengshi.ecp.goods.dao.model.GdsCategory;
import com.zengshi.ecp.goods.dao.model.GdsPlatRecom;
import com.zengshi.ecp.goods.dao.model.GdsPlatRecomCriteria;
import com.zengshi.ecp.goods.dao.model.GdsPlatRecomCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPlatRecomRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsCategorySV;
import com.zengshi.ecp.goods.service.common.interfaces.IGdsPlatRecomSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 平台推荐服务接口实现类。 <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月20日上午11:18:23 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsPlatRecomSVImpl extends AbstractSVImpl implements IGdsPlatRecomSV {

    @Resource(name = "seq_gds_platrecom")
    private PaasSequence seqGdsPlatRecom;

    private static final String DEFAULT_ORDER_BY = "SORT_NO,CREATE_TIME";

    @Resource
    private GdsPlatRecomMapper gdsPlatRecomMapper;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    private IGdsCategorySV gdsCategorySV;

    @Override
    public GdsPlatRecom saveGdsPlatRecom(GdsPlatRecom gdsPlatRecom) throws BusinessException {
        gdsPlatRecom.setId(seqGdsPlatRecom.nextValue());
        gdsPlatRecom.setCreateTime(now());
        if (StringUtil.isBlank(gdsPlatRecom.getStatus())) {
            gdsPlatRecom.setStatus(GdsConstants.Commons.STATUS_VALID);
        }
        gdsPlatRecomMapper.insert(gdsPlatRecom);
        return gdsPlatRecom;
    }

    @Override
    public boolean queryExist(String catgCode, Long gdsId, Long skuId, String... status)
            throws BusinessException {
        StringBuffer errorMsg = new StringBuffer();
        if (!StringUtil.isNotBlank(catgCode)) {
            errorMsg.append("catgCode,");
        }
        if (null == gdsId) {
            errorMsg.append("gdsId,");
        }
        if (null == skuId) {
            errorMsg.append("skuId,");
        }
        if (errorMsg.length() > 0) {
            errorMsg.deleteCharAt(errorMsg.length() - 1);
            throw new BusinessException(GdsErrorConstants.Commons.ERROR_GOODS_200100,
                    new String[] { errorMsg.toString() });
        }
        GdsPlatRecomCriteria criteria = new GdsPlatRecomCriteria();
        Criteria c = criteria.createCriteria();
        c.andCatgCodeEqualTo(catgCode);
        c.andGdsIdEqualTo(gdsId);
        c.andSkuIdEqualTo(skuId);
        initStatusCriteria(c, status);
        return gdsPlatRecomMapper.countByExample(criteria) > 0;
    }

    @Override
    public int deleteGdsPlatRecom(Long id, Long updateStaff) throws BusinessException {
        return executeBatchDeleteGdsPlatRecom(Arrays.asList(new Long[] { id }), updateStaff);
    }

    public void deletGdsPlatRecomByGdsId(GdsInfoReqDTO gdsInfoReqDTO) throws BusinessException {

        GdsPlatRecomCriteria criteria = new GdsPlatRecomCriteria();
        Criteria c = criteria.createCriteria();
        c.andGdsIdEqualTo(gdsInfoReqDTO.getId());
        c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        GdsPlatRecom record = new GdsPlatRecom();
        record.setStatus(GdsConstants.Commons.STATUS_INVALID);       
        gdsPlatRecomMapper.updateByExampleSelective(record, criteria);
    }

    @Override
    public int executeBatchDeleteGdsPlatRecom(List<Long> ids, Long updateStaff)
            throws BusinessException {
        if (ids != null && !ids.isEmpty()) {
            GdsPlatRecom record = new GdsPlatRecom();
            record.setUpdateStaff(updateStaff);
            record.setUpdateTime(now());
            record.setStatus(GdsConstants.Commons.STATUS_INVALID);
            GdsPlatRecomCriteria criteria = new GdsPlatRecomCriteria();
            Criteria c = criteria.createCriteria();
            if (ids.size() == 1) {
                c.andIdEqualTo(ids.get(0));
            } else {
                c.andIdIn(ids);
            }
            return gdsPlatRecomMapper.updateByExampleSelective(record, criteria);
        }
        return 0;
    }

    @Override
    public PageResponseDTO<GdsPlatRecomRespDTO> queryGdsPlatRecomRespDTOPaging(
            GdsPlatRecomReqDTO dto) throws BusinessException {
        GdsPlatRecomCriteria criteria = new GdsPlatRecomCriteria();
        if(dto!=null){
            criteria.setLimitClauseStart(dto.getStartRowIndex());
            criteria.setLimitClauseCount(dto.getPageSize());
        }
        criteria.setOrderByClause(DEFAULT_ORDER_BY);
        Criteria c = criteria.createCriteria();
        if (dto != null) {
            initCriteria(c, dto);
        }
        return super.queryByPagination(dto, criteria, false,
                new GdsPlatRecomRespDTOPaginationCallback());
    }

    @Override
    public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(Long id) throws BusinessException {

        GdsPlatRecomRespDTO gdsPlatRecomRespDTO = new GdsPlatRecomRespDTO();
        GdsPlatRecom gdsPlatRecom = gdsPlatRecomMapper.selectByPrimaryKey(id);
        ObjectCopyUtil.copyObjValue(gdsPlatRecom, gdsPlatRecomRespDTO, null, false);
        setExtractInfo(gdsPlatRecom, gdsPlatRecomRespDTO);
        return gdsPlatRecomRespDTO;
    }

    @Override
    public GdsPlatRecomRespDTO queryGdsPlatRecomByPK(Long id, boolean isValid)
            throws BusinessException {
        GdsPlatRecomRespDTO gdsPlatRecomRespDTO = new GdsPlatRecomRespDTO();
        GdsPlatRecom gdsPlatRecom = gdsPlatRecomMapper.selectByPrimaryKey(id);
        ObjectCopyUtil.copyObjValue(gdsPlatRecom, gdsPlatRecomRespDTO, null, false);
        setExtractInfo(gdsPlatRecom, gdsPlatRecomRespDTO);

        if (gdsPlatRecomRespDTO != null) {
            if (isValid
                    && !GdsConstants.Commons.STATUS_VALID.equals(gdsPlatRecomRespDTO.getStatus())) {
                gdsPlatRecomRespDTO = null;
            }
        }
        return gdsPlatRecomRespDTO;
    }

    /**
     * 
     * 标签分页查询回调实现类.<br>
     * Project Name:ecp-services-goods <br>
     * Description: <br>
     * Date:2015年8月20日上午9:22:15 <br>
     * Copyright (c) 2015 ZengShi All Rights Reserved <br>
     * 
     * @author liyong7
     * @version GdsLabelSVImpl
     * @since JDK 1.6
     */
    protected class GdsPlatRecomRespDTOPaginationCallback extends
            PaginationCallback<GdsPlatRecom, GdsPlatRecomRespDTO> {

        @Override
        public List<GdsPlatRecom> queryDB(BaseCriteria criteria) {
            return gdsPlatRecomMapper.selectByExample((GdsPlatRecomCriteria) criteria);
        }

        @Override
        public long queryTotal(BaseCriteria criteria) {
            return gdsPlatRecomMapper.countByExample((GdsPlatRecomCriteria) criteria);
        }

        @Override
        public GdsPlatRecomRespDTO warpReturnObject(GdsPlatRecom t) {
            GdsPlatRecomRespDTO dto = new GdsPlatRecomRespDTO();
            // TODO 需要封装单品名称进返回对象。
            // 查询列表需要获取单品名称，此处需要根据单品ID(skuId)获取单品信息或者单品名称封装入返回对象。
            // 此处可以结合缓存进行查询，若未使用缓存，则可以通过单品的SV查询获取单品实体。
            ObjectCopyUtil.copyObjValue(t, dto, null, false);
            setExtractInfo(t, dto);
            return dto;
        }

    }

    private void setExtractInfo(GdsPlatRecom t, GdsPlatRecomRespDTO dto) {
        GdsCategory gdsCategory = gdsCategorySV.queryGdsCategoryById(t.getCatgCode());
        if (gdsCategory != null) {
            dto.setCatgName(gdsCategory.getCatgName());
        }
    }

    /*
     * 根据请求DTO设置查询条件. <br/>
     * 
     * @author liyong7
     * 
     * @param criteria
     * 
     * @param dto
     * 
     * @since JDK 1.6
     */
    private Criteria initCriteria(Criteria c, GdsPlatRecomReqDTO dto) {
        if (StringUtil.isNotBlank(dto.getCatgCode())) {
            c.andCatgCodeEqualTo(dto.getCatgCode());
        }
        if (StringUtil.isNotBlank(dto.getGdsName())) {
            c.andGdsNameLike("%" + dto.getGdsName() + "%");
        }
        if (StringUtil.isNotBlank(dto.getStatus())) {
            c.andStatusEqualTo(dto.getStatus());
        } else {
            c.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        }
        if (StringUtil.isNotBlank(dto.getIfDefault())) {
            c.andIfDefaultEqualTo(dto.getIfDefault());
        }
        return c;
    }

    @Override
    public Integer editGdsPlatRecom(GdsPlatRecom gdsPlatRecom) throws BusinessException {
        return gdsPlatRecomMapper.updateByPrimaryKeySelective(gdsPlatRecom);
    }

}
