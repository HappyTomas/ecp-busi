package com.zengshi.ecp.sys.service.common.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.SysCfgUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.sys.dao.mapper.common.BaseSysCfgMapper;
import com.zengshi.ecp.sys.dao.model.BaseSysCfg;
import com.zengshi.ecp.sys.dao.model.BaseSysCfgCriteria;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgReqDTO;
import com.zengshi.ecp.sys.dubbo.dto.SysCfgResDTO;
import com.zengshi.ecp.sys.service.common.interfaces.ISysCfgSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.LogUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;

public class SysCfgSVImpl extends GeneralSQLSVImpl implements ISysCfgSV {
	
	
	private static final String MODULE =SysCfgSVImpl.class.getName();
	@Resource
	private BaseSysCfgMapper baseSysCfgMapper;

	/**
	 * 
	 * saveSysCfg: 保存系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public int saveSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		if(baseSysCfgMapper.selectByPrimaryKey(sysCfgReqDTO.getParaCode())!=null){
			 throw new BusinessException("sys.cfg.param.exist");
		}
		BaseSysCfg baseSysCfg = new BaseSysCfg();
		ObjectCopyUtil.copyObjValue(sysCfgReqDTO, baseSysCfg, null, false);
		int i = 0;
		if(StringUtil.isEmpty(baseSysCfg.getCreateTime())){
			baseSysCfg.setCreateTime(DateUtil.getSysDate());
		}
	    baseSysCfg.setCreateStaff(sysCfgReqDTO.getStaff().getId());
		try {
			   i = baseSysCfgMapper.insertSelective(baseSysCfg);
			   if(i>0){
				   SysCfgUtil.refreshSysCfgByCode(sysCfgReqDTO.getParaCode());
			   }
		} catch (BusinessException e) {
			 LogUtil.error(MODULE, e.toString());
			throw new BusinessException("sys.cfg.param.save.failure");
		}
		return i ;
	}

	/**
	 * 
	 * updateSysCfg: 更新系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public int updateSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		BaseSysCfg baseSysCfg = new BaseSysCfg();
		ObjectCopyUtil.copyObjValue(sysCfgReqDTO, baseSysCfg, null, false);
		baseSysCfg.setUpdateTime(DateUtil.getSysDate());
		BaseSysCfgCriteria example = new BaseSysCfgCriteria();
		BaseSysCfgCriteria.Criteria cr = example.createCriteria();
		cr.andParaCodeEqualTo(sysCfgReqDTO.getSearchParams());
		int i = 0;
		if(StringUtil.isEmpty(baseSysCfg.getCreateTime())){
			baseSysCfg.setCreateTime(DateUtil.getSysDate());
		}
		try {
			   i = baseSysCfgMapper.updateByExampleSelective(baseSysCfg, example);
			   if(i>0){
				   SysCfgUtil.delSysCfg(sysCfgReqDTO.getSearchParams());
				   SysCfgUtil.refreshSysCfgByCode(sysCfgReqDTO.getParaCode());
			   }
		} catch (BusinessException e) {
			LogUtil.error(MODULE, e.toString());
			throw new BusinessException("sys.cfg.param.update.failure");
		}
		return i ;
	}

	/**
	 * 系统参数 查询 分页
	 * 
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public PageResponseDTO<SysCfgResDTO> querySysCfgForPage(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		if (sysCfgReqDTO == null) {
			return null;
		}
		BaseSysCfgCriteria example = new BaseSysCfgCriteria();
		example.setLimitClauseCount(sysCfgReqDTO.getPageSize());
		example.setLimitClauseStart(sysCfgReqDTO.getStartRowIndex());
		BaseSysCfgCriteria.Criteria cr = example.createCriteria();
		
		if (StringUtil.isNotEmpty(sysCfgReqDTO.getParaCode())) {
			cr.andParaCodeLike("%"+sysCfgReqDTO.getParaCode()+"%");
		}
		if (StringUtil.isNotEmpty(sysCfgReqDTO.getParaDesc())) {
			cr.andParaDescLike("%"+sysCfgReqDTO.getParaDesc()+"%");
		}
		if (StringUtil.isNotEmpty(sysCfgReqDTO.getParaValue()) ) {
			cr.andParaValueEqualTo(sysCfgReqDTO.getParaValue());
		}
		return super.queryByPagination(sysCfgReqDTO, example, true, new PaginationCallback<BaseSysCfg, SysCfgResDTO>() {
			// 查询记录集
			@Override
			public List<BaseSysCfg> queryDB(BaseCriteria example) {
				return baseSysCfgMapper.selectByExample((BaseSysCfgCriteria) example);
			}

			// 查询总记录数
			@Override
			public long queryTotal(BaseCriteria example) {
				return baseSysCfgMapper.countByExample((BaseSysCfgCriteria) example);
			}

			// 查询结果转换
			@Override
			public SysCfgResDTO warpReturnObject(BaseSysCfg t) {
				SysCfgResDTO sysCfgResDTO = new SysCfgResDTO();
				ObjectCopyUtil.copyObjValue(t, sysCfgResDTO, "", false);
				return sysCfgResDTO;
			}
		});
	}

	/**
	 * 
	 * fetchByparaCode: 查找系统参数信息 <br/>
	 * 
	 * @return
	 * @throws BusinessException
	 */

	@Override
	public SysCfgResDTO fetchByparaCode(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		SysCfgResDTO sysCfgResDTO = new SysCfgResDTO();
		BaseSysCfg baseSysCfg = baseSysCfgMapper.selectByPrimaryKey(sysCfgReqDTO.getParaCode());
		if(StringUtil.isNotEmpty(baseSysCfg)){
			ObjectCopyUtil.copyObjValue(baseSysCfg, sysCfgResDTO, null, false);
			return sysCfgResDTO;
			}else{
				return null;
			}
		
	}
	/**
	 * 删除系统参数
	 * 
	 * @param sysCfgReqDTO
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public void removeSysCfg(SysCfgReqDTO sysCfgReqDTO) throws BusinessException {
		try {
			int i = baseSysCfgMapper.deleteByPrimaryKey(sysCfgReqDTO.getParaCode());
			if(i>0){
				SysCfgUtil.delSysCfg(sysCfgReqDTO.getParaCode());
			}
		} catch (BusinessException e) {
			LogUtil.error(MODULE, e.toString());
            throw new BusinessException("sys.cfg.param.delete.failure");
		}
	}
}
