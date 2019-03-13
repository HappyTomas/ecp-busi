package com.zengshi.ecp.im.dubbo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.OnlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.common.interfaces.ICustServiceMgrSV;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

/**
 * Created by zhangys on 16/10/5.
 */
public class CustServiceMgrRSVImpl implements ICustServiceMgrRSV {


    @Resource
    private ICustServiceMgrSV custServiceMgrSV;
    
    @Resource
    private IStaffHotlineSV hotlineSV;
    
    @Resource
    private IStaffHotlineSV staffHotlineSV;
    
    
    public ImStaffHotlineResDTO getStaffHotline(ImCustReqDTO dto)
        throws  BusinessException {
        return custServiceMgrSV.getStaffHotline(dto);
    }

    public ImStaffHotlineResDTO finishChat(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
    	SessionReqDTO sessionReqDTO = new SessionReqDTO();
    	sessionReqDTO.setUserCode(dto.getOfStaffCode());
    	sessionReqDTO.setStatus(ImConstants.STATE_0);
    	hotlineSV.updateSession(sessionReqDTO);
    	return custServiceMgrSV.finishChat(dto);
    }

    public ImStaffHotlineResDTO staffLogin(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
    	ImStaffHotlineResDTO dto2 = null;
    	int i = hotlineSV.updateHotlineOnlineStart(dto);
    	if(i>0){
    		dto2 = custServiceMgrSV.staffLogin(dto);
    	}
        return dto2;
    }

    public ImStaffHotlineResDTO staffLogout(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
    	SessionReqDTO sessionReqDTO = new SessionReqDTO();
    	sessionReqDTO.setCsaCode(dto.getCsaCode());
    	sessionReqDTO.setStatus(ImConstants.STATE_0);
    	hotlineSV.updateSession(sessionReqDTO);
    	
    	/*2017-02-14 离线时，增加一条离线记录*/
    	//获取客服资料
		ImStaffHotlineResDTO dto2 = staffHotlineSV.getHotlineByStaff(dto);
		
		OnlineReqDTO onlineReqDTO = new OnlineReqDTO();
		BasicDBObject query = new BasicDBObject();
		query.put("csaCode", dto.getCsaCode());
		DBObject stuFound = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").findOne(query);
		stuFound.put("onlineStatus", "0");
		stuFound.put("updateTime", DateUtil.getSysDate());
		WriteResult result = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").update(query, stuFound);
		onlineReqDTO.setOnlineStatus("0");
		onlineReqDTO.setCsaCode(dto.getCsaCode());
		if (result.getN() > 0) {
			JSONObject doc = new JSONObject();
			onlineReqDTO.setCreateTime(DateUtil.getSysDate());
			onlineReqDTO.setUpdateTime(DateUtil.getSysDate());
			onlineReqDTO.setShopId(dto2.getShopId());
			Map map = stuFound.toMap();
			onlineReqDTO.setHotlineId((Long) map.get("hotlineId"));
			onlineReqDTO.setResource("WEB");
			doc = (JSONObject) JSON.toJSON(onlineReqDTO);
			doc.remove("staff");
			doc.remove("local");
			MongoUtil.insert("T_IM_HOTLINE_ONLINE_LOG", doc);

		}

        return custServiceMgrSV.staffLogout(dto);
    }

    public ImStaffHotlineResDTO alterStaffLineStatus(ImStaffHotlineReqDTO dto)
        throws  BusinessException {

        return custServiceMgrSV.alterStaffLineStatus(dto);
    }

    public ImStaffHotlineResDTO getWaitCount(ImStaffHotlineReqDTO dto)
        throws  BusinessException {
        return custServiceMgrSV.getWaitCount(dto);
    }

    @Override 
    public ImStaffHotlineResDTO staffFinishChat(
        ImStaffHotlineReqDTO dto) {
        return custServiceMgrSV.staffFinishChat(dto);
    }
    
    /**
     * 清理im缓存
     * @see com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV#cleanImCache()
     */
    @Override
    public void cleanImCache() throws BusinessException{
    	custServiceMgrSV.cleanImCache();
    }
    
    /**
     * 查找客服对应的买家会员编号
     * @see com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV#findCustCodes(java.lang.String)
     */
    @Override
    public List<String> findCustCodes(String csaCode) throws BusinessException {
    	return custServiceMgrSV.findCustCodes(csaCode);
    }
    
    /**
     * 
     * 判断用户是否接入客服
     * @see com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV#isCustJoinIn(com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO)
     */
    @Override
    public boolean isCustJoinIn(ImCustReqDTO custReqDTO) throws BusinessException {
    	return custServiceMgrSV.isCustJoinIn(custReqDTO);
    }
    
    /**
     * 
     * 用户转入转出. 
     * @see com.zengshi.ecp.im.dubbo.interfaces.ICustServiceMgrRSV#rollInRollOut(com.zengshi.ecp.im.dubbo.dto.ImCustReqDTO)
     */
    @Override
    public void rollInRollOut(ImCustReqDTO custReqDTO) throws BusinessException {
    	custServiceMgrSV.rollInRollOut(custReqDTO);
    }

	@Override
	public void reconnectionSer(ImCustReqDTO custReqDTO) throws BusinessException {
		custServiceMgrSV.reconnectionSer(custReqDTO);
	}
}
