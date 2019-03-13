package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsBrowseHisMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.GdsBrowseHisExtraMapper;
import com.zengshi.ecp.goods.dao.model.GdsBrowseHis;
import com.zengshi.ecp.goods.dao.model.GdsBrowseHisCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsOption.SkuQueryOption;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsBrowseHisRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsBrowseHisSV;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustDiscSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ImageUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.distribute.DistributeRuleAssist;
import com.db.sequence.Sequence;

public class GdsBrowseHisSVImpl extends AbstractSVImpl implements IGdsBrowseHisSV {
    @Resource(name = "seq_gds_browse_his")
    private Sequence seqGdsBrowseHis;

    @Resource
    private GdsBrowseHisMapper browseHisMapper;
    
    @Resource
    private GdsBrowseHisExtraMapper browseHisExtraMapper;

    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;

    @Resource
    private IGdsCatgCustDiscSV catgCustDiscSV;

    @Override
    public void addGdsBrowseHis(GdsBrowseHisReqDTO browseHisReqDTO) throws Exception {
        LogUtil.info(GdsBrowseHisSVImpl.class.getName(), "=============" + browseHisReqDTO.toString());
        GdsBrowseHis browseHis = new GdsBrowseHis();
        ObjectCopyUtil.copyObjValue(browseHisReqDTO, browseHis, null, false);
        browseHis.setStaffId(browseHisReqDTO.getStaffId());
        browseHis.setStatus(GdsConstants.Commons.STATUS_VALID);
        browseHis.setBrowseTime(new Timestamp(System.currentTimeMillis()));
        browseHis.setCreateStaff(browseHisReqDTO.getStaffId());
        browseHis.setUpdateStaff(browseHisReqDTO.getStaffId());
        browseHis.setCreateTime(new Timestamp(System.currentTimeMillis()));
        browseHis.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        browseHis.setId(seqGdsBrowseHis.nextValue());
        browseHisMapper.insertSelective(browseHis);

    }

    @Override
    public void deleteGdsBrowseHis(GdsBrowseHisReqDTO browseHisReqDTO) throws Exception {
        GdsBrowseHisCriteria browseHisCriteria = new GdsBrowseHisCriteria();
        GdsBrowseHisCriteria.Criteria criteria = browseHisCriteria.createCriteria();
        criteria.andIdEqualTo(browseHisReqDTO.getId());
        GdsBrowseHis browseHis = new GdsBrowseHis();
        browseHis.setStatus(GdsConstants.Commons.STATUS_INVALID);
        int key = browseHisMapper.updateByExampleSelective(browseHis, browseHisCriteria);
        if (key == 0) {

            throw new BusinessException(GdsErrorConstants.GdsBrowseHis.ERROR_GOODS_BROWSE_HIS_240401);
        }
    }

