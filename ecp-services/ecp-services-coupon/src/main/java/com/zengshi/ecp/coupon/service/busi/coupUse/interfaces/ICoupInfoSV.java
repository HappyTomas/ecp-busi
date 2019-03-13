package com.zengshi.ecp.coupon.service.busi.coupUse.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-services-coupon-server <br>
 * Description: <br>
 * Date:2015-10-9下午12:39:37  <br>
 * 
 * @author huanghe5
 * @version  
 * @since JDK 1.7
 */
public interface ICoupInfoSV extends IGeneralSQLSV{
	
	/**
	 * 
	 * queryCoupInfoPage:优惠券小类查询(分页). <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public PageResponseDTO<CoupInfoRespDTO> queryCoupInfoPage(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
	
	/**
	 * 
	 * queryCoupInfoPage:优惠券小类查询. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupInfoRespDTO> queryCoupInfoList(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
	 
	
	/**
	 * 
	 * queryCoupInfoPage:根据ID 查询到小类信息. <br/> 
	 * 
	 * @author huanghe5 
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public List<CoupInfoRespDTO> queryCoupInfoList(List<Long> coupInfoReqBeans) throws BusinessException;
	 
	
    /**
     * 优惠券小类查询
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupInfoRespDTO queryCoupInfoById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    
	/**
	 * 
	 * saveCoup:保存以及编辑. <br/> 
	 * 
	 * @author huanghe5
	 * @param coupReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
    public String saveCoup(CoupReqDTO coupReqDTO) throws BusinessException;
    

    
    /**
     * 
     * deleteCoup:优惠券信息批量删除. <br/> 
     * @author huanghe5
     * @param coupReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void deleteBatchCoup(CoupReqDTO coupReqDTO) throws BusinessException;
    
    /**
     * 
     * ifInvalidCoup:批量生失效接口. <br/> 
     * 
     * @author huanghe5
     * @param coupReqDTO
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public void ifInvalidCoup(CoupReqDTO coupReqDTO) throws BusinessException;
    
    /**
     * 
     * queryCoupById:查询有效的优惠券信息Bean. <br/> 
     * @author huanghe5
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CoupInfoRespDTO queryCoupById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    /**
     * 
     * queryCoupInfoByCoupCode:根据优惠码查询优惠券. <br/> 
     * 
     * @author lisp 
     * @param CoupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
	public CoupInfoRespDTO queryCoupInfoByCoupCode(CoupInfoReqDTO coupInfoReqDTO)
			throws BusinessException;
	
	/**
	 * 
	 * getCoupCode:校验优惠码是否重复. <br/> 
	 * 
	 * @author lisp
	 * @param coupInfoReqDTO
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.7
	 */
	public boolean getCoupCode(CoupInfoReqDTO coupInfoReqDTO)throws BusinessException ;
}
