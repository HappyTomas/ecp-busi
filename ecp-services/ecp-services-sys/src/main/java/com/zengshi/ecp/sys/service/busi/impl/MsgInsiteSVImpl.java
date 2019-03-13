/** 
 * Project Name:ecp-services-sys 
 * File Name:MsgInsiteSVImpl.java 
 * Package Name:com.zengshi.ecp.sys.service.busi.impl 
 * Date:2016年3月11日上午10:56:02 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.sys.service.busi.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.sys.dao.mapper.busi.MsgInsiteMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgDefineMapper;
import com.zengshi.ecp.sys.dao.model.MsgDefine;
import com.zengshi.ecp.sys.dao.model.MsgInsite;
import com.zengshi.ecp.sys.dao.model.MsgInsiteCriteria;
import com.zengshi.ecp.sys.dao.model.MsgInsiteCriteria.Criteria;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-sys <br>
 * Description: <br>
 * Date:2016年3月11日上午10:56:02  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author yugn
 * @version  
 * @since JDK 1.6 
 */
@Service("msgInsiteSV")
public class MsgInsiteSVImpl extends GeneralSQLSVImpl implements IMsgInsiteSV{
    
    @Resource(name="msgInsiteMapper")
    private MsgInsiteMapper msgInsiteMapper;
    
    @Resource
    private MsgDefineMapper msgDefineMapper;
    
    /** 
     *  简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV#insertMsgInsite(MsgInsite) 
     */
    @Override
    public void insertMsgInsite(MsgInsite msgInsite) {
        //保存的时候，默认设置为未读状态
        msgInsite.setReadFlag(BaseMsgConstants.SYS_MSGINSITE_UNREAD);
        msgInsite.setCreateTime(DateUtil.getSysDate());
        msgInsite.setRecTime(DateUtil.getSysDate());

        //如果原来设置的fromStaffId为空，那么这里统一写系统管理员；
        if(msgInsite.getFromStaffId() == null || msgInsite.getFromStaffId() < 1L){
            msgInsite.setFromStaffId(BaseMsgConstants.SYS_ADMIN_STAFF_ID);
        }
        
        this.msgInsiteMapper.insert(msgInsite);
    }

    @Override
    public long countMsgInsite(long staffId) {
        
        MsgInsiteCriteria criteria = new MsgInsiteCriteria();
        
        criteria.createCriteria().andStaffIdEqualTo(staffId);
        
        return this.msgInsiteMapper.countByExample(criteria);
        
    }

    /**
     * 
     *  以下方法编写者：chenyz3. 
     * @see com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV#updateMsgInsite(com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO)
     */
    @Override
    public int updateMsgInsite(MsgInsiteReqDTO req) throws BusinessException {
        MsgInsite msgInsite = new MsgInsite();
        ObjectCopyUtil.copyObjValue(req, msgInsite, null, true);
        msgInsite.setUpdateStaff(req.getStaff().getId());
        msgInsite.setUpdateTime(DateUtil.getSysDate());
        MsgInsiteCriteria criteria = new MsgInsiteCriteria();
        Criteria sql = criteria.createCriteria();
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
        	sql.andStaffIdEqualTo(req.getStaffId());
        }
        if (req.getMsgInfoId() != null && req.getMsgInfoId() != 0L) {
        	sql.andMsgInfoIdEqualTo(req.getMsgInfoId());
        }
        return msgInsiteMapper.updateByExampleSelective(msgInsite, criteria);
    }

    @Override
    public int deleteMsgInsite(MsgInsiteReqDTO req) throws BusinessException {
        MsgInsiteCriteria criteria = new MsgInsiteCriteria();
        MsgInsiteCriteria.Criteria sql = criteria.createCriteria();
        //查询条件：用户ID
        if(req.getStaffId() != null && req.getStaffId() != 0L){
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：消息ID
        if(req.getMsgInfoId() != null && req.getMsgInfoId() != 0L){
            sql.andMsgInfoIdEqualTo(req.getMsgInfoId());
        }
        return msgInsiteMapper.deleteByExample(criteria);
    }

    @Override
    public PageResponseDTO<MsgInsiteResDTO> listMsgInsite(MsgInsiteReqDTO req,List<String> codeList)
            throws BusinessException {
        PageResponseDTO<MsgInsiteResDTO> pageInfo = new PageResponseDTO<MsgInsiteResDTO>();
        MsgInsiteCriteria criteria = new MsgInsiteCriteria();
        MsgInsiteCriteria.Criteria sql = criteria.createCriteria();
        //查询条件：站内消息接收用户
        sql.andStaffIdEqualTo(req.getStaffId());
        //查询条件：消息编码
        if (CollectionUtils.isNotEmpty(codeList)) {
            sql.andMsgCodeIn(codeList);
        }
        //查询条件：已读、未读标识
        if(StringUtil.isNotBlank(req.getReadFlag())){
            sql.andReadFlagEqualTo(req.getReadFlag());
        }
        /*//查询条件：站内消息接收用户
        if(req.getStaffId() != null && req.getStaffId() != 0L){
            sql.andStaffIdEqualTo(req.getStaffId());
        }*/
        //查询条件：对接消息表的消息ID
        if(req.getMsgInfoId() != null && req.getMsgInfoId() != 0L){
            sql.andMsgInfoIdEqualTo(req.getMsgInfoId());
        }
        criteria.setLimitClauseCount(req.getPageSize());
        criteria.setLimitClauseStart(req.getStartRowIndex());
        criteria.setOrderByClause("CREATE_TIME DESC");
        pageInfo=super.queryByPagination(req, criteria, true,new PaginationCallback<MsgInsite, MsgInsiteResDTO>() {

            @Override
            public List<MsgInsite> queryDB(BaseCriteria criteria) {
                return msgInsiteMapper.selectByExample((MsgInsiteCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return msgInsiteMapper.countByExample((MsgInsiteCriteria)criteria);
            }
            
            
            @Override
            public List<Comparator<MsgInsite>> defineComparators() {
                List<Comparator<MsgInsite>> ls = new ArrayList<Comparator<MsgInsite>>();
                ls.add(new Comparator<MsgInsite>() {

                    @Override
                    public int compare(MsgInsite o1, MsgInsite o2) {
                        return o1.getCreateTime().getTime()<o2.getCreateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }

            @Override
            public MsgInsiteResDTO warpReturnObject(MsgInsite msg) {
                MsgInsiteResDTO dto = new MsgInsiteResDTO();
                ObjectCopyUtil.copyObjValue(msg, dto, null, false);
                //通过msgCode查询msgName
                if (StringUtil.isNotBlank(msg.getMsgCode())) {
                	MsgDefine define = msgDefineMapper.selectByPrimaryKey(msg.getMsgCode());
                	if (define != null) {
                		dto.setMsgName(define.getMsgName());
                	}
                }
                return dto;
            }
        });
        return pageInfo;
    }

    @Override
    public long countMsgInsiteByrole(MsgInsiteReqDTO req,List<String> codeList) {
        MsgInsiteCriteria criteria = new MsgInsiteCriteria();
        MsgInsiteCriteria.Criteria sql = criteria.createCriteria();
        //查询条件：站内消息接收用户
        sql.andStaffIdEqualTo(req.getStaffId());
        //查询条件：消息编码
        if (CollectionUtils.isNotEmpty(codeList)) {
            sql.andMsgCodeIn(codeList);
        }
        //查询条件：已读、未读标识
        if(StringUtil.isNotBlank(req.getReadFlag())){
            sql.andReadFlagEqualTo(req.getReadFlag());
        }
        return this.msgInsiteMapper.countByExample(criteria);
    }
    
    
}

