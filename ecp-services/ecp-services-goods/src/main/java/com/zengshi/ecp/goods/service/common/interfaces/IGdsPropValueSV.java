/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsPropValue.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月14日上午10:55:57 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dao.model.GdsPropValue;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsPropValueRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 属性值服务接口. <br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月14日上午10:55:57  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsPropValueSV extends IGeneralSQLSV{
    
    /**
     * 
     * 保存商品属性值. <br/> 
     * 
     * @author liyong7
     * @param GdsPropDTO
     * @return 操作结果数量.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int saveGdsPropValue(GdsPropValueReqDTO gdsPropValueReqDTO) throws BusinessException;
    /**
     * 
     * 根据属性ID结合状态查询出与属性相关的属性值,根据SORT_NO自然升序. <br/> 
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<GdsPropValue> listGdsPropValuesByPropId(Long propId,String... status) throws BusinessException;
    /**
     * 
     * 属性值编辑. <br/> 
     * 
     * @author liyong7
     * @param gdsPropValue
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropValue editGdsPropValue(GdsPropValue gdsPropValue, Long updateStaff) throws BusinessException;
    /**
     * 
     * 属性值编辑. <br/> 
     * 
     * @author liyong7
     * @param gdsPropValue
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void editGdsPropValue(GdsPropValueReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 批量保存属性值信息。<br/>
     * 
     * @author liyong7
     * @param propValues
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void saveGdsPropValue(List<GdsPropValueReqDTO> gdsPropValueReqDTOS) throws BusinessException;
    /**
     * 
     * 结合属性值状态判断指定属性是否已经存在相同属性值。<br/>
     * 
     * @author liyong7
     * @param propValue
     * @param propId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExists(String propValue, long propId, String... status) throws BusinessException;
    /**
     * 
     * 删除指定ID属性值.（该删除操作仅对属性值信息状态作失效操作） <br/> 
     * 
     * @author liyong7
     * @param propValueId
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    int deleteGdsPropValue(long propValueId,Long updateStaff) throws BusinessException;
    /**
     * 
     * 分页查询出GdsPropValueRespDTO.
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsPropValueRespDTO> queryGdsPropValueRespDTOPaging(GdsPropValueReqDTO dto) throws BusinessException;
    /**
     * 
     * 根据主键ID查询属性值.
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsPropValue queryPropValueById(Long id) throws BusinessException;
    
    
    /**
     * 
     * 根据主键ID查询属性值.
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<GdsPropValueRespDTO> queryPropValuesByPropId(Long id,String...status) throws BusinessException;
    
    /**
     * 
     * queryPropValueInfoByVal:(根据值查询对应的属性值记录). <br/> 
     * 
     * @author zjh 
     * @param propValue
     * @param propId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsPropValue queryPropValueInfoByVal(String propValue, long propId, String... status)
            throws BusinessException;
}

