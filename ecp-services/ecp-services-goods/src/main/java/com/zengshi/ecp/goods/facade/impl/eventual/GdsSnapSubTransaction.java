package com.zengshi.ecp.goods.facade.impl.eventual;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsSkuInfoReqDTO;
import com.zengshi.ecp.goods.facade.interfaces.eventual.IGdsSnapSubTransaction;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsSkuSnapSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsSkuInfoQuerySV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfoidx.IGdsInfoIDXSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.alibaba.fastjson.JSON;
import com.distribute.tx.common.TransactionStatus;

public class GdsSnapSubTransaction extends AbstractSVImpl implements IGdsSnapSubTransaction {
    @Resource
    private IGdsSkuSnapSV gdsSkuSnapSV;
    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;
    @Resource
    private IGdsSkuInfoQuerySV gdsSkuInfoQuerySV;
    @Resource
    private IGdsInfoIDXSV gdsInfoIDXSV;
    
    private static final String MODULE = GdsSnapSubTransaction.class.getName();
    @Override
    public void joinTransaction(JSONObject message, TransactionStatus status, String transctionName) {
        LogUtil.info(MODULE, "商品上架回调子事务接收到消息:" + message.toString());
        final GdsSkuInfoReqDTO gdsSkuInfoReqDTO = (GdsSkuInfoReqDTO) JSON.parseObject(message.toString(),
                GdsSkuInfoReqDTO.class);
        if (null != gdsSkuInfoReqDTO) {

            // 上架所有快照保存
            Long snapId;
			try {
				snapId = gdsSkuSnapSV.addGdsSkuSnapInfo(gdsSkuInfoReqDTO);
			} catch (Exception e) {
				LogUtil.error(MODULE, "skuId = " + gdsSkuInfoReqDTO.getId() + "创建快照失败！");
				return;
			}
            // 更新单品主表快照字段
            GdsSkuInfo gdsSkuInfo = new GdsSkuInfo();
            gdsSkuInfo.setId(gdsSkuInfoReqDTO.getId());
            gdsSkuInfo.setSnapId(snapId);
            Long staffId = gdsSkuInfoReqDTO.getStaff().getId();
            editSkuInfo(gdsSkuInfo,staffId);
        
        }
    }
    
    /**
     * 
     * saveSkuInfo:(保存单品信息). <br/>
     * 
     * @author linwb3
     * @param gdsSkuInfoReqDTO
     * @return
     * @since JDK 1.6
     */
    private void editSkuInfo(GdsSkuInfo gdsSkuInfo,Long staffId) {
        Long skuId = gdsSkuInfo.getId();
        gdsSkuInfo.setId(gdsSkuInfo.getId());
        gdsSkuInfo.setUpdateTime(DateUtil.getSysDate());
        gdsSkuInfo.setUpdateStaff(staffId);
        gdsSkuInfoMapper.updateByPrimaryKeySelective(gdsSkuInfo);

        // 查询旧数据，用新数据刷新旧数据，然后更新缓存
        GdsSkuInfoReqDTO gdsSkuInfoReq = new GdsSkuInfoReqDTO();
        gdsSkuInfoReq.setId(skuId);
        GdsSkuInfo before = null;
        before = (GdsSkuInfo) CacheUtil.getItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX+ skuId);
        if(before==null){
            before = gdsSkuInfoQuerySV.querySkuInfoFromDB(gdsSkuInfoReq);
        }
        ObjectCopyUtil.copyObjValue(gdsSkuInfo, before, null, false);
        
        try {
            CacheUtil.addItem(GdsConstants.GdsInfoCacheKey.SKU_CACHE_KEY_PREFIX+ skuId, before,GdsConstants.GdsInfoCacheKey.SKU_CACHE_TIME);
        } catch (Exception e) {
            LogUtil.error(MODULE,"edit skuInfo cache failed! please check  Cache Server!", e);
        }
        gdsInfoIDXSV.editSkuInfoIDX(before, null);
    }
}
