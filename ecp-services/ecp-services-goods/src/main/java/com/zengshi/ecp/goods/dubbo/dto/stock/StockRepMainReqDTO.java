package com.zengshi.ecp.goods.dubbo.dto.stock;

import java.util.List;

import com.zengshi.ecp.server.front.dto.BaseInfo;

public class StockRepMainReqDTO extends BaseInfo {
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    private static final long serialVersionUID = -5479054381981334463L;

    private StockRepReqDTO stockRepDTO;

    private long staffId;
    
    private List<StockRepAdaptReqDTO> newRepAdaptList;
    
    private   List<StockRepAdaptReqDTO> delRepAdaptList;
    //判断是否新增操作
    private Boolean ifNew;

    public StockRepReqDTO getStockRepDTO() {
        return stockRepDTO;
    }

    public void setStockRepDTO(StockRepReqDTO stockRepDTO) {
        this.stockRepDTO = stockRepDTO;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public List<StockRepAdaptReqDTO> getNewRepAdaptList() {
        return newRepAdaptList;
    }

    public void setNewRepAdaptList(List<StockRepAdaptReqDTO> newRepAdaptList) {
        this.newRepAdaptList = newRepAdaptList;
    }

    public List<StockRepAdaptReqDTO> getDelRepAdaptList() {
        return delRepAdaptList;
    }

    public void setDelRepAdaptList(List<StockRepAdaptReqDTO> delRepAdaptList) {
        this.delRepAdaptList = delRepAdaptList;
    }

    public Boolean getIfNew() {
        return ifNew;
    }

    public void setIfNew(Boolean ifNew) {
        this.ifNew = ifNew;
    }
}
