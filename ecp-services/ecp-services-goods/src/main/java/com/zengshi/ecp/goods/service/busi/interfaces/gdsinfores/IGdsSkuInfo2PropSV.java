package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsSku2Prop;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2PropReqDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 单品属性关系SV <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午4:11:46 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfo2PropSV {

    /**
     * 
     * getSkuPropBySkuId:(根据单品Id获取单品所有属性). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsPropRespDTO> getSkuPropsBySkuId(Long gdsId, Long skuId, List<Long> propIds)  throws BusinessException;

    /**
     * 
     * getSkuPropBySkuId:(根据单品Id获取单品所有属性). <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2Prop> getSkuPropsModelBySkuId(Long gdsId, Long skuId, List<Long> propIds)  throws BusinessException;


    /**
     * 
     * convertSku2propTOProp:(将单品属性关系转换为属性). <br/>
     * 
     * @author linwb3
     * @param sku2Props
     * @return
     * @since JDK 1.6
     */
    public List<GdsPropRespDTO> convertSku2propTOProp(List<GdsSku2Prop> sku2Props)  throws BusinessException;

    /**
     * 
     * queryGdsSku2Prop:根据skuId与propId获取单品与属性关联关系。 <br/>
     * 
     * @author linwb3
     * @param gdsSku2propReqDTO
     * @return
     * @since JDK 1.6
     */

    public GdsSku2Prop queryGdsSku2Prop(GdsSku2PropReqDTO gdsSku2propReqDTO)  throws BusinessException;

    /**
     * 
     * queryGdsSku2Prop:查询单品属性关系原子服务。 <br/>
     * 
     * @author linwb3
     * @param gdsSku2propReqDTO
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2Prop> queryGdsSkuInfo2Prop(GdsSku2PropReqDTO reqDTO)  throws BusinessException;
    
    
    /**
     * 
     * updateGds2Media:(更新商品与属性关系表). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 更新对象承载对象
     * @param query  查询条件承载对象
     * @since JDK 1.6
     */
    public void updateSku2Prop(GdsSku2PropReqDTO reqDTO,GdsSku2PropReqDTO query)  throws BusinessException;
    
    /**
     * 
     * saveGds2Media:(保存商品与属性关联关系。). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void saveSku2Prop(GdsSku2PropReqDTO reqDTO)  throws BusinessException;
    
    
    /**
     * 
     * delGds2Media:(删除商品与属性关联关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void delSku2Prop(GdsSku2PropReqDTO reqDTO)  throws BusinessException;


    /**
     * 
     * realDelGdsSkuInfo2Prop:(<font color='red'>物理删除商品与属性关联关系<font/>). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void realDelSku2Prop(GdsSku2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * updateSku2PropGdsStatus:(<font color='red'>批量更新单品与属性关联关系的商品状态<font/>). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void updateSku2PropGdsStatus(GdsSku2PropReqDTO reqDTO) throws BusinessException;

}
