package com.zengshi.ecp.sys.service.common.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.sys.dao.mapper.busi.MsgInfoMapper;
import com.zengshi.ecp.sys.dao.mapper.common.BaseEmailMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgDefineMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgMailMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgSendMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgSiteMapper;
import com.zengshi.ecp.sys.dao.mapper.common.MsgSmsMapper;
import com.zengshi.ecp.sys.dao.model.BaseEmail;
import com.zengshi.ecp.sys.dao.model.BaseEmailCriteria;
import com.zengshi.ecp.sys.dao.model.MsgDefine;
import com.zengshi.ecp.sys.dao.model.MsgDefineCriteria;
import com.zengshi.ecp.sys.dao.model.MsgInfo;
import com.zengshi.ecp.sys.dao.model.MsgMail;
import com.zengshi.ecp.sys.dao.model.MsgSend;
import com.zengshi.ecp.sys.dao.model.MsgSendCriteria;
import com.zengshi.ecp.sys.dao.model.MsgSendKey;
import com.zengshi.ecp.sys.dao.model.MsgSite;
import com.zengshi.ecp.sys.dao.model.MsgSms;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.BaseEmailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInfoReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgMailResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSendResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSiteResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgSmsResDTO;
import com.zengshi.ecp.sys.dubbo.util.BaseMsgConstants;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff-server <br>
 * Description: 消息中心服务接口实现类<br>
 * Date:2016-2-24上午11:33:17  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl5
 * @version  
 * @since JDK 1.6
 */
@Service("msgManageSV")
public class MsgManageSVImpl extends GeneralSQLSVImpl implements IMsgManageSV{
    
    @Resource 
    private BaseEmailMapper baseEmailMapper;
    
    @Resource
    private MsgDefineMapper msgDefineMapper;
    
    @Resource
    private MsgSendMapper msgSendMapper;
    
    
    @Resource
    private MsgSmsMapper msgSmsMapper;
    
    @Resource
    private MsgMailMapper msgMailMapper;
    
    @Resource 
    private MsgSiteMapper msgSiteMapper;

    @Override
    public int saveBaseEmail(BaseEmailReqDTO req) throws BusinessException {
        /*删除数据*/
        this.deleteBaseEmail();
        /*插入新数据*/
        BaseEmail baseEmail = new BaseEmail();
        ObjectCopyUtil.copyObjValue(req, baseEmail, null, true);
        return baseEmailMapper.insertSelective(baseEmail);
    }

