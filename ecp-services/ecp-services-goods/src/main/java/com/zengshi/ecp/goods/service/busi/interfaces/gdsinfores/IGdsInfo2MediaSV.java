package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsGds2Media;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2MediaRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 商品图片关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午4:11:02 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfo2MediaSV {

    /**
     * 
     * getGdsMainPicByGdsId:(通过商品编码获取商品主图). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public GdsMediaRespDTO queryGdsMainPicByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应媒体图片关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2MediaRespDTO> queryGds2MediasByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * queryGds2MediaModelByGdsId:(查询商品对应媒体图片关系列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public List<GdsGds2Media> queryGds2MediasModelByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * getMainPicCache:(获取商品主图缓存). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public GdsMediaRespDTO getMainPicCache(Long gdsId) throws BusinessException;

    /**
     * 
     * queryGds2Media:(查询商品图片原子服务). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2Media> queryGdsInfo2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * updateGds2Media:(更新商品与媒体关系表). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void updateGds2Media(GdsGds2MediaReqDTO reqDTO,GdsGds2MediaReqDTO query) throws BusinessException;

    /**
     * 
     * saveGds2Media:(保存商品与媒体关联关系。). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void saveGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * delGds2Media:(逻辑删除商品与媒体关联关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void delGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * realDelGds2Prop:(<font color='red'>物理删除商品与媒体关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void realDelGds2Media(GdsGds2MediaReqDTO reqDTO) throws BusinessException;

}
