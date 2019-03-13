package com.zengshi.ecp.goods.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.zengshi.ecp.goods.dao.model.GdsInfo;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV;
import com.zengshi.ecp.goods.dubbo.util.GdsUtils;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsCatgCustBlacklistSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdsinfo.IGdsInfoQuerySV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.StringUtil;

public class GdsCatgCustBlacklistRSVImpl extends AbstractRSVImpl implements IGdsCatgCustBlacklistRSV {
	
    @Resource(name = "gdsCatgCustBlacklistSV")
    IGdsCatgCustBlacklistSV gdsCatgCustBlacklistSV;
    @Resource
    private IGdsInfoQuerySV gdsInfoQuerySV;
    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#addGdsCatgCustBlacklist(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public void addGdsCatgCustBlacklist(GdsCatgCustBlacklistReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getShopId(),false,"reqDTO.shopId");
        List<Long> gdsIdList = mergeItemAndList(reqDTO.getGdsId(),reqDTO.getGdsIdList());
        paramNullCheck(gdsIdList,"reqDTO.gdsId and reqDTO.gdsIdList");
        gdsCatgCustBlacklistSV.addGdsCatgCustBlacklist(reqDTO);
        
        //更新商品索引
        for(Long gdsId : gdsIdList){
            //查询当前商品信息缓存
            GdsInfoReqDTO temp = new GdsInfoReqDTO();
            temp.setId(gdsId);
            try {
            GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
            //更新商品索引
            GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, gdsInfo.getId(), gdsInfo.getCatlogId());
            } catch (Exception e) {
                LogUtil.error(MODULE,"添加折扣黑名单时更新商品索引异常", e);
            }
         }
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#deleteGdsCatgCustBlacklistByPrimaryKey(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public int deleteGdsCatgCustBlacklistByPrimaryKey(GdsCatgCustBlacklistReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, true);
        List<Long> idList = mergeItemAndList(reqDTO.getId(),reqDTO.getIdList());
        paramNullCheck(idList,"reqDTO.id and reqDTO.idList");
        
        //将数据缓存起来  用于刷索引
        GdsCatgCustBlacklistReqDTO tempDto = new GdsCatgCustBlacklistReqDTO();
        tempDto.setIdList(idList);
        if(StringUtil.isBlank(reqDTO.getStatus())){
            tempDto.setStatus(GdsConstants.Commons.STATUS_VALID);  
        }
        List<GdsCatgCustBlacklistRespDTO> tempList = null;
        try {
            tempList = gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistList(tempDto);
        } catch (Exception e) {
            LogUtil.error(MODULE,"删除折扣黑名单时查询黑名单列表异常", e);
        }
        
        //执行删除
        int num = gdsCatgCustBlacklistSV.deleteGdsCatgCustBlacklistByPrimaryKey(reqDTO);
        
        //更新商品索引
        if(CollectionUtils.isNotEmpty(tempList)){
            //更新商品索引
            for(GdsCatgCustBlacklistRespDTO item : tempList){
                //查询当前商品信息缓存
                GdsInfoReqDTO temp = new GdsInfoReqDTO();
                temp.setId(item.getGdsId());
                try {
                GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
                //更新商品索引
                GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, gdsInfo.getId(), gdsInfo.getCatlogId());
                } catch (Exception e) {
                    LogUtil.error(MODULE,"添加折扣黑名单时更新商品索引异常", e);
                }
             }
        }
        return num;
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#deleteGdsCatgCustBlacklistByGdsId(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public int deleteGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getShopId(),false,"reqDTO.shopId");
        paramNullCheck(mergeItemAndList(reqDTO.getGdsId(),reqDTO.getGdsIdList()),"reqDTO.gdsId and reqDTO.gdsIdList");
        
        //将数据缓存起来  用于刷索引
        if(StringUtil.isBlank(reqDTO.getStatus())){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);  
        }
        List<GdsCatgCustBlacklistRespDTO> tempList = null;
        try {
            tempList = gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistList(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE,"删除折扣黑名单时查询黑名单列表异常", e);
        }
        
        int num = gdsCatgCustBlacklistSV.deleteGdsCatgCustBlacklistByGdsId(reqDTO);
        
        //更新商品索引
        if(CollectionUtils.isNotEmpty(tempList)){
            //更新商品索引
            for(GdsCatgCustBlacklistRespDTO item : tempList){
                //查询当前商品信息缓存
                GdsInfoReqDTO temp = new GdsInfoReqDTO();
                temp.setId(item.getGdsId());
                try {
                GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
                //更新商品索引
                GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, gdsInfo.getId(), gdsInfo.getCatlogId());
                } catch (Exception e) {
                    LogUtil.error(MODULE,"添加折扣黑名单时更新商品索引异常", e);
                }
             }
        }
        return num;
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#deleteBatchGdsCatgCustBlacklistByGdsId(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public int deleteBatchGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO)
            throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(mergeItemAndList(reqDTO.getGdsId(),reqDTO.getGdsIdList()),"reqDTO.gdsId and reqDTO.gdsIdList");
        
        //将数据缓存起来  用于刷索引
        if(StringUtil.isBlank(reqDTO.getStatus())){
            reqDTO.setStatus(GdsConstants.Commons.STATUS_VALID);  
        }
        List<GdsCatgCustBlacklistRespDTO> tempList = null;
        try {
            tempList = gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistList(reqDTO);
        } catch (Exception e) {
            LogUtil.error(MODULE,"删除折扣黑名单时查询黑名单列表异常", e);
        }
        
        int num = gdsCatgCustBlacklistSV.deleteBatchGdsCatgCustBlacklistByGdsId(reqDTO);
        
        //更新商品索引
        if(CollectionUtils.isNotEmpty(tempList)){
            //更新商品索引
            for(GdsCatgCustBlacklistRespDTO item : tempList){
                //查询当前商品信息缓存
                GdsInfoReqDTO temp = new GdsInfoReqDTO();
                temp.setId(item.getGdsId());
                try {
                GdsInfo gdsInfo = gdsInfoQuerySV.queryGdsInfoModel(temp);
                //更新商品索引
                GdsUtils.sendGdsIndexMsg(null, "T_GDS_INFO", MODULE, gdsInfo.getId(), gdsInfo.getCatlogId());
                } catch (Exception e) {
                    LogUtil.error(MODULE,"添加折扣黑名单时更新商品索引异常", e);
                }
             }
        }
        return num;
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#queryGdsCatgcustBlacklistByExample(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public GdsCatgCustBlacklistRespDTO queryGdsCatgcustBlacklistByExample(
            GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getShopId(),false,"reqDTO.shopId");
        return gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistByExample(reqDTO);
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#queryGdsCatgcustBlacklistPage(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public PageResponseDTO<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistPage(
            GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getShopId(),false,"reqDTO.shopId");
        return gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistPage(reqDTO);
    }

    /**
     * 
     * @see com.zengshi.ecp.goods.dubbo.interfaces.IGdsCatgCustBlacklistRSV#queryGdsCatgcustBlacklistList(com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO)
     */
    @Override
    public List<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistList(
            GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException {
        paramNullCheck(reqDTO, true);
        paramNullCheck(reqDTO.getShopId(),false,"reqDTO.shopId");
        return gdsCatgCustBlacklistSV.queryGdsCatgcustBlacklistList(reqDTO);
    }
    /**
     * 
     * mergeGdsIdAndGdsIdList:(将请求参数的gdsId 跟gdsIdList 合并). <br/> 
     * 
     * @author zhanbh 
     * @param gdsId
     * @param gdsIds
     * @return 
     * @since JDK 1.6
     */
    private List<Long> mergeItemAndList(Long item,List<Long> list){
        List<Long> result = new ArrayList<Long>();
        if(null != item){
            result.add(item);
        }
        if(CollectionUtils.isNotEmpty(list)){
            result.addAll(list);
        }
        return result;
    }
}
