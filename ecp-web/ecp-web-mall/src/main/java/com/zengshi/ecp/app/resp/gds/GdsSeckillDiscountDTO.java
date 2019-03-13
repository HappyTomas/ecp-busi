package com.zengshi.ecp.app.resp.gds;

import java.sql.Timestamp;

import com.zengshi.butterfly.app.model.IBody;


public class GdsSeckillDiscountDTO extends IBody {

    private boolean exist = false;
    private boolean start = false;
    private Timestamp systemTime;
    private GdsSecKillPromInfoDTO seckillProm;
    public boolean isExist() {
        return exist;
    }
    public void setExist(boolean exist) {
        this.exist = exist;
    }
    public boolean isStart() {
        return start;
    }
    public void setStart(boolean start) {
        this.start = start;
    }
    public GdsSecKillPromInfoDTO getSeckillProm() {
        if (seckillProm == null) {
            return new GdsSecKillPromInfoDTO(); 
        }
        return seckillProm;
    }
    public void setSeckillProm(GdsSecKillPromInfoDTO seckillProm) {
        this.seckillProm = seckillProm;
    }
    public Timestamp getSystemTime() {
        return systemTime;
    }
    public void setSystemTime(Timestamp systemTime) {
        this.systemTime = systemTime;
    }
    
    
    
  
}
