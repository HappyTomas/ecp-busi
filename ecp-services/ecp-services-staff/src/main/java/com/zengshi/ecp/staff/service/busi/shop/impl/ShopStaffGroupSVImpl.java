package com.zengshi.ecp.staff.service.busi.shop.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopStaff2GroupMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ShopStaffGroupMapper;
import com.zengshi.ecp.staff.dao.model.ShopInfo;
import com.zengshi.ecp.staff.dao.model.ShopStaff2Group;
import com.zengshi.ecp.staff.dao.model.ShopStaff2GroupCriteria;
import com.zengshi.ecp.staff.dao.model.ShopStaff2GroupKey;
import com.zengshi.ecp.staff.dao.model.ShopStaffGroup;
import com.zengshi.ecp.staff.dao.model.ShopStaffGroupCriteria;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopStaffGroupResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.manage.interfaces.IShopMgrSV;
import com.zengshi.ecp.staff.service.busi.shop.interfaces.IShopStaffGroupSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.zengshi.paas.utils.ThreadId;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: 店铺会员分组管理服务实现<br>
 * Date:2015年9月9日下午2:39:22  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author linby
 * @version  
 * @since JDK 1.6
 */
public class ShopStaffGroupSVImpl extends GeneralSQLSVImpl implements IShopStaffGroupSV {
    
    private static final String MODULE = ShopStaffGroupSVImpl.class.getName();
    
    @Resource
    private ShopStaffGroupMapper shopStaffGroupMapper; //店铺会员分组
    
    @Resource
    private ShopStaff2GroupMapper shopStaffGroupRelMapper; //店铺会员分组关系
    
    @Resource
    private IShopMgrSV shopMgrSV; //店铺信息管理
    
    @Resource(name = "seq_shop_staff_group_id")
    private Sequence seqShopStaffGroup; //店铺会员分组id sequence

    @Override
    public long saveShopStaffGroup(ShopStaffGroup save) throws BusinessException {
        save.setId(seqShopStaffGroup.nextValue());
        if(StringUtil.isBlank(save.getStatus())){// 缺省为“有效”
            save.setStatus(StaffConstants.ShopStaffGroup.STATUS_ACTIVE);
        }
        save.setCreateTime(new Timestamp(System.currentTimeMillis()));
        try {
            shopStaffGroupMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据新增异常", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "店铺会员分组新增" });
        }
        return save.getId();
    }

    @Override
    public ShopStaffGroup findShopStaffGroup(ShopStaffGroup find) throws BusinessException {
        ShopStaffGroup res = null;
        ShopStaffGroupCriteria sCriteria = new ShopStaffGroupCriteria();
        ShopStaffGroupCriteria.Criteria sql = sCriteria.createCriteria();
        if(StringUtil.isNotBlank(find.getStatus())){//缺省查有效
            sql.andStatusEqualTo(find.getStatus());
        }else{
            sql.andStatusEqualTo(StaffConstants.ShopStaffGroup.STATUS_ACTIVE);
        }
        if(find.getId()!=null){
            sql.andIdEqualTo(find.getId());
        }
        if(find.getShopId()!=null){
            sql.andShopIdEqualTo(find.getShopId());
        }
        
        
        try {
            List<ShopStaffGroup> ls = shopStaffGroupMapper.selectByExample(sCriteria);
            if(CollectionUtils.isNotEmpty(ls)){
                res = ls.get(0);
            }
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据查询异常", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "店铺会员分组查询" });
        }
        
        return res;
    }

