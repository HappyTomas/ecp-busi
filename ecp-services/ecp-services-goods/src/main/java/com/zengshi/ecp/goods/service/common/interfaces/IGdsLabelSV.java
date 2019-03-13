/** 
 * Project Name:ecp-services-goods 
 * File Name:IGdsLabelSV.java 
 * Package Name:com.zengshi.ecp.goods.service.common.interfaces 
 * Date:2015年8月19日下午8:30:06 
 * Copyright (c) 2015, ZengShi All Rights Reserved. 
 * 
 */ 
package com.zengshi.ecp.goods.service.common.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;
import com.zengshi.ecp.server.service.interfaces.IGeneralSQLSV;

/**
 * 标签管理服务接口.<br>
 * Project Name:ecp-services-goods <br>
 * Description: <br>
 * Date:2015年8月19日下午8:30:06  <br>
 * Copyright (c) 2015 ZengShi All Rights Reserved <br>
 * 
 * @author liyong7
 * @version  
 * @since JDK 1.6 
 */
public interface IGdsLabelSV extends IGeneralSQLSV {
    /**
     * 
     * 创建标签对象.
     * 
     * @author liyong7
     * @param gdsLabel
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
	public GdsLabelReqDTO saveGdsLabel(GdsLabelReqDTO gdsLabel) throws BusinessException;
    /**
     * 
     * 分页查询.
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsLabelRespDTO> queryGdsLabelRespDTOPaging(GdsLabelReqDTO dto) throws BusinessException;
    
    /**
     * 
     * 编辑标签. 
     * 
     * @author liyong7
     * @param gdsLabel
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabel, Long updateStaff)throws BusinessException;
    /**
     * 
     * 编辑状态.<br/>
     * 
     * @author liyong7
     * @param labelId
     * @param status
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int editStatus(Long labelId,String status,Long updateStaff) throws BusinessException;
    /**
     * 
     * 禁用指定标签. <br/> 
     * 
     * @author liyong7
     * @param labelId
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int executeDisableGdsLabel(Long labelId,Long updateStaff) throws BusinessException;
    /**
     * 
     * 启用指定标签.<br/> 
     * 
     * @author liyong7
     * @param labelId
     * @param updateStaff
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int executeEnableGdsLabel(Long labelId,Long updateStaff) throws BusinessException;
    /**
     * 
     * 结合标签类型，标签名称以及状态判断标签是否已经存在。
     * 
     * @author liyong7
     * @param labelType
     * @param labelTitle
     * @param status
     * @return 
     * @since JDK 1.6
     */
    public boolean queryExist(String labelType, String labelTitle, String... status) throws BusinessException;
    /**
     * 
     * 根据主键查询出标签.<br/>
     * 
     * @author liyong7
     * @param id
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsLabelRespDTO queryByPK(Long id)throws BusinessException;
    /**
     * 
     * 编辑标签。<br/>
     * 
     * @author liyong7
     * @param gdsLabelReqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabelReqDTO) throws BusinessException;

}