    @Override
    public PageResponseDTO<MsgDefineResDTO> listMsgDefine(MsgDefineReqDTO req) throws BusinessException {
        PageResponseDTO<MsgDefineResDTO> pageInfo = new PageResponseDTO<MsgDefineResDTO>();

        MsgDefineCriteria criteria = new MsgDefineCriteria();
        criteria.setOrderByClause("update_time desc");//根据更新时间倒序
        //com.zengshi.ecp.staff.dao.model.MsgDefineCriteria.Criteria sql = criteria.createCriteria();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        MsgDefineCriteria.Criteria sql = criteria.createCriteria();
        //查询条件：消息类型
        if(StringUtil.isNotBlank(req.getMsgType())){
            sql.andMsgTypeEqualTo(req.getMsgType());
        }
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<MsgDefine, MsgDefineResDTO>() {

            @Override
            public List<MsgDefine> queryDB(BaseCriteria bc) {
                return msgDefineMapper.selectByExample((MsgDefineCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return msgDefineMapper.countByExample((MsgDefineCriteria)bc);
            }

            @Override
            public List<Comparator<MsgDefine>> defineComparators() {
                List<Comparator<MsgDefine>> ls=new ArrayList<Comparator<MsgDefine>>();
                ls.add(new Comparator<MsgDefine>(){

                    @Override
                    public int compare(MsgDefine o1, MsgDefine o2) {
                        return o1.getUpdateTime().getTime()<o2.getUpdateTime().getTime()?1:-1;
                    }
                    
                });
                return ls;
            }
            @Override
            public MsgDefineResDTO warpReturnObject(MsgDefine msgDefine) {
                MsgDefineResDTO res = new MsgDefineResDTO();
                ObjectCopyUtil.copyObjValue(msgDefine, res, null, true);
                //分别查询模板对应的三种方式，是否开启
                MsgSendKey key = new MsgSendKey();
                key.setMsgCode(res.getMsgCode());
                key.setSendType(BaseMsgConstants.SEND_TYPE_INSITE);//站内短信
                res.setInsiteOnOff(MsgManageSVImpl.this.findMsgSend(key));
                key.setSendType(BaseMsgConstants.SEND_TYPE_SMS);//手机短信
                res.setSmsOnOff(MsgManageSVImpl.this.findMsgSend(key));
                key.setSendType(BaseMsgConstants.SEND_TYPE_EMAIL);//邮件
                res.setEmailOnOff(MsgManageSVImpl.this.findMsgSend(key));
                return res;
            }
        });
        return pageInfo;
    }
    
    /**
     * 
     * findMsgSend:(查询指定消息模板是否开启). <br/> 
     * 
     * @author huangxl5
     * @param key
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public String findMsgSend(MsgSendKey key) throws BusinessException {
        MsgSend msgSend = msgSendMapper.selectByPrimaryKey(key);
        if (msgSend != null) {
            return msgSend.getSendFlag();
        } else {
            return "0";
        }
    }

    @Override
    public int saveMsgSend(MsgSendReqDTO req) throws BusinessException {
        MsgSend record = new MsgSend();
        ObjectCopyUtil.copyObjValue(req, record, null, false);
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateTime(DateUtil.getSysDate());
        return msgSendMapper.insertSelective(record);
    }

    @Override
    public int updateMsgSend(MsgSendReqDTO req) throws BusinessException {
        MsgSend record = new MsgSend();
        ObjectCopyUtil.copyObjValue(req,record, null, false);
        record.setCreateTime(DateUtil.getSysDate());
        record.setUpdateTime(DateUtil.getSysDate());
        record.setMsgCode(req.getMsgCode());
        record.setSendType(req.getSendType());
        return msgSendMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateMsgSms(MsgSmsReqDTO req) throws BusinessException {
        MsgSms record = new MsgSms();
        ObjectCopyUtil.copyObjValue(req, record, null, false);
        return msgSmsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateMsgSite(MsgSiteReqDTO req) throws BusinessException {
        MsgSite record = new MsgSite();
        ObjectCopyUtil.copyObjValue(req, record, null, false);
        return msgSiteMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateMsgEmail(MsgMailReqDTO req) throws BusinessException {
        MsgMail record = new MsgMail();
        ObjectCopyUtil.copyObjValue(req, record, null, false);
        return msgMailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MsgSiteResDTO findMsgSite(MsgSiteReqDTO req) throws BusinessException {
        MsgSiteResDTO res = new MsgSiteResDTO();
        MsgSite msgSite = msgSiteMapper.selectByPrimaryKey(req.getMsgCode());
        ObjectCopyUtil.copyObjValue(msgSite, res, null, false);
        return res;
    }

    @Override
    public MsgSmsResDTO findMsgSms(MsgSmsReqDTO req) throws BusinessException {
        MsgSmsResDTO res = new MsgSmsResDTO();
        MsgSms sms = msgSmsMapper.selectByPrimaryKey(req.getMsgCode());
        ObjectCopyUtil.copyObjValue(sms, res, null, false);
        return res;
    }

    @Override
    public MsgMailResDTO findMsgMail(MsgMailReqDTO req) throws BusinessException {
        MsgMailResDTO res = new MsgMailResDTO();
        MsgMail mail = msgMailMapper.selectByPrimaryKey(req.getMsgCode());
        ObjectCopyUtil.copyObjValue(mail, res, null, false);
        return res;
    }

    @Override
    public String findMsgSend(MsgSendReqDTO req) throws BusinessException {
        MsgSendKey key = new MsgSendKey();
        key.setMsgCode(req.getMsgCode());
        key.setSendType(req.getSendType());
        return this.findMsgSend(key);
    }
    
    /**
     * 
     * deleteBaseEmail:(删除邮件服务配置表数据). <br/> 
     * 该表只会有一条记录，所以可以直接删除表
     * @author huangxl5
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    private int deleteBaseEmail() throws BusinessException {
        BaseEmailCriteria criteria = new BaseEmailCriteria();
        return baseEmailMapper.deleteByExample(criteria);
    }

    @Override
    public BaseEmailResDTO findBaseEmail() throws BusinessException {
        BaseEmailCriteria criteria = new BaseEmailCriteria();
        List<BaseEmail> list = baseEmailMapper.selectByExample(criteria);
        BaseEmailResDTO res = new BaseEmailResDTO();
        if (!CollectionUtils.isEmpty(list)) {
            ObjectCopyUtil.copyObjValue(list.get(0), res, null, false);
        } else {
            res = null;
        }
        return res;
    }

    @Override
    public List<MsgSend> fetchMsgSendByMsgCode(String msgCode) throws Exception {
        MsgSendCriteria criteria = new MsgSendCriteria();
        criteria.createCriteria().andMsgCodeEqualTo(msgCode);
        return this.msgSendMapper.selectByExample(criteria);
    }

    @Override
    public List<MsgSend> fetchActivedMsgSendByMsgCode(String msgCode) throws Exception {
        MsgSendCriteria criteria = new MsgSendCriteria();
        
        //消息编码一致，并且 发送标识 =1 ；
        criteria.createCriteria()
        .andMsgCodeEqualTo(msgCode)
        .andSendFlagEqualTo(BaseMsgConstants.SYS_MSG_SEND_ACTIVED);
        
        return this.msgSendMapper.selectByExample(criteria);
    }
    
    
    
}

