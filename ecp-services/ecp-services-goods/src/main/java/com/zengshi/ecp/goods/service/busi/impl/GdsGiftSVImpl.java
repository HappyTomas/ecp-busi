package com.zengshi.ecp.goods.service.busi.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGiftMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsGiftShopIdxMapper;
import com.zengshi.ecp.goods.dao.mapper.busi.GdsSkuInfoMapper;
import com.zengshi.ecp.goods.dao.model.GdsGift;
import com.zengshi.ecp.goods.dao.model.GdsGiftCriteria;
import com.zengshi.ecp.goods.dao.model.GdsGiftShopIdx;
import com.zengshi.ecp.goods.dao.model.GdsGiftShopIdxCriteria;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfo;
import com.zengshi.ecp.goods.dao.model.GdsSkuInfoCriteria;
import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.constants.GdsErrorConstants;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsGiftRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfoidx.GdsGiftShopIdxRespDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV;
import com.zengshi.ecp.goods.service.common.impl.AbstractSVImpl;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.db.sequence.Sequence;

public class GdsGiftSVImpl extends AbstractSVImpl implements IGdsGiftSV{
    
    @Resource
    private GdsGiftMapper gdsGiftMapper;

    @Resource
    private GdsGiftShopIdxMapper gdsGiftShopIdxMapper;
    
    @Resource
    private GdsSkuInfoMapper gdsSkuInfoMapper;
    
    @Resource(name = "seq_gds_gift")
    private Sequence seqGdsGift;
    
