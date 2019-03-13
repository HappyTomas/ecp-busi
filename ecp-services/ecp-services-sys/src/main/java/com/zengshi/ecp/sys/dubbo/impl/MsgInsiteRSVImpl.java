package com.zengshi.ecp.sys.dubbo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgDefineResDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.MsgInsiteResDTO;
import com.zengshi.ecp.sys.dubbo.interfaces.IMsgInsiteRSV;
import com.zengshi.ecp.sys.service.busi.interfaces.IMsgInsiteSV;
import com.zengshi.ecp.sys.service.common.interfaces.IMsgManageSV;
import com.alibaba.dubbo.common.utils.CollectionUtils;
/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-sys-server <br>
 * Description: <br>
 * Date:2016年5月20日下午3:58:28  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author chenyz3
 * @version  
 * @since JDK 1.6
 */
public class MsgInsiteRSVImpl implements IMsgInsiteRSV {

    @Resource
    private IMsgManageSV msgManageService;
    @Resource
    private IMsgInsiteSV msgInsite;
    
    @Override
    public void insertMsgInsite(MsgInsiteReqDTO req) {
    }

    @Override
    public long countMsgInsite(long staffId) {
        return 0;
    }

    @Override
    public int updateMsgInsite(MsgInsiteReqDTO req) throws BusinessException {
        if(req == null ){
            throw new BusinessException("入参对象不能为空");
        }
        return msgInsite.updateMsgInsite(req);
    }

    @Override
    public int deleteMsgInsite(MsgInsiteReqDTO req) throws BusinessException {
        if(req == null ){
            throw new BusinessException("入参对象不能为空");
        }
        if(req.getStaffId() == null || req.getStaffId() == 0L){
            throw new BusinessException("站内接收用户ID不能为空");
        }
        if(req.getMsgInfoId() == null || req.getMsgInfoId() ==0L){
            throw new BusinessException("对接消息表的消息ID不能为空");
        }
        return msgInsite.deleteMsgInsite(req);
    }

    @Override
    public PageResponseDTO<MsgInsiteResDTO> listMsgInsiteBymsgType(MsgInsiteReqDTO req)
            throws BusinessException {
        //1.执行参数规则校验
        if(req == null)
        {
            throw new BusinessException("入参对象不能为空");
        }
        List<MsgDefineResDTO> resultLists = null;
        MsgDefineReqDTO defineDTO = new MsgDefineReqDTO();
        defineDTO.setMsgType(req.getMsgType());
        defineDTO.setPageNo(0);//查所有
        PageResponseDTO<MsgDefineResDTO> pageResponseDTO = new PageResponseDTO<MsgDefineResDTO>();
        PageResponseDTO<MsgInsiteResDTO> result = new PageResponseDTO<MsgInsiteResDTO>();
        try {
            pageResponseDTO = msgManageService.listMsgDefine(defineDTO);
            if(CollectionUtils.isNotEmpty(pageResponseDTO.getResult())){
                resultLists = pageResponseDTO.getResult();
                List<String> codeDList = new ArrayList<String>(resultLists.size());
                for(MsgDefineResDTO msg :resultLists ){
                    codeDList.add(msg.getMsgCode());
                }
                result = msgInsite.listMsgInsite(req, codeDList);
            }
            
        } catch (Exception e) {
            throw new BusinessException("查询异常");
        }
       
        return result;
    }

    @Override
    public long countMsgInsiteByrole(MsgInsiteReqDTO req) {
        //1.执行参数规则校验
        if(req == null)
        {
            throw new BusinessException("入参对象不能为空");
        }
        List<MsgDefineResDTO> resultLists = null;
        MsgDefineReqDTO defineDTO = new MsgDefineReqDTO();
        defineDTO.setMsgType(req.getMsgType());
        defineDTO.setPageNo(0);//查询所有
        PageResponseDTO<MsgDefineResDTO> pageResponseDTO = new PageResponseDTO<MsgDefineResDTO>();
        Long result=0L;
        try {
            pageResponseDTO = msgManageService.listMsgDefine(defineDTO);
            if(CollectionUtils.isNotEmpty(pageResponseDTO.getResult())){
                resultLists = pageResponseDTO.getResult();
                List<String> codeDList = new ArrayList<String>(resultLists.size());
                for(MsgDefineResDTO msg :resultLists ){
                    codeDList.add(msg.getMsgCode());
                }
                result = msgInsite.countMsgInsiteByrole(req, codeDList);
            }
            
        } catch (Exception e) {
            throw new BusinessException("查询异常");
        }
       
        return result;
    }

	@Override
	public PageResponseDTO<MsgInsiteResDTO> listMsgInsiteByStaff(MsgInsiteReqDTO req) throws BusinessException {
		return msgInsite.listMsgInsite(req, null);
	}

	@Override
	public long countMsgInsiteByStaff(MsgInsiteReqDTO req) {
		return msgInsite.countMsgInsiteByrole(req, null);
	}

}

