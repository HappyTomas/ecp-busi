/** 
 * Project Name:ecp-services-goods 
 * File Name:GdsEvalIDXSVImpl.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl 
 * Date:2015年8月24日上午11:48:24 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */
package com.zengshi.ecp.goods.service.busi.impl.gdsinfoidx;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectGdsIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsCollectStaffIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalGdsIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalOrdIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsEvalStaffIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.manual.GdsCollectShopIdxExtraMapper;
import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria.Criteria;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEval;
import com.zengshi.ecp.goods.dao.model.GdsEvalGdsIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalGdsIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalOrdIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalOrdIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdx;
import com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdxCriteria;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCollectRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月24日上午11:48:24 <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version
 * @since JDK 1.6
 */
public class GdsIDXSVImpl extends AbstractSVImpl implements IGdsIDXSV {

    @Resource
    private GdsEvalGdsIdxMapper gdsIdxMapper;

    @Resource
    private GdsEvalOrdIdxMapper ordIdxMapper;

    @Resource
    private GdsEvalShopIdxMapper shopIdxMapper;

    @Resource
    private GdsEvalStaffIdxMapper staffIdxMapper;

    // 商品收藏索引表Mapper
    @Resource
    private GdsCollectGdsIdxMapper gdsCollectGdsIdxMapper;

    @Resource
    private GdsCollectShopIdxMapper gdsCollectShopIdxMapper;
    
    @Resource
    private GdsCollectShopIdxExtraMapper gdsCollectShopIdxExtraMapper;

    @Resource
    private GdsCollectStaffIdxMapper gdsCollectStaffIdxMapper;

    @Override
    public void createGdsEvalIdx(GdsEval record) throws BusinessException {
        gdsEvalIdxParamChk(record);
        this.createGdsEvalGdsIdx(record);
        this.createGdsEvalOrdIdx(record);
        this.createGdsEvalShopIdx(record);
        this.createGdsEvalStaffIdx(record);
        
    }

	

	@Override
	public void deleteGdsEvalIdx(GdsEval record) throws BusinessException {
		gdsEvalIdxParamChk(record);
		this.deleteGdsEvalOrdIdx(record.getId(), record.getOrderId(), record.getOrderSubId());
        this.deleteGdsEvalShopIdx(record.getId(), record.getShopId());
        this.deleteGdsEvalStaffIdx(record.getId(), record.getStaffId());
        this.deleteGdsEvalGdsIdx(record.getId(), record.getSkuId());
	}

