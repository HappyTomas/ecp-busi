package com.zengshi.ecp.im.service.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zengshi.ecp.im.dao.model.ImCustInfo;
import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.dubbo.util.ImErrorConstants;
import com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.im.service.util.DateTimeUtil;
import com.zengshi.ecp.im.service.util.ImCustInfoCmp;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.CacheUtil;
import com.zengshi.paas.utils.StringUtil;

/**
 * Created by zhangys on 16/10/5.
 */
public class CustServiceMgrSVImpl implements ICustServiceMgrSV {

    private static final Logger logger = LoggerFactory.getLogger(CustServiceMgrSVImpl.class);

    @Resource
    private IStaffHotlineSV staffHotlineSV;

    private static final Object synObj = new Object();

    private static final String IM_CUST_QUEUE_KEY = "ECP.IM.CUST.QUEUE";//买家会员队列

    private static final String IM_CUST_WAITING_KEY = "ECP.IM.CUST_WAITING";//买家会员等待集合
    private static final String IM_CUST_WAITING_LIST_KEY = "ECP.IM.CUST_WAITING_LIST";//买家会员等待列表
    private static final String IM_STAFF_IDLE_KEY = "ECP.IM.STAFF.IDLE";//闲的客服有序集合
    private static final String IM_STAFF_BUSY_KEY = "ECP.IM.STAFF_BUSY";//忙的客服有序集合
    private static final String IM_STAFF_CUST_SET_KEY = "ECP.IM.STAFF.CUST.SET";//客服和买家会员已经建立聊天关系的集合
    private static final String IM_STAFF_CUST_CREATED_TIME_KEY = "ECP.IM.STAFF.CUST.CREATED.TIME";//客服和买家会员已经建立聊天关系的时间
    private static final String IM_CUST_STAFF_MAP_KEY = "ECP.IM.CUST.STAFF.MAP";//买家会员和客服已经建立聊天关系的集合
    private static final String IM_STAFF_MAX_SERVICE_COUNT = "ECP.IM.STAFF.MAX.SERVICE.COUNT";//客服最大接入人数
    private static final String IM_STAFF_CURR_LINE_COUNT = "ECP.IM.STAFF.CURR_LINE.COUNT";//客服当前已经接入人数
    private static final String IM_STAFF_SHOP_MAP_KEY = "ECP.IM.STAFF.SHOP_MAP";//客服和店铺关系集合
    private static final String IM_CUST_SHOP_MAP_KEY = "ECP.IM.CUST.SHOP_MAP";//买家会员和店铺关系集合
    
    /**
     * 启动时是否清理im缓存
     */
    private boolean startToCleanCache;
    
    /**
     * 是否开启清理失效队列线程
     */
	private boolean startDealInvalidCustQueueThead = true;
	
	/**
	 * 清理间隔时间
	 */
	private static final int DEAL_INTERVAL_TIME = 5000;
    
    /**
     * 
     * @see com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV#init()
     */
    @Override
    public void init() throws BusinessException{
    	if(isStartToCleanCache()){
    		cleanImCache();
    	}
    	if(isStartDealInvalidCustQueueThead()){
    		DealInvalidCustQueueThread dealThread = new DealInvalidCustQueueThread();
    		new Thread(dealThread).start();
    	}
    }
    
    /**
     * 
     * @see com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV#cleanImCache()
     */
    @SuppressWarnings("unchecked")
    @Override
    public void cleanImCache() throws BusinessException{
    	logger.info("custservicequeuemgr:调用服务cleanImCache开始,清理im缓存");
    	// key前缀
    	String imPreKeys = "ECP.IM.";
    	// 遍历key, 删除key
    	List<String> delKeys = CacheUtil.keys(imPreKeys + "*");
		if(CollectionUtils.isNotEmpty(delKeys)){
			for(String delKey: delKeys){
				CacheUtil.delItem(delKey);
			}
		}
    	logger.info("custservicequeuemgr:调用服务cleanImCache结束,清理im缓存");
    }
    
