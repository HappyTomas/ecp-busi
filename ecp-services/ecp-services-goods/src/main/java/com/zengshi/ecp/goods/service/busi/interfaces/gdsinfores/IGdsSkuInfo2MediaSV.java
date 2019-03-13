package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsSku2Media;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsSku2MediaRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 单品图片关系SV<br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午4:11:30 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsSkuInfo2MediaSV {

    /**
     * 
     * querySkuMainPicBySkuId:(获取单品主图)throws BusinessException. <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public GdsMediaRespDTO querySkuMainPicBySkuId(Long skuId,Long gdsId) throws BusinessException;

    /**
     * 
     * querySkuMediaBySkuId:(获取单品媒体关系,不继承商品图片)throws BusinessException.  <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2MediaRespDTO> querySkuMediaBySkuIdWithOutGds(Long skuId,Long gdsId) throws BusinessException;

    /**
     * 
     * querySkuMediaBySkuId:(获取单品媒体关系,继承商品图片)throws BusinessException.  <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2MediaRespDTO> querySkuMediaBySkuId(Long skuId, Long gdsId) throws BusinessException;

    
    /**
     * 
     * querySkuMediaBySkuId:(获取单品媒体关系,继承商品图片)throws BusinessException.  <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2Media> querySkuMediaModelBySkuIdWithOutGds(Long skuId, Long gdsId) throws BusinessException;

    /**
     * 
     * querySkuMediaBySkuId:(获取单品媒体关系,不继承商品图片)throws BusinessException.  <br/>
     * 
     * @author linwb3
     * @param skuId
     * @param status
     * @return
     * @since JDK 1.6
     */
    public List<GdsSku2Media> querySkuMediaModelBySkuId(Long skuId, Long gdsId) throws BusinessException;

    
    /**
     * 
     * querySkuInfo2Media:(查询). <br/> 
     * 
     * @author linwb3
     * @param skuId
     * @param gdsId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsSku2Media> getGdsSkuInfo2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * getMainPicCache:(获取单品主图缓存). <br/> 
     * 
     * @author linwb3
     * @param skuId
     * @return 
     * @since JDK 1.6
     */
    public GdsMediaRespDTO getMainPicCache(Long skuId)  throws BusinessException;
    
    
    /**
     * 
     * updateGds2Media:(更新商品与媒体关系表). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 数据更新对象
     * @param query  查询条件对象
     * @since JDK 1.6
     */
    public void updateSku2Media(GdsSku2MediaReqDTO reqDTO,GdsSku2MediaReqDTO query)  throws BusinessException;
    
    /**
     * 
     * saveGds2Media:(保存商品与媒体关联关系。). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void saveSku2Media(GdsSku2MediaReqDTO reqDTO)  throws BusinessException;
    
    
    /**
     * 
     * delGds2Media:(逻辑删除商品与媒体关联关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void delSku2Media(GdsSku2MediaReqDTO reqDTO)  throws BusinessException;
    
    /**
     * 
     * realDelGds2Prop:(<font color='red'>物理删除商品与媒体关联关系<font/>). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void realDelSku2Media(GdsSku2MediaReqDTO reqDTO) throws BusinessException;

}