	@Override
	public void updateGdsEvalIdx(GdsEval record) throws BusinessException {
		gdsEvalIdxParamChk(record);
		
		// 评单维度.
		GdsEvalOrdIdx ordIdx = new GdsEvalOrdIdx();
		ObjectCopyUtil.copyObjValue(record, ordIdx, null, true);
		ordIdx.setEvalId(record.getId());
		GdsEvalOrdIdxCriteria ordIdxExample = new GdsEvalOrdIdxCriteria();
		GdsEvalOrdIdxCriteria.Criteria c1 = ordIdxExample.createCriteria();
		c1.andEvalIdEqualTo(ordIdx.getEvalId());
		c1.andOrderIdEqualTo(ordIdx.getOrderId());
		c1.andOrderSubIdEqualTo(ordIdx.getOrderSubId());
		ordIdxMapper.updateByExampleSelective(ordIdx, ordIdxExample);
		
		// 用户维度.
		GdsEvalStaffIdx staffIdx = new GdsEvalStaffIdx();
		ObjectCopyUtil.copyObjValue(record, staffIdx, null, true);
		staffIdx.setEvalId(record.getId());
		GdsEvalStaffIdxCriteria staffIdxExample = new GdsEvalStaffIdxCriteria();
		GdsEvalStaffIdxCriteria.Criteria c2 = staffIdxExample.createCriteria();
		c2.andEvalIdEqualTo(staffIdx.getEvalId());
		c2.andStaffIdEqualTo(staffIdx.getStaffId());
		staffIdxMapper.updateByExampleSelective(staffIdx, staffIdxExample);
		// 店铺维度.
		GdsEvalShopIdx shopIdx = new GdsEvalShopIdx();
		ObjectCopyUtil.copyObjValue(record, shopIdx, null, true);
		shopIdx.setEvalId(record.getId());
		GdsEvalShopIdxCriteria shopIdxExample = new GdsEvalShopIdxCriteria();
		GdsEvalShopIdxCriteria.Criteria c3 = shopIdxExample.createCriteria();
		c3.andEvalIdEqualTo(shopIdx.getEvalId());
		c3.andShopIdEqualTo(shopIdx.getShopId());
		shopIdxMapper.updateByExampleSelective(shopIdx, shopIdxExample);
		// 商品维度.
		GdsEvalGdsIdx gdsIdx = new GdsEvalGdsIdx();
		ObjectCopyUtil.copyObjValue(record, gdsIdx, null, true);
		gdsIdx.setEvalId(record.getId());
		GdsEvalGdsIdxCriteria gdsIdxExample = new GdsEvalGdsIdxCriteria();
		GdsEvalGdsIdxCriteria.Criteria c4 = gdsIdxExample.createCriteria();
		c4.andEvalIdEqualTo(gdsIdx.getEvalId());
		c4.andGdsIdEqualTo(gdsIdx.getGdsId());
		gdsIdxMapper.updateByExampleSelective(gdsIdx, gdsIdxExample);
	}



	@Override
    public void saveGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException {
        
        checkGdsCollectIdxParam(reqDTO);

        // 商品维度
        GdsCollectGdsIdx gdsIdx = new GdsCollectGdsIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsIdx, null, false);
        gdsIdx.setCollId(reqDTO.getId());
        preInsert(reqDTO, gdsIdx);
        this.gdsCollectGdsIdxMapper.insert(gdsIdx);

