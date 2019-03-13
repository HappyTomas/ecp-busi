package com.zengshi.ecp.app.resp;

import com.zengshi.ecp.app.resp.gds.GdsCategoryVO;
import com.zengshi.butterfly.app.model.IBody;

import java.util.List;

/**
 * 
 * Title: ECP <br>
 * Project Name:ecp-web-mall <br>
 * Description: 条形码查询返回对象.<br>
 * Date:2016年10月9日下午5:55:11  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6
 */
public class Gds026Resp extends IBody {

    /**
     * 商品编号
     */
    private Long gdsId;
    /**
     * 单品编号
     */
    private Long skuId;
    public Long getGdsId() {
        return gdsId;
    }
    public void setGdsId(Long gdsId) {
        this.gdsId = gdsId;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    
    
}
