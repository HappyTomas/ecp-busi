package com.zengshi.ecp.busi.buyer.vo;

import java.util.List;

import com.zengshi.ecp.coupon.dubbo.dto.resp.CoupMeRespDTO;
import com.zengshi.ecp.server.front.dto.BaseResponseDTO;

/**
 * Created by HDF on 2016/7/4.
 */
public class CoupScrollResultVO extends BaseResponseDTO{

    private static final long serialVersionUID = -7262550402838886595L;

    private long total;

    private List<CoupDetailVO> datas;
    
    private String dataType;
   

    public List<CoupDetailVO> getDatas() {
		return datas;
	}

	public void setDatas(List<CoupDetailVO> datas) {
		this.datas = datas;
	}

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
}
