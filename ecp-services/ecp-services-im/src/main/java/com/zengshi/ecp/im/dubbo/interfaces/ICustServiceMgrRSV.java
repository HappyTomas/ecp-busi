package com.zengshi.ecp.im.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Created by zhangys on 16/10/5.
 */
public interface ICustServiceMgrRSV {

    /**
     * 获取在线空闲客服人员信息,如果没有,如果返回需要等待的人数
     *
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO getStaffHotline(ImCustReqDTO dto)
        throws  BusinessException;

    /**
     * 客服人员登录接口
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO staffLogin(ImStaffHotlineReqDTO dto)
        throws  BusinessException;

    /**
     * 客服人员退出接口
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO staffLogout(ImStaffHotlineReqDTO dto)
        throws  BusinessException;

    /**
     * 客服人员状态修改
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO alterStaffLineStatus(ImStaffHotlineReqDTO dto)
        throws  BusinessException;

    /**
     * 买家会员和客服人员聊天结束,释放队列
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO finishChat(ImStaffHotlineReqDTO dto)
        throws  BusinessException;

    /**
     * 客服人员结束和买家会员聊天,释放队列
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO staffFinishChat(ImStaffHotlineReqDTO dto);


    /**
     * 获取指定店铺的队列等待人数
     * @param dto
     * @return
     * @throws BusinessException
     */
    ImStaffHotlineResDTO getWaitCount(ImStaffHotlineReqDTO dto)
        throws  BusinessException;
    
    /**
	 * 
	 * cleanImCache:(清理im缓存). <br/> 
	 * 
	 * @author ruanzz 
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	void cleanImCache() throws BusinessException;
	
	/**
	 * 
	 * findCustCodes:(查找客服对应的买家会员编号). <br/> 
	 * 
	 * @author ruanzz 
	 * @param csaCode
	 * @return
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	List<String> findCustCodes(String csaCode) throws BusinessException;
	
	/**
	 * 
	 * isCustJoinIn:(判断用户是否接入客服). <br/> 
	 * 
	 * @author ruanzz 
	 * @param custReqDTO
	 * @return true-用户已经接入客服, false-用户还未接入客服
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	boolean isCustJoinIn(ImCustReqDTO custReqDTO) throws BusinessException;
	
	/**
	 * 
	 * rollInRollOut:(用户转入转出). <br/> 
	 * 
	 * @author ruanzz 
	 * @param custReqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
	void rollInRollOut(ImCustReqDTO custReqDTO) throws BusinessException;
	
	 /**
     * 客服重新连接
     * @param custReqDTO
     * @return
     * @throws BusinessException
     */
    void reconnectionSer(ImCustReqDTO custReqDTO)throws BusinessException;
}
