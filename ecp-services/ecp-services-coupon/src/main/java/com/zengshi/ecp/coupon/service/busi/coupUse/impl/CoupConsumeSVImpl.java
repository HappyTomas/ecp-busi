package com.zengshi.ecp.coupon.service.busi.coupUse.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.coupon.dao.mapper.busi.CoupConsumeMapper;
import com.zengshi.ecp.coupon.dao.model.CoupConsume;
import com.zengshi.ecp.coupon.dao.model.CoupConsumeCriteria;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupConsumeReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupConsumeRespDTO;
import com.zengshi.ecp.coupon.dubbo.impl.converter.Converter;
import com.zengshi.ecp.coupon.service.busi.combination.interfaces.ICoupCreateInitSV;
import com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupConsumeSV;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;

public class CoupConsumeSVImpl extends GeneralSQLSVImpl  implements ICoupConsumeSV{

	@Resource
    private CoupConsumeMapper coupConsumeMapper;
	
	@Resource
    private ICoupCreateInitSV coupCreateInitSV;
	
	@Resource
	private Converter<CoupConsume,CoupConsumeRespDTO> coupConsumeDTOConverter;

	@Override
	public PageResponseDTO<CoupConsumeRespDTO> queryCoupConsumePage(
			CoupConsumeReqDTO coupConsumeReqDTO) throws BusinessException {
		// 查询初始化查询条件
		CoupConsumeCriteria example = new CoupConsumeCriteria();
		example.setLimitClauseCount(coupConsumeReqDTO.getPageSize());
		example.setLimitClauseStart(coupConsumeReqDTO.getStartRowIndex());
		
		// 排序
		example.setOrderByClause("id desc");
		coupCreateInitSV.initCoupCounSume(coupConsumeReqDTO, example);
		
		// 返回查询分页结果集
		return super.queryByPagination(coupConsumeReqDTO, example, true,
				new PaginationCallback<CoupConsume, CoupConsumeRespDTO>() {
					// 查询记录集
					@Override
					public List<CoupConsume> queryDB(BaseCriteria example) {

						return coupConsumeMapper
								.selectByExample((CoupConsumeCriteria) example);
					}

					// 查询总记录数
					@Override
					public long queryTotal(BaseCriteria example) {

						return coupConsumeMapper
								.countByExample((CoupConsumeCriteria) example);
					}

					// 查询结果转换
					@Override
					public CoupConsumeRespDTO warpReturnObject(CoupConsume t) {
						return coupConsumeDTOConverter.convert(t);
					}
				});
	}



}
