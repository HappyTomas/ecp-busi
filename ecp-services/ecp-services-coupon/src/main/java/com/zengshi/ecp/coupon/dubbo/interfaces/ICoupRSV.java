package com.zengshi.ecp.coupon.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.req.CoupInfoReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO;
import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupInfoRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: AI-ECP <br>
 * Description: <br>
 * Date: 2015-10-8 <br>
 * Copyright (c) 2015 AI <br>
 * 
 * @author huangjx
 */
public interface ICoupRSV {
 
	/**
     * 
     * TODO 优惠券规则新增. 
     * Date:2015-10-23下午4:36:46  <br>
     * @author huanghe5
     * @return 0:表示优惠券  否则是 优惠码 
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#saveCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public String saveCoup(CoupReqDTO coupReqDTO)
            throws BusinessException;
    
    /**
     * 优惠券规则编辑
     * @param coupReqDTO
     * @throws BusinessException
     * @return 0:表示优惠券  否则是 优惠码 
     * @author huanghe5
     */
    public String saveEditCoup(CoupReqDTO coupReqDTO) throws BusinessException;
    
  /**
   * 
   * deleteBatchCoup:优惠券信息批量删除
   * 
   * @author huanghe5
   * @param coupReqDTO
   * @throws BusinessException 
   * @since JDK 1.7
   */
    public void deleteBatchCoup(CoupReqDTO coupReqDTO)
            throws BusinessException;

    /**
     * 
     * TODO 优惠券信息生效. 
     * Date:2015-10-26下午8:16:18  <br>
     * @author huanghe5
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#validCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public void validCoup(CoupReqDTO coupReqDTO)
            throws BusinessException;
    /**
     * 优惠券查询
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huanghe5
     */
    public PageResponseDTO<CoupInfoRespDTO> queryCoupInfoPage(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    /**
     * 优惠券查询
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException
     * @author huangjx
     */
    public CoupInfoRespDTO queryCoupInfo(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    
    /**
     * 
     * queryCoupById:查询有效的优惠券信息Bean. <br/> 
     * 
     * @author huanghe5
     * @param coupInfoReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.7
     */
    public CoupInfoRespDTO queryCoupById(CoupInfoReqDTO coupInfoReqDTO) throws BusinessException;
    
    
    /**
     * 
     * TODO 优惠券信息失效. 
     * Date:2015-10-26下午8:16:18  <br>
     * @author huanghe5
     * @see com.zengshi.ecp.coupon.dubbo.interfaces.ICoupRSV#validCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
     */
    public void invalidCoup(CoupReqDTO coupReqDTO) throws BusinessException;
    
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
	public List<CoupInfoRespDTO> queryCoupInfoList(List<Long> coupInfoBeans) throws BusinessException;
	 
	 /**
		 * 
		 * TODO 系统产生优惠码. 
		 * Date:2016-10-6  <br>
		 * @author lisp
		 * @return 
		 * @see com.zengshi.ecp.coupon.service.busi.coupUse.interfaces.ICoupInfoSV#saveCoup(com.zengshi.ecp.coupon.dubbo.dto.req.CoupReqDTO)
		 */
		public String coupCode( ) throws BusinessException ;
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
}
