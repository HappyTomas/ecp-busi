/** 
 * Project Name:ecp-services-goods-server 
 * File Name:GdsInfoOpeTypeCal.java 
 * Package Name:com.zengshi.ecp.goods.service.busi.impl.gdslog 
 * Date:2016-3-29下午4:16:42 
 * Copyright (c) 2016, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.busi.impl.gdslog;

import org.apache.commons.lang3.ArrayUtils;

import com.zengshi.ecp.goods.dubbo.constants.GdsConstants;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfo.GdsInfoReqDTO;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.ICalOperationTypeSV;
import com.zengshi.ecp.goods.service.busi.interfaces.gdslog.OperationType;
import com.zengshi.paas.utils.StringUtil;

/**
 * Title: ECP <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016-3-29下午4:16:42  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public class GdsInfoOpeTypeCalSVImpl implements ICalOperationTypeSV {

	/** 
	 * TODO 简单描述该方法的实现功能（可选）. 
	 * @see com.zengshi.ecp.goods.service.busi.interfaces.gdslog.ICalOperationType#getOperationType(com.zengshi.ecp.server.front.dto.BaseInfo) 
	 */
	@Override
	public OperationType getOperationType(Object arg) {
		OperationType type = null;
		if(null != arg){
			GdsInfoReqDTO reqDTO = (GdsInfoReqDTO) arg;
			if(StringUtil.isNotBlank(reqDTO.getGdsStatus())){
				switch (reqDTO.getGdsStatus()) {
				case GdsConstants.GdsInfo.GDS_STATUS_DELETE:
					if(ArrayUtils.isNotEmpty(reqDTO.getIds())){
						type = OperationType.GDS_BATCH_DELETE;
					}else{
						type = OperationType.GDS_DELETE;
					}
					break;
				case GdsConstants.GdsInfo.GDS_STATUS_OFFSHELVES:
					if(ArrayUtils.isNotEmpty(reqDTO.getIds())){
						type = OperationType.GDS_BATCH_OFF_SHELVES;
					}else{
						type = OperationType.GDS_OFF_SHELVES;
					}
					break;
				case GdsConstants.GdsInfo.GDS_STATUS_ONSHELVES:
					if(ArrayUtils.isNotEmpty(reqDTO.getIds())){
						type = OperationType.GDS_BATCH_ON_SHELVES;
					}else{
						type = OperationType.GDS_ON_SHELVES;
					}
					break;
				default:
					break;
				}
			}
		}
		return type;
	}

}