    @Override
    public void deleteGdsBrowseHisClear(GdsBrowseHisReqDTO browseHisReqDTO) throws Exception {
        GdsBrowseHisCriteria browseHisCriteria = new GdsBrowseHisCriteria();
        GdsBrowseHisCriteria.Criteria criteria = browseHisCriteria.createCriteria();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.MONTH, -1);
        Date date = calendar.getTime();
        criteria.andCreateTimeLessThan(new Timestamp(date.getTime()));
        DistributeRuleAssist.setTableIndex(browseHisReqDTO.getTableIndex());
        browseHisMapper.deleteByExample(browseHisCriteria);
    }

    @Override
    public PageResponseDTO<GdsBrowseHisRespDTO> queryGdsBrowseHisByPage(final GdsBrowseHisReqDTO browseHisReqDTO) throws Exception {

        GdsBrowseHisCriteria browseHisCriteria = new GdsBrowseHisCriteria();
        GdsBrowseHisCriteria.Criteria criteria = browseHisCriteria.createCriteria();
        criteria.andStaffIdEqualTo(browseHisReqDTO.getStaffId());
        criteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        // 过滤店铺
        if(browseHisReqDTO.getShopId() != null){
        	criteria.andShopIdEqualTo(browseHisReqDTO.getShopId());
        }
        // 过滤商品
        if (StringUtil.isNotEmpty(browseHisReqDTO.getGdsId())) {
            criteria.andGdsIdNotEqualTo(browseHisReqDTO.getGdsId());
        }
        browseHisCriteria.setDistinct(true);
        browseHisCriteria.setOrderByClause("BROWSE_TIME DESC");
        browseHisCriteria.setLimitClauseCount(browseHisReqDTO.getPageSize());
        browseHisCriteria.setLimitClauseStart(browseHisReqDTO.getStartRowIndex());
        return super.queryByPagination(browseHisReqDTO, browseHisCriteria, true, new PaginationCallback<GdsBrowseHis, GdsBrowseHisRespDTO>() {
        	
            @Override
			public List<Comparator<GdsBrowseHis>> defineComparators() {
				// TODO Auto-generated method stub
            	List<Comparator<GdsBrowseHis>> cLst = new ArrayList<>();
            	Comparator<GdsBrowseHis> c = new Comparator<GdsBrowseHis>() {
					@Override
					public int compare(GdsBrowseHis o1, GdsBrowseHis o2) {
						if(o1.getBrowseTime().after(o2.getBrowseTime())){
							return -1;
						}else if(o2.getBrowseTime().after(o1.getBrowseTime())){
							return 1;
						}
						// TODO Auto-generated method stub
						return 0;
					}
				};
            	cLst.add(c);
            	return cLst;
			}

			@Override
            public List<GdsBrowseHis> queryDB(BaseCriteria criteria) {
            	List<GdsBrowseHis> list=browseHisExtraMapper.selectByExample((GdsBrowseHisCriteria) criteria);
        		return list;
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return browseHisExtraMapper.countByExample((GdsBrowseHisCriteria) criteria);
            }

            @Override
            public GdsBrowseHisRespDTO warpReturnObject(GdsBrowseHis t) {
                GdsBrowseHisRespDTO browseHisRespDTO = copyInfo2Resp(t);
                SkuQueryOption[] skuQueryOptions = new SkuQueryOption[] { SkuQueryOption.BASIC, SkuQueryOption.MAINPIC, SkuQueryOption.CAlDISCOUNT };
                GdsSkuInfoReqDTO dto = new GdsSkuInfoReqDTO();
                dto.setId(t.getSkuId());
                GdsSkuInfoRespDTO resultDto = gdsSkuInfoQuerySV.querySkuInfoByOptions(dto,skuQueryOptions);
                if (resultDto != null) {
                    browseHisRespDTO.setGdsName(resultDto.getGdsName());
                    if (resultDto.getMainPic() != null) {
                        browseHisRespDTO.setMediaId(resultDto.getMainPic().getMediaUuid());
                        browseHisRespDTO.setMainPicURL(ImageUtil.getImageUrl(resultDto.getMainPic().getMediaUuid()+"_"+"140x140!"));
                    }
                    browseHisRespDTO.setPrice(resultDto.getDiscountPrice());
                    browseHisRespDTO.setGuidePrice(resultDto.getGuidePrice());
                }
                return browseHisRespDTO;
            }
        });
    }
    
    private GdsBrowseHisRespDTO copyInfo2Resp(GdsBrowseHis info){
        GdsBrowseHisRespDTO resp = new GdsBrowseHisRespDTO();
        resp.setId(info.getId());
        resp.setStaffId(info.getStaffId());
        resp.setShopId(info.getShopId());
        resp.setGdsId(info.getGdsId());
        resp.setSkuId(info.getSkuId());
        resp.setBrowseTime(info.getBrowseTime());
        resp.setBrowsePrice(info.getBrowsePrice());
        resp.setBrowseCount(info.getBrowseCount());
        resp.setStatus(info.getStatus());
        resp.setCreateStaff(info.getCreateStaff());
        resp.setCreateTime(info.getCreateTime());
        resp.setUpdateStaff(info.getUpdateStaff());
        resp.setUpdateTime(info.getUpdateTime());
        return resp;
    }
}
