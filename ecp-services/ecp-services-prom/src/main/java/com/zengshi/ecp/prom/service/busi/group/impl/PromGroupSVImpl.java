package com.zengshi.ecp.prom.service.busi.group.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.drools.core.util.StringUtils;
import org.springframework.util.CollectionUtils;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.prom.dao.mapper.busi.PromGroupMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromInfoMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.manual.PromInfoManualMapper;
import com.zengshi.ecp.prom.dao.model.PromGroup;
import com.zengshi.ecp.prom.dao.model.PromGroupCriteria;
import com.zengshi.ecp.prom.dao.model.PromInfoCriteria;
import com.zengshi.ecp.prom.dao.model.PromGroupCriteria.Criteria;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromGroupResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromInfoResponseDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.CheckPageNull;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.group.interfaces.IPromGroupSV;
import com.zengshi.ecp.prom.service.busi.interfaces.IPromInfoSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromGroupSVImpl extends GeneralSQLSVImpl implements IPromGroupSV {

    private static final String MODULE = PromGroupSVImpl.class.getName();

    @Resource
    private IPromInfoSV promInfoSV;

    @Resource
    private PromGroupMapper promGroupMapper;
    
    @Resource
    private PromInfoManualMapper promInfoManualMapper;
    
    @Resource
    private PromInfoMapper promInfoMapper;

    @Resource
    private Converter<PromGroupDTO, PromGroup> promGroupConverter;

    @Resource
    private Converter<PromGroup, PromGroupResponseDTO> promGroupResponseDTOConverter;

    @Resource(name = "seq_prom_group_id")
    private PaasSequence seq_prom_group_id;

    /**
     * 主题促销保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromGroup(PromGroupDTO promGroupDTO) throws BusinessException {
        promGroupDTO.setId(seq_prom_group_id.nextValue());
        promGroupMapper.insert(promGroupConverter.convert(promGroupDTO));
        
    }

    /**
     * 主题促销编辑保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void saveEditPromGroup(PromGroupDTO promGroupDTO) throws BusinessException {

        if (promGroupDTO.getId() == null) {
            throw new BusinessException("prom.400076");
        }
        PromGroup record = promGroupConverter.convert(promGroupDTO);
        promGroupMapper.updateByPrimaryKeySelective(record);
        
    }
 
    /**
     * 获得主题促销列表 ifCalShopCnt 非空表示需要计算参与店铺数量
     * @param promGroupDTO
     * @param ifCalShopCnt
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromGroupResponseDTO> queryPromGroupForPage(PromGroupDTO promGroupDTO,String ifCalShopCnt)
            throws BusinessException {

        PromGroupCriteria example = new PromGroupCriteria();
        //初始化查询条件
        this.initPromGroupParm(promGroupDTO, example);
        
        example.setLimitClauseCount(promGroupDTO.getPageSize());
        example.setLimitClauseStart(promGroupDTO.getStartRowIndex());
        example.setOrderByClause(" id desc ");
        
        PageResponseDTO<PromGroupResponseDTO> page = null;
        // 返回查询分页结果集
        page= super.queryByPagination(promGroupDTO, example, true,
                new PaginationCallback<PromGroup, PromGroupResponseDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromGroup> queryDB(BaseCriteria example) {

                        return promGroupMapper.selectByExample((PromGroupCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promGroupMapper.countByExample((PromGroupCriteria) example);
                    }
                    // 查询结果转换
                    @Override
                    public PromGroupResponseDTO warpReturnObject(PromGroup t) {
                        return promGroupResponseDTOConverter.convert(t);
                    }
                });
        
        //标记非空 需要计算店铺参与数量
        if(!StringUtils.isEmpty(ifCalShopCnt)){
              if( CheckPageNull.checkPageNull(page)){
                  for(PromGroupResponseDTO promGroupResponseDTO:page.getResult()){
                      //计算店铺参与数量
                        //暂时没有此需求 先屏蔽
                        //promGroupResponseDTO.setShopCnt(this.queryCountByGroupId(promGroupResponseDTO.getId()));
                        if(StringUtil.isEmpty(promGroupResponseDTO.getShopCnt())){
                            promGroupResponseDTO.setShopCnt(Long.valueOf(0));
                        }
                        promGroupResponseDTO.setPromCnt(this.queryPromCntByGroupId(promGroupResponseDTO.getId()));
                        if(StringUtil.isEmpty(promGroupResponseDTO.getPromCnt())){
                            promGroupResponseDTO.setPromCnt(Long.valueOf(0));
                        }
                        
                  }
           }
        }
        
       return page;
    }

    /**
     * 获得主题促销列表 ifCalShopCnt 非空表示需要计算参与店铺数量
     * @param promGroupDTO
     * @param ifCalShopCnt
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromGroupResponseDTO> queryPromGroupForList(PromGroupDTO promGroupDTO,String ifCalShopCnt) throws BusinessException{

        PromGroupCriteria example = new PromGroupCriteria();
        this.initPromGroupParm(promGroupDTO, example);

        example.setOrderByClause(" id desc ");
        
        List<PromGroup> promGroupList = promGroupMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(promGroupList)) {
            return null;
        }

        List<PromGroupResponseDTO> reList = new ArrayList<PromGroupResponseDTO>();

        PromGroupResponseDTO promGroupResponseDTO = new PromGroupResponseDTO();

        for (PromGroup promGroup : promGroupList) {
            promGroupResponseDTO = promGroupResponseDTOConverter.convert(promGroup);
             //统计促销店铺数量
            if(!StringUtils.isEmpty(ifCalShopCnt)){
               promGroupResponseDTO.setShopCnt(this.queryCountByGroupId(promGroup.getId()));
            }
            reList.add(promGroupResponseDTO);
        }

        return reList;
    
    }
    /**
     * 返回促销组参与店铺数量
     * 
     * @param groupId
     * @return
     * @author huangjx
     */
    private Long queryCountByGroupId(Long groupId) {
        
        //select count(distinct shop_id) from t_prom_info where group_id=?; 不支持 但是可以自己写mapper.xml 和接口
        //初始化返回变量值
    /*    Long reValue = new Long(0);
        
        //初始化查询条件
        PromInfoDTO promInfoDTO = new PromInfoDTO();
        promInfoDTO.setGroupId(groupId);
        //查询列表
        List<PromInfoResponseDTO> promInfoResponseDTOList = promInfoSV
                .queryPromInfoList(promInfoDTO);
        
        //为空返回0
        if (CollectionUtils.isEmpty(promInfoResponseDTOList)) {
            return reValue;
        }
        
        Set<Long> shopIdSet=new HashSet<Long>();
        //重复值过滤
        for(PromInfoResponseDTO promInfoResponseDTO:promInfoResponseDTOList){
            shopIdSet.add(promInfoResponseDTO.getShopId());
        }
        //返回过滤后数值
        reValue=new Long(shopIdSet.size());
        return reValue;*/
        //手工定义xml 配置文件 并实现接口
        PromInfoCriteria example=new PromInfoCriteria();
        PromInfoCriteria.Criteria cr=example.createCriteria();
        cr.andGroupIdEqualTo(groupId);
        
        return promInfoManualMapper.countShopIdByGoupyId(example);

    }
    /**
     * 返回促销数量
     * 
     * @param groupId
     * @return
     * @author huangjx
     */
    private Long queryPromCntByGroupId(Long groupId) {
        
        PromInfoCriteria example=new PromInfoCriteria();
        PromInfoCriteria.Criteria cr=example.createCriteria();
        cr.andGroupIdEqualTo(groupId);
        cr.andStatusNotEqualTo(PromConstants.PromInfo.STATUS_60);
        
        return promInfoMapper.countByExample(example);

    }
    /**
     * 获得主题组对象
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromGroupResponseDTO queryPromGroupById(Long id) throws BusinessException {

        PromGroup promGroup = promGroupMapper.selectByPrimaryKey(id);
        if (promGroup == null) {
            return null;
        }
        return promGroupResponseDTOConverter.convert(promGroup);
    }

    /**
     * 获得某个主题下 店铺参加列表
     * 
     * @param groupId
     *            必传
     * @param promInfoDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromInfoResponseDTO> queryPromGroup4Shop(Long groupId, PromInfoDTO promInfoDTO)
            throws BusinessException {
        
        if (groupId == null) {
            throw new BusinessException("prom.400061");
        }
        
        if (promInfoDTO == null) {
            promInfoDTO = new PromInfoDTO();
        }
      
        promInfoDTO.setGroupId(groupId);
        return promInfoSV.queryPromInfoForPage(promInfoDTO,"");
    }

    /**
     * 组织参数 设置查询条件
     * 
     * @param promGroupDTO
     * @param example
     * @author huangjx
     */
    private void initPromGroupParm(PromGroupDTO promGroupDTO, PromGroupCriteria example) {

        if (promGroupDTO == null) {
            return;
        }
        
        Criteria cr = example.createCriteria();
        
        this.initPromGroupCondtion(promGroupDTO,cr);
        
    }
    
    /**
     * 组织参数 设置查询条件
     * 
     * @param promGroupDTO
     * @param example
     * @author huangjx
     */
    private void initPromGroupCondtion(PromGroupDTO promGroupDTO,  Criteria cr ) {

        if (promGroupDTO == null) {
            return;
        }
        //站点
        if (promGroupDTO.getSiteId() != null) {
            cr.andSiteIdEqualTo(promGroupDTO.getSiteId());
        }
        
        if (promGroupDTO.getId() != null) {
            cr.andIdEqualTo(promGroupDTO.getId());
        }
        // 主题
        if (!StringUtil.isEmpty(promGroupDTO.getPromTheme())) {
            cr.andPromThemeLike(PromConstants.PromSys.LIKE_PERCENT+promGroupDTO.getPromTheme()+PromConstants.PromSys.LIKE_PERCENT);
        }
        // 状态
        if (!StringUtil.isEmpty(promGroupDTO.getStatus())) {
            cr.andStatusEqualTo(promGroupDTO.getStatus());
        }
        // 主题开始展示时间
        if (!StringUtil.isEmpty(promGroupDTO.getShowStartTime())) {
            cr.andShowStartTimeGreaterThanOrEqualTo(new Timestamp(promGroupDTO.getShowStartTime()
                    .getTime()));
        }
        // 主题截止展示时间
        if (!StringUtil.isEmpty(promGroupDTO.getShowEndTime())) {
            cr.andShowEndTimeLessThanOrEqualTo(new Timestamp(promGroupDTO.getShowEndTime()
                    .getTime()));
        }
        // 验证 有效性和 caldate时间 有效的数据 
        if(!StringUtil.isEmpty(promGroupDTO.getCalDate())){
            cr.andShowEndTimeGreaterThanOrEqualTo(new Timestamp(promGroupDTO.getCalDate().getTime()));
        }
    }
}