    /**
     * 
     * 查找客服对应的买家会员编号. 
     * @see com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV#findCustCodes(java.lang.String)
     */
    @Override
    public List<String> findCustCodes(String csaCode) throws BusinessException {
    	List<String> custCodes = new ArrayList<String>();
    	if(StringUtil.isBlank(csaCode)){
    		logger.error("findCustCodes:查找客服对应的买家会员编号, 客服编号为空");
    		return custCodes;
    	}
    	Long shopId = (Long) CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY, csaCode);
    	if(null == shopId){
    		return custCodes;
    	}
    	Set<String> custSet = CacheUtil.getSet(IM_STAFF_CUST_SET_KEY + shopId + csaCode);
    	if(CollectionUtils.isEmpty(custSet)){
    		return custCodes;
    	}
    	return new ArrayList<String>(custSet);
    }
    
    /**
     * 
     * 判断用户是否接入客服. 
     * @see com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV#isCustJoinIn(com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO)
     */
    @Override
    public boolean isCustJoinIn(ImCustReqDTO dto) throws BusinessException {
    	if(dto == null || StringUtil.isBlank(dto.getOfStaffCode())){
    		logger.error("isCustJoinIn:判断用户是否接入客服, 用户为空");
    		return false;
    	}
    	String ofStaffCode = dto.getOfStaffCode(); 
    	Long shopId = (Long) CacheUtil.hgetItem(IM_CUST_SHOP_MAP_KEY, ofStaffCode);
    	if(shopId == null){
    		logger.info("isCustJoinIn:判断用户是否接入客服,查找到的shopId为空");
    		return false;
    	}
    	String csaCode = (String) CacheUtil.hgetItem(IM_CUST_STAFF_MAP_KEY + shopId, ofStaffCode);
    	return StringUtil.isNotBlank(csaCode);
    }
    
    /**
     * 用户转入转出. 
     * @see com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV#rollInRollOut(com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO)
     */
    @Override
    public void rollInRollOut(ImCustReqDTO dto) throws BusinessException{
    	// 买家会员编号
    	String ofStaffCode = dto.getOfStaffCode();
    	// 原客服编号
    	String srcCsaCode = dto.getSrcCsaCode();
    	// 目标客服编号
    	String destCsaCode = dto.getDestCsaCode();
    	if (StringUtil.isBlank(ofStaffCode) || StringUtil.isBlank(srcCsaCode) || StringUtil.isBlank(destCsaCode)) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600010);
        }
    	Long shopId = (Long) CacheUtil.hgetItem(IM_CUST_SHOP_MAP_KEY, ofStaffCode);
    	if(shopId == null){
    		logger.error("rollInRollOut:用户转入转出,查找到的shopId为空");
    		throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600015);
    	}
    	// 1.删除原客服的买家会员对应关系
        CacheUtil.hdelItem(IM_CUST_STAFF_MAP_KEY + shopId, ofStaffCode);
        CacheUtil.hdelItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + srcCsaCode, ofStaffCode);
        CacheUtil.sRemMember(IM_STAFF_CUST_SET_KEY + shopId + srcCsaCode, ofStaffCode);
        // 判断原客服是否是忙碌状态，需置为空闲状态
        Integer currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode);
        --currLineCount;
        CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode, currLineCount);
        currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode);
        String busyStaffState = (String) CacheUtil.hgetItem(IM_STAFF_BUSY_KEY + shopId, srcCsaCode);
        if (StringUtil.isNotBlank(busyStaffState)) {
            CacheUtil.hdelItem(IM_STAFF_BUSY_KEY + shopId, srcCsaCode);
            CacheUtil.zaddItem(IM_STAFF_IDLE_KEY + shopId, srcCsaCode, currLineCount);
        }
    	// 2.买家会员的对应关系转入新的客服
    	CacheUtil.hsetItem(IM_CUST_STAFF_MAP_KEY + shopId, ofStaffCode, destCsaCode);
        CacheUtil.saddStringItem(IM_STAFF_CUST_SET_KEY + shopId + destCsaCode, ofStaffCode);
        CacheUtil.hsetItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + destCsaCode, ofStaffCode, System.currentTimeMillis());
    }
    
    
    /**
     * 清除失效队列线程
     */
    class DealInvalidCustQueueThread implements Runnable {
    	
    	@Override
		public void run() {
			while(true){
				// 1.获取客户排队列中的店铺id列表
				List<Long> shopIds = getShopIds();
	        	if(CollectionUtils.isEmpty(shopIds)){
	        		continue;
	        	}
	        	// 2.清除失效队列
	        	synchronized(synObj) {
	        		dealInvalidCustQueue(shopIds);
	        	}
				// 3.休眠5秒
				try {
					Thread.sleep(DEAL_INTERVAL_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
    	
    	/**
    	 * 
    	 * dealInvalidCustQueue:(清理失效队列). <br/> 
    	 * 
    	 * @author ruanzz  
    	 * @since JDK 1.6
    	 */
		private void dealInvalidCustQueue(List<Long> shopIds) {
        	for(Long shopId: shopIds){
        		Set<String> waitingCustSet = CacheUtil.hkeys(IM_CUST_WAITING_KEY + shopId);
                if (waitingCustSet != null && waitingCustSet.size() > 0) {
                    Long currTime = System.currentTimeMillis();
                    List<ImCustInfo> delList = new ArrayList<>();
                    for (String ofStaffCode : waitingCustSet) {
                        Long reqTime = (Long) CacheUtil.hgetItem(IM_CUST_WAITING_KEY + shopId, ofStaffCode);
                        if (DateTimeUtil.time1AfterTime2(currTime,reqTime,ImConstants.QUEUE_INVALID_SECONDS*1000)) {
                            CacheUtil.hdelItem(IM_CUST_WAITING_KEY + shopId, ofStaffCode);
                            CacheUtil.zdelItem(IM_CUST_WAITING_LIST_KEY + shopId,ofStaffCode);
                            ImCustInfo o = new ImCustInfo();
                            o.setOfStaffCode(ofStaffCode);
                            o.setShopId(shopId);
                            delList.add(o);
                        }
                    }
                    if (delList.size() > 0) {
                        PriorityQueue<ImCustInfo> pqCust = getCustQueue(String.valueOf(shopId));
                        pqCust.removeAll(delList);
                        CacheUtil.hsetItem(IM_CUST_QUEUE_KEY, String.valueOf(shopId), pqCust);
                    }
                }else{
                	CacheUtil.hdelItem(IM_CUST_QUEUE_KEY, String.valueOf(shopId));
                }
        	}
        }
    	
    	/**
    	 * 
    	 * getShopIds:(获取店铺id列表). <br/> 
    	 * 
    	 * @author ruanzz 
    	 * @return 
    	 * @since JDK 1.6
    	 */
    	private List<Long> getShopIds(){
    		Set<String> shopIdStrs = CacheUtil.hkeys(IM_CUST_QUEUE_KEY);
    		if(CollectionUtils.isEmpty(shopIdStrs)){
    			return null;
    		}
    		List<Long> shopIds = new ArrayList<Long>();
    		for(String shopIdStr : shopIdStrs){
    			Long shopId = Long.parseLong(shopIdStr);
    			shopIds.add(shopId);
    		}
    		return shopIds;
    	}
	}

    @SuppressWarnings("unchecked")
	private static PriorityQueue<ImCustInfo> getCustQueue(String shopId) {
        Object tmp = CacheUtil.hgetItem(IM_CUST_QUEUE_KEY,shopId);
        PriorityQueue<ImCustInfo> custQueue = null;
        if (null != tmp) {
            custQueue = (PriorityQueue<ImCustInfo>)tmp;
        } else {
            ImCustInfoCmp cmp = new ImCustInfoCmp();
            custQueue = new PriorityQueue<>(100, cmp);
        }
        return custQueue;
    }


    public ImStaffHotlineResDTO getStaffHotline(ImCustReqDTO dto) throws  BusinessException {
//        logger.debug("custservicequeuemgr:调用服务getStaffHotline开始,获取在线客服入参信息:{}",dto);
        checkCustParam(dto);
        ImStaffHotlineResDTO resDTO = null;
        //先获取买家会员
        ImCustInfo outCust = new ImCustInfo(dto);
        resDTO = new ImStaffHotlineResDTO();
        Long shopId = dto.getShopId();
        synchronized(synObj) {
            CacheUtil.hsetItem(IM_CUST_SHOP_MAP_KEY, dto.getOfStaffCode(), shopId);
            String oldCsaCode = (String) CacheUtil.hgetItem(IM_CUST_STAFF_MAP_KEY + shopId, dto.getOfStaffCode());
            if (null != oldCsaCode) {
                logger.warn("custservicequeuemgr:调用服务getStaffHotline结束,没有结束会话,重复发起请求获取在线客服操作!");
                resDTO.setCsaCode(oldCsaCode);
                resDTO.setOfStaffCode(dto.getOfStaffCode());
                resDTO.setShopId(shopId);
                resDTO.setWaitCount(ImConstants.NUM_ZERO);
                return resDTO;
            }
            ImCustInfo cust = null;
            PriorityQueue<ImCustInfo> pqCust = getCustQueue(shopId + "");
            //            logger.debug("店铺:{}原来买家会员队列信息 :{}", shopId, pqCust);
            if (!pqCust.contains(outCust)) {
                pqCust.offer(outCust);
                CacheUtil.hsetItem(IM_CUST_QUEUE_KEY, shopId + "", pqCust);
            }
            cust = pqCust.peek();
//            logger.debug("custservicequeuemgr:店铺:{}增加当前请求的买家会员:{} 后队列信息:{},outCust:{}", shopId, cust, pqCust);
            Set<String> idleStaffSet = CacheUtil.zgetItems(IM_STAFF_IDLE_KEY + shopId);
//            logger.debug("custservicequeuemgr:店铺:{}下空闲客服集合:{},买家会员队列:{},请求接入会员:{}", shopId, idleStaffSet, pqCust, outCust);
            if (null != idleStaffSet && idleStaffSet.size() > 0 && cust.equals(outCust)) {

                if (!dto.getBusinessType().equals(ImConstants.BUSINESS_TYPE_0)) {
                    //不是综合,需要查找继承关系的分配
                    SessionReqDTO sessionReqDTO = new SessionReqDTO();
                    sessionReqDTO.setUserCode(dto.getOfStaffCode());
                    sessionReqDTO.setIssueType(dto.getBusinessType().toString());
                    if (dto.getBusinessType().equals(ImConstants.BUSINESS_TYPE_ORDER)) {
                        sessionReqDTO.setOrdId(dto.getBusinessId());
                    } else {
                        sessionReqDTO.setGdsId(dto.getBusinessId());
                    }

                    SessionResDTO sessionResDTO = staffHotlineSV.getSession(sessionReqDTO);
//                    logger.debug("custservicequeuemgr:根据入参:{},调用服务staffHotlineSV.getSession获取到的以前会话信息:{}", sessionReqDTO, sessionResDTO);
                    if (sessionResDTO != null && StringUtil.isNotBlank(sessionResDTO.getCsaCode()) && idleStaffSet.contains(sessionResDTO.getCsaCode())) {
                        //如果从数据库表查询到满足条件记录
//                        logger.debug("custservicequeuemgr:店铺:{},买家会员:{},根据继承原则获取绩效客服:{}.", shopId, dto.getOfStaffCode(), sessionResDTO.getCsaCode());
                        resDTO = getStaffHotline(cust, shopId, sessionResDTO.getCsaCode(), dto.getOfStaffCode());
//                        logger.debug("custservicequeuemgr:调用服务getStaffHotline结束,店铺:{},买家会员:{}取到客服:{}", shopId, dto.getOfStaffCode(), resDTO);
                    } else {
//                        logger.debug("custservicequeuemgr:店铺:{},买家会员:{},非继承原则获取绩效客服.", shopId, dto.getOfStaffCode());
                        resDTO = getNotInheritStaffHotline(dto, cust, idleStaffSet);
//                        logger.debug("custservicequeuemgr:调用服务getStaffHotline结束,店铺:{},买家会员:{}取到客服:{}", shopId, dto.getOfStaffCode(), resDTO);
                    }
                } else {  //通过非继承性分配客服人员
//                    logger.debug("custservicequeuemgr:店铺:{},买家会员:{},非继承原则获取客服.", shopId, dto.getOfStaffCode());
                    resDTO = getNotInheritStaffHotline(dto, cust, idleStaffSet);
//                    logger.debug("custservicequeuemgr:调用服务getStaffHotline结束,店铺:{},买家会员:{}取到客服:{}", shopId, dto.getOfStaffCode(), resDTO);
                }
            } else {

                int waitCount = getCurrCustWaitCount(shopId, dto.getOfStaffCode());
                resDTO.setWaitCount(waitCount);
                if (waitCount != ImConstants.SHOP_STAFF_QUEUE_NULL) {
                    CacheUtil.hsetItem(IM_CUST_WAITING_KEY + shopId, dto.getOfStaffCode(), System.currentTimeMillis());
                    if (null == CacheUtil.zrank(IM_CUST_WAITING_LIST_KEY + shopId, dto.getOfStaffCode())) {
                        CacheUtil.zaddItem(IM_CUST_WAITING_LIST_KEY + shopId, dto.getOfStaffCode(), System.currentTimeMillis());
                    }
                } else {
                    pqCust = getCustQueue(shopId + "");
                    pqCust.remove(outCust);
                    CacheUtil.hsetItem(IM_CUST_QUEUE_KEY, shopId + "", pqCust);
                }
//                logger.debug("custservicequeuemgr:调用服务getStaffHotline结束,店铺:{},买家会员:{}取到等待信息:{}", shopId, dto.getOfStaffCode(), resDTO);
            }
        }
        return resDTO;
    }

    private ImStaffHotlineResDTO getNotInheritStaffHotline(ImCustReqDTO dto,
                                                           ImCustInfo cust,
                                                           Set<String> idleStaffSet) {
        String[] idleStaffList = new String[idleStaffSet.size()];
        idleStaffSet.toArray(idleStaffList);
//        logger.debug("custservicequeuemgr:店铺:{}当前空闲的客服集合:{},列表:{}",dto.getShopId(),idleStaffSet,idleStaffList);
        return getStaffHotline(cust,dto.getShopId(),idleStaffList[0],dto.getOfStaffCode());
    }

    private ImStaffHotlineResDTO getStaffHotline(ImCustInfo cust
        ,Long shopId,String csaCode,String ofStaffCode) {
        ImStaffHotlineResDTO resDTO = new ImStaffHotlineResDTO();
        CacheUtil.zincrby(IM_STAFF_IDLE_KEY+shopId,csaCode,1);
        Integer currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT+shopId,csaCode);
        ++currLineCount;
        Integer staffMaxServiceCount = (Integer) CacheUtil.hgetItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, csaCode);
        // 客服最大接入数限制
        if(currLineCount.intValue() > staffMaxServiceCount.intValue()){
        	int waitCount = getCurrCustWaitCount(shopId, ofStaffCode);
            resDTO.setWaitCount(waitCount);
            return resDTO;
        }
        CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT + shopId, csaCode, currLineCount);
//        logger.debug("custservicequeuemgr:店铺:{}客服:{}的当前接入数:{},最大可接入数:{}", shopId, csaCode, currLineCount, staffMaxServiceCount);
        if (currLineCount.equals(staffMaxServiceCount)) {
//            logger.debug("custservicequeuemgr:店铺:{}客服:{}已经达到最大接入数,客服进入忙队列",shopId,csaCode);
            CacheUtil.zdelItem(IM_STAFF_IDLE_KEY + shopId,csaCode);
            CacheUtil.hsetItem(IM_STAFF_BUSY_KEY+shopId,csaCode,ImConstants.STATE_1);
        }
        resDTO.setShopId(shopId);
        resDTO.setOfStaffCode(ofStaffCode);
        resDTO.setCsaCode(csaCode);
        resDTO.setWaitCount(ImConstants.NUM_ZERO);
        PriorityQueue<ImCustInfo> pqCust = getCustQueue(shopId + "");
        pqCust.remove(cust);
        CacheUtil.hsetItem(IM_CUST_QUEUE_KEY, shopId + "", pqCust);
        CacheUtil.hdelItem(IM_CUST_WAITING_KEY + shopId, ofStaffCode);
        CacheUtil.zdelItem(IM_CUST_WAITING_LIST_KEY + shopId, ofStaffCode);
        CacheUtil.hsetItem(IM_CUST_STAFF_MAP_KEY+shopId,ofStaffCode,csaCode);
        CacheUtil.saddStringItem(IM_STAFF_CUST_SET_KEY + shopId + csaCode, ofStaffCode);
        CacheUtil.hsetItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + csaCode, ofStaffCode, System.currentTimeMillis());
//        Set<String> custSet = CacheUtil.getSet(IM_STAFF_CUST_SET_KEY+shopId+csaCode);
//        logger.debug("custservicequeuemgr:店铺:{}客服人员:{}接入的买家会员是:{}", shopId, csaCode, custSet);
//        logger.debug("custservicequeuemgr:店铺:{}当前买家会员等待队列:{}",shopId,pqCust);
        return resDTO;
    }

    private int getCurrCustWaitCount(Long shopId,String ofStaffCode) {
        int waitCount = 1;
        Set<String> busyStaffSet = CacheUtil.hkeys(IM_STAFF_BUSY_KEY + shopId);
        Set<String> waitingCustSet = CacheUtil.zgetItems(IM_CUST_WAITING_LIST_KEY + shopId);
        if ((null != busyStaffSet && busyStaffSet.size() > 0)
            || (null != waitingCustSet && waitingCustSet.size() > 0)) {
            if (null != waitingCustSet && waitingCustSet.size() > 0) {
//                logger.debug("custservicequeuemgr:店铺:{}客服忙状态,等待买家会员:{}", shopId, waitingCustSet);
                for (String cust : waitingCustSet) {
                    if (cust.equals(ofStaffCode)) {
                        break;
                    }
                    waitCount++;
                }
            }
        } else {
//            logger.debug("custservicequeuemgr:店铺:{}客服全部挂起或不在线状态",shopId);
            waitCount = ImConstants.SHOP_STAFF_QUEUE_NULL;
        }
        return waitCount;
    }


    public ImStaffHotlineResDTO staffFinishChat(ImStaffHotlineReqDTO dto) {

        logger.debug("custservicequeuemgr:调用服务staffFinishChat开始,入参:{}",dto);
        checkStaffParam(dto);
        if (StringUtil.isBlank(dto.getOfStaffCode()) ) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600008);
        }

        Long shopId = (Long) CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY, dto.getCsaCode()); //tmpResDTO.getShopId();
        String csaCode = (String) CacheUtil.hgetItem(IM_CUST_STAFF_MAP_KEY + shopId, dto.getOfStaffCode());
        boolean isInSet = CacheUtil.sIsMember(IM_STAFF_CUST_SET_KEY + shopId + dto.getCsaCode(), dto.getOfStaffCode());
        if (StringUtil.isBlank(csaCode) || !isInSet) {
            logger.warn("custservicequeuemgr:调用服务staffFinishChat不能正常释放资源,店铺:{},客服:{},买家会员:{},聊天会话已经不存在,不需要再结束.", shopId, dto.getCsaCode(), dto.getOfStaffCode());
            ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
            res.setStatus(ImConstants.STATE_0);
            return res;
        }
        synchronized (synObj) {
            Object relCsaCode = CacheUtil.hgetItem(IM_CUST_STAFF_MAP_KEY + shopId, dto.getOfStaffCode());
            if (StringUtil.isEmpty(relCsaCode)) {
//                logger.warn("custservicequeuemgr:调用服务staffFinishChat不能正常释放资源,店铺:{},客服:{},买家会员:{},聊天会话已经不存在,不需要再结束.", shopId, dto.getCsaCode(), dto.getOfStaffCode());
                ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
                res.setStatus(ImConstants.STATE_0);
                return res;
            }
            Long getStaffTime = (Long) CacheUtil.hgetItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + dto.getCsaCode(), dto.getOfStaffCode());
            if ((null != dto.getReqTime() && dto.getReqTime() > 0)
                && (null != getStaffTime && getStaffTime > 0)) {
                Long finishChatTime = dto.getReqTime();
//                logger.debug("custservicequeuemgr:买家会员请求客服的时间:{},当前结束会话时间:{}", getStaffTime, finishChatTime);
                if (DateTimeUtil.time1AfterTime2(getStaffTime, finishChatTime)) {//getStaffTime > dto.getReqTime()) {
                    logger.warn("custservicequeuemgr:调用服务staffFinishChat不能正常释放资源,结束会话时间超时!");
                    ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
                    res.setStatus(ImConstants.STATE_0);
                    return res;
                }
            } else {
                logger.warn("custservicequeuemgr:调用服务staffFinishChat不能正常释放资源,店铺:{},客服:{},买家会员:{},聊天会话已经不存在,不需要再结束.", shopId, dto.getCsaCode(), dto.getOfStaffCode());
                ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
                res.setStatus(ImConstants.STATE_0);
                return res;
            }

            CacheUtil.hdelItem(IM_CUST_SHOP_MAP_KEY, dto.getOfStaffCode());
            CacheUtil.hdelItem(IM_CUST_STAFF_MAP_KEY + shopId, dto.getOfStaffCode());
            CacheUtil.hdelItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + dto.getCsaCode(), dto.getOfStaffCode());
            CacheUtil.sRemMember(IM_STAFF_CUST_SET_KEY + shopId + dto.getCsaCode(), dto.getOfStaffCode());

            Integer currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode());
