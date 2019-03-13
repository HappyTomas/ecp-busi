package com.zengshi.ecp.goods.service.common.interfaces;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupReqDTO;
import com.zengshi.ecp.goods.dubbo.dto.GdsCatg2PGroupRespDTO;
import com.zengshi.ecp.server.front.dto.PageResponseDTO;
import com.zengshi.ecp.server.front.exception.BusinessException;

public interface IGdsCatg2PGroupSV {
	
	/**
	 * 
	 * saveGdsCatg2Prop:保存分类与属性组关联关系. 
	 * 
	 * @author liyong7
	 * @param reqDTO
	 * @throws BusinessException 
	 * @since JDK 1.6
	 */
    void saveGdsCatg2PGroup(GdsCatg2PGroupReqDTO reqDTO) throws BusinessException;

    /**
     * 
     * 批量添加分类与属性组关联关系.
     * 
     * @author liyong7
     * @param catgCode
     * @param propGroupId
     * @param createStaff
     * @param skipWhenExist
     * @throws BusinessException 
     * @since JDK 1.6
     */
    void batchAddGdsCatg2PGroups(String catgCode, List<Long> propGroupIds, Long createStaff, boolean skipWhenExist)
            throws BusinessException;

    /**
     * 
     * 分页查询出已关联属性信息,根据SORT_NO自然排序.<br/>
     * 
     * @author liyong7
     * @param dto
     * @return
     * @throws BusinessException
     * @since JDK 1.6
     */
    public PageResponseDTO<GdsCatg2PGroupRespDTO> queryConfigedGdsPropGroupRespDTOPaging(
			GdsCatg2PGroupReqDTO reqDTO) throws BusinessException;
    /**
     * 
     * 批量删除指定分类的已关联属性关系. <br/>
     * 
     * @author liyong7
     * @param catgCode
     * @param propGroupIds
     * @param updateStaff
     * @return
     * @throws DataAccessException
     * @since JDK 1.6
     */
    int batchDeleteCatg2Prop(String catgCode, List<Long> propGroupIds, Long updateStaff)
            throws BusinessException;
    /**
     * 
     * 查询出与指定分类已经关联的属性ID序列. <br/> 
     * 
     * @author liyong7
     * @param catgCode
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    List<Long> queryConfigedPropgroupsIds(String catgCode, String... status) throws BusinessException;
    /**
     * 
     * 判断选定分类是否已经与指定属性关联.
     * 
     * @author liyong7
     * @param catgCode
     * @param propId
     * @param status
     * @return
     * @throws BusinessException 
     * @since JDK 1.6
     */
    boolean queryExist(String catgCode, Long propGroupId, String... status) throws BusinessException;

}

