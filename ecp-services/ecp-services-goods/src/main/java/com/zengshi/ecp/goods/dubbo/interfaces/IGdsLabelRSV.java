package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsLabelReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsLabelRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsLabelRSV {
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
     * @deprecated 该方法不建议使用。请使用{@link #editGdsLabel(GdsLabelReqDTO)}
     */
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabel, Long updateStaff)throws BusinessException;
    
    
    /**
     * 
     * 编辑标签. 
     * 
     * @author liyong7
     * @param gdsLabel
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsLabelReqDTO editGdsLabel(GdsLabelReqDTO gdsLabel)throws BusinessException;
    
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
     * @deprecated 该方法不建议使用。请使用{@link #editStatus(GdsLabelReqDTO)}
     */
    public int editStatus(Long labelId,String status,Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 编辑状态.<br/>
     * 
     * @author liyong7
     * @param reqDTO labelId,status不允许为空
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int editStatus(GdsLabelReqDTO reqDTO)throws BusinessException;
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
     * @deprecated 该方法不建议使用。
     */
    public int executeDisableGdsLabel(Long labelId,Long updateStaff) throws BusinessException;
    
    /**
     * 
     * 禁用指定标签. <br/> 
     * 
     * @author liyong7
     * @param reqDTO labelId不允许为空。
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int executeDisableGdsLabel(GdsLabelReqDTO reqDTO) throws BusinessException;
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
     * @deprecated 该方法不建议使用,请使用{@link #executeEnableGdsLabel(GdsLabelReqDTO)}
     */
    public int executeEnableGdsLabel(Long labelId,Long updateStaff) throws BusinessException;
    /**
     * 
     * 启用指定标签.<br/> 
     * 
     * @author liyong7
     * @param reqDTO labelId不允许为空
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public int executeEnableGdsLabel(GdsLabelReqDTO reqDTO) throws BusinessException;
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
     * @deprecated 该方法不建议使用，请使用{@link #queryExist(GdsLabelReqDTO)}
     */
    public boolean queryExist(String labelType, String labelTitle, String... status) throws BusinessException;
    /**
     * 
     * 结合标签类型，标签名称以及状态判断标签是否已经存在。
     * 
     * @author liyong7
     * @param reqDTO labelType,labelTitle不允许为空。
     * @param labelTitle
     * @param status
     * @return 
     * @since JDK 1.6
     */
    public boolean queryExist(GdsLabelReqDTO reqDTO) throws BusinessException;
    
    
    

}

