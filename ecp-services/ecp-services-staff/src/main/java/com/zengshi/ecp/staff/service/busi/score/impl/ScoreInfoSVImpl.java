package com.zengshi.ecp.staff.service.busi.score.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dao.mapper.busi.AuthStaffCIDXMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreDetailSelMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreExchangeMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreInfoMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreOptLogMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreSignCheckMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.ScoreSourceMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.manual.ScoreExchangeSumMapper;
import com.zengshi.ecp.staff.dao.mapper.busi.manual.ScoreSourceSumMapper;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDX;
import com.zengshi.ecp.staff.dao.model.AuthStaffCIDXCriteria;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreDetailSel;
import com.zengshi.ecp.staff.dao.model.ScoreDetailSelCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreExchange;
import com.zengshi.ecp.staff.dao.model.ScoreExchangeCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dao.model.ScoreInfoCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreOptLog;
import com.zengshi.ecp.staff.dao.model.ScoreSignCheck;
import com.zengshi.ecp.staff.dao.model.ScoreSource;
import com.zengshi.ecp.staff.dao.model.ScoreSourceCriteria;
import com.zengshi.ecp.staff.dao.model.ScoreSourceCriteria.Criteria;
import com.zengshi.ecp.staff.dao.model.ScoreType;
import com.zengshi.ecp.staff.dubbo.dto.ScoreClearReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreDetailSelResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreExchangeResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreInfoResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSignCheckRespDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceReqDTO;
import com.zengshi.ecp.staff.dubbo.dto.ScoreSourceResDTO;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.custinfo.interfaces.ICustInfoSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.ecp.staff.service.common.score.interfaces.IScoreTypeSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.db.sequence.Sequence;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: <br>
 * Date:2015-8-6下午3:04:43  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ScoreInfoSVImpl extends GeneralSQLSVImpl implements IScoreInfoSV{
    
    private  static final String MODULE = ScoreInfoSVImpl.class.getName();
    //积分类型：签到送积分
    private static final String SIGN_SOURCE_TYPE = "7001";
    
    @Resource
    private ScoreInfoMapper scoreInfoMapper ;
    @Resource
    private ScoreSourceMapper scoreSourceMapper;
    
    @Resource
    private ScoreOptLogMapper scoreOptLogMapper;
    
    @Resource
    private ScoreExchangeMapper scoreExchangeMapper;
    
    @Resource
    private AuthStaffCIDXMapper cidMapper;
    
    @Resource
    private ScoreSourceSumMapper scoreSourceSumMapper;
    
    @Resource
    private ScoreExchangeSumMapper scoreExchangeSumMapper;
    
    @Resource
    private ScoreDetailSelMapper scoreDetailSelMapper;
    
    @Resource
    private ScoreSignCheckMapper scoreSignCheckMapper;
    
    @Resource
    private IScoreTypeSV scoreTypeSV;
    
    
    @Resource
    private ICustInfoSV custInfoSV;
    
    @Resource(name = "seq_score_scource_id")
    private Sequence seq_scource_id;
    
    @Resource(name="seq_score_info")
    private Sequence seq_score_info; 
    
    @Resource(name="seq_score_exchange")
    private Sequence seq_score_exchange;
    
    @Resource(name="seq_score_opt_log")
    private Sequence seq_score_opt_log;
    
    @Override
    public void saveScoreInfo(ScoreInfo scoreInfo) throws BusinessException {
        try {
            scoreInfoMapper.insert(scoreInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ScoreInfo findScoreInfoById(Long id) throws BusinessException {
        return scoreInfoMapper.selectByPrimaryKey(4L);
    }

    @Override
    public PageResponseDTO<ScoreInfoResDTO> listScoreInfo(ScoreInfoReqDTO req) throws BusinessException {
        PageResponseDTO<ScoreInfoResDTO> pageInfo = new PageResponseDTO<ScoreInfoResDTO>();
        ScoreInfoCriteria criteria = new ScoreInfoCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreInfoCriteria.Criteria sql = criteria.createCriteria();
        //条件：积分账户状态
        if (StringUtil.isNotBlank(req.getStatus())) {
            sql.andStatusEqualTo(req.getStatus());
        }
        //条件：会员登录号
        if (StringUtil.isNotBlank(req.getStaffCode())) {
            AuthStaffCIDX cidx = new AuthStaffCIDX();
            AuthStaffCIDXCriteria cidCriteria = new AuthStaffCIDXCriteria();
            cidCriteria.createCriteria().andStaffCodeLike("%" + req.getStaffCode() + "%");
            cidx.setStaffCode(req.getStaffCode());
            List<AuthStaffCIDX> cidList = cidMapper.selectByExample(cidCriteria);
            if (!CollectionUtils.isEmpty(cidList)) {
                List<Long> staffIdList = new ArrayList<Long>();
                for (AuthStaffCIDX authStaffCIDX: cidList) {
                    staffIdList.add(authStaffCIDX.getStaffId());
                }
                sql.andStaffIdIn(staffIdList);
            //staffCode查不到，则放入一个不可能的条件
            } else {
                sql.andStaffIdEqualTo(0L);
            }
        }
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" CREATE_TIME DESC");
        pageInfo = super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreInfo, ScoreInfoResDTO>() {

            @Override
            public List<ScoreInfo> queryDB(BaseCriteria bc) {
                return scoreInfoMapper.selectByExample((ScoreInfoCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreInfoMapper.countByExample((ScoreInfoCriteria)bc);
            }

            @Override
            public List<Comparator<ScoreInfo>> defineComparators() {
                List<Comparator<ScoreInfo>> ls=new ArrayList<Comparator<ScoreInfo>>();
                ls.add(new Comparator<ScoreInfo>(){

                    @Override
                    public int compare(ScoreInfo o1, ScoreInfo o2) {
                    	return (int)(o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
                    }
                    
                });
                return ls;
            }
            @Override
            public ScoreInfoResDTO warpReturnObject(ScoreInfo scoreInfo) {
                ScoreInfoResDTO res = new ScoreInfoResDTO();
                if (scoreInfo != null) {
                    ObjectCopyUtil.copyObjValue(scoreInfo, res, null, true);
                    //取出会员的相关信息
                    CustInfo custInfo = custInfoSV.findCustInfoById(scoreInfo.getStaffId());
                    if (custInfo != null) {
                        res.setNickname(custInfo.getNickname());
                        res.setCustName(custInfo.getCustName());
                        res.setStaffCode(custInfo.getStaffCode());
                    }
                }
                
                return res;
            }
        });
        return pageInfo;
    }

    /**
     * 
     * TODO 根据用户ID查找用户积分账户信息. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#findScoreInfoByStaffId(java.lang.Long)
     */
    @Override
    public ScoreInfo findScoreInfoByStaffId(Long pStaffId) throws BusinessException {
        ScoreInfoCriteria criteria = new ScoreInfoCriteria();
        criteria.createCriteria().andStaffIdEqualTo(pStaffId);
        List<ScoreInfo> infoList = null;
        try {
            infoList = scoreInfoMapper.selectByExample(criteria);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CollectionUtils.isEmpty(infoList)?null:infoList.get(0);
    }
    
    /**
     * 
     * TODO 根据用户信息查找用户积分账户信息. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#findScoreInfoByCustInfo(com.zengshi.ecp.staff.dao.model.CustInfo)
     */
    public ScoreInfo findScoreInfoByCustInfo(CustInfo pCustInfo) throws BusinessException 
    {
        ScoreInfo curScoreInfo = null;
        curScoreInfo = findScoreInfoByStaffId(pCustInfo.getId());
        if(curScoreInfo == null)
        {
            LogUtil.info(MODULE, "=======该账户 ："+pCustInfo.getStaffCode()+"没有积分账户信息，新增一条记录 =======");
            curScoreInfo = createScoreInfo(pCustInfo);
        }
        return curScoreInfo;
    }

    private ScoreInfo createScoreInfo(CustInfo pCustInfo) throws BusinessException 
    {
        ScoreInfo sScoreInfo = new ScoreInfo();
        sScoreInfo.setId(seq_score_info.nextValue());
        sScoreInfo.setStaffId(pCustInfo.getId());
        sScoreInfo.setStatus(StaffConstants.Score.SCORE_STATUS_NORMAL);
        sScoreInfo.setScoreTotal(0L);
        sScoreInfo.setScoreFrozen(0L);
        sScoreInfo.setScoreUsed(0L);
        sScoreInfo.setScoreBalance(0L);
        sScoreInfo.setCreateStaff(pCustInfo.getId());
        sScoreInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
        sScoreInfo.setRemark("用户："+pCustInfo.getStaffCode()+"的积分账户");
        try {
            scoreInfoMapper.insert(sScoreInfo);
        } catch (Exception e) {
            throw new BusinessException(StaffConstants.STAFF_INSERT_ERROR);
        }
        return sScoreInfo;
    }

    /**
     * 
     * TODO 更新用户积分账户信息. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#updateScoreInfo(com.zengshi.ecp.staff.dao.model.ScoreInfo)
     */
    @Override
    public void updateScoreInfo(ScoreInfo pScoreInfo) throws BusinessException 
    {
        pScoreInfo.setUpdateTime(DateUtil.getSysDate());
        pScoreInfo.setUpdateStaff(pScoreInfo.getStaffId());
        pScoreInfo.setId(pScoreInfo.getId());
        try {
            scoreInfoMapper.updateByPrimaryKeySelective(pScoreInfo);
        } catch (Exception e) {
            throw new BusinessException(StaffConstants.STAFF_UPDATE_ERROR);
        }
    }

    @Override
    public int saveScoreOptLog(ScoreOptLog log) throws BusinessException {
        log.setId(seq_score_opt_log.nextValue());
        log.setCreateTime(DateUtil.getSysDate());
        return scoreOptLogMapper.insert(log);
    }
    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#saveScoreOptLog(com.zengshi.ecp.staff.dao.model.CustInfo, com.zengshi.ecp.staff.dao.model.ScoreInfo, com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO) 
     */
    @Override
    public int saveScoreOptLog(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult)throws BusinessException {
        
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(scoreResult.getScore());//本次操作的积分
        log.setStaffId(custInfo.getId());//用户ID
        log.setRemark(scoreResult.getRemark());//备注
        log.setOrderId(scoreResult.getOrderId());//订单
        log.setSubOrderId(scoreResult.getOrderSubId());//子订单
        log.setOptType(scoreResult.getOptType());//操作类型
        log.setScoreType(scoreResult.getScoreType());//积分类型
        log.setCreateStaff(String.valueOf(custInfo.getId()));//操作人
        log.setSiteId(scoreResult.getSiteId());
        
        this.saveScoreOptLog(log);

        return 0;
    }
    @Override
    public int saveScoreExchange(ScoreExchange exchange) throws BusinessException {
        exchange.setId(seq_score_exchange.nextValue());
        exchange.setCreateTime(DateUtil.getSysDate());
        return scoreExchangeMapper.insert(exchange);
    }

    @Override
    public void saveScoreUse(ScoreExchangeReqDTO req) throws BusinessException {
        /*1、取出会员的积分账户信息*/
        CustInfo custInfo = new CustInfo();
        custInfo.setId(req.getStaffId());//用户ID
        ScoreInfo scoreInfo = this.findScoreInfoByCustInfo(custInfo);
        
        /*2、新增积分消费记录*/
        ScoreExchange exchange = new ScoreExchange();
        ObjectCopyUtil.copyObjValue(req, exchange, null, true);
        this.saveScoreExchange(exchange);
        
        /*3、新增积分操作日志记录*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(req.getScoreUsing());//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        log.setRemark(req.getRemark());//备注
        log.setOrderId(req.getOrderId());//订单编码
        log.setSubOrderId(req.getSubOrderId());//子订单编码
        log.setOptType(req.getOptType());//操作类型
        log.setScoreType(req.getScoreType());//积分类型
        log.setScoreTypeName(req.getScoreTypeName());//积分类型名称
        log.setCreateStaff(String.valueOf(req.getCreateStaff()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        this.saveScoreOptLog(log);
        
        /*4、变更用户积分账户信息*/
        BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
        BigDecimal use = new BigDecimal(scoreInfo.getScoreUsed());
        BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
        /*4-1、如果此操作为冻结，则可用积分减少，冻结积分增加*/
        if (StaffConstants.Score.SCORE_OPT_TYPE_4.equals(req.getOptType())) {
            balance = balance.subtract(new BigDecimal(req.getScoreUsing()));//可用积分减少
            freeze = freeze.add(new BigDecimal(req.getScoreUsing()));//冻结积分增加
        /*4-2、如果此操作为支出，则可用积分减少，已使用积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_2.equals(req.getOptType())) {
            balance = balance.subtract(new BigDecimal(req.getScoreUsing()));//可用积分减少
            use = use.add(new BigDecimal(req.getScoreUsing()));//已使用积分增加
        /*4-3、如果此操作为后台调减，则可用积分减少，已使用积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_6.equals(req.getOptType())) {
            balance = balance.subtract(new BigDecimal(req.getScoreUsing()));//可用积分减少
            use = use.add(new BigDecimal(req.getScoreUsing()));//已使用积分增加
        /*4-4、如果此操作为退款，则可用积分减少，已使用积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_8.equals(req.getOptType())) {
            balance = balance.subtract(new BigDecimal(req.getScoreUsing()));//可用积分减少
            use = use.add(new BigDecimal(req.getScoreUsing()));//已使用积分增加
        }
        //可用积分不足，且积分不能扣为负时
        if (balance.compareTo(new BigDecimal(0)) == -1 && !StaffConstants.Score.SUBTRACT_BELOW_ZERO.equals(req.getFlag())) {
            throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH, new String[]{"可用"});
        }
        scoreInfo.setScoreBalance(balance.longValue());
        scoreInfo.setScoreUsed(use.longValue());
        scoreInfo.setScoreFrozen(freeze.longValue());
        scoreInfo.setUpdateStaff(req.getStaffId());
        scoreInfo.setUpdateTime(DateUtil.getSysDate());
        this.updateScoreInfo(scoreInfo);
    }

   
    @Override
    public void saveScoreAdd(ScoreSourceReqDTO req) throws BusinessException {
        /*1、取出会员的积分账户信息*/
        CustInfo custInfo = new CustInfo();
        custInfo.setId(req.getStaffId());//用户ID
        //如果不存在，则会创建
        ScoreInfo scoreInfo = this.findScoreInfoByCustInfo(custInfo);
        
        /*2、新增积分来源记录*/
        ScoreSource scoreSource = new ScoreSource();
        ObjectCopyUtil.copyObjValue(req, scoreSource, null, true);
        scoreSource.setStatus("1"); //积分来源明细状态
        scoreSource.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        scoreSource.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        scoreSource.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        scoreSource.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        scoreSource.setSiteId(req.getSiteId());//站点ID
        this.saveScoreSource(scoreSource);
        
        /*3、新增积分操作日志记录*/
        ScoreOptLog log = new ScoreOptLog();
        log.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        log.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        log.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        log.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        log.setScore(req.getScore());//本次操作的积分
        log.setStaffId(req.getStaffId());//用户ID
        log.setRemark(req.getRemark());//备注
        log.setOrderId(req.getOrderId());//订单
        log.setSubOrderId(req.getSubOrderId());//子订单
        log.setOptType(req.getOptType());//操作类型
        log.setScoreType(req.getSourceType());//积分类型
        log.setCreateStaff(String.valueOf(req.getCreateStaff()));//操作人
        log.setSiteId(req.getSiteId());//站点id
        this.saveScoreOptLog(log);
        
        /*4、变更用户积分账户信息*/
        BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
        BigDecimal use = new BigDecimal(scoreInfo.getScoreUsed());
        BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
        BigDecimal total = new BigDecimal(scoreInfo.getScoreTotal());
        /*4-1、如果此操作为解冻，则可用积分增加，冻结积分减少*/
        if (StaffConstants.Score.SCORE_OPT_TYPE_3.equals(req.getOptType())) {
            freeze = freeze.subtract(new BigDecimal(req.getScore()));//冻结积分减少
            balance = balance.add(new BigDecimal(req.getScore()));//可用积分增加
            //解冻的积分不足
            if (freeze.compareTo(new BigDecimal(0)) == -1) {
                throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH, new String[]{"用于解冻的"});
            }
        /*4-2、如果此操作为收入，则可用积分增加，总积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_1.equals(req.getOptType())) {
            balance = balance.add(new BigDecimal(req.getScore()));//可用积分增加
            total = total.add(new BigDecimal(req.getScore()));//总积分增加
        /*4-3、如果此操作为后台调增，则可用积分增加，总积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_5.equals(req.getOptType())) {
            balance = balance.add(new BigDecimal(req.getScore()));//可用积分增加
            total = total.add(new BigDecimal(req.getScore()));//总积分增加
        /*4-4、如果此操作为回滚补偿，则可用积分增加，使用积分减少。注意：不增加总的积分*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_7.equals(req.getOptType())) {
            balance = balance.add(new BigDecimal(req.getScore()));//可用积分增加
            use = use.subtract(new BigDecimal(req.getScore()));//冻结积分减少
        }
        scoreInfo.setScoreBalance(balance.longValue());
        scoreInfo.setScoreUsed(use.longValue());
        scoreInfo.setScoreFrozen(freeze.longValue());
        scoreInfo.setScoreTotal(total.longValue());
        scoreInfo.setUpdateStaff(req.getStaffId());
        scoreInfo.setUpdateTime(DateUtil.getSysDate());
        this.updateScoreInfo(scoreInfo);
    }
    
    @Override
    public PageResponseDTO<ScoreSourceResDTO> listScoreSource(ScoreSourceReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreSourceResDTO> pageInfo = new PageResponseDTO<ScoreSourceResDTO>();
        ScoreSourceCriteria criteria = new ScoreSourceCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreSourceCriteria.Criteria sql = criteria.createCriteria();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" create_time desc ");
        //查询条件：员工号
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：查询开始时间
        if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
            sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
        }
        //查询条件：查询结束时间
        if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
            sql.andCreateTimeLessThanOrEqualTo(req.getSelDateEnd());
        }
        //查询条件：积分类型
        if (StringUtil.isNotEmpty(req.getSourceType())) {
            sql.andSourceTypeEqualTo(req.getSourceType());
        }
        //查询条件：订单id
        if (StringUtil.isNotBlank(req.getOrderId())) {
            sql.andOrderIdEqualTo(req.getOrderId());
        }
        //查询条件：子订单id
        if (StringUtil.isNotBlank(req.getSubOrderId())) {
            sql.andSubOrderIdEqualTo(req.getSubOrderId());
        }
        //查询条件：回退标识 
        if (StringUtil.isNotBlank(req.getBackFlag())) {
            sql.andBackFlagEqualTo(req.getBackFlag());
        }
        //查询条件：isbn号
        if (StringUtil.isNotBlank(req.getIsbnCode())) {
            sql.andIsbnCodeEqualTo(req.getIsbnCode());
        }
        //查询条件：书码
        if (StringUtil.isNotBlank(req.getBookCode())) {
        	sql.andBookCodeEqualTo(req.getBookCode());
        }
        pageInfo =  super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreSource, ScoreSourceResDTO>() {

            @Override
            public List<ScoreSource> queryDB(BaseCriteria bc) {
                return scoreSourceMapper.selectByExample((ScoreSourceCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreSourceMapper.countByExample((ScoreSourceCriteria)bc);
            }
            @Override
            public List<Comparator<ScoreSource>> defineComparators() {
                List<Comparator<ScoreSource>> ls=new ArrayList<Comparator<ScoreSource>>();
                ls.add(new Comparator<ScoreSource>(){

                    @Override
                    public int compare(ScoreSource o1, ScoreSource o2) {
                    	if (o1.getCreateTime().getTime() == o2.getCreateTime().getTime()) {
                    		return (o2.getId() - o1.getId())<0?1:-1;
                    	} else {
                    		return (o2.getCreateTime().getTime() - o1.getCreateTime().getTime())>0?1:-1;
                    	}
                    }
                    
                });
                return ls;
            }

            @Override
            public ScoreSourceResDTO warpReturnObject(ScoreSource score) {
                ScoreSourceResDTO dto = new ScoreSourceResDTO();
                ObjectCopyUtil.copyObjValue(score, dto, null, true);
                String scoreTypeName = BaseParamUtil.fetchParamValue("SCORE_ADJUST_TYPE", score.getSourceType());
                if (StringUtil.isNotBlank(scoreTypeName)) {
                    dto.setScoreTypeName(scoreTypeName);
                } else {
                    ScoreType scoreType = scoreTypeSV.findScoreTypeByKey(score.getSourceType());
                    if (scoreType != null) {
                        dto.setScoreTypeName(scoreType.getScoreTypeName());
                    }
                }
                
                return dto;
            }
        });
        
        return pageInfo;
    }

    @Override
    public PageResponseDTO<ScoreExchangeResDTO> listScoreExchange(ScoreExchangeReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreExchangeResDTO> pageInfo = new PageResponseDTO<ScoreExchangeResDTO>();
        ScoreExchangeCriteria criteria = new ScoreExchangeCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreExchangeCriteria.Criteria sql = criteria.createCriteria();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" create_time desc ");
        //查询条件：员工号
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：订单ID
        if(!StringUtil.isBlank(req.getOrderId())) {
            sql.andOrderIdEqualTo(req.getOrderId());
        }
        //查询条件：子订单ID
        if(!StringUtil.isBlank(req.getSubOrderId())) {
            sql.andSubOrderIdEqualTo(req.getSubOrderId());
        }
        //查询条件：查询开始时间
        if (!StringUtil.isEmpty(req.getSelDateFrom())) {
            sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
        }
        //查询条件：查询结束时间
        if (!StringUtil.isEmpty(req.getSelDateEnd())) {
            sql.andCreateTimeLessThan(req.getSelDateEnd());
        }
        //查询条件：是否进行了回退操作
        if (StringUtil.isNotBlank(req.getBackFlag())) {
            sql.andBackFlagEqualTo(req.getBackFlag());
        }
        pageInfo =  super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreExchange, ScoreExchangeResDTO>() {

            @Override
            public List<ScoreExchange> queryDB(BaseCriteria bc) {
                return scoreExchangeMapper.selectByExample((ScoreExchangeCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreExchangeMapper.countByExample((ScoreExchangeCriteria)bc);
            }
            
            @Override
            public List<Comparator<ScoreExchange>> defineComparators() {
                List<Comparator<ScoreExchange>> ls=new ArrayList<Comparator<ScoreExchange>>();
                ls.add(new Comparator<ScoreExchange>(){

                    @Override
                    public int compare(ScoreExchange o1, ScoreExchange o2) {
                    	
                    	return (int)(o2.getCreateTime().getTime() - o1.getCreateTime().getTime());
                    }
                    
                });
                return ls;
            }

            @Override
            public ScoreExchangeResDTO warpReturnObject(ScoreExchange score) {
                ScoreExchangeResDTO dto = new ScoreExchangeResDTO();
                ObjectCopyUtil.copyObjValue(score, dto, null, true);
                CustInfo custInfo = custInfoSV.findCustInfoById(score.getStaffId());
                if (custInfo != null) {
                    dto.setStaffCode(custInfo.getStaffCode());
                }
                return dto;
            }
        });
        
        return pageInfo;
    }

    /**
     * 
     * saveScoreSource:(保存积分来源信息). <br/> 
     * 
     * @author huangxl 
     * @param scoreSource 
     * @since JDK 1.7
     */
    @Override
    public void saveScoreSource(ScoreSource scoreSource) throws BusinessException{
        scoreSource.setId(seq_scource_id.nextValue());
        scoreSource.setCreateTime(DateUtil.getSysDate());
        scoreSource.setUpdateTime(DateUtil.getSysDate());
        //计算积分有效期为：积分生成时间+365天
        Timestamp nowtime = new Timestamp(scoreSource.getCreateTime().getTime());
        scoreSource.setInureTime(DateUtil.getOffsetDaysTime(nowtime, StaffConstants.Score.SCORE_ACTIVE_DAY));
        
        scoreSourceMapper.insert(scoreSource);
    }
    /** 
     * TODO 保持积分来源明细（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#saveScoreSource(com.zengshi.ecp.staff.dao.model.CustInfo, com.zengshi.ecp.staff.dao.model.ScoreInfo, com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO) 
     */
    @Override
    public void saveScoreSource(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException {
        /*2、新增积分来源记录*/
        ScoreSource scoreSource = new ScoreSource();
        
        scoreSource.setSourceType(scoreResult.getScoreType());
        scoreSource.setScore(scoreResult.getScore());
        scoreSource.setStaffId(custInfo.getId());
        scoreSource.setRemark(scoreResult.getRemark());
        scoreSource.setOrderId(scoreResult.getOrderId());
        scoreSource.setSubOrderId(scoreResult.getOrderSubId());
        scoreSource.setUpdateStaff(custInfo.getId());
        scoreSource.setCreateStaff(custInfo.getId());
        scoreSource.setSiteId(scoreResult.getSiteId());
        scoreSource.setIsbnCode(scoreResult.getIsbnCode());
        scoreSource.setBookCode(scoreResult.getBookCode());

        scoreSource.setStatus("1"); //积分来源明细状态
        scoreSource.setTotalScore(scoreInfo.getScoreTotal());//操作前，总积分
        scoreSource.setBalanceScore(scoreInfo.getScoreBalance());//操作前，可用积分
        scoreSource.setFreezeScore(scoreInfo.getScoreFrozen());//操作前，冻结积分
        scoreSource.setUsedScore(scoreInfo.getScoreUsed());//操作前，已使用积分
        //计算积分有效期为：积分生成时间+365天
        Timestamp nowtime = DateUtil.getSysDate();
        scoreSource.setInureTime(DateUtil.getOffsetDaysTime(nowtime, StaffConstants.Score.SCORE_ACTIVE_DAY));
        
        this.saveScoreSource(scoreSource);
    }
    @Override
    public long sumScoreSourceExample(ScoreSourceReqDTO req) throws BusinessException {
        ScoreSourceCriteria criteria = new ScoreSourceCriteria();
        Criteria sql = criteria.createCriteria();
        //员工id为先决条件，不传值，则直接返回
        //查询条件：员工id
        if (req.getStaffId() != null && req.getStaffId() != 0) {
            sql.andStaffIdEqualTo(req.getStaffId());
            //查询条件：开始时间
            if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
                sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
            }
            //查询条件：结束时间
            if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
                sql.andCreateTimeLessThanOrEqualTo(req.getSelDateEnd());
            }
            //查询条件：积分类型
            if (StringUtil.isNotBlank(req.getSourceType())) {
                sql.andSourceTypeEqualTo(req.getSourceType());
            }
            //查询条件：状态
            if (StringUtil.isNotBlank(req.getStatus())) {
                sql.andStatusEqualTo(req.getStatus());
            }
            return scoreSourceSumMapper.sumByExample(criteria);
        }
        return 0;
    }

    @Override
    public long sumScoreExchangeExample(ScoreExchangeReqDTO req) throws BusinessException {
        ScoreExchangeCriteria criteria = new ScoreExchangeCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreExchangeCriteria.Criteria sql = criteria.createCriteria();
        //员工id为先决条件，不传值，则直接返回
        //查询条件：员工id
        if (req.getStaffId() != null && req.getStaffId() != 0) {
            sql.andStaffIdEqualTo(req.getStaffId());
            //查询条件：开始时间
            if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
                sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
            }
            //查询条件：结束时间
            if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
                sql.andCreateTimeLessThanOrEqualTo(req.getSelDateEnd());
            }
            return scoreExchangeSumMapper.sumByExample(criteria);
        }
        return 0;
    }

    /** 
     * TODO 简单描述该方法的实现功能（可选）. 
     * @see com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV#updateScoreInfo(com.zengshi.ecp.staff.dao.model.CustInfo, com.zengshi.ecp.staff.dao.model.ScoreInfo, com.zengshi.ecp.staff.dubbo.dto.ScoreResultResDTO, com.zengshi.ecp.staff.dubbo.dto.ProductInfoReqDto) 
     */
    @Override
    public void updateScoreInfo(CustInfo custInfo, ScoreInfo scoreInfo, ScoreResultResDTO scoreResult) throws BusinessException {
        /*4、变更用户积分账户信息*/
        BigDecimal balance = new BigDecimal(scoreInfo.getScoreBalance());
        BigDecimal use = new BigDecimal(scoreInfo.getScoreUsed());
        BigDecimal freeze = new BigDecimal(scoreInfo.getScoreFrozen());
        BigDecimal total = new BigDecimal(scoreInfo.getScoreTotal());
        /*4-1、如果此操作为解冻，则可用积分增加，冻结积分减少*/
        if (StaffConstants.Score.SCORE_OPT_TYPE_3.equals(scoreResult.getOptType())) {
            freeze = freeze.subtract(new BigDecimal(scoreResult.getScore()));//冻结积分减少
            balance = balance.add(new BigDecimal(scoreResult.getScore()));//可用积分增加
            //解冻的积分不足
            if (freeze.compareTo(new BigDecimal(0)) == -1) {
                throw new BusinessException(StaffConstants.Score.SCORE_NOT_ENOUGH, new String[]{"用于解冻的"});
            }
        /*4-2、如果此操作为收入，则可用积分增加，总积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_1.equals(scoreResult.getOptType())) {
            balance = balance.add(new BigDecimal(scoreResult.getScore()));//可用积分增加
            total = total.add(new BigDecimal(scoreResult.getScore()));//总积分增加
        /*4-3、如果此操作为后台调增，则可用积分增加，总积分增加*/
        } else if (StaffConstants.Score.SCORE_OPT_TYPE_5.equals(scoreResult.getOptType())) {
            balance = balance.add(new BigDecimal(scoreResult.getScore()));//可用积分增加
            total = total.add(new BigDecimal(scoreResult.getScore()));//总积分增加
        }
        scoreInfo.setScoreBalance(balance.longValue());
        scoreInfo.setScoreUsed(use.longValue());
        scoreInfo.setScoreFrozen(freeze.longValue());
        scoreInfo.setScoreTotal(total.longValue());
        scoreInfo.setUpdateStaff(custInfo.getId());
        scoreInfo.setUpdateTime(DateUtil.getSysDate());
        this.updateScoreInfo(scoreInfo);
    }

    @Override
    public boolean saveScoreClear(ScoreClearReqDTO req) throws BusinessException {
        /*1、查询用户是否有积分帐户，如果没有积分帐户，则直接跳过后面的处理逻辑*/
        ScoreInfo scoreInfo = this.findScoreInfoByStaffId(req.getStaffId());
        if (scoreInfo != null) {
            
            /*2、把用户积分来源表中，创建时间(人卫需求)超过指定日间的记录，全更新为失效状态*/
            ScoreSource scoreSource = new ScoreSource();
            scoreSource.setStaffId(req.getStaffId());//员工id
            scoreSource.setCreateTime(req.getDeadLineTime());//这里并不更新创建时间，只是做为过期时间的传值参数
            scoreSource.setUpdateTime(DateUtil.getSysDate());
            scoreSource.setUpdateStaff(1000L);//更新人统一为管理员
            scoreSource.setStatus(StaffConstants.Score.SCORE_STATUS_INVALID);
            scoreSourceMapper.updateByCreateTimeEndSelective(scoreSource);
            
            /*3、计算用户积分来源表中，状态为有效的积分之和*/
            ScoreSourceReqDTO sourceReq = new ScoreSourceReqDTO();
            sourceReq.setStaffId(req.getStaffId());//员工id
            sourceReq.setStatus(StaffConstants.Score.SCORE_STATUS_NORMAL);
            //获取来源表中，有效积分之和
            long scoreTotal = this.sumScoreSourceExample(sourceReq);
            
            /*4、对比积分来源之和与账户可用积分*/
            /*4-1、积分来源之和 小于 账户可用积分，说明可用积分中有已失效的积分，要相应扣减；反之，则不做任何处理
             * 扣减逻辑：可用积分设为-->积分分源之和，因此记一笔积分使用记录（原可用积分 - 积分来源之和）*/
            if (scoreInfo.getScoreBalance().longValue() > scoreTotal) {
                //调用积分使用接口，扣减相应的积分
                ScoreExchangeReqDTO exchangeReq = new ScoreExchangeReqDTO();
                exchangeReq.setStaffId(req.getStaffId());
                exchangeReq.setOptType(StaffConstants.Score.SCORE_OPT_TYPE_6);//操作类型：后台调减
                exchangeReq.setRemark("积分调整：清除超过有效期的积分");
                exchangeReq.setExchangeMode("1");//扣减模式：普通
                exchangeReq.setScoreType(StaffConstants.Score.SCORE_ADJUST_TYPE_9001);//积分类型：客户维系
                exchangeReq.setScoreUsing(scoreInfo.getScoreBalance().longValue() - scoreTotal);//调整的积分
                exchangeReq.setCreateStaff(1000L);//统一写管理员
                exchangeReq.setSiteId(1L);//站点：人卫商城
                this.saveScoreUse(exchangeReq);
            }
        }
        return true;
    }

    @Override
    public int updateScoreSourceById(ScoreSourceReqDTO req) throws BusinessException {
        ScoreSource scoreSource = new ScoreSource();
        ObjectCopyUtil.copyObjValue(req, scoreSource, null, true);
        scoreSource.setUpdateTime(DateUtil.getSysDate());
        return scoreSourceMapper.updateByPrimaryKeySelective(scoreSource);
    }

    @Override
    public int updateScoreExchangeById(ScoreExchangeReqDTO req) throws BusinessException {
        ScoreExchange scoreExchange = new ScoreExchange();
        ObjectCopyUtil.copyObjValue(req, scoreExchange, null, true);
        return scoreExchangeMapper.updateByPrimaryKeySelective(scoreExchange);
    }

    @Override
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetail(ScoreDetailSelReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreDetailSelResDTO> pageInfo = new PageResponseDTO<ScoreDetailSelResDTO>();
        ScoreDetailSelCriteria criteria = new ScoreDetailSelCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreDetailSelCriteria.Criteria sql = criteria.createCriteria();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        final String staffCode = req.getStaffCode();
        //查询条件：员工号
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：查询开始时间
        if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
            sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
        }
        //查询条件：查询结束时间
        if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
            sql.andCreateTimeLessThan(req.getSelDateEnd());
        }
        //查询条件：积分类型
        if (StringUtil.isNotBlank(req.getScoreType())) {
            sql.andScoreTypeEqualTo(req.getScoreType());
        }
        //查询条件：原始积分类型
        if (StringUtil.isNotBlank(req.getScoreTypeOrig())) {
            sql.andScoreTypeOrigEqualTo(req.getScoreTypeOrig());
        }
        //查询条件：订单id
        if (StringUtil.isNotBlank(req.getOrderId())) {
            sql.andOrderIdLike("%" + req.getOrderId() + "%");
        }
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" create_time desc ");
        
        pageInfo =  super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreDetailSel, ScoreDetailSelResDTO>() {

            @Override
            public List<ScoreDetailSel> queryDB(BaseCriteria bc) {
                return scoreDetailSelMapper.selectByExample((ScoreDetailSelCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreDetailSelMapper.countByExample((ScoreDetailSelCriteria)bc);
            }
            @Override
            public List<Comparator<ScoreDetailSel>> defineComparators() {
                List<Comparator<ScoreDetailSel>> ls=new ArrayList<Comparator<ScoreDetailSel>>();
                ls.add(new Comparator<ScoreDetailSel>(){

                    @Override
                    public int compare(ScoreDetailSel o1, ScoreDetailSel o2) {
                    	if (o2.getCreateTime().getTime() == o1.getCreateTime().getTime()) {
                    		return (o2.getId() - o1.getId())<0?1:-1;
                    	} else {
                    		return (o2.getCreateTime().getTime() - o1.getCreateTime().getTime())>0?1:-1;
                    	}
                    }
                    
                });
                return ls;
            }

            @Override
            public ScoreDetailSelResDTO warpReturnObject(ScoreDetailSel score) {
                ScoreDetailSelResDTO dto = new ScoreDetailSelResDTO();
                ObjectCopyUtil.copyObjValue(score, dto, null, true);
                dto.setStaffCode(staffCode);
                //由于页面显示需要把4列积分值，显示成一列（因为这些积分只有一个会有值）
                if (dto.getModifyAddScore() > 0) {
                    dto.setConsumeScore(dto.getModifyAddScore());
                } else if (dto.getModifyReduceScore() > 0) {
                    dto.setConsumeScore(dto.getModifyReduceScore());
                } else if (dto.getExchangeScore() > 0) {
                    dto.setConsumeScore(dto.getExchangeScore());
                }
                dto.setScoreTypeName(BaseParamUtil.fetchParamValue("DETAIL_SEL_SCORE_TYPE", dto.getScoreType()));
                return dto;
            }
        });
        return pageInfo;
    }

    @Override
    public ScoreDetailSelResDTO sumScoreByExample(ScoreDetailSelReqDTO req)
            throws BusinessException {
        ScoreDetailSelCriteria criteria = new ScoreDetailSelCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreDetailSelCriteria.Criteria sql = criteria.createCriteria();
        //查询条件：员工号
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：查询开始时间
        if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
            sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
        }
        //查询条件：查询结束时间
        if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
            sql.andCreateTimeLessThan(req.getSelDateEnd());
        }
        //查询条件：积分类型
        if (StringUtil.isNotBlank(req.getScoreType())) {
            sql.andScoreTypeEqualTo(req.getScoreType());
        }
        //查询条件：订单id
        if (StringUtil.isNotBlank(req.getOrderId())) {
            sql.andOrderIdLike("%" + req.getOrderId() + "%");
        }
        ScoreDetailSel result = scoreDetailSelMapper.sumByExample(criteria);
        ScoreDetailSelResDTO res = new ScoreDetailSelResDTO();
        ObjectCopyUtil.copyObjValue(result, res, null, false);
        return res;
    }
    
    @Override
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetailForApp(ScoreDetailSelReqDTO req)
            throws BusinessException {
        PageResponseDTO<ScoreDetailSelResDTO> pageInfo = new PageResponseDTO<ScoreDetailSelResDTO>();
        ScoreDetailSelCriteria criteria = new ScoreDetailSelCriteria();
        com.zengshi.ecp.staff.dao.model.ScoreDetailSelCriteria.Criteria sql = criteria.createCriteria();
        pageInfo.setPageNo(req.getPageNo());
        pageInfo.setPageSize(req.getPageSize());
        final String staffCode = req.getStaffCode();
        //查询条件：员工号
        if (req.getStaffId() != null && req.getStaffId() != 0L) {
            sql.andStaffIdEqualTo(req.getStaffId());
        }
        //查询条件：查询开始时间
        if (StringUtil.isNotEmpty(req.getSelDateFrom())) {
            sql.andCreateTimeGreaterThanOrEqualTo(req.getSelDateFrom());
        }
        //查询条件：查询结束时间
        if (StringUtil.isNotEmpty(req.getSelDateEnd())) {
            sql.andCreateTimeLessThan(req.getSelDateEnd());
        }
        //查询条件：积分类型
        if (StringUtil.isNotBlank(req.getScoreType())) {
            sql.andScoreTypeEqualTo(req.getScoreType());
        }
        //查询条件：原始积分类型
        if (StringUtil.isNotBlank(req.getScoreTypeOrig())) {
            sql.andScoreTypeOrigEqualTo(req.getScoreTypeOrig());
        }
        //查询条件：订单id
        if (StringUtil.isNotBlank(req.getOrderId())) {
            sql.andOrderIdLike("%" + req.getOrderId() + "%");
        }
        //设置查询的开始及终止的rownum
        criteria.setLimitClauseStart(pageInfo.getStartRowIndex());
        criteria.setLimitClauseCount(pageInfo.getPageSize());
        criteria.setOrderByClause(" create_time desc ");
        
        pageInfo =  super.queryByPagination(req, criteria, true, new PaginationCallback<ScoreDetailSel, ScoreDetailSelResDTO>() {

            @Override
            public List<ScoreDetailSel> queryDB(BaseCriteria bc) {
                return scoreDetailSelMapper.selectByExample((ScoreDetailSelCriteria)bc);
            }

            @Override
            public long queryTotal(BaseCriteria bc) {
                return scoreDetailSelMapper.countByExample((ScoreDetailSelCriteria)bc);
            }
            @Override
            public List<Comparator<ScoreDetailSel>> defineComparators() {
                List<Comparator<ScoreDetailSel>> ls=new ArrayList<Comparator<ScoreDetailSel>>();
                ls.add(new Comparator<ScoreDetailSel>(){

                    @Override
                    public int compare(ScoreDetailSel o1, ScoreDetailSel o2) {
                    	if (o2.getCreateTime().getTime() == o1.getCreateTime().getTime()) {
                    		return (o2.getId() - o1.getId())<0?1:-1;
                    	} else {
                    		return (o2.getCreateTime().getTime() - o1.getCreateTime().getTime())>0?1:-1;
                    	}
                    }
                    
                });
                return ls;
            }

            @Override
            public ScoreDetailSelResDTO warpReturnObject(ScoreDetailSel score) {
                ScoreDetailSelResDTO dto = new ScoreDetailSelResDTO();
                ObjectCopyUtil.copyObjValue(score, dto, null, true);
                dto.setStaffCode(staffCode);
                //由于页面显示需要把4列积分值，显示成一列（因为这些积分只有一个会有值）
                if (dto.getModifyAddScore() > 0) {
                    dto.setConsumeScore(dto.getModifyAddScore());
                } else if (dto.getModifyReduceScore() > 0) {
                    dto.setConsumeScore(dto.getModifyReduceScore());
                } else if (dto.getExchangeScore() > 0) {
                    dto.setConsumeScore(dto.getExchangeScore());
                }
                if ("-".equals(score.getScoreTypeOrig())) {
                    dto.setScoreTypeName("使用积分");
                } else {
                    ScoreType scoreType = scoreTypeSV.findScoreTypeByKey(score.getScoreTypeOrig());
                    if (scoreType != null) {
                        dto.setScoreTypeName(scoreType.getScoreTypeName());
                    } else {
                        String scoreTypeName = BaseParamUtil.fetchParamValue("SCORE_ADJUST_TYPE", score.getScoreTypeOrig());
                        dto.setScoreTypeName(scoreTypeName);
                    }
                }
                return dto;
            }
        });
        return pageInfo;
    }

	@Override
	public int saveScoreSignCheck(Long staffId) throws BusinessException {
		if (staffId != null && staffId != 0L) {
			/*查询是否有连续签到记录*/
			ScoreSignCheck sign = scoreSignCheckMapper.selectByPrimaryKey(staffId);
			/*1、如果没有记录，则新建一条记录*/
			if (sign == null) {
				sign = new ScoreSignCheck();
				sign.setStaffId(staffId);
				sign.setSignCnt(1L);
				sign.setSignDateBegin(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd")));
				sign.setSignDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd")));
				scoreSignCheckMapper.insert(sign);
				/*2、如果有记录*/
			} else {
				/*2.1、超过一天，重新计数*/
				if (DateUtil.getDatesDifference(DateUtil.getSysDate(), sign.getSignDateEnd()) != 1){
					sign.setSignCnt(1L);
					sign.setSignDateBegin(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd")));
					sign.setSignDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd")));
					scoreSignCheckMapper.updateByPrimaryKey(sign);
					/*2.2、连续签到，则次数+1，签到截止时间变为当天*/
				} else {
					long cnt = sign.getSignCnt() + 1;
					sign.setSignCnt(cnt);
					sign.setSignDateEnd(DateUtil.getTimestamp(DateUtil.getDateString("yyyy-MM-dd")));
					scoreSignCheckMapper.updateByPrimaryKey(sign);
				}
			}
		}
		return 1;
	}

	@Override
	public ScoreSignCheckRespDTO findScoreSignByStaffId(Long staffId) throws BusinessException {
		ScoreSignCheckRespDTO dto = new ScoreSignCheckRespDTO();
		ScoreSignCheck sign = scoreSignCheckMapper.selectByPrimaryKey(staffId);
		if (sign != null) {
			ObjectCopyUtil.copyObjValue(sign, dto, null, false);
			return dto;
		} else {
			return null;
		}
		
	}

    @Override
    public ScoreSourceResDTO signAlready(Long staffId) {
        ScoreSourceReqDTO req = new ScoreSourceReqDTO();
        //查询时间为今天开始~今天结束
        req.setSelDateFrom(DateUtil.getTheDayFirstSecond(DateUtil.getSysDate()));
        req.setSelDateEnd(DateUtil.getTheDayLastSecond(DateUtil.getSysDate()));
        //用户id
        req.setStaffId(staffId);
        req.setSourceType(SIGN_SOURCE_TYPE);
        req.setPageNo(0);
        PageResponseDTO<ScoreSourceResDTO> records = this.listScoreSource(req);
        if (records != null && CollectionUtils.isNotEmpty(records.getResult())) {
            return records.getResult().get(0);
        }
        return null;
    }
}

