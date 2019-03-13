package com.zengshi.ecp.order.dubbo.interfaces;

import com.zengshi.ecp.order.dubbo.dto.*;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

import java.util.List;

public interface IOrdSubRSV {
    /** 
     * queryOrderSub:订单发货明细查询. <br/> 
     * @author cbl  
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSub(RShowOrdSubRequest rShowOrdSubRequest) throws BusinessException;
    
    /** 
     * queryOrderSubByStaffId:根据买家ID查询子订单信息. <br/> 
     * @author cbl 
     * @param rQueryOrderRequest
     * @return 
     * @since JDK 1.6 
     */ 
    public PageResponseDTO<RShowOrdSubResponse> queryOrderSubByStaffId(RQueryOrderRequest rQueryOrderRequest) throws BusinessException;

    /**
     *
     * queryGoodSaleInfo:“商品销售明细”列表. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wangxq
     * @param rGoodSaleRequest
     * @since JDK 1.6
     */
    public PageResponseDTO<RGoodSaleResponse> queryGoodSaleInfo(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;

    /**
     *
     * queryGoodSaleInfo:“商品销售明细”列表. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wangxq
     * @param rGoodSaleRequest
     * @since JDK 1.6
     */
    public RGoodSaleSumResp queryGoodSaleSum(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;

    /**
     *
     * exportGoodSaleExcel:“商品销售明细”文档. <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @author wangxq
     * @param rGoodSaleRequest
     * @since JDK 1.6
     */
    public RExportExcleResponse exportGoodSaleExcel(RGoodSaleRequest rGoodSaleRequest) throws BusinessException;

    /**
     * queryOrderSubByStaffId:根据买家ID和skuId查询子订单信息. <br/>
     * @author cbl
     * @param rOrdSubStaffIdxReq
     * @return
     * @since JDK 1.6
     */
    public List<ROrdSubStaffIdxResp> queryOrderSubByStaffIdAndSkuid(ROrdSubStaffIdxReq rOrdSubStaffIdxReq) throws BusinessException;

}

