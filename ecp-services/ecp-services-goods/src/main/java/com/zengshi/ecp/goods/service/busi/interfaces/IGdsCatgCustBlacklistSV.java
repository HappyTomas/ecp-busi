package com.zengshi.ecp.goods.service.busi.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatgCustBlacklistRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;


public interface IGdsCatgCustBlacklistSV {
    /**
     * 
     * 支持批量添加  会合并gdsId与gdsIdList. 
     * 添加前失效库中对应gdsId数据
     */
    void addGdsCatgCustBlacklist(GdsCatgCustBlacklistReqDTO gdsCatgCustBlacklistReqDTO) throws BusinessException;

	//Long addGdsCatgCustBlacklistByOne(GdsCatgCustBlacklistReqDTO gdsCatgCustBlacklistReqDTO) throws BusinessException;
    /**
     * 
     * 根据id删除  只识别reqDTO中的id与idList字段  
     * 支持批量删除   会合并id与idList
     */
	 int deleteGdsCatgCustBlacklistByPrimaryKey(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
	 /**
	     * 
	     * 按条件删除    
	     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	     */
	 int deleteGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
	 /**
	     * 
	     * 按条件删除 
	     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	     */
	 int deleteBatchGdsCatgCustBlacklistByGdsId(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
	 /**
	     * 
	     * 按条件查询 
	     * 只返回第一个数据    
	     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	     */
	 GdsCatgCustBlacklistRespDTO queryGdsCatgcustBlacklistByExample(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
	 /**
	     * 
	     * 按条件查询  
	     * 分页    
	     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	     */
	 PageResponseDTO<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistPage(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
	 /**
	     * 
	     * 按条件查询 
	     * 列表    
	     * reqDTO字段之间是与  字段之内是或  如 id 与 idList  会是 id = 'id' and id in idList 
	     */
	 List<GdsCatgCustBlacklistRespDTO> queryGdsCatgcustBlacklistList(GdsCatgCustBlacklistReqDTO reqDTO) throws BusinessException;
}
