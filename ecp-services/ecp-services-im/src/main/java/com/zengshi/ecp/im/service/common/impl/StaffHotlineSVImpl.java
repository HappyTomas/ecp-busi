package com.zengshi.ecp.im.service.common.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zengshi.ecp.frame.sequence.PaasSequence;
import com.zengshi.ecp.frame.vo.BaseCriteria;
import com.zengshi.ecp.im.dao.mapper.busi.ImOfuserRelIdxMapper;
import com.zengshi.ecp.im.dao.mapper.common.ImHotlineInfoMapper;
import com.zengshi.ecp.im.dao.mapper.common.ImStaffHotlineMapper;
import com.zengshi.ecp.im.dao.model.ImHotlineInfo;
import com.zengshi.ecp.im.dao.model.ImHotlineInfoCriteria;
import com.zengshi.ecp.im.dao.model.ImOfuserRelIdx;
import com.zengshi.ecp.im.dao.model.ImStaffHotline;
import com.zengshi.ecp.im.dao.model.ImStaffHotlineCriteria;
import com.zengshi.ecp.im.dao.model.ImStaffHotlineCriteria.Criteria;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImHotlineInfoResDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.ImStaffHotlineResDTO;
import com.zengshi.ecp.im.dubbo.dto.OnlineReqDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionReqDTO;
import com.zengshi.ecp.im.dubbo.dto.SessionResDTO;
import com.zengshi.ecp.im.dubbo.util.ImConstants;
import com.zengshi.ecp.im.service.common.interfaces.IOpenFireUserSV;
import com.zengshi.ecp.im.service.common.interfaces.IStaffHotlineSV;
import com.zengshi.ecp.im.service.util.ImUtil;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.front.util.BaseParamUtil;
import com.zengshi.ecp.server.service.impl.GeneralSQLSVImpl;
import com.zengshi.ecp.server.service.pagination.PaginationCallback;
import com.zengshi.ecp.staff.dubbo.dto.AuthStaffResDTO;
import com.zengshi.ecp.staff.dubbo.dto.ShopInfoResDTO;
import com.zengshi.ecp.staff.dubbo.interfaces.IAuthStaffRSV;
import com.zengshi.ecp.staff.dubbo.interfaces.IShopInfoRSV;
import com.zengshi.paas.utils.DateUtil;
import com.zengshi.paas.utils.MongoUtil;
import com.zengshi.paas.utils.ObjectCopyUtil;
import com.zengshi.paas.utils.StringUtil;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

/**
 * 获取用户关系
 * 
 * @author wangbh
 * 
 */
public class StaffHotlineSVImpl extends GeneralSQLSVImpl implements IStaffHotlineSV {

	private static String MODULE = StaffHotlineSVImpl.class.getName();


	@Resource
	private ImStaffHotlineMapper imStaffHotlineMapper;

	@Resource
	private ImHotlineInfoMapper imHotlineInfoMapper;

	@Resource
	private IAuthStaffRSV authStaffRSV;

	@Resource
	private ImOfuserRelIdxMapper imOfuserRelIdxMapper;
	
	@Resource
	private IShopInfoRSV iShopInfoRSV;
	
	@Resource
	private IOpenFireUserSV openFireUserSV;

	@Resource(name = "seq_hotline_info_id")
	private PaasSequence seqHotlineInfoId;

