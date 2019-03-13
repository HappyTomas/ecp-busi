package com.zengshi.ecp.goods.dubbo.interfaces;

import com.zengshi.ecp.goods.dubbo.dto.GdsPropRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRelationRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsType2PropRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsType2PropRSV {
    
    /**
     * 
     * 查询不可编辑类型属性关联关系
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long queryUneditableType2Prop(GdsType2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 根据类型信息查询对应类型的属性
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType2PropRelationRespDTO queryTypeProps(GdsType2PropReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 分页查询出已经与指定类型关联的属性。<br/>
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsType2PropRespDTO> queryConfigedPropsPaging(GdsType2PropReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 分页查询备选属性。 
     * 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsPropRespDTO> queryOptionalPropsPaging(GdsType2PropReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 关联属性.<br/>  
     * 
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void addProps(GdsType2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 删除关联属性.<br/>  
     * 
     * @param reqDTO catgCode与propIds必须有值.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void deleteProps(GdsType2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 配置是否基础属性. 
     * 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeIsBaseConfig(GdsType2PropReqDTO reqDTO)
            throws BusinessException ;
    
    /**
     * 
     * 配置是否必填属性. 
     * 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeIsRequireConfig(GdsType2PropReqDTO reqDTO)
            throws BusinessException;
    
    /**
     * 
     * 配置是否搜索属性.
     * 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeIsSearchConfig(GdsType2PropReqDTO reqDTO)
            throws BusinessException;
    
    /**
     * 
     * 根据类型编码查询出搜索属性。<br/>
     * 
     * @param reqDTO catgCode必传。
     * @return 对返回值需要进行null判断，获取GdsCatg2PropRelationRespDTO中的searchProps,<br/>
     * searchProps无需进行null判断，如果没有返回属性则返回一个空List.<br/>
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public GdsType2PropRelationRespDTO querySearchProps(GdsType2PropReqDTO reqDTO)throws BusinessException;
    
    /**
     * 
     * 是否输入属性配置.
     * 
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public void executeIfGdsInputConfig(GdsType2PropReqDTO reqDTO ) throws BusinessException;

}

