package com.zengshi.ecp.unpf.service.busi.auth.interfaces;

import java.util.List;

import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.auth.UnpfShopAuthRespDTO;

/**
* @author  lisp: 
* @date 创建时间：2016年11月8日 上午9:29:34 
* @version 1.0 
*  */
public interface IUnpfShopAuthSV extends IGeneralSQLSV {

	/**
	* 对接平台 删除店铺授权
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public int deletePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	
	/**
	* 对接平台 更新店铺授权
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public int updatePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	/**
	* 对接平台 店铺授权详情
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public UnpfShopAuthRespDTO queryPlatType4ShopById(UnpfShopAuthReqDTO unpfShopAuthReqDTO)throws BusinessException;
	
	
	/**
	* 对接平台 店铺授权详情
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public List<String>  queryPlatType4ShopByExample(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	
	/**
	* 对接平台授权店铺 保存验证 如果验证不通过 返回有值
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return 
	* @throws 
	*/
	public UnpfShopAuthReqDTO validPlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	/**
	* 对接平台 添加店铺授权
	* 
	* @author lisp
	* @param List<UnpfShopAuthReqDTO>
	* @return 
	* @throws 
	*/
	public void savePlatType4Shop(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;
	
	/**
	* 对接平台 店铺授权列表 分页
	* 
	* @author lisp
	* @param UnpfShopAuthReqDTO
	* @return PageResponseDTO<UnpfShopAuthRespDTO>
	* @throws ParseException
	*/
	public PageResponseDTO<UnpfShopAuthRespDTO> queryPlatType4ShopForPage(UnpfShopAuthReqDTO unpfShopAuthReqDTO) throws BusinessException;

	

}


