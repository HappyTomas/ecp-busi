package com.zengshi.ecp.staff.dubbo.impl;


import com.zengshi.ecp.general.order.dto.PayQuartzInfoRequest;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.staff.dao.model.CustInfo;
import com.zengshi.ecp.staff.dao.model.ScoreInfo;
import com.zengshi.ecp.staff.dubbo.dto.*;
import com.zengshi.ecp.staff.dubbo.interfaces.IScoreInfoRSV;
import com.zengshi.ecp.staff.dubbo.util.StaffConstants;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreCaclSV;
import com.zengshi.ecp.staff.service.busi.score.interfaces.IScoreInfoSV;
import com.zengshi.ecp.staff.service.common.score.interfaces.IScoreTypeSV;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

import javax.annotation.Resource;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-staff <br>
 * Description: dubbo层  积分信息服务接口实现类<br>
 * Date:2015-8-26下午8:34:36  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author huangxl
 * @version  
 * @since JDK 1.7
 */
public class ScoreInfoRSVImpl implements IScoreInfoRSV{

    @Resource
    private IScoreInfoSV scoreInfoSV;
    
    @Resource
    private IScoreTypeSV scoreTypeSV;
    
    @Resource
    private IScoreCaclSV scoreCaclSV;
    
    private static final String MODULE = ManagerLoginRSVImpl.class.getName();


    @Override
    public void scoreUse(ScoreExchangeReqDTO req) throws BusinessException {
        //入参对象为空
        if (req == null) {
            LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"入参对象"});
        }
        //积分操作类型不能为空
        if (StringUtil.isBlank(req.getOptType())) {
            LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"积分操作类型"});
        }
        //积分额度不能为空
        if (req.getScoreUsing() == 0L) {
            LogUtil.info(MODULE, "入参对象不能为空！");
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR,new String[]{"使用的积分"});
        }
        scoreInfoSV.saveScoreUse(req);
    }

   

    @Override
    public void updateScoreInfo(ScoreInfoReqDTO req) throws BusinessException {
        ScoreInfo scoreInfo = new ScoreInfo();
        ObjectCopyUtil.copyObjValue(req, scoreInfo, null, true);
        scoreInfoSV.updateScoreInfo(scoreInfo);
    }

    @Override
    public PageResponseDTO<ScoreInfoResDTO> listScoreInfo(ScoreInfoReqDTO req)
            throws BusinessException {
        return scoreInfoSV.listScoreInfo(req);
    }

    @Override
    public void saveScoreAdd(ScoreSourceReqDTO req) throws BusinessException {
        scoreInfoSV.saveScoreAdd(req);
    }
    
    @Override
    public ScoreResultResDTO caclScore(PayQuartzInfoRequest pOrderInfo)throws BusinessException
    {
        if(pOrderInfo ==null)
        {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"======caclScore(PayQuartzInfoDTO pOrderInfo)入参不能为空======"});
        }
        String pSourceType = pOrderInfo.getSourceType();
        CustInfoReqDTO pCustInfo = new  CustInfoReqDTO();
        pCustInfo.setId(pOrderInfo.getStaffId());
        
        ScoreResultResDTO resultResDTO = scoreCaclSV.updateScore(pSourceType, pCustInfo, pOrderInfo);
        
        return resultResDTO;
    }

    @Override
    public ScoreInfoResDTO findScoreInfoByStaffId(Long pStaffId) throws BusinessException {
        ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByStaffId(pStaffId);
        ScoreInfoResDTO dto = new ScoreInfoResDTO();
        ObjectCopyUtil.copyObjValue(scoreInfo, dto, null, true);
        return dto;
    }



    @Override
    public PageResponseDTO<ScoreTypeResDTO> queryScoreTypeList(ScoreTypeReqDTO req)
            throws BusinessException {
        return scoreTypeSV.queryScoreTypeList(req);
    }



    @Override
    public PageResponseDTO<ScoreSourceResDTO> listScoreSource(ScoreSourceReqDTO req)
            throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"入参对象"});
        }
        return scoreInfoSV.listScoreSource(req);
    }



    @Override
    public PageResponseDTO<ScoreExchangeResDTO> listScoreExchange(ScoreExchangeReqDTO req)
            throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"入参对象"});
        }
        return scoreInfoSV.listScoreExchange(req);
    }



    @Override
    public long sumScoreSourceExample(ScoreSourceReqDTO req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"入参对象"});
        }
        return scoreInfoSV.sumScoreSourceExample(req);
    }



    @Override
    public long sumScoreExchangeExample(ScoreExchangeReqDTO req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"入参对象"});
        }
        return scoreInfoSV.sumScoreExchangeExample(req);
    }



    @Override
    public boolean saveScoreClear(ScoreClearReqDTO req) throws BusinessException {
        if (req == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"入参对象"});
        }
        if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"用户id"});
        }
        if (req.getDeadLineTime() == null) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"积分到期时间"});
        }
        return scoreInfoSV.saveScoreClear(req);
    }



    @Override
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetail(ScoreDetailSelReqDTO req)
            throws BusinessException {
        if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"用户id"});
        }
        return scoreInfoSV.listScoreDetail(req);
    }



    @Override
    public ScoreDetailSelResDTO sumScoreByExample(ScoreDetailSelReqDTO req)
            throws BusinessException {
        if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"用户id"});
        }
        return scoreInfoSV.sumScoreByExample(req);
    }
    
    @Override
    public PageResponseDTO<ScoreDetailSelResDTO> listScoreDetailForApp(ScoreDetailSelReqDTO req)
            throws BusinessException {
        if (req.getStaffId() == null || req.getStaffId() == 0L) {
            throw new BusinessException(StaffConstants.STAFF_NULL_ERROR, new String []{"用户id"});
        }
        return scoreInfoSV.listScoreDetailForApp(req);
    }



    @Override
    public void updateScoreInfo(CustInfoReqDTO custReq, ScoreInfoReqDTO scoreReq,
            ScoreResultResDTO scoreResult) throws BusinessException {
        CustInfo custInfo = new CustInfo();
        ObjectCopyUtil.copyObjValue(custReq, custInfo, null, false);
        ScoreInfo scoreInfo = new ScoreInfo();
        ObjectCopyUtil.copyObjValue(scoreReq, scoreInfo, null, false);
        scoreInfoSV.updateScoreInfo(custInfo, scoreInfo, scoreResult);
    }



	@Override
	public ScoreInfoResDTO findScoreInfoByCustAndCreate(CustInfoReqDTO req) throws BusinessException {
		CustInfo custInfo = new CustInfo();
		custInfo.setId(req.getId());
		custInfo.setStaffCode(req.getStaffCode());
		//该方法在查询不到用户的积分账户时，会创建一个初始的积分账户
		ScoreInfo scoreInfo = scoreInfoSV.findScoreInfoByCustInfo(custInfo);
		ScoreInfoResDTO res = new ScoreInfoResDTO();
		ObjectCopyUtil.copyObjValue(scoreInfo,res,null,false);
		return res;
	}



	@Override
	public ScoreSignCheckRespDTO findScoreSignByStaffId(Long staffId) throws BusinessException {
		return scoreInfoSV.findScoreSignByStaffId(staffId);
	}



    @Override
    public ScoreSourceResDTO signAlready(Long staffId) {
        return scoreInfoSV.signAlready(staffId);
    }

}

