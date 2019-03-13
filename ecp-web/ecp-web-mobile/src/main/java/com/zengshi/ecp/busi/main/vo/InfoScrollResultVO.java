package com.zengshi.ecp.busi.main.vo;

import java.util.List;
import java.util.Map;

import com.zengshi.ecp.cms.dubbo.dto.CmsInfoRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Created by HDF on 2016/7/4.
 */
public class InfoScrollResultVO extends BaseResponseDTO{

    private static final long serialVersionUID = -7262550402838886595L;

    private long total;

    private List<InfoScrollObjVO> datas;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<InfoScrollObjVO> getDatas() {
		return datas;
	}

	public void setDatas(List<InfoScrollObjVO> datas) {
		this.datas = datas;
	}

   

    
}