//            logger.debug("custservicequeuemgr:结束会话前店铺:{}客服:{}接入线路数:{}", shopId, dto.getCsaCode(), currLineCount);
            --currLineCount;
            CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode(), currLineCount);
            currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode());
//            logger.debug("custservicequeuemgr:结束会话后店铺:{}客服:{}接入线路数:{}", shopId, dto.getCsaCode(), currLineCount);

            String busyStaffState = (String) CacheUtil.hgetItem(IM_STAFF_BUSY_KEY + shopId, dto.getCsaCode());
            if (StringUtil.isNotBlank(busyStaffState)) {
//                logger.debug("custservicequeuemgr:客服由忙状态变空闲状态");
                CacheUtil.hdelItem(IM_STAFF_BUSY_KEY + shopId, dto.getCsaCode());
                CacheUtil.zaddItem(IM_STAFF_IDLE_KEY + shopId, dto.getCsaCode(),currLineCount);
            }
        }
        ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
        res.setOfStaffCode(dto.getOfStaffCode());
        res.setShopId(shopId);
        res.setCsaCode(dto.getCsaCode());
        res.setStatus(ImConstants.STATE_1);
        logger.debug("custservicequeuemgr:调用服务staffFinishChat结束,出参:{}", res);
        return res;
    }

    public ImStaffHotlineResDTO finishChat(ImStaffHotlineReqDTO dto) throws BusinessException {
        // 买家会员编号
        String ofStaffCode = dto.getOfStaffCode();
        if (StringUtil.isBlank(ofStaffCode) ) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600008);
        }
        ImStaffHotlineResDTO resDTO = new ImStaffHotlineResDTO();
        resDTO.setOfStaffCode(ofStaffCode);
        Long shopId = (Long)CacheUtil.hgetItem(IM_CUST_SHOP_MAP_KEY, ofStaffCode); 
        if(shopId == null){
        	logger.info("custservicequeuemgr:调用服务finishChat, 获取会员对应的店铺id为空");
        	return resDTO;
        }
        ImCustInfo imCustInof = new ImCustInfo();
        imCustInof.setOfStaffCode(ofStaffCode);
        imCustInof.setShopId(shopId);
        synchronized (synObj) {
        	CacheUtil.hdelItem(IM_CUST_WAITING_KEY + shopId, ofStaffCode);
            CacheUtil.zdelItem(IM_CUST_WAITING_LIST_KEY + shopId, ofStaffCode);
        	removeCustFromQueue(imCustInof, shopId);
        }
        logger.debug("custservicequeuemgr:调用服务finishChat继续调用服务staffFinishChat,入参:{}",dto);
        return resDTO;
    }
    
    /**
     * 
     * removeCustFromQueue:(移除买家会员队列). <br/> 
     * 
     * @param custInfo
     * @param shopId 
     * @since JDK 1.6
     */
    private void removeCustFromQueue(ImCustInfo custInfo, Long shopId){
    	PriorityQueue<ImCustInfo> pqCust = getCustQueue(String.valueOf(shopId));
        if(CollectionUtils.isEmpty(pqCust)){
        	return;
        }
        pqCust.remove(custInfo);
        if(CollectionUtils.isNotEmpty(pqCust)){
        	CacheUtil.hsetItem(IM_CUST_QUEUE_KEY, String.valueOf(shopId), pqCust);
        }else{
        	CacheUtil.hdelItem(IM_CUST_QUEUE_KEY, String.valueOf(shopId));
        }
    }     
      

    public ImStaffHotlineResDTO staffLogin(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
        logger.debug("custservicequeuemgr:调用staffLogin开始,入参:{}",dto);
        checkStaffParam(dto);
        dto.setOfStaffCode(dto.getCsaCode());
        ImStaffHotlineResDTO tmpResDTO = staffHotlineSV.getHotlineByStaff(dto);
        if (null == tmpResDTO) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600001);
        }
        logger.debug("custservicequeuemgr:staffLogin中取到的ImStaffHotlineResDTO对象值:{}",tmpResDTO);
        Long shopId = tmpResDTO.getShopId();
        Long shopId2 = (Long)CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY,dto.getCsaCode());
        if (null != shopId2 && shopId.equals(shopId2)) {
            logger.warn("custservicequeuemgr:staffLogin,客服没有退出,不能再登录.");
            return new ImStaffHotlineResDTO();
        }
        CacheUtil.hsetItem(IM_STAFF_SHOP_MAP_KEY,dto.getCsaCode(),shopId);
        Integer staffReceptionCount = tmpResDTO.getReceptionCount().intValue();
        CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT+shopId,dto.getCsaCode(),0);
        CacheUtil.hsetItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, dto.getCsaCode(), staffReceptionCount);
        CacheUtil.zaddItem(IM_STAFF_IDLE_KEY+shopId,dto.getCsaCode(),0);
        Set<String> idleStaffSet = CacheUtil.zgetItems(IM_STAFF_IDLE_KEY+shopId);
        logger.debug("custservicequeuemgr:调用staffLogin结束,店铺:{}的客服:{}登录后,空闲客服集合:{}",shopId,dto.getCsaCode(),idleStaffSet);
        ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
        res.setCsaCode(dto.getCsaCode());
        res.setShopId(shopId);
        return res;
    }

    public ImStaffHotlineResDTO staffLogout(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
        logger.debug("custservicequeuemgr:调用staffLogout开始,入参:{}",dto);
        checkStaffParam(dto);

        Long shopId = (Long) CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY,dto.getCsaCode());
        synchronized (synObj) {
            CacheUtil.hdelItem(IM_STAFF_SHOP_MAP_KEY, dto.getCsaCode());
            CacheUtil.zdelItem(IM_STAFF_IDLE_KEY + shopId, dto.getCsaCode());
            CacheUtil.hdelItem(IM_STAFF_BUSY_KEY + shopId, dto.getCsaCode());
            DealCustForNoIdleOrBusyStaff(shopId);
            CacheUtil.hdelItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode());
            CacheUtil.hdelItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, dto.getCsaCode());
            Set<String> custSet = CacheUtil.getSet(IM_STAFF_CUST_SET_KEY + shopId + dto.getCsaCode());
