package com.zengshi.ecp.app.resp;

import java.util.List;

import com.zengshi.butterfly.app.model.IBody;

public class Prom002Resp extends IBody{

	List<PromInfoRespVO> resList;

    private boolean seckillExist = false;
    private boolean seckillStart = false;
    
    private PromInfoRespVO seckillProm;
	
	public List<PromInfoRespVO> getResList() {
		return resList;
	}

	public void setResList(List<PromInfoRespVO> resList) {
		this.resList = resList;
	}

    public boolean isSeckillExist() {
        return seckillExist;
    }

    public void setSeckillExist(boolean seckillExist) {
        this.seckillExist = seckillExist;
    }

    public boolean isSeckillStart() {
        return seckillStart;
    }

    public void setSeckillStart(boolean seckillStart) {
        this.seckillStart = seckillStart;
    }

    public PromInfoRespVO getSeckillProm() {
        return seckillProm;
    }

    public void setSeckillProm(PromInfoRespVO seckillProm) {
        this.seckillProm = seckillProm;
    }
	
}