    /**
     * 
     * 新增保存赠品（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#saveGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void saveGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        Long id = seqGdsGift.nextValue();
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        Long createStaff = reqDTO.getStaff().getId();
        //添加记录到商品赠品表（t_gds_gift）
        GdsGift gdsGift = new GdsGift();
        getGdsGiftShopIdxParam(gdsGift,reqDTO);
        gdsGift.setId(id);
        gdsGift.setCreateTime(createTime);
        gdsGift.setCreateStaff(createStaff);
        gdsGift.setUpdateTime(createTime);
        gdsGift.setUpdateStaff(createStaff);
        gdsGiftMapper.insert(gdsGift);
        //添加记录到商品赠品店铺索引维度表（t_gds_gift_shop_idx）
        GdsGiftShopIdx gdsGiftShopIdx = new GdsGiftShopIdx();
        //获取前店传过来的参数
        getGdsGiftShopIdxParam(gdsGiftShopIdx,reqDTO);
        gdsGiftShopIdx.setGiftId(id);
        gdsGiftShopIdx.setCreateStaff(createStaff);
        gdsGiftShopIdx.setCreateTime(createTime);
        gdsGiftShopIdx.setUpdateTime(createTime);
        gdsGiftShopIdx.setUpdateStaff(createStaff);
        gdsGiftShopIdxMapper.insert(gdsGiftShopIdx);
    }
   
    /**
     * 
     * 删除赠品（只做逻辑删除）（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#delteGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void delteGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        Long updateStaff = reqDTO.getStaff().getId();
        Long id = reqDTO.getId();
        //失效商品赠品表（t_gds_gift）
        GdsGift gdsGift = new GdsGift();
        gdsGift.setId(id);
        gdsGift.setUpdateStaff(updateStaff);
        gdsGift.setUpdateTime(updateTime);
        gdsGift.setGiftStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
        GdsGiftCriteria.Criteria giftCriteria = gdsGiftCriteria.createCriteria();
        giftCriteria.andIdEqualTo(reqDTO.getId());
        giftCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftMapper.updateByExampleSelective(gdsGift, gdsGiftCriteria);
        //失效商品赠品店铺索引维度表（t_gds_gift_shop_idx）
        GdsGiftShopIdx gdsGiftShopIdx= new GdsGiftShopIdx();
        gdsGiftShopIdx.setUpdateStaff(updateStaff);
        gdsGiftShopIdx.setUpdateTime(updateTime);
        gdsGiftShopIdx.setGiftStatus(GdsConstants.Commons.STATUS_INVALID);
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        GdsGiftShopIdxCriteria.Criteria idxCriteria = gdsGiftShopIdxCriteria.createCriteria();
        idxCriteria.andGiftIdEqualTo(id);
        idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idxCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftShopIdxMapper.updateByExampleSelective(gdsGiftShopIdx, gdsGiftShopIdxCriteria);
    }
    /**
     * 
     * 保存编辑的赠品（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#editGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void editGdsGift(GdsGiftReqDTO reqDTO) throws BusinessException {
        Long updateStaff = reqDTO.getStaff().getId();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        //更新商品赠品主表（t_gds_gift）
        GdsGift gdsGift = new GdsGift();
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGift, "", false);
        gdsGift.setUpdateStaff(updateStaff);
        gdsGift.setUpdateTime(updateTime);
        getEditGdsGiftParam(gdsGift,reqDTO);
        GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
        GdsGiftCriteria.Criteria giftCriteria = gdsGiftCriteria.createCriteria();
        giftCriteria.andIdEqualTo(reqDTO.getId());
        giftCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftMapper.updateByExampleSelective(gdsGift, gdsGiftCriteria);
        //更新商品赠品店铺索引维度表
        GdsGiftShopIdx gdsGiftShopIdx = new GdsGiftShopIdx();
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        GdsGiftShopIdxCriteria.Criteria idxCriteria = gdsGiftShopIdxCriteria.createCriteria();
        idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idxCriteria.andGiftIdEqualTo(reqDTO.getId());
        ObjectCopyUtil.copyObjValue(reqDTO, gdsGiftShopIdx, "", false);
        //获取前店传过来的参数
        getGdsGiftShopIdxParam(gdsGiftShopIdx,reqDTO);
        gdsGiftShopIdx.setUpdateStaff(updateStaff);
        gdsGiftShopIdx.setUpdateTime(updateTime);
        gdsGiftShopIdxMapper.updateByExampleSelective(gdsGiftShopIdx, gdsGiftShopIdxCriteria);
    }
   
    /**
     * 
     * 获取赠品列表信息（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#queryGdsGift(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public PageResponseDTO<GdsGiftRespDTO> queryGdsGift(GdsGiftReqDTO reqDTO)
            throws BusinessException {
        PageResponseDTO<GdsGiftRespDTO> resultPages = new PageResponseDTO<GdsGiftRespDTO>();
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        getReuestCondition(reqDTO,gdsGiftShopIdxCriteria);
        PageResponseDTO<GdsGiftShopIdxRespDTO> pageInfo = super.queryByPagination(reqDTO, gdsGiftShopIdxCriteria,
                        false, new PaginationCallback<GdsGiftShopIdx, GdsGiftShopIdxRespDTO>(){
                            @Override
                            public List<GdsGiftShopIdx> queryDB(BaseCriteria criteria) {
                                return gdsGiftShopIdxMapper.selectByExample((GdsGiftShopIdxCriteria)criteria);
                            }
                            @Override
                            public long queryTotal(BaseCriteria example) {
                                return gdsGiftShopIdxMapper.countByExample((GdsGiftShopIdxCriteria)example);
                            }
                            @Override
                            public GdsGiftShopIdxRespDTO warpReturnObject(GdsGiftShopIdx t) {
                                GdsGiftShopIdxRespDTO dto = new GdsGiftShopIdxRespDTO();
                                ObjectCopyUtil.copyObjValue(t, dto, null, true);
                                if(StringUtils.isNotBlank(dto.getGiftType())){
                                    dto.setGiftTypeName(BaseParamUtil.fetchParamValue("GDS_GIFT_TYPE", dto.getGiftType()));
                                }
                                return dto;
                            }
                            
                });
        List<GdsGiftRespDTO> giftList = new ArrayList<GdsGiftRespDTO>();
        if(StringUtil.isNotEmpty(pageInfo) && StringUtil.isNotEmpty(pageInfo.getResult())){
            for(GdsGiftShopIdxRespDTO dto : pageInfo.getResult()){
                GdsGiftRespDTO resultDto = new GdsGiftRespDTO();
                ObjectCopyUtil.copyObjValue(dto, resultDto, "", false);
                GdsGift gdsGiftInfo = gdsGiftMapper.selectByPrimaryKey(dto.getGiftId());
                if(gdsGiftInfo != null){
                    resultDto.setGiftSend(gdsGiftInfo.getGiftSend());
                    resultDto.setGiftValid(gdsGiftInfo.getGiftValid());
                }
                resultDto.setId(dto.getGiftId());
                if(GdsConstants.Commons.STATUS_VALID.equals(resultDto.getGiftStatus())){
                    resultDto.setGiftStatusName("有效");
                }else{
                    resultDto.setGiftStatusName("无效");
                }
                if(dto!=null && dto.getSkuId().longValue()!=0){
                    GdsSkuInfo gdsskuInfo = gdsSkuInfoMapper.selectByPrimaryKey(dto.getSkuId());
                    if(gdsskuInfo!=null){
                        resultDto.setGdsName(gdsskuInfo.getGdsName());
                    }
                }
               
                giftList.add(resultDto);
            }
        }
        resultPages.setCount(pageInfo.getCount());
        resultPages.setPageCount(pageInfo.getPageCount());
        resultPages.setPageNo(pageInfo.getPageNo());
        resultPages.setPageSize(pageInfo.getPageSize());
        resultPages.setResult(giftList);
        return resultPages;
    }
    
    /**
     * 
     * 获取单条赠品记录信息（可选）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#querySingleGiftInfo(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public GdsGiftRespDTO querySingleGiftInfo(GdsGiftReqDTO reqDTO) throws BusinessException {
        GdsGiftRespDTO gdsGiftRespDTO = new GdsGiftRespDTO();
        List<GdsGift> gdsGift = null;
        if(!StringUtil.isEmpty(reqDTO.getId())){
            GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
            GdsGiftCriteria.Criteria criteria = gdsGiftCriteria.createCriteria();
            criteria.andIdEqualTo(reqDTO.getId());
            if(!StringUtil.isBlank(reqDTO.getGiftStatus())){
                criteria.andGiftStatusEqualTo(reqDTO.getGiftStatus());
            }
            gdsGift = gdsGiftMapper.selectByExample(gdsGiftCriteria);
        }
        if(gdsGift!= null && gdsGift.size()>=1){
            ObjectCopyUtil.copyObjValue(gdsGift.get(0), gdsGiftRespDTO, null, false);
        }else{
            return null;
        }
        
        if(StringUtils.isNotBlank(gdsGiftRespDTO.getGiftType())){
            gdsGiftRespDTO.setGiftTypeName(BaseParamUtil.fetchParamValue("GDS_GIFT_TYPE", gdsGiftRespDTO.getGiftType()));
        }
        
        if(GdsConstants.Commons.STATUS_VALID.equals(gdsGiftRespDTO.getGiftStatus())){
            gdsGiftRespDTO.setGiftStatusName("有效");
        }else{
            gdsGiftRespDTO.setGiftStatusName("无效");
        }
        if(StringUtil.isNotEmpty(gdsGiftRespDTO.getSkuId())){
            GdsSkuInfoCriteria gdsSkuInfoCriteria = new GdsSkuInfoCriteria();
            GdsSkuInfoCriteria.Criteria skuCriteria = gdsSkuInfoCriteria.createCriteria();
            skuCriteria.andGdsIdEqualTo(gdsGiftRespDTO.getGdsId());
            skuCriteria.andIdEqualTo(gdsGiftRespDTO.getSkuId());
            List<GdsSkuInfo> skuInfo = gdsSkuInfoMapper.selectByExample(gdsSkuInfoCriteria);
            if(skuInfo != null && skuInfo.size()>0){
                gdsGiftRespDTO.setGdsName(skuInfo.get(0).getGdsName());
            }
        }
        
        return gdsGiftRespDTO;
    }
    
    private void getGdsGiftShopIdxParam(GdsGift gdsGift,GdsGiftReqDTO reqDTO){
        gdsGift.setEndTime(reqDTO.getEndTime());
        gdsGift.setGdsId(reqDTO.getGdsId());
        gdsGift.setGiftAmount(reqDTO.getGiftAmount());
        gdsGift.setGiftDesc(reqDTO.getGiftDesc());
        gdsGift.setGiftName(reqDTO.getGiftName());
        gdsGift.setGiftPic(reqDTO.getGiftPic());
        gdsGift.setGiftStatus(GdsConstants.Commons.STATUS_VALID);
        gdsGift.setGiftValid(reqDTO.getGiftAmount());
        gdsGift.setGiftValue(reqDTO.getGiftValue());
        gdsGift.setSkuId(reqDTO.getSkuId());
        gdsGift.setShopId(reqDTO.getShopId());
        gdsGift.setGiftType(reqDTO.getGiftType());
        gdsGift.setGiftSend(0L);
    }
    private void getGdsGiftShopIdxParam(GdsGiftShopIdx gdsGiftShopIdx,GdsGiftReqDTO reqDTO){
        gdsGiftShopIdx.setGiftAmount(reqDTO.getGiftAmount());
        gdsGiftShopIdx.setGiftName(reqDTO.getGiftName());
        gdsGiftShopIdx.setGiftStatus(GdsConstants.Commons.STATUS_VALID);
        gdsGiftShopIdx.setGiftValue(reqDTO.getGiftValue());
        gdsGiftShopIdx.setShopId(reqDTO.getShopId());
        gdsGiftShopIdx.setSkuId(reqDTO.getSkuId());
        gdsGiftShopIdx.setGdsId(reqDTO.getGdsId());
        gdsGiftShopIdx.setGiftId(reqDTO.getId());
        gdsGiftShopIdx.setGiftType(reqDTO.getGiftType());
    }
    
    private void getEditGdsGiftParam(GdsGift gdsGift,GdsGiftReqDTO reqDTO){
        //根据id先获取赠品的信息。主要是用于已赠量和可用量的计算
        gdsGift.setGiftName(reqDTO.getGiftName());
        gdsGift.setGiftPic(reqDTO.getGiftPic());
        gdsGift.setGiftValid(reqDTO.getGiftValid());
        gdsGift.setGiftAmount(reqDTO.getGiftAmount());
        gdsGift.setGiftValue(reqDTO.getGiftValue());
        gdsGift.setGiftDesc(reqDTO.getGiftDesc());
        gdsGift.setGiftType(reqDTO.getGiftType());
    }
    
    private void getReuestCondition(GdsGiftReqDTO reqDTO,GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria){
        GdsGiftShopIdxCriteria.Criteria idxCriteria = gdsGiftShopIdxCriteria.createCriteria();
        if(!StringUtil.isEmpty(reqDTO.getId())){
            idxCriteria.andGiftIdEqualTo(reqDTO.getId());
        }
        if(!StringUtil.isEmpty(reqDTO.getGiftName())){
            idxCriteria.andGiftNameLike("%"+reqDTO.getGiftName()+"%");
        }
        if(!StringUtil.isEmpty(reqDTO.getGiftType())){
            idxCriteria.andGiftTypeEqualTo(reqDTO.getGiftType());
        }
        idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idxCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftShopIdxCriteria.setOrderByClause("create_time desc,update_time desc");
        gdsGiftShopIdxCriteria.setLimitClauseCount(reqDTO.getPageSize());
        gdsGiftShopIdxCriteria.setLimitClauseStart(reqDTO.getStartRowIndex());
        
    }
    
    /**
     * 
     * 简单描述该方法的实现功能（赠品调增。赠品总量=已赠量+可赠量）. 
     * @see com.zengshi.ecp.goods.service.busi.interfaces.IGdsGiftSV#increaseGiftAmount(com.zengshi.ecp.goods.dubbo.dto.GdsGiftReqDTO)
     */
    @Override
    public void changeGiftAmount(GdsGiftReqDTO reqDTO) throws BusinessException {
        if(StringUtil.isNotEmpty(reqDTO.getList())){
            for(GdsGiftReqDTO gdsGiftReqDTO : reqDTO.getList()){
                packageChangeGiftAmount(gdsGiftReqDTO);
            }
        }else{
            packageChangeGiftAmount(reqDTO);
        }
    }
    