	@Override
	public ImStaffHotlineResDTO getHotlineByStaff(ImStaffHotlineReqDTO dto)
			throws BusinessException {
		ImStaffHotlineResDTO imStaffHotlineResDTO = new ImStaffHotlineResDTO();
		ImStaffHotlineCriteria example = new ImStaffHotlineCriteria();
		Criteria sql = example.createCriteria();

		if (StringUtil.isNotBlank(dto.getStaffClass())) {
			sql.andStaffClassEqualTo(dto.getStaffClass());
		}

		if (StringUtil.isNotBlank(dto.getStaffCode())) {
			sql.andStaffCodeEqualTo(dto.getStaffCode());
		}
		
		if(StringUtil.isNotBlank(dto.getOfStaffCode())){
			sql.andOfStaffCodeEqualTo(dto.getOfStaffCode());
		}
		
		if(StringUtil.isNotBlank(dto.getStatus())){
			sql.andStatusEqualTo(dto.getStatus());
		}
		
		if(null!=dto.getId()&&0!=dto.getId()){
			sql.andIdEqualTo(dto.getId());
		}
		List<ImStaffHotline> list = imStaffHotlineMapper
				.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			ObjectCopyUtil.copyObjValue(list.get(0), imStaffHotlineResDTO,
					null, false);
			ImHotlineInfo hotlineInfo = imHotlineInfoMapper.selectByPrimaryKey(imStaffHotlineResDTO.getId());
			imStaffHotlineResDTO.setShopId(hotlineInfo.getShopId());
			imStaffHotlineResDTO.setModuleType(hotlineInfo.getModuleType());
			imStaffHotlineResDTO.setHotlinePerson(hotlineInfo.getHotlinePerson());
			imStaffHotlineResDTO.setHotlinePhone(hotlineInfo.getHotlinePhone());
			imStaffHotlineResDTO.setReceptionCount(hotlineInfo.getReceptionCount());
			imStaffHotlineResDTO.setOrderEdit(hotlineInfo.getOrderEdit());
		} else {
			return null;
		}