        // 店铺维度
        GdsCollectShopIdx shopIdx = new GdsCollectShopIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, shopIdx, null, false);
        shopIdx.setCollId(reqDTO.getId());
        preInsert(reqDTO, shopIdx);
        this.gdsCollectShopIdxMapper.insert(shopIdx);

        // 用户维度
        GdsCollectStaffIdx staffIdx = new GdsCollectStaffIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, staffIdx, null, false);
        staffIdx.setCollId(reqDTO.getId());
        preInsert(reqDTO, staffIdx);
        this.gdsCollectStaffIdxMapper.insert(staffIdx);

    }

    @Override
    public void deleteGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException {
        
        checkGdsCollectIdxParam(reqDTO);

        // 商品维度，物理删除
        GdsCollectGdsIdxCriteria criteria1 = new GdsCollectGdsIdxCriteria();
        Criteria c1 = criteria1.createCriteria();
        c1.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectGdsIdxMapper.deleteByExample(criteria1);

        // 店铺维度，物理删除
        GdsCollectShopIdxCriteria criteria2 = new GdsCollectShopIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria.Criteria c2 = criteria2
                .createCriteria();
        c2.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectShopIdxMapper.deleteByExample(criteria2);

        // 用户维度，物理删除
        GdsCollectStaffIdxCriteria criteria3 = new GdsCollectStaffIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria.Criteria c3 = criteria3
                .createCriteria();
        c3.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectStaffIdxMapper.deleteByExample(criteria3);

    }

    @Override
    public void updateGdsCollectIdx(GdsCollectReqDTO reqDTO) throws BusinessException {
        
        checkGdsCollectIdxParam(reqDTO);

        // 商品维度
        GdsCollectGdsIdx gdsIdx = new GdsCollectGdsIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsIdx, null, false);
        preUpdate(reqDTO, gdsIdx);
        GdsCollectGdsIdxCriteria criteria1 = new GdsCollectGdsIdxCriteria();
        Criteria c1 = criteria1.createCriteria();
        c1.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectGdsIdxMapper.updateByExampleSelective(gdsIdx, criteria1);

        // 店铺维度
        GdsCollectShopIdx shopIdx = new GdsCollectShopIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, shopIdx, null, false);
        preUpdate(reqDTO, shopIdx);
        GdsCollectShopIdxCriteria criteria2 = new GdsCollectShopIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria.Criteria c2 = criteria2
                .createCriteria();
        c2.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectShopIdxMapper.updateByExampleSelective(shopIdx, criteria2);

        
        // 用户维度
        GdsCollectStaffIdx staffIdx = new GdsCollectStaffIdx();
        ObjectCopyUtil.copyObjValue(reqDTO, staffIdx, null, false);
        preUpdate(reqDTO, staffIdx);
        GdsCollectStaffIdxCriteria criteria3 = new GdsCollectStaffIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria.Criteria c3 = criteria3
                .createCriteria();
        c3.andCollIdEqualTo(reqDTO.getId());
        this.gdsCollectStaffIdxMapper.updateByExampleSelective(staffIdx, criteria3);

    }
    
    private void checkGdsCollectIdxParam(GdsCollectReqDTO reqDTO) throws BusinessException{
        
        //无论是创建、修改、删除三个维度
        this.paramNullCheck(reqDTO.getId(), "collectId");
        this.paramNullCheck(reqDTO.getGdsId(), "gdsId");
        this.paramNullCheck(reqDTO.getShopId(), "shopId");
        this.paramNullCheck(reqDTO.getStaffId(), "staffId");
    }
    
    /**
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV#selectByExample(com.zengshi.ecp.goods.dao.model.GdsCollectGdsIdxCriteria)
     */
    public List<GdsCollectGdsIdx> selectByExample(GdsCollectGdsIdxCriteria example) throws BusinessException{
        return gdsCollectGdsIdxMapper.selectByExample(example);
    }
    
    @Override
    public long countByExampleOrderByCollAmount(GdsCollectShopIdxCriteria example) throws BusinessException{
    	return gdsCollectShopIdxExtraMapper.countByExampleOrderByCollAmount(example); 
    }
    
    
    @Override
	public List<GdsCollectRespDTO> selectByExampleOrderByCollAmount(
			GdsCollectShopIdxCriteria example) throws BusinessException {
		return gdsCollectShopIdxExtraMapper.selectByExampleOrderByCollAmount(example);
	}



	/**
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV#selectByExample(com.zengshi.ecp.goods.dao.model.GdsCollectShopIdxCriteria)
     */
    public List<GdsCollectShopIdx> selectByExample(GdsCollectShopIdxCriteria example) throws BusinessException{
        if(example.isDistinct()){
            return gdsCollectShopIdxExtraMapper.selectByExampleDistinctSkuId(example);
        }else{
            return gdsCollectShopIdxMapper.selectByExample(example);
        }
    }
    
    /**
     * @see com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsIDXSV#selectByExample(com.zengshi.ecp.goods.dao.model.GdsCollectStaffIdxCriteria)
     */
    public List<GdsCollectStaffIdx> selectByExample(GdsCollectStaffIdxCriteria example) throws BusinessException{
        return gdsCollectStaffIdxMapper.selectByExample(example);
    }

    @Override
    public long countByExample(GdsCollectGdsIdxCriteria example) throws BusinessException {
        return gdsCollectGdsIdxMapper.countByExample(example);
    }

    @Override
    public long countByExample(GdsCollectShopIdxCriteria example) throws BusinessException {
        if(example.isDistinct()){
            return gdsCollectShopIdxExtraMapper.countByExampleDistinctSkuId(example);
        }else{
            return gdsCollectShopIdxMapper.countByExample(example);
        }
    }

    @Override
    public long countByExample(GdsCollectStaffIdxCriteria example) throws BusinessException {
        return gdsCollectStaffIdxMapper.countByExample(example);
    }
    
    
    /************************************
     ************* private method********    
     ************************************/
    
    /*
     * 
     * 商品评价索引参数空检测.<br/>
     * 
     * @author liyong7
     * @param reqDTO 
     * @since JDK 1.6
     */
    private void gdsEvalIdxParamChk(GdsEval record) {
		paramNullCheck(record);
		paramCheck(new Object[]{
							record.getId(),
							record.getSkuId(),
							record.getOrderId(),
							record.getStaffId(),
							record.getShopId()
				    }, 
				   new String[]{
				               "id",
				               "skuId",
				               "orderId",
				               "staffId",
				               "shopId"
		});
	}
    
    
    private GdsEvalGdsIdx createGdsEvalGdsIdx(GdsEval eval) throws BusinessException {
        GdsEvalGdsIdx idx = new GdsEvalGdsIdx();
        ObjectCopyUtil.copyObjValue(eval, idx, null, false);
        idx.setEvalId(eval.getId());
        gdsIdxMapper.insert(idx);
        return idx;
    }

    private GdsEvalOrdIdx createGdsEvalOrdIdx(GdsEval eval) throws BusinessException {

        GdsEvalOrdIdx idx = new GdsEvalOrdIdx();
        ObjectCopyUtil.copyObjValue(eval, idx, null, false);
        idx.setEvalId(eval.getId());
        ordIdxMapper.insert(idx);
        return idx;

    }

    private GdsEvalShopIdx createGdsEvalShopIdx(GdsEval eval) throws BusinessException {
        GdsEvalShopIdx idx = new GdsEvalShopIdx();
        ObjectCopyUtil.copyObjValue(eval, idx, null, false);
        idx.setEvalId(eval.getId());
        shopIdxMapper.insert(idx);
        return idx;
    }

    private GdsEvalStaffIdx createGdsEvalStaffIdx(GdsEval eval) throws BusinessException {
        GdsEvalStaffIdx idx = new GdsEvalStaffIdx();
        ObjectCopyUtil.copyObjValue(eval, idx, null, false);
        idx.setEvalId(eval.getId());
        staffIdxMapper.insert(idx);
        return idx;
    }

    private int deleteGdsEvalShopIdx(Long id, Long shopId) throws BusinessException {
        GdsEvalShopIdxCriteria criteria = new GdsEvalShopIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsEvalShopIdxCriteria.Criteria c = criteria.createCriteria();
        c.andEvalIdEqualTo(id);
        c.andShopIdEqualTo(shopId);
        return shopIdxMapper.deleteByExample(criteria);
    }

    private int deleteGdsEvalStaffIdx(Long id, Long createStaff) throws BusinessException {
        GdsEvalStaffIdxCriteria criteria = new GdsEvalStaffIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsEvalStaffIdxCriteria.Criteria c = criteria.createCriteria();
        c.andEvalIdEqualTo(id);
        c.andCreateStaffEqualTo(createStaff);
        return staffIdxMapper.deleteByExample(criteria);
    }

    private int deleteGdsEvalGdsIdx(Long id, Long skuId) throws BusinessException {
        GdsEvalGdsIdxCriteria criteria = new GdsEvalGdsIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsEvalGdsIdxCriteria.Criteria c = criteria.createCriteria();
        c.andEvalIdEqualTo(id);
        c.andSkuIdEqualTo(skuId);
        return gdsIdxMapper.deleteByExample(criteria);
    }

    private int deleteGdsEvalOrdIdx(Long id, String orderId, String subOrderId)
            throws BusinessException {
        GdsEvalOrdIdxCriteria criteria = new GdsEvalOrdIdxCriteria();
        com.zengshi.ecp.goods.dao.model.GdsEvalOrdIdxCriteria.Criteria c = criteria.createCriteria();
        c.andEvalIdEqualTo(id);
        c.andOrderIdEqualTo(orderId);
        if (StringUtils.hasText(subOrderId)) {
            c.andOrderSubIdEqualTo(subOrderId);
        }
        return ordIdxMapper.deleteByExample(criteria);
    }
    
}