    /**
     * 
     * packagechangeGiftAmount:(封装的赠品数量变更方法). <br/> 
     * @author gxq 
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void packageChangeGiftAmount(GdsGiftReqDTO reqDTO){
      //根据id先获取赠品的信息。主要是用于已赠量和可用量的计算
        GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
        GdsGiftCriteria.Criteria giftCriteria = gdsGiftCriteria.createCriteria();
        giftCriteria.andIdEqualTo(reqDTO.getId());
        List<GdsGift> oldGiftInfo = gdsGiftMapper.selectByExample(gdsGiftCriteria);
        if(StringUtil.isEmpty(oldGiftInfo) || oldGiftInfo.size()==0){
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200005);   
        }
        //根据店铺id，赠品id获取赠品店铺索引维度的信息，
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        GdsGiftShopIdxCriteria.Criteria idCriteria = gdsGiftShopIdxCriteria.createCriteria();
        idCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idCriteria.andGiftIdEqualTo(reqDTO.getId());
        idCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        List<GdsGiftShopIdx> idxInfo = gdsGiftShopIdxMapper.selectByExample(gdsGiftShopIdxCriteria);
        if(StringUtil.isEmpty(idxInfo)){
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200005);   
        }
        //如果是调减0
        if(GdsConstants.GdsGift.DOWN.equals(reqDTO.getIdUpDown())){
            //调减
            decreaseGiftAmount(reqDTO,oldGiftInfo.get(0),idxInfo.get(0));
        }else{
            //调增
            increaseGiftAmount(reqDTO,oldGiftInfo.get(0),idxInfo.get(0));
        }
    }
    /**
     * 
     * increaseGiftAmount:(封装的调增赠品量). <br/> 
     * @author gxq 
     * @param reqDTO
     * @param oldGiftInfo
     * @param oldIdx 
     * @since JDK 1.6
     */
    public void increaseGiftAmount(GdsGiftReqDTO reqDTO,GdsGift oldGiftInfo,GdsGiftShopIdx oldIdx){
        GdsGift giftAdd = new GdsGift();
        Long updateStaff = reqDTO.getStaff().getId();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        giftAdd.setUpdateStaff(updateStaff);
        giftAdd.setUpdateTime(updateTime);
        //改变的数量
        Long changeAmount = reqDTO.getChangeAmount();
        if(StringUtil.isNotEmpty(changeAmount)){
            giftAdd.setGiftValid(oldGiftInfo.getGiftValid()+changeAmount);
            if(oldGiftInfo.getGiftSend() != null && oldGiftInfo.getGiftSend() >=changeAmount){
                giftAdd.setGiftSend(oldGiftInfo.getGiftSend()-changeAmount);
            }else{
                throw new BusinessException("赠品可用量不足，无法更新赠品库存！");
            }
         }
        GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
        GdsGiftCriteria.Criteria giftCriteria = gdsGiftCriteria.createCriteria();
        giftCriteria.andIdEqualTo(reqDTO.getId());
        giftCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftMapper.updateByExampleSelective(giftAdd, gdsGiftCriteria);
        //更新赠品店铺维度索引表
        GdsGiftShopIdx idx = new GdsGiftShopIdx();
        idx.setUpdateStaff(updateStaff);
        idx.setUpdateTime(updateTime);
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        GdsGiftShopIdxCriteria.Criteria idxCriteria = gdsGiftShopIdxCriteria.createCriteria();
        idxCriteria.andGiftIdEqualTo(reqDTO.getId());
        idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idxCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftShopIdxMapper.updateByExampleSelective(idx, gdsGiftShopIdxCriteria);
    }
    
