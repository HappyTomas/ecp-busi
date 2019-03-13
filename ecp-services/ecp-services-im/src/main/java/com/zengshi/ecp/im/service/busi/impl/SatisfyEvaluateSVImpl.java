package com.zengshi.ecp.im.service.busi.impl;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.im.dao.mapper.busi.ImCustservSatisfyInfoMapper;
import com.zengshi.ecp.im.dao.model.ImCustservSatisfyInfo;
import com.zengshi.ecp.im.dao.model.ImCustservSatisfyInfoCriteria;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyReqDTO;
import com.zengshi.ecp.im.dubbo.dto.CustServSatisfyResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.dubbo.util.ImErrorConstants;
import com.zengshi.ecp.im.service.busi.interfaces.ISatisfyEvaluateSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.ObjectCopyUtil;
import org.apache.commons.collections.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhangys on 16/11/9.
 */
public class SatisfyEvaluateSVImpl implements ISatisfyEvaluateSV {

    @Resource
    private ImCustservSatisfyInfoMapper imCustservSatisfyInfoMapper;

    @Resource(name = "seq_custserv_satisfy_info_id")
    private PaasSequence seqCustservSatisfyInfoId;

    @Override
    public CustServSatisfyResDTO addSatisfyEvaluate( CustServSatisfyReqDTO reqDTO) throws
        BusinessException {
        ImCustservSatisfyInfo imCustservSatisfyInfo
            = new ImCustservSatisfyInfo(reqDTO);
        CustServSatisfyResDTO tmpRes = qrySatisfyEvaluate(reqDTO);
        if (tmpRes != null) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600009);
        }
        Long seq = seqCustservSatisfyInfoId.nextValue();
        imCustservSatisfyInfo.setId(seq);
        imCustservSatisfyInfoMapper.insertSelective(imCustservSatisfyInfo);
        CustServSatisfyResDTO resDTO = new CustServSatisfyResDTO();
        ObjectCopyUtil.copyObjValue(imCustservSatisfyInfo,resDTO,null,true);
        return resDTO;
    }

    @Override
    public CustServSatisfyResDTO qrySatisfyEvaluate(CustServSatisfyReqDTO reqDTO) throws
        BusinessException {
        ImCustservSatisfyInfoCriteria criteria = new ImCustservSatisfyInfoCriteria();
        ImCustservSatisfyInfoCriteria.Criteria cond = criteria.createCriteria();
        if (null == reqDTO.getSessionId()) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600010);
        }
        cond.andSessionIdEqualTo(reqDTO.getSessionId());
        List<ImCustservSatisfyInfo> resList = imCustservSatisfyInfoMapper.selectByExample(criteria);
        if (CollectionUtils.isNotEmpty(resList)) {
            CustServSatisfyResDTO resDTO = new CustServSatisfyResDTO();
            ObjectCopyUtil.copyObjValue(resList.get(0),resDTO,null,true);
            return  resDTO;
        } else {
            return null;
        }
    }
}
