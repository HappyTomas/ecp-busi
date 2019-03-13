package com.zengshi.ecp.unpf.dubbo.interfaces.gdssend;

import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.unpf.dao.model.UnpfGdsSend;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendLogRespDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendReqDTO;
import com.zengshi.ecp.unpf.dubbo.dto.gdssend.UnpfGdsSendRespDTO;

import java.util.List;

/**
* @author  lisp: 
* @date 创建时间：2016年11月16日 下午2:28:05 
* @version 1.0 
**/
public interface IUnpfGoodSendRSV {

	
	/**
     * 
     * queryGdsInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
     *  待推送商品查询
     * 
     * @author lisp
     * @param gdsInfoReqDTO
     * @return
     * @since JDK 1.6
     */
	public PageResponseDTO<UnpfGdsSendRespDTO> queryGdsInfoListPage(GdsInfoReqDTO gdsInfoReqDTO);
	
	/**
     * 
     * queryGdsLogInfoListPage:(根据条件查询对应的商品列表，分页). <br/>
     * 商品推送记录查询
     * 
     * @author lisp
     * @param
     * @return
     * @since JDK 1.6
     */
	public PageResponseDTO<UnpfGdsSendLogRespDTO> queryGdsLogInfoListPage(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;

	/**
	 * 查询推送商品信息
	 * @param unpfGdsSendReqDTO
	 * @return
	 * @throws BusinessException
	 */
	public List<UnpfGdsSendLogRespDTO> querySends(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;

	/**
	 * 修改对应关系提交
	 * @param unpfGdsSendReqDTO
	 * @throws BusinessException
	 */
	public void updateGdsSendSubmit(UnpfGdsSendReqDTO unpfGdsSendReqDTO) throws BusinessException;
}