    /**
     * 
     * decreaseGiftAmount:(封装的调减赠品量). <br/> 
     * @author gxq 
     * @param reqDTO
     * @param oldGiftInfo
     * @param oldIdx 
     * @since JDK 1.6
     */
    public void decreaseGiftAmount(GdsGiftReqDTO reqDTO,GdsGift oldGiftInfo,GdsGiftShopIdx oldIdx){
        GdsGift giftDown = new GdsGift();
        Long updateStaff = reqDTO.getStaff().getId();
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        giftDown.setUpdateStaff(updateStaff);
        giftDown.setUpdateTime(updateTime);
        //改变的数量
        Long changeAmount = reqDTO.getChangeAmount();
        if(StringUtil.isNotEmpty(changeAmount)){
            if(oldGiftInfo.getGiftValid() - changeAmount < 0 ){
                throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200006);
            }
            giftDown.setGiftValid(oldGiftInfo.getGiftValid()-changeAmount);
            if(oldGiftInfo.getGiftSend() != null){
                giftDown.setGiftSend(changeAmount+oldGiftInfo.getGiftSend());
            }else{
                giftDown.setGiftSend(changeAmount);
            }
           
        }
        GdsGiftCriteria gdsGiftCriteria = new GdsGiftCriteria();
        GdsGiftCriteria.Criteria giftCriteria = gdsGiftCriteria.createCriteria();
        giftCriteria.andIdEqualTo(reqDTO.getId());
        giftCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        giftCriteria.andGiftValidGreaterThanOrEqualTo(changeAmount);

        int count=gdsGiftMapper.updateByExampleSelective(giftDown, gdsGiftCriteria);
        if(count == 0){
            throw new BusinessException(GdsErrorConstants.GdsGift.ERROR_GOODS_GIFT_200006);
        }

        //更新赠品店铺维度索引表
        GdsGiftShopIdx idx = new GdsGiftShopIdx();
        idx.setUpdateStaff(updateStaff);
        idx.setUpdateTime(updateTime);
        GdsGiftShopIdxCriteria gdsGiftShopIdxCriteria = new GdsGiftShopIdxCriteria();
        GdsGiftShopIdxCriteria.Criteria idxCriteria = gdsGiftShopIdxCriteria.createCriteria();
        idxCriteria.andGiftIdEqualTo(reqDTO.getId());
        idxCriteria.andShopIdEqualTo(reqDTO.getShopId());
        idxCriteria.andGiftStatusEqualTo(GdsConstants.Commons.STATUS_VALID);
        gdsGiftShopIdxMapper.updateByExampleSelective(idx, gdsGiftShopIdxCriteria);
    }
}

