/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsPropRSV.java 
 * Package Name:com.zengshi.ecp.goods.dubbo.interfaces 
 * Date:2015年8月28日上午11:05:52 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.LongReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

/**
 * Title: 属性Dubbo服务接口. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月28日上午11:05:52  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsPropRSV {
    /**
     * 
     * 创建属性.(新增属性需要针对属性值进行处理.)<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void createGdsProp(GdsPropReqDTO reqDTO)throws BusinessException;

    /**
     * 
     * 编辑属性,该方法仅针对属性基础信息进行编辑,不针对属性值进行处理.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int editGdsProp(GdsPropReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 分页查询属性.<br/> 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropRespDTO> queryGdsPropPaging(GdsPropReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 为指定属性新增属性值.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int addPropValue(GdsPropValueReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 分页查询属性值,如无需分页查询则可以尽可能使pageSize的值最大化,以确保可以一次性加载出所有属性信息<br/>
     * 例如:可以设置Integer.MAX_VALUE<br/>
     * 
     * @author liyong7
     * @param reqDTO 该对象中的propId为必传参数.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropValueRespDTO> queryGdsPropValuePaging(GdsPropValueReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 判断当前属性是否已经关联同样的属性值.<br/> 
     * 
     * @author liyong7
     * @param reqDTO 该对象中,propId与propValue为必填对象.
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExistPropValue(GdsPropValueReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据属性主键ID禁用属性信息。 
     * 
     * @author liyong7
     * @param reqDTO 对象中的id为必须属性。
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int executeDisableGdsPropByPK(LongReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据属性主键ID启用属性信息。<br/> 
     * 
     * @author liyong7
     * @param reqDTO 对象中的id为必须属性。
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int executeEnableGdsPropByPK(LongReqDTO reqDTO)throws BusinessException;
    /**
     * 
     * 根据条件查询指定属性是否已经跟有效分类关联. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryIsPropInUse(GdsCatg2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 分页查询属性 添加过滤条件.<br/> 
     * 
     * @author xiaosm3
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropRespDTO> queryGdsPropPagingInOrNot(GdsPropReqDTO reqDTO, List<Long> excludeIds, List<Long> includeIds) throws BusinessException;
    
}

