package com.zengshi.ecp.prom.dubbo.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;


public class SeckillDiscountDTO extends CommPromTypeDTO {

    private static final long serialVersionUID = 1L;
    
    private boolean exist = false;
    private boolean start = false;
    private Timestamp systemTime;
    private PromInfoDTO seckillProm;
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
    public PromInfoDTO getSeckillProm() {
        if (seckillProm == null) {
            return new PromInfoDTO(); 
        }
        return seckillProm;
    }
    public void setSeckillProm(PromInfoDTO seckillProm) {
        this.seckillProm = seckillProm;
    }
    public Timestamp getSystemTime() {
        return systemTime;
    }
    public void setSystemTime(Timestamp systemTime) {
        this.systemTime = systemTime;
    }
    
  
}
