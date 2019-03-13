package com.zengshi.ecp.goods.dubbo.interfaces;

import java.util.List;

import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsMediaCategoryRespDTO;
import com.zengshi.ecp.goods.dubbo.dto.common.StringReqDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsMediaCategoryRSV {
    /**
     * 
     * 保存媒体分类. 
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
	GdsMediaCategoryRespDTO saveGdsMediaCategory(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 删除媒体分类
     * 
     * @author liyong7
     * @param reqDTO reqDTO中的ID参数必传.
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void deleteGdsMediaCategory(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 编辑媒体分类.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void editGdsMediaCategory(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 分页查询媒体分类.<br/>
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    PageResponseDTO<GdsMediaCategoryRespDTO> queryGdsMediaCategoryPaging(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据主键查询媒体分类信息,此方法reqDTO中的ID为必传参数. 
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    GdsMediaCategoryRespDTO queryGdsMediaCategoryByPK(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 根据主键值删除媒体分类.
     * 
     * @author liyong7
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void deleteGdsMediaCategoryByPK(StringReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * queryAllTopCategory:(查询所有顶级分类). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<GdsMediaCategoryRespDTO> queryRootCategory(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * querySubCategory:(查询对应分类的子分类). <br/> 
     * 
     * @author linwb3
     * @param reqDTO
     * @return 
     * @since JDK 1.6
     */
    List<GdsMediaCategoryRespDTO> querySubCategory(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
    /**
     * 
     * 根据指定媒体分类ID统计有效子分类数量.
     * 
     * @author liyong7
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public Long executeCountSubMediaCatg(GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * queryCategoryTraceUpon:获取媒体分类轨迹. <br/> 
     * 
     * @author Lenovo 
     * @param reqDTO
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    public List<GdsMediaCategoryRespDTO> queryCategoryTraceUpon(
            GdsMediaCategoryReqDTO reqDTO) throws BusinessException;
    
}

