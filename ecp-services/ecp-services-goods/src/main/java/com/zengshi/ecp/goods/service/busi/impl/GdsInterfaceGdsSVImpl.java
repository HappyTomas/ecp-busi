package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsInterfaceGdsGidxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsInterfaceGdsMapper;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGds;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidx;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsGidxCriteria.Criteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsDataImportErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsInterfaceGdsReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsInterfaceGdsGidxReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsInterfaceGdsSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsInterfaceGdsSVImpl extends AbstractSVImpl implements IGdsInterfaceGdsSV {

    @Resource
    private GdsInterfaceGdsMapper gdsInterfaceGdsMapper;

    @Resource
    private GdsInterfaceGdsGidxMapper gdsInterfaceGdsGidxMapper;

    @Resource(name = "seq_gds_interface_gdsr")
    private Sequence seqGdsInterfaceGds;

    @Override
    public void saveGdsInterfaceGds(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {

        Long id = this.seqGdsInterfaceGds.nextValue();

        // ------------保存维度是第三方系统商品的映射表------------
        GdsInterfaceGds gdsInterfaceGds = new GdsInterfaceGds();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGdsReqDTO, gdsInterfaceGds, null, false);
        preInsert(gdsInterfaceGdsReqDTO, gdsInterfaceGds);
        gdsInterfaceGds.setId(id);
        gdsInterfaceGds.setStatus(GdsConstants.Commons.STATUS_VALID);
        this.gdsInterfaceGdsMapper.insert(gdsInterfaceGds);

        // ------------保存维度是ECP商品的映射表------------
        GdsInterfaceGdsGidx gdsInterfaceGdsGidx = new GdsInterfaceGdsGidx();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGdsReqDTO, gdsInterfaceGdsGidx, null, false);
        preInsert(gdsInterfaceGdsReqDTO, gdsInterfaceGdsGidx);
        gdsInterfaceGdsGidx.setId(id);
        gdsInterfaceGdsGidx.setStatus(GdsConstants.Commons.STATUS_VALID);
        this.gdsInterfaceGdsGidxMapper.insert(gdsInterfaceGdsGidx);
    }

    //TODO 该接口命名有问题，ECP侧商品编码和原始系统商品编码都是必传的。
    @Override
    public void deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {

        if(gdsInterfaceGdsReqDTO.getGdsId()==null||gdsInterfaceGdsReqDTO.getGdsId().longValue()==0){
            throw new BusinessException("error.goods.dataimport.15");
        }

        if(StringUtils.isBlank(gdsInterfaceGdsReqDTO.getOriginGdsId())){
            throw new BusinessException("error.goods.dataimport.16");
        }

        // ------------删除维度是第三方系统商品的映射表------------
        GdsInterfaceGds gdsInterfaceGds = new GdsInterfaceGds();
        // 包含更新更新时间字段
        preUpdate(gdsInterfaceGdsReqDTO, gdsInterfaceGds);
        gdsInterfaceGds.setStatus(GdsConstants.Commons.STATUS_INVALID);

        GdsInterfaceGdsCriteria criteria1 = new GdsInterfaceGdsCriteria();
        com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria.Criteria c1 = criteria1.createCriteria();
        c1.andOriginGdsIdEqualTo(gdsInterfaceGdsReqDTO.getOriginGdsId());//根据分库键删除
        if(StringUtil.isNotBlank(gdsInterfaceGdsReqDTO.getOrigin())){
            c1.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());
        }
        c1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        // 假删除
        this.gdsInterfaceGdsMapper.updateByExampleSelective(gdsInterfaceGds, criteria1);

        // ------------删除维度是ECP商品的映射表------------
        GdsInterfaceGdsGidx gdsInterfaceGdsGidx = new GdsInterfaceGdsGidx();
        // 包含更新更新时间字段
        preUpdate(gdsInterfaceGdsReqDTO, gdsInterfaceGdsGidx);
        gdsInterfaceGdsGidx.setStatus(GdsConstants.Commons.STATUS_INVALID);

        GdsInterfaceGdsGidxCriteria criteria2 = new GdsInterfaceGdsGidxCriteria();
        Criteria c2 = criteria2.createCriteria();
        c2.andGdsIdEqualTo(gdsInterfaceGdsReqDTO.getGdsId());//根据分库键删除
        if(StringUtil.isNotBlank(gdsInterfaceGdsReqDTO.getOrigin())){
            c2.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());  
        }
        c2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);

        // 假删除
        this.gdsInterfaceGdsGidxMapper.updateByExampleSelective(gdsInterfaceGdsGidx, criteria2);

    }

    @Override
    public void deleteGdsInterfaceGdsByGdsId(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {

       GdsInterfaceGdsGidxCriteria gdsInterfaceGdsGidxExam  = new GdsInterfaceGdsGidxCriteria();
       GdsInterfaceGdsGidxCriteria.Criteria gdsInterfaceGdsGidxCriteria = gdsInterfaceGdsGidxExam.createCriteria() ;
       gdsInterfaceGdsGidxCriteria.andGdsIdEqualTo(gdsInterfaceGdsReqDTO.getGdsId());
       if(StringUtils.isNotBlank(gdsInterfaceGdsReqDTO.getOrigin())){
           gdsInterfaceGdsGidxCriteria.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());           
       }
       gdsInterfaceGdsGidxCriteria.andSkuIdEqualTo(gdsInterfaceGdsReqDTO.getSkuId());
       gdsInterfaceGdsGidxCriteria.andShopIdEqualTo(gdsInterfaceGdsReqDTO.getShopId());
       gdsInterfaceGdsGidxCriteria.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
       List<GdsInterfaceGdsGidx> gdsGidxs = this.gdsInterfaceGdsGidxMapper.selectByExample(gdsInterfaceGdsGidxExam);
       if(!CollectionUtils.isEmpty(gdsGidxs)){
    	   GdsInterfaceGdsGidx gdsInterfaceGdsGidx = gdsGidxs.get(0);
    	   gdsInterfaceGdsReqDTO.setOriginGdsId(gdsInterfaceGdsGidx.getOriginGdsId());
    	   gdsInterfaceGdsReqDTO.setOriginSkuId(gdsInterfaceGdsGidx.getOriginSkuId());
    	   this.deleteGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(gdsInterfaceGdsReqDTO);
       }
     
    }
    
    
    @Override
    public void updateGdsInterfaceGdsByGdsInfoAndOriginGdsInfo(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
            throws BusinessException {

        if(gdsInterfaceGdsReqDTO.getGdsId()==null||gdsInterfaceGdsReqDTO.getGdsId().longValue()==0){
            throw new BusinessException("error.goods.dataimport.15");
        }

        if(StringUtils.isBlank(gdsInterfaceGdsReqDTO.getOriginGdsId())){
            throw new BusinessException("error.goods.dataimport.16");
        }

        // ------------保存维度是第三方系统商品的映射表------------
        GdsInterfaceGds gdsInterfaceGds = new GdsInterfaceGds();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGdsReqDTO, gdsInterfaceGds, null, false);
        preUpdate(gdsInterfaceGdsReqDTO, gdsInterfaceGds);
        GdsInterfaceGdsCriteria criteria1 = new GdsInterfaceGdsCriteria();
        com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria.Criteria c1 = criteria1.createCriteria();
        c1.andOriginGdsIdEqualTo(gdsInterfaceGdsReqDTO.getOriginGdsId());//根据分库键更新
        c1.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());
        c1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        this.gdsInterfaceGdsMapper.updateByExampleSelective(gdsInterfaceGds, criteria1);

        // ------------保存维度是ECP商品的映射表------------
        GdsInterfaceGdsGidx gdsInterfaceGdsGidx = new GdsInterfaceGdsGidx();
        ObjectCopyUtil.copyObjValue(gdsInterfaceGdsReqDTO, gdsInterfaceGdsGidx, null, false);
        preUpdate(gdsInterfaceGdsReqDTO, gdsInterfaceGdsGidx);
        GdsInterfaceGdsGidxCriteria criteria2 = new GdsInterfaceGdsGidxCriteria();
        Criteria c2 = criteria2.createCriteria();
        c2.andGdsIdEqualTo(gdsInterfaceGdsReqDTO.getGdsId());//根据分库键更新
        c2.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());
        c2.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        this.gdsInterfaceGdsGidxMapper.updateByExampleSelective(gdsInterfaceGdsGidx, criteria2);
        
    }

    @Override
    public GdsInterfaceGds queryGdsInterfaceGdsByOriginGdsId(
            GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO) throws BusinessException {
        GdsInterfaceGdsCriteria criteria1 = new GdsInterfaceGdsCriteria();
        com.zengshi.ecp.goods.dao.model.GdsInterfaceGdsCriteria.Criteria c1 = criteria1.createCriteria();
        
        //泽云原始商品编码需要添加来源系统前缀
        //加上系统来源类型前缀的商品编码就可以保证唯一了。
        c1.andOriginGdsIdEqualTo(gdsInterfaceGdsReqDTO.getOriginGdsId());

        if(StringUtils.isNotBlank(gdsInterfaceGdsReqDTO.getOrigin())){
            c1.andOriginEqualTo(gdsInterfaceGdsReqDTO.getOrigin());
        }
        
        c1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsInterfaceGds> gdsInterfaceGdsList = this.gdsInterfaceGdsMapper
                .selectByExample(criteria1);

        if (CollectionUtils.isEmpty(gdsInterfaceGdsList)) {
            return null;
        }
        
        //找到多个抛出异常
        if(gdsInterfaceGdsList.size()>1){
            throw new BusinessException(GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_13,new String[]{gdsInterfaceGdsReqDTO.getOriginGdsId()});
        }

        return gdsInterfaceGdsList.get(0);
    }

    @Override
    public GdsInterfaceGdsGidx queryGdsInterfaceGdsGidxByEcpGdsId(
            GdsInterfaceGdsGidxReqDTO gdsInterfaceGdsGidxReqDTO) throws BusinessException {
        GdsInterfaceGdsGidxCriteria criteria1 = new GdsInterfaceGdsGidxCriteria();
        Criteria c1 = criteria1.createCriteria();
        c1.andGdsIdEqualTo(gdsInterfaceGdsGidxReqDTO.getGdsId());

        if(StringUtils.isNotBlank(gdsInterfaceGdsGidxReqDTO.getOrigin())){
            c1.andOriginEqualTo(gdsInterfaceGdsGidxReqDTO.getOrigin());
        }
        
        c1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsInterfaceGdsGidx> gdsInterfaceGdsGidxList = this.gdsInterfaceGdsGidxMapper
                .selectByExample(criteria1);

        if (CollectionUtils.isEmpty(gdsInterfaceGdsGidxList)) {
            return null;
        }
        
        //找到多个抛出异常
        if(gdsInterfaceGdsGidxList.size()>1){
            throw new BusinessException(GdsDataImportErrorConstants.Commons.ERROR_GOODS_DATAIMPORT_14,new String[]{gdsInterfaceGdsGidxReqDTO.getGdsId()+""});
        }
        
        return gdsInterfaceGdsGidxList.get(0);
    }

	@Override
	public List<GdsInterfaceGds> queryGdsInterfaceGdsByDate(GdsInterfaceGdsReqDTO gdsInterfaceGdsReqDTO)
			throws BusinessException {
		GdsInterfaceGdsCriteria criteria1 = new GdsInterfaceGdsCriteria();
		GdsInterfaceGdsCriteria.Criteria c1 = criteria1.createCriteria();
        String starTime = gdsInterfaceGdsReqDTO.getStartTime()+" 23:55:00";
        String endTime = gdsInterfaceGdsReqDTO.getEndTime()+" 23:59:59";
        
        Timestamp starVal = Timestamp.valueOf(starTime);
        Timestamp endVal = Timestamp.valueOf(endTime);
        c1.andCreateTimeBetween(starVal, endVal);
        c1.andStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsInterfaceGds> interFaceGdsList = gdsInterfaceGdsMapper.selectByExample(criteria1);
		return interFaceGdsList;
	}

    public static void main(String[] args) {
    	GdsInterfaceGdsReqDTO dto = new GdsInterfaceGdsReqDTO();
    	dto.setCreateTime(Timestamp.valueOf("2016-10-12 00:00:00"));
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		
    	//new GdsInterfaceGdsSVImpl().queryGdsInterfaceGdsByDate(dto);
	}
}
