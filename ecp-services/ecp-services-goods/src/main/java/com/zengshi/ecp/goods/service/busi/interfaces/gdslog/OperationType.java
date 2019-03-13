package com.zengshi.ecp.goods.service.busi.interfaces.gdslog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OperationType {
    /**
     * 新增，添加
     */
    GDS_ADD("商品新增"),
 
    /**
     * 修改，更新
     */
    GDS_EDIT("商品编辑"),
 
    /**
     * 删除
     */
    GDS_DELETE("商品删除"),
    /**
     * 商品批量删除
     */
    GDS_BATCH_DELETE("商品批量删除"),
    /**
     * 商品上架
     */
    GDS_ON_SHELVES("商品上架"),
    /**
     * 商品下架
     */
    GDS_OFF_SHELVES("商品下架"),
    /**
     * 商品批量上架
     */
    GDS_BATCH_ON_SHELVES("商品批量上架"),
    /**
     * 商品批量下架
     */
    GDS_BATCH_OFF_SHELVES("商品批量下架"),
    /**
     * 商品上架审核
     */
    GDS_ON_SHELVES_VERIFY("提交商品上架审核"),
    /**
     * 商品批量上架审核
     */
    GDS_BATCH_ON_SHELVES_VERIFY("提交商品上架审核"),
    /**
     * 商品删除审核
     */
    GDS_DELETE_VERIFY("提交商品删除审核"),
    /**
     * 商品批量删除审核
     */
    GDS_BATCH_DELETE_VERIFY("提交商品删除审核"),
    /**
     * 单品新增
     */
    SKU_ADD("单品新增"),
    /**
     * 单品编辑
     */
    SKU_EDIT("单品编辑"),
    /**
     * 单品删除
     */
    SKU_DELETE("单品删除"),
    /**
     * 单品上架
     */
    SKU_ON_SHELVES("单品上架"),
    /**
     * 单品上架审核 
     */
    SKU_ON_SHELVES_VERIFY("提交单品上架审核 "),
    /**
     * 单品批量上架审核
     */
    SKU_BATCH_ON_SHELVES_VERIFY("提交单品上架审核 "),
    /**
     * 单品下架
     */
    SKU_OFF_SHELVES("单品下架"),
    /**
     * 单品下架审核
     */
    SKU_OFF_SHELVES_VERIFY("提交单品下架审核"),
    /**
     * 单品批量下架审核
     */
    SKU_BATCH_OFF_SHELVES_VERIFY("提交单品下架审核"),
    /**
     * 单品删除审核 
     */
    SKU_DELETE_VERIFY("提交单品删除审核"),
    /**
     * 单品批量删除审核
     */
    SKU_BATCH_DELETE_VERIFY("提交单品删除审核"),
    /**
     * 商品审核
     */
    GDS_INFO_VERIFY("商品审核"),
    /**
     * 单品审核.
     */
    SKU_INFO_VERIFY("单品审核"),
    /**
     * 未指定操作类型.
     */
    NULL("未指定");
 
    private String name;
 
    private OperationType() {
    }
 
    public String getName() {
        return name;
    }
 
    private OperationType(String name) { 
        this.name = name;
    }
 
    /**
     * 获取所有的枚举集合
     * @return
     */
    public static List<OperationType> getOperationTypes() {
        return new ArrayList<OperationType>(Arrays.asList(OperationType
                .values()));
    }
     
}