    @Override
    public void saveShopStaff2Group(ShopStaff2Group save) throws BusinessException {
        save.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(StringUtil.isBlank(save.getStatus())){
            save.setStatus(StaffConstants.ShopStaffGroup.REL_STATUS_ACTIVE);
        }
        try {
            shopStaffGroupRelMapper.insertSelective(save);
        } catch (DataAccessException e) {
            LogUtil.error(MODULE, "数据新增异常", e);
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR, new String[]{ "店铺会员分组关系新增" });
        }
    }

    @Override
    public PageResponseDTO<ShopStaffGroupResDTO> listShopStaffGroup(ShopStaffGroupReqDTO reqDto)
            throws BusinessException {
        if(reqDto==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        PageResponseDTO<ShopStaffGroupResDTO> res = new PageResponseDTO<ShopStaffGroupResDTO>();
        ShopStaffGroupCriteria criteria = new ShopStaffGroupCriteria();
        ShopStaffGroupCriteria.Criteria sql = criteria.createCriteria();
        if(StringUtil.isNotBlank(reqDto.getStatus())){// 缺省查询“有效”记录
            sql.andStatusEqualTo(reqDto.getStatus());
        }else{
            sql.andStatusEqualTo(StaffConstants.ShopStaffGroup.STATUS_ACTIVE);
        }
        if(reqDto.getAdaptId()!=null){
            sql.andAdaptIdEqualTo(reqDto.getAdaptId());
        }
        if(StringUtil.isNotBlank(reqDto.getGroupName())){
            sql.andGroupNameLike("%"+reqDto.getGroupName()+"%");
        }
        if(reqDto.getShopId()!=null){
            sql.andShopIdEqualTo(reqDto.getShopId());
        }
        
        criteria.setLimitClauseCount(reqDto.getPageSize());
        criteria.setLimitClauseStart(reqDto.getStartRowIndex());
        
        res = super.queryByPagination(reqDto, criteria, true, new PaginationCallback<ShopStaffGroup, ShopStaffGroupResDTO>() {

            @Override
            public List<ShopStaffGroup> queryDB(BaseCriteria criteria) {
                return shopStaffGroupMapper.selectByExample((ShopStaffGroupCriteria) criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return shopStaffGroupMapper.countByExample((ShopStaffGroupCriteria) criteria);
            }

            @Override
            public ShopStaffGroupResDTO warpReturnObject(ShopStaffGroup t) {
                ShopStaffGroupResDTO dto = new ShopStaffGroupResDTO();
                ObjectCopyUtil.copyObjValue(t, dto, null, true);
                // 店铺名
                ShopInfo si = shopMgrSV.findShopByShopID(t.getShopId());
                dto.setShopName(si!=null&&StringUtil.isNotBlank(si.getShopFullName())
                        ?si.getShopFullName():"佚名店铺");
                // 会员数
                dto.setStaffCount(countShopStaffGroupRelatedStaff(t.getId()));
                return dto;
            }
            
        });
        
        return res;
    }

    /**
     * 
     * countShopStaffGroupRelatedStaff:(通过分组id统计店铺会员分组关系). <br/> 
     * 
     * @author linby 
     * @param groupId
     * @return 
     * @since JDK 1.6
     */
    private Long countShopStaffGroupRelatedStaff(Long groupId){
        Long res = 0L;
        ShopStaff2GroupCriteria relCriteria = new ShopStaff2GroupCriteria();
        relCriteria.createCriteria().andStatusEqualTo(StaffConstants.ShopStaffGroup.REL_STATUS_ACTIVE)
            .andGroupIdEqualTo(groupId);
        try {
            res = shopStaffGroupRelMapper.countByExample(relCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];通过分组id统计店铺会员分组关系出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return res;
    }
    
    @Override
    public ShopStaff2Group findShopStaff2GroupByKey(ShopStaff2GroupKey key)
            throws BusinessException {
        ShopStaff2Group res = null;
        try {
            res = shopStaffGroupRelMapper.selectByPrimaryKey(key);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];查询店铺会员分组关系出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR);
        }
        return res;
    }

    @Override
    public void updateShopStaffGroup(ShopStaffGroup update) throws BusinessException {
        if(update==null||update.getId()==null||update.getShopId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
        update.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
        ShopStaffGroupCriteria sCriteria = new ShopStaffGroupCriteria();
        ShopStaffGroupCriteria.Criteria sql = sCriteria.createCriteria();
        sql.andIdEqualTo(update.getId());
        sql.andShopIdEqualTo(update.getShopId());
        
        try {
            shopStaffGroupMapper.updateByExampleSelective(update, sCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];修改店铺会员分组出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
    }

    @Override
    public void deleteShopStaffGroup(ShopStaffGroup delete) throws BusinessException {
        if(delete==null||delete.getId()==null||delete.getShopId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR);
        }
      //1.逻辑删除分组
        try {
            ShopStaffGroup group = new ShopStaffGroup();
            group.setStatus(StaffConstants.ShopStaffGroup.STATUS_INVALID);
            
            ShopStaffGroupCriteria sCriteria = new ShopStaffGroupCriteria();
            ShopStaffGroupCriteria.Criteria sql = sCriteria.createCriteria();
            sql.andIdEqualTo(delete.getId());
            sql.andShopIdEqualTo(delete.getShopId());
            
            shopStaffGroupMapper.updateByExampleSelective(group, sCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];逻辑删除店铺会员分组出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "店铺会员分组逻辑删除" });
        }
        //2.逻辑删除分组关系
        try {
            ShopStaff2Group groupRel = new ShopStaff2Group();
            groupRel.setStatus(StaffConstants.ShopStaffGroup.REL_STATUS_INVALID);
            
            ShopStaff2GroupCriteria ssCriteria = new ShopStaff2GroupCriteria();
            ShopStaff2GroupCriteria.Criteria sql = ssCriteria.createCriteria();
            sql.andGroupIdEqualTo(delete.getId());
            
            shopStaffGroupRelMapper.updateByExampleSelective(groupRel, ssCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];逻辑删除店铺会员分组关系出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "店铺会员分组关系逻辑删除" });
        }
        /*//1.删除分组
        try {
            ShopStaffGroupCriteria sCriteria = new ShopStaffGroupCriteria();
            ShopStaffGroupCriteria.Criteria sql = sCriteria.createCriteria();
            sql.andIdEqualTo(delete.getId());
            sql.andShopIdEqualTo(delete.getShopId());
            shopStaffGroupMapper.deleteByExample(sCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];删除店铺会员分组出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "店铺会员分组" });
        }
        //2.删除分组关系
        try {
            ShopStaff2GroupCriteria ssCriteria = new ShopStaff2GroupCriteria();
            ShopStaff2GroupCriteria.Criteria sql = ssCriteria.createCriteria();
            sql.andGroupIdEqualTo(delete.getId());
            shopStaffGroupRelMapper.deleteByExample(ssCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];删除店铺会员分组关系出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_DELETE_ERROR, new String[]{ "店铺会员分组关系" });
        }*/
    }

    @Override
    public String queryShopStaffGroup(ShopStaffGroupReqDTO reqDto) throws BusinessException {
        if(reqDto==null||reqDto.getShopId()==null||reqDto.getStaffId()==null){
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String[]{"参数异常"});
        }
        StringBuilder res = new StringBuilder();
        //1.查询店铺分组
        ShopStaffGroupCriteria sCriteria = new ShopStaffGroupCriteria();
        sCriteria.createCriteria().andStatusEqualTo(StaffConstants.ShopStaffGroup.STATUS_ACTIVE)
            .andShopIdEqualTo(reqDto.getShopId());
        List<ShopStaffGroup> listGroup = null;
        try {
            listGroup = shopStaffGroupMapper.selectByExample(sCriteria);
        } catch (DataAccessException e) {//log
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];店铺会员分组查询出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "店铺会员分组" });
        }
        if(CollectionUtils.isEmpty(listGroup)){//店铺不存在店铺会员分组，返回空
            return "";
        }
        List<Long> listShopId = new ArrayList<Long>();
        for (ShopStaffGroup shopStaffGroup : listGroup) {
            listShopId.add(shopStaffGroup.getId());
        }
        //2.确定分组与会员关系
        ShopStaff2GroupCriteria relCriteria = new ShopStaff2GroupCriteria();
        relCriteria.createCriteria().andStatusEqualTo(StaffConstants.ShopStaffGroup.REL_STATUS_ACTIVE)
            .andStaffIdEqualTo(reqDto.getStaffId()).andGroupIdIn(listShopId);
        List<ShopStaff2Group> listRel = null;
        try {
            listRel = shopStaffGroupRelMapper.selectByExample(relCriteria);
        } catch (DataAccessException e) {
            LogUtil.info(MODULE, "ThreadId:["+ThreadId.getThreadId()+"];店铺会员分组关系查询出现异常：", e);
            throw new BusinessException(StaffConstants.STAFF_SELECT_ERROR, new String[]{ "店铺会员分组关系" });
        }
        if(CollectionUtils.isEmpty(listRel)){//店铺会员分组关系不存在，返回空
            return "";
        }
        for (ShopStaff2Group shopStaff2Group : listRel) {
            res.append(",").append(shopStaff2Group.getGroupId());
        }
        return res.substring(1);
    }
    
    

}

