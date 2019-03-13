package com.zengshi.ecp.prom.service.busi.auth.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.drools.core.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.prom.dao.mapper.busi.PromType4ShopLogMapper;
import com.zengshi.ecp.prom.dao.mapper.busi.PromType4ShopMapper;
import com.zengshi.ecp.prom.dao.model.PromType4Shop;
import com.zengshi.ecp.prom.dao.model.PromType4ShopCriteria;
import com.zengshi.ecp.prom.dao.model.PromType4ShopCriteria.Criteria;
import com.zengshi.ecp.prom.dao.model.PromType4ShopLog;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.dto.PromType4ShopResponseDTO;
import com.zengshi.ecp.prom.dubbo.dto.QueryPromType4ShopDTO;
import com.zengshi.ecp.prom.dubbo.impl.converter.Converter;
import com.zengshi.ecp.prom.dubbo.util.PromConstants;
import com.zengshi.ecp.prom.service.busi.auth.interfaces.IPromType4ShopSV;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ResourceMsgUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-8-5 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class PromType4ShopSVImpl extends GeneralSQLSVImpl implements IPromType4ShopSV {

    private static final String MODULE = PromType4ShopSVImpl.class.getName();

    @Resource
    private PromType4ShopMapper promType4ShopMapper;

    @Resource
    private PromType4ShopLogMapper promType4ShopLogMapper;

    @Resource
    private Converter<PromType4Shop, PromType4ShopResponseDTO> promType4ShopResponseDTOConverter;

    @Resource
    private Converter<PromType4ShopDTO, PromType4Shop> promType4ShopConverter;

    @Resource(name = "seq_prom_type4shop_id")
    private PaasSequence seq_prom_type4shop_id;

    @Resource(name = "seq_prom_type4shop_log_id")
    private PaasSequence seq_prom_type4shop_log_id;

    /**
     * TODO促销授权店铺 保存验证 如果验证不通过 返回list有值 并且包含不通过原因说明
     * 
     * @see com.zengshi.ecp.prom.service.busi.auth.interfaces.IPromType4ShopSV#validPromType4Shop(java.util.List)
     * @param promType4ShopDTOList
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromType4ShopDTO> validPromType4Shop(List<PromType4ShopDTO> promType4ShopDTOList)
            throws BusinessException {

        boolean validValue = true;// 定义验证是否存在 无效状态
        if (!CollectionUtils.isEmpty(promType4ShopDTOList)) {

            QueryPromType4ShopDTO queryPromType4ShopDTO = new QueryPromType4ShopDTO();
            for (PromType4ShopDTO promType4ShopDTO : promType4ShopDTOList) {
                queryPromType4ShopDTO.setPromTypeCode(promType4ShopDTO.getPromTypeCode());
                queryPromType4ShopDTO.setShopId(promType4ShopDTO.getShopId());
                queryPromType4ShopDTO.setStatus(PromConstants.PromType4Shop.STATUS_1);
               // queryPromType4ShopDTO.setStartTime(promType4ShopDTO.getStartTime());
               // queryPromType4ShopDTO.setEndTime(promType4ShopDTO.getEndTime());
                List<PromType4ShopResponseDTO> l = this
                        .queryPromType4ShopList(queryPromType4ShopDTO);

                if (!CollectionUtils.isEmpty(l)) {
                    for(PromType4ShopResponseDTO dto:l){
                        if(dto.getStartTime().compareTo(promType4ShopDTO.getStartTime())<=0 || dto.getEndTime().compareTo(promType4ShopDTO.getEndTime())>=0){
                                validValue = false;
                                String[] keys=new String[3];
                                keys[0]=DateUtil.getDateString(new Timestamp(dto.getStartTime().getTime()), DateUtil.DATE_FORMAT);
                                keys[1]=DateUtil.getDateString(new Timestamp(dto.getEndTime().getTime()), DateUtil.DATE_FORMAT);
                                keys[2]=dto.getPromTypeName();
                                promType4ShopDTO.setValidErrorStr(ResourceMsgUtil.getMessage("prom.400084", keys));
                                break;
                              /*  promType4ShopDTO.setValidErrorStr("已经存在有效的授权,授权时间 " +  DateUtil.getDateString(new Timestamp(l.get(0).getStartTime().getTime()), DateUtil.DATE_FORMAT)
                                        + " 到 " +  DateUtil.getDateString(new Timestamp(l.get(0).getEndTime().getTime()), DateUtil.DATE_FORMAT));*/
                            }
                    }
                 
                }

            }
        }
        // 存在有效的授权 返回list 否则返回null
        if (!validValue) {
            return promType4ShopDTOList;
        }
        return null;
    }

    /**
     * 促销授权店铺 保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void savePromType4Shop(List<PromType4ShopDTO> promType4ShopDTOList)
            throws BusinessException {

        if (!CollectionUtils.isEmpty(promType4ShopDTOList)) {
            for (PromType4ShopDTO promType4ShopDTO : promType4ShopDTOList) {
                //状态初始化
                if(StringUtils.isEmpty(promType4ShopDTO.getStatus())){
                    promType4ShopDTO.setStatus(PromConstants.PromType4Shop.STATUS_1);
                }
                //操作用户
                if(promType4ShopDTO.getCreateStaff()==null){
                    promType4ShopDTO.setCreateStaff(promType4ShopDTO.getStaff().getId());
                }
                if(promType4ShopDTO.getCreateTime()==null){
                    promType4ShopDTO.setCreateTime(DateUtil.getSysDate());
                }
                promType4ShopDTO.setId(seq_prom_type4shop_id.nextValue());
                PromType4Shop record = promType4ShopConverter.convert(promType4ShopDTO);
                promType4ShopMapper.insert(record);
                // insert log
                insertPromType4ShopLog(promType4ShopDTO);
            }
        }
    }

    /**
     * 促销授权店铺 编辑保存
     * 
     * @param promGroupDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void updatePromType4ShopById(PromType4ShopDTO promType4ShopDTO) throws BusinessException {

        if (promType4ShopDTO.getId() == null) {
            // throw new BusinessException("参数ID 必需要有值！");
            throw new BusinessException("prom.400062");
        }

        // 组织验证参数
        QueryPromType4ShopDTO queryPromType4ShopDTO = new QueryPromType4ShopDTO();
        queryPromType4ShopDTO.setPromTypeCode(promType4ShopDTO.getPromTypeCode());
        queryPromType4ShopDTO.setShopId(promType4ShopDTO.getShopId());
        queryPromType4ShopDTO.setStatus(PromConstants.PromType4Shop.STATUS_1);
        //queryPromType4ShopDTO.setStartTime(promType4ShopDTO.getStartTime());
        //queryPromType4ShopDTO.setEndTime(promType4ShopDTO.getEndTime());

        // 取出授权列表（包含当前修改数据）
        List<PromType4ShopResponseDTO> l = this.queryPromType4ShopList(queryPromType4ShopDTO);
        // 验证
        if (!CollectionUtils.isEmpty(l)) {
            for (PromType4ShopResponseDTO dto : l) {
                if (dto.getId().longValue()==promType4ShopDTO.getId().longValue()) {
                
                }else{
                    // "已经存在有效的授权,授权时间 "+l.get(0).getStartTime()+" 到 "+l.get(0).getEndTime();
                    if(dto.getStartTime().compareTo(promType4ShopDTO.getStartTime())<=0 || dto.getEndTime().compareTo(promType4ShopDTO.getEndTime())>=0){
                            String[] times = new String[2];
                            times[0] = DateUtil.getDateString(new Timestamp(l.get(0).getStartTime().getTime()), DateUtil.DATE_FORMAT);
                            times[1] = DateUtil.getDateString(new Timestamp(l.get(0).getEndTime().getTime()), DateUtil.DATE_FORMAT);
                            throw new BusinessException("prom.400081", times);
                    }
                }
            }
        }
        // 验证通过
        PromType4Shop record = promType4ShopConverter.convert(promType4ShopDTO);
        
        //操作用户
        if(promType4ShopDTO.getCreateStaff()==null){
            promType4ShopDTO.setCreateStaff(promType4ShopDTO.getStaff().getId());
        }
        if(promType4ShopDTO.getCreateTime()==null){
            promType4ShopDTO.setCreateTime(DateUtil.getSysDate());
        }
        // insert log
        insertPromType4ShopLog(promType4ShopDTO);
        
        //操作用户
        if(record.getUpdateStaff()==null){
            record.setUpdateStaff(promType4ShopDTO.getStaff().getId());
        }
        if(record.getUpdateTime()==null){
            record.setUpdateTime(DateUtil.getSysDate());
        }

        promType4ShopMapper.updateByPrimaryKeySelective(record);

    }

    /**
     * 编辑 状态
     * 
     * @param id
     * @param status
     * @throws BusinessException
     * @author huangjx
     */
    public void updateStatus(PromType4ShopDTO promType4ShopDTO) throws BusinessException {

        
        //操作用户
        if(promType4ShopDTO.getCreateStaff()==null){
            promType4ShopDTO.setCreateStaff(promType4ShopDTO.getStaff().getId());
        }
        if(promType4ShopDTO.getCreateTime()==null){
            promType4ShopDTO.setCreateTime(DateUtil.getSysDate());
        }
        
        // insert log
        insertPromType4ShopLog(promType4ShopDTO);
        // 变更参数组织
        PromType4Shop record = new PromType4Shop();
        record.setId(promType4ShopDTO.getId());
        record.setStatus(promType4ShopDTO.getStatus());
        
        //操作用户
        if(record.getUpdateStaff()==null){
            record.setUpdateStaff(promType4ShopDTO.getStaff().getId());
        }
        if(record.getUpdateTime()==null){
            record.setUpdateTime(DateUtil.getSysDate());
        }
      /*  record.setUpdateStaff(promType4ShopDTO.getUpdateStaff());
        record.setUpdateTime(new Timestamp(promType4ShopDTO.getUpdateTime().getTime()));*/
        // 变更
        promType4ShopMapper.updateByPrimaryKeySelective(record);

    }

    /**
     * 促销类型 授权店铺列表 分页
     * 
     * @param queryPromType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<PromType4ShopResponseDTO> queryPromType4ShopForPage(
            QueryPromType4ShopDTO queryPromType4ShopDTO) throws BusinessException {

        PromType4ShopCriteria example = new PromType4ShopCriteria();
        //初始化查询页数 数量
        example.setLimitClauseCount(queryPromType4ShopDTO.getPageSize());
        example.setLimitClauseStart(queryPromType4ShopDTO.getStartRowIndex());
        //排序
        example.setOrderByClause("id desc");
        //初始化查询条件
        this.initPromType4ShopParm(queryPromType4ShopDTO, example);
        //返回查询分页结果集
        return super.queryByPagination(queryPromType4ShopDTO, example, true,
                new PaginationCallback<PromType4Shop, PromType4ShopResponseDTO>() {
                    // 查询记录集
                    @Override
                    public List<PromType4Shop> queryDB(BaseCriteria example) {

                        return promType4ShopMapper
                                .selectByExample((PromType4ShopCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return promType4ShopMapper.countByExample((PromType4ShopCriteria) example);
                    }

                    // 可以不定义
                    /*
                     * @Override public List<Comparator<DemoLog>> defineComparators() {
                     * List<Comparator<DemoLog>> ls=new ArrayList<Comparator<DemoLog>>(); ls.add(new
                     * Comparator<DemoLog>(){
                     * 
                     * @Override public int compare(DemoLog o1, DemoLog o2) { return
                     * o1.getLogId()>o2.getLogId()?1:-1; }
                     * 
                     * }); return ls; }
                     */
                    // 查询结果转换
                    @Override
                    public PromType4ShopResponseDTO warpReturnObject(PromType4Shop t) {
                        //PromType4ShopResponseDTO dto = new PromType4ShopResponseDTO();
                        //BeanUtils.copyProperties(t, dto);
                        //return dto;
                        return promType4ShopResponseDTOConverter.convert(t);
                    }
                });
    }

    /**
     * 促销类型 授权店铺列表
     * 
     * @param queryPromType4ShopDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<PromType4ShopResponseDTO> queryPromType4ShopList(
            QueryPromType4ShopDTO queryPromType4ShopDTO) throws BusinessException {

        PromType4ShopCriteria example = new PromType4ShopCriteria();
        this.initPromType4ShopParm(queryPromType4ShopDTO, example);

        example.setOrderByClause(" id asc ");
        List<PromType4Shop> list = promType4ShopMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<PromType4ShopResponseDTO> reList = new ArrayList<PromType4ShopResponseDTO>();
        for (PromType4Shop promType4Shop : list) {
            reList.add(promType4ShopResponseDTOConverter.convert(promType4Shop));
        }

        return reList;
    }

    /**
     * 根据id活动 授权店铺信息
     * 
     * @param id
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PromType4ShopResponseDTO queryPromType4ShopById(Long id) throws BusinessException {
        return promType4ShopResponseDTOConverter
                .convert(promType4ShopMapper.selectByPrimaryKey(id));
    }

    /**
     * 组织查询条件
     * 
     * @param queryPromType4ShopDTO
     * @param example
     * @author huangjx
     */
    private void initPromType4ShopParm(QueryPromType4ShopDTO queryPromType4ShopDTO,
            PromType4ShopCriteria example) {
        if (queryPromType4ShopDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getStatus())) {
            cr.andStatusEqualTo(queryPromType4ShopDTO.getStatus());
        }
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getPromTypeCode())) {
            cr.andPromTypeCodeEqualTo(queryPromType4ShopDTO.getPromTypeCode());
        }
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getShopId())) {
            cr.andShopIdEqualTo(queryPromType4ShopDTO.getShopId());
        }
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getId())) {
            cr.andIdEqualTo(queryPromType4ShopDTO.getId());
        }

        // 系统传入
        if (queryPromType4ShopDTO.getEffectiveDate() != null) {
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(queryPromType4ShopDTO
                    .getEffectiveDate().getTime()));
        }else{
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(DateUtil.getSysDate().getTime()));
        }
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getStartTime())) {
            cr.andStartTimeLessThanOrEqualTo(new Timestamp(queryPromType4ShopDTO.getStartTime().getTime()));
        }
        if (!StringUtil.isEmpty(queryPromType4ShopDTO.getEndTime())) {
            cr.andEndTimeGreaterThanOrEqualTo(new Timestamp(queryPromType4ShopDTO.getEndTime().getTime()));
        }
    }

    /**
     * 变更log记录
     * 
     * @param promType4ShopDTO
     * @throws BusinessException
     * @author huangjx
     */
    public void insertPromType4ShopLog(PromType4ShopDTO promType4ShopDTO) throws BusinessException {

        // 根据id获得 record
        PromType4Shop promType4Shop = promType4ShopMapper.selectByPrimaryKey(promType4ShopDTO
                .getId());
        // insert t_prom_type4shop_log
        PromType4ShopLog promType4ShopLog = new PromType4ShopLog();
        BeanUtils.copyProperties(promType4Shop, promType4ShopLog);

        // create staff 非空
        if (promType4ShopDTO.getCreateStaff() != null) {
            promType4ShopLog.setCreateStaffLog(promType4ShopDTO.getCreateStaff());
        }
        // update staff 非空
        if (promType4ShopDTO.getUpdateStaff() != null) {
            promType4ShopLog.setCreateStaffLog(promType4ShopDTO.getUpdateStaff());
        }

        // 系统时间
        promType4ShopLog.setCreateTimeLog(DateUtil.getSysDate());

        promType4ShopLog.setId(seq_prom_type4shop_log_id.nextValue());

        promType4ShopLog.setType4shopId(promType4ShopDTO.getId());

        promType4ShopLogMapper.insert(promType4ShopLog);
    }
}