//            logger.debug("custservicequeuemgr:调用staffLogout删除正在聊天的买家会员关系集合:{}", custSet);
            if (null != custSet && custSet.size() > 0) {
                for (String cust : custSet) {
                    CacheUtil.hdelItem(IM_CUST_STAFF_MAP_KEY + shopId, cust);
                    CacheUtil.hdelItem(IM_CUST_SHOP_MAP_KEY, cust);
                }
            }
            CacheUtil.delItem(IM_STAFF_CUST_SET_KEY + shopId + dto.getCsaCode());
            Set<String> custSet2 = CacheUtil.hkeys(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + dto.getCsaCode());
//            logger.debug("custservicequeuemgr:调用staffLogout删除正在聊天的买家会员创建时间关系集合:{}", custSet2);
            if (null != custSet2 && custSet2.size() > 0) {
                for (String cust : custSet2) {
                    CacheUtil.hdelItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + dto.getCsaCode(), cust);
                }
            }
        }
        Set<String> idleStaffSet = CacheUtil.zgetItems(IM_STAFF_IDLE_KEY+shopId);
        logger.debug("custservicequeuemgr:调用staffLogout结束,店铺:{}的客服:{}退出后,空闲客服集合:{}",shopId,dto.getCsaCode(),idleStaffSet);
        ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
        res.setCsaCode(dto.getCsaCode());
        res.setShopId(shopId);
        return res;
    }

    private void checkLineStatus(Integer lineStatus) throws  BusinessException{
        if (!lineStatus.equals(ImConstants.HANG) && !lineStatus.equals(ImConstants.OFFLINE)
            && !lineStatus.equals(ImConstants.ONLINE)) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600011);
        }
    }

    public ImStaffHotlineResDTO alterStaffLineStatus(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
        logger.debug("custservicequeuemgr:调用alterStaffLineStatus开始,入参:{}",dto);
        checkStaffParam(dto);
        checkLineStatus(dto.getLineStatus());
        Long shopId = (Long) CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY,dto.getCsaCode());
        synchronized (synObj) {
            if (dto.getLineStatus().equals(ImConstants.HANG)) {
//                logger.debug("custservicequeuemgr:调用alterStaffLineStatus挂起操作,将把店铺:{},客服:{},从闲和忙的队列移出.", shopId, dto.getCsaCode());
                CacheUtil.zdelItem(IM_STAFF_IDLE_KEY + shopId, dto.getCsaCode());
                CacheUtil.hdelItem(IM_STAFF_BUSY_KEY + shopId, dto.getCsaCode());
                DealCustForNoIdleOrBusyStaff(shopId);

            } else if (dto.getLineStatus().equals(ImConstants.ONLINE)) {
                Integer currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode());
                Integer maxServiceCount = (Integer) CacheUtil.hgetItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, dto.getCsaCode());
                // 空指针处理
                if(currLineCount == null){
                	currLineCount = 0;
                	CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT + shopId, dto.getCsaCode(), currLineCount);
                }
                if(maxServiceCount == null){
                	dto.setOfStaffCode(dto.getCsaCode());
                    ImStaffHotlineResDTO tmpResDTO = staffHotlineSV.getHotlineByStaff(dto);
                    if (null == tmpResDTO) {
                        throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600001);
                    }
                    maxServiceCount = new Integer(tmpResDTO.getReceptionCount());
                    CacheUtil.hsetItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, dto.getCsaCode(), maxServiceCount);
                }
                if (currLineCount.intValue() < maxServiceCount.intValue()) {
//                    logger.debug("custservicequeuemgr:调用alterStaffLineStatus上线操作,将把店铺:{},客服:{}加到闲队列.", shopId, dto.getCsaCode());
                    CacheUtil.zaddItem(IM_STAFF_IDLE_KEY + shopId, dto.getCsaCode(), currLineCount);//hadServiceCount);
                } else {
//                    logger.debug("custservicequeuemgr:调用alterStaffLineStatus上线操作,将把店铺:{},客服:{}加到忙队列.", shopId, dto.getCsaCode());
                    CacheUtil.hsetItem(IM_STAFF_BUSY_KEY + shopId, dto.getCsaCode(), ImConstants.STATE_1);
                }
            } else {
                throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600011);
            }
        }
        ImStaffHotlineResDTO res = new ImStaffHotlineResDTO();
        res.setCsaCode(dto.getCsaCode());
        res.setShopId(shopId);
        logger.debug("custservicequeuemgr:调用alterStaffLineStatus结束,出参:{}",res);
        return res;
    }

    private void DealCustForNoIdleOrBusyStaff(Long shopId) {
        Set<String> idelCustSet = CacheUtil.zgetItems(IM_STAFF_IDLE_KEY + shopId);
        Set<String> busyCustSet = CacheUtil.hkeys(IM_STAFF_BUSY_KEY + shopId);
        if ((null == idelCustSet || idelCustSet.size() <= 0)
            &&(null == busyCustSet || busyCustSet.size() <= 0)) {
            CacheUtil.delItem(IM_CUST_WAITING_KEY+ shopId);
            CacheUtil.delItem(IM_CUST_WAITING_LIST_KEY + shopId);
            CacheUtil.hdelItem(IM_CUST_QUEUE_KEY, shopId + "");
        }
    }

    private void checkStaffParam(ImStaffHotlineReqDTO dto) throws  BusinessException {

        if (StringUtil.isBlank(dto.getCsaCode())) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600003);
        }
    }

    private void checkCustParam(ImCustReqDTO dto) throws  BusinessException {

        if (dto.getBusinessType() == null) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600013);
        }
        if (!dto.getBusinessType().equals(ImConstants.BUSINESS_TYPE_0)) {
            if (null == dto.getBusinessId()) {
                throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600012);
            }
        }
        if (dto.getShopId() == null) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600007);
        }
        if (StringUtil.isBlank(dto.getOfStaffCode())) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600004);
        }
        if (dto.getCustLevel() == null || dto.getCustLevel().trim().equals("")) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600006);
        }
    }

    public ImStaffHotlineResDTO getWaitCount(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
//        logger.debug("custservicequeuemgr:调用服务getWaitCount开始,店铺:{}",dto.getShopId());
        Long shopId = dto.getShopId();
        if (shopId == null || shopId.equals(0L)) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600002);
        }
        ImStaffHotlineResDTO resDTO = new ImStaffHotlineResDTO();
        Integer custPQWaitCount = 0;
        Set<String> waitingCustSet = CacheUtil.hkeys(IM_CUST_WAITING_KEY+shopId);