		return imStaffHotlineResDTO;
	}

	@Override
	public int addHotlineStaff(ImStaffHotlineReqDTO dto)
			throws BusinessException {
		
		int i = 0;
		AuthStaffResDTO authStaffResDTO = authStaffRSV.findAuthStaffById(dto
				.getStaffId());
		
		ImStaffHotlineCriteria example = new ImStaffHotlineCriteria();
		example.createCriteria().andStaffCodeEqualTo(authStaffResDTO.getStaffCode());
		List<ImStaffHotline> list = imStaffHotlineMapper.selectByExample(example);
        if(null!=list&&list.size()>0){
               throw new BusinessException("客服已经存在");        	
        }
		
		Map<String, String> map = new HashMap<String, String>();
		String ofStaffcode = "csa_" + authStaffResDTO.getStaffCode();

			// 新增关系表
			Long holineid = seqHotlineInfoId.nextValue();
			ImStaffHotline record = new ImStaffHotline();
			ObjectCopyUtil.copyObjValue(authStaffResDTO, record, null, false);
			record.setStaffId(authStaffResDTO.getId());
			record.setId(holineid);
			record.setOfStaffCode(ofStaffcode);
			record.setStatus(ImConstants.STATE_1);
			record.setCreateStaff(dto.getStaff().getId());
			record.setCreateTime(DateUtil.getSysDate());
			i = imStaffHotlineMapper.insertSelective(record);

			if (i > 0) {
				// 新增im用户信息表
				ImHotlineInfo hotlineInfo = new ImHotlineInfo();
				ObjectCopyUtil.copyObjValue(dto, hotlineInfo, null, false);
				hotlineInfo.setId(holineid);
				hotlineInfo.setStatus(ImConstants.STATE_1);
				hotlineInfo.setCreateStaff(dto.getStaff().getId());
				hotlineInfo.setCreateTime(DateUtil.getSysDate());
				imHotlineInfoMapper.insertSelective(hotlineInfo);
				// 新增序列表
				ImOfuserRelIdx idx = new ImOfuserRelIdx();
				idx.setOfStaffCode(ofStaffcode);
				idx.setStaffCode(authStaffResDTO.getStaffCode());
				idx.setStaffId(authStaffResDTO.getId());
				imOfuserRelIdxMapper.insertSelective(idx);
				//新增实时状态表
				createOnlineStatus(authStaffResDTO,ofStaffcode);
			

	/*	try {
				//新增openfire用户
				ImUtil.addAccountManager(ofStaffcode,
						ImConstants.IM_USER_DEFAULT_PASSWORD, map);
		} catch (XMPPException e) {
			LogUtil.error(MODULE, e.getMessage(),e);
			throw new BusinessException(ImConstants.ERROR_600101);
		}*/
				openFireUserSV.addOfUser(ofStaffcode, ImConstants.IM_USER_DEFAULT_PASSWORD);
				
				
			}
		return i;
	}

	@Override
	public SessionResDTO getSession(SessionReqDTO dto) throws BusinessException {
		
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		BasicDBObject query = new BasicDBObject();
		if(StringUtil.isNotBlank(dto.getUserCode())){
			query.put("userCode", dto.getUserCode());
		}
		if(StringUtil.isNotBlank(dto.getIssueType())){
			query.put("issueType", dto.getIssueType());
			if(dto.getIssueType().equals(ImConstants.issueType_1)){
				query.put("ordId", dto.getOrdId());
			}else if(dto.getIssueType().equals(ImConstants.issueType_2)){
				query.put("gdsId", dto.getGdsId());
			}
		}
		
		if(StringUtil.isNotBlank(dto.getCsaCode())){
			query.put("csaCode", dto.getCsaCode());
		}
		
		if(StringUtil.isNotBlank(dto.getStatus())){
			query.put("status", dto.getStatus());
		}
		
		if(StringUtil.isNotBlank(dto.getId())){
			query.put("id", dto.getId());
		}
	
		DBCursor cursor =collection.find(query).sort(new BasicDBObject("sessionTime",-1));
		try {
			SessionResDTO resDTO = new SessionResDTO();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
			    ImUtil.dbObjectToBean(obj, resDTO);
				return resDTO;
			}
		} finally {
			cursor.close();
		}
		return null;
	}
	
	public void createOnlineStatus(AuthStaffResDTO authStaffResDTO,String ofStaffcode){
		
	    OnlineReqDTO reqVO = new OnlineReqDTO();
	    reqVO.setHotlineId(authStaffResDTO.getId());
	    reqVO.setCreateTime(DateUtil.getSysDate());
	    reqVO.setCsaCode(ofStaffcode);
	    reqVO.setOnlineStatus("0");
	    reqVO.setResource(ImConstants.RESOUREC_WEB);
	    reqVO.setUpdateTime(DateUtil.getSysDate());
		JSONObject doc = new JSONObject();
		doc = (JSONObject) JSON.toJSON(reqVO);
		MongoUtil.insert("T_IM_HOTLINE_ONLINE", doc);
		
	}

	@Override
	public PageResponseDTO<ImHotlineInfoResDTO> getHotlineList(ImHotlineInfoReqDTO dto) throws BusinessException {
		
		ImHotlineInfoCriteria example = new ImHotlineInfoCriteria();
		com.zengshi.ecp.im.dao.model.ImHotlineInfoCriteria.Criteria sql = example.createCriteria();

		
		if(null!=dto.getId()&&0!=dto.getId()){
			sql.andIdEqualTo(dto.getId());
		}
		
		if(StringUtil.isNotBlank(dto.getHotlinePerson())){
			sql.andHotlinePersonEqualTo(dto.getHotlinePerson());
		}
		
		
		if(StringUtil.isNotBlank(dto.getStatus())){
			sql.andStatusEqualTo(dto.getStatus());
		}
		
		if(StringUtil.isNotBlank(dto.getModuleType())){
			sql.andModuleTypeEqualTo(dto.getModuleType());
		}
		
		if(null!=dto.getShopId()){
			sql.andShopIdEqualTo(dto.getShopId());
		}
		if (StringUtil.isNotBlank(dto.getPlatSource())) {
			sql.andPlatSourceEqualTo(dto.getPlatSource());
		}
		
		example.setLimitClauseCount(dto.getPageSize());
		example.setLimitClauseStart(dto.getStartRowIndex());
		example.setOrderByClause("create_time desc ");
        
        return super.queryByPagination(dto, example, true, new PaginationCallback<ImHotlineInfo, ImHotlineInfoResDTO>() {

            @Override
            public List<ImHotlineInfo> queryDB(BaseCriteria criteria) {
                return imHotlineInfoMapper.selectByExample((ImHotlineInfoCriteria)criteria);
            }

            @Override
            public long queryTotal(BaseCriteria criteria) {
                return imHotlineInfoMapper.countByExample((ImHotlineInfoCriteria)criteria);
            }
            
            @Override
            public List<Comparator<ImHotlineInfo>> defineComparators(){
                List<Comparator<ImHotlineInfo>> ls = new ArrayList<Comparator<ImHotlineInfo>>();
                ls.add(new Comparator<ImHotlineInfo>() {
                	public int compare(ImHotlineInfo o1, ImHotlineInfo o2) {
                		if(o1.getCreateTime()==null||o2.getCreateTime()==null){
                			return 0;
                		}
						return o1.getCreateTime().getTime()>o2.getCreateTime().getTime()?-1:1;
					}
                });
                return ls;
            }
            
            @Override
            public ImHotlineInfoResDTO warpReturnObject(ImHotlineInfo t) {
            	ImHotlineInfoResDTO dto = new ImHotlineInfoResDTO();
                ObjectCopyUtil.copyObjValue(t,dto,null,true);
                //字段转义
                dto.setModuleType(BaseParamUtil.fetchParamValue(ImConstants.MOUBLE_TYPE_CONFIG, 
                                                                           t.getModuleType()));//资金类型名
                ImStaffHotlineReqDTO dto2 = new ImStaffHotlineReqDTO();
                dto2.setId(dto.getId());
                ImStaffHotlineResDTO dto3 =   getHotlineByStaff(dto2);
                dto.setStaffCode(dto3.getStaffCode());
                if(dto.getShopId()==0){
                	dto.setShopName("-");
                }else{
                	//如果是第三方平台，则查平台名称
                	if("01".equals(dto.getPlatSource())){
                		dto.setShopName(BaseParamUtil.fetchParamValue("IM_PLAT_SOURCE", dto.getShopId()+""));
                	} else {
                		ShopInfoResDTO s = iShopInfoRSV.findShopInfoByShopID(dto.getShopId());
                    	dto.setShopName(s!=null&&StringUtil.isNotBlank(s.getShopName())?s.getShopName():"佚名店铺");
                	}
                }
                return dto;
            }
        });

	}

	@Override
	public List<ImStaffHotlineResDTO> getHotlineByStaffList(ImStaffHotlineReqDTO dto) throws BusinessException {
		List<ImStaffHotlineResDTO> list = new ArrayList<>();
		ImStaffHotlineCriteria example = new ImStaffHotlineCriteria();
		Criteria sql = example.createCriteria();

		if (StringUtil.isNotBlank(dto.getStaffClass())) {
			sql.andStaffClassEqualTo(dto.getStaffClass());
		}

		if (StringUtil.isNotBlank(dto.getStaffCode())) {
			sql.andStaffCodeEqualTo(dto.getStaffCode());
		}
		
		if(StringUtil.isNotBlank(dto.getOfStaffCode())){
			sql.andOfStaffCodeEqualTo(dto.getOfStaffCode());
		}
		
		if(null!=dto.getId()&&0!=dto.getId()){
			sql.andIdEqualTo(dto.getId());
		}
		
		if(StringUtil.isNotBlank(dto.getStatus())){
			sql.andStatusEqualTo(dto.getStatus());
		}
		
		List<ImStaffHotline> listh = imStaffHotlineMapper
				.selectByExample(example);
		if (CollectionUtils.isNotEmpty(listh)) {
			for (ImStaffHotline imStaffHotline : listh) {
				ImStaffHotlineResDTO imStaffHotlineResDTO = new ImStaffHotlineResDTO();
				ObjectCopyUtil.copyObjValue(imStaffHotline, imStaffHotlineResDTO,
						null, false);
				ImHotlineInfo hotlineInfo = imHotlineInfoMapper.selectByPrimaryKey(imStaffHotlineResDTO.getId());
				imStaffHotlineResDTO.setShopId(hotlineInfo.getShopId());
				imStaffHotlineResDTO.setModuleType(hotlineInfo.getModuleType());
				imStaffHotlineResDTO.setReceptionCount(hotlineInfo.getReceptionCount());
				list.add(imStaffHotlineResDTO);
			}
			
		} else {
			return null;
		}

		return list;
	}

	@Override
	public int updateHotlineInfo(ImHotlineInfoReqDTO dto) throws BusinessException {
		
		ImHotlineInfo record = new ImHotlineInfo();
		ObjectCopyUtil.copyObjValue(dto, record, null, false);
		int i  = imHotlineInfoMapper.updateByPrimaryKeySelective(record);
		if(i>0){
			if(StringUtil.isNotBlank(dto.getStatus())){
				ImStaffHotline hotline = new ImStaffHotline();
				hotline.setId(dto.getId());
				hotline.setStatus(dto.getStatus());
				imStaffHotlineMapper.updateByPrimaryKeySelective(hotline);
			}
		}
		
		return i;
	}

	@Override
	public int updateSession(SessionReqDTO dto) throws BusinessException {
		int i = 0;
		QueryBuilder query = new QueryBuilder();
		if(StringUtil.isNotBlank(dto.getId())){
			query.and("id").is(dto.getId());
		}
		if(StringUtil.isNotBlank(dto.getCsaCode())){
			query.and("csaCode").is(dto.getCsaCode());
		}
		if(StringUtil.isNotBlank(dto.getUserCode())){
			query.and("userCode").is(dto.getUserCode());
		}
		
		query.and("status").is(ImConstants.STATE_1);
		DBCursor stuFound = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").find(query.get());
	    try {
			
			while (stuFound.hasNext()) {
				DBObject dbObject = stuFound.next();
				dbObject.put("status", dto.getStatus());
				WriteResult result = MongoUtil.getDBCollection("T_IM_SESSION_HISTORY").update(query.get(), dbObject);
				i = i+ result.getN();
			}
		} finally {
			stuFound.close();
		}
		return i;
		
	}

	@Override
	public List<SessionResDTO> getSessionList(SessionReqDTO dto) throws BusinessException {
		List<SessionResDTO> list = new ArrayList<>();
		DBCollection collection  =MongoUtil.getDBCollection("T_IM_SESSION_HISTORY");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		if(StringUtil.isNotBlank(dto.getCsaCode())){
			queryBuilder.and("csaCode").is(dto.getCsaCode());
		}
		
		queryBuilder.and("status").is(ImConstants.STATE_1);
		
		DBCursor cursor = collection.find(queryBuilder.get());
		try {
			while (cursor.hasNext()) {
				SessionResDTO respVO = new SessionResDTO();
				DBObject obj = cursor.next();
				ImUtil.dbObjectToBean(obj, respVO);
				list.add(respVO);
			}
		} finally {
			cursor.close();
		}
			return list;	
	}

	@Override
	public int updateHotlineOnlineStart(ImStaffHotlineReqDTO dto) throws BusinessException {
		//获取客服资料
		ImStaffHotlineResDTO dto2 = this.getHotlineByStaff(dto);
		
		OnlineReqDTO onlineReqDTO = new OnlineReqDTO();
		BasicDBObject query = new BasicDBObject();
		query.put("csaCode", dto.getCsaCode());
		DBObject stuFound = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").findOne(query);
		stuFound.put("onlineStatus", ImConstants.ONLINE);
		stuFound.put("updateTime", DateUtil.getSysDate());
		WriteResult result = MongoUtil.getDBCollection("T_IM_HOTLINE_ONLINE").update(query, stuFound);
		onlineReqDTO.setOnlineStatus(String.valueOf(ImConstants.ONLINE));
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
		return result.getN();
	}

}
