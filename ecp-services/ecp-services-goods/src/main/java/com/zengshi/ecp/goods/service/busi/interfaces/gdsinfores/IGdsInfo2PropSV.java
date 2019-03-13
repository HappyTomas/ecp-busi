package com.zengshi.ecp.goods.service.busi.interfaces.gdsinfores;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsGds2Prop;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.gdsinfores.GdsGds2PropRespDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * 
 * Title: 商品图片关系操作 <br>
 * Project Name:ecp-services-goods-server <br>
 * Description: <br>
 * Date:2016年3月22日下午4:11:20 <br>
 * Copyright (c) 2016 ZengShi All Rights Reserved <br>
 * 
 * @author linwb3
 * @version
 * @since JDK 1.6
 */
public interface IGdsInfo2PropSV {
    /**
     * 
     * queryGdsCatgsByGdsId:(查询商品对应的属性列表). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2PropRespDTO> queryGds2PropsByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * queryGds2PropModel:(根据商品编码，商品状态查询商品属性). <br/>
     * 
     * @author linwb3
     * @param gdsId
     * @param gdsStatus
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2Prop> queryGds2PropsModelByGdsId(Long gdsId) throws BusinessException;

    /**
     * 
     * queryGds2PropModel:(根据gdsId,propId查询出有效状态商品属性关联关系). <br/>
     * 
     * @author liyong7
     * @param gds2propReqDTO
     * @return
     * @since JDK 1.6
     */
    public GdsGds2Prop queryGds2PropModel(Long gdsId, Long propId) throws BusinessException;

    /**
     * 商品属性关系原子查询服务
     * 
     * @author linwb3
     * @param reqDTO
     * @return
     * @since JDK 1.6
     */
    public List<GdsGds2Prop> queryGdsInfo2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * updateGds2Media:(更新商品与属性关系表). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void updateGds2Prop(GdsGds2PropReqDTO reqDTO,GdsGds2PropReqDTO query) throws BusinessException;

    /**
     * 
     * saveGds2Media:(保存商品与属性关联关系。). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void saveGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * delGds2Media:(删除商品与属性关联关系). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void delGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * realDelGds2Prop:(<font color='red'>物理删除商品与属性关联关系<font/>). <br/>
     * 
     * @author linwb3
     * @param reqDTO
     * @since JDK 1.6
     */
    public void realDelGds2Prop(GdsGds2PropReqDTO reqDTO) throws BusinessException;

    public void updateGds2PropGdsStatus(GdsGds2PropReqDTO reqDTO) throws BusinessException;

}