//        logger.debug("custservicequeuemgr:店铺:{},等待队列中的买家会员:{}",shopId,waitingCustSet);
        if (null != waitingCustSet && waitingCustSet.size() > 0) {
            custPQWaitCount = waitingCustSet.size();
        }
        resDTO.setWaitCount(custPQWaitCount);
        resDTO.setShopId(shopId);
//        logger.debug("custservicequeuemgr:调用服务getWaitCount结束,店铺:{}",dto.getShopId());
        return resDTO;
    }

	public boolean isStartToCleanCache() {
		return startToCleanCache;
	}

	public void setStartToCleanCache(boolean startToCleanCache) {
		this.startToCleanCache = startToCleanCache;
	}

	public boolean isStartDealInvalidCustQueueThead() {
		return startDealInvalidCustQueueThead;
	}

	public void setStartDealInvalidCustQueueThead(boolean startDealInvalidCustQueueThead) {
		this.startDealInvalidCustQueueThead = startDealInvalidCustQueueThead;
	}

	@Override
	public void reconnectionSer(ImCustReqDTO custReqDTO) throws BusinessException {
		// 买家会员编号
    	String ofStaffCode = custReqDTO.getOfStaffCode();
    	// 原客服编号
    	String srcCsaCode = custReqDTO.getSrcCsaCode();
    	if (StringUtil.isBlank(ofStaffCode) || StringUtil.isBlank(srcCsaCode)) {
            throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600010);
        }
    	Long shopId = (Long) CacheUtil.hgetItem(IM_STAFF_SHOP_MAP_KEY, srcCsaCode);
    	if(shopId == null){
    		logger.error("rollInRollOut:客服重新连接,查找到的shopId为空");
    		throw new BusinessException(ImErrorConstants.Chatting.ERROR_CHATTING_600015);
    	}
    	  // 判断原客服是否是忙碌
        Integer currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode);
        currLineCount++;
        CacheUtil.hsetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode, currLineCount);
        currLineCount = (Integer) CacheUtil.hgetItem(IM_STAFF_CURR_LINE_COUNT + shopId, srcCsaCode);
        
        Integer staffMaxServiceCount = (Integer) CacheUtil.hgetItem(IM_STAFF_MAX_SERVICE_COUNT + shopId, srcCsaCode);
        if (currLineCount.equals(staffMaxServiceCount)) {
          CacheUtil.zdelItem(IM_STAFF_IDLE_KEY + shopId,srcCsaCode);
          CacheUtil.hsetItem(IM_STAFF_BUSY_KEY+shopId,srcCsaCode,ImConstants.STATE_1);
      }
       /* String busyStaffState = (String) CacheUtil.hgetItem(IM_STAFF_BUSY_KEY + shopId, srcCsaCode);*/
        /*if (StringUtil.isNotBlank(busyStaffState)) {
            CacheUtil.hdelItem(IM_STAFF_BUSY_KEY + shopId, srcCsaCode);
            CacheUtil.zaddItem(IM_STAFF_IDLE_KEY + shopId, srcCsaCode, currLineCount);
        }*/
    	// 2.原客服队列关系重组
    	CacheUtil.hsetItem(IM_CUST_STAFF_MAP_KEY + shopId, ofStaffCode, srcCsaCode);
        CacheUtil.saddStringItem(IM_STAFF_CUST_SET_KEY + shopId + srcCsaCode, ofStaffCode);
        CacheUtil.hsetItem(IM_STAFF_CUST_CREATED_TIME_KEY + shopId + srcCsaCode, ofStaffCode, System.currentTimeMillis());
	}
	
}
