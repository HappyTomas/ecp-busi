package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsGds2Catg;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2CatgRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 商品分类关系操作SV <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午4:10:46  <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version  
 * @since JDK 1.6
 */
public interface IGdsInfo2CatgSV{
    
    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应的分类关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2CatgRespDTO> queryGds2CatgsByGdsId(Long gdsId) throws BusinessException;
    
    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应的分类关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2Catg> queryGds2CatgsModelByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * getGdsMainPicByGdsId:(通过商品编码获取商品主分类). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public GdsGds2CatgRespDTO queryGdsMainCatgByGdsId(Long gdsId) throws BusinessException;

    
    
    /**
     * 
     * updateGds2Catg:(更新商品分类关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void updateGds2Catg(GdsGds2CatgReqDTO reqDTO,GdsGds2CatgReqDTO query)  throws BusinessException;
    
    /**
     * 
     * saveGds2Catg:(保存商品分类关系). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void saveGds2Catg(GdsGds2CatgReqDTO reqDTO)  throws BusinessException;
    
    
    /**
     * 
     * delGds2Catg:(逻辑删除商品分类关系). <br/> 
     * 
     * @author linwb3
     * @param gds2MediaReqDTO 
     * @since JDK 1.6
     */
    public void delGds2Catg(GdsGds2CatgReqDTO reqDTO)  throws BusinessException;
    
    /**
     * 
     * realDelGds2Prop:(<font color='red'>物理删除商品与媒体关联关系<font/>). <br/> 
     * 
     * @author linwb3
     * @param reqDTO 
     * @since JDK 1.6
     */
    public void realDelGds2Catg(GdsGds2CatgReqDTO reqDTO) throws BusinessException;

    public void updateGds2CatgGdsStatus(GdsGds2CatgReqDTO reqDTO) throws BusinessException;
    
    public List<GdsGds2Catg> queryGdsInfo2Catg(GdsGds2CatgReqDTO reqDto) throws BusinessException;
}

