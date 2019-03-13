package com.zengshi.ecp.coupon.service.common.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.util.CollectionUtils;
import com.zengshi.ecp.coupon.dao.mapper.common.CoupParamMapper;
import com.zengshi.ecp.coupon.dao.model.CoupParam;
import com.zengshi.ecp.coupon.dao.model.CoupParamCriteria;
import com.zengshi.ecp.coupon.dao.model.CoupParamCriteria.Criteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupParamReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupParamRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.service.common.interfaces.ICoupParamSV;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-4 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public class CoupParamSVImpl extends GeneralSQLSVImpl implements ICoupParamSV {

    private static final String MODULE = CoupParamSVImpl.class.getName();

    @Resource
    private CoupParamMapper coupParamMapper;

    @Resource
    private Converter<CoupParamReqDTO, CoupParam> coupParamConverter;
    
    @Resource
    private Converter<CoupParam, CoupParamRespDTO> coupParamRespDTOConverter;

    /**
     * 优惠券参数查询 编码必传
     * @param ruleCode
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParamByCode(String ruleCode)
            throws BusinessException {
        return coupParamRespDTOConverter.convert(coupParamMapper.selectByPrimaryKey(ruleCode));
    }

    /**
     * 优惠券参数查询 编码必传
     * 
     * @param coupParamReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupParamRespDTO queryCoupParamById(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException {
        CoupParamRespDTO dto = new CoupParamRespDTO();
        CoupParamCriteria example = new CoupParamCriteria();
        this.initCoupParamCond(coupParamReqDTO, example);
        coupParamMapper.selectByExample(example);
        return dto;

    }

    /**
     * 优惠券参数查询
     * 
     * @param coupParamReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public List<CoupParamRespDTO> queryCoupParamList(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException {
        // 组织参数
        CoupParamCriteria example = new CoupParamCriteria();
        this.initCoupParamCond(coupParamReqDTO, example);
        // 查询
        List<CoupParam> list = coupParamMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        // 返回解析
        List<CoupParamRespDTO> reList = new ArrayList<CoupParamRespDTO>();
        for (CoupParam coupParam : list) {
            reList.add(coupParamRespDTOConverter.convert(coupParam));
        }
        return reList;
    }

    /**
     * 优惠券参数查询
     * 
     * @param coupParamReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public PageResponseDTO<CoupParamRespDTO> queryCoupParamPage(CoupParamReqDTO coupParamReqDTO)
            throws BusinessException {

        CoupParamCriteria example = new CoupParamCriteria();
        // 初始化查询条件
        this.initCoupParamCond(coupParamReqDTO, example);

        example.setLimitClauseCount(coupParamReqDTO.getPageSize());
        example.setLimitClauseStart(coupParamReqDTO.getStartRowIndex());
        example.setOrderByClause(" id desc ");

        PageResponseDTO<CoupParamRespDTO> page = null;
        // 返回查询分页结果集
        page = super.queryByPagination(coupParamReqDTO, example, true,
                new PaginationCallback<CoupParam, CoupParamRespDTO>() {
                    // 查询记录集
                    @Override
                    public List<CoupParam> queryDB(BaseCriteria example) {

                        return coupParamMapper.selectByExample((CoupParamCriteria) example);
                    }

                    // 查询总记录数
                    @Override
                    public long queryTotal(BaseCriteria example) {

                        return coupParamMapper.countByExample((CoupParamCriteria) example);
                    }

                    // 查询结果转换
                    @Override
                    public CoupParamRespDTO warpReturnObject(CoupParam t) {
                        return coupParamRespDTOConverter.convert(t);
                    }
                });

        return page;

    }

    /**
     * 优惠券参数 条件组织
     * 
     * @param coupParamReqDTO
     * @param example
     * @author huangjx
     */
    private void initCoupParamCond(CoupParamReqDTO coupParamReqDTO, CoupParamCriteria example) {

        if (coupParamReqDTO == null) {
            return;
        }
        Criteria cr = example.createCriteria();
        // ID 查询
        if (!StringUtil.isEmpty(coupParamReqDTO.getRuleCode())) {
            cr.andRuleCodeEqualTo(coupParamReqDTO.getRuleCode());
        }
        // 状态
        if (!StringUtil.isEmpty(coupParamReqDTO.getStatus())) {
            cr.andStatusEqualTo(coupParamReqDTO.getStatus());
        }
        // 名字
        if (!StringUtil.isEmpty(coupParamReqDTO.getRuleName())) {
            cr.andRuleNameLike("%"+coupParamReqDTO.getRuleName()+"%");
        }
        // 类型
        if (!StringUtil.isEmpty(coupParamReqDTO.getRuleType())) {
            cr.andRuleTypeEqualTo(coupParamReqDTO.getRuleType());
        }
    }
}
